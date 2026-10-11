# ADR-019: Local recorded reflection for the Hawassa pilot

> **2026-10-11 implementation update:** [ADR-020](020-provider-independent-pilot-infrastructure.md) adds provider-independent Django/PostgreSQL research infrastructure, an optional Android HTTPS outbox, priority withdrawal and deferred local signal analysis. Earlier no-network/no-intake statements below describe the pre-ADR-020 baseline. Default builds remain disconnected; learned pedagogy inference and reviewed translations remain pending.

Date: 2026-10-10. Status: implemented development slice; field release pending validation.

## Context

The user's current brief replaces the former RAM-only capture constraint with audio retained exclusively on the teacher's phone for at most one month. The pilot runs concurrently for one month across four Hawassa primary schools/eight teachers, Grades 1–4. The 20,000 ETB budget is hardware-only. Four classroom/app languages are intended: Amharic, English, Afaan Oromo and Sidaamu Afoo.

## Decision

The launcher now opens a separate local recording flow in `pilot/`. The previous seeded demo is an explicitly named, non-exported activity accessed from Settings. Its fixtures and storage are not imported into the local flow. Existing indicator-release gates remain fail-closed and Grade 4 is added to context classification without releasing any model.

The microphone foreground service captures 16 kHz mono PCM into a bounded buffer and encodes AAC-LC at 32 kbit/s using the platform codec. Approximately 30-second ADTS chunks are encrypted with AES-GCM and atomically saved under app-private, backup-excluded storage. Each private file has its own Android Keystore key and authenticated file identity. Metadata is written only after its audio chunk commits. No unencrypted audio temporary file is created. No model dependency or network permission is added.

Screen-off recording uses a bounded wake lock. Capture stops on requested Stop, failed audio delivery, detected route change, Android microphone silencing, low disk space or a two-hour limit. Recovery marks incomplete sessions interrupted and keeps only committed chunks. Up to approximately 30 seconds in RAM may be lost on abrupt process termination; no missing interval is invented as observed audio. The app does not silently restart capture after process death or boot.

Local excerpts are decrypted one at a time into RAM for playback. Playback stops when the app leaves the foreground, when recording starts, or when the user deletes data. The app never uploads audio or notes.

The teacher sees recording duration, optional signal warnings, lesson context corrections, exclusion controls and source-linked goal-based strategies. These are not acoustic pedagogy judgments. Reflection choices and usefulness ratings persist locally. Coaching content remains draft and English; choosing another language explicitly shows the English-preview limitation. External translation APIs are not called without credentials or reviewed content. Classroom language and app language are independent settings.

Collected data expires 30 days after its creation. Cleanup runs during repository reads, app resume, periodic foreground refresh and a daily persisted Android job. Playback checks expiry before decrypting. A persisted high-water timestamp prevents clock rollback from reviving expired records. Profile configuration is separate and remains. Powered-off/force-stopped devices cannot execute physical deletion; the next allowed run resumes it. Unreadable encrypted data fails closed rather than resetting to demo fixtures.

## Scope overrides and retained boundaries

- This supersedes the no-persisted-audio part of ADR-016 **for the new local development flow**, based on the current user request. It does not authorize exporting classroom recordings.
- The former default-on private-summary/research sharing settings belong to the synthetic demo. The real flow has no transfer adapter or sharing toggle; no permission to transmit is inferred.
- Provider capability gates in ADR-015 and model evidence gates in ADR-018 remain. Zergaw runtime/database selection, research authorization, payload contract and deletion/backup verification are unresolved.
- Institutional validation, completed translations and real-device evidence are required before calling this classroom-ready. This change does not grant school/guardian authorization or claim a validated pedagogy model.

## Consequences

The offline recording/review workflow can be built and device-tested independently of cloud providers, translation APIs and model training. Teachers can listen to excerpts and correct context rather than relying on invented semantic observations. Future validated local inference must consume the actual encrypted AAC decode path, carry model/configuration provenance and pass the existing release policy before displaying pedagogical metrics.

See [device runbook](../testing/OFFLINE_PILOT_RUNBOOK.md) and [design review](../research/HAWASSA_PILOT_REVIEW_2026-10-10.md).
