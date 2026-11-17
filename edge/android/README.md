# Echo Android development build

Updated 2026-10-23. The launcher opens real offline recording and weekly reflection. This is a development build awaiting device and classroom validation. The [root README](../../README.md) defines the primary pilot brief; [ADR-019](../../docs/adr/019-offline-recorded-reflection-pilot.md) defines the current data boundary. [Implementation status](../../docs/PEDAGOGY_IMPLEMENTATION_STATUS.md) records the newer pedagogy and Grades 11–12 development work.

## Build and install

Open this directory in Android Studio. Requirements: Android SDK 37, a compatible JDK and Gradle dependencies; target device Android 10+ (API 29). The current scaffold declares an arm64 ABI filter. Compatibility/performance on teachers' 2 GB phones is not yet measured.

```sh
./gradlew :app:testDebugUnitTest :app:assembleDebug :app:lintDebug --offline
./gradlew :app:installDebug
```

The first command needs cached dependencies for offline use; the second needs a connected device/emulator. If your shell lacks a JDK, Android Studio on macOS commonly bundles one at `/Applications/Android Studio.app/Contents/jbr/Contents/Home`.

Output: `app/build/outputs/apk/debug/app-debug.apk`. There are no separate synthetic/recording product flavors: both activities are in this APK.

## Teacher workflow

1. Save class details: grade 1–4 for the primary pilot, or Grade 11/12 for separate development testing; subject, language, class size and goal. Avoid pupil names.
2. Connect a wired/USB microphone and tap Record. Grant microphone permission; notification permission supports reminders/notification visibility. External input is required by default; phone-microphone testing is an explicit Settings option. Bluetooth input is not implemented.
3. Stop in the app or recording notification. Committed audio saves locally without an additional confirmation screen. Capture has a two-hour limit and stops on detected routing/silencing/input/storage failures.
4. Open lesson details for private excerpt playback, context corrections, exclusion or deletion.
5. In Reflection, choose class/week, inspect duration bars and choose one draft practice. Record whether last week's practice was tried and rate usefulness.
6. In Settings, add classes, change language/microphone preference, opt into weekend reminders or delete collected data.

The teacher pilot UI and bundled card copy are available in English and Amharic; classroom language is a separate field. The Amharic wording and source-linked cards remain drafts until fluent educator review is recorded. The app does not interpret speech, identify speakers or estimate learning. Cards are selected deterministically from the teacher's goal, grade and subject, never from an unvalidated sound pattern.

## Storage and lifecycle

`RecordingService` encodes bounded 16 kHz mono input to AAC-LC at 32 kbit/s. Approximately 30-second chunks and JSON records use authenticated AES-GCM encryption with per-file Android Keystore keys in backup-excluded app-private storage. No cleartext audio file is written. Metadata commits after its chunk; interrupted sessions recover committed chunks only, potentially losing the last uncommitted chunk.

Playback decrypts an excerpt into RAM and stops on backgrounding, recording or deletion. Active collected records expire after 30 days; class/settings configuration is separate. Ordinary lesson deletion first commits a minimal encrypted tombstone, immediately blocks the lesson from history, playback and reflection, removes its metadata, and retains only its encrypted audio until the independent 15-day deletion deadline. Delete-all and research withdrawal purge shadow-deleted audio immediately. Cleanup runs on reads, resume, foreground refresh and a persisted daily job. Force-stop/power-off delays physical cleanup until the app can run. Weekend reminders are opt-in and best-effort, not exact alarms.

The manifest includes Internet permission and forbids cleartext traffic. Research sharing remains disconnected by default; a connected build binds a reviewed HTTPS origin via the echoResearchOrigin Gradle property. Enrollment requires a private individual code and separate study acknowledgement. Only explicitly submitted numeric/enumerated reviews enter the encrypted queue; withdrawal takes priority. No audio/private note or translation API is connected. Keystore/ciphertext failures surface as errors rather than replacing real data with fixtures.

## Code map

Paths below are relative to `app/src/main/java/org/ethioware/echo/`.

| Path | Responsibility |
|---|---|
| `MainActivity.kt` | Launches the local flow and handles reminder navigation |
| `pilot/PilotApp.kt` | Home, Record, History, Reflection and Settings Compose UI |
| `pilot/PilotViewModel.kt` | Serialized background storage work, state and RAM excerpt playback |
| `pilot/RecordingService.kt` | Explicit foreground capture, route/input checks and bounded encoding |
| `pilot/PilotData.kt` | Profiles, lessons, reflection, draft cards, recovery and expiry rules |
| `pilot/AuthenticatedEnvelope.kt` | Versioned authenticated encryption envelope |
| `pilot/AndroidPrivateFiles.kt` | Keystore/AtomicFile adapter, runtime and expiry job |
| `pilot/WeeklyReminder.kt` | Opt-in local weekend notification and deduplication |
| `SyntheticDemoActivity.kt` | Non-exported entry point to the isolated fixture app |
| `EchoViewModel.kt`, `data/`, `ui/` | Legacy synthetic state, SharedPreferences and UI |
| `domain/` | Synthetic session/measurement rules and fail-closed evidence policy |

The separate synthetic demo opens through Settings. Its fixture values, language drafts, default-on sharing preferences and demo reset apply only inside that activity. It does not record or send data. Shared fixtures live at `tools/demo/mock-data/android-assets/mock/` in the repository and are packaged by Gradle.

## Verification and remaining work

On 2026-10-11, 82 JVM tests passed, debug assembly succeeded and lint had no errors (existing warnings remain). JVM tests cover storage/recovery/retention, encryption envelopes and synthetic evidence rules. They do not execute Android Keystore, AudioRecord, MediaCodec or rendered UI.

No device/emulator was connected for the latest verification. Use the [offline runbook](../../docs/testing/OFFLINE_PILOT_RUNBOOK.md) for device acceptance and the [synthetic runbook](../../docs/testing/DEMO_DEPLOYMENT_AND_MANUAL_TESTING.md) for fixture testing. A short routed-input signal check, deferred capture-quality analysis and research enrollment/transfer are implemented. Learned inference is intentionally unavailable. Educator-approved translations, real hosting activation and physical K9/device validation remain release gates. See the [implementation status](../../docs/UNDERGRAD_TEACHER_MVP_IMPLEMENTATION_STATUS.md) and [infrastructure runbook](../../docs/testing/NON_HUAWEI_INFRASTRUCTURE_RUNBOOK.md).

Build admission is explicit. The default is disconnected:

```sh
./gradlew :app:assembleDebug -PechoPilotEnvironment=offline
./gradlew :app:assembleDebug -PechoPilotEnvironment=synthetic
./gradlew :app:assembleDebug -PechoPilotEnvironment=live -PechoResearchOrigin=https://APPROVED-LIVE-HOST
```

Offline and synthetic teacher builds reject any embedded research origin. Only a `live` build accepts one approved HTTPS origin. The Render blueprint is synthetic-only and is not a live Android destination.
