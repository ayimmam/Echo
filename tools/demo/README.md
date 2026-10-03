# Demo data and mock fixtures

Deterministic, **synthetic** data shared by the Android demo, the Python tools and the synthetic server
stub. Nothing here is a measurement and nothing is derived from sound, a person or a classroom.

```sh
python3 tools/demo/generate_mock_data.py            # rewrite tools/demo/mock-data/
python3 tools/demo/generate_mock_data.py --check    # CI: fail if committed files are stale
```

Pipeline: `tools/synthetic-lessons/synth_timeline.py` (seeded label timelines on a 10 ms grid) →
`indicators-core` reference engine → reduced ADR-016 lesson record (spec 3, `coarse-v0-draft`).

| File | Used by | Content |
|---|---|---|
| `mock-data/meta.json` | docs/tests | Disclaimer, reference date, spec versions |
| `mock-data/android-assets/mock/teacher_profile.json` | Android | Demo teacher (pseudonym, no real name), Grade 2, fixed "today" = 2026-11-06 |
| `…/lessons.json` | Android | 11 sealed lesson records over weeks 43–45 |
| `…/local_state.json` | Android | Phone-only state: one excluded lesson, one chosen practice card awaiting follow-up |
| `…/practice_cards.json` | Android | **Hand-authored** (not generated): 3 cards, English + Amharic draft, Sidaamu `null` |
| `mock-data/server/cohort_lessons.json` | `server/api/demo_wsgi.py` | 189 records, 20 pseudonymous teachers (O: 2, G1: 4, G2: 9, G3: 5) to show suppression |

The seeded week scenarios are intentional: week 43 → enough reliable observations; week 44 → not enough
(one phone-call lesson with 66 % coverage, one "other adult / playback" lesson); week 45 → enough, with one
lesson left out by the teacher.

Record shape (illustrative, **draft until gate G1**): `record_id` (UUIDv7), `schema_version: 3`,
`label_schema_version: "coarse-v0-draft"`, `teacher_pseudo_id`, `started_on` + `start_time` (lesson start
only), `capture{observed_s, missing_s, coverage, mic}`, `context{activity, adult_conditions}`,
`indicators{I1_adult_talk_ratio, I5_turns_per_minute}` each `{available, value | reason}`, and
`deferred_indicators` listing the measures that are not produced. No audio, features, transcript, embeddings
or timeline are ever part of a record; `tests/demo/test_mock_data.py` checks for that.

```sh
python3 -m unittest discover -s tests/demo -v
```
