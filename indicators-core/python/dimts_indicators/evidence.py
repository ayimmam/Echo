"""Fail-closed metric provenance and release decisions.

The registry starts empty. Synthetic fixtures may be rendered only in explicitly labelled demo
surfaces; model values need a matching, reviewed release for their exact method and scope.
"""
from __future__ import annotations

import math
import re
from typing import Mapping

METRIC_FIELDS = {
    "I1": "I1_adult_talk_ratio",
    "I5": "I5_turns_per_minute",
}

# Populated only through a reviewed release process. There are no released model metrics today.
METRIC_RELEASES: tuple[Mapping, ...] = ()
SYNTHETIC_MANIFESTS = {
    "tools/demo/mock-data/meta.json",
    "demo/session-simulator-v1",
}


def metric_decision(
    record: Mapping,
    metric_id: str,
    *,
    allow_synthetic_preview: bool = False,
) -> dict:
    """Return an availability decision with a stable reason, never a substitute value."""
    key = METRIC_FIELDS.get(metric_id)
    if key is None:
        return {"available": False, "reason": "unknown_metric"}
    indicators = record.get("indicators")
    if not isinstance(indicators, Mapping):
        return {"available": False, "reason": "invalid_measurement"}
    measure = indicators.get(key)
    if not isinstance(measure, Mapping):
        return {"available": False, "reason": "invalid_measurement"}
    available = measure.get("available")
    if not isinstance(available, bool):
        return {"available": False, "reason": "invalid_measurement"}
    if not available:
        if measure.get("value") is not None:
            return {"available": False, "reason": "invalid_measurement"}
        reason = measure.get("reason")
        return {
            "available": False,
            "reason": reason if isinstance(reason, str) and reason.strip() else "invalid_measurement",
        }
    if measure.get("reason") is not None:
        return {"available": False, "reason": "invalid_measurement"}
    value = measure.get("value")
    if isinstance(value, bool) or not isinstance(value, (int, float)) or not math.isfinite(value):
        return {"available": False, "reason": "invalid_measurement"}
    if (metric_id == "I1" and not 0 <= value <= 1) or (metric_id == "I5" and value < 0):
        return {"available": False, "reason": "invalid_measurement"}
    context = record.get("context")
    if not isinstance(context, Mapping) or context.get("adult_conditions") != "single_adult_no_playback":
        return {"available": False, "reason": "adult_context_not_confirmed"}

    grade = record.get("grade")
    subject = record.get("subject")
    if not isinstance(grade, str) or not grade or not isinstance(subject, str) or not subject:
        return {"available": False, "reason": "unknown_provenance"}
    expected_cohort = (
        "stem_adult" if grade == "STEM" else "early_grade" if grade in {"O", "1", "2", "3", "4"}
        else "secondary" if grade in {"11", "12"} else None
    )
    if expected_cohort is None or record.get("cohort") != expected_cohort:
        return {"available": False, "reason": "unknown_provenance"}

    schema = record.get("schema_version")
    method_version = measure.get("method_version")
    label_schema = record.get("label_schema_version")
    if (
        type(schema) is not int
        or schema < 4
        or type(record.get("measurement_provenance_version")) is not int
        or record.get("measurement_provenance_version") != 1
        or type(record.get("indicator_spec_version")) is not int
        or type(method_version) is not int
        or method_version != record.get("indicator_spec_version")
        or measure.get("label_schema_version") != label_schema
        or not isinstance(label_schema, str)
        or not label_schema
    ):
        return {"available": False, "reason": "version_mismatch"}

    origin = measure.get("origin")
    if origin == "synthetic":
        if (
            allow_synthetic_preview
            and record.get("synthetic") is True
            and measure.get("source_manifest") in SYNTHETIC_MANIFESTS
        ):
            return {"available": True, "synthetic_preview": True}
        return {"available": False, "reason": "synthetic_preview_only"}
    if origin == "observer":
        return {"available": False, "reason": "observer_not_teacher_metric"}
    if origin != "model":
        return {"available": False, "reason": "unknown_provenance"}
    if record.get("synthetic") is True:
        return {"available": False, "reason": "synthetic_preview_only"}
    if record.get("synthetic") is not False:
        return {"available": False, "reason": "unknown_provenance"}

    identity_fields = (
        "model_artifact_sha256",
        "feature_config_hash",
        "postprocess_hash",
        "evidence_manifest_hash",
        "release_id",
    )
    if any(not isinstance(measure.get(field), str) or not measure[field] for field in identity_fields):
        return {"available": False, "reason": "unknown_provenance"}
    if any(
        re.fullmatch(r"[a-fA-F0-9]{64}", measure[field]) is None
        for field in identity_fields[:-1]
    ):
        return {"available": False, "reason": "unknown_provenance"}

    expected = {
        "release_id": measure["release_id"],
        "metric_id": metric_id,
        "cohort": expected_cohort,
        "grade": record.get("grade"),
        "subject": record.get("subject"),
        "spec_version": record.get("indicator_spec_version"),
        "label_schema_version": label_schema,
        "model_artifact_sha256": measure["model_artifact_sha256"],
        "feature_config_hash": measure["feature_config_hash"],
        "postprocess_hash": measure["postprocess_hash"],
        "evidence_manifest_hash": measure["evidence_manifest_hash"],
    }
    release = next((r for r in METRIC_RELEASES if all(r.get(k) == v for k, v in expected.items())), None)
    if release is None:
        return {"available": False, "reason": "unreleased_metric"}
    if release.get("stage") == "withdrawn":
        return {"available": False, "reason": "release_withdrawn"}
    if release.get("stage") != "released":
        return {"available": False, "reason": "unreleased_metric"}
    return {"available": True, "release_id": release["release_id"]}


def measurement_signature(record: Mapping, metric_id: str) -> tuple:
    """Fields that must agree before records can be averaged together."""
    key = METRIC_FIELDS.get(metric_id)
    measure = record.get("indicators", {}).get(key, {}) if key else {}
    return (
        record.get("cohort"),
        record.get("grade"),
        record.get("subject"),
        record.get("indicator_spec_version"),
        record.get("label_schema_version"),
        measure.get("origin"),
        measure.get("method_version"),
        measure.get("label_schema_version"),
        measure.get("model_artifact_sha256"),
        measure.get("feature_config_hash"),
        measure.get("postprocess_hash"),
        measure.get("evidence_manifest_hash"),
        measure.get("release_id"),
        measure.get("source_manifest"),
    )
