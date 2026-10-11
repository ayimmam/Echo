"""Tests for the synthetic demo stub: suppression rules, fail-closed config, read-only HTTP."""
import json
import sys
import unittest
from io import BytesIO
from pathlib import Path
from wsgiref.util import setup_testing_defaults

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "indicators-core" / "python"))

from server.api import demo_wsgi  # noqa: E402
from server.config import demo_settings  # noqa: E402
from server.domain.aggregation import aggregate_by_grade  # noqa: E402
from dimts_indicators.evidence import metric_decision  # noqa: E402


def record(teacher, grade="2", i1=0.6, i5=2.0, cohort="early_grade", observed=2400, coverage=1.0,
           adult="single_adult_no_playback"):
    ind = lambda v: {  # noqa: E731
        "available": True, "value": v, "origin": "synthetic", "method_version": 3,
        "label_schema_version": "coarse-v0-draft", "source_manifest": "tools/demo/mock-data/meta.json",
    }
    return {"record_id": f"{teacher}-{i1}-{i5}", "schema_version": 4,
            "measurement_provenance_version": 1, "indicator_spec_version": 3,
            "label_schema_version": "coarse-v0-draft", "cohort": cohort, "teacher_pseudo_id": teacher,
            "grade": grade, "subject": "mother_tongue", "started_on": "2026-11-02", "synthetic": True,
            "capture": {"observed_s": observed, "coverage": coverage},
            "context": {"adult_conditions": adult},
            "indicators": {"I1_adult_talk_ratio": ind(i1), "I5_turns_per_minute": ind(i5)}}


def call(app, path="/", method="GET"):
    env = {"PATH_INFO": path, "REQUEST_METHOD": method, "wsgi.input": BytesIO()}
    setup_testing_defaults(env)
    out = {}
    def start(status, headers):
        out["status"], out["headers"] = status, dict(headers)
    out["body"] = b"".join(app(env, start))
    return out


class Suppression(unittest.TestCase):
    def cell(self, cells, grade):
        return next(c for c in cells if c["grade"] == grade)

    def test_four_teachers_hidden_five_shown(self):
        recs = [record(f"t{i}", grade="1") for i in range(4)] + [record(f"u{i}", grade="2") for i in range(5)]
        cells = aggregate_by_grade(recs)
        self.assertTrue(self.cell(cells, "1")["suppressed"])
        self.assertFalse(self.cell(cells, "2")["suppressed"])
        self.assertEqual(self.cell(cells, "2")["teachers"], 5)

    def test_suppressed_cell_reveals_nothing_else(self):
        cells = aggregate_by_grade([record(f"t{i}", grade="1") for i in range(4)])
        self.assertEqual(set(self.cell(cells, "1")), {"grade", "suppressed", "reason"})
        self.assertEqual(set(self.cell(cells, "O")), set(self.cell(cells, "1")))

    def test_counts_teachers_not_lessons(self):
        recs = [record("busy", i1=0.5 + i / 100) for i in range(10)] + [record(f"t{i}") for i in range(3)]
        self.assertTrue(self.cell(aggregate_by_grade(recs), "2")["suppressed"])  # 4 teachers, 13 lessons

    def test_each_teacher_averaged_first(self):
        recs = [record("a", i1=0.2)] * 1 + [record("b", i1=0.8, i5=2.0)] * 9
        recs += [record(f"t{i}", i1=0.5) for i in range(3)]
        recs = [dict(r, record_id=f"{n}") for n, r in enumerate(recs)]
        cell = self.cell(aggregate_by_grade(recs), "2")
        self.assertAlmostEqual(cell["I1_mean"], (0.2 + 0.8 + 0.5 * 3) / 5, places=3)

    def test_ineligible_and_other_cohort_records_excluded(self):
        good = [record(f"t{i}") for i in range(5)]
        junk = [record("stem", cohort="undergraduate_stem"), record("short", observed=300),
                record("gappy", coverage=0.5), record("playback", adult="other_adult_or_playback")]
        cell = self.cell(aggregate_by_grade(good + junk), "2")
        self.assertEqual(cell["teachers"], 5)

    def test_no_total_row(self):
        self.assertNotIn("total", {c["grade"] for c in aggregate_by_grade([record("a")])})

    def test_incompatible_provenance_hides_cell_without_revealing_count(self):
        recs = [record(f"t{i}") for i in range(5)]
        recs[-1]["indicators"]["I1_adult_talk_ratio"]["source_manifest"] = "demo/session-simulator-v1"
        cell = self.cell(aggregate_by_grade(recs), "2")
        self.assertTrue(cell["suppressed"])
        self.assertEqual(cell["reason"], "incompatible_provenance")
        self.assertEqual(set(cell), {"grade", "suppressed", "reason"})


