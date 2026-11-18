# Undergraduate teacher MVP implementation status

Updated 2026-11-17 against `UNDERGRAD_TEACHER_MVP_IMPLEMENTATION_PLAN.md`.

This file distinguishes implemented software from evidence that requires devices, people, approvals, or deployed infrastructure. “Implemented” does not mean “approved for field use.”

## Work-package status

| Work package | Software status | Remaining release evidence |
| --- | --- | --- |
| WP0 — baseline/design | Build manifest generator and deployment checklist implemented. UI traced to the repository exports and all seven listed corrections are represented. | Name the Render operator and bilingual reviewers; approve the design revision/protocol; inventory physical phones/K9 units; create the signed release manifest. |
| WP1 — offline flow | New five-tab Compose UI implemented: Home, Record, History, Reflection and Settings. Setup, class profiles, permissions, recovery, playback, correction/exclusion, support, privacy and rating have English/Amharic copy and accessible labels. Offline/synthetic builds cannot embed a research origin. | Render screenshots on supported small/large-font/TalkBack devices and complete unassisted bilingual walkthroughs. Fluent reviewer approval is still required. |
| WP2 — K9/storage | External K9/USB is the default. UI distinguishes enumeration from a short routed signal check; the check saves no clip. Capture already verifies the routed device after recording starts and fails without silent fallback. AES-GCM storage, bounded AAC chunks, recovery, 30-day active expiry and 15-day tombstone deletion are implemented. | Qualify every actual K9/phone pair and measure battery, PSS, thermal, storage, gaps and longest lesson. Run Keystore/backup/low-space device checks. |
| WP3 — feedback/reminders | Deterministic grade/subject/goal selector, English/Amharic versioned card text, rationale, action, example, non-fit warning, follow-up and 1–5 rating are implemented. Reminder defaults to Sunday 18:00, permits Saturday/Sunday after 17:00, localizes notification text and deduplicates by week. | Educator and fluent Amharic review/approval; physical timing/timezone/reboot/permission tests. Cards remain explicitly labeled draft until approval. |
| WP4 — research | Stable payload IDs, v1/v2 projections, encrypted bounded outbox, explicit submit, retry classification/backoff, whole-grant withdrawal and ingest/withdrawal locking already exist. Teacher builds only accept a live HTTPS origin when built as `live`; synthetic/offline remain disconnected. | Freeze live collection/deletion policy and run PostgreSQL concurrency/network/TLS tests on the approved topology. Targeted retraction remains intentionally unimplemented because whole-grant withdrawal is the current policy. |
| WP5 — hosting | `render.yaml` now defines synthetic-only web/PostgreSQL/purge/readiness services. Existing `/healthz`, `/readyz`, migration, purge, access-only backup and restricted aggregate viewer are retained. | Deploy to Render, set the exact host, inspect TLS/proxy/rate limits, configure alerts, test provider restore/WAL/backups/log retention, then repeat independently on the approved in-country live host. No external deployment was performed from this workspace. |
| WP6 — audit/analytics | Encrypted bounded operational audit events and exact allowlisted usability events are implemented. Audit failures do not block app state. Usability buffering is synthetic-only and has no network transport, preventing unapproved PostHog egress. | Select/approve the PostHog project, SDK/adapter, consent, retention and deletion behavior; inspect staging traffic before any forwarding is enabled. |
| WP7 — rehearsal/release | Runbooks, deployment checklist and versioned usefulness outcome (`card-usefulness-v1`) are implemented. Exactly 80% fails the strict target and missing/coverage counts are reported. | Adult K9 rehearsal, synthetic connected/load rehearsal, unassisted teacher runs, institutional/school authorization, signed release and field execution. |

## Automated evidence in this implementation pass

- Android: 100 JVM tests, zero failures/skips; debug APK assembly and lint pass.
- Django: 36 tests pass on SQLite; one PostgreSQL-only locking test is skipped because no local PostgreSQL service is running. Schema check reports no migration changes and Django system check passes.
- Other Python suites: 14 indicator, 18 server, 7 demo and 10 hosting tests pass.
- `render.yaml` parses successfully as YAML.
- No Android device/emulator is connected, so DESIGN-01, device portions of UX/MIC/STORE/REM/OFF, and FIELD-01 are not claimed complete.

## Gate decision

The code-verifiable implementation is complete for this pass. Field deployment remains blocked by the explicit human/device/provider evidence above. Do not mark the plan “fully complete” or enroll teachers until those gates are recorded in the deployment checklist.
