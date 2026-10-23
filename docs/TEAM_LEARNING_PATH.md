# Echo team learning path

Resources selected on 13 October 2026 for the provider-independent Echo repository. Links and topic descriptions were checked against search results and creator/course pages; the videos have not been watched end to end. Most resources are in English. Publication age does not invalidate foundational concepts, but examples must be checked against the project's current dependencies and official API documentation.

## Shared foundations

| Topic | Video and creator | Connection to Echo |
|---|---|---|
| Team collaboration | [Git and GitHub Crash Course for Beginners](https://www.youtube.com/watch?v=mAFoROnOfHs) — freeCodeCamp | Branches, commits and collaborative changes. |
| Digital audio | [How Digital Audio Works](https://www.youtube.com/watch?v=1RIA9U5oXro) — Computerphile | Sampling, bit depth and the PCM input to recording. |
| Audio compression | [Digital Audio Compression](https://www.youtube.com/watch?v=KGZ0een8vSE) — Computerphile | Why compressed audio saves storage; Echo specifically uses AAC. |
| Teaching foundations | [An introduction to formative assessment](https://www.youtube.com/watch?v=zZL6Zf5lMVw) — Dylan Wiliam | Why coaching should connect evidence of understanding to a teaching response. Requires adaptation and educator review for Hawassa. |

## Android development

Watch in this order after the shared foundations. New programmers should complete Kotlin basics before architecture sessions.

| Topic | Video and creator | Connection to Echo |
|---|---|---|
| Kotlin | [Learn Kotlin Programming Full Course for Beginners](https://www.youtube.com/watch?v=EExSSotojVI) — freeCodeCamp | Functions, types, classes and the language used throughout the app. |
| Compose | [Intuitive Thinking in Compose](https://www.youtube.com/watch?v=4zf30a34OOA) — Android Developers | Declarative UI and state-driven screens in `PilotApp.kt`. |
| Architecture | [Architecture The UI layer](https://www.youtube.com/watch?v=p9VR8KbmzEE) — Android Developers | UI state, events and data flow around `PilotViewModel.kt`. |
| Offline operation | [Create offline-first apps](https://www.youtube.com/watch?v=jaZ2gLMGUsM) — Android Developers | Local-first data, synchronization and retry concepts behind `ResearchQueue.kt`. This does not require adopting every library shown. |
| Background execution | [Effective background tasks on Android](https://www.youtube.com/watch?v=IqnCqHyu1E4) — Android Developers | Distinguish live capture in `RecordingService.kt` from scheduled processing in `AnalysisJob.kt`. Older session; check current foreground-service requirements. |
| Device encryption | [Full Guide to Encryption and Decryption in Android](https://www.youtube.com/watch?v=aaSck7jBDbw) — Philipp Lackner | Keystore and cipher foundations for `AndroidPrivateFiles.kt` and `AuthenticatedEnvelope.kt`. Echo uses authenticated AES-GCM; tutorial cipher examples are not an approved replacement for it. |
| UI testing | [Jetpack Compose Testing](https://www.youtube.com/watch?v=kdwofTaEHrs) — Android Developers | UI test rules, element queries and assertions. Older examples need current dependency checks. |

## Backend and operations

| Topic | Video and creator | Connection to Echo |
|---|---|---|
| Python | [Learn Python Full Course for Beginners](https://www.youtube.com/watch?v=rfscVS0vtbw) — freeCodeCamp / Mike Dane | Language foundations for the backend, indicators and tests. |
| Django and HTTP | [Django for Everybody Full Python University Course](https://www.youtube.com/watch?v=o0XbHvKxw7Y) — Charles Severance / freeCodeCamp | Request/response cycle, views, models and web fundamentals. Use selected chapters; the whole course is not a prerequisite. Linked by the instructor's [course site](https://www.dj4e.com/). |
| Relational databases | [Learn PostgreSQL Full Course for Beginners](https://www.youtube.com/watch?v=qw--VYLpxG4) — freeCodeCamp / Amigoscode | Tables, constraints, keys and queries underlying enrollment and summary records. |
| Server deployment | [Deploy Django REST API on a Linux VPS](https://www.youtube.com/watch?v=eIxdZlvZGik) — Code With Clinton | PostgreSQL, Gunicorn, systemd and Nginx. The hosting-provider example differs from Echo's intended Ethiopian deployment; do not upload classroom audio as part of the exercise. Echo's backend uses Django views rather than requiring Django REST Framework. |

## Optional specialist learning

| Topic | Video and creator | Connection to Echo |
|---|---|---|
| Audio machine learning | [Machine Learning for audio classification](https://www.youtube.com/watch?v=GxBG4wUWf4w) — TensorFlow | Introduction to an eventual on-device analysis pipeline. Audio classification, transcription and speaker diarization are different tasks. This is background learning, not a model already implemented or validated in Echo. |
| Continuous integration | [Complete GitHub Actions Tutorial Beginner to Pro](https://www.youtube.com/watch?v=U0S7ddSGaYI) — DevOps Directive | Workflows, jobs and automated verification. Start with the introductory workflow chapters rather than watching the entire course. |

## Practical checkpoints

1. Everyone: draw Echo's flow from microphone to encrypted local storage, weekly reflection and separately consented summary transfer. Explain which data stays on the phone.
2. Android members: build the app, trace Record/Stop through the service, and trace one UI event through the ViewModel. Use synthetic data or authorized adult test recordings.
3. Backend members: run the backend tests and explain enrollment, identical retries, withdrawal and expiry. Trace a summary through validation and database persistence.
4. Research/product members: propose one coaching card about checking understanding and one question that lets the teacher correct the interpretation. Separate observations from claims about teaching quality or student learning.
5. QA/operations members: use the existing device and infrastructure runbooks to demonstrate the tests required before classroom deployment.

## Read alongside the videos

- [Project overview](../README.md)
- [Android setup](../edge/android/README.md)
- [Backend setup](../server/pilot/README.md)
- [Device pilot runbook](testing/OFFLINE_PILOT_RUNBOOK.md)
- [Infrastructure runbook](testing/NON_HUAWEI_INFRASTRUCTURE_RUNBOOK.md)
- [Research summary contract](contracts/RESEARCH_SUMMARY_V1.md)
- [Local research and pedagogy review](research/HAWASSA_PILOT_REVIEW_2026-10-10.md)
- [Current Android foreground-service changes](https://developer.android.com/develop/background-work/services/fgs/changes)
- [Google audio classification pathway](https://developers.google.com/learn/pathways/get-started-audio-classification)
- [EEF feedback guidance](https://educationendowmentfoundation.org.uk/education-evidence/guidance-reports/feedback)

The current app provides recording, local reflection and draft goal-based coaching. It does not establish student understanding from audio, provide released teacher/student diarization or automatically diagnose pedagogy. A generic tutorial or pretrained audio model does not establish classroom or language validity.
