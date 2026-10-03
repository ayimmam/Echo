"""Reference indicator engine over a tick timeline (contract: acceptance-v1).

Only denominators and boundary rules proven by ``tests/acceptance/vectors.json`` are
fixed. Everything the vectors deliberately leave open (quantile estimator, how OVERLAP
interacts with turns) is marked DRAFT below and must be frozen at gate G1.

Availability follows the label schema: the coarse draft schema cannot separate single
from choral child speech or identify languages, so I2/I3/I4/I6 are unavailable (``None``)
for it. Unavailable means "not measured", never zero.
"""
from __future__ import annotations

from typing import Dict, List, Optional, Sequence

from .timeline import (
    ADULT_LABELS,
    CHILD_LABELS,
    LANGUAGES,
    MISSING,
    NON_SPEECH,
    OVERLAP,
    SPEECH_LABELS,
    TICKS_PER_S,
    Segment,
    merge_runs,
)

MAX_RESPONSE_GAP_TICKS = 10 * TICKS_PER_S  # inclusive: exactly 10 s counts, 10.01 s does not
MIN_INDIVIDUAL_RESPONSE_TICKS = TICKS_PER_S // 2  # 0.5 s
BIN_TICKS = 60 * TICKS_PER_S
LONG_SILENCE_TICKS = 60 * TICKS_PER_S

# Indicators the coarse draft schema cannot produce, with the reason shown to callers.
COARSE_UNAVAILABLE = {
    "I2": "requires_single_vs_choral_labels",
    "I3": "requires_single_vs_choral_labels",
    "I4": "requires_single_vs_choral_labels",
    "I6": "requires_language_identification",
}


def quantile(values: Sequence[float], q: float) -> Optional[float]:
    """Linear-interpolation quantile (DRAFT: the estimator is not frozen until G1)."""
    if not values:
        return None
    ordered = sorted(values)
    pos = (len(ordered) - 1) * q
    lo = int(pos)
    hi = min(lo + 1, len(ordered) - 1)
    return ordered[lo] + (ordered[hi] - ordered[lo]) * (pos - lo)


def _latency_summary(gaps_ticks: List[int]) -> dict:
    seconds = [t / TICKS_PER_S for t in gaps_ticks]
    return {
        "n": len(seconds),
        "median": quantile(seconds, 0.5),
        "p25": quantile(seconds, 0.25),
        "p75": quantile(seconds, 0.75),
    }


def _role(label: str) -> Optional[str]:
    if label in ADULT_LABELS:
        return "adult"
    if label in CHILD_LABELS:
        return "child"
    return None  # NON_SPEECH, OVERLAP and MISSING are not turns


def _alternations(segments: Sequence[Segment]) -> List[int]:
    """Start ticks of each adult<->child role change.

    NON_SPEECH never breaks a turn sequence (no silence-gap cap). OVERLAP is skipped
    (DRAFT). MISSING ends the uninterrupted span: no alternation is bridged across it.
    The tick of an alternation is the start of the destination turn, so a change on an
    exact minute boundary belongs to the destination bin.
    """
    ticks: List[int] = []
    prev_role: Optional[str] = None
    for seg in segments:
        if seg.label == MISSING:
            prev_role = None
            continue
        role = _role(seg.label)
        if role is None:
            continue
        if prev_role is not None and role != prev_role:
            ticks.append(seg.start)
        prev_role = role
    return ticks


def _response_gaps(runs: Sequence[Segment]) -> Dict[str, List[int]]:
    """Gap between an adult turn and the next child turn, split single / choral."""
    gaps: Dict[str, List[int]] = {"single": [], "choral": []}
    for i, run in enumerate(runs):
        if run.label not in ADULT_LABELS:
            continue
        j = i + 1
        if j < len(runs) and runs[j].label == NON_SPEECH:
            j += 1  # only pure non-speech may sit in the gap; OVERLAP/MISSING break the pair
        if j >= len(runs):
            continue
        nxt = runs[j]
        gap = nxt.start - run.end
        if gap > MAX_RESPONSE_GAP_TICKS:
            continue
        if nxt.label == "CHILD_SINGLE":
            gaps["single"].append(gap)
        elif nxt.label == "CHILD_CHORAL":
            gaps["choral"].append(gap)
    return gaps


def compute_indicators(segments: Sequence[Segment], duration_ticks: int, schema: str) -> dict:
    """Return the normalised acceptance-v1 result for a validated timeline."""
    coarse = schema == "coarse-v0-draft"
    runs = merge_runs(segments)

    missing_ticks = sum(s.ticks for s in segments if s.label == MISSING)
    observed_ticks = duration_ticks - missing_ticks
    observed_s = observed_ticks / TICKS_PER_S
    observed_min = observed_s / 60

    adult = sum(s.ticks for s in segments if s.label in ADULT_LABELS)
    speech = sum(s.ticks for s in segments if s.label in SPEECH_LABELS)
    i1 = adult / speech if speech else None

    alternations = _alternations(segments)
    i5 = len(alternations) / observed_min if observed_ticks else None

    # I7: share of wholly observed 60 s bins (final partial bin included) with an alternation.
    eligible = with_turn = 0
    missing_by_bin: Dict[int, bool] = {}
    for s in segments:
        if s.label == MISSING:
            for b in range(s.start // BIN_TICKS, (s.end - 1) // BIN_TICKS + 1):
                missing_by_bin[b] = True
    n_bins = -(-duration_ticks // BIN_TICKS)  # ceil
    turn_bins = {t // BIN_TICKS for t in alternations}
    for b in range(n_bins):
        if missing_by_bin.get(b):
            continue
        eligible += 1
        with_turn += b in turn_bins
    i7 = with_turn / eligible if eligible else None

    i8 = sum(1 for r in runs if r.label == NON_SPEECH and r.ticks >= LONG_SILENCE_TICKS)

    result: dict = {
        "observed_s": observed_s,
        "missing_s": missing_ticks / TICKS_PER_S,
        "I1": i1,
        "I5": i5,
        "I7": i7,
        "I8": i8,
    }

    if coarse:
        empty_latency = {"n": 0, "median": None, "p25": None, "p75": None}
        result.update(
            {
                "I2": {"single": dict(empty_latency), "choral": dict(empty_latency)},
                "I3": None,
                "I4": {"count": 0, "per_10min": None},
                "I6": None,
                "unavailable": dict(COARSE_UNAVAILABLE),
            }
        )
        return result

    gaps = _response_gaps(runs)
    result["I2"] = {k: _latency_summary(v) for k, v in gaps.items()}

    single = sum(s.ticks for s in segments if s.label == "CHILD_SINGLE")
    choral = sum(s.ticks for s in segments if s.label == "CHILD_CHORAL")
    result["I3"] = choral / (single + choral) if (single + choral) else None

    count = sum(
        1 for r in runs if r.label == "CHILD_SINGLE" and r.ticks >= MIN_INDIVIDUAL_RESPONSE_TICKS
    )
    result["I4"] = {
        "count": count,
        "per_10min": count / observed_min * 10 if observed_ticks else None,
    }

    teacher_by_lang = {lang: 0 for lang in LANGUAGES}
    for s in segments:
        if s.label == "TEACHER":
            teacher_by_lang[s.lang or "unknown"] += s.ticks
    teacher_total = sum(teacher_by_lang.values())
    result["I6"] = (
        {lang: t / teacher_total for lang, t in teacher_by_lang.items()} if teacher_total else None
    )
    return result
