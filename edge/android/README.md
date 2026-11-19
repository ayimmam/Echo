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

1. Choose English or Amharic and set up your class. Enter the class size and keep learner names out of class details. Use Grades 1–4 for the primary pilot or Grades 11–12 for separate development testing.
2. On Home, choose the external wired/USB microphone or the phone microphone beside **Start recording**. External input is the default. **Test mic** checks the selected route without saving a clip. Android asks for microphone access when you first record or test.
3. Tap **Stop and save** in the app or the recording notification when visible. Capture has a two-hour limit and stops on detected routing, silencing, input or storage failures. If interrupted, previously saved excerpts remain in History.
4. Open a saved lesson in History to play excerpts, view the offline audio check, edit private notes and context, exclude it from reflection or delete it.
5. In Reflection, use the weekly overview, write private notes or choose one draft practice. You can also record whether you tried last week’s practice and rate its usefulness.
6. Open Settings from Home to manage classes, change language, enable weekend reminders, find built-in help or delete collected data.

The teacher pilot UI and bundled card copy are available in English and Amharic; classroom language is a separate field. The Amharic wording and source-linked cards remain drafts until fluent educator review is recorded. The app does not interpret speech, identify speakers or estimate learning. Cards are selected deterministically from the teacher's goal, grade and subject, never from an unvalidated sound pattern.

## Storage and lifecycle

`RecordingService` encodes bounded 16 kHz mono input to AAC-LC at 32 kbit/s. Approximately 30-second chunks and JSON records use authenticated AES-GCM encryption with per-file Android Keystore keys in backup-excluded app-private storage. No cleartext audio file is written. Metadata commits after its chunk; interrupted sessions recover committed chunks only, potentially losing the last uncommitted chunk.

Playback decrypts an excerpt into RAM and stops on backgrounding, recording or deletion. Active collected records expire after 30 days; class/settings configuration is separate. Ordinary lesson deletion first commits a minimal encrypted tombstone, immediately blocks the lesson from history, playback and reflection, removes its metadata, and retains only its encrypted audio until the independent 15-day deletion deadline. Delete-all and research withdrawal purge shadow-deleted audio immediately. Cleanup runs on reads, resume, foreground refresh and a persisted daily job. Force-stop/power-off delays physical cleanup until the app can run. Weekend reminders are opt-in and best-effort, not exact alarms.

The recorder verifies the chosen input before accepting PCM and stops if the route changes. The live level display uses recent input peaks in memory. The saved audio check uses clipping and near-zero counts in the encrypted lesson metadata. It runs on demand, including in airplane mode, and requires no audio decode, charging window, separate report file or model download.

The manifest includes Internet permission and forbids cleartext traffic. Research sharing remains disconnected by default; a connected build binds a reviewed HTTPS origin via the echoResearchOrigin Gradle property. Enrollment requires a private individual code and separate study acknowledgement. Only explicitly submitted numeric/enumerated reviews enter the encrypted queue; withdrawal takes priority. No audio/private note or translation API is connected. Keystore/ciphertext failures surface as errors rather than replacing real data with fixtures.

## Code map

Paths below are relative to `app/src/main/java/org/ethioware/echo/`.

| Path | Responsibility |
|---|---|
| `MainActivity.kt` | Launches the local flow and handles reminder navigation |
| `pilot/PilotApp.kt`, `pilot/PilotCaptureUi.kt` and `pilot/PilotReviewUi.kt` | Teacher navigation, onboarding, recording, history, reflection and settings |
| `pilot/PilotViewModel.kt` | Serialized background storage work, state and RAM excerpt playback |
| `pilot/RecordingService.kt` | Explicit foreground capture, route/input checks and bounded encoding |
| `pilot/MicrophoneInput.kt` and `pilot/LocalAnalysis.kt` | Shared input routing and local signal diagnostics |
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

Use the [offline runbook](../../docs/testing/OFFLINE_PILOT_RUNBOOK.md) for device acceptance and the [synthetic runbook](../../docs/testing/DEMO_DEPLOYMENT_AND_MANUAL_TESTING.md) for fixture testing. Audio checks are limited to microphone signal levels. Educator-approved translations, real hosting activation and physical K9/device validation remain release gates. See the [implementation status](../../docs/UNDERGRAD_TEACHER_MVP_IMPLEMENTATION_STATUS.md) and [infrastructure runbook](../../docs/testing/NON_HUAWEI_INFRASTRUCTURE_RUNBOOK.md).

Build admission is explicit. The default is disconnected:

```sh
./gradlew :app:assembleDebug -PechoPilotEnvironment=offline
./gradlew :app:assembleDebug -PechoPilotEnvironment=synthetic
./gradlew :app:assembleDebug -PechoPilotEnvironment=live -PechoResearchOrigin=https://APPROVED-LIVE-HOST
```

Offline and synthetic teacher builds reject any embedded research origin. Only a `live` build accepts one approved HTTPS origin. The Render blueprint is synthetic-only and is not a live Android destination.
