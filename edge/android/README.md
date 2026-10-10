# Echovolve Android app — demo build

> **Implementation status, 2026-10-09:** the app now uses Today/Reflection/Settings, independent default-ON sharing preferences for a new setup, schema-v4 measurement provenance and fail-closed metric release checks. This remains a synthetic demo: there is no consent-grant service, upload adapter, model release or destination, so preferences never send data.

A runnable, **synthetic-data** prototype of the teacher app in the ADR-016 scope: Grade 2 mother-tongue
reading, offline, no recording. It exists so the flow and wording can be seen and criticised.

> **What this build is not.** It opens **no microphone** (no `RECORD_AUDIO` permission, no foreground
> service), runs **no model**, stores nothing encrypted and sends nothing anywhere. Level bars and the
> readiness check are simulated; every indicator value is a seeded fixture. It must not be used in a
> classroom: field capture needs ethics approval, school permission and the Article 22 decision
> (ADR-016 §9) first.

## Run it

1. Open **`edge/android`** (not the repo root) in Android Studio.
2. *Device Manager → Create device* (any phone, an arm64 image with API 29+ — e.g. Pixel, API 36),
   or plug in a phone with USB debugging.
3. Press **Run ▶** (`app`). Or from a terminal:

```sh
cd edge/android
./gradlew :app:installDebug          # installs on the running emulator / attached phone
```

The Gradle wrapper provisions the JDK named in `gradle/gradle-daemon-jvm.properties`. If your shell has
no JDK, point `JAVA_HOME` at Android Studio's bundled one:
`/Applications/Android Studio.app/Contents/jbr/Contents/Home`.

## Tour (first launch)

| Step | What to try |
|---|---|
| **Welcome (3 steps)** | Pick English / አማርኛ / Sidaamu Afoo, read the privacy notice, tick consent. A skippable five-page button guide then opens automatically on first use. |
| **Today** | One primary action: preview the simulated microphone check, then start a demo lesson. Class size and microphone choice are under **Lesson details**. A status line says no sharing destination is connected. |
| **Lesson** | Timer + three separate numbers (elapsed / observed / *not observed*), clear demo microphone state, **no score**, Pause, End, and **Stop and discard**. The demo-only **Simulate phone call** marks time as *not observed*, never as silence. **Skip ahead 10 min** makes a realistic lesson quickly. |
| **End of lesson** | Optional context (what the lesson mostly was, who was speaking), *leave out of my weekly reflection*, **Save summary** or **Discard** (discard creates no record). Choosing anything but "only me, nothing played" makes the adult-voice measures *unavailable*, not zero. |
| **Reflection** | Seeded "today" is **Fri 6 Nov 2026**. I-1 and I-5 are checked independently and shown only for a compatible, provenance-checked demo path. Lesson history is a collapsed secondary view. A follow-up question appears once; choose one of three practice cards or none. |
| **Settings** | Language, separate private-summary sync and study-contribution preferences, transfer status, local withdrawal, **Show button guide**, and **Reset demo data**. Preferences start on after a new setup, but the demo has no authorisation or transfer service and sends nothing. |

Everything on screen is labelled as demo data. Sidaamu Afoo has no wording yet: it shows English with a
`[SID-DRAFT]` banner rather than an invented translation. The Amharic is an unreviewed draft.

## Code map (`app/src/main/java/org/ethioware/echo`)

| Path | Role |
|---|---|
| `data/` | Models, JSON parsing, `KeyValueStore` (SharedPreferences in the app, in-memory in tests), asset loading |
| `domain/Rules.kt`, `domain/MetricEvidence.kt` | Per-metric weekly eligibility, practice-card rules and fail-closed provenance/release policy. No model metrics are released. |
| `domain/Session.kt` | Timestamp-based lesson state machine (running / paused / interrupted) and the 30 s readiness check |
| `domain/MockMeasure.kt` | Stand-in for model + indicator engine. Real availability rules, fake numbers |
| `EchoViewModel.kt` | All flows; time and storage are injected so they are unit-testable |
| `ui/i18n/` | Typed string keys, English source, Amharic draft, Sidaamu fallback |
| `ui/screens/`, `ui/components/`, `ui/theme/` | Compose UI (Material 3, fixed light brand palette regardless of device dark mode) |

Mock data comes from the repo-level `tools/demo/mock-data/android-assets/mock/` and is packaged into the
APK as `assets/mock/*.json` by `androidComponents.onVariants` in `app/build.gradle.kts` — one source for
the app, the Python tools and the server stub. Regenerate it with `python3 tools/demo/generate_mock_data.py`.

## Tests

```sh
cd edge/android
./gradlew :app:testDebugUnitTest :app:assembleDebug
```

The unit tests read the same shared mock files, and `RulesTest` asserts the same week-by-week
eligibility verdicts as the Python suite, which keeps the two implementations honest. There is no
automated UI/screenshot test yet.

For installation and a device-by-device manual pass, use the [synthetic demo runbook](../../docs/testing/DEMO_DEPLOYMENT_AND_MANUAL_TESTING.md).

## Known gaps (deliberate)

* No capture, foreground service, model or encrypted storage (Room + SQLCipher + Keystore): gate G2.
* Process death during a lesson is not recovered (the real app must show an explicitly interrupted session).
* Dates are Gregorian; Ethiopian-calendar display is open.
* Sharing preferences persist independently, but there are no grants, outboxes, acknowledgements or network code. Old queued demo state is discarded during migration; the app never claims it was sent.
* `compileSdk`/`targetSdk` 37 and the dependency versions are the scaffold's; lint reports newer versions.
