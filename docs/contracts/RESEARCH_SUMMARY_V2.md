# Research summary v2 — optional study-scoped contract

Implemented in the Android projection and research service on 2026-10-23. This is a contract for separately authorized research submissions, not field approval or proof of educational benefit. The v1 endpoint remains available to existing primary grants and queued v1 payloads.

The operator issues a grant with an immutable `study` (`primary` or `secondary`) and `schema_version` (1 or 2). Secondary grants require v2. Enrollment returns both fields to the client; the server stores them on the grant and checks every submission against them. A client-supplied grade cannot change the authorized study. The dashboard routes are fixed and separately permissioned: `/research/` for primary and `/research/secondary/` for secondary. Neither route accepts drill-down filters. The same five-contributor suppression rule applies to each study separately.

Issue a v2 grant only after the institution approves the relevant study and consent scope:

```sh
python server/pilot/manage.py issue_enrollment --study primary --schema-version 2
python server/pilot/manage.py issue_enrollment --study secondary --schema-version 2
```

The operator enters the private consent evidence reference at the prompt. Existing default issuance remains primary v1. The app stays disconnected without a compiled approved HTTPS origin and individual enrollment. Research enrollment does not enable content or metrics that lack review.

## Exact v2 payload

Unknown fields, audio, text notes, transcripts, class identifiers and model measurements are rejected. The v2 payload has the following exact fields:

| Field | Rule |
|---|---|
| `schema_version` | Integer 2; must equal grant scope |
| `record_id` | Random UUID, unchanged on retry |
| `collected_at_ms` | UTC-day floor of earliest selected lesson or reflection creation; original deadline anchor |
| `grade` | Primary grant: 1–4; secondary grant: 11 or 12 |
| `language` | `en`, `am`, `om` or `sid`; this is declared classroom language, not speech identification |
| `class_size_band` | `1-39`, `40-70` or `71-150` |
| `lesson_count` | 0–100; zero permits reflection without recording |
| `recorded_minutes` | 0–12000; zero exactly when lesson count is zero |
| `rating` | Integer 1–5 or null; teacher self-report |
| `practice` | `none`, `understanding`, `reading`, `explanations`, or `math_reasoning`; `reading` is primary-only, `math_reasoning` secondary-only |
| `follow_up` | `unknown`, `yes`, `not_yet` or `did_not_fit` |
| `reflection_completed` | Boolean for structured local review completion; not independently observed practice |
| `content_version` | Explicit admitted development pack version; secondary requires `hawassa-coaching-2026-10-draft-2` |

The current v2 schema retains a weekly usefulness rating. The restricted dashboard first averages each contributor's available ratings, then averages contributors. It does **not** calculate a one-time final teacher-level usefulness outcome; that study outcome needs a separately frozen protocol and data capture. No learning-effect or exam prediction field exists.

Research v1 and v2 use the same locked-grant admission, exact-field validation, rate/body limits, collection-based 30-day expiry, encrypted local queue, idempotent retry and priority withdrawal. A queued item keeps its original schema and bytes across client/server upgrades. Roll out backend admission before a connected v2 client. Both cohorts require separate protocol, school authorization and consent before any real submission.
