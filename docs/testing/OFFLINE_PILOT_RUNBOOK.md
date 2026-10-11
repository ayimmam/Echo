# Offline recording development build

Current workflow: 2026-10-11. See the [Android guide](../../edge/android/README.md) for build prerequisites and the [documentation index](../README.md) for current versus historical scope.

The launcher now opens real local capture, not the seeded simulator. Do not confuse successful compilation with readiness to record classrooms. Use authorized adult test audio for the checks below before field validation.

## Build and use

Open `edge/android` in Android Studio, or run from that directory:

```sh
./gradlew :app:testDebugUnitTest :app:assembleDebug :app:lintDebug --offline
```

APK: `edge/android/app/build/outputs/apk/debug/app-debug.apk`. The current scaffold requires Android 10+; inventory actual teacher devices before procurement. Native model compatibility is not established.

1. Save a class name, grade 1–4, subject, classroom language, class size and learning goal. Do not enter pupil names. More classes can be added in Settings.
2. Connect a wired/USB input and tap Record; grant microphone permission. The default refuses to start without an external input. Phone-mic testing is an explicit Settings choice. Bluetooth capture is not part of this implementation.
3. Lock the screen and teach/test normally. Stop from the app or ongoing notification. Completed audio is saved locally without a separate save screen.
4. Open the lesson's details to play a selected short excerpt on the phone, add private context, exclude the lesson or delete it. Use headphones for privacy.
5. Open Reflection, choose the class/week, review saved-duration bars, correct lesson context, choose one goal-based practice and rate the review. Next week's reflection asks whether the previous practice was tried.
6. Settings has an opt-in local weekend reminder (Android notification permission is required), and exposes the separately stored synthetic demo, language preference, mic choice and permanent deletion of collected data. No research data is sent.

## Automated checks

JVM tests cover encrypted-envelope round trips, randomized nonces, authentication failure on tampering/file substitution, exact expiry, rollback protection, playback expiry, interrupted-session recovery, crash between audio/metadata writes, orphan cleanup, exclusions, class/week isolation and deletion. These run with in-memory private-file adapters and JVM crypto; they do not certify Android Keystore or MediaCodec behavior. The legacy demo tests still run.

Python indicator tests cover Grade 4 context admission without implying model release; existing indicator/server/mock/hosting suites remain applicable.

## Device acceptance checklist

Record results per phone, OS, microphone/adapter and app revision. A failed row prevents claiming readiness for that configuration.

| Check | Expected result |
|---|---|
| Microphone denied/revoked | No capture or crash; actionable message; already committed chunks remain recoverable. |
| External input absent | Default external mode refuses to record; no automatic phone-mic fallback. |
| Unplug/change route | Capture ends with an interruption reason; inspect for any audio after the route change. |
| Screen locked for 60 minutes | Continuous committed chunks, notification Stop works, no unexplained gaps. |
| Phone call/another recording app | Capture either continues on the selected route or stops with an interruption; silenced periods must not become teaching evidence. |
| Stop immediately/after 35 seconds | Empty sessions are not described as successful audio; committed excerpts play and duration is conservative. |
| Kill process during chunk write | Reopen: interrupted status, only committed chunks, no corrupt plaintext or invented duration. |
| Reboot | No automatic microphone restart; previous interrupted session recovers when opened. |
| Low storage | Capture stops before exhausting storage; the app reports the problem. |
| Playback | First/middle/final AAC excerpt plays from RAM; leaving the app and starting capture stop playback. |
| Retention | In a disposable test installation, advance the clock past 30 days: audio, notes and reflection disappear; playback denies expired content; rollback does not restore it. |
| Backups | Verify OS backup/restore and device-transfer exclusions on the tested vendor build. |
| Corrupt ciphertext/Keystore loss | Visible storage error; no demo data substitution or silent reset. |
| Reminder | Opt in with notification permission; an eligible weekend reminder opens Reflection and is not duplicated in the same week. Opt out and verify no further reminder. Allow for Android scheduling delays. |
| Demo isolation | Open the synthetic demo from Settings: no capture starts, real lessons are absent, and resetting fixtures leaves real local records unchanged. |
| No connectivity | Capture, correction, playback and weekly report all work. No traffic should originate from this flow. |
| Battery/thermal | Measure against an idle baseline, including screen-off capture; target ≤5 additional percentage points/hour and no thermal warning. This is a proposed target, not a measured result. |
| Memory | Measure release-build PSS; proposed capture target below 200 MB. No full-lesson PCM buffer or file. |

## Explicit remaining work

- Device execution of the above, acoustic signal/coverage validation in real room conditions, and battery profiling.
- A validated, licensed compact local model and deferred inference pipeline. Present code performs capture/signal checks, not teacher/child diarization, ASR or pedagogical interpretation.
- Educator approval and fully reviewed Amharic, Afaan Oromo and Sidaamu Afoo content/UI. Non-English preferences currently disclose English-preview fallback. Addis/Hasab credentials and human Sidama translation are not present.
- Research enrollment/consent, minimized payload and authenticated outbox/intake, provider capability verification, remote retention/backup handling. These are not implemented or enabled.
- Separate synthetic Huawei deployment and confirmation of applicable competition acceptance criteria.
- Usability tests with teachers, including opt-in best-effort weekend reminders. Android background restrictions can delay notifications; reports are always available on demand.

The one-month pilot begins after readiness, not on the date of this development build. More than 70% sustained use means at least six of eight teachers; verify continued use after the pilot separately. Interviews alone measure perceived change; retain the independent-observation plan in the review.

## Verification recorded 2026-10-11

- Android debug APK assembly and lint succeeded. Lint retains existing scaffold/dependency/style warnings; no lint errors.
- 67 Android JVM tests passed; 0 failures/errors.
- Python: 12 indicator/scope, 18 server, 7 mock-data and 10 hosting-readiness tests passed during this implementation.
- `git diff --check` passed. No Android device or emulator was connected, so capture, playback, UI rendering, Android Keystore and battery behavior have not been executed on a device.
