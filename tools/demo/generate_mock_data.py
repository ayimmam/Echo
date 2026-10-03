#!/usr/bin/env python3
"""Generate the deterministic SYNTHETIC mock data shared by the Android demo and server stub.

    python3 tools/demo/generate_mock_data.py            # (re)write tools/demo/mock-data/
    python3 tools/demo/generate_mock_data.py --check    # fail if committed files are stale

Pipeline: seeded label timeline -> indicators-core reference -> reduced ADR-016 lesson record.
Every value is a fixture. Nothing here is a measurement and no person, audio or transcript exists.
"""
from __future__ import annotations

import argparse
import json
import random
import sys
from datetime import date, datetime, timedelta, timezone
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT / "indicators-core" / "python"))
sys.path.insert(0, str(ROOT / "tools" / "synthetic-lessons"))

from dimts_indicators.indicators import compute_indicators  # noqa: E402
from dimts_indicators.reporting import CONFIRMED_ADULT_CONTEXT  # noqa: E402
from dimts_indicators.timeline import parse_timeline  # noqa: E402
from synth_timeline import generate_timeline  # noqa: E402

OUT = ROOT / "tools" / "demo" / "mock-data"
SCHEMA_VERSION = 3
SPEC_VERSION = 3
LABEL_SCHEMA = "coarse-v0-draft"
REFERENCE_DATE = "2026-11-06"  # Friday: the fixed "today" of the demo
DEFERRED = ["I2", "I3", "I4", "I6", "I7", "I8"]
OTHER_ADULT = "other_adult_or_playback"

DISCLAIMER = (
    "SYNTHETIC DEMO DATA. Seeded label timelines run through the draft reference engine. Not "
    "measurements of any classroom, teacher or child. Indicators are unvalidated (gates G1/G5-E)."
)


def uuid7(rng: random.Random, when: datetime) -> str:
    ms = int(when.replace(tzinfo=timezone(timedelta(hours=3))).timestamp() * 1000)
    rand_a, rand_b = rng.getrandbits(12), rng.getrandbits(62)
    value = (ms << 80) | (0x7 << 76) | (rand_a << 64) | (0b10 << 62) | rand_b
    h = f"{value:032x}"
    return f"{h[:8]}-{h[8:12]}-{h[12:16]}-{h[16:20]}-{h[20:]}"


def pseudo_id(rng: random.Random) -> str:
    return "tp_" + "".join(rng.choice("0123456789abcdefghjkmnpqrstvwxyz") for _ in range(10))


def make_record(rng, *, teacher, school, started, duration_s, adult_share, mic="builtin",
                activity="mixed", adult_conditions=CONFIRMED_ADULT_CONTEXT, interruptions=(),
                grade="2", class_size=58) -> dict:
    ticks = round(duration_s * 100)
    timeline = generate_timeline(rng, duration_s, adult_share, interruptions=interruptions)
    result = compute_indicators(parse_timeline(timeline, ticks, LABEL_SCHEMA), ticks, LABEL_SCHEMA)
    observed, missing = result["observed_s"], result["missing_s"]

    confirmed = adult_conditions == CONFIRMED_ADULT_CONTEXT
    def indicator(key, value, digits):
        if not confirmed:
            return {"available": False, "reason": "adult_context_not_confirmed"}
        if value is None:
            return {"available": False, "reason": "insufficient_speech"}
        return {"available": True, "value": round(value, digits)}

    return {
        "record_id": uuid7(rng, started),
        "schema_version": SCHEMA_VERSION,
        "indicator_spec_version": SPEC_VERSION,
        "label_schema_version": LABEL_SCHEMA,
        "cohort": "early_grade",
        "teacher_pseudo_id": teacher,
        "school_id": school,
        "grade": grade,
        "subject": "mother_tongue",
        "started_on": started.date().isoformat(),
        "start_time": started.strftime("%H:%M"),
        "duration_s": int(duration_s),
        "capture": {
            "observed_s": round(observed, 2),
            "missing_s": round(missing, 2),
            "coverage": round(observed / duration_s, 4),
            "mic": mic,
            "class_size_estimate": class_size,
        },
        "context": {"activity": activity, "adult_conditions": adult_conditions},
        "indicators": {
            "I1_adult_talk_ratio": indicator("I1", result["I1"], 3),
            "I5_turns_per_minute": indicator("I5", result["I5"], 2),
        },
        "deferred_indicators": DEFERRED,
        "synthetic": True,
    }


def at(day: str, hhmm: str) -> datetime:
    return datetime.fromisoformat(f"{day}T{hhmm}")


