"""Grade expansion admits context without releasing an unvalidated measurement."""
import sys
import unittest
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))
from dimts_indicators.cohort import validate_context, validate_model
from dimts_indicators.evidence import metric_decision


class PilotScope(unittest.TestCase):
    def test_grade_four_model_measurement_still_needs_release(self):
        measure = dict(available=True, value=0.5, reason=None, origin="model", method_version=3,
                       label_schema_version="coarse-v0-draft", release_id="not-released",
                       model_artifact_sha256="a" * 64, feature_config_hash="b" * 64,
                       postprocess_hash="c" * 64, evidence_manifest_hash="d" * 64)
        record = dict(grade="4", subject="reading", cohort="early_grade", synthetic=False,
                      schema_version=4, measurement_provenance_version=1, indicator_spec_version=3,
                      label_schema_version="coarse-v0-draft",
                      context={"adult_conditions": "single_adult_no_playback"},
                      indicators={"I1_adult_talk_ratio": measure})
        self.assertEqual({"available": False, "reason": "unreleased_metric"}, metric_decision(record, "I1"))

    def test_grade_four_context_is_supported(self):
        self.assertEqual({"valid": True}, validate_context({
            "cohort": "early_grade", "session_type": "early_grade_lesson", "grade": "4",
        }))

    def test_grade_five_and_unreleased_cohort_are_rejected(self):
        self.assertFalse(validate_context({
            "cohort": "early_grade", "session_type": "early_grade_lesson", "grade": "5",
        })["valid"])
        self.assertFalse(validate_model({"cohort": "early_grade", "model": {}})["valid"])

    def test_secondary_context_is_explicit_and_does_not_release_model(self):
        self.assertEqual({"valid": True}, validate_context({
            "cohort": "secondary", "session_type": "secondary_lesson", "grade": "11", "subject": "mathematics"}))
        self.assertFalse(validate_context({
            "cohort": "secondary", "session_type": "secondary_lesson", "grade": "10", "subject": "mathematics"})["valid"])
        self.assertFalse(validate_model({"cohort": "secondary", "model": {"supported_cohorts": ["early_grade"]}})["valid"])
