# Echovolve Android app — demo build

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
| **Welcome (3 steps)** | Pick English / አማርኛ / Sidaamu Afoo, read the privacy notice, tick consent. |
| **Today** | Class size, microphone choice, **Check microphone (30 s)**, **Start lesson**. |
| **Lesson** | Timer + three separate numbers (elapsed / observed / *not observed*), mic state, **no score**. **Pause** and the demo-only **Simulate phone call** both mark time as *not observed*, never as silence. **Skip ahead 10 min** makes a realistic lesson quickly. |
| **End of lesson** | Optional context (what the lesson mostly was, who was speaking), *leave out of my weekly reflection*, **Save summary** or **Discard** (discard creates no record). Choosing anything but "only me, nothing played" makes the adult-voice measures *unavailable*, not zero. |
| **Lessons** | Seeded history for three weeks; tap one for its summary, to leave it out, or to delete it. |
| **Report** | Seeded "today" is **Fri 6 Nov 2026**. This week shows two measures (3 eligible lessons on 3 days). Step back one week: *"Not enough reliable observations this week"*. A follow-up question about last week's chosen practice appears once. Three practice cards: choose one (or none), open its example. |
| **Settings** | Language, sync opt-in (off by default; nothing is sent), **Withdraw and delete everything** (deletes local data at once; server deletion shows **pending** until acknowledged), **Reset demo data**. |

Everything on screen is labelled as demo data. Sidaamu Afoo has no wording yet: it shows English with a
`[SID-DRAFT]` banner rather than an invented translation. The Amharic is an unreviewed draft.

## Code map (`app/src/main/java/org/ethioware/echo`)

| Path | Role |
|---|---|
| `data/` | Models, JSON parsing, `KeyValueStore` (SharedPreferences in the app, in-memory in tests), asset loading |
| `domain/Rules.kt` | Weekly observation eligibility + practice-card/follow-up rules (mirror of `indicators-core/python/.../reporting.py`; thresholds **provisional**) |
| `domain/Session.kt` | Timestamp-based lesson state machine (running / paused / interrupted) and the 30 s readiness check |
| `domain/MockMeasure.kt` | Stand-in for model + indicator engine. Real availability rules, fake numbers |
| `EchoViewModel.kt` | All flows; time and storage are injected so they are unit-testable |
| `ui/i18n/` | Typed string keys, English source, Amharic draft, Sidaamu fallback |
| `ui/screens/`, `ui/components/`, `ui/theme/` | Compose UI (Material 3, fixed brand palette, light + dark) |

Mock data comes from the repo-level `tools/demo/mock-data/android-assets/mock/` and is packaged into the
APK as `assets/mock/*.json` by `androidComponents.onVariants` in `app/build.gradle.kts` — one source for
the app, the Python tools and the server stub. Regenerate it with `python3 tools/demo/generate_mock_data.py`.

## Tests

```sh
cd edge/android
./gradlew :app:testDebugUnitTest     # 43 JVM tests: rules, session, translations, view-model flows
```

The unit tests read the same shared mock files, and `RulesTest` asserts the same week-by-week
eligibility verdicts as the Python suite, which keeps the two implementations honest. There is no
automated UI/screenshot test yet.

## Known gaps (deliberate)

* No capture, foreground service, model or encrypted storage (Room + SQLCipher + Keystore): gate G2.
* Process death during a lesson is not recovered (the real app must show an explicitly interrupted session).
* Dates are Gregorian; Ethiopian-calendar display is open.
* Sync is a switch and a queue counter only; there is no network code.
* `compileSdk`/`targetSdk` 37 and the dependency versions are the scaffold's; lint reports newer versions.