def build_teacher_files(seed: int = 20261106) -> dict:
    rng = random.Random(seed)
    teacher = pseudo_id(rng)
    school = "SCH-DEMO-A"

    # (day, time, minutes, adult_share, extras) -- weeks 43-45 of 2026, reference date = Fri 6 Nov.
    plan = [
        # Week 43: four good lessons on three days -> observations eligible.
        ("2026-10-19", "08:15", 40, 0.62, {"activity": "demonstration"}),
        ("2026-10-20", "09:05", 41, 0.58, {}),
        ("2026-10-22", "08:15", 39, 0.66, {"activity": "pupil_practice"}),
        ("2026-10-23", "10:20", 40, 0.60, {}),
        # Week 44: too little reliable data -> "Not enough reliable observations".
        ("2026-10-27", "08:15", 40, 0.64, {}),
        ("2026-10-29", "09:05", 38, 0.70, {"interruptions": [(600, 780)]}),  # long phone call
        ("2026-10-30", "08:15", 40, 0.55, {"adult_conditions": OTHER_ADULT}),
        # Week 45 (current): three eligible lessons; one is excluded locally by the teacher.
        ("2026-11-02", "08:15", 40, 0.61, {}),
        ("2026-11-03", "09:05", 37, 0.72, {}),
        ("2026-11-04", "08:15", 40, 0.57, {"activity": "pupil_practice"}),
        ("2026-11-05", "10:20", 41, 0.63, {"mic": "wired_lavalier"}),
    ]
    records = [
        make_record(rng, teacher=teacher, school=school, started=at(d, t), duration_s=m * 60,
                    adult_share=share, **extra)
        for d, t, m, share, extra in plan
    ]
    excluded_id = next(r["record_id"] for r in records if r["started_on"] == "2026-11-03")
    chosen_on = "2026-10-29"

    profile = {
        "synthetic": True,
        "teacher_pseudo_id": teacher,
        "display_name": "Demo teacher",
        "school_id": school,
        "school_label": "Demo school A (synthetic)",
        "grade": "2",
        "subject": "mother_tongue",
        "class_size": 58,
        "preferred_mic": "builtin",
        "reference_date": REFERENCE_DATE,
    }
    local_state = {
        "excluded_record_ids": [excluded_id],
        "chosen_card": {"card_id": "explain_after_answer", "chosen_on": chosen_on},
        "follow_ups": [],
        "dismissed_card_ids": [],
    }
    return {"profile": profile, "records": records, "local_state": local_state}


SCHOOLS = ["SCH-DEMO-A", "SCH-DEMO-B", "SCH-DEMO-C", "SCH-DEMO-D"]


def build_cohort(seed: int = 7) -> list:
    """~21 synthetic teachers over a demo woreda: three weeks, 2-4 lessons each."""
    rng = random.Random(seed)
    # (grade, teachers): O-class and Grade 1 fall below the k=5 suppression threshold on purpose.
    layout = [("O", 2), ("1", 4), ("2", 9), ("3", 5)]
    records = []
    for grade, count in layout:
        for _ in range(count):
            teacher = pseudo_id(rng)
            school = rng.choice(SCHOOLS)
            base_share = rng.uniform(0.5, 0.78)
            for week_start in ("2026-10-19", "2026-10-26", "2026-11-02"):
                monday = date.fromisoformat(week_start)
                for _ in range(rng.randint(2, 4)):
                    day = monday + timedelta(days=rng.randint(0, 4))
                    started = datetime.combine(day, datetime.min.time()) + timedelta(
                        hours=rng.choice([8, 9, 10]), minutes=rng.choice([5, 15, 20]))
                    cond = CONFIRMED_ADULT_CONTEXT if rng.random() > 0.15 else OTHER_ADULT
                    inter = [(rng.randint(200, 1500), rng.randint(60, 600))] if rng.random() < 0.12 else []
                    records.append(make_record(
                        rng, teacher=teacher, school=school, started=started,
                        duration_s=rng.randint(34, 42) * 60,
                        adult_share=min(0.9, max(0.35, rng.gauss(base_share, 0.05))),
                        adult_conditions=cond, interruptions=inter, grade=grade,
                        class_size=rng.randint(40, 70)))
    records.sort(key=lambda r: (r["started_on"], r["start_time"], r["record_id"]))
    return records


def render() -> dict:
    t = build_teacher_files()
    meta = {
        "synthetic": True,
        "disclaimer": DISCLAIMER,
        "reference_date": REFERENCE_DATE,
        "schema_version": SCHEMA_VERSION,
        "indicator_spec_version": SPEC_VERSION,
        "label_schema_version": LABEL_SCHEMA,
        "generator": "tools/demo/generate_mock_data.py",
    }
    android = OUT / "android-assets" / "mock"
    return {
        OUT / "meta.json": meta,
        android / "teacher_profile.json": {**t["profile"], "disclaimer": DISCLAIMER},
        android / "lessons.json": {"synthetic": True, "records": t["records"]},
        android / "local_state.json": t["local_state"],
        OUT / "server" / "cohort_lessons.json": {
            "synthetic": True, "disclaimer": DISCLAIMER, "records": build_cohort()},
    }


def dumps(obj) -> str:
    return json.dumps(obj, indent=2, ensure_ascii=False) + "\n"


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--check", action="store_true", help="verify committed files are up to date")
    args = parser.parse_args()
    stale = []
    for path, obj in render().items():
        text = dumps(obj)
        if args.check:
            if not path.exists() or path.read_text() != text:
                stale.append(path.relative_to(ROOT))
        else:
            path.parent.mkdir(parents=True, exist_ok=True)
            path.write_text(text)
            print(f"wrote {path.relative_to(ROOT)}")
    if stale:
        print("stale mock data (run without --check): " + ", ".join(map(str, stale)), file=sys.stderr)
        return 1
    return 0


if __name__ == "__main__":
    sys.exit(main())