class DemoHttp(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        records = demo_settings.load_records(demo_settings.load({}))
        cls.app = staticmethod(demo_wsgi.make_app(records))

    def test_dashboard_page(self):
        r = call(self.app)
        self.assertTrue(r["status"].startswith("200"))
        html = r["body"].decode()
        self.assertIn("SYNTHETIC DEMO DATA", html)
        self.assertIn("Hidden: fewer than 5 teachers", html)
        self.assertIn("Grade 2", html)
        for header in ("X-Content-Type-Options", "Content-Security-Policy", "Cache-Control"):
            self.assertIn(header, r["headers"])

    def test_json_shape_and_expected_suppression(self):
        data = json.loads(call(self.app, "/api/v1/demo/aggregate")["body"])
        self.assertTrue(data["synthetic"])
        cells = {c["grade"]: c for c in data["cells"]}
        self.assertTrue(cells["O"]["suppressed"] and cells["1"]["suppressed"])
        self.assertFalse(cells["2"]["suppressed"])

    def test_read_only_and_unknown_paths(self):
        self.assertTrue(call(self.app, "/", "POST")["status"].startswith("405"))
        self.assertTrue(call(self.app, "/api/v1/lessons", "PUT")["status"].startswith("405"))
        self.assertTrue(call(self.app, "/secret")["status"].startswith("404"))
        self.assertEqual(call(self.app, "/", "HEAD")["body"], b"")

    def test_no_identifiers_in_responses(self):
        for path in ("/", "/api/v1/demo/aggregate"):
            body = call(self.app, path)["body"].decode()
            self.assertNotIn("tp_", body)       # teacher pseudonyms
            self.assertNotIn("SCH-", body)      # school identifiers
            self.assertNotIn("record_id", body)


class FailClosed(unittest.TestCase):
    def test_preview_is_explicit_and_requires_declared_synthetic_source(self):
        row = record("teacher")
        preview = metric_decision(row, "I1", allow_synthetic_preview=True)
        self.assertTrue(preview["available"])
        self.assertTrue(preview["synthetic_preview"])
        self.assertEqual(metric_decision(row, "I1")["reason"], "synthetic_preview_only")
        row["indicators"]["I1_adult_talk_ratio"]["source_manifest"] = "unknown-fixture"
        self.assertEqual(
            metric_decision(row, "I1", allow_synthetic_preview=True)["reason"],
            "synthetic_preview_only",
        )

    def test_model_claim_cannot_release_a_synthetic_row_and_malformed_shape_fails_closed(self):
        row = record("teacher")
        measure = row["indicators"]["I1_adult_talk_ratio"]
        measure.update({
            "origin": "model",
            "model_artifact_sha256": "a" * 64,
            "feature_config_hash": "b" * 64,
            "postprocess_hash": "c" * 64,
            "evidence_manifest_hash": "d" * 64,
            "release_id": "release-1",
        })
        self.assertEqual(
            metric_decision(row, "I1")["reason"],
            "synthetic_preview_only",
        )
        measure["reason"] = "forged-reason"
        self.assertEqual(metric_decision(row, "I1")["reason"], "invalid_measurement")

    def test_model_measure_stays_unreleased_without_exact_reviewed_registry_entry(self):
        row = record("teacher")
        row["synthetic"] = False
        row["indicators"]["I1_adult_talk_ratio"].update({
            "origin": "model",
            "model_artifact_sha256": "a" * 64,
            "feature_config_hash": "b" * 64,
            "postprocess_hash": "c" * 64,
            "evidence_manifest_hash": "d" * 64,
            "release_id": "release-1",
        })
        self.assertEqual(metric_decision(row, "I1")["reason"], "unreleased_metric")

    def test_teacher_proxy_measurement_requires_confirmed_single_adult_context(self):
        row = record("teacher", adult="other_adult_or_playback")
        self.assertEqual(
            metric_decision(row, "I1", allow_synthetic_preview=True)["reason"],
            "adult_context_not_confirmed",
        )

    def test_refuses_other_purposes_and_public_binds(self):
        with self.assertRaises(demo_settings.ConfigError):
            demo_settings.load({"ECHO_PURPOSE": "pilot"})
        with self.assertRaises(demo_settings.ConfigError):
            demo_settings.load({"ECHO_DEMO_HOST": "0.0.0.0"})
        with self.assertRaises(demo_settings.ConfigError):
            demo_settings.load({"ECHO_DEMO_PORT": "http"})
        with self.assertRaises(demo_settings.ConfigError):
            demo_settings.load({"ECHO_DEMO_PORT": "65536"})
        with self.assertRaises(demo_settings.ConfigError):
            demo_settings.load({"ECHO_DEMO_HOST": "::1"})

    def test_refuses_unmarked_data(self):
        import tempfile
        with tempfile.TemporaryDirectory() as tmp:
            path = Path(tmp) / "x.json"
            path.write_text(json.dumps({"synthetic": True, "records": [{"synthetic": False}]}))
            with self.assertRaises(demo_settings.ConfigError):
                demo_settings.load_records(demo_settings.load({"ECHO_DEMO_DATA": str(path)}))

    def test_refuses_malformed_fixture_envelope(self):
        import tempfile
        with tempfile.TemporaryDirectory() as tmp:
            path = Path(tmp) / "x.json"
            settings = demo_settings.load({"ECHO_DEMO_DATA": str(path)})
            for data in ([], {"synthetic": True}, {"synthetic": True, "records": [None]}):
                path.write_text(json.dumps(data))
                with self.assertRaises(demo_settings.ConfigError):
                    demo_settings.load_records(settings)


if __name__ == "__main__":
    unittest.main()
