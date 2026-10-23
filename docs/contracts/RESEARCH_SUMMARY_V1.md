# Research summary v1 — implemented contract

2026-10-11. Applies to the real local flow, separately from legacy synthetic default-on sharing settings. Enrollment is optional, individually authorized and off until an approved HTTPS origin is compiled into the app. No Huawei service is involved.

The teacher chooses a class/week and explicitly submits it after enrollment. Projection uses only saved, included, nonempty lessons with consistent class context. It does not serialize a lesson object, recording, class name, class ID or private note. Mixed language/grade/size contexts are rejected instead of misrepresented.

| Field | Value / bound |
|---|---|
| `schema_version` | Integer 1 |
| `record_id` | Random UUID, persisted unchanged through retries |
| `collected_at_ms` | UTC-day floor of earliest included lesson/reflection creation; conservative collection/expiry anchor |
| `grade` | Integer 1–4 |
| `language` | en, am, om or sid |
| `class_size_band` | 1-39, 40-70 or 71-150 |
| `lesson_count` | Integer 1–100 |
| `recorded_minutes` | Integer 1–12000; rounded down, minimum one for nonempty selected audio |
| `rating` | Integer 1–5 or null |
| `practice` | none, understanding, reading or explanations |
| `tried` | yes, no or unknown; not-yet/does-not-fit are projected to no |
| `content_version` | Exact supported draft content release, `hawassa-coaching-2026-10-draft-1` or `hawassa-coaching-2026-10-draft-2`; new grades/cards remain outside v1 |

These fields measure recorded participation and teacher self-report, not audio interpretation or student understanding. Stable grant authentication means these summaries remain pseudonymous. A small pilot cannot be assumed anonymous simply by excluding names.

Server ingest requires a live consent grant and bearer token, uses a transaction and row lock, and stores the payload with its canonical hash. Each grant admits at most 128 summary records. Identical grant/record retries return duplicate; altered content for an existing record returns conflict. Unsupported model metrics/free text/audio are forbidden. Expired data is rejected before ingestion and hidden before physical purge.

The Android queue freezes one submitted class/week payload, keeps a local acknowledgement marker until expiry and schedules bounded retries outside recording time. Permanent rejection is retained as a local failed marker rather than endlessly retried. Token failure stops sharing and moves to withdrawal. Queued/sent markers retain the same collection-based deadline; server expiry also cannot exceed grant expiry.

Withdrawal clears the local queue immediately, disables new submissions and retains only the encrypted credential needed for remote withdrawal until acknowledged or grant expiry. Acknowledgement arriving during local deletion cannot recreate an item. Deleting/excluding a potentially submitted lesson withdraws the grant to retract all research submissions; later re-enrollment requires an appropriate consent decision. Remote deletion depends on connectivity and host operation, never an invented local-only remote-success message.

The research dashboard is permission-restricted and has only a whole-pilot view with at least five contributors. Ratings separately require five rated contributors and weight participants equally. It is not an anonymous public export; publication/repeated-release review remains a human research responsibility.

See [service runbook](../../server/pilot/README.md). Existing [measurement gates](MEASUREMENT_EVIDENCE.md) remain fail-closed; this schema admits no model-derived pedagogy measure.
