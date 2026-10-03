"""Tick-timeline model shared by every indicator.

A timeline is an ordered, non-overlapping list of half-open ``[start_tick, end_tick)``
segments on a 10 ms grid that must exactly tile ``[0, duration_ticks)``. ``MISSING`` is
capture metadata (lost time), never a learned class and never silence.

Two label schemas exist side by side; neither is silently reinterpreted as the other
(ADR-016):

* ``early-grade-v1`` -- the original five-class contract used by the acceptance vectors
  (``indicator_spec_version`` 2).
* ``coarse-v0-draft`` -- the reduced adult / child-unspecified / overlap / non-speech
  candidate (``indicator_spec_version`` 3). DRAFT: not frozen until gate G1.
"""
from __future__ import annotations

from dataclasses import dataclass
from typing import Iterable, List, Optional

TICK_MS = 10
TICKS_PER_S = 1000 // TICK_MS

MISSING = "MISSING"
NON_SPEECH = "NON_SPEECH"
OVERLAP = "OVERLAP"

LABEL_SCHEMAS = {
    "early-grade-v1": frozenset(
        {"TEACHER", "CHILD_SINGLE", "CHILD_CHORAL", OVERLAP, NON_SPEECH, MISSING}
    ),
    "coarse-v0-draft": frozenset(
        {"ADULT", "CHILD_UNSPECIFIED", OVERLAP, NON_SPEECH, MISSING}
    ),
}

# indicator_spec_version -> label schema. Anything else is rejected, not guessed.
SPEC_VERSION_SCHEMA = {2: "early-grade-v1", 3: "coarse-v0-draft"}

LANGUAGES = ("amh", "sid", "eng", "oth", "unknown")

ADULT_LABELS = frozenset({"TEACHER", "ADULT"})
CHILD_LABELS = frozenset({"CHILD_SINGLE", "CHILD_CHORAL", "CHILD_UNSPECIFIED"})
SPEECH_LABELS = ADULT_LABELS | CHILD_LABELS | {OVERLAP}


class TimelineError(ValueError):
    """The timeline violates the structural contract."""


class LabelSchemaMismatch(TimelineError):
    """A label does not belong to the schema selected by the spec version."""


@dataclass(frozen=True)
class Segment:
    start: int
    end: int
    label: str
    lang: Optional[str] = None

    @property
    def ticks(self) -> int:
        return self.end - self.start


def schema_for_spec(spec_version: int) -> str:
    try:
        return SPEC_VERSION_SCHEMA[spec_version]
    except (KeyError, TypeError):
        raise TimelineError("unsupported_spec_version") from None


def parse_timeline(
    raw: Iterable[dict], duration_ticks: int, schema: str
) -> List[Segment]:
    """Validate a raw JSON timeline and return segments tiling ``[0, duration_ticks)``."""
    if isinstance(duration_ticks, bool) or not isinstance(duration_ticks, int) or duration_ticks < 0:
        raise TimelineError("invalid_duration")
    allowed = LABEL_SCHEMAS[schema]
    segments: List[Segment] = []
    cursor = 0
    for item in raw:
        label = item.get("label")
        if label not in allowed:
            raise LabelSchemaMismatch("label_schema_mismatch")
        start, end = item.get("start_tick"), item.get("end_tick")
        if not all(isinstance(v, int) and not isinstance(v, bool) for v in (start, end)):
            raise TimelineError("invalid_segment")
        if start != cursor or end <= start:
            raise TimelineError("timeline_not_contiguous")
        lang = item.get("lang")
        if lang is not None and lang not in LANGUAGES:
            raise TimelineError("invalid_language")
        segments.append(Segment(start, end, label, lang))
        cursor = end
    if cursor != duration_ticks:
        raise TimelineError("timeline_does_not_cover_duration")
    return segments


def merge_runs(segments: Iterable[Segment]) -> List[Segment]:
    """Join adjacent segments with the same label (language is ignored)."""
    runs: List[Segment] = []
    for seg in segments:
        if runs and runs[-1].label == seg.label and runs[-1].end == seg.start:
            runs[-1] = Segment(runs[-1].start, seg.end, seg.label)
        else:
            runs.append(Segment(seg.start, seg.end, seg.label))
    return runs
