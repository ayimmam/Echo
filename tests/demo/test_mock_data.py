"""Guards for the synthetic mock data shared by the Android demo and the server stub."""
import json
import subprocess
import sys
import unittest
from datetime import date
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT / "indicators-core" / "python"))

from dimts_indicators.reporting import week_eligibility, week_start  # noqa: E402

MOCK = ROOT / "tools/demo/mock-data"
ANDROID = MOCK / "android-assets/mock"
FORBIDDEN_KEYS = {"audio", "embedding", "transcript", "text", "posterior", "features", "timeline", "name",
                  "email", "phone", "child_id", "voice"}


def walk_keys(node):
    if isinstance(node, dict):
        for k, v in node.items():
            yield k
            yield from walk_keys(v)
    elif isinstance(node, list):
        for v in node:
            yield from walk_keys(v)


def load(path):
    return json.loads(path.read_text())


class MockData(unittest.TestCase):
    def test_committed_files_match_generator(self):
        proc = subprocess.run([sys.executable, str(ROOT / "tools/demo/generate_mock_data.py"), "--check"],
                              capture_output=True, text=True)
        self.assertEqual(proc.returncode, 0, proc.stderr)

    def test_everything_marked_synthetic_and_free_of_sensitive_keys(self):
        files = [p for p in MOCK.rglob("*.json")]
        self.assertGreaterEqual(len(files), 6)
        for path in files:
            data = load(path)
            with self.subTest(path.name):
                bad = FORBIDDEN_KEYS & set(walk_keys(data))
                self.assertFalse(bad, f"{path.name} has sensitive keys {bad}")
                if path.name not in ("practice_cards.json", "local_state.json"):
                    self.assertTrue(data.get("synthetic"), path.name)
        for key in ("records",):
            for path in (ANDROID / "lessons.json", MOCK / "server/cohort_lessons.json"):
                self.assertTrue(all(r["synthetic"] for r in load(path)[key]))

    def test_records_follow_reduced_adr016_contract(self):
        for r in load(ANDROID / "lessons.json")["records"]:
            self.assertEqual((r["schema_version"], r["label_schema_version"]), (3, "coarse-v0-draft"))
            self.assertEqual(set(r["indicators"]), {"I1_adult_talk_ratio", "I5_turns_per_minute"})
            self.assertEqual(r["deferred_indicators"], ["I2", "I3", "I4", "I6", "I7", "I8"])
            self.assertAlmostEqual(r["capture"]["coverage"], r["capture"]["observed_s"] / r["duration_s"], places=3)
            self.assertAlmostEqual(r["capture"]["observed_s"] + r["capture"]["missing_s"], r["duration_s"], places=1)
            self.assertEqual(len(r["start_time"]), 5)  # lesson start only, no finer timestamps
            for ind in r["indicators"].values():
                self.assertTrue(ind["available"] or "reason" in ind)
                self.assertNotEqual(ind.get("value"), 0 if not ind["available"] else None)

    def test_weekly_scenarios_cover_eligible_and_not_enough(self):
        records = load(ANDROID / "lessons.json")["records"]
        excluded = load(ANDROID / "local_state.json")["excluded_record_ids"]
        weeks = {}
        for r in records:
            weeks.setdefault(week_start(date.fromisoformat(r["started_on"])), []).append(r)
        verdict = {w.isoformat(): week_eligibility(rs, excluded)["eligible"] for w, rs in sorted(weeks.items())}
        self.assertEqual(verdict, {"2026-10-19": True, "2026-10-26": False, "2026-11-02": True})

    def test_cohort_has_hidden_and_shown_cells(self):
        grades = {}
        for r in load(MOCK / "server/cohort_lessons.json")["records"]:
            grades.setdefault(r["grade"], set()).add(r["teacher_pseudo_id"])
        self.assertEqual({g: len(t) for g, t in grades.items()}, {"O": 2, "1": 4, "2": 9, "3": 5})

    def test_practice_cards(self):
        cards = load(ANDROID / "practice_cards.json")["cards"]
        self.assertEqual(len(cards), 3)
        for card in cards:
            for field in ("title", "action", "example", "discussion_prompt"):
                self.assertTrue(card[field]["en"] and card[field]["am"], (card["id"], field))
                self.assertIsNone(card[field]["sid"])  # never invent Sidaamu Afoo wording
            words = len(card["example"]["en"].split())
            self.assertTrue(40 <= words <= 70, f"{card['id']} example is {words} words")


if __name__ == "__main__":
    unittest.main()
