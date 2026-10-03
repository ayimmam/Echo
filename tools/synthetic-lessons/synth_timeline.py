"""Seeded synthetic classroom timelines on the 10 ms tick grid (coarse-v0-draft labels).

No audio, features or speech are involved: this emits label timelines only, so it cannot carry
personal data. Output is statistically plausible *fixture* data for demos and tests, never evidence
about real classrooms.
"""
from __future__ import annotations

import random
from typing import Iterable, List, Sequence, Tuple

TICKS_PER_S = 100


def _ticks(seconds: float) -> int:
    return max(1, round(seconds * TICKS_PER_S))


def generate_timeline(
    rng: random.Random,
    duration_s: float,
    adult_share: float,
    cycle_s: float = 50.0,
    interruptions: Sequence[Tuple[float, float]] = (),
) -> List[dict]:
    """Build a tiling timeline for ``duration_s`` seconds.

    ``adult_share`` is the *target* adult fraction of adult+child speech; the realised value varies.
    ``interruptions`` are ``(start_s, length_s)`` spans overwritten as MISSING (e.g. a phone call).
    """
    total = _ticks(duration_s)
    segs: List[Tuple[str, int]] = []
    used = 0
    since_quiet_s = 0.0
    while used < total:
        adult_s = max(1.5, rng.gauss(adult_share * cycle_s, adult_share * cycle_s * 0.45))
        child_s = max(0.8, rng.gauss((1 - adult_share) * cycle_s, (1 - adult_share) * cycle_s * 0.5))
        block = [("ADULT", _ticks(adult_s)), ("NON_SPEECH", _ticks(rng.uniform(0.3, 2.5)))]
        block.append(("CHILD_UNSPECIFIED", _ticks(child_s)))
        if rng.random() < 0.12:  # a pupil starts before the adult has fully stopped
            block.insert(1, ("OVERLAP", _ticks(rng.uniform(0.2, 0.9))))
        block.append(("NON_SPEECH", _ticks(rng.uniform(0.3, 2.0))))
        since_quiet_s += adult_s + child_s
        if since_quiet_s > 420 and rng.random() < 0.35:  # quiet seatwork
            block.append(("NON_SPEECH", _ticks(rng.uniform(45, 110))))
            since_quiet_s = 0.0
        for label, n in block:
            segs.append((label, n))
            used += n
            if used >= total:
                break
    return _overlay_missing(_trim(segs, total), total, interruptions)


def _trim(segs: Iterable[Tuple[str, int]], total: int) -> List[Tuple[int, int, str]]:
    out, cursor = [], 0
    for label, n in segs:
        n = min(n, total - cursor)
        if n <= 0:
            break
        out.append((cursor, cursor + n, label))
        cursor += n
    return out


def _overlay_missing(
    segs: List[Tuple[int, int, str]], total: int, interruptions: Sequence[Tuple[float, float]]
) -> List[dict]:
    """Overwrite spans as MISSING, then merge adjacent equal labels."""
    cuts = sorted((_ticks(s), min(total, _ticks(s) + _ticks(length))) for s, length in interruptions)
    result: List[Tuple[int, int, str]] = []
    for start, end, label in segs:
        pieces = [(start, end)]
        for c0, c1 in cuts:
            nxt = []
            for a, b in pieces:
                if c1 <= a or c0 >= b:
                    nxt.append((a, b))
                    continue
                if a < c0:
                    nxt.append((a, c0))
                if c1 < b:
                    nxt.append((c1, b))
            pieces = nxt
        result.extend((a, b, label) for a, b in pieces)
    for c0, c1 in cuts:
        result.append((c0, c1, "MISSING"))
    result.sort()
    merged: List[dict] = []
    for a, b, label in result:
        if merged and merged[-1]["label"] == label and merged[-1]["end_tick"] == a:
            merged[-1]["end_tick"] = b
        else:
            merged.append({"start_tick": a, "end_tick": b, "label": label})
    return merged
