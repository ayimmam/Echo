# Current codebase structure

Updated 2026-10-11. This is the implementation map for [ADR-019](../adr/019-offline-recorded-reflection-pilot.md). The [earlier architecture overview](../archive/ARCHITECTURE_OVERVIEW_PRE_ADR019.md) preserves the previous proposed layout. Presence of a directory or contract does not imply a deployed service.

| Location | Implemented role / status |
|---|---|
| `edge/android/app/src/main/java/org/ethioware/echo/MainActivity.kt` | Launches real offline local flow |
| `edge/android/app/src/main/java/org/ethioware/echo/pilot/` | Compose UI, class profiles, capture service, AAC encryption/storage, recovery, expiry, excerpts, draft coaching, reflection and local reminders |
| `edge/android/app/src/main/java/org/ethioware/echo/SyntheticDemoActivity.kt` | Non-exported fixture app entered from Settings; isolated legacy state |
| `edge/android/app/src/main/java/org/ethioware/echo/domain/` | Synthetic session/measurement behavior and fail-closed evidence/release rules |
| `edge/android/app/src/test/` | JVM tests for local storage/crypto and legacy fixture/evidence behavior; no device certification |
| `indicators-core/python/dimts_indicators/` | Reference arithmetic, cohort/provenance checks, timeline validation and acceptance adapter; no acoustic model |
| `server/api/demo_wsgi.py` | Loopback-only, read-only synthetic dashboard API |
| `server/domain/aggregation.py` | Synthetic aggregate/suppression behavior; not a production research intake |
| `server/security/`, `server/jobs/`, `server/db/` | Future production boundaries/contracts; runtime/database unselected |
| `tools/demo/` | Shared fixture generator and Android/server mock assets |
| `tools/check-hosting/` | Offline provider-evidence checker; cannot provision or certify a host |
| `tests/` | Demo, hosting-readiness and reference acceptance tests |
| `infra/` | Conditional Zergaw/VPS/research/Huawei target descriptions, not deployed environments |
| `docs/` | Decisions, contracts, research, validation procedures and historical plans |

## Real local dependency path

`MainActivity → PilotApp / PilotViewModel → PilotStore → AndroidPrivateFiles → private encrypted files + Android Keystore`.

`RecordingService → bounded AudioRecord input → MediaCodec AAC → PilotStore committed chunks`. Audio is not imported into the synthetic indicator engine. `WeeklyReminder` and the expiry job use local state only. No network permission, model runtime, translation API, auth/grant service, sync outbox or remote intake is connected.

See the [Android code map](../../edge/android/README.md) for individual file responsibilities and the [runbook](../testing/OFFLINE_PILOT_RUNBOOK.md) for verification limits. Grade 4 context acceptance does not change the empty model-release registry.

## Next integration boundaries

A future local model must consume the actual AAC decode path, have supported language/device evidence and pass metric-specific release gates. Research transfer needs an explicit minimized payload and separate authorization, retry/idempotency, deletion and retention behavior. Zergaw runtime selection depends on verified provider capabilities; Huawei remains a separate synthetic competition target. None of these integrations is implied by the implemented local slice.
