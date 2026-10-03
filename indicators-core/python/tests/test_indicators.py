"""Reference-implementation tests: acceptance vectors, hand-calculated golden cases, edges."""
import importlib.util
import json
import subprocess
import sys
import unittest
from pathlib import Path

ROOT = Path(__file__).resolve().parents[3]
sys.path.insert(0, str(ROOT / "indicators-core" / "python"))

from dimts_indicators.adapter import handle  # noqa: E402
from dimts_indicators.indicators import quantile  # noqa: E402

_spec = importlib.util.spec_from_file_location("acceptance_runner", ROOT / "tests/acceptance/runner.py")
runner = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(runner)


def indicators(timeline, duration, spec=2):
    return handle({
        "operation": "indicators", "contract": "acceptance-v1", "indicator_spec_version": spec,
        "cohort": "early_grade", "tick_ms": 10, "duration_ticks": duration, "timeline": timeline,
    })


class AcceptanceVectors(unittest.TestCase):
    def test_all_prepared_vectors(self):
        for case in runner.load_vectors():
            with self.subTest(case["id"]):
                runner.compare(handle(case["request"]), case["expected"])

    def test_runner_cli_with_subprocess_adapter(self):
        adapter = json.dumps([sys.executable, str(ROOT / "indicators-core/python/dimts_indicators/adapter.py")])
        proc = subprocess.run([sys.executable, str(ROOT / "tests/acceptance/runner.py"), "--adapter", adapter],
                              capture_output=True, text=True)
        self.assertEqual(proc.returncode, 0, proc.stdout)
        self.assertIn("18/18 contract cases passed", proc.stdout)


class GoldenCases(unittest.TestCase):
    def test_coarse_v0_draft(self):
        cases = json.loads((ROOT / "indicators-core/golden/coarse-v0-draft.json").read_text())
        for case in cases:
            with self.subTest(case["id"]):
                runner.compare(handle(case["request"]), case["expected"])


class Quantiles(unittest.TestCase):
    def test_linear_interpolation(self):
        self.assertEqual(quantile([2.0, 0.5, 1.0], 0.5), 1.0)
        self.assertAlmostEqual(quantile([0.5, 1.0, 2.0], 0.25), 0.75)
        self.assertAlmostEqual(quantile([0.5, 1.0, 2.0], 0.75), 1.5)
        self.assertIsNone(quantile([], 0.5))

    def test_multi_response_latency_summary(self):
        # Three teacher->single-child pairs with 0.5 s, 1.0 s and 2.0 s gaps.
        t, tl = 0, []
        for gap in (50, 100, 200):
            tl += [{"start_tick": t, "end_tick": t + 500, "label": "TEACHER", "lang": "amh"}]
            t += 500
            tl += [{"start_tick": t, "end_tick": t + gap, "label": "NON_SPEECH"}]
            t += gap
            tl += [{"start_tick": t, "end_tick": t + 100, "label": "CHILD_SINGLE"}]
            t += 100
        single = indicators(tl, t)["I2"]["single"]
        self.assertEqual(single["n"], 3)
        self.assertAlmostEqual(single["median"], 1.0)
        self.assertAlmostEqual(single["p25"], 0.75)
        self.assertAlmostEqual(single["p75"], 1.5)


class StructuralErrors(unittest.TestCase):
    def test_gap_overlap_and_short_coverage_rejected(self):
        seg = lambda a, b, l="NON_SPEECH": {"start_tick": a, "end_tick": b, "label": l}  # noqa: E731
        self.assertEqual(indicators([seg(0, 100), seg(200, 300)], 300), {"error": "timeline_not_contiguous"})
        self.assertEqual(indicators([seg(0, 200), seg(100, 300)], 300), {"error": "timeline_not_contiguous"})
        self.assertEqual(indicators([seg(0, 100)], 300), {"error": "timeline_does_not_cover_duration"})

    def test_unsupported_inputs(self):
        self.assertEqual(indicators([], 0, spec=99), {"error": "unsupported_spec_version"})
        self.assertEqual(handle({"contract": "nope"}), {"error": "unsupported_contract"})
        self.assertEqual(handle({"contract": "acceptance-v1", "operation": "x"}), {"error": "unsupported_operation"})

    def test_bad_language_rejected(self):
        tl = [{"start_tick": 0, "end_tick": 100, "label": "TEACHER", "lang": "fra"}]
        self.assertEqual(indicators(tl, 100), {"error": "invalid_language"})

    def test_early_grade_context_requires_known_grade(self):
        base = {"operation": "validate_context", "contract": "acceptance-v1", "cohort": "early_grade",
                "session_type": "early_grade_lesson", "subject": "mother_tongue"}
        self.assertTrue(handle({**base, "grade": "2"})["valid"])
        self.assertEqual(handle({**base, "grade": "7"})["reason"], "cohort_context_mismatch")


if __name__ == "__main__":
    unittest.main()
