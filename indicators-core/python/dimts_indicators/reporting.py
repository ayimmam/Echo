"""Rule-based observation eligibility (FEATURE_REVIEW A1). PROVISIONAL thresholds.

The numbers below are design safeguards to freeze on usability evidence before field testing; they
are not literature cut-offs. Values never select a practice card or a "weakness".
"""
from __future__ import annotations

from datetime import date, timedelta
from typing import Iterable, List, Mapping
from .evidence import measurement_signature, metric_decision

RULE_VERSION = "obs-eligibility-v0-draft"
MIN_OBSERVED_S = 10 * 60
MIN_COVERAGE = 0.80
MIN_LESSONS = 3
MIN_DISTINCT_DAYS = 2
CONFIRMED_ADULT_CONTEXT = "single_adult_no_playback"


def lesson_ineligible_reason(record: Mapping, excluded: bool = False) -> str | None:
    """First reason a saved lesson cannot support an observations panel, else ``None``."""
    if excluded:
        return "excluded_by_teacher"
    capture = record["capture"]
    if capture["observed_s"] < MIN_OBSERVED_S:
        return "under_10_observed_minutes"
    if capture["coverage"] < MIN_COVERAGE:
        return "coverage_below_80_percent"
    if record["context"]["adult_conditions"] != CONFIRMED_ADULT_CONTEXT:
        return "adult_context_not_confirmed"
    return None


def week_start(day: date) -> date:
    """Monday of the ISO week containing ``day``."""
    return day - timedelta(days=day.weekday())


def week_eligibility(records: Iterable[Mapping], excluded_ids: Iterable[str] = ()) -> dict:
    """Eligibility of one week's lessons (same grade/subject/spec assumed by the caller)."""
    excluded = set(excluded_ids)
    eligible: List[Mapping] = [
        r for r in records if lesson_ineligible_reason(r, r["record_id"] in excluded) is None
    ]
    days = {r["started_on"] for r in eligible}
    ok = len(eligible) >= MIN_LESSONS and len(days) >= MIN_DISTINCT_DAYS
    return {
        "rule_version": RULE_VERSION,
        "eligible": ok,
        "eligible_lessons": len(eligible),
        "distinct_days": len(days),
        "eligible_record_ids": [r["record_id"] for r in eligible],
    }


def week_metric_eligibility(
    records: Iterable[Mapping],
    metric_id: str,
    excluded_ids: Iterable[str] = (),
    *,
    allow_synthetic_preview: bool = False,
) -> dict:
    """Apply evidence and sample thresholds independently to I-1 or I-5."""
    if metric_id not in {"I1", "I5"}:
        return {"available": False, "reason": "unknown_metric", "eligible_lessons": 0, "distinct_days": 0}
    excluded = set(excluded_ids)
    eligible = [
        r for r in records
        if lesson_ineligible_reason(r, r["record_id"] in excluded) is None
    ]
    decisions = [
        (r, metric_decision(r, metric_id, allow_synthetic_preview=allow_synthetic_preview))
        for r in eligible
    ]
    supported = [r for r, decision in decisions if decision["available"]]
    signatures = {measurement_signature(r, metric_id) for r in supported}
    if len(signatures) > 1:
        return {
            "available": False,
            "reason": "incompatible_provenance",
            "eligible_lessons": 0,
            "distinct_days": 0,
            "eligible_record_ids": [],
        }
    days = {r["started_on"] for r in supported}
    ready = len(supported) >= MIN_LESSONS and len(days) >= MIN_DISTINCT_DAYS
    unavailable_reason = next(
        (
            decision["reason"]
            for _, decision in decisions
            if not decision["available"] and decision.get("reason") not in {"insufficient_speech"}
        ),
        "insufficient_observation",
    )
    return {
        "available": ready,
        "reason": None if ready else unavailable_reason,
        "eligible_lessons": len(supported),
        "distinct_days": len(days),
        "mean_coverage": sum(r["capture"]["coverage"] for r in supported) / len(supported) if ready else None,
        "eligible_record_ids": [r["record_id"] for r in supported],
    }
