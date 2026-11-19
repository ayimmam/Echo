# Echo team context and assignments — week of 9 November 2026

This is the handoff for this week's design and research work. It describes the repository as it exists now and the decisions each owner should return. **A proposed design is not an implemented or approved feature.** Use the linked source documents when a detail matters; some older architecture plans describe superseded scopes.

## Project in one page

Echo (ድምጽ) helps Ethiopian teachers reflect privately on their own classroom practice. The agreed prospective pilot is **eight teachers in four Hawassa primary schools, Grades 1–4, for one month**, after device, educator, research and hosting readiness checks. Teachers use their own Android phones, with unreliable connectivity and a target of 2 GB RAM. The hardware budget is **20,000 ETB total**. Amharic, English, Afaan Oromo and Sidaamu Afoo are intended. Grades 11–12 are a **separate development path**, not part of this primary pilot.

The Android development build currently saves class profiles, records audio on an explicit Record/Stop action, keeps encrypted chunks on the phone for up to 30 days, offers private excerpt playback and a weekly reflection. The bars show **saved recording duration only**. Practice cards are draft, selected by class goal/subject, and are not speech-derived. No speech recognition, speaker identification, released classroom model or validated pedagogical metric is in the build. The current teacher UI/coaching remains English draft content. Real-device and classroom suitability are unproven.

An optional, separately authorized research path is implemented to send only tightly constrained numeric/enumerated summaries to a Django/PostgreSQL service; real hosting has not been activated or validated. The default Android build has no configured research origin and sends nothing. Audio, private notes, transcripts and exact class identifiers are outside the research payload. A synthetic demo and its local dashboard are separate from the real recording flow. Competition/Huawei work has a separate plan and repository; do not treat the synthetic demo as field data.

Read first: [project README](../README.md), [Android guide](../edge/android/README.md), [current local recording decision](adr/019-offline-recorded-reflection-pilot.md), [research infrastructure decision](adr/020-provider-independent-pilot-infrastructure.md), [pedagogy status](PEDAGOGY_IMPLEMENTATION_STATUS.md), and [documentation index](README.md).

## This week's owners and deliverables

| Owner | Assignment | Deliverable and decision needed |
|---|---|---|
| @Paradox0315 | GitHub Actions and branching policy | A short proposed branching/PR policy plus a reviewed CI workflow change or issue list. Identify the integration branch, required reviews/checks, naming, merge method, and release tagging; confirm with the team before treating the proposal as policy. |
| @nurelaynn | UI redesign | A teacher-flow design covering setup, microphone readiness, recording/interruptions, lesson review, weekly reflection, settings/consent and deletion; include small-phone layouts, accessibility, localization space, copy, and a mapping to current Compose screens. |
| @amen_sisa | DB schema | An ER diagram and migration proposal for the **research service**, starting from existing Django models and v1/v2 contracts. State whether any Android local persistence change is actually needed; do not silently combine local audio storage with server research data. |
| Project lead | Audio analysis pipeline design | A versioned, device-bounded pipeline proposal from stored AAC chunks to quality evidence and any candidate acoustic outputs; specify evidence gates and what remains unavailable without validation. |
| @Melodyofyared_21 | Android project architectural design diagram | An as-built component/data-flow diagram plus clearly marked proposed integration points, with privacy and offline boundaries. |
| @josizeku | Research best wireless microphone and price | A dated comparison and purchase recommendation for **Saturday, 14 November 2026**, including local availability, total cost within the 20,000 ETB hardware budget, and a tested or testable Android connection path. |

Each owner should put the artifact under `docs/` (or link a design source from a Markdown summary), state assumptions and open questions, and open a focused PR for review. Cross-check changes that touch another owner's contract before implementation.

## Shared boundaries for every design

- **Teacher privacy:** do not introduce teacher ranking, child identification, transcription or audio upload. A classroom recording needs the applicable school/research permissions and consent before field use.
- **Offline first:** recording and reflection must remain useful without network. A research upload is optional, individually consented and scoped to the approved study.
- **Honest evidence:** duration and capture-quality signals cannot be labelled participation, understanding or teaching quality. Any acoustic metric needs a licensed model, ground-truth evaluation, device measurements and metric-specific release decision.
- **Separation:** primary Grades 1–4, secondary Grades 11–12 development, and synthetic demo data have distinct scope. Do not mix their records, claims or UI labels.
- **Retention:** real local collected lessons/reflections expire after 30 days; the research service enforces collection/grant-bound expiry and withdrawal. Account for deletion, backups, logs and offline devices in designs.
- **Constraints:** Android 10+ (API 29), current arm64 build, 2 GB target phones, uncertain microphone compatibility and intermittent connectivity. Treat hardware performance targets as targets until measured.

## Owner briefs

### @Paradox0315 — Actions and branches

Current remote is `ayimmam/Echo`. At this writing, `origin/claude/main` is the remote default; `version-one` and `codex/pilot-infrastructure` also exist. The working checkout is `codex/pilot-infrastructure`. **There is no documented team-wide branching policy yet.** Confirm which branch is the integration baseline before telling contributors to branch or merging anything.

The only workflow is [`.github/workflows/pilot-infrastructure.yml`](../.github/workflows/pilot-infrastructure.yml). It runs on pushes and PRs: Python 3.12 with PostgreSQL 16, Django checks/migration check/research tests plus Python suites, then Java 25/Android SDK 37 with Android JVM tests, debug assembly and lint. Review path filters, concurrency/cancellation, least-privilege permissions, action pinning, dependency caching, artifact handling, branch protection and whether device-only checks must be manual gates. Keep secrets out of CI and do not claim an APK build validates recording on a device. Existing uncommitted workflow edits in this checkout belong to ongoing work; inspect them before changing the file.

