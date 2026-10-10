# Synthetic demo: local deployment and manual testing

This runbook is for the **offline Android reflection demo** and the **read-only synthetic dashboard** in this repository. Use only the checked-in synthetic fixtures. Neither component is a field build: the phone does not open a microphone, run a model, encrypt its demo state or send data; the dashboard has no accounts, ingest or production hosting configuration. There is no production deployment command in this repository.

## 1. Prepare and run automated checks

From the repository root, use Python 3.10+ and run:

```sh
python3 tools/demo/generate_mock_data.py --check
python3 -m unittest discover -s indicators-core/python/tests -v
python3 -m unittest discover -s tests/demo -v
python3 -m unittest discover -s server/tests -v
python3 -m unittest discover -s tests/infrastructure -v
python3 -m unittest discover -s tests/acceptance -p 'test_harness.py' -v
python3 tests/acceptance/runner.py --adapter '["python3", "indicators-core/python/dimts_indicators/adapter.py"]'
```

The acceptance adapter checks the **draft Python indicator reference** against 18 contract vectors. It does not exercise Android capture, inference or a field service. The separate [post-implementation test plan](POST_IMPLEMENTATION_TEST_PLAN.md) and [evidence/sharing/UI plan](EVIDENCE_SHARING_UI_TEST_PLAN.md) remain future gate procedures, not completed test results.

For Android, open `edge/android` in Android Studio, let Gradle sync, and use an arm64 Android API 29+ emulator or device. The installed SDK must include compile SDK 37. In a terminal:

```sh
cd edge/android
./gradlew :app:testDebugUnitTest :app:assembleDebug
```

If the shell cannot find a JDK, set `JAVA_HOME` to an installed JDK; on macOS, Android Studio commonly bundles one at `/Applications/Android Studio.app/Contents/jbr/Contents/Home`. The debug APK is `edge/android/app/build/outputs/apk/debug/app-debug.apk`. The JVM suite does not replace a device UI pass.

## 2. Install and test the Android demo

Start the emulator or connect one debug-enabled arm64 device, then press **Run** in Android Studio or run:

```sh
cd edge/android
./gradlew :app:installDebug
```

Use **Settings → Reset demo data** to return to first launch. This deletes your local demo choices and restores seeded synthetic lessons. The fixture's fixed “today” is **Friday 6 November 2026**; it intentionally does not follow the phone clock.

Record the device model, Android version/API, app commit, test date and pass/fail for each row. Use no real names, classrooms, audio or participant data.

| Manual action | Expected result |
|---|---|
| On first launch, choose English, read the three welcome steps, leave the agreement unchecked, then check it. | The final welcome action is blocked until the check is selected. A five-page control guide opens before Today, with Next, Back and Skip. Every screen identifies the app as a synthetic demo. |
| Move through the guide, finish it, relaunch the app, then open **Settings → Show button guide**. Also try Skip and **Reset demo data**. | The guide appears automatically only until finished or skipped, stays dismissed after relaunch, can be reopened at any time, and appears again after a full demo reset and setup. |
| On Today, open **Lesson details**, change class size and microphone choice, then run the readiness preview. | Choices are reflected in the lesson. Readiness says it is simulated, completes after 30 seconds or **Finish now**, and presents advice without an accuracy claim. The OS never asks for microphone permission. |
| Start a lesson; pause and resume; simulate a phone call and end it; use **Skip ahead 10 min**; end the lesson. | Running, paused and interrupted states are clear. Paused/interrupted time increases **not observed**, not observed speech. No live score appears. |
| At the end, choose **Unsure** or **other adult/playback** and save. Repeat once and choose **single adult/no playback**. | The first saved lesson shows I-1 and I-5 as unavailable, never zero. A supported synthetic lesson shows illustrative values and provenance warnings. |
| Start another lesson and use **Stop and discard**; also try **Discard** on the end screen. | Neither action adds a lesson to Reflection history. |
| Open Reflection and move between seeded weeks; expand history; open a lesson; exclude and include it. | The current week can show separate I-1/I-5 eligibility and sample/coverage detail. The insufficient week stays unavailable. Exclusion changes local weekly eligibility and can be reversed; it does not claim to change sharing. |
| Answer the pending practice follow-up; choose, inspect, change and remove a card. | The follow-up appears once; card choice is a teacher action, not an automatic score prescription. |
| In Settings, independently switch private-summary sync and research contribution OFF and ON, then relaunch. | New-setup preferences start ON and changes persist independently. Transfer status still says no destination is connected; no sent/queued/deleted-remote claim appears. |
| Check English, Amharic and Sidaamu Afoo; enable large font and TalkBack/screen reader. | Navigation, switches, buttons and dialogs remain operable. Amharic is marked draft; Sidaamu Afoo uses an explicit `[SID-DRAFT]` English fallback. Record clipping, unreadable labels or missing focus as defects. |
| Change the phone between light and dark appearance while the app is open and after relaunch. | The app and system-bar icons retain the same readable light appearance in both device modes. |
| Save a demo lesson, relaunch, then use **Withdraw** in Settings and relaunch again. | Saved demo state persists before withdrawal. Withdrawal empties local lessons, choices and preferences and stays withdrawn after relaunch. Its message says local-only; **Start again** restores the synthetic setup. |

Also inspect the installed app's permissions in Android Settings: it should have **no microphone or internet permission**. These manual steps verify user-visible behavior; they do not establish accessibility certification, field validity or Android performance on the target 2 GB phone.

## 3. Run and inspect the dashboard locally

From the repository root, start the loopback-only WSGI preview:

```sh
python3 server/api/demo_wsgi.py
```

Open <http://127.0.0.1:8000/> in a browser. In a second terminal:

```sh
curl -sS http://127.0.0.1:8000/healthz
curl -sS http://127.0.0.1:8000/api/v1/demo/aggregate
curl -i -X POST http://127.0.0.1:8000/api/v1/demo/aggregate
```

The page must say **SYNTHETIC DEMO DATA**. The JSON must say `"synthetic": true` and `"k_min": 5`. O-class and Grade 1 are suppressed with no teacher counts or metric values; Grades 2 and 3 are visible. No teacher, school or record identifiers should appear. The POST must return **405 Method Not Allowed**. Stop the local server with Ctrl-C. The server refuses a public bind such as `ECHO_DEMO_HOST=0.0.0.0`; do not put this preview behind a public reverse proxy.

## 4. Before any real deployment or field test

There is **no implemented production backend, sync path, model release, real dashboard deployment or field APK** to publish. The [infrastructure README](../../infra/README.md) identifies the possible destinations, and [ADR-015](../adr/015-capability-gated-hosting.md) determines stack selection only after provider evidence. The hosting checker can review a private `capabilities.local.json` declaration, but a passing result is not a deployment or security approval:

```sh
python3 tools/check-hosting/check_hosting.py infra/in-country/shared-hosting/capabilities.local.json
```

Before a field build or external hosting, complete the relevant G0–G5 gates in the [post-implementation plan](POST_IMPLEMENTATION_TEST_PLAN.md): approved cohort/consent and Article 22 decisions, selected and provisioned in-country host where applicable, implemented security and withdrawal paths, released model evidence for each metric, device measurements, and the HOST/SEC and EVID/SHARE/UI procedures. Keep the competition's synthetic deployment separate from any pilot or research data. Until those components and gates exist, only the local synthetic demo steps above are executable.
