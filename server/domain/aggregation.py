"""Minimum-group suppression for synthetic aggregate previews (framework-free).

Rules (README s3.2, SEC architecture): count *distinct teachers*, not lessons; average each teacher
first, then across teachers (equal weighting); hide any cell with fewer than K_MIN teachers and reveal
nothing else about it (no counts); fixed cohort and window, no totals row that could be differenced.
This is "minimum-group suppression", not anonymity, and it has not been tested against repeated
releases (complementary-suppression tests are still required before any real release).
"""
from __future__ import annotations

from collections import defaultdict
from statistics import fmean
from typing import Iterable, List, Mapping

from dimts_indicators.evidence import metric_decision, measurement_signature
from dimts_indicators.reporting import lesson_ineligible_reason

K_MIN = 5
COHORT = "early_grade"  # STEM adult records never enter early-grade aggregates (ADR-014)
GRADE_ORDER = ("O", "1", "2", "3")


def _value(record: Mapping, key: str, metric_id: str):
    decision = metric_decision(record, metric_id, allow_synthetic_preview=True)
    if not decision["available"]:
        return None
    ind = record["indicators"][key]
    return ind["value"] if ind.get("available") else None


def aggregate_by_grade(records: Iterable[Mapping], k: int = K_MIN) -> List[dict]:
    per_teacher: dict = defaultdict(lambda: defaultdict(lambda: {"I1": [], "I5": [], "signatures": set()}))
    for r in records:
        if (
            r.get("synthetic") is not True
            or r.get("schema_version") != 4
            or r.get("cohort") != COHORT
            or lesson_ineligible_reason(r) is not None
        ):
            continue
        i1 = _value(r, "I1_adult_talk_ratio", "I1")
        i5 = _value(r, "I5_turns_per_minute", "I5")
        if i1 is None or i5 is None:
            continue
        bucket = per_teacher[r["grade"]][r["teacher_pseudo_id"]]
        bucket["I1"].append(i1)
        bucket["I5"].append(i5)
        bucket["signatures"].add((measurement_signature(r, "I1"), measurement_signature(r, "I5")))

    cells = []
    for grade in GRADE_ORDER:
        teachers = {
            teacher: values
            for teacher, values in per_teacher.get(grade, {}).items()
            if values["I1"] and values["I5"] and len(values["signatures"]) == 1
        }
        release_signatures = {next(iter(values["signatures"])) for values in teachers.values()}
        if len(teachers) < k or len(release_signatures) != 1:
            # Empty and small cells look identical on purpose: no count, no value.
            reason = f"fewer_than_{k}_teachers" if len(teachers) < k else "incompatible_provenance"
            cells.append({"grade": grade, "suppressed": True, "reason": reason})
            continue
        cells.append({
            "grade": grade,
            "suppressed": False,
            "teachers": len(teachers),
            "I1_mean": round(fmean(fmean(t["I1"]) for t in teachers.values()), 3),
            "I5_mean": round(fmean(fmean(t["I5"]) for t in teachers.values()), 2),
        })
    return cells