Return: an explicit policy proposal, a branch protection/check matrix and the minimal workflow changes needed to enforce it. Coordinate the chosen integration branch with every owner.

### @nurelaynn — UI redesign

The live teacher flow is under [`pilot/PilotApp.kt`](../edge/android/app/src/main/java/org/ethioware/echo/pilot/PilotApp.kt), with state in `PilotViewModel.kt`; the `ui/screens/` tree and `EchoApp.kt` include the older synthetic experience, so label mockups by flow. Start from [Android guide](../edge/android/README.md) and [pedagogy status](PEDAGOGY_IMPLEMENTATION_STATUS.md). Make Record/Stop and microphone route state obvious; explain interruptions, saved-duration meaning, privacy, expiry and deletion in plain language. Keep research contribution separately opted in. Include empty/error/permission states, screen-off return, low-literacy usability, font scaling and the four intended language lengths/scripts. Mark all untranslated or unreviewed content as draft. Return a clickable flow or screen set and a screen-to-code handoff; do not imply that visual redesign validates coaching content.

### @amen_sisa — DB schema

Start with [`server/pilot/research/models.py`](../server/pilot/research/models.py) and existing migrations, [research service guide](../server/pilot/README.md), [v1 contract](contracts/RESEARCH_SUMMARY_V1.md) and [v2 contract](contracts/RESEARCH_SUMMARY_V2.md). Existing server tables are `Grant`, `Summary`, `RateWindow` and `Maintenance`; `Summary` holds an exact validated JSON payload with a `(grant, record_id)` uniqueness constraint. Grants are study/schema scoped, participant-specific and revocable. Primary and secondary aggregate views are separately permissioned, with no individual drill-down. PostgreSQL is the real-deployment target; SQLite is only for disposable development/tests.

Return: as-built and proposed ER diagrams, field/type/index/constraint changes, migration/backfill and rollback approach, authorization/expiry/withdrawal invariants, and the expected effect on v1/v2 clients and aggregate queries. Give a data-minimization reason for every added field. Coordinate any contract change with the lead and Android owner **before** migration.

### Project lead — audio analysis pipeline

Current capture is `AudioRecord` → bounded PCM → AAC-LC 16 kHz mono, 32 kbit/s → approximately 30-second encrypted local chunks. [`AnalysisJob.kt`](../edge/android/app/src/main/java/org/ethioware/echo/pilot/AnalysisJob.kt) schedules deferred charging/battery/storage-safe work and runs signal/capture analysis only. [`SignalAnalysis` in `LocalAnalysis.kt`](../edge/android/app/src/main/java/org/ethioware/echo/pilot/LocalAnalysis.kt) does not infer silence or speakers. The [indicator reference](../indicators-core/python/README.md) and [measurement contract](contracts/MEASUREMENT_EVIDENCE.md) are specifications, not proof of a deployed model.

Design bounded chunk decoding, interruption/missing-span handling, feature/model versioning, encrypted result storage, retry/cancel behavior, CPU/RAM/battery budget, evaluation data and per-metric release gates. State whether inference is deferred until after recording or can run safely on the target phone; do not let it run during capture without measured evidence. Distinguish capture quality from acoustic role inference and from pedagogical claims. Include a failure path that leaves reflection usable and metrics unavailable.

### @Melodyofyared_21 — Android architecture diagram

Show the **current** path: `MainActivity` → Compose `PilotApp`/`PilotViewModel` → `PilotStore`/`AndroidPrivateFiles` and Keystore; `RecordingService` → encrypted chunks; local analysis job; reminders/expiry; optional encrypted research queue → approved HTTPS Django endpoint; and the isolated `SyntheticDemoActivity`. Draw arrows for audio, local metadata, summary payloads and consent, and mark the phone/host trust boundary. Add proposed analysis and redesigned UI as dashed or separately labelled boxes. Cite the [Android code map](../edge/android/README.md), [codebase map](research/CODEBASE_STRUCTURE.md) and [ADR-020](adr/020-provider-independent-pilot-infrastructure.md). The older codebase map contains pre-ADR-020 wording, so use current code/Android guide when they disagree. Mermaid in Markdown plus a PNG/SVG export is sufficient.

### @josizeku — wireless microphone purchase research

The **current recorder defaults to wired or USB external input** and does not implement Bluetooth input. A wireless kit is viable only if its receiver presents a compatible wired/USB audio input to the actual teacher phones, or if an Android route change is explicitly designed and tested. Do not assume Bluetooth mic support from a product listing. Use the [device runbook](testing/OFFLINE_PILOT_RUNBOOK.md) to define a quick pre-purchase test: recognition as selected input, screen-off 60-minute capture, route-loss behavior, playback, audibility in a 40–70 pupil room, clothing noise, battery and connector/adapter compatibility.

Compare at least three locally purchasable options with **dated seller links or quotations**, ETB price, receiver/phone connector, included adapters, warranty/return terms, battery life, maximum practical distance, number of kits possible within **20,000 ETB total**, and any shipping/tax. Recommend one option and one fallback by Friday so purchase on Saturday is possible. Prices and stock must be rechecked on purchase day. If no wireless kit passes the app/device test and budget, recommend a compatible wired/USB alternative rather than guessing.

## Coordination and finish line

1. The lead and @Paradox0315 agree on the integration branch and review rule; each owner works in a focused branch/PR from that baseline.
2. @nurelaynn and @Melodyofyared_21 align screen names and component boundaries. @amen_sisa and the lead freeze any summary or analysis contract before code changes. @josizeku checks microphone routing with the Android owner.
3. By the end of the week, each PR has a short decision summary, source links, diagram/design as applicable, unanswered questions and a verification note. Device, field and price claims must be dated and supported by direct evidence.

