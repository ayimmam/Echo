"""Rule-based observation eligibility (FEATURE_REVIEW A1). PROVISIONAL thresholds.

The numbers below are design safeguards to freeze on usability evidence before field testing; they
are not literature cut-offs. Values never select a practice card or a "weakness".
"""
from __future__ import annotations

from datetime import date, timedelta
from typing import Iterable, List, Mapping

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
