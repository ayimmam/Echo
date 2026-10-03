# Codex conversation

## User

verify the planned implementation and proposed architecture to modify relevant plans. Don't implement anything

## Assistant

I’ll review the repository’s plans and architecture against the existing code, then update the relevant planning documents without changing the implementation.

## Activity

    $ pwd; rg --files -g 'AGENTS.md' -g '*PLAN*' -g '*plan*' -g '*ARCH*' -g '*arch*' -g 'README*' -g 'package.json' -g '*design*' -g '*proposal*' -g '*roadmap*'
    /Users/anteneh/Documents/Repos/Echo
    docs/legal/README.md
    docs/DIMTS_ARCHITECTURE_CONTEXT.md
    docs/research/RESEARCH_PLAN.md
    docs/research/README.md
    docs/research/papers/Education Research International - 2024 - Yisihak - Ethiopian Early Grade English Teachers  Preparedness to Teach Basic.pdf
    docs/research/papers/Education Research International - 2023 - Haile - Early‐Grade Reading  The Challenges That Affect Teachers  Practice of.pdf
    ✓ • 0ms

## Activity

    $ git status --short
    ✓ • 0ms

## Activity

    $ ls -la; rg --files -g '*.md' -g '*.toml' -g '*.json' -g '*.py' -g '*.ts' -g '*.swift' -g '*.kt' -g '*.dart' -g '*.yaml' -g '*.yml'
    total 32
    drwxr-xr-x@ 13 anteneh  staff   416 Sep 26 17:28 .
    drwxr-xr-x   9 anteneh  staff   288 Sep 26 11:00 ..
    -rw-r--r--@  1 anteneh  staff  8196 Sep 26 17:27 .DS_Store
    drwxr-xr-x@ 15 anteneh  staff   480 Sep 26 17:40 .git
    -rw-r--r--@  1 anteneh  staff    23 Sep 26 10:56 .gitignore
    drwxr-xr-x@  3 anteneh  staff    96 Sep 26 17:28 dashboard
    drwxr-xr-x@  8 anteneh  staff   256 Sep 26 17:28 docs
    drwxr-xr-x@  3 anteneh  staff    96 Sep 26 17:28 edge
    drwxr-xr-x@  5 anteneh  staff   160 Sep 26 17:28 indicators-core
    drwxr-xr-x@  5 anteneh  staff   160 Sep 26 17:28 infra
    drwxr-xr-x@ 12 anteneh  staff   384 Sep 26 17:28 ml
    drwxr-xr-x@  6 anteneh  staff   192 Sep 26 17:28 server
    drwxr-xr-x@  6 anteneh  staff   192 Sep 26 17:28 tools
    edge/android/gradle/libs.versions.toml
    docs/legal/README.md
    docs/DIMTS_ARCHITECTURE_CONTEXT.md
    edge/android/app/src/main/java/org/ethioware/echo/MainActivity.kt
    docs/research/HOSTING_OPTIONS.md
    docs/research/CLAIMS_AUDIT.md
    docs/research/RESEARCH_PLAN.md
    docs/research/README.md
    docs/research/ASSUMPTIONS.md
    docs/research/TO_OBTAIN.md
    docs/research/BIBLIOGRAPHY.md
    docs/research/CODEBASE_STRUCTURE.md
    edge/android/app/src/main/java/org/ethioware/echo/ui/theme/Type.kt
    edge/android/app/src/main/java/org/ethioware/echo/ui/theme/Theme.kt
    edge/android/app/src/main/java/org/ethioware/echo/ui/theme/Color.kt
    edge/android/app/src/test/java/org/ethioware/echo/ExampleUnitTest.kt
    edge/android/app/src/androidTest/java/org/ethioware/echo/ExampleInstrumentedTest.kt
    ✓ • 0ms

## Activity

    $ for p in /AGENTS.md /Users/AGENTS.md /Users/anteneh/AGENTS.md /Users/anteneh/Documents/AGENTS.md /Users/anteneh/Documents/Repos/AGENTS.md; do if [ -f "$p" ]; then cat "$p"; fi; done
    ✓ • 0ms

## Activity

    $ rg --files --hidden -g '!.git/**' -g '!**/.DS_Store' -g '!docs/research/papers/**'; cat docs/DIMTS_ARCHITECTURE_CONTEXT.md docs/research/CODEBASE_STRUCTURE.md
    indicators-core/kotlin/.gitkeep
    indicators-core/golden/.gitkeep
    indicators-core/python/dimts_indicators/.gitkeep
    ml/teacher/.gitkeep
    ml/data/manifests/.gitkeep
    ml/export/.gitkeep
    ml/eval/.gitkeep
    ml/configs/.gitkeep
    ml/lid/.gitkeep
    ml/distill/.gitkeep
    ml/student/crnn_fp16gru/.gitkeep
    ml/student/conformer_tiny/.gitkeep
    ml/student/tcn_int8/.gitkeep
    ml/features/parity/.gitkeep
    ml/modelarts/.gitkeep
    .gitignore
    dashboard/.gitkeep
    server/jobs/.gitkeep
    docs/legal/README.md
    docs/legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf
    infra/openeuler/backup/.gitkeep
    server/api/.gitkeep
    docs/competition/.gitkeep
    docs/DIMTS_ARCHITECTURE_CONTEXT.md
    infra/openeuler/compose/.gitkeep
    docs/ethics/.gitkeep
    infra/ci/.gitkeep
    server/db/migrations/.gitkeep
    docs/adr/.gitkeep
    server/tests/.gitkeep
    infra/huawei-cloud/rds/.gitkeep
    edge/android/.idea/compiler.xml
    edge/android/.idea/misc.xml
    edge/android/.idea/.name
    edge/android/.idea/.gitignore
    edge/android/.idea/vcs.xml
    edge/android/.idea/gradle.xml
    edge/android/.idea/AndroidProjectSystem.xml
    edge/android/.idea/runConfigurations.xml
    docs/research/HOSTING_OPTIONS.md
    docs/research/CLAIMS_AUDIT.md
    docs/research/RESEARCH_PLAN.md
    docs/research/README.md
    docs/research/ASSUMPTIONS.md
    tools/lesson-simulator/.gitkeep
    infra/huawei-cloud/obs/.gitkeep
    tools/check-elf-alignment/.gitkeep
    edge/android/.idea/inspectionProfiles/Project_Default.xml
    edge/android/.idea/deploymentTargetSelector.xml
    edge/android/gradlew.bat
    infra/huawei-cloud/ecs/.gitkeep
    docs/research/TO_OBTAIN.md
    docs/research/BIBLIOGRAPHY.md
    docs/research/CODEBASE_STRUCTURE.md
    edge/android/gradlew
    tools/demo/.gitkeep
    edge/android/build.gradle.kts
    edge/android/.gitignore
    edge/android/settings.gradle.kts
    edge/android/sync/.gitkeep
    edge/android/gradle.properties
    edge/android/audio/.gitkeep
    edge/android/inference/.gitkeep
    edge/android/pipeline/.gitkeep
    edge/android/gradle/libs.versions.toml
    edge/android/gradle/gradle-daemon-jvm.properties
    tools/synthetic-lessons/.gitkeep
    edge/android/benchmark/.gitkeep
    edge/android/gradle/wrapper/gradle-wrapper.jar
    edge/android/gradle/wrapper/gradle-wrapper.properties
    edge/android/storage/.gitkeep
    edge/android/app/src/main/java/org/ethioware/echo/MainActivity.kt
    edge/android/app/src/main/java/org/ethioware/echo/ui/theme/Type.kt
    edge/android/app/src/main/java/org/ethioware/echo/ui/theme/Theme.kt
    edge/android/app/src/main/java/org/ethioware/echo/ui/theme/Color.kt
    edge/android/app/src/main/AndroidManifest.xml
    edge/android/app/src/main/keepRules/rules.keep
    edge/android/app/src/main/res/mipmap-anydpi/ic_launcher_round.xml
    edge/android/app/src/main/res/mipmap-anydpi/ic_launcher.xml
    edge/android/app/src/main/res/mipmap-xhdpi/ic_launcher_round.webp
    edge/android/app/src/main/res/mipmap-xhdpi/ic_launcher.webp
    edge/android/app/src/main/res/xml/data_extraction_rules.xml
    edge/android/app/src/main/res/xml/backup_rules.xml
    edge/android/app/src/main/res/values/strings.xml
    edge/android/app/src/main/res/values/themes.xml
    edge/android/app/src/main/res/values/colors.xml
    edge/android/app/src/main/res/drawable/ic_launcher_background.xml
    edge/android/app/src/main/res/drawable/ic_launcher_foreground.xml
    edge/android/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp
    edge/android/app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp
    edge/android/app/.gitignore
    edge/android/app/.gitkeep
    edge/android/app/build.gradle.kts
    edge/android/app/src/main/res/mipmap-xxhdpi/ic_launcher_round.webp
    edge/android/app/src/main/res/mipmap-xxhdpi/ic_launcher.webp
    edge/android/app/src/main/res/mipmap-hdpi/ic_launcher_round.webp
    edge/android/app/src/main/res/mipmap-hdpi/ic_launcher.webp
    edge/android/app/src/main/res/mipmap-mdpi/ic_launcher_round.webp
    edge/android/app/src/main/res/mipmap-mdpi/ic_launcher.webp
    edge/android/app/src/androidTest/java/org/ethioware/echo/ExampleInstrumentedTest.kt
    edge/android/app/src/test/java/org/ethioware/echo/ExampleUnitTest.kt
    # Dimts (ድምጽ) — Architecture Context Document
    
    > **Purpose of this file.** Single source of truth for designing the codebase. Hand it to any teammate, reviewer, or coding agent before they write code. It contains the problem, the constraints, the specs, the Huawei technology mapping, the proposed architecture, and the open decisions. It does **not** contain final code.
    >
    > **Codename:** Dimts (Amharic ድምጽ, "voice / sound") — placeholder, rename freely.
    > **Owner:** Anteneh F. Yimmam (team lead) · Hawassa University, Information Systems
    > **Status:** Pre-architecture · Last updated 2026-09-25
    > **Targets:** B.Sc. final-year project (defence Jun 2027) + Huawei ICT Competition 2026–2027, Innovation Competition
    
    ---
    
    ## 0. How to use this document
    
    1. Read §1–§6 before proposing any design. They are the *why* and the *must-not-break* constraints.
    2. §7–§13 are the proposed architecture. Treat them as a strong default, not scripture. Changes go through an ADR (§19).
    3. §14 defines "done." If a design choice cannot be evaluated against §14, it is out of scope.
    4. Anything marked **[VERIFY]** is a fact that must be checked against a primary source before it is relied on.
    5. Anything marked **[DECISION]** is open and needs an ADR.
    
    ---
    
    ## 1. One-paragraph summary
    
    Dimts is an offline, on-device classroom audio analysis system for early-grade (O-class and Grades 1–3) teachers in Ethiopia. A teacher places a low-end Android phone on her desk during a lesson. The phone analyses the audio **in real time, on the device**, without speech recognition, and produces pedagogical indicators: how much the teacher talks, how long she waits for children to respond, whether children answer individually or only in chorus, how often turns change, and which language the lesson is conducted in (Amharic, Sidaamu Afoo, English). Raw audio never leaves the phone. Only derived indicators sync to an in-country server, where they feed a private weekly report to the teacher and anonymised aggregates to district education offices. The system is language-agnostic by design because it never touches text, and it is validated on two orthographically distinct languages: Amharic (Ge'ez abugida) and Sidaamu Afoo (Latin script).
    
    ---
    
    ## 2. Problem and evidence
    
    ### 2.1 The learning crisis is flat, not unmeasured
    
    - EGRA upper-benchmark reading performance: **31.3% (2014), 34.2% (2016), 32.4% (2018)** — essentially flat across three national rounds.
    - USAID 2018: only ~**40%** of Grade 2–3 students read at 20–25 words per minute.
    - Depending on language and region, **0.5%–13%** of students could read with comprehension.
    - Conclusion: Ethiopia has been measured repeatedly. Another child assessment tool is not the missing piece.
    
    ### 2.2 Teacher capability is the binding constraint
    
    - Early-grade English teachers averaged **43.4%** on a reading-instruction knowledge test; **70%** scored below 50%. Preservice programs devote only a few sessions to early reading.
    - Teachers are required to teach phonics and interactive reading pedagogy they were never trained in, and they receive **no feedback loop** on their actual classroom practice.
    
    ### 2.3 Pre-primary expanded faster than quality
    
    - Pre-primary GER reached **59.8%** in 2024/25; kindergarten schools grew **22%** in one year.
    - Extreme regional gaps: Addis Ababa 145.9% vs Somali 17.7%, Afar 26.8%.
    - O-classes and child-to-child modalities serve ~**75%** of pre-primary children; a 2025 mixed-methods study found systemic deficits in qualified personnel, play facilities, and classroom conditions nationwide.
    
    ### 2.4 Mother-tongue instruction is policy without verification
    
    - Policy mandates primary instruction in nationality languages. Research documents shortages of trained teachers and materials in L1, and teachers not proficient in the language they must teach in.
    - **No one currently measures, at scale, which language is actually spoken in the classroom.** Dimts's language-ratio indicator is a direct, novel policy measurement.
    
    ### 2.5 Why classroom *audio*, and why teacher-facing
    
    - Classroom observation is the standard instrument for improving teaching practice, but it requires trained observers who do not exist at scale in Ethiopia.
    - Acoustic indicators (talk ratio, wait time, choral vs individual response) are well-established in the classroom-discourse literature as correlates of instructional quality and are extractable **without transcription**.
    - Recording an adult teacher, with her consent, in her own classroom is ethically and logistically far lighter than collecting child speech data for assessment.
    
    ### 2.6 Why no ASR
    
    - Amharic ASR is weak; Sidaamu Afoo ASR effectively does not exist.
    - Any design that depends on transcription will fail on the target languages. **This is a hard architectural rule (see §6, C-1).**
    
    ---
    
    ## 3. Users and scenarios
    
    | User | Goal | Touchpoint | What they must never see |
    |---|---|---|---|
    | **Teacher** (primary) | Improve her own practice, privately | Android app: record → weekly report | Other teachers' data |
    | **School director** | Support teachers | Optional: school-level aggregate (opt-in by teachers) | Individual teacher reports unless shared by the teacher |
    | **Woreda / district education officer** | Target training and resources | Web dashboard: anonymised aggregates | Any individual-teacher identity; any cell with < k teachers |
    | **Researcher (team)** | Train and evaluate models | Research Mode app + annotation tool | Audio from teachers who did not consent to Research Mode |
    | **Competition judge** | Evaluate innovation | Live demo on a cheap phone + dashboard | — |
    
    ### Core scenario (Deployment Mode)
    1. Teacher opens app, taps **Start lesson**, selects grade and subject.
    2. Phone processes audio in streaming chunks. No raw audio written to disk.
    3. Teacher taps **End lesson**. Indicators computed in seconds.
    4. Indicators stored encrypted locally; queued for sync.
    5. When connectivity appears, indicators upload to the in-country server.
    6. Weekly: teacher sees a simple, non-punitive report in her language with one suggested focus (e.g., "Try waiting 3 seconds after asking a question").
    7. District dashboard updates aggregates.
    
    ### Secondary scenario (Research Mode)
    Same as above, **plus** raw audio is stored encrypted on the device for later transfer to the research server, **only** with explicit written consent, used to build training and evaluation datasets.
    
    ---
    
    ## 4. Scope
    
    ### 4.1 In scope (competition + thesis core)
    - On-device audio segmentation into teacher / child-single / child-choral / overlap / non-speech.
    - On-device spoken language identification over teacher segments: Amharic, Sidaamu Afoo, English, other.
    - Indicator engine (§5).
    - Android teacher app (record, report, sync, consent).
    - In-country server: ingestion, storage, report generation, aggregation.
    - District web dashboard with k-anonymity.
    - Training / distillation / export pipeline on MindSpore.
    - Evaluation harness incl. cross-language evaluation (Amharic → Sidaamu Afoo).
    
    ### 4.2 Explicitly out of scope
    - Any speech recognition or transcription.
    - Any identification of individual children, by voice or otherwise.
    - Any teacher performance scoring used for evaluation, ranking, or discipline. (Product principle: **coaching, not surveillance.**)
    - Child reading assessment (future component).
    
    ### 4.3 Roadmap (pitch as roadmap, do not claim as built)
    - R-1: Decodable mother-tongue material generation constrained to taught graphemes.
    - R-2: Child micro-diagnostics (phoneme isolation, blending) via forced alignment.
    - R-3: Digits-in-noise hearing pre-screen with referral routing.
    - R-4: Prosody-based question detection (rising intonation) to refine wait-time.
    - R-5: HarmonyOS native client (see §7.4).
    
    ---
    
    ## 5. Indicator specification (the core contract)
    
    All indicators are computed from a **segment timeline**: an ordered list of `(start_s, end_s, label, confidence)` where `label ∈ {TEACHER, CHILD_SINGLE, CHILD_CHORAL, OVERLAP, NON_SPEECH}`, plus a per-teacher-segment language label `lang ∈ {amh, sid, eng, oth}`.
    
    Segment post-processing (applied before indicators, parameters fixed per version):
    - Minimum segment duration: 0.3 s (shorter segments merged into neighbour by majority).
    - Gap bridging: same-label segments separated by < 0.25 s NON_SPEECH are merged.
    - Language label assigned per teacher segment ≥ 2.0 s; shorter segments inherit the language of the nearest labelled neighbour.
    
    | ID | Indicator | Definition | Unit | Notes |
    |---|---|---|---|---|
    | I-1 | Teacher talk ratio | Σ TEACHER duration ÷ Σ (TEACHER + CHILD_* + OVERLAP) duration | % | Also report ÷ total lesson duration |
    | I-2 | Response latency (wait-time proxy) | For each TEACHER→CHILD_* transition where the child segment begins ≤ 10 s after the teacher segment ends: the silence gap. Report median and P25/P75. | s | Proxy for wait time. True wait time needs question detection (R-4). Document this limitation explicitly. |
    | I-3 | Choral response ratio | Σ CHILD_CHORAL duration ÷ Σ CHILD_* duration | % | Also report count ratio |
    | I-4 | Individual responses | Count of CHILD_SINGLE segments ≥ 0.5 s | count / lesson and / 10 min | |
    | I-5 | Turn density | Count of TEACHER↔CHILD_* alternations ÷ lesson minutes | turns / min | |
    | I-6 | Language of instruction | Duration share of teacher speech by `lang` | % per language | Novel policy indicator |
    | I-7 | Interaction coverage | Share of lesson minutes containing ≥ 1 turn alternation | % | Flags long lecture or idle stretches |
    | I-8 | Long silence events | Count of NON_SPEECH runs ≥ 60 s | count | Could indicate seatwork or idle time; report neutrally |
    
    **Rules**
    - Indicator definitions are versioned (`indicator_spec_version`). Any change bumps the version; server stores the version with every record.
    - There is exactly **one reference implementation** (Python, `indicators-core/python`). The on-device implementation (Kotlin) must pass the shared golden test vectors byte-for-byte on outputs (tolerance 1e-6 for floats). See §12.
    - Reports to teachers show **at most one suggested focus per week**, chosen by a simple, documented rule table, not by a model.
    
    ---
    
    ## 6. Hard constraints
    
    | ID | Constraint | Consequence for design |
    |---|---|---|
    | C-1 | **No ASR, no text.** | Every model operates on acoustic features only. Any PR introducing transcription is rejected. |
    | C-2 | **Target device:** Android 10+ (API 29), arm64-v8a, **2 GB RAM**, 4–8 × Cortex-A53/A55 class cores, no NPU assumed, 16 GB storage, often shared. | All inference on CPU, INT8. Peak app RSS ≤ 300 MB. Model files total ≤ 20 MB. |
    | C-3 | **Real-time streaming.** | Real-time factor (RTF) ≤ 0.3 on target device, so a 40-min lesson processes live without falling behind and the phone stays usable. |
    | C-4 | **Intermittent connectivity.** | Fully functional offline. Sync is opportunistic, resumable, idempotent. Payloads small (indicators JSON, KB-scale). |
    | C-5 | **Noisy, reverberant classrooms,** 40–80 children, single cheap mic, hard walls, outside noise. | Heavy augmentation; evaluation on real classroom audio only. |
    | C-6 | **Two languages, two scripts:** Amharic (Ge'ez), Sidaamu Afoo (Latin). English code-switching common. | LID must handle all three + "other". Cross-language evaluation is mandatory. |
    | C-7 | **Data protection law:** Ethiopia Personal Data Protection Proclamation No. 1321/2024 (in force 24 Jul 2024). Reported to require consent, **local storage of personal data on servers in Ethiopia**, 72-hour breach notification, and special protection for minors (under 16). [VERIFY with faculty legal advisor — not legal advice] | Personal data (including voice audio and teacher-linked indicators) stored in-country. Cross-border transfer of raw classroom audio avoided by design (see §11, ADR-003). |
    | C-8 | **Children are recorded incidentally.** | No child-level features stored. No voice embeddings persisted. Deployment Mode never writes raw audio to disk. School + parent notification per ethics approval. |
    | C-9 | **Timeline:** Working demo required for the national stage (Jan–Feb 2027). | Architecture must allow a thin vertical slice end-to-end by early December 2026. |
    | C-10 | **Team of 3, students.** | Prefer boring, well-documented tech outside the ML core. No bespoke distributed systems. |
    | C-11 | **Huawei stack preference** for competition credibility, without compromising C-2 and C-7. | See §7. Huawei-native wherever it does not break the device or legal constraints; documented escape hatches where it does. |
    
    ---
    
    ## 7. Huawei technology mapping
    
    ### 7.1 Principles
    - Use Huawei technology where it is **load-bearing**, not decorative. Judges probe in Q&A.
    - Keep an **escape hatch** (ONNX) for every model so a framework bug cannot sink the timeline.
    - The deployment-critical path (phone + in-country server) must not depend on a foreign cloud region (C-7).
    
    ### 7.2 Mapping table
    
    | Layer | Huawei technology | Role in Dimts | Why / caveat |
    |---|---|---|---|
    | On-device inference | **MindSpore Lite** (2.x; Android-aarch64 AAR; current release listed as 2.10.0 [VERIFY at build time]) | Runs segmentation + LID models, INT8 | Supports Android, post-training INT8 quantization, and converts MindIR/ONNX/TFLite to `.ms`. Built into HarmonyOS, so the same model ports to R-5. |
    | Model training | **MindSpore** 2.x | Teacher-model fine-tuning, student training, distillation | Primary framework. |
    | Audio toolkit | **MindAudio** (mindspore-lab) | Feature extraction (fbank), augmentation, ECAPA-TDNN reference | Maturity is limited (last tagged release 0.1.x) [VERIFY]. Use for features; do not depend on it for novel models. |
    | Pretrained speech encoders | **MindNLP** (HuggingFace-compatible, PyTorch-API proxy) | Load multilingual self-supervised encoders (wav2vec2 / XLS-R / HuBERT family) as the server-side teacher model | Community reports Ascend-specific errors with some wav2vec2 variants [VERIFY on your hardware early — week 1 spike]. |
    | Cloud training | **Huawei Cloud ModelArts** (Ascend) | Pre-training / fine-tuning on **public** speech data; pipeline demo; distillation runs on de-identified targets | Nearest regions: Johannesburg, Cairo, Lagos AZ. **No Ethiopia region** [VERIFY]. Do not upload raw classroom audio here without an ADR-003 decision. |
    | Object storage (cloud) | **Huawei Cloud OBS** | Public datasets, model artefacts, experiment logs | No personal data. |
    | Server OS | **openEuler** (LTS) | In-country server OS at Hawassa University | Huawei-originated open-source Linux. |
    | Database | **openGauss** | Indicator records, reports, aggregates, consent ledger | PostgreSQL-lineage; standard drivers mostly work [VERIFY driver for chosen backend language]. |
    | CI/CD (optional) | Huawei Cloud **CodeArts** | Build/test pipelines | Optional; GitHub Actions acceptable if friction is high. |
    | Client OS (stretch) | **HarmonyOS / OpenHarmony**, ArkTS | Native client port (R-5) | Target teachers overwhelmingly use Android phones (Tecno, Itel, Samsung). HarmonyOS NEXT does not run Android apps, so it cannot be the primary client. Frame the port as "same `.ms` model, second OS." |
    
    ### 7.3 What to say in Q&A
    - "Every model is trained in MindSpore and deployed with MindSpore Lite on a US$80 Android phone."
    - "The cloud is used for compute on public data; personal data stays on Ethiopian soil on openEuler + openGauss, because Ethiopian law requires it."
    - "The same `.ms` model artefact runs on Android today and HarmonyOS tomorrow."
    
    ### 7.4 HarmonyOS position
    Primary client is Android because that is what teachers own. The HarmonyOS client is a roadmap item unless the team has spare capacity after the national stage. Do not let it block the critical path.
    
    ---
    
    ## 8. System architecture
    
    ### 8.1 Tiers
    
    ```
    ┌──────────────────────────── EDGE (teacher's Android phone) ─────────────────────────────┐
    │  Mic → Ring buffer (≤ 10 s, RAM only) → Feature extractor (log-mel, 16 kHz)             │
    │      → Segmentation model (MindSpore Lite, INT8) → Frame labels                          │
    │      → LID model on TEACHER segments (MindSpore Lite, INT8) → lang labels                │
    │      → Post-processor → Segment timeline → Indicator engine (Kotlin, spec vN)            │
    │      → Encrypted local store (indicators, timeline summary, consent state)               │
    │      → Outbox → Sync client (HTTPS, idempotent, resumable)                               │
    │  Research Mode only: encrypted raw audio file, per-lesson key, manual export             │
    └───────────────────────────────────────────┬─────────────────────────────────────────────┘
                                                │ indicators JSON (KB), TLS, pinned cert
    ┌───────────────────────── SOVEREIGN TIER (in-country, Hawassa Univ.) ────────────────────┐
    │  openEuler host · containers                                                             │
    │  Ingest API → validation (schema + spec version) → openGauss                              │
    │  Report service (weekly teacher report, rule table) → pull endpoint for app              │
    │  Aggregation job (k-anonymity ≥ 5) → Dashboard API → District web dashboard              │
    │  Consent ledger · Audit log · Backup (encrypted, in-country)                              │
    │  Research store (raw audio from Research Mode, encrypted, access-controlled)             │
    │  Local training node (GPU/CPU) for fine-tuning on classroom audio  [ADR-003]             │
    └───────────────────────────────────────────┬─────────────────────────────────────────────┘
                                                │ model artefacts only (no personal data)
    ┌──────────────────────────── HUAWEI CLOUD (non-personal compute) ────────────────────────┐
    │  ModelArts: pre-training / adaptation of teacher encoder on PUBLIC speech data           │
    │  OBS: public datasets, model artefacts, experiment tracking                              │
    │  Export pipeline: MindIR → MindSpore Lite converter → .ms (INT8 PTQ)                     │
    └─────────────────────────────────────────────────────────────────────────────────────────┘
    ```
    
    ### 8.2 Key design decisions (proposed; ratify via ADR)
    
    1. **Frame classification, not clustering diarization.** The task is a fixed 5-class problem, not "who spoke when" with unknown N. This is simpler, trainable, and real-time.
    2. **Teacher–student distillation.** A large multilingual self-supervised encoder (server-side "teacher model") is fine-tuned on annotated classroom audio and used to produce soft labels on unannotated audio. A tiny on-device "student" (CRNN or small conformer on log-mel) is trained on those soft labels. This is how a 2 GB phone gets near-large-model accuracy. It is also the main technical-complexity story for judges.
    3. **Streaming, not batch, in Deployment Mode.** Raw audio exists only in a ≤ 10 s RAM ring buffer. Nothing is re-analysable after the lesson. This is a privacy guarantee and a storage guarantee at once.
    4. **Append-only sync, no CRDTs.** Each device produces immutable lesson records with client-generated UUIDv7 IDs. No record is ever edited on two devices, so no conflict resolution is needed. Deletion is a tombstone record.
    5. **Single indicator spec, two implementations, golden tests.** Python reference on server/eval; Kotlin port on device; shared golden vectors in CI.
    6. **Rule-based coaching, not model-based.** The weekly suggestion is a transparent lookup table from indicator ranges to one suggestion. Explainable to teachers, directors, and judges.
    
    ### 8.3 Edge pipeline details
    
    | Stage | Spec (initial; tune in experiments) |
    |---|---|
    | Capture | 16 kHz mono PCM16 via `AudioRecord`; foreground service with persistent notification; wake lock during lesson |
    | Features | 64-bin log-mel, 25 ms window, 10 ms hop; per-utterance CMVN replaced by running normalisation (streaming) |
    | Segmentation model | Input: 1.0–2.0 s context windows, stride 0.5 s; output: per-10 ms-frame 5-class posteriors; smoothing via median filter or small HMM |
    | LID model | Runs on TEACHER segments ≥ 2 s; 3 s windows; outputs {amh, sid, eng, oth}; segment label = mean posterior argmax with "oth" threshold |
    | Indicator engine | Runs incrementally; final values at lesson end |
    | Storage | Encrypted SQLite (SQLCipher or Jetpack Security + Room) with keys in Android Keystore |
    | Sync | WorkManager periodic job, network-constrained; exponential backoff; resumable; server returns per-record ACKs |
    
    **Performance budget (target device, 40-min lesson):**
    - RTF ≤ 0.3 total (segmentation + LID + indicators).
    - Peak RSS ≤ 300 MB.
    - Battery ≤ 10% per 40-min lesson [measure].
    - Models: segmentation ≤ 8 MB, LID ≤ 8 MB (INT8).
    
    ---
    
    ## 9. ML design
    
    ### 9.1 Tasks
    - **T-1 Segmentation:** 5-class frame labelling {TEACHER, CHILD_SINGLE, CHILD_CHORAL, OVERLAP, NON_SPEECH}.
    - **T-2 Language ID:** 4-class segment labelling {amh, sid, eng, oth} over teacher speech.
    
    ### 9.2 Data sources
    
    | Source | Content | Use | Location |
    |---|---|---|---|
    | D-1 Classroom Research Mode recordings | 20–30 lessons × ~40 min, Amharic + Sidaamu Afoo schools | Fine-tune teacher model, train student, **evaluate** | In-country research store only |
    | D-2 Annotated subset of D-1 | 6–10 hours, frame labels + language labels | Supervised training + test sets | In-country |
    | D-3 Public Amharic speech (e.g., ALFFA Amharic, FLEURS Amharic) [VERIFY licences & availability] | Adult read/spontaneous speech | LID training, encoder adaptation | Huawei Cloud OBS OK (public) |
    | D-4 Sidaamu Afoo speech | Scarce publicly [VERIFY]; likely self-collected (radio with permission, consented adult volunteers) | LID training | In-country unless public-licensed |
    | D-5 Public noise / reverberation / babble corpora (e.g., MUSAN-style, RIR sets) [VERIFY licences] | Augmentation | Anywhere |
    | D-6 Synthetic classroom mixtures | Adult speech + child-babble + RIR + choral simulation (time-aligned copies with jitter/pitch shift) | Pre-training the student before real data arrives | Anywhere (built from public data) |
    
    **Rule:** the **test set is real classroom audio only** (D-2 held-out lessons, split by teacher and by school, never by random frames).
    
    ### 9.3 Annotation
    - Tool: Label Studio (self-hosted on the in-country server) or equivalent audio segmentation tool [DECISION: ADR-006].
    - Protocol: written annotation guide in `docs/annotation-guide.md` with audio examples for each class, especially CHILD_SINGLE vs CHILD_CHORAL vs OVERLAP boundary cases.
    - Agreement: double-annotate ≥ 15% of hours; report Cohen's κ on frame labels. Target κ ≥ 0.70 before training on single-annotated data.
    - Annotators: team + trained native speakers for language labels. Pay/credit per ethics approval.
    
    ### 9.4 Models
    - **Teacher model:** multilingual self-supervised speech encoder (loaded via MindNLP) + frame classification head (T-1) and pooled head (T-2). Fine-tuned on D-2, adapted on D-3/D-4. Server-side only.
    - **Student models:** small CRNN or compact conformer on log-mel. Trained on D-2 hard labels + teacher soft labels on unannotated D-1 + D-6. Loss: CE on hard labels + KL to teacher posteriors (temperature T tuned).
    - **Quantization:** MindSpore Lite post-training INT8 with a calibration set drawn from real classroom audio (not synthetic).
    
    ### 9.5 Export path
    ```
    MindSpore (train) → export MindIR → MindSpore Lite converter → .ms (FP32) → PTQ INT8 → .ms (INT8)
    Escape hatch: framework-agnostic training → ONNX → MindSpore Lite converter → .ms
    ```
    Both paths must be scripted and tested in CI from week 3.
    
    ---
    
    ## 10. Data model and flows
    
    ### 10.1 What leaves the device (Deployment Mode)
    
    ```jsonc
    // LessonRecord v1 — the ONLY payload that syncs in Deployment Mode
    {
      "record_id": "uuidv7",
      "schema_version": 1,
      "indicator_spec_version": 1,
      "model_versions": { "seg": "seg-student-0.3.1-int8", "lid": "lid-student-0.2.0-int8" },
      "teacher_pseudo_id": "opaque, server-issued at enrolment",
      "school_id": "opaque",
      "grade": "O|1|2|3",
      "subject": "mother_tongue|math|english|environmental|other",
      "lesson_start_local": "2026-11-03T08:15:00+03:00",
      "duration_s": 2400,
      "indicators": {
        "I1_teacher_talk_ratio": 0.78,
        "I2_response_latency_s": { "median": 0.6, "p25": 0.3, "p75": 1.1, "n": 42 },
        "I3_choral_ratio": 0.91,
        "I4_individual_responses": { "count": 5, "per_10min": 1.25 },
        "I5_turn_density_per_min": 1.9,
        "I6_language_share": { "amh": 0.62, "sid": 0.31, "eng": 0.05, "oth": 0.02 },
        "I7_interaction_coverage": 0.55,
        "I8_long_silence_events": 1
      },
      "quality": { "mean_seg_confidence": 0.84, "clipping_ratio": 0.001, "snr_estimate_db": 11.2 },
      "device": { "model_class": "arm64-2gb", "app_version": "0.4.0" }
    }
    ```
    
    No audio, no embeddings, no timestamps finer than lesson start, no child-level data.
    
    ### 10.2 Server entities (openGauss)
    `teacher` (pseudo_id, school_id, consent_state, language_pref) · `school` · `woreda` · `lesson_record` · `weekly_report` · `aggregate_cell` (woreda × grade × week, suppressed if n < k) · `consent_event` (append-only ledger) · `audit_log` · `research_recording` (Research Mode only, pointer to encrypted blob, consent_ref).
    
    ### 10.3 Identity
    - Teacher enrols with a code issued by the research team; server returns an opaque `teacher_pseudo_id`. Real names stored only in a separate, access-restricted enrolment table (or on paper) [DECISION: ADR-007].
    
    ---
    
    ## 11. Privacy, security, and ethics architecture
    
    | Concern | Control |
    |---|---|
    | Raw audio exposure | Deployment Mode: RAM ring buffer only, never persisted. Research Mode: per-lesson AES key, file encrypted at rest, deleted from phone after verified transfer. |
    | Voice biometrics | No speaker embeddings persisted anywhere. Models output class posteriors only. |
    | Child data | No child-level features. CHILD_* are acoustic classes, not individuals. |
    | Data localisation (C-7) | All personal data (records, research audio, enrolment) on in-country server. Huawei Cloud holds public data and model artefacts only. |
    | Re-identification via dashboard | k-anonymity: suppress any aggregate cell with < 5 teachers. No drill-down to school when school has < 5 participating teachers. |
    | Teacher misuse by management | Individual reports visible only to the teacher. Sharing with director is teacher-initiated, revocable. Written into consent form and product copy. |
    | Transport | TLS 1.2+, certificate pinning in app. |
    | At rest | Device: Keystore-backed encryption. Server: disk encryption + encrypted backups in-country. |
    | Consent | Per-teacher consent recorded in app and in server ledger; separate opt-in for Research Mode; withdrawal deletes server records (tombstone + purge job). Parent/school notification per ethics board. |
    | Breach | 72-hour notification runbook (per C-7) in `docs/ethics/breach-runbook.md`. |
    | Approvals | Hawassa University research ethics review **before first recording**. School and woreda permission letters. |
    
    ---
    
    ## 12. Proposed repository structure (monorepo)
    
    ```
    dimts/
    ├── README.md
    ├── docs/
    │   ├── ARCHITECTURE_CONTEXT.md        ← this file
    │   ├── adr/                           ← ADR-000 template, ADR-001..n
    │   ├── annotation-guide.md
    │   ├── indicator-spec.md              ← mirrors §5, versioned
    │   └── ethics/                        ← consent forms (amh, sid, eng), approval, breach runbook
    ├── indicators-core/
    │   ├── python/                        ← reference implementation (package: dimts_indicators)
    │   ├── kotlin/                        ← Android port (Gradle module)
    │   └── golden/                        ← JSON timelines + expected outputs; CI runs both impls
    ├── ml/
    │   ├── configs/                       ← experiment configs (YAML)
    │   ├── data/                          ← manifests, splits, NO audio in git
    │   ├── features/                      ← log-mel, augmentation (MindAudio + custom)
    │   ├── synth/                         ← synthetic classroom mixture generator (D-6)
    │   ├── teacher/                       ← SSL encoder fine-tuning (MindSpore + MindNLP)
    │   ├── student/                       ← CRNN / compact conformer
    │   ├── distill/                       ← soft-label generation, KD training
    │   ├── export/                        ← MindIR → .ms, INT8 PTQ, ONNX fallback
    │   ├── eval/                          ← frame F1, indicator error, cross-language, latency
    │   └── modelarts/                     ← job definitions for Huawei Cloud
    ├── edge/
    │   └── android/
    │       ├── app/                       ← UI (Jetpack Compose), i18n: amh, sid, eng
    │       ├── audio/                     ← capture service, ring buffer
    │       ├── inference/                 ← MindSpore Lite wrapper (Java API or JNI)
    │       ├── pipeline/                  ← features → seg → LID → post-process
    │       ├── storage/                   ← encrypted Room/SQLCipher
    │       ├── sync/                      ← outbox, WorkManager, API client
    │       └── benchmark/                 ← on-device RTF / RSS / battery harness
    ├── server/
    │   ├── api/                           ← ingest, reports, dashboard API
    │   ├── jobs/                          ← weekly reports, aggregation, purge
    │   ├── db/                            ← openGauss migrations
    │   └── tests/
    ├── dashboard/                         ← district web UI
    ├── harmony/                           ← (stretch) ArkTS client, R-5
    ├── infra/
    │   ├── openeuler/                     ← host provisioning, container compose, backups
    │   └── ci/                            ← pipelines (CodeArts or GitHub Actions)
    └── tools/
        ├── lesson-simulator/              ← replays recorded/synthetic lessons into the pipeline
        └── demo/                          ← competition demo scripts and fixtures
    ```
    
    **Backend language [DECISION: ADR-005]:** default proposal Python (FastAPI) for the server so the indicator reference implementation and aggregation share one language with the ML stack. Confirm openGauss driver compatibility in week 1.
    
    ---
    
    ## 13. Interfaces
    
    ### 13.1 Sync API (sovereign tier)
    - `POST /v1/lessons` — body: array of `LessonRecord`; response: per-record `{record_id, status: accepted|duplicate|rejected, reason?}`. Idempotent on `record_id`.
    - `GET /v1/reports/latest?teacher_pseudo_id=…` — authenticated per teacher device token.
    - `POST /v1/consent` — consent event append.
    - `DELETE /v1/teacher/{pseudo_id}` — withdrawal, triggers purge.
    - Auth: device-bound token issued at enrolment; rotate on reinstall.
    
    ### 13.2 Model contract (edge)
    ```
    seg_student.ms   input:  float32[1, 64, T]   (log-mel, T frames)
                     output: float32[1, T, 5]    (posteriors: TEACHER, CHILD_SINGLE, CHILD_CHORAL, OVERLAP, NON_SPEECH)
    lid_student.ms   input:  float32[1, 64, 300] (3 s log-mel)
                     output: float32[1, 4]       (amh, sid, eng, oth)
    ```
    Each model ships with `model_card.json`: version, training data summary, eval metrics, calibration set hash, feature config hash. The app refuses a model whose feature config hash does not match the pipeline.
    
    ---
    
    ## 14. Evaluation plan (definition of done)
    
    ### 14.1 Model metrics (real classroom test set, split by teacher/school)
    
    | Metric | Target (thesis) | Stretch |
    |---|---|---|
    | T-1 frame macro-F1 (5 classes) | ≥ 0.70 | ≥ 0.80 |
    | T-1 CHILD_SINGLE vs CHILD_CHORAL F1 | ≥ 0.75 | ≥ 0.85 |
    | T-2 LID segment accuracy (teacher segments ≥ 3 s) | ≥ 0.85 | ≥ 0.92 |
    | Student vs teacher-model gap (macro-F1) | ≤ 0.08 | ≤ 0.04 |
    | INT8 vs FP32 gap (macro-F1) | ≤ 0.02 | ≤ 0.01 |
    
    ### 14.2 Indicator validity (the metric that matters)
    Compare Dimts indicators against human-coded indicators on held-out lessons:
    - I-1 teacher talk ratio: mean absolute error ≤ 5 percentage points.
    - I-2 response latency median: MAE ≤ 0.5 s.
    - I-3 choral ratio: MAE ≤ 10 percentage points.
    - Across lessons, Spearman ρ between Dimts and human indicators ≥ 0.80 for I-1, I-3, I-5, I-6.
    
    ### 14.3 Cross-language generalisation (thesis contribution)
    - Train T-1 on Amharic-school lessons only; test on Sidaamu Afoo-school lessons. Report degradation vs in-language baseline.
    - Hypothesis: segmentation degrades little (acoustic, script-independent); LID requires both languages in training. Report honestly either way.
    
    ### 14.4 System metrics (target device)
    - RTF ≤ 0.3; peak RSS ≤ 300 MB; battery ≤ 10% / 40 min; crash-free sessions ≥ 99% in pilot.
    - Sync: 100% of records eventually delivered in a simulated 72-hour offline test with random disconnects.
    
    ### 14.5 Pilot / usefulness
    - ≥ 6 teachers, ≥ 3 weeks of use, both languages.
    - Short structured teacher interviews (reuse your survey-methods skills): perceived usefulness, trust, discomfort. Pre/post change in I-2 and I-4 as exploratory (not causal) evidence.
    
    ---
    
    ## 15. Team and ownership
    
    | Person | Owns | Key deliverables |
    |---|---|---|
    | **Anteneh (lead)** | Data, ethics, indicator spec, evaluation design, pilot, thesis, pitch | Ethics approval, consent forms (3 languages), annotation guide + κ, indicator spec v1, eval report, demo script |
    | **Person 2 (ML)** | `ml/`, model cards, export | Teacher model, student models, INT8 `.ms` artefacts, eval numbers |
    | **Person 3 (systems)** | `edge/android/`, `server/`, `dashboard/`, `infra/` | Streaming pipeline, encrypted storage, sync, server on openEuler + openGauss, dashboard |
    | Shared | `indicators-core/` | Python reference (lead), Kotlin port (P3), golden vectors (lead + P3) |
    
    If no teammate can fine-tune speech models, that is the first gap to close.
    
    ---
    
    ## 16. Timeline (from 2026-09-25)
    
    > Registration for the 2026–2027 cycle is open now; confirm the Innovation Competition deadline and Ethiopia's regional grouping on the Huawei Talent portal this week [VERIFY].
    
    | Weeks | Dates (approx.) | Milestone |
    |---|---|---|
    | W0 | Sep 25 – Oct 4 | Register team. Submit ethics application. Record **one** real lesson on a cheap phone and listen to it. Week-1 spikes: MindSpore Lite on target phone (hello-world INT8 model), MindNLP loading an SSL encoder, openGauss + backend driver. |
    | W1–2 | Oct 5 – Oct 18 | ADR-001..005 ratified. Indicator spec v1 + golden vectors. Synthetic mixture generator (D-6). Android capture service + ring buffer. Repo + CI. |
    | W3–4 | Oct 19 – Nov 1 | Ethics approved (target). First 10 Research Mode lessons. Annotation guide + κ pilot. Student v0 trained on synthetic data, exported to `.ms`, **running live on the phone** (end-to-end thin slice with fake-quality labels). |
    | W5–6 | Nov 2 – Nov 15 | 20–30 lessons collected. 6–10 h annotated. Teacher model fine-tuned. LID v0. Server ingest + openGauss live on openEuler. |
    | W7–8 | Nov 16 – Nov 29 | Distillation → student v1. INT8 PTQ with real calibration. Indicator validity eval v1. First weekly reports to pilot teachers. Dashboard v0. |
    | W9–10 | Nov 30 – Dec 13 | Cross-language eval. On-device benchmark report. Dashboard k-anonymity. Demo video. **Feature freeze.** |
    | W11+ | Dec 14 → | Pitch deck, rehearsal, competition submission. Continue pilot for thesis. Post-national: roadmap items (R-1..R-5). |
    
    **Critical path:** ethics approval → data collection → annotation → teacher model → distillation. Start ethics in W0 or the whole plan slips.
    
    ---
    
    ## 17. Competition mapping
    
    Innovation Competition judging criteria (per Huawei's published description of prior editions): **creativity, system complexity, technical complexity, societal benefit, functionality.** Presentation format at global level: 15 min presentation + 5 min Q&A, in English; PPT, video, or physical demo. AI use is mandatory. [VERIFY for 2026–2027 rules]
    
    | Criterion | Dimts evidence |
    |---|---|
    | Creativity | Teacher-facing, not child-facing. No-ASR design turns a language gap into a strength. First measurement of mother-tongue policy compliance in the room. |
    | System complexity | Edge (streaming inference) + sovereign server (openEuler/openGauss) + cloud (ModelArts) + dashboard; offline-first sync; consent ledger. |
    | Technical complexity | Teacher–student distillation from a multilingual SSL encoder into an INT8 on-device student; real-time RTF ≤ 0.3 on a 2 GB phone; cross-script generalisation experiment. |
    | Societal benefit | EGRA flatline (§2.1) → teacher feedback loop at near-zero marginal cost; data sovereignty by design; pilot numbers. |
    | Functionality | Live demo on a cheap Android phone: start lesson, play recorded classroom clip, show indicators and report, show dashboard update. |
    
    ### 17.1 Demo script (draft, 15 min)
    1. (2 min) The flatline chart. One sentence: "Ethiopia has measured children for 15 years. No one has given teachers a mirror."
    2. (2 min) A 20-second classroom clip. Ask judges to guess the teacher talk ratio.
    3. (4 min) Live: phone processes the clip in real time; indicators appear; teacher report in Amharic and Sidaamu Afoo.
    4. (3 min) Architecture: edge / sovereign / cloud; why audio never leaves the phone; MindSpore → MindSpore Lite INT8.
    5. (2 min) Results: indicator validity vs human coders; cross-language result; on-device benchmarks.
    6. (2 min) Pilot teacher quote + dashboard + roadmap.
    
    Prepare Q&A answers for: accuracy in very noisy classes; teacher surveillance concerns; why not ASR; scaling to other languages; cost per school; data law compliance; why Android and not HarmonyOS.
    
    ---
    
    ## 18. Risks and mitigations
    
    | Risk | Likelihood | Impact | Mitigation |
    |---|---|---|---|
    | Ethics approval delayed | Med | High | Submit W0; start with synthetic data (D-6) so ML work is not blocked. |
    | Audio unusable (too noisy) | Med | High | W0 test recording. Test placement (desk vs wall), clip-on mic fallback. |
    | MindSpore/MindNLP bug blocks teacher model | Med | Med | Week-1 spike; ONNX escape hatch; keep student training in MindSpore. |
    | CHILD_SINGLE vs CHILD_CHORAL not separable | Low–Med | High | Synthetic choral simulation; add OVERLAP class; fall back to 4-class if needed and report. |
    | Sidaamu Afoo LID data scarce | High | Med | Collect consented adult speech early; accept 3-class (amh / non-amh / eng) v0. |
    | Phone too slow | Low–Med | High | Budget in W3 thin slice; reduce context window; frame skipping. |
    | Teachers perceive surveillance | Med | High | Private-by-default reports; co-design report wording with 2 teachers; consent copy reviewed by E-Learning Directorate. |
    | Team capacity | Med | High | Strict scope; roadmap items stay roadmap. |
    | Legal interpretation of Proclamation 1321/2024 | Med | Med | Get written guidance via university legal/ethics office; default to in-country storage for all personal data. |
    
    ---
    
    ## 19. Open decisions (ADR backlog)
    
    | ADR | Question | Default proposal |
    |---|---|---|
    | ADR-001 | Segmentation as frame classification vs clustering diarization | Frame classification, 5 classes |
    | ADR-002 | Student architecture | Small CRNN first; compact conformer if budget allows |
    | ADR-003 | Where classroom audio is used for training | In-country training node only; ModelArts for public data and pipeline; no raw classroom audio to cloud without explicit consent + legal sign-off |
    | ADR-004 | MindSpore Lite Java API vs JNI (C++) on Android | Java API first; JNI only if profiling demands |
    | ADR-005 | Backend language & framework | Python + FastAPI |
    | ADR-006 | Annotation tool | Self-hosted Label Studio (in-country) |
    | ADR-007 | Where real teacher identities live | Separate restricted table, or offline register |
    | ADR-008 | Dashboard stack | Server-rendered or light SPA; keep it simple |
    | ADR-009 | CI platform | GitHub Actions default; CodeArts if judged valuable for the pitch |
    | ADR-010 | Coaching rule table content | Co-designed with E-Learning Directorate and 2 pilot teachers |
    
    ADR template: `Context · Decision · Alternatives considered · Consequences · Status · Date`.
    
    ---
    
    ## 20. Glossary
    
    - **EGRA** — Early Grade Reading Assessment.
    - **GER / NER** — Gross / Net Enrolment Rate.
    - **O-class** — One-year pre-primary class annexed to a primary school.
    - **Woreda** — District-level administrative unit.
    - **SSL encoder** — Self-supervised speech representation model.
    - **Distillation** — Training a small model to imitate a large model's outputs.
    - **PTQ** — Post-training quantization.
    - **RTF** — Real-time factor: processing time ÷ audio duration.
    - **LID** — Language identification.
    - **k-anonymity** — Suppression rule ensuring any reported group has ≥ k members.
    - **MindIR / .ms** — MindSpore intermediate representation / MindSpore Lite model format.
    
    ---
    
    ## 21. References
    
    Evidence (§2)
    - RTI International / USAID EGRA summaries and analyses: Haile (2023), *Education Research International*, https://onlinelibrary.wiley.com/doi/10.1155/2023/9527369
    - USAID Ethiopia Education & Youth fact sheet (2020), ERIC ED628406, https://eric.ed.gov/?id=ED628406
    - RTI, Assessing Early Grade Reading Skills in Africa, https://www.rti.org/brochures/assessing-early-grade-reading-skills-africa
    - Yisihak (2024), teacher preparedness for early reading, https://onlinelibrary.wiley.com/doi/10.1155/2024/5596229
    - Atenu, Ethiopia Education Report 2024/25, https://blog.atenu.org/ethiopia-education-report-2024-25/
    - Alemu (2025), Reimagining ECCE, *SAGE Open*, https://journals.sagepub.com/doi/10.1177/21582440251379497
    - Mother-tongue instruction challenges: https://www.tandfonline.com/doi/full/10.1080/2331186X.2025.2545617 ; https://www.academia.edu/44993803/
    - ECCE implementation, Bahir Dar (2025): https://link.springer.com/article/10.1186/s40723-025-00146-1
    
    Huawei technology (§7)
    - MindSpore Lite overview: https://www.mindspore.cn/lite/en
    - MindSpore Lite downloads (Android-aarch64 package): https://www.mindspore.cn/lite/docs/en/r2.10.0/use/downloads.html
    - MindSpore Lite device-side docs (PTQ, Android): https://www.mindspore.cn/lite/docs/en/master/index.html
    - MindAudio: https://github.com/mindspore-lab/mindaudio
    - MindNLP (HF-compatible): https://github.com/mindspore-lab/mindnlp
    - Huawei Cloud Africa footprint (Cairo region serving Ethiopia among others): https://www.intelligentcio.com/africa/2024/05/23/huawei-cloud-announces-cairo-region/
    - Huawei Cloud Africa AZs (Egypt, Nigeria): https://www.itweb.co.za/article/huawei-cloud-expands-footprint-to-lead-new-era-of-african-digitisation/8OKdWMDXwKLMbznQ
    
    Competition (§17)
    - Innovation Competition criteria (creativity, system complexity, technical complexity, societal benefit, functionality): https://www.huawei.com/en/huaweitech/publication/202501/huawei-ict-competition
    - 2025–2026 Global Final format: https://www.huawei.com/minisite/ict-competition-2025-2026-global/en/index.html
    - 2024–2025 Innovation requirements (AI mandatory, fully functional systems): https://www.huawei.com/minisite/ict-competition-2024-2025-global/en/index.html
    - Huawei Talent portal (registration): https://e.huawei.com/en/talent/portal/
    
    Legal (§6, §11)
    - Proclamation No. 1321/2024 summaries (data localisation; minors under 16): https://digitalpolicyalert.org/change/12100 ; https://digitalpolicyalert.org/change/12099
    - Obtain the official gazette text via the university legal office before finalising ADR-003.
    # Sample codebase structure — Dimts
    
    A revision of the monorepo in architecture §12, reflecting [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) and [`ASSUMPTIONS.md`](ASSUMPTIONS.md). This is a proposal to ratify through ADRs, not scaffolding that has been built.
    
    ## What changed from §12, and why
    
    | Change | Reason |
    |---|---|
    | **Two deployment targets:** `infra/huawei-cloud/` (competition demo) and `infra/openeuler/` (pilot) | The 2026–27 rules require Huawei Cloud; the law requires in-country storage of personal data (A-C2, A-C3, D-6). |
    | **`tools/synthetic-lessons/`** generates both training mixtures and demo lesson records | The Regional stage re-runs code and datasets, and the demo can't use pilot data (A-C3). |
    | `ml/teacher/` uses **PyTorch/HF**; `ml/student/` uses **MindSpore** | MindNLP is discontinued (A-T8, D-1). |
    | `ml/features/` is our own log-mel code, with a Kotlin twin and parity tests | MindAudio is unmaintained. Feature drift between Python and Kotlin would silently break the model. |
    | `ml/lid/` with an MMS-LID baseline | Sidama LID models and data exist (A-D3). |
    | `ml/student/` supports conv-only (INT8) and CRNN (FP16 GRU) variants | Lite has no INT8 GRU/LSTM (A-T3). |
    | `edge/android/audio/` detects mic silencing and supports an external mic | Android silences capture during calls (A-T7); desk audio may be too noisy (A-T4). |
    | `edge/android/storage/` uses SQLCipher + Keystore | Jetpack Security is deprecated (A-T11). |
    | `indicators-core/` handles **missing** spans | Silenced audio must not count as NON_SPEECH. |
    | `tools/check-elf-alignment/` in CI | The 2.10.0 AAR is 4 KB-aligned; catch it before any Play release (A-T6). |
    | `docs/competition/`, `docs/legal/` | Template 1/2 drafts; Art. 33 registration, DPIA and consent packs. |
    | Research PDFs stay in a **separate private repository** | The competition requires open-sourcing; `docs/research/papers/` holds copyrighted PDFs. |
    
    ## Tree
    
    ```
    dimts/
    ├── README.md                         ← what it is, quick start for the demo (reproducible by judges)
    ├── LICENSE                           ← open-source licence (needed for Regional shortlist); e.g. Apache-2.0
    ├── .gitignore                        ← includes .DS_Store, *.wav, *.ms, data/, .env
    ├── docs/
    │   ├── ARCHITECTURE_CONTEXT.md
    │   ├── adr/                          ← ADR-000 template; ADR-001…011 (011 = two-tier deployment, D-6)
    │   ├── indicator-spec.md             ← mirrors §5; versioned; I-3 framed as balance indicator
    │   ├── indicator-rationale.md        ← RQ-B1 literature matrix
    │   ├── annotation-guide.md           ← class definitions + audio examples; missing/silenced span rule
    │   ├── ethics/                       ← consent + assent forms (amh, sid, eng), IRB approval, breach runbook (Art. 43 + 44)
    │   ├── legal/                        ← Art. 33 registration record, DPIA (Art. 47), legal-advisor Q&A (WS-F)
    │   └── competition/                  ← Template 1 draft, Template 2 slides, demo script, reproduction guide
    │
    ├── indicators-core/                  ← single source of truth for I-1…I-8
    │   ├── python/dimts_indicators/      ← reference implementation (Python ≥ 3.10)
    │   │   ├── timeline.py               ← Segment(start_s, end_s, label, conf), labels incl. MISSING
    │   │   ├── postprocess.py            ← min-duration merge, gap bridging, LID inheritance
    │   │   ├── indicators.py             ← I-1…I-8, spec_version
    │   │   └── calibration.py            ← E7: confusion-matrix error propagation, intervals
    │   ├── kotlin/                       ← Android port (Gradle module, pure Kotlin, no Android deps)
    │   └── golden/                       ← timelines + expected outputs; both impls must match (1e-6)
    │       ├── 001_basic.json
    │       ├── 014_call_interruption.json   ← MISSING span mid-lesson (Android silencing)
    │       └── 020_choral_drill.json        ← near-zero latencies; I-2 split by response type
    │
    ├── ml/
    │   ├── configs/                      ← YAML per experiment (E2…E7)
    │   ├── data/
    │   │   ├── DATASETS.md               ← licence, gating, register, overlap notes (E8)
    │   │   └── manifests/                ← file lists + splits by teacher/school; NO audio in git
    │   ├── features/
    │   │   ├── logmel.py                 ← 64-bin, 25 ms / 10 ms, streaming normalisation
    │   │   └── parity/                   ← fixtures shared with edge/android/pipeline (Kotlin twin)
    │   ├── synth/ → see tools/synthetic-lessons
    │   ├── teacher/                      ← PyTorch + Hugging Face (XLS-R / MMS / HuBERT) — server only
    │   ├── lid/                          ← MMS-LID-256 baseline (E3), student LID head
    │   ├── student/                      ← MindSpore; variants: tcn_int8/, crnn_fp16gru/, conformer_tiny/
    │   ├── distill/                      ← soft labels from teacher → student (E6)
    │   ├── export/
    │   │   ├── mindir_to_ms.sh           ← MindSpore → MindIR → converter_lite → .ms (+ PTQ full-quant)
    │   │   ├── onnx_to_ms.sh             ← escape hatch: PyTorch → ONNX → .ms
    │   │   └── model_card.py             ← writes model_card.json (feature-config hash, calib-set hash, licence of upstream models)
    │   ├── eval/                         ← frame F1, indicator MAE/ρ, cross-language, teacher-independent splits
    │   └── modelarts/                    ← job specs; AF-Johannesburg; OBS same region; PUBLIC data only
    │
    ├── edge/android/                     ← Kotlin, Jetpack Compose; minSdk 29
    │   ├── app/                          ← UI, i18n (amh, sid, eng); report view; consent screens
    │   ├── audio/
    │   │   ├── CaptureService.kt         ← foregroundServiceType="microphone", started only from UI
    │   │   ├── RingBuffer.kt             ← ≤ 10 s, RAM only
    │   │   ├── SilencingMonitor.kt       ← AudioRecordingCallback → emits MISSING spans
    │   │   └── MicSource.kt              ← built-in vs wired/USB lavalier (D-7)
    │   ├── pipeline/                     ← features (Kotlin twin of logmel.py) → seg → LID → postprocess
    │   ├── inference/                    ← MindSpore Lite 2.10.0 AAR (Java API); ONNX Runtime fallback flag
    │   ├── storage/                      ← Room + SQLCipher; key wrapped by Android Keystore
    │   ├── sync/                         ← WorkManager outbox; HTTPS; per-record ACK; idempotent on UUIDv7
    │   └── benchmark/                    ← RTF / RSS / battery harness (S2); wraps Lite `benchmark` tool
    │
    ├── server/                           ← Python FastAPI; same code runs in both deployments
    │   ├── api/                          ← /v1/lessons, /v1/reports, /v1/consent, /v1/teacher/{id}
    │   ├── jobs/                         ← weekly reports (rule table), k-anonymity aggregation, purge
    │   ├── db/migrations/                ← SQL kept to the PostgreSQL/openGauss common subset
    │   └── tests/
    │
    ├── dashboard/                        ← district view; doubles as the competition "visual demo page"
    │
    ├── tools/
    │   ├── synthetic-lessons/            ← ONE generator, two outputs:
    │   │   ├── audio_mix.py              ←   (a) training mixtures: Afrivoice/FLEURS/ALFFA + MUSAN + RIR + choral sim
    │   │   └── lesson_records.py         ←   (b) synthetic LessonRecords + timelines for the cloud demo
    │   ├── lesson-simulator/             ← web page: play a synthetic/public clip → live indicators (demo)
    │   ├── check-elf-alignment/          ← fails CI if any bundled arm64 .so has PT_LOAD align < 16384 (warn-only for pilot)
    │   └── demo/                         ← scripted demo accounts, seed data (synthetic only)
    │
    └── infra/
        ├── huawei-cloud/                 ← COMPETITION DEMO — synthetic/public data only
        │   ├── README.md                 ← one-command redeploy; region AF-Johannesburg
        │   ├── ecs/                      ← server + dashboard + simulator containers
        │   ├── rds/                      ← RDS for PostgreSQL (or GaussDB) schema init
        │   └── obs/                      ← public datasets + model artefacts (unencrypted bucket; no personal data)
        ├── openeuler/                    ← PILOT — personal data, Hawassa University
        │   ├── compose/                  ← server, openGauss, Label Studio, backup
        │   └── backup/                   ← encrypted, in-country
        └── ci/                           ← GitHub Actions: golden tests (py + kt), feature parity,
                                             model-contract check, ELF alignment, secret scan
    ```
    
    ## The data boundary
    
    | | `infra/huawei-cloud/` (competition) | `infra/openeuler/` (pilot) |
    |---|---|---|
    | Data | Synthetic lessons, public corpora, model artefacts | LessonRecords from consenting teachers, Research Mode audio |
    | Personal data | **None**, enforced by seeding only from `tools/synthetic-lessons` and `tools/demo` | Yes. Art. 22(1): stored in Ethiopia. |
    | Who writes | Deploy scripts only | Phones (sync API), annotators (Label Studio) |
    | Data path to the other tier | None | None (model artefacts flow cloud → pilot only) |
    | Reproducible by judges | Yes (README + generator) | No, and not required |
    
    ## Contract changes worth making now
    
    **`LessonRecord` v1 → v2 (§10.1):**
    
    ```jsonc
    {
      "schema_version": 2,
      // …all v1 fields…
      "capture": {
        "mic": "builtin|wired_lavalier|usb",   // E1 / D-7
        "missing_s": 42.5,                     // silenced spans (calls, mic taken); excluded from I-7/I-8
        "class_size_estimate": 58              // entered by teacher; C-5
      },
      "indicators": {
        // …I1…I8…
        "I2_response_latency_s": { "choral": {"median": 0.2, "n": 30}, "single": {"median": 1.4, "n": 12} },
        "I3_choral_ratio": 0.71                // reported as a balance indicator
      },
      "model_versions": { "seg": "…", "lid": "…", "upstream_licences": ["CC-BY-NC-4.0 (MMS-LID, teacher only)"] }
    }
    ```
    
    **`model_card.json`** gains `upstream_models` (with licences), `precision_by_layer` (records which layers stayed FP16 because Lite lacks INT8 GRU), and `page_alignment` of the runtime used in the benchmark.
    
    ## Ownership (from §15)
    
    | Area | Owner |
    |---|---|
    | `indicators-core/python`, `golden/`, `docs/` (ethics, legal, competition, indicator-rationale) | Lead |
    | `ml/`, `tools/synthetic-lessons/audio_mix.py` | ML |
    | `edge/`, `server/`, `dashboard/`, `infra/`, `tools/check-elf-alignment`, `tools/lesson-simulator` | Sys |
    | `indicators-core/kotlin`, `ml/features/parity` | Sys + ML |
    ✓ • 0ms

## Activity

    $ cat docs/research/README.md docs/research/ASSUMPTIONS.md docs/research/RESEARCH_PLAN.md
    # Dimts research pack
    
    Research audit and plan for the Dimts (ድምጽ) proposal described in `DIMTS_ARCHITECTURE_CONTEXT.md` (status "pre-architecture", 2026-09-25). Finalised 2026-09-26 after reading the supplied full texts. No code exists yet, so "the codebase" here means the design in §7–§13 of that document.
    
    | File | What it is |
    |---|---|
    | [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) | Each factual claim and assumption in the architecture document, checked against sources (2026-09-25; updated 2026-09-26 with supplied full texts), with a verdict and the change it implies. |
    | [`RESEARCH_PLAN.md`](RESEARCH_PLAN.md) | What is still open: research questions, reading lists, experiments, infrastructure spikes, legal questions, team decisions and a schedule merged with the 2026–27 competition calendar. |
    | [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md) | Every source used or recommended, in APA 7th style, tagged with how far it was checked. |
    | [`ASSUMPTIONS.md`](ASSUMPTIONS.md) | Register of the 50 assumptions the design rests on: origin, status, evidence, test and impact if wrong. |
    | [`CODEBASE_STRUCTURE.md`](CODEBASE_STRUCTURE.md) | Proposed monorepo layout revised from §12 in light of the findings, including the two-tier (competition cloud / in-country pilot) deployment. |
    | [`TO_OBTAIN.md`](TO_OBTAIN.md) | Sources still needed, and what the supplied batch resolved. |
    | [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md) | Ethiopian hosting/cloud providers checked against Article 22 (data sovereignty), for the `infra/openeuler` sovereign-tier server. |
    | [`papers/`](papers/) | Full texts supplied on 2026-09-26. Copyrighted, so keep them private (see TO_OBTAIN). |
    
    ## Verdict labels
    
    The bar for **verified** is a primary source, a peer-reviewed paper, or official documentation or software that was actually opened. When the primary could not be opened, an abstract or a reputable secondary source is used and labelled as such. Neither counts as verified.
    
    | Label | Meaning |
    |---|---|
    | **VERIFIED** | Confirmed in a primary, peer-reviewed or official source that was opened (full text, official page, released package or binary). |
    | **VERIFIED (abstract)** | Confirmed only from the primary source's own abstract or registry metadata (Crossref, ERIC, Europe PMC). |
    | **SECONDARY** | Supported only by a secondary source (blog, press, search-engine summary). The primary was not opened. |
    | **PARTLY** | The fact is right but the claim goes beyond it (scope, date, sample, generalisation). |
    | **CONTRADICTED** | A primary source says otherwise. |
    | **UNVERIFIED** | Could not be checked. The plan says how to check it. |
    
    ## Access limits during the first pass (2026-09-25)
    
    Most of the gaps below were closed by the papers supplied on 2026-09-26; see [`TO_OBTAIN.md`](TO_OBTAIN.md).
    
    - **Refused by the site's bot protection (HTTP 403):** Wiley/Hindawi, Taylor & Francis, SAGE, ScienceDirect, ResearchGate, policycommons.net and the Huawei Talent portal. For those papers, Crossref, ERIC or Europe PMC metadata and abstracts were used instead.
    - **USAID's document archive** (`pdf.usaid.gov`, `dec.usaid.gov`) was unreachable (gateway 502), so the EGRA 2014/2016/2018 reports could not be opened.
    - **`web.archive.org` and several `.gov.et` hosts** were unreachable.
    - **`moe.gov.et`** served Indonesian gambling spam on its `/resources/annual-abstract/` pages. The site looks compromised, so nothing was downloaded from it. Get the ESAA from the Ministry through official channels.
    - **Search results are not evidence.** Web-search summaries were used only to find sources. Anything that rests on a search summary is labelled SECONDARY.
    # Assumptions register — Dimts
    
    Status as of 2026-09-26. This lists every assumption the design still rests on, where it comes from, how strong the evidence is, and how it will be tested. Evidence is detailed in [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md); tests are scheduled in [`RESEARCH_PLAN.md`](RESEARCH_PLAN.md).
    
    **Origin column:**
    
    - **ARCH** — stated in `DIMTS_ARCHITECTURE_CONTEXT.md`.
    - **PACK** — introduced by this research pack. These are my working assumptions, so challenge them.
    
    **Status column:**
    
    | Status | Meaning |
    |---|---|
    | ✅ Holds | Verified against a primary source. |
    | 🟡 Plausible | Supported, but not yet tested in the Dimts setting. |
    | ❓ Untested | No evidence either way yet. |
    | ❌ Changed | Contradicted; the replacement assumption is given. |
    
    ---
    
    ## Summary
    
    - **Out of 50 assumptions:**
      - 7 hold;
      - 16 are plausible but untested in this setting;
      - 16 are untested;
      - 11 were contradicted and have been replaced.
    - **The five that could sink the project if wrong:**
      - **A-T4:** a desk-placed phone yields usable audio.
      - **A-D5:** CHILD_SINGLE and CHILD_CHORAL are separable.
      - **A-L2:** incidental child audio can be handled under the ethics approval.
      - **A-C3:** a synthetic-data cloud demo satisfies both the competition and the law.
      - **A-O1:** ethics approval arrives by W3–4.
    - **The biggest changes from the architecture document:**
      - The competition now *requires* Huawei Cloud (A-C2).
      - MindNLP is gone (A-T8).
      - Sidama speech data exists (A-D3).
      - Teachers do get *some* observation, and it matters (A-P2).
    
    ---
    
    ## P — Problem and pedagogy
    
    | ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
    |---|---|---|---|---|---|---|
    | A-P1 | Early-grade reading is low and flat (≈32% at the top two benchmarks, 2014–2018). | ARCH §2.1 | ✅ Holds (corrected: 2016 = 34.3%) | AIR (2019), p. ix | — | — |
    | A-P2 | Teachers get **no** feedback on classroom practice. | ARCH §2.2 | ❌ Changed | EGRA 2018: some schools have mother-tongue class observers, and observation frequency is associated with reading (d = 0.15–0.28) (AIR, 2019, pp. 53–54) | **Replacement:** observation is uneven, infrequent and unstandardised, and where it exists it is associated with better reading. Dimts makes it frequent, standardised and teacher-owned. RQ-A3 / Lead | The pitch has to lean on "frequency and standardisation" |
    | A-P3 | Amharic-medium and Sidaamu Afoo-medium pilot schools are reachable from Hawassa. | PACK | ❓ Untested | ESAA 2024/25 excludes Amhara because schooling there is disrupted (MoE, 2025, PDF p. 10) | Confirm Amharic-medium schools in Hawassa city or Sidama. Lead, W1 | The cross-language experiment (§14.3) loses one language |
    | A-P4 | Teacher capability is the binding constraint. | ARCH §2.2 | ❌ Changed | MTTCA ranks home, then school, then teacher (AIR, 2020) | **Replacement:** "a significant, addressable constraint" | — |
    | A-P5 | Talk ratio (I-1) is a meaningful quality proxy that teachers can change with feedback. | ARCH §5 | 🟡 Plausible | RCT −5 pp talk share (Demszky & Liu, 2023, US online); Liu & Cohen (2021) | Pilot pre/post plus the expert panel (B2) | Report it only as descriptive |
    | A-P6 | Short response latency (I-2) signals insufficient wait time. | ARCH §5 | 🟡 Plausible, confounded | Rowe (1986); Tobin (1987). Choral drills have near-zero latency by design. | Split I-2 by response type; question detection (R-4) | Drop the coaching rule for I-2 |
    | A-P7 | High choral ratio (I-3) is undesirable. | ARCH §5 | ❌ Changed | Heward et al. (1989) vs Pontefract & Hardman (2005) | **Replacement:** I-3 is a *balance* indicator | — |
    | A-P8 | Classroom language has not been measured systematically, so I-6 is novel. | ARCH §2.4 | ✅ Holds (as "automated, systematic") | Heugh et al. (2007): notes, checklist abandoned. Piper et al. (2016): child self-report. | Systematic search C1 before claiming "first" | Reword to "first automated" |
    | A-P9 | Teachers switch between Amharic, Sidaamu Afoo and English within lessons. | ARCH §6 C-6 | 🟡 Plausible | Heugh et al. (2007) observed routine Amharic–English code-switching | RQ-A4 classroom visits | The LID label set changes |
    | A-P10 | Teachers will use a private, non-punitive weekly report. | ARCH §3 | ❓ Untested | Jensen et al. (2020): feedback is usually evaluative. Birnhack & Perry-Hazan (2021): teachers weigh their own privacy. | Pilot interviews (§14.5), co-design | Product redesign |
    | A-P11 | Classrooms have 40–80 children. | ARCH §6 C-5 | 🟡 Plausible (averages) | Pupil-section ratio, Grades 1–6: national 54.2, Sidama 59.6 (MoE, 2025) | Record class size per pilot lesson | Augmentation ranges change |
    | A-P12 | O-class pedagogy can be coached with the same indicators as Grades 1–3. | ARCH §1 | ❓ Untested | Play-based ECE differs (CLASS, MELE) | RQ-B3 | O-class becomes measure-only |
    
    ## T — Technical (device, stack, infrastructure)
    
    | ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
    |---|---|---|---|---|---|---|
    | A-T1 | Teachers own Android 10+ phones with about 2 GB RAM. | ARCH C-2 | 🟡 Plausible | StatCounter: Android 94%, mostly v11+ (web traffic) | Phone survey of pilot teachers. Sys, W1 | Provide reference phones |
    | A-T2 | RTF ≤ 0.3 and RSS ≤ 300 MB on the target phone. | ARCH C-3 | ❓ Untested | Schlotterbeck et al. (2021) ≈ RTF 0.12 on unspecified hardware | S2 with the bundled `benchmark` tool | Smaller model or frame skipping |
    | A-T3 | All on-device inference is INT8. | ARCH C-2 | ❌ Changed | Lite r2.10.0: GRU/LSTM FP16/FP32 only | **Replacement:** conv/attention layers INT8, recurrent layers FP16 (or a convolution-only student) | — |
    | A-T4 | A phone lying on the teacher's desk captures usable audio. | ARCH §3 | ❓ Untested — **critical** | Jensen et al. (2020): room mics "produced noisy audio". Schlotterbeck et al. (2021): lavalier into a smartphone. | E1: desk vs wired lavalier. ML, W1 | Add a clip-on mic to the kit (D-7) |
    | A-T5 | MindSpore Lite 2.10.0 runs on Android via the Java API. | ARCH §7 | ✅ Holds (package) / ❓ on device | AAR inspected; minSdk 19 | S1 | JNI or ONNX fallback |
    | A-T6 | The app can be published on Google Play later. | PACK | ❓ Untested | The AAR is 4 KB-aligned; Play requires 16 KB for API 35+ updates from 2027-02-01 | S8 | Rebuild Lite with 16 KB alignment |
    | A-T7 | Capture runs uninterrupted for 40 minutes. | ARCH §8.3 | 🟡 Plausible, with gaps | Android silences the mic during calls or when a higher-priority app takes it | S7; mark silenced spans as missing | Indicators computed on partial lessons |
    | A-T8 | MindNLP loads the SSL teacher encoder. | ARCH §7.2 | ❌ Changed | The repository pivoted to MindAct; legacy branch frozen | **Replacement:** PyTorch/HF teacher; student in MindSpore (D-1) | — |
    | A-T9 | MindSpore 2.10 trains on the in-country node. | ARCH §7.2 | ❓ Untested | 2.10 notes are Ascend-focused | S4 | Train the student in PyTorch too; keep Lite for inference |
    | A-T10 | openGauss works with the Python backend. | ARCH §12 | 🟡 Plausible | Known SASL/sha256 workaround | S6 | Fall back to PostgreSQL (same SQL) |
    | A-T11 | Encrypted local storage via SQLCipher or Jetpack Security. | ARCH §8.3 | ❌ Changed | Jetpack Security deprecated | **Replacement:** SQLCipher + Keystore-wrapped key | — |
    | A-T12 | Huawei Cloud AF-Johannesburg offers what the demo needs (ECS, RDS/GaussDB, OBS, ModelArts). | PACK | 🟡 Plausible | Huawei recommends AF-Johannesburg for African users (Huawei Cloud, 2026a). ModelArts needs OBS in the same region, unencrypted (Huawei Cloud, 2026b). | S5 | Use AF-Cairo |
    
    ## D — Data and ML
    
    | ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
    |---|---|---|---|---|---|---|
    | A-D1 | A fixed 5-class frame classifier beats clustering diarization for this task. | ARCH ADR-001 | 🟡 Plausible | VTC (Lavechin et al., 2020); diarization weak in classrooms (Wang et al., 2025) | E2 | — |
    | A-D2 | No ASR is needed for any indicator. | ARCH C-1 | ✅ Holds | Precedents: DART, Schlotterbeck et al., Chanchal & Zualkernan | — | — |
    | A-D3 | Sidaamu Afoo speech data is scarce. | ARCH D-4 | ❌ Changed | Afrivoice about 603 h (225 h transcribed); WAXAL `sid_asr`; MMS `sid` | **Replacement:** public adult Sidama speech is plentiful. *Classroom-register* speech is what's scarce. | — |
    | A-D4 | LID works on teacher segments ≥ 2 s. | ARCH §5 | ❓ Untested | Short-segment LID is hard; the thresholds conflict across §5, §8.3 and §14.1 | E3 | Raise to 3–5 s; more "unknown" labels |
    | A-D5 | CHILD_SINGLE and CHILD_CHORAL are acoustically separable. | ARCH §9 | ❓ Untested — **critical** | No benchmark exists. Speaker-count estimation is the nearest precedent (Stöter et al., 2018). | E4 | 4-class fallback (§18) |
    | A-D6 | Teacher–student distillation gets a tiny model close to a large one. | ARCH §8.2 | 🟡 Plausible | Chang et al. (2022); Schmid et al. (2023) | E6 | Supervised small model only |
    | A-D7 | 6–10 annotated hours with κ ≥ 0.70 is achievable. | ARCH §9.3 | ❓ Untested | — | Annotation pilot, W3–4 | Fewer classes or more hours |
    | A-D8 | Frame-level errors don't wreck indicator validity. | PACK | ❓ Untested | Errors bias downstream estimates (Gautheron et al., 2025) | E7 | Report calibrated indicators with intervals |
    | A-D9 | MMS models (CC-BY-NC) may be used for this project. | PACK | 🟡 Plausible | Non-commercial thesis and competition | Note in model cards | Replace them before any commercial use |
    | A-D10 | Synthetic classroom mixtures give a useful pre-training signal. | ARCH D-6 | 🟡 Plausible | Common practice (Ko et al., 2017) | E2/E4 comparisons | Rely on real data only |
    
    ## L — Legal and ethics
    
    | ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
    |---|---|---|---|---|---|---|
    | A-L1 | Personal data must be stored in Ethiopia. | ARCH C-7 | ✅ Holds | Proclamation 1321/2024, Art. 22(1) | — | — |
    | A-L2 | Incidental child audio needs only school/parent *notification*. | ARCH §11 | ❌ Changed | Art. 11: parent/guardian consent or vital interest | **Replacement:** plan for parental consent in Research Mode, and ask whether Deployment Mode's in-RAM processing needs it (WS-F Q2) | Research Mode limited to consenting classes |
    | A-L3 | Voice audio without speaker ID isn't biometric/sensitive data. | PACK | 🟡 Plausible | Art. 2(6) ties biometric data to unique identification | Legal advisor, WS-F Q4 | Art. 22(3) approval needed for any transfer |
    | A-L4 | A student research project is exempt from Art. 33 registration. | PACK | ❓ Untested | — | WS-F Q1 | Register with the Ethiopian Communications Authority before recording |
    | A-L5 | Indicator records under a pseudonymous teacher ID are personal data. | PACK | ✅ Holds (by definition, Art. 2) | Pseudonymisation defined; still personal data | — | — |
    | A-L6 | The 72-hour breach notice goes to the Authority only. | ARCH §11 | ❌ Changed | Art. 44 also covers data subjects | **Replacement:** notify both | — |
    
    ## C — Competition
    
    | ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
    |---|---|---|---|---|---|---|
    | A-C1 | The Preliminary submission is due around **1 Dec 2026**. | PACK | ❓ Untested | The page says only "Nov–Dec 2026" | Read the template and portal deadline. Lead, W0 | Pull W7–8 work forward |
    | A-C2 | The Huawei stack is a *preference*. | ARCH C-11 | ❌ Changed | 2026–27: "must be deployed on Huawei Cloud and use at least one Huawei Cloud service" | **Replacement:** Huawei Cloud deployment is mandatory for the competition build | — |
    | A-C3 | A Huawei Cloud demo holding **only synthetic or public data** satisfies both the competition ("complying with local laws") and Proclamation 1321/2024. | PACK | 🟡 Plausible — **critical** | No personal data means Art. 22 doesn't apply; the competition only requires lawful operation | Legal advisor WS-F Q8, in writing | Demo hosted in Ethiopia with only a thin Huawei Cloud service (e.g. RDS for public aggregates) |
    | A-C4 | Ethiopia competes in the "Northern Africa" division. | PACK | 🟡 Plausible | The Huawei Technologies Ethiopia registration guide points to the Northern Africa division | Email the Huawei Ethiopia contact | Different schedule or rules |
    | A-C5 | A live Android demo will count. | ARCH §17 | ✅ Holds (optional) | "Hardware devices are optional … prepare them on their own" | — | — |
    | A-C6 | Scoring rewards system and technical complexity. | ARCH §17 | ❌ Changed → see A-C2 | Prelim/National score Innovation 60 / Application value 40 | Pitch leads with innovation and application value; complexity supports them | — |
    | A-C7 | The team can open-source the product code by the Regional stage. | PACK | ❓ Untested | Mandatory for Regional and Global shortlists | Team decision (RESEARCH_PLAN §6 Q7) | Not eligible beyond National |
    
    ## O — Operational
    
    | ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
    |---|---|---|---|---|---|---|
    | A-O1 | Ethics approval arrives by W3–4. | ARCH §16 | ❓ Untested — **critical** | — | Ask the IRB for typical turnaround. Lead, W0 | Synthetic-only ML until approval; the Preliminary uses synthetic data |
    | A-O2 | Three people can deliver edge, server, dashboard, ML and cloud demo by December. | ARCH C-10 | 🟡 Plausible, tight | The scope grew with the cloud demo tier (S10) | Weekly burn-down | Cut the dashboard to one view; the demo page reuses it |
    | A-O3 | Twenty to thirty lessons can be recorded by W5–6. | ARCH §16 | ❓ Untested | Depends on A-O1 and on teachers | Recruitment log | Fewer lessons, more augmentation |
    # Research plan — Dimts (final, v1.0)
    
    Status as of 2026-09-26. This plan builds on [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md), which is now updated with the full texts in [`papers/`](papers/). Related files:
    
    - [`ASSUMPTIONS.md`](ASSUMPTIONS.md) — every assumption the design still rests on.
    - [`CODEBASE_STRUCTURE.md`](CODEBASE_STRUCTURE.md) — the repository layout that follows from these findings.
    
    Owners follow §15 of the architecture document:
    
    - **Lead** — Anteneh
    - **ML** — Person 2
    - **Sys** — Person 3
    
    Weeks count from W0 = 2026-09-25. Sources are cited (Author, year) and listed in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).
    
    ---
    
    ## 1. What the evidence now says
    
    1. **The problem is real and measured.**
       - Only 32.4% of Grade 2–3 pupils reached the top two reading benchmarks in 2018, essentially unchanged since 2014 (31.3%) and 2016 (34.3%). Only 6.2% read fluently with full comprehension (AIR, 2019).
       - Only 39% of mother-tongue teachers are Proficient-or-above, and Sidaamu Afoo teachers sit in the 22–33% band (AIR, 2020).
    2. **Observation-based feedback is associated with better reading, but it is patchy.** Where a school has someone who observes mother-tongue classes, and observes more often, pupils read better (AIR, 2019, pp. 53–54). There is no standardised or frequent measure. This is the gap Dimts fills. "No feedback at all" overstates it.
    3. **Classroom language has never been measured systematically.** Earlier work used handwritten observation notes (Heugh et al., 2007) or children's self-reports (Piper et al., 2016). Automated I-6 is a defensible novelty claim.
    4. **Audio-only classroom analytics work at coarse grain.** Reported results: ~90% (Owens et al., 2017), >80% (Schlotterbeck et al., 2021), F1 0.64–0.78 (Donnelly et al., 2016). Fine-grained teacher-vs-child separation is still hard: 69% in Wang et al. (2025).
    5. **Talk-time feedback changes behaviour.** In an RCT, automated feedback cut talk share by 5 pp (Demszky & Liu, 2023). No such evidence exists for choral ratio or language share yet.
    6. **The competition has changed shape.**
       - 2026–27 requires an AI application deployed on **Huawei Cloud** with a **visual demo page**.
       - Preliminary and National stages score **Innovation 60% / Application value 40%**.
       - Regional-stage entries must be reproducible and open source.
       - The Preliminary runs **Nov–Dec 2026**, earlier than the architecture doc's feature freeze.
    7. **Some stack assumptions broke:**
       - MindNLP is discontinued and MindAudio is unmaintained.
       - Lite has no INT8 GRU.
       - The AAR is 4 KB-aligned.
       - Jetpack Security is deprecated.
       - Sidama speech data and models do exist.
    
    ---
    
    ## 2. Principles
    
    1. **Evidence bar.** Only **VERIFIED** claims go into the thesis or pitch. Anything weaker is written as "reported by …".
    2. **One log.** Every search goes into `docs/research/search-log.csv` with columns `date, database, query, hits, screened, kept, notes`, so the review is PRISMA-style.
    3. **Check before you build.** Don't ratify an ADR until its research question closes.
    4. **Two data worlds.** Personal data (the pilot) stays in-country. The competition cloud demo holds synthetic or public data only (ASSUMPTIONS A-C3).
    5. **Reproducible by a stranger.** The Regional stage re-runs your code and data, so every number in the pitch must come from a script in `ml/eval/` or `tools/demo/`.
    
    ---
    
    ## 3. Workstreams
    
    Status is **Done**, **Open** or **Blocked** (waiting on an item in [`TO_OBTAIN.md`](TO_OBTAIN.md)).
    
    ### WS-A: Problem evidence (Lead)
    
    | RQ | Question | Status | Result or next step |
    |---|---|---|---|
    | A1 | EGRA 2014–2018 results | **Done** | 31.3 / 34.3 / 32.4% top-two levels, Grades 2–3, 7 languages. The Sidaamu Afoo 2016 value is flagged as unreliable (AIR, 2019). |
    | A2 | Pre-primary figures | **Done** | ESAA 2024/25: GER 59.8% excluding Amhara. The 75% O-class/child-to-child share is from ESAA 2021/22. Class size in Grades 1–6: 54.2 nationally, 59.6 in Sidama. |
    | A3 | How often are teachers observed, by whom, and with what feedback? | **Open** | EGRA Table 16 shows association but not base rates. Run 5–10 interviews (teachers, unit leaders, woreda supervisors) during W1–W3 school visits. Output: a 1-page memo. Due W3. |
    | A4 | Actual classroom languages and code-switching in the pilot schools | **Open** | Heugh et al. (2007) observed routine Amharic–English switching. Confirm for Sidama and Hawassa schools in W1 visits. This sets the LID label set. Due W2. |
    | A5 | Home vs school vs teacher factors | **Done** | MTTCA ranks home, then school, then teacher (AIR, 2020). Framing updated. |
    | A6 | Current mother-tongue policy grade span | **Blocked** (ETP 2023 text) | Needed for the §2.4 wording. Due W2. |
    
    ### WS-B: Pedagogy and indicator validity (Lead)
    
    The core thesis question: *Do Dimts' acoustic indicators measure something Ethiopian teachers and experts recognise as instructional quality, and in which direction?*
    
    | RQ | Question | Status | Method | Output | Due |
    |---|---|---|---|---|---|
    | B1 | Construct and normative direction for I-1 to I-8 | Open | Build the indicator × literature matrix (§9 reading list). I-1 now has RCT support (Demszky & Liu, 2023). | `docs/indicator-rationale.md` | W2 |
    | B2 | Local expert validation | Open | Panel of 5–8 people (Hawassa College of Education, READ alumni, E-Learning Directorate) rating relevance and direction. | Inputs to ADR-010 | W5 |
    | B3 | O-class specifics | Open | CLASS, MELE, Teach ECE; Alemu (2025). | Scope decision D-4 | W3 |
    | B4 | Feedback design | Open | Kraft et al. (2018); Demszky et al. (2023, 2025); Jensen et al. (2020); Hattie & Timperley (2007). | Report copy rules | W4 |
    | B5 | Surveillance perception | Open | Birnhack & Perry-Hazan (2021): nearly all teachers weigh their own privacy. Co-design with 2 teachers. | Consent copy | W3 |
    | B6 | I-3 as a balance indicator | Open | Heward et al. (1989) vs Pontefract & Hardman (2005). | I-3 reporting rule | W2 |
    
    ### WS-C: Prior systems and novelty (Lead + ML)
    
    - **C1: Systematic search** (protocol in §8). Novelty statement for I-6 and choral detection. Due W2.
    - **C2: Comparison table.** Done except the IEEE papers; see the table below.
    - **C3: Competition benchmarking.** Review previous Innovation winners in the Learning Space (the page links "reference entries from previous competitions"). Due W4.
    
    | System | Input → output | Setting | Result | Take-away for Dimts |
    |---|---|---|---|---|
    | DART (Owens et al., 2017) | Volume/variance → single, multiple or no voice | 1,486 US college sessions | ~90% | Closest no-ASR precedent; coarse classes |
    | Schlotterbeck et al. (2021) | Lavalier + smartphone; amplitude, mel and MFCC statistics → COPUS practices | 42 lessons, 19 teachers, Chile | >80% accuracy (2 practices); 7 min per hour of audio | Cheap-phone precedent; **clip-on mic** |
    | Chanchal & Zualkernan (ED626900) | Mel spectrograms → Stallings categories | Semi-rural Pakistan, phones | F1 0.83; 0.72–0.81 with 5–20% labels | Semi-supervised learning with few labels |
    | Donnelly et al. (2016) | Teacher audio, speech/rest, ASR, acoustic features → 5 segment types | 76 classes, 11 teachers | F1 0.64–0.78, teacher-independent | Validate across teachers, not frames |
    | Jensen et al. (2020) | Headset, ASR, ML → discourse features | 142 sessions, 89% usable | Moderate accuracy | Room mics noisy; usability metric |
    | M-Powering Teachers (Demszky & Liu, 2023; Demszky et al., 2023, 2025) | Transcripts → talk time and uptake feedback | US; RCTs | −5 pp talk time; +10–13% uptake | Automated feedback changes practice |
    | Classroom diarization (Wang et al., 2025) | ECAPA + Whisper | US group work | Teacher-vs-child 69% | Realistic difficulty |
    | VTC (Lavechin et al., 2020) | SincNet + RNN → 4 voice types | 260 h, 10 languages | Beats LENA | Frame-classifier baseline |
    | LENA feedback (Wang et al., 2013) | LENA → talk ratio | US maths PD | SECONDARY | Teacher-facing precedent |
    | Shapsough et al. (2016) | Mobile EGRA + MQTT | LMIC assessment | Low power | Sync design context |
    | Human tools: Teach, Stallings, CLASS, and Heugh et al. (2007) field notes | Observers | Global / Ethiopia | Validated or qualitative | What Dimts complements |
    
    ### WS-D: ML and data (ML)
    
    | Experiment | Question | Method | Feeds | Due |
    |---|---|---|---|---|
    | E1 | Usable audio: desk vs clip-on | W0–W1 test lessons. Measure SNR, clipping and RT60 estimate. Compare desk phone with a wired lavalier into the same phone (Schlotterbeck et al., 2021; Jensen et al., 2020). | Capture spec; ASSUMPTIONS A-T4 | W1 |
    | E2 | How hard is T-1 on real Ethiopian audio? | 1–2 annotated lessons; VTC, pyannote-seg-3.0 and a DART-style energy baseline; per-class F1 with teacher-independent splits. | §14.1 realism; 4-class fallback | W4 |
    | E3 | LID accuracy at 2 / 3 / 5 s | MMS-LID-256 (amh/sid/eng + other) on FLEURS, Afrivoice and pilot teacher segments. | LID threshold (audit 5d) | W3 |
    | E4 | Can choral vs single child speech be separated? | Synthetic choral mixtures from Afrivoice speakers + pitch/formant scaling; CountNet-style head; validate on real clips. | 5-class vs 4-class | W5 |
    | E5 | Student architecture under Lite ops | CRNN (FP16/FP32 GRU) vs convolution-only TCN (INT8) vs tiny conformer. Measure F1, MB and RTF. | ADR-002 | W4 |
    | E6 | Distillation gap | KD from the D-1 teacher to the E5 winner. | §14.1 | W8 |
    | E7 | Error propagation | Simulate confusion matrices on golden timelines and compute indicator error (Gautheron et al., 2025). | Final §14 targets | W3 |
    | E8 | Dataset register | Licence, gating, register, overlap between WAXAL and Afrivoice. | `ml/data/DATASETS.md` | W1 |
    | E9 | Competition "dataset selection and processing" section | Document D-3 to D-6 plus the synthetic-lesson generator as the Template 1 dataset story. | Preliminary submission | W6 |
    
    ### WS-E: Stack and device spikes (Sys + ML)
    
    | Spike | Pass criterion | Due |
    |---|---|---|
    | S1 | `.ms` INT8 hello-world through the 2.10.0 AAR on a 2 GB phone | W0 |
    | S2 | RTF ≤ 0.3 and RSS ≤ 300 MB via the bundled `benchmark` tool and the in-app harness | W3 |
    | S3 | PyTorch→ONNX→`.ms` and MindSpore→MindIR→`.ms` outputs agree within 1e-4; full-quant PTQ runs; list the ops that fall back to FP32 | W3 |
    | S4 | MindSpore 2.10 trains a toy model on the in-country hardware | W1 |
    | S5 | ModelArts, OBS and ECS all in **AF-Johannesburg**; flavours and prices recorded; OBS unencrypted and holding **public data only** | W1 |
    | S6 | openEuler LTS + openGauss + psycopg connect (SASL/sha256 workaround); **host chosen from [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md)** — Zergaw VPS/DBaaS, INSA G-Cloud or Ethio Telecom teleCloud, not the on-campus box, unless the university prefers self-hosting | W1 |
    | S7 | Microphone foreground service; `AudioRecordingCallback` detects call-silencing; 40-minute battery test | W2 |
    | S8 | 16 KB decision: sideload for the pilot; rebuild Lite before any Play release | W6 |
    | S9 | SQLCipher + Keystore key | W2 |
    | **S10** (new) | **Competition cloud stack:** ECS + RDS for PostgreSQL (or GaussDB) + OBS in AF-Johannesburg serving the demo page and lesson simulator with synthetic lessons. Redeployable from a script. | W5 |
    
    ### WS-F: Legal and ethics (Lead)
    
    Primary text: Proclamation 1321/2024. Get the official Gazette copy (TO_OBTAIN #2).
    
    Questions for the legal advisor, answered in writing by W3:
    
    1. Is the university the data controller, and does Art. 33 registration apply to student research?
    2. Is transient in-RAM processing of children's voices processing of a minor's data (Arts. 2(16), 11)? What consent does it need?
    3. Research Mode child audio: Art. 11 parental consent, or the Art. 54 research exemption?
    4. Is raw voice audio without speaker identification *biometric* under Art. 2(6)?
    5. Are teacher-linked indicators personal data, and does in-country storage satisfy Art. 22(1)?
    6. Are a DPIA (Art. 47) and a DPO (Art. 40) needed?
    7. Could the weekly report be read as an automated decision about the teacher (Art. 31)?
    8. **New:** does a competition demo on Huawei Cloud (AF-Johannesburg) holding only synthetic lessons, plus scripted demo accounts, fall entirely outside the Proclamation? Get this in writing.
    
    Ethics: the Hawassa IRB (the critical path); assent and consent forms in Amharic, Sidaamu Afoo and English; school and woreda letters.
    
    ### WS-G: Competition (Lead)
    
    Northern Africa division, 2026–27 (see audit §7):
    
    | Item | Requirement | Plan |
    |---|---|---|
    | Topic | AI application powered by Huawei Cloud | Dimts cloud tier: indicator analytics, teacher report generation, district dashboard and a lesson simulator, all on Huawei Cloud with synthetic data |
    | Huawei Cloud service | ≥ 1 service (ECS, RDS, DCS …) | ECS + RDS/GaussDB + OBS (+ ModelArts for training on public data) |
    | Visual demo page | Mandatory | `dashboard/` + `tools/lesson-simulator` web page |
    | Hardware | Optional, team-provided | Live phone demo as a bonus |
    | Prelim/National scoring | Innovation 60 / Application value 40 | Lead with the no-ASR, language-agnostic, on-device design plus the I-6 novelty. Back application value with EGRA and MTTCA numbers. |
    | Regional reproducibility | Code, datasets and README re-run by the committee | Synthetic dataset generator and scripts in the repo; the pilot data is never needed to reproduce the demo |
    | Open source | GitHub or Gitee for Regional and Global shortlists | Keep `docs/research/papers/` out of the public repository |
    
    ---
    
    ## 4. Decisions that need the team
    
    | ID | Decision | Recommendation |
    |---|---|---|
    | D-1 | Teacher-model framework (MindNLP is gone) | PyTorch/HF teacher on the in-country node, MMS-LID-256 as the LID teacher, and the student trained in MindSpore. Reword the pitch to "every model on the phone is trained in MindSpore and runs on MindSpore Lite". |
    | D-2 | Student architecture | Decide on E5 data; default to a convolution-only TCN if RTF is tight. |
    | D-3 | Distribution | Sideload for the pilot; handle 16 KB before any Play release. |
    | D-4 | O-class in scope for coaching? | Wait for B3. |
    | D-5 | CC-BY-NC models (MMS) | Research and competition only; state it in the model cards. |
    | **D-6** (new) | Competition cloud tier vs data sovereignty | Two deployments from one codebase: `infra/huawei-cloud` (demo, synthetic data, AF-Johannesburg) and `infra/openeuler` (pilot, personal data, Hawassa). There is no data path from pilot to cloud (ASSUMPTIONS A-C3). |
    | **D-7** (new) | Microphone | Decide from E1. If desk SNR is poor, ship a wired lavalier as part of the kit. |
    
    ---
    
    ## 5. Schedule (architecture §16 merged with the competition calendar)
    
    The exact Preliminary deadline within Nov–Dec 2026 is not yet known. The plan assumes **1 Dec 2026** until the templates or portal say otherwise (ASSUMPTIONS A-C1).
    
    | Week | Dates | Research and build | Competition |
    |---|---|---|---|
    | W0 | Sep 25 – Oct 4 | E1 test lessons; S1, S4, S6; legal questions sent; ethics submitted; A3/A4 visits begin | Register team; download templates; confirm division with Huawei Ethiopia |
    | W1–2 | Oct 5 – 18 | A4, A6; B1, B6; C1; E8; S5, S7, S9; ADR-001/004/005 | Apply for Huawei Cloud vouchers |
    | W3–4 | Oct 19 – Nov 1 | A3 memo; B3, B5; E2, E3, E5, E7; S2, S3; legal answers; ADR-002/003 | Draft Template 1 |
    | W5–6 | Nov 2 – 15 | B2 panel; E4; **S10 cloud demo live**; S8 decision; E9 | Record demo video |
    | W7–8 | Nov 16 – 29 | E6 distillation; indicator validity v1 (§14.2) on pilot data | **Preliminary submission (assumed by 1 Dec)** |
    | W9–10 | Nov 30 – Dec 13 | Cross-language evaluation (§14.3); feature freeze | Preliminary review |
    | W11–16 | Dec 14 – Jan 31 | Pilot continues; literature review chapter from the search log | **National final (Jan 2027)** |
    | W17–24 | Feb – Mar 2027 | Open-source cleanup; reproducibility check by a non-team member | **Regional final (Feb/Mar 2027)** |
    
    ---
    
    ## 6. Open questions only the team can answer
    
    1. The training node's hardware (S4, D-1).
    2. The pilot teachers' phones (ASSUMPTIONS A-T1).
    3. Hawassa IRB turnaround.
    4. Play Store: ever?
    5. Who is the data controller?
    6. **New:** will the Amharic-medium pilot schools be in Hawassa or Sidama rather than Amhara region? ESAA 2024/25 excludes Amhara because schooling is disrupted there (ASSUMPTIONS A-P3).
    7. **New:** does the team accept making the product code open source (a Regional requirement)?
    
    ---
    
    ## 7. Search protocol (C1 and literature review)
    
    **Databases:** ERIC, ACL Anthology, ISCA Archive, IEEE Xplore, ACM DL (LAK, L@S, CHI, UMAP), EDM/JEDM, arXiv (cs.SD, eess.AS, cs.CL), Google Scholar, plus Scopus or Web of Science via the library.
    
    **Query blocks, combined with AND:**
    
    1. `classroom OR lesson OR teacher OR "early grade" OR preschool OR kindergarten`
    2. `audio OR acoustic OR "speech activity" OR diarization OR "voice type" OR "language identification" OR "code-switching"`
    3. `feedback OR observation OR "teacher talk" OR "wait time" OR choral OR "student talk" OR "language of instruction"`
    4. Optional LMIC filter: `Africa OR Ethiopia OR Kenya OR "low-income" OR "developing countries" OR India OR Pakistan`
    
    **Inclusion criteria:** empirical, automated or audio-based, classroom setting, 2010–2026, plus backward and forward snowballing from DART, VTC, M-Powering Teachers and Schlotterbeck et al. (2021).
    
    **Record for each paper:** setting, grade, language(s), microphone, input, classes, metric, result, and whether ASR was used.
    
    ---
    
    ## 8. Reading lists
    
    ★ marks the core list to read first. Full references are in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).
    
    **Ethiopian context**
    ★ AIR (2019, EGRA 2018 endline) · ★ AIR (2020, MTTCA) · ★ MoE (2025, ESAA 2024/25) · ★ Heugh et al. (2007) · Vujcich (2013) · Alemu (2025) · Kim (2020) · Wendie & Berhanu (2025) · Gobana (2025) · Yisihak & Damtew (2024) · Haile & Mendisu (2023) · Piper et al. (2016)
    
    **Pedagogy and indicator validity**
    ★ Rowe (1986) · ★ Tobin (1987) · ★ Howe et al. (2019) · ★ Liu & Cohen (2021) · ★ Pontefract & Hardman (2005) · ★ Heward et al. (1989) · ★ Kraft et al. (2018) · Hardman et al. (2009) · Alexander (2015) · Kelly et al. (2018) · Hattie & Timperley (2007) · Popova et al. (2021) · Popova & Evans (2016) · Glewwe & Muralidharan (2016) · Piper & Zuilkowski (2015) · Piper et al. (2018) · Cilliers et al. (2018) · Molina et al. (2018) · Stallings et al. (2014) · La Paro & Pianta (2003) · ★ Birnhack & Perry-Hazan (2021) · Shield & Dockrell (2003)
    
    **Prior systems**
    ★ Owens et al. (2017) · ★ Schlotterbeck et al. (2021) · ★ Demszky & Liu (2023) · ★ Jensen et al. (2020) · ★ Donnelly et al. (2016) · Demszky et al. (2023, 2025) · Jacobs et al. (2022) · Suresh et al. (2022) · Demszky & Hill (2023) · Wang & Demszky (2023) · Chanchal & Zualkernan (ED626900) · Zualkernan & Khan (2020) · Shapsough & Zualkernan (2017) · Shapsough et al. (2016) · Cosbey et al. (2019) · Li et al. (2020) · Wang et al. (2013, 2014) · Blanchard et al. (2016)
    
    **Speech and ML**
    ★ Wang et al. (2025) · ★ Lavechin et al. (2020) · ★ Gautheron et al. (2025) · ★ Pratap et al. (2023) · Diack et al. (2026) · Conneau et al. (2022) · Valk & Alumäe (2020) · Babu et al. (2021) · Baevski et al. (2020) · Hsu et al. (2021) · Radford et al. (2022) · Plaquet & Bredin (2023) · Bredin (2023) · Stöter et al. (2018) · Hinton et al. (2015) · Chang et al. (2022) · Schmid et al. (2023) · Jacob et al. (2018) · Snyder et al. (2015, 2018) · Desplanques et al. (2020) · Ko et al. (2017) · Park et al. (2019) · Potamianos & Narayanan (2003) · Lee et al. (1999) · Abate et al. (2005) · Gauthier et al. (2016) · Ganek & Eriks-Brophy (2018)
    
    **Infrastructure documentation**
    ★ MindSpore Lite 2.10.0 (release notes, downloads, quantization, operator list) · ★ Huawei Cloud ModelArts and Cloud Connect overviews · ★ 2026–27 Innovation Competition page · MindSpore 2.10.0 release notes · Android FGS, audio sharing, 16 KB pages, Jetpack Security notes · SQLCipher · RFC 9562 · openGauss psycopg2 · Label Studio
    
    **Legal and ethics**
    ★ Proclamation 1321/2024 · Sweeney (2002) · Hawassa IRB guidance (to obtain)
    ✓ • 0ms

## Activity

    $ cat edge/android/settings.gradle.kts edge/android/app/build.gradle.kts edge/android/gradle/libs.versions.toml edge/android/app/src/main/AndroidManifest.xml edge/android/app/src/main/java/org/ethioware/echo/MainActivity.kt; cat docs/research/CLAIMS_AUDIT.md
    pluginManagement {
        repositories {
            google {
                content {
                    includeGroupByRegex("com\\.android.*")
                    includeGroupByRegex("com\\.google.*")
                    includeGroupByRegex("androidx.*")
                }
            }
            mavenCentral()
            gradlePluginPortal()
        }
    }
    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    }
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
        }
    }
    
    rootProject.name = "Echo"
    include(":app")
    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.kotlin.compose)
    }
    
    android {
        namespace = "org.ethioware.echo"
        compileSdk {
            version = release(37)
        }
    
        defaultConfig {
                ndk { abiFilters += "arm64-v8a" }
        }
    
        defaultConfig {
            applicationId = "org.ethioware.echo"
            minSdk = 29
            targetSdk = 37
            versionCode = 1
            versionName = "1.0"
    
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    
        buildTypes {
            release {
                optimization {
                    enable = false
                }
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        buildFeatures {
            compose = true
        }
    }
    
    dependencies {
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.compose.material3)
        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.ui.graphics)
        implementation(libs.androidx.compose.ui.tooling.preview)
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        testImplementation(libs.junit)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.compose.ui.test.junit4)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(libs.androidx.junit)
        debugImplementation(libs.androidx.compose.ui.test.manifest)
        debugImplementation(libs.androidx.compose.ui.tooling)
    }[versions]
    agp = "9.4.1"
    coreKtx = "1.10.1"
    junit = "4.13.2"
    junitVersion = "1.1.5"
    espressoCore = "3.5.1"
    lifecycleRuntimeKtx = "2.6.1"
    activityCompose = "1.8.0"
    kotlin = "2.2.10"
    composeBom = "2026.02.01"
    
    [libraries]
    androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
    junit = { group = "junit", name = "junit", version.ref = "junit" }
    androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
    androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
    androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
    androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
    androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
    androidx-compose-ui = { group = "androidx.compose.ui", name = "ui" }
    androidx-compose-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
    androidx-compose-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
    androidx-compose-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
    androidx-compose-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
    androidx-compose-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
    androidx-compose-material3 = { group = "androidx.compose.material3", name = "material3" }
    
    [plugins]
    android-application = { id = "com.android.application", version.ref = "agp" }
    kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
    
    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools">
    
        <application
            android:allowBackup="true"
            android:dataExtractionRules="@xml/data_extraction_rules"
            android:fullBackupContent="@xml/backup_rules"
            android:icon="@mipmap/ic_launcher"
            android:label="@string/app_name"
            android:roundIcon="@mipmap/ic_launcher_round"
            android:supportsRtl="true"
            android:theme="@style/Theme.Echo">
            <activity
                android:name=".MainActivity"
                android:exported="true"
                android:label="@string/app_name"
                android:theme="@style/Theme.Echo"
                android:windowSoftInputMode="adjustResize">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />
    
                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity>
        </application>
    
    </manifest>package org.ethioware.echo
    
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.tooling.preview.Preview
    import org.ethioware.echo.ui.theme.EchoTheme
    
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                EchoTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
    
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
    
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        EchoTheme {
            Greeting("Android")
        }
    }# Claims audit — DIMTS_ARCHITECTURE_CONTEXT.md
    
    First checked 2026-09-25. Updated 2026-09-26 with the full texts supplied in [`papers/`](papers/). Verdict labels are defined in [`README.md`](README.md). Citations are (Author, year) and resolve in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md). Section numbers (§) refer to the architecture document. Page numbers marked "PDF p." count PDF pages rather than printed page numbers.
    
    ## 0. Findings that change the design
    
    These are ordered by how much they change the plan. Details are in the section tables below.
    
    1. **The 2026–27 Innovation Competition requires deployment on Huawei Cloud (§7, §17).** New, VERIFIED from the Huawei Talent page for the Northern Africa division (supplied screenshots).
       - The topic is "Creating Innovative AI Applications Powered by Huawei Cloud". Entries must "be deployed on Huawei Cloud and use at least one Huawei Cloud service while complying with local laws and regulations", and must be shown on "a visual demo page".
       - Scoring differs from what §17 assumes:
         - Preliminary and National stages: **Innovation 60% / Application value 40%**.
         - Regional stage: Innovation 40 / Application value 35 / Completeness & demonstrability 15 / Presentation 10.
         - At Regional, the committee **re-runs the submitted code and datasets**, and a mismatch disqualifies the entry.
         - Regional- and Global-shortlisted entries must be **open source** (GitHub or Gitee).
       - This is in tension with C-7 (personal data stays in Ethiopia; Huawei Cloud has no Ethiopian region). The design needs a **cloud demo tier that holds only synthetic or public data**, separate from the in-country pilot tier. See ASSUMPTIONS A-C3 and CODEBASE_STRUCTURE.
    2. **MindNLP is no longer a viable dependency (§7.2, §9.4).** CONTRADICTED.
       - The GitHub repository (`mindlab-ai/mindnlp`, formerly `mindspore-lab/mindnlp`) now hosts **MindAct**, a PyTorch/Hugging Face embodied-AI toolkit. Its README says the MindNLP code "is preserved in the `legacy` branch".
       - The `legacy` branch's last commit is 2026-03-08, and the last PyPI release is 0.5.1 (2025-11-05).
    3. **Sidaamu Afoo speech technology and data exist (§2.6, §9.2 D-4).** CONTRADICTED.
       - MMS has a Sidamo ASR adapter, a Sidamo TTS voice, and `sid`/`amh`/`eng` in the MMS-LID-256 and MMS-LID-4017 label sets (CC-BY-NC-4.0).
       - *Afrivoice Ethiopia* (CC-BY-4.0) has about 603 h of Sidama speech (225 h transcribed). WAXAL has `sid_asr`.
       - The no-ASR rule still holds, but on privacy, child-speech, noise and on-device grounds.
    4. **MindSpore Lite has no INT8 kernels for GRU or LSTM (§6 C-2, §9.4, ADR-002).** PARTLY. A CRNN student will not be fully INT8.
    5. **The MindSpore Lite 2.10.0 Android AAR is 4 KB page-aligned (§7.2).** New. This blocks Google Play updates targeting API 35+ from 2027-02-01. It doesn't matter for sideloaded pilot phones.
    6. **The legal duties are broader than the document lists (§6 C-7, §11).** From the Gazette text:
       - registration with the Ethiopian Communications Authority (Art. 33);
       - parent or guardian consent for minors' data (Art. 11);
       - breach notice to data subjects within 72 h (Art. 44);
       - a DPIA (Art. 47), a DPO (Art. 40), and a conditional research exemption (Art. 54).
    7. **"Teachers get no feedback" and "teacher capability is the binding constraint" both overstate the evidence (§2.2).** PARTLY.
       - MTTCA: 39% of mother-tongue teachers are Proficient-or-above, but home resources predict reading more strongly than teacher competency (AIR, 2020).
       - EGRA 2018 shows classroom observation **does exist in some schools**. Having someone who observes mother-tongue classes is **positively associated with reading** (d = 0.23–0.28), as is observing them three times per semester vs never (d = 0.18) (AIR, 2019, pp. 53–54).
       - The accurate framing: observation is uneven, infrequent and not standardised, and where it happens it is associated with better reading. That supports Dimts better than "no feedback loop".
    8. **The EGRA flat-line figures are confirmed, with one typo (§2.1).** VERIFIED.
       - The EGRA 2018 endline report gives **31.3% (2014), 34.3% (2016), 32.4% (2018)** for the top two benchmark levels combined, Grades 2–3, all languages (AIR, 2019, p. ix).
       - The architecture document's "34.2%" comes from Haile and Mendisu (2023), who mis-copied it.
    9. **The choral-response indicator has a contested normative direction (§5 I-3).**
       - It is an evidence-based active-response technique (Heward et al., 1989) and part of READ's "we do" structured pedagogy (AIR, 2020).
       - It is also a marker of rote teaching (Pontefract & Hardman, 2005; Hardman et al., 2009).
    10. **Classroom language has never been measured systematically. That strengthens the I-6 novelty claim (§2.4).** VERIFIED from full texts.
        - Heugh et al. (2007) used about 100 classroom observations recorded as handwritten notes. Their checklist was "used in the first half of the fieldwork, [but] not … in the second".
        - Piper et al. (2016) measured school language use through **children's self-reports**, not observation.
    11. **Separating teacher from child speech is hard, and desk-placed microphones are the weak link.**
        - The best system in Wang et al. (2025) reached 69% teacher-vs-child accuracy.
        - Jensen et al. (2020) chose a $230 wireless headset because room microphones "produced noisy audio".
        - Schlotterbeck et al. (2021) used low-cost lavalier mics plugged into teachers' smartphones.
        - Plan a **clip-on microphone** arm in experiment E1, not just desk placement.
    
    ---
    
    ## 1. §2 Problem and evidence
    
    | # | Claim in document | Verdict | Evidence | What to change |
    |---|---|---|---|---|
    | 2.1a | EGRA upper-benchmark performance was 31.3% (2014), 34.2% (2016), 32.4% (2018). | **VERIFIED** with correction | EGRA 2018 Endline (AIR, 2019, p. ix): "upper two benchmark levels was 31.3% in 2014 … increased slightly to 34.3% in 2016 and then slipped back to 32.4% in 2018, with the differences being too small to be considered practically significant." The benchmark is Levels 1 + 2 ("full" + "increasing" proficiency), with language-specific ORF cut scores from a 2015 MoE/USAID workshop. Grades 2–3, 7 languages. Caveats (footnotes 2–3): Aff Somali 2014 and Wolayttatto 2016 are excluded from aggregates, and "2016 data for Sidamu Affo may not be reliable". | Change 34.2 → **34.3**. Cite AIR (2019), not Haile & Mendisu. Add: only **6.2%** reach the top level (Amharic 11.5% → Tigrigna 2.0%). The top-two share is 50.0% for Amharic and 16.0% for Haddiysa (p. viii). |
    | 2.1b | ~40% of Grade 2–3 students read at 20–25 wpm (USAID 2018). | **VERIFIED** | USAID fact sheet (Oct 2020, ERIC ED628406). | Pair it with AIR (2019). |
    | 2.1c | 0.5%–13% of students could read with comprehension. | **VERIFIED**, but dated | RTI EdData II brochure (programme 2004–2013). | Say it predates the 2014–2018 rounds. |
    | 2.1d | Ethiopia has been measured repeatedly; another child assessment isn't the missing piece. | **PARTLY** | Supported by EGRA 2010/2014/2016/2018 and the MTTCA. Home literacy resources are the strongest predictor (AIR, 2020). | Keep the argument; acknowledge factors outside the classroom. |
    | 2.2a | Early-grade English teachers averaged 43.4%; 70% scored below 50%. | **VERIFIED**, narrow | Yisihak and Damtew (2024), full text: n = 30 English teachers, 5 schools. The authors' own limitation: "conducted in one of big cities in Ethiopia … generalizability … is limited". | Present it as a case study. Lead with MTTCA (39% Proficient-or-above; Sidaamu Afoo in the 22–33% band). |
    | 2.2b | Teacher capability is the binding constraint. | **PARTLY** | MTTCA multivariate ranking: home resources, then school resources, then teacher competency (AIR, 2020, pp. 65–66). | Say "a significant and addressable constraint". |
    | 2.2c | Preservice programs devote only a few sessions to early reading. | **VERIFIED** for one sample | Yisihak and Damtew (2024). MTTCA expert notes: "no adequate subject matter pedagogy courses in the CTEs in Sidama region" (AIR, 2020, p. 29). | Scope it to those sources. |
    | 2.2d | Teachers receive no feedback loop on actual classroom practice. | **PARTLY** (overstated) | Some Ethiopian schools have a person who observes mother-tongue classes. Where present, observation (d = 0.23–0.28) and frequency (3×/semester vs never, d = 0.18) are positively associated with reading. But director "monitoring by classroom observation" is negatively associated (d = −0.10). All correlational (AIR, 2019, pp. 53–54). AIR recommends observing every mother-tongue teacher at least three times per semester (p. 66). In the US, feedback is "infrequent … often more focused on evaluating performance than on improving practice" (Jensen et al., 2020). | Reframe: observation is uneven, infrequent and unstandardised. Dimts supplies frequent, non-evaluative, teacher-owned feedback. |
    | 2.3a | Pre-primary GER 59.8% (2024/25); KG schools +22%; Addis Ababa 145.9%, Somali 17.7%, Afar 26.8%. | **VERIFIED** | ESAA 2024/25 (MoE, 2025): national GER 59.8%, +2 pp, GPI 0.95 (PDF p. 14). Table 2.1: Afar 26.8, Somali 17.7, Harari 102.9, Addis Ababa 145.9. KG schools 18,209 (72% government) vs 14,909 (PDF p. 17). **Indicators exclude Amhara** because "teaching-learning is not fully implemented in most parts of Amhara region for the last two consecutive years"; Tigray is re-included (PDF p. 10). | Cite ESAA directly. State that national figures exclude Amhara. |
    | 2.3b | O-classes and child-to-child modalities serve ~75% of pre-primary children. | **VERIFIED** (via citation), dated | Alemu (2025), full text: "According to MoE (2021), 75% of children enrolled in pre-primary education in the 2021/2022 academic year were attending O-classes and child-to-child programs. Only 25% … kindergartens." | Say "in 2021/22 (ESAA)". Look for a newer split in ESAA 2024/25. |
    | 2.3c | A 2025 mixed-methods study found systemic deficits nationwide. | **PARTLY** | Alemu (2025), full text: rural Amhara and Oromia only. n = 380 valid responses (86.4% response rate). The plain-language summary says 541, which contradicts the methods section. | Say "in rural Amhara and Oromia". |
    | 2.4a | Policy mandates primary instruction in nationality languages. | **PARTLY** | Gobana (2025) says the model is anchored in the 1994 and 2023 Education and Training Policies. Heugh et al. (2007) describe the SNNPR model at the time: mother tongues including Sidama as MOI in the first cycle. The policy texts themselves weren't opened. | Cite the 2023 ETP text. Confirm the current grade span for Sidaamu Afoo MOI. |
    | 2.4b | Shortages of trained L1 teachers and materials; teachers not proficient in the language. | **VERIFIED** (Ethiopian source) | Heugh et al. (2007, PDF p. 38): "Many teachers do not have adequate academic literacy skills or proficiency in the language of learning and teaching". SNNPR/Sidama rural schools had "severe shortages of books" and "large class size" (PDF p. 74). MTTCA regional deficits (AIR, 2020). Gobana (2025) is conceptual and cites Trudell (2016). | Cite Heugh et al. and AIR (2020); treat Gobana as secondary. |
    | 2.4c | No one measures, at scale, which language is actually spoken in the classroom. | **VERIFIED** for systematic/automated measurement | Heugh et al. (2007, PDF pp. 5, 16): about 100 observations, note-taking, occasional audio/video; the checklist was abandoned mid-fieldwork. Piper et al. (2016, PDF p. 9): school language use from children's self-report (Kiswahili 79–87%, English 21–26%, mother tongue ~29%). Vujcich (2013): survey. | Claim novelty for **systematic, automated, continuous** measurement. Still run the RQ-C1 search before saying "first". |
    | 2.5a | Acoustic indicators are well-established correlates of instructional quality. | **PARTLY** | **Wait time:** established (Rowe, 1986; Tobin, 1987), US. **Talk ratio:** a teacher-centred factor negatively predicts value-added (Liu & Cohen, 2021). An RCT showed that talk-time feedback **cut mentors' talk share by 5 pp, to 69%** (Demszky & Liu, 2023), using the same definition as I-1. **Choral:** contested. No Ethiopian validation. | Separate "established" from "validated in Ethiopia". |
    | 2.5b | Extractable without transcription. | **VERIFIED** | DART ~90% (Owens et al., 2017). Pakistan audio Stallings F1 0.83 (Chanchal & Zualkernan). Chile, smartphone + lavalier, >80% accuracy for the two most common practices (Schlotterbeck et al., 2021). VTC (Lavechin et al., 2020). | Cite as precedents. |
    | 2.5c | Trained observers don't exist at scale in Ethiopia. | **UNVERIFIED** | EGRA 2018 shows some schools have an assigned observer (director, unit leader or department head), but not how many. | RQ-A3; get the Table 16 base rates from AIR's data or report annex. |
    | 2.5d | Recording an adult teacher is ethically much lighter than collecting child speech. | **PARTLY** | Children's voices are still captured (Proclamation 1321/2024, Art. 11). | Treat incidental child audio as minors' data unless the legal advisor rules otherwise. |
    | 2.6a | Amharic ASR is weak. | **UNVERIFIED** as stated | ASR resources exist (MMS `adapter.amh`, ALFFA, FLEURS, Afrivoice). | Don't use it as the argument. |
    | 2.6b | Sidaamu Afoo ASR effectively doesn't exist. | **CONTRADICTED** | See finding 3. | Rewrite §2.6. |
    
    ## 2. §5 Indicator specification
    
    | # | Item | Verdict | Evidence | What to change |
    |---|---|---|---|---|
    | 5a | I-2 response latency as a wait-time proxy. | **PARTLY** | Wait time I follows a question (Rowe, 1986). I-2 also mixes in instructions and choral drills. | Report I-2 separately for choral and single responses. Don't compare with the 3-s threshold until R-4 exists. |
    | 5b | I-3 choral ratio. | **PARTLY** | See finding 9. | Frame it as a balance indicator. |
    | 5c | I-6 language share is novel. | **VERIFIED** as automated | See 2.4c. | — |
    | 5d | Language-label thresholds. | **Internal inconsistency** | ≥ 2.0 s (§5) vs 3-s windows (§8.3) vs ≥ 3 s evaluation (§14.1). | Decide after E3. |
    | 5e | Error propagation. | **Not addressed** | Gautheron et al. (2025). | Experiment E7. |
    | 5f | I-1 talk ratio definition. | **Consistent with literature** | Demszky and Liu (2023) define talk time as teacher / (teacher + student), the same as I-1. | Cite it. |
    
    ## 3. §6 Hard constraints
    
    | # | Claim | Verdict | Evidence | What to change |
    |---|---|---|---|---|
    | C-2a | Target device is Android 10+. | **SECONDARY** (reasonable) | StatCounter, Aug 2026: Android 94.0%. Versions 14 (20.2%), 16, 13, 15, 12, 11. | Teacher phone survey. |
    | C-2b | Tecno, Itel, Samsung. | **PARTLY** (SECONDARY) | Samsung 48.1%, Tecno 11.6%, Infinix 6.2%, Apple 5.9%, Itel 2.2%. | Add Infinix. |
    | C-2c | 2 GB RAM, A53/A55, no NPU. | **UNVERIFIED** | — | Teacher phone survey. |
    | C-2d | All inference CPU INT8. | **PARTLY** | GRU/LSTM FP16/FP32 only in Lite r2.10.0. | Finding 4. |
    | C-3 | RTF ≤ 0.3. | **UNVERIFIED** | The package ships an on-device benchmark tool. For scale, Schlotterbeck et al. (2021) report ~7 min per 1 h of audio (RTF ≈ 0.12, their hardware). | Spike S2. |
    | C-5 | Classrooms of 40–80 children. | **PARTLY VERIFIED** (averages) | ESAA 2024/25 pupil-section ratio, Grades 1–6: national 54.2 (standard 50), **Sidama 59.6**, Oromia 60.0, Addis Ababa 37.1 (PDF p. 28). Averages hide the upper tail. | Report the range observed in pilot classrooms. |
    | C-7a | Consent is required. | **VERIFIED** + additions | Arts. 7, 8 and 11. | Finding 6. |
    | C-7b | Personal data stored in Ethiopia. | **VERIFIED** | Art. 22(1); Art. 22(3). | Keep ADR-003. The competition cloud demo must hold no personal data. |
    | C-7c | 72-hour breach notice. | **VERIFIED** + addition | Arts. 43, 44. | Update the runbook. |
    | C-7d | Minors under 16. | **VERIFIED** | Art. 2(15); Art. 11(4). | — |
    | C-7e | In force 24 Jul 2024. | **VERIFIED** | Art. 70; Negarit Gazette 30th Year No. 35. | — |
    | C-7f | *Not in document:* registration, DPO, DPIA, research exemption. | **VERIFIED** gaps | Arts. 33, 40, 47, 54. | Add to §11. |
    | C-8 | No voice embeddings persisted. | Consistent | Art. 2(6). | Legal confirmation. |
    | C-11 | Huawei stack is a "preference". | **CONTRADICTED** for the competition | 2026–27 rules make Huawei Cloud deployment mandatory (finding 1). | Upgrade to a hard requirement for the competition build only. |
    
    ## 4. §7 Huawei technology mapping
    
    | # | Claim | Verdict | Evidence | What to change |
    |---|---|---|---|---|
    | 7a | MindSpore Lite AAR, 2.10.0 current. | **VERIFIED** | Tag, downloads page and AAR inspected. | Pin 2.10.0. |
    | 7b | Post-training INT8. | **VERIFIED** | Quantization docs; no QAT in Lite. | — |
    | 7c | Converts MindIR, ONNX, TFLite. | **VERIFIED** | Lite README. | — |
    | 7d | Built into HarmonyOS. | **VERIFIED** (vendor) | Downloads page. | — |
    | 7e | *New:* removed features in 2.8–2.10. | **VERIFIED** | Release notes. | No Lite-side model encryption. |
    | 7f | MindSpore 2.x training. | **PARTLY** | 2.10.0 is Ascend-focused; GPU status not stated. | Spike S4. |
    | 7g | MindAudio 0.1.x. | **CONTRADICTED** (detail) | 0.3.0 (2024-01); unmaintained. | Own feature code. |
    | 7h | MindNLP loads SSL encoders. | **CONTRADICTED** as viable | Finding 2. | Decision D-1. |
    | 7i | ModelArts nearest regions Johannesburg, Cairo, Lagos. | **PARTLY** | Regions FAQ lists AF-Cairo and AF-Johannesburg; no Lagos. Cloud Connect overview (Huawei Cloud, 2026a): "If your target users are in Africa, select the AF-Johannesburg region." ModelArts overview (Huawei Cloud, 2026b): "ModelArts does not support encrypted OBS buckets" and "does not support cross-region access to OBS buckets". Per-region ModelArts or Ascend availability still unconfirmed. | Use AF-Johannesburg. Keep ModelArts and OBS in the same region. Never place personal data in unencrypted OBS. |
    | 7j | openGauss drivers. | **PARTLY** | psycopg2 SASL/sha256 issue. | W0 spike. |
    | 7k | openEuler LTS. | **UNVERIFIED** | — | Spike S6. |
    | 7l | HarmonyOS NEXT / Android. | Half SECONDARY | — | Cite a Huawei primary source. |
    | 7m | "Every model is trained in MindSpore". | **At risk** | D-1. | Reword to the on-device model. |
    | 7n | "Personal data stays on Ethiopian soil". | **VERIFIED** | Art. 22(1). | Pitch the cloud demo as "synthetic data only". |
    | 7o | *New:* competition needs a Huawei Cloud service. | **VERIFIED** | Finding 1. ECS, RDS and DCS are named as examples. | Deploy the demo stack on ECS + RDS (or GaussDB) in AF-Johannesburg. |
    
    ## 5. §8–§9 System and ML design
    
    | # | Claim | Verdict | Evidence | What to change |
    |---|---|---|---|---|
    | 8a | Foreground service, wake lock. | **VERIFIED** | FGS type `microphone`; can't start from background. | Start only from the visible UI. |
    | 8b | *Not in document:* silent interruption. | **VERIFIED** risk | Android 10+ audio sharing: the capturing app "receives silence". | `AudioRecordingCallback`; mark spans as missing. |
    | 8c | "SQLCipher or Jetpack Security". | **PARTLY** | security-crypto deprecated. | SQLCipher + Keystore. |
    | 8d | UUIDv7. | **VERIFIED** | RFC 9562. | — |
    | 8e | Models ≤ 8 MB. | Plausible | pyannote seg-3.0 5.9 MB FP32. | — |
    | 8f | Frame classification (ADR-001). | **Supported** | Lavechin et al. (2020); Wang et al. (2025); Donnelly et al. (2016): five segment types, F1 0.64–0.78, teacher-independent validation. | Cite in ADR-001. |
    | 8g | *New:* microphone placement. | **Evidence against desk-only** | Jensen et al. (2020): room mics "produced noisy audio"; they used a headset; 127/142 (89%) recordings usable. Schlotterbeck et al. (2021): lavalier + smartphone. | E1 compares desk vs a wired lavalier (confirm local price and availability). |
    | 9a | ALFFA, FLEURS Amharic. | **VERIFIED** | — | — |
    | 9b | Sidama speech scarce. | **CONTRADICTED** | Afrivoice, WAXAL. | Use for LID and D-6. |
    | 9c | MUSAN, RIR. | **VERIFIED** | — | Check MUSAN subsets. |
    | 9d | *New:* MMS-LID. | Option | CC-BY-NC-4.0. | Research use only. |
    | 9e | Label Studio. | **VERIFIED** | Apache-2.0. | — |
    | 10a | *New:* sync transport. | Context | A MQTT-based mobile assessment pipeline in an LMIC EGRA context showed low power use (Shapsough et al., 2016). | HTTPS batch sync stays the default; MQTT isn't needed for weekly reports. |
    
    ## 6. §14 Evaluation targets
    
    | # | Target | Verdict | Evidence |
    |---|---|---|---|
    | 14a | T-1 macro-F1 ≥ 0.70; CHILD_SINGLE vs CHILD_CHORAL F1 ≥ 0.75. | **Ambitious** | Wang et al. (2025): teacher vs child 69%. Donnelly et al. (2016): F1 0.64–0.78 for 5 segment types, with ASR. Schlotterbeck et al. (2021): >80% on 2 practices. Owens et al. (2017): ~90% on 3 coarse classes. No choral benchmark exists. |
    | 14b | Indicator MAE targets. | Not derivable | Set after E7. |
    | 14c | *New:* recording usability. | Benchmark | 89% usable in Jensen et al. (2020). Add "≥ 85% of pilot lessons usable" as a system metric. |
    
    ## 7. §17 Competition
    
    | # | Claim | Verdict | Evidence |
    |---|---|---|---|
    | 17a | Criteria: creativity, system complexity, technical complexity, societal benefit, functionality. | **OUTDATED** | That was the 2023–24 wording (Huawei, 2025). For 2026–27: Prelim/National = Innovation 60 + Application value 40; Regional = 40/35/15/10; Global = 40/30/15, then Application & implementation progress 10 (includes "GitHub stars"), then Presentation 5. |
    | 17b | 15-min presentation + 5-min Q&A in English. | **VERIFIED** for 2026–27 Regional and Global | Huawei Talent page. |
    | 17c | AI mandatory; fully functional. | **VERIFIED** | "Participants are required to use AI technologies and develop their entries based on Huawei Cloud services." |
    | 17d | 2026–27 rules, deadlines, regional grouping. | **VERIFIED** (Northern Africa division) | Registration Aug–Dec 2026; Preliminary Nov–Dec 2026; National Jan 2027; Regional Feb/Mar 2027; Global May 2027. The Huawei Ethiopia registration guide points Ethiopian students to the "Northern Africa" division. Confirm with the Huawei Ethiopia ICT Ecosystem contact. |
    | 17e | *New:* submission requirements. | **VERIFIED** | Prelim/National: Template 1 (Word) covering background, innovation highlights, **dataset selection and processing**, key code snippets, quantifiable indicators. Regional: Template 2 PPT, visual demo-page URL, complete code, datasets, reproducible README, and proof of open-sourcing. |
    | 17f | Demo on a cheap Android phone. | **Allowed** | "Hardware devices are optional … the team must prepare them on their own." The cloud demo page is mandatory in addition. |
    ✓ • 0ms

## Activity

    $ sed -n '270,540p' docs/DIMTS_ARCHITECTURE_CONTEXT.md
    | D-6 Synthetic classroom mixtures | Adult speech + child-babble + RIR + choral simulation (time-aligned copies with jitter/pitch shift) | Pre-training the student before real data arrives | Anywhere (built from public data) |
    
    **Rule:** the **test set is real classroom audio only** (D-2 held-out lessons, split by teacher and by school, never by random frames).
    
    ### 9.3 Annotation
    - Tool: Label Studio (self-hosted on the in-country server) or equivalent audio segmentation tool [DECISION: ADR-006].
    - Protocol: written annotation guide in `docs/annotation-guide.md` with audio examples for each class, especially CHILD_SINGLE vs CHILD_CHORAL vs OVERLAP boundary cases.
    - Agreement: double-annotate ≥ 15% of hours; report Cohen's κ on frame labels. Target κ ≥ 0.70 before training on single-annotated data.
    - Annotators: team + trained native speakers for language labels. Pay/credit per ethics approval.
    
    ### 9.4 Models
    - **Teacher model:** multilingual self-supervised speech encoder (loaded via MindNLP) + frame classification head (T-1) and pooled head (T-2). Fine-tuned on D-2, adapted on D-3/D-4. Server-side only.
    - **Student models:** small CRNN or compact conformer on log-mel. Trained on D-2 hard labels + teacher soft labels on unannotated D-1 + D-6. Loss: CE on hard labels + KL to teacher posteriors (temperature T tuned).
    - **Quantization:** MindSpore Lite post-training INT8 with a calibration set drawn from real classroom audio (not synthetic).
    
    ### 9.5 Export path
    ```
    MindSpore (train) → export MindIR → MindSpore Lite converter → .ms (FP32) → PTQ INT8 → .ms (INT8)
    Escape hatch: framework-agnostic training → ONNX → MindSpore Lite converter → .ms
    ```
    Both paths must be scripted and tested in CI from week 3.
    
    ---
    
    ## 10. Data model and flows
    
    ### 10.1 What leaves the device (Deployment Mode)
    
    ```jsonc
    // LessonRecord v1 — the ONLY payload that syncs in Deployment Mode
    {
      "record_id": "uuidv7",
      "schema_version": 1,
      "indicator_spec_version": 1,
      "model_versions": { "seg": "seg-student-0.3.1-int8", "lid": "lid-student-0.2.0-int8" },
      "teacher_pseudo_id": "opaque, server-issued at enrolment",
      "school_id": "opaque",
      "grade": "O|1|2|3",
      "subject": "mother_tongue|math|english|environmental|other",
      "lesson_start_local": "2026-11-03T08:15:00+03:00",
      "duration_s": 2400,
      "indicators": {
        "I1_teacher_talk_ratio": 0.78,
        "I2_response_latency_s": { "median": 0.6, "p25": 0.3, "p75": 1.1, "n": 42 },
        "I3_choral_ratio": 0.91,
        "I4_individual_responses": { "count": 5, "per_10min": 1.25 },
        "I5_turn_density_per_min": 1.9,
        "I6_language_share": { "amh": 0.62, "sid": 0.31, "eng": 0.05, "oth": 0.02 },
        "I7_interaction_coverage": 0.55,
        "I8_long_silence_events": 1
      },
      "quality": { "mean_seg_confidence": 0.84, "clipping_ratio": 0.001, "snr_estimate_db": 11.2 },
      "device": { "model_class": "arm64-2gb", "app_version": "0.4.0" }
    }
    ```
    
    No audio, no embeddings, no timestamps finer than lesson start, no child-level data.
    
    ### 10.2 Server entities (openGauss)
    `teacher` (pseudo_id, school_id, consent_state, language_pref) · `school` · `woreda` · `lesson_record` · `weekly_report` · `aggregate_cell` (woreda × grade × week, suppressed if n < k) · `consent_event` (append-only ledger) · `audit_log` · `research_recording` (Research Mode only, pointer to encrypted blob, consent_ref).
    
    ### 10.3 Identity
    - Teacher enrols with a code issued by the research team; server returns an opaque `teacher_pseudo_id`. Real names stored only in a separate, access-restricted enrolment table (or on paper) [DECISION: ADR-007].
    
    ---
    
    ## 11. Privacy, security, and ethics architecture
    
    | Concern | Control |
    |---|---|
    | Raw audio exposure | Deployment Mode: RAM ring buffer only, never persisted. Research Mode: per-lesson AES key, file encrypted at rest, deleted from phone after verified transfer. |
    | Voice biometrics | No speaker embeddings persisted anywhere. Models output class posteriors only. |
    | Child data | No child-level features. CHILD_* are acoustic classes, not individuals. |
    | Data localisation (C-7) | All personal data (records, research audio, enrolment) on in-country server. Huawei Cloud holds public data and model artefacts only. |
    | Re-identification via dashboard | k-anonymity: suppress any aggregate cell with < 5 teachers. No drill-down to school when school has < 5 participating teachers. |
    | Teacher misuse by management | Individual reports visible only to the teacher. Sharing with director is teacher-initiated, revocable. Written into consent form and product copy. |
    | Transport | TLS 1.2+, certificate pinning in app. |
    | At rest | Device: Keystore-backed encryption. Server: disk encryption + encrypted backups in-country. |
    | Consent | Per-teacher consent recorded in app and in server ledger; separate opt-in for Research Mode; withdrawal deletes server records (tombstone + purge job). Parent/school notification per ethics board. |
    | Breach | 72-hour notification runbook (per C-7) in `docs/ethics/breach-runbook.md`. |
    | Approvals | Hawassa University research ethics review **before first recording**. School and woreda permission letters. |
    
    ---
    
    ## 12. Proposed repository structure (monorepo)
    
    ```
    dimts/
    ├── README.md
    ├── docs/
    │   ├── ARCHITECTURE_CONTEXT.md        ← this file
    │   ├── adr/                           ← ADR-000 template, ADR-001..n
    │   ├── annotation-guide.md
    │   ├── indicator-spec.md              ← mirrors §5, versioned
    │   └── ethics/                        ← consent forms (amh, sid, eng), approval, breach runbook
    ├── indicators-core/
    │   ├── python/                        ← reference implementation (package: dimts_indicators)
    │   ├── kotlin/                        ← Android port (Gradle module)
    │   └── golden/                        ← JSON timelines + expected outputs; CI runs both impls
    ├── ml/
    │   ├── configs/                       ← experiment configs (YAML)
    │   ├── data/                          ← manifests, splits, NO audio in git
    │   ├── features/                      ← log-mel, augmentation (MindAudio + custom)
    │   ├── synth/                         ← synthetic classroom mixture generator (D-6)
    │   ├── teacher/                       ← SSL encoder fine-tuning (MindSpore + MindNLP)
    │   ├── student/                       ← CRNN / compact conformer
    │   ├── distill/                       ← soft-label generation, KD training
    │   ├── export/                        ← MindIR → .ms, INT8 PTQ, ONNX fallback
    │   ├── eval/                          ← frame F1, indicator error, cross-language, latency
    │   └── modelarts/                     ← job definitions for Huawei Cloud
    ├── edge/
    │   └── android/
    │       ├── app/                       ← UI (Jetpack Compose), i18n: amh, sid, eng
    │       ├── audio/                     ← capture service, ring buffer
    │       ├── inference/                 ← MindSpore Lite wrapper (Java API or JNI)
    │       ├── pipeline/                  ← features → seg → LID → post-process
    │       ├── storage/                   ← encrypted Room/SQLCipher
    │       ├── sync/                      ← outbox, WorkManager, API client
    │       └── benchmark/                 ← on-device RTF / RSS / battery harness
    ├── server/
    │   ├── api/                           ← ingest, reports, dashboard API
    │   ├── jobs/                          ← weekly reports, aggregation, purge
    │   ├── db/                            ← openGauss migrations
    │   └── tests/
    ├── dashboard/                         ← district web UI
    ├── harmony/                           ← (stretch) ArkTS client, R-5
    ├── infra/
    │   ├── openeuler/                     ← host provisioning, container compose, backups
    │   └── ci/                            ← pipelines (CodeArts or GitHub Actions)
    └── tools/
        ├── lesson-simulator/              ← replays recorded/synthetic lessons into the pipeline
        └── demo/                          ← competition demo scripts and fixtures
    ```
    
    **Backend language [DECISION: ADR-005]:** default proposal Python (FastAPI) for the server so the indicator reference implementation and aggregation share one language with the ML stack. Confirm openGauss driver compatibility in week 1.
    
    ---
    
    ## 13. Interfaces
    
    ### 13.1 Sync API (sovereign tier)
    - `POST /v1/lessons` — body: array of `LessonRecord`; response: per-record `{record_id, status: accepted|duplicate|rejected, reason?}`. Idempotent on `record_id`.
    - `GET /v1/reports/latest?teacher_pseudo_id=…` — authenticated per teacher device token.
    - `POST /v1/consent` — consent event append.
    - `DELETE /v1/teacher/{pseudo_id}` — withdrawal, triggers purge.
    - Auth: device-bound token issued at enrolment; rotate on reinstall.
    
    ### 13.2 Model contract (edge)
    ```
    seg_student.ms   input:  float32[1, 64, T]   (log-mel, T frames)
                     output: float32[1, T, 5]    (posteriors: TEACHER, CHILD_SINGLE, CHILD_CHORAL, OVERLAP, NON_SPEECH)
    lid_student.ms   input:  float32[1, 64, 300] (3 s log-mel)
                     output: float32[1, 4]       (amh, sid, eng, oth)
    ```
    Each model ships with `model_card.json`: version, training data summary, eval metrics, calibration set hash, feature config hash. The app refuses a model whose feature config hash does not match the pipeline.
    
    ---
    
    ## 14. Evaluation plan (definition of done)
    
    ### 14.1 Model metrics (real classroom test set, split by teacher/school)
    
    | Metric | Target (thesis) | Stretch |
    |---|---|---|
    | T-1 frame macro-F1 (5 classes) | ≥ 0.70 | ≥ 0.80 |
    | T-1 CHILD_SINGLE vs CHILD_CHORAL F1 | ≥ 0.75 | ≥ 0.85 |
    | T-2 LID segment accuracy (teacher segments ≥ 3 s) | ≥ 0.85 | ≥ 0.92 |
    | Student vs teacher-model gap (macro-F1) | ≤ 0.08 | ≤ 0.04 |
    | INT8 vs FP32 gap (macro-F1) | ≤ 0.02 | ≤ 0.01 |
    
    ### 14.2 Indicator validity (the metric that matters)
    Compare Dimts indicators against human-coded indicators on held-out lessons:
    - I-1 teacher talk ratio: mean absolute error ≤ 5 percentage points.
    - I-2 response latency median: MAE ≤ 0.5 s.
    - I-3 choral ratio: MAE ≤ 10 percentage points.
    - Across lessons, Spearman ρ between Dimts and human indicators ≥ 0.80 for I-1, I-3, I-5, I-6.
    
    ### 14.3 Cross-language generalisation (thesis contribution)
    - Train T-1 on Amharic-school lessons only; test on Sidaamu Afoo-school lessons. Report degradation vs in-language baseline.
    - Hypothesis: segmentation degrades little (acoustic, script-independent); LID requires both languages in training. Report honestly either way.
    
    ### 14.4 System metrics (target device)
    - RTF ≤ 0.3; peak RSS ≤ 300 MB; battery ≤ 10% / 40 min; crash-free sessions ≥ 99% in pilot.
    - Sync: 100% of records eventually delivered in a simulated 72-hour offline test with random disconnects.
    
    ### 14.5 Pilot / usefulness
    - ≥ 6 teachers, ≥ 3 weeks of use, both languages.
    - Short structured teacher interviews (reuse your survey-methods skills): perceived usefulness, trust, discomfort. Pre/post change in I-2 and I-4 as exploratory (not causal) evidence.
    
    ---
    
    ## 15. Team and ownership
    
    | Person | Owns | Key deliverables |
    |---|---|---|
    | **Anteneh (lead)** | Data, ethics, indicator spec, evaluation design, pilot, thesis, pitch | Ethics approval, consent forms (3 languages), annotation guide + κ, indicator spec v1, eval report, demo script |
    | **Person 2 (ML)** | `ml/`, model cards, export | Teacher model, student models, INT8 `.ms` artefacts, eval numbers |
    | **Person 3 (systems)** | `edge/android/`, `server/`, `dashboard/`, `infra/` | Streaming pipeline, encrypted storage, sync, server on openEuler + openGauss, dashboard |
    | Shared | `indicators-core/` | Python reference (lead), Kotlin port (P3), golden vectors (lead + P3) |
    
    If no teammate can fine-tune speech models, that is the first gap to close.
    
    ---
    
    ## 16. Timeline (from 2026-09-25)
    
    > Registration for the 2026–2027 cycle is open now; confirm the Innovation Competition deadline and Ethiopia's regional grouping on the Huawei Talent portal this week [VERIFY].
    
    | Weeks | Dates (approx.) | Milestone |
    |---|---|---|
    | W0 | Sep 25 – Oct 4 | Register team. Submit ethics application. Record **one** real lesson on a cheap phone and listen to it. Week-1 spikes: MindSpore Lite on target phone (hello-world INT8 model), MindNLP loading an SSL encoder, openGauss + backend driver. |
    | W1–2 | Oct 5 – Oct 18 | ADR-001..005 ratified. Indicator spec v1 + golden vectors. Synthetic mixture generator (D-6). Android capture service + ring buffer. Repo + CI. |
    | W3–4 | Oct 19 – Nov 1 | Ethics approved (target). First 10 Research Mode lessons. Annotation guide + κ pilot. Student v0 trained on synthetic data, exported to `.ms`, **running live on the phone** (end-to-end thin slice with fake-quality labels). |
    | W5–6 | Nov 2 – Nov 15 | 20–30 lessons collected. 6–10 h annotated. Teacher model fine-tuned. LID v0. Server ingest + openGauss live on openEuler. |
    | W7–8 | Nov 16 – Nov 29 | Distillation → student v1. INT8 PTQ with real calibration. Indicator validity eval v1. First weekly reports to pilot teachers. Dashboard v0. |
    | W9–10 | Nov 30 – Dec 13 | Cross-language eval. On-device benchmark report. Dashboard k-anonymity. Demo video. **Feature freeze.** |
    | W11+ | Dec 14 → | Pitch deck, rehearsal, competition submission. Continue pilot for thesis. Post-national: roadmap items (R-1..R-5). |
    
    **Critical path:** ethics approval → data collection → annotation → teacher model → distillation. Start ethics in W0 or the whole plan slips.
    
    ---
    
    ## 17. Competition mapping
    
    Innovation Competition judging criteria (per Huawei's published description of prior editions): **creativity, system complexity, technical complexity, societal benefit, functionality.** Presentation format at global level: 15 min presentation + 5 min Q&A, in English; PPT, video, or physical demo. AI use is mandatory. [VERIFY for 2026–2027 rules]
    
    | Criterion | Dimts evidence |
    |---|---|
    | Creativity | Teacher-facing, not child-facing. No-ASR design turns a language gap into a strength. First measurement of mother-tongue policy compliance in the room. |
    | System complexity | Edge (streaming inference) + sovereign server (openEuler/openGauss) + cloud (ModelArts) + dashboard; offline-first sync; consent ledger. |
    | Technical complexity | Teacher–student distillation from a multilingual SSL encoder into an INT8 on-device student; real-time RTF ≤ 0.3 on a 2 GB phone; cross-script generalisation experiment. |
    | Societal benefit | EGRA flatline (§2.1) → teacher feedback loop at near-zero marginal cost; data sovereignty by design; pilot numbers. |
    | Functionality | Live demo on a cheap Android phone: start lesson, play recorded classroom clip, show indicators and report, show dashboard update. |
    
    ### 17.1 Demo script (draft, 15 min)
    1. (2 min) The flatline chart. One sentence: "Ethiopia has measured children for 15 years. No one has given teachers a mirror."
    2. (2 min) A 20-second classroom clip. Ask judges to guess the teacher talk ratio.
    3. (4 min) Live: phone processes the clip in real time; indicators appear; teacher report in Amharic and Sidaamu Afoo.
    4. (3 min) Architecture: edge / sovereign / cloud; why audio never leaves the phone; MindSpore → MindSpore Lite INT8.
    5. (2 min) Results: indicator validity vs human coders; cross-language result; on-device benchmarks.
    6. (2 min) Pilot teacher quote + dashboard + roadmap.
    
    Prepare Q&A answers for: accuracy in very noisy classes; teacher surveillance concerns; why not ASR; scaling to other languages; cost per school; data law compliance; why Android and not HarmonyOS.
    
    ---
    
    ## 18. Risks and mitigations
    
    | Risk | Likelihood | Impact | Mitigation |
    |---|---|---|---|
    | Ethics approval delayed | Med | High | Submit W0; start with synthetic data (D-6) so ML work is not blocked. |
    | Audio unusable (too noisy) | Med | High | W0 test recording. Test placement (desk vs wall), clip-on mic fallback. |
    | MindSpore/MindNLP bug blocks teacher model | Med | Med | Week-1 spike; ONNX escape hatch; keep student training in MindSpore. |
    | CHILD_SINGLE vs CHILD_CHORAL not separable | Low–Med | High | Synthetic choral simulation; add OVERLAP class; fall back to 4-class if needed and report. |
    | Sidaamu Afoo LID data scarce | High | Med | Collect consented adult speech early; accept 3-class (amh / non-amh / eng) v0. |
    | Phone too slow | Low–Med | High | Budget in W3 thin slice; reduce context window; frame skipping. |
    | Teachers perceive surveillance | Med | High | Private-by-default reports; co-design report wording with 2 teachers; consent copy reviewed by E-Learning Directorate. |
    | Team capacity | Med | High | Strict scope; roadmap items stay roadmap. |
    | Legal interpretation of Proclamation 1321/2024 | Med | Med | Get written guidance via university legal/ethics office; default to in-country storage for all personal data. |
    
    ---
    
    ## 19. Open decisions (ADR backlog)
    
    | ADR | Question | Default proposal |
    |---|---|---|
    | ADR-001 | Segmentation as frame classification vs clustering diarization | Frame classification, 5 classes |
    | ADR-002 | Student architecture | Small CRNN first; compact conformer if budget allows |
    | ADR-003 | Where classroom audio is used for training | In-country training node only; ModelArts for public data and pipeline; no raw classroom audio to cloud without explicit consent + legal sign-off |
    | ADR-004 | MindSpore Lite Java API vs JNI (C++) on Android | Java API first; JNI only if profiling demands |
    | ADR-005 | Backend language & framework | Python + FastAPI |
    ✓ • 0ms

## Activity

    $ cat docs/research/RESEARCH_PLAN.md
    # Research plan — Dimts (final, v1.0)
    
    Status as of 2026-09-26. This plan builds on [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md), which is now updated with the full texts in [`papers/`](papers/). Related files:
    
    - [`ASSUMPTIONS.md`](ASSUMPTIONS.md) — every assumption the design still rests on.
    - [`CODEBASE_STRUCTURE.md`](CODEBASE_STRUCTURE.md) — the repository layout that follows from these findings.
    
    Owners follow §15 of the architecture document:
    
    - **Lead** — Anteneh
    - **ML** — Person 2
    - **Sys** — Person 3
    
    Weeks count from W0 = 2026-09-25. Sources are cited (Author, year) and listed in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).
    
    ---
    
    ## 1. What the evidence now says
    
    1. **The problem is real and measured.**
       - Only 32.4% of Grade 2–3 pupils reached the top two reading benchmarks in 2018, essentially unchanged since 2014 (31.3%) and 2016 (34.3%). Only 6.2% read fluently with full comprehension (AIR, 2019).
       - Only 39% of mother-tongue teachers are Proficient-or-above, and Sidaamu Afoo teachers sit in the 22–33% band (AIR, 2020).
    2. **Observation-based feedback is associated with better reading, but it is patchy.** Where a school has someone who observes mother-tongue classes, and observes more often, pupils read better (AIR, 2019, pp. 53–54). There is no standardised or frequent measure. This is the gap Dimts fills. "No feedback at all" overstates it.
    3. **Classroom language has never been measured systematically.** Earlier work used handwritten observation notes (Heugh et al., 2007) or children's self-reports (Piper et al., 2016). Automated I-6 is a defensible novelty claim.
    4. **Audio-only classroom analytics work at coarse grain.** Reported results: ~90% (Owens et al., 2017), >80% (Schlotterbeck et al., 2021), F1 0.64–0.78 (Donnelly et al., 2016). Fine-grained teacher-vs-child separation is still hard: 69% in Wang et al. (2025).
    5. **Talk-time feedback changes behaviour.** In an RCT, automated feedback cut talk share by 5 pp (Demszky & Liu, 2023). No such evidence exists for choral ratio or language share yet.
    6. **The competition has changed shape.**
       - 2026–27 requires an AI application deployed on **Huawei Cloud** with a **visual demo page**.
       - Preliminary and National stages score **Innovation 60% / Application value 40%**.
       - Regional-stage entries must be reproducible and open source.
       - The Preliminary runs **Nov–Dec 2026**, earlier than the architecture doc's feature freeze.
    7. **Some stack assumptions broke:**
       - MindNLP is discontinued and MindAudio is unmaintained.
       - Lite has no INT8 GRU.
       - The AAR is 4 KB-aligned.
       - Jetpack Security is deprecated.
       - Sidama speech data and models do exist.
    
    ---
    
    ## 2. Principles
    
    1. **Evidence bar.** Only **VERIFIED** claims go into the thesis or pitch. Anything weaker is written as "reported by …".
    2. **One log.** Every search goes into `docs/research/search-log.csv` with columns `date, database, query, hits, screened, kept, notes`, so the review is PRISMA-style.
    3. **Check before you build.** Don't ratify an ADR until its research question closes.
    4. **Two data worlds.** Personal data (the pilot) stays in-country. The competition cloud demo holds synthetic or public data only (ASSUMPTIONS A-C3).
    5. **Reproducible by a stranger.** The Regional stage re-runs your code and data, so every number in the pitch must come from a script in `ml/eval/` or `tools/demo/`.
    
    ---
    
    ## 3. Workstreams
    
    Status is **Done**, **Open** or **Blocked** (waiting on an item in [`TO_OBTAIN.md`](TO_OBTAIN.md)).
    
    ### WS-A: Problem evidence (Lead)
    
    | RQ | Question | Status | Result or next step |
    |---|---|---|---|
    | A1 | EGRA 2014–2018 results | **Done** | 31.3 / 34.3 / 32.4% top-two levels, Grades 2–3, 7 languages. The Sidaamu Afoo 2016 value is flagged as unreliable (AIR, 2019). |
    | A2 | Pre-primary figures | **Done** | ESAA 2024/25: GER 59.8% excluding Amhara. The 75% O-class/child-to-child share is from ESAA 2021/22. Class size in Grades 1–6: 54.2 nationally, 59.6 in Sidama. |
    | A3 | How often are teachers observed, by whom, and with what feedback? | **Open** | EGRA Table 16 shows association but not base rates. Run 5–10 interviews (teachers, unit leaders, woreda supervisors) during W1–W3 school visits. Output: a 1-page memo. Due W3. |
    | A4 | Actual classroom languages and code-switching in the pilot schools | **Open** | Heugh et al. (2007) observed routine Amharic–English switching. Confirm for Sidama and Hawassa schools in W1 visits. This sets the LID label set. Due W2. |
    | A5 | Home vs school vs teacher factors | **Done** | MTTCA ranks home, then school, then teacher (AIR, 2020). Framing updated. |
    | A6 | Current mother-tongue policy grade span | **Blocked** (ETP 2023 text) | Needed for the §2.4 wording. Due W2. |
    
    ### WS-B: Pedagogy and indicator validity (Lead)
    
    The core thesis question: *Do Dimts' acoustic indicators measure something Ethiopian teachers and experts recognise as instructional quality, and in which direction?*
    
    | RQ | Question | Status | Method | Output | Due |
    |---|---|---|---|---|---|
    | B1 | Construct and normative direction for I-1 to I-8 | Open | Build the indicator × literature matrix (§9 reading list). I-1 now has RCT support (Demszky & Liu, 2023). | `docs/indicator-rationale.md` | W2 |
    | B2 | Local expert validation | Open | Panel of 5–8 people (Hawassa College of Education, READ alumni, E-Learning Directorate) rating relevance and direction. | Inputs to ADR-010 | W5 |
    | B3 | O-class specifics | Open | CLASS, MELE, Teach ECE; Alemu (2025). | Scope decision D-4 | W3 |
    | B4 | Feedback design | Open | Kraft et al. (2018); Demszky et al. (2023, 2025); Jensen et al. (2020); Hattie & Timperley (2007). | Report copy rules | W4 |
    | B5 | Surveillance perception | Open | Birnhack & Perry-Hazan (2021): nearly all teachers weigh their own privacy. Co-design with 2 teachers. | Consent copy | W3 |
    | B6 | I-3 as a balance indicator | Open | Heward et al. (1989) vs Pontefract & Hardman (2005). | I-3 reporting rule | W2 |
    
    ### WS-C: Prior systems and novelty (Lead + ML)
    
    - **C1: Systematic search** (protocol in §8). Novelty statement for I-6 and choral detection. Due W2.
    - **C2: Comparison table.** Done except the IEEE papers; see the table below.
    - **C3: Competition benchmarking.** Review previous Innovation winners in the Learning Space (the page links "reference entries from previous competitions"). Due W4.
    
    | System | Input → output | Setting | Result | Take-away for Dimts |
    |---|---|---|---|---|
    | DART (Owens et al., 2017) | Volume/variance → single, multiple or no voice | 1,486 US college sessions | ~90% | Closest no-ASR precedent; coarse classes |
    | Schlotterbeck et al. (2021) | Lavalier + smartphone; amplitude, mel and MFCC statistics → COPUS practices | 42 lessons, 19 teachers, Chile | >80% accuracy (2 practices); 7 min per hour of audio | Cheap-phone precedent; **clip-on mic** |
    | Chanchal & Zualkernan (ED626900) | Mel spectrograms → Stallings categories | Semi-rural Pakistan, phones | F1 0.83; 0.72–0.81 with 5–20% labels | Semi-supervised learning with few labels |
    | Donnelly et al. (2016) | Teacher audio, speech/rest, ASR, acoustic features → 5 segment types | 76 classes, 11 teachers | F1 0.64–0.78, teacher-independent | Validate across teachers, not frames |
    | Jensen et al. (2020) | Headset, ASR, ML → discourse features | 142 sessions, 89% usable | Moderate accuracy | Room mics noisy; usability metric |
    | M-Powering Teachers (Demszky & Liu, 2023; Demszky et al., 2023, 2025) | Transcripts → talk time and uptake feedback | US; RCTs | −5 pp talk time; +10–13% uptake | Automated feedback changes practice |
    | Classroom diarization (Wang et al., 2025) | ECAPA + Whisper | US group work | Teacher-vs-child 69% | Realistic difficulty |
    | VTC (Lavechin et al., 2020) | SincNet + RNN → 4 voice types | 260 h, 10 languages | Beats LENA | Frame-classifier baseline |
    | LENA feedback (Wang et al., 2013) | LENA → talk ratio | US maths PD | SECONDARY | Teacher-facing precedent |
    | Shapsough et al. (2016) | Mobile EGRA + MQTT | LMIC assessment | Low power | Sync design context |
    | Human tools: Teach, Stallings, CLASS, and Heugh et al. (2007) field notes | Observers | Global / Ethiopia | Validated or qualitative | What Dimts complements |
    
    ### WS-D: ML and data (ML)
    
    | Experiment | Question | Method | Feeds | Due |
    |---|---|---|---|---|
    | E1 | Usable audio: desk vs clip-on | W0–W1 test lessons. Measure SNR, clipping and RT60 estimate. Compare desk phone with a wired lavalier into the same phone (Schlotterbeck et al., 2021; Jensen et al., 2020). | Capture spec; ASSUMPTIONS A-T4 | W1 |
    | E2 | How hard is T-1 on real Ethiopian audio? | 1–2 annotated lessons; VTC, pyannote-seg-3.0 and a DART-style energy baseline; per-class F1 with teacher-independent splits. | §14.1 realism; 4-class fallback | W4 |
    | E3 | LID accuracy at 2 / 3 / 5 s | MMS-LID-256 (amh/sid/eng + other) on FLEURS, Afrivoice and pilot teacher segments. | LID threshold (audit 5d) | W3 |
    | E4 | Can choral vs single child speech be separated? | Synthetic choral mixtures from Afrivoice speakers + pitch/formant scaling; CountNet-style head; validate on real clips. | 5-class vs 4-class | W5 |
    | E5 | Student architecture under Lite ops | CRNN (FP16/FP32 GRU) vs convolution-only TCN (INT8) vs tiny conformer. Measure F1, MB and RTF. | ADR-002 | W4 |
    | E6 | Distillation gap | KD from the D-1 teacher to the E5 winner. | §14.1 | W8 |
    | E7 | Error propagation | Simulate confusion matrices on golden timelines and compute indicator error (Gautheron et al., 2025). | Final §14 targets | W3 |
    | E8 | Dataset register | Licence, gating, register, overlap between WAXAL and Afrivoice. | `ml/data/DATASETS.md` | W1 |
    | E9 | Competition "dataset selection and processing" section | Document D-3 to D-6 plus the synthetic-lesson generator as the Template 1 dataset story. | Preliminary submission | W6 |
    
    ### WS-E: Stack and device spikes (Sys + ML)
    
    | Spike | Pass criterion | Due |
    |---|---|---|
    | S1 | `.ms` INT8 hello-world through the 2.10.0 AAR on a 2 GB phone | W0 |
    | S2 | RTF ≤ 0.3 and RSS ≤ 300 MB via the bundled `benchmark` tool and the in-app harness | W3 |
    | S3 | PyTorch→ONNX→`.ms` and MindSpore→MindIR→`.ms` outputs agree within 1e-4; full-quant PTQ runs; list the ops that fall back to FP32 | W3 |
    | S4 | MindSpore 2.10 trains a toy model on the in-country hardware | W1 |
    | S5 | ModelArts, OBS and ECS all in **AF-Johannesburg**; flavours and prices recorded; OBS unencrypted and holding **public data only** | W1 |
    | S6 | openEuler LTS + openGauss + psycopg connect (SASL/sha256 workaround); **host chosen from [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md)** — Zergaw VPS/DBaaS, INSA G-Cloud or Ethio Telecom teleCloud, not the on-campus box, unless the university prefers self-hosting | W1 |
    | S7 | Microphone foreground service; `AudioRecordingCallback` detects call-silencing; 40-minute battery test | W2 |
    | S8 | 16 KB decision: sideload for the pilot; rebuild Lite before any Play release | W6 |
    | S9 | SQLCipher + Keystore key | W2 |
    | **S10** (new) | **Competition cloud stack:** ECS + RDS for PostgreSQL (or GaussDB) + OBS in AF-Johannesburg serving the demo page and lesson simulator with synthetic lessons. Redeployable from a script. | W5 |
    
    ### WS-F: Legal and ethics (Lead)
    
    Primary text: Proclamation 1321/2024. Get the official Gazette copy (TO_OBTAIN #2).
    
    Questions for the legal advisor, answered in writing by W3:
    
    1. Is the university the data controller, and does Art. 33 registration apply to student research?
    2. Is transient in-RAM processing of children's voices processing of a minor's data (Arts. 2(16), 11)? What consent does it need?
    3. Research Mode child audio: Art. 11 parental consent, or the Art. 54 research exemption?
    4. Is raw voice audio without speaker identification *biometric* under Art. 2(6)?
    5. Are teacher-linked indicators personal data, and does in-country storage satisfy Art. 22(1)?
    6. Are a DPIA (Art. 47) and a DPO (Art. 40) needed?
    7. Could the weekly report be read as an automated decision about the teacher (Art. 31)?
    8. **New:** does a competition demo on Huawei Cloud (AF-Johannesburg) holding only synthetic lessons, plus scripted demo accounts, fall entirely outside the Proclamation? Get this in writing.
    
    Ethics: the Hawassa IRB (the critical path); assent and consent forms in Amharic, Sidaamu Afoo and English; school and woreda letters.
    
    ### WS-G: Competition (Lead)
    
    Northern Africa division, 2026–27 (see audit §7):
    
    | Item | Requirement | Plan |
    |---|---|---|
    | Topic | AI application powered by Huawei Cloud | Dimts cloud tier: indicator analytics, teacher report generation, district dashboard and a lesson simulator, all on Huawei Cloud with synthetic data |
    | Huawei Cloud service | ≥ 1 service (ECS, RDS, DCS …) | ECS + RDS/GaussDB + OBS (+ ModelArts for training on public data) |
    | Visual demo page | Mandatory | `dashboard/` + `tools/lesson-simulator` web page |
    | Hardware | Optional, team-provided | Live phone demo as a bonus |
    | Prelim/National scoring | Innovation 60 / Application value 40 | Lead with the no-ASR, language-agnostic, on-device design plus the I-6 novelty. Back application value with EGRA and MTTCA numbers. |
    | Regional reproducibility | Code, datasets and README re-run by the committee | Synthetic dataset generator and scripts in the repo; the pilot data is never needed to reproduce the demo |
    | Open source | GitHub or Gitee for Regional and Global shortlists | Keep `docs/research/papers/` out of the public repository |
    
    ---
    
    ## 4. Decisions that need the team
    
    | ID | Decision | Recommendation |
    |---|---|---|
    | D-1 | Teacher-model framework (MindNLP is gone) | PyTorch/HF teacher on the in-country node, MMS-LID-256 as the LID teacher, and the student trained in MindSpore. Reword the pitch to "every model on the phone is trained in MindSpore and runs on MindSpore Lite". |
    | D-2 | Student architecture | Decide on E5 data; default to a convolution-only TCN if RTF is tight. |
    | D-3 | Distribution | Sideload for the pilot; handle 16 KB before any Play release. |
    | D-4 | O-class in scope for coaching? | Wait for B3. |
    | D-5 | CC-BY-NC models (MMS) | Research and competition only; state it in the model cards. |
    | **D-6** (new) | Competition cloud tier vs data sovereignty | Two deployments from one codebase: `infra/huawei-cloud` (demo, synthetic data, AF-Johannesburg) and `infra/openeuler` (pilot, personal data, Hawassa). There is no data path from pilot to cloud (ASSUMPTIONS A-C3). |
    | **D-7** (new) | Microphone | Decide from E1. If desk SNR is poor, ship a wired lavalier as part of the kit. |
    
    ---
    
    ## 5. Schedule (architecture §16 merged with the competition calendar)
    
    The exact Preliminary deadline within Nov–Dec 2026 is not yet known. The plan assumes **1 Dec 2026** until the templates or portal say otherwise (ASSUMPTIONS A-C1).
    
    | Week | Dates | Research and build | Competition |
    |---|---|---|---|
    | W0 | Sep 25 – Oct 4 | E1 test lessons; S1, S4, S6; legal questions sent; ethics submitted; A3/A4 visits begin | Register team; download templates; confirm division with Huawei Ethiopia |
    | W1–2 | Oct 5 – 18 | A4, A6; B1, B6; C1; E8; S5, S7, S9; ADR-001/004/005 | Apply for Huawei Cloud vouchers |
    | W3–4 | Oct 19 – Nov 1 | A3 memo; B3, B5; E2, E3, E5, E7; S2, S3; legal answers; ADR-002/003 | Draft Template 1 |
    | W5–6 | Nov 2 – 15 | B2 panel; E4; **S10 cloud demo live**; S8 decision; E9 | Record demo video |
    | W7–8 | Nov 16 – 29 | E6 distillation; indicator validity v1 (§14.2) on pilot data | **Preliminary submission (assumed by 1 Dec)** |
    | W9–10 | Nov 30 – Dec 13 | Cross-language evaluation (§14.3); feature freeze | Preliminary review |
    | W11–16 | Dec 14 – Jan 31 | Pilot continues; literature review chapter from the search log | **National final (Jan 2027)** |
    | W17–24 | Feb – Mar 2027 | Open-source cleanup; reproducibility check by a non-team member | **Regional final (Feb/Mar 2027)** |
    
    ---
    
    ## 6. Open questions only the team can answer
    
    1. The training node's hardware (S4, D-1).
    2. The pilot teachers' phones (ASSUMPTIONS A-T1).
    3. Hawassa IRB turnaround.
    4. Play Store: ever?
    5. Who is the data controller?
    6. **New:** will the Amharic-medium pilot schools be in Hawassa or Sidama rather than Amhara region? ESAA 2024/25 excludes Amhara because schooling is disrupted there (ASSUMPTIONS A-P3).
    7. **New:** does the team accept making the product code open source (a Regional requirement)?
    
    ---
    
    ## 7. Search protocol (C1 and literature review)
    
    **Databases:** ERIC, ACL Anthology, ISCA Archive, IEEE Xplore, ACM DL (LAK, L@S, CHI, UMAP), EDM/JEDM, arXiv (cs.SD, eess.AS, cs.CL), Google Scholar, plus Scopus or Web of Science via the library.
    
    **Query blocks, combined with AND:**
    
    1. `classroom OR lesson OR teacher OR "early grade" OR preschool OR kindergarten`
    2. `audio OR acoustic OR "speech activity" OR diarization OR "voice type" OR "language identification" OR "code-switching"`
    3. `feedback OR observation OR "teacher talk" OR "wait time" OR choral OR "student talk" OR "language of instruction"`
    4. Optional LMIC filter: `Africa OR Ethiopia OR Kenya OR "low-income" OR "developing countries" OR India OR Pakistan`
    
    **Inclusion criteria:** empirical, automated or audio-based, classroom setting, 2010–2026, plus backward and forward snowballing from DART, VTC, M-Powering Teachers and Schlotterbeck et al. (2021).
    
    **Record for each paper:** setting, grade, language(s), microphone, input, classes, metric, result, and whether ASR was used.
    
    ---
    
    ## 8. Reading lists
    
    ★ marks the core list to read first. Full references are in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).
    
    **Ethiopian context**
    ★ AIR (2019, EGRA 2018 endline) · ★ AIR (2020, MTTCA) · ★ MoE (2025, ESAA 2024/25) · ★ Heugh et al. (2007) · Vujcich (2013) · Alemu (2025) · Kim (2020) · Wendie & Berhanu (2025) · Gobana (2025) · Yisihak & Damtew (2024) · Haile & Mendisu (2023) · Piper et al. (2016)
    
    **Pedagogy and indicator validity**
    ★ Rowe (1986) · ★ Tobin (1987) · ★ Howe et al. (2019) · ★ Liu & Cohen (2021) · ★ Pontefract & Hardman (2005) · ★ Heward et al. (1989) · ★ Kraft et al. (2018) · Hardman et al. (2009) · Alexander (2015) · Kelly et al. (2018) · Hattie & Timperley (2007) · Popova et al. (2021) · Popova & Evans (2016) · Glewwe & Muralidharan (2016) · Piper & Zuilkowski (2015) · Piper et al. (2018) · Cilliers et al. (2018) · Molina et al. (2018) · Stallings et al. (2014) · La Paro & Pianta (2003) · ★ Birnhack & Perry-Hazan (2021) · Shield & Dockrell (2003)
    
    **Prior systems**
    ★ Owens et al. (2017) · ★ Schlotterbeck et al. (2021) · ★ Demszky & Liu (2023) · ★ Jensen et al. (2020) · ★ Donnelly et al. (2016) · Demszky et al. (2023, 2025) · Jacobs et al. (2022) · Suresh et al. (2022) · Demszky & Hill (2023) · Wang & Demszky (2023) · Chanchal & Zualkernan (ED626900) · Zualkernan & Khan (2020) · Shapsough & Zualkernan (2017) · Shapsough et al. (2016) · Cosbey et al. (2019) · Li et al. (2020) · Wang et al. (2013, 2014) · Blanchard et al. (2016)
    
    **Speech and ML**
    ★ Wang et al. (2025) · ★ Lavechin et al. (2020) · ★ Gautheron et al. (2025) · ★ Pratap et al. (2023) · Diack et al. (2026) · Conneau et al. (2022) · Valk & Alumäe (2020) · Babu et al. (2021) · Baevski et al. (2020) · Hsu et al. (2021) · Radford et al. (2022) · Plaquet & Bredin (2023) · Bredin (2023) · Stöter et al. (2018) · Hinton et al. (2015) · Chang et al. (2022) · Schmid et al. (2023) · Jacob et al. (2018) · Snyder et al. (2015, 2018) · Desplanques et al. (2020) · Ko et al. (2017) · Park et al. (2019) · Potamianos & Narayanan (2003) · Lee et al. (1999) · Abate et al. (2005) · Gauthier et al. (2016) · Ganek & Eriks-Brophy (2018)
    
    **Infrastructure documentation**
    ★ MindSpore Lite 2.10.0 (release notes, downloads, quantization, operator list) · ★ Huawei Cloud ModelArts and Cloud Connect overviews · ★ 2026–27 Innovation Competition page · MindSpore 2.10.0 release notes · Android FGS, audio sharing, 16 KB pages, Jetpack Security notes · SQLCipher · RFC 9562 · openGauss psycopg2 · Label Studio
    
    **Legal and ethics**
    ★ Proclamation 1321/2024 · Sweeney (2002) · Hawassa IRB guidance (to obtain)
    ✓ • 0ms

## Activity

    $ cat edge/android/settings.gradle.kts edge/android/app/build.gradle.kts edge/android/gradle/libs.versions.toml edge/android/app/src/main/AndroidManifest.xml edge/android/app/src/main/java/org/ethioware/echo/MainActivity.kt; cat docs/legal/README.md
    pluginManagement {
        repositories {
            google {
                content {
                    includeGroupByRegex("com\\.android.*")
                    includeGroupByRegex("com\\.google.*")
                    includeGroupByRegex("androidx.*")
                }
            }
            mavenCentral()
            gradlePluginPortal()
        }
    }
    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    }
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
        }
    }
    
    rootProject.name = "Echo"
    include(":app")
    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.kotlin.compose)
    }
    
    android {
        namespace = "org.ethioware.echo"
        compileSdk {
            version = release(37)
        }
    
        defaultConfig {
                ndk { abiFilters += "arm64-v8a" }
        }
    
        defaultConfig {
            applicationId = "org.ethioware.echo"
            minSdk = 29
            targetSdk = 37
            versionCode = 1
            versionName = "1.0"
    
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    
        buildTypes {
            release {
                optimization {
                    enable = false
                }
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        buildFeatures {
            compose = true
        }
    }
    
    dependencies {
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.compose.material3)
        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.ui.graphics)
        implementation(libs.androidx.compose.ui.tooling.preview)
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        testImplementation(libs.junit)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.compose.ui.test.junit4)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(libs.androidx.junit)
        debugImplementation(libs.androidx.compose.ui.test.manifest)
        debugImplementation(libs.androidx.compose.ui.tooling)
    }[versions]
    agp = "9.4.1"
    coreKtx = "1.10.1"
    junit = "4.13.2"
    junitVersion = "1.1.5"
    espressoCore = "3.5.1"
    lifecycleRuntimeKtx = "2.6.1"
    activityCompose = "1.8.0"
    kotlin = "2.2.10"
    composeBom = "2026.02.01"
    
    [libraries]
    androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
    junit = { group = "junit", name = "junit", version.ref = "junit" }
    androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
    androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
    androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
    androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
    androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
    androidx-compose-ui = { group = "androidx.compose.ui", name = "ui" }
    androidx-compose-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
    androidx-compose-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
    androidx-compose-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
    androidx-compose-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
    androidx-compose-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
    androidx-compose-material3 = { group = "androidx.compose.material3", name = "material3" }
    
    [plugins]
    android-application = { id = "com.android.application", version.ref = "agp" }
    kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
    
    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools">
    
        <application
            android:allowBackup="true"
            android:dataExtractionRules="@xml/data_extraction_rules"
            android:fullBackupContent="@xml/backup_rules"
            android:icon="@mipmap/ic_launcher"
            android:label="@string/app_name"
            android:roundIcon="@mipmap/ic_launcher_round"
            android:supportsRtl="true"
            android:theme="@style/Theme.Echo">
            <activity
                android:name=".MainActivity"
                android:exported="true"
                android:label="@string/app_name"
                android:theme="@style/Theme.Echo"
                android:windowSoftInputMode="adjustResize">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />
    
                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity>
        </application>
    
    </manifest>package org.ethioware.echo
    
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.tooling.preview.Preview
    import org.ethioware.echo.ui.theme.EchoTheme
    
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                EchoTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
    
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
    
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        EchoTheme {
            Greeting("Android")
        }
    }# Legal source documents
    
    - [`Proclamation-1321-2016EC-Personal-Data-Protection.pdf`](Proclamation-1321-2016EC-Personal-Data-Protection.pdf) — Ethiopia's Personal Data Protection Proclamation No. 1321/2024 (1321/2016 E.C.), Federal Negarit Gazette, 30th Year No. 35, 24 July 2024. Supplied by the user 2026-09-26; confirmed identical to the DataGuidance-hosted scan used throughout `docs/research/` (same 64 pages, same Article 22 text). See [`../research/CLAIMS_AUDIT.md`](../research/CLAIMS_AUDIT.md) §3 and [`../research/HOSTING_OPTIONS.md`](../research/HOSTING_OPTIONS.md) for how it's used.
    
    **Before making this repository public** (the Huawei ICT Competition requires open-sourcing for the Regional and Global stages — see `docs/research/RESEARCH_PLAN.md` WS-G): keep this folder and `docs/research/papers/` out of the public repository, or check with the legal advisor whether redistributing the Gazette text is fine. The Proclamation itself is a public law, but confirm before including any other legal correspondence here.
    ✓ • 0ms

## Assistant

The repository has a basic Android starter app and placeholder directories. The research plans correct several assumptions that the main architecture still relies on. I also found gaps in missing-audio handling, sync and withdrawal behavior, model validation, and the boundary between pilot data and the cloud demo. I’m reconciling those documents and adding clear prerequisites for implementation.

## Activity

    Opened https://developer.android.com/guide/practices/page-sizes

## Activity

    $ sed -n '205,270p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; cat edge/android/app/src/main/res/xml/backup_rules.xml edge/android/app/src/main/res/xml/data_extraction_rules.xml; sed -n '1,65p' docs/research/HOSTING_OPTIONS.md
    │      → Outbox → Sync client (HTTPS, idempotent, resumable)                               │
    │  Research Mode only: encrypted raw audio file, per-lesson key, manual export             │
    └───────────────────────────────────────────┬─────────────────────────────────────────────┘
                                                │ indicators JSON (KB), TLS, pinned cert
    ┌───────────────────────── SOVEREIGN TIER (in-country, Hawassa Univ.) ────────────────────┐
    │  openEuler host · containers                                                             │
    │  Ingest API → validation (schema + spec version) → openGauss                              │
    │  Report service (weekly teacher report, rule table) → pull endpoint for app              │
    │  Aggregation job (k-anonymity ≥ 5) → Dashboard API → District web dashboard              │
    │  Consent ledger · Audit log · Backup (encrypted, in-country)                              │
    │  Research store (raw audio from Research Mode, encrypted, access-controlled)             │
    │  Local training node (GPU/CPU) for fine-tuning on classroom audio  [ADR-003]             │
    └───────────────────────────────────────────┬─────────────────────────────────────────────┘
                                                │ model artefacts only (no personal data)
    ┌──────────────────────────── HUAWEI CLOUD (non-personal compute) ────────────────────────┐
    │  ModelArts: pre-training / adaptation of teacher encoder on PUBLIC speech data           │
    │  OBS: public datasets, model artefacts, experiment tracking                              │
    │  Export pipeline: MindIR → MindSpore Lite converter → .ms (INT8 PTQ)                     │
    └─────────────────────────────────────────────────────────────────────────────────────────┘
    ```
    
    ### 8.2 Key design decisions (proposed; ratify via ADR)
    
    1. **Frame classification, not clustering diarization.** The task is a fixed 5-class problem, not "who spoke when" with unknown N. This is simpler, trainable, and real-time.
    2. **Teacher–student distillation.** A large multilingual self-supervised encoder (server-side "teacher model") is fine-tuned on annotated classroom audio and used to produce soft labels on unannotated audio. A tiny on-device "student" (CRNN or small conformer on log-mel) is trained on those soft labels. This is how a 2 GB phone gets near-large-model accuracy. It is also the main technical-complexity story for judges.
    3. **Streaming, not batch, in Deployment Mode.** Raw audio exists only in a ≤ 10 s RAM ring buffer. Nothing is re-analysable after the lesson. This is a privacy guarantee and a storage guarantee at once.
    4. **Append-only sync, no CRDTs.** Each device produces immutable lesson records with client-generated UUIDv7 IDs. No record is ever edited on two devices, so no conflict resolution is needed. Deletion is a tombstone record.
    5. **Single indicator spec, two implementations, golden tests.** Python reference on server/eval; Kotlin port on device; shared golden vectors in CI.
    6. **Rule-based coaching, not model-based.** The weekly suggestion is a transparent lookup table from indicator ranges to one suggestion. Explainable to teachers, directors, and judges.
    
    ### 8.3 Edge pipeline details
    
    | Stage | Spec (initial; tune in experiments) |
    |---|---|
    | Capture | 16 kHz mono PCM16 via `AudioRecord`; foreground service with persistent notification; wake lock during lesson |
    | Features | 64-bin log-mel, 25 ms window, 10 ms hop; per-utterance CMVN replaced by running normalisation (streaming) |
    | Segmentation model | Input: 1.0–2.0 s context windows, stride 0.5 s; output: per-10 ms-frame 5-class posteriors; smoothing via median filter or small HMM |
    | LID model | Runs on TEACHER segments ≥ 2 s; 3 s windows; outputs {amh, sid, eng, oth}; segment label = mean posterior argmax with "oth" threshold |
    | Indicator engine | Runs incrementally; final values at lesson end |
    | Storage | Encrypted SQLite (SQLCipher or Jetpack Security + Room) with keys in Android Keystore |
    | Sync | WorkManager periodic job, network-constrained; exponential backoff; resumable; server returns per-record ACKs |
    
    **Performance budget (target device, 40-min lesson):**
    - RTF ≤ 0.3 total (segmentation + LID + indicators).
    - Peak RSS ≤ 300 MB.
    - Battery ≤ 10% per 40-min lesson [measure].
    - Models: segmentation ≤ 8 MB, LID ≤ 8 MB (INT8).
    
    ---
    
    ## 9. ML design
    
    ### 9.1 Tasks
    - **T-1 Segmentation:** 5-class frame labelling {TEACHER, CHILD_SINGLE, CHILD_CHORAL, OVERLAP, NON_SPEECH}.
    - **T-2 Language ID:** 4-class segment labelling {amh, sid, eng, oth} over teacher speech.
    
    ### 9.2 Data sources
    
    | Source | Content | Use | Location |
    |---|---|---|---|
    | D-1 Classroom Research Mode recordings | 20–30 lessons × ~40 min, Amharic + Sidaamu Afoo schools | Fine-tune teacher model, train student, **evaluate** | In-country research store only |
    | D-2 Annotated subset of D-1 | 6–10 hours, frame labels + language labels | Supervised training + test sets | In-country |
    | D-3 Public Amharic speech (e.g., ALFFA Amharic, FLEURS Amharic) [VERIFY licences & availability] | Adult read/spontaneous speech | LID training, encoder adaptation | Huawei Cloud OBS OK (public) |
    | D-4 Sidaamu Afoo speech | Scarce publicly [VERIFY]; likely self-collected (radio with permission, consented adult volunteers) | LID training | In-country unless public-licensed |
    | D-5 Public noise / reverberation / babble corpora (e.g., MUSAN-style, RIR sets) [VERIFY licences] | Augmentation | Anywhere |
    | D-6 Synthetic classroom mixtures | Adult speech + child-babble + RIR + choral simulation (time-aligned copies with jitter/pitch shift) | Pre-training the student before real data arrives | Anywhere (built from public data) |
    <?xml version="1.0" encoding="utf-8"?><!--
       Sample backup rules file; uncomment and customize as necessary.
       See https://developer.android.com/guide/topics/data/autobackup
       for details.
       Note: This file is ignored for devices older than API 31
       See https://developer.android.com/about/versions/12/backup-restore
    -->
    <full-backup-content>
        <!--
       <include domain="sharedpref" path="."/>
       <exclude domain="sharedpref" path="device.xml"/>
    -->
    </full-backup-content><?xml version="1.0" encoding="utf-8"?><!--
       Sample data extraction rules file; uncomment and customize as necessary.
       See https://developer.android.com/about/versions/12/backup-restore#xml-changes
       for details.
    -->
    <data-extraction-rules>
        <cloud-backup>
            <!-- TODO: Use <include> and <exclude> to control what is backed up.
            <include .../>
            <exclude .../>
            -->
        </cloud-backup>
        <!--
        <device-transfer>
            <include .../>
            <exclude .../>
        </device-transfer>
        -->
    </data-extraction-rules># In-country hosting options — Article 22 compliance
    
    Checked 2026-09-26, prompted by a review of Ethio Telecom's shared-hosting pricing (`myportal.ethiotelecom.et`) against the uploaded copy of Proclamation No. 1321/2016 E.C. (= 1321/2024 G.C.), Article 22 ("Data Sovereignty"): *"Every data controller or data processor shall ensure the storage, on a server or data center located in Ethiopia, of personal data collected or obtained locally."* This matches the Negarit Gazeta text already used in [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) §3 (Art. 22). A copy of the Proclamation is kept at [`../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf`](../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf).
    
    This answers a narrower question than the rest of the pack: **where can the `infra/openeuler` sovereign-tier server (§8.1, §12) actually be hosted?**
    
    ## The screenshot in question doesn't fit the job
    
    The Ethio Telecom pricing shown (Linux Bronze/Silver/Gold/Platinum, Plesk, ETB 650–5,200/year) is **shared web hosting** — a PHP/MySQL site host with a control panel, not a VPS or bare-metal server with root access. Dimts' sovereign tier needs to run a custom FastAPI app and openGauss/PostgreSQL, which shared Plesk hosting cannot do. Even if Ethio Telecom's data centers are in Ethiopia (they are — see below), this specific product is the wrong tier.
    
    ## Update 2026-09-26: Zergaw's own shared-hosting tier has the same problem
    
    The user found Zergaw's shared-hosting page (`zergaw.com/shared-hosting-platform/`), which states **"Hosting Location: Ethiopia"** per plan — even more explicit Article 22 evidence than anything found earlier. But most of its tiers have the same fit problem as the Ethio Telecom screenshot:
    
    | Zergaw plan | Server | Root access | Price | Fits `infra/openeuler`? |
    |---|---|---|---|---|
    | Basic | "None (shared)" | No | ETB 5,481/**year** | No — shared hosting, "Python support" is a WSGI/Passenger app slot, not a machine you control |
    | Standard | "None (shared)" | No | ETB 7,035/year | No |
    | Premium | "None (shared)" | No | ETB 10,139/year | No |
    | **Dedicated** | 2 CPU, 4 GB RAM, dedicated IP | **Yes** | ETB 5,646.55/**month** (≈ ETB 67,760/year) | **Yes** — this is the one that can install openGauss and run FastAPI, WorkManager ingest, and cron jobs as your own daemons |
    
    The "Dedicated" row is a materially different product from the shared tiers above it, despite being listed on the same pricing page. It overlaps in spec (2 vCPU/4 GB) with a similarly-priced entry on Zergaw's separate Cloud Server (VPS) page (`CS02004`, ETB 5,205/month, quoted in the table below) — confirm with Zergaw sales whether "Dedicated" (shared-hosting page) and the `CS02004` cloud server (VPS page) are the same underlying product, and whether either lets you provision an openEuler image rather than their default OS template.
    
    **Rule of thumb going forward:** on any Ethiopian host's pricing page, "shared hosting" / "Server: none" / a Plesk-or-cPanel-style feature list (subdomains, email accounts, "N databases") means no root and no ability to install openGauss or run your own background services, whatever the location says. Only a line item that names CPU/RAM and says **root/dedicated IP** (VPS, cloud server, dedicated server, bare metal) is a candidate for `infra/openeuler`.
    
    ## Providers that offer rentable infrastructure in Ethiopia
    
    | Provider | Confirmed location | Offering | Fits the sovereign-tier server? | Price |
    |---|---|---|---|---|
    | **INSA G-Cloud** — `cloud.gov.et` | ICT Park, Addis Ababa. Ethiopia's National Data Center, run by the Information Network Security Administration. | IaaS (Elastic Cloud Servers, Bare Metal Servers, VPC, storage), PaaS (container service, **managed MySQL/PostgreSQL/document DB**), SaaS (email, workspace), backup/DR, firewalls, vulnerability scanning. | Best compliance story: a government-run sovereign cloud built explicitly around "local data sovereignty." Targets government, finance, telecom and enterprise; doesn't explicitly exclude a university research project. | Not published; request a quote. |
    | **Ethio Telecom teleCloud** — `telecloud.ethiotelecom.et` | Ethio Telecom's own data centers, built on **Huawei Cloud Stack** as Ethiopia's national sovereign cloud (Ethio Telecom, 2026; Huawei Cloud case study). | IaaS/PaaS/SaaS; billing via telebirr. | Same Huawei stack as MindSpore/ModelArts — a clean story for the competition pitch. **Site returned no response from this session** (network policy); verify pricing and Postgres/DB offerings directly. | Not confirmed here. |
    | **Zergaw Cloud** — `zergaw.com` | HQ and infrastructure at ICT Park, Addis Ababa. Founded 2019; states government institutions among its ~500 clients. | Cloud servers and bare-metal servers (9 tiers, 1–32 vCPU / 2–64 GB RAM), shared hosting, and a **Database-as-a-Service** for PostgreSQL/MySQL/MariaDB marketed as *"Quality and secured databases at local host under Ethiopian legal framework."* | Closest self-service match: rentable VPS with root, or a managed Postgres instance, in ETB, from a company already positioning itself around local legal compliance. | Cloud servers: **ETB 3,272/month** (1 vCPU, 2 GB, 30 GB) up to **ETB 60,573/month** (32 vCPU, 64 GB). DBaaS pricing not published on the page fetched. |
    | **Raxio Ethiopia (ET1)** | ICT Park, Addis Ababa; Tier III certified (Uptime Institute); 800 racks, 3 MW IT power. | **Colocation only** — cross-connect, fibre, carrier-neutral connectivity. You supply and manage the physical server. | Only relevant if the university wants to rack its own hardware in a proper facility instead of hosting on campus. Not a managed-hosting or VPS product. | Colocation pricing not published; comparable retail colocation elsewhere in Addis runs USD 380–650/kW/month (Safaricom Ethiopia, per UT Solutions' 2026 guide). |
    | **UT Solutions PLC** | Addis Ababa HQ; builds and operates data centers (Tier III/IV), including a private edge deployment inside Hawassa Industrial Park for one industrial tenant. | Data-center design, build and managed operations — not a rentable public cloud. | Not a fit as a hosting product. Worth knowing that Hawassa-area data-center-grade infrastructure has been built before, if the university ever wanted a custom on-site facility. | N/A |
    
    ## What this doesn't resolve
    
    - **Article 33 registration.** None of these providers' marketing pages mention helping customers register as a data controller/processor with the Ethiopian Communications Authority. That's still the team's responsibility regardless of host (see `CLAIMS_AUDIT.md` §3, C-7f; `RESEARCH_PLAN.md` WS-F Q1).
    - **Whether renting from a private Ethiopian cloud (Zergaw, teleCloud) satisfies Art. 22** as fully as the government's own G-Cloud. The article only requires the server/data center be *located in Ethiopia* — it doesn't require a government operator — but confirm this reading with the legal advisor alongside the other WS-F questions.
    - **Ethio Telecom and HostHabesha sites were unreachable from this session** (network policy), so their VPS/teleCloud pricing and specs are unverified here. Fetch them directly.
    - **Actual database compatibility.** Zergaw's DBaaS lists PostgreSQL/MySQL/MariaDB, not openGauss by name. openGauss is PostgreSQL-lineage (per `CLAIMS_AUDIT.md` §4, 7j), so a managed *PostgreSQL* instance is a reasonable substitute if openGauss itself isn't offered — this feeds back into ADR-005 (backend/database choice).
    
    ## Recommendation
    
    For a **budget self-service VPS or managed Postgres with an explicit local-law framing**, contact **Zergaw** first, specifically about their **Dedicated** server or `CS02004` cloud server (both root, 2 vCPU/4 GB) — not the Basic/Standard/Premium shared tiers, which don't give you a machine to install anything on regardless of their stated location. For the strongest **compliance and pitch narrative** ("hosted on Ethiopia's own national data center"), get a quote from **INSA's G-Cloud**. Check **Ethio Telecom teleCloud** too, given the Huawei Cloud Stack overlap with the rest of the competition build. Treat Raxio and UT Solutions as colocation/build options only, not as what you want for W0–W1's spike S6.
    ✓ • 0ms

## Activity

    $ cat > /tmp/revise_echo_plans.py <<'PY'
        from pathlib import Path
        root=Path('/Users/anteneh/Documents/Repos/Echo')
        def edit(path, pairs):
         p=root/path; s=p.read_text()
         for old,new in pairs:
          if old not in s: raise ValueError(f'Missing replacement in {path}: {old[:90]}')
          s=s.replace(old,new)
         p.write_text(s)
        def section(s,start,end,new):
         a=s.index(start); b=s.index(end,a); return s[:a]+new+'\n\n'+s[b:]
        p=root/'docs/DIMTS_ARCHITECTURE_CONTEXT.md'; s=p.read_text()
        s=s.replace('**Status:** Pre-architecture · Last updated 2026-09-25','**Status:** Reviewed proposal; implementation gates open · Last updated 2026-09-27')
        s=s.replace('5. Anything marked **[DECISION]** is open and needs an ADR.','5. Anything marked **[DECISION]** is open and needs an ADR.\n6. Read [`research/IMPLEMENTATION_REVIEW.md`](research/IMPLEMENTATION_REVIEW.md) for the verified repository baseline, unresolved contracts and ordered implementation gates. The Android app is a starter; other component directories are placeholders. No runtime feasibility is established. The review governs unresolved details; [`research/RESEARCH_PLAN.md`](research/RESEARCH_PLAN.md) owns the schedule and [`research/CODEBASE_STRUCTURE.md`](research/CODEBASE_STRUCTURE.md) owns the detailed proposed layout.')
        s=s.replace('Raw audio never leaves the phone.', 'Deployment Mode never persists or uploads raw audio; separately consented Research Mode transfers encrypted audio only to the in-country research store.')
        s=s.replace('The system is language-agnostic by design because it never touches text, and it is validated on two orthographically distinct languages: Amharic (Ge\'ez abugida) and Sidaamu Afoo (Latin script).','The segmentation design avoids transcription; its cross-language robustness remains to be evaluated on Amharic and Sidaamu Afoo. LID is language-dependent. Script differences do not themselves establish acoustic generalisation.')
        s=s.replace('34.2% (2016)', '34.3% (2016)').replace('### 2.2 Teacher capability is the binding constraint','### 2.2 Teacher capability is a significant, addressable constraint')
        s=s.replace('and they receive **no feedback loop** on their actual classroom practice.', 'while existing classroom observation is uneven; the proposed contribution is frequent, private feedback. The cited English-teacher study covers 30 teachers in five schools and is not a national estimate (see the claims audit).')
        s=s.replace('**No one currently measures, at scale, which language is actually spoken in the classroom.** Dimts\'s language-ratio indicator is a direct, novel policy measurement.','The reviewed studies do not establish continuous automated measurement of classroom language. I-6 is a proposed descriptive measure; any claim to be the first requires the RQ-C1 search, and language shares alone do not establish policy compliance.')
        s=section(s,'### 2.6 Why no ASR','---\n\n## 3.', '''### 2.6 Why no ASR
        
        No ASR is a deliberate scope, compute and data-minimisation decision. Public Amharic and Sidaamu Afoo resources exist (see the research audit); their existence does not resolve classroom acoustics or short-segment LID. Indicators remain acoustic and must be validated independently of transcription.''')
        s=s.replace('"Try waiting 3 seconds after asking a question"','"Review how individual and choral responses were balanced"')
        s=section(s,'All indicators are computed from a **segment timeline**:', '---\n\n## 6.', '''All indicators derive from an ordered, non-overlapping timeline with integer 10 ms ticks and half-open intervals. Acoustic labels are `{TEACHER, CHILD_SINGLE, CHILD_CHORAL, OVERLAP, NON_SPEECH}`. `MISSING` is capture/pipeline metadata, never a sixth learned acoustic class. Every elapsed tick must be observed or missing. Language decisions use `{amh, sid, eng, oth, unknown}`; `unknown` means insufficient evidence, not another language.
        
        The following are proposed v2 semantics, to freeze with golden fixtures at gate G1 in the implementation review:
        
        - Missing capture, queue overflow, permission loss and interruption split observed spans. Never smooth, bridge, count a turn or infer silence across them. Report elapsed, observed and missing duration separately.
        - Smoothing parameters (minimum segment 0.3 s, same-label gap bridging below 0.25 s) remain candidates. Preserve original timing for latency and silence: bridged gaps must not become speech duration. Specify tie-breaking, operation order, edge handling and bounded lookahead before implementation.
        - LID initially consumes complete 3 s teacher-only windows. Short/uncertain speech stays `unknown`; do not inherit neighbouring language. E3 may revise the threshold with a spec/model version change. Preserve within-turn code switches at window resolution.
        
        | ID | Indicator | Proposed definition and missing-data behavior |
        |---|---|---|
        | I-1 | Teacher talk ratio | Exclusive TEACHER duration / observed speech duration (TEACHER + CHILD_* + OVERLAP). Also report / observed lesson duration and overlap share; overlap is not attributed to the teacher. Zero denominator → null. |
        | I-2 | Response latency | Adjacent TEACHER → CHILD_* speech turns separated only by 0–10 s observed NON_SPEECH; OVERLAP or MISSING breaks eligibility. Median, P25/P75 and n, separately for single and choral. No events → null quantiles, n=0. This is not question-based wait time and must not trigger a 3 s coaching rule. |
        | I-3 | Choral balance | CHILD_CHORAL / CHILD_* duration, plus separately defined qualifying-segment count ratio. Zero denominator → null. Neither direction is inherently better. |
        | I-4 | Individual responses | Count CHILD_SINGLE segments ≥ 0.5 s, plus count / observed minutes × 10. Null rate when no observed time. |
        | I-5 | Turn density | TEACHER ↔ CHILD_* alternations within uninterrupted observed spans; intervening NON_SPEECH allowed, OVERLAP breaks the pair. Divide by observed minutes. |
        | I-6 | Language shares | Duration by language / all exclusive TEACHER duration, including `unknown`; also report classified coverage. No teacher speech → null shares. Shares sum to one when defined. |
        | I-7 | Interaction coverage | Fixed lesson-relative 60 s bins; only wholly observed bins eligible (including a wholly observed final partial bin). Share with ≥1 eligible alternation, assigned at the destination turn start; report eligible-bin count. No eligible bins → null. |
        | I-8 | Long silence | Count original contiguous observed NON_SPEECH runs ≥60 s; MISSING splits runs. Interpret neutrally. |
        
        `indicator_spec_version`, feature/postprocess versions and model versions travel together. Python is the reference and Kotlin the port: compare parsed outputs, exact categorical/integer/null fields and absolute float tolerance 1e-6, not JSON bytes. Freeze quantile interpolation and rounding rules at G1. Low-coverage records can be retained with quality flags but cannot produce coaching or district aggregates until eligibility thresholds are fixed. No coaching is enabled before indicator validity and teacher co-design gates pass. At most one focus is shown each week.''')
        s=s.replace('All inference on CPU, INT8.', 'All inference on CPU; INT8 convolution-only candidate preferred, FP16/FP32 recurrent candidate permitted only if measured budgets pass.')
        s=s.replace('School + parent notification per ethics approval.', 'Ethics-approved consent/assent and school permissions before any classroom capture, including transient processing; legal questions remain in WS-F.')
        s=s.replace('Huawei-native wherever it does not break the device or legal constraints; documented escape hatches where it does.', 'Research audit reports Huawei Cloud deployment mandatory for the competition build. Use a separate synthetic-only demo; confirm exact regional requirements. Prefer Huawei components where tested; preserve documented fallbacks.')
        s=s.replace('**MindAudio** (mindspore-lab)', '**Owned feature extraction** (Python + Kotlin)').replace('Feature extraction (fbank), augmentation, ECAPA-TDNN reference', 'Versioned log-mel extraction with streaming parity fixtures').replace('Maturity is limited (last tagged release 0.1.x) [VERIFY]. Use for features; do not depend on it for novel models.', 'Do not depend on MindAudio maintenance; research audit recommends owned feature code.')
        s=s.replace('**MindNLP** (HuggingFace-compatible, PyTorch-API proxy)', '**PyTorch + Hugging Face**').replace('Community reports Ascend-specific errors with some wav2vec2 variants [VERIFY on your hardware early — week 1 spike].', 'Proposed replacement for MindNLP per audit; verify model licence and target training hardware in S4.')
        s=s.replace('distillation runs on de-identified targets', 'public-data-only experiments').replace('Nearest regions: Johannesburg, Cairo, Lagos AZ. **No Ethiopia region** [VERIFY]. Do not upload raw classroom audio here without an ADR-003 decision.', 'AF-Johannesburg is a candidate, subject to S5 service availability. No pilot audio, posteriors, features, logs or classroom-trained weights may flow to this tier under the proposed boundary.')
        s=section(s,'### 7.3 What to say in Q&A','### 7.4 HarmonyOS position', '''### 7.3 What to say in Q&A
        Describe demonstrated components only. Proposed: PyTorch/HF teacher, MindSpore student where hardware supports it, Lite CPU inference. The competition demo uses synthetic records on Huawei Cloud; the pilot uses a separate in-country deployment. Model portability, runtime precision and cross-language accuracy are experiments, not completed claims.''')
        s=s.replace('│ model artefacts only (no personal data)', '│ NO pilot → cloud export; approved cloud → pilot models only')
        s=s.replace('server-side "teacher model")', 'server-side "teacher model", PyTorch/HF)')
        s=s.replace('This is how a 2 GB phone gets near-large-model accuracy.', 'Whether this reaches useful accuracy on a 2 GB phone is tested in E6.')
        s=s.replace('Deletion is a tombstone record.', 'Withdrawal is a separate revocation and purge workflow that takes precedence over queued uploads (§13.1).')
        s=s.replace('Runs on TEACHER segments ≥ 2 s; 3 s windows; outputs {amh, sid, eng, oth}; segment label = mean posterior argmax with "oth" threshold', 'Complete 3 s teacher-only windows; {amh, sid, eng, oth} plus calibrated abstention to unknown; no short-segment inheritance')
        s=s.replace('Encrypted SQLite (SQLCipher or Jetpack Security + Room) with keys in Android Keystore', 'Room + SQLCipher with a random database key wrapped by Android Keystore; backup, restore and key-loss policy required before real data')
        s=s.replace('Scarce publicly [VERIFY]; likely self-collected (radio with permission, consented adult volunteers)', 'Afrivoice/WAXAL/MMS resources reported by audit; verify speaker overlap, licences and classroom-domain suitability')
        s=s.replace('Huawei Cloud OBS OK (public)', 'Cloud only after licence and privacy review').replace('In-country unless public-licensed','In-country unless approved for the cloud data register').replace('Anywhere (built from public data)', 'Cloud only for approved inputs; mixing public voices is not anonymisation')
        s=s.replace('loaded via MindNLP','loaded via PyTorch/Hugging Face')
        s=s.replace('Both paths must be scripted and tested in CI from week 3.', 'S3 compares each candidate to its own source model on identical features; require cross-framework equality only for explicitly weight-equivalent graphs. Quantization is assessed separately by task and indicator degradation. Run public-fixture checks in CI; real-data calibration/evaluation runs only in-country.')
        s=s.replace('// LessonRecord v1 — the ONLY payload that syncs in Deployment Mode', '// LessonRecord v2 — illustrative subset; freeze complete schema at G1 before coding').replace('"schema_version": 1','"schema_version": 2').replace('"indicator_spec_version": 1','"indicator_spec_version": 2')
        s=s.replace('"duration_s": 2400,', '"duration_s": 2400,\n  "capture": { "observed_s": 2400, "missing_s": 0, "mic": "builtin" },\n  "processing_versions": { "features": "candidate-v1", "postprocess": "candidate-v2" },')
        s=s.replace('"I2_response_latency_s": { "median": 0.6, "p25": 0.3, "p75": 1.1, "n": 42 }', '"I2_response_latency_s": { "single": { "median": 0.6, "p25": 0.3, "p75": 1.1, "n": 12 }, "choral": { "median": 0.2, "p25": 0.1, "p75": 0.3, "n": 30 } }')
        s=s.replace('"eng": 0.05, "oth": 0.02','"eng": 0.05, "oth": 0.01, "unknown": 0.01')
        s=s.replace('Huawei Cloud holds public data and model artefacts only.', 'Huawei Cloud demo holds synthetic records; any public speech/model asset requires separate licence and privacy approval. No pilot-derived export, including weights or soft labels.')
        s=s.replace('k-anonymity: suppress any aggregate cell with < 5 teachers. No drill-down to school when school has < 5 participating teachers.', 'Minimum group size 5 distinct teachers, not lessons; fixed cohorts, equal teacher weighting, no arbitrary filters, and complementary suppression/differencing tests before district release. This alone is not proof of anonymity.')
        s=s.replace('Parent/school notification per ethics board.', 'Child consent/assent and school permissions per legal/ethics review; no classroom capture before approval. Offline withdrawal stops capture and discards queued lessons; server revocation rejects stale retries.')
        s=s.replace('Device: Keystore-backed encryption.', 'Device: SQLCipher + Keystore; exclude research files, databases, identifiers and credentials from cloud backup/device transfer; test API 29 and API 31+ rules.')
        s=s.replace('│   ├── ARCHITECTURE_CONTEXT.md', '│   ├── DIMTS_ARCHITECTURE_CONTEXT.md').replace('log-mel, augmentation (MindAudio + custom)', 'owned log-mel, augmentation, Python/Kotlin parity').replace('SSL encoder fine-tuning (MindSpore + MindNLP)', 'SSL encoder fine-tuning (PyTorch/HF, in-country for pilot data)')
        s=s.replace('│   ├── openeuler/                     ← host provisioning, container compose, backups', '│   ├── huawei-cloud/                  ← synthetic-only competition demo, separate credentials\n│   ├── openeuler/                     ← in-country pilot host, containers and backups')
        s=s.replace('- Auth: device-bound token issued at enrolment; rotate on reinstall.', '''- Auth: device-bound token issued at enrolment; server derives teacher/school scope from the authenticated enrolment and rejects mismatched payload IDs. Reinstall needs re-enrolment/recovery; tokens are revocable.
        - A transaction persists each valid lesson before ACK. Same owner + record ID + same canonical payload is duplicate; changed payload is conflict, never success. Mixed batches return per-record results; retry only retryable failures.
        - Withdrawal revokes every enrolled device, blocks stale uploads, purges records and derived reports/aggregates, and specifies backup expiry/restore deletion replay. Restrict any retained audit metadata and set its retention in WS-F.
        - Report reads/deletes must enforce ownership server-side; query parameters are never authority. Set request limits and supported schema/spec versions at G1.''')
        s=s.replace('Each model ships with `model_card.json`: version, training data summary, eval metrics, calibration set hash, feature config hash. The app refuses a model whose feature config hash does not match the pipeline.', 'Each model ships with a manifest: hashes, runtime/converter versions, feature and postprocess contracts, input/output shape and class order, context/stride/lookahead and state reset, precision by layer, upstream licences, split/calibration provenance and eval metrics. Reject incompatible bundles atomically; use the last compatible bundle or stop processing. Overlapping windows emit each tick exactly once. MISSING resets streaming state. Feature parity must cover chunk boundaries, resampling, FFT/mel conventions and normalisation. Unknown LID is postprocessing, not a fifth output class.')
        s=s.replace('Target (thesis)', 'Provisional target (thesis)').replace('split by teacher/school)', 'split by teacher/school; frozen before training)')
        s=s.replace('### 14.2 Indicator validity (the metric that matters)', '### 14.2 Indicator validity (the metric that matters)\nAll targets are prospective. Tune thresholds and calibration on development data only; never use the locked test set for distillation, PTQ calibration or threshold selection. Report uncertainty and denominators, including unknown LID and missing capture.')
        s=s.replace('RTF ≤ 0.3; peak RSS', 'End-to-end RTF ≤ 0.3 including capture/features/LID/storage; bounded backlog and measured emission latency; peak RSS')
        s=s.replace('Sync: 100% of records eventually delivered', 'Sync: 100% of valid, non-withdrawn records eventually delivered')
        s=section(s,'## 16. Timeline (from 2026-09-25)', '---\n\n## 17.', '''## 16. Timeline and gates
        
        Use [`research/RESEARCH_PLAN.md`](research/RESEARCH_PLAN.md) §5 for the single schedule and [`research/IMPLEMENTATION_REVIEW.md`](research/IMPLEMENTATION_REVIEW.md) for G0–G5 acceptance gates. Dates are targets, not evidence of approval or completion. No classroom recording, even a microphone test, precedes ethics and required consent. Before that, use authorised adult/staged fixtures.
        
        Critical paths run separately: synthetic demo → competition submission; ethics + consent → classroom collection → annotation → held-out validation → private pilot reports. Delayed ethics must not block the synthetic demo, and demo success must not be presented as classroom validity.''')
        s=s.replace('Innovation Competition judging criteria (per Huawei\'s published description of prior editions): **creativity, system complexity, technical complexity, societal benefit, functionality.** Presentation format at global level: 15 min presentation + 5 min Q&A, in English; PPT, video, or physical demo. AI use is mandatory. [VERIFY for 2026–2027 rules]', 'The research audit reports 2026–27 Prelim/National weighting of Innovation 60 / Application value 40, mandatory Huawei Cloud deployment and a visual demo page, plus open-source/reproduction requirements at later stages. Reconfirm portal rules and Ethiopia division before submission. The table below organises evidence, not current scoring categories.')
        s=s.replace('First measurement of mother-tongue policy compliance in the room.', 'Proposed continuous acoustic language-share measurement; novelty and usefulness remain to be established.').replace('No one has given teachers a mirror.', 'Teachers need frequent, private feedback.')
        s=s.replace('A 20-second classroom clip.', 'An approved synthetic/staged 20-second clip.').replace('show dashboard update.', 'show the synthetic cloud dashboard update.')
        s=s.replace('W0 test recording.', 'Ethics-gated classroom test; authorised staged audio before approval.').replace('MindSpore/MindNLP bug blocks teacher model', 'Training/export hardware or framework incompatibility').replace('accept 3-class (amh / non-amh / eng) v0.', 'use explicit unknown labels; any reduced label set needs a new model/spec version.').replace('fall back to 4-class if needed and report.', 'fall back to CHILD_UNSPECIFIED with a new schema/model contract; I-3/I-4 and response-type I-2 become unavailable, not zero.')
        s=s.replace('| ADR-002 | Student architecture | Small CRNN first; compact conformer if budget allows |','| ADR-002 | Student architecture | E5: conv-only INT8 versus mixed-precision CRNN; defer conformer unless needed |')
        s=s.replace('no raw classroom audio to cloud without explicit consent + legal sign-off', 'no pilot-derived export under this proposal; any boundary change needs a separate reviewed ADR')
        s=s.replace('ADR template: `Context', '| ADR-011 | Separate competition/pilot deployments | Separate credentials, endpoints, databases, logs and backups; synthetic-only demo |\n| ADR-012 | Timeline, missingness, unknown LID and schema v2 | Freeze §5 and G1 golden fixtures before implementation |\n| ADR-013 | Auth, withdrawal, retention and aggregate disclosure | G4/G5 negative tests and legal/ethics answers |\n\nADR template: `Context')
        p.write_text(s)
        PY
        python3 /tmp/revise_echo_plans.py
    ✓ • 0ms

## Assistant

The main architecture now separates proposed behavior from verified capability. I’m tightening the acceptance criteria: missing audio must not count as silence, uncertain language must remain unknown, withdrawn data must not return through queued uploads, and a working synthetic demo must not be treated as classroom validation. I also confirmed Android backup is a gap to address before collecting real data.

## Activity

    $ cat > docs/research/IMPLEMENTATION_REVIEW.md <<'EOF'
        # Architecture and implementation-plan review
        
        Reviewed 2026-09-27. **Planning changes only; no implementation, builds, device benchmarks or deployment performed.**
        
        The edge / in-country pilot / separate Huawei Cloud demo architecture is a reasonable proposal. It is not ready for unrestricted implementation: the indicator and streaming contracts need to be frozen, device feasibility remains unmeasured, and real-data collection/release has unresolved ethics and security gates. This review governs unresolved implementation details; the architecture owns product constraints, RESEARCH_PLAN owns dates, and CODEBASE_STRUCTURE owns the proposed layout. Prior audits remain dated evidence, not proof that a capability was built.
        
        ## Verified repository baseline
        
        - `edge/android/settings.gradle.kts` includes only `:app`. Other Android folders contain `.gitkeep`, not Gradle modules.
        - `MainActivity.kt` is a Compose “Hello Android” starter. There is no capture, inference, indicator, storage, enrolment or sync implementation.
        - `app/build.gradle.kts` declares minSdk 29, compile/target SDK 37 and arm64-v8a. The version catalog declares AGP 9.4.1 and Kotlin 2.2.10. These are file contents, not a verified compatible build toolchain; G0 must resolve/build them before selecting upgrades or downgrades.
        - `AndroidManifest.xml` has `allowBackup=true`; the referenced backup/extraction XML contains template rules. Microphone permissions/service are absent. These are planned work, not regressions in an existing audio app.
        - ML, indicator, server, dashboard and infrastructure trees are placeholders. No executable tests beyond Android template tests establish product behavior. `docs/adr/` contains no ratified decisions.
        - Research PDFs are already tracked. Adding ignore rules later will not remove tracked files or history; a future public-release audit must cover the actual published tree and history. Do not delete research material as part of this review.
        
        ## Findings and required plan changes
        
        | ID | Priority | Evidence / failure mode | Planned resolution |
        |---|---|---|---|
        | V1 | High | Main architecture used MindNLP/MindAudio, all-INT8 inference and one deployment while the research pack proposes replacements and two deployments. | Reconcile §7–§12; PyTorch/HF teacher, owned features, measured precision variants, isolated cloud demo. These remain candidates until S1–S6 pass. |
        | V2 | High | Missing spans, bridged silence, zero denominators and short-segment LID inheritance could fabricate or bias indicators. | §5 v2: explicit observed/missing time, original timing for latency, null availability, unknown LID, deterministic postprocessing. Freeze all fixture outcomes before coding. |
        | V3 | High | 1–2 s windows every 0.5 s duplicate predictions unless emission ownership is defined; smoothing needs future context. | Specify each output tick's owner, bounded lookahead/finalisation, ring/queue sizes, end flush and interruption resets. Report both throughput and emission latency. |
        | V4 | High | Cloud boundary assumed public/synthetic audio and model weights were automatically non-personal; diagram implied pilot export. | Synthetic record demo by default. Public voices, derived mixtures, weights and logs need provenance/privacy/licence review. No pilot audio, features, soft labels, weights or identifiers flow to cloud. Separate endpoints, identities, credentials, logs and backups. |
        | V5 | High | Encryption alone does not stop OS backup; starter allows backup. | Plan exclusions for sensitive files in both legacy and API 31+ cloud/transfer rules. Test backup/restore, key invalidation, reinstall and shared-phone access before real data. |
        | V6 | High | UUID idempotency alone does not address payload conflicts, forged teacher IDs or withdrawal races. | Ownership derived server-side, transaction-before-ACK, identical retries only, revocation precedence, deletion of derived data and backup restore replay. |
        | V7 | High | k=5 alone does not prevent differencing or identify the intended weighting. | Count distinct teachers, aggregate each teacher first, fixed cohorts and windows, complementary suppression, no flexible drill-down; test repeated releases. Call it minimum-group suppression until disclosure review supports stronger claims. |
        | V8 | High | E1 was scheduled before ethics; E3 expects pilot data before collection; E2 asks teacher-independent evidence from 1–2 lessons. | Staged/adult fixtures before ethics; initial lessons are feasibility only. Real validation follows consent, collection and independent splits; dates move if gates slip. |
        | V9 | Medium | S3 demanded equality between differently trained graphs and quantized outputs. | Compare each export to its own source FP baseline; cross-framework tolerance only for equivalent weights/graphs. Set quantized task/indicator loss budgets separately. |
        | V10 | Medium | 16 KB check covered ELF load alignment only and treated sideloading as a fix. | Inspect all native dependencies (Lite, SQLCipher, fallback runtime), APK packaging/ZIP and runtime behavior. Sideloading is acceptable only on a tested compatible device; it cannot repair native incompatibility. |
        | V11 | Medium | Hosting alternatives and database engines were treated as interchangeable. | Choose one initial engine and test its exact driver/migrations/auth/TLS/backup/restore. PostgreSQL lineage is not compatibility evidence; choosing GaussDB adds its own spike. Host location includes backup/log processing, not just a marketing page. |
        | V12 | Medium | “Never measured”, “validated”, all-MindSpore and “language-agnostic” claims exceeded evidence. | Mark novelty, portability, performance and classroom utility as hypotheses. Report domain/language splits and abstention coverage; avoid policy-compliance or causal coaching claims. |
        
        ## Ordered implementation gates
        
        These are future acceptance criteria, not tasks executed during this review. Gates can overlap only when their prerequisites permit it.
        
        | Gate | Owner | Required evidence before proceeding |
        |---|---|---|
        | G0 — baseline and feasibility | Sys + ML | Reproducible clean Android starter build with pinned toolchain; measured target phone/OS/page size; S1 Lite invocation; S4 training hardware; S6 exact DB driver/migration path. Decide failures through ADRs instead of silently swapping stacks. |
        | G1 — contracts | Lead + Sys + ML | ADR-001/002/004/005/011/012 candidate decisions; full schema v2 and model manifest; exact timeline/postprocess/quantile semantics; proposed Python/Kotlin golden cases reviewed with expected results; feature config and chunk ownership frozen. ADR-002 remains provisional until E5. |
        | G2 — offline vertical slice | Sys + ML | Authorised synthetic/staged PCM → features → candidate inference → indicators → encrypted lesson record → local report on target phone. Bounded queues and a 40 min soak; process death produces an explicit interrupted session with no invented unobserved audio. No cloud or real classroom data prerequisite. |
        | G3 — reproducible competition demo | Sys + Lead | Synthetic fixtures → authenticated ingest → one dashboard/report view on isolated Huawei Cloud deployment; licence manifest, seeded generator, documented model versus fixture outputs and independent reproduction. Public-audio playback is optional and separately reviewed. |
        | G4 — real-data readiness | Lead + Sys | Ethics/consent/legal answers, confirmed in-country processing/storage/backups, mode separation, backup exclusions, shared-device lock/logout, Research Mode encrypted transfer verification, revocation/purge tests. Classroom collection cannot precede this gate. |
        | G5 — validity and controlled release | Lead + ML + Sys | Teacher/school-independent frozen real test set, annotation agreement, indicator error with intervals/coverage, quantized-device equivalence, coaching co-design, aggregate disclosure tests. Release only indicators that pass; invalid/unavailable outputs are null with reasons. |
        
        ## Contract checklist for G1
        
        - Fixture cases: empty/zero-duration input, all silence, all missing, all overlap, interrupted teacher/child transition, 0.25/0.3/0.5/10/60 s boundaries, smoothing ties, final partial minute, mixed-language turn, LID abstention, dropped chunks and end flush. Validate interval ordering, complete coverage and finite values.
        - Rates use observed time; report elapsed time separately. Specify count-ratio qualification and quantile interpolation explicitly. Reports must preserve missing/unknown coverage rather than treating it as zero activity.
        - Teacher role is an acoustic hypothesis: define other adults, playback speech, quiet children and teacher-plus-child overlap in the annotation guide. Pilot-gate each class. A merged CHILD_UNSPECIFIED fallback requires new class/spec versions and disables I-3/I-4 and response-type-specific latency.
        - `LessonRecord` includes schema/spec/model/feature/postprocess versions, immutable ID, consent/enrolment reference, elapsed/observed/missing time, indicator availability/reasons and denominators. No full timeline leaves Deployment Mode. Set strict field allowlists, ranges, size limits and supported versions; examples in the other plans are incomplete, not executable schemas.
        - Weekly offline reports need the same versioned deterministic rule table as server reports. Define week boundaries in Africa/Addis_Ababa, equal teacher weighting for district outputs, compatible-version grouping, minimum valid duration/lessons and when cached/server reports supersede local reports. G1 must fix thresholds; coaching stays disabled until G5.
        - Model manifest pins sample rate, resampler, frame centering/padding, FFT/window/mel/log conventions, streaming normalisation, tensor axes/dtypes/class order, quantization scales, receptive field/lookahead, chunk/state-reset behavior and hashes. Calibration and feature parity cover warm-up and chunk boundaries.
        - Partition real data before adaptation, pseudo-labelling, distillation, calibration or threshold tuning. Public corpus overlap also needs speaker/source checks. Small pilot splits may not support school-level inference: report that limit rather than claim independence.
        
        ## Verification to schedule
        
        - Sync/auth: other-teacher read/delete/write denial; wrong school; duplicate same bytes/semantics; conflicting same ID; lost ACK; partial batch; offline retry; expiry; withdrawal during upload; restore after deletion. Never log record bodies/tokens.
        - Capture/storage: permission denial/revocation, call/mic silencing, external-mic removal, background restrictions, screen lock, thermal throttling, low storage, crash/restart, key loss and backup/transfer. Test API 29 and a modern target-compatible device. Benchmark release builds with the whole pipeline.
        - Aggregates: 4 versus 5 distinct teachers, many lessons from one teacher, adjacent/overlapping cohorts, suppression at totals, withdrawal and repeated weekly releases. No district release if only six pilot teachers make useful safe grouping impossible; use synthetic demo aggregates.
        - Research transfer: separate consent and endpoint, ciphertext integrity and durable in-country receipt before local deletion; retained audio TTL/storage cap and failure/retry behavior. No Research Mode secrets or audio in deployment logs/CI.
        - Public release: dataset/model licensing includes derived student weights, attribution and redistribution; CC-BY-NC is not blanket competition permission. Re-run from licensed synthetic fixtures without pilot data. Audit tracked PDFs/history and secrets before publishing.
        
        ## Sources and limits
        
        Repository findings above were inspected directly. Existing research audit findings are carried forward with their original dates; this review did not revalidate every paper, competition rule, provider price or legal interpretation. No runtime/model accuracy claim is verified.
        
        Official Android documentation checked on 2026-09-27:
        
        - [Sharing audio input](https://developer.android.com/media/platform/sharing-audio-input): capture can be silenced and recording callbacks expose changes; plan missingness separately from acoustic silence.
        - [Auto Backup](https://developer.android.com/identity/data/autobackup): eligible app data is backed up by default; Android 12+ uses separate cloud-backup/device-transfer controls. Test explicit exclusions, including restore behavior.
        - [16 KB page sizes](https://developer.android.com/guide/practices/page-sizes): native libraries and APK packaging need checks and runtime testing. All bundled native dependencies are in scope, not just the inference AAR.
        
        Outstanding legal decisions stay in WS-F; outstanding current competition requirements stay in WS-G. Neither is resolved merely by choosing an architecture.
        EOF
        cat >> /tmp/revise_echo_plans.py <<'PY'
        edit('docs/research/README.md',[
         ('Finalised 2026-09-26 after reading the supplied full texts. No code exists yet, so "the codebase" here means the design in §7–§13 of that document.', 'Research evidence updated 2026-09-26; implementation-plan review updated 2026-09-27. A basic Android Compose starter exists; other components are placeholders. No runtime or product behavior has been validated.'),
         ('| File | What it is |', '| File | What it is |'),
         ('| [`CLAIMS_AUDIT.md`]', '| [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md) | Current repository baseline, architecture findings, contract checklist and implementation gates G0–G5. Read before implementing. |\n| [`CLAIMS_AUDIT.md`]')])
        edit('docs/research/RESEARCH_PLAN.md',[
         ('# Research plan — Dimts (final, v1.0)', '# Research and implementation plan — Dimts (reviewed proposal, v1.1)'),
         ('Status as of 2026-09-26.', 'Status as of 2026-09-27. Implementation must follow the ordered gates in [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md); none is recorded as passed.'),
         ('**Classroom language has never been measured systematically.**', '**Continuous automated classroom-language measurement remains a novelty hypothesis.**'),
         ('Automated I-6 is a defensible novelty claim.', 'Run C1 before making a first-of-kind claim; these studies alone cannot prove absence of other systems.'),
         ('W0–W1 test lessons.', 'W0–W1 authorised staged/adult fixtures; real classroom comparison only after G4.'),
         ('1–2 annotated lessons;', '1–2 annotated lessons for feasibility only; collect multiple teachers/schools before independent evaluation;'),
         ('| W3 |\n| E4', '| W3 public/staged baseline; classroom phase after G4 |\n| E4'),
         ('PyTorch→ONNX→`.ms` and MindSpore→MindIR→`.ms` outputs agree within 1e-4; full-quant PTQ runs; list the ops that fall back to FP32', 'Each FP export agrees with its own source graph on fixed features (initial tolerance 1e-4, justified per model); compare frameworks only for weight-equivalent graphs. Assess PTQ through task/indicator degradation separately; record actual precision/fallback ops'),
         ('Sideload for the pilot; handle 16 KB before any Play release.', 'Sideload only on tested compatible phones; test every native library plus ELF/ZIP packaging and 16 KB runtime before wider distribution.'),
         ('16 KB decision: sideload for the pilot; rebuild Lite before any Play release', '16 KB decision: test Lite, SQLCipher and any fallback native libraries, ELF/ZIP alignment and runtime; sideload only on verified compatible phones'),
         ('Get the official Gazette copy (TO_OBTAIN #2).', 'The supplied Gazette copy is in `docs/legal/`; use it with the legal advisor (see TO_OBTAIN #2).'),
         ('E1 test lessons; S1', 'E1 staged/adult fixtures only (classroom work waits for G4); S1'),
         ('Legal answers; ADR-002/003', 'Legal answers; ADR-002/003'),
         ('Weeks count from W0 = 2026-09-25.', 'Weeks count from W0 = 2026-09-25. Dates are targets: real-data phases of E1–E7 move with ethics/consent and G4; public/staged work and the synthetic demo continue independently.'),
         ('Reword the pitch to "every model on the phone is trained in MindSpore and runs on MindSpore Lite".', 'Describe only the training/runtime path actually demonstrated; MindSpore remains subject to S4 and export/device gates.'),
         ('Research and competition only; state it in the model cards.', 'Review exact upstream and derived-weight terms for competition use and redistribution; record attribution/restrictions and use approved alternatives if unresolved.'),
         ('There is no data path from pilot to cloud (ASSUMPTIONS A-C3).', 'There is no pilot-to-cloud path, including features, soft labels, weights, logs and backups. Public voices/mixtures need explicit approval; synthetic records are the demo default (ASSUMPTIONS A-C3).'),
         ('| S9 | SQLCipher + Keystore key |', '| S9 | SQLCipher + Keystore key; backup/transfer exclusions, key-loss/reinstall and shared-phone tests before G4 |'),
         ('| S5 | ModelArts, OBS and ECS', '| S5 | Confirm exact services before choosing a region; ModelArts, OBS and ECS'),
         ('OBS unencrypted and holding **public data only**', 'any required unencrypted training bucket restricted to approved corpus objects, never anonymous/public access; verify licence/privacy separately'),
         ('| **S10** (new)', '| S11 | Sync ownership, ACK/conflict/retry, withdrawal and restore-purge tests per G4; fixed-cohort disclosure tests before G5 | Before pilot/release |\n| **S10** (new)')])
        edit('docs/research/CODEBASE_STRUCTURE.md',[
         ('This is a proposal to ratify through ADRs, not scaffolding that has been built.', 'Reviewed 2026-09-27 against [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md). The folders are scaffolded; only `edge/android/app` is a starter Gradle module. All described product files and other modules remain proposed and require G0–G5.'),
         ('│   ├── ARCHITECTURE_CONTEXT.md', '│   ├── DIMTS_ARCHITECTURE_CONTEXT.md'),
         ('min-duration merge, gap bridging, LID inheritance', 'deterministic smoothing, timing preservation, unknown LID'),
         ('Room + SQLCipher; key wrapped by Android Keystore', 'Room + SQLCipher; Keystore-wrapped key, backup/transfer exclusions'),
         ('WorkManager outbox; HTTPS; per-record ACK; idempotent on UUIDv7', 'WorkManager outbox; ownership checks, durable ACK, payload conflicts, revocation precedence'),
         ('model artefacts (unencrypted bucket; no personal data)', 'approved model artefacts (private access; bucket encryption set per service requirement)'),
         ('fails CI if any bundled arm64 .so has PT_LOAD align < 16384 (warn-only for pilot)', 'checks all native libraries, ELF/ZIP packaging; runtime test on 16 KB; pilot limited to verified devices'),
         ('RDS for PostgreSQL (or GaussDB) schema init', 'one chosen DB engine after S6; driver/migration/TLS/restore compatibility proven'),
         ('Synthetic lessons, public corpora, model artefacts', 'Synthetic records; corpora/models only after licence and privacy approval'),
         ('**None**, enforced by seeding only from `tools/synthetic-lessons` and `tools/demo`', 'No pilot data; separate credentials/endpoints, seed allowlists and logging controls. Public speech or mixed voices are not automatically non-personal.'),
         ('None (model artefacts flow cloud → pilot only)', 'None from pilot → cloud, including weights/soft labels; approved artefacts may flow cloud → pilot'),
         ('// silenced spans (calls, mic taken); excluded from I-7/I-8', '// all lost capture/processing; excluded from every eligible denominator'),
         ('"missing_s": 42.5,', '"observed_s": 2357.5,\n    "missing_s": 42.5,'),
         ('"upstream_licences": ["CC-BY-NC-4.0 (MMS-LID, teacher only)"]', '"model_manifest_hash": "…"'),
         ('**`LessonRecord` v1 → v2 (§10.1):**', '**`LessonRecord` v1 → v2 (§10.1), illustrative subset only:** Freeze full schema, null/availability reasons, denominators, consent reference, processing versions and validation at G1. No previous version is implemented.'),
         ('and `page_alignment` of the runtime used in the benchmark.', 'and compatibility evidence for all bundled native libraries/runtime packaging. Manifest also defines shapes, class order, streaming state/lookahead and atomic compatibility checks (review G1). Upstream/derived licences belong in this manifest; using a model only as a teacher does not automatically remove downstream restrictions.')])
        edit('docs/research/ASSUMPTIONS.md',[
         ('Status as of 2026-09-26.', 'Evidence register dated 2026-09-26; planning caveats updated 2026-09-27. Status counts below describe that evidence audit, not implementation readiness. See [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md) for current gates.'),
         ('| Reword to "first automated" |', '| Describe proposed continuous measurement; first-of-kind claim remains unverified |'),
         ('Play requires 16 KB for API 35+ updates from 2027-02-01', 'Play requires 16 KB for API 35+ updates from 2027-02-01; sideloading does not fix incompatible native libraries on 16 KB devices'),
         ('conv/attention layers INT8, recurrent layers FP16', 'INT8 candidate where export/runtime support is demonstrated; recurrent layers may need FP16/FP32'),
         ('| Note in model cards | Replace them before any commercial use |', '| Review competition use and derived-weight redistribution before G3; document in model manifests | Use an approved alternative where restrictions cannot be met |'),
         ('No personal data means Art. 22 doesn\'t apply; the competition only requires lawful operation', 'Synthetic records minimise pilot-data risk; public voices, mixtures and derived artefacts still require review. Legal and competition acceptance remains open'),
         ('School/parent', 'School/parent')])
        # Add review caveats without rewriting the historical source audit.
        p=root/'docs/research/CLAIMS_AUDIT.md'; s=p.read_text(); pos=s.index('\n'); s=s[:pos]+ '\n\n> Planning review 2026-09-27: this is a dated source audit, not implementation evidence. See [IMPLEMENTATION_REVIEW.md](IMPLEMENTATION_REVIEW.md). In particular, the reviewed papers do not prove universal absence of prior automated language measurement; public/synthetic speech is not automatically non-personal; model licensing needs derived-weight review; and sideloading does not resolve 16 KB runtime incompatibility.\n'+s[pos:]; p.write_text(s)
        PY
        # Run only the appended research-document edits; architecture edits already applied.
        python3 - <<'PY'
        from pathlib import Path
        s=Path('/tmp/revise_echo_plans.py').read_text()
        setup=s[:s.index("p=root/'docs/DIMTS_ARCHITECTURE_CONTEXT.md'")]
        append=s[s.index("edit('docs/research/README.md'"):]
        exec(setup+append)
        PY
    Traceback (most recent call last):
      File "<stdin>", line 5, in <module>
      File "<string>", line 15, in <module>
      File "<string>", line 6, in edit
    ValueError: Missing replacement in docs/research/RESEARCH_PLAN.md: Legal answers; ADR-002/003
    ✗ (1) • 0ms

## Activity

    $ python3 - <<'PY'
        from pathlib import Path
        s=Path('/tmp/revise_echo_plans.py').read_text()
        setup=s[:s.index("p=root/'docs/DIMTS_ARCHITECTURE_CONTEXT.md'")]
        append=s[s.index("edit('docs/research/RESEARCH_PLAN.md'"):]
        append=append.replace(" ('Legal answers; ADR-002/003', 'Legal answers; ADR-002/003'),\n",'').replace(" ('School/parent', 'School/parent')",'')
        exec(setup+append)
        PY
        git diff --stat; git diff --check; rg -n 'MindNLP|inherit|public data|public-licensed|all models|No code|byte-for|≥ 2|notification|W0 test|consent|model artefacts only' docs/DIMTS_ARCHITECTURE_CONTEXT.md
     docs/DIMTS_ARCHITECTURE_CONTEXT.md  | 183 ++++++++++++++++++------------------
     docs/research/ASSUMPTIONS.md        |  12 +--
     docs/research/CLAIMS_AUDIT.md       |   3 +
     docs/research/CODEBASE_STRUCTURE.md |  31 +++---
     docs/research/README.md             |   3 +-
     docs/research/RESEARCH_PLAN.md      |  35 +++----
     6 files changed, 135 insertions(+), 132 deletions(-)
    25:Dimts is an offline, on-device classroom audio analysis system for early-grade (O-class and Grades 1–3) teachers in Ethiopia. A teacher places a low-end Android phone on her desk during a lesson. The phone analyses the audio **in real time, on the device**, without speech recognition, and produces pedagogical indicators: how much the teacher talks, how long she waits for children to respond, whether children answer individually or only in chorus, how often turns change, and which language the lesson is conducted in (Amharic, Sidaamu Afoo, English). Deployment Mode never persists or uploads raw audio; separately consented Research Mode transfers encrypted audio only to the in-country research store. Only derived indicators sync to an in-country server, where they feed a private weekly report to the teacher and anonymised aggregates to district education offices. The segmentation design avoids transcription; its cross-language robustness remains to be evaluated on Amharic and Sidaamu Afoo. LID is language-dependent. Script differences do not themselves establish acoustic generalisation.
    58:- Recording an adult teacher, with her consent, in her own classroom is ethically and logistically far lighter than collecting child speech data for assessment.
    73:| **Researcher (team)** | Train and evaluate models | Research Mode app + annotation tool | Audio from teachers who did not consent to Research Mode |
    86:Same as above, **plus** raw audio is stored encrypted on the device for later transfer to the research server, **only** with explicit written consent, used to build training and evaluation datasets.
    96:- Android teacher app (record, report, sync, consent).
    125:- LID initially consumes complete 3 s teacher-only windows. Short/uncertain speech stays `unknown`; do not inherit neighbouring language. E3 may revise the threshold with a spec/model version change. Preserve within-turn code switches at window resolution.
    152:| C-7 | **Data protection law:** Ethiopia Personal Data Protection Proclamation No. 1321/2024 (in force 24 Jul 2024). Reported to require consent, **local storage of personal data on servers in Ethiopia**, 72-hour breach notification, and special protection for minors (under 16). [VERIFY with faculty legal advisor — not legal advice] | Personal data (including voice audio and teacher-linked indicators) stored in-country. Cross-border transfer of raw classroom audio avoided by design (see §11, ADR-003). |
    153:| C-8 | **Children are recorded incidentally.** | No child-level features stored. No voice embeddings persisted. Deployment Mode never writes raw audio to disk. Ethics-approved consent/assent and school permissions before any classroom capture, including transient processing; legal questions remain in WS-F. |
    174:| Pretrained speech encoders | **PyTorch + Hugging Face** | Load multilingual self-supervised encoders (wav2vec2 / XLS-R / HuBERT family) as the server-side teacher model | Proposed replacement for MindNLP per audit; verify model licence and target training hardware in S4. |
    178:| Database | **openGauss** | Indicator records, reports, aggregates, consent ledger | PostgreSQL-lineage; standard drivers mostly work [VERIFY driver for chosen backend language]. |
    200:│      → Encrypted local store (indicators, timeline summary, consent state)               │
    217:│  OBS: public datasets, model artefacts, experiment tracking                              │
    235:| Capture | 16 kHz mono PCM16 via `AudioRecord`; foreground service with persistent notification; wake lock during lesson |
    238:| LID model | Complete 3 s teacher-only windows; {amh, sid, eng, oth} plus calibrated abstention to unknown; no short-segment inheritance |
    327:`teacher` (pseudo_id, school_id, consent_state, language_pref) · `school` · `woreda` · `lesson_record` · `weekly_report` · `aggregate_cell` (woreda × grade × week, suppressed if n < k) · `consent_event` (append-only ledger) · `audit_log` · `research_recording` (Research Mode only, pointer to encrypted blob, consent_ref).
    343:| Teacher misuse by management | Individual reports visible only to the teacher. Sharing with director is teacher-initiated, revocable. Written into consent form and product copy. |
    346:| Consent | Per-teacher consent recorded in app and in server ledger; separate opt-in for Research Mode; withdrawal deletes server records (tombstone + purge job). Child consent/assent and school permissions per legal/ethics review; no classroom capture before approval. Offline withdrawal stops capture and discards queued lessons; server revocation rejects stale retries. |
    347:| Breach | 72-hour notification runbook (per C-7) in `docs/ethics/breach-runbook.md`. |
    362:│   └── ethics/                        ← consent forms (amh, sid, eng), approval, breach runbook
    412:- `POST /v1/consent` — consent event append.
    468:| **Anteneh (lead)** | Data, ethics, indicator spec, evaluation design, pilot, thesis, pitch | Ethics approval, consent forms (3 languages), annotation guide + κ, indicator spec v1, eval report, demo script |
    479:Use [`research/RESEARCH_PLAN.md`](research/RESEARCH_PLAN.md) §5 for the single schedule and [`research/IMPLEMENTATION_REVIEW.md`](research/IMPLEMENTATION_REVIEW.md) for G0–G5 acceptance gates. Dates are targets, not evidence of approval or completion. No classroom recording, even a microphone test, precedes ethics and required consent. Before that, use authorised adult/staged fixtures.
    481:Critical paths run separately: synthetic demo → competition submission; ethics + consent → classroom collection → annotation → held-out validation → private pilot reports. Delayed ethics must not block the synthetic demo, and demo success must not be presented as classroom validity.
    492:| System complexity | Edge (streaming inference) + sovereign server (openEuler/openGauss) + cloud (ModelArts) + dashboard; offline-first sync; consent ledger. |
    517:| Sidaamu Afoo LID data scarce | High | Med | Collect consented adult speech early; use explicit unknown labels; any reduced label set needs a new model/spec version. |
    519:| Teachers perceive surveillance | Med | High | Private-by-default reports; co-design report wording with 2 teachers; consent copy reviewed by E-Learning Directorate. |
    531:| ADR-003 | Where classroom audio is used for training | In-country training node only; ModelArts for public data and pipeline; no pilot-derived export under this proposal; any boundary change needs a separate reviewed ADR |
    581:- MindNLP (HF-compatible): https://github.com/mindspore-lab/mindnlp
    ✓ • 0ms

## Activity

    $ git diff -- docs/research/RESEARCH_PLAN.md docs/research/CODEBASE_STRUCTURE.md docs/research/ASSUMPTIONS.md; sed -n '185,225p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; git status --short
    diff --git a/docs/research/ASSUMPTIONS.md b/docs/research/ASSUMPTIONS.md
    index dcefa21..612fd38 100644
    --- a/docs/research/ASSUMPTIONS.md
    +++ b/docs/research/ASSUMPTIONS.md
    @@ -1,6 +1,6 @@
     # Assumptions register — Dimts
    
    -Status as of 2026-09-26. This lists every assumption the design still rests on, where it comes from, how strong the evidence is, and how it will be tested. Evidence is detailed in [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md); tests are scheduled in [`RESEARCH_PLAN.md`](RESEARCH_PLAN.md).
    +Evidence register dated 2026-09-26; planning caveats updated 2026-09-27. Status counts below describe that evidence audit, not implementation readiness. See [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md) for current gates. This lists every assumption the design still rests on, where it comes from, how strong the evidence is, and how it will be tested. Evidence is detailed in [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md); tests are scheduled in [`RESEARCH_PLAN.md`](RESEARCH_PLAN.md).
    
     **Origin column:**
    
    @@ -50,7 +50,7 @@ Status as of 2026-09-26. This lists every assumption the design still rests on,
     | A-P5 | Talk ratio (I-1) is a meaningful quality proxy that teachers can change with feedback. | ARCH §5 | 🟡 Plausible | RCT −5 pp talk share (Demszky & Liu, 2023, US online); Liu & Cohen (2021) | Pilot pre/post plus the expert panel (B2) | Report it only as descriptive |
     | A-P6 | Short response latency (I-2) signals insufficient wait time. | ARCH §5 | 🟡 Plausible, confounded | Rowe (1986); Tobin (1987). Choral drills have near-zero latency by design. | Split I-2 by response type; question detection (R-4) | Drop the coaching rule for I-2 |
     | A-P7 | High choral ratio (I-3) is undesirable. | ARCH §5 | ❌ Changed | Heward et al. (1989) vs Pontefract & Hardman (2005) | **Replacement:** I-3 is a *balance* indicator | — |
    -| A-P8 | Classroom language has not been measured systematically, so I-6 is novel. | ARCH §2.4 | ✅ Holds (as "automated, systematic") | Heugh et al. (2007): notes, checklist abandoned. Piper et al. (2016): child self-report. | Systematic search C1 before claiming "first" | Reword to "first automated" |
    +| A-P8 | Classroom language has not been measured systematically, so I-6 is novel. | ARCH §2.4 | ✅ Holds (as "automated, systematic") | Heugh et al. (2007): notes, checklist abandoned. Piper et al. (2016): child self-report. | Systematic search C1 before claiming "first" | Describe proposed continuous measurement; first-of-kind claim remains unverified |
     | A-P9 | Teachers switch between Amharic, Sidaamu Afoo and English within lessons. | ARCH §6 C-6 | 🟡 Plausible | Heugh et al. (2007) observed routine Amharic–English code-switching | RQ-A4 classroom visits | The LID label set changes |
     | A-P10 | Teachers will use a private, non-punitive weekly report. | ARCH §3 | ❓ Untested | Jensen et al. (2020): feedback is usually evaluative. Birnhack & Perry-Hazan (2021): teachers weigh their own privacy. | Pilot interviews (§14.5), co-design | Product redesign |
     | A-P11 | Classrooms have 40–80 children. | ARCH §6 C-5 | 🟡 Plausible (averages) | Pupil-section ratio, Grades 1–6: national 54.2, Sidama 59.6 (MoE, 2025) | Record class size per pilot lesson | Augmentation ranges change |
    @@ -62,10 +62,10 @@ Status as of 2026-09-26. This lists every assumption the design still rests on,
     |---|---|---|---|---|---|---|
     | A-T1 | Teachers own Android 10+ phones with about 2 GB RAM. | ARCH C-2 | 🟡 Plausible | StatCounter: Android 94%, mostly v11+ (web traffic) | Phone survey of pilot teachers. Sys, W1 | Provide reference phones |
     | A-T2 | RTF ≤ 0.3 and RSS ≤ 300 MB on the target phone. | ARCH C-3 | ❓ Untested | Schlotterbeck et al. (2021) ≈ RTF 0.12 on unspecified hardware | S2 with the bundled `benchmark` tool | Smaller model or frame skipping |
    -| A-T3 | All on-device inference is INT8. | ARCH C-2 | ❌ Changed | Lite r2.10.0: GRU/LSTM FP16/FP32 only | **Replacement:** conv/attention layers INT8, recurrent layers FP16 (or a convolution-only student) | — |
    +| A-T3 | All on-device inference is INT8. | ARCH C-2 | ❌ Changed | Lite r2.10.0: GRU/LSTM FP16/FP32 only | **Replacement:** INT8 candidate where export/runtime support is demonstrated; recurrent layers may need FP16/FP32 (or a convolution-only student) | — |
     | A-T4 | A phone lying on the teacher's desk captures usable audio. | ARCH §3 | ❓ Untested — **critical** | Jensen et al. (2020): room mics "produced noisy audio". Schlotterbeck et al. (2021): lavalier into a smartphone. | E1: desk vs wired lavalier. ML, W1 | Add a clip-on mic to the kit (D-7) |
     | A-T5 | MindSpore Lite 2.10.0 runs on Android via the Java API. | ARCH §7 | ✅ Holds (package) / ❓ on device | AAR inspected; minSdk 19 | S1 | JNI or ONNX fallback |
    -| A-T6 | The app can be published on Google Play later. | PACK | ❓ Untested | The AAR is 4 KB-aligned; Play requires 16 KB for API 35+ updates from 2027-02-01 | S8 | Rebuild Lite with 16 KB alignment |
    +| A-T6 | The app can be published on Google Play later. | PACK | ❓ Untested | The AAR is 4 KB-aligned; Play requires 16 KB for API 35+ updates from 2027-02-01; sideloading does not fix incompatible native libraries on 16 KB devices | S8 | Rebuild Lite with 16 KB alignment |
     | A-T7 | Capture runs uninterrupted for 40 minutes. | ARCH §8.3 | 🟡 Plausible, with gaps | Android silences the mic during calls or when a higher-priority app takes it | S7; mark silenced spans as missing | Indicators computed on partial lessons |
     | A-T8 | MindNLP loads the SSL teacher encoder. | ARCH §7.2 | ❌ Changed | The repository pivoted to MindAct; legacy branch frozen | **Replacement:** PyTorch/HF teacher; student in MindSpore (D-1) | — |
     | A-T9 | MindSpore 2.10 trains on the in-country node. | ARCH §7.2 | ❓ Untested | 2.10 notes are Ascend-focused | S4 | Train the student in PyTorch too; keep Lite for inference |
    @@ -85,7 +85,7 @@ Status as of 2026-09-26. This lists every assumption the design still rests on,
     | A-D6 | Teacher–student distillation gets a tiny model close to a large one. | ARCH §8.2 | 🟡 Plausible | Chang et al. (2022); Schmid et al. (2023) | E6 | Supervised small model only |
     | A-D7 | 6–10 annotated hours with κ ≥ 0.70 is achievable. | ARCH §9.3 | ❓ Untested | — | Annotation pilot, W3–4 | Fewer classes or more hours |
     | A-D8 | Frame-level errors don't wreck indicator validity. | PACK | ❓ Untested | Errors bias downstream estimates (Gautheron et al., 2025) | E7 | Report calibrated indicators with intervals |
    -| A-D9 | MMS models (CC-BY-NC) may be used for this project. | PACK | 🟡 Plausible | Non-commercial thesis and competition | Note in model cards | Replace them before any commercial use |
    +| A-D9 | MMS models (CC-BY-NC) may be used for this project. | PACK | 🟡 Plausible | Non-commercial thesis and competition | Review competition use and derived-weight redistribution before G3; document in model manifests | Use an approved alternative where restrictions cannot be met |
     | A-D10 | Synthetic classroom mixtures give a useful pre-training signal. | ARCH D-6 | 🟡 Plausible | Common practice (Ko et al., 2017) | E2/E4 comparisons | Rely on real data only |
    
     ## L — Legal and ethics
    @@ -105,7 +105,7 @@ Status as of 2026-09-26. This lists every assumption the design still rests on,
     |---|---|---|---|---|---|---|
     | A-C1 | The Preliminary submission is due around **1 Dec 2026**. | PACK | ❓ Untested | The page says only "Nov–Dec 2026" | Read the template and portal deadline. Lead, W0 | Pull W7–8 work forward |
     | A-C2 | The Huawei stack is a *preference*. | ARCH C-11 | ❌ Changed | 2026–27: "must be deployed on Huawei Cloud and use at least one Huawei Cloud service" | **Replacement:** Huawei Cloud deployment is mandatory for the competition build | — |
    -| A-C3 | A Huawei Cloud demo holding **only synthetic or public data** satisfies both the competition ("complying with local laws") and Proclamation 1321/2024. | PACK | 🟡 Plausible — **critical** | No personal data means Art. 22 doesn't apply; the competition only requires lawful operation | Legal advisor WS-F Q8, in writing | Demo hosted in Ethiopia with only a thin Huawei Cloud service (e.g. RDS for public aggregates) |
    +| A-C3 | A Huawei Cloud demo holding **only synthetic or public data** satisfies both the competition ("complying with local laws") and Proclamation 1321/2024. | PACK | 🟡 Plausible — **critical** | Synthetic records minimise pilot-data risk; public voices, mixtures and derived artefacts still require review. Legal and competition acceptance remains open | Legal advisor WS-F Q8, in writing | Demo hosted in Ethiopia with only a thin Huawei Cloud service (e.g. RDS for public aggregates) |
     | A-C4 | Ethiopia competes in the "Northern Africa" division. | PACK | 🟡 Plausible | The Huawei Technologies Ethiopia registration guide points to the Northern Africa division | Email the Huawei Ethiopia contact | Different schedule or rules |
     | A-C5 | A live Android demo will count. | ARCH §17 | ✅ Holds (optional) | "Hardware devices are optional … prepare them on their own" | — | — |
     | A-C6 | Scoring rewards system and technical complexity. | ARCH §17 | ❌ Changed → see A-C2 | Prelim/National score Innovation 60 / Application value 40 | Pitch leads with innovation and application value; complexity supports them | — |
    diff --git a/docs/research/CODEBASE_STRUCTURE.md b/docs/research/CODEBASE_STRUCTURE.md
    index 13667e4..2d42489 100644
    --- a/docs/research/CODEBASE_STRUCTURE.md
    +++ b/docs/research/CODEBASE_STRUCTURE.md
    @@ -1,6 +1,6 @@
     # Sample codebase structure — Dimts
    
    -A revision of the monorepo in architecture §12, reflecting [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) and [`ASSUMPTIONS.md`](ASSUMPTIONS.md). This is a proposal to ratify through ADRs, not scaffolding that has been built.
    +A revision of the monorepo in architecture §12, reflecting [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) and [`ASSUMPTIONS.md`](ASSUMPTIONS.md). Reviewed 2026-09-27 against [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md). The folders are scaffolded; only `edge/android/app` is a starter Gradle module. All described product files and other modules remain proposed and require G0–G5.
    
     ## What changed from §12, and why
    
    @@ -27,7 +27,7 @@ dimts/
     ├── LICENSE                           ← open-source licence (needed for Regional shortlist); e.g. Apache-2.0
     ├── .gitignore                        ← includes .DS_Store, *.wav, *.ms, data/, .env
     ├── docs/
    -│   ├── ARCHITECTURE_CONTEXT.md
    +│   ├── DIMTS_ARCHITECTURE_CONTEXT.md
     │   ├── adr/                          ← ADR-000 template; ADR-001…011 (011 = two-tier deployment, D-6)
     │   ├── indicator-spec.md             ← mirrors §5; versioned; I-3 framed as balance indicator
     │   ├── indicator-rationale.md        ← RQ-B1 literature matrix
    @@ -39,7 +39,7 @@ dimts/
     ├── indicators-core/                  ← single source of truth for I-1…I-8
     │   ├── python/dimts_indicators/      ← reference implementation (Python ≥ 3.10)
     │   │   ├── timeline.py               ← Segment(start_s, end_s, label, conf), labels incl. MISSING
    -│   │   ├── postprocess.py            ← min-duration merge, gap bridging, LID inheritance
    +│   │   ├── postprocess.py            ← deterministic smoothing, timing preservation, unknown LID
     │   │   ├── indicators.py             ← I-1…I-8, spec_version
     │   │   └── calibration.py            ← E7: confusion-matrix error propagation, intervals
     │   ├── kotlin/                       ← Android port (Gradle module, pure Kotlin, no Android deps)
    @@ -77,8 +77,8 @@ dimts/
     │   │   └── MicSource.kt              ← built-in vs wired/USB lavalier (D-7)
     │   ├── pipeline/                     ← features (Kotlin twin of logmel.py) → seg → LID → postprocess
     │   ├── inference/                    ← MindSpore Lite 2.10.0 AAR (Java API); ONNX Runtime fallback flag
    -│   ├── storage/                      ← Room + SQLCipher; key wrapped by Android Keystore
    -│   ├── sync/                         ← WorkManager outbox; HTTPS; per-record ACK; idempotent on UUIDv7
    +│   ├── storage/                      ← Room + SQLCipher; Keystore-wrapped key, backup/transfer exclusions
    +│   ├── sync/                         ← WorkManager outbox; ownership checks, durable ACK, payload conflicts, revocation precedence
     │   └── benchmark/                    ← RTF / RSS / battery harness (S2); wraps Lite `benchmark` tool
     │
     ├── server/                           ← Python FastAPI; same code runs in both deployments
    @@ -94,15 +94,15 @@ dimts/
     │   │   ├── audio_mix.py              ←   (a) training mixtures: Afrivoice/FLEURS/ALFFA + MUSAN + RIR + choral sim
     │   │   └── lesson_records.py         ←   (b) synthetic LessonRecords + timelines for the cloud demo
     │   ├── lesson-simulator/             ← web page: play a synthetic/public clip → live indicators (demo)
    -│   ├── check-elf-alignment/          ← fails CI if any bundled arm64 .so has PT_LOAD align < 16384 (warn-only for pilot)
    +│   ├── check-elf-alignment/          ← checks all native libraries, ELF/ZIP packaging; runtime test on 16 KB; pilot limited to verified devices
     │   └── demo/                         ← scripted demo accounts, seed data (synthetic only)
     │
     └── infra/
         ├── huawei-cloud/                 ← COMPETITION DEMO — synthetic/public data only
         │   ├── README.md                 ← one-command redeploy; region AF-Johannesburg
         │   ├── ecs/                      ← server + dashboard + simulator containers
    -    │   ├── rds/                      ← RDS for PostgreSQL (or GaussDB) schema init
    -    │   └── obs/                      ← public datasets + model artefacts (unencrypted bucket; no personal data)
    +    │   ├── rds/                      ← one chosen DB engine after S6; driver/migration/TLS/restore compatibility proven
    +    │   └── obs/                      ← public datasets + approved model artefacts (private access; bucket encryption set per service requirement)
         ├── openeuler/                    ← PILOT — personal data, Hawassa University
         │   ├── compose/                  ← server, openGauss, Label Studio, backup
         │   └── backup/                   ← encrypted, in-country
    @@ -114,15 +114,15 @@ dimts/
    
     | | `infra/huawei-cloud/` (competition) | `infra/openeuler/` (pilot) |
     |---|---|---|
    -| Data | Synthetic lessons, public corpora, model artefacts | LessonRecords from consenting teachers, Research Mode audio |
    -| Personal data | **None**, enforced by seeding only from `tools/synthetic-lessons` and `tools/demo` | Yes. Art. 22(1): stored in Ethiopia. |
    +| Data | Synthetic records; corpora/models only after licence and privacy approval | LessonRecords from consenting teachers, Research Mode audio |
    +| Personal data | No pilot data; separate credentials/endpoints, seed allowlists and logging controls. Public speech or mixed voices are not automatically non-personal. | Yes. Art. 22(1): stored in Ethiopia. |
     | Who writes | Deploy scripts only | Phones (sync API), annotators (Label Studio) |
    -| Data path to the other tier | None | None (model artefacts flow cloud → pilot only) |
    +| Data path to the other tier | None | None from pilot → cloud, including weights/soft labels; approved artefacts may flow cloud → pilot |
     | Reproducible by judges | Yes (README + generator) | No, and not required |
    
     ## Contract changes worth making now
    
    -**`LessonRecord` v1 → v2 (§10.1):**
    +**`LessonRecord` v1 → v2 (§10.1), illustrative subset only:** Freeze full schema, null/availability reasons, denominators, consent reference, processing versions and validation at G1. No previous version is implemented.
    
     ```jsonc
     {
    @@ -130,7 +130,8 @@ dimts/
       // …all v1 fields…
       "capture": {
         "mic": "builtin|wired_lavalier|usb",   // E1 / D-7
    -    "missing_s": 42.5,                     // silenced spans (calls, mic taken); excluded from I-7/I-8
    +    "observed_s": 2357.5,
    +    "missing_s": 42.5,                     // all lost capture/processing; excluded from every eligible denominator
         "class_size_estimate": 58              // entered by teacher; C-5
       },
       "indicators": {
    @@ -138,11 +139,11 @@ dimts/
         "I2_response_latency_s": { "choral": {"median": 0.2, "n": 30}, "single": {"median": 1.4, "n": 12} },
         "I3_choral_ratio": 0.71                // reported as a balance indicator
       },
    -  "model_versions": { "seg": "…", "lid": "…", "upstream_licences": ["CC-BY-NC-4.0 (MMS-LID, teacher only)"] }
    +  "model_versions": { "seg": "…", "lid": "…", "model_manifest_hash": "…" }
     }
     ```
    
    -**`model_card.json`** gains `upstream_models` (with licences), `precision_by_layer` (records which layers stayed FP16 because Lite lacks INT8 GRU), and `page_alignment` of the runtime used in the benchmark.
    +**`model_card.json`** gains `upstream_models` (with licences), `precision_by_layer` (records which layers stayed FP16 because Lite lacks INT8 GRU), and compatibility evidence for all bundled native libraries/runtime packaging. Manifest also defines shapes, class order, streaming state/lookahead and atomic compatibility checks (review G1). Upstream/derived licences belong in this manifest; using a model only as a teacher does not automatically remove downstream restrictions.
    
     ## Ownership (from §15)
    
    diff --git a/docs/research/RESEARCH_PLAN.md b/docs/research/RESEARCH_PLAN.md
    index 89d5fea..7e375a0 100644
    --- a/docs/research/RESEARCH_PLAN.md
    +++ b/docs/research/RESEARCH_PLAN.md
    @@ -1,6 +1,6 @@
    -# Research plan — Dimts (final, v1.0)
    +# Research and implementation plan — Dimts (reviewed proposal, v1.1)
    
    -Status as of 2026-09-26. This plan builds on [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md), which is now updated with the full texts in [`papers/`](papers/). Related files:
    +Status as of 2026-09-27. Implementation must follow the ordered gates in [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md); none is recorded as passed. This plan builds on [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md), which is now updated with the full texts in [`papers/`](papers/). Related files:
    
     - [`ASSUMPTIONS.md`](ASSUMPTIONS.md) — every assumption the design still rests on.
     - [`CODEBASE_STRUCTURE.md`](CODEBASE_STRUCTURE.md) — the repository layout that follows from these findings.
    @@ -11,7 +11,7 @@ Owners follow §15 of the architecture document:
     - **ML** — Person 2
     - **Sys** — Person 3
    
    -Weeks count from W0 = 2026-09-25. Sources are cited (Author, year) and listed in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).
    +Weeks count from W0 = 2026-09-25. Dates are targets: real-data phases of E1–E7 move with ethics/consent and G4; public/staged work and the synthetic demo continue independently. Sources are cited (Author, year) and listed in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).
    
     ---
    
    @@ -21,7 +21,7 @@ Weeks count from W0 = 2026-09-25. Sources are cited (Author, year) and listed in
        - Only 32.4% of Grade 2–3 pupils reached the top two reading benchmarks in 2018, essentially unchanged since 2014 (31.3%) and 2016 (34.3%). Only 6.2% read fluently with full comprehension (AIR, 2019).
        - Only 39% of mother-tongue teachers are Proficient-or-above, and Sidaamu Afoo teachers sit in the 22–33% band (AIR, 2020).
     2. **Observation-based feedback is associated with better reading, but it is patchy.** Where a school has someone who observes mother-tongue classes, and observes more often, pupils read better (AIR, 2019, pp. 53–54). There is no standardised or frequent measure. This is the gap Dimts fills. "No feedback at all" overstates it.
    -3. **Classroom language has never been measured systematically.** Earlier work used handwritten observation notes (Heugh et al., 2007) or children's self-reports (Piper et al., 2016). Automated I-6 is a defensible novelty claim.
    +3. **Continuous automated classroom-language measurement remains a novelty hypothesis.** Earlier work used handwritten observation notes (Heugh et al., 2007) or children's self-reports (Piper et al., 2016). Run C1 before making a first-of-kind claim; these studies alone cannot prove absence of other systems.
     4. **Audio-only classroom analytics work at coarse grain.** Reported results: ~90% (Owens et al., 2017), >80% (Schlotterbeck et al., 2021), F1 0.64–0.78 (Donnelly et al., 2016). Fine-grained teacher-vs-child separation is still hard: 69% in Wang et al. (2025).
     5. **Talk-time feedback changes behaviour.** In an RCT, automated feedback cut talk share by 5 pp (Demszky & Liu, 2023). No such evidence exists for choral ratio or language share yet.
     6. **The competition has changed shape.**
    @@ -100,9 +100,9 @@ The core thesis question: *Do Dimts' acoustic indicators measure something Ethio
    
     | Experiment | Question | Method | Feeds | Due |
     |---|---|---|---|---|
    -| E1 | Usable audio: desk vs clip-on | W0–W1 test lessons. Measure SNR, clipping and RT60 estimate. Compare desk phone with a wired lavalier into the same phone (Schlotterbeck et al., 2021; Jensen et al., 2020). | Capture spec; ASSUMPTIONS A-T4 | W1 |
    -| E2 | How hard is T-1 on real Ethiopian audio? | 1–2 annotated lessons; VTC, pyannote-seg-3.0 and a DART-style energy baseline; per-class F1 with teacher-independent splits. | §14.1 realism; 4-class fallback | W4 |
    -| E3 | LID accuracy at 2 / 3 / 5 s | MMS-LID-256 (amh/sid/eng + other) on FLEURS, Afrivoice and pilot teacher segments. | LID threshold (audit 5d) | W3 |
    +| E1 | Usable audio: desk vs clip-on | W0–W1 authorised staged/adult fixtures; real classroom comparison only after G4. Measure SNR, clipping and RT60 estimate. Compare desk phone with a wired lavalier into the same phone (Schlotterbeck et al., 2021; Jensen et al., 2020). | Capture spec; ASSUMPTIONS A-T4 | W1 |
    +| E2 | How hard is T-1 on real Ethiopian audio? | 1–2 annotated lessons for feasibility only; collect multiple teachers/schools before independent evaluation; VTC, pyannote-seg-3.0 and a DART-style energy baseline; per-class F1 with teacher-independent splits. | §14.1 realism; 4-class fallback | W4 |
    +| E3 | LID accuracy at 2 / 3 / 5 s | MMS-LID-256 (amh/sid/eng + other) on FLEURS, Afrivoice and pilot teacher segments. | LID threshold (audit 5d) | W3 public/staged baseline; classroom phase after G4 |
     | E4 | Can choral vs single child speech be separated? | Synthetic choral mixtures from Afrivoice speakers + pitch/formant scaling; CountNet-style head; validate on real clips. | 5-class vs 4-class | W5 |
     | E5 | Student architecture under Lite ops | CRNN (FP16/FP32 GRU) vs convolution-only TCN (INT8) vs tiny conformer. Measure F1, MB and RTF. | ADR-002 | W4 |
     | E6 | Distillation gap | KD from the D-1 teacher to the E5 winner. | §14.1 | W8 |
    @@ -116,18 +116,19 @@ The core thesis question: *Do Dimts' acoustic indicators measure something Ethio
     |---|---|---|
     | S1 | `.ms` INT8 hello-world through the 2.10.0 AAR on a 2 GB phone | W0 |
     | S2 | RTF ≤ 0.3 and RSS ≤ 300 MB via the bundled `benchmark` tool and the in-app harness | W3 |
    -| S3 | PyTorch→ONNX→`.ms` and MindSpore→MindIR→`.ms` outputs agree within 1e-4; full-quant PTQ runs; list the ops that fall back to FP32 | W3 |
    +| S3 | Each FP export agrees with its own source graph on fixed features (initial tolerance 1e-4, justified per model); compare frameworks only for weight-equivalent graphs. Assess PTQ through task/indicator degradation separately; record actual precision/fallback ops | W3 |
     | S4 | MindSpore 2.10 trains a toy model on the in-country hardware | W1 |
    -| S5 | ModelArts, OBS and ECS all in **AF-Johannesburg**; flavours and prices recorded; OBS unencrypted and holding **public data only** | W1 |
    +| S5 | Confirm exact services before choosing a region; ModelArts, OBS and ECS all in **AF-Johannesburg**; flavours and prices recorded; any required unencrypted training bucket restricted to approved corpus objects, never anonymous/public access; verify licence/privacy separately | W1 |
     | S6 | openEuler LTS + openGauss + psycopg connect (SASL/sha256 workaround); **host chosen from [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md)** — Zergaw VPS/DBaaS, INSA G-Cloud or Ethio Telecom teleCloud, not the on-campus box, unless the university prefers self-hosting | W1 |
     | S7 | Microphone foreground service; `AudioRecordingCallback` detects call-silencing; 40-minute battery test | W2 |
    -| S8 | 16 KB decision: sideload for the pilot; rebuild Lite before any Play release | W6 |
    -| S9 | SQLCipher + Keystore key | W2 |
    +| S8 | 16 KB decision: test Lite, SQLCipher and any fallback native libraries, ELF/ZIP alignment and runtime; sideload only on verified compatible phones | W6 |
    +| S9 | SQLCipher + Keystore key; backup/transfer exclusions, key-loss/reinstall and shared-phone tests before G4 | W2 |
    +| S11 | Sync ownership, ACK/conflict/retry, withdrawal and restore-purge tests per G4; fixed-cohort disclosure tests before G5 | Before pilot/release |
     | **S10** (new) | **Competition cloud stack:** ECS + RDS for PostgreSQL (or GaussDB) + OBS in AF-Johannesburg serving the demo page and lesson simulator with synthetic lessons. Redeployable from a script. | W5 |
    
     ### WS-F: Legal and ethics (Lead)
    
    -Primary text: Proclamation 1321/2024. Get the official Gazette copy (TO_OBTAIN #2).
    +Primary text: Proclamation 1321/2024. The supplied Gazette copy is in `docs/legal/`; use it with the legal advisor (see TO_OBTAIN #2).
    
     Questions for the legal advisor, answered in writing by W3:
    
    @@ -162,12 +163,12 @@ Northern Africa division, 2026–27 (see audit §7):
    
     | ID | Decision | Recommendation |
     |---|---|---|
    -| D-1 | Teacher-model framework (MindNLP is gone) | PyTorch/HF teacher on the in-country node, MMS-LID-256 as the LID teacher, and the student trained in MindSpore. Reword the pitch to "every model on the phone is trained in MindSpore and runs on MindSpore Lite". |
    +| D-1 | Teacher-model framework (MindNLP is gone) | PyTorch/HF teacher on the in-country node, MMS-LID-256 as the LID teacher, and the student trained in MindSpore. Describe only the training/runtime path actually demonstrated; MindSpore remains subject to S4 and export/device gates. |
     | D-2 | Student architecture | Decide on E5 data; default to a convolution-only TCN if RTF is tight. |
    -| D-3 | Distribution | Sideload for the pilot; handle 16 KB before any Play release. |
    +| D-3 | Distribution | Sideload only on tested compatible phones; test every native library plus ELF/ZIP packaging and 16 KB runtime before wider distribution. |
     | D-4 | O-class in scope for coaching? | Wait for B3. |
    -| D-5 | CC-BY-NC models (MMS) | Research and competition only; state it in the model cards. |
    -| **D-6** (new) | Competition cloud tier vs data sovereignty | Two deployments from one codebase: `infra/huawei-cloud` (demo, synthetic data, AF-Johannesburg) and `infra/openeuler` (pilot, personal data, Hawassa). There is no data path from pilot to cloud (ASSUMPTIONS A-C3). |
    +| D-5 | CC-BY-NC models (MMS) | Review exact upstream and derived-weight terms for competition use and redistribution; record attribution/restrictions and use approved alternatives if unresolved. |
    +| **D-6** (new) | Competition cloud tier vs data sovereignty | Two deployments from one codebase: `infra/huawei-cloud` (demo, synthetic data, AF-Johannesburg) and `infra/openeuler` (pilot, personal data, Hawassa). There is no pilot-to-cloud path, including features, soft labels, weights, logs and backups. Public voices/mixtures need explicit approval; synthetic records are the demo default (ASSUMPTIONS A-C3). |
     | **D-7** (new) | Microphone | Decide from E1. If desk SNR is poor, ship a wired lavalier as part of the kit. |
    
     ---
    @@ -178,7 +179,7 @@ The exact Preliminary deadline within Nov–Dec 2026 is not yet known. The plan
    
     | Week | Dates | Research and build | Competition |
     |---|---|---|---|
    -| W0 | Sep 25 – Oct 4 | E1 test lessons; S1, S4, S6; legal questions sent; ethics submitted; A3/A4 visits begin | Register team; download templates; confirm division with Huawei Ethiopia |
    +| W0 | Sep 25 – Oct 4 | E1 staged/adult fixtures only (classroom work waits for G4); S1, S4, S6; legal questions sent; ethics submitted; A3/A4 visits begin | Register team; download templates; confirm division with Huawei Ethiopia |
     | W1–2 | Oct 5 – 18 | A4, A6; B1, B6; C1; E8; S5, S7, S9; ADR-001/004/005 | Apply for Huawei Cloud vouchers |
     | W3–4 | Oct 19 – Nov 1 | A3 memo; B3, B5; E2, E3, E5, E7; S2, S3; legal answers; ADR-002/003 | Draft Template 1 |
     | W5–6 | Nov 2 – 15 | B2 panel; E4; **S10 cloud demo live**; S8 decision; E9 | Record demo video |
    ### 7.4 HarmonyOS position
    Primary client is Android because that is what teachers own. The HarmonyOS client is a roadmap item unless the team has spare capacity after the national stage. Do not let it block the critical path.
    
    ---
    
    ## 8. System architecture
    
    ### 8.1 Tiers
    
    ```
    ┌──────────────────────────── EDGE (teacher's Android phone) ─────────────────────────────┐
    │  Mic → Ring buffer (≤ 10 s, RAM only) → Feature extractor (log-mel, 16 kHz)             │
    │      → Segmentation model (MindSpore Lite, INT8) → Frame labels                          │
    │      → LID model on TEACHER segments (MindSpore Lite, INT8) → lang labels                │
    │      → Post-processor → Segment timeline → Indicator engine (Kotlin, spec vN)            │
    │      → Encrypted local store (indicators, timeline summary, consent state)               │
    │      → Outbox → Sync client (HTTPS, idempotent, resumable)                               │
    │  Research Mode only: encrypted raw audio file, per-lesson key, manual export             │
    └───────────────────────────────────────────┬─────────────────────────────────────────────┘
                                                │ indicators JSON (KB), TLS, pinned cert
    ┌───────────────────────── SOVEREIGN TIER (in-country, Hawassa Univ.) ────────────────────┐
    │  openEuler host · containers                                                             │
    │  Ingest API → validation (schema + spec version) → openGauss                              │
    │  Report service (weekly teacher report, rule table) → pull endpoint for app              │
    │  Aggregation job (k-anonymity ≥ 5) → Dashboard API → District web dashboard              │
    │  Consent ledger · Audit log · Backup (encrypted, in-country)                              │
    │  Research store (raw audio from Research Mode, encrypted, access-controlled)             │
    │  Local training node (GPU/CPU) for fine-tuning on classroom audio  [ADR-003]             │
    └───────────────────────────────────────────┬─────────────────────────────────────────────┘
                                                │ NO pilot → cloud export; approved cloud → pilot models only
    ┌──────────────────────────── HUAWEI CLOUD (non-personal compute) ────────────────────────┐
    │  ModelArts: pre-training / adaptation of teacher encoder on PUBLIC speech data           │
    │  OBS: public datasets, model artefacts, experiment tracking                              │
    │  Export pipeline: MindIR → MindSpore Lite converter → .ms (INT8 PTQ)                     │
    └─────────────────────────────────────────────────────────────────────────────────────────┘
    ```
    
    ### 8.2 Key design decisions (proposed; ratify via ADR)
    
    1. **Frame classification, not clustering diarization.** The task is a fixed 5-class problem, not "who spoke when" with unknown N. This is simpler, trainable, and real-time.
    2. **Teacher–student distillation.** A large multilingual self-supervised encoder (server-side "teacher model", PyTorch/HF) is fine-tuned on annotated classroom audio and used to produce soft labels on unannotated audio. A tiny on-device "student" (CRNN or small conformer on log-mel) is trained on those soft labels. Whether this reaches useful accuracy on a 2 GB phone is tested in E6. It is also the main technical-complexity story for judges.
     M docs/DIMTS_ARCHITECTURE_CONTEXT.md
     M docs/research/ASSUMPTIONS.md
     M docs/research/CLAIMS_AUDIT.md
     M docs/research/CODEBASE_STRUCTURE.md
     M docs/research/README.md
     M docs/research/RESEARCH_PLAN.md
    ?? docs/research/IMPLEMENTATION_REVIEW.md
    ✓ • 0ms

## Activity

    $ python3 - <<'PY'
        from pathlib import Path
        p=Path('docs/DIMTS_ARCHITECTURE_CONTEXT.md'); s=p.read_text()
        a=s.index('```',s.index('### 8.1 Tiers')); b=s.index('```',a+3)+3
        s=s[:a]+'''```text
        ANDROID (offline pilot client)
          Mic → bounded RAM capture → owned features → Lite inference
              → observed/MISSING timeline + language/unknown → Kotlin indicators
              → encrypted local record + local report → authenticated outbox
          Research Mode: separate consent → encrypted audio → in-country transfer
                            │ derived LessonRecords only in Deployment Mode
                            ▼
        IN-COUNTRY PILOT (host selected through S6)
          FastAPI → chosen/tested database → private weekly reports
                  → fixed-cohort, disclosure-reviewed district aggregates
          Separate enrolment, consent/revocation, audit, encrypted backups
          Research audio/annotation and classroom training stay in-country
        
        HUAWEI CLOUD DEMO (isolated endpoint, credentials, DB, logs and backups)
          Synthetic fixture generator → same API/report code → visual demo page
          Approved public-corpus/model experiments → ModelArts/OBS, subject to S5
          Approved cloud artefacts may enter pilot; NO pilot-derived export
        ```'''+s[b:]
        s=s.replace('Dimts is an offline, on-device', 'Dimts is a proposed offline, on-device',1)
        s=s.replace('anonymised aggregates to district education offices', 'minimum-group-suppressed aggregates to district education offices, subject to disclosure review')
        s=s.replace('Recording an adult teacher, with her consent, in her own classroom is ethically and logistically far lighter than collecting child speech data for assessment.', 'Teacher consent alone is insufficient for classroom capture that includes children. Both modes must follow the ethics and consent decisions in WS-F.')
        s=s.replace('indicator spec v1, eval report', 'indicator spec v2, eval report')
        s=s.replace('**Quantization:** MindSpore Lite post-training INT8 with a calibration set drawn from real classroom audio (not synthetic).', '**Quantization:** evaluate supported INT8 and mixed-precision candidates; use a training/development-only real classroom calibration subset for the pilot. The synthetic demo uses approved public/staged calibration and makes no classroom accuracy claim.')
        s=s.replace('Models: segmentation ≤ 8 MB, LID ≤ 8 MB (INT8).', 'Models: preferred segmentation ≤ 8 MB and LID ≤ 8 MB; hard combined ceiling ≤ 20 MB at actual shipped precision.')
        s=s.replace('**No ASR, no text.**', '**No ASR or speech-derived text.**')
        s=s.replace('Every model operates on acoustic features only. Any PR introducing transcription is rejected.', 'Speech models operate on acoustics only. UI text, translations and rule-based reports are allowed; transcription is out of scope.')
        p.write_text(s)
        p=Path('docs/research/ASSUMPTIONS.md'); s=p.read_text().replace('Status counts below describe that evidence audit, not implementation readiness.', 'Status counts include the 2026-09-27 downgrade of the unproven novelty claim, not implementation readiness.').replace('  - 7 hold;', '  - 6 hold;').replace('  - 16 are untested;', '  - 17 are untested;').replace('✅ Holds (as "automated, systematic")','❓ Untested (novelty search incomplete)'); p.write_text(s)
        p=Path('docs/research/CODEBASE_STRUCTURE.md'); s=p.read_text().replace('ADR-001…011 (011 = two-tier deployment, D-6)', 'ADR-001…013 (011 deployment; 012 contracts; 013 security)').replace('Segment(start_s, end_s, label, conf), labels incl. MISSING','integer-tick intervals; MISSING metadata separate from five acoustic classes').replace('ONNX Runtime fallback flag', 'ONNX Runtime fallback candidate; separate size/performance gate').replace('| Data path to the other tier | None |', '| Data path to the other tier | Approved cloud models → pilot only |'); p.write_text(s)
        p=Path('docs/research/RESEARCH_PLAN.md'); s=p.read_text(); s=s.replace('Weeks count from W0 = 2026-09-25.', 'Week labels follow the milestone calendar in §5 (W0 starts 2026-09-25).'); s=s.replace('on the in-country hardware | W1', 'on the in-country hardware | W1'); s=s.replace('E2, E3, E5, E7; S2, S3; legal answers; ADR-002/003', 'E2/E3 real phases only after G4; E5, E7; S2, S3; legal answers; ADR-002/003/011/012; G1–G2'); s=s.replace('S5, S7, S9; ADR-001/004/005', 'S5, S7, S9; ADR-001/004/005; G0 and contract draft'); s=s.replace('E1 staged/adult fixtures only (classroom work waits for G4); S1, S4, S6', 'E1 staged/adult fixtures only (classroom work waits for G4); start S1, S4, S6');
        # Keep S identifiers ordered.
        lines=s.splitlines(); idx=next(i for i,l in enumerate(lines) if l.startswith('| S11 |')); row=lines.pop(idx); idx=next(i for i,l in enumerate(lines) if l.startswith('| **S10**')); lines.insert(idx+1,row); p.write_text('\n'.join(lines)+'\n')
        PY
        git diff --check
        python3 - <<'PY'
        from pathlib import Path
        import re, subprocess
        paths=subprocess.check_output(['git','diff','--name-only'],text=True).splitlines()+['docs/research/IMPLEMENTATION_REVIEW.md']
        missing=[]
        for path in paths:
         p=Path(path); s=p.read_text()
         assert path.endswith('.md') and path.startswith('docs/'),path
         assert len(re.findall(r'^```',s,re.M))%2==0,path
         for link in re.findall(r'\]\(([^)]+)\)',s):
          if '://' in link or link.startswith('#'):continue
          target=(p.parent/link.split('#')[0])
          if not target.exists():missing.append((path,link))
        print('Documentation-only changes; code fences balanced.')
        print('Missing relative Markdown link targets:',missing)
        print('Reviewed files:',len(paths))
        PY
    Documentation-only changes; code fences balanced.
    Missing relative Markdown link targets: []
    Reviewed files: 7
    ✓ • 0ms

## Assistant

Reviewed and updated seven planning documents. No implementation code changed.

The architecture remains viable as a proposal, but needs explicit validation gates. Plans now address missing audio, uncertain language labels, cloud isolation, backup privacy, sync/withdrawal behavior, and evaluation sequencing.

Findings and acceptance gates are in [IMPLEMENTATION_REVIEW.md](/Users/anteneh/Documents/Repos/Echo/docs/research/IMPLEMENTATION_REVIEW.md).

Documentation checks passed. Builds, benchmarks, and runtime tests were not run.

## User

I plan to test the setup on undergraduate STEM classrooms first before the intended demographics, ensure this is accounted for and re-check the documentations of the infrstructure we'll use and prepare test suits to be run after the implementation is done.

## Assistant

I’ll add undergraduate STEM classrooms as the first test phase, re-check the proposed infrastructure against current official documentation, and prepare test suites for use after implementation. I’ll keep product implementation out of scope.

## Activity

    $ git status --short; rg --files -g 'AGENTS.md' -g '*.md' -g '*test*' -g '*requirements*' -g '*pyproject*'; cat docs/research/IMPLEMENTATION_REVIEW.md
     M docs/DIMTS_ARCHITECTURE_CONTEXT.md
     M docs/research/ASSUMPTIONS.md
     M docs/research/CLAIMS_AUDIT.md
     M docs/research/CODEBASE_STRUCTURE.md
     M docs/research/README.md
     M docs/research/RESEARCH_PLAN.md
    ?? docs/research/IMPLEMENTATION_REVIEW.md
    docs/DIMTS_ARCHITECTURE_CONTEXT.md
    docs/legal/README.md
    docs/research/HOSTING_OPTIONS.md
    docs/research/CLAIMS_AUDIT.md
    docs/research/RESEARCH_PLAN.md
    docs/research/IMPLEMENTATION_REVIEW.md
    docs/research/README.md
    docs/research/ASSUMPTIONS.md
    docs/research/TO_OBTAIN.md
    docs/research/BIBLIOGRAPHY.md
    docs/research/CODEBASE_STRUCTURE.md
    # Architecture and implementation-plan review
    
    Reviewed 2026-09-27. **Planning changes only; no implementation, builds, device benchmarks or deployment performed.**
    
    The edge / in-country pilot / separate Huawei Cloud demo architecture is a reasonable proposal. It is not ready for unrestricted implementation: the indicator and streaming contracts need to be frozen, device feasibility remains unmeasured, and real-data collection/release has unresolved ethics and security gates. This review governs unresolved implementation details; the architecture owns product constraints, RESEARCH_PLAN owns dates, and CODEBASE_STRUCTURE owns the proposed layout. Prior audits remain dated evidence, not proof that a capability was built.
    
    ## Verified repository baseline
    
    - `edge/android/settings.gradle.kts` includes only `:app`. Other Android folders contain `.gitkeep`, not Gradle modules.
    - `MainActivity.kt` is a Compose “Hello Android” starter. There is no capture, inference, indicator, storage, enrolment or sync implementation.
    - `app/build.gradle.kts` declares minSdk 29, compile/target SDK 37 and arm64-v8a. The version catalog declares AGP 9.4.1 and Kotlin 2.2.10. These are file contents, not a verified compatible build toolchain; G0 must resolve/build them before selecting upgrades or downgrades.
    - `AndroidManifest.xml` has `allowBackup=true`; the referenced backup/extraction XML contains template rules. Microphone permissions/service are absent. These are planned work, not regressions in an existing audio app.
    - ML, indicator, server, dashboard and infrastructure trees are placeholders. No executable tests beyond Android template tests establish product behavior. `docs/adr/` contains no ratified decisions.
    - Research PDFs are already tracked. Adding ignore rules later will not remove tracked files or history; a future public-release audit must cover the actual published tree and history. Do not delete research material as part of this review.
    
    ## Findings and required plan changes
    
    | ID | Priority | Evidence / failure mode | Planned resolution |
    |---|---|---|---|
    | V1 | High | Main architecture used MindNLP/MindAudio, all-INT8 inference and one deployment while the research pack proposes replacements and two deployments. | Reconcile §7–§12; PyTorch/HF teacher, owned features, measured precision variants, isolated cloud demo. These remain candidates until S1–S6 pass. |
    | V2 | High | Missing spans, bridged silence, zero denominators and short-segment LID inheritance could fabricate or bias indicators. | §5 v2: explicit observed/missing time, original timing for latency, null availability, unknown LID, deterministic postprocessing. Freeze all fixture outcomes before coding. |
    | V3 | High | 1–2 s windows every 0.5 s duplicate predictions unless emission ownership is defined; smoothing needs future context. | Specify each output tick's owner, bounded lookahead/finalisation, ring/queue sizes, end flush and interruption resets. Report both throughput and emission latency. |
    | V4 | High | Cloud boundary assumed public/synthetic audio and model weights were automatically non-personal; diagram implied pilot export. | Synthetic record demo by default. Public voices, derived mixtures, weights and logs need provenance/privacy/licence review. No pilot audio, features, soft labels, weights or identifiers flow to cloud. Separate endpoints, identities, credentials, logs and backups. |
    | V5 | High | Encryption alone does not stop OS backup; starter allows backup. | Plan exclusions for sensitive files in both legacy and API 31+ cloud/transfer rules. Test backup/restore, key invalidation, reinstall and shared-phone access before real data. |
    | V6 | High | UUID idempotency alone does not address payload conflicts, forged teacher IDs or withdrawal races. | Ownership derived server-side, transaction-before-ACK, identical retries only, revocation precedence, deletion of derived data and backup restore replay. |
    | V7 | High | k=5 alone does not prevent differencing or identify the intended weighting. | Count distinct teachers, aggregate each teacher first, fixed cohorts and windows, complementary suppression, no flexible drill-down; test repeated releases. Call it minimum-group suppression until disclosure review supports stronger claims. |
    | V8 | High | E1 was scheduled before ethics; E3 expects pilot data before collection; E2 asks teacher-independent evidence from 1–2 lessons. | Staged/adult fixtures before ethics; initial lessons are feasibility only. Real validation follows consent, collection and independent splits; dates move if gates slip. |
    | V9 | Medium | S3 demanded equality between differently trained graphs and quantized outputs. | Compare each export to its own source FP baseline; cross-framework tolerance only for equivalent weights/graphs. Set quantized task/indicator loss budgets separately. |
    | V10 | Medium | 16 KB check covered ELF load alignment only and treated sideloading as a fix. | Inspect all native dependencies (Lite, SQLCipher, fallback runtime), APK packaging/ZIP and runtime behavior. Sideloading is acceptable only on a tested compatible device; it cannot repair native incompatibility. |
    | V11 | Medium | Hosting alternatives and database engines were treated as interchangeable. | Choose one initial engine and test its exact driver/migrations/auth/TLS/backup/restore. PostgreSQL lineage is not compatibility evidence; choosing GaussDB adds its own spike. Host location includes backup/log processing, not just a marketing page. |
    | V12 | Medium | “Never measured”, “validated”, all-MindSpore and “language-agnostic” claims exceeded evidence. | Mark novelty, portability, performance and classroom utility as hypotheses. Report domain/language splits and abstention coverage; avoid policy-compliance or causal coaching claims. |
    
    ## Ordered implementation gates
    
    These are future acceptance criteria, not tasks executed during this review. Gates can overlap only when their prerequisites permit it.
    
    | Gate | Owner | Required evidence before proceeding |
    |---|---|---|
    | G0 — baseline and feasibility | Sys + ML | Reproducible clean Android starter build with pinned toolchain; measured target phone/OS/page size; S1 Lite invocation; S4 training hardware; S6 exact DB driver/migration path. Decide failures through ADRs instead of silently swapping stacks. |
    | G1 — contracts | Lead + Sys + ML | ADR-001/002/004/005/011/012 candidate decisions; full schema v2 and model manifest; exact timeline/postprocess/quantile semantics; proposed Python/Kotlin golden cases reviewed with expected results; feature config and chunk ownership frozen. ADR-002 remains provisional until E5. |
    | G2 — offline vertical slice | Sys + ML | Authorised synthetic/staged PCM → features → candidate inference → indicators → encrypted lesson record → local report on target phone. Bounded queues and a 40 min soak; process death produces an explicit interrupted session with no invented unobserved audio. No cloud or real classroom data prerequisite. |
    | G3 — reproducible competition demo | Sys + Lead | Synthetic fixtures → authenticated ingest → one dashboard/report view on isolated Huawei Cloud deployment; licence manifest, seeded generator, documented model versus fixture outputs and independent reproduction. Public-audio playback is optional and separately reviewed. |
    | G4 — real-data readiness | Lead + Sys | Ethics/consent/legal answers, confirmed in-country processing/storage/backups, mode separation, backup exclusions, shared-device lock/logout, Research Mode encrypted transfer verification, revocation/purge tests. Classroom collection cannot precede this gate. |
    | G5 — validity and controlled release | Lead + ML + Sys | Teacher/school-independent frozen real test set, annotation agreement, indicator error with intervals/coverage, quantized-device equivalence, coaching co-design, aggregate disclosure tests. Release only indicators that pass; invalid/unavailable outputs are null with reasons. |
    
    ## Contract checklist for G1
    
    - Fixture cases: empty/zero-duration input, all silence, all missing, all overlap, interrupted teacher/child transition, 0.25/0.3/0.5/10/60 s boundaries, smoothing ties, final partial minute, mixed-language turn, LID abstention, dropped chunks and end flush. Validate interval ordering, complete coverage and finite values.
    - Rates use observed time; report elapsed time separately. Specify count-ratio qualification and quantile interpolation explicitly. Reports must preserve missing/unknown coverage rather than treating it as zero activity.
    - Teacher role is an acoustic hypothesis: define other adults, playback speech, quiet children and teacher-plus-child overlap in the annotation guide. Pilot-gate each class. A merged CHILD_UNSPECIFIED fallback requires new class/spec versions and disables I-3/I-4 and response-type-specific latency.
    - `LessonRecord` includes schema/spec/model/feature/postprocess versions, immutable ID, consent/enrolment reference, elapsed/observed/missing time, indicator availability/reasons and denominators. No full timeline leaves Deployment Mode. Set strict field allowlists, ranges, size limits and supported versions; examples in the other plans are incomplete, not executable schemas.
    - Weekly offline reports need the same versioned deterministic rule table as server reports. Define week boundaries in Africa/Addis_Ababa, equal teacher weighting for district outputs, compatible-version grouping, minimum valid duration/lessons and when cached/server reports supersede local reports. G1 must fix thresholds; coaching stays disabled until G5.
    - Model manifest pins sample rate, resampler, frame centering/padding, FFT/window/mel/log conventions, streaming normalisation, tensor axes/dtypes/class order, quantization scales, receptive field/lookahead, chunk/state-reset behavior and hashes. Calibration and feature parity cover warm-up and chunk boundaries.
    - Partition real data before adaptation, pseudo-labelling, distillation, calibration or threshold tuning. Public corpus overlap also needs speaker/source checks. Small pilot splits may not support school-level inference: report that limit rather than claim independence.
    
    ## Verification to schedule
    
    - Sync/auth: other-teacher read/delete/write denial; wrong school; duplicate same bytes/semantics; conflicting same ID; lost ACK; partial batch; offline retry; expiry; withdrawal during upload; restore after deletion. Never log record bodies/tokens.
    - Capture/storage: permission denial/revocation, call/mic silencing, external-mic removal, background restrictions, screen lock, thermal throttling, low storage, crash/restart, key loss and backup/transfer. Test API 29 and a modern target-compatible device. Benchmark release builds with the whole pipeline.
    - Aggregates: 4 versus 5 distinct teachers, many lessons from one teacher, adjacent/overlapping cohorts, suppression at totals, withdrawal and repeated weekly releases. No district release if only six pilot teachers make useful safe grouping impossible; use synthetic demo aggregates.
    - Research transfer: separate consent and endpoint, ciphertext integrity and durable in-country receipt before local deletion; retained audio TTL/storage cap and failure/retry behavior. No Research Mode secrets or audio in deployment logs/CI.
    - Public release: dataset/model licensing includes derived student weights, attribution and redistribution; CC-BY-NC is not blanket competition permission. Re-run from licensed synthetic fixtures without pilot data. Audit tracked PDFs/history and secrets before publishing.
    
    ## Sources and limits
    
    Repository findings above were inspected directly. Existing research audit findings are carried forward with their original dates; this review did not revalidate every paper, competition rule, provider price or legal interpretation. No runtime/model accuracy claim is verified.
    
    Official Android documentation checked on 2026-09-27:
    
    - [Sharing audio input](https://developer.android.com/media/platform/sharing-audio-input): capture can be silenced and recording callbacks expose changes; plan missingness separately from acoustic silence.
    - [Auto Backup](https://developer.android.com/identity/data/autobackup): eligible app data is backed up by default; Android 12+ uses separate cloud-backup/device-transfer controls. Test explicit exclusions, including restore behavior.
    - [16 KB page sizes](https://developer.android.com/guide/practices/page-sizes): native libraries and APK packaging need checks and runtime testing. All bundled native dependencies are in scope, not just the inference AAR.
    
    Outstanding legal decisions stay in WS-F; outstanding current competition requirements stay in WS-G. Neither is resolved merely by choosing an architecture.
    ✓ • 0ms

## Assistant

Which languages and session types do you expect in the undergraduate STEM trial (for example, English lectures, Amharic discussions, or lab sessions)? I’ll keep these configurable and treat this phase as setup validation, with early-grade validation still required.

## Activity

    Searched the web for site.mindspore.cn lite r2.10.0 Android java download operator GRU, site.support.huaweicloud.com modelarts OBS encrypted buckets same region, site.docs.opengauss.org psycopg Python connect SSL, site.zetetic.net sqlcipher sqlcipher-android Room SupportOpenHelperFactory

## Activity

    $ cat edge/android/gradle/wrapper/gradle-wrapper.properties edge/android/gradle/gradle-daemon-jvm.properties; cat docs/research/RESEARCH_PLAN.md
    #Sat Sep 26 17:34:07 EAT 2026
    distributionBase=GRADLE_USER_HOME
    distributionPath=wrapper/dists
    distributionSha256Sum=bbaeb2fef8710818cf0e261201dab964c572f92b942812df0c3620d62a529a01
    distributionUrl=https\://services.gradle.org/distributions/gradle-9.6.0-bin.zip
    networkTimeout=10000
    validateDistributionUrl=true
    zipStoreBase=GRADLE_USER_HOME
    zipStorePath=wrapper/dists
    #This file is generated by updateDaemonJvm
    toolchainUrl.FREE_BSD.AARCH64=https\://api.foojay.io/disco/v3.0/ids/cf726b4a1c84b50457225f9bba6d7650/redirect
    toolchainUrl.FREE_BSD.X86_64=https\://api.foojay.io/disco/v3.0/ids/fa1e318c287360478e3c83a9a3ef1007/redirect
    toolchainUrl.LINUX.AARCH64=https\://api.foojay.io/disco/v3.0/ids/cf726b4a1c84b50457225f9bba6d7650/redirect
    toolchainUrl.LINUX.X86_64=https\://api.foojay.io/disco/v3.0/ids/fa1e318c287360478e3c83a9a3ef1007/redirect
    toolchainUrl.MAC_OS.AARCH64=https\://api.foojay.io/disco/v3.0/ids/c2dd35c9d0aaf0ba6ad0791320f99dfc/redirect
    toolchainUrl.MAC_OS.X86_64=https\://api.foojay.io/disco/v3.0/ids/e5810bd7fd1f8a586644409d395a7e55/redirect
    toolchainUrl.UNIX.AARCH64=https\://api.foojay.io/disco/v3.0/ids/cf726b4a1c84b50457225f9bba6d7650/redirect
    toolchainUrl.UNIX.X86_64=https\://api.foojay.io/disco/v3.0/ids/fa1e318c287360478e3c83a9a3ef1007/redirect
    toolchainUrl.WINDOWS.AARCH64=https\://api.foojay.io/disco/v3.0/ids/7b3c4877c0749019e6805bb61e421497/redirect
    toolchainUrl.WINDOWS.X86_64=https\://api.foojay.io/disco/v3.0/ids/d76df094a9cbbabd3b08251f9e61444a/redirect
    toolchainVersion=25
    # Research and implementation plan — Dimts (reviewed proposal, v1.1)
    
    Status as of 2026-09-27. Implementation must follow the ordered gates in [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md); none is recorded as passed. This plan builds on [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md), which is now updated with the full texts in [`papers/`](papers/). Related files:
    
    - [`ASSUMPTIONS.md`](ASSUMPTIONS.md) — every assumption the design still rests on.
    - [`CODEBASE_STRUCTURE.md`](CODEBASE_STRUCTURE.md) — the repository layout that follows from these findings.
    
    Owners follow §15 of the architecture document:
    
    - **Lead** — Anteneh
    - **ML** — Person 2
    - **Sys** — Person 3
    
    Week labels follow the milestone calendar in §5 (W0 starts 2026-09-25). Dates are targets: real-data phases of E1–E7 move with ethics/consent and G4; public/staged work and the synthetic demo continue independently. Sources are cited (Author, year) and listed in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).
    
    ---
    
    ## 1. What the evidence now says
    
    1. **The problem is real and measured.**
       - Only 32.4% of Grade 2–3 pupils reached the top two reading benchmarks in 2018, essentially unchanged since 2014 (31.3%) and 2016 (34.3%). Only 6.2% read fluently with full comprehension (AIR, 2019).
       - Only 39% of mother-tongue teachers are Proficient-or-above, and Sidaamu Afoo teachers sit in the 22–33% band (AIR, 2020).
    2. **Observation-based feedback is associated with better reading, but it is patchy.** Where a school has someone who observes mother-tongue classes, and observes more often, pupils read better (AIR, 2019, pp. 53–54). There is no standardised or frequent measure. This is the gap Dimts fills. "No feedback at all" overstates it.
    3. **Continuous automated classroom-language measurement remains a novelty hypothesis.** Earlier work used handwritten observation notes (Heugh et al., 2007) or children's self-reports (Piper et al., 2016). Run C1 before making a first-of-kind claim; these studies alone cannot prove absence of other systems.
    4. **Audio-only classroom analytics work at coarse grain.** Reported results: ~90% (Owens et al., 2017), >80% (Schlotterbeck et al., 2021), F1 0.64–0.78 (Donnelly et al., 2016). Fine-grained teacher-vs-child separation is still hard: 69% in Wang et al. (2025).
    5. **Talk-time feedback changes behaviour.** In an RCT, automated feedback cut talk share by 5 pp (Demszky & Liu, 2023). No such evidence exists for choral ratio or language share yet.
    6. **The competition has changed shape.**
       - 2026–27 requires an AI application deployed on **Huawei Cloud** with a **visual demo page**.
       - Preliminary and National stages score **Innovation 60% / Application value 40%**.
       - Regional-stage entries must be reproducible and open source.
       - The Preliminary runs **Nov–Dec 2026**, earlier than the architecture doc's feature freeze.
    7. **Some stack assumptions broke:**
       - MindNLP is discontinued and MindAudio is unmaintained.
       - Lite has no INT8 GRU.
       - The AAR is 4 KB-aligned.
       - Jetpack Security is deprecated.
       - Sidama speech data and models do exist.
    
    ---
    
    ## 2. Principles
    
    1. **Evidence bar.** Only **VERIFIED** claims go into the thesis or pitch. Anything weaker is written as "reported by …".
    2. **One log.** Every search goes into `docs/research/search-log.csv` with columns `date, database, query, hits, screened, kept, notes`, so the review is PRISMA-style.
    3. **Check before you build.** Don't ratify an ADR until its research question closes.
    4. **Two data worlds.** Personal data (the pilot) stays in-country. The competition cloud demo holds synthetic or public data only (ASSUMPTIONS A-C3).
    5. **Reproducible by a stranger.** The Regional stage re-runs your code and data, so every number in the pitch must come from a script in `ml/eval/` or `tools/demo/`.
    
    ---
    
    ## 3. Workstreams
    
    Status is **Done**, **Open** or **Blocked** (waiting on an item in [`TO_OBTAIN.md`](TO_OBTAIN.md)).
    
    ### WS-A: Problem evidence (Lead)
    
    | RQ | Question | Status | Result or next step |
    |---|---|---|---|
    | A1 | EGRA 2014–2018 results | **Done** | 31.3 / 34.3 / 32.4% top-two levels, Grades 2–3, 7 languages. The Sidaamu Afoo 2016 value is flagged as unreliable (AIR, 2019). |
    | A2 | Pre-primary figures | **Done** | ESAA 2024/25: GER 59.8% excluding Amhara. The 75% O-class/child-to-child share is from ESAA 2021/22. Class size in Grades 1–6: 54.2 nationally, 59.6 in Sidama. |
    | A3 | How often are teachers observed, by whom, and with what feedback? | **Open** | EGRA Table 16 shows association but not base rates. Run 5–10 interviews (teachers, unit leaders, woreda supervisors) during W1–W3 school visits. Output: a 1-page memo. Due W3. |
    | A4 | Actual classroom languages and code-switching in the pilot schools | **Open** | Heugh et al. (2007) observed routine Amharic–English switching. Confirm for Sidama and Hawassa schools in W1 visits. This sets the LID label set. Due W2. |
    | A5 | Home vs school vs teacher factors | **Done** | MTTCA ranks home, then school, then teacher (AIR, 2020). Framing updated. |
    | A6 | Current mother-tongue policy grade span | **Blocked** (ETP 2023 text) | Needed for the §2.4 wording. Due W2. |
    
    ### WS-B: Pedagogy and indicator validity (Lead)
    
    The core thesis question: *Do Dimts' acoustic indicators measure something Ethiopian teachers and experts recognise as instructional quality, and in which direction?*
    
    | RQ | Question | Status | Method | Output | Due |
    |---|---|---|---|---|---|
    | B1 | Construct and normative direction for I-1 to I-8 | Open | Build the indicator × literature matrix (§9 reading list). I-1 now has RCT support (Demszky & Liu, 2023). | `docs/indicator-rationale.md` | W2 |
    | B2 | Local expert validation | Open | Panel of 5–8 people (Hawassa College of Education, READ alumni, E-Learning Directorate) rating relevance and direction. | Inputs to ADR-010 | W5 |
    | B3 | O-class specifics | Open | CLASS, MELE, Teach ECE; Alemu (2025). | Scope decision D-4 | W3 |
    | B4 | Feedback design | Open | Kraft et al. (2018); Demszky et al. (2023, 2025); Jensen et al. (2020); Hattie & Timperley (2007). | Report copy rules | W4 |
    | B5 | Surveillance perception | Open | Birnhack & Perry-Hazan (2021): nearly all teachers weigh their own privacy. Co-design with 2 teachers. | Consent copy | W3 |
    | B6 | I-3 as a balance indicator | Open | Heward et al. (1989) vs Pontefract & Hardman (2005). | I-3 reporting rule | W2 |
    
    ### WS-C: Prior systems and novelty (Lead + ML)
    
    - **C1: Systematic search** (protocol in §8). Novelty statement for I-6 and choral detection. Due W2.
    - **C2: Comparison table.** Done except the IEEE papers; see the table below.
    - **C3: Competition benchmarking.** Review previous Innovation winners in the Learning Space (the page links "reference entries from previous competitions"). Due W4.
    
    | System | Input → output | Setting | Result | Take-away for Dimts |
    |---|---|---|---|---|
    | DART (Owens et al., 2017) | Volume/variance → single, multiple or no voice | 1,486 US college sessions | ~90% | Closest no-ASR precedent; coarse classes |
    | Schlotterbeck et al. (2021) | Lavalier + smartphone; amplitude, mel and MFCC statistics → COPUS practices | 42 lessons, 19 teachers, Chile | >80% accuracy (2 practices); 7 min per hour of audio | Cheap-phone precedent; **clip-on mic** |
    | Chanchal & Zualkernan (ED626900) | Mel spectrograms → Stallings categories | Semi-rural Pakistan, phones | F1 0.83; 0.72–0.81 with 5–20% labels | Semi-supervised learning with few labels |
    | Donnelly et al. (2016) | Teacher audio, speech/rest, ASR, acoustic features → 5 segment types | 76 classes, 11 teachers | F1 0.64–0.78, teacher-independent | Validate across teachers, not frames |
    | Jensen et al. (2020) | Headset, ASR, ML → discourse features | 142 sessions, 89% usable | Moderate accuracy | Room mics noisy; usability metric |
    | M-Powering Teachers (Demszky & Liu, 2023; Demszky et al., 2023, 2025) | Transcripts → talk time and uptake feedback | US; RCTs | −5 pp talk time; +10–13% uptake | Automated feedback changes practice |
    | Classroom diarization (Wang et al., 2025) | ECAPA + Whisper | US group work | Teacher-vs-child 69% | Realistic difficulty |
    | VTC (Lavechin et al., 2020) | SincNet + RNN → 4 voice types | 260 h, 10 languages | Beats LENA | Frame-classifier baseline |
    | LENA feedback (Wang et al., 2013) | LENA → talk ratio | US maths PD | SECONDARY | Teacher-facing precedent |
    | Shapsough et al. (2016) | Mobile EGRA + MQTT | LMIC assessment | Low power | Sync design context |
    | Human tools: Teach, Stallings, CLASS, and Heugh et al. (2007) field notes | Observers | Global / Ethiopia | Validated or qualitative | What Dimts complements |
    
    ### WS-D: ML and data (ML)
    
    | Experiment | Question | Method | Feeds | Due |
    |---|---|---|---|---|
    | E1 | Usable audio: desk vs clip-on | W0–W1 authorised staged/adult fixtures; real classroom comparison only after G4. Measure SNR, clipping and RT60 estimate. Compare desk phone with a wired lavalier into the same phone (Schlotterbeck et al., 2021; Jensen et al., 2020). | Capture spec; ASSUMPTIONS A-T4 | W1 |
    | E2 | How hard is T-1 on real Ethiopian audio? | 1–2 annotated lessons for feasibility only; collect multiple teachers/schools before independent evaluation; VTC, pyannote-seg-3.0 and a DART-style energy baseline; per-class F1 with teacher-independent splits. | §14.1 realism; 4-class fallback | W4 |
    | E3 | LID accuracy at 2 / 3 / 5 s | MMS-LID-256 (amh/sid/eng + other) on FLEURS, Afrivoice and pilot teacher segments. | LID threshold (audit 5d) | W3 public/staged baseline; classroom phase after G4 |
    | E4 | Can choral vs single child speech be separated? | Synthetic choral mixtures from Afrivoice speakers + pitch/formant scaling; CountNet-style head; validate on real clips. | 5-class vs 4-class | W5 |
    | E5 | Student architecture under Lite ops | CRNN (FP16/FP32 GRU) vs convolution-only TCN (INT8) vs tiny conformer. Measure F1, MB and RTF. | ADR-002 | W4 |
    | E6 | Distillation gap | KD from the D-1 teacher to the E5 winner. | §14.1 | W8 |
    | E7 | Error propagation | Simulate confusion matrices on golden timelines and compute indicator error (Gautheron et al., 2025). | Final §14 targets | W3 |
    | E8 | Dataset register | Licence, gating, register, overlap between WAXAL and Afrivoice. | `ml/data/DATASETS.md` | W1 |
    | E9 | Competition "dataset selection and processing" section | Document D-3 to D-6 plus the synthetic-lesson generator as the Template 1 dataset story. | Preliminary submission | W6 |
    
    ### WS-E: Stack and device spikes (Sys + ML)
    
    | Spike | Pass criterion | Due |
    |---|---|---|
    | S1 | `.ms` INT8 hello-world through the 2.10.0 AAR on a 2 GB phone | W0 |
    | S2 | RTF ≤ 0.3 and RSS ≤ 300 MB via the bundled `benchmark` tool and the in-app harness | W3 |
    | S3 | Each FP export agrees with its own source graph on fixed features (initial tolerance 1e-4, justified per model); compare frameworks only for weight-equivalent graphs. Assess PTQ through task/indicator degradation separately; record actual precision/fallback ops | W3 |
    | S4 | MindSpore 2.10 trains a toy model on the in-country hardware | W1 |
    | S5 | Confirm exact services before choosing a region; ModelArts, OBS and ECS all in **AF-Johannesburg**; flavours and prices recorded; any required unencrypted training bucket restricted to approved corpus objects, never anonymous/public access; verify licence/privacy separately | W1 |
    | S6 | openEuler LTS + openGauss + psycopg connect (SASL/sha256 workaround); **host chosen from [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md)** — Zergaw VPS/DBaaS, INSA G-Cloud or Ethio Telecom teleCloud, not the on-campus box, unless the university prefers self-hosting | W1 |
    | S7 | Microphone foreground service; `AudioRecordingCallback` detects call-silencing; 40-minute battery test | W2 |
    | S8 | 16 KB decision: test Lite, SQLCipher and any fallback native libraries, ELF/ZIP alignment and runtime; sideload only on verified compatible phones | W6 |
    | S9 | SQLCipher + Keystore key; backup/transfer exclusions, key-loss/reinstall and shared-phone tests before G4 | W2 |
    | **S10** (new) | **Competition cloud stack:** ECS + RDS for PostgreSQL (or GaussDB) + OBS in AF-Johannesburg serving the demo page and lesson simulator with synthetic lessons. Redeployable from a script. | W5 |
    | S11 | Sync ownership, ACK/conflict/retry, withdrawal and restore-purge tests per G4; fixed-cohort disclosure tests before G5 | Before pilot/release |
    
    ### WS-F: Legal and ethics (Lead)
    
    Primary text: Proclamation 1321/2024. The supplied Gazette copy is in `docs/legal/`; use it with the legal advisor (see TO_OBTAIN #2).
    
    Questions for the legal advisor, answered in writing by W3:
    
    1. Is the university the data controller, and does Art. 33 registration apply to student research?
    2. Is transient in-RAM processing of children's voices processing of a minor's data (Arts. 2(16), 11)? What consent does it need?
    3. Research Mode child audio: Art. 11 parental consent, or the Art. 54 research exemption?
    4. Is raw voice audio without speaker identification *biometric* under Art. 2(6)?
    5. Are teacher-linked indicators personal data, and does in-country storage satisfy Art. 22(1)?
    6. Are a DPIA (Art. 47) and a DPO (Art. 40) needed?
    7. Could the weekly report be read as an automated decision about the teacher (Art. 31)?
    8. **New:** does a competition demo on Huawei Cloud (AF-Johannesburg) holding only synthetic lessons, plus scripted demo accounts, fall entirely outside the Proclamation? Get this in writing.
    
    Ethics: the Hawassa IRB (the critical path); assent and consent forms in Amharic, Sidaamu Afoo and English; school and woreda letters.
    
    ### WS-G: Competition (Lead)
    
    Northern Africa division, 2026–27 (see audit §7):
    
    | Item | Requirement | Plan |
    |---|---|---|
    | Topic | AI application powered by Huawei Cloud | Dimts cloud tier: indicator analytics, teacher report generation, district dashboard and a lesson simulator, all on Huawei Cloud with synthetic data |
    | Huawei Cloud service | ≥ 1 service (ECS, RDS, DCS …) | ECS + RDS/GaussDB + OBS (+ ModelArts for training on public data) |
    | Visual demo page | Mandatory | `dashboard/` + `tools/lesson-simulator` web page |
    | Hardware | Optional, team-provided | Live phone demo as a bonus |
    | Prelim/National scoring | Innovation 60 / Application value 40 | Lead with the no-ASR, language-agnostic, on-device design plus the I-6 novelty. Back application value with EGRA and MTTCA numbers. |
    | Regional reproducibility | Code, datasets and README re-run by the committee | Synthetic dataset generator and scripts in the repo; the pilot data is never needed to reproduce the demo |
    | Open source | GitHub or Gitee for Regional and Global shortlists | Keep `docs/research/papers/` out of the public repository |
    
    ---
    
    ## 4. Decisions that need the team
    
    | ID | Decision | Recommendation |
    |---|---|---|
    | D-1 | Teacher-model framework (MindNLP is gone) | PyTorch/HF teacher on the in-country node, MMS-LID-256 as the LID teacher, and the student trained in MindSpore. Describe only the training/runtime path actually demonstrated; MindSpore remains subject to S4 and export/device gates. |
    | D-2 | Student architecture | Decide on E5 data; default to a convolution-only TCN if RTF is tight. |
    | D-3 | Distribution | Sideload only on tested compatible phones; test every native library plus ELF/ZIP packaging and 16 KB runtime before wider distribution. |
    | D-4 | O-class in scope for coaching? | Wait for B3. |
    | D-5 | CC-BY-NC models (MMS) | Review exact upstream and derived-weight terms for competition use and redistribution; record attribution/restrictions and use approved alternatives if unresolved. |
    | **D-6** (new) | Competition cloud tier vs data sovereignty | Two deployments from one codebase: `infra/huawei-cloud` (demo, synthetic data, AF-Johannesburg) and `infra/openeuler` (pilot, personal data, Hawassa). There is no pilot-to-cloud path, including features, soft labels, weights, logs and backups. Public voices/mixtures need explicit approval; synthetic records are the demo default (ASSUMPTIONS A-C3). |
    | **D-7** (new) | Microphone | Decide from E1. If desk SNR is poor, ship a wired lavalier as part of the kit. |
    
    ---
    
    ## 5. Schedule (architecture §16 merged with the competition calendar)
    
    The exact Preliminary deadline within Nov–Dec 2026 is not yet known. The plan assumes **1 Dec 2026** until the templates or portal say otherwise (ASSUMPTIONS A-C1).
    
    | Week | Dates | Research and build | Competition |
    |---|---|---|---|
    | W0 | Sep 25 – Oct 4 | E1 staged/adult fixtures only (classroom work waits for G4); start S1, S4, S6; legal questions sent; ethics submitted; A3/A4 visits begin | Register team; download templates; confirm division with Huawei Ethiopia |
    | W1–2 | Oct 5 – 18 | A4, A6; B1, B6; C1; E8; S5, S7, S9; ADR-001/004/005; G0 and contract draft | Apply for Huawei Cloud vouchers |
    | W3–4 | Oct 19 – Nov 1 | A3 memo; B3, B5; E2/E3 real phases only after G4; E5, E7; S2, S3; legal answers; ADR-002/003/011/012; G1–G2 | Draft Template 1 |
    | W5–6 | Nov 2 – 15 | B2 panel; E4; **S10 cloud demo live**; S8 decision; E9 | Record demo video |
    | W7–8 | Nov 16 – 29 | E6 distillation; indicator validity v1 (§14.2) on pilot data | **Preliminary submission (assumed by 1 Dec)** |
    | W9–10 | Nov 30 – Dec 13 | Cross-language evaluation (§14.3); feature freeze | Preliminary review |
    | W11–16 | Dec 14 – Jan 31 | Pilot continues; literature review chapter from the search log | **National final (Jan 2027)** |
    | W17–24 | Feb – Mar 2027 | Open-source cleanup; reproducibility check by a non-team member | **Regional final (Feb/Mar 2027)** |
    
    ---
    
    ## 6. Open questions only the team can answer
    
    1. The training node's hardware (S4, D-1).
    2. The pilot teachers' phones (ASSUMPTIONS A-T1).
    3. Hawassa IRB turnaround.
    4. Play Store: ever?
    5. Who is the data controller?
    6. **New:** will the Amharic-medium pilot schools be in Hawassa or Sidama rather than Amhara region? ESAA 2024/25 excludes Amhara because schooling is disrupted there (ASSUMPTIONS A-P3).
    7. **New:** does the team accept making the product code open source (a Regional requirement)?
    
    ---
    
    ## 7. Search protocol (C1 and literature review)
    
    **Databases:** ERIC, ACL Anthology, ISCA Archive, IEEE Xplore, ACM DL (LAK, L@S, CHI, UMAP), EDM/JEDM, arXiv (cs.SD, eess.AS, cs.CL), Google Scholar, plus Scopus or Web of Science via the library.
    
    **Query blocks, combined with AND:**
    
    1. `classroom OR lesson OR teacher OR "early grade" OR preschool OR kindergarten`
    2. `audio OR acoustic OR "speech activity" OR diarization OR "voice type" OR "language identification" OR "code-switching"`
    3. `feedback OR observation OR "teacher talk" OR "wait time" OR choral OR "student talk" OR "language of instruction"`
    4. Optional LMIC filter: `Africa OR Ethiopia OR Kenya OR "low-income" OR "developing countries" OR India OR Pakistan`
    
    **Inclusion criteria:** empirical, automated or audio-based, classroom setting, 2010–2026, plus backward and forward snowballing from DART, VTC, M-Powering Teachers and Schlotterbeck et al. (2021).
    
    **Record for each paper:** setting, grade, language(s), microphone, input, classes, metric, result, and whether ASR was used.
    
    ---
    
    ## 8. Reading lists
    
    ★ marks the core list to read first. Full references are in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).
    
    **Ethiopian context**
    ★ AIR (2019, EGRA 2018 endline) · ★ AIR (2020, MTTCA) · ★ MoE (2025, ESAA 2024/25) · ★ Heugh et al. (2007) · Vujcich (2013) · Alemu (2025) · Kim (2020) · Wendie & Berhanu (2025) · Gobana (2025) · Yisihak & Damtew (2024) · Haile & Mendisu (2023) · Piper et al. (2016)
    
    **Pedagogy and indicator validity**
    ★ Rowe (1986) · ★ Tobin (1987) · ★ Howe et al. (2019) · ★ Liu & Cohen (2021) · ★ Pontefract & Hardman (2005) · ★ Heward et al. (1989) · ★ Kraft et al. (2018) · Hardman et al. (2009) · Alexander (2015) · Kelly et al. (2018) · Hattie & Timperley (2007) · Popova et al. (2021) · Popova & Evans (2016) · Glewwe & Muralidharan (2016) · Piper & Zuilkowski (2015) · Piper et al. (2018) · Cilliers et al. (2018) · Molina et al. (2018) · Stallings et al. (2014) · La Paro & Pianta (2003) · ★ Birnhack & Perry-Hazan (2021) · Shield & Dockrell (2003)
    
    **Prior systems**
    ★ Owens et al. (2017) · ★ Schlotterbeck et al. (2021) · ★ Demszky & Liu (2023) · ★ Jensen et al. (2020) · ★ Donnelly et al. (2016) · Demszky et al. (2023, 2025) · Jacobs et al. (2022) · Suresh et al. (2022) · Demszky & Hill (2023) · Wang & Demszky (2023) · Chanchal & Zualkernan (ED626900) · Zualkernan & Khan (2020) · Shapsough & Zualkernan (2017) · Shapsough et al. (2016) · Cosbey et al. (2019) · Li et al. (2020) · Wang et al. (2013, 2014) · Blanchard et al. (2016)
    
    **Speech and ML**
    ★ Wang et al. (2025) · ★ Lavechin et al. (2020) · ★ Gautheron et al. (2025) · ★ Pratap et al. (2023) · Diack et al. (2026) · Conneau et al. (2022) · Valk & Alumäe (2020) · Babu et al. (2021) · Baevski et al. (2020) · Hsu et al. (2021) · Radford et al. (2022) · Plaquet & Bredin (2023) · Bredin (2023) · Stöter et al. (2018) · Hinton et al. (2015) · Chang et al. (2022) · Schmid et al. (2023) · Jacob et al. (2018) · Snyder et al. (2015, 2018) · Desplanques et al. (2020) · Ko et al. (2017) · Park et al. (2019) · Potamianos & Narayanan (2003) · Lee et al. (1999) · Abate et al. (2005) · Gauthier et al. (2016) · Ganek & Eriks-Brophy (2018)
    
    **Infrastructure documentation**
    ★ MindSpore Lite 2.10.0 (release notes, downloads, quantization, operator list) · ★ Huawei Cloud ModelArts and Cloud Connect overviews · ★ 2026–27 Innovation Competition page · MindSpore 2.10.0 release notes · Android FGS, audio sharing, 16 KB pages, Jetpack Security notes · SQLCipher · RFC 9562 · openGauss psycopg2 · Label Studio
    
    **Legal and ethics**
    ★ Proclamation 1321/2024 · Sweeney (2002) · Hawassa IRB guidance (to obtain)
    ✓ • 0ms

## Activity

    Opened https://www.mindspore.cn/lite/docs/en/r2.10.0/use/downloads.html

## Activity

    Searched the web for site.openeuler.org 24.03 LTS SP lifecycle container docker, site.support.huaweicloud.com RDS PostgreSQL SSL verify-full connection, site.fastapi.tiangolo.com deployment docker workers https, site.labelstud.io guide security deployment telemetry

## Activity

    Opened https://docs.opengauss.org/en/docs/latest/developer_guide/connecting_to_the_database_using_ssl_psycopg.html

## Activity

    Searched the web for site.mindspore.cn lite r2.10.0 "GRU" "INT8", site.developer.android.com build releases agp-9-4-0-release-notes, site.support.huaweicloud.com intl en-us rds postgresql connecting SSL security group, site.openeuler.org lifecycle 24.03 LTS SP3

## Assistant

The STEM trial needs distinct labels and reporting: adult student speech cannot simply stand in for the planned child-speech classes. I’ll make it a technical trial with separate data and results, followed by early-grade validation.

The infrastructure review also found a concrete compatibility issue: openGauss documents `verify-full` TLS mode as unsupported. I’ll add certificate-identity tests and a database fallback criterion before either classroom trial.

## Activity

    Searched the web for site.mindspore.cn/lite/docs/en/r2.10.0 "Operator List", site.mindspore.cn/docs/en/r2.10.0 release CPU GPU Ascend

## Activity

    $ cat edge/android/gradle/wrapper/gradle-wrapper.properties edge/android/gradle/gradle-daemon-jvm.properties; rg -n 'GRU|operator|2.10|Zergaw' docs/research/BIBLIOGRAPHY.md
    #Sat Sep 26 17:34:07 EAT 2026
    distributionBase=GRADLE_USER_HOME
    distributionPath=wrapper/dists
    distributionSha256Sum=bbaeb2fef8710818cf0e261201dab964c572f92b942812df0c3620d62a529a01
    distributionUrl=https\://services.gradle.org/distributions/gradle-9.6.0-bin.zip
    networkTimeout=10000
    validateDistributionUrl=true
    zipStoreBase=GRADLE_USER_HOME
    zipStorePath=wrapper/dists
    #This file is generated by updateDaemonJvm
    toolchainUrl.FREE_BSD.AARCH64=https\://api.foojay.io/disco/v3.0/ids/cf726b4a1c84b50457225f9bba6d7650/redirect
    toolchainUrl.FREE_BSD.X86_64=https\://api.foojay.io/disco/v3.0/ids/fa1e318c287360478e3c83a9a3ef1007/redirect
    toolchainUrl.LINUX.AARCH64=https\://api.foojay.io/disco/v3.0/ids/cf726b4a1c84b50457225f9bba6d7650/redirect
    toolchainUrl.LINUX.X86_64=https\://api.foojay.io/disco/v3.0/ids/fa1e318c287360478e3c83a9a3ef1007/redirect
    toolchainUrl.MAC_OS.AARCH64=https\://api.foojay.io/disco/v3.0/ids/c2dd35c9d0aaf0ba6ad0791320f99dfc/redirect
    toolchainUrl.MAC_OS.X86_64=https\://api.foojay.io/disco/v3.0/ids/e5810bd7fd1f8a586644409d395a7e55/redirect
    toolchainUrl.UNIX.AARCH64=https\://api.foojay.io/disco/v3.0/ids/cf726b4a1c84b50457225f9bba6d7650/redirect
    toolchainUrl.UNIX.X86_64=https\://api.foojay.io/disco/v3.0/ids/fa1e318c287360478e3c83a9a3ef1007/redirect
    toolchainUrl.WINDOWS.AARCH64=https\://api.foojay.io/disco/v3.0/ids/7b3c4877c0749019e6805bb61e421497/redirect
    toolchainUrl.WINDOWS.X86_64=https\://api.foojay.io/disco/v3.0/ids/d76df094a9cbbabd3b08251f9e61444a/redirect
    toolchainVersion=25
    50:- Liu, J., & Cohen, J. (2021). Measuring teaching practices at scale: A novel application of text-as-data methods. *Educational Evaluation and Policy Analysis, 43*(4), 587–614. https://doi.org/10.3102/01623737211009267 **[Abstract]**
    72:- Jacobs, J., Scornavacco, K., Harty, C., Suresh, A., Lai, V., & Sumner, T. (2022). Promoting rich discussions in mathematics classrooms: Using personalized, automated feedback to support reflection and instructional change. *Teaching and Teacher Education, 112*, 103631. https://doi.org/10.1016/j.tate.2022.103631 **[Metadata]**
    140:- MindSpore. (2026). *MindSpore 2.10.0 release notes*. https://www.mindspore.cn/docs/en/r2.10.0/RELEASE.html **[Full]**
    141:- MindSpore. (2026). *MindSpore Lite* (Version 2.10.0) [Computer software]. https://github.com/mindspore-ai/mindspore-lite/tree/v2.10.0 **[Full]** (README, RELEASE.md, Java/AAR module, quantizer sources)
    142:- MindSpore. (2026). *Downloading MindSpore Lite (r2.10.0)*. https://www.mindspore.cn/lite/docs/en/r2.10.0/use/downloads.html **[Full]**
    143:- MindSpore. (2026). *Quantization (r2.10.0)*. https://www.mindspore.cn/lite/docs/en/r2.10.0/advanced/quantization.html **[Full]**
    144:- MindSpore. (2026). *Lite operator support (r2.10.0)*. https://www.mindspore.cn/lite/docs/en/r2.10.0/reference/operator_list_lite.html **[Full]**
    ✓ • 0ms

## Activity

    Opened https://www.mindspore.cn/lite/docs/en/r2.10.0/reference/operator_list_lite.html

## Activity

    Searched for 'GRU'

## Activity

    $ mkdir -p tests/acceptance docs/testing
        cat > docs/research/STEM_TRIAL_PLAN.md <<'EOF'
        # Undergraduate STEM technical trial
        
        User-directed first classroom phase, 2026-09-27. Intended product population remains Ethiopian O-class and Grades 1–3. This phase validates the setup before work with that population; it does not validate child-speech models or early-grade pedagogy.
        
        ## Stages and entry/exit criteria
        
        | Stage | Entry | Work | Exit / evidence |
        |---|---|---|---|
        | P0: synthetic/staged | G0–G2 technical work; authorised fixtures | Offline pipeline, synthetic API, device faults, storage and recovery | Core, device and infrastructure suites; no unresolved critical failures |
        | P1: undergraduate STEM | G4-U: institution/ethics decision and consent covering students and instructors; deployment/privacy tests pass | Real lectures first, then discussion/tutorial/lab sessions actually available; test desk versus external mic, capture continuity, runtime, offline sync and workflow | Technical report stratified by session/language/device, defects resolved, no data loss or privacy boundary failure; explicit decision to proceed |
        | P2: intended early-grade population | P1 technical exit plus G4-E: separate age-appropriate approvals/consent and school permissions | Collect/annotate intended population, re-evaluate microphones and all models; freeze independent test set | G5-E: early-grade accuracy/indicator validity and coaching co-design; only then educational pilot release |
        
        Do not assume undergraduates are all adults. Recruitment must establish eligibility through the approved process; minors require the applicable approved procedure or exclusion before capture. Students can decline without academic consequences, with a practical non-recording alternative. Instructor agreement alone does not cover students. Keep raw audio, indicators, consent and annotator access in-country for P1 just as for P2. This plan does not make a new legal determination; institutional review governs participation.
        
        Default planning sample: at least 3 instructors across 2 STEM courses, 6 sessions total, and 2 rooms if feasible. These are feasibility targets, not a powered validity study or substitute for the separate ≥6-teacher early-grade pilot. Record actual availability and limitations. Include the 40-minute benchmark and the longest scheduled STEM session (duration to confirm). Languages, class size, room, lecture amplification, lecturer movement and lab/discussion format are measured metadata, not assumed to match early grades.
        
        ## Domain and label contract
        
        - Add `cohort = undergraduate_stem | early_grade` and `session_type = lecture | tutorial | lab | early_grade_lesson` to records, manifests and report queries. Use a mutually exclusive context: STEM has course category/session type and no O–3 grade; early grade has grade/subject and no STEM course field. Server enrolment constrains cohort. No names/student IDs belong in lesson records.
        - STEM annotations: `INSTRUCTOR`, `STUDENT_SINGLE`, `STUDENT_GROUP`, `OVERLAP`, `NON_SPEECH`, plus `MISSING` capture metadata. Define group speech operationally in the guide; parallel lab conversations are not automatically choral response. Uncertain adult role attribution remains unavailable, never guessed from pitch/age alone.
        - Existing early-grade `TEACHER/CHILD_*` model outputs are incompatible with the STEM profile. Do not rename logits to adult roles. A STEM-compatible model and manifest are required for automated role results; until then run capture/transport and coarse speech-activity tests, and use human annotations to test indicator calculations. Label every displayed result as human-coded, candidate-model or synthetic.
        - Use separate `label_schema_version` and model `supported_cohorts`. Reusable duration/turn arithmetic can map independently validated cohort-specific labels to semantic roles internally. This does not confer model transfer validity.
        - STEM response counts and group-speech share are descriptive exploratory outputs. Early-grade choral coaching, child-response metrics and district education aggregates are disabled in P1. Private technical reports must say “undergraduate STEM trial”. Unsupported/unvalidated metrics return null plus an availability reason; do not show zeros as successful detection.
        - Language vocabulary remains amh/sid/eng/oth with unknown abstention unless E3 establishes a versioned change. STEM English/code-switching performance provides no Sidaamu Afoo coverage when absent. Report per-language sample sizes and unknown rate.
        - Split P1 by instructor/course/room as feasible and keep entire sessions together. Separate P1 from P2 manifests, annotations, calibration, metric tables and reports. Never pool STEM with early-grade test metrics or use an early-grade test partition for adaptation. Any P1 → P2 transfer experiment is declared and independently tested.
        
        ## Technical exit thresholds
        
        Run the suites in [POST_IMPLEMENTATION_TEST_PLAN.md](../testing/POST_IMPLEMENTATION_TEST_PLAN.md). For each supported reference device: end-to-end RTF ≤0.3, peak RSS ≤300 MB, bounded backlog with recorded p95 emission latency, ≤10 percentage points battery loss over the controlled 40-minute run, and no unaccounted audio intervals. Extended STEM sessions require measured completion and bounded resources; do not extrapolate a linear battery limit.
        
        Every valid, non-withdrawn synthetic record survives the 72-hour offline/disconnect scenario and is delivered once connectivity and worker execution are available. Zero cross-user/cohort leakage, no deployment audio persistence/upload, and withdrawal/restore tests are hard gates. A six-session trial cannot establish a 99% crash-free population rate: publish successes/attempts, durations and failures instead. Record signal usability and optional human agreement without claiming early-grade accuracy.
        
        ## Outputs and responsibility
        
        Lead: recruitment/permissions, session matrix, annotation definitions, cohort-separated report and P1 exit decision. Sys: device/server acceptance evidence and resolved defects. ML: model compatibility, feature/export parity and exploratory P1 metrics. Team: ratify ADR-014 (staged cohorts) and ADR-012 schema extension before implementation.
        
        Schedule is dependency-based: P0 → G4-U → P1 → P1 exit + G4-E → P2 → G5-E. Competition dates never waive these gates; if only P1 is complete, present it as undergraduate technical evidence and keep the cloud demo synthetic.
        EOF
        cat > docs/research/INFRASTRUCTURE_RECHECK.md <<'EOF'
        # Infrastructure documentation re-check
        
        Checked official documentation on 2026-09-27. These are documentation findings and proposed choices, not successful installs or benchmarks. Final host, training hardware, regional capacity and credentials have not been supplied. Applies equally to undergraduate STEM and later early-grade data.
        
        | ID | Component / source | Confirmed documentation and implication | Required post-implementation case |
        |---|---|---|---|
        | INF-01 | [AGP 9.4 compatibility](https://developer.android.com/build/releases/agp-9-4-0-release-notes) | AGP 9.4 lists Gradle 9.6.0, API ≤37 and minimum JDK 17. Repo uses Gradle 9.6.0, API 37, daemon JDK 25 and AGP 9.4.1. Family requirements match; this page does not establish the exact patch/plugin/JDK combination. Resolve and build exact pins before changes. Java source target 11 is separate from Gradle's runtime JDK. | BUILD-01 |
        | INF-02 | [Lite 2.10.0 downloads](https://www.mindspore.cn/lite/docs/en/r2.10.0/use/downloads.html) | Android-aarch64 package contains AAR/runtime/benchmark; cloud-side and device-side Linux converters are distinct downloads. Pin the device-side package and published SHA-256 with matching converter. Package availability is not model compatibility. | MODEL-01 |
        | INF-03 | [Lite operator table](https://www.mindspore.cn/lite/docs/en/r2.10.0/reference/operator_list_lite.html), [PTQ](https://www.mindspore.cn/lite/docs/en/r2.10.0/advanced/quantization.html) | CPU GRU/LSTM list FP16/FP32, not INT8. Keep conv-only INT8 and mixed-precision recurrent candidates; prove full exported graph support and calibration behavior. | MODEL-01/02 |
        | INF-04 | [MindSpore 2.10 release](https://www.mindspore.cn/docs/en/r2.10.0/RELEASE.html) | Release notes do not prove a usable training stack on the team's unidentified hardware. Capture OS/Python/accelerator/driver/CANN compatibility and a forward/backward/save/reload spike. PyTorch teacher and MindSpore student stay conditional. | MODEL-03 |
        | INF-05 | [Microphone foreground service](https://developer.android.com/develop/background-work/services/fgs/service-types#microphone), [audio input sharing](https://developer.android.com/media/platform/sharing-audio-input) | Plan runtime RECORD_AUDIO, foreground-service declarations/permissions appropriate to target SDK, visible-UI start and recording callback handling. Capture can be silenced; callbacks plus frame continuity determine missingness. | DEVICE-01/02 |
        | INF-06 | [WorkManager work requests](https://developer.android.com/develop/background-work/background-tasks/persistent/getting-started/define-work) | Periodic work has a 15-minute minimum and timing depends on constraints/system scheduling. Use unique network-constrained one-time work when an outbox record is ready, plus periodic recovery. Do not promise immediate sync or run live capture in WorkManager. | SYNC-01/02 |
        | INF-07 | [SQLCipher migration](https://www.zetetic.net/sqlcipher/sqlcipher-for-android-migration/), [Auto Backup](https://developer.android.com/identity/data/autobackup), [16 KB support](https://developer.android.com/guide/practices/page-sizes) | Use `net.zetetic:sqlcipher-android` with `SupportOpenHelperFactory` for Room, not the legacy library/API. Pin a tested Room/SQLite/SQLCipher set. SQLCipher and Lite both need native/packaging/runtime checks; encryption does not replace backup exclusions and key lifecycle tests. | STORE-01/02; BUILD-02 |
        | INF-08 | [openEuler releases](https://www.openeuler.org/en/download/), [24.03 LTS SP3 containers](https://docs.openeuler.org/en/docs/24.03_LTS_SP3/cloud/index.html) | 24.03 LTS SP3 is a candidate with published planned EOL 2027/12. Select maintained service pack, architecture, image checksum and actual container engine/Compose versions. A generic “LTS” or “Docker compatible” description is not enough. | INFRA-01 |
        | INF-09 | [openGauss psycopg SSL](https://docs.opengauss.org/en/docs/latest/developer_guide/connecting_to_the_database_using_ssl_psycopg.html), [PostgreSQL libpq SSL](https://www.postgresql.org/docs/current/libpq-ssl.html) | openGauss documents `verify-full` as unsupported; `require` encrypts without verifying server authenticity. PostgreSQL documents certificate/hostname checks for `verify-full`. This is a selection gate: prove authenticated server identity for the chosen openGauss/driver arrangement or choose PostgreSQL; never silently weaken verification. No generic SASL workaround is approved. | DB-01/02 |
        | INF-10 | [FastAPI containers](https://fastapi.tiangolo.com/deployment/docker/) | Build from a pinned Python base rather than the deprecated prebuilt FastAPI image. Proposed deployment uses one API process initially, a separate durable scheduled job runner and a TLS reverse proxy with restricted trusted proxy addresses. Migration/job coordination remains the application's responsibility. | INFRA-01; API-01 |
        | INF-11 | [Huawei RDS PostgreSQL + ECS](https://support.huaweicloud.com/intl/en-us/qs-rds-pg/rds_02_0016.html) | Vendor path uses ECS/RDS in the same region/VPC and controlled security groups with SSL. Demo default: ECS + RDS PostgreSQL, private DB connectivity, least-privilege user. GaussDB is a separate alternative requiring its own compatibility evidence, not a drop-in alias. AF-Johannesburg availability/quota still needs an account-level check. | CLOUD-01; DB-01 |
        | INF-12 | [ModelArts constraints](https://support.huaweicloud.com/intl/en-us/productdesc-modelarts/modelarts_01_0062.html) | Standard ModelArts requires same-region OBS and does not support encrypted OBS buckets. Restrict a dedicated private-access bucket to separately approved inputs/outputs; never place either classroom cohort there. This is service-specific, not a reason to disable encryption on all buckets. | CLOUD-02 |
        | INF-13 | [Label Studio security](https://labelstud.io/guide/security) | Official guidance includes internal-network isolation and production SSRF protection (`SSRF_PROTECTION_ENABLED=true`). Verify authentication, media URLs, outbound telemetry and exact edition capabilities; do not assume enterprise RBAC/audit features in Community. Annotation stays on the in-country private tier. | ANNOT-01 |
        | INF-14 | [Zergaw Cloud Server](https://zergaw.com/cloud-server/) | Provider publishes a cloud-server offering, but this does not establish purchased-host suitability, permitted OS image, accelerator, backups, access terms or data location for every service. Existing hosting list remains a shortlist. Obtain exact configuration/location/backup terms and a restore test. No provider purchase or price revalidation performed. | INFRA-01/02 |
        
        ## Proposed deployment baseline after this check
        
        - Android minSdk 29 / arm64 remains. Retain existing toolchain files pending BUILD-01; this review changes no dependencies. Validate actual target-SDK behavior on modern Android and resource constraints on the reference 2 GB phone.
        - In-country P1 and P2 use one simple API + database + job runner; annotation/training isolated from the public API. openEuler SP3 candidate, openGauss conditional on DB-01; PostgreSQL fallback explicit in ADR-005. Do not start with two pilot database engines.
        - Competition demo is a distinct ECS/RDS PostgreSQL deployment using synthetic records. ModelArts/OBS training is a separate optional capability; it is not required to unblock undergraduate capture tests. Record exact region/service quota before booking capacity.
        - DB listener and annotation UI are private. Only HTTPS API/demo routes are public. Keep service secrets out of images, source and logs; test certificate rotation, backups and restart recovery. Pilot backups, observability and model artefacts remain in-country for both cohorts.
        
        ## Evidence record to complete at G0/G4-U
        
        Record selected host/location, OS image/version/hash, container runtime/Compose, Python/API/driver/database versions, region/VPC and service availability, CA/trust configuration, encrypted-backup location/retention, restore evidence, Android dependency lock/checksums, model/converter/runtime hashes and device OS/RAM/page size. Retention, recovery-time and recovery-point objectives must be assigned numerical values by Lead/Sys before tests can pass. Record unresolved items as blocked, not verified.
        
        Scope limits: no cloud account inspection, provider contract, download/binary inspection, build, training, device operation or data transfer was performed. Previous 4 KB AAR binary finding remains dated prior evidence; verify the newly chosen binary. No new legal interpretation is made here.
        EOF
    ✓ • 0ms

## Activity

    $ cat > tests/acceptance/runner.py <<'EOF'
        """Black-box acceptance runner; contains no product/indicator implementation."""
        import argparse
        import json
        import math
        from pathlib import Path
        import subprocess
        import sys
        
        ROOT = Path(__file__).resolve().parent
        
        
        def strict_json(text):
            def invalid(value):
                raise ValueError(f"Non-finite JSON number: {value}")
            return json.loads(text, parse_constant=invalid)
        
        
        def compare(actual, expected, path="$"):
            """Expected dictionaries are subsets; arrays/scalars must match exactly."""
            if isinstance(expected, dict):
                if not isinstance(actual, dict):
                    raise AssertionError(f"{path}: expected object")
                for key, value in expected.items():
                    if key not in actual:
                        raise AssertionError(f"{path}.{key}: missing")
                    compare(actual[key], value, f"{path}.{key}")
            elif isinstance(expected, list):
                if not isinstance(actual, list) or len(actual) != len(expected):
                    raise AssertionError(f"{path}: array length/type differs")
                for index, value in enumerate(expected):
                    compare(actual[index], value, f"{path}[{index}]")
            elif isinstance(expected, float):
                if type(actual) not in (int, float) or not math.isfinite(actual):
                    raise AssertionError(f"{path}: expected finite number")
                if not math.isclose(actual, expected, rel_tol=0, abs_tol=1e-6):
                    raise AssertionError(f"{path}: numeric mismatch")
            elif type(actual) is not type(expected) or actual != expected:
                raise AssertionError(f"{path}: value/type mismatch")
        
        
        def load_vectors():
            data = strict_json((ROOT / "vectors.json").read_text())
            seen = set()
            for case in data:
                if not isinstance(case.get("id"), str) or case["id"] in seen:
                    raise ValueError("Invalid/duplicate case ID")
                seen.add(case["id"])
                if not case.get("rationale") or not isinstance(case.get("request"), dict):
                    raise ValueError(f"Incomplete case: {case['id']}")
                if not isinstance(case.get("expected"), dict) or not case["expected"]:
                    raise ValueError(f"Missing oracle: {case['id']}")
            if not data:
                raise ValueError("Empty suite")
            return data
        
        
        def main():
            parser = argparse.ArgumentParser(description=__doc__)
            parser.add_argument("--validate", action="store_true", help="Validate fixtures only; no product tests")
            parser.add_argument("--adapter", help="JSON argv array for a future implementation adapter; no shell")
            parser.add_argument("--timeout", type=float, default=30)
            args = parser.parse_args()
            cases = load_vectors()
            if args.validate:
                print(f"Validated {len(cases)} fixture definitions; product tests NOT RUN.")
                return 0
            if not args.adapter:
                parser.error("Implementation adapter required. No tests passed; use --validate for fixture-only checks.")
            command = strict_json(args.adapter)
            if not isinstance(command, list) or not command or not all(isinstance(x, str) and x for x in command):
                parser.error("--adapter must be a non-empty JSON array of non-empty argv strings")
            if not math.isfinite(args.timeout) or args.timeout <= 0:
                parser.error("--timeout must be finite and positive")
            failed = 0
            for case in cases:
                try:
                    proc = subprocess.run(command, input=json.dumps(case["request"], allow_nan=False),
                                          text=True, capture_output=True, timeout=args.timeout, check=False)
                    if proc.returncode:
                        raise AssertionError(f"adapter exited {proc.returncode}; output withheld (may contain secrets)")
                    compare(strict_json(proc.stdout), case["expected"])
                    print(f"PASS {case['id']}")
                except (AssertionError, ValueError, OSError, subprocess.TimeoutExpired) as exc:
                    failed += 1
                    # No raw adapter output or classroom content is printed.
                    print(f"FAIL {case['id']}: {type(exc).__name__}: " +
                          (str(exc) if isinstance(exc, AssertionError) else "adapter/configuration error"))
            print(f"{len(cases) - failed}/{len(cases)} contract cases passed; hardware/infrastructure suites are separate.")
            return 1 if failed else 0
        
        
        if __name__ == "__main__":
            sys.exit(main())
        EOF
        cat > /tmp/echo_make_vectors.py <<'PY'
        import json
        from pathlib import Path
        cases=[]
        def add(cid, duration, spans, expected, rationale, cohort='early_grade'):
         cases.append({'id':cid,'rationale':rationale,'request':{'operation':'indicators','contract':'acceptance-v1','indicator_spec_version':2,'cohort':cohort,'tick_ms':10,'duration_ticks':duration,'timeline':[dict(start_tick=a,end_tick=b,label=c,**({'lang':d[0]} if d else {})) for a,b,c,*d in spans]},'expected':expected})
        # Hand-calculated oracles; input is already postprocessed, original timing preserved.
        add('CORE-01',0,[],{'observed_s':0.0,'missing_s':0.0,'I1':None,'I2':{'single':{'n':0,'median':None}},'I3':None,'I4':{'count':0,'per_10min':None},'I5':None,'I6':None,'I7':None,'I8':0},'No elapsed or observed time; ratios are unavailable.')
        add('CORE-02',6000,[(0,6000,'NON_SPEECH')],{'observed_s':60.0,'I1':None,'I3':None,'I5':0.0,'I6':None,'I7':0.0,'I8':1},'Exactly 60 seconds observed silence is one event, no speech ratios.')
        add('CORE-03',6000,[(0,6000,'MISSING')],{'observed_s':0.0,'missing_s':60.0,'I1':None,'I4':{'count':0,'per_10min':None},'I5':None,'I7':None,'I8':0},'Missing capture is neither silence nor an eligible minute.')
        add('CORE-04',6000,[(0,6000,'TEACHER','unknown')],{'I1':1.0,'I3':None,'I5':0.0,'I6':{'amh':0.0,'sid':0.0,'eng':0.0,'oth':0.0,'unknown':1.0},'I7':0.0,'I8':0},'Unknown teacher language retains its duration in denominator.')
        add('CORE-05',6000,[(0,1000,'TEACHER','eng'),(1000,2000,'CHILD_SINGLE'),(2000,6000,'NON_SPEECH')],{'I1':0.5,'I2':{'single':{'n':1,'median':0.0,'p25':0.0,'p75':0.0},'choral':{'n':0,'median':None}},'I3':0.0,'I4':{'count':1,'per_10min':10.0},'I5':1.0,'I6':{'eng':1.0,'unknown':0.0},'I7':1.0,'I8':0},'One zero-gap response, one turn in one observed minute.')
        add('CORE-06',6000,[(0,1000,'TEACHER','amh'),(1000,2000,'MISSING'),(2000,3000,'CHILD_SINGLE'),(3000,6000,'NON_SPEECH')],{'observed_s':50.0,'missing_s':10.0,'I1':0.5,'I2':{'single':{'n':0,'median':None}},'I4':{'count':1,'per_10min':12.0},'I5':0.0,'I7':None,'I8':0},'Missing gap breaks turn and invalidates the sole coverage bin; rate uses 50 seconds.')
        add('CORE-07',6000,[(0,1000,'TEACHER','eng'),(1000,2000,'NON_SPEECH'),(2000,3000,'CHILD_CHORAL'),(3000,6000,'NON_SPEECH')],{'I1':0.5,'I2':{'choral':{'n':1,'median':10.0,'p25':10.0,'p75':10.0}},'I3':1.0,'I4':{'count':0,'per_10min':0.0},'I5':1.0,'I7':1.0},'Exactly 10 second response gap is included in latency.')
        add('CORE-08',6000,[(0,1000,'TEACHER','eng'),(1000,2001,'NON_SPEECH'),(2001,3001,'CHILD_SINGLE'),(3001,6000,'NON_SPEECH')],{'I2':{'single':{'n':0,'median':None}},'I5':1.0,'I7':1.0},'10.01 second gap excluded from I2 but I5 has no silence-gap cap.')
        add('CORE-09',6000,[(0,6000,'OVERLAP')],{'I1':0.0,'I3':None,'I5':0.0,'I6':None,'I7':0.0,'I8':0},'Overlap contributes to speech denominator but is not attributed to teacher.')
        add('CORE-10',6000,[(0,3000,'TEACHER','eng'),(3000,6000,'TEACHER','unknown')],{'I1':1.0,'I6':{'eng':0.5,'unknown':0.5},'I5':0.0},'Within-turn language change/abstention is duration weighted without nearest-label inheritance.')
        add('CORE-11',11999,[(0,5999,'NON_SPEECH'),(5999,6000,'MISSING'),(6000,11999,'NON_SPEECH')],{'observed_s':119.98,'missing_s':0.01,'I8':0,'I7':0.0},'Two 59.99 second silent spans separated by missing tick are not a long silence; final partial bin is observed.')
        add('CORE-12',12000,[(0,5000,'TEACHER','eng'),(5000,6000,'NON_SPEECH'),(6000,7000,'CHILD_SINGLE'),(7000,12000,'NON_SPEECH')],{'I2':{'single':{'n':1,'median':10.0}},'I4':{'count':1,'per_10min':5.0},'I5':0.5,'I7':0.5},'Turn at exact minute boundary belongs to destination bin only.')
        cases.extend([
         {'id':'COHORT-01','rationale':'Adult model trial cannot use O-class grade context.','request':{'operation':'validate_context','contract':'acceptance-v1','cohort':'undergraduate_stem','session_type':'lecture','course_category':'physics','grade':'O'},'expected':{'valid':False,'reason':'cohort_context_mismatch'}},
         {'id':'COHORT-02','rationale':'Valid STEM session with no child grade.','request':{'operation':'validate_context','contract':'acceptance-v1','cohort':'undergraduate_stem','session_type':'lecture','course_category':'physics'},'expected':{'valid':True}},
         {'id':'COHORT-03','rationale':'Early-grade model must not silently relabel child logits as adult students.','request':{'operation':'validate_model','contract':'acceptance-v1','cohort':'undergraduate_stem','model':{'supported_cohorts':['early_grade'],'labels':['TEACHER','CHILD_SINGLE','CHILD_CHORAL','OVERLAP','NON_SPEECH']}},'expected':{'valid':False,'reason':'unsupported_cohort'}},
         {'id':'COHORT-04','rationale':'Unknown cohort must not fall back to early grade.','request':{'operation':'validate_context','contract':'acceptance-v1','cohort':'unspecified','session_type':'lecture'},'expected':{'valid':False,'reason':'unsupported_cohort'}},
         {'id':'COHORT-05','rationale':'Early-grade context remains supported independently.','request':{'operation':'validate_context','contract':'acceptance-v1','cohort':'early_grade','session_type':'early_grade_lesson','grade':'2','subject':'math'},'expected':{'valid':True}},
         {'id':'COHORT-06','rationale':'Reject adult labels in early-grade indicator input.','request':{'operation':'indicators','contract':'acceptance-v1','indicator_spec_version':2,'cohort':'early_grade','tick_ms':10,'duration_ticks':6000,'timeline':[{'start_tick':0,'end_tick':6000,'label':'STUDENT_SINGLE'}]},'expected':{'error':'label_schema_mismatch'}}])
        Path('tests/acceptance/vectors.json').write_text(json.dumps(cases,indent=2)+'\n')
        PY
        python3 /tmp/echo_make_vectors.py
    ✓ • 0ms

## Assistant

The plans now place the STEM trial before early-grade testing, with separate cohort metadata, consent, labels, model compatibility and results.

I’m preparing two test layers: executable contract checks with hand-calculated expectations, and scenario suites for devices, databases, cloud isolation and classroom trials. Missing implementation adapters will block execution rather than produce misleading passes.

## Activity

    $ cat > tests/acceptance/README.md <<'EOF'
        # Post-implementation acceptance tests
        
        Prepared 2026-09-27. No product implementation is included. Python 3.10+ standard library only. `vectors.json` contains hand-calculated indicator oracles and cohort-contract cases; `runner.py` sends them to a future implementation adapter. The device, infrastructure and field suites are specified in [the test plan](../../docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md). They are not automated by this runner.
        
        ## Before implementation
        
        Validate the fixture format (does not test the product):
        
        ```sh
        python3 tests/acceptance/runner.py --validate
        python3 -m unittest discover -s tests/acceptance -p 'test_harness.py'
        ```
        
        Ratify the proposed contract in ADR-012/014 before implementing its adapter. Resolve remaining postprocessing/quantile/quality thresholds at G1. These fixtures deliberately avoid unresolved smoothing and multi-value quantile choices; they are a starter contract suite, not exhaustive model or pipeline coverage.
        
        ## After implementation
        
        Create adapters that call the actual Python reference and Kotlin implementation. Each adapter must accept **one JSON request on stdin**, emit **one JSON result on stdout**, and exit; logging goes to stderr. Implementations may map their public field names to the normalized acceptance fields below, but must not calculate replacement results or read expected fixtures. No adapter exists yet. Run each independently against the same vectors:
        
        ```sh
        python3 tests/acceptance/runner.py --adapter '["python3", "path/to/python_adapter.py"]'
        python3 tests/acceptance/runner.py --adapter '["java", "-jar", "path/to/kotlin-adapter.jar"]'
        ```
        
        Replace the paths with actual future adapters. The runner never invokes a shell, uses a timeout per case, fails on missing/malformed/non-finite output and returns nonzero on any failure. No adapter means an error, not a skip or pass. Actual output may contain additional fields, but every oracle field must exist. Counts and categorical/null values are exact; floating values use absolute tolerance 1e-6.
        
        ## Adapter protocol (test-only, acceptance-v1)
        
        - `indicators`: fields `indicator_spec_version`, `cohort`, `tick_ms=10`, `duration_ticks`, `timeline`. Timeline uses half-open `start_tick`/`end_tick`, `label`, optional `lang`. It is already postprocessed, preserves original timestamps and includes missing intervals. Use the real indicator engine, bypassing only feature/model inference. Result: `observed_s`, `missing_s`, `I1` ratio, `I2` single/choral quantiles with `n`, `I3` duration ratio, `I4` `{count, per_10min}`, `I5` per-minute rate, `I6` language shares or null, `I7` coverage or null, `I8` count. Invalid cohort labels return `{"error":"label_schema_mismatch"}`. This normalized shape is not the sync API schema.
        - `validate_context`: invoke actual session/cohort validation. Return `{"valid":true}` or `{"valid":false,"reason":"cohort_context_mismatch|unsupported_cohort"}` (one reason value). STEM cannot contain an O–3 grade; early-grade sessions retain grade/subject.
        - `validate_model`: invoke actual cohort/manifest compatibility check. Unsupported cohort returns `{"valid":false,"reason":"unsupported_cohort"}`. The provided case checks only that incompatibility, not complete manifest verification.
        
        Never use a self-reported mock pass as implementation evidence. Store full runs with git SHA, adapter SHA and model/spec versions in the private evidence store. Contract vectors contain no real audio or personal data. Keep real classroom evidence and test credentials out of this repository.
        EOF
        cat > tests/acceptance/test_harness.py <<'EOF'
        """Checks the acceptance harness, not the future product."""
        import unittest
        from runner import compare, load_vectors, strict_json
        
        
        class HarnessTests(unittest.TestCase):
            def test_vectors_have_unique_oracles(self):
                cases = load_vectors()
                self.assertGreaterEqual(len(cases), 18)
        
            def test_subset_and_absolute_tolerance(self):
                compare({'ratio': 0.5000001, 'count': 1, 'extra': True}, {'ratio': 0.5, 'count': 1})
        
            def test_reject_false_passes(self):
                pairs = [({}, {'count': 1}), ({'n': True}, {'n': 1}),
                         ({'n': 0}, {'n': None}), ({'ratio': 0.51}, {'ratio': 0.5}),
                         ({'ratio': float('nan')}, {'ratio': 0.5}),
                         ({'n': 1.0}, {'n': 1}), ({'x': [1]}, {'x': [1, 2]})]
                for actual, expected in pairs:
                    with self.subTest(actual=actual, expected=expected):
                        with self.assertRaises(AssertionError):
                            compare(actual, expected)
        
            def test_reject_nonfinite_json(self):
                for text in ['NaN', 'Infinity', '-Infinity']:
                    with self.assertRaises(ValueError):
                        strict_json(text)
        
        
        if __name__ == '__main__':
            unittest.main()
        EOF
        cat > docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md <<'EOF'
        # Post-implementation acceptance suites
        
        Prepared 2026-09-27. Status of **every product case: NOT RUN**. This document specifies executable procedures/oracles for later runs. The [contract runner](../../tests/acceptance/README.md) is ready to connect to future adapters; hardware, service and field cases below require implementation and a provisioned test environment. No test result is claimed from this plan.
        
        Related: [STEM trial](../research/STEM_TRIAL_PLAN.md), [infrastructure evidence INF-01–14](../research/INFRASTRUCTURE_RECHECK.md), [G0–G5](../research/IMPLEMENTATION_REVIEW.md).
        
        ## Execution order and reporting
        
        1. BUILD, CORE/COHORT, MODEL, API/SYNC and disposable infrastructure tests on synthetic fixtures.
        2. DEVICE/STORE tests on reference devices; CLOUD/ANNOT and backup/restore negative cases. Resolve all blocking failures before G4-U.
        3. STEM-01–04 after G4-U. Report technical feasibility separately from adult-model exploratory metrics.
        4. EARLY-01–03 after STEM exit and G4-E. No early-grade release before G5-E.
        
        Default owners: Sys (build/device/storage/API/sync/infra/cloud/database), ML (models/features), Lead (cohorts/ethics/field validity); Lead + Sys own disclosure/withdrawal cases. Each row is blocking for its applicable gate unless explicitly exploratory. A blocked prerequisite, unsupported required feature or skipped case is **not a pass**. Changes to thresholds need an ADR dated before the held-out run.
        
        Use isolated, disposable test accounts/databases/devices with generated non-personal records. Never run purge, fault injection or restore cases against real pilot data. The test environment must identify itself as non-production before destructive cases begin. Do not expose debug/admin reset endpoints in release builds. Preserve evidence privately with: case ID, git/build/adapter SHA, OS/device, pinned dependencies, model/feature/spec/label versions, cohort, test-fixture hash/seed, UTC start/end, steps executed, actual result, pass/fail/blocked, redacted evidence path, defect and rerun ID. Logs must omit tokens, raw classroom content and participant identities.
        
        ## A. Build and contract suite (G0–G2)
        
        | ID | Preconditions and steps | Expected result / evidence |
        |---|---|---|
        | BUILD-01 | Clean checkout/cache on documented CI image. Resolve exact AGP/Gradle/JDK/Compose pins; assemble debug and release, run lint/unit tests and install release on API 29 arm64 plus a modern target-compatible device. | Reproducible successful builds/install; dependency manifest and checksums. Template greeting tests alone do not satisfy acceptance. Record actual JDK rather than confuse source target 11 with daemon runtime. |
        | BUILD-02 | Extract release APK/AAB-derived APKs; inspect every bundled native library, ELF load/RELRO alignment and ZIP packaging. Install/run on 4 KB and 16 KB environments. Exercise Lite and SQLCipher, not just launch activity. | No native loader/crash failures on supported configurations. Capture `adb shell getconf PAGE_SIZE`, official alignment-check and `zipalign -v -c -P 16 4` outputs. Incompatible device support cannot be claimed merely because sideload succeeded elsewhere. |
        | CORE-01–12 | Run supplied vectors against actual Python and Kotlin adapters independently. | Every oracle passes; null/missing/overlap/turn/latency/language/time-bin behavior matches. Preserve both outputs. Counts exact, floats abs ≤1e-6. |
        | CORE-13 | After G1 freezes postprocessing, add fixtures at 0.24/0.25/0.26 s gaps, 0.29/0.30/0.31 s segments, 0.49/0.50/0.51 s responses; ties, quantile interpolation, zero duration, invalid overlap/order/NaN, end flush and chunk-boundary splits. | Reviewed expected outputs before implementation adaptation; original silence/latency preserved; missing intervals never bridged. This case remains blocked until G1 oracles are frozen; existing 12 vectors do not cover it. |
        | COHORT-01–06 | Run provided context/manifest/profile vectors; then submit equivalent valid/invalid sessions through UI and API. | Adult sessions cannot masquerade as O-class, unknown cohorts fail, unsupported child model is rejected for STEM, valid early-grade schema preserved. API must enforce enrolment cohort independent of client input. |
        
        ## B. Model and streaming suite (G2, then G5-E)
        
        | ID | Preconditions and steps | Expected result / evidence |
        |---|---|---|
        | MODEL-01 | Hash-check device-side runtime/converter; export each chosen model; run deterministic public/staged features in source framework and Lite; verify shapes/class order and corrupt a manifest/hash. | Source/export comparison meets predeclared per-model tolerance (initial FP abs 1e-4); incompatible/corrupt bundle rejected atomically. Record per-layer precision and unsupported ops; no silent unsupported cohort mapping. |
        | MODEL-02 | Split calibration/development/test manifests by speaker/session before PTQ. Run FP and quantized candidates on the same locked set and device. | Report quantization macro-F1 gap (target ≤0.02) and indicator errors; no test-set calibration. Report failing or absent classes explicitly. P1 metrics never satisfy child-class target. |
        | MODEL-03 | On actual in-country training machine: record OS/Python/framework/accelerator/driver; train a toy forward/backward step, save/reload and export. | Finite loss/gradients, reload agreement and device import; written framework fallback if unsupported. No classroom artefacts leave the host. |
        | STREAM-01 | Feed the same authorised PCM in fixed and irregular chunks, including resampling/warm-up/end flush; compare Python/Kotlin features and emitted labels. Drop a chunk, inject silencing and overflow, then resume. | Feature parity within G1's frozen tolerance; every timeline tick emitted once or MISSING, bounded lookahead/backlog; state resets at missing spans; no duplicate processing counts. Save timestamp traces. |
        | STREAM-02 | Replay a STEM session using early-grade-only manifest; then use a compatible adult model or human-coded timeline in explicitly labelled analysis mode. Include another adult speaker, playback and simultaneous lab groups. | Incompatible automated role results unavailable; no age/pitch-based role assumption. Human-coded arithmetic distinguished from model inference. Group conversation not automatically choral response. |
        
        ## C. Device, storage and privacy suite (G4-U; repeat changed parts at G4-E)
        
        | ID | Preconditions and steps | Expected result / evidence |
        |---|---|---|
        | DEVICE-01 | Grant/deny/revoke microphone permission; start via visible UI, lock screen, rotate UI, background app, attempt unsupported background start; end session. | Legal foreground start with visible notification; denial has usable UI; no crash or hidden capture. End stops mic/wake lock. Unsupported start fails cleanly. |
        | DEVICE-02 | During staged audio, take call/competing-mic event, remove external mic, suspend capture and terminate/restart process. | Detected silencing/drop intervals are MISSING, not silence. No invented continuity. Recovery marks interrupted session; completed encrypted records preserved. Permission/restart behavior recorded per OS. |
        | DEVICE-03 | Release-build 40-minute soak on 2 GB arm64 reference phone with active features/models/encryption; measure processing time, RSS, queue age, temperature and battery. Repeat longest scheduled STEM session. | RTF ≤0.3, peak RSS ≤300 MB, ≤10 percentage-point battery loss/40 min under documented conditions; bounded queue; no unaccounted spans. Record p95 latency and predeclared G1 latency budget. Extended run completes without unbounded growth; report actual battery. |
        | STORE-01 | Create synthetic record/research file; inspect DB/WAL/temp/cache; attempt ordinary SQLite open and wrong-key SQLCipher open; invalidate wrapped key; simulate full storage. | No recoverable plaintext sensitive fields/audio; correct key opens DB; invalid key fails safely with recovery path, no silent discard of acknowledged records. Full storage stops safely and surfaces failure. |
        | STORE-02 | Trigger legacy and API 31+ backup/restore/device transfer; inspect resulting archives and restored files on test devices. Reinstall and switch shared-phone accounts. | No DB/audio/credentials/identifiers in external backup/transfer; enrolment recovery explicit; other account cannot see reports. Evidence covers manufacturer-specific transfer behavior, not only manifest flags. |
        | PRIVACY-01 | In Deployment Mode, stream staged audio and inspect filesystem/network/logs/crash output. Repeat in Research Mode with separate consent disabled/enabled. | Deployment writes/uploads no raw audio/features/embeddings/timeline; research path only enabled by separate approved consent. Research transfer deletes local encrypted file only after integrity and durable-receipt verification. |
        
        ## D. API, outbox and reports suite (G3/G4-U)
        
        | ID | Preconditions and steps | Expected result / evidence |
        |---|---|---|
        | API-01 | Use two teachers, two schools and both cohorts. Try unauthenticated/expired tokens, forged teacher/school/cohort fields, cross-user report GET/DELETE and oversized/unsupported-schema/nonfinite payloads. Spoof forwarded headers through direct backend access. | Server-side scope rejection, no cross-user mutation/disclosure; field allowlist/size limits enforced, secrets absent in logs; proxy trust restricted. No reliance on user-supplied teacher IDs. |
        | SYNC-01 | POST record; repeat same canonical payload; reuse ID with changed payload; drop response after commit; submit mixed valid/rejected batch; restart DB/API around commit. | Accepted only after durable commit; same record duplicate, changed payload conflict; one stored record per owner/ID. Retry only transient failures; rejected record reason visible; no lost/false ACK. |
        | SYNC-02 | Queue valid synthetic records for 72 hours offline with app/process restarts and random disconnects; restore network and permit worker execution. Test one-time work and periodic recovery independently. | All non-withdrawn valid records eventually ACK once; local data available throughout. Do not require a periodic worker to run on an exact minute. Record eventual-delivery latency and scheduler state. |
        | SYNC-03 | Withdraw offline while records queued, reconnect; race upload with server withdrawal; retry using second enrolled device and old token. Restore pre-withdrawal backup in disposable environment. | Capture/upload stops; all devices revoked server-side; stale data never resurrected; derived reports/aggregates removed/recomputed; restore replays deletion records before serving. Retained ledger limited by policy. |
        | REPORT-01 | Generate same weekly report offline/server from identical versioned inputs; cross week boundary in Africa/Addis_Ababa; mix unavailable metrics and versions. | Deterministic report; no more than one approved focus. STEM has no early-grade coaching, child claims or district publishing. Low coverage yields unavailable explanation. Compatible versions grouped explicitly. |
        | REPORT-02 | Create 4/5 distinct-teacher cells, multiple lessons from one teacher, overlapping filters and successive releases; withdraw contributor. | Threshold counts teachers, teacher-level weighting, suppression propagates to totals/differences; no arbitrary drill-down or cross-cohort pooling. Reviewer signs disclosure check before district release. |
        
        ## E. Database, hosting and cloud suite (G0/G3/G4-U)
        
        | ID | Preconditions and steps | Expected result / evidence |
        |---|---|---|
        | DB-01 | Use selected pinned server/driver. Connect with trusted cert, then untrusted CA, expired cert and wrong hostname. Check negotiated connection; test restricted app user and blocked public port. | Valid identity succeeds; every invalid identity fails. `sslmode=require` alone is insufficient. If openGauss arrangement cannot meet this, block pilot and ratify authenticated alternative/PostgreSQL; do not downgrade checks. |
        | DB-02 | Apply migrations to empty DB and upgrade a previous fixture schema; check transaction/unique/JSON/timezone behavior; backup, restore to fresh instance, compare logical row counts/hashes; replay withdrawal. | Exact chosen engine/driver passes; rollback/recovery documented; no unreviewed claim of compatibility with a different GaussDB/PostgreSQL engine. Record measured recovery point/time against agreed numerical objectives. |
        | INFRA-01 | Provision pinned supported openEuler image/container stack on disposable selected host. Restrict inbound ports, boot/reboot, kill API/job/DB container, rotate secrets/cert and fill disk. Run same scheduled report twice concurrently. | Services recover within declared objective; DB volumes durable; failed jobs retry idempotently; migrations execute once; no duplicate reports. Only intended HTTPS exposed, admin/DB private, no secrets baked into image/logs. |
        | INFRA-02 | Inspect provider storage, encrypted backups, logs, monitoring destinations and restore path; induce backup failure and revoke a credential. | P1/P2 personal data and replicas remain in-country; alerts observed; actual restore meets signed retention/RPO/RTO; no unverified overseas diagnostic service. Provider contract/location evidence retained. |
        | CLOUD-01 | Deploy synthetic demo to confirmed region; check ECS/RDS VPC/security groups, least-privilege users, TLS, public demo URL and separate pilot credentials. Attempt pilot credential/data submission to demo; rebuild from empty synthetic state. | Approved synthetic dataset only, pilot credentials rejected, DB private, reproducible page. No automatic cross-tier replication, logging or model upload. Exact region/quota/service evidence recorded. |
        | CLOUD-02 | Verify ModelArts/OBS same region and service-specific bucket settings. Attempt anonymous access and access outside allowed prefix; inspect generated logs/checkpoints; attempt unapproved cohort upload through app/export pipeline. | Training accesses only approved assets; anonymous/unauthorised access denied. No P1/P2 audio, posteriors, weights or identifiers. Unencrypted training bucket never reused for pilot storage. ModelArts failure does not block offline STEM test. |
        | ANNOT-01 | Self-host exact Label Studio edition privately with SSRF protection. Try unauthenticated UI/media/API access, cross-project permissions, unapproved external/metadata URL imports; monitor outbound traffic and browser-loaded resources. | Authorised annotators only; private media stays in-country; unsafe fetch blocked; telemetry/resources reviewed or blocked; no assumed Enterprise-only guarantees. Preserve minimum access test evidence. |
        
        ## F. Field progression suite (Lead + ML + Sys)
        
        | ID | Preconditions and steps | Expected result / evidence |
        |---|---|---|
        | STEM-01 | Before recording, review G4-U, participant eligibility/consent and decline alternative; confirm course/session/language/mic/device matrix. | Approved protocol covers actual participants; no academic penalty/compulsion; adult-only assumption not made; no recording when prerequisite missing. Signed readiness checklist kept privately. |
        | STEM-02 | Run proposed ≥6 sessions across ≥3 instructors/≥2 STEM courses and rooms where feasible, including discussion/lab if available; human-log capture faults and mic placement. | Technical thresholds met and failures resolved; actual counts/conditions reported. Missing modalities/languages explicitly untested. No inference of child-speech performance or 99% reliability from small sample. |
        | STEM-03 | Independently annotate a disclosed subset of whole sessions with adult-role guide; compare adult-compatible model to human labels, grouped by instructor/course and language. | Exploratory F1/confusion, indicator error and unknown coverage reported separately. Unsupported roles unavailable; instructor/adult-student ambiguity discussed. Thresholds set before locked evaluation, no child-label score substitution. |
        | STEM-04 | Query reports/exports/aggregate jobs with mixed-cohort inputs; review technical report and unresolved defects. | STEM excluded from early-grade dashboard/evaluation and no normative coaching. P1 exit signed; critical privacy/data-loss/device defects resolved before G4-E. |
        | EARLY-01 | After P1 exit, review G4-E approvals/consent and target-device/mic feasibility anew; freeze school/teacher splits before training/calibration. | Separate early-grade readiness; STEM technical approval not reused as demographic/ethics approval. Adult data provenance declared in any transfer experiment. |
        | EARLY-02 | Evaluate held-out real O–3 audio with native language annotation and agreement; report each class, language and indicator with uncertainty/coverage. | Proposed targets: macro-F1 ≥0.70, child-single/choral F1 ≥0.75, LID accuracy ≥0.85 on eligible ≥3 s segments, I1 MAE ≤5 pp, I2 median MAE ≤0.5 s, I3 MAE ≤10 pp. Insufficient samples or failing indicators remain unavailable; do not pool STEM metrics. |
        | EARLY-03 | Co-design report rules; conduct separate ≥6-teacher/≥3-week intended-population pilot after technical and validity gates; apply disclosure tests. | G5-E sign-off for only validated indicators; qualitative usefulness reported; no causal/disciplinary claims. No safe district cell means no live aggregate release. |
        
        ## Readiness report template
        
        ```text
        Run ID / date / commit:
        Stage and cohort: P0 | P1 undergraduate_stem | P2 early_grade
        Implementation adapters and dependency/model/spec hashes:
        Applicable cases / passed / failed / blocked / not run:
        Device and deployment configuration:
        Fixture or approved dataset manifest hash:
        Evidence location (restricted; no personal data in git):
        Critical defects and rerun references:
        Measured technical metrics and sample counts:
        Unsupported languages/classes/session types:
        Gate decision and responsible reviewers:
        ```
        
        Acceptance-runner success covers only CORE-01–12 and the six COHORT vectors. It does not certify infrastructure, field accuracy, privacy or consent. Run the other suites and retain their evidence before advancing the corresponding gate.
        EOF
        python3 tests/acceptance/runner.py --validate
        PYTHONDONTWRITEBYTECODE=1 python3 -m unittest discover -s tests/acceptance -p 'test_harness.py'
    ....
    ----------------------------------------------------------------------
    Ran 4 tests in 0.000s
    
    OK
    ✓ • 34ms

## User

continue

## Assistant

I’m continuing with the cross-document updates and consistency checks. The test package now has 18 contract cases and procedures for device faults, sync, database recovery, cloud isolation and both classroom phases. I’ll verify that the architecture and schedule consistently put STEM testing first and preserve the later early-grade validation requirements.

## Activity

    $ sed -n '55,170p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; sed -n '235,320p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; sed -n '420,465p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; cat docs/research/README.md
    
    - Classroom observation is the standard instrument for improving teaching practice, but it requires trained observers who do not exist at scale in Ethiopia.
    - Acoustic indicators (talk ratio, wait time, choral vs individual response) are well-established in the classroom-discourse literature as correlates of instructional quality and are extractable **without transcription**.
    - Teacher consent alone is insufficient for classroom capture that includes children. Both modes must follow the ethics and consent decisions in WS-F.
    
    ### 2.6 Why no ASR
    
    No ASR is a deliberate scope, compute and data-minimisation decision. Public Amharic and Sidaamu Afoo resources exist (see the research audit); their existence does not resolve classroom acoustics or short-segment LID. Indicators remain acoustic and must be validated independently of transcription.
    
    ---
    
    ## 3. Users and scenarios
    
    | User | Goal | Touchpoint | What they must never see |
    |---|---|---|---|
    | **Teacher** (primary) | Improve her own practice, privately | Android app: record → weekly report | Other teachers' data |
    | **School director** | Support teachers | Optional: school-level aggregate (opt-in by teachers) | Individual teacher reports unless shared by the teacher |
    | **Woreda / district education officer** | Target training and resources | Web dashboard: anonymised aggregates | Any individual-teacher identity; any cell with < k teachers |
    | **Researcher (team)** | Train and evaluate models | Research Mode app + annotation tool | Audio from teachers who did not consent to Research Mode |
    | **Competition judge** | Evaluate innovation | Live demo on a cheap phone + dashboard | — |
    
    ### Core scenario (Deployment Mode)
    1. Teacher opens app, taps **Start lesson**, selects grade and subject.
    2. Phone processes audio in streaming chunks. No raw audio written to disk.
    3. Teacher taps **End lesson**. Indicators computed in seconds.
    4. Indicators stored encrypted locally; queued for sync.
    5. When connectivity appears, indicators upload to the in-country server.
    6. Weekly: teacher sees a simple, non-punitive report in her language with one suggested focus (e.g., "Review how individual and choral responses were balanced").
    7. District dashboard updates aggregates.
    
    ### Secondary scenario (Research Mode)
    Same as above, **plus** raw audio is stored encrypted on the device for later transfer to the research server, **only** with explicit written consent, used to build training and evaluation datasets.
    
    ---
    
    ## 4. Scope
    
    ### 4.1 In scope (competition + thesis core)
    - On-device audio segmentation into teacher / child-single / child-choral / overlap / non-speech.
    - On-device spoken language identification over teacher segments: Amharic, Sidaamu Afoo, English, other.
    - Indicator engine (§5).
    - Android teacher app (record, report, sync, consent).
    - In-country server: ingestion, storage, report generation, aggregation.
    - District web dashboard with k-anonymity.
    - Training / distillation / export pipeline on MindSpore.
    - Evaluation harness incl. cross-language evaluation (Amharic → Sidaamu Afoo).
    
    ### 4.2 Explicitly out of scope
    - Any speech recognition or transcription.
    - Any identification of individual children, by voice or otherwise.
    - Any teacher performance scoring used for evaluation, ranking, or discipline. (Product principle: **coaching, not surveillance.**)
    - Child reading assessment (future component).
    
    ### 4.3 Roadmap (pitch as roadmap, do not claim as built)
    - R-1: Decodable mother-tongue material generation constrained to taught graphemes.
    - R-2: Child micro-diagnostics (phoneme isolation, blending) via forced alignment.
    - R-3: Digits-in-noise hearing pre-screen with referral routing.
    - R-4: Prosody-based question detection (rising intonation) to refine wait-time.
    - R-5: HarmonyOS native client (see §7.4).
    
    ---
    
    ## 5. Indicator specification (the core contract)
    
    All indicators derive from an ordered, non-overlapping timeline with integer 10 ms ticks and half-open intervals. Acoustic labels are `{TEACHER, CHILD_SINGLE, CHILD_CHORAL, OVERLAP, NON_SPEECH}`. `MISSING` is capture/pipeline metadata, never a sixth learned acoustic class. Every elapsed tick must be observed or missing. Language decisions use `{amh, sid, eng, oth, unknown}`; `unknown` means insufficient evidence, not another language.
    
    The following are proposed v2 semantics, to freeze with golden fixtures at gate G1 in the implementation review:
    
    - Missing capture, queue overflow, permission loss and interruption split observed spans. Never smooth, bridge, count a turn or infer silence across them. Report elapsed, observed and missing duration separately.
    - Smoothing parameters (minimum segment 0.3 s, same-label gap bridging below 0.25 s) remain candidates. Preserve original timing for latency and silence: bridged gaps must not become speech duration. Specify tie-breaking, operation order, edge handling and bounded lookahead before implementation.
    - LID initially consumes complete 3 s teacher-only windows. Short/uncertain speech stays `unknown`; do not inherit neighbouring language. E3 may revise the threshold with a spec/model version change. Preserve within-turn code switches at window resolution.
    
    | ID | Indicator | Proposed definition and missing-data behavior |
    |---|---|---|
    | I-1 | Teacher talk ratio | Exclusive TEACHER duration / observed speech duration (TEACHER + CHILD_* + OVERLAP). Also report / observed lesson duration and overlap share; overlap is not attributed to the teacher. Zero denominator → null. |
    | I-2 | Response latency | Adjacent TEACHER → CHILD_* speech turns separated only by 0–10 s observed NON_SPEECH; OVERLAP or MISSING breaks eligibility. Median, P25/P75 and n, separately for single and choral. No events → null quantiles, n=0. This is not question-based wait time and must not trigger a 3 s coaching rule. |
    | I-3 | Choral balance | CHILD_CHORAL / CHILD_* duration, plus separately defined qualifying-segment count ratio. Zero denominator → null. Neither direction is inherently better. |
    | I-4 | Individual responses | Count CHILD_SINGLE segments ≥ 0.5 s, plus count / observed minutes × 10. Null rate when no observed time. |
    | I-5 | Turn density | TEACHER ↔ CHILD_* alternations within uninterrupted observed spans; intervening NON_SPEECH allowed, OVERLAP breaks the pair. Divide by observed minutes. |
    | I-6 | Language shares | Duration by language / all exclusive TEACHER duration, including `unknown`; also report classified coverage. No teacher speech → null shares. Shares sum to one when defined. |
    | I-7 | Interaction coverage | Fixed lesson-relative 60 s bins; only wholly observed bins eligible (including a wholly observed final partial bin). Share with ≥1 eligible alternation, assigned at the destination turn start; report eligible-bin count. No eligible bins → null. |
    | I-8 | Long silence | Count original contiguous observed NON_SPEECH runs ≥60 s; MISSING splits runs. Interpret neutrally. |
    
    `indicator_spec_version`, feature/postprocess versions and model versions travel together. Python is the reference and Kotlin the port: compare parsed outputs, exact categorical/integer/null fields and absolute float tolerance 1e-6, not JSON bytes. Freeze quantile interpolation and rounding rules at G1. Low-coverage records can be retained with quality flags but cannot produce coaching or district aggregates until eligibility thresholds are fixed. No coaching is enabled before indicator validity and teacher co-design gates pass. At most one focus is shown each week.
    
    ---
    
    ## 6. Hard constraints
    
    | ID | Constraint | Consequence for design |
    |---|---|---|
    | C-1 | **No ASR or speech-derived text.** | Speech models operate on acoustics only. UI text, translations and rule-based reports are allowed; transcription is out of scope. |
    | C-2 | **Target device:** Android 10+ (API 29), arm64-v8a, **2 GB RAM**, 4–8 × Cortex-A53/A55 class cores, no NPU assumed, 16 GB storage, often shared. | All inference on CPU; INT8 convolution-only candidate preferred, FP16/FP32 recurrent candidate permitted only if measured budgets pass. Peak app RSS ≤ 300 MB. Model files total ≤ 20 MB. |
    | C-3 | **Real-time streaming.** | Real-time factor (RTF) ≤ 0.3 on target device, so a 40-min lesson processes live without falling behind and the phone stays usable. |
    | C-4 | **Intermittent connectivity.** | Fully functional offline. Sync is opportunistic, resumable, idempotent. Payloads small (indicators JSON, KB-scale). |
    | C-5 | **Noisy, reverberant classrooms,** 40–80 children, single cheap mic, hard walls, outside noise. | Heavy augmentation; evaluation on real classroom audio only. |
    | C-6 | **Two languages, two scripts:** Amharic (Ge'ez), Sidaamu Afoo (Latin). English code-switching common. | LID must handle all three + "other". Cross-language evaluation is mandatory. |
    | C-7 | **Data protection law:** Ethiopia Personal Data Protection Proclamation No. 1321/2024 (in force 24 Jul 2024). Reported to require consent, **local storage of personal data on servers in Ethiopia**, 72-hour breach notification, and special protection for minors (under 16). [VERIFY with faculty legal advisor — not legal advice] | Personal data (including voice audio and teacher-linked indicators) stored in-country. Cross-border transfer of raw classroom audio avoided by design (see §11, ADR-003). |
    | C-8 | **Children are recorded incidentally.** | No child-level features stored. No voice embeddings persisted. Deployment Mode never writes raw audio to disk. Ethics-approved consent/assent and school permissions before any classroom capture, including transient processing; legal questions remain in WS-F. |
    | C-9 | **Timeline:** Working demo required for the national stage (Jan–Feb 2027). | Architecture must allow a thin vertical slice end-to-end by early December 2026. |
    | C-10 | **Team of 3, students.** | Prefer boring, well-documented tech outside the ML core. No bespoke distributed systems. |
    | C-11 | **Huawei stack preference** for competition credibility, without compromising C-2 and C-7. | See §7. Research audit reports Huawei Cloud deployment mandatory for the competition build. Use a separate synthetic-only demo; confirm exact regional requirements. Prefer Huawei components where tested; preserve documented fallbacks. |
    
    ---
    
    ## 7. Huawei technology mapping
    
    ### 7.1 Principles
    - Use Huawei technology where it is **load-bearing**, not decorative. Judges probe in Q&A.
    - Keep an **escape hatch** (ONNX) for every model so a framework bug cannot sink the timeline.
    - The deployment-critical path (phone + in-country server) must not depend on a foreign cloud region (C-7).
    
    ### 7.2 Mapping table
    
    | Layer | Huawei technology | Role in Dimts | Why / caveat |
    |---|---|---|---|
    **Performance budget (target device, 40-min lesson):**
    - RTF ≤ 0.3 total (segmentation + LID + indicators).
    - Peak RSS ≤ 300 MB.
    - Battery ≤ 10% per 40-min lesson [measure].
    - Models: preferred segmentation ≤ 8 MB and LID ≤ 8 MB; hard combined ceiling ≤ 20 MB at actual shipped precision.
    
    ---
    
    ## 9. ML design
    
    ### 9.1 Tasks
    - **T-1 Segmentation:** 5-class frame labelling {TEACHER, CHILD_SINGLE, CHILD_CHORAL, OVERLAP, NON_SPEECH}.
    - **T-2 Language ID:** 4-class segment labelling {amh, sid, eng, oth} over teacher speech.
    
    ### 9.2 Data sources
    
    | Source | Content | Use | Location |
    |---|---|---|---|
    | D-1 Classroom Research Mode recordings | 20–30 lessons × ~40 min, Amharic + Sidaamu Afoo schools | Fine-tune teacher model, train student, **evaluate** | In-country research store only |
    | D-2 Annotated subset of D-1 | 6–10 hours, frame labels + language labels | Supervised training + test sets | In-country |
    | D-3 Public Amharic speech (e.g., ALFFA Amharic, FLEURS Amharic) [VERIFY licences & availability] | Adult read/spontaneous speech | LID training, encoder adaptation | Cloud only after licence and privacy review |
    | D-4 Sidaamu Afoo speech | Afrivoice/WAXAL/MMS resources reported by audit; verify speaker overlap, licences and classroom-domain suitability | LID training | In-country unless approved for the cloud data register |
    | D-5 Public noise / reverberation / babble corpora (e.g., MUSAN-style, RIR sets) [VERIFY licences] | Augmentation | Anywhere |
    | D-6 Synthetic classroom mixtures | Adult speech + child-babble + RIR + choral simulation (time-aligned copies with jitter/pitch shift) | Pre-training the student before real data arrives | Cloud only for approved inputs; mixing public voices is not anonymisation |
    
    **Rule:** the **test set is real classroom audio only** (D-2 held-out lessons, split by teacher and by school, never by random frames).
    
    ### 9.3 Annotation
    - Tool: Label Studio (self-hosted on the in-country server) or equivalent audio segmentation tool [DECISION: ADR-006].
    - Protocol: written annotation guide in `docs/annotation-guide.md` with audio examples for each class, especially CHILD_SINGLE vs CHILD_CHORAL vs OVERLAP boundary cases.
    - Agreement: double-annotate ≥ 15% of hours; report Cohen's κ on frame labels. Target κ ≥ 0.70 before training on single-annotated data.
    - Annotators: team + trained native speakers for language labels. Pay/credit per ethics approval.
    
    ### 9.4 Models
    - **Teacher model:** multilingual self-supervised speech encoder (loaded via PyTorch/Hugging Face) + frame classification head (T-1) and pooled head (T-2). Fine-tuned on D-2, adapted on D-3/D-4. Server-side only.
    - **Student models:** small CRNN or compact conformer on log-mel. Trained on D-2 hard labels + teacher soft labels on unannotated D-1 + D-6. Loss: CE on hard labels + KL to teacher posteriors (temperature T tuned).
    - **Quantization:** evaluate supported INT8 and mixed-precision candidates; use a training/development-only real classroom calibration subset for the pilot. The synthetic demo uses approved public/staged calibration and makes no classroom accuracy claim.
    
    ### 9.5 Export path
    ```
    MindSpore (train) → export MindIR → MindSpore Lite converter → .ms (FP32) → PTQ INT8 → .ms (INT8)
    Escape hatch: framework-agnostic training → ONNX → MindSpore Lite converter → .ms
    ```
    S3 compares each candidate to its own source model on identical features; require cross-framework equality only for explicitly weight-equivalent graphs. Quantization is assessed separately by task and indicator degradation. Run public-fixture checks in CI; real-data calibration/evaluation runs only in-country.
    
    ---
    
    ## 10. Data model and flows
    
    ### 10.1 What leaves the device (Deployment Mode)
    
    ```jsonc
    // LessonRecord v2 — illustrative subset; freeze complete schema at G1 before coding
    {
      "record_id": "uuidv7",
      "schema_version": 2,
      "indicator_spec_version": 2,
      "model_versions": { "seg": "seg-student-0.3.1-int8", "lid": "lid-student-0.2.0-int8" },
      "teacher_pseudo_id": "opaque, server-issued at enrolment",
      "school_id": "opaque",
      "grade": "O|1|2|3",
      "subject": "mother_tongue|math|english|environmental|other",
      "lesson_start_local": "2026-11-03T08:15:00+03:00",
      "duration_s": 2400,
      "capture": { "observed_s": 2400, "missing_s": 0, "mic": "builtin" },
      "processing_versions": { "features": "candidate-v1", "postprocess": "candidate-v2" },
      "indicators": {
        "I1_teacher_talk_ratio": 0.78,
        "I2_response_latency_s": { "single": { "median": 0.6, "p25": 0.3, "p75": 1.1, "n": 12 }, "choral": { "median": 0.2, "p25": 0.1, "p75": 0.3, "n": 30 } },
        "I3_choral_ratio": 0.91,
        "I4_individual_responses": { "count": 5, "per_10min": 1.25 },
        "I5_turn_density_per_min": 1.9,
        "I6_language_share": { "amh": 0.62, "sid": 0.31, "eng": 0.05, "oth": 0.01, "unknown": 0.01 },
        "I7_interaction_coverage": 0.55,
        "I8_long_silence_events": 1
      },
      "quality": { "mean_seg_confidence": 0.84, "clipping_ratio": 0.001, "snr_estimate_db": 11.2 },
      "device": { "model_class": "arm64-2gb", "app_version": "0.4.0" }
    }
    ```
    
    No audio, no embeddings, no timestamps finer than lesson start, no child-level data.
    
    ### 10.2 Server entities (openGauss)
    `teacher` (pseudo_id, school_id, consent_state, language_pref) · `school` · `woreda` · `lesson_record` · `weekly_report` · `aggregate_cell` (woreda × grade × week, suppressed if n < k) · `consent_event` (append-only ledger) · `audit_log` · `research_recording` (Research Mode only, pointer to encrypted blob, consent_ref).
    
    ---
    
    ## 14. Evaluation plan (definition of done)
    
    ### 14.1 Model metrics (real classroom test set, split by teacher/school; frozen before training)
    
    | Metric | Provisional target (thesis) | Stretch |
    |---|---|---|
    | T-1 frame macro-F1 (5 classes) | ≥ 0.70 | ≥ 0.80 |
    | T-1 CHILD_SINGLE vs CHILD_CHORAL F1 | ≥ 0.75 | ≥ 0.85 |
    | T-2 LID segment accuracy (teacher segments ≥ 3 s) | ≥ 0.85 | ≥ 0.92 |
    | Student vs teacher-model gap (macro-F1) | ≤ 0.08 | ≤ 0.04 |
    | INT8 vs FP32 gap (macro-F1) | ≤ 0.02 | ≤ 0.01 |
    
    ### 14.2 Indicator validity (the metric that matters)
    All targets are prospective. Tune thresholds and calibration on development data only; never use the locked test set for distillation, PTQ calibration or threshold selection. Report uncertainty and denominators, including unknown LID and missing capture.
    Compare Dimts indicators against human-coded indicators on held-out lessons:
    - I-1 teacher talk ratio: mean absolute error ≤ 5 percentage points.
    - I-2 response latency median: MAE ≤ 0.5 s.
    - I-3 choral ratio: MAE ≤ 10 percentage points.
    - Across lessons, Spearman ρ between Dimts and human indicators ≥ 0.80 for I-1, I-3, I-5, I-6.
    
    ### 14.3 Cross-language generalisation (thesis contribution)
    - Train T-1 on Amharic-school lessons only; test on Sidaamu Afoo-school lessons. Report degradation vs in-language baseline.
    - Hypothesis: segmentation degrades little (acoustic, script-independent); LID requires both languages in training. Report honestly either way.
    
    ### 14.4 System metrics (target device)
    - End-to-end RTF ≤ 0.3 including capture/features/LID/storage; bounded backlog and measured emission latency; peak RSS ≤ 300 MB; battery ≤ 10% / 40 min; crash-free sessions ≥ 99% in pilot.
    - Sync: 100% of valid, non-withdrawn records eventually delivered in a simulated 72-hour offline test with random disconnects.
    
    ### 14.5 Pilot / usefulness
    - ≥ 6 teachers, ≥ 3 weeks of use, both languages.
    - Short structured teacher interviews (reuse your survey-methods skills): perceived usefulness, trust, discomfort. Pre/post change in I-2 and I-4 as exploratory (not causal) evidence.
    
    ---
    
    ## 15. Team and ownership
    
    | Person | Owns | Key deliverables |
    |---|---|---|
    | **Anteneh (lead)** | Data, ethics, indicator spec, evaluation design, pilot, thesis, pitch | Ethics approval, consent forms (3 languages), annotation guide + κ, indicator spec v2, eval report, demo script |
    | **Person 2 (ML)** | `ml/`, model cards, export | Teacher model, student models, INT8 `.ms` artefacts, eval numbers |
    | **Person 3 (systems)** | `edge/android/`, `server/`, `dashboard/`, `infra/` | Streaming pipeline, encrypted storage, sync, server on openEuler + openGauss, dashboard |
    | Shared | `indicators-core/` | Python reference (lead), Kotlin port (P3), golden vectors (lead + P3) |
    
    If no teammate can fine-tune speech models, that is the first gap to close.
    # Dimts research pack
    
    Research audit and plan for the Dimts (ድምጽ) proposal described in `DIMTS_ARCHITECTURE_CONTEXT.md` (status "pre-architecture", 2026-09-25). Research evidence updated 2026-09-26; implementation-plan review updated 2026-09-27. A basic Android Compose starter exists; other components are placeholders. No runtime or product behavior has been validated.
    
    | File | What it is |
    |---|---|
    | [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md) | Current repository baseline, architecture findings, contract checklist and implementation gates G0–G5. Read before implementing. |
    | [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) | Each factual claim and assumption in the architecture document, checked against sources (2026-09-25; updated 2026-09-26 with supplied full texts), with a verdict and the change it implies. |
    | [`RESEARCH_PLAN.md`](RESEARCH_PLAN.md) | What is still open: research questions, reading lists, experiments, infrastructure spikes, legal questions, team decisions and a schedule merged with the 2026–27 competition calendar. |
    | [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md) | Every source used or recommended, in APA 7th style, tagged with how far it was checked. |
    | [`ASSUMPTIONS.md`](ASSUMPTIONS.md) | Register of the 50 assumptions the design rests on: origin, status, evidence, test and impact if wrong. |
    | [`CODEBASE_STRUCTURE.md`](CODEBASE_STRUCTURE.md) | Proposed monorepo layout revised from §12 in light of the findings, including the two-tier (competition cloud / in-country pilot) deployment. |
    | [`TO_OBTAIN.md`](TO_OBTAIN.md) | Sources still needed, and what the supplied batch resolved. |
    | [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md) | Ethiopian hosting/cloud providers checked against Article 22 (data sovereignty), for the `infra/openeuler` sovereign-tier server. |
    | [`papers/`](papers/) | Full texts supplied on 2026-09-26. Copyrighted, so keep them private (see TO_OBTAIN). |
    
    ## Verdict labels
    
    The bar for **verified** is a primary source, a peer-reviewed paper, or official documentation or software that was actually opened. When the primary could not be opened, an abstract or a reputable secondary source is used and labelled as such. Neither counts as verified.
    
    | Label | Meaning |
    |---|---|
    | **VERIFIED** | Confirmed in a primary, peer-reviewed or official source that was opened (full text, official page, released package or binary). |
    | **VERIFIED (abstract)** | Confirmed only from the primary source's own abstract or registry metadata (Crossref, ERIC, Europe PMC). |
    | **SECONDARY** | Supported only by a secondary source (blog, press, search-engine summary). The primary was not opened. |
    | **PARTLY** | The fact is right but the claim goes beyond it (scope, date, sample, generalisation). |
    | **CONTRADICTED** | A primary source says otherwise. |
    | **UNVERIFIED** | Could not be checked. The plan says how to check it. |
    
    ## Access limits during the first pass (2026-09-25)
    
    Most of the gaps below were closed by the papers supplied on 2026-09-26; see [`TO_OBTAIN.md`](TO_OBTAIN.md).
    
    - **Refused by the site's bot protection (HTTP 403):** Wiley/Hindawi, Taylor & Francis, SAGE, ScienceDirect, ResearchGate, policycommons.net and the Huawei Talent portal. For those papers, Crossref, ERIC or Europe PMC metadata and abstracts were used instead.
    - **USAID's document archive** (`pdf.usaid.gov`, `dec.usaid.gov`) was unreachable (gateway 502), so the EGRA 2014/2016/2018 reports could not be opened.
    - **`web.archive.org` and several `.gov.et` hosts** were unreachable.
    - **`moe.gov.et`** served Indonesian gambling spam on its `/resources/annual-abstract/` pages. The site looks compromised, so nothing was downloaded from it. Get the ESAA from the Ministry through official channels.
    - **Search results are not evidence.** Web-search summaries were used only to find sources. Anything that rests on a search summary is labelled SECONDARY.
    ✓ • 0ms

## Activity

    $ cat > /tmp/echo_stem_docs.py <<'PY'
        from pathlib import Path
        
        def change(name, replacements):
         p=Path(name); text=p.read_text()
         for old,new in replacements:
          if old not in text: raise ValueError(f'{name}: {old[:70]}')
          text=text.replace(old,new)
         p.write_text(text)
        
        change('docs/DIMTS_ARCHITECTURE_CONTEXT.md',[
         ('The segmentation design avoids transcription;', 'The first classroom test phase is undergraduate STEM, used for technical setup validation before the intended early-grade population (§3.1). The segmentation design avoids transcription;'),
         ('## 3. Users and scenarios\n', '''## 3. Users and scenarios
        
        ### 3.1 Test undergraduate STEM first
        
        Sequence: synthetic/staged fixtures → undergraduate STEM technical trial → intended O-class/Grades 1–3 validation. See [STEM_TRIAL_PLAN.md](research/STEM_TRIAL_PLAN.md) and [ADR-014](adr/014-staged-classroom-trials.md). The STEM phase tests capture, microphones, streaming, storage, sync and private technical reports. Adult classroom results cannot validate child-speech classes, early-grade coaching or mother-tongue policy measurement.
        
        Maintain separate cohorts, label/model contracts, approvals, dataset partitions and result tables. P1 has instructor/student roles and course/session context, not child labels or O–3 grades. A child-only model is incompatible; use capture/coarse-activity testing or explicitly human-coded timelines until a STEM-compatible model is available. No automatic logit relabelling. Both classroom cohorts use the in-country tier; the cloud demo stays synthetic.
        
        ### 3.2 Intended-population users and scenarios
        '''),
         ('All indicators derive from an ordered', 'The definitions below apply to the **early-grade profile**. The STEM profile uses a separately versioned adult-role schema and exposes only validated descriptive measures; no child/choral metric or early-grade coaching is inferred by relabelling.\n\nAll indicators derive from an ordered'),
         ('### 7.1 Principles\n', '### 7.1 Principles\nCurrent official-source findings and test links are in [INFRASTRUCTURE_RECHECK.md](research/INFRASTRUCTURE_RECHECK.md). Exact versions, hardware and regional service availability remain acceptance gates.\n'),
         ('PostgreSQL-lineage; standard drivers mostly work [VERIFY driver for chosen backend language].', 'Conditional on exact-driver, migration and authenticated-TLS tests: documented openGauss verify-full limitation is a blocker unless resolved; PostgreSQL is the explicit fallback (INF-09).'),
         ('**openEuler** (LTS)', '**openEuler** (24.03 LTS SP3 candidate)'),
         ('Room + SQLCipher with a random database key', 'Room + modern `sqlcipher-android` (`SupportOpenHelperFactory`) with a random database key'),
         ('WorkManager periodic job, network-constrained; exponential backoff; resumable; server returns per-record ACKs', 'Unique network-constrained one-time WorkManager jobs on enqueue; periodic recovery (minimum 15 min, not exact scheduling); backoff and durable per-record ACKs'),
         ('### 9.1 Tasks\n', '### 9.1 Tasks\nT-1/T-2 below target early grades. STEM has separate supported-cohort/label manifests; adult instructor/student attribution needs its own validation.\n'),
         ('| D-1 Classroom Research Mode recordings', '| D-0 Undergraduate STEM trial | Proposed ≥6 sessions, ≥3 instructors, ≥2 courses; adult roles, actual languages/session durations recorded | First technical trial; exploratory adult-domain metrics, separate from child validation | In-country only, G4-U |\n| D-1 Classroom Research Mode recordings'),
         ('**Rule:** the **test set is real classroom audio only**', '**Rule:** intended-population validity uses an **early-grade real classroom test set only**'),
         ('- Protocol: written annotation guide', '- STEM requires a separate instructor/student/group-speech guide and independent agreement; lab chatter is not choral response. D-0 never pools with D-2 evaluation.\n- Protocol: written annotation guide'),
         ('"indicator_spec_version": 2,', '"indicator_spec_version": 2,\n  "cohort": "early_grade",\n  "session_type": "early_grade_lesson",\n  "label_schema_version": "early-grade-v1",'),
         ('No audio, no embeddings, no timestamps finer than lesson start, no child-level data.', 'No audio, no embeddings, no timestamps finer than lesson start, no student-level data. STEM v2 context uses `cohort=undergraduate_stem`, `session_type=lecture|tutorial|lab` and `course_category`, and forbids O–3 `grade`/early-grade `subject`. Persist cohort with enrolment and every dataset/report key; do not overload a school ID for a university course. Final institution/course identifiers and mutual-exclusion validation are frozen at G1.'),
         ('### 10.2 Server entities (openGauss)', '### 10.2 Server entities (selected database)'),
         ('### 10.3 Identity', 'Cohort extension: institution and course/session context for STEM, enrolment cohort, cohort-scoped report keys and dataset provenance. District aggregates consume early-grade records only.\n\n### 10.3 Identity'),
         ('| Approvals | Hawassa University research ethics review **before first recording**. School and woreda permission letters. |', '| Approvals | Separate G4-U undergraduate recruitment/consent and G4-E early-grade ethics/permissions **before recording each cohort**. Do not assume all undergraduates are adults. No participation tied to grades; practical decline alternative. |'),
         ('input/output shape and class order,', 'supported cohorts, label schema, input/output shape and class order,'),
         ('## 14. Evaluation plan (definition of done)\n', '''## 14. Evaluation plan (definition of done)
        
        Run [post-implementation suites](testing/POST_IMPLEMENTATION_TEST_PLAN.md) in stage order. The 18 prepared contract cases require actual implementation adapters; no product tests have run. P1 STEM establishes technical feasibility only; §14.1–14.3 and §14.5 remain P2 early-grade criteria. Report each cohort separately. Re-check conditions/models on P2 even if P1 passes.
        '''),
         ('battery ≤ 10% / 40 min; crash-free sessions ≥ 99% in pilot.', 'battery ≤ 10 percentage points / 40 min under documented conditions; report actual crashes/attempts and exposure. A small STEM sample cannot establish the longer-term 99% crash-free target. Also test the longest scheduled STEM session.'),
         ('### 14.5 Pilot / usefulness', '### 14.5 Intended early-grade pilot / usefulness (after STEM technical exit)'),
         ('Critical paths run separately: synthetic demo → competition submission; ethics + consent → classroom collection → annotation → held-out validation → private pilot reports.', 'Critical paths run separately: synthetic demo → competition submission; G4-U → STEM trial → technical exit → G4-E → early-grade collection → annotation → held-out validation → private pilot reports.'),
         ('| ADR-013 | Auth, withdrawal, retention and aggregate disclosure | G4/G5 negative tests and legal/ethics answers |', '| ADR-013 | Auth, withdrawal, retention and aggregate disclosure | G4/G5 negative tests and legal/ethics answers |\n| ADR-014 | Undergraduate STEM first, then early-grade validation | User-directed stage order; cohort/schema details proposed in [ADR-014](adr/014-staged-classroom-trials.md) |')])
        change('docs/research/RESEARCH_PLAN.md',[
         ('v1.1)', 'v1.2 — STEM first)'),
         ('- [`ASSUMPTIONS.md`]', '- [`STEM_TRIAL_PLAN.md`](STEM_TRIAL_PLAN.md) — first classroom phase and transition to early grades.\n- [`INFRASTRUCTURE_RECHECK.md`](INFRASTRUCTURE_RECHECK.md) — official docs rechecked 2026-09-27.\n- [`../testing/POST_IMPLEMENTATION_TEST_PLAN.md`](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) — contract, device, infrastructure and field suites.\n- [`ASSUMPTIONS.md`]'),
         ('real-data phases of E1–E7 move with ethics/consent and G4;', 'real-data phases run first on undergraduate STEM after G4-U; early-grade E2/E4/E6/E7 validation follows STEM exit and G4-E;'),
         ('## 3. Workstreams\n', '## 3. Workstreams\n\nUser-directed order: P0 synthetic/staged → P1 undergraduate STEM technical trial → P2 early-grade validation. P1 does not establish early-grade indicator accuracy or classroom usefulness. Languages/session types remain configurable until recruited; no assumption of English-only or adult-only enrolment.\n'),
         ('real classroom comparison only after G4.', 'first classroom comparison in STEM after G4-U; repeat in early grades after G4-E.'),
         ('| E2 | How hard is T-1 on real Ethiopian audio?', '| E2 | How hard is T-1 on intended early-grade Ethiopian audio?'),
         ('W3 public/staged baseline; classroom phase after G4', 'W3 public/staged baseline; STEM after G4-U, target languages in early grades after G4-E'),
         ('| E9 | Competition', '| E10 | STEM setup trial first | P1 sample and adult-role protocol in STEM_TRIAL_PLAN; hardware/storage/sync first, exploratory adult-role metrics only with compatible model | Technical exit before P2 | After G4-U |\n| E9 | Competition'),
         ('| S6 | openEuler LTS + openGauss + psycopg connect (SASL/sha256 workaround);', '| S6 | Pin maintained openEuler SP3 candidate + chosen DB/driver; test authenticated TLS including wrong hostname/CA rejection (INF-09); no generic SASL downgrade;'),
         ('| S7 | Microphone foreground service;', '| S7 | Modern target-SDK microphone permission/foreground-service lifecycle;'),
         ('G4; fixed-cohort disclosure tests before G5', 'G4-U; repeat changed paths for G4-E; cohort isolation and disclosure tests before G5-E'),
         ('| **S10** (new) | **Competition cloud stack:** ECS + RDS for PostgreSQL (or GaussDB)', '| **S10** (new) | **Competition cloud stack:** ECS + RDS for PostgreSQL (GaussDB only after a separate compatibility decision)'),
         ('Ethics: the Hawassa IRB (the critical path);', 'Add P1 questions: institution-approved instructor/student consent, age eligibility, withdrawal without academic consequences and a non-recording alternative; then obtain separate P2 scope/approval.\n\nEthics: the Hawassa IRB (the critical path);'),
         ('| **D-7** (new) | Microphone | Decide from E1. If desk SNR is poor, ship a wired lavalier as part of the kit. |', '| **D-7** (new) | Microphone | Decide from E1 in STEM, then recheck in early-grade rooms. If desk SNR is poor, ship a wired lavalier as part of the kit. |\n| D-8 / ADR-014 | First testing population | Undergraduate STEM first (user directed); isolate adult roles/data/results; later early-grade validation remains required. |'),
         ('The exact Preliminary deadline', 'P1 starts only after G4-U. P2 moves until STEM technical exit and G4-E pass; fixed competition dates do not imply target-population results will exist.\n\nThe exact Preliminary deadline'),
         ('E2/E3 real phases only after G4; E5, E7;', 'G4-U readiness; E10 STEM preparation, E3 public baseline; E5, E7 synthetic;'),
         ('ADR-002/003/011/012; G1–G2', 'ADR-002/003/011/012/014; G1–G2'),
         ('B2 panel; E4; **S10 cloud demo live**; S8 decision; E9', 'E10 STEM sessions if G4-U passes; B2 panel; E4 synthetic work; **S10 cloud demo live**; S8; E9'),
         ('E6 distillation; indicator validity v1 (§14.2) on pilot data', 'STEM technical report/exit; G4-E preparation; early-grade collection and E6 only when prerequisites pass'),
         ('Cross-language evaluation (§14.3); feature freeze', 'Early-grade work if authorised; cross-language evaluation only with sufficient P2 data; demo feature freeze'),
         ('Pilot continues; literature review chapter', 'Separate early-grade pilot/evaluation continues after G4-E; literature review chapter'),
         ('7. **New:** does the team accept making the product code open source (a Regional requirement)?', '7. **New:** does the team accept making the product code open source (a Regional requirement)?\n8. Which STEM courses, instructors, languages and session formats/durations are available for P1? Recruitment plan defaults are in STEM_TRIAL_PLAN; confirm actual numbers before capture.')])
        change('docs/research/IMPLEMENTATION_REVIEW.md',[
         ('**Planning changes only; no implementation, builds, device benchmarks or deployment performed.**', '**No product implementation, builds, device benchmarks or deployment performed.** Follow-up adds a future acceptance harness/fixtures and the user-directed STEM-first trial; harness self-checks are not product validation.'),
         ('This review governs unresolved implementation details;', 'See [STEM_TRIAL_PLAN.md](STEM_TRIAL_PLAN.md), [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md) and [test suites](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) for the 2026-09-27 follow-up. This review governs unresolved implementation details;'),
         ('`docs/adr/` contains no ratified decisions.', 'ADR-014 records the user-directed trial sequence; detailed schema/runtime decisions remain proposed.'),
         ('| G4 — real-data readiness | Lead + Sys | Ethics/consent/legal answers, confirmed in-country processing/storage/backups, mode separation, backup exclusions, shared-device lock/logout, Research Mode encrypted transfer verification, revocation/purge tests. Classroom collection cannot precede this gate. |', '| G4-U / G4-E — cohort readiness | Lead + Sys | G4-U authorises the first undergraduate STEM phase after participant/age eligibility, institutional consent and privacy tests. G4-E separately authorises early grades after STEM technical exit and child/school requirements. Both require in-country storage/backups, mode separation, shared-device controls and transfer/revocation/purge tests. |'),
         ('| G5 — validity and controlled release |', '| G5-E — early-grade validity and controlled release |'),
         ('- `LessonRecord` includes schema/spec/model/feature/postprocess versions,', '- `LessonRecord` includes cohort/session context, supported-cohort label schema, schema/spec/model/feature/postprocess versions,'),
         ('this review did not revalidate every paper, competition rule, provider price or legal interpretation.', 'the follow-up infrastructure recheck opens current official technical docs but does not revalidate every paper, competition rule, provider price or legal interpretation.')])
        change('docs/research/CODEBASE_STRUCTURE.md',[
         ('All described product files and other modules remain proposed and require G0–G5.', 'All described product files and other modules remain proposed and require G0–G5. Prepared test harness/fixtures now live in `tests/acceptance`; see the STEM-first plan and infrastructure recheck.'),
         ('ADR-001…013 (011 deployment; 012 contracts; 013 security)', 'ADR-001…014 (014 STEM-first trial)'),
         ('│   ├── annotation-guide.md', '│   ├── testing/                     ← post-implementation suite/runbook (prepared)\n│   ├── annotation-guide.md'),
         ('│   │   └── manifests/                ← file lists + splits by teacher/school; NO audio in git', '│   │   └── manifests/                ← separate STEM instructor/course and early-grade teacher/school splits; NO audio in git'),
         ('├── indicators-core/                  ← single source of truth', '├── tests/acceptance/                 ← prepared runner + 18 contract vectors; implementation adapters still needed\n├── indicators-core/                  ← single source of truth'),
         ('│   ├── storage/                      ← Room + SQLCipher;', '│   ├── storage/                      ← Room + modern sqlcipher-android;'),
         ('│   ├── sync/                         ← WorkManager outbox;', '│   ├── sync/                         ← one-time WorkManager outbox + periodic recovery;'),
         ('│   ├── api/                          ← /v1/lessons', '│   ├── api/                          ← cohort-scoped enrolment/context; /v1/lessons'),
         ('"schema_version": 2,', '"schema_version": 2,\n  "cohort": "early_grade",\n  "session_type": "early_grade_lesson",\n  "label_schema_version": "early-grade-v1",'),
         ('No previous version is implemented.', 'No previous version is implemented. STEM requires a distinct context without O–3 grade, compatible model/labels, and cohort-isolated reports/aggregates (ADR-014).')])
        change('docs/research/README.md',[
         ('(status "pre-architecture", 2026-09-25)', '(reviewed proposal, 2026-09-27)'),
         ('| [`IMPLEMENTATION_REVIEW.md`]', '| [`STEM_TRIAL_PLAN.md`](STEM_TRIAL_PLAN.md) | Undergraduate STEM technical trial first, then separate early-grade validation. |\n| [`INFRASTRUCTURE_RECHECK.md`](INFRASTRUCTURE_RECHECK.md) | Current official infrastructure findings, proposed choices and linked acceptance cases. |\n| [`../testing/POST_IMPLEMENTATION_TEST_PLAN.md`](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) | Test procedures, acceptance criteria and evidence template; links to 18 executable contract cases needing implementation adapters. |\n| [`IMPLEMENTATION_REVIEW.md`]')])
        # Preserve the earlier audit's 50-entry count and add an explicitly separate register.
        p=Path('docs/research/ASSUMPTIONS.md'); s=p.read_text(); s+='''
        
        ## Follow-up register — STEM-first trial (2026-09-27)
        
        The 50-entry counts above cover the original audit only. Stage order is user-directed, not an assumption. These additional assumptions remain open:
        
        | ID | Assumption | Status / test | Consequence |
        |---|---|---|---|
        | A-U1 | Undergraduate participants are all adults. | Not assumed; eligibility and approved consent at G4-U. | Apply appropriate procedure or exclude minors before recording. |
        | A-U2 | Child-role model transfers to adult instructor/student attribution. | Untested; STREAM-02/MODEL-01; do not relabel logits. | Capture/coarse-activity only or compatible adult model/human annotations. |
        | A-U3 | STEM rooms/mics/languages represent early grades. | Untested; E1/E3 repeated independently in P2. | P1 technical success cannot replace G5-E validity. |
        | A-U4 | ≥3 instructors/≥2 courses/6 sessions can be recruited. | Planning target; confirm formats, durations, languages and voluntary participation. | Report actual sample and unsupported conditions. |
        | A-U5 | Selected DB/driver verifies server identity. | Open; INF-09/DB-01 documents openGauss verify-full limitation. | Ratify authenticated alternative or PostgreSQL before real data. |
        
        See [STEM_TRIAL_PLAN.md](STEM_TRIAL_PLAN.md) and [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md). Neither classroom cohort may export personal or pilot-derived data to the synthetic cloud demo.
        '''; p.write_text(s)
        p=Path('docs/research/HOSTING_OPTIONS.md'); s=p.read_text(); i=s.index('\n'); s=s[:i]+ '\n\n> 2026-09-27 follow-up: this is a dated provider shortlist, not a selected host. The undergraduate STEM trial uses the same in-country privacy boundary as early grades. See [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md): openEuler service-pack pinning, exact database/driver TLS identity validation (openGauss limitation), provider backup/location terms and restore tests are mandatory selection gates. Prices below were not revalidated in this pass.\n'+s[i:]; p.write_text(s)
        PY
        python3 /tmp/echo_stem_docs.py
        cat > docs/adr/014-staged-classroom-trials.md <<'EOF'
        # ADR-014: Undergraduate STEM technical trial before early-grade validation
        
        Date: 2026-09-27. Status: **Stage order directed by user; detailed schema/model design proposed for G1 ratification.**
        
        ## Context
        
        The intended product serves O-class and Grades 1–3, but the user plans to test the setup first in undergraduate STEM classrooms. Audio roles, participant ages, session formats and languages differ. Neither the starter app nor the proposed models have established classroom readiness.
        
        ## Decision
        
        Proceed P0 synthetic/staged → P1 undergraduate STEM technical trial → P2 intended early-grade validation. Use the in-country pilot tier for both real cohorts and keep the Huawei Cloud demo synthetic. P1 is a setup/feasibility trial, with separate approvals and voluntary participant consent, not evidence of child-speech accuracy or early-grade pedagogical value.
        
        Require cohort/session context, label schema and model supported-cohort declarations. STEM annotations describe adult instructor/student roles; child-only model logits cannot be renamed into those roles. Keep manifests/calibration/evaluation/reports isolated by cohort. Disable early-grade coaching and district aggregation for STEM. If no adult-compatible model exists, P1 can still test capture, coarse activity, storage/sync and human-coded indicator arithmetic with explicit provenance.
        
        ## Alternatives considered
        
        - Begin in early grades: superseded by the user's requested order.
        - Reuse child labels and combine results: rejected because it conceals the domain change.
        - Build a separate university product: unnecessary scope; use shared infrastructure with explicit cohort contracts.
        
        ## Consequences and validation
        
        P1 precedes P2 recruitment/capture readiness, with distinct G4-U/G4-E approvals. Technical fixes can transfer, but microphones, model/indicator accuracy and coaching need early-grade revalidation. Dates move when prerequisites slip. [STEM_TRIAL_PLAN.md](../research/STEM_TRIAL_PLAN.md) owns trial details; [acceptance suites](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) own test procedures. No product implementation is authorised by this ADR alone.
        EOF
    ✓ • 433ms

## Activity

    $ python3 - <<'PY'
        from pathlib import Path
        p=Path('tests/acceptance/runner.py'); s=p.read_text(); old='''    return json.loads(text, parse_constant=invalid)
        '''; new='''    def pairs(items):
                result = {}
                for key, value in items:
                    if key in result:
                        raise ValueError("Duplicate JSON key")
                    result[key] = value
                return result
        
            def finite(value):
                if isinstance(value, float) and not math.isfinite(value):
                    raise ValueError("Non-finite JSON number")
                if isinstance(value, dict):
                    for item in value.values():
                        finite(item)
                elif isinstance(value, list):
                    for item in value:
                        finite(item)
        
            result = json.loads(text, parse_constant=invalid, object_pairs_hook=pairs)
            finite(result)
            return result
        '''; assert old in s; p.write_text(s.replace(old,new))
        p=Path('tests/acceptance/test_harness.py'); s=p.read_text().replace('import unittest','import json\nfrom pathlib import Path\nimport subprocess\nimport sys\nimport unittest').replace("['NaN', 'Infinity', '-Infinity']","['NaN', 'Infinity', '-Infinity', '{\"extra\": 1e999}', '{\"n\": 1, \"n\": 2}']")
        s=s.replace("\n\nif __name__ == '__main__':", '''
            def test_missing_adapter_is_not_a_pass(self):
                proc = subprocess.run([sys.executable, str(Path(__file__).with_name('runner.py'))],
                                      capture_output=True, text=True)
                self.assertEqual(proc.returncode, 2)
                self.assertIn('Implementation adapter required', proc.stderr)
                self.assertNotIn('PASS', proc.stdout)
        
            def test_bad_adapter_results_fail_all_cases(self):
                # Deliberately wrong test double verifies IPC/failure handling only.
                command = json.dumps([sys.executable, '-c', 'print("{}")'])
                proc = subprocess.run([sys.executable, str(Path(__file__).with_name('runner.py')),
                                       '--adapter', command], capture_output=True, text=True)
                self.assertEqual(proc.returncode, 1)
                self.assertEqual(proc.stdout.count('FAIL '), len(load_vectors()))
                self.assertIn('0/18 contract cases passed', proc.stdout)
        
        
        if __name__ == '__main__':'''); p.write_text(s)
        PY
        PYTHONDONTWRITEBYTECODE=1 python3 -m unittest discover -s tests/acceptance -p 'test_harness.py'
        python3 tests/acceptance/runner.py --validate
        git diff --check
        rg -n 'G4\b|G5\b|SASL|periodic job|first.*lesson|classroom.*after|STEM|verify-full' docs/research/IMPLEMENTATION_REVIEW.md docs/research/RESEARCH_PLAN.md docs/DIMTS_ARCHITECTURE_CONTEXT.md
    ......
    ----------------------------------------------------------------------
    Ran 6 tests in 0.783s
    
    OK
    Validated 18 fixture definitions; product tests NOT RUN.
    docs/research/IMPLEMENTATION_REVIEW.md:3:Reviewed 2026-09-27. **No product implementation, builds, device benchmarks or deployment performed.** Follow-up adds a future acceptance harness/fixtures and the user-directed STEM-first trial; harness self-checks are not product validation.
    docs/research/IMPLEMENTATION_REVIEW.md:5:The edge / in-country pilot / separate Huawei Cloud demo architecture is a reasonable proposal. It is not ready for unrestricted implementation: the indicator and streaming contracts need to be frozen, device feasibility remains unmeasured, and real-data collection/release has unresolved ethics and security gates. See [STEM_TRIAL_PLAN.md](STEM_TRIAL_PLAN.md), [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md) and [test suites](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) for the 2026-09-27 follow-up. This review governs unresolved implementation details; the architecture owns product constraints, RESEARCH_PLAN owns dates, and CODEBASE_STRUCTURE owns the proposed layout. Prior audits remain dated evidence, not proof that a capability was built.
    docs/research/IMPLEMENTATION_REVIEW.md:43:| G4-U / G4-E — cohort readiness | Lead + Sys | G4-U authorises the first undergraduate STEM phase after participant/age eligibility, institutional consent and privacy tests. G4-E separately authorises early grades after STEM technical exit and child/school requirements. Both require in-country storage/backups, mode separation, shared-device controls and transfer/revocation/purge tests. |
    docs/research/IMPLEMENTATION_REVIEW.md:44:| G5-E — early-grade validity and controlled release | Lead + ML + Sys | Teacher/school-independent frozen real test set, annotation agreement, indicator error with intervals/coverage, quantized-device equivalence, coaching co-design, aggregate disclosure tests. Release only indicators that pass; invalid/unavailable outputs are null with reasons. |
    docs/research/IMPLEMENTATION_REVIEW.md:52:- Weekly offline reports need the same versioned deterministic rule table as server reports. Define week boundaries in Africa/Addis_Ababa, equal teacher weighting for district outputs, compatible-version grouping, minimum valid duration/lessons and when cached/server reports supersede local reports. G1 must fix thresholds; coaching stays disabled until G5.
    docs/research/RESEARCH_PLAN.md:1:# Research and implementation plan — Dimts (reviewed proposal, v1.2 — STEM first)
    docs/research/RESEARCH_PLAN.md:5:- [`STEM_TRIAL_PLAN.md`](STEM_TRIAL_PLAN.md) — first classroom phase and transition to early grades.
    docs/research/RESEARCH_PLAN.md:17:Week labels follow the milestone calendar in §5 (W0 starts 2026-09-25). Dates are targets: real-data phases run first on undergraduate STEM after G4-U; early-grade E2/E4/E6/E7 validation follows STEM exit and G4-E; public/staged work and the synthetic demo continue independently. Sources are cited (Author, year) and listed in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).
    docs/research/RESEARCH_PLAN.md:56:User-directed order: P0 synthetic/staged → P1 undergraduate STEM technical trial → P2 early-grade validation. P1 does not establish early-grade indicator accuracy or classroom usefulness. Languages/session types remain configurable until recruited; no assumption of English-only or adult-only enrolment.
    docs/research/RESEARCH_PLAN.md:108:| E1 | Usable audio: desk vs clip-on | W0–W1 authorised staged/adult fixtures; first classroom comparison in STEM after G4-U; repeat in early grades after G4-E. Measure SNR, clipping and RT60 estimate. Compare desk phone with a wired lavalier into the same phone (Schlotterbeck et al., 2021; Jensen et al., 2020). | Capture spec; ASSUMPTIONS A-T4 | W1 |
    docs/research/RESEARCH_PLAN.md:110:| E3 | LID accuracy at 2 / 3 / 5 s | MMS-LID-256 (amh/sid/eng + other) on FLEURS, Afrivoice and pilot teacher segments. | LID threshold (audit 5d) | W3 public/staged baseline; STEM after G4-U, target languages in early grades after G4-E |
    docs/research/RESEARCH_PLAN.md:116:| E10 | STEM setup trial first | P1 sample and adult-role protocol in STEM_TRIAL_PLAN; hardware/storage/sync first, exploratory adult-role metrics only with compatible model | Technical exit before P2 | After G4-U |
    docs/research/RESEARCH_PLAN.md:128:| S6 | Pin maintained openEuler SP3 candidate + chosen DB/driver; test authenticated TLS including wrong hostname/CA rejection (INF-09); no generic SASL downgrade; **host chosen from [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md)** — Zergaw VPS/DBaaS, INSA G-Cloud or Ethio Telecom teleCloud, not the on-campus box, unless the university prefers self-hosting | W1 |
    docs/research/RESEARCH_PLAN.md:131:| S9 | SQLCipher + Keystore key; backup/transfer exclusions, key-loss/reinstall and shared-phone tests before G4 | W2 |
    docs/research/RESEARCH_PLAN.md:133:| S11 | Sync ownership, ACK/conflict/retry, withdrawal and restore-purge tests per G4-U; repeat changed paths for G4-E; cohort isolation and disclosure tests before G5-E | Before pilot/release |
    docs/research/RESEARCH_PLAN.md:180:| **D-7** (new) | Microphone | Decide from E1 in STEM, then recheck in early-grade rooms. If desk SNR is poor, ship a wired lavalier as part of the kit. |
    docs/research/RESEARCH_PLAN.md:181:| D-8 / ADR-014 | First testing population | Undergraduate STEM first (user directed); isolate adult roles/data/results; later early-grade validation remains required. |
    docs/research/RESEARCH_PLAN.md:187:P1 starts only after G4-U. P2 moves until STEM technical exit and G4-E pass; fixed competition dates do not imply target-population results will exist.
    docs/research/RESEARCH_PLAN.md:193:| W0 | Sep 25 – Oct 4 | E1 staged/adult fixtures only (classroom work waits for G4); start S1, S4, S6; legal questions sent; ethics submitted; A3/A4 visits begin | Register team; download templates; confirm division with Huawei Ethiopia |
    docs/research/RESEARCH_PLAN.md:195:| W3–4 | Oct 19 – Nov 1 | A3 memo; B3, B5; G4-U readiness; E10 STEM preparation, E3 public baseline; E5, E7 synthetic; S2, S3; legal answers; ADR-002/003/011/012/014; G1–G2 | Draft Template 1 |
    docs/research/RESEARCH_PLAN.md:196:| W5–6 | Nov 2 – 15 | E10 STEM sessions if G4-U passes; B2 panel; E4 synthetic work; **S10 cloud demo live**; S8; E9 | Record demo video |
    docs/research/RESEARCH_PLAN.md:197:| W7–8 | Nov 16 – 29 | STEM technical report/exit; G4-E preparation; early-grade collection and E6 only when prerequisites pass | **Preliminary submission (assumed by 1 Dec)** |
    docs/research/RESEARCH_PLAN.md:199:| W11–16 | Dec 14 – Jan 31 | Separate early-grade pilot/evaluation continues after G4-E; literature review chapter from the search log | **National final (Jan 2027)** |
    docs/research/RESEARCH_PLAN.md:213:8. Which STEM courses, instructors, languages and session formats/durations are available for P1? Recruitment plan defaults are in STEM_TRIAL_PLAN; confirm actual numbers before capture.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:25:Dimts is a proposed offline, on-device classroom audio analysis system for early-grade (O-class and Grades 1–3) teachers in Ethiopia. A teacher places a low-end Android phone on her desk during a lesson. The phone analyses the audio **in real time, on the device**, without speech recognition, and produces pedagogical indicators: how much the teacher talks, how long she waits for children to respond, whether children answer individually or only in chorus, how often turns change, and which language the lesson is conducted in (Amharic, Sidaamu Afoo, English). Deployment Mode never persists or uploads raw audio; separately consented Research Mode transfers encrypted audio only to the in-country research store. Only derived indicators sync to an in-country server, where they feed a private weekly report to the teacher and minimum-group-suppressed aggregates to district education offices, subject to disclosure review. The first classroom test phase is undergraduate STEM, used for technical setup validation before the intended early-grade population (§3.1). The segmentation design avoids transcription; its cross-language robustness remains to be evaluated on Amharic and Sidaamu Afoo. LID is language-dependent. Script differences do not themselves establish acoustic generalisation.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:68:### 3.1 Test undergraduate STEM first
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:70:Sequence: synthetic/staged fixtures → undergraduate STEM technical trial → intended O-class/Grades 1–3 validation. See [STEM_TRIAL_PLAN.md](research/STEM_TRIAL_PLAN.md) and [ADR-014](adr/014-staged-classroom-trials.md). The STEM phase tests capture, microphones, streaming, storage, sync and private technical reports. Adult classroom results cannot validate child-speech classes, early-grade coaching or mother-tongue policy measurement.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:72:Maintain separate cohorts, label/model contracts, approvals, dataset partitions and result tables. P1 has instructor/student roles and course/session context, not child labels or O–3 grades. A child-only model is incompatible; use capture/coarse-activity testing or explicitly human-coded timelines until a STEM-compatible model is available. No automatic logit relabelling. Both classroom cohorts use the in-country tier; the cloud demo stays synthetic.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:127:The definitions below apply to the **early-grade profile**. The STEM profile uses a separately versioned adult-role schema and exposes only validated descriptive measures; no child/choral metric or early-grade coaching is inferred by relabelling.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:189:| Database | **openGauss** | Indicator records, reports, aggregates, consent ledger | Conditional on exact-driver, migration and authenticated-TLS tests: documented openGauss verify-full limitation is a blocker unless resolved; PostgreSQL is the explicit fallback (INF-09). |
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:257:T-1/T-2 below target early grades. STEM has separate supported-cohort/label manifests; adult instructor/student attribution needs its own validation.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:265:| D-0 Undergraduate STEM trial | Proposed ≥6 sessions, ≥3 instructors, ≥2 courses; adult roles, actual languages/session durations recorded | First technical trial; exploratory adult-domain metrics, separate from child validation | In-country only, G4-U |
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:277:- STEM requires a separate instructor/student/group-speech guide and independent agreement; lab chatter is not choral response. D-0 never pools with D-2 evaluation.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:333:No audio, no embeddings, no timestamps finer than lesson start, no student-level data. STEM v2 context uses `cohort=undergraduate_stem`, `session_type=lecture|tutorial|lab` and `course_category`, and forbids O–3 `grade`/early-grade `subject`. Persist cohort with enrolment and every dataset/report key; do not overload a school ID for a university course. Final institution/course identifiers and mutual-exclusion validation are frozen at G1.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:338:Cohort extension: institution and course/session context for STEM, enrolment cohort, cohort-scoped report keys and dataset provenance. District aggregates consume early-grade records only.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:359:| Approvals | Separate G4-U undergraduate recruitment/consent and G4-E early-grade ethics/permissions **before recording each cohort**. Do not assume all undergraduates are adults. No participation tied to grades; practical decline alternative. |
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:443:Run [post-implementation suites](testing/POST_IMPLEMENTATION_TEST_PLAN.md) in stage order. The 18 prepared contract cases require actual implementation adapters; no product tests have run. P1 STEM establishes technical feasibility only; §14.1–14.3 and §14.5 remain P2 early-grade criteria. Report each cohort separately. Re-check conditions/models on P2 even if P1 passes.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:468:- End-to-end RTF ≤ 0.3 including capture/features/LID/storage; bounded backlog and measured emission latency; peak RSS ≤ 300 MB; battery ≤ 10 percentage points / 40 min under documented conditions; report actual crashes/attempts and exposure. A small STEM sample cannot establish the longer-term 99% crash-free target. Also test the longest scheduled STEM session.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:471:### 14.5 Intended early-grade pilot / usefulness (after STEM technical exit)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:492:Use [`research/RESEARCH_PLAN.md`](research/RESEARCH_PLAN.md) §5 for the single schedule and [`research/IMPLEMENTATION_REVIEW.md`](research/IMPLEMENTATION_REVIEW.md) for G0–G5 acceptance gates. Dates are targets, not evidence of approval or completion. No classroom recording, even a microphone test, precedes ethics and required consent. Before that, use authorised adult/staged fixtures.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:494:Critical paths run separately: synthetic demo → competition submission; G4-U → STEM trial → technical exit → G4-E → early-grade collection → annotation → held-out validation → private pilot reports. Delayed ethics must not block the synthetic demo, and demo success must not be presented as classroom validity.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:555:| ADR-013 | Auth, withdrawal, retention and aggregate disclosure | G4/G5 negative tests and legal/ethics answers |
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:556:| ADR-014 | Undergraduate STEM first, then early-grade validation | User-directed stage order; cohort/schema details proposed in [ADR-014](adr/014-staged-classroom-trials.md) |
    ✓ • 936ms

## Activity

    $ python3 - <<'PY'
        from pathlib import Path
        p=Path('docs/research/RESEARCH_PLAN.md'); s=p.read_text().replace('before G4 | W2', 'before G4-U | W2').replace('(classroom work waits for G4)', '(STEM classroom work waits for G4-U)'); rows=s.splitlines(); i=next(i for i,l in enumerate(rows) if l.startswith('| E10 |')); row=rows.pop(i); i=next(i for i,l in enumerate(rows) if l.startswith('| E9 |')); rows.insert(i+1,row); p.write_text('\n'.join(rows)+'\n')
        p=Path('docs/research/IMPLEMENTATION_REVIEW.md'); s=p.read_text().replace('coaching stays disabled until G5.', 'early-grade coaching stays disabled until G5-E; STEM remains descriptive.'); p.write_text(s)
        # Check relative links and fences across all modified/new planning and test documentation.
        import re, subprocess
        paths=subprocess.check_output(['git','ls-files','--modified','--others','--exclude-standard'],text=True).splitlines()
        errors=[]
        for name in paths:
         p=Path(name)
         if p.suffix!='.md':continue
         text=p.read_text()
         if len(re.findall(r'^```',text,re.M))%2:errors.append((name,'unbalanced fence'))
         for link in re.findall(r'\]\(([^)]+)\)',text):
          if '://' in link or link.startswith('#'):continue
          if not (p.parent/link.split('#')[0]).exists():errors.append((name,link))
        print('Broken doc links/fences:',errors)
        assert not errors
        assert all(n.startswith(('docs/','tests/acceptance/')) for n in paths),paths
        print('All changed files are planning documents or acceptance-test assets.')
        # Check input fixtures have a complete timeline, except intentional invalid label case.
        import json
        cases=json.loads(Path('tests/acceptance/vectors.json').read_text())
        for case in cases:
         req=case['request']
         if req['operation']!='indicators':continue
         cursor=0
         for seg in req['timeline']:
          assert seg['start_tick']==cursor,(case['id'],seg)
          assert seg['end_tick']>cursor
          cursor=seg['end_tick']
         assert cursor==req['duration_ticks'],case['id']
        print('All indicator timelines are contiguous and duration-complete.')
        PY
        git diff --check
        git status --short
        git diff --stat
     M docs/DIMTS_ARCHITECTURE_CONTEXT.md
     M docs/research/ASSUMPTIONS.md
     M docs/research/CLAIMS_AUDIT.md
     M docs/research/CODEBASE_STRUCTURE.md
     M docs/research/HOSTING_OPTIONS.md
     M docs/research/README.md
     M docs/research/RESEARCH_PLAN.md
    ?? docs/adr/014-staged-classroom-trials.md
    ?? docs/research/IMPLEMENTATION_REVIEW.md
    ?? docs/research/INFRASTRUCTURE_RECHECK.md
    ?? docs/research/STEM_TRIAL_PLAN.md
    ?? docs/testing/
    ?? tests/
     docs/DIMTS_ARCHITECTURE_CONTEXT.md  | 273 +++++++++++++++++++-----------------
     docs/research/ASSUMPTIONS.md        |  31 ++--
     docs/research/CLAIMS_AUDIT.md       |   3 +
     docs/research/CODEBASE_STRUCTURE.md |  46 +++---
     docs/research/HOSTING_OPTIONS.md    |   3 +
     docs/research/README.md             |   6 +-
     docs/research/RESEARCH_PLAN.md      |  67 +++++----
     7 files changed, 242 insertions(+), 187 deletions(-)
    ✓ • 27ms

## Assistant

Updated the plans to test **undergraduate STEM classrooms first**, with separate cohorts, labels and results. Early-grade validation remains required afterward.

Prepared:
- [STEM trial plan](/Users/anteneh/Documents/Repos/Echo/docs/research/STEM_TRIAL_PLAN.md)
- [Infrastructure re-check](/Users/anteneh/Documents/Repos/Echo/docs/research/INFRASTRUCTURE_RECHECK.md), including the documented [openGauss TLS limitation](https://docs.opengauss.org/en/docs/latest/developer_guide/connecting_to_the_database_using_ssl_psycopg.html)
- [Post-implementation test suites](/Users/anteneh/Documents/Repos/Echo/docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md)
- [18 executable contract cases and runner instructions](/Users/anteneh/Documents/Repos/Echo/tests/acceptance/README.md)

Six harness checks passed; fixtures and documentation checks passed. Product tests await implementation adapters and infrastructure. No product code changed.

## User

is this using the most effecient and maintainable tech stack given the aim of the project and the context it's planned to function in?

## Assistant

I’ll reassess the stack against the team size, low-end phones, offline use, STEM-first testing and competition requirements, focusing on where simpler choices would reduce risk and maintenance.

## Activity

    $ git status --short; cat docs/research/INFRASTRUCTURE_RECHECK.md; sed -n '165,245p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; rg --files -g 'AGENTS.md' -g '*adr*' -g '*requirements*' -g '*pyproject*' -g 'package.json'; cat docs/adr/014-staged-classroom-trials.md
    ?? README.md
    ?? docs/images/
    # Infrastructure documentation re-check
    
    Checked official documentation on 2026-09-27. These are documentation findings and proposed choices, not successful installs or benchmarks. Final host, training hardware, regional capacity and credentials have not been supplied. Applies equally to undergraduate STEM and later early-grade data.
    
    | ID | Component / source | Confirmed documentation and implication | Required post-implementation case |
    |---|---|---|---|
    | INF-01 | [AGP 9.4 compatibility](https://developer.android.com/build/releases/agp-9-4-0-release-notes) | AGP 9.4 lists Gradle 9.6.0, API ≤37 and minimum JDK 17. Repo uses Gradle 9.6.0, API 37, daemon JDK 25 and AGP 9.4.1. Family requirements match; this page does not establish the exact patch/plugin/JDK combination. Resolve and build exact pins before changes. Java source target 11 is separate from Gradle's runtime JDK. | BUILD-01 |
    | INF-02 | [Lite 2.10.0 downloads](https://www.mindspore.cn/lite/docs/en/r2.10.0/use/downloads.html) | Android-aarch64 package contains AAR/runtime/benchmark; cloud-side and device-side Linux converters are distinct downloads. Pin the device-side package and published SHA-256 with matching converter. Package availability is not model compatibility. | MODEL-01 |
    | INF-03 | [Lite operator table](https://www.mindspore.cn/lite/docs/en/r2.10.0/reference/operator_list_lite.html), [PTQ](https://www.mindspore.cn/lite/docs/en/r2.10.0/advanced/quantization.html) | CPU GRU/LSTM list FP16/FP32, not INT8. Keep conv-only INT8 and mixed-precision recurrent candidates; prove full exported graph support and calibration behavior. | MODEL-01/02 |
    | INF-04 | [MindSpore 2.10 release](https://www.mindspore.cn/docs/en/r2.10.0/RELEASE.html) | Release notes do not prove a usable training stack on the team's unidentified hardware. Capture OS/Python/accelerator/driver/CANN compatibility and a forward/backward/save/reload spike. PyTorch teacher and MindSpore student stay conditional. | MODEL-03 |
    | INF-05 | [Microphone foreground service](https://developer.android.com/develop/background-work/services/fgs/service-types#microphone), [audio input sharing](https://developer.android.com/media/platform/sharing-audio-input) | Plan runtime RECORD_AUDIO, foreground-service declarations/permissions appropriate to target SDK, visible-UI start and recording callback handling. Capture can be silenced; callbacks plus frame continuity determine missingness. | DEVICE-01/02 |
    | INF-06 | [WorkManager work requests](https://developer.android.com/develop/background-work/background-tasks/persistent/getting-started/define-work) | Periodic work has a 15-minute minimum and timing depends on constraints/system scheduling. Use unique network-constrained one-time work when an outbox record is ready, plus periodic recovery. Do not promise immediate sync or run live capture in WorkManager. | SYNC-01/02 |
    | INF-07 | [SQLCipher migration](https://www.zetetic.net/sqlcipher/sqlcipher-for-android-migration/), [Auto Backup](https://developer.android.com/identity/data/autobackup), [16 KB support](https://developer.android.com/guide/practices/page-sizes) | Use `net.zetetic:sqlcipher-android` with `SupportOpenHelperFactory` for Room, not the legacy library/API. Pin a tested Room/SQLite/SQLCipher set. SQLCipher and Lite both need native/packaging/runtime checks; encryption does not replace backup exclusions and key lifecycle tests. | STORE-01/02; BUILD-02 |
    | INF-08 | [openEuler releases](https://www.openeuler.org/en/download/), [24.03 LTS SP3 containers](https://docs.openeuler.org/en/docs/24.03_LTS_SP3/cloud/index.html) | 24.03 LTS SP3 is a candidate with published planned EOL 2027/12. Select maintained service pack, architecture, image checksum and actual container engine/Compose versions. A generic “LTS” or “Docker compatible” description is not enough. | INFRA-01 |
    | INF-09 | [openGauss psycopg SSL](https://docs.opengauss.org/en/docs/latest/developer_guide/connecting_to_the_database_using_ssl_psycopg.html), [PostgreSQL libpq SSL](https://www.postgresql.org/docs/current/libpq-ssl.html) | openGauss documents `verify-full` as unsupported; `require` encrypts without verifying server authenticity. PostgreSQL documents certificate/hostname checks for `verify-full`. This is a selection gate: prove authenticated server identity for the chosen openGauss/driver arrangement or choose PostgreSQL; never silently weaken verification. No generic SASL workaround is approved. | DB-01/02 |
    | INF-10 | [FastAPI containers](https://fastapi.tiangolo.com/deployment/docker/) | Build from a pinned Python base rather than the deprecated prebuilt FastAPI image. Proposed deployment uses one API process initially, a separate durable scheduled job runner and a TLS reverse proxy with restricted trusted proxy addresses. Migration/job coordination remains the application's responsibility. | INFRA-01; API-01 |
    | INF-11 | [Huawei RDS PostgreSQL + ECS](https://support.huaweicloud.com/intl/en-us/qs-rds-pg/rds_02_0016.html) | Vendor path uses ECS/RDS in the same region/VPC and controlled security groups with SSL. Demo default: ECS + RDS PostgreSQL, private DB connectivity, least-privilege user. GaussDB is a separate alternative requiring its own compatibility evidence, not a drop-in alias. AF-Johannesburg availability/quota still needs an account-level check. | CLOUD-01; DB-01 |
    | INF-12 | [ModelArts constraints](https://support.huaweicloud.com/intl/en-us/productdesc-modelarts/modelarts_01_0062.html) | Standard ModelArts requires same-region OBS and does not support encrypted OBS buckets. Restrict a dedicated private-access bucket to separately approved inputs/outputs; never place either classroom cohort there. This is service-specific, not a reason to disable encryption on all buckets. | CLOUD-02 |
    | INF-13 | [Label Studio security](https://labelstud.io/guide/security) | Official guidance includes internal-network isolation and production SSRF protection (`SSRF_PROTECTION_ENABLED=true`). Verify authentication, media URLs, outbound telemetry and exact edition capabilities; do not assume enterprise RBAC/audit features in Community. Annotation stays on the in-country private tier. | ANNOT-01 |
    | INF-14 | [Zergaw Cloud Server](https://zergaw.com/cloud-server/) | Provider publishes a cloud-server offering, but this does not establish purchased-host suitability, permitted OS image, accelerator, backups, access terms or data location for every service. Existing hosting list remains a shortlist. Obtain exact configuration/location/backup terms and a restore test. No provider purchase or price revalidation performed. | INFRA-01/02 |
    
    ## Proposed deployment baseline after this check
    
    - Android minSdk 29 / arm64 remains. Retain existing toolchain files pending BUILD-01; this review changes no dependencies. Validate actual target-SDK behavior on modern Android and resource constraints on the reference 2 GB phone.
    - In-country P1 and P2 use one simple API + database + job runner; annotation/training isolated from the public API. openEuler SP3 candidate, openGauss conditional on DB-01; PostgreSQL fallback explicit in ADR-005. Do not start with two pilot database engines.
    - Competition demo is a distinct ECS/RDS PostgreSQL deployment using synthetic records. ModelArts/OBS training is a separate optional capability; it is not required to unblock undergraduate capture tests. Record exact region/service quota before booking capacity.
    - DB listener and annotation UI are private. Only HTTPS API/demo routes are public. Keep service secrets out of images, source and logs; test certificate rotation, backups and restart recovery. Pilot backups, observability and model artefacts remain in-country for both cohorts.
    
    ## Evidence record to complete at G0/G4-U
    
    Record selected host/location, OS image/version/hash, container runtime/Compose, Python/API/driver/database versions, region/VPC and service availability, CA/trust configuration, encrypted-backup location/retention, restore evidence, Android dependency lock/checksums, model/converter/runtime hashes and device OS/RAM/page size. Retention, recovery-time and recovery-point objectives must be assigned numerical values by Lead/Sys before tests can pass. Record unresolved items as blocked, not verified.
    
    Scope limits: no cloud account inspection, provider contract, download/binary inspection, build, training, device operation or data transfer was performed. Previous 4 KB AAR binary finding remains dated prior evidence; verify the newly chosen binary. No new legal interpretation is made here.
    | C-10 | **Team of 3, students.** | Prefer boring, well-documented tech outside the ML core. No bespoke distributed systems. |
    | C-11 | **Huawei stack preference** for competition credibility, without compromising C-2 and C-7. | See §7. Research audit reports Huawei Cloud deployment mandatory for the competition build. Use a separate synthetic-only demo; confirm exact regional requirements. Prefer Huawei components where tested; preserve documented fallbacks. |
    
    ---
    
    ## 7. Huawei technology mapping
    
    ### 7.1 Principles
    Current official-source findings and test links are in [INFRASTRUCTURE_RECHECK.md](research/INFRASTRUCTURE_RECHECK.md). Exact versions, hardware and regional service availability remain acceptance gates.
    - Use Huawei technology where it is **load-bearing**, not decorative. Judges probe in Q&A.
    - Keep an **escape hatch** (ONNX) for every model so a framework bug cannot sink the timeline.
    - The deployment-critical path (phone + in-country server) must not depend on a foreign cloud region (C-7).
    
    ### 7.2 Mapping table
    
    | Layer | Huawei technology | Role in Dimts | Why / caveat |
    |---|---|---|---|
    | On-device inference | **MindSpore Lite** (2.x; Android-aarch64 AAR; current release listed as 2.10.0 [VERIFY at build time]) | Runs segmentation + LID models, INT8 | Supports Android, post-training INT8 quantization, and converts MindIR/ONNX/TFLite to `.ms`. Built into HarmonyOS, so the same model ports to R-5. |
    | Model training | **MindSpore** 2.x | Teacher-model fine-tuning, student training, distillation | Primary framework. |
    | Audio toolkit | **Owned feature extraction** (Python + Kotlin) | Versioned log-mel extraction with streaming parity fixtures | Do not depend on MindAudio maintenance; research audit recommends owned feature code. |
    | Pretrained speech encoders | **PyTorch + Hugging Face** | Load multilingual self-supervised encoders (wav2vec2 / XLS-R / HuBERT family) as the server-side teacher model | Proposed replacement for MindNLP per audit; verify model licence and target training hardware in S4. |
    | Cloud training | **Huawei Cloud ModelArts** (Ascend) | Pre-training / fine-tuning on **public** speech data; pipeline demo; public-data-only experiments | AF-Johannesburg is a candidate, subject to S5 service availability. No pilot audio, posteriors, features, logs or classroom-trained weights may flow to this tier under the proposed boundary. |
    | Object storage (cloud) | **Huawei Cloud OBS** | Public datasets, model artefacts, experiment logs | No personal data. |
    | Server OS | **openEuler** (24.03 LTS SP3 candidate) | In-country server OS at Hawassa University | Huawei-originated open-source Linux. |
    | Database | **openGauss** | Indicator records, reports, aggregates, consent ledger | Conditional on exact-driver, migration and authenticated-TLS tests: documented openGauss verify-full limitation is a blocker unless resolved; PostgreSQL is the explicit fallback (INF-09). |
    | CI/CD (optional) | Huawei Cloud **CodeArts** | Build/test pipelines | Optional; GitHub Actions acceptable if friction is high. |
    | Client OS (stretch) | **HarmonyOS / OpenHarmony**, ArkTS | Native client port (R-5) | Target teachers overwhelmingly use Android phones (Tecno, Itel, Samsung). HarmonyOS NEXT does not run Android apps, so it cannot be the primary client. Frame the port as "same `.ms` model, second OS." |
    
    ### 7.3 What to say in Q&A
    Describe demonstrated components only. Proposed: PyTorch/HF teacher, MindSpore student where hardware supports it, Lite CPU inference. The competition demo uses synthetic records on Huawei Cloud; the pilot uses a separate in-country deployment. Model portability, runtime precision and cross-language accuracy are experiments, not completed claims.
    
    ### 7.4 HarmonyOS position
    Primary client is Android because that is what teachers own. The HarmonyOS client is a roadmap item unless the team has spare capacity after the national stage. Do not let it block the critical path.
    
    ---
    
    ## 8. System architecture
    
    ### 8.1 Tiers
    
    ```text
    ANDROID (offline pilot client)
      Mic → bounded RAM capture → owned features → Lite inference
          → observed/MISSING timeline + language/unknown → Kotlin indicators
          → encrypted local record + local report → authenticated outbox
      Research Mode: separate consent → encrypted audio → in-country transfer
                        │ derived LessonRecords only in Deployment Mode
                        ▼
    IN-COUNTRY PILOT (host selected through S6)
      FastAPI → chosen/tested database → private weekly reports
              → fixed-cohort, disclosure-reviewed district aggregates
      Separate enrolment, consent/revocation, audit, encrypted backups
      Research audio/annotation and classroom training stay in-country
    
    HUAWEI CLOUD DEMO (isolated endpoint, credentials, DB, logs and backups)
      Synthetic fixture generator → same API/report code → visual demo page
      Approved public-corpus/model experiments → ModelArts/OBS, subject to S5
      Approved cloud artefacts may enter pilot; NO pilot-derived export
    ```
    
    ### 8.2 Key design decisions (proposed; ratify via ADR)
    
    1. **Frame classification, not clustering diarization.** The task is a fixed 5-class problem, not "who spoke when" with unknown N. This is simpler, trainable, and real-time.
    2. **Teacher–student distillation.** A large multilingual self-supervised encoder (server-side "teacher model", PyTorch/HF) is fine-tuned on annotated classroom audio and used to produce soft labels on unannotated audio. A tiny on-device "student" (CRNN or small conformer on log-mel) is trained on those soft labels. Whether this reaches useful accuracy on a 2 GB phone is tested in E6. It is also the main technical-complexity story for judges.
    3. **Streaming, not batch, in Deployment Mode.** Raw audio exists only in a ≤ 10 s RAM ring buffer. Nothing is re-analysable after the lesson. This is a privacy guarantee and a storage guarantee at once.
    4. **Append-only sync, no CRDTs.** Each device produces immutable lesson records with client-generated UUIDv7 IDs. No record is ever edited on two devices, so no conflict resolution is needed. Withdrawal is a separate revocation and purge workflow that takes precedence over queued uploads (§13.1).
    5. **Single indicator spec, two implementations, golden tests.** Python reference on server/eval; Kotlin port on device; shared golden vectors in CI.
    6. **Rule-based coaching, not model-based.** The weekly suggestion is a transparent lookup table from indicator ranges to one suggestion. Explainable to teachers, directors, and judges.
    
    ### 8.3 Edge pipeline details
    
    | Stage | Spec (initial; tune in experiments) |
    |---|---|
    | Capture | 16 kHz mono PCM16 via `AudioRecord`; foreground service with persistent notification; wake lock during lesson |
    | Features | 64-bin log-mel, 25 ms window, 10 ms hop; per-utterance CMVN replaced by running normalisation (streaming) |
    | Segmentation model | Input: 1.0–2.0 s context windows, stride 0.5 s; output: per-10 ms-frame 5-class posteriors; smoothing via median filter or small HMM |
    | LID model | Complete 3 s teacher-only windows; {amh, sid, eng, oth} plus calibrated abstention to unknown; no short-segment inheritance |
    | Indicator engine | Runs incrementally; final values at lesson end |
    | Storage | Room + modern `sqlcipher-android` (`SupportOpenHelperFactory`) with a random database key wrapped by Android Keystore; backup, restore and key-loss policy required before real data |
    | Sync | Unique network-constrained one-time WorkManager jobs on enqueue; periodic recovery (minimum 15 min, not exact scheduling); backoff and durable per-record ACKs |
    
    # ADR-014: Undergraduate STEM technical trial before early-grade validation
    
    Date: 2026-09-27. Status: **Stage order directed by user; detailed schema/model design proposed for G1 ratification.**
    
    ## Context
    
    The intended product serves O-class and Grades 1–3, but the user plans to test the setup first in undergraduate STEM classrooms. Audio roles, participant ages, session formats and languages differ. Neither the starter app nor the proposed models have established classroom readiness.
    
    ## Decision
    
    Proceed P0 synthetic/staged → P1 undergraduate STEM technical trial → P2 intended early-grade validation. Use the in-country pilot tier for both real cohorts and keep the Huawei Cloud demo synthetic. P1 is a setup/feasibility trial, with separate approvals and voluntary participant consent, not evidence of child-speech accuracy or early-grade pedagogical value.
    
    Require cohort/session context, label schema and model supported-cohort declarations. STEM annotations describe adult instructor/student roles; child-only model logits cannot be renamed into those roles. Keep manifests/calibration/evaluation/reports isolated by cohort. Disable early-grade coaching and district aggregation for STEM. If no adult-compatible model exists, P1 can still test capture, coarse activity, storage/sync and human-coded indicator arithmetic with explicit provenance.
    
    ## Alternatives considered
    
    - Begin in early grades: superseded by the user's requested order.
    - Reuse child labels and combine results: rejected because it conceals the domain change.
    - Build a separate university product: unnecessary scope; use shared infrastructure with explicit cohort contracts.
    
    ## Consequences and validation
    
    P1 precedes P2 recruitment/capture readiness, with distinct G4-U/G4-E approvals. Technical fixes can transfer, but microphones, model/indicator accuracy and coaching need early-grade revalidation. Dates move when prerequisites slip. [STEM_TRIAL_PLAN.md](../research/STEM_TRIAL_PLAN.md) owns trial details; [acceptance suites](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) own test procedures. No product implementation is authorised by this ADR alone.
    ✓ • 0ms

## Activity

    $ rg -n '2026.*27|talent|competition.*http|Innovation Competition' docs/research/BIBLIOGRAPHY.md docs/research/CLAIMS_AUDIT.md | tail -20; cat README.md
    docs/research/BIBLIOGRAPHY.md:96:- Diack, A., Nelson, P., Agbesi, K., Nakalembe, A., MohamedKhair, M., Dube, V., Siyavora, T., Venugopalan, S., Hickey, J., Okonkwo, U., Bapna, A., Wiafe, I., Helegah, R. D., Atsakpo, E. D., Nutrokpor, C., Winful, F. B. P., Solaga, K. K., Abdulai, J., Ekpezu, A. O., . . . Matias, Y. (2026). *WAXAL: A large-scale multilingual African language speech corpus* (arXiv:2602.02734). arXiv. https://doi.org/10.48550/arXiv.2602.02734 **[Abstract]**
    docs/research/BIBLIOGRAPHY.md:161:- Huawei. (2026). *Huawei ICT Competition 2026–2027: Northern Africa – Innovation Competition* [Competition page]. Huawei Talent. https://e.huawei.com/en/talent/ **[Full]** (screenshots supplied: `papers/Huawei Talent Competition details.pdf`; the official Word/PPT templates are still to be downloaded after login)
    docs/research/BIBLIOGRAPHY.md:162:- Huawei Technologies Ethiopia. (2026). *Huawei ICT Competition 2026–2027 (Northern Africa): Registration now open* and *Steps to register for Huawei ICT Academy* [Guides]. **[Full]** (`papers/`)
    docs/research/CLAIMS_AUDIT.md:3:> Planning review 2026-09-27: this is a dated source audit, not implementation evidence. See [IMPLEMENTATION_REVIEW.md](IMPLEMENTATION_REVIEW.md). In particular, the reviewed papers do not prove universal absence of prior automated language measurement; public/synthetic speech is not automatically non-personal; model licensing needs derived-weight review; and sideloading does not resolve 16 KB runtime incompatibility.
    docs/research/CLAIMS_AUDIT.md:12:1. **The 2026–27 Innovation Competition requires deployment on Huawei Cloud (§7, §17).** New, VERIFIED from the Huawei Talent page for the Northern Africa division (supplied screenshots).
    docs/research/CLAIMS_AUDIT.md:108:| C-11 | Huawei stack is a "preference". | **CONTRADICTED** for the competition | 2026–27 rules make Huawei Cloud deployment mandatory (finding 1). | Upgrade to a hard requirement for the competition build only. |
    docs/research/CLAIMS_AUDIT.md:160:| 17a | Criteria: creativity, system complexity, technical complexity, societal benefit, functionality. | **OUTDATED** | That was the 2023–24 wording (Huawei, 2025). For 2026–27: Prelim/National = Innovation 60 + Application value 40; Regional = 40/35/15/10; Global = 40/30/15, then Application & implementation progress 10 (includes "GitHub stars"), then Presentation 5. |
    docs/research/CLAIMS_AUDIT.md:161:| 17b | 15-min presentation + 5-min Q&A in English. | **VERIFIED** for 2026–27 Regional and Global | Huawei Talent page. |
    docs/research/CLAIMS_AUDIT.md:163:| 17d | 2026–27 rules, deadlines, regional grouping. | **VERIFIED** (Northern Africa division) | Registration Aug–Dec 2026; Preliminary Nov–Dec 2026; National Jan 2027; Regional Feb/Mar 2027; Global May 2027. The Huawei Ethiopia registration guide points Ethiopian students to the "Northern Africa" division. Confirm with the Huawei Ethiopia ICT Ecosystem contact. |
    # Echovolve (ድምጽ)
    
    ![status](https://img.shields.io/badge/status-design%20stage-B8791C)
    ![target](https://img.shields.io/badge/target-Android%2010%2B%20and%202%20GB%20RAM-0F6E63)
    ![data](https://img.shields.io/badge/personal%20data-stays%20in%20Ethiopia-2C4A6B)
    ![asr](https://img.shields.io/badge/speech%20recognition-none%20by%20design-54606F)
    
    **Echovolve turns a cheap Android phone into a private coach for early-grade teachers in Ethiopia.**
    The phone listens to a lesson as it happens, never writes the audio down, and at the end produces eight
    plain-language numbers about how the lesson was taught: how much of the talking the teacher did, how
    long children took to answer, whether they answered together or one at a time, how often the class went
    back and forth, and which language was actually spoken. Once a week the teacher — and nobody else —
    sees a short report in Amharic, Sidaamu Afoo or English with one thing to try next.
    
    There is no speech recognition anywhere in the system. Nothing is transcribed, no child is identified,
    no voice print is stored, and no teacher is scored, ranked or compared. The design principle is
    **coaching, not surveillance**, and it is enforced by the architecture rather than by policy alone.
    
    > [!IMPORTANT]
    > **This repository is at the design stage.** As of 2026-09-27 the only code that exists is a Jetpack
    > Compose starter activity under `edge/android/app`. There is no capture, no inference, no indicator
    > engine, no server, no dashboard and no deployment. Every screen and every box in this README is a
    > design target behind the acceptance gates in
    > [`docs/research/IMPLEMENTATION_REVIEW.md`](docs/research/IMPLEMENTATION_REVIEW.md). All numbers shown
    > in the mockups are illustrative fixtures, not measurements.
    
    Echovolve is a B.Sc. final-year project at Hawassa University (defence June 2027) and an entry in the
    Huawei ICT Competition 2026–2027, Innovation track. The documents under `docs/` still carry the earlier
    codename **Dimts**; they have not been renamed.
    
    ---
    
    ## Contents
    
    1. [The problem](#1-the-problem)
    2. [What Echovolve does](#2-what-echovolve-does)
    3. [What the teacher sees](#3-what-the-teacher-sees)
    4. [The eight indicators](#4-the-eight-indicators)
    5. [Architecture](#5-architecture)
    6. [Huawei technology](#6-huawei-technology)
    7. [Privacy, ethics and Ethiopian law](#7-privacy-ethics-and-ethiopian-law)
    8. [Status: what exists and what does not](#8-status-what-exists-and-what-does-not)
    9. [Definition of done](#9-definition-of-done)
    10. [Roadmap](#10-roadmap)
    11. [Documentation](#11-documentation)
    12. [Evidence and citations](#12-evidence-and-citations)
    
    ---
    
    ## 1. The problem
    
    **Ethiopia has measured its children repeatedly, and the line is flat.** Across three national rounds of
    the Early Grade Reading Assessment, the share of Grade 2–3 pupils reaching the top two reading benchmark
    levels was 31.3% in 2014, 34.3% in 2016 and 32.4% in 2018 — differences the report itself calls too small
    to be practically significant (AIR, 2019, p. ix). Only 6.2% read fluently with full comprehension, and the
    spread between languages is enormous: 50.0% of Amharic pupils reach the top two levels against 16.0% in
    Haddiysa. A fourth child-assessment instrument is not the missing piece.
    
    **Teacher capability is a real and addressable constraint — but it is not the only one.** The Mother
    Tongue Teachers' Competencies Assessment found 39% of mother-tongue teachers at Proficient or above, with
    Sidaamu Afoo teachers in the 22–33% band; its own multivariate ranking puts home resources first, school
    resources second and teacher competency third (AIR, 2020). A separate study of 30 early-grade English
    teachers in five schools recorded a mean of 43.4% on a reading-instruction knowledge test, with 70% below
    50% (Yisihak & Damtew, 2024) — a case study, not a national estimate.
    
    **Observation-based feedback already helps, where it happens.** Where a school has someone who observes
    mother-tongue classes, pupils read better (d = 0.23–0.28), and observing three times a semester rather
    than never is associated with d = 0.18 (AIR, 2019, pp. 53–54). The honest framing is not that teachers get
    no feedback; it is that observation is **uneven, infrequent and unstandardised**, because trained observers
    do not exist at the scale the system would need. That is the gap Echovolve aims at: frequent, private,
    non-evaluative feedback at close to zero marginal cost.
    
    **Mother-tongue policy is asserted but never verified.** Policy mandates primary instruction in nationality
    languages, yet the research documents teachers who are not proficient in the language they must teach in
    (Heugh et al., 2007). Nobody measures, continuously and at scale, which language is actually spoken in the
    room. The prior work used about a hundred handwritten observations with a checklist that was abandoned
    mid-fieldwork (Heugh et al., 2007), or children's self-reports (Piper et al., 2016), or a survey
    (Vujcich, 2013).
    
    **Pre-primary grew faster than its quality.** Pre-primary gross enrolment reached 59.8% in 2024/25 — a
    national figure that excludes Amhara — against 145.9% in Addis Ababa and 17.7% in Somali (MoE, 2025). In
    2021/22 about 75% of pre-primary children were in O-classes and child-to-child programmes rather than
    kindergartens (MoE figures cited in Alemu, 2025). Class sizes in Grades 1–6 average 54.2 nationally and 59.6 in Sidama (MoE, 2025).
    
    ---
    
    ## 2. What Echovolve does
    
    A teacher opens the app, taps **Start lesson**, and teaches. The phone processes the sound in streaming
    chunks: raw audio lives for at most ten seconds in a RAM ring buffer and is never written to disk, so
    nothing is re-analysable after the fact. Two small on-device models run on the CPU — one labels every
    10 ms frame as teacher speech, one child, several children together, overlap or non-speech; the other
    identifies the language of complete three-second windows of teacher speech as Amharic, Sidaamu Afoo,
    English or other, abstaining to *unknown* when the evidence is thin.
    
    From that timeline an indicator engine computes eight measures (§4). They are stored encrypted on the
    phone and queued for upload. When a network appears, a few kilobytes of JSON go to a server inside
    Ethiopia. Once a week the teacher gets a report. A woreda education officer sees only suppressed
    aggregates, never an individual.
    
    **What Echovolve deliberately does not do**
    
    | Not this | Why |
    |---|---|
    | Speech recognition or any speech-derived text | Scope, compute and data minimisation. Every indicator is acoustic, so no words are ever reconstructed. |
    | Identify individual children, by voice or otherwise | `CHILD_SINGLE` and `CHILD_CHORAL` are acoustic classes, not people. No speaker embeddings are persisted anywhere. |
    | Score, rank or evaluate teachers | The report belongs to the teacher. Sharing with a director is teacher-initiated and revocable. |
    | Assess children's reading | A possible future component, not this one. |
    
    ---
    
    ## 3. What the teacher sees
    
    Six screens cover the whole product: starting a lesson, the lesson itself, the saved result, the weekly
    report in two of its three locales, and the teacher's own controls over consent and deletion.
    
    ![Echovolve teacher app mockup — six Android screens: starting a lesson, the in-lesson view, the saved-lesson summary, the weekly report in English and in Amharic, and the teacher's data and consent controls](docs/images/ui-mockup.svg)
    
    ### 3.1 Why the UI looks like this
    
    Almost every visible choice traces back to a constraint or a finding, not to taste.
    
    | What you see | Why it is that way |
    |---|---|
    | **"No audio is saved"** on the start screen, repeated in the lesson | It is the literal truth of the architecture, and it is the first thing a teacher who fears surveillance needs to know. Nearly all teachers weigh their own privacy when technology enters the classroom (Birnhack & Perry-Hazan, 2021). |
    | **No live score during the lesson** — only a timer, a level meter and a microphone-health light | Coaching is weekly and reflective. A live number turns the phone into an invigilator, which is exactly the failure mode the project is trying to avoid. |
    | **An interruption is shown, in seconds, as "not observed"** | A phone call silences the microphone and the app receives silence, not an error. Time lost this way is reported as `MISSING` and is never bridged, smoothed or counted as classroom silence — otherwise the indicators would quietly invent an unusually quiet lesson. |
    | **Elapsed, observed and missing time are three separate numbers** | Every rate uses observed time as its denominator. A record with poor coverage is flagged, not silently averaged in. |
    | **"One child at a time — not enough this week"** instead of a zero | An unavailable indicator returns null with a reason. Showing 0 would read as a successful measurement of no activity. |
    | **Choral vs individual shown as a balance, with "neither is better"** | The evidence genuinely points both ways: choral responding is an established active-response technique (Heward et al., 1989) and a marker of rote teaching (Pontefract & Hardman, 2005; Hardman et al., 2009). The UI must not pick a side the evidence has not picked. |
    | **Exactly one suggestion per week** | Feedback that changes practice is specific and sparse (Hattie & Timperley, 2007; Kraft et al., 2018). An automated talk-time nudge cut mentors' talk share by five percentage points in a randomised trial (Demszky & Liu, 2023). |
    | **"From your own lessons. This is not a score."** | The suggestion comes from a transparent rule table, not a model — explainable to a teacher, a director and a competition judge alike. |
    | **Amharic, Sidaamu Afoo and English, with Ge'ez and Latin side by side** | The product measures mother-tongue instruction; it would be incoherent to deliver its feedback only in English. |
    | **"Withdraw and delete everything" as a first-class button** | Withdrawal stops capture immediately, discards queued lessons and purges server records and derived reports. It takes precedence over anything already in the outbox. |
    
    > The Amharic copy in the mockup is a working draft. Report wording is to be co-designed with pilot
    > teachers and reviewed by native speakers before it ships (research questions B4 and B5).
    
    ### 3.2 The district dashboard
    
    The only view above the individual teacher — and the same code that serves the competition's visual
    demo page, where every record is synthetic.
    
    ![Echovolve woreda dashboard mockup — aggregate indicators by grade with cells below five teachers hidden, and language shares of teacher speech by grade](docs/images/ui-dashboard.svg)
    
    Aggregation counts **distinct teachers, not lessons**, averages each teacher before taking the district
    figure, and hides any cell with fewer than five teachers (Sweeney, 2002). Fixed cohorts and fixed windows
    remove the drill-down that would otherwise allow differencing attacks. This is called *minimum-group
    suppression* rather than anonymity, because k = 5 on its own does not prove anonymity, and repeated weekly
    releases have to be tested for what they leak in combination.
    
    ---
    
    ## 4. The eight indicators
    
    All eight derive from one ordered, non-overlapping timeline of integer 10 ms ticks. Acoustic labels are
    `{TEACHER, CHILD_SINGLE, CHILD_CHORAL, OVERLAP, NON_SPEECH}`. `MISSING` is capture metadata, never a
    sixth learned class. Language decisions are `{amh, sid, eng, oth, unknown}`, where `unknown` means
    insufficient evidence — not another language, and never inherited from a neighbouring segment.
    
    | ID | Indicator | Definition | Where it comes from |
    |---|---|---|---|
    | **I-1** | Teacher talk ratio | Exclusive teacher duration ÷ observed speech duration. Overlap is reported separately and never attributed to the teacher. | Same denominator as the talk-time measure that changed behaviour in a randomised trial (Demszky & Liu, 2023); a teacher-centred factor negatively predicts value-added (Liu & Cohen, 2021). |
    | **I-2** | Response latency | Median, P25, P75 and n of the gap between a teacher turn and a child turn, reported **separately** for choral and individual responses. | Wait time is well established (Rowe, 1986; Tobin, 1987) — but wait time I follows a *question*, and I-2 cannot see questions. It is therefore not compared against the 3-second threshold until prosody-based question detection exists. |
    | **I-3** | Choral balance | Choral child duration ÷ all child duration, plus a qualifying-segment count ratio. | Contested in both directions (Heward et al., 1989 vs. Pontefract & Hardman, 2005), so it is presented as a balance with no preferred value. |
    | **I-4** | Individual responses | Count of child-single segments ≥ 0.5 s, and the same count per ten observed minutes. | Active student responding (Heward et al., 1989); dialogue and outcomes (Howe et al., 2019). |
    | **I-5** | Turn density | Teacher ↔ child alternations within uninterrupted observed spans, per observed minute. | Classroom discourse as an indicator of instructional quality (Howe et al., 2019; Pontefract & Hardman, 2005). |
    | **I-6** | Language shares | Duration by language ÷ all exclusive teacher duration, `unknown` included and reported, never redistributed. | The project's novelty claim: prior measurement of classroom language used field notes (Heugh et al., 2007), self-reports (Piper et al., 2016) or surveys (Vujcich, 2013). Treated as a hypothesis until the systematic search closes. |
    | **I-7** | Interaction coverage | Share of wholly observed 60-second bins containing at least one alternation. | A spread measure, to catch a lesson whose interaction is concentrated in five minutes. |
    | **I-8** | Long silences | Count of contiguous observed non-speech runs ≥ 60 s. `MISSING` splits a run rather than joining it. | Interpreted neutrally: quiet work and a stalled class look the same to a microphone. |
    
    Two implementations exist by design — a Python reference and a Kotlin port — and they must agree to
    1e-6 on shared golden vectors in CI. Classification error propagates into every one of these numbers, so
    indicator error is estimated by pushing confusion matrices through the golden timelines rather than
    assumed away (Gautheron et al., 2025).
    
    ---
    
    ## 5. Architecture
    
    ![Echovolve code architecture — the Android edge tier, the in-country pilot tier, the Huawei Cloud demo tier, the data boundary between them, and indicators-core as the shared specification](docs/images/architecture.svg)
    
    ### 5.1 Three tiers, and the line between two of them
    
    **① The phone (`edge/android/`)** is the product. It works with no network for days. Capture runs in a
    foreground service started from visible UI; features, both models, postprocessing and the indicator
    engine all run on the CPU in a streaming loop. Storage is Room over SQLCipher with a random database key
    wrapped by the Android Keystore, excluded from OS backup and device transfer. Sync is a WorkManager
    outbox with durable per-record acknowledgements and UUIDv7 record IDs (RFC 9562), so a record is never
    edited on two devices and no conflict resolution is needed.
    
    **② The in-country tier (`infra/openeuler/`)** holds everything that is personal data: lesson records,
    enrolment, the consent ledger, Research Mode audio, annotation and any model trained on classroom audio.
    It is one FastAPI process, one database and one job runner, deliberately boring. Article 22 of
    Proclamation No. 1321/2024 requires personal data collected in Ethiopia to be stored on a server or data
    centre located in Ethiopia, so this tier cannot be a foreign cloud region.
    
    **③ The Huawei Cloud demo (`infra/huawei-cloud/`)** exists because the 2026–27 Innovation Competition
    requires an entry deployed on Huawei Cloud with a visual demo page. Huawei Cloud has no Ethiopian region.
    The resolution is a genuinely separate deployment — separate account scope, credentials, endpoint,
    database, logs and backups — running the same server and dashboard code over **synthetic records** from a
    seeded generator that ships with the repository.
    
    **The boundary between ② and ③ is the most important line in the diagram.** Approved public-corpus data
    and cloud-trained artefacts may move from cloud to pilot after licence and privacy review. Nothing moves
    the other way: no classroom audio, no features, no posteriors, no soft labels, no trained weights, no logs
    and no identifiers, for either cohort. Public speech and synthetic mixtures of real voices are *not*
    automatically non-personal, so even those need a provenance review before they enter the cloud tier.
    
    **④ `indicators-core/`** is the single source of truth. The Python package is the reference; the Kotlin
    module is the port; `golden/` holds JSON timelines with expected outputs that both must reproduce to
    1e-6 in CI. This is what stops the phone and the server from quietly disagreeing about what a number means.
    
    ### 5.2 Six design decisions
    
    | Decision | Instead of | Why |
    |---|---|---|
    | **Frame classification, five fixed classes** | Clustering diarization ("who spoke when", unknown N) | The task is a fixed classification problem, not speaker discovery. It is simpler, trainable on little data and runs in real time. Frame-classifier precedent: Lavechin et al. (2020); five-segment-type precedent with teacher-independent validation: Donnelly et al. (2016). |
    | **Teacher–student distillation** | Training a small model from scratch | A large multilingual self-supervised encoder is fine-tuned server-side and used to produce soft labels on unannotated audio; a tiny on-device student learns from them (Hinton et al., 2015). It is also the project's main technical-complexity story. |
    | **Streaming, never batch, in Deployment Mode** | Recording then analysing | Raw audio exists only in a ≤ 10 s RAM ring buffer. This is a privacy guarantee and a storage guarantee at the same time, on a phone with 2 GB of RAM and 16 GB of shared storage. |
    | **Append-only sync, no CRDTs** | Conflict resolution | Each device emits immutable records with client-generated IDs. Withdrawal is a separate revocation and purge workflow that outranks anything queued. |
    | **One indicator spec, two implementations, golden tests** | Reimplementing per platform | See §5.1 ④. |
    | **Rule-based coaching** | A model that suggests | The weekly suggestion is a versioned lookup table from indicator ranges to one sentence. Explainable to a teacher, auditable by an ethics reviewer, defensible in Q&A. |
    
    ### 5.3 What actually leaves the phone
    
    One `LessonRecord` per lesson, a few kilobytes of JSON: indicators, spec and model versions, capture
    accounting, quality flags, an opaque server-issued teacher pseudonym and a school ID. **No audio. No
    embeddings. No timestamps finer than the lesson start. No child-level data. No full timeline.**
    
    ```jsonc
    {
      "record_id": "uuidv7",
      "schema_version": 2,
      "indicator_spec_version": 2,
      "cohort": "early_grade",
      "teacher_pseudo_id": "opaque, server-issued at enrolment",
      "grade": "O|1|2|3",
      "subject": "mother_tongue|math|english|environmental|other",
      "duration_s": 2400,
      "capture": { "observed_s": 2357.5, "missing_s": 42.5, "mic": "builtin" },
      "model_versions": { "seg": "seg-student-0.3.1-int8", "lid": "lid-student-0.2.0-int8" },
      "indicators": {
        "I1_teacher_talk_ratio": 0.78,
        "I2_response_latency_s": { "choral": { "median": 0.2, "n": 30 },
                                   "single": { "median": 0.6, "n": 12 } },
        "I3_choral_ratio": 0.91,
        "I6_language_share": { "amh": 0.62, "sid": 0.31, "eng": 0.05,
                               "oth": 0.01, "unknown": 0.01 }
      },
      "quality": { "mean_seg_confidence": 0.84, "snr_estimate_db": 11.2 }
    }
    ```
    
    The complete schema, its null-and-reason rules and its validation are frozen at gate G1 before any code
    is written against it. The block above is an illustrative subset.
    
    ### 5.4 Repository map
    
    ```
    Echovolve/
    ├── docs/                    architecture context, ADRs, research pack, ethics, legal, competition
    ├── indicators-core/         the contract: python reference · kotlin port · golden vectors
    ├── ml/                      features · teacher model · student · distillation · export · eval
    ├── edge/                    android: app · audio · pipeline · inference · storage · sync · benchmark
    ├── server/                  fastapi api · jobs · db migrations · tests
    ├── dashboard/               woreda view; doubles as the competition demo page
    ├── tests/acceptance/        18 prepared contract vectors + a runner (adapters not written)
    ├── tools/                   synthetic-lessons · lesson-simulator · check-elf-alignment · demo
    └── infra/                   huawei-cloud (demo) · openeuler (pilot) · ci
    ```
    
    The detailed layout, with the reasoning behind each deviation from the original proposal, is in
    [`docs/research/CODEBASE_STRUCTURE.md`](docs/research/CODEBASE_STRUCTURE.md).
    
    ---
    
    ## 6. Huawei technology
    
    Used where it is load-bearing, with a documented escape hatch for every model, because judges probe this
    in Q&A and because a framework bug must not be able to sink the timeline.
    
    | Layer | Choice | Role and caveat |
    |---|---|---|
    | On-device inference | **MindSpore Lite 2.10.0** (Android aarch64 AAR) | Runs both student models on the CPU. Supports post-training INT8 and converts MindIR, ONNX and TFLite to `.ms`. Its operator table lists GRU and LSTM as FP16/FP32 only, so a CRNN student cannot be fully INT8 — which is exactly why ADR-002 keeps a convolution-only candidate. |
    | Student training | **MindSpore 2.x** | Trains the on-device student. Conditional on a week-one spike proving the training stack works on the team's actual hardware. |
    | Teacher model | **PyTorch + Hugging Face** | Loads a multilingual self-supervised encoder server-side. MindNLP was the original plan; its repository now hosts a different project and the preserved branch is unmaintained, so it was dropped. |
    | Feature extraction | **Owned code, Python + Kotlin** | MindAudio is unmaintained, and feature drift between the two implementations would silently break the model. Parity fixtures cover chunk boundaries, resampling and normalisation. |
    | Cloud training | **ModelArts on Ascend**, AF-Johannesburg | Public data only. ModelArts requires same-region OBS and does not support encrypted buckets, so its bucket holds approved public objects and nothing else. |
    | Object storage | **OBS** | Public datasets and model artefacts. No personal data. |
    | Server OS | **openEuler 24.03 LTS SP3** (candidate) | In-country pilot host. |
    | Database | **openGauss**, PostgreSQL as the explicit fallback | Conditional on a hard gate: openGauss documents `verify-full` as unsupported, and shipping a database connection that encrypts without verifying server identity is not acceptable. If that cannot be resolved, ADR-005 selects PostgreSQL. |
    | Client OS (stretch) | HarmonyOS / ArkTS | A roadmap item. Teachers own Android phones, and HarmonyOS NEXT does not run Android apps, so it cannot be the primary client. |
    
    ---
    
    ## 7. Privacy, ethics and Ethiopian law
    
    Ethiopia's Personal Data Protection Proclamation No. 1321/2024 has been in force since 24 July 2024. The
    duties it creates shaped the architecture rather than being bolted onto it:
    
    | Obligation | Article | How the design answers it |
    |---|---|---|
    | Personal data collected locally is stored in Ethiopia | Art. 22(1) | Two tiers. The pilot is in-country; the competition demo holds synthetic records only. |
    | Consent | Arts. 7, 8 | Per-teacher consent in the app and in an append-only server ledger, with a separate opt-in for Research Mode. |
    | Parent or guardian consent for minors under 16 | Arts. 2(15), 11 | Children are recorded incidentally. No classroom capture — not even a microphone test — happens before ethics approval, school permission and the required consent and assent. |
    | Breach notice within 72 hours | Arts. 43, 44 | A runbook in `docs/ethics/`. |
    | Registration with the Ethiopian Communications Authority | Art. 33 | Open item, tracked in the legal workstream. |
    | Data protection officer | Art. 40 | Open item. |
    | Data protection impact assessment | Art. 47 | Open item. |
    | Research exemption | Art. 54 | Conditional; to be confirmed with the university legal office. |
    
    Controls the architecture actually enforces:
    
    - **Raw audio exposure.** Deployment Mode: RAM ring buffer only, never persisted. Research Mode: a
      per-lesson AES key, encrypted at rest, deleted from the phone after a verified in-country receipt.
    - **Voice biometrics.** No speaker embeddings persisted anywhere. Models emit class posteriors only.
    - **Child data.** No child-level features, ever. `CHILD_*` are acoustic classes, not individuals.
    - **Misuse by management.** Individual reports are visible only to the teacher. Director sharing is
      teacher-initiated and revocable, and that promise is written into the consent form and the product copy.
    - **Re-identification via the dashboard.** Minimum group of five distinct teachers, fixed cohorts, equal
      teacher weighting, no arbitrary filters, and complementary-suppression tests before any district release.
    - **Transport and rest.** TLS 1.2+ with certificate pinning; SQLCipher plus Keystore on the device;
      disk encryption and encrypted in-country backups on the server.
    - **Withdrawal.** Stops capture, discards queued lessons, revokes every enrolled device, rejects stale
      retries, purges server records and derived reports, and replays the deletion if a backup is restored.
    
    The independent claims audit is deliberately unflattering about anything that outruns its evidence; see
    [`docs/research/CLAIMS_AUDIT.md`](docs/research/CLAIMS_AUDIT.md).
    
    ---
    
    ## 8. Status: what exists and what does not
    
    | Area | State |
    |---|---|
    | `docs/` | **Substantial.** Architecture context, a claims audit against primary sources, a research and implementation plan, an assumptions register, ADRs, a STEM trial plan, an infrastructure recheck and a hosting analysis. |
    | `edge/android/app` | **A Compose starter.** `MainActivity.kt` is "Hello Android". `settings.gradle.kts` includes only `:app`; the other Android folders hold `.gitkeep`. Microphone permissions and the foreground service are absent. |
    | `tests/acceptance/` | **Prepared, not wired.** A runner and 18 hand-calculated contract vectors exist; no implementation adapter exists, so nothing product-related has been executed. |
    | `indicators-core/`, `ml/`, `server/`, `dashboard/`, `infra/`, `tools/` | **Empty placeholders.** |
    | Any accuracy, latency, memory, battery or deployment number | **Not measured.** No build has been verified, no model trained, no device benchmarked, no host purchased, no cloud account inspected. |
    
    ### Acceptance gates
    
    Nothing proceeds past a gate until its evidence exists.
    
    | Gate | What it requires |
    |---|---|
    | **G0** Baseline and feasibility | A reproducible clean Android build with pinned toolchain; a measured target phone including page size; a MindSpore Lite invocation on device; a training-hardware spike; an exact database driver and migration path. |
    | **G1** Contracts | Candidate ADRs ratified; the full schema v2 and model manifest; exact timeline, postprocess and quantile semantics; golden cases reviewed with expected results; feature config and chunk ownership frozen. |
    | **G2** Offline vertical slice | Authorised synthetic or staged audio → features → inference → indicators → encrypted record → local report, on the target phone, with bounded queues and a 40-minute soak. Process death must produce an explicitly interrupted session, never invented audio. |
    | **G3** Reproducible competition demo | Synthetic fixtures → authenticated ingest → dashboard, on the isolated Huawei Cloud deployment, with a licence manifest and independent reproduction. |
    | **G4-U / G4-E** Cohort readiness | Separate approvals for the undergraduate STEM trial and for early grades. Neither is implied by the other. |
    | **G5-E** Validity and controlled release | A teacher- and school-independent frozen test set, annotation agreement, indicator error with intervals, device equivalence after quantization, coaching co-design and aggregate disclosure tests. Only indicators that pass are released. |
    
    ### Trial order
    
    Synthetic and staged fixtures → an undergraduate STEM technical trial → the intended early-grade
    validation. The STEM phase tests capture, microphones, streaming, storage, sync and private technical
    reports with adult participants. It establishes **technical** feasibility only: adult classroom results
    cannot validate child-speech classes, early-grade coaching or mother-tongue policy measurement, the label
    schemas are deliberately incompatible, and child-model outputs are never relabelled into adult roles.
    See [ADR-014](docs/adr/014-staged-classroom-trials.md) and
    [`docs/research/STEM_TRIAL_PLAN.md`](docs/research/STEM_TRIAL_PLAN.md).
    
    ---
    
    ## 9. Definition of done
    
    All targets are prospective. Thresholds are tuned on development data only; the locked test set is never
    used for distillation, calibration or threshold selection.
    
    **Model metrics**, on a real early-grade classroom test set split by teacher and by school:
    
    | Metric | Target | Stretch |
    |---|---|---|
    | Frame macro-F1, five classes | ≥ 0.70 | ≥ 0.80 |
    | `CHILD_SINGLE` vs `CHILD_CHORAL` F1 | ≥ 0.75 | ≥ 0.85 |
    | Language-ID accuracy, teacher segments ≥ 3 s | ≥ 0.85 | ≥ 0.92 |
    | Student vs teacher-model gap | ≤ 0.08 | ≤ 0.04 |
    | INT8 vs FP32 gap | ≤ 0.02 | ≤ 0.01 |
    
    These are ambitious against the published state of the art: the best system in a recent classroom
    diarization study reached 69% on teacher-versus-child, and five-segment-type classification with ASR
    support reached F1 0.64–0.78 (Wang et al., 2025; Donnelly et al., 2016). No published benchmark exists at
    all for choral-versus-single child speech.
    
    **Indicator validity** — the metric that actually matters — compares Echovolve against human coders on
    held-out lessons: teacher talk ratio within 5 percentage points, response-latency median within 0.5 s,
    choral ratio within 10 percentage points, and Spearman ρ ≥ 0.80 across lessons for I-1, I-3, I-5 and I-6.
    
    **Cross-language generalisation**, the thesis contribution: train segmentation on Amharic-school lessons
    only, test on Sidaamu Afoo-school lessons. The hypothesis is that segmentation degrades little because it
    is acoustic and script-independent, while language identification needs both languages in training. The
    result gets reported either way.
    
    **System metrics**, on the target device: end-to-end real-time factor ≤ 0.3 including capture, features,
    language ID and storage; peak RSS ≤ 300 MB; ≤ 10 percentage points of battery over a documented
    40-minute run; and every valid, non-withdrawn record delivered in a simulated 72-hour offline test with
    random disconnects.
    
    **Usefulness**, after the technical exit: at least six teachers, at least three weeks, both languages,
    with structured interviews on perceived usefulness, trust and discomfort.
    
    ---
    
    ## 10. Roadmap
    
    Presented as roadmap. None of it is built, and none of it should be pitched as built.
    
    - **R-1** Decodable mother-tongue reading material generated under the constraint of the graphemes actually taught.
    - **R-2** Child micro-diagnostics — phoneme isolation and blending — via forced alignment.
    - **R-3** Digits-in-noise hearing pre-screen with referral routing.
    - **R-4** Prosody-based question detection, which would finally turn I-2 into a defensible wait-time measure.
    - **R-5** A HarmonyOS/ArkTS client running the same `.ms` models.
    
    ---
    
    ## 11. Documentation
    
    | Document | What it is |
    |---|---|
    | [`docs/DIMTS_ARCHITECTURE_CONTEXT.md`](docs/DIMTS_ARCHITECTURE_CONTEXT.md) | The single source of truth for design: problem, constraints, indicator spec, Huawei mapping, architecture, open decisions. Read it before proposing anything. |
    | [`docs/research/IMPLEMENTATION_REVIEW.md`](docs/research/IMPLEMENTATION_REVIEW.md) | The verified repository baseline, twelve findings that change the plan, the G1 contract checklist and gates G0–G5. **Read before implementing.** |
    | [`docs/research/CLAIMS_AUDIT.md`](docs/research/CLAIMS_AUDIT.md) | Every factual claim in the architecture document checked against a primary source, with a verdict and the change it implies. |
    | [`docs/research/RESEARCH_PLAN.md`](docs/research/RESEARCH_PLAN.md) | Open research questions, experiments E1–E10, infrastructure spikes S1–S8, legal questions and the schedule. |
    | [`docs/research/ASSUMPTIONS.md`](docs/research/ASSUMPTIONS.md) | The 50 assumptions the design rests on: origin, status, evidence, test, and impact if wrong. |
    | [`docs/research/CODEBASE_STRUCTURE.md`](docs/research/CODEBASE_STRUCTURE.md) | The proposed monorepo layout and why it differs from the original proposal. |
    | [`docs/research/BIBLIOGRAPHY.md`](docs/research/BIBLIOGRAPHY.md) | Every source in APA 7th, tagged by how far it was actually read. |
    | [`docs/research/STEM_TRIAL_PLAN.md`](docs/research/STEM_TRIAL_PLAN.md) | The undergraduate trial, its label contract and its exit thresholds. |
    | [`docs/research/INFRASTRUCTURE_RECHECK.md`](docs/research/INFRASTRUCTURE_RECHECK.md) | Official infrastructure documentation rechecked, with the acceptance case each finding implies. |
    | [`docs/research/HOSTING_OPTIONS.md`](docs/research/HOSTING_OPTIONS.md) | Ethiopian hosting providers checked against Article 22. |
    | [`docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md`](docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md) | Contract, device, infrastructure and field test suites. |
    | [`tests/acceptance/README.md`](tests/acceptance/README.md) | The adapter protocol for the 18 prepared contract vectors. |
    
    A note on evidence: throughout the research pack, **verified** means a primary, peer-reviewed or official
    source was actually opened. Abstracts, secondary summaries and search results are labelled as such and do
    not count.
    
    ---
    
    ## 12. Evidence and citations
    
    ### 12.1 Which source decided what
    
    | Decision | What settled it |
    |---|---|
    | **Aim at the teacher, not the child** | EGRA's three national rounds are flat — 31.3% / 34.3% / 32.4% at the top two benchmark levels, differences too small to be practically significant, with only 6.2% reading fluently with full comprehension (AIR, 2019). Corroborated by the ~40% reading at 20–25 wpm in USAID (2020) and the older 0.5–13% comprehension range in RTI (n.d.). Another child-assessment instrument is not the gap. |
    | **Teacher capability framed as "significant and addressable", not "the binding constraint"** | AIR (2020) ranks home resources first, school resources second, teacher competency third. Yisihak & Damtew (2024) is a 30-teacher, five-school case study whose authors state its generalisability is limited. |
    | **"Frequent and private", not "teachers get no feedback"** | AIR (2019, pp. 53–54) shows observation exists in some schools and is positively associated with reading (d = 0.23–0.28; d = 0.18 for three times a semester vs. never). Jensen et al. (2020) describe feedback elsewhere as infrequent and more evaluative than developmental. |
    | **Feedback can move practice at all** | Kraft et al. (2018) meta-analysis of coaching; Demszky & Liu (2023) randomised trial cutting talk share by 5 pp; Demszky et al. (2024, 2025) on uptake and questioning quality; Hattie & Timperley (2007) on what makes feedback work; Popova et al. (2021) on the gap between evidence and practice in teacher development. |
    | **Acoustic indicators without any transcription** | Owens et al. (2017), ~90% on three coarse classes from classroom sound alone across 1,486 sessions; Schlotterbeck et al. (2021), >80% on two practices from a lavalier into a teacher's own smartphone, at roughly 7 minutes of processing per hour of audio; Donnelly et al. (2016), F1 0.64–0.78 on five segment types with teacher-independent validation; Chanchal & Zualkernan, F1 0.83 in semi-rural Pakistan with phones; Zualkernan & Khan (2020) and Shapsough & Zualkernan (2017) on low-cost classroom audio devices. |
    | **Frame classification, not clustering diarization** (ADR-001) | Lavechin et al. (2020), a frame-level voice-type classifier that beats LENA; Donnelly et al. (2016) for five-class segment labelling; Wang et al. (2025), whose best classroom system reached 69% on teacher-versus-child, which is why §9's targets are labelled ambitious. |
    | **I-1 defined as teacher ÷ (teacher + child)** | Demszky & Liu (2023) use exactly this denominator; Liu & Cohen (2021) find a teacher-centred factor negatively predicts value-added. |
    | **I-2 split by response type, and not called wait time** | Rowe (1986) and Tobin (1987) established wait time — but wait time I follows a question, and an acoustic system cannot see questions. The 3-second threshold stays out of the product until prosody-based question detection (R-4) exists. |
    | **I-3 presented as a balance with no preferred direction** | Heward et al. (1989) treat choral responding as an evidence-based active-response technique; Pontefract & Hardman (2005) and Hardman et al. (2009) read the same behaviour as a marker of rote teaching in East African classrooms. |
    | **I-5 and I-7 as dialogue measures** | Howe et al. (2019) on teacher–student dialogue and student outcomes; Alexander (2015) on the quality imperative. |
    | **I-6 as the novelty claim — and as a hypothesis** | Heugh et al. (2007) recorded about 100 classroom observations as handwritten notes and abandoned the checklist halfway through fieldwork; Piper et al. (2016) measured school language use through children's self-reports; Vujcich (2013) used a survey. Continuous automated measurement appears to be new, but a systematic search has to close before anyone says "first". |
    | **Indicators reported with uncertainty, not as point facts** | Gautheron et al. (2025) show how classification errors distort findings in automated speech processing, with solutions from child-development research. |
    | **What Echovolve complements rather than replaces** | The human instruments: Stallings et al. (2014), Molina et al. (2018) on the World Bank's Teach tool, and La Paro & Pianta (2003) on CLASS. |
    | **Teacher–student distillation into a tiny on-device model** | Hinton et al. (2015) for the method; Baevski et al. (2020), Babu et al. (2021), Hsu et al. (2021) and Pratap et al. (2023) for the encoder families; Chang et al. (2022) and Schmid et al. (2023) for compressing speech and audio models specifically. |
    | **INT8 on the CPU, with a convolution-only candidate** | Jacob et al. (2018) for integer-arithmetic-only inference; MindSpore Lite's 2.10.0 operator table, which lists GRU and LSTM as FP16/FP32 only, which is what forces ADR-002 to keep a conv-only variant. |
    | **Language ID as a segment-level task with abstention** | Snyder et al. (2018) on x-vectors for spoken language recognition; Valk & Alumäe (2020) on VoxLingua107; Pratap et al. (2023), whose label sets include `amh`, `sid` and `eng`. |
    | **Heavy augmentation, and evaluation only on real classroom audio** | Ko et al. (2017) on reverberant augmentation; Park et al. (2019) on SpecAugment; Snyder et al. (2015) for MUSAN; Shield & Dockrell (2003) on noise in schools; Lee et al. (1999) and Potamianos & Narayanan (2003) on why children's speech is acoustically different from adults'; Stöter et al. (2018) on estimating how many people are speaking, which is the heart of choral-versus-single. |
    | **A clip-on microphone tested as a real arm, not desk placement only** | Jensen et al. (2020) found room microphones produced noisy audio and used a $230 headset, with 127 of 142 recordings usable; Schlotterbeck et al. (2021) used a low-cost lavalier plugged into teachers' own phones. |
    | **Sidaamu Afoo is not the reason for the no-ASR rule** | MMS ships a Sidamo ASR adapter and `sid` in its language-ID label sets (Pratap et al., 2023); Afrivoice Ethiopia carries roughly 603 h of Sidama speech and WAXAL has a `sid_asr` configuration (Digital Umuganda, 2025; Diack et al., 2026). The no-ASR decision rests on privacy, child speech, classroom acoustics and on-device cost instead. |
    | **Private by default, never a management instrument** | Birnhack & Perry-Hazan (2021): teachers weigh their own privacy when surveillance technology enters the school, and rights consciousness differs sharply by role. |
    | **Minimum-group suppression on the dashboard** | Sweeney (2002) on k-anonymity — applied over distinct teachers rather than lessons, and named "suppression" rather than "anonymity" because k = 5 alone does not prove the latter. |
    | **Personal data stays in Ethiopia; the cloud tier is synthetic** | Proclamation No. 1321/2024, Art. 22(1) on data sovereignty, plus Arts. 7–8 (consent), 11 (minors under 16), 33 (registration), 40 (DPO), 43–44 (breach notice), 47 (DPIA) and 54 (research exemption). Both official copies checked were identical. |
    | **A separate Huawei Cloud demo deployment at all** | The 2026–27 Innovation Competition requires entries to be deployed on Huawei Cloud, to use at least one Huawei Cloud service, and to be shown on a visual demo page; Regional entries are re-run by the committee and must be open source (Huawei, 2026). Huawei Cloud has no Ethiopian region, which is what forces the two-tier split. |
    | **UUIDv7 record IDs and append-only sync** | RFC 9562 (Davis et al., 2024). |
    | **SQLCipher rather than Jetpack Security** | `androidx.security-crypto` is deprecated; `net.zetetic:sqlcipher-android` with `SupportOpenHelperFactory` is the supported path (Zetetic, 2025). |
    | **`MISSING` as capture metadata, not a sixth class** | Android documents that a capturing app can be silenced and simply receives silence, with `AudioRecordingCallback` exposing the change — so lost time is knowable, and must never be smoothed into the timeline. |
    | **Android as the primary client** | StatCounter's Ethiopian traffic share puts Android at about 94%, with Samsung, Tecno and Infinix leading among vendors. HarmonyOS NEXT does not run Android apps. |
    
    ### 12.2 References
    
    APA 7th. Status tags showing how far each source was actually read, and roughly eighty further sources,
    are in [`docs/research/BIBLIOGRAPHY.md`](docs/research/BIBLIOGRAPHY.md).
    
    **Ethiopian education context**
    
    - Alemu, A. (2025). Education crisis in Ethiopia: Reimagining early childhood care and education for lasting impact. *SAGE Open, 15*(4). https://doi.org/10.1177/21582440251379497
    - American Institutes for Research. (2019). *Early grade reading assessment (EGRA) 2018 endline report*. U.S. Agency for International Development.
    - American Institutes for Research. (2020). *Mother tongue teachers' competencies assessment (MTTCA) 2019 report*. U.S. Agency for International Development. https://www.air.org/sites/default/files/2025-09/Mother-Tongue-Teachers-Competencies-Assessment-Final-Report-June-2020.pdf
    - Heugh, K., Benson, C., Bogale, B., & Yohannes, M. A. G. (2007). *Study on medium of instruction in primary schools in Ethiopia: Final report*. Ministry of Education. https://repository.hsrc.ac.za/handle/20.500.11910/6273
    - Ministry of Education (Ethiopia). (2025). *Education statistics annual abstract 2024/25 (2017 E.C.)*. EMIS and ICT Executive Office.
    - Piper, B., Zuilkowski, S. S., & Ong'ele, S. (2016). Implementing mother tongue instruction in the real world: Results from a medium-scale randomized controlled trial in Kenya. *Comparative Education Review, 60*(4), 776–807. https://doi.org/10.1086/688493
    - RTI International. (n.d.). *Assessing early grade reading skills in Africa* [Brochure]. https://www.rti.org/brochures/assessing-early-grade-reading-skills-africa
    - U.S. Agency for International Development. (2020). *Ethiopia fact sheet: Education and youth* (ED628406). ERIC. https://files.eric.ed.gov/fulltext/ED628406.pdf
    - Vujcich, D. (2013). *Policy and practice on language of instruction in Ethiopian schools* (Working Paper No. 108). Young Lives. https://www.younglives-ethiopia.org/sites/default/files/YL-WP108_Vujcich.pdf
    - Yisihak, E., & Damtew, A. (2024). Ethiopian early grade English teachers' preparedness to teach basic reading skills. *Education Research International, 2024*, 1–14. https://doi.org/10.1155/2024/5596229
    
    **Pedagogy, classroom discourse and teacher development**
    
    - Alexander, R. J. (2015). Teaching and learning for all? The quality imperative revisited. *International Journal of Educational Development, 40*, 250–258. https://doi.org/10.1016/j.ijedudev.2014.11.012
    - Birnhack, M., & Perry-Hazan, L. (2021). Differential rights consciousness: Teachers' perceptions of privacy in the surveillance school. *Teaching and Teacher Education, 101*, 103302. https://doi.org/10.1016/j.tate.2021.103302
    - Hardman, F., Abd-Kadir, J., Agg, C., Migwi, J., Ndambuku, J., & Smith, F. (2009). Changing pedagogical practice in Kenyan primary schools: The impact of school-based training. *Comparative Education, 45*(1), 65–86. https://doi.org/10.1080/03050060802661402
    - Hattie, J., & Timperley, H. (2007). The power of feedback. *Review of Educational Research, 77*(1), 81–112. https://doi.org/10.3102/003465430298487
    - Heward, W. L., Courson, F. H., & Narayan, J. S. (1989). Using choral responding to increase active student response. *TEACHING Exceptional Children, 21*(3), 72–75. https://doi.org/10.1177/004005998902100321
    - Howe, C., Hennessy, S., Mercer, N., Vrikki, M., & Wheatley, L. (2019). Teacher–student dialogue during classroom teaching: Does it really impact on student outcomes? *Journal of the Learning Sciences, 28*(4–5), 462–512. https://doi.org/10.1080/10508406.2019.1573730
    - Kraft, M. A., Blazar, D., & Hogan, D. (2018). The effect of teacher coaching on instruction and achievement: A meta-analysis of the causal evidence. *Review of Educational Research, 88*(4), 547–588. https://doi.org/10.3102/0034654318759268
    - La Paro, K. M., & Pianta, R. C. (2003). *Classroom Assessment Scoring System* [Database record]. APA PsycTests. https://doi.org/10.1037/t08945-000
    - Liu, J., & Cohen, J. (2021). Measuring teaching practices at scale: A novel application of text-as-data methods. *Educational Evaluation and Policy Analysis, 43*(4), 587–614. https://doi.org/10.3102/01623737211009267
    - Molina, E., Fatima, S. F., Ho, A., Melo Hurtado, C., Wilichowksi, T., & Pushparatnam, A. (2018). *Measuring teaching practices at scale: Results from the development and validation of the Teach classroom observation tool* (Policy Research Working Paper No. 8653). World Bank. https://doi.org/10.1596/1813-9450-8653
    - Pontefract, C., & Hardman, F. (2005). The discourse of classroom interaction in Kenyan primary schools. *Comparative Education, 41*(1), 87–106. https://doi.org/10.1080/03050060500073264
    - Popova, A., Evans, D. K., Arancibia, V., & Breeding, M. E. (2021). *Teacher professional development around the world: The gap between evidence and practice*. World Bank. https://doi.org/10.1596/40097
    - Rowe, M. B. (1986). Wait time: Slowing down may be a way of speeding up! *Journal of Teacher Education, 37*(1), 43–50. https://doi.org/10.1177/002248718603700110
    - Shield, B. M., & Dockrell, J. E. (2003). The effects of noise on children at school: A review. *Building Acoustics, 10*(2), 97–116. https://doi.org/10.1260/135101003768965960
    - Stallings, J. A., Knight, S. L., & Markham, D. (2014). *Using the Stallings observation system to investigate time on task in four countries*. World Bank. https://doi.org/10.1596/20687
    - Tobin, K. (1987). The role of wait time in higher cognitive level learning. *Review of Educational Research, 57*(1), 69–95. https://doi.org/10.3102/00346543057001069
    
    **Automated classroom analysis — the prior systems Echovolve builds on**
    
    - Chanchal, A., & Zualkernan, I. (n.d.). *Exploring semi-supervised learning for audio-based automated classroom observations* (ED626900). ERIC. https://files.eric.ed.gov/fulltext/ED626900.pdf
    - Cosbey, R., Wusterbarth, A., & Hutchinson, B. (2019). Deep learning for classroom activity detection from audio. In *ICASSP 2019* (pp. 3727–3731). IEEE. https://doi.org/10.1109/ICASSP.2019.8683365
    - Demszky, D., & Liu, J. (2023). M-Powering Teachers: Natural language processing powered feedback improves 1:1 instruction and student outcomes. In *Proceedings of the Tenth ACM Conference on Learning @ Scale* (pp. 59–69). ACM. https://doi.org/10.1145/3573051.3593379
    - Demszky, D., Liu, J., Hill, H. C., Jurafsky, D., & Piech, C. (2024). Can automated feedback improve teachers' uptake of student ideas? Evidence from a randomized controlled trial in a large-scale online course. *Educational Evaluation and Policy Analysis, 46*(3), 483–505. https://doi.org/10.3102/01623737231169270
    - Demszky, D., Liu, J., Hill, H. C., Sanghi, S., & Chung, A. (2025). Automated feedback improves teachers' questioning quality in brick-and-mortar classrooms. *Computers & Education, 227*, 105183. https://doi.org/10.1016/j.compedu.2024.105183
    - Donnelly, P. J., Blanchard, N., Samei, B., Olney, A. M., Sun, X., Ward, B., Kelly, S., Nystrand, M., & D'Mello, S. K. (2016). Automatic teacher modeling from live classroom audio. In *Proceedings of UMAP 2016* (pp. 45–53). ACM. https://doi.org/10.1145/2930238.2930250
    - Jensen, E., Dale, M., Donnelly, P. J., Stone, C., Kelly, S., Godley, A., & D'Mello, S. K. (2020). Toward automated feedback on teacher discourse to enhance teacher learning. In *Proceedings of CHI 2020* (pp. 1–13). ACM. https://doi.org/10.1145/3313831.3376418
    - Owens, M. T., Seidel, S. B., Wong, M., Bejines, T. E., Lietz, S., Perez, J. R., … Tanner, K. D. (2017). Classroom sound can be used to classify teaching practices in college science courses. *Proceedings of the National Academy of Sciences, 114*(12), 3085–3090. https://doi.org/10.1073/pnas.1618693114
    - Schlotterbeck, D., Uribe, P., Araya, R., Jimenez, A., & Caballero, D. (2021). What classroom audio tells about teaching: A cost-effective approach for detection of teaching practices using spectral audio features. In *LAK21* (pp. 132–140). ACM. https://doi.org/10.1145/3448139.3448152
    - Shapsough, S. Y., & Zualkernan, I. A. (2017). A voice-based mobile system for generating Stallings-type class observations. In *ICALT 2017* (pp. 457–459). IEEE. https://doi.org/10.1109/ICALT.2017.139
    - Wang, J., Dudy, S., He, X., Wang, Z., Southwell, R., & Whitehill, J. (2025). Optimizing speaker diarization for the classroom: Applications in timing student speech and distinguishing teachers from children. *Journal of Educational Data Mining, 17*(1), 98–125. https://doi.org/10.5281/zenodo.14871875
    - Zualkernan, I., & Khan, M. S. (2020). Towards an audio-based CNN for classroom observation on a smartwatch. In *AI4G 2020* (pp. 224–229). IEEE. https://doi.org/10.1109/AI4G50087.2020.9311083
    
    **Speech processing, datasets and machine-learning method**
    
    - Babu, A., Wang, C., Tjandra, A., Lakhotia, K., Xu, Q., Goyal, N., … Auli, M. (2021). *XLS-R: Self-supervised cross-lingual speech representation learning at scale* (arXiv:2111.09296). https://doi.org/10.48550/arXiv.2111.09296
    - Baevski, A., Zhou, H., Mohamed, A., & Auli, M. (2020). *wav2vec 2.0: A framework for self-supervised learning of speech representations* (arXiv:2006.11477). https://doi.org/10.48550/arXiv.2006.11477
    - Chang, H.-J., Yang, S.-W., & Lee, H.-Y. (2022). DistilHuBERT: Speech representation learning by layer-wise distillation of hidden-unit BERT. In *ICASSP 2022* (pp. 7087–7091). IEEE. https://doi.org/10.1109/ICASSP43922.2022.9747490
    - Diack, A., Nelson, P., Agbesi, K., Nakalembe, A., … Matias, Y. (2026). *WAXAL: A large-scale multilingual African language speech corpus* (arXiv:2602.02734). https://doi.org/10.48550/arXiv.2602.02734
    - Digital Umuganda. (2025). *Afrivoice Ethiopia* [Data set]. Hugging Face. https://huggingface.co/datasets/DigitalUmuganda/Afrivoice_Ethiopia
    - Gautheron, L., Kidd, E., Malko, A., Lavechin, M., & Cristia, A. (2025). *Classification errors distort findings in automated speech processing: Examples and solutions from child-development research* [Preprint]. PsyArXiv. https://doi.org/10.31234/osf.io/u925y_v1
    - Hinton, G., Vinyals, O., & Dean, J. (2015). *Distilling the knowledge in a neural network* (arXiv:1503.02531). https://doi.org/10.48550/arXiv.1503.02531
    - Hsu, W.-N., Bolte, B., Tsai, Y.-H. H., Lakhotia, K., Salakhutdinov, R., & Mohamed, A. (2021). *HuBERT: Self-supervised speech representation learning by masked prediction of hidden units* (arXiv:2106.07447). https://doi.org/10.48550/arXiv.2106.07447
    - Jacob, B., Kligys, S., Chen, B., Zhu, M., Tang, M., Howard, A., Adam, H., & Kalenichenko, D. (2018). Quantization and training of neural networks for efficient integer-arithmetic-only inference. In *CVPR 2018* (pp. 2704–2713). IEEE. https://doi.org/10.1109/CVPR.2018.00286
    - Ko, T., Peddinti, V., Povey, D., Seltzer, M. L., & Khudanpur, S. (2017). A study on data augmentation of reverberant speech for robust speech recognition. In *ICASSP 2017* (pp. 5220–5224). IEEE. https://doi.org/10.1109/ICASSP.2017.7953152
    - Lavechin, M., Bousbib, R., Bredin, H., Dupoux, E., & Cristia, A. (2020). An open-source voice type classifier for child-centered daylong recordings. In *Interspeech 2020* (pp. 3072–3076). ISCA. https://doi.org/10.21437/Interspeech.2020-1690
    - Lee, S., Potamianos, A., & Narayanan, S. (1999). Acoustics of children's speech: Developmental changes of temporal and spectral parameters. *The Journal of the Acoustical Society of America, 105*(3), 1455–1468. https://doi.org/10.1121/1.426686
    - Park, D. S., Chan, W., Zhang, Y., Chiu, C.-C., Zoph, B., Cubuk, E. D., & Le, Q. V. (2019). SpecAugment: A simple data augmentation method for automatic speech recognition. In *Interspeech 2019* (pp. 2613–2617). ISCA. https://doi.org/10.21437/Interspeech.2019-2680
    - Potamianos, A., & Narayanan, S. (2003). Robust recognition of children's speech. *IEEE Transactions on Speech and Audio Processing, 11*(6), 603–616. https://doi.org/10.1109/TSA.2003.818026
    - Pratap, V., Tjandra, A., Shi, B., Tomasello, P., Babu, A., Kundu, S., … Auli, M. (2023). *Scaling speech technology to 1,000+ languages* (arXiv:2305.13516). https://doi.org/10.48550/arXiv.2305.13516
    - Schmid, F., Koutini, K., & Widmer, G. (2023). Efficient large-scale audio tagging via transformer-to-CNN knowledge distillation. In *ICASSP 2023* (pp. 1–5). IEEE. https://doi.org/10.1109/ICASSP49357.2023.10096110
    - Snyder, D., Chen, G., & Povey, D. (2015). *MUSAN: A music, speech, and noise corpus* (arXiv:1510.08484). https://doi.org/10.48550/arXiv.1510.08484
    - Snyder, D., Garcia-Romero, D., McCree, A., Sell, G., Povey, D., & Khudanpur, S. (2018). Spoken language recognition using x-vectors. In *Odyssey 2018* (pp. 105–111). ISCA. https://doi.org/10.21437/Odyssey.2018-15
    - Stöter, F.-R., Chakrabarty, S., Edler, B., & Habets, E. A. P. (2018). *Classification vs. regression in supervised learning for single channel speaker count estimation* (arXiv:1712.04555). https://doi.org/10.48550/arXiv.1712.04555
    - Valk, J., & Alumäe, T. (2020). *VoxLingua107: A dataset for spoken language recognition* (arXiv:2011.12998). https://doi.org/10.48550/arXiv.2011.12998
    
    **Legal, privacy and platform**
    
    - Android Developers. (n.d.). *Sharing audio input*. https://developer.android.com/media/platform/sharing-audio-input
    - Android Developers. (n.d.). *Support 16 KB page sizes*. https://developer.android.com/guide/practices/page-sizes
    - Davis, K., Peabody, B., & Leach, P. (2024). *Universally unique identifiers (UUIDs)* (RFC 9562). RFC Editor. https://www.rfc-editor.org/rfc/rfc9562
    - Federal Democratic Republic of Ethiopia. (2024). *Personal Data Protection Proclamation No. 1321/2024*. Federal Negarit Gazette, 30(35). Local copy: [`docs/legal/`](docs/legal/)
    - MindSpore. (2026). *MindSpore Lite 2.10.0 — downloads, quantization and operator support*. https://www.mindspore.cn/lite/docs/en/r2.10.0/
    - Sweeney, L. (2002). k-anonymity: A model for protecting privacy. *International Journal of Uncertainty, Fuzziness and Knowledge-Based Systems, 10*(5), 557–570. https://doi.org/10.1142/S0218488502001648
    - Zetetic. (2025). *SQLCipher for Android* (Version 4.9.0) [Computer software]. Maven Central `net.zetetic:sqlcipher-android`.
    
    **Competition**
    
    - Huawei. (2026). *Huawei ICT Competition 2026–2027: Northern Africa — Innovation Competition*. Huawei Talent. https://e.huawei.com/en/talent/
    
    ---
    
    ## Team
    
    | Person | Owns |
    |---|---|
    | **Anteneh F. Yimmam** (lead) | Data, ethics, the indicator specification, evaluation design, the pilot, the thesis and the pitch |
    | Person 2 | `ml/` — teacher model, student models, INT8 artefacts, evaluation numbers |
    | Person 3 | `edge/android/`, `server/`, `dashboard/`, `infra/` |
    | Shared | `indicators-core/` — Python reference, Kotlin port, golden vectors |
    
    Hawassa University, Information Systems.
    
    ## Licence
    
    Not yet chosen. An open-source licence is required before the Regional stage of the competition, and
    Apache-2.0 is the working assumption. Note that `docs/research/papers/` currently tracks copyrighted PDFs;
    a public-release audit of the tree **and its history** has to happen before anything is published.
    
    ## Contributing
    
    Read [`docs/research/IMPLEMENTATION_REVIEW.md`](docs/research/IMPLEMENTATION_REVIEW.md) first. Design
    changes go through an ADR in [`docs/adr/`](docs/adr/) using the template `Context · Decision · Alternatives
    considered · Consequences · Status · Date`. No classroom recording — not even a microphone test — happens
    before ethics approval and the required consent.
    ✓ • 0ms

## Activity

    Opened https://onnxruntime.ai/docs/tutorials/mobile/

## Activity

    Opened https://developer.android.com/topic/architecture/data-layer/offline-first

## Activity

    $ rg -n 'dashboard|modules|Compose|RDS|Huawei Cloud|mandatory|framework' docs/research/CODEBASE_STRUCTURE.md docs/research/CLAIMS_AUDIT.md | head -30
    docs/research/CODEBASE_STRUCTURE.md:3:A revision of the monorepo in architecture §12, reflecting [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) and [`ASSUMPTIONS.md`](ASSUMPTIONS.md). Reviewed 2026-09-27 against [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md). The folders are scaffolded; only `edge/android/app` is a starter Gradle module. All described product files and other modules remain proposed and require G0–G5. Prepared test harness/fixtures now live in `tests/acceptance`; see the STEM-first plan and infrastructure recheck.
    docs/research/CODEBASE_STRUCTURE.md:9:| **Two deployment targets:** `infra/huawei-cloud/` (competition demo) and `infra/openeuler/` (pilot) | The 2026–27 rules require Huawei Cloud; the law requires in-country storage of personal data (A-C2, A-C3, D-6). |
    docs/research/CODEBASE_STRUCTURE.md:73:├── edge/android/                     ← Kotlin, Jetpack Compose; minSdk 29
    docs/research/CODEBASE_STRUCTURE.md:92:├── dashboard/                        ← district view; doubles as the competition "visual demo page"
    docs/research/CODEBASE_STRUCTURE.md:105:    │   ├── ecs/                      ← server + dashboard + simulator containers
    docs/research/CODEBASE_STRUCTURE.md:159:| `edge/`, `server/`, `dashboard/`, `infra/`, `tools/check-elf-alignment`, `tools/lesson-simulator` | Sys |
    docs/research/CLAIMS_AUDIT.md:12:1. **The 2026–27 Innovation Competition requires deployment on Huawei Cloud (§7, §17).** New, VERIFIED from the Huawei Talent page for the Northern Africa division (supplied screenshots).
    docs/research/CLAIMS_AUDIT.md:13:   - The topic is "Creating Innovative AI Applications Powered by Huawei Cloud". Entries must "be deployed on Huawei Cloud and use at least one Huawei Cloud service while complying with local laws and regulations", and must be shown on "a visual demo page".
    docs/research/CLAIMS_AUDIT.md:19:   - This is in tension with C-7 (personal data stays in Ethiopia; Huawei Cloud has no Ethiopian region). The design needs a **cloud demo tier that holds only synthetic or public data**, separate from the in-country pilot tier. See ASSUMPTIONS A-C3 and CODEBASE_STRUCTURE.
    docs/research/CLAIMS_AUDIT.md:108:| C-11 | Huawei stack is a "preference". | **CONTRADICTED** for the competition | 2026–27 rules make Huawei Cloud deployment mandatory (finding 1). | Upgrade to a hard requirement for the competition build only. |
    docs/research/CLAIMS_AUDIT.md:122:| 7i | ModelArts nearest regions Johannesburg, Cairo, Lagos. | **PARTLY** | Regions FAQ lists AF-Cairo and AF-Johannesburg; no Lagos. Cloud Connect overview (Huawei Cloud, 2026a): "If your target users are in Africa, select the AF-Johannesburg region." ModelArts overview (Huawei Cloud, 2026b): "ModelArts does not support encrypted OBS buckets" and "does not support cross-region access to OBS buckets". Per-region ModelArts or Ascend availability still unconfirmed. | Use AF-Johannesburg. Keep ModelArts and OBS in the same region. Never place personal data in unencrypted OBS. |
    docs/research/CLAIMS_AUDIT.md:128:| 7o | *New:* competition needs a Huawei Cloud service. | **VERIFIED** | Finding 1. ECS, RDS and DCS are named as examples. | Deploy the demo stack on ECS + RDS (or GaussDB) in AF-Johannesburg. |
    docs/research/CLAIMS_AUDIT.md:162:| 17c | AI mandatory; fully functional. | **VERIFIED** | "Participants are required to use AI technologies and develop their entries based on Huawei Cloud services." |
    docs/research/CLAIMS_AUDIT.md:165:| 17f | Demo on a cheap Android phone. | **Allowed** | "Hardware devices are optional … the team must prepare them on their own." The cloud demo page is mandatory in addition. |
    ✓ • 0ms

## Assistant

**Not entirely. The overall architecture fits the project, but the proposed stack still has more complexity than a three-person team needs.** My earlier review established a workable direction; it did not establish that every component was the best choice.

For your context, I would prioritise reliable offline operation, straightforward debugging and low maintenance. Runtime efficiency still needs measurements on the actual 2 GB phone.

| Area | My recommendation | Reason |
|---|---|---|
| Android | **Keep Kotlin + Compose** | Appropriate for an Android-only app needing microphone capture, foreground services and device-level control. |
| Local storage/sync | **Keep Room + SQLCipher + Keystore + WorkManager** | Fits encrypted, offline operation. Keep local data authoritative for the UI, consistent with Android’s [offline-first guidance](https://developer.android.com/topic/architecture/data-layer/offline-first). |
| Backend | **Keep FastAPI as one application** | Reuses Python knowledge from ML and evaluation. Separate modules are sufficient; separate services would add operational work. |
| Database | **Make PostgreSQL the default in both deployments** | Removes the planned openGauss/PostgreSQL compatibility burden. openGauss’s documented `verify-full` limitation adds another integration decision without a demonstrated project benefit. [openGauss documentation](https://docs.opengauss.org/en/docs/latest/developer_guide/connecting_to_the_database_using_ssl_psycopg.html) |
| Dashboard | **Server-rendered pages initially** | Your first dashboard needs reports and a few charts. FastAPI supports templates; a separate frontend application would introduce another build and deployment workflow. [FastAPI templates](https://fastapi.tiangolo.com/advanced/templates/) |
| Hosting | **One small in-country server, with a maintained, provider-supported Linux LTS** | Choose openEuler if the provider and team can support it comfortably. Otherwise, its branding alone does not justify additional administration. |
| Deployment | **Docker Compose, one scheduled job process, encrypted backups** | Sufficient for the planned scale. Keep the synthetic competition deployment isolated, using the same application and database engine. |

**The biggest simplification should be in ML.** The current PyTorch teacher → MindSpore student → Lite pipeline requires maintaining two training frameworks and conversion boundaries.

My preferred baseline would be:

- **PyTorch for both teacher and student training**, unless a confirmed competition requirement or available hardware justifies MindSpore.
- **One small convolutional student first.** Add distillation or a more complicated architecture when measured accuracy requires it.
- **Benchmark MindSpore Lite against ONNX Runtime on the same model and phone**, then ship one runtime. Keep the alternative as an export option, rather than bundling both.

ONNX Runtime documents Android support and CPU execution, making it a credible comparison candidate—not a proven faster choice. MindSpore Lite remains reasonable, but its CPU GRU/LSTM support is FP16/FP32 rather than INT8. These constraints should influence model selection. [ONNX Runtime mobile](https://onnxruntime.ai/docs/tutorials/mobile/), [Lite operator support](https://www.mindspore.cn/lite/docs/en/r2.10.0/reference/operator_list_lite.html)

I would also **keep ModelArts off the critical path**. Based on the competition rules captured in your research pack, Huawei Cloud deployment is required; that does not establish a requirement to use Huawei technology at every layer. Exact eligibility still needs confirmation.

For the **STEM-first trial**, build capture, encrypted storage, offline recovery and descriptive reports first. Adult instructor/student attribution is a separate model challenge; developing a complete university analytics product would distract from validating the setup.

The practical stack I recommend is **native Android + one measured inference runtime + FastAPI + PostgreSQL + a simple dashboard**, with one training framework initially. Preserve the privacy boundaries and shared indicator tests. Reduce framework duplication and optional infrastructure.

I haven’t changed the plans in this turn; these are the revisions I recommend before implementation.

## User

[Image #1] I plan to use shared hosting from zergaw: [Image #2]. This is hosted in ethiopia, will this be enough for this project scope?

## Assistant

I’ll check what Zergaw’s shared plan supports against the project’s backend needs, especially Python hosting, databases and scheduled jobs.

## Activity

    Opened https://zergaw.com/shared-hosting-platform/

## Activity

    Searched the web for site.zergaw.com "Standard" "Python" "7,035", site.zergaw.com "Python" "cron", site.zergaw.com "ASGI" OR "WSGI" OR "PostgreSQL" hosting

## Activity

    Opened https://docs.djangoproject.com/en/5.2/howto/deployment/wsgi/

## Assistant

**It could be enough for the undergraduate pilot’s backend, provided Zergaw supports the application runtime and database we need. The screenshots alone don’t establish that.**

I would revise my earlier blanket rejection of shared hosting: your project’s server workload can be small because audio analysis happens on the phone. A VPS is not automatically necessary.

Your screenshot advertises **100 GB, Python support, SSH, SSL and weekly backups for ETB 7,035/year**. It does not show CPU/RAM limits, the Python deployment method, database engines or scheduled-job support. I couldn’t retrieve the shared-plan page during this check to resolve those details.

| Project component | Suitability for this plan |
|---|---|
| Website and lightweight dashboard | Likely suitable |
| Login, lesson-indicator uploads and report retrieval | Potentially suitable; runtime and resource limits need confirmation |
| Weekly reports and deletion jobs | Suitable if scheduled tasks are supported |
| Android audio analysis | Runs on the phone; hosting capacity doesn’t determine this |
| Model training and large-scale audio processing | Keep on separate research hardware |
| Research audio and annotation tools | Keep separate initially; require verified access, storage and backup controls |

**Storage is unlikely to be the pilot’s bottleneck.** For illustration, 100 teachers uploading 20 lesson records monthly at 10 KB each produce roughly **20 MB/month of payloads**, excluding database overhead and backups. The unknowns are application support, resource limits and reliability.

The most important distinction is what **“Python support”** means:

- **If they support ASGI/Uvicorn with managed process restart**, the proposed FastAPI backend may work. FastAPI needs an ASGI server. [FastAPI deployment documentation](https://fastapi.tiangolo.com/deployment/manually/)
- **If they support only WSGI**, I would consider **Django with server-rendered pages** for this hosting arrangement. Django supports WSGI and PostgreSQL, MariaDB and MySQL. That could serve this small backend without requiring a VPS. [Django deployment](https://docs.djangoproject.com/en/5.2/howto/deployment/wsgi/), [database support](https://docs.djangoproject.com/en/5.2/ref/databases/)
- Zergaw advertises PostgreSQL separately through its database service, but that does **not** establish inclusion in the Standard hosting plan. [Zergaw DBaaS](https://zergaw.com/database-as-a-service/)

Before committing, send their support this:

> For your Standard shared-hosting plan:
> 1. Can I deploy FastAPI through ASGI/Uvicorn, with automatic restart? If not, do you support Django through WSGI?
> 2. Which Python versions, virtual environments and pip packages are supported?
> 3. Which databases are included, and what are their storage/connection limits?
> 4. Are cron jobs allowed? What are the CPU, RAM, process and request-time limits?
> 5. Are application data, database backups and disaster-recovery copies all stored in Ethiopia? Can I create daily backups and test restoration?

**My recommendation: treat this as a credible budget option for the pilot, conditional on those answers and a small deployment test.** Keep training and research audio separate. Weekly backups alone also need improvement before relying on it for live pilot records.

## User

modify this repo structure to implement the infrustructure changes and give me a summary at the end. Ask me clarifying questions to ensure your recommendations are sound on your way to implement the code architecture changes. Also add security modifications at the architecture level

## Assistant

I’ll review the current repository, then revise its structure and architecture for the Zergaw hosting plan. I’ll also add security boundaries and deployment checks. I’ll ask about the hosting capabilities early, since they determine whether FastAPI remains suitable or a WSGI backend would be a better fit.

## Assistant

Has Zergaw confirmed the Standard plan’s Python runtime, database and scheduled-job support?
- Not confirmed yet — keep deployment choices conditional
- ASGI/Uvicorn, PostgreSQL and cron are supported
- WSGI only, MySQL/MariaDB and cron are supported

How much implementation do you want in this pass?
- Repository structure, architecture/security contracts and deployment scaffolding; no product features
- Also implement the initial backend and security foundations

## Activity

    $ git status --short; rg --files --hidden -g '!.git/**' -g '!docs/research/papers/**' -g '!docs/images/**' -g '!**/.DS_Store' -g '!*gradle-wrapper.jar' -g '!*.webp' -g '!*.pdf' -g '!**/.idea/**'
    ?? README.md
    ?? docs/images/
    indicators-core/kotlin/.gitkeep
    indicators-core/golden/.gitkeep
    indicators-core/python/dimts_indicators/.gitkeep
    ml/teacher/.gitkeep
    ml/data/manifests/.gitkeep
    ml/export/.gitkeep
    ml/eval/.gitkeep
    ml/configs/.gitkeep
    ml/lid/.gitkeep
    ml/distill/.gitkeep
    .gitignore
    ml/student/crnn_fp16gru/.gitkeep
    ml/student/conformer_tiny/.gitkeep
    tests/acceptance/vectors.json
    tests/acceptance/README.md
    tests/acceptance/test_harness.py
    tests/acceptance/runner.py
    ml/student/tcn_int8/.gitkeep
    infra/ci/.gitkeep
    infra/openeuler/backup/.gitkeep
    dashboard/.gitkeep
    README.md
    docs/legal/README.md
    ml/features/parity/.gitkeep
    infra/huawei-cloud/rds/.gitkeep
    docs/competition/.gitkeep
    docs/DIMTS_ARCHITECTURE_CONTEXT.md
    infra/huawei-cloud/ecs/.gitkeep
    infra/huawei-cloud/obs/.gitkeep
    edge/android/gradlew.bat
    tools/lesson-simulator/.gitkeep
    docs/ethics/.gitkeep
    infra/openeuler/compose/.gitkeep
    tools/check-elf-alignment/.gitkeep
    edge/android/sync/.gitkeep
    edge/android/gradle.properties
    edge/android/settings.gradle.kts
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md
    server/jobs/.gitkeep
    ml/modelarts/.gitkeep
    tools/demo/.gitkeep
    docs/adr/014-staged-classroom-trials.md
    docs/adr/.gitkeep
    tools/synthetic-lessons/.gitkeep
    edge/android/inference/.gitkeep
    edge/android/build.gradle.kts
    edge/android/.gitignore
    edge/android/gradlew
    server/tests/.gitkeep
    edge/android/audio/.gitkeep
    server/api/.gitkeep
    edge/android/pipeline/.gitkeep
    server/db/migrations/.gitkeep
    edge/android/storage/.gitkeep
    edge/android/gradle/libs.versions.toml
    edge/android/gradle/gradle-daemon-jvm.properties
    docs/research/HOSTING_OPTIONS.md
    docs/research/CLAIMS_AUDIT.md
    docs/research/RESEARCH_PLAN.md
    docs/research/IMPLEMENTATION_REVIEW.md
    docs/research/INFRASTRUCTURE_RECHECK.md
    docs/research/README.md
    docs/research/ASSUMPTIONS.md
    edge/android/benchmark/.gitkeep
    edge/android/gradle/wrapper/gradle-wrapper.properties
    docs/research/TO_OBTAIN.md
    docs/research/BIBLIOGRAPHY.md
    docs/research/CODEBASE_STRUCTURE.md
    docs/research/STEM_TRIAL_PLAN.md
    edge/android/app/build.gradle.kts
    edge/android/app/.gitignore
    edge/android/app/.gitkeep
    edge/android/app/src/main/keepRules/rules.keep
    edge/android/app/src/main/java/org/ethioware/echo/MainActivity.kt
    edge/android/app/src/test/java/org/ethioware/echo/ExampleUnitTest.kt
    edge/android/app/src/androidTest/java/org/ethioware/echo/ExampleInstrumentedTest.kt
    edge/android/app/src/main/res/mipmap-anydpi/ic_launcher_round.xml
    edge/android/app/src/main/res/mipmap-anydpi/ic_launcher.xml
    edge/android/app/src/main/AndroidManifest.xml
    edge/android/app/src/main/res/xml/data_extraction_rules.xml
    edge/android/app/src/main/res/xml/backup_rules.xml
    edge/android/app/src/main/java/org/ethioware/echo/ui/theme/Type.kt
    edge/android/app/src/main/java/org/ethioware/echo/ui/theme/Theme.kt
    edge/android/app/src/main/java/org/ethioware/echo/ui/theme/Color.kt
    edge/android/app/src/main/res/drawable/ic_launcher_background.xml
    edge/android/app/src/main/res/drawable/ic_launcher_foreground.xml
    edge/android/app/src/main/res/values/strings.xml
    edge/android/app/src/main/res/values/themes.xml
    edge/android/app/src/main/res/values/colors.xml
    ✓ • 0ms

## Activity

    $ for p in /AGENTS.md /Users/AGENTS.md /Users/anteneh/AGENTS.md /Users/anteneh/Documents/AGENTS.md /Users/anteneh/Documents/Repos/AGENTS.md; do if [ -f "$p" ]; then cat "$p"; fi; done; rg --files --hidden -g 'AGENTS.md' -g '!.git/**'
    ✗ (1) • 0ms

## Activity

    $ cat .gitignore; cat docs/research/CODEBASE_STRUCTURE.md; cat tests/acceptance/README.md; cat docs/adr/014-staged-classroom-trials.md
    .DS_Store
    **/.DS_Store
    # Sample codebase structure — Dimts
    
    A revision of the monorepo in architecture §12, reflecting [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) and [`ASSUMPTIONS.md`](ASSUMPTIONS.md). Reviewed 2026-09-27 against [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md). The folders are scaffolded; only `edge/android/app` is a starter Gradle module. All described product files and other modules remain proposed and require G0–G5. Prepared test harness/fixtures now live in `tests/acceptance`; see the STEM-first plan and infrastructure recheck.
    
    ## What changed from §12, and why
    
    | Change | Reason |
    |---|---|
    | **Two deployment targets:** `infra/huawei-cloud/` (competition demo) and `infra/openeuler/` (pilot) | The 2026–27 rules require Huawei Cloud; the law requires in-country storage of personal data (A-C2, A-C3, D-6). |
    | **`tools/synthetic-lessons/`** generates both training mixtures and demo lesson records | The Regional stage re-runs code and datasets, and the demo can't use pilot data (A-C3). |
    | `ml/teacher/` uses **PyTorch/HF**; `ml/student/` uses **MindSpore** | MindNLP is discontinued (A-T8, D-1). |
    | `ml/features/` is our own log-mel code, with a Kotlin twin and parity tests | MindAudio is unmaintained. Feature drift between Python and Kotlin would silently break the model. |
    | `ml/lid/` with an MMS-LID baseline | Sidama LID models and data exist (A-D3). |
    | `ml/student/` supports conv-only (INT8) and CRNN (FP16 GRU) variants | Lite has no INT8 GRU/LSTM (A-T3). |
    | `edge/android/audio/` detects mic silencing and supports an external mic | Android silences capture during calls (A-T7); desk audio may be too noisy (A-T4). |
    | `edge/android/storage/` uses SQLCipher + Keystore | Jetpack Security is deprecated (A-T11). |
    | `indicators-core/` handles **missing** spans | Silenced audio must not count as NON_SPEECH. |
    | `tools/check-elf-alignment/` in CI | The 2.10.0 AAR is 4 KB-aligned; catch it before any Play release (A-T6). |
    | `docs/competition/`, `docs/legal/` | Template 1/2 drafts; Art. 33 registration, DPIA and consent packs. |
    | Research PDFs stay in a **separate private repository** | The competition requires open-sourcing; `docs/research/papers/` holds copyrighted PDFs. |
    
    ## Tree
    
    ```
    dimts/
    ├── README.md                         ← what it is, quick start for the demo (reproducible by judges)
    ├── LICENSE                           ← open-source licence (needed for Regional shortlist); e.g. Apache-2.0
    ├── .gitignore                        ← includes .DS_Store, *.wav, *.ms, data/, .env
    ├── docs/
    │   ├── DIMTS_ARCHITECTURE_CONTEXT.md
    │   ├── adr/                          ← ADR-000 template; ADR-001…014 (014 STEM-first trial)
    │   ├── indicator-spec.md             ← mirrors §5; versioned; I-3 framed as balance indicator
    │   ├── indicator-rationale.md        ← RQ-B1 literature matrix
    │   ├── testing/                     ← post-implementation suite/runbook (prepared)
    │   ├── annotation-guide.md           ← class definitions + audio examples; missing/silenced span rule
    │   ├── ethics/                       ← consent + assent forms (amh, sid, eng), IRB approval, breach runbook (Art. 43 + 44)
    │   ├── legal/                        ← Art. 33 registration record, DPIA (Art. 47), legal-advisor Q&A (WS-F)
    │   └── competition/                  ← Template 1 draft, Template 2 slides, demo script, reproduction guide
    │
    ├── tests/acceptance/                 ← prepared runner + 18 contract vectors; implementation adapters still needed
    ├── indicators-core/                  ← single source of truth for I-1…I-8
    │   ├── python/dimts_indicators/      ← reference implementation (Python ≥ 3.10)
    │   │   ├── timeline.py               ← integer-tick intervals; MISSING metadata separate from five acoustic classes
    │   │   ├── postprocess.py            ← deterministic smoothing, timing preservation, unknown LID
    │   │   ├── indicators.py             ← I-1…I-8, spec_version
    │   │   └── calibration.py            ← E7: confusion-matrix error propagation, intervals
    │   ├── kotlin/                       ← Android port (Gradle module, pure Kotlin, no Android deps)
    │   └── golden/                       ← timelines + expected outputs; both impls must match (1e-6)
    │       ├── 001_basic.json
    │       ├── 014_call_interruption.json   ← MISSING span mid-lesson (Android silencing)
    │       └── 020_choral_drill.json        ← near-zero latencies; I-2 split by response type
    │
    ├── ml/
    │   ├── configs/                      ← YAML per experiment (E2…E7)
    │   ├── data/
    │   │   ├── DATASETS.md               ← licence, gating, register, overlap notes (E8)
    │   │   └── manifests/                ← separate STEM instructor/course and early-grade teacher/school splits; NO audio in git
    │   ├── features/
    │   │   ├── logmel.py                 ← 64-bin, 25 ms / 10 ms, streaming normalisation
    │   │   └── parity/                   ← fixtures shared with edge/android/pipeline (Kotlin twin)
    │   ├── synth/ → see tools/synthetic-lessons
    │   ├── teacher/                      ← PyTorch + Hugging Face (XLS-R / MMS / HuBERT) — server only
    │   ├── lid/                          ← MMS-LID-256 baseline (E3), student LID head
    │   ├── student/                      ← MindSpore; variants: tcn_int8/, crnn_fp16gru/, conformer_tiny/
    │   ├── distill/                      ← soft labels from teacher → student (E6)
    │   ├── export/
    │   │   ├── mindir_to_ms.sh           ← MindSpore → MindIR → converter_lite → .ms (+ PTQ full-quant)
    │   │   ├── onnx_to_ms.sh             ← escape hatch: PyTorch → ONNX → .ms
    │   │   └── model_card.py             ← writes model_card.json (feature-config hash, calib-set hash, licence of upstream models)
    │   ├── eval/                         ← frame F1, indicator MAE/ρ, cross-language, teacher-independent splits
    │   └── modelarts/                    ← job specs; AF-Johannesburg; OBS same region; PUBLIC data only
    │
    ├── edge/android/                     ← Kotlin, Jetpack Compose; minSdk 29
    │   ├── app/                          ← UI, i18n (amh, sid, eng); report view; consent screens
    │   ├── audio/
    │   │   ├── CaptureService.kt         ← foregroundServiceType="microphone", started only from UI
    │   │   ├── RingBuffer.kt             ← ≤ 10 s, RAM only
    │   │   ├── SilencingMonitor.kt       ← AudioRecordingCallback → emits MISSING spans
    │   │   └── MicSource.kt              ← built-in vs wired/USB lavalier (D-7)
    │   ├── pipeline/                     ← features (Kotlin twin of logmel.py) → seg → LID → postprocess
    │   ├── inference/                    ← MindSpore Lite 2.10.0 AAR (Java API); ONNX Runtime fallback candidate; separate size/performance gate
    │   ├── storage/                      ← Room + modern sqlcipher-android; Keystore-wrapped key, backup/transfer exclusions
    │   ├── sync/                         ← one-time WorkManager outbox + periodic recovery; ownership checks, durable ACK, payload conflicts, revocation precedence
    │   └── benchmark/                    ← RTF / RSS / battery harness (S2); wraps Lite `benchmark` tool
    │
    ├── server/                           ← Python FastAPI; same code runs in both deployments
    │   ├── api/                          ← cohort-scoped enrolment/context; /v1/lessons, /v1/reports, /v1/consent, /v1/teacher/{id}
    │   ├── jobs/                         ← weekly reports (rule table), k-anonymity aggregation, purge
    │   ├── db/migrations/                ← SQL kept to the PostgreSQL/openGauss common subset
    │   └── tests/
    │
    ├── dashboard/                        ← district view; doubles as the competition "visual demo page"
    │
    ├── tools/
    │   ├── synthetic-lessons/            ← ONE generator, two outputs:
    │   │   ├── audio_mix.py              ←   (a) training mixtures: Afrivoice/FLEURS/ALFFA + MUSAN + RIR + choral sim
    │   │   └── lesson_records.py         ←   (b) synthetic LessonRecords + timelines for the cloud demo
    │   ├── lesson-simulator/             ← web page: play a synthetic/public clip → live indicators (demo)
    │   ├── check-elf-alignment/          ← checks all native libraries, ELF/ZIP packaging; runtime test on 16 KB; pilot limited to verified devices
    │   └── demo/                         ← scripted demo accounts, seed data (synthetic only)
    │
    └── infra/
        ├── huawei-cloud/                 ← COMPETITION DEMO — synthetic/public data only
        │   ├── README.md                 ← one-command redeploy; region AF-Johannesburg
        │   ├── ecs/                      ← server + dashboard + simulator containers
        │   ├── rds/                      ← one chosen DB engine after S6; driver/migration/TLS/restore compatibility proven
        │   └── obs/                      ← public datasets + approved model artefacts (private access; bucket encryption set per service requirement)
        ├── openeuler/                    ← PILOT — personal data, Hawassa University
        │   ├── compose/                  ← server, openGauss, Label Studio, backup
        │   └── backup/                   ← encrypted, in-country
        └── ci/                           ← GitHub Actions: golden tests (py + kt), feature parity,
                                             model-contract check, ELF alignment, secret scan
    ```
    
    ## The data boundary
    
    | | `infra/huawei-cloud/` (competition) | `infra/openeuler/` (pilot) |
    |---|---|---|
    | Data | Synthetic records; corpora/models only after licence and privacy approval | LessonRecords from consenting teachers, Research Mode audio |
    | Personal data | No pilot data; separate credentials/endpoints, seed allowlists and logging controls. Public speech or mixed voices are not automatically non-personal. | Yes. Art. 22(1): stored in Ethiopia. |
    | Who writes | Deploy scripts only | Phones (sync API), annotators (Label Studio) |
    | Data path to the other tier | Approved cloud models → pilot only | None from pilot → cloud, including weights/soft labels; approved artefacts may flow cloud → pilot |
    | Reproducible by judges | Yes (README + generator) | No, and not required |
    
    ## Contract changes worth making now
    
    **`LessonRecord` v1 → v2 (§10.1), illustrative subset only:** Freeze full schema, null/availability reasons, denominators, consent reference, processing versions and validation at G1. No previous version is implemented. STEM requires a distinct context without O–3 grade, compatible model/labels, and cohort-isolated reports/aggregates (ADR-014).
    
    ```jsonc
    {
      "schema_version": 2,
      "cohort": "early_grade",
      "session_type": "early_grade_lesson",
      "label_schema_version": "early-grade-v1",
      // …all v1 fields…
      "capture": {
        "mic": "builtin|wired_lavalier|usb",   // E1 / D-7
        "observed_s": 2357.5,
        "missing_s": 42.5,                     // all lost capture/processing; excluded from every eligible denominator
        "class_size_estimate": 58              // entered by teacher; C-5
      },
      "indicators": {
        // …I1…I8…
        "I2_response_latency_s": { "choral": {"median": 0.2, "n": 30}, "single": {"median": 1.4, "n": 12} },
        "I3_choral_ratio": 0.71                // reported as a balance indicator
      },
      "model_versions": { "seg": "…", "lid": "…", "model_manifest_hash": "…" }
    }
    ```
    
    **`model_card.json`** gains `upstream_models` (with licences), `precision_by_layer` (records which layers stayed FP16 because Lite lacks INT8 GRU), and compatibility evidence for all bundled native libraries/runtime packaging. Manifest also defines shapes, class order, streaming state/lookahead and atomic compatibility checks (review G1). Upstream/derived licences belong in this manifest; using a model only as a teacher does not automatically remove downstream restrictions.
    
    ## Ownership (from §15)
    
    | Area | Owner |
    |---|---|
    | `indicators-core/python`, `golden/`, `docs/` (ethics, legal, competition, indicator-rationale) | Lead |
    | `ml/`, `tools/synthetic-lessons/audio_mix.py` | ML |
    | `edge/`, `server/`, `dashboard/`, `infra/`, `tools/check-elf-alignment`, `tools/lesson-simulator` | Sys |
    | `indicators-core/kotlin`, `ml/features/parity` | Sys + ML |
    # Post-implementation acceptance tests
    
    Prepared 2026-09-27. No product implementation is included. Python 3.10+ standard library only. `vectors.json` contains hand-calculated indicator oracles and cohort-contract cases; `runner.py` sends them to a future implementation adapter. The device, infrastructure and field suites are specified in [the test plan](../../docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md). They are not automated by this runner.
    
    ## Before implementation
    
    Validate the fixture format (does not test the product):
    
    ```sh
    python3 tests/acceptance/runner.py --validate
    python3 -m unittest discover -s tests/acceptance -p 'test_harness.py'
    ```
    
    Ratify the proposed contract in ADR-012/014 before implementing its adapter. Resolve remaining postprocessing/quantile/quality thresholds at G1. These fixtures deliberately avoid unresolved smoothing and multi-value quantile choices; they are a starter contract suite, not exhaustive model or pipeline coverage.
    
    ## After implementation
    
    Create adapters that call the actual Python reference and Kotlin implementation. Each adapter must accept **one JSON request on stdin**, emit **one JSON result on stdout**, and exit; logging goes to stderr. Implementations may map their public field names to the normalized acceptance fields below, but must not calculate replacement results or read expected fixtures. No adapter exists yet. Run each independently against the same vectors:
    
    ```sh
    python3 tests/acceptance/runner.py --adapter '["python3", "path/to/python_adapter.py"]'
    python3 tests/acceptance/runner.py --adapter '["java", "-jar", "path/to/kotlin-adapter.jar"]'
    ```
    
    Replace the paths with actual future adapters. The runner never invokes a shell, uses a timeout per case, fails on missing/malformed/non-finite output and returns nonzero on any failure. No adapter means an error, not a skip or pass. Actual output may contain additional fields, but every oracle field must exist. Counts and categorical/null values are exact; floating values use absolute tolerance 1e-6.
    
    ## Adapter protocol (test-only, acceptance-v1)
    
    - `indicators`: fields `indicator_spec_version`, `cohort`, `tick_ms=10`, `duration_ticks`, `timeline`. Timeline uses half-open `start_tick`/`end_tick`, `label`, optional `lang`. It is already postprocessed, preserves original timestamps and includes missing intervals. Use the real indicator engine, bypassing only feature/model inference. Result: `observed_s`, `missing_s`, `I1` ratio, `I2` single/choral quantiles with `n`, `I3` duration ratio, `I4` `{count, per_10min}`, `I5` per-minute rate, `I6` language shares or null, `I7` coverage or null, `I8` count. Invalid cohort labels return `{"error":"label_schema_mismatch"}`. This normalized shape is not the sync API schema.
    - `validate_context`: invoke actual session/cohort validation. Return `{"valid":true}` or `{"valid":false,"reason":"cohort_context_mismatch|unsupported_cohort"}` (one reason value). STEM cannot contain an O–3 grade; early-grade sessions retain grade/subject.
    - `validate_model`: invoke actual cohort/manifest compatibility check. Unsupported cohort returns `{"valid":false,"reason":"unsupported_cohort"}`. The provided case checks only that incompatibility, not complete manifest verification.
    
    Never use a self-reported mock pass as implementation evidence. Store full runs with git SHA, adapter SHA and model/spec versions in the private evidence store. Contract vectors contain no real audio or personal data. Keep real classroom evidence and test credentials out of this repository.
    # ADR-014: Undergraduate STEM technical trial before early-grade validation
    
    Date: 2026-09-27. Status: **Stage order directed by user; detailed schema/model design proposed for G1 ratification.**
    
    ## Context
    
    The intended product serves O-class and Grades 1–3, but the user plans to test the setup first in undergraduate STEM classrooms. Audio roles, participant ages, session formats and languages differ. Neither the starter app nor the proposed models have established classroom readiness.
    
    ## Decision
    
    Proceed P0 synthetic/staged → P1 undergraduate STEM technical trial → P2 intended early-grade validation. Use the in-country pilot tier for both real cohorts and keep the Huawei Cloud demo synthetic. P1 is a setup/feasibility trial, with separate approvals and voluntary participant consent, not evidence of child-speech accuracy or early-grade pedagogical value.
    
    Require cohort/session context, label schema and model supported-cohort declarations. STEM annotations describe adult instructor/student roles; child-only model logits cannot be renamed into those roles. Keep manifests/calibration/evaluation/reports isolated by cohort. Disable early-grade coaching and district aggregation for STEM. If no adult-compatible model exists, P1 can still test capture, coarse activity, storage/sync and human-coded indicator arithmetic with explicit provenance.
    
    ## Alternatives considered
    
    - Begin in early grades: superseded by the user's requested order.
    - Reuse child labels and combine results: rejected because it conceals the domain change.
    - Build a separate university product: unnecessary scope; use shared infrastructure with explicit cohort contracts.
    
    ## Consequences and validation
    
    P1 precedes P2 recruitment/capture readiness, with distinct G4-U/G4-E approvals. Technical fixes can transfer, but microphones, model/indicator accuracy and coaching need early-grade revalidation. Dates move when prerequisites slip. [STEM_TRIAL_PLAN.md](../research/STEM_TRIAL_PLAN.md) owns trial details; [acceptance suites](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) own test procedures. No product implementation is authorised by this ADR alone.
    ✓ • 0ms

## Activity

    $ sed -n '190,390p' README.md; sed -n '1,110p' docs/research/IMPLEMENTATION_REVIEW.md; sed -n '1,100p' docs/research/INFRASTRUCTURE_RECHECK.md
    edited on two devices and no conflict resolution is needed.
    
    **② The in-country tier (`infra/openeuler/`)** holds everything that is personal data: lesson records,
    enrolment, the consent ledger, Research Mode audio, annotation and any model trained on classroom audio.
    It is one FastAPI process, one database and one job runner, deliberately boring. Article 22 of
    Proclamation No. 1321/2024 requires personal data collected in Ethiopia to be stored on a server or data
    centre located in Ethiopia, so this tier cannot be a foreign cloud region.
    
    **③ The Huawei Cloud demo (`infra/huawei-cloud/`)** exists because the 2026–27 Innovation Competition
    requires an entry deployed on Huawei Cloud with a visual demo page. Huawei Cloud has no Ethiopian region.
    The resolution is a genuinely separate deployment — separate account scope, credentials, endpoint,
    database, logs and backups — running the same server and dashboard code over **synthetic records** from a
    seeded generator that ships with the repository.
    
    **The boundary between ② and ③ is the most important line in the diagram.** Approved public-corpus data
    and cloud-trained artefacts may move from cloud to pilot after licence and privacy review. Nothing moves
    the other way: no classroom audio, no features, no posteriors, no soft labels, no trained weights, no logs
    and no identifiers, for either cohort. Public speech and synthetic mixtures of real voices are *not*
    automatically non-personal, so even those need a provenance review before they enter the cloud tier.
    
    **④ `indicators-core/`** is the single source of truth. The Python package is the reference; the Kotlin
    module is the port; `golden/` holds JSON timelines with expected outputs that both must reproduce to
    1e-6 in CI. This is what stops the phone and the server from quietly disagreeing about what a number means.
    
    ### 5.2 Six design decisions
    
    | Decision | Instead of | Why |
    |---|---|---|
    | **Frame classification, five fixed classes** | Clustering diarization ("who spoke when", unknown N) | The task is a fixed classification problem, not speaker discovery. It is simpler, trainable on little data and runs in real time. Frame-classifier precedent: Lavechin et al. (2020); five-segment-type precedent with teacher-independent validation: Donnelly et al. (2016). |
    | **Teacher–student distillation** | Training a small model from scratch | A large multilingual self-supervised encoder is fine-tuned server-side and used to produce soft labels on unannotated audio; a tiny on-device student learns from them (Hinton et al., 2015). It is also the project's main technical-complexity story. |
    | **Streaming, never batch, in Deployment Mode** | Recording then analysing | Raw audio exists only in a ≤ 10 s RAM ring buffer. This is a privacy guarantee and a storage guarantee at the same time, on a phone with 2 GB of RAM and 16 GB of shared storage. |
    | **Append-only sync, no CRDTs** | Conflict resolution | Each device emits immutable records with client-generated IDs. Withdrawal is a separate revocation and purge workflow that outranks anything queued. |
    | **One indicator spec, two implementations, golden tests** | Reimplementing per platform | See §5.1 ④. |
    | **Rule-based coaching** | A model that suggests | The weekly suggestion is a versioned lookup table from indicator ranges to one sentence. Explainable to a teacher, auditable by an ethics reviewer, defensible in Q&A. |
    
    ### 5.3 What actually leaves the phone
    
    One `LessonRecord` per lesson, a few kilobytes of JSON: indicators, spec and model versions, capture
    accounting, quality flags, an opaque server-issued teacher pseudonym and a school ID. **No audio. No
    embeddings. No timestamps finer than the lesson start. No child-level data. No full timeline.**
    
    ```jsonc
    {
      "record_id": "uuidv7",
      "schema_version": 2,
      "indicator_spec_version": 2,
      "cohort": "early_grade",
      "teacher_pseudo_id": "opaque, server-issued at enrolment",
      "grade": "O|1|2|3",
      "subject": "mother_tongue|math|english|environmental|other",
      "duration_s": 2400,
      "capture": { "observed_s": 2357.5, "missing_s": 42.5, "mic": "builtin" },
      "model_versions": { "seg": "seg-student-0.3.1-int8", "lid": "lid-student-0.2.0-int8" },
      "indicators": {
        "I1_teacher_talk_ratio": 0.78,
        "I2_response_latency_s": { "choral": { "median": 0.2, "n": 30 },
                                   "single": { "median": 0.6, "n": 12 } },
        "I3_choral_ratio": 0.91,
        "I6_language_share": { "amh": 0.62, "sid": 0.31, "eng": 0.05,
                               "oth": 0.01, "unknown": 0.01 }
      },
      "quality": { "mean_seg_confidence": 0.84, "snr_estimate_db": 11.2 }
    }
    ```
    
    The complete schema, its null-and-reason rules and its validation are frozen at gate G1 before any code
    is written against it. The block above is an illustrative subset.
    
    ### 5.4 Repository map
    
    ```
    Echovolve/
    ├── docs/                    architecture context, ADRs, research pack, ethics, legal, competition
    ├── indicators-core/         the contract: python reference · kotlin port · golden vectors
    ├── ml/                      features · teacher model · student · distillation · export · eval
    ├── edge/                    android: app · audio · pipeline · inference · storage · sync · benchmark
    ├── server/                  fastapi api · jobs · db migrations · tests
    ├── dashboard/               woreda view; doubles as the competition demo page
    ├── tests/acceptance/        18 prepared contract vectors + a runner (adapters not written)
    ├── tools/                   synthetic-lessons · lesson-simulator · check-elf-alignment · demo
    └── infra/                   huawei-cloud (demo) · openeuler (pilot) · ci
    ```
    
    The detailed layout, with the reasoning behind each deviation from the original proposal, is in
    [`docs/research/CODEBASE_STRUCTURE.md`](docs/research/CODEBASE_STRUCTURE.md).
    
    ---
    
    ## 6. Huawei technology
    
    Used where it is load-bearing, with a documented escape hatch for every model, because judges probe this
    in Q&A and because a framework bug must not be able to sink the timeline.
    
    | Layer | Choice | Role and caveat |
    |---|---|---|
    | On-device inference | **MindSpore Lite 2.10.0** (Android aarch64 AAR) | Runs both student models on the CPU. Supports post-training INT8 and converts MindIR, ONNX and TFLite to `.ms`. Its operator table lists GRU and LSTM as FP16/FP32 only, so a CRNN student cannot be fully INT8 — which is exactly why ADR-002 keeps a convolution-only candidate. |
    | Student training | **MindSpore 2.x** | Trains the on-device student. Conditional on a week-one spike proving the training stack works on the team's actual hardware. |
    | Teacher model | **PyTorch + Hugging Face** | Loads a multilingual self-supervised encoder server-side. MindNLP was the original plan; its repository now hosts a different project and the preserved branch is unmaintained, so it was dropped. |
    | Feature extraction | **Owned code, Python + Kotlin** | MindAudio is unmaintained, and feature drift between the two implementations would silently break the model. Parity fixtures cover chunk boundaries, resampling and normalisation. |
    | Cloud training | **ModelArts on Ascend**, AF-Johannesburg | Public data only. ModelArts requires same-region OBS and does not support encrypted buckets, so its bucket holds approved public objects and nothing else. |
    | Object storage | **OBS** | Public datasets and model artefacts. No personal data. |
    | Server OS | **openEuler 24.03 LTS SP3** (candidate) | In-country pilot host. |
    | Database | **openGauss**, PostgreSQL as the explicit fallback | Conditional on a hard gate: openGauss documents `verify-full` as unsupported, and shipping a database connection that encrypts without verifying server identity is not acceptable. If that cannot be resolved, ADR-005 selects PostgreSQL. |
    | Client OS (stretch) | HarmonyOS / ArkTS | A roadmap item. Teachers own Android phones, and HarmonyOS NEXT does not run Android apps, so it cannot be the primary client. |
    
    ---
    
    ## 7. Privacy, ethics and Ethiopian law
    
    Ethiopia's Personal Data Protection Proclamation No. 1321/2024 has been in force since 24 July 2024. The
    duties it creates shaped the architecture rather than being bolted onto it:
    
    | Obligation | Article | How the design answers it |
    |---|---|---|
    | Personal data collected locally is stored in Ethiopia | Art. 22(1) | Two tiers. The pilot is in-country; the competition demo holds synthetic records only. |
    | Consent | Arts. 7, 8 | Per-teacher consent in the app and in an append-only server ledger, with a separate opt-in for Research Mode. |
    | Parent or guardian consent for minors under 16 | Arts. 2(15), 11 | Children are recorded incidentally. No classroom capture — not even a microphone test — happens before ethics approval, school permission and the required consent and assent. |
    | Breach notice within 72 hours | Arts. 43, 44 | A runbook in `docs/ethics/`. |
    | Registration with the Ethiopian Communications Authority | Art. 33 | Open item, tracked in the legal workstream. |
    | Data protection officer | Art. 40 | Open item. |
    | Data protection impact assessment | Art. 47 | Open item. |
    | Research exemption | Art. 54 | Conditional; to be confirmed with the university legal office. |
    
    Controls the architecture actually enforces:
    
    - **Raw audio exposure.** Deployment Mode: RAM ring buffer only, never persisted. Research Mode: a
      per-lesson AES key, encrypted at rest, deleted from the phone after a verified in-country receipt.
    - **Voice biometrics.** No speaker embeddings persisted anywhere. Models emit class posteriors only.
    - **Child data.** No child-level features, ever. `CHILD_*` are acoustic classes, not individuals.
    - **Misuse by management.** Individual reports are visible only to the teacher. Director sharing is
      teacher-initiated and revocable, and that promise is written into the consent form and the product copy.
    - **Re-identification via the dashboard.** Minimum group of five distinct teachers, fixed cohorts, equal
      teacher weighting, no arbitrary filters, and complementary-suppression tests before any district release.
    - **Transport and rest.** TLS 1.2+ with certificate pinning; SQLCipher plus Keystore on the device;
      disk encryption and encrypted in-country backups on the server.
    - **Withdrawal.** Stops capture, discards queued lessons, revokes every enrolled device, rejects stale
      retries, purges server records and derived reports, and replays the deletion if a backup is restored.
    
    The independent claims audit is deliberately unflattering about anything that outruns its evidence; see
    [`docs/research/CLAIMS_AUDIT.md`](docs/research/CLAIMS_AUDIT.md).
    
    ---
    
    ## 8. Status: what exists and what does not
    
    | Area | State |
    |---|---|
    | `docs/` | **Substantial.** Architecture context, a claims audit against primary sources, a research and implementation plan, an assumptions register, ADRs, a STEM trial plan, an infrastructure recheck and a hosting analysis. |
    | `edge/android/app` | **A Compose starter.** `MainActivity.kt` is "Hello Android". `settings.gradle.kts` includes only `:app`; the other Android folders hold `.gitkeep`. Microphone permissions and the foreground service are absent. |
    | `tests/acceptance/` | **Prepared, not wired.** A runner and 18 hand-calculated contract vectors exist; no implementation adapter exists, so nothing product-related has been executed. |
    | `indicators-core/`, `ml/`, `server/`, `dashboard/`, `infra/`, `tools/` | **Empty placeholders.** |
    | Any accuracy, latency, memory, battery or deployment number | **Not measured.** No build has been verified, no model trained, no device benchmarked, no host purchased, no cloud account inspected. |
    
    ### Acceptance gates
    
    Nothing proceeds past a gate until its evidence exists.
    
    | Gate | What it requires |
    |---|---|
    | **G0** Baseline and feasibility | A reproducible clean Android build with pinned toolchain; a measured target phone including page size; a MindSpore Lite invocation on device; a training-hardware spike; an exact database driver and migration path. |
    | **G1** Contracts | Candidate ADRs ratified; the full schema v2 and model manifest; exact timeline, postprocess and quantile semantics; golden cases reviewed with expected results; feature config and chunk ownership frozen. |
    | **G2** Offline vertical slice | Authorised synthetic or staged audio → features → inference → indicators → encrypted record → local report, on the target phone, with bounded queues and a 40-minute soak. Process death must produce an explicitly interrupted session, never invented audio. |
    | **G3** Reproducible competition demo | Synthetic fixtures → authenticated ingest → dashboard, on the isolated Huawei Cloud deployment, with a licence manifest and independent reproduction. |
    | **G4-U / G4-E** Cohort readiness | Separate approvals for the undergraduate STEM trial and for early grades. Neither is implied by the other. |
    | **G5-E** Validity and controlled release | A teacher- and school-independent frozen test set, annotation agreement, indicator error with intervals, device equivalence after quantization, coaching co-design and aggregate disclosure tests. Only indicators that pass are released. |
    
    ### Trial order
    
    Synthetic and staged fixtures → an undergraduate STEM technical trial → the intended early-grade
    validation. The STEM phase tests capture, microphones, streaming, storage, sync and private technical
    reports with adult participants. It establishes **technical** feasibility only: adult classroom results
    cannot validate child-speech classes, early-grade coaching or mother-tongue policy measurement, the label
    schemas are deliberately incompatible, and child-model outputs are never relabelled into adult roles.
    See [ADR-014](docs/adr/014-staged-classroom-trials.md) and
    [`docs/research/STEM_TRIAL_PLAN.md`](docs/research/STEM_TRIAL_PLAN.md).
    
    ---
    
    ## 9. Definition of done
    
    All targets are prospective. Thresholds are tuned on development data only; the locked test set is never
    used for distillation, calibration or threshold selection.
    
    **Model metrics**, on a real early-grade classroom test set split by teacher and by school:
    
    | Metric | Target | Stretch |
    |---|---|---|
    | Frame macro-F1, five classes | ≥ 0.70 | ≥ 0.80 |
    | `CHILD_SINGLE` vs `CHILD_CHORAL` F1 | ≥ 0.75 | ≥ 0.85 |
    | Language-ID accuracy, teacher segments ≥ 3 s | ≥ 0.85 | ≥ 0.92 |
    | Student vs teacher-model gap | ≤ 0.08 | ≤ 0.04 |
    | INT8 vs FP32 gap | ≤ 0.02 | ≤ 0.01 |
    
    These are ambitious against the published state of the art: the best system in a recent classroom
    diarization study reached 69% on teacher-versus-child, and five-segment-type classification with ASR
    support reached F1 0.64–0.78 (Wang et al., 2025; Donnelly et al., 2016). No published benchmark exists at
    all for choral-versus-single child speech.
    
    **Indicator validity** — the metric that actually matters — compares Echovolve against human coders on
    held-out lessons: teacher talk ratio within 5 percentage points, response-latency median within 0.5 s,
    choral ratio within 10 percentage points, and Spearman ρ ≥ 0.80 across lessons for I-1, I-3, I-5 and I-6.
    # Architecture and implementation-plan review
    
    Reviewed 2026-09-27. **No product implementation, builds, device benchmarks or deployment performed.** Follow-up adds a future acceptance harness/fixtures and the user-directed STEM-first trial; harness self-checks are not product validation.
    
    The edge / in-country pilot / separate Huawei Cloud demo architecture is a reasonable proposal. It is not ready for unrestricted implementation: the indicator and streaming contracts need to be frozen, device feasibility remains unmeasured, and real-data collection/release has unresolved ethics and security gates. See [STEM_TRIAL_PLAN.md](STEM_TRIAL_PLAN.md), [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md) and [test suites](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) for the 2026-09-27 follow-up. This review governs unresolved implementation details; the architecture owns product constraints, RESEARCH_PLAN owns dates, and CODEBASE_STRUCTURE owns the proposed layout. Prior audits remain dated evidence, not proof that a capability was built.
    
    ## Verified repository baseline
    
    - `edge/android/settings.gradle.kts` includes only `:app`. Other Android folders contain `.gitkeep`, not Gradle modules.
    - `MainActivity.kt` is a Compose “Hello Android” starter. There is no capture, inference, indicator, storage, enrolment or sync implementation.
    - `app/build.gradle.kts` declares minSdk 29, compile/target SDK 37 and arm64-v8a. The version catalog declares AGP 9.4.1 and Kotlin 2.2.10. These are file contents, not a verified compatible build toolchain; G0 must resolve/build them before selecting upgrades or downgrades.
    - `AndroidManifest.xml` has `allowBackup=true`; the referenced backup/extraction XML contains template rules. Microphone permissions/service are absent. These are planned work, not regressions in an existing audio app.
    - ML, indicator, server, dashboard and infrastructure trees are placeholders. No executable tests beyond Android template tests establish product behavior. ADR-014 records the user-directed trial sequence; detailed schema/runtime decisions remain proposed.
    - Research PDFs are already tracked. Adding ignore rules later will not remove tracked files or history; a future public-release audit must cover the actual published tree and history. Do not delete research material as part of this review.
    
    ## Findings and required plan changes
    
    | ID | Priority | Evidence / failure mode | Planned resolution |
    |---|---|---|---|
    | V1 | High | Main architecture used MindNLP/MindAudio, all-INT8 inference and one deployment while the research pack proposes replacements and two deployments. | Reconcile §7–§12; PyTorch/HF teacher, owned features, measured precision variants, isolated cloud demo. These remain candidates until S1–S6 pass. |
    | V2 | High | Missing spans, bridged silence, zero denominators and short-segment LID inheritance could fabricate or bias indicators. | §5 v2: explicit observed/missing time, original timing for latency, null availability, unknown LID, deterministic postprocessing. Freeze all fixture outcomes before coding. |
    | V3 | High | 1–2 s windows every 0.5 s duplicate predictions unless emission ownership is defined; smoothing needs future context. | Specify each output tick's owner, bounded lookahead/finalisation, ring/queue sizes, end flush and interruption resets. Report both throughput and emission latency. |
    | V4 | High | Cloud boundary assumed public/synthetic audio and model weights were automatically non-personal; diagram implied pilot export. | Synthetic record demo by default. Public voices, derived mixtures, weights and logs need provenance/privacy/licence review. No pilot audio, features, soft labels, weights or identifiers flow to cloud. Separate endpoints, identities, credentials, logs and backups. |
    | V5 | High | Encryption alone does not stop OS backup; starter allows backup. | Plan exclusions for sensitive files in both legacy and API 31+ cloud/transfer rules. Test backup/restore, key invalidation, reinstall and shared-phone access before real data. |
    | V6 | High | UUID idempotency alone does not address payload conflicts, forged teacher IDs or withdrawal races. | Ownership derived server-side, transaction-before-ACK, identical retries only, revocation precedence, deletion of derived data and backup restore replay. |
    | V7 | High | k=5 alone does not prevent differencing or identify the intended weighting. | Count distinct teachers, aggregate each teacher first, fixed cohorts and windows, complementary suppression, no flexible drill-down; test repeated releases. Call it minimum-group suppression until disclosure review supports stronger claims. |
    | V8 | High | E1 was scheduled before ethics; E3 expects pilot data before collection; E2 asks teacher-independent evidence from 1–2 lessons. | Staged/adult fixtures before ethics; initial lessons are feasibility only. Real validation follows consent, collection and independent splits; dates move if gates slip. |
    | V9 | Medium | S3 demanded equality between differently trained graphs and quantized outputs. | Compare each export to its own source FP baseline; cross-framework tolerance only for equivalent weights/graphs. Set quantized task/indicator loss budgets separately. |
    | V10 | Medium | 16 KB check covered ELF load alignment only and treated sideloading as a fix. | Inspect all native dependencies (Lite, SQLCipher, fallback runtime), APK packaging/ZIP and runtime behavior. Sideloading is acceptable only on a tested compatible device; it cannot repair native incompatibility. |
    | V11 | Medium | Hosting alternatives and database engines were treated as interchangeable. | Choose one initial engine and test its exact driver/migrations/auth/TLS/backup/restore. PostgreSQL lineage is not compatibility evidence; choosing GaussDB adds its own spike. Host location includes backup/log processing, not just a marketing page. |
    | V12 | Medium | “Never measured”, “validated”, all-MindSpore and “language-agnostic” claims exceeded evidence. | Mark novelty, portability, performance and classroom utility as hypotheses. Report domain/language splits and abstention coverage; avoid policy-compliance or causal coaching claims. |
    
    ## Ordered implementation gates
    
    These are future acceptance criteria, not tasks executed during this review. Gates can overlap only when their prerequisites permit it.
    
    | Gate | Owner | Required evidence before proceeding |
    |---|---|---|
    | G0 — baseline and feasibility | Sys + ML | Reproducible clean Android starter build with pinned toolchain; measured target phone/OS/page size; S1 Lite invocation; S4 training hardware; S6 exact DB driver/migration path. Decide failures through ADRs instead of silently swapping stacks. |
    | G1 — contracts | Lead + Sys + ML | ADR-001/002/004/005/011/012 candidate decisions; full schema v2 and model manifest; exact timeline/postprocess/quantile semantics; proposed Python/Kotlin golden cases reviewed with expected results; feature config and chunk ownership frozen. ADR-002 remains provisional until E5. |
    | G2 — offline vertical slice | Sys + ML | Authorised synthetic/staged PCM → features → candidate inference → indicators → encrypted lesson record → local report on target phone. Bounded queues and a 40 min soak; process death produces an explicit interrupted session with no invented unobserved audio. No cloud or real classroom data prerequisite. |
    | G3 — reproducible competition demo | Sys + Lead | Synthetic fixtures → authenticated ingest → one dashboard/report view on isolated Huawei Cloud deployment; licence manifest, seeded generator, documented model versus fixture outputs and independent reproduction. Public-audio playback is optional and separately reviewed. |
    | G4-U / G4-E — cohort readiness | Lead + Sys | G4-U authorises the first undergraduate STEM phase after participant/age eligibility, institutional consent and privacy tests. G4-E separately authorises early grades after STEM technical exit and child/school requirements. Both require in-country storage/backups, mode separation, shared-device controls and transfer/revocation/purge tests. |
    | G5-E — early-grade validity and controlled release | Lead + ML + Sys | Teacher/school-independent frozen real test set, annotation agreement, indicator error with intervals/coverage, quantized-device equivalence, coaching co-design, aggregate disclosure tests. Release only indicators that pass; invalid/unavailable outputs are null with reasons. |
    
    ## Contract checklist for G1
    
    - Fixture cases: empty/zero-duration input, all silence, all missing, all overlap, interrupted teacher/child transition, 0.25/0.3/0.5/10/60 s boundaries, smoothing ties, final partial minute, mixed-language turn, LID abstention, dropped chunks and end flush. Validate interval ordering, complete coverage and finite values.
    - Rates use observed time; report elapsed time separately. Specify count-ratio qualification and quantile interpolation explicitly. Reports must preserve missing/unknown coverage rather than treating it as zero activity.
    - Teacher role is an acoustic hypothesis: define other adults, playback speech, quiet children and teacher-plus-child overlap in the annotation guide. Pilot-gate each class. A merged CHILD_UNSPECIFIED fallback requires new class/spec versions and disables I-3/I-4 and response-type-specific latency.
    - `LessonRecord` includes cohort/session context, supported-cohort label schema, schema/spec/model/feature/postprocess versions, immutable ID, consent/enrolment reference, elapsed/observed/missing time, indicator availability/reasons and denominators. No full timeline leaves Deployment Mode. Set strict field allowlists, ranges, size limits and supported versions; examples in the other plans are incomplete, not executable schemas.
    - Weekly offline reports need the same versioned deterministic rule table as server reports. Define week boundaries in Africa/Addis_Ababa, equal teacher weighting for district outputs, compatible-version grouping, minimum valid duration/lessons and when cached/server reports supersede local reports. G1 must fix thresholds; early-grade coaching stays disabled until G5-E; STEM remains descriptive.
    - Model manifest pins sample rate, resampler, frame centering/padding, FFT/window/mel/log conventions, streaming normalisation, tensor axes/dtypes/class order, quantization scales, receptive field/lookahead, chunk/state-reset behavior and hashes. Calibration and feature parity cover warm-up and chunk boundaries.
    - Partition real data before adaptation, pseudo-labelling, distillation, calibration or threshold tuning. Public corpus overlap also needs speaker/source checks. Small pilot splits may not support school-level inference: report that limit rather than claim independence.
    
    ## Verification to schedule
    
    - Sync/auth: other-teacher read/delete/write denial; wrong school; duplicate same bytes/semantics; conflicting same ID; lost ACK; partial batch; offline retry; expiry; withdrawal during upload; restore after deletion. Never log record bodies/tokens.
    - Capture/storage: permission denial/revocation, call/mic silencing, external-mic removal, background restrictions, screen lock, thermal throttling, low storage, crash/restart, key loss and backup/transfer. Test API 29 and a modern target-compatible device. Benchmark release builds with the whole pipeline.
    - Aggregates: 4 versus 5 distinct teachers, many lessons from one teacher, adjacent/overlapping cohorts, suppression at totals, withdrawal and repeated weekly releases. No district release if only six pilot teachers make useful safe grouping impossible; use synthetic demo aggregates.
    - Research transfer: separate consent and endpoint, ciphertext integrity and durable in-country receipt before local deletion; retained audio TTL/storage cap and failure/retry behavior. No Research Mode secrets or audio in deployment logs/CI.
    - Public release: dataset/model licensing includes derived student weights, attribution and redistribution; CC-BY-NC is not blanket competition permission. Re-run from licensed synthetic fixtures without pilot data. Audit tracked PDFs/history and secrets before publishing.
    
    ## Sources and limits
    
    Repository findings above were inspected directly. Existing research audit findings are carried forward with their original dates; the follow-up infrastructure recheck opens current official technical docs but does not revalidate every paper, competition rule, provider price or legal interpretation. No runtime/model accuracy claim is verified.
    
    Official Android documentation checked on 2026-09-27:
    
    - [Sharing audio input](https://developer.android.com/media/platform/sharing-audio-input): capture can be silenced and recording callbacks expose changes; plan missingness separately from acoustic silence.
    - [Auto Backup](https://developer.android.com/identity/data/autobackup): eligible app data is backed up by default; Android 12+ uses separate cloud-backup/device-transfer controls. Test explicit exclusions, including restore behavior.
    - [16 KB page sizes](https://developer.android.com/guide/practices/page-sizes): native libraries and APK packaging need checks and runtime testing. All bundled native dependencies are in scope, not just the inference AAR.
    
    Outstanding legal decisions stay in WS-F; outstanding current competition requirements stay in WS-G. Neither is resolved merely by choosing an architecture.
    # Infrastructure documentation re-check
    
    Checked official documentation on 2026-09-27. These are documentation findings and proposed choices, not successful installs or benchmarks. Final host, training hardware, regional capacity and credentials have not been supplied. Applies equally to undergraduate STEM and later early-grade data.
    
    | ID | Component / source | Confirmed documentation and implication | Required post-implementation case |
    |---|---|---|---|
    | INF-01 | [AGP 9.4 compatibility](https://developer.android.com/build/releases/agp-9-4-0-release-notes) | AGP 9.4 lists Gradle 9.6.0, API ≤37 and minimum JDK 17. Repo uses Gradle 9.6.0, API 37, daemon JDK 25 and AGP 9.4.1. Family requirements match; this page does not establish the exact patch/plugin/JDK combination. Resolve and build exact pins before changes. Java source target 11 is separate from Gradle's runtime JDK. | BUILD-01 |
    | INF-02 | [Lite 2.10.0 downloads](https://www.mindspore.cn/lite/docs/en/r2.10.0/use/downloads.html) | Android-aarch64 package contains AAR/runtime/benchmark; cloud-side and device-side Linux converters are distinct downloads. Pin the device-side package and published SHA-256 with matching converter. Package availability is not model compatibility. | MODEL-01 |
    | INF-03 | [Lite operator table](https://www.mindspore.cn/lite/docs/en/r2.10.0/reference/operator_list_lite.html), [PTQ](https://www.mindspore.cn/lite/docs/en/r2.10.0/advanced/quantization.html) | CPU GRU/LSTM list FP16/FP32, not INT8. Keep conv-only INT8 and mixed-precision recurrent candidates; prove full exported graph support and calibration behavior. | MODEL-01/02 |
    | INF-04 | [MindSpore 2.10 release](https://www.mindspore.cn/docs/en/r2.10.0/RELEASE.html) | Release notes do not prove a usable training stack on the team's unidentified hardware. Capture OS/Python/accelerator/driver/CANN compatibility and a forward/backward/save/reload spike. PyTorch teacher and MindSpore student stay conditional. | MODEL-03 |
    | INF-05 | [Microphone foreground service](https://developer.android.com/develop/background-work/services/fgs/service-types#microphone), [audio input sharing](https://developer.android.com/media/platform/sharing-audio-input) | Plan runtime RECORD_AUDIO, foreground-service declarations/permissions appropriate to target SDK, visible-UI start and recording callback handling. Capture can be silenced; callbacks plus frame continuity determine missingness. | DEVICE-01/02 |
    | INF-06 | [WorkManager work requests](https://developer.android.com/develop/background-work/background-tasks/persistent/getting-started/define-work) | Periodic work has a 15-minute minimum and timing depends on constraints/system scheduling. Use unique network-constrained one-time work when an outbox record is ready, plus periodic recovery. Do not promise immediate sync or run live capture in WorkManager. | SYNC-01/02 |
    | INF-07 | [SQLCipher migration](https://www.zetetic.net/sqlcipher/sqlcipher-for-android-migration/), [Auto Backup](https://developer.android.com/identity/data/autobackup), [16 KB support](https://developer.android.com/guide/practices/page-sizes) | Use `net.zetetic:sqlcipher-android` with `SupportOpenHelperFactory` for Room, not the legacy library/API. Pin a tested Room/SQLite/SQLCipher set. SQLCipher and Lite both need native/packaging/runtime checks; encryption does not replace backup exclusions and key lifecycle tests. | STORE-01/02; BUILD-02 |
    | INF-08 | [openEuler releases](https://www.openeuler.org/en/download/), [24.03 LTS SP3 containers](https://docs.openeuler.org/en/docs/24.03_LTS_SP3/cloud/index.html) | 24.03 LTS SP3 is a candidate with published planned EOL 2027/12. Select maintained service pack, architecture, image checksum and actual container engine/Compose versions. A generic “LTS” or “Docker compatible” description is not enough. | INFRA-01 |
    | INF-09 | [openGauss psycopg SSL](https://docs.opengauss.org/en/docs/latest/developer_guide/connecting_to_the_database_using_ssl_psycopg.html), [PostgreSQL libpq SSL](https://www.postgresql.org/docs/current/libpq-ssl.html) | openGauss documents `verify-full` as unsupported; `require` encrypts without verifying server authenticity. PostgreSQL documents certificate/hostname checks for `verify-full`. This is a selection gate: prove authenticated server identity for the chosen openGauss/driver arrangement or choose PostgreSQL; never silently weaken verification. No generic SASL workaround is approved. | DB-01/02 |
    | INF-10 | [FastAPI containers](https://fastapi.tiangolo.com/deployment/docker/) | Build from a pinned Python base rather than the deprecated prebuilt FastAPI image. Proposed deployment uses one API process initially, a separate durable scheduled job runner and a TLS reverse proxy with restricted trusted proxy addresses. Migration/job coordination remains the application's responsibility. | INFRA-01; API-01 |
    | INF-11 | [Huawei RDS PostgreSQL + ECS](https://support.huaweicloud.com/intl/en-us/qs-rds-pg/rds_02_0016.html) | Vendor path uses ECS/RDS in the same region/VPC and controlled security groups with SSL. Demo default: ECS + RDS PostgreSQL, private DB connectivity, least-privilege user. GaussDB is a separate alternative requiring its own compatibility evidence, not a drop-in alias. AF-Johannesburg availability/quota still needs an account-level check. | CLOUD-01; DB-01 |
    | INF-12 | [ModelArts constraints](https://support.huaweicloud.com/intl/en-us/productdesc-modelarts/modelarts_01_0062.html) | Standard ModelArts requires same-region OBS and does not support encrypted OBS buckets. Restrict a dedicated private-access bucket to separately approved inputs/outputs; never place either classroom cohort there. This is service-specific, not a reason to disable encryption on all buckets. | CLOUD-02 |
    | INF-13 | [Label Studio security](https://labelstud.io/guide/security) | Official guidance includes internal-network isolation and production SSRF protection (`SSRF_PROTECTION_ENABLED=true`). Verify authentication, media URLs, outbound telemetry and exact edition capabilities; do not assume enterprise RBAC/audit features in Community. Annotation stays on the in-country private tier. | ANNOT-01 |
    | INF-14 | [Zergaw Cloud Server](https://zergaw.com/cloud-server/) | Provider publishes a cloud-server offering, but this does not establish purchased-host suitability, permitted OS image, accelerator, backups, access terms or data location for every service. Existing hosting list remains a shortlist. Obtain exact configuration/location/backup terms and a restore test. No provider purchase or price revalidation performed. | INFRA-01/02 |
    
    ## Proposed deployment baseline after this check
    
    - Android minSdk 29 / arm64 remains. Retain existing toolchain files pending BUILD-01; this review changes no dependencies. Validate actual target-SDK behavior on modern Android and resource constraints on the reference 2 GB phone.
    - In-country P1 and P2 use one simple API + database + job runner; annotation/training isolated from the public API. openEuler SP3 candidate, openGauss conditional on DB-01; PostgreSQL fallback explicit in ADR-005. Do not start with two pilot database engines.
    - Competition demo is a distinct ECS/RDS PostgreSQL deployment using synthetic records. ModelArts/OBS training is a separate optional capability; it is not required to unblock undergraduate capture tests. Record exact region/service quota before booking capacity.
    - DB listener and annotation UI are private. Only HTTPS API/demo routes are public. Keep service secrets out of images, source and logs; test certificate rotation, backups and restart recovery. Pilot backups, observability and model artefacts remain in-country for both cohorts.
    
    ## Evidence record to complete at G0/G4-U
    
    Record selected host/location, OS image/version/hash, container runtime/Compose, Python/API/driver/database versions, region/VPC and service availability, CA/trust configuration, encrypted-backup location/retention, restore evidence, Android dependency lock/checksums, model/converter/runtime hashes and device OS/RAM/page size. Retention, recovery-time and recovery-point objectives must be assigned numerical values by Lead/Sys before tests can pass. Record unresolved items as blocked, not verified.
    
    Scope limits: no cloud account inspection, provider contract, download/binary inspection, build, training, device operation or data transfer was performed. Previous 4 KB AAR binary finding remains dated prior evidence; verify the newly chosen binary. No new legal interpretation is made here.
    ✓ • 0ms

## Activity

    Opened https://docs.djangoproject.com/en/5.2/howto/deployment/checklist/

## Activity

    $ python3 --version; sed -n '1,55p' docs/research/HOSTING_OPTIONS.md; sed -n '315,370p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; sed -n '1,45p' README.md
    Python 3.14.6
    # In-country hosting options — Article 22 compliance
    
    > 2026-09-27 follow-up: this is a dated provider shortlist, not a selected host. The undergraduate STEM trial uses the same in-country privacy boundary as early grades. See [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md): openEuler service-pack pinning, exact database/driver TLS identity validation (openGauss limitation), provider backup/location terms and restore tests are mandatory selection gates. Prices below were not revalidated in this pass.
    
    
    Checked 2026-09-26, prompted by a review of Ethio Telecom's shared-hosting pricing (`myportal.ethiotelecom.et`) against the uploaded copy of Proclamation No. 1321/2016 E.C. (= 1321/2024 G.C.), Article 22 ("Data Sovereignty"): *"Every data controller or data processor shall ensure the storage, on a server or data center located in Ethiopia, of personal data collected or obtained locally."* This matches the Negarit Gazeta text already used in [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) §3 (Art. 22). A copy of the Proclamation is kept at [`../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf`](../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf).
    
    This answers a narrower question than the rest of the pack: **where can the `infra/openeuler` sovereign-tier server (§8.1, §12) actually be hosted?**
    
    ## The screenshot in question doesn't fit the job
    
    The Ethio Telecom pricing shown (Linux Bronze/Silver/Gold/Platinum, Plesk, ETB 650–5,200/year) is **shared web hosting** — a PHP/MySQL site host with a control panel, not a VPS or bare-metal server with root access. Dimts' sovereign tier needs to run a custom FastAPI app and openGauss/PostgreSQL, which shared Plesk hosting cannot do. Even if Ethio Telecom's data centers are in Ethiopia (they are — see below), this specific product is the wrong tier.
    
    ## Update 2026-09-26: Zergaw's own shared-hosting tier has the same problem
    
    The user found Zergaw's shared-hosting page (`zergaw.com/shared-hosting-platform/`), which states **"Hosting Location: Ethiopia"** per plan — even more explicit Article 22 evidence than anything found earlier. But most of its tiers have the same fit problem as the Ethio Telecom screenshot:
    
    | Zergaw plan | Server | Root access | Price | Fits `infra/openeuler`? |
    |---|---|---|---|---|
    | Basic | "None (shared)" | No | ETB 5,481/**year** | No — shared hosting, "Python support" is a WSGI/Passenger app slot, not a machine you control |
    | Standard | "None (shared)" | No | ETB 7,035/year | No |
    | Premium | "None (shared)" | No | ETB 10,139/year | No |
    | **Dedicated** | 2 CPU, 4 GB RAM, dedicated IP | **Yes** | ETB 5,646.55/**month** (≈ ETB 67,760/year) | **Yes** — this is the one that can install openGauss and run FastAPI, WorkManager ingest, and cron jobs as your own daemons |
    
    The "Dedicated" row is a materially different product from the shared tiers above it, despite being listed on the same pricing page. It overlaps in spec (2 vCPU/4 GB) with a similarly-priced entry on Zergaw's separate Cloud Server (VPS) page (`CS02004`, ETB 5,205/month, quoted in the table below) — confirm with Zergaw sales whether "Dedicated" (shared-hosting page) and the `CS02004` cloud server (VPS page) are the same underlying product, and whether either lets you provision an openEuler image rather than their default OS template.
    
    **Rule of thumb going forward:** on any Ethiopian host's pricing page, "shared hosting" / "Server: none" / a Plesk-or-cPanel-style feature list (subdomains, email accounts, "N databases") means no root and no ability to install openGauss or run your own background services, whatever the location says. Only a line item that names CPU/RAM and says **root/dedicated IP** (VPS, cloud server, dedicated server, bare metal) is a candidate for `infra/openeuler`.
    
    ## Providers that offer rentable infrastructure in Ethiopia
    
    | Provider | Confirmed location | Offering | Fits the sovereign-tier server? | Price |
    |---|---|---|---|---|
    | **INSA G-Cloud** — `cloud.gov.et` | ICT Park, Addis Ababa. Ethiopia's National Data Center, run by the Information Network Security Administration. | IaaS (Elastic Cloud Servers, Bare Metal Servers, VPC, storage), PaaS (container service, **managed MySQL/PostgreSQL/document DB**), SaaS (email, workspace), backup/DR, firewalls, vulnerability scanning. | Best compliance story: a government-run sovereign cloud built explicitly around "local data sovereignty." Targets government, finance, telecom and enterprise; doesn't explicitly exclude a university research project. | Not published; request a quote. |
    | **Ethio Telecom teleCloud** — `telecloud.ethiotelecom.et` | Ethio Telecom's own data centers, built on **Huawei Cloud Stack** as Ethiopia's national sovereign cloud (Ethio Telecom, 2026; Huawei Cloud case study). | IaaS/PaaS/SaaS; billing via telebirr. | Same Huawei stack as MindSpore/ModelArts — a clean story for the competition pitch. **Site returned no response from this session** (network policy); verify pricing and Postgres/DB offerings directly. | Not confirmed here. |
    | **Zergaw Cloud** — `zergaw.com` | HQ and infrastructure at ICT Park, Addis Ababa. Founded 2019; states government institutions among its ~500 clients. | Cloud servers and bare-metal servers (9 tiers, 1–32 vCPU / 2–64 GB RAM), shared hosting, and a **Database-as-a-Service** for PostgreSQL/MySQL/MariaDB marketed as *"Quality and secured databases at local host under Ethiopian legal framework."* | Closest self-service match: rentable VPS with root, or a managed Postgres instance, in ETB, from a company already positioning itself around local legal compliance. | Cloud servers: **ETB 3,272/month** (1 vCPU, 2 GB, 30 GB) up to **ETB 60,573/month** (32 vCPU, 64 GB). DBaaS pricing not published on the page fetched. |
    | **Raxio Ethiopia (ET1)** | ICT Park, Addis Ababa; Tier III certified (Uptime Institute); 800 racks, 3 MW IT power. | **Colocation only** — cross-connect, fibre, carrier-neutral connectivity. You supply and manage the physical server. | Only relevant if the university wants to rack its own hardware in a proper facility instead of hosting on campus. Not a managed-hosting or VPS product. | Colocation pricing not published; comparable retail colocation elsewhere in Addis runs USD 380–650/kW/month (Safaricom Ethiopia, per UT Solutions' 2026 guide). |
    | **UT Solutions PLC** | Addis Ababa HQ; builds and operates data centers (Tier III/IV), including a private edge deployment inside Hawassa Industrial Park for one industrial tenant. | Data-center design, build and managed operations — not a rentable public cloud. | Not a fit as a hosting product. Worth knowing that Hawassa-area data-center-grade infrastructure has been built before, if the university ever wanted a custom on-site facility. | N/A |
    
    ## What this doesn't resolve
    
    - **Article 33 registration.** None of these providers' marketing pages mention helping customers register as a data controller/processor with the Ethiopian Communications Authority. That's still the team's responsibility regardless of host (see `CLAIMS_AUDIT.md` §3, C-7f; `RESEARCH_PLAN.md` WS-F Q1).
    - **Whether renting from a private Ethiopian cloud (Zergaw, teleCloud) satisfies Art. 22** as fully as the government's own G-Cloud. The article only requires the server/data center be *located in Ethiopia* — it doesn't require a government operator — but confirm this reading with the legal advisor alongside the other WS-F questions.
    - **Ethio Telecom and HostHabesha sites were unreachable from this session** (network policy), so their VPS/teleCloud pricing and specs are unverified here. Fetch them directly.
    - **Actual database compatibility.** Zergaw's DBaaS lists PostgreSQL/MySQL/MariaDB, not openGauss by name. openGauss is PostgreSQL-lineage (per `CLAIMS_AUDIT.md` §4, 7j), so a managed *PostgreSQL* instance is a reasonable substitute if openGauss itself isn't offered — this feeds back into ADR-005 (backend/database choice).
    
    ## Recommendation
    
    For a **budget self-service VPS or managed Postgres with an explicit local-law framing**, contact **Zergaw** first, specifically about their **Dedicated** server or `CS02004` cloud server (both root, 2 vCPU/4 GB) — not the Basic/Standard/Premium shared tiers, which don't give you a machine to install anything on regardless of their stated location. For the strongest **compliance and pitch narrative** ("hosted on Ethiopia's own national data center"), get a quote from **INSA's G-Cloud**. Check **Ethio Telecom teleCloud** too, given the Huawei Cloud Stack overlap with the rest of the competition build. Treat Raxio and UT Solutions as colocation/build options only, not as what you want for W0–W1's spike S6.
      "duration_s": 2400,
      "capture": { "observed_s": 2400, "missing_s": 0, "mic": "builtin" },
      "processing_versions": { "features": "candidate-v1", "postprocess": "candidate-v2" },
      "indicators": {
        "I1_teacher_talk_ratio": 0.78,
        "I2_response_latency_s": { "single": { "median": 0.6, "p25": 0.3, "p75": 1.1, "n": 12 }, "choral": { "median": 0.2, "p25": 0.1, "p75": 0.3, "n": 30 } },
        "I3_choral_ratio": 0.91,
        "I4_individual_responses": { "count": 5, "per_10min": 1.25 },
        "I5_turn_density_per_min": 1.9,
        "I6_language_share": { "amh": 0.62, "sid": 0.31, "eng": 0.05, "oth": 0.01, "unknown": 0.01 },
        "I7_interaction_coverage": 0.55,
        "I8_long_silence_events": 1
      },
      "quality": { "mean_seg_confidence": 0.84, "clipping_ratio": 0.001, "snr_estimate_db": 11.2 },
      "device": { "model_class": "arm64-2gb", "app_version": "0.4.0" }
    }
    ```
    
    No audio, no embeddings, no timestamps finer than lesson start, no student-level data. STEM v2 context uses `cohort=undergraduate_stem`, `session_type=lecture|tutorial|lab` and `course_category`, and forbids O–3 `grade`/early-grade `subject`. Persist cohort with enrolment and every dataset/report key; do not overload a school ID for a university course. Final institution/course identifiers and mutual-exclusion validation are frozen at G1.
    
    ### 10.2 Server entities (selected database)
    `teacher` (pseudo_id, school_id, consent_state, language_pref) · `school` · `woreda` · `lesson_record` · `weekly_report` · `aggregate_cell` (woreda × grade × week, suppressed if n < k) · `consent_event` (append-only ledger) · `audit_log` · `research_recording` (Research Mode only, pointer to encrypted blob, consent_ref).
    
    Cohort extension: institution and course/session context for STEM, enrolment cohort, cohort-scoped report keys and dataset provenance. District aggregates consume early-grade records only.
    
    ### 10.3 Identity
    - Teacher enrols with a code issued by the research team; server returns an opaque `teacher_pseudo_id`. Real names stored only in a separate, access-restricted enrolment table (or on paper) [DECISION: ADR-007].
    
    ---
    
    ## 11. Privacy, security, and ethics architecture
    
    | Concern | Control |
    |---|---|
    | Raw audio exposure | Deployment Mode: RAM ring buffer only, never persisted. Research Mode: per-lesson AES key, file encrypted at rest, deleted from phone after verified transfer. |
    | Voice biometrics | No speaker embeddings persisted anywhere. Models output class posteriors only. |
    | Child data | No child-level features. CHILD_* are acoustic classes, not individuals. |
    | Data localisation (C-7) | All personal data (records, research audio, enrolment) on in-country server. Huawei Cloud demo holds synthetic records; any public speech/model asset requires separate licence and privacy approval. No pilot-derived export, including weights or soft labels. |
    | Re-identification via dashboard | Minimum group size 5 distinct teachers, not lessons; fixed cohorts, equal teacher weighting, no arbitrary filters, and complementary suppression/differencing tests before district release. This alone is not proof of anonymity. |
    | Teacher misuse by management | Individual reports visible only to the teacher. Sharing with director is teacher-initiated, revocable. Written into consent form and product copy. |
    | Transport | TLS 1.2+, certificate pinning in app. |
    | At rest | Device: SQLCipher + Keystore; exclude research files, databases, identifiers and credentials from cloud backup/device transfer; test API 29 and API 31+ rules. Server: disk encryption + encrypted backups in-country. |
    | Consent | Per-teacher consent recorded in app and in server ledger; separate opt-in for Research Mode; withdrawal deletes server records (tombstone + purge job). Child consent/assent and school permissions per legal/ethics review; no classroom capture before approval. Offline withdrawal stops capture and discards queued lessons; server revocation rejects stale retries. |
    | Breach | 72-hour notification runbook (per C-7) in `docs/ethics/breach-runbook.md`. |
    | Approvals | Separate G4-U undergraduate recruitment/consent and G4-E early-grade ethics/permissions **before recording each cohort**. Do not assume all undergraduates are adults. No participation tied to grades; practical decline alternative. |
    
    ---
    
    ## 12. Proposed repository structure (monorepo)
    
    ```
    dimts/
    ├── README.md
    ├── docs/
    │   ├── DIMTS_ARCHITECTURE_CONTEXT.md        ← this file
    │   ├── adr/                           ← ADR-000 template, ADR-001..n
    # Echovolve (ድምጽ)
    
    ![status](https://img.shields.io/badge/status-design%20stage-B8791C)
    ![target](https://img.shields.io/badge/target-Android%2010%2B%20and%202%20GB%20RAM-0F6E63)
    ![data](https://img.shields.io/badge/personal%20data-stays%20in%20Ethiopia-2C4A6B)
    ![asr](https://img.shields.io/badge/speech%20recognition-none%20by%20design-54606F)
    
    **Echovolve turns a cheap Android phone into a private coach for early-grade teachers in Ethiopia.**
    The phone listens to a lesson as it happens, never writes the audio down, and at the end produces eight
    plain-language numbers about how the lesson was taught: how much of the talking the teacher did, how
    long children took to answer, whether they answered together or one at a time, how often the class went
    back and forth, and which language was actually spoken. Once a week the teacher — and nobody else —
    sees a short report in Amharic, Sidaamu Afoo or English with one thing to try next.
    
    There is no speech recognition anywhere in the system. Nothing is transcribed, no child is identified,
    no voice print is stored, and no teacher is scored, ranked or compared. The design principle is
    **coaching, not surveillance**, and it is enforced by the architecture rather than by policy alone.
    
    > [!IMPORTANT]
    > **This repository is at the design stage.** As of 2026-09-27 the only code that exists is a Jetpack
    > Compose starter activity under `edge/android/app`. There is no capture, no inference, no indicator
    > engine, no server, no dashboard and no deployment. Every screen and every box in this README is a
    > design target behind the acceptance gates in
    > [`docs/research/IMPLEMENTATION_REVIEW.md`](docs/research/IMPLEMENTATION_REVIEW.md). All numbers shown
    > in the mockups are illustrative fixtures, not measurements.
    
    Echovolve is a B.Sc. final-year project at Hawassa University (defence June 2027) and an entry in the
    Huawei ICT Competition 2026–2027, Innovation track. The documents under `docs/` still carry the earlier
    codename **Dimts**; they have not been renamed.
    
    ---
    
    ## Contents
    
    1. [The problem](#1-the-problem)
    2. [What Echovolve does](#2-what-echovolve-does)
    3. [What the teacher sees](#3-what-the-teacher-sees)
    4. [The eight indicators](#4-the-eight-indicators)
    5. [Architecture](#5-architecture)
    6. [Huawei technology](#6-huawei-technology)
    7. [Privacy, ethics and Ethiopian law](#7-privacy-ethics-and-ethiopian-law)
    8. [Status: what exists and what does not](#8-status-what-exists-and-what-does-not)
    9. [Definition of done](#9-definition-of-done)
    10. [Roadmap](#10-roadmap)
    11. [Documentation](#11-documentation)
    ✓ • 0ms

## Activity

    Opened https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html

## User

> Has Zergaw confirmed the Standard plan’s Python runtime, database and scheduled-job support?

Not confirmed yet — keep deployment choices conditional

## Assistant

I’ll keep the runtime and database conditional, as you requested. The structure will support a lightweight shared-hosting backend, with research audio and training on separate in-country infrastructure and the synthetic demo on Huawei Cloud. I’ll focus this pass on structure, deployment scaffolding and security contracts; a readiness check will flag missing host capabilities.

## Assistant

For the pilot, can research audio, annotation and training stay on a separate university or private in-country machine, while Zergaw receives only derived indicators and reports?
- Yes — keep research data off shared hosting
- Not yet — plan that infrastructure before enabling Research Mode

## User

> How much implementation do you want in this pass?

Repository structure, architecture/security contracts and deployment scaffolding; no product features

## Activity

    $ mkdir -p infra/in-country/shared-hosting infra/in-country/vps/compose infra/in-country/vps/backup infra/research tools/check-hosting tests/infrastructure docs/security server/domain server/security server/templates server/config
        cat > /tmp/echo_structure.py <<'PY'
        from pathlib import Path
        # Relocate only the empty deployment placeholders; no existing code is moved.
        for folder in ('compose','backup'):
         old=Path('infra/openeuler')/folder/'.gitkeep'
         new=Path('infra/in-country/vps')/folder/'.gitkeep'
         if old.exists():old.rename(new)
        p=Path('.gitignore'); s=p.read_text(); s+='''
        # Local configuration, secrets and private research/runtime artefacts
        .env
        .env.*
        !.env.example
        *.local.json
        *.local.toml
        *.pem
        *.key
        *.p12
        *.pfx
        /private-data/
        /artifacts/
        /backups/
        *.sqlite3
        *.sqlite3-*
        *.db
        *.db-*
        __pycache__/
        *.py[cod]
        .venv/
        .pytest_cache/
        # Copyrighted PDFs already tracked are NOT removed by ignore rules.
        '''; p.write_text(s)
        PY
        python3 /tmp/echo_structure.py
        cat > infra/README.md <<'EOF'
        # Deployment targets
        
        Current architecture, 2026-10-02. **Scaffolding only; no running service or provisioning script.** The user intends Zergaw Standard shared hosting. Runtime/database/cron/resource and storage-location capabilities are unconfirmed.
        
        | Target | Purpose | Allowed data | Readiness |
        |---|---|---|---|
        | [in-country/shared-hosting](in-country/shared-hosting/README.md) | Preferred lightweight pilot API + rendered dashboard + scheduled reports/purge | Pseudonymous derived records, consent references and reports; still personal data | Conditional on capability evidence and security tests |
        | [in-country/vps](in-country/vps/README.md) | Fallback when shared-host controls/runtime cannot meet requirements | Same derived-data scope | Provider/OS and versions unselected |
        | [research](research/README.md) | Separate in-country recording transfer, annotation and training | Approved research audio, enrolment identity register, derived research artefacts | Research Mode disabled until provisioned and approved |
        | [huawei-cloud](huawei-cloud/README.md) | Isolated competition demo | Synthetic records only; separate approval for public ML assets | No pilot credentials or connectivity |
        
        One application is deployed to the chosen pilot host and, separately, the synthetic demo. Do not build two production web frameworks or a SQL compatibility layer for hypothetical databases. [ADR-015](../docs/adr/015-capability-gated-hosting.md) defines the selection rule. Shared hosting does not require Docker/root or permit choosing its OS.
        
        Run the offline evidence checker after completing a private copy of the host template:
        
        ```sh
        python3 tools/check-hosting/check_hosting.py infra/in-country/shared-hosting/capabilities.example.json
        ```
        
        The example intentionally returns **BLOCKED**. A passing check only establishes completeness/consistency of declared evidence; it does not probe the provider, certify security, authorise recording or deploy anything. Run HOST/SEC tests before G4-U. See [security architecture](../docs/security/ARCHITECTURE.md).
        EOF
        cat > infra/in-country/shared-hosting/README.md <<'EOF'
        # Zergaw shared-hosting target
        
        Preferred budget candidate, not yet verified. The user confirmed on 2026-10-02 that runtime, database and cron support are unknown. A screenshot advertising Python/SSH/SSL/100 GB cannot determine ASGI/WSGI, root access, process management or tenant isolation.
        
        ## Select one deployment path
        
        | Confirmed capability | Candidate application | Database choice |
        |---|---|---|
        | Managed ASGI with restart and reverse proxy | FastAPI; rendered templates | PostgreSQL preferred |
        | Managed WSGI only | Django; rendered templates | PostgreSQL preferred; supported MySQL/MariaDB transactional configuration acceptable after testing |
        | Neither, no package installation, insufficient isolation or no reliable scheduling | Do not deploy pilot here | Use the in-country VPS fallback |
        
        Do not implement both paths. Record the selected runtime/framework/database versions after provider confirmation and a disposable synthetic smoke test. No framework-dependent entrypoint, connection string, worker command or Compose file is supplied while those choices are unknown. Do not run a web server under an SSH session as a substitute for provider-managed process lifecycle.
        
        ## Capability intake
        
        Copy `capabilities.example.json` to `capabilities.local.json` (ignored). It contains no credentials. Each capability has `value` and `evidence`: use a dated support ticket or private test-report reference. Populate all required fields after confirmation. Database/resource numbers must reflect the actual plan, not the word “unlimited”. The readiness checker requires both provider capability evidence and a supported application combination.
        
        Ask Zergaw for:
        
        1. Supported Python versions; ASGI/Uvicorn versus WSGI; virtualenv/pip support; managed restart/reload and rollback procedure.
        2. Database engine/version, transaction support, DB/user permissions, connection cap and connection protection (authenticated TLS or verified local socket).
        3. RAM, CPU, worker/process, disk/inode, request/body/time limits; scheduled-task frequency and maximum runtime.
        4. Host/proxy configuration, HTTPS renewal, trusted forwarded-header handling and static document-root mapping. Secrets, DB dumps and private files must be outside the public root.
        5. Account/filesystem/process isolation; staff access policy and access logging; secret injection, patch ownership and incident contact.
        6. Primary, database, logs, backup/replica and restore locations; encryption at rest, daily encrypted recoverable backup, customer restore access and retention/deletion procedure. Do not infer all locations from the primary server's country.
        
        ## Deployment sequence after product implementation
        
        1. Complete intake and checker; ratify one framework/DB; run HOST-01–04 and applicable SEC cases on synthetic fixtures.
        2. Install pinned dependencies in a private application directory/venv, never upload the repository wholesale into `public_html`/`web`.
        3. Expose only reviewed static assets through the public document root. Framework gateway routes dynamic requests; secrets, source, fixtures, docs and research assets stay inaccessible.
        4. Load secrets using provider-private configuration; use separate application/migration accounts and separate demo/pilot credentials. Set exact hosts/origins, debug off, verified HTTPS/proxy handling and production session settings.
        5. Backup, migrate once with a deployment lock, load no real data, run auth/scope/restore tests and switch traffic using provider-supported restart. Keep a documented application rollback and schema recovery plan.
        6. Schedule bounded, idempotent CLI jobs for reports, purge and backup. Use a DB-backed lease and checkpoint, not an in-memory timer or publicly callable cron URL. Alert on missed runs; cron limitations are a selection gate.
        7. Enable undergraduate pilot enrolment only after G4-U. Research Mode has a different endpoint and remains disabled until the research environment is ready. Repeat G4-E separately.
        
        Proposed recovery objectives: backup at least daily, RPO ≤24 h, restore target ≤8 h in a tested run. Retention is set through the approved pilot policy before collection; do not retain backups indefinitely. These are project targets, not claims about Zergaw's service. Weekly-only backup does not meet them.
        EOF
        cat > infra/in-country/vps/README.md <<'EOF'
        # In-country VPS fallback
        
        Use only if the shared-hosting readiness checks fail or later measured load exceeds the plan. This is a fallback topology, not a second pilot or a purchased server.
        
        Select a provider-supported maintained Linux LTS (openEuler is optional), one chosen application framework, one database, HTTPS proxy and scheduled CLI jobs. Compose is appropriate here only if the selected host supports it. `compose/` and `backup/` are relocated empty placeholders from `infra/openeuler/`; deployment files await runtime/version selection.
        
        Keep the same domain/security contracts and cohort separation as shared hosting. No openGauss-specific schema or required OS brand. Do not combine Label Studio/ML training with the public application. Require private DB networking, authenticated DB connections, volume encryption, encrypted in-country backups, restore testing and root/admin access controls. Prefer the same framework/database as the synthetic demo; switching DB engine needs migration and behaviour tests.
        EOF
        cat > infra/openeuler/README.md <<'EOF'
        # Legacy deployment path
        
        The previous OS-specific pilot topology has been superseded by [in-country/shared-hosting](../in-country/shared-hosting/README.md) and the [VPS fallback](../in-country/vps/README.md). Empty `compose` and `backup` placeholders were moved to the VPS path. No deployed infrastructure existed here.
        
        openEuler remains an optional maintained VPS OS. It is not a requirement on Zergaw shared hosting. Historical research links to this directory remain valid for context; use `infra/in-country/` for new work.
        EOF
        cat > infra/research/README.md <<'EOF'
        # Separate in-country research environment
        
        Scope: approved recording transfer/storage, private annotation, identity register and ML training. Shared hosting never receives raw recordings, embeddings, fine-grained timelines or model-training jobs. No public annotation URL or public object bucket.
        
        Provisioning and access remain unconfirmed. Until readiness/consent is established, Research Mode recording/export stays disabled; the offline Deployment Mode and synthetic tests may proceed within their gates. Prefer a university/private in-country machine with verified backup, access and operational ownership. A separate folder on the shared web account is not this boundary.
        
        Require separate credentials and encryption keys, per-lesson consent references, authenticated transfer/integrity/durable receipt, finite retention, controlled annotator access and restore/deletion tests. Do not store participant identity registers in git. Only explicitly approved signed model releases flow to the phone/pilot. No pilot-trained weights, soft labels, logs or classroom data flow to the Huawei Cloud demo.
        EOF
        cat > infra/huawei-cloud/README.md <<'EOF'
        # Synthetic competition deployment
        
        Keep `ecs/`, `rds/` and `obs/` placeholders as optional service locations, not mandatory resources. Deploy the same selected application as the pilot with separate configuration, credentials, DB and hostname. A Huawei Cloud service is part of the recorded competition plan; exact current eligibility/region remains a competition check.
        
        Only synthetic records enter this deployment. Reject pilot tokens and non-synthetic provenance; no live pilot data is copied for seed/demo/debug. OBS/ModelArts are optional and not required for the STEM setup trial. Public corpus/model assets need a distinct licence/privacy review. No automatic sync, backup, telemetry or training export from either real cohort to this tier.
        
        Provisioning awaits framework/runtime selection and service availability. Do not reuse the pilot's `.env`, DB dump, certificates/private keys or Research Mode endpoint.
        EOF
        cat > server/README.md <<'EOF'
        # Pilot and synthetic-demo backend
        
        One modular Python application; no product implementation exists yet. Framework selection is conditional: FastAPI if managed ASGI is verified, Django if only managed WSGI is available. PostgreSQL is preferred; confirmed supported MySQL/MariaDB may be selected with Django after transaction and recovery tests. Implement one combination, not all alternatives.
        
        | Directory | Responsibility / dependency boundary |
        |---|---|
        | `api/` | HTTP validation, authenticated principal, calls to domain use cases; no direct bypass of scope checks |
        | `domain/` | Enrolment/cohort rules, immutable lesson ingest, reports and withdrawal; no web-framework imports |
        | `security/` | Central authorisation policy, credential/session lifecycle and redacted audit contracts |
        | `db/migrations/` | Chosen-engine migrations and scoped repositories; separate migration privilege |
        | `jobs/` | Bounded cron-invoked CLI jobs calling the same use cases, DB leases/checkpoints, retry/idempotency |
        | `templates/` | Rendered dashboard/report pages, autoescaping, no separate SPA build |
        | `config/` | Environment-specific validated settings and startup refusal conditions |
        | `tests/` | Future API/database/security integration tests on synthetic disposable fixtures |
        
        Dependency direction: HTTP/CLI → authorised use cases → scoped repositories. No public research-audio endpoints or training imports. The server never needs the ML framework installed to accept indicator records. Framework-native auth/session/CSRF mechanisms should be reused; do not create custom cryptography or a second generic web framework.
        
        See [security architecture](../docs/security/ARCHITECTURE.md), [ADR-015](../docs/adr/015-capability-gated-hosting.md) and [hosting target](../infra/in-country/shared-hosting/README.md).
        EOF
        cat > server/domain/README.md <<'EOF'
        # Domain boundary
        
        Future use cases: enrolment, cohort/context validation, immutable lesson acceptance, local/server report rules, consent withdrawal and deletion. Require an authenticated actor plus explicit capability/scope for each operation; domain objects never trust request teacher/school/cohort IDs as authority.
        
        Transactions commit lesson + idempotency result together. Owner/record ID + canonical payload hash defines identical retries; conflicting content fails. Revocation wins over stale uploads; use one transaction/locking strategy for consent status checks and acceptance. STEM records cannot enter early-grade coaching or district aggregates. All callers, including jobs, use this boundary.
        EOF
        cat > server/security/README.md <<'EOF'
        # Security implementation boundary (future)
        
        The normative design is [docs/security/ARCHITECTURE.md](../../docs/security/ARCHITECTURE.md). Centralise resource authorisation, enrolment-token revocation, browser session policy and audit redaction. Use the selected framework's maintained authentication/password/CSRF facilities; no shared global API key for teachers.
        
        Separate teacher, aggregate viewer, research annotator and operator capabilities. Operator account administration does not automatically grant permission to read individual reports. Enrolment and recovery are rate-limited, audited and bound to cohort/institution. No raw credentials, report bodies or audio in logs. Every endpoint and job requires negative authorisation tests.
        EOF
        cat > server/config/README.md <<'EOF'
        # Configuration contract (future startup gate)
        
        Configuration is environment-specific, never a checked-in secret. Validate before accepting requests:
        
        - Explicit deployment purpose: pilot, synthetic demo or local synthetic tests. Demo cannot enable real enrolment or research ingestion.
        - One framework/runtime and DB driver with pinned supported versions; exact allowed hostnames and browser origins; debug off remotely.
        - TLS/trusted-proxy configuration tied to the actual host; verify remote DB identity or use a confirmed private local socket.
        - Private secret source outside document root, separate DB/app/migration/backup credentials, revocation/session settings and request limits.
        - Approved consent/cohort scope, finite retention and deletion/backup policy; Research Mode disabled by default and separately configured on research infrastructure.
        
        Unknown/missing production settings must refuse startup. The host evidence checker is an offline planning tool; it does not implement this application startup check.
        EOF
        cat > server/jobs/README.md <<'EOF'
        # Scheduled command contract
        
        Use host-supported cron invoking bounded CLI commands in the application's virtual environment. Proposed commands: generate due weekly reports, process purge work, expire sessions and verify backups. Names/entrypoints await framework selection; no public HTTP endpoint is a cron substitute.
        
        Jobs acquire a database lease, store durable checkpoints, process a bounded batch and exit within the host's time/memory limit. Report-generation keys include teacher/cohort/week/spec version. Duplicate invocations must not create duplicate reports; expired leases must permit crash recovery. Record last-success and backlog metrics without personal payloads; missed runs must alert the operator. Purge overrides stale report work and replays after restore. Invoke the same authorised use cases as the API.
        EOF
        cat > server/templates/README.md <<'EOF'
        # Rendered dashboard
        
        Start with framework-rendered pages and minimal static assets. Keep templates autoescaped; use explicit JSON serialisation when charts need data and avoid unsanitised HTML/script interpolation. Teacher reports are private and not browser/CDN cached; aggregate access is a separate role with disclosure controls. STEM outputs remain technical, with no early-grade coaching or district release.
        EOF
        cat > dashboard/README.md <<'EOF'
        # Dashboard scope
        
        The initial dashboard is rendered by `server/templates/`; this directory holds future design/UX material, not a separate frontend service. It uses the backend's sessions, authorisation and deployment. Add a client application only if a measured interaction need warrants it. The synthetic competition deployment uses the same templates with separate synthetic data and credentials.
        EOF
    ✓ • 0ms

## User

> For the pilot, can research audio, annotation and training stay on a separate university or private in-country machine, while Zergaw receives only derived indicators and reports?

Not yet — plan that infrastructure before enabling Research Mode

## Assistant

I’ve recorded that research infrastructure is not yet available, so Research Mode will remain blocked pending that setup. The shared-hosting target will handle only derived records and reports. I’m adding security contracts for tenant and cohort access, secrets outside the web root, scheduled-job recovery, and deletion that remains effective after a backup restore.

## Activity

    $ cat > docs/security/ARCHITECTURE.md <<'EOF'
        # Security architecture — shared-hosting pilot
        
        2026-10-02. **Architecture requirements, not implemented controls.** Normative for the hosting revision in [ADR-015](../adr/015-capability-gated-hosting.md). User confirmed hosting capabilities are unknown and separate research infrastructure is not yet available. Research Mode is disabled until that infrastructure is prepared and approved.
        
        ## Trust boundaries and minimisation
        
        ```text
        Android (encrypted local state, on-device inference)
          └─ HTTPS derived records → provider gateway → pilot app → selected database
                                       │                 └─ bounded cron jobs
                                       └─ private config; reviewed static assets only
        
        Private in-country research machine [NOT YET AVAILABLE]
          └─ separate consent, endpoint, identity register, audio/annotation/training
        
        Huawei Cloud synthetic demo
          └─ different credentials, hostname, DB and logs; no pilot/research import
        ```
        
        The shared backend stores pseudonymous records, minimal enrolment and consent references, and private reports. Pseudonymous does not mean anonymous. Keep names/contact register separately where practical; do not add student identifiers. Store no audio, embeddings, fine-grained speech timeline, model-training targets or biometric identifiers on shared hosting. These exclusions are the default API field allowlist and infrastructure boundary, not merely upload-size limits.
        
        ## Threat model and controls
        
        | ID | Threat / control | Enforcement owner and acceptance evidence |
        |---|---|---|
        | SEC-01 | Guessed IDs, forged institution/cohort, role escalation: deny by default; derive scope from authenticated enrolment and check ownership on every record/report/export/job. | `server/security` + scoped DB queries; cross-user/cohort negative tests |
        | SEC-02 | Credential theft/reuse: per-device revocable credentials; one-use enrolment codes; explicit recovery. Browser accounts use maintained framework password hashing/session protection. Rate-limit enrolment/login/recovery by account and source without globally locking victims out. | API/config; expiry, replay, revocation and throttling tests; admin MFA via supported mechanism or restricted operator access |
        | SEC-03 | Web attacks: exact hosts/origins; browser session cookies Secure/HttpOnly/SameSite; CSRF on cookie-authenticated changes; autoescaping; CSP appropriate to actual assets; no framing; no debug traces. Native token API is separately authenticated. | Framework/proxy; CSRF/XSS/host/proxy spoof tests; CORS is not authentication |
        | SEC-04 | Cross-tenant/provider access: verify filesystem/process/account isolation and staff-access policy; secrets/source/backups outside document root; least-privilege filesystem/DB roles. | Provider + deployment; unauthorised-file/access tests. Provider admins remain trusted processors; app encryption with keys on the same host does not protect against host compromise. Reject host if residual risk is unacceptable. |
        | SEC-05 | Transport interception: HTTPS with normal hostname/CA validation; remote DB authenticated TLS or verified private local socket. Trust forwarded headers only from the provider's identified proxy. | Provider + API; wrong-cert/hostname and forged-header tests. Pinning is optional only with backup pins/rotation/recovery; never disable validation to recover. |
        | SEC-06 | Secret/data disclosure: private config, distinct demo/pilot/app/migration/backup credentials, rotation and no plaintext tokens/passwords in DB or logs. Use established libraries and random secrets. Log only allowlisted operational/security metadata. | Config + audit; canary-secret/log scan, rotation and document-root tests |
        | SEC-07 | Duplicate/replayed records and withdrawal races: scoped immutable IDs with canonical content hash; transaction before ACK; revoke enrolment before purge and reject stale devices. | Domain + DB; dropped-ACK, concurrent withdrawal, conflict and partial-batch tests |
        | SEC-08 | Silent cron failures or restore resurrection: bounded idempotent DB-leased jobs, checkpointing and missed-run alerts; erase derived reports; replay deletion ledger before restored service returns. | Jobs + backup runbook; crash/retry/lease expiry and restore tests. No long-lived in-memory scheduler or public cron URL. |
        | SEC-09 | Excessive data collection/retention: strict JSON schema, body/batch/rate limits; finite approved retention; no raw-audio endpoints in pilot app. Limits must be numeric/configured before launch. | API + jobs; oversized/extra-field rejection and retention tests |
        | SEC-10 | Disclosure through aggregates: distinct-teacher threshold, equal teacher weighting, fixed cohorts, complementary suppression and repeated-release review. STEM excluded from early-grade coaching/district aggregation. | Reports; disclosure and mixed-cohort tests |
        | SEC-11 | Backup/supply-chain exposure: encrypted daily in-country backups, keys stored separately from archives, least privilege, dependency pins/hashes, small deploy bundle and restore tests. No deploy of entire repo, PDFs, private data or training dependencies. | Deployment + CI; backup/RPO/restore evidence, dependency/secret scan and protected release workflow |
        | SEC-12 | Lost/shared phone and model tampering: SQLCipher/Keystore, account lock/logout, OS backup/transfer exclusions, model manifest integrity and compatibility checks. Offline use remains available within approved enrolled scope. | Android future implementation; storage/privacy/device/model suites; local revocation acts immediately, remote revocation applies once contact occurs |
        
        Architecture decisions follow [OWASP authorisation guidance](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html) for per-request checks and least privilege, and [OWASP logging guidance](https://cheatsheetseries.owasp.org/cheatsheets/Logging_Cheat_Sheet.html) for excluding secrets/sensitive content. Framework settings must be verified against the selected release, e.g. [Django deployment checklist](https://docs.djangoproject.com/en/5.2/howto/deployment/checklist/). These references do not certify this application or provider.
        
        ## Access matrix (default deny)
        
        | Principal | Permitted | Prohibited by default |
        |---|---|---|
        | Enrolled teacher/instructor device | Own cohort sessions/records and reports; own consent/withdrawal | Other users/institutions, role changes, raw research upload to pilot |
        | Teacher/instructor browser session | Own reports and explicitly supported account operations | Other individual reports; unrestricted aggregate queries |
        | District aggregate viewer | Released, disclosure-reviewed early-grade aggregates | Individual reports, STEM cohorts, arbitrary filters |
        | Research annotator | Assigned approved research recordings on private research environment | Shared-backend reports, identity register, cloud demo |
        | Operator | Deploy, account support and redacted system health through restricted operator channel | Routine access to individual report/audio content; role escalation through public UI |
        | Scheduled job | Named internal use case with scoped DB privilege | Public login, arbitrary SQL/admin rights, cross-tier export |
        | Demo identity | Synthetic deployment only | Any pilot/research authentication or connectivity |
        
        Support access to personal content, if ever needed, requires a specifically authorised audited workflow; database administration capabilities must be documented as residual privileged access. Do not describe operator exclusion as protection from a host/database administrator.
        
        ## Shared-hosting launch gates
        
        - Confirm exact Python/DB/scheduler resources, tenant isolation, protected file placement, secret management, backup/log destinations, encryption and restore rights. Unknown entries block real-data deployment.
        - Run the host checklist checker and the actual HOST/SEC tests. Checklist success only validates recorded evidence shape; no provider claim becomes true because a JSON value is `true`.
        - G4-U still requires undergraduate consent/eligibility and institutional approval. G4-E remains separate. Research Mode stays disabled until its separate host, access/transfer/backup controls and approvals exist.
        - Daily backup and target RPO ≤24 h / RTO ≤8 h are provisional project requirements. Retention and deletion-ledger retention need explicit approved values before capture. Keep the service unavailable during restore until deletion replay is complete.
        - If a shared host cannot enforce these boundaries, use the VPS fallback. Root, Docker and an openEuler image are not security prerequisites for a well-managed shared application service.
        
        ## Incident response and operations
        
        Assign a named operator/contact and response process before launch. On suspected credential compromise: revoke affected credentials, suspend suspect enrolments, preserve minimum redacted evidence in-country, rotate secrets and assess records/backup exposure. Follow the existing university/legal notification process; this document establishes no new legal deadline. Document who patches the provider runtime versus application dependencies and schedule periodic access/restore reviews.
        EOF
        cat > docs/adr/015-capability-gated-hosting.md <<'EOF'
        # ADR-015: Capability-gated shared hosting and separated research infrastructure
        
        Date: 2026-10-02. Status: **Repository structure/security contracts adopted for this pass; framework, database and live deployment remain conditional.**
        
        ## Context and user decisions
        
        The user intends to use Zergaw Standard shared hosting in Ethiopia. They confirmed that runtime/database/cron support is not yet known, requested structure/contracts/deployment scaffolding only (no product features), and reported that a separate in-country research machine is not yet available. Earlier documents assumed an openEuler/openGauss VPS and overgeneralised limitations of shared Python hosting.
        
        ## Decision
        
        1. Make `infra/in-country/shared-hosting/` the preferred pilot target; move empty OS-specific VPS scaffolding to `infra/in-country/vps/`. Keep `infra/openeuler/README.md` as a historical redirect.
        2. Host a small Python application for derived-record ingest, rendered reports and scheduled tasks. Research audio/annotation/training move to a separate `infra/research/` boundary. Research Mode remains blocked until provisioned/approved. Preserve the separate synthetic Huawei Cloud target.
        3. Select one framework after evidence: FastAPI for verified managed ASGI; Django for managed WSGI-only. Python support or SSH does not establish either. Do not ship two frameworks or implement a speculative adapter layer. [FastAPI deployment](https://fastapi.tiangolo.com/deployment/manually/), [Django WSGI](https://docs.djangoproject.com/en/5.2/howto/deployment/wsgi/).
        4. Prefer PostgreSQL. If WSGI hosting provides supported MySQL/MariaDB, choose one after transactional/idempotency/migration/restore tests; Django supports these engines. Remove openGauss as a baseline requirement. [Django databases](https://docs.djangoproject.com/en/5.2/ref/databases/).
        5. Use bounded cron-invoked CLI jobs and rendered templates. No separate SPA, Docker/root dependency on shared hosting, persistent background worker or application-owned server-OS requirement.
        6. Enforce the [security architecture](../security/ARCHITECTURE.md) as future implementation/release criteria. Add a fail-closed **offline evidence checker**, not a deployment or runtime-security implementation. Unknown host settings are blocked.
        7. Keep the mobile architecture and existing STEM-first gates. ML framework/runtime selection is not changed by this hosting ADR; it remains subject to device/training evidence and is outside this structural pass.
        
        ## Alternatives and consequences
        
        A VPS gives operational control but adds cost/administration; retain it only as fallback. Shared hosting may lower operational burden, but requires provider evidence on runtime, isolation, resources, scheduling and data/backup locations. No ASGI/WSGI conclusion can be drawn from the screenshot control panel.
        
        Separate domain/security contracts from HTTP and cron adapters, while using one selected framework's maintained facilities. Use the same framework/DB engine across pilot and synthetic demo where available. Do not keep an openGauss/PostgreSQL common SQL subset solely for an unused alternative.
        
        No product routes, authentication implementation, schema migrations, dependency installs, live deployment or provider contact occurs in this pass. Exact versions/entrypoints await capability selection; existing historical evidence remains dated and subordinate to this ADR for current topology.
        EOF
    ✓ • 0ms

## Activity

    $ cat > tools/check-hosting/check_hosting.py <<'EOF'
        """Offline hosting-evidence check. No probes, deployment, secrets or runtime controls."""
        import argparse
        from datetime import date
        import json
        import math
        from pathlib import Path
        import re
        
        CHECKS = (
            'python_packages_and_venv', 'supported_dependency_versions',
            'managed_gateway_and_restart', 'scheduled_cli_jobs',
            'database_transactions_and_unique_constraints', 'database_connection_protected',
            'tenant_isolation_reviewed', 'private_files_outside_document_root',
            'private_secret_injection', 'https_and_proxy_trust_verified',
            'primary_database_logs_in_ethiopia', 'backups_and_replicas_in_ethiopia',
            'encryption_at_rest_verified', 'daily_encrypted_backup',
            'restore_and_deletion_replay_tested', 'redacted_logging_verified',
            'resource_load_test_passed', 'retention_and_incident_owners_assigned',
        )
        LIMITS = ('ram_mb', 'cpu_limit', 'process_limit', 'request_timeout_s',
                  'cron_max_runtime_s', 'database_connection_limit',
                  'backup_interval_hours', 'rpo_hours', 'restore_hours', 'backup_retention_days')
        SELECTIONS = ('runtime', 'framework', 'database', 'python_version')
        ROOT_FIELDS = {'profile', 'data_kind', 'research_mode_enabled', 'selection', 'checks', 'limits'}
        
        
        def load_json(path):
            def unique(pairs):
                result = {}
                for key, value in pairs:
                    if key in result:
                        raise ValueError('Duplicate field')
                    result[key] = value
                return result
        
            def bad_constant(_):
                raise ValueError('Non-finite number')
        
            return json.loads(Path(path).read_text(), object_pairs_hook=unique,
                              parse_constant=bad_constant)
        
        
        def validate(document, today=None):
            """Return field-only errors; supplied evidence is not independently authenticated."""
            errors = []
            today = today or date.today()
            if not isinstance(document, dict) or set(document) != ROOT_FIELDS:
                return ['document: unexpected or missing fields']
            for field, expected in [('profile', 'zergaw-shared-derived-v1'),
                                    ('data_kind', 'derived_only'), ('research_mode_enabled', False)]:
                if type(document[field]) is not type(expected) or document[field] != expected:
                    errors.append(f'{field}: unsupported scope')
        
            values = {}
            for group, names in [('selection', SELECTIONS), ('checks', CHECKS), ('limits', LIMITS)]:
                records = document[group]
                if not isinstance(records, dict) or set(records) != set(names):
                    errors.append(f'{group}: unexpected or missing fields')
                    records = records if isinstance(records, dict) else {}
                for name in names:
                    key = f'{group}.{name}'
                    record = records.get(name)
                    if not isinstance(record, dict) or set(record) != {'value', 'evidence', 'confirmed_on'}:
                        errors.append(f'{key}: incomplete evidence record')
                        continue
                    values[key] = record['value']
                    if not isinstance(record['evidence'], str) or not record['evidence'].strip():
                        errors.append(f'{key}: evidence reference required')
                    try:
                        if not isinstance(record['confirmed_on'], str):
                            raise ValueError()
                        confirmed = date.fromisoformat(record['confirmed_on'])
                        if confirmed.isoformat() != record['confirmed_on'] or confirmed > today:
                            raise ValueError()
                    except ValueError:
                        errors.append(f'{key}: valid non-future confirmation date required')
                    value = record['value']
                    if group == 'checks' and value is not True:
                        errors.append(f'{key}: not confirmed')
                    if group == 'limits' and (type(value) not in (int, float) or
                                               not math.isfinite(value) or value <= 0):
                        errors.append(f'{key}: positive measured limit required')
        
            runtime = values.get('selection.runtime')
            framework = values.get('selection.framework')
            database = values.get('selection.database')
            if (runtime, framework) not in [('asgi', 'fastapi'), ('wsgi', 'django')]:
                errors.append('selection: choose supported ASGI/FastAPI or WSGI/Django path')
            if database not in ('postgresql', 'mysql', 'mariadb'):
                errors.append('selection.database: supported engine required')
            elif framework == 'fastapi' and database != 'postgresql':
                errors.append('selection.database: this ASGI baseline requires PostgreSQL')
            python_version = values.get('selection.python_version')
            if not isinstance(python_version, str) or not re.fullmatch(r'3\.(1[0-9]|[2-9][0-9])\.\d+', python_version):
                errors.append('selection.python_version: explicit Python 3.10+ patch version required')
            # Future-version compatibility is still established by supported_dependency_versions.
            for name, maximum in [('backup_interval_hours', 24), ('rpo_hours', 24), ('restore_hours', 8)]:
                value = values.get(f'limits.{name}')
                if type(value) in (int, float) and value > maximum:
                    errors.append(f'limits.{name}: exceeds project recovery target')
            return errors
        
        
        def main():
            parser = argparse.ArgumentParser(description=__doc__)
            parser.add_argument('capabilities', help='Private JSON evidence file; never include credentials')
            args = parser.parse_args()
            try:
                errors = validate(load_json(args.capabilities))
            except (OSError, ValueError, UnicodeError):
                print('BLOCKED: cannot read a valid capability record (details withheld).')
                return 2
            if errors:
                print('BLOCKED: declared hosting evidence is incomplete or incompatible.')
                for error in errors:
                    print(f'- {error}')
                return 1
            print('Declared evidence is complete. Provider verification, HOST/SEC tests and cohort approvals remain required.')
            return 0
        
        
        if __name__ == '__main__':
            raise SystemExit(main())
        EOF
        python3 - <<'PY'
        from pathlib import Path
        import importlib.util,json
        spec=importlib.util.spec_from_file_location('checker','tools/check-hosting/check_hosting.py'); module=importlib.util.module_from_spec(spec);spec.loader.exec_module(module)
        def unknown():return dict(value=None,evidence='',confirmed_on=None)
        data=dict(profile='zergaw-shared-derived-v1',data_kind='derived_only',research_mode_enabled=False,
         selection={key:unknown() for key in module.SELECTIONS},checks={key:unknown() for key in module.CHECKS},limits={key:unknown() for key in module.LIMITS})
        Path('infra/in-country/shared-hosting/capabilities.example.json').write_text(json.dumps(data,indent=2)+'\n')
        PY
        cat > tests/infrastructure/test_hosting_readiness.py <<'EOF'
        """Synthetic tests of evidence checker only; not provider/application security tests."""
        from copy import deepcopy
        from datetime import date, timedelta
        import importlib.util
        import json
        from pathlib import Path
        import subprocess
        import sys
        import tempfile
        import unittest
        
        ROOT = Path(__file__).resolve().parents[2]
        SCRIPT = ROOT / 'tools/check-hosting/check_hosting.py'
        spec = importlib.util.spec_from_file_location('hosting_check', SCRIPT)
        checker = importlib.util.module_from_spec(spec)
        spec.loader.exec_module(checker)
        
        
        def evidence(value):
            return {'value': value, 'evidence': 'synthetic-test-fixture-only', 'confirmed_on': '2026-10-02'}
        
        
        def complete():
            return {
                'profile': 'zergaw-shared-derived-v1', 'data_kind': 'derived_only', 'research_mode_enabled': False,
                'selection': {key: evidence(value) for key, value in
                              zip(checker.SELECTIONS, ['wsgi', 'django', 'mariadb', '3.12.12'])},
                'checks': {key: evidence(True) for key in checker.CHECKS},
                'limits': {key: evidence(value) for key, value in zip(checker.LIMITS,
                           [512, 1, 2, 60, 120, 10, 24, 24, 8, 30])},
            }
        
        
        class HostingReadinessTests(unittest.TestCase):
            def check(self, document):
                return checker.validate(document, today=date(2026, 10, 2))
        
            def test_supported_wsgi_engines(self):
                for database in ('postgresql', 'mysql', 'mariadb'):
                    record = complete()
                    record['selection']['database']['value'] = database
                    self.assertEqual(self.check(record), [])
        
            def test_supported_asgi_path(self):
                record = complete()
                for key, value in [('runtime', 'asgi'), ('framework', 'fastapi'), ('database', 'postgresql')]:
                    record['selection'][key]['value'] = value
                self.assertEqual(self.check(record), [])
        
            def test_unconfirmed_template_blocks(self):
                path = ROOT / 'infra/in-country/shared-hosting/capabilities.example.json'
                self.assertTrue(self.check(checker.load_json(path)))
                result = subprocess.run([sys.executable, str(SCRIPT), str(path)], capture_output=True, text=True)
                self.assertEqual(result.returncode, 1)
                self.assertIn('BLOCKED', result.stdout)
        
            def test_each_required_check_must_be_true_and_evidenced(self):
                for name in checker.CHECKS:
                    for value in (None, False, 'true', 1):
                        record = complete()
                        record['checks'][name]['value'] = value
                        with self.subTest(name=name, value=value):
                            self.assertTrue(self.check(record))
                    record = complete()
                    record['checks'][name]['evidence'] = ''
                    self.assertTrue(self.check(record))
        
            def test_security_scope_and_unknown_fields_block(self):
                mutations = [('research_mode_enabled', True), ('data_kind', 'raw_audio'),
                             ('profile', 'demo'), ('unexpected', 'secret')]
                for field, value in mutations:
                    record = complete()
                    record[field] = value
                    self.assertTrue(self.check(record))
        
            def test_invalid_limits_and_recovery_targets_block(self):
                for value in (0, -1, True, '512', None, float('inf'), float('nan')):
                    record = complete()
                    record['limits']['ram_mb']['value'] = value
                    self.assertTrue(self.check(record))
                for field, value in [('backup_interval_hours', 168), ('rpo_hours', 25), ('restore_hours', 9)]:
                    record = complete()
                    record['limits'][field]['value'] = value
                    self.assertTrue(self.check(record))
        
            def test_missing_invalid_or_future_evidence_dates_block(self):
                for value in (None, '2026-10-03', 'yesterday', 12):
                    record = complete()
                    record['checks']['daily_encrypted_backup']['confirmed_on'] = value
                    self.assertTrue(self.check(record))
        
            def test_runtime_and_database_mismatch_blocks(self):
                for field, value in [('framework', 'fastapi'), ('database', 'opengauss'), ('runtime', 'cgi'),
                                     ('python_version', 'latest')]:
                    record = complete()
                    record['selection'][field]['value'] = value
                    self.assertTrue(self.check(record))
        
            def test_malformed_or_duplicate_json_is_rejected_without_echo(self):
                with tempfile.TemporaryDirectory() as folder:
                    path = Path(folder) / 'input.json'
                    for content in ('{"secret":"sensitive-canary",', '{"a":1,"a":2}', '{"a":NaN}'):
                        path.write_text(content)
                        result = subprocess.run([sys.executable, str(SCRIPT), str(path)], capture_output=True, text=True)
                        self.assertEqual(result.returncode, 2)
                        self.assertNotIn('sensitive-canary', result.stdout + result.stderr)
        
            def test_bad_shapes_block_without_crashing(self):
                for record in ([], None, {}, {'profile':'wrong'}):
                    self.assertTrue(self.check(record))
                record = complete()
                record['checks'] = []
                self.assertTrue(self.check(record))
                record = complete()
                record['selection']['framework'] = None
                self.assertTrue(self.check(record))
        
        
        if __name__ == '__main__':
            unittest.main()
        EOF
        PYTHONDONTWRITEBYTECODE=1 python3 -m unittest discover -s tests/infrastructure -p 'test_*.py'
    ..........
    ----------------------------------------------------------------------
    Ran 10 tests in 0.279s
    
    OK
    ✓ • 406ms

## Activity

    • Context compacted

## Activity

    $ git status --short
     M .gitignore
     D infra/openeuler/backup/.gitkeep
     D infra/openeuler/compose/.gitkeep
    ?? README.md
    ?? dashboard/README.md
    ?? docs/adr/015-capability-gated-hosting.md
    ?? docs/images/
    ?? docs/security/
    ?? infra/README.md
    ?? infra/huawei-cloud/README.md
    ?? infra/in-country/
    ?? infra/openeuler/README.md
    ?? infra/research/
    ?? server/README.md
    ?? server/config/
    ?? server/domain/
    ?? server/jobs/README.md
    ?? server/security/
    ?? server/templates/
    ?? tests/infrastructure/
    ?? tools/check-hosting/
    ✓ • 0ms

## Activity

    $ rg -n 'openEuler|openGauss|FastAPI|WSGI|shared|^#' docs/research/{CODEBASE_STRUCTURE,RESEARCH_PLAN,IMPLEMENTATION_REVIEW,INFRASTRUCTURE_RECHECK,HOSTING_OPTIONS,README}.md docs/DIMTS_ARCHITECTURE_CONTEXT.md docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md README.md
    docs/research/HOSTING_OPTIONS.md:1:# In-country hosting options — Article 22 compliance
    docs/research/HOSTING_OPTIONS.md:3:> 2026-09-27 follow-up: this is a dated provider shortlist, not a selected host. The undergraduate STEM trial uses the same in-country privacy boundary as early grades. See [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md): openEuler service-pack pinning, exact database/driver TLS identity validation (openGauss limitation), provider backup/location terms and restore tests are mandatory selection gates. Prices below were not revalidated in this pass.
    docs/research/HOSTING_OPTIONS.md:6:Checked 2026-09-26, prompted by a review of Ethio Telecom's shared-hosting pricing (`myportal.ethiotelecom.et`) against the uploaded copy of Proclamation No. 1321/2016 E.C. (= 1321/2024 G.C.), Article 22 ("Data Sovereignty"): *"Every data controller or data processor shall ensure the storage, on a server or data center located in Ethiopia, of personal data collected or obtained locally."* This matches the Negarit Gazeta text already used in [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) §3 (Art. 22). A copy of the Proclamation is kept at [`../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf`](../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf).
    docs/research/HOSTING_OPTIONS.md:10:## The screenshot in question doesn't fit the job
    docs/research/HOSTING_OPTIONS.md:12:The Ethio Telecom pricing shown (Linux Bronze/Silver/Gold/Platinum, Plesk, ETB 650–5,200/year) is **shared web hosting** — a PHP/MySQL site host with a control panel, not a VPS or bare-metal server with root access. Dimts' sovereign tier needs to run a custom FastAPI app and openGauss/PostgreSQL, which shared Plesk hosting cannot do. Even if Ethio Telecom's data centers are in Ethiopia (they are — see below), this specific product is the wrong tier.
    docs/research/HOSTING_OPTIONS.md:14:## Update 2026-09-26: Zergaw's own shared-hosting tier has the same problem
    docs/research/HOSTING_OPTIONS.md:16:The user found Zergaw's shared-hosting page (`zergaw.com/shared-hosting-platform/`), which states **"Hosting Location: Ethiopia"** per plan — even more explicit Article 22 evidence than anything found earlier. But most of its tiers have the same fit problem as the Ethio Telecom screenshot:
    docs/research/HOSTING_OPTIONS.md:20:| Basic | "None (shared)" | No | ETB 5,481/**year** | No — shared hosting, "Python support" is a WSGI/Passenger app slot, not a machine you control |
    docs/research/HOSTING_OPTIONS.md:21:| Standard | "None (shared)" | No | ETB 7,035/year | No |
    docs/research/HOSTING_OPTIONS.md:22:| Premium | "None (shared)" | No | ETB 10,139/year | No |
    docs/research/HOSTING_OPTIONS.md:23:| **Dedicated** | 2 CPU, 4 GB RAM, dedicated IP | **Yes** | ETB 5,646.55/**month** (≈ ETB 67,760/year) | **Yes** — this is the one that can install openGauss and run FastAPI, WorkManager ingest, and cron jobs as your own daemons |
    docs/research/HOSTING_OPTIONS.md:25:The "Dedicated" row is a materially different product from the shared tiers above it, despite being listed on the same pricing page. It overlaps in spec (2 vCPU/4 GB) with a similarly-priced entry on Zergaw's separate Cloud Server (VPS) page (`CS02004`, ETB 5,205/month, quoted in the table below) — confirm with Zergaw sales whether "Dedicated" (shared-hosting page) and the `CS02004` cloud server (VPS page) are the same underlying product, and whether either lets you provision an openEuler image rather than their default OS template.
    docs/research/HOSTING_OPTIONS.md:27:**Rule of thumb going forward:** on any Ethiopian host's pricing page, "shared hosting" / "Server: none" / a Plesk-or-cPanel-style feature list (subdomains, email accounts, "N databases") means no root and no ability to install openGauss or run your own background services, whatever the location says. Only a line item that names CPU/RAM and says **root/dedicated IP** (VPS, cloud server, dedicated server, bare metal) is a candidate for `infra/openeuler`.
    docs/research/HOSTING_OPTIONS.md:29:## Providers that offer rentable infrastructure in Ethiopia
    docs/research/HOSTING_OPTIONS.md:35:| **Zergaw Cloud** — `zergaw.com` | HQ and infrastructure at ICT Park, Addis Ababa. Founded 2019; states government institutions among its ~500 clients. | Cloud servers and bare-metal servers (9 tiers, 1–32 vCPU / 2–64 GB RAM), shared hosting, and a **Database-as-a-Service** for PostgreSQL/MySQL/MariaDB marketed as *"Quality and secured databases at local host under Ethiopian legal framework."* | Closest self-service match: rentable VPS with root, or a managed Postgres instance, in ETB, from a company already positioning itself around local legal compliance. | Cloud servers: **ETB 3,272/month** (1 vCPU, 2 GB, 30 GB) up to **ETB 60,573/month** (32 vCPU, 64 GB). DBaaS pricing not published on the page fetched. |
    docs/research/HOSTING_OPTIONS.md:39:## What this doesn't resolve
    docs/research/HOSTING_OPTIONS.md:44:- **Actual database compatibility.** Zergaw's DBaaS lists PostgreSQL/MySQL/MariaDB, not openGauss by name. openGauss is PostgreSQL-lineage (per `CLAIMS_AUDIT.md` §4, 7j), so a managed *PostgreSQL* instance is a reasonable substitute if openGauss itself isn't offered — this feeds back into ADR-005 (backend/database choice).
    docs/research/HOSTING_OPTIONS.md:46:## Recommendation
    docs/research/HOSTING_OPTIONS.md:48:For a **budget self-service VPS or managed Postgres with an explicit local-law framing**, contact **Zergaw** first, specifically about their **Dedicated** server or `CS02004` cloud server (both root, 2 vCPU/4 GB) — not the Basic/Standard/Premium shared tiers, which don't give you a machine to install anything on regardless of their stated location. For the strongest **compliance and pitch narrative** ("hosted on Ethiopia's own national data center"), get a quote from **INSA's G-Cloud**. Check **Ethio Telecom teleCloud** too, given the Huawei Cloud Stack overlap with the rest of the competition build. Treat Raxio and UT Solutions as colocation/build options only, not as what you want for W0–W1's spike S6.
    docs/research/INFRASTRUCTURE_RECHECK.md:1:# Infrastructure documentation re-check
    docs/research/INFRASTRUCTURE_RECHECK.md:14:| INF-08 | [openEuler releases](https://www.openeuler.org/en/download/), [24.03 LTS SP3 containers](https://docs.openeuler.org/en/docs/24.03_LTS_SP3/cloud/index.html) | 24.03 LTS SP3 is a candidate with published planned EOL 2027/12. Select maintained service pack, architecture, image checksum and actual container engine/Compose versions. A generic “LTS” or “Docker compatible” description is not enough. | INFRA-01 |
    docs/research/INFRASTRUCTURE_RECHECK.md:15:| INF-09 | [openGauss psycopg SSL](https://docs.opengauss.org/en/docs/latest/developer_guide/connecting_to_the_database_using_ssl_psycopg.html), [PostgreSQL libpq SSL](https://www.postgresql.org/docs/current/libpq-ssl.html) | openGauss documents `verify-full` as unsupported; `require` encrypts without verifying server authenticity. PostgreSQL documents certificate/hostname checks for `verify-full`. This is a selection gate: prove authenticated server identity for the chosen openGauss/driver arrangement or choose PostgreSQL; never silently weaken verification. No generic SASL workaround is approved. | DB-01/02 |
    docs/research/INFRASTRUCTURE_RECHECK.md:16:| INF-10 | [FastAPI containers](https://fastapi.tiangolo.com/deployment/docker/) | Build from a pinned Python base rather than the deprecated prebuilt FastAPI image. Proposed deployment uses one API process initially, a separate durable scheduled job runner and a TLS reverse proxy with restricted trusted proxy addresses. Migration/job coordination remains the application's responsibility. | INFRA-01; API-01 |
    docs/research/INFRASTRUCTURE_RECHECK.md:22:## Proposed deployment baseline after this check
    docs/research/INFRASTRUCTURE_RECHECK.md:25:- In-country P1 and P2 use one simple API + database + job runner; annotation/training isolated from the public API. openEuler SP3 candidate, openGauss conditional on DB-01; PostgreSQL fallback explicit in ADR-005. Do not start with two pilot database engines.
    docs/research/INFRASTRUCTURE_RECHECK.md:29:## Evidence record to complete at G0/G4-U
    docs/research/CODEBASE_STRUCTURE.md:1:# Sample codebase structure — Dimts
    docs/research/CODEBASE_STRUCTURE.md:5:## What changed from §12, and why
    docs/research/CODEBASE_STRUCTURE.md:22:## Tree
    docs/research/CODEBASE_STRUCTURE.md:60:│   │   └── parity/                   ← fixtures shared with edge/android/pipeline (Kotlin twin)
    docs/research/CODEBASE_STRUCTURE.md:86:├── server/                           ← Python FastAPI; same code runs in both deployments
    docs/research/CODEBASE_STRUCTURE.md:89:│   ├── db/migrations/                ← SQL kept to the PostgreSQL/openGauss common subset
    docs/research/CODEBASE_STRUCTURE.md:109:    │   ├── compose/                  ← server, openGauss, Label Studio, backup
    docs/research/CODEBASE_STRUCTURE.md:115:## The data boundary
    docs/research/CODEBASE_STRUCTURE.md:125:## Contract changes worth making now
    docs/research/CODEBASE_STRUCTURE.md:153:## Ownership (from §15)
    docs/research/IMPLEMENTATION_REVIEW.md:1:# Architecture and implementation-plan review
    docs/research/IMPLEMENTATION_REVIEW.md:7:## Verified repository baseline
    docs/research/IMPLEMENTATION_REVIEW.md:16:## Findings and required plan changes
    docs/research/IMPLEMENTATION_REVIEW.md:24:| V5 | High | Encryption alone does not stop OS backup; starter allows backup. | Plan exclusions for sensitive files in both legacy and API 31+ cloud/transfer rules. Test backup/restore, key invalidation, reinstall and shared-phone access before real data. |
    docs/research/IMPLEMENTATION_REVIEW.md:33:## Ordered implementation gates
    docs/research/IMPLEMENTATION_REVIEW.md:43:| G4-U / G4-E — cohort readiness | Lead + Sys | G4-U authorises the first undergraduate STEM phase after participant/age eligibility, institutional consent and privacy tests. G4-E separately authorises early grades after STEM technical exit and child/school requirements. Both require in-country storage/backups, mode separation, shared-device controls and transfer/revocation/purge tests. |
    docs/research/IMPLEMENTATION_REVIEW.md:46:## Contract checklist for G1
    docs/research/IMPLEMENTATION_REVIEW.md:56:## Verification to schedule
    docs/research/IMPLEMENTATION_REVIEW.md:64:## Sources and limits
    docs/research/README.md:1:# Dimts research pack
    docs/research/README.md:20:## Verdict labels
    docs/research/README.md:33:## Access limits during the first pass (2026-09-25)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:1:# Dimts (ድምጽ) — Architecture Context Document
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:12:## 0. How to use this document
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:23:## 1. One-paragraph summary
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:29:## 2. Problem and evidence
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:31:### 2.1 The learning crisis is flat, not unmeasured
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:38:### 2.2 Teacher capability is a significant, addressable constraint
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:43:### 2.3 Pre-primary expanded faster than quality
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:49:### 2.4 Mother-tongue instruction is policy without verification
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:54:### 2.5 Why classroom *audio*, and why teacher-facing
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:60:### 2.6 Why no ASR
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:66:## 3. Users and scenarios
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:68:### 3.1 Test undergraduate STEM first
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:74:### 3.2 Intended-population users and scenarios
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:79:| **School director** | Support teachers | Optional: school-level aggregate (opt-in by teachers) | Individual teacher reports unless shared by the teacher |
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:84:### Core scenario (Deployment Mode)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:93:### Secondary scenario (Research Mode)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:98:## 4. Scope
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:100:### 4.1 In scope (competition + thesis core)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:110:### 4.2 Explicitly out of scope
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:116:### 4.3 Roadmap (pitch as roadmap, do not claim as built)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:125:## 5. Indicator specification (the core contract)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:152:## 6. Hard constraints
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:157:| C-2 | **Target device:** Android 10+ (API 29), arm64-v8a, **2 GB RAM**, 4–8 × Cortex-A53/A55 class cores, no NPU assumed, 16 GB storage, often shared. | All inference on CPU; INT8 convolution-only candidate preferred, FP16/FP32 recurrent candidate permitted only if measured budgets pass. Peak app RSS ≤ 300 MB. Model files total ≤ 20 MB. |
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:170:## 7. Huawei technology mapping
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:172:### 7.1 Principles
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:178:### 7.2 Mapping table
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:188:| Server OS | **openEuler** (24.03 LTS SP3 candidate) | In-country server OS at Hawassa University | Huawei-originated open-source Linux. |
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:189:| Database | **openGauss** | Indicator records, reports, aggregates, consent ledger | Conditional on exact-driver, migration and authenticated-TLS tests: documented openGauss verify-full limitation is a blocker unless resolved; PostgreSQL is the explicit fallback (INF-09). |
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:193:### 7.3 What to say in Q&A
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:196:### 7.4 HarmonyOS position
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:201:## 8. System architecture
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:203:### 8.1 Tiers
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:214:  FastAPI → chosen/tested database → private weekly reports
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:225:### 8.2 Key design decisions (proposed; ratify via ADR)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:231:5. **Single indicator spec, two implementations, golden tests.** Python reference on server/eval; Kotlin port on device; shared golden vectors in CI.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:234:### 8.3 Edge pipeline details
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:254:## 9. ML design
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:256:### 9.1 Tasks
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:261:### 9.2 Data sources
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:275:### 9.3 Annotation
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:282:### 9.4 Models
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:287:### 9.5 Export path
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:296:## 10. Data model and flows
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:298:### 10.1 What leaves the device (Deployment Mode)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:335:### 10.2 Server entities (selected database)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:340:### 10.3 Identity
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:345:## 11. Privacy, security, and ethics architecture
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:363:## 12. Proposed repository structure (monorepo)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:401:│   ├── db/                            ← openGauss migrations
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:414:**Backend language [DECISION: ADR-005]:** default proposal Python (FastAPI) for the server so the indicator reference implementation and aggregation share one language with the ML stack. Confirm openGauss driver compatibility in week 1.
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:418:## 13. Interfaces
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:420:### 13.1 Sync API (sovereign tier)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:430:### 13.2 Model contract (edge)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:441:## 14. Evaluation plan (definition of done)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:445:### 14.1 Model metrics (real classroom test set, split by teacher/school; frozen before training)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:455:### 14.2 Indicator validity (the metric that matters)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:463:### 14.3 Cross-language generalisation (thesis contribution)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:467:### 14.4 System metrics (target device)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:471:### 14.5 Intended early-grade pilot / usefulness (after STEM technical exit)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:477:## 15. Team and ownership
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:483:| **Person 3 (systems)** | `edge/android/`, `server/`, `dashboard/`, `infra/` | Streaming pipeline, encrypted storage, sync, server on openEuler + openGauss, dashboard |
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:490:## 16. Timeline and gates
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:498:## 17. Competition mapping
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:505:| System complexity | Edge (streaming inference) + sovereign server (openEuler/openGauss) + cloud (ModelArts) + dashboard; offline-first sync; consent ledger. |
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:510:### 17.1 Demo script (draft, 15 min)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:522:## 18. Risks and mitigations
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:538:## 19. Open decisions (ADR backlog)
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:546:| ADR-005 | Backend language & framework | Python + FastAPI |
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:562:## 20. Glossary
    docs/DIMTS_ARCHITECTURE_CONTEXT.md:578:## 21. References
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:1:# Post-implementation acceptance suites
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:7:## Execution order and reporting
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:18:## A. Build and contract suite (G0–G2)
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:28:## B. Model and streaming suite (G2, then G5-E)
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:38:## C. Device, storage and privacy suite (G4-U; repeat changed parts at G4-E)
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:46:| STORE-02 | Trigger legacy and API 31+ backup/restore/device transfer; inspect resulting archives and restored files on test devices. Reinstall and switch shared-phone accounts. | No DB/audio/credentials/identifiers in external backup/transfer; enrolment recovery explicit; other account cannot see reports. Evidence covers manufacturer-specific transfer behavior, not only manifest flags. |
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:49:## D. API, outbox and reports suite (G3/G4-U)
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:60:## E. Database, hosting and cloud suite (G0/G3/G4-U)
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:64:| DB-01 | Use selected pinned server/driver. Connect with trusted cert, then untrusted CA, expired cert and wrong hostname. Check negotiated connection; test restricted app user and blocked public port. | Valid identity succeeds; every invalid identity fails. `sslmode=require` alone is insufficient. If openGauss arrangement cannot meet this, block pilot and ratify authenticated alternative/PostgreSQL; do not downgrade checks. |
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:66:| INFRA-01 | Provision pinned supported openEuler image/container stack on disposable selected host. Restrict inbound ports, boot/reboot, kill API/job/DB container, rotate secrets/cert and fill disk. Run same scheduled report twice concurrently. | Services recover within declared objective; DB volumes durable; failed jobs retry idempotently; migrations execute once; no duplicate reports. Only intended HTTPS exposed, admin/DB private, no secrets baked into image/logs. |
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:72:## F. Field progression suite (Lead + ML + Sys)
    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md:84:## Readiness report template
    docs/research/RESEARCH_PLAN.md:1:# Research and implementation plan — Dimts (reviewed proposal, v1.2 — STEM first)
    docs/research/RESEARCH_PLAN.md:21:## 1. What the evidence now says
    docs/research/RESEARCH_PLAN.md:44:## 2. Principles
    docs/research/RESEARCH_PLAN.md:54:## 3. Workstreams
    docs/research/RESEARCH_PLAN.md:60:### WS-A: Problem evidence (Lead)
    docs/research/RESEARCH_PLAN.md:71:### WS-B: Pedagogy and indicator validity (Lead)
    docs/research/RESEARCH_PLAN.md:84:### WS-C: Prior systems and novelty (Lead + ML)
    docs/research/RESEARCH_PLAN.md:104:### WS-D: ML and data (ML)
    docs/research/RESEARCH_PLAN.md:119:### WS-E: Stack and device spikes (Sys + ML)
    docs/research/RESEARCH_PLAN.md:128:| S6 | Pin maintained openEuler SP3 candidate + chosen DB/driver; test authenticated TLS including wrong hostname/CA rejection (INF-09); no generic SASL downgrade; **host chosen from [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md)** — Zergaw VPS/DBaaS, INSA G-Cloud or Ethio Telecom teleCloud, not the on-campus box, unless the university prefers self-hosting | W1 |
    docs/research/RESEARCH_PLAN.md:131:| S9 | SQLCipher + Keystore key; backup/transfer exclusions, key-loss/reinstall and shared-phone tests before G4-U | W2 |
    docs/research/RESEARCH_PLAN.md:135:### WS-F: Legal and ethics (Lead)
    docs/research/RESEARCH_PLAN.md:154:### WS-G: Competition (Lead)
    docs/research/RESEARCH_PLAN.md:170:## 4. Decisions that need the team
    docs/research/RESEARCH_PLAN.md:185:## 5. Schedule (architecture §16 merged with the competition calendar)
    docs/research/RESEARCH_PLAN.md:204:## 6. Open questions only the team can answer
    docs/research/RESEARCH_PLAN.md:217:## 7. Search protocol (C1 and literature review)
    docs/research/RESEARCH_PLAN.md:234:## 8. Reading lists
    docs/research/RESEARCH_PLAN.md:251:★ MindSpore Lite 2.10.0 (release notes, downloads, quantization, operator list) · ★ Huawei Cloud ModelArts and Cloud Connect overviews · ★ 2026–27 Innovation Competition page · MindSpore 2.10.0 release notes · Android FGS, audio sharing, 16 KB pages, Jetpack Security notes · SQLCipher · RFC 9562 · openGauss psycopg2 · Label Studio
    README.md:1:# Echovolve (ድምጽ)
    README.md:33:## Contents
    README.md:50:## 1. The problem
    README.md:87:## 2. What Echovolve does
    README.md:112:## 3. What the teacher sees
    README.md:119:### 3.1 Why the UI looks like this
    README.md:139:### 3.2 The district dashboard
    README.md:154:## 4. The eight indicators
    README.md:173:1e-6 on shared golden vectors in CI. Classification error propagates into every one of these numbers, so
    README.md:179:## 5. Architecture
    README.md:181:![Echovolve code architecture — the Android edge tier, the in-country pilot tier, the Huawei Cloud demo tier, the data boundary between them, and indicators-core as the shared specification](docs/images/architecture.svg)
    README.md:183:### 5.1 Three tiers, and the line between two of them
    README.md:194:It is one FastAPI process, one database and one job runner, deliberately boring. Article 22 of
    README.md:214:### 5.2 Six design decisions
    README.md:220:| **Streaming, never batch, in Deployment Mode** | Recording then analysing | Raw audio exists only in a ≤ 10 s RAM ring buffer. This is a privacy guarantee and a storage guarantee at the same time, on a phone with 2 GB of RAM and 16 GB of shared storage. |
    README.md:225:### 5.3 What actually leaves the phone
    README.md:258:### 5.4 Repository map
    README.md:278:## 6. Huawei technology
    README.md:291:| Server OS | **openEuler 24.03 LTS SP3** (candidate) | In-country pilot host. |
    README.md:292:| Database | **openGauss**, PostgreSQL as the explicit fallback | Conditional on a hard gate: openGauss documents `verify-full` as unsupported, and shipping a database connection that encrypts without verifying server identity is not acceptable. If that cannot be resolved, ADR-005 selects PostgreSQL. |
    README.md:297:## 7. Privacy, ethics and Ethiopian law
    README.md:333:## 8. Status: what exists and what does not
    README.md:343:### Acceptance gates
    README.md:356:### Trial order
    README.md:368:## 9. Definition of done
    README.md:407:## 10. Roadmap
    README.md:419:## 11. Documentation
    README.md:442:## 12. Evidence and citations
    README.md:444:### 12.1 Which source decided what
    README.md:476:### 12.2 References
    README.md:567:## Team
    README.md:578:## Licence
    README.md:584:## Contributing
    ✓ • 0ms

## Assistant

The structure now separates shared hosting, a VPS fallback, research infrastructure, and the synthetic cloud demo. I’m updating the older plans to match those boundaries and adding test procedures that distinguish scaffold checks from security checks that must run on the implemented system.

## Activity

    $ cat docs/research/CODEBASE_STRUCTURE.md docs/research/IMPLEMENTATION_REVIEW.md docs/research/INFRASTRUCTURE_RECHECK.md
    # Sample codebase structure — Dimts
    
    A revision of the monorepo in architecture §12, reflecting [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) and [`ASSUMPTIONS.md`](ASSUMPTIONS.md). Reviewed 2026-09-27 against [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md). The folders are scaffolded; only `edge/android/app` is a starter Gradle module. All described product files and other modules remain proposed and require G0–G5. Prepared test harness/fixtures now live in `tests/acceptance`; see the STEM-first plan and infrastructure recheck.
    
    ## What changed from §12, and why
    
    | Change | Reason |
    |---|---|
    | **Two deployment targets:** `infra/huawei-cloud/` (competition demo) and `infra/openeuler/` (pilot) | The 2026–27 rules require Huawei Cloud; the law requires in-country storage of personal data (A-C2, A-C3, D-6). |
    | **`tools/synthetic-lessons/`** generates both training mixtures and demo lesson records | The Regional stage re-runs code and datasets, and the demo can't use pilot data (A-C3). |
    | `ml/teacher/` uses **PyTorch/HF**; `ml/student/` uses **MindSpore** | MindNLP is discontinued (A-T8, D-1). |
    | `ml/features/` is our own log-mel code, with a Kotlin twin and parity tests | MindAudio is unmaintained. Feature drift between Python and Kotlin would silently break the model. |
    | `ml/lid/` with an MMS-LID baseline | Sidama LID models and data exist (A-D3). |
    | `ml/student/` supports conv-only (INT8) and CRNN (FP16 GRU) variants | Lite has no INT8 GRU/LSTM (A-T3). |
    | `edge/android/audio/` detects mic silencing and supports an external mic | Android silences capture during calls (A-T7); desk audio may be too noisy (A-T4). |
    | `edge/android/storage/` uses SQLCipher + Keystore | Jetpack Security is deprecated (A-T11). |
    | `indicators-core/` handles **missing** spans | Silenced audio must not count as NON_SPEECH. |
    | `tools/check-elf-alignment/` in CI | The 2.10.0 AAR is 4 KB-aligned; catch it before any Play release (A-T6). |
    | `docs/competition/`, `docs/legal/` | Template 1/2 drafts; Art. 33 registration, DPIA and consent packs. |
    | Research PDFs stay in a **separate private repository** | The competition requires open-sourcing; `docs/research/papers/` holds copyrighted PDFs. |
    
    ## Tree
    
    ```
    dimts/
    ├── README.md                         ← what it is, quick start for the demo (reproducible by judges)
    ├── LICENSE                           ← open-source licence (needed for Regional shortlist); e.g. Apache-2.0
    ├── .gitignore                        ← includes .DS_Store, *.wav, *.ms, data/, .env
    ├── docs/
    │   ├── DIMTS_ARCHITECTURE_CONTEXT.md
    │   ├── adr/                          ← ADR-000 template; ADR-001…014 (014 STEM-first trial)
    │   ├── indicator-spec.md             ← mirrors §5; versioned; I-3 framed as balance indicator
    │   ├── indicator-rationale.md        ← RQ-B1 literature matrix
    │   ├── testing/                     ← post-implementation suite/runbook (prepared)
    │   ├── annotation-guide.md           ← class definitions + audio examples; missing/silenced span rule
    │   ├── ethics/                       ← consent + assent forms (amh, sid, eng), IRB approval, breach runbook (Art. 43 + 44)
    │   ├── legal/                        ← Art. 33 registration record, DPIA (Art. 47), legal-advisor Q&A (WS-F)
    │   └── competition/                  ← Template 1 draft, Template 2 slides, demo script, reproduction guide
    │
    ├── tests/acceptance/                 ← prepared runner + 18 contract vectors; implementation adapters still needed
    ├── indicators-core/                  ← single source of truth for I-1…I-8
    │   ├── python/dimts_indicators/      ← reference implementation (Python ≥ 3.10)
    │   │   ├── timeline.py               ← integer-tick intervals; MISSING metadata separate from five acoustic classes
    │   │   ├── postprocess.py            ← deterministic smoothing, timing preservation, unknown LID
    │   │   ├── indicators.py             ← I-1…I-8, spec_version
    │   │   └── calibration.py            ← E7: confusion-matrix error propagation, intervals
    │   ├── kotlin/                       ← Android port (Gradle module, pure Kotlin, no Android deps)
    │   └── golden/                       ← timelines + expected outputs; both impls must match (1e-6)
    │       ├── 001_basic.json
    │       ├── 014_call_interruption.json   ← MISSING span mid-lesson (Android silencing)
    │       └── 020_choral_drill.json        ← near-zero latencies; I-2 split by response type
    │
    ├── ml/
    │   ├── configs/                      ← YAML per experiment (E2…E7)
    │   ├── data/
    │   │   ├── DATASETS.md               ← licence, gating, register, overlap notes (E8)
    │   │   └── manifests/                ← separate STEM instructor/course and early-grade teacher/school splits; NO audio in git
    │   ├── features/
    │   │   ├── logmel.py                 ← 64-bin, 25 ms / 10 ms, streaming normalisation
    │   │   └── parity/                   ← fixtures shared with edge/android/pipeline (Kotlin twin)
    │   ├── synth/ → see tools/synthetic-lessons
    │   ├── teacher/                      ← PyTorch + Hugging Face (XLS-R / MMS / HuBERT) — server only
    │   ├── lid/                          ← MMS-LID-256 baseline (E3), student LID head
    │   ├── student/                      ← MindSpore; variants: tcn_int8/, crnn_fp16gru/, conformer_tiny/
    │   ├── distill/                      ← soft labels from teacher → student (E6)
    │   ├── export/
    │   │   ├── mindir_to_ms.sh           ← MindSpore → MindIR → converter_lite → .ms (+ PTQ full-quant)
    │   │   ├── onnx_to_ms.sh             ← escape hatch: PyTorch → ONNX → .ms
    │   │   └── model_card.py             ← writes model_card.json (feature-config hash, calib-set hash, licence of upstream models)
    │   ├── eval/                         ← frame F1, indicator MAE/ρ, cross-language, teacher-independent splits
    │   └── modelarts/                    ← job specs; AF-Johannesburg; OBS same region; PUBLIC data only
    │
    ├── edge/android/                     ← Kotlin, Jetpack Compose; minSdk 29
    │   ├── app/                          ← UI, i18n (amh, sid, eng); report view; consent screens
    │   ├── audio/
    │   │   ├── CaptureService.kt         ← foregroundServiceType="microphone", started only from UI
    │   │   ├── RingBuffer.kt             ← ≤ 10 s, RAM only
    │   │   ├── SilencingMonitor.kt       ← AudioRecordingCallback → emits MISSING spans
    │   │   └── MicSource.kt              ← built-in vs wired/USB lavalier (D-7)
    │   ├── pipeline/                     ← features (Kotlin twin of logmel.py) → seg → LID → postprocess
    │   ├── inference/                    ← MindSpore Lite 2.10.0 AAR (Java API); ONNX Runtime fallback candidate; separate size/performance gate
    │   ├── storage/                      ← Room + modern sqlcipher-android; Keystore-wrapped key, backup/transfer exclusions
    │   ├── sync/                         ← one-time WorkManager outbox + periodic recovery; ownership checks, durable ACK, payload conflicts, revocation precedence
    │   └── benchmark/                    ← RTF / RSS / battery harness (S2); wraps Lite `benchmark` tool
    │
    ├── server/                           ← Python FastAPI; same code runs in both deployments
    │   ├── api/                          ← cohort-scoped enrolment/context; /v1/lessons, /v1/reports, /v1/consent, /v1/teacher/{id}
    │   ├── jobs/                         ← weekly reports (rule table), k-anonymity aggregation, purge
    │   ├── db/migrations/                ← SQL kept to the PostgreSQL/openGauss common subset
    │   └── tests/
    │
    ├── dashboard/                        ← district view; doubles as the competition "visual demo page"
    │
    ├── tools/
    │   ├── synthetic-lessons/            ← ONE generator, two outputs:
    │   │   ├── audio_mix.py              ←   (a) training mixtures: Afrivoice/FLEURS/ALFFA + MUSAN + RIR + choral sim
    │   │   └── lesson_records.py         ←   (b) synthetic LessonRecords + timelines for the cloud demo
    │   ├── lesson-simulator/             ← web page: play a synthetic/public clip → live indicators (demo)
    │   ├── check-elf-alignment/          ← checks all native libraries, ELF/ZIP packaging; runtime test on 16 KB; pilot limited to verified devices
    │   └── demo/                         ← scripted demo accounts, seed data (synthetic only)
    │
    └── infra/
        ├── huawei-cloud/                 ← COMPETITION DEMO — synthetic/public data only
        │   ├── README.md                 ← one-command redeploy; region AF-Johannesburg
        │   ├── ecs/                      ← server + dashboard + simulator containers
        │   ├── rds/                      ← one chosen DB engine after S6; driver/migration/TLS/restore compatibility proven
        │   └── obs/                      ← public datasets + approved model artefacts (private access; bucket encryption set per service requirement)
        ├── openeuler/                    ← PILOT — personal data, Hawassa University
        │   ├── compose/                  ← server, openGauss, Label Studio, backup
        │   └── backup/                   ← encrypted, in-country
        └── ci/                           ← GitHub Actions: golden tests (py + kt), feature parity,
                                             model-contract check, ELF alignment, secret scan
    ```
    
    ## The data boundary
    
    | | `infra/huawei-cloud/` (competition) | `infra/openeuler/` (pilot) |
    |---|---|---|
    | Data | Synthetic records; corpora/models only after licence and privacy approval | LessonRecords from consenting teachers, Research Mode audio |
    | Personal data | No pilot data; separate credentials/endpoints, seed allowlists and logging controls. Public speech or mixed voices are not automatically non-personal. | Yes. Art. 22(1): stored in Ethiopia. |
    | Who writes | Deploy scripts only | Phones (sync API), annotators (Label Studio) |
    | Data path to the other tier | Approved cloud models → pilot only | None from pilot → cloud, including weights/soft labels; approved artefacts may flow cloud → pilot |
    | Reproducible by judges | Yes (README + generator) | No, and not required |
    
    ## Contract changes worth making now
    
    **`LessonRecord` v1 → v2 (§10.1), illustrative subset only:** Freeze full schema, null/availability reasons, denominators, consent reference, processing versions and validation at G1. No previous version is implemented. STEM requires a distinct context without O–3 grade, compatible model/labels, and cohort-isolated reports/aggregates (ADR-014).
    
    ```jsonc
    {
      "schema_version": 2,
      "cohort": "early_grade",
      "session_type": "early_grade_lesson",
      "label_schema_version": "early-grade-v1",
      // …all v1 fields…
      "capture": {
        "mic": "builtin|wired_lavalier|usb",   // E1 / D-7
        "observed_s": 2357.5,
        "missing_s": 42.5,                     // all lost capture/processing; excluded from every eligible denominator
        "class_size_estimate": 58              // entered by teacher; C-5
      },
      "indicators": {
        // …I1…I8…
        "I2_response_latency_s": { "choral": {"median": 0.2, "n": 30}, "single": {"median": 1.4, "n": 12} },
        "I3_choral_ratio": 0.71                // reported as a balance indicator
      },
      "model_versions": { "seg": "…", "lid": "…", "model_manifest_hash": "…" }
    }
    ```
    
    **`model_card.json`** gains `upstream_models` (with licences), `precision_by_layer` (records which layers stayed FP16 because Lite lacks INT8 GRU), and compatibility evidence for all bundled native libraries/runtime packaging. Manifest also defines shapes, class order, streaming state/lookahead and atomic compatibility checks (review G1). Upstream/derived licences belong in this manifest; using a model only as a teacher does not automatically remove downstream restrictions.
    
    ## Ownership (from §15)
    
    | Area | Owner |
    |---|---|
    | `indicators-core/python`, `golden/`, `docs/` (ethics, legal, competition, indicator-rationale) | Lead |
    | `ml/`, `tools/synthetic-lessons/audio_mix.py` | ML |
    | `edge/`, `server/`, `dashboard/`, `infra/`, `tools/check-elf-alignment`, `tools/lesson-simulator` | Sys |
    | `indicators-core/kotlin`, `ml/features/parity` | Sys + ML |
    # Architecture and implementation-plan review
    
    Reviewed 2026-09-27. **No product implementation, builds, device benchmarks or deployment performed.** Follow-up adds a future acceptance harness/fixtures and the user-directed STEM-first trial; harness self-checks are not product validation.
    
    The edge / in-country pilot / separate Huawei Cloud demo architecture is a reasonable proposal. It is not ready for unrestricted implementation: the indicator and streaming contracts need to be frozen, device feasibility remains unmeasured, and real-data collection/release has unresolved ethics and security gates. See [STEM_TRIAL_PLAN.md](STEM_TRIAL_PLAN.md), [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md) and [test suites](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) for the 2026-09-27 follow-up. This review governs unresolved implementation details; the architecture owns product constraints, RESEARCH_PLAN owns dates, and CODEBASE_STRUCTURE owns the proposed layout. Prior audits remain dated evidence, not proof that a capability was built.
    
    ## Verified repository baseline
    
    - `edge/android/settings.gradle.kts` includes only `:app`. Other Android folders contain `.gitkeep`, not Gradle modules.
    - `MainActivity.kt` is a Compose “Hello Android” starter. There is no capture, inference, indicator, storage, enrolment or sync implementation.
    - `app/build.gradle.kts` declares minSdk 29, compile/target SDK 37 and arm64-v8a. The version catalog declares AGP 9.4.1 and Kotlin 2.2.10. These are file contents, not a verified compatible build toolchain; G0 must resolve/build them before selecting upgrades or downgrades.
    - `AndroidManifest.xml` has `allowBackup=true`; the referenced backup/extraction XML contains template rules. Microphone permissions/service are absent. These are planned work, not regressions in an existing audio app.
    - ML, indicator, server, dashboard and infrastructure trees are placeholders. No executable tests beyond Android template tests establish product behavior. ADR-014 records the user-directed trial sequence; detailed schema/runtime decisions remain proposed.
    - Research PDFs are already tracked. Adding ignore rules later will not remove tracked files or history; a future public-release audit must cover the actual published tree and history. Do not delete research material as part of this review.
    
    ## Findings and required plan changes
    
    | ID | Priority | Evidence / failure mode | Planned resolution |
    |---|---|---|---|
    | V1 | High | Main architecture used MindNLP/MindAudio, all-INT8 inference and one deployment while the research pack proposes replacements and two deployments. | Reconcile §7–§12; PyTorch/HF teacher, owned features, measured precision variants, isolated cloud demo. These remain candidates until S1–S6 pass. |
    | V2 | High | Missing spans, bridged silence, zero denominators and short-segment LID inheritance could fabricate or bias indicators. | §5 v2: explicit observed/missing time, original timing for latency, null availability, unknown LID, deterministic postprocessing. Freeze all fixture outcomes before coding. |
    | V3 | High | 1–2 s windows every 0.5 s duplicate predictions unless emission ownership is defined; smoothing needs future context. | Specify each output tick's owner, bounded lookahead/finalisation, ring/queue sizes, end flush and interruption resets. Report both throughput and emission latency. |
    | V4 | High | Cloud boundary assumed public/synthetic audio and model weights were automatically non-personal; diagram implied pilot export. | Synthetic record demo by default. Public voices, derived mixtures, weights and logs need provenance/privacy/licence review. No pilot audio, features, soft labels, weights or identifiers flow to cloud. Separate endpoints, identities, credentials, logs and backups. |
    | V5 | High | Encryption alone does not stop OS backup; starter allows backup. | Plan exclusions for sensitive files in both legacy and API 31+ cloud/transfer rules. Test backup/restore, key invalidation, reinstall and shared-phone access before real data. |
    | V6 | High | UUID idempotency alone does not address payload conflicts, forged teacher IDs or withdrawal races. | Ownership derived server-side, transaction-before-ACK, identical retries only, revocation precedence, deletion of derived data and backup restore replay. |
    | V7 | High | k=5 alone does not prevent differencing or identify the intended weighting. | Count distinct teachers, aggregate each teacher first, fixed cohorts and windows, complementary suppression, no flexible drill-down; test repeated releases. Call it minimum-group suppression until disclosure review supports stronger claims. |
    | V8 | High | E1 was scheduled before ethics; E3 expects pilot data before collection; E2 asks teacher-independent evidence from 1–2 lessons. | Staged/adult fixtures before ethics; initial lessons are feasibility only. Real validation follows consent, collection and independent splits; dates move if gates slip. |
    | V9 | Medium | S3 demanded equality between differently trained graphs and quantized outputs. | Compare each export to its own source FP baseline; cross-framework tolerance only for equivalent weights/graphs. Set quantized task/indicator loss budgets separately. |
    | V10 | Medium | 16 KB check covered ELF load alignment only and treated sideloading as a fix. | Inspect all native dependencies (Lite, SQLCipher, fallback runtime), APK packaging/ZIP and runtime behavior. Sideloading is acceptable only on a tested compatible device; it cannot repair native incompatibility. |
    | V11 | Medium | Hosting alternatives and database engines were treated as interchangeable. | Choose one initial engine and test its exact driver/migrations/auth/TLS/backup/restore. PostgreSQL lineage is not compatibility evidence; choosing GaussDB adds its own spike. Host location includes backup/log processing, not just a marketing page. |
    | V12 | Medium | “Never measured”, “validated”, all-MindSpore and “language-agnostic” claims exceeded evidence. | Mark novelty, portability, performance and classroom utility as hypotheses. Report domain/language splits and abstention coverage; avoid policy-compliance or causal coaching claims. |
    
    ## Ordered implementation gates
    
    These are future acceptance criteria, not tasks executed during this review. Gates can overlap only when their prerequisites permit it.
    
    | Gate | Owner | Required evidence before proceeding |
    |---|---|---|
    | G0 — baseline and feasibility | Sys + ML | Reproducible clean Android starter build with pinned toolchain; measured target phone/OS/page size; S1 Lite invocation; S4 training hardware; S6 exact DB driver/migration path. Decide failures through ADRs instead of silently swapping stacks. |
    | G1 — contracts | Lead + Sys + ML | ADR-001/002/004/005/011/012 candidate decisions; full schema v2 and model manifest; exact timeline/postprocess/quantile semantics; proposed Python/Kotlin golden cases reviewed with expected results; feature config and chunk ownership frozen. ADR-002 remains provisional until E5. |
    | G2 — offline vertical slice | Sys + ML | Authorised synthetic/staged PCM → features → candidate inference → indicators → encrypted lesson record → local report on target phone. Bounded queues and a 40 min soak; process death produces an explicit interrupted session with no invented unobserved audio. No cloud or real classroom data prerequisite. |
    | G3 — reproducible competition demo | Sys + Lead | Synthetic fixtures → authenticated ingest → one dashboard/report view on isolated Huawei Cloud deployment; licence manifest, seeded generator, documented model versus fixture outputs and independent reproduction. Public-audio playback is optional and separately reviewed. |
    | G4-U / G4-E — cohort readiness | Lead + Sys | G4-U authorises the first undergraduate STEM phase after participant/age eligibility, institutional consent and privacy tests. G4-E separately authorises early grades after STEM technical exit and child/school requirements. Both require in-country storage/backups, mode separation, shared-device controls and transfer/revocation/purge tests. |
    | G5-E — early-grade validity and controlled release | Lead + ML + Sys | Teacher/school-independent frozen real test set, annotation agreement, indicator error with intervals/coverage, quantized-device equivalence, coaching co-design, aggregate disclosure tests. Release only indicators that pass; invalid/unavailable outputs are null with reasons. |
    
    ## Contract checklist for G1
    
    - Fixture cases: empty/zero-duration input, all silence, all missing, all overlap, interrupted teacher/child transition, 0.25/0.3/0.5/10/60 s boundaries, smoothing ties, final partial minute, mixed-language turn, LID abstention, dropped chunks and end flush. Validate interval ordering, complete coverage and finite values.
    - Rates use observed time; report elapsed time separately. Specify count-ratio qualification and quantile interpolation explicitly. Reports must preserve missing/unknown coverage rather than treating it as zero activity.
    - Teacher role is an acoustic hypothesis: define other adults, playback speech, quiet children and teacher-plus-child overlap in the annotation guide. Pilot-gate each class. A merged CHILD_UNSPECIFIED fallback requires new class/spec versions and disables I-3/I-4 and response-type-specific latency.
    - `LessonRecord` includes cohort/session context, supported-cohort label schema, schema/spec/model/feature/postprocess versions, immutable ID, consent/enrolment reference, elapsed/observed/missing time, indicator availability/reasons and denominators. No full timeline leaves Deployment Mode. Set strict field allowlists, ranges, size limits and supported versions; examples in the other plans are incomplete, not executable schemas.
    - Weekly offline reports need the same versioned deterministic rule table as server reports. Define week boundaries in Africa/Addis_Ababa, equal teacher weighting for district outputs, compatible-version grouping, minimum valid duration/lessons and when cached/server reports supersede local reports. G1 must fix thresholds; early-grade coaching stays disabled until G5-E; STEM remains descriptive.
    - Model manifest pins sample rate, resampler, frame centering/padding, FFT/window/mel/log conventions, streaming normalisation, tensor axes/dtypes/class order, quantization scales, receptive field/lookahead, chunk/state-reset behavior and hashes. Calibration and feature parity cover warm-up and chunk boundaries.
    - Partition real data before adaptation, pseudo-labelling, distillation, calibration or threshold tuning. Public corpus overlap also needs speaker/source checks. Small pilot splits may not support school-level inference: report that limit rather than claim independence.
    
    ## Verification to schedule
    
    - Sync/auth: other-teacher read/delete/write denial; wrong school; duplicate same bytes/semantics; conflicting same ID; lost ACK; partial batch; offline retry; expiry; withdrawal during upload; restore after deletion. Never log record bodies/tokens.
    - Capture/storage: permission denial/revocation, call/mic silencing, external-mic removal, background restrictions, screen lock, thermal throttling, low storage, crash/restart, key loss and backup/transfer. Test API 29 and a modern target-compatible device. Benchmark release builds with the whole pipeline.
    - Aggregates: 4 versus 5 distinct teachers, many lessons from one teacher, adjacent/overlapping cohorts, suppression at totals, withdrawal and repeated weekly releases. No district release if only six pilot teachers make useful safe grouping impossible; use synthetic demo aggregates.
    - Research transfer: separate consent and endpoint, ciphertext integrity and durable in-country receipt before local deletion; retained audio TTL/storage cap and failure/retry behavior. No Research Mode secrets or audio in deployment logs/CI.
    - Public release: dataset/model licensing includes derived student weights, attribution and redistribution; CC-BY-NC is not blanket competition permission. Re-run from licensed synthetic fixtures without pilot data. Audit tracked PDFs/history and secrets before publishing.
    
    ## Sources and limits
    
    Repository findings above were inspected directly. Existing research audit findings are carried forward with their original dates; the follow-up infrastructure recheck opens current official technical docs but does not revalidate every paper, competition rule, provider price or legal interpretation. No runtime/model accuracy claim is verified.
    
    Official Android documentation checked on 2026-09-27:
    
    - [Sharing audio input](https://developer.android.com/media/platform/sharing-audio-input): capture can be silenced and recording callbacks expose changes; plan missingness separately from acoustic silence.
    - [Auto Backup](https://developer.android.com/identity/data/autobackup): eligible app data is backed up by default; Android 12+ uses separate cloud-backup/device-transfer controls. Test explicit exclusions, including restore behavior.
    - [16 KB page sizes](https://developer.android.com/guide/practices/page-sizes): native libraries and APK packaging need checks and runtime testing. All bundled native dependencies are in scope, not just the inference AAR.
    
    Outstanding legal decisions stay in WS-F; outstanding current competition requirements stay in WS-G. Neither is resolved merely by choosing an architecture.
    # Infrastructure documentation re-check
    
    Checked official documentation on 2026-09-27. These are documentation findings and proposed choices, not successful installs or benchmarks. Final host, training hardware, regional capacity and credentials have not been supplied. Applies equally to undergraduate STEM and later early-grade data.
    
    | ID | Component / source | Confirmed documentation and implication | Required post-implementation case |
    |---|---|---|---|
    | INF-01 | [AGP 9.4 compatibility](https://developer.android.com/build/releases/agp-9-4-0-release-notes) | AGP 9.4 lists Gradle 9.6.0, API ≤37 and minimum JDK 17. Repo uses Gradle 9.6.0, API 37, daemon JDK 25 and AGP 9.4.1. Family requirements match; this page does not establish the exact patch/plugin/JDK combination. Resolve and build exact pins before changes. Java source target 11 is separate from Gradle's runtime JDK. | BUILD-01 |
    | INF-02 | [Lite 2.10.0 downloads](https://www.mindspore.cn/lite/docs/en/r2.10.0/use/downloads.html) | Android-aarch64 package contains AAR/runtime/benchmark; cloud-side and device-side Linux converters are distinct downloads. Pin the device-side package and published SHA-256 with matching converter. Package availability is not model compatibility. | MODEL-01 |
    | INF-03 | [Lite operator table](https://www.mindspore.cn/lite/docs/en/r2.10.0/reference/operator_list_lite.html), [PTQ](https://www.mindspore.cn/lite/docs/en/r2.10.0/advanced/quantization.html) | CPU GRU/LSTM list FP16/FP32, not INT8. Keep conv-only INT8 and mixed-precision recurrent candidates; prove full exported graph support and calibration behavior. | MODEL-01/02 |
    | INF-04 | [MindSpore 2.10 release](https://www.mindspore.cn/docs/en/r2.10.0/RELEASE.html) | Release notes do not prove a usable training stack on the team's unidentified hardware. Capture OS/Python/accelerator/driver/CANN compatibility and a forward/backward/save/reload spike. PyTorch teacher and MindSpore student stay conditional. | MODEL-03 |
    | INF-05 | [Microphone foreground service](https://developer.android.com/develop/background-work/services/fgs/service-types#microphone), [audio input sharing](https://developer.android.com/media/platform/sharing-audio-input) | Plan runtime RECORD_AUDIO, foreground-service declarations/permissions appropriate to target SDK, visible-UI start and recording callback handling. Capture can be silenced; callbacks plus frame continuity determine missingness. | DEVICE-01/02 |
    | INF-06 | [WorkManager work requests](https://developer.android.com/develop/background-work/background-tasks/persistent/getting-started/define-work) | Periodic work has a 15-minute minimum and timing depends on constraints/system scheduling. Use unique network-constrained one-time work when an outbox record is ready, plus periodic recovery. Do not promise immediate sync or run live capture in WorkManager. | SYNC-01/02 |
    | INF-07 | [SQLCipher migration](https://www.zetetic.net/sqlcipher/sqlcipher-for-android-migration/), [Auto Backup](https://developer.android.com/identity/data/autobackup), [16 KB support](https://developer.android.com/guide/practices/page-sizes) | Use `net.zetetic:sqlcipher-android` with `SupportOpenHelperFactory` for Room, not the legacy library/API. Pin a tested Room/SQLite/SQLCipher set. SQLCipher and Lite both need native/packaging/runtime checks; encryption does not replace backup exclusions and key lifecycle tests. | STORE-01/02; BUILD-02 |
    | INF-08 | [openEuler releases](https://www.openeuler.org/en/download/), [24.03 LTS SP3 containers](https://docs.openeuler.org/en/docs/24.03_LTS_SP3/cloud/index.html) | 24.03 LTS SP3 is a candidate with published planned EOL 2027/12. Select maintained service pack, architecture, image checksum and actual container engine/Compose versions. A generic “LTS” or “Docker compatible” description is not enough. | INFRA-01 |
    | INF-09 | [openGauss psycopg SSL](https://docs.opengauss.org/en/docs/latest/developer_guide/connecting_to_the_database_using_ssl_psycopg.html), [PostgreSQL libpq SSL](https://www.postgresql.org/docs/current/libpq-ssl.html) | openGauss documents `verify-full` as unsupported; `require` encrypts without verifying server authenticity. PostgreSQL documents certificate/hostname checks for `verify-full`. This is a selection gate: prove authenticated server identity for the chosen openGauss/driver arrangement or choose PostgreSQL; never silently weaken verification. No generic SASL workaround is approved. | DB-01/02 |
    | INF-10 | [FastAPI containers](https://fastapi.tiangolo.com/deployment/docker/) | Build from a pinned Python base rather than the deprecated prebuilt FastAPI image. Proposed deployment uses one API process initially, a separate durable scheduled job runner and a TLS reverse proxy with restricted trusted proxy addresses. Migration/job coordination remains the application's responsibility. | INFRA-01; API-01 |
    | INF-11 | [Huawei RDS PostgreSQL + ECS](https://support.huaweicloud.com/intl/en-us/qs-rds-pg/rds_02_0016.html) | Vendor path uses ECS/RDS in the same region/VPC and controlled security groups with SSL. Demo default: ECS + RDS PostgreSQL, private DB connectivity, least-privilege user. GaussDB is a separate alternative requiring its own compatibility evidence, not a drop-in alias. AF-Johannesburg availability/quota still needs an account-level check. | CLOUD-01; DB-01 |
    | INF-12 | [ModelArts constraints](https://support.huaweicloud.com/intl/en-us/productdesc-modelarts/modelarts_01_0062.html) | Standard ModelArts requires same-region OBS and does not support encrypted OBS buckets. Restrict a dedicated private-access bucket to separately approved inputs/outputs; never place either classroom cohort there. This is service-specific, not a reason to disable encryption on all buckets. | CLOUD-02 |
    | INF-13 | [Label Studio security](https://labelstud.io/guide/security) | Official guidance includes internal-network isolation and production SSRF protection (`SSRF_PROTECTION_ENABLED=true`). Verify authentication, media URLs, outbound telemetry and exact edition capabilities; do not assume enterprise RBAC/audit features in Community. Annotation stays on the in-country private tier. | ANNOT-01 |
    | INF-14 | [Zergaw Cloud Server](https://zergaw.com/cloud-server/) | Provider publishes a cloud-server offering, but this does not establish purchased-host suitability, permitted OS image, accelerator, backups, access terms or data location for every service. Existing hosting list remains a shortlist. Obtain exact configuration/location/backup terms and a restore test. No provider purchase or price revalidation performed. | INFRA-01/02 |
    
    ## Proposed deployment baseline after this check
    
    - Android minSdk 29 / arm64 remains. Retain existing toolchain files pending BUILD-01; this review changes no dependencies. Validate actual target-SDK behavior on modern Android and resource constraints on the reference 2 GB phone.
    - In-country P1 and P2 use one simple API + database + job runner; annotation/training isolated from the public API. openEuler SP3 candidate, openGauss conditional on DB-01; PostgreSQL fallback explicit in ADR-005. Do not start with two pilot database engines.
    - Competition demo is a distinct ECS/RDS PostgreSQL deployment using synthetic records. ModelArts/OBS training is a separate optional capability; it is not required to unblock undergraduate capture tests. Record exact region/service quota before booking capacity.
    - DB listener and annotation UI are private. Only HTTPS API/demo routes are public. Keep service secrets out of images, source and logs; test certificate rotation, backups and restart recovery. Pilot backups, observability and model artefacts remain in-country for both cohorts.
    
    ## Evidence record to complete at G0/G4-U
    
    Record selected host/location, OS image/version/hash, container runtime/Compose, Python/API/driver/database versions, region/VPC and service availability, CA/trust configuration, encrypted-backup location/retention, restore evidence, Android dependency lock/checksums, model/converter/runtime hashes and device OS/RAM/page size. Retention, recovery-time and recovery-point objectives must be assigned numerical values by Lead/Sys before tests can pass. Record unresolved items as blocked, not verified.
    
    Scope limits: no cloud account inspection, provider contract, download/binary inspection, build, training, device operation or data transfer was performed. Previous 4 KB AAR binary finding remains dated prior evidence; verify the newly chosen binary. No new legal interpretation is made here.
    ✓ • 0ms

## Activity

    $ sed -n '170,235p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; sed -n '340,418p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; cat docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md
    ## 7. Huawei technology mapping
    
    ### 7.1 Principles
    Current official-source findings and test links are in [INFRASTRUCTURE_RECHECK.md](research/INFRASTRUCTURE_RECHECK.md). Exact versions, hardware and regional service availability remain acceptance gates.
    - Use Huawei technology where it is **load-bearing**, not decorative. Judges probe in Q&A.
    - Keep an **escape hatch** (ONNX) for every model so a framework bug cannot sink the timeline.
    - The deployment-critical path (phone + in-country server) must not depend on a foreign cloud region (C-7).
    
    ### 7.2 Mapping table
    
    | Layer | Huawei technology | Role in Dimts | Why / caveat |
    |---|---|---|---|
    | On-device inference | **MindSpore Lite** (2.x; Android-aarch64 AAR; current release listed as 2.10.0 [VERIFY at build time]) | Runs segmentation + LID models, INT8 | Supports Android, post-training INT8 quantization, and converts MindIR/ONNX/TFLite to `.ms`. Built into HarmonyOS, so the same model ports to R-5. |
    | Model training | **MindSpore** 2.x | Teacher-model fine-tuning, student training, distillation | Primary framework. |
    | Audio toolkit | **Owned feature extraction** (Python + Kotlin) | Versioned log-mel extraction with streaming parity fixtures | Do not depend on MindAudio maintenance; research audit recommends owned feature code. |
    | Pretrained speech encoders | **PyTorch + Hugging Face** | Load multilingual self-supervised encoders (wav2vec2 / XLS-R / HuBERT family) as the server-side teacher model | Proposed replacement for MindNLP per audit; verify model licence and target training hardware in S4. |
    | Cloud training | **Huawei Cloud ModelArts** (Ascend) | Pre-training / fine-tuning on **public** speech data; pipeline demo; public-data-only experiments | AF-Johannesburg is a candidate, subject to S5 service availability. No pilot audio, posteriors, features, logs or classroom-trained weights may flow to this tier under the proposed boundary. |
    | Object storage (cloud) | **Huawei Cloud OBS** | Public datasets, model artefacts, experiment logs | No personal data. |
    | Server OS | **openEuler** (24.03 LTS SP3 candidate) | In-country server OS at Hawassa University | Huawei-originated open-source Linux. |
    | Database | **openGauss** | Indicator records, reports, aggregates, consent ledger | Conditional on exact-driver, migration and authenticated-TLS tests: documented openGauss verify-full limitation is a blocker unless resolved; PostgreSQL is the explicit fallback (INF-09). |
    | CI/CD (optional) | Huawei Cloud **CodeArts** | Build/test pipelines | Optional; GitHub Actions acceptable if friction is high. |
    | Client OS (stretch) | **HarmonyOS / OpenHarmony**, ArkTS | Native client port (R-5) | Target teachers overwhelmingly use Android phones (Tecno, Itel, Samsung). HarmonyOS NEXT does not run Android apps, so it cannot be the primary client. Frame the port as "same `.ms` model, second OS." |
    
    ### 7.3 What to say in Q&A
    Describe demonstrated components only. Proposed: PyTorch/HF teacher, MindSpore student where hardware supports it, Lite CPU inference. The competition demo uses synthetic records on Huawei Cloud; the pilot uses a separate in-country deployment. Model portability, runtime precision and cross-language accuracy are experiments, not completed claims.
    
    ### 7.4 HarmonyOS position
    Primary client is Android because that is what teachers own. The HarmonyOS client is a roadmap item unless the team has spare capacity after the national stage. Do not let it block the critical path.
    
    ---
    
    ## 8. System architecture
    
    ### 8.1 Tiers
    
    ```text
    ANDROID (offline pilot client)
      Mic → bounded RAM capture → owned features → Lite inference
          → observed/MISSING timeline + language/unknown → Kotlin indicators
          → encrypted local record + local report → authenticated outbox
      Research Mode: separate consent → encrypted audio → in-country transfer
                        │ derived LessonRecords only in Deployment Mode
                        ▼
    IN-COUNTRY PILOT (host selected through S6)
      FastAPI → chosen/tested database → private weekly reports
              → fixed-cohort, disclosure-reviewed district aggregates
      Separate enrolment, consent/revocation, audit, encrypted backups
      Research audio/annotation and classroom training stay in-country
    
    HUAWEI CLOUD DEMO (isolated endpoint, credentials, DB, logs and backups)
      Synthetic fixture generator → same API/report code → visual demo page
      Approved public-corpus/model experiments → ModelArts/OBS, subject to S5
      Approved cloud artefacts may enter pilot; NO pilot-derived export
    ```
    
    ### 8.2 Key design decisions (proposed; ratify via ADR)
    
    1. **Frame classification, not clustering diarization.** The task is a fixed 5-class problem, not "who spoke when" with unknown N. This is simpler, trainable, and real-time.
    2. **Teacher–student distillation.** A large multilingual self-supervised encoder (server-side "teacher model", PyTorch/HF) is fine-tuned on annotated classroom audio and used to produce soft labels on unannotated audio. A tiny on-device "student" (CRNN or small conformer on log-mel) is trained on those soft labels. Whether this reaches useful accuracy on a 2 GB phone is tested in E6. It is also the main technical-complexity story for judges.
    3. **Streaming, not batch, in Deployment Mode.** Raw audio exists only in a ≤ 10 s RAM ring buffer. Nothing is re-analysable after the lesson. This is a privacy guarantee and a storage guarantee at once.
    4. **Append-only sync, no CRDTs.** Each device produces immutable lesson records with client-generated UUIDv7 IDs. No record is ever edited on two devices, so no conflict resolution is needed. Withdrawal is a separate revocation and purge workflow that takes precedence over queued uploads (§13.1).
    5. **Single indicator spec, two implementations, golden tests.** Python reference on server/eval; Kotlin port on device; shared golden vectors in CI.
    6. **Rule-based coaching, not model-based.** The weekly suggestion is a transparent lookup table from indicator ranges to one suggestion. Explainable to teachers, directors, and judges.
    
    ### 8.3 Edge pipeline details
    
    ### 10.3 Identity
    - Teacher enrols with a code issued by the research team; server returns an opaque `teacher_pseudo_id`. Real names stored only in a separate, access-restricted enrolment table (or on paper) [DECISION: ADR-007].
    
    ---
    
    ## 11. Privacy, security, and ethics architecture
    
    | Concern | Control |
    |---|---|
    | Raw audio exposure | Deployment Mode: RAM ring buffer only, never persisted. Research Mode: per-lesson AES key, file encrypted at rest, deleted from phone after verified transfer. |
    | Voice biometrics | No speaker embeddings persisted anywhere. Models output class posteriors only. |
    | Child data | No child-level features. CHILD_* are acoustic classes, not individuals. |
    | Data localisation (C-7) | All personal data (records, research audio, enrolment) on in-country server. Huawei Cloud demo holds synthetic records; any public speech/model asset requires separate licence and privacy approval. No pilot-derived export, including weights or soft labels. |
    | Re-identification via dashboard | Minimum group size 5 distinct teachers, not lessons; fixed cohorts, equal teacher weighting, no arbitrary filters, and complementary suppression/differencing tests before district release. This alone is not proof of anonymity. |
    | Teacher misuse by management | Individual reports visible only to the teacher. Sharing with director is teacher-initiated, revocable. Written into consent form and product copy. |
    | Transport | TLS 1.2+, certificate pinning in app. |
    | At rest | Device: SQLCipher + Keystore; exclude research files, databases, identifiers and credentials from cloud backup/device transfer; test API 29 and API 31+ rules. Server: disk encryption + encrypted backups in-country. |
    | Consent | Per-teacher consent recorded in app and in server ledger; separate opt-in for Research Mode; withdrawal deletes server records (tombstone + purge job). Child consent/assent and school permissions per legal/ethics review; no classroom capture before approval. Offline withdrawal stops capture and discards queued lessons; server revocation rejects stale retries. |
    | Breach | 72-hour notification runbook (per C-7) in `docs/ethics/breach-runbook.md`. |
    | Approvals | Separate G4-U undergraduate recruitment/consent and G4-E early-grade ethics/permissions **before recording each cohort**. Do not assume all undergraduates are adults. No participation tied to grades; practical decline alternative. |
    
    ---
    
    ## 12. Proposed repository structure (monorepo)
    
    ```
    dimts/
    ├── README.md
    ├── docs/
    │   ├── DIMTS_ARCHITECTURE_CONTEXT.md        ← this file
    │   ├── adr/                           ← ADR-000 template, ADR-001..n
    │   ├── annotation-guide.md
    │   ├── indicator-spec.md              ← mirrors §5, versioned
    │   └── ethics/                        ← consent forms (amh, sid, eng), approval, breach runbook
    ├── indicators-core/
    │   ├── python/                        ← reference implementation (package: dimts_indicators)
    │   ├── kotlin/                        ← Android port (Gradle module)
    │   └── golden/                        ← JSON timelines + expected outputs; CI runs both impls
    ├── ml/
    │   ├── configs/                       ← experiment configs (YAML)
    │   ├── data/                          ← manifests, splits, NO audio in git
    │   ├── features/                      ← owned log-mel, augmentation, Python/Kotlin parity
    │   ├── synth/                         ← synthetic classroom mixture generator (D-6)
    │   ├── teacher/                       ← SSL encoder fine-tuning (PyTorch/HF, in-country for pilot data)
    │   ├── student/                       ← CRNN / compact conformer
    │   ├── distill/                       ← soft-label generation, KD training
    │   ├── export/                        ← MindIR → .ms, INT8 PTQ, ONNX fallback
    │   ├── eval/                          ← frame F1, indicator error, cross-language, latency
    │   └── modelarts/                     ← job definitions for Huawei Cloud
    ├── edge/
    │   └── android/
    │       ├── app/                       ← UI (Jetpack Compose), i18n: amh, sid, eng
    │       ├── audio/                     ← capture service, ring buffer
    │       ├── inference/                 ← MindSpore Lite wrapper (Java API or JNI)
    │       ├── pipeline/                  ← features → seg → LID → post-process
    │       ├── storage/                   ← encrypted Room/SQLCipher
    │       ├── sync/                      ← outbox, WorkManager, API client
    │       └── benchmark/                 ← on-device RTF / RSS / battery harness
    ├── server/
    │   ├── api/                           ← ingest, reports, dashboard API
    │   ├── jobs/                          ← weekly reports, aggregation, purge
    │   ├── db/                            ← openGauss migrations
    │   └── tests/
    ├── dashboard/                         ← district web UI
    ├── harmony/                           ← (stretch) ArkTS client, R-5
    ├── infra/
    │   ├── huawei-cloud/                  ← synthetic-only competition demo, separate credentials
    │   ├── openeuler/                     ← in-country pilot host, containers and backups
    │   └── ci/                            ← pipelines (CodeArts or GitHub Actions)
    └── tools/
        ├── lesson-simulator/              ← replays recorded/synthetic lessons into the pipeline
        └── demo/                          ← competition demo scripts and fixtures
    ```
    
    **Backend language [DECISION: ADR-005]:** default proposal Python (FastAPI) for the server so the indicator reference implementation and aggregation share one language with the ML stack. Confirm openGauss driver compatibility in week 1.
    
    ---
    
    ## 13. Interfaces
    # Post-implementation acceptance suites
    
    Prepared 2026-09-27. Status of **every product case: NOT RUN**. This document specifies executable procedures/oracles for later runs. The [contract runner](../../tests/acceptance/README.md) is ready to connect to future adapters; hardware, service and field cases below require implementation and a provisioned test environment. No test result is claimed from this plan.
    
    Related: [STEM trial](../research/STEM_TRIAL_PLAN.md), [infrastructure evidence INF-01–14](../research/INFRASTRUCTURE_RECHECK.md), [G0–G5](../research/IMPLEMENTATION_REVIEW.md).
    
    ## Execution order and reporting
    
    1. BUILD, CORE/COHORT, MODEL, API/SYNC and disposable infrastructure tests on synthetic fixtures.
    2. DEVICE/STORE tests on reference devices; CLOUD/ANNOT and backup/restore negative cases. Resolve all blocking failures before G4-U.
    3. STEM-01–04 after G4-U. Report technical feasibility separately from adult-model exploratory metrics.
    4. EARLY-01–03 after STEM exit and G4-E. No early-grade release before G5-E.
    
    Default owners: Sys (build/device/storage/API/sync/infra/cloud/database), ML (models/features), Lead (cohorts/ethics/field validity); Lead + Sys own disclosure/withdrawal cases. Each row is blocking for its applicable gate unless explicitly exploratory. A blocked prerequisite, unsupported required feature or skipped case is **not a pass**. Changes to thresholds need an ADR dated before the held-out run.
    
    Use isolated, disposable test accounts/databases/devices with generated non-personal records. Never run purge, fault injection or restore cases against real pilot data. The test environment must identify itself as non-production before destructive cases begin. Do not expose debug/admin reset endpoints in release builds. Preserve evidence privately with: case ID, git/build/adapter SHA, OS/device, pinned dependencies, model/feature/spec/label versions, cohort, test-fixture hash/seed, UTC start/end, steps executed, actual result, pass/fail/blocked, redacted evidence path, defect and rerun ID. Logs must omit tokens, raw classroom content and participant identities.
    
    ## A. Build and contract suite (G0–G2)
    
    | ID | Preconditions and steps | Expected result / evidence |
    |---|---|---|
    | BUILD-01 | Clean checkout/cache on documented CI image. Resolve exact AGP/Gradle/JDK/Compose pins; assemble debug and release, run lint/unit tests and install release on API 29 arm64 plus a modern target-compatible device. | Reproducible successful builds/install; dependency manifest and checksums. Template greeting tests alone do not satisfy acceptance. Record actual JDK rather than confuse source target 11 with daemon runtime. |
    | BUILD-02 | Extract release APK/AAB-derived APKs; inspect every bundled native library, ELF load/RELRO alignment and ZIP packaging. Install/run on 4 KB and 16 KB environments. Exercise Lite and SQLCipher, not just launch activity. | No native loader/crash failures on supported configurations. Capture `adb shell getconf PAGE_SIZE`, official alignment-check and `zipalign -v -c -P 16 4` outputs. Incompatible device support cannot be claimed merely because sideload succeeded elsewhere. |
    | CORE-01–12 | Run supplied vectors against actual Python and Kotlin adapters independently. | Every oracle passes; null/missing/overlap/turn/latency/language/time-bin behavior matches. Preserve both outputs. Counts exact, floats abs ≤1e-6. |
    | CORE-13 | After G1 freezes postprocessing, add fixtures at 0.24/0.25/0.26 s gaps, 0.29/0.30/0.31 s segments, 0.49/0.50/0.51 s responses; ties, quantile interpolation, zero duration, invalid overlap/order/NaN, end flush and chunk-boundary splits. | Reviewed expected outputs before implementation adaptation; original silence/latency preserved; missing intervals never bridged. This case remains blocked until G1 oracles are frozen; existing 12 vectors do not cover it. |
    | COHORT-01–06 | Run provided context/manifest/profile vectors; then submit equivalent valid/invalid sessions through UI and API. | Adult sessions cannot masquerade as O-class, unknown cohorts fail, unsupported child model is rejected for STEM, valid early-grade schema preserved. API must enforce enrolment cohort independent of client input. |
    
    ## B. Model and streaming suite (G2, then G5-E)
    
    | ID | Preconditions and steps | Expected result / evidence |
    |---|---|---|
    | MODEL-01 | Hash-check device-side runtime/converter; export each chosen model; run deterministic public/staged features in source framework and Lite; verify shapes/class order and corrupt a manifest/hash. | Source/export comparison meets predeclared per-model tolerance (initial FP abs 1e-4); incompatible/corrupt bundle rejected atomically. Record per-layer precision and unsupported ops; no silent unsupported cohort mapping. |
    | MODEL-02 | Split calibration/development/test manifests by speaker/session before PTQ. Run FP and quantized candidates on the same locked set and device. | Report quantization macro-F1 gap (target ≤0.02) and indicator errors; no test-set calibration. Report failing or absent classes explicitly. P1 metrics never satisfy child-class target. |
    | MODEL-03 | On actual in-country training machine: record OS/Python/framework/accelerator/driver; train a toy forward/backward step, save/reload and export. | Finite loss/gradients, reload agreement and device import; written framework fallback if unsupported. No classroom artefacts leave the host. |
    | STREAM-01 | Feed the same authorised PCM in fixed and irregular chunks, including resampling/warm-up/end flush; compare Python/Kotlin features and emitted labels. Drop a chunk, inject silencing and overflow, then resume. | Feature parity within G1's frozen tolerance; every timeline tick emitted once or MISSING, bounded lookahead/backlog; state resets at missing spans; no duplicate processing counts. Save timestamp traces. |
    | STREAM-02 | Replay a STEM session using early-grade-only manifest; then use a compatible adult model or human-coded timeline in explicitly labelled analysis mode. Include another adult speaker, playback and simultaneous lab groups. | Incompatible automated role results unavailable; no age/pitch-based role assumption. Human-coded arithmetic distinguished from model inference. Group conversation not automatically choral response. |
    
    ## C. Device, storage and privacy suite (G4-U; repeat changed parts at G4-E)
    
    | ID | Preconditions and steps | Expected result / evidence |
    |---|---|---|
    | DEVICE-01 | Grant/deny/revoke microphone permission; start via visible UI, lock screen, rotate UI, background app, attempt unsupported background start; end session. | Legal foreground start with visible notification; denial has usable UI; no crash or hidden capture. End stops mic/wake lock. Unsupported start fails cleanly. |
    | DEVICE-02 | During staged audio, take call/competing-mic event, remove external mic, suspend capture and terminate/restart process. | Detected silencing/drop intervals are MISSING, not silence. No invented continuity. Recovery marks interrupted session; completed encrypted records preserved. Permission/restart behavior recorded per OS. |
    | DEVICE-03 | Release-build 40-minute soak on 2 GB arm64 reference phone with active features/models/encryption; measure processing time, RSS, queue age, temperature and battery. Repeat longest scheduled STEM session. | RTF ≤0.3, peak RSS ≤300 MB, ≤10 percentage-point battery loss/40 min under documented conditions; bounded queue; no unaccounted spans. Record p95 latency and predeclared G1 latency budget. Extended run completes without unbounded growth; report actual battery. |
    | STORE-01 | Create synthetic record/research file; inspect DB/WAL/temp/cache; attempt ordinary SQLite open and wrong-key SQLCipher open; invalidate wrapped key; simulate full storage. | No recoverable plaintext sensitive fields/audio; correct key opens DB; invalid key fails safely with recovery path, no silent discard of acknowledged records. Full storage stops safely and surfaces failure. |
    | STORE-02 | Trigger legacy and API 31+ backup/restore/device transfer; inspect resulting archives and restored files on test devices. Reinstall and switch shared-phone accounts. | No DB/audio/credentials/identifiers in external backup/transfer; enrolment recovery explicit; other account cannot see reports. Evidence covers manufacturer-specific transfer behavior, not only manifest flags. |
    | PRIVACY-01 | In Deployment Mode, stream staged audio and inspect filesystem/network/logs/crash output. Repeat in Research Mode with separate consent disabled/enabled. | Deployment writes/uploads no raw audio/features/embeddings/timeline; research path only enabled by separate approved consent. Research transfer deletes local encrypted file only after integrity and durable-receipt verification. |
    
    ## D. API, outbox and reports suite (G3/G4-U)
    
    | ID | Preconditions and steps | Expected result / evidence |
    |---|---|---|
    | API-01 | Use two teachers, two schools and both cohorts. Try unauthenticated/expired tokens, forged teacher/school/cohort fields, cross-user report GET/DELETE and oversized/unsupported-schema/nonfinite payloads. Spoof forwarded headers through direct backend access. | Server-side scope rejection, no cross-user mutation/disclosure; field allowlist/size limits enforced, secrets absent in logs; proxy trust restricted. No reliance on user-supplied teacher IDs. |
    | SYNC-01 | POST record; repeat same canonical payload; reuse ID with changed payload; drop response after commit; submit mixed valid/rejected batch; restart DB/API around commit. | Accepted only after durable commit; same record duplicate, changed payload conflict; one stored record per owner/ID. Retry only transient failures; rejected record reason visible; no lost/false ACK. |
    | SYNC-02 | Queue valid synthetic records for 72 hours offline with app/process restarts and random disconnects; restore network and permit worker execution. Test one-time work and periodic recovery independently. | All non-withdrawn valid records eventually ACK once; local data available throughout. Do not require a periodic worker to run on an exact minute. Record eventual-delivery latency and scheduler state. |
    | SYNC-03 | Withdraw offline while records queued, reconnect; race upload with server withdrawal; retry using second enrolled device and old token. Restore pre-withdrawal backup in disposable environment. | Capture/upload stops; all devices revoked server-side; stale data never resurrected; derived reports/aggregates removed/recomputed; restore replays deletion records before serving. Retained ledger limited by policy. |
    | REPORT-01 | Generate same weekly report offline/server from identical versioned inputs; cross week boundary in Africa/Addis_Ababa; mix unavailable metrics and versions. | Deterministic report; no more than one approved focus. STEM has no early-grade coaching, child claims or district publishing. Low coverage yields unavailable explanation. Compatible versions grouped explicitly. |
    | REPORT-02 | Create 4/5 distinct-teacher cells, multiple lessons from one teacher, overlapping filters and successive releases; withdraw contributor. | Threshold counts teachers, teacher-level weighting, suppression propagates to totals/differences; no arbitrary drill-down or cross-cohort pooling. Reviewer signs disclosure check before district release. |
    
    ## E. Database, hosting and cloud suite (G0/G3/G4-U)
    
    | ID | Preconditions and steps | Expected result / evidence |
    |---|---|---|
    | DB-01 | Use selected pinned server/driver. Connect with trusted cert, then untrusted CA, expired cert and wrong hostname. Check negotiated connection; test restricted app user and blocked public port. | Valid identity succeeds; every invalid identity fails. `sslmode=require` alone is insufficient. If openGauss arrangement cannot meet this, block pilot and ratify authenticated alternative/PostgreSQL; do not downgrade checks. |
    | DB-02 | Apply migrations to empty DB and upgrade a previous fixture schema; check transaction/unique/JSON/timezone behavior; backup, restore to fresh instance, compare logical row counts/hashes; replay withdrawal. | Exact chosen engine/driver passes; rollback/recovery documented; no unreviewed claim of compatibility with a different GaussDB/PostgreSQL engine. Record measured recovery point/time against agreed numerical objectives. |
    | INFRA-01 | Provision pinned supported openEuler image/container stack on disposable selected host. Restrict inbound ports, boot/reboot, kill API/job/DB container, rotate secrets/cert and fill disk. Run same scheduled report twice concurrently. | Services recover within declared objective; DB volumes durable; failed jobs retry idempotently; migrations execute once; no duplicate reports. Only intended HTTPS exposed, admin/DB private, no secrets baked into image/logs. |
    | INFRA-02 | Inspect provider storage, encrypted backups, logs, monitoring destinations and restore path; induce backup failure and revoke a credential. | P1/P2 personal data and replicas remain in-country; alerts observed; actual restore meets signed retention/RPO/RTO; no unverified overseas diagnostic service. Provider contract/location evidence retained. |
    | CLOUD-01 | Deploy synthetic demo to confirmed region; check ECS/RDS VPC/security groups, least-privilege users, TLS, public demo URL and separate pilot credentials. Attempt pilot credential/data submission to demo; rebuild from empty synthetic state. | Approved synthetic dataset only, pilot credentials rejected, DB private, reproducible page. No automatic cross-tier replication, logging or model upload. Exact region/quota/service evidence recorded. |
    | CLOUD-02 | Verify ModelArts/OBS same region and service-specific bucket settings. Attempt anonymous access and access outside allowed prefix; inspect generated logs/checkpoints; attempt unapproved cohort upload through app/export pipeline. | Training accesses only approved assets; anonymous/unauthorised access denied. No P1/P2 audio, posteriors, weights or identifiers. Unencrypted training bucket never reused for pilot storage. ModelArts failure does not block offline STEM test. |
    | ANNOT-01 | Self-host exact Label Studio edition privately with SSRF protection. Try unauthenticated UI/media/API access, cross-project permissions, unapproved external/metadata URL imports; monitor outbound traffic and browser-loaded resources. | Authorised annotators only; private media stays in-country; unsafe fetch blocked; telemetry/resources reviewed or blocked; no assumed Enterprise-only guarantees. Preserve minimum access test evidence. |
    
    ## F. Field progression suite (Lead + ML + Sys)
    
    | ID | Preconditions and steps | Expected result / evidence |
    |---|---|---|
    | STEM-01 | Before recording, review G4-U, participant eligibility/consent and decline alternative; confirm course/session/language/mic/device matrix. | Approved protocol covers actual participants; no academic penalty/compulsion; adult-only assumption not made; no recording when prerequisite missing. Signed readiness checklist kept privately. |
    | STEM-02 | Run proposed ≥6 sessions across ≥3 instructors/≥2 STEM courses and rooms where feasible, including discussion/lab if available; human-log capture faults and mic placement. | Technical thresholds met and failures resolved; actual counts/conditions reported. Missing modalities/languages explicitly untested. No inference of child-speech performance or 99% reliability from small sample. |
    | STEM-03 | Independently annotate a disclosed subset of whole sessions with adult-role guide; compare adult-compatible model to human labels, grouped by instructor/course and language. | Exploratory F1/confusion, indicator error and unknown coverage reported separately. Unsupported roles unavailable; instructor/adult-student ambiguity discussed. Thresholds set before locked evaluation, no child-label score substitution. |
    | STEM-04 | Query reports/exports/aggregate jobs with mixed-cohort inputs; review technical report and unresolved defects. | STEM excluded from early-grade dashboard/evaluation and no normative coaching. P1 exit signed; critical privacy/data-loss/device defects resolved before G4-E. |
    | EARLY-01 | After P1 exit, review G4-E approvals/consent and target-device/mic feasibility anew; freeze school/teacher splits before training/calibration. | Separate early-grade readiness; STEM technical approval not reused as demographic/ethics approval. Adult data provenance declared in any transfer experiment. |
    | EARLY-02 | Evaluate held-out real O–3 audio with native language annotation and agreement; report each class, language and indicator with uncertainty/coverage. | Proposed targets: macro-F1 ≥0.70, child-single/choral F1 ≥0.75, LID accuracy ≥0.85 on eligible ≥3 s segments, I1 MAE ≤5 pp, I2 median MAE ≤0.5 s, I3 MAE ≤10 pp. Insufficient samples or failing indicators remain unavailable; do not pool STEM metrics. |
    | EARLY-03 | Co-design report rules; conduct separate ≥6-teacher/≥3-week intended-population pilot after technical and validity gates; apply disclosure tests. | G5-E sign-off for only validated indicators; qualitative usefulness reported; no causal/disciplinary claims. No safe district cell means no live aggregate release. |
    
    ## Readiness report template
    
    ```text
    Run ID / date / commit:
    Stage and cohort: P0 | P1 undergraduate_stem | P2 early_grade
    Implementation adapters and dependency/model/spec hashes:
    Applicable cases / passed / failed / blocked / not run:
    Device and deployment configuration:
    Fixture or approved dataset manifest hash:
    Evidence location (restricted; no personal data in git):
    Critical defects and rerun references:
    Measured technical metrics and sample counts:
    Unsupported languages/classes/session types:
    Gate decision and responsible reviewers:
    ```
    
    Acceptance-runner success covers only CORE-01–12 and the six COHORT vectors. It does not certify infrastructure, field accuracy, privacy or consent. Run the other suites and retain their evidence before advancing the corresponding gate.
    ✓ • 0ms

## Activity

    $ cat tools/check-hosting/check_hosting.py; cat tests/infrastructure/test_hosting_readiness.py; sed -n '119,140p' docs/research/RESEARCH_PLAN.md; cat docs/research/README.md
    """Offline hosting-evidence check. No probes, deployment, secrets or runtime controls."""
    import argparse
    from datetime import date
    import json
    import math
    from pathlib import Path
    import re
    
    CHECKS = (
        'python_packages_and_venv', 'supported_dependency_versions',
        'managed_gateway_and_restart', 'scheduled_cli_jobs',
        'database_transactions_and_unique_constraints', 'database_connection_protected',
        'tenant_isolation_reviewed', 'private_files_outside_document_root',
        'private_secret_injection', 'https_and_proxy_trust_verified',
        'primary_database_logs_in_ethiopia', 'backups_and_replicas_in_ethiopia',
        'encryption_at_rest_verified', 'daily_encrypted_backup',
        'restore_and_deletion_replay_tested', 'redacted_logging_verified',
        'resource_load_test_passed', 'retention_and_incident_owners_assigned',
    )
    LIMITS = ('ram_mb', 'cpu_limit', 'process_limit', 'request_timeout_s',
              'cron_max_runtime_s', 'database_connection_limit',
              'backup_interval_hours', 'rpo_hours', 'restore_hours', 'backup_retention_days')
    SELECTIONS = ('runtime', 'framework', 'database', 'python_version')
    ROOT_FIELDS = {'profile', 'data_kind', 'research_mode_enabled', 'selection', 'checks', 'limits'}
    
    
    def load_json(path):
        def unique(pairs):
            result = {}
            for key, value in pairs:
                if key in result:
                    raise ValueError('Duplicate field')
                result[key] = value
            return result
    
        def bad_constant(_):
            raise ValueError('Non-finite number')
    
        return json.loads(Path(path).read_text(), object_pairs_hook=unique,
                          parse_constant=bad_constant)
    
    
    def validate(document, today=None):
        """Return field-only errors; supplied evidence is not independently authenticated."""
        errors = []
        today = today or date.today()
        if not isinstance(document, dict) or set(document) != ROOT_FIELDS:
            return ['document: unexpected or missing fields']
        for field, expected in [('profile', 'zergaw-shared-derived-v1'),
                                ('data_kind', 'derived_only'), ('research_mode_enabled', False)]:
            if type(document[field]) is not type(expected) or document[field] != expected:
                errors.append(f'{field}: unsupported scope')
    
        values = {}
        for group, names in [('selection', SELECTIONS), ('checks', CHECKS), ('limits', LIMITS)]:
            records = document[group]
            if not isinstance(records, dict) or set(records) != set(names):
                errors.append(f'{group}: unexpected or missing fields')
                records = records if isinstance(records, dict) else {}
            for name in names:
                key = f'{group}.{name}'
                record = records.get(name)
                if not isinstance(record, dict) or set(record) != {'value', 'evidence', 'confirmed_on'}:
                    errors.append(f'{key}: incomplete evidence record')
                    continue
                values[key] = record['value']
                if not isinstance(record['evidence'], str) or not record['evidence'].strip():
                    errors.append(f'{key}: evidence reference required')
                try:
                    if not isinstance(record['confirmed_on'], str):
                        raise ValueError()
                    confirmed = date.fromisoformat(record['confirmed_on'])
                    if confirmed.isoformat() != record['confirmed_on'] or confirmed > today:
                        raise ValueError()
                except ValueError:
                    errors.append(f'{key}: valid non-future confirmation date required')
                value = record['value']
                if group == 'checks' and value is not True:
                    errors.append(f'{key}: not confirmed')
                if group == 'limits' and (type(value) not in (int, float) or
                                           not math.isfinite(value) or value <= 0):
                    errors.append(f'{key}: positive measured limit required')
    
        runtime = values.get('selection.runtime')
        framework = values.get('selection.framework')
        database = values.get('selection.database')
        if (runtime, framework) not in [('asgi', 'fastapi'), ('wsgi', 'django')]:
            errors.append('selection: choose supported ASGI/FastAPI or WSGI/Django path')
        if database not in ('postgresql', 'mysql', 'mariadb'):
            errors.append('selection.database: supported engine required')
        elif framework == 'fastapi' and database != 'postgresql':
            errors.append('selection.database: this ASGI baseline requires PostgreSQL')
        python_version = values.get('selection.python_version')
        if not isinstance(python_version, str) or not re.fullmatch(r'3\.(1[0-9]|[2-9][0-9])\.\d+', python_version):
            errors.append('selection.python_version: explicit Python 3.10+ patch version required')
        # Future-version compatibility is still established by supported_dependency_versions.
        for name, maximum in [('backup_interval_hours', 24), ('rpo_hours', 24), ('restore_hours', 8)]:
            value = values.get(f'limits.{name}')
            if type(value) in (int, float) and value > maximum:
                errors.append(f'limits.{name}: exceeds project recovery target')
        return errors
    
    
    def main():
        parser = argparse.ArgumentParser(description=__doc__)
        parser.add_argument('capabilities', help='Private JSON evidence file; never include credentials')
        args = parser.parse_args()
        try:
            errors = validate(load_json(args.capabilities))
        except (OSError, ValueError, UnicodeError):
            print('BLOCKED: cannot read a valid capability record (details withheld).')
            return 2
        if errors:
            print('BLOCKED: declared hosting evidence is incomplete or incompatible.')
            for error in errors:
                print(f'- {error}')
            return 1
        print('Declared evidence is complete. Provider verification, HOST/SEC tests and cohort approvals remain required.')
        return 0
    
    
    if __name__ == '__main__':
        raise SystemExit(main())
    """Synthetic tests of evidence checker only; not provider/application security tests."""
    from copy import deepcopy
    from datetime import date, timedelta
    import importlib.util
    import json
    from pathlib import Path
    import subprocess
    import sys
    import tempfile
    import unittest
    
    ROOT = Path(__file__).resolve().parents[2]
    SCRIPT = ROOT / 'tools/check-hosting/check_hosting.py'
    spec = importlib.util.spec_from_file_location('hosting_check', SCRIPT)
    checker = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(checker)
    
    
    def evidence(value):
        return {'value': value, 'evidence': 'synthetic-test-fixture-only', 'confirmed_on': '2026-10-02'}
    
    
    def complete():
        return {
            'profile': 'zergaw-shared-derived-v1', 'data_kind': 'derived_only', 'research_mode_enabled': False,
            'selection': {key: evidence(value) for key, value in
                          zip(checker.SELECTIONS, ['wsgi', 'django', 'mariadb', '3.12.12'])},
            'checks': {key: evidence(True) for key in checker.CHECKS},
            'limits': {key: evidence(value) for key, value in zip(checker.LIMITS,
                       [512, 1, 2, 60, 120, 10, 24, 24, 8, 30])},
        }
    
    
    class HostingReadinessTests(unittest.TestCase):
        def check(self, document):
            return checker.validate(document, today=date(2026, 10, 2))
    
        def test_supported_wsgi_engines(self):
            for database in ('postgresql', 'mysql', 'mariadb'):
                record = complete()
                record['selection']['database']['value'] = database
                self.assertEqual(self.check(record), [])
    
        def test_supported_asgi_path(self):
            record = complete()
            for key, value in [('runtime', 'asgi'), ('framework', 'fastapi'), ('database', 'postgresql')]:
                record['selection'][key]['value'] = value
            self.assertEqual(self.check(record), [])
    
        def test_unconfirmed_template_blocks(self):
            path = ROOT / 'infra/in-country/shared-hosting/capabilities.example.json'
            self.assertTrue(self.check(checker.load_json(path)))
            result = subprocess.run([sys.executable, str(SCRIPT), str(path)], capture_output=True, text=True)
            self.assertEqual(result.returncode, 1)
            self.assertIn('BLOCKED', result.stdout)
    
        def test_each_required_check_must_be_true_and_evidenced(self):
            for name in checker.CHECKS:
                for value in (None, False, 'true', 1):
                    record = complete()
                    record['checks'][name]['value'] = value
                    with self.subTest(name=name, value=value):
                        self.assertTrue(self.check(record))
                record = complete()
                record['checks'][name]['evidence'] = ''
                self.assertTrue(self.check(record))
    
        def test_security_scope_and_unknown_fields_block(self):
            mutations = [('research_mode_enabled', True), ('data_kind', 'raw_audio'),
                         ('profile', 'demo'), ('unexpected', 'secret')]
            for field, value in mutations:
                record = complete()
                record[field] = value
                self.assertTrue(self.check(record))
    
        def test_invalid_limits_and_recovery_targets_block(self):
            for value in (0, -1, True, '512', None, float('inf'), float('nan')):
                record = complete()
                record['limits']['ram_mb']['value'] = value
                self.assertTrue(self.check(record))
            for field, value in [('backup_interval_hours', 168), ('rpo_hours', 25), ('restore_hours', 9)]:
                record = complete()
                record['limits'][field]['value'] = value
                self.assertTrue(self.check(record))
    
        def test_missing_invalid_or_future_evidence_dates_block(self):
            for value in (None, '2026-10-03', 'yesterday', 12):
                record = complete()
                record['checks']['daily_encrypted_backup']['confirmed_on'] = value
                self.assertTrue(self.check(record))
    
        def test_runtime_and_database_mismatch_blocks(self):
            for field, value in [('framework', 'fastapi'), ('database', 'opengauss'), ('runtime', 'cgi'),
                                 ('python_version', 'latest')]:
                record = complete()
                record['selection'][field]['value'] = value
                self.assertTrue(self.check(record))
    
        def test_malformed_or_duplicate_json_is_rejected_without_echo(self):
            with tempfile.TemporaryDirectory() as folder:
                path = Path(folder) / 'input.json'
                for content in ('{"secret":"sensitive-canary",', '{"a":1,"a":2}', '{"a":NaN}'):
                    path.write_text(content)
                    result = subprocess.run([sys.executable, str(SCRIPT), str(path)], capture_output=True, text=True)
                    self.assertEqual(result.returncode, 2)
                    self.assertNotIn('sensitive-canary', result.stdout + result.stderr)
    
        def test_bad_shapes_block_without_crashing(self):
            for record in ([], None, {}, {'profile':'wrong'}):
                self.assertTrue(self.check(record))
            record = complete()
            record['checks'] = []
            self.assertTrue(self.check(record))
            record = complete()
            record['selection']['framework'] = None
            self.assertTrue(self.check(record))
    
    
    if __name__ == '__main__':
        unittest.main()
    ### WS-E: Stack and device spikes (Sys + ML)
    
    | Spike | Pass criterion | Due |
    |---|---|---|
    | S1 | `.ms` INT8 hello-world through the 2.10.0 AAR on a 2 GB phone | W0 |
    | S2 | RTF ≤ 0.3 and RSS ≤ 300 MB via the bundled `benchmark` tool and the in-app harness | W3 |
    | S3 | Each FP export agrees with its own source graph on fixed features (initial tolerance 1e-4, justified per model); compare frameworks only for weight-equivalent graphs. Assess PTQ through task/indicator degradation separately; record actual precision/fallback ops | W3 |
    | S4 | MindSpore 2.10 trains a toy model on the in-country hardware | W1 |
    | S5 | Confirm exact services before choosing a region; ModelArts, OBS and ECS all in **AF-Johannesburg**; flavours and prices recorded; any required unencrypted training bucket restricted to approved corpus objects, never anonymous/public access; verify licence/privacy separately | W1 |
    | S6 | Pin maintained openEuler SP3 candidate + chosen DB/driver; test authenticated TLS including wrong hostname/CA rejection (INF-09); no generic SASL downgrade; **host chosen from [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md)** — Zergaw VPS/DBaaS, INSA G-Cloud or Ethio Telecom teleCloud, not the on-campus box, unless the university prefers self-hosting | W1 |
    | S7 | Modern target-SDK microphone permission/foreground-service lifecycle; `AudioRecordingCallback` detects call-silencing; 40-minute battery test | W2 |
    | S8 | 16 KB decision: test Lite, SQLCipher and any fallback native libraries, ELF/ZIP alignment and runtime; sideload only on verified compatible phones | W6 |
    | S9 | SQLCipher + Keystore key; backup/transfer exclusions, key-loss/reinstall and shared-phone tests before G4-U | W2 |
    | **S10** (new) | **Competition cloud stack:** ECS + RDS for PostgreSQL (GaussDB only after a separate compatibility decision) + OBS in AF-Johannesburg serving the demo page and lesson simulator with synthetic lessons. Redeployable from a script. | W5 |
    | S11 | Sync ownership, ACK/conflict/retry, withdrawal and restore-purge tests per G4-U; repeat changed paths for G4-E; cohort isolation and disclosure tests before G5-E | Before pilot/release |
    
    ### WS-F: Legal and ethics (Lead)
    
    Primary text: Proclamation 1321/2024. The supplied Gazette copy is in `docs/legal/`; use it with the legal advisor (see TO_OBTAIN #2).
    
    Questions for the legal advisor, answered in writing by W3:
    
    # Dimts research pack
    
    Research audit and plan for the Dimts (ድምጽ) proposal described in `DIMTS_ARCHITECTURE_CONTEXT.md` (reviewed proposal, 2026-09-27). Research evidence updated 2026-09-26; implementation-plan review updated 2026-09-27. A basic Android Compose starter exists; other components are placeholders. No runtime or product behavior has been validated.
    
    | File | What it is |
    |---|---|
    | [`STEM_TRIAL_PLAN.md`](STEM_TRIAL_PLAN.md) | Undergraduate STEM technical trial first, then separate early-grade validation. |
    | [`INFRASTRUCTURE_RECHECK.md`](INFRASTRUCTURE_RECHECK.md) | Current official infrastructure findings, proposed choices and linked acceptance cases. |
    | [`../testing/POST_IMPLEMENTATION_TEST_PLAN.md`](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) | Test procedures, acceptance criteria and evidence template; links to 18 executable contract cases needing implementation adapters. |
    | [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md) | Current repository baseline, architecture findings, contract checklist and implementation gates G0–G5. Read before implementing. |
    | [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) | Each factual claim and assumption in the architecture document, checked against sources (2026-09-25; updated 2026-09-26 with supplied full texts), with a verdict and the change it implies. |
    | [`RESEARCH_PLAN.md`](RESEARCH_PLAN.md) | What is still open: research questions, reading lists, experiments, infrastructure spikes, legal questions, team decisions and a schedule merged with the 2026–27 competition calendar. |
    | [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md) | Every source used or recommended, in APA 7th style, tagged with how far it was checked. |
    | [`ASSUMPTIONS.md`](ASSUMPTIONS.md) | Register of the 50 assumptions the design rests on: origin, status, evidence, test and impact if wrong. |
    | [`CODEBASE_STRUCTURE.md`](CODEBASE_STRUCTURE.md) | Proposed monorepo layout revised from §12 in light of the findings, including the two-tier (competition cloud / in-country pilot) deployment. |
    | [`TO_OBTAIN.md`](TO_OBTAIN.md) | Sources still needed, and what the supplied batch resolved. |
    | [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md) | Ethiopian hosting/cloud providers checked against Article 22 (data sovereignty), for the `infra/openeuler` sovereign-tier server. |
    | [`papers/`](papers/) | Full texts supplied on 2026-09-26. Copyrighted, so keep them private (see TO_OBTAIN). |
    
    ## Verdict labels
    
    The bar for **verified** is a primary source, a peer-reviewed paper, or official documentation or software that was actually opened. When the primary could not be opened, an abstract or a reputable secondary source is used and labelled as such. Neither counts as verified.
    
    | Label | Meaning |
    |---|---|
    | **VERIFIED** | Confirmed in a primary, peer-reviewed or official source that was opened (full text, official page, released package or binary). |
    | **VERIFIED (abstract)** | Confirmed only from the primary source's own abstract or registry metadata (Crossref, ERIC, Europe PMC). |
    | **SECONDARY** | Supported only by a secondary source (blog, press, search-engine summary). The primary was not opened. |
    | **PARTLY** | The fact is right but the claim goes beyond it (scope, date, sample, generalisation). |
    | **CONTRADICTED** | A primary source says otherwise. |
    | **UNVERIFIED** | Could not be checked. The plan says how to check it. |
    
    ## Access limits during the first pass (2026-09-25)
    
    Most of the gaps below were closed by the papers supplied on 2026-09-26; see [`TO_OBTAIN.md`](TO_OBTAIN.md).
    
    - **Refused by the site's bot protection (HTTP 403):** Wiley/Hindawi, Taylor & Francis, SAGE, ScienceDirect, ResearchGate, policycommons.net and the Huawei Talent portal. For those papers, Crossref, ERIC or Europe PMC metadata and abstracts were used instead.
    - **USAID's document archive** (`pdf.usaid.gov`, `dec.usaid.gov`) was unreachable (gateway 502), so the EGRA 2014/2016/2018 reports could not be opened.
    - **`web.archive.org` and several `.gov.et` hosts** were unreachable.
    - **`moe.gov.et`** served Indonesian gambling spam on its `/resources/annual-abstract/` pages. The site looks compromised, so nothing was downloaded from it. Get the ESAA from the Ministry through official channels.
    - **Search results are not evidence.** Web-search summaries were used only to find sources. Anything that rests on a search summary is labelled SECONDARY.
    ✓ • 0ms

## Activity

    $ sed -n '170,225p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; sed -n '345,418p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; cat docs/security/ARCHITECTURE.md; cat docs/research/INFRASTRUCTURE_RECHECK.md
    ## 7. Huawei technology mapping
    
    ### 7.1 Principles
    Current official-source findings and test links are in [INFRASTRUCTURE_RECHECK.md](research/INFRASTRUCTURE_RECHECK.md). Exact versions, hardware and regional service availability remain acceptance gates.
    - Use Huawei technology where it is **load-bearing**, not decorative. Judges probe in Q&A.
    - Keep an **escape hatch** (ONNX) for every model so a framework bug cannot sink the timeline.
    - The deployment-critical path (phone + in-country server) must not depend on a foreign cloud region (C-7).
    
    ### 7.2 Mapping table
    
    | Layer | Huawei technology | Role in Dimts | Why / caveat |
    |---|---|---|---|
    | On-device inference | **MindSpore Lite** (2.x; Android-aarch64 AAR; current release listed as 2.10.0 [VERIFY at build time]) | Runs segmentation + LID models, INT8 | Supports Android, post-training INT8 quantization, and converts MindIR/ONNX/TFLite to `.ms`. Built into HarmonyOS, so the same model ports to R-5. |
    | Model training | **MindSpore** 2.x | Teacher-model fine-tuning, student training, distillation | Primary framework. |
    | Audio toolkit | **Owned feature extraction** (Python + Kotlin) | Versioned log-mel extraction with streaming parity fixtures | Do not depend on MindAudio maintenance; research audit recommends owned feature code. |
    | Pretrained speech encoders | **PyTorch + Hugging Face** | Load multilingual self-supervised encoders (wav2vec2 / XLS-R / HuBERT family) as the server-side teacher model | Proposed replacement for MindNLP per audit; verify model licence and target training hardware in S4. |
    | Cloud training | **Huawei Cloud ModelArts** (Ascend) | Pre-training / fine-tuning on **public** speech data; pipeline demo; public-data-only experiments | AF-Johannesburg is a candidate, subject to S5 service availability. No pilot audio, posteriors, features, logs or classroom-trained weights may flow to this tier under the proposed boundary. |
    | Object storage (cloud) | **Huawei Cloud OBS** | Public datasets, model artefacts, experiment logs | No personal data. |
    | Server OS | **openEuler** (24.03 LTS SP3 candidate) | In-country server OS at Hawassa University | Huawei-originated open-source Linux. |
    | Database | **openGauss** | Indicator records, reports, aggregates, consent ledger | Conditional on exact-driver, migration and authenticated-TLS tests: documented openGauss verify-full limitation is a blocker unless resolved; PostgreSQL is the explicit fallback (INF-09). |
    | CI/CD (optional) | Huawei Cloud **CodeArts** | Build/test pipelines | Optional; GitHub Actions acceptable if friction is high. |
    | Client OS (stretch) | **HarmonyOS / OpenHarmony**, ArkTS | Native client port (R-5) | Target teachers overwhelmingly use Android phones (Tecno, Itel, Samsung). HarmonyOS NEXT does not run Android apps, so it cannot be the primary client. Frame the port as "same `.ms` model, second OS." |
    
    ### 7.3 What to say in Q&A
    Describe demonstrated components only. Proposed: PyTorch/HF teacher, MindSpore student where hardware supports it, Lite CPU inference. The competition demo uses synthetic records on Huawei Cloud; the pilot uses a separate in-country deployment. Model portability, runtime precision and cross-language accuracy are experiments, not completed claims.
    
    ### 7.4 HarmonyOS position
    Primary client is Android because that is what teachers own. The HarmonyOS client is a roadmap item unless the team has spare capacity after the national stage. Do not let it block the critical path.
    
    ---
    
    ## 8. System architecture
    
    ### 8.1 Tiers
    
    ```text
    ANDROID (offline pilot client)
      Mic → bounded RAM capture → owned features → Lite inference
          → observed/MISSING timeline + language/unknown → Kotlin indicators
          → encrypted local record + local report → authenticated outbox
      Research Mode: separate consent → encrypted audio → in-country transfer
                        │ derived LessonRecords only in Deployment Mode
                        ▼
    IN-COUNTRY PILOT (host selected through S6)
      FastAPI → chosen/tested database → private weekly reports
              → fixed-cohort, disclosure-reviewed district aggregates
      Separate enrolment, consent/revocation, audit, encrypted backups
      Research audio/annotation and classroom training stay in-country
    
    HUAWEI CLOUD DEMO (isolated endpoint, credentials, DB, logs and backups)
      Synthetic fixture generator → same API/report code → visual demo page
      Approved public-corpus/model experiments → ModelArts/OBS, subject to S5
      Approved cloud artefacts may enter pilot; NO pilot-derived export
    ```
    
    ### 8.2 Key design decisions (proposed; ratify via ADR)
    ## 11. Privacy, security, and ethics architecture
    
    | Concern | Control |
    |---|---|
    | Raw audio exposure | Deployment Mode: RAM ring buffer only, never persisted. Research Mode: per-lesson AES key, file encrypted at rest, deleted from phone after verified transfer. |
    | Voice biometrics | No speaker embeddings persisted anywhere. Models output class posteriors only. |
    | Child data | No child-level features. CHILD_* are acoustic classes, not individuals. |
    | Data localisation (C-7) | All personal data (records, research audio, enrolment) on in-country server. Huawei Cloud demo holds synthetic records; any public speech/model asset requires separate licence and privacy approval. No pilot-derived export, including weights or soft labels. |
    | Re-identification via dashboard | Minimum group size 5 distinct teachers, not lessons; fixed cohorts, equal teacher weighting, no arbitrary filters, and complementary suppression/differencing tests before district release. This alone is not proof of anonymity. |
    | Teacher misuse by management | Individual reports visible only to the teacher. Sharing with director is teacher-initiated, revocable. Written into consent form and product copy. |
    | Transport | TLS 1.2+, certificate pinning in app. |
    | At rest | Device: SQLCipher + Keystore; exclude research files, databases, identifiers and credentials from cloud backup/device transfer; test API 29 and API 31+ rules. Server: disk encryption + encrypted backups in-country. |
    | Consent | Per-teacher consent recorded in app and in server ledger; separate opt-in for Research Mode; withdrawal deletes server records (tombstone + purge job). Child consent/assent and school permissions per legal/ethics review; no classroom capture before approval. Offline withdrawal stops capture and discards queued lessons; server revocation rejects stale retries. |
    | Breach | 72-hour notification runbook (per C-7) in `docs/ethics/breach-runbook.md`. |
    | Approvals | Separate G4-U undergraduate recruitment/consent and G4-E early-grade ethics/permissions **before recording each cohort**. Do not assume all undergraduates are adults. No participation tied to grades; practical decline alternative. |
    
    ---
    
    ## 12. Proposed repository structure (monorepo)
    
    ```
    dimts/
    ├── README.md
    ├── docs/
    │   ├── DIMTS_ARCHITECTURE_CONTEXT.md        ← this file
    │   ├── adr/                           ← ADR-000 template, ADR-001..n
    │   ├── annotation-guide.md
    │   ├── indicator-spec.md              ← mirrors §5, versioned
    │   └── ethics/                        ← consent forms (amh, sid, eng), approval, breach runbook
    ├── indicators-core/
    │   ├── python/                        ← reference implementation (package: dimts_indicators)
    │   ├── kotlin/                        ← Android port (Gradle module)
    │   └── golden/                        ← JSON timelines + expected outputs; CI runs both impls
    ├── ml/
    │   ├── configs/                       ← experiment configs (YAML)
    │   ├── data/                          ← manifests, splits, NO audio in git
    │   ├── features/                      ← owned log-mel, augmentation, Python/Kotlin parity
    │   ├── synth/                         ← synthetic classroom mixture generator (D-6)
    │   ├── teacher/                       ← SSL encoder fine-tuning (PyTorch/HF, in-country for pilot data)
    │   ├── student/                       ← CRNN / compact conformer
    │   ├── distill/                       ← soft-label generation, KD training
    │   ├── export/                        ← MindIR → .ms, INT8 PTQ, ONNX fallback
    │   ├── eval/                          ← frame F1, indicator error, cross-language, latency
    │   └── modelarts/                     ← job definitions for Huawei Cloud
    ├── edge/
    │   └── android/
    │       ├── app/                       ← UI (Jetpack Compose), i18n: amh, sid, eng
    │       ├── audio/                     ← capture service, ring buffer
    │       ├── inference/                 ← MindSpore Lite wrapper (Java API or JNI)
    │       ├── pipeline/                  ← features → seg → LID → post-process
    │       ├── storage/                   ← encrypted Room/SQLCipher
    │       ├── sync/                      ← outbox, WorkManager, API client
    │       └── benchmark/                 ← on-device RTF / RSS / battery harness
    ├── server/
    │   ├── api/                           ← ingest, reports, dashboard API
    │   ├── jobs/                          ← weekly reports, aggregation, purge
    │   ├── db/                            ← openGauss migrations
    │   └── tests/
    ├── dashboard/                         ← district web UI
    ├── harmony/                           ← (stretch) ArkTS client, R-5
    ├── infra/
    │   ├── huawei-cloud/                  ← synthetic-only competition demo, separate credentials
    │   ├── openeuler/                     ← in-country pilot host, containers and backups
    │   └── ci/                            ← pipelines (CodeArts or GitHub Actions)
    └── tools/
        ├── lesson-simulator/              ← replays recorded/synthetic lessons into the pipeline
        └── demo/                          ← competition demo scripts and fixtures
    ```
    
    **Backend language [DECISION: ADR-005]:** default proposal Python (FastAPI) for the server so the indicator reference implementation and aggregation share one language with the ML stack. Confirm openGauss driver compatibility in week 1.
    
    ---
    
    ## 13. Interfaces
    # Security architecture — shared-hosting pilot
    
    2026-10-02. **Architecture requirements, not implemented controls.** Normative for the hosting revision in [ADR-015](../adr/015-capability-gated-hosting.md). User confirmed hosting capabilities are unknown and separate research infrastructure is not yet available. Research Mode is disabled until that infrastructure is prepared and approved.
    
    ## Trust boundaries and minimisation
    
    ```text
    Android (encrypted local state, on-device inference)
      └─ HTTPS derived records → provider gateway → pilot app → selected database
                                   │                 └─ bounded cron jobs
                                   └─ private config; reviewed static assets only
    
    Private in-country research machine [NOT YET AVAILABLE]
      └─ separate consent, endpoint, identity register, audio/annotation/training
    
    Huawei Cloud synthetic demo
      └─ different credentials, hostname, DB and logs; no pilot/research import
    ```
    
    The shared backend stores pseudonymous records, minimal enrolment and consent references, and private reports. Pseudonymous does not mean anonymous. Keep names/contact register separately where practical; do not add student identifiers. Store no audio, embeddings, fine-grained speech timeline, model-training targets or biometric identifiers on shared hosting. These exclusions are the default API field allowlist and infrastructure boundary, not merely upload-size limits.
    
    ## Threat model and controls
    
    | ID | Threat / control | Enforcement owner and acceptance evidence |
    |---|---|---|
    | SEC-01 | Guessed IDs, forged institution/cohort, role escalation: deny by default; derive scope from authenticated enrolment and check ownership on every record/report/export/job. | `server/security` + scoped DB queries; cross-user/cohort negative tests |
    | SEC-02 | Credential theft/reuse: per-device revocable credentials; one-use enrolment codes; explicit recovery. Browser accounts use maintained framework password hashing/session protection. Rate-limit enrolment/login/recovery by account and source without globally locking victims out. | API/config; expiry, replay, revocation and throttling tests; admin MFA via supported mechanism or restricted operator access |
    | SEC-03 | Web attacks: exact hosts/origins; browser session cookies Secure/HttpOnly/SameSite; CSRF on cookie-authenticated changes; autoescaping; CSP appropriate to actual assets; no framing; no debug traces. Native token API is separately authenticated. | Framework/proxy; CSRF/XSS/host/proxy spoof tests; CORS is not authentication |
    | SEC-04 | Cross-tenant/provider access: verify filesystem/process/account isolation and staff-access policy; secrets/source/backups outside document root; least-privilege filesystem/DB roles. | Provider + deployment; unauthorised-file/access tests. Provider admins remain trusted processors; app encryption with keys on the same host does not protect against host compromise. Reject host if residual risk is unacceptable. |
    | SEC-05 | Transport interception: HTTPS with normal hostname/CA validation; remote DB authenticated TLS or verified private local socket. Trust forwarded headers only from the provider's identified proxy. | Provider + API; wrong-cert/hostname and forged-header tests. Pinning is optional only with backup pins/rotation/recovery; never disable validation to recover. |
    | SEC-06 | Secret/data disclosure: private config, distinct demo/pilot/app/migration/backup credentials, rotation and no plaintext tokens/passwords in DB or logs. Use established libraries and random secrets. Log only allowlisted operational/security metadata. | Config + audit; canary-secret/log scan, rotation and document-root tests |
    | SEC-07 | Duplicate/replayed records and withdrawal races: scoped immutable IDs with canonical content hash; transaction before ACK; revoke enrolment before purge and reject stale devices. | Domain + DB; dropped-ACK, concurrent withdrawal, conflict and partial-batch tests |
    | SEC-08 | Silent cron failures or restore resurrection: bounded idempotent DB-leased jobs, checkpointing and missed-run alerts; erase derived reports; replay deletion ledger before restored service returns. | Jobs + backup runbook; crash/retry/lease expiry and restore tests. No long-lived in-memory scheduler or public cron URL. |
    | SEC-09 | Excessive data collection/retention: strict JSON schema, body/batch/rate limits; finite approved retention; no raw-audio endpoints in pilot app. Limits must be numeric/configured before launch. | API + jobs; oversized/extra-field rejection and retention tests |
    | SEC-10 | Disclosure through aggregates: distinct-teacher threshold, equal teacher weighting, fixed cohorts, complementary suppression and repeated-release review. STEM excluded from early-grade coaching/district aggregation. | Reports; disclosure and mixed-cohort tests |
    | SEC-11 | Backup/supply-chain exposure: encrypted daily in-country backups, keys stored separately from archives, least privilege, dependency pins/hashes, small deploy bundle and restore tests. No deploy of entire repo, PDFs, private data or training dependencies. | Deployment + CI; backup/RPO/restore evidence, dependency/secret scan and protected release workflow |
    | SEC-12 | Lost/shared phone and model tampering: SQLCipher/Keystore, account lock/logout, OS backup/transfer exclusions, model manifest integrity and compatibility checks. Offline use remains available within approved enrolled scope. | Android future implementation; storage/privacy/device/model suites; local revocation acts immediately, remote revocation applies once contact occurs |
    
    Architecture decisions follow [OWASP authorisation guidance](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html) for per-request checks and least privilege, and [OWASP logging guidance](https://cheatsheetseries.owasp.org/cheatsheets/Logging_Cheat_Sheet.html) for excluding secrets/sensitive content. Framework settings must be verified against the selected release, e.g. [Django deployment checklist](https://docs.djangoproject.com/en/5.2/howto/deployment/checklist/). These references do not certify this application or provider.
    
    ## Access matrix (default deny)
    
    | Principal | Permitted | Prohibited by default |
    |---|---|---|
    | Enrolled teacher/instructor device | Own cohort sessions/records and reports; own consent/withdrawal | Other users/institutions, role changes, raw research upload to pilot |
    | Teacher/instructor browser session | Own reports and explicitly supported account operations | Other individual reports; unrestricted aggregate queries |
    | District aggregate viewer | Released, disclosure-reviewed early-grade aggregates | Individual reports, STEM cohorts, arbitrary filters |
    | Research annotator | Assigned approved research recordings on private research environment | Shared-backend reports, identity register, cloud demo |
    | Operator | Deploy, account support and redacted system health through restricted operator channel | Routine access to individual report/audio content; role escalation through public UI |
    | Scheduled job | Named internal use case with scoped DB privilege | Public login, arbitrary SQL/admin rights, cross-tier export |
    | Demo identity | Synthetic deployment only | Any pilot/research authentication or connectivity |
    
    Support access to personal content, if ever needed, requires a specifically authorised audited workflow; database administration capabilities must be documented as residual privileged access. Do not describe operator exclusion as protection from a host/database administrator.
    
    ## Shared-hosting launch gates
    
    - Confirm exact Python/DB/scheduler resources, tenant isolation, protected file placement, secret management, backup/log destinations, encryption and restore rights. Unknown entries block real-data deployment.
    - Run the host checklist checker and the actual HOST/SEC tests. Checklist success only validates recorded evidence shape; no provider claim becomes true because a JSON value is `true`.
    - G4-U still requires undergraduate consent/eligibility and institutional approval. G4-E remains separate. Research Mode stays disabled until its separate host, access/transfer/backup controls and approvals exist.
    - Daily backup and target RPO ≤24 h / RTO ≤8 h are provisional project requirements. Retention and deletion-ledger retention need explicit approved values before capture. Keep the service unavailable during restore until deletion replay is complete.
    - If a shared host cannot enforce these boundaries, use the VPS fallback. Root, Docker and an openEuler image are not security prerequisites for a well-managed shared application service.
    
    ## Incident response and operations
    
    Assign a named operator/contact and response process before launch. On suspected credential compromise: revoke affected credentials, suspend suspect enrolments, preserve minimum redacted evidence in-country, rotate secrets and assess records/backup exposure. Follow the existing university/legal notification process; this document establishes no new legal deadline. Document who patches the provider runtime versus application dependencies and schedule periodic access/restore reviews.
    # Infrastructure documentation re-check
    
    Checked official documentation on 2026-09-27. These are documentation findings and proposed choices, not successful installs or benchmarks. Final host, training hardware, regional capacity and credentials have not been supplied. Applies equally to undergraduate STEM and later early-grade data.
    
    | ID | Component / source | Confirmed documentation and implication | Required post-implementation case |
    |---|---|---|---|
    | INF-01 | [AGP 9.4 compatibility](https://developer.android.com/build/releases/agp-9-4-0-release-notes) | AGP 9.4 lists Gradle 9.6.0, API ≤37 and minimum JDK 17. Repo uses Gradle 9.6.0, API 37, daemon JDK 25 and AGP 9.4.1. Family requirements match; this page does not establish the exact patch/plugin/JDK combination. Resolve and build exact pins before changes. Java source target 11 is separate from Gradle's runtime JDK. | BUILD-01 |
    | INF-02 | [Lite 2.10.0 downloads](https://www.mindspore.cn/lite/docs/en/r2.10.0/use/downloads.html) | Android-aarch64 package contains AAR/runtime/benchmark; cloud-side and device-side Linux converters are distinct downloads. Pin the device-side package and published SHA-256 with matching converter. Package availability is not model compatibility. | MODEL-01 |
    | INF-03 | [Lite operator table](https://www.mindspore.cn/lite/docs/en/r2.10.0/reference/operator_list_lite.html), [PTQ](https://www.mindspore.cn/lite/docs/en/r2.10.0/advanced/quantization.html) | CPU GRU/LSTM list FP16/FP32, not INT8. Keep conv-only INT8 and mixed-precision recurrent candidates; prove full exported graph support and calibration behavior. | MODEL-01/02 |
    | INF-04 | [MindSpore 2.10 release](https://www.mindspore.cn/docs/en/r2.10.0/RELEASE.html) | Release notes do not prove a usable training stack on the team's unidentified hardware. Capture OS/Python/accelerator/driver/CANN compatibility and a forward/backward/save/reload spike. PyTorch teacher and MindSpore student stay conditional. | MODEL-03 |
    | INF-05 | [Microphone foreground service](https://developer.android.com/develop/background-work/services/fgs/service-types#microphone), [audio input sharing](https://developer.android.com/media/platform/sharing-audio-input) | Plan runtime RECORD_AUDIO, foreground-service declarations/permissions appropriate to target SDK, visible-UI start and recording callback handling. Capture can be silenced; callbacks plus frame continuity determine missingness. | DEVICE-01/02 |
    | INF-06 | [WorkManager work requests](https://developer.android.com/develop/background-work/background-tasks/persistent/getting-started/define-work) | Periodic work has a 15-minute minimum and timing depends on constraints/system scheduling. Use unique network-constrained one-time work when an outbox record is ready, plus periodic recovery. Do not promise immediate sync or run live capture in WorkManager. | SYNC-01/02 |
    | INF-07 | [SQLCipher migration](https://www.zetetic.net/sqlcipher/sqlcipher-for-android-migration/), [Auto Backup](https://developer.android.com/identity/data/autobackup), [16 KB support](https://developer.android.com/guide/practices/page-sizes) | Use `net.zetetic:sqlcipher-android` with `SupportOpenHelperFactory` for Room, not the legacy library/API. Pin a tested Room/SQLite/SQLCipher set. SQLCipher and Lite both need native/packaging/runtime checks; encryption does not replace backup exclusions and key lifecycle tests. | STORE-01/02; BUILD-02 |
    | INF-08 | [openEuler releases](https://www.openeuler.org/en/download/), [24.03 LTS SP3 containers](https://docs.openeuler.org/en/docs/24.03_LTS_SP3/cloud/index.html) | 24.03 LTS SP3 is a candidate with published planned EOL 2027/12. Select maintained service pack, architecture, image checksum and actual container engine/Compose versions. A generic “LTS” or “Docker compatible” description is not enough. | INFRA-01 |
    | INF-09 | [openGauss psycopg SSL](https://docs.opengauss.org/en/docs/latest/developer_guide/connecting_to_the_database_using_ssl_psycopg.html), [PostgreSQL libpq SSL](https://www.postgresql.org/docs/current/libpq-ssl.html) | openGauss documents `verify-full` as unsupported; `require` encrypts without verifying server authenticity. PostgreSQL documents certificate/hostname checks for `verify-full`. This is a selection gate: prove authenticated server identity for the chosen openGauss/driver arrangement or choose PostgreSQL; never silently weaken verification. No generic SASL workaround is approved. | DB-01/02 |
    | INF-10 | [FastAPI containers](https://fastapi.tiangolo.com/deployment/docker/) | Build from a pinned Python base rather than the deprecated prebuilt FastAPI image. Proposed deployment uses one API process initially, a separate durable scheduled job runner and a TLS reverse proxy with restricted trusted proxy addresses. Migration/job coordination remains the application's responsibility. | INFRA-01; API-01 |
    | INF-11 | [Huawei RDS PostgreSQL + ECS](https://support.huaweicloud.com/intl/en-us/qs-rds-pg/rds_02_0016.html) | Vendor path uses ECS/RDS in the same region/VPC and controlled security groups with SSL. Demo default: ECS + RDS PostgreSQL, private DB connectivity, least-privilege user. GaussDB is a separate alternative requiring its own compatibility evidence, not a drop-in alias. AF-Johannesburg availability/quota still needs an account-level check. | CLOUD-01; DB-01 |
    | INF-12 | [ModelArts constraints](https://support.huaweicloud.com/intl/en-us/productdesc-modelarts/modelarts_01_0062.html) | Standard ModelArts requires same-region OBS and does not support encrypted OBS buckets. Restrict a dedicated private-access bucket to separately approved inputs/outputs; never place either classroom cohort there. This is service-specific, not a reason to disable encryption on all buckets. | CLOUD-02 |
    | INF-13 | [Label Studio security](https://labelstud.io/guide/security) | Official guidance includes internal-network isolation and production SSRF protection (`SSRF_PROTECTION_ENABLED=true`). Verify authentication, media URLs, outbound telemetry and exact edition capabilities; do not assume enterprise RBAC/audit features in Community. Annotation stays on the in-country private tier. | ANNOT-01 |
    | INF-14 | [Zergaw Cloud Server](https://zergaw.com/cloud-server/) | Provider publishes a cloud-server offering, but this does not establish purchased-host suitability, permitted OS image, accelerator, backups, access terms or data location for every service. Existing hosting list remains a shortlist. Obtain exact configuration/location/backup terms and a restore test. No provider purchase or price revalidation performed. | INFRA-01/02 |
    
    ## Proposed deployment baseline after this check
    
    - Android minSdk 29 / arm64 remains. Retain existing toolchain files pending BUILD-01; this review changes no dependencies. Validate actual target-SDK behavior on modern Android and resource constraints on the reference 2 GB phone.
    - In-country P1 and P2 use one simple API + database + job runner; annotation/training isolated from the public API. openEuler SP3 candidate, openGauss conditional on DB-01; PostgreSQL fallback explicit in ADR-005. Do not start with two pilot database engines.
    - Competition demo is a distinct ECS/RDS PostgreSQL deployment using synthetic records. ModelArts/OBS training is a separate optional capability; it is not required to unblock undergraduate capture tests. Record exact region/service quota before booking capacity.
    - DB listener and annotation UI are private. Only HTTPS API/demo routes are public. Keep service secrets out of images, source and logs; test certificate rotation, backups and restart recovery. Pilot backups, observability and model artefacts remain in-country for both cohorts.
    
    ## Evidence record to complete at G0/G4-U
    
    Record selected host/location, OS image/version/hash, container runtime/Compose, Python/API/driver/database versions, region/VPC and service availability, CA/trust configuration, encrypted-backup location/retention, restore evidence, Android dependency lock/checksums, model/converter/runtime hashes and device OS/RAM/page size. Retention, recovery-time and recovery-point objectives must be assigned numerical values by Lead/Sys before tests can pass. Record unresolved items as blocked, not verified.
    
    Scope limits: no cloud account inspection, provider contract, download/binary inspection, build, training, device operation or data transfer was performed. Previous 4 KB AAR binary finding remains dated prior evidence; verify the newly chosen binary. No new legal interpretation is made here.
    ✓ • 0ms

## Activity

    $ rg --files infra server dashboard tests tools | sort; sed -n '1,24p' docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md; sed -n '60,72p' docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md; sed -n '1,14p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; sed -n '535,560p' docs/DIMTS_ARCHITECTURE_CONTEXT.md; sed -n '183,214p' README.md
    dashboard/README.md
    infra/README.md
    infra/huawei-cloud/README.md
    infra/in-country/shared-hosting/README.md
    infra/in-country/shared-hosting/capabilities.example.json
    infra/in-country/vps/README.md
    infra/openeuler/README.md
    infra/research/README.md
    server/README.md
    server/config/README.md
    server/domain/README.md
    server/jobs/README.md
    server/security/README.md
    server/templates/README.md
    tests/acceptance/README.md
    tests/acceptance/runner.py
    tests/acceptance/test_harness.py
    tests/acceptance/vectors.json
    tests/infrastructure/test_hosting_readiness.py
    tools/check-hosting/check_hosting.py
    # Post-implementation acceptance suites
    
    Prepared 2026-09-27. Status of **every product case: NOT RUN**. This document specifies executable procedures/oracles for later runs. The [contract runner](../../tests/acceptance/README.md) is ready to connect to future adapters; hardware, service and field cases below require implementation and a provisioned test environment. No test result is claimed from this plan.
    
    Related: [STEM trial](../research/STEM_TRIAL_PLAN.md), [infrastructure evidence INF-01–14](../research/INFRASTRUCTURE_RECHECK.md), [G0–G5](../research/IMPLEMENTATION_REVIEW.md).
    
    ## Execution order and reporting
    
    1. BUILD, CORE/COHORT, MODEL, API/SYNC and disposable infrastructure tests on synthetic fixtures.
    2. DEVICE/STORE tests on reference devices; CLOUD/ANNOT and backup/restore negative cases. Resolve all blocking failures before G4-U.
    3. STEM-01–04 after G4-U. Report technical feasibility separately from adult-model exploratory metrics.
    4. EARLY-01–03 after STEM exit and G4-E. No early-grade release before G5-E.
    
    Default owners: Sys (build/device/storage/API/sync/infra/cloud/database), ML (models/features), Lead (cohorts/ethics/field validity); Lead + Sys own disclosure/withdrawal cases. Each row is blocking for its applicable gate unless explicitly exploratory. A blocked prerequisite, unsupported required feature or skipped case is **not a pass**. Changes to thresholds need an ADR dated before the held-out run.
    
    Use isolated, disposable test accounts/databases/devices with generated non-personal records. Never run purge, fault injection or restore cases against real pilot data. The test environment must identify itself as non-production before destructive cases begin. Do not expose debug/admin reset endpoints in release builds. Preserve evidence privately with: case ID, git/build/adapter SHA, OS/device, pinned dependencies, model/feature/spec/label versions, cohort, test-fixture hash/seed, UTC start/end, steps executed, actual result, pass/fail/blocked, redacted evidence path, defect and rerun ID. Logs must omit tokens, raw classroom content and participant identities.
    
    ## A. Build and contract suite (G0–G2)
    
    | ID | Preconditions and steps | Expected result / evidence |
    |---|---|---|
    | BUILD-01 | Clean checkout/cache on documented CI image. Resolve exact AGP/Gradle/JDK/Compose pins; assemble debug and release, run lint/unit tests and install release on API 29 arm64 plus a modern target-compatible device. | Reproducible successful builds/install; dependency manifest and checksums. Template greeting tests alone do not satisfy acceptance. Record actual JDK rather than confuse source target 11 with daemon runtime. |
    | BUILD-02 | Extract release APK/AAB-derived APKs; inspect every bundled native library, ELF load/RELRO alignment and ZIP packaging. Install/run on 4 KB and 16 KB environments. Exercise Lite and SQLCipher, not just launch activity. | No native loader/crash failures on supported configurations. Capture `adb shell getconf PAGE_SIZE`, official alignment-check and `zipalign -v -c -P 16 4` outputs. Incompatible device support cannot be claimed merely because sideload succeeded elsewhere. |
    | CORE-01–12 | Run supplied vectors against actual Python and Kotlin adapters independently. | Every oracle passes; null/missing/overlap/turn/latency/language/time-bin behavior matches. Preserve both outputs. Counts exact, floats abs ≤1e-6. |
    ## E. Database, hosting and cloud suite (G0/G3/G4-U)
    
    | ID | Preconditions and steps | Expected result / evidence |
    |---|---|---|
    | DB-01 | Use selected pinned server/driver. Connect with trusted cert, then untrusted CA, expired cert and wrong hostname. Check negotiated connection; test restricted app user and blocked public port. | Valid identity succeeds; every invalid identity fails. `sslmode=require` alone is insufficient. If openGauss arrangement cannot meet this, block pilot and ratify authenticated alternative/PostgreSQL; do not downgrade checks. |
    | DB-02 | Apply migrations to empty DB and upgrade a previous fixture schema; check transaction/unique/JSON/timezone behavior; backup, restore to fresh instance, compare logical row counts/hashes; replay withdrawal. | Exact chosen engine/driver passes; rollback/recovery documented; no unreviewed claim of compatibility with a different GaussDB/PostgreSQL engine. Record measured recovery point/time against agreed numerical objectives. |
    | INFRA-01 | Provision pinned supported openEuler image/container stack on disposable selected host. Restrict inbound ports, boot/reboot, kill API/job/DB container, rotate secrets/cert and fill disk. Run same scheduled report twice concurrently. | Services recover within declared objective; DB volumes durable; failed jobs retry idempotently; migrations execute once; no duplicate reports. Only intended HTTPS exposed, admin/DB private, no secrets baked into image/logs. |
    | INFRA-02 | Inspect provider storage, encrypted backups, logs, monitoring destinations and restore path; induce backup failure and revoke a credential. | P1/P2 personal data and replicas remain in-country; alerts observed; actual restore meets signed retention/RPO/RTO; no unverified overseas diagnostic service. Provider contract/location evidence retained. |
    | CLOUD-01 | Deploy synthetic demo to confirmed region; check ECS/RDS VPC/security groups, least-privilege users, TLS, public demo URL and separate pilot credentials. Attempt pilot credential/data submission to demo; rebuild from empty synthetic state. | Approved synthetic dataset only, pilot credentials rejected, DB private, reproducible page. No automatic cross-tier replication, logging or model upload. Exact region/quota/service evidence recorded. |
    | CLOUD-02 | Verify ModelArts/OBS same region and service-specific bucket settings. Attempt anonymous access and access outside allowed prefix; inspect generated logs/checkpoints; attempt unapproved cohort upload through app/export pipeline. | Training accesses only approved assets; anonymous/unauthorised access denied. No P1/P2 audio, posteriors, weights or identifiers. Unencrypted training bucket never reused for pilot storage. ModelArts failure does not block offline STEM test. |
    | ANNOT-01 | Self-host exact Label Studio edition privately with SSRF protection. Try unauthenticated UI/media/API access, cross-project permissions, unapproved external/metadata URL imports; monitor outbound traffic and browser-loaded resources. | Authorised annotators only; private media stays in-country; unsafe fetch blocked; telemetry/resources reviewed or blocked; no assumed Enterprise-only guarantees. Preserve minimum access test evidence. |
    
    ## F. Field progression suite (Lead + ML + Sys)
    # Dimts (ድምጽ) — Architecture Context Document
    
    > **Purpose of this file.** Single source of truth for designing the codebase. Hand it to any teammate, reviewer, or coding agent before they write code. It contains the problem, the constraints, the specs, the Huawei technology mapping, the proposed architecture, and the open decisions. It does **not** contain final code.
    >
    > **Codename:** Dimts (Amharic ድምጽ, "voice / sound") — placeholder, rename freely.
    > **Owner:** Anteneh F. Yimmam (team lead) · Hawassa University, Information Systems
    > **Status:** Reviewed proposal; implementation gates open · Last updated 2026-09-27
    > **Targets:** B.Sc. final-year project (defence Jun 2027) + Huawei ICT Competition 2026–2027, Innovation Competition
    
    ---
    
    ## 0. How to use this document
    
    1. Read §1–§6 before proposing any design. They are the *why* and the *must-not-break* constraints.
    
    ---
    
    ## 19. Open decisions (ADR backlog)
    
    | ADR | Question | Default proposal |
    |---|---|---|
    | ADR-001 | Segmentation as frame classification vs clustering diarization | Frame classification, 5 classes |
    | ADR-002 | Student architecture | E5: conv-only INT8 versus mixed-precision CRNN; defer conformer unless needed |
    | ADR-003 | Where classroom audio is used for training | In-country training node only; ModelArts for public data and pipeline; no pilot-derived export under this proposal; any boundary change needs a separate reviewed ADR |
    | ADR-004 | MindSpore Lite Java API vs JNI (C++) on Android | Java API first; JNI only if profiling demands |
    | ADR-005 | Backend language & framework | Python + FastAPI |
    | ADR-006 | Annotation tool | Self-hosted Label Studio (in-country) |
    | ADR-007 | Where real teacher identities live | Separate restricted table, or offline register |
    | ADR-008 | Dashboard stack | Server-rendered or light SPA; keep it simple |
    | ADR-009 | CI platform | GitHub Actions default; CodeArts if judged valuable for the pitch |
    | ADR-010 | Coaching rule table content | Co-designed with E-Learning Directorate and 2 pilot teachers |
    
    | ADR-011 | Separate competition/pilot deployments | Separate credentials, endpoints, databases, logs and backups; synthetic-only demo |
    | ADR-012 | Timeline, missingness, unknown LID and schema v2 | Freeze §5 and G1 golden fixtures before implementation |
    | ADR-013 | Auth, withdrawal, retention and aggregate disclosure | G4/G5 negative tests and legal/ethics answers |
    | ADR-014 | Undergraduate STEM first, then early-grade validation | User-directed stage order; cohort/schema details proposed in [ADR-014](adr/014-staged-classroom-trials.md) |
    
    ADR template: `Context · Decision · Alternatives considered · Consequences · Status · Date`.
    
    ---
    ### 5.1 Three tiers, and the line between two of them
    
    **① The phone (`edge/android/`)** is the product. It works with no network for days. Capture runs in a
    foreground service started from visible UI; features, both models, postprocessing and the indicator
    engine all run on the CPU in a streaming loop. Storage is Room over SQLCipher with a random database key
    wrapped by the Android Keystore, excluded from OS backup and device transfer. Sync is a WorkManager
    outbox with durable per-record acknowledgements and UUIDv7 record IDs (RFC 9562), so a record is never
    edited on two devices and no conflict resolution is needed.
    
    **② The in-country tier (`infra/openeuler/`)** holds everything that is personal data: lesson records,
    enrolment, the consent ledger, Research Mode audio, annotation and any model trained on classroom audio.
    It is one FastAPI process, one database and one job runner, deliberately boring. Article 22 of
    Proclamation No. 1321/2024 requires personal data collected in Ethiopia to be stored on a server or data
    centre located in Ethiopia, so this tier cannot be a foreign cloud region.
    
    **③ The Huawei Cloud demo (`infra/huawei-cloud/`)** exists because the 2026–27 Innovation Competition
    requires an entry deployed on Huawei Cloud with a visual demo page. Huawei Cloud has no Ethiopian region.
    The resolution is a genuinely separate deployment — separate account scope, credentials, endpoint,
    database, logs and backups — running the same server and dashboard code over **synthetic records** from a
    seeded generator that ships with the repository.
    
    **The boundary between ② and ③ is the most important line in the diagram.** Approved public-corpus data
    and cloud-trained artefacts may move from cloud to pilot after licence and privacy review. Nothing moves
    the other way: no classroom audio, no features, no posteriors, no soft labels, no trained weights, no logs
    and no identifiers, for either cohort. Public speech and synthetic mixtures of real voices are *not*
    automatically non-personal, so even those need a provenance review before they enter the cloud tier.
    
    **④ `indicators-core/`** is the single source of truth. The Python package is the reference; the Kotlin
    module is the port; `golden/` holds JSON timelines with expected outputs that both must reproduce to
    1e-6 in CI. This is what stops the phone and the server from quietly disagreeing about what a number means.
    
    ### 5.2 Six design decisions
    ✓ • 0ms

## Activity

    $ cat > /tmp/echo_docs.py <<'PY'
        from pathlib import Path
        
        def edit(name, old, new):
            p=Path(name); s=p.read_text()
            assert old in s, (name, old[:70])
            p.write_text(s.replace(old,new))
        
        def note(name, text):
            p=Path(name); s=p.read_text(); title,rest=s.split('\n',1)
            p.write_text(title+'\n\n'+text+'\n'+rest)
        
        p=Path('docs/research/CODEBASE_STRUCTURE.md')
        s=p.read_text(); contracts=s[s.index('## Contract changes worth making now'):]
        p.write_text('''# Codebase structure — Dimts
        
        Updated 2026-10-02. [ADR-015](../adr/015-capability-gated-hosting.md) adopts the structure and security contracts below. Runtime, database and deployment remain conditional on Zergaw's confirmed capabilities. The user authorised scaffolding only, with no product features. Research infrastructure is not yet available; Research Mode must remain disabled until separately prepared and approved.
        
        ## Current layout and status
        
        ```text
        Echo/
        ├── docs/
        │   ├── adr/015-capability-gated-hosting.md
        │   ├── security/ARCHITECTURE.md         # trust boundaries and SEC-01–12
        │   ├── testing/                       # product and hosting/security procedures
        │   └── research/                      # STEM-first plan, evidence, contracts
        ├── edge/android/                      # app is a Compose starter; other areas placeholders
        ├── indicators-core/                   # proposed Python/Kotlin implementations and golden data
        ├── ml/                                # research placeholders; no training on shared hosting
        ├── server/                            # framework-neutral contracts, no running application
        │   ├── api/                           # future HTTP adapters
        │   ├── domain/                        # authorised use cases and idempotency contracts
        │   ├── security/                      # authentication, scope and redacted audit contracts
        │   ├── config/                        # future fail-closed runtime configuration
        │   ├── db/migrations/                 # selected engine only; currently empty
        │   ├── jobs/                          # bounded scheduled CLI work, DB leases/checkpoints
        │   ├── templates/                     # future rendered reports/dashboard
        │   └── tests/                         # future product tests
        ├── dashboard/                         # design boundary; no separate frontend service
        ├── infra/
        │   ├── in-country/
        │   │   ├── shared-hosting/            # conditional default, derived records/reports only
        │   │   │   ├── README.md              # capability intake and deployment sequence
        │   │   │   └── capabilities.example.json  # deliberately unconfirmed
        │   │   └── vps/                       # fallback; compose/ and backup/ placeholders moved here
        │   ├── research/                      # separate private in-country setup; not provisioned
        │   ├── huawei-cloud/                  # isolated synthetic demo; ecs/rds/obs placeholders
        │   ├── openeuler/                     # legacy path redirect only
        │   └── ci/                            # future CI definitions
        ├── tools/check-hosting/               # executable offline evidence checker
        └── tests/
            ├── infrastructure/                # executable checker tests, synthetic evidence only
            └── acceptance/                    # 18 contract vectors and harness; needs product adapters
        ```
        
        Other existing ML, Android and tools placeholders remain. Named responsibilities above are not implemented modules. No server entry point, framework dependency, database migration or deployment automation is supplied before host selection; the deployment scaffolding is a capability manifest and runbook. Only the empty OS-specific backup/Compose placeholders moved.
        
        ## Stack and dependency boundaries
        
        One modular Python application handles the API and rendered pages. HTTP and CLI adapters call authorised domain use cases, which access scoped repositories; neither templates nor jobs bypass authorisation. The web process does not import ML frameworks, run inference, host annotation or train models. See [server contracts](../../server/README.md).
        
        Prefer PostgreSQL. Managed ASGI permits FastAPI/PostgreSQL; WSGI-only hosting permits Django with PostgreSQL or a supported MySQL/MariaDB version. Select and pin one path after provider evidence and tests. Do not build two backends or maintain cross-engine migrations. Scheduled CLI jobs must survive bounded runtimes and retries; no resident worker, Redis, Docker or root access is required for the shared profile. VPS is the fallback if required capabilities or isolation fail. openEuler is optional there; openGauss is not the baseline.
        
        The Android Kotlin/Compose, encrypted local storage and WorkManager plan is unchanged. ML choices remain subject to existing hardware/model gates; this infrastructure revision does not ratify an ML runtime.
        
        ## Data boundaries
        
        | Target | Allowed data / access | Release condition |
        |---|---|---|
        | Shared hosting | Derived records, minimal pseudonymous enrolment/consent references and private reports | Provider evidence + HOST/SEC suites + G4-U; no audio/features/training targets |
        | Private research environment | Consented research audio, restricted identity register, annotation and training | Machine, storage, backup, access controls and ethics approval prepared before enabling Research Mode |
        | Huawei Cloud demo | Synthetic records; other assets only after separate provenance/privacy/licence review | Separate identities, secrets, DB and logs; no pilot-derived import |
        
        Research is a separate environment, not another folder in the shared account. All pilot/research personal data, logs, replicas and backups stay in-country under the existing project boundary. The synthetic cloud demo should use the same selected app/engine when available. Neither the undergraduate trial nor a complete capability checklist approves early-grade release.
        
        ## Verification
        
        [Hosting/security procedures](../testing/HOSTING_SECURITY_TEST_PLAN.md) cover provider selection and future implementation. [Existing product suites](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) still govern device, model, indicator and cohort gates. The checker verifies declared evidence completeness only; it cannot establish provider truth or actual security. See [infra](../../infra/README.md) for commands and profile selection.
        
        '''+contracts)
        
        note('docs/DIMTS_ARCHITECTURE_CONTEXT.md', '> **2026-10-02 infrastructure revision:** [ADR-015](adr/015-capability-gated-hosting.md), [current structure](research/CODEBASE_STRUCTURE.md) and [security contracts](security/ARCHITECTURE.md) govern deployment. Zergaw capabilities are unconfirmed. Research Mode remains blocked until separate in-country research infrastructure is available. This pass adds structure/contracts and an offline evidence checker, not product features.')
        edit('docs/DIMTS_ARCHITECTURE_CONTEXT.md','Last updated 2026-09-27','Last updated 2026-10-02 (infrastructure; prior research evidence retains its dates)')
        edit('docs/DIMTS_ARCHITECTURE_CONTEXT.md','| Server OS | **openEuler** (24.03 LTS SP3 candidate) | In-country server OS at Hawassa University | Huawei-originated open-source Linux. |','| Server OS | Provider-managed on shared hosting | OS not selected by the app | Supported Linux LTS on VPS fallback; openEuler optional after verification. |')
        edit('docs/DIMTS_ARCHITECTURE_CONTEXT.md','| Database | **openGauss** | Indicator records, reports, aggregates, consent ledger | Conditional on exact-driver, migration and authenticated-TLS tests: documented openGauss verify-full limitation is a blocker unless resolved; PostgreSQL is the explicit fallback (INF-09). |','| Database | **PostgreSQL preferred** | Indicator records, reports, aggregates, consent ledger | Django/WSGI may use supported MySQL/MariaDB if required by the host. One tested engine; openGauss removed from baseline (ADR-015). |')
        edit('docs/DIMTS_ARCHITECTURE_CONTEXT.md','  Research Mode: separate consent → encrypted audio → in-country transfer','  Research Mode [BLOCKED]: needs separate private in-country infrastructure')
        edit('docs/DIMTS_ARCHITECTURE_CONTEXT.md','IN-COUNTRY PILOT (host selected through S6)\n  FastAPI → chosen/tested database → private weekly reports','IN-COUNTRY PILOT (Zergaw shared candidate; S6/S12 capability gates)\n  Conditional Python app → chosen/tested database → private weekly reports')
        edit('docs/DIMTS_ARCHITECTURE_CONTEXT.md','  Research audio/annotation and classroom training stay in-country','  Derived-only host; bounded scheduled CLI jobs\n\nPRIVATE IN-COUNTRY RESEARCH (not yet provisioned)\n  Research audio/annotation/training; separate endpoint, access and backups')
        edit('docs/DIMTS_ARCHITECTURE_CONTEXT.md','## 11. Privacy, security, and ethics architecture\n','## 11. Privacy, security, and ethics architecture\n\nThese are required future controls, not implemented guarantees. [SEC-01–12](security/ARCHITECTURE.md) define trust boundaries, role access, secrets, web protection and operational gates. Provider encryption/isolation/location remain unconfirmed.\n')
        edit('docs/DIMTS_ARCHITECTURE_CONTEXT.md','| Transport | TLS 1.2+, certificate pinning in app. |','| Transport | HTTPS with certificate/hostname validation; authenticated remote DB TLS or verified private local socket. Optional app pinning requires tested backup pins and rotation. |')
        p=Path('docs/DIMTS_ARCHITECTURE_CONTEXT.md'); s=p.read_text(); a=s.index('## 12. Proposed repository'); b=s.index('## 13. Interfaces', a)
        s=s[:a]+'''## 12. Repository structure and backend choice
        
        The [current structure](research/CODEBASE_STRUCTURE.md) owns the repository map and distinguishes real files from future responsibilities. `server/` contains framework-neutral architecture contracts; `infra/in-country/shared-hosting/` is the conditional pilot target, `infra/in-country/vps/` is the fallback, `infra/research/` plans separate private research, and `infra/huawei-cloud/` is synthetic-only. `infra/openeuler/` redirects to the fallback.
        
        **Backend (ADR-005 as constrained by ADR-015):** one Python application and rendered pages; FastAPI/PostgreSQL for confirmed managed ASGI, or Django with a supported selected database for WSGI-only hosting. No framework or database selected until provider evidence. No ML packages in the shared backend. Cron-driven bounded jobs replace a resident worker requirement.
        
        ---
        
        '''+s[b:]; s=s.replace('server on openEuler + openGauss','capability-selected in-country server').replace('sovereign server (openEuler/openGauss)','in-country server (selected supported stack)').replace('| ADR-005 | Backend language & framework | Python + FastAPI |','| ADR-005 | Backend language & framework | Conditional Python ASGI/FastAPI or WSGI/Django; one selected engine, ADR-015 |').replace('| ADR-008 | Dashboard stack | Server-rendered or light SPA; keep it simple |','| ADR-008 | Dashboard stack | Server-rendered pages in the selected backend (ADR-015) |')
        s=s.replace('ADR template:', '| ADR-015 | Capability-gated hosting and security | [Adopted structure/contracts](adr/015-capability-gated-hosting.md); runtime/DB conditional; Research Mode blocked pending infrastructure |\n\nADR template:'); p.write_text(s)
        
        note('docs/research/RESEARCH_PLAN.md','> **2026-10-02 update:** [ADR-015](../adr/015-capability-gated-hosting.md) changes hosting selection to a capability-gated shared-hosting candidate with VPS fallback. Runtime/DB and research machine are unconfirmed. Research Mode and research-dependent field work stay blocked pending infrastructure and approvals. Undergraduate STEM remains first, followed by separate early-grade gates; scaffolding is not gate sign-off.')
        p=Path('docs/research/RESEARCH_PLAN.md'); s=p.read_text(); lines=s.splitlines()
        for i,line in enumerate(lines):
            if line.startswith('| S6 |'): lines[i]='| S6 | Confirm managed ASGI or WSGI, exact Python/framework/DB/driver versions and migration/transaction tests. Prefer PostgreSQL; supported MySQL/MariaDB allowed with Django. Verify remote DB TLS identity or protected local socket. Zergaw shared candidate first; VPS fallback if required controls fail, per ADR-015 | W1, blocked on evidence |'
            if line.startswith('| **S10**'): lines[i]='| **S10** | **Competition cloud stack:** same selected application and DB engine where available, independent synthetic data/credentials; ECS with managed DB optional after region/quota verification. OBS/ModelArts only as needed. Reproducible deployment after runtime decision. | W5 |'
            if line.startswith('| S11 |'): lines.insert(i+1,'| S12 | Complete provider capability evidence and HOST-01–04/SEC-01–12 tests; prepare separate in-country research host, protected transfer, annotation access and restore/deletion evidence before Research Mode. Daily backup/RPO ≤24 h/RTO ≤8 h provisional targets; finite approved retention required | Before G4-U / Research Mode |'); break
        p.write_text('\n'.join(lines)+'\n')
        note('docs/research/IMPLEMENTATION_REVIEW.md','> **2026-10-02 follow-up:** [ADR-015](../adr/015-capability-gated-hosting.md) adopts shared-hosting/VPS/research separation, security contracts and an offline evidence checker. Runtime/DB remain unselected; research infrastructure is unavailable. G0 now includes S12 capability evidence and G4 includes [HOST/SEC tests](../testing/HOSTING_SECURITY_TEST_PLAN.md). Research Mode cannot proceed before its own infrastructure and approvals. The baseline below describes the earlier review; scaffolding changes do not implement product controls.')
        note('docs/research/INFRASTRUCTURE_RECHECK.md','> **2026-10-02 follow-up:** [ADR-015](../adr/015-capability-gated-hosting.md) supersedes OS-specific deployment recommendations below. Official FastAPI ASGI and Django WSGI/database/deployment documentation was rechecked (links in ADR-015 and the security contract). Zergaw shared-plan capabilities could not be verified from the provider site; user screenshots advertise Python/SSH, not a confirmed gateway, database, quotas or cron. All provider facts remain unconfirmed. INF-01–14 retain their original evidence dates; they are not a fresh version audit.')
        edit('docs/research/INFRASTRUCTURE_RECHECK.md','- In-country P1 and P2 use one simple API + database + job runner; annotation/training isolated from the public API. openEuler SP3 candidate, openGauss conditional on DB-01; PostgreSQL fallback explicit in ADR-005. Do not start with two pilot database engines.','- In-country P1/P2: one capability-selected Python application, one database (PostgreSQL preferred) and bounded scheduled CLI jobs. Shared hosting is conditional; supported Linux VPS is the fallback. No openGauss baseline or shared-host OS/container requirement. Research infrastructure is separate and not yet available; Research Mode remains blocked.')
        edit('docs/research/INFRASTRUCTURE_RECHECK.md','- Competition demo is a distinct ECS/RDS PostgreSQL deployment using synthetic records.','- Competition demo is a distinct synthetic deployment using the same selected application/engine where available; ECS/managed DB selections remain conditional.')
        edit('docs/research/INFRASTRUCTURE_RECHECK.md','Record selected host/location, OS image/version/hash, container runtime/Compose, Python/API/driver/database versions,','Record selected host/location, managed gateway (shared) or OS image/container versions (VPS only), Python/API/driver/database versions,')
        
        p=Path('docs/research/HOSTING_OPTIONS.md'); s=p.read_text(); start=s.index('## The screenshot'); end=s.index('## Providers that offer', start)
        s=s[:start]+'''## 2026-10-02 correction: shared hosting is a conditional candidate
        
        The earlier rejection of all shared hosting was too broad. Python application support can be sufficient for derived records and reports without root access. The user intends Zergaw Standard and supplied screenshots advertising Ethiopia hosting, Python/SSH, weekly backup, 100 GB and ETB 7,035/year. These are screenshot claims, not verified capacity or a current quote; the cropped “Unlimited” rows do not establish application resources.
        
        Neither the dashboard image nor “Python support” establishes ASGI, WSGI, Passenger, installed databases, scheduler rights or tenant isolation. The user confirmed these are **not yet confirmed**. Do not assume a particular gateway or infer feasibility from storage quota.
        
        Use [ADR-015](../adr/015-capability-gated-hosting.md) and the [shared-hosting intake](../../infra/in-country/shared-hosting/README.md): managed ASGI permits FastAPI/PostgreSQL; WSGI permits Django with a supported selected database. Verify dependency installation, process/restart and job limits, transaction semantics, private files/secrets, protected DB access, local backups/logs, and measured load. Weekly backup alone fails the proposed daily recovery target. Use a VPS if any required capability or security boundary fails.
        
        Shared hosting never receives research audio, annotation or training workloads. No separate research machine is available yet: [research setup](../../infra/research/README.md) must be completed before Research Mode. Root access and an openEuler image are not prerequisites for the shared application profile.
        
        The historical provider shortlist below is retained as research, not as current pricing, compliance certification or a selected deployment. Reconfirm every commercial and location claim with the provider before selection.
        
        '''+s[end:]
        a=s.index('## Recommendation'); s=s[:a]+'''## Recommendation
        
        Assess Zergaw Standard first using the capability manifest and HOST/SEC acceptance plan. Its suitability is **unknown**, not rejected or approved. Compare the full cost of daily backups, reliable scheduled jobs, support and capacity with a local VPS if the gates cannot be met. Use one maintained application and one tested database; do not install openGauss merely for a competition technology mapping. Retain alternative providers as fallbacks with fresh contractual and technical evidence.
        '''
        s=s.replace('openEuler service-pack pinning, exact database/driver TLS identity validation (openGauss limitation), provider backup/location terms and restore tests are mandatory selection gates.','exact runtime/database compatibility, authenticated connections, provider backup/location terms and restore tests are mandatory selection gates; the October revision below removes the OS-specific baseline.')
        s=s.replace('Zergaw\'s DBaaS lists PostgreSQL/MySQL/MariaDB, not openGauss by name. openGauss is PostgreSQL-lineage (per `CLAIMS_AUDIT.md` §4, 7j), so a managed *PostgreSQL* instance is a reasonable substitute if openGauss itself isn\'t offered — this feeds back into ADR-005 (backend/database choice).','The historical DBaaS list does not establish engines included with Standard. Confirm the exact plan, versions, driver, transaction, migration and connection-security behavior. PostgreSQL is preferred, with supported MySQL/MariaDB conditional on the Django path.')
        p.write_text(s)
        note('docs/research/ASSUMPTIONS.md','> **2026-10-02 deployment addendum:** Hosting/gateway/database/cron capabilities remain unconfirmed. [ADR-015](../adr/015-capability-gated-hosting.md) supersedes OS-specific hosting assumptions: shared candidate, VPS fallback, PostgreSQL preferred, one conditional framework/engine. Separate research infrastructure is not available; Research Mode is blocked pending setup and approvals. The historical register below is retained as evidence, not deployment sign-off.')
        note('docs/research/README.md','> **Current infrastructure (2026-10-02):** [ADR-015](../adr/015-capability-gated-hosting.md), [structure](CODEBASE_STRUCTURE.md), [security](../security/ARCHITECTURE.md), [HOST/SEC suites](../testing/HOSTING_SECURITY_TEST_PLAN.md) and [deployment scaffolding](../../infra/README.md). Hosting remains conditional; research infrastructure is not yet available. Only the evidence checker and its tests are executable infrastructure additions.')
        edit('docs/research/README.md','including the two-tier (competition cloud / in-country pilot) deployment.','including shared-hosting, VPS fallback, separate research and synthetic demo boundaries.')
        edit('docs/research/README.md','for the `infra/openeuler` sovereign-tier server.','with the current capability-gated shared-hosting correction and VPS alternatives.')
        
        note('README.md','> **Infrastructure update — 2026-10-02:** The [current repository structure](docs/research/CODEBASE_STRUCTURE.md), [ADR-015](docs/adr/015-capability-gated-hosting.md), [security architecture](docs/security/ARCHITECTURE.md) and [deployment scaffolding](infra/README.md) supersede the earlier infrastructure illustration and OS/database choices below. Zergaw shared hosting is conditional on provider evidence; FastAPI/ASGI or Django/WSGI and the database remain unselected. Research Mode stays blocked until separate private in-country infrastructure is ready. This repository pass adds contracts/scaffolding and checker tests, not product features.')
        edit('README.md','**② The in-country tier (`infra/openeuler/`)** holds everything that is personal data: lesson records,\nenrolment, the consent ledger, Research Mode audio, annotation and any model trained on classroom audio.\nIt is one FastAPI process, one database and one job runner, deliberately boring.','**② The in-country tier (`infra/in-country/`)** uses a conditional shared-hosting application for derived\nlesson records, minimal enrolment/consent references and private reports, with a VPS fallback. It uses\none selected Python framework/database and bounded scheduled jobs. Research audio, annotation and\ntraining require separate private infrastructure (`infra/research/`), which is not yet available.')
        PY
        python3 /tmp/echo_docs.py
    ✓ • 0ms

## Assistant

I corrected an earlier recommendation that rejected shared hosting outright: a supported Python application host may be sufficient for derived records and reports. Zergaw Standard is still unverified, and its advertised weekly backup falls short of the proposed daily recovery target. The updated plans keep both points explicit.

## Activity

    $ cat > docs/testing/HOSTING_SECURITY_TEST_PLAN.md <<'EOF'
        # Hosting and security acceptance procedures
        
        Prepared 2026-10-02 for [ADR-015](../adr/015-capability-gated-hosting.md) and [SEC-01–12](../security/ARCHITECTURE.md). **All host/product cases below: NOT RUN.** The offline checker and its unit tests validate evidence records only. They do not exercise a provider, app, database or device.
        
        ## Prerequisites and evidence
        
        Sys owns HOST tests; Sys + Lead own SEC tests and privacy decisions. Use an explicitly identified disposable environment, synthetic records, isolated accounts and test keys. Load/fault tests on shared infrastructure require provider-approved limits; do not attack adjacent tenants. Provider-led isolation evidence plus scoped tests must establish the boundary. No destructive testing on pilot data.
        
        Before testing, record selected profile, framework/runtime/database/driver pins, quotas, data destinations, numeric request/batch/rate/job limits, finite retention periods and named owners. Proposed recovery targets: backup interval and RPO ≤24 h, measured RTO ≤8 h. Lead/Sys must approve the targets and any change before the run. Decide expected simultaneous classrooms, offline backlog per device and acceptable sync/report delay before the capacity test; storage allowance alone gives no concurrency estimate.
        
        Each result records case ID, commit/config hash, UTC time, fixture seed/hash, steps, expected/actual result, pass/fail/blocked, restricted redacted evidence reference, operator and rerun/defect reference. Use the report template in [the product plan](POST_IMPLEMENTATION_TEST_PLAN.md). Missing prerequisites are BLOCKED, not passed. Failed applicable controls block real-data launch. Host location and operator access require contract/process evidence in addition to technical tests.
        
        ## HOST suite
        
        | ID | Procedure after provider selection / implementation | Pass criteria |
        |---|---|---|
        | HOST-01 — capabilities and isolation | Obtain written plan-specific gateway, Python/package, database, cron, quota, filesystem, support-access, encryption and location terms. In a disposable slot install pinned dependencies, launch through the managed gateway, invoke a scheduled CLI task and restart through the supported mechanism. Request provider isolation evidence; test only owned accounts/paths. | Exact supported versions and tested entry/restart path recorded; dependencies and jobs work within quotas. Private files and credentials are inaccessible from public paths/other owned test accounts. Unknown required capability blocks selection. |
        | HOST-02 — database and recovery | Apply empty/upgrade migrations on the selected engine. Test remote TLS valid/untrusted/expired/wrong-host certs, or documented private socket ownership/permissions. Exercise restricted app/migration roles, concurrent inserts/uniqueness and rollback. Back up, mutate/delete fixtures, restore to fresh DB and replay deletion ledger before enabling service. | Valid connection only, minimum privileges, one durable row per idempotent key; migrations recover safely. Deleted records/reports stay deleted after restore. Measured backup cadence, RPO and RTO meet approved targets; archives/keys/logs remain in-country. |
        | HOST-03 — capacity and scheduled work | Simulate the agreed simultaneous STEM classrooms reconnecting with maximum approved backlog, bounded payloads and duplicate requests. Run report/purge work concurrently, cancel tasks mid-batch, exhaust a test-account quota within provider limits, skip a cron run, expire a lease and retry. | Measured sync latency, report deadline and resource headroom meet predeclared budgets within plan quotas. No acknowledged data loss/duplicate reports; jobs checkpoint/recover; missed-run and quota alerts reach the named operator. Failure requires reducing documented load or changing host before launch. |
        | HOST-04 — release and rollback | Build an allowlisted app/static bundle from a clean checkout; scan for credentials, PDFs, research assets and debug settings. Deploy using supported private config, run migration once and smoke-test. Rotate app/DB credentials, roll back app with documented compatible schema/recovery, restore a fresh instance. Attempt pilot authentication on synthetic demo. | Reproducible bundle contains only required code/assets/pins; no research dependencies or secrets. Debug disabled, trusted hosts/proxies exact, rollback/recovery documented, old credentials fail. Demo rejects pilot identities and has separate DB/logs/backups. |
        
        ## SEC suite
        
        | ID | Negative cases / procedure | Required result |
        |---|---|---|
        | SEC-01 | Create two instructors in different institutions/cohorts plus aggregate-only and operator identities. Substitute user/cohort/institution IDs in every record/report/export/read/write route; exercise jobs and missing scope. | Default deny; ownership derived from enrolment. No cross-user/cohort data or role escalation. Every route and job has a tested policy; errors do not disclose record existence. |
        | SEC-02 | Reuse/expire enrolment codes; use expired/revoked tokens; recover an account; throttle login/recovery/enrolment bursts across account/source; exercise operator MFA or restricted access. | One-use enrolment, scoped credentials, immediate server revocation, documented recovery and bounded abuse. No global victim lockout or account enumeration. Operator access matches the approved mechanism. |
        | SEC-03 | Submit cookie-authenticated mutations without/wrong CSRF tokens; inject HTML/script in allowed text; forge Host/Origin/forwarded headers and frame reports. Inspect cookies, CSP, cache and error responses. | CSRF rejected; output escaped; exact trusted host/proxy checks; Secure/HttpOnly/SameSite cookies; no framing, private-page caching or debug stack traces. Native requests require valid device auth regardless of CORS. |
        | SEC-04 | Request config, source, dotfiles, backup/temp/archive paths through the public document root and traversal/encoded variants. Check filesystem and DB access with owned low-privilege accounts; review provider administrator policy. | No private files exposed; least-privilege access verified. Provider privileged access and residual risk explicitly accepted or hosting rejected; a separate folder alone is not isolation. |
        | SEC-05 | Use invalid/expired/wrong-host HTTPS certificates from test clients and invalid remote DB certificates; spoof forwarded headers; rotate valid certificates. If pinning selected, test backup pins and recovery. | Invalid peer identity fails closed; valid rotation works; scheme/client identity cannot be spoofed via untrusted proxy headers. No TLS verification bypass. Private local DB socket alternative is evidenced in HOST-02. |
        | SEC-06 | Inject canary credentials and personal fields into success/failure paths; inspect app/proxy/job/audit logs and error bodies. Rotate distinct app/demo/backup secrets and exercise revoked credentials. | No tokens/passwords/content leaked; audit stores only approved metadata. Old credentials fail; rotation preserves allowed operations; separate deployment credentials cannot cross-authenticate. |
        | SEC-07 | Lose ACK after commit, retry identical and altered payloads, use same ID across owners, partially fail a batch, race sync/report with consent withdrawal. Restore an old client outbox. | Durable ACK only; identical retries idempotent, altered content rejected, scopes isolated; withdrawal wins and stale retries cannot resurrect records or reports. |
        | SEC-08 | Start concurrent report/purge runs; stop after lease acquisition and after commit; replay old checkpoints; delay cron beyond alert threshold. Restore a backup predating withdrawal while service is offline. | Bounded work resumes without duplicate outputs or stale jobs overriding purge. Missed-run alerts observed. Deletion replay completes before service reopens; archived retention handled by the approved policy. |
        | SEC-09 | Send audio/binary data, base64 audio in extra fields, fine-grained timelines, unknown fields and oversized/batch/rate-limit requests. Advance test clock across retention deadlines, including failed/retried jobs. Try enabling Research Mode without its readiness approval. | Strict schema/semantic allowlist and numeric limits reject unsupported content; finite retention enforced for records, reports, logs and backups. Shared-host research enablement fails closed. No raw upload route exists. |
        | SEC-10 | Mix STEM/early-grade/institution records; submit repeated lessons from one teacher; request small overlapping groups or time windows and difference successive releases. | Distinct-teacher counts, equal weighting and suppression rules hold; no individual reconstruction or cross-cohort coaching. Unsafe aggregate withheld; no k-threshold alone claimed as anonymisation. |
        | SEC-11 | Inspect backup destinations, ciphertext, key separation and retention. Restore without key, then with authorised key. Review pinned dependency/artifact manifests, scanner results and deploy access; simulate backup failure. | Unauthorised restore fails; authorised restore meets HOST-02 and deletion replay. Locality and daily cadence verified; no whole-repo deployment, public archives or unreviewed critical dependency risk; backup failure alerts observed. |
        | SEC-12 | Run device storage, backup/transfer/shared-account and model-integrity cases from the product plan. Lose/invalidate key, sign out/switch user, revoke locally offline, reconnect after remote revocation, tamper with model manifest. | No cross-account or OS-backup disclosure; explicit recovery, no silent plaintext fallback; local withdrawal immediate, server rejects revoked device on contact; incompatible/tampered model rejected. |
        
        ## Research and cohort gates
        
        [Research setup](../../infra/research/README.md) must provide separate host/access, protected transfer and acknowledgement, audio integrity, backup/restore/deletion, consent linkage and annotation isolation evidence (ANNOT-01 and relevant SEC cases) before Research Mode. No machine has been confirmed, so these cases are BLOCKED until provisioned. Derived-only shared hosting cannot satisfy research prerequisites.
        
        G4-U authorises the undergraduate STEM phase only after its ethics/eligibility and applicable technical tests. G4-E/G5-E remain separate before early-grade capture/release. Synthetic offline checks may proceed while these gates remain closed. Model accuracy, low-end phone performance and field validity remain governed by the existing product suites.
        EOF
        cat > tools/check-hosting/README.md <<'EOF'
        # Offline hosting evidence checker
        
        This standard-library tool checks the shape and declared completeness of the conditional Zergaw shared-hosting profile. It performs no network probes, deployment or provider verification. It is not a runtime security control.
        
        Copy `infra/in-country/shared-hosting/capabilities.example.json` to a private `*.local.json` file. Fill every value, non-secret evidence reference and ISO confirmation date from provider answers or measured test evidence. Keep credentials and personal data out of this record; local files are ignored by git. The example remains intentionally unconfirmed.
        
        From the repository root:
        
        ```sh
        python3 tools/check-hosting/check_hosting.py infra/in-country/shared-hosting/capabilities.local.json
        PYTHONDONTWRITEBYTECODE=1 python3 -m unittest discover -s tests/infrastructure -p 'test_*.py'
        ```
        
        Exit codes: `0` complete declarations (still requires independent evidence review and HOST/SEC/cohort approvals), `1` blocked/incompatible declarations, `2` unreadable or invalid JSON. Errors name fields and omit supplied values. Duplicate JSON keys and non-finite numbers are rejected. Valid evidence references are not dereferenced or authenticated; confirmation dates are not a freshness guarantee. Recheck evidence before every deployment and material provider change.
        
        Limits use the units in field names: CPU limit in vCPU equivalents, process/connection counts, RAM in MB, request/cron durations in seconds, backup/RPO/restore durations in hours, backup retention in days. Provider vague “unlimited” claims cannot substitute for measured/contractual limits. Sample limits in unit tests are synthetic and do not establish a feasible classroom size or retention policy. Request/batch/rate and other runtime retention settings belong in future validated application configuration; this manifest is not the entire configuration schema.
        
        The accepted profiles are ASGI/FastAPI/PostgreSQL or WSGI/Django with PostgreSQL/MySQL/MariaDB. Exact supported dependency compatibility remains an evidenced check, not something inferred from the Python version pattern. Research Mode and raw data are forbidden in this shared profile. See [infra runbook](../../infra/README.md) and [future host/security tests](../../docs/testing/HOSTING_SECURITY_TEST_PLAN.md).
        EOF
        python3 - <<'PY'
        from pathlib import Path
        p=Path('docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md'); s=p.read_text(); title,rest=s.split('\n',1); s=title+'\n\n> **2026-10-02 hosting revision:** [HOST-01–04 and SEC-01–12](HOSTING_SECURITY_TEST_PLAN.md) add shared-hosting, security and research readiness procedures. Run the applicable profile: shared hosting does not require root, Docker or a selectable OS; VPS/container tests apply only if selected. Research Mode remains blocked pending separate infrastructure. Offline checker success is not a product/security test pass.\n'+rest
        s=s.replace('If openGauss arrangement cannot meet this, block pilot and ratify authenticated alternative/PostgreSQL; do not downgrade checks.','For a private local socket instead, verify owner/permissions and provider isolation; do not silently substitute unverified TCP. Test the one selected PostgreSQL/MySQL/MariaDB engine; no openGauss baseline.')
        s=s.replace('Provision pinned supported openEuler image/container stack on disposable selected host.','VPS profile only: provision the selected supported Linux image and runtime/container stack on a disposable host. Shared profile: run HOST-01–04 instead; do not attempt host reboot or container control without provider support.')
        s=s.replace('check ECS/RDS VPC/security groups,','check selected ECS/database topology (RDS optional), VPC/security groups,')
        p.write_text(s)
        p=Path('tests/infrastructure/test_hosting_readiness.py'); s=p.read_text().replace('from copy import deepcopy\n','').replace('from datetime import date, timedelta','from datetime import date').replace('import json\n',''); s=s.replace("float('inf'), float('nan')", "float('inf'), float('nan'), 10 ** 400"); p.write_text(s)
        p=Path('tools/check-hosting/check_hosting.py'); s=p.read_text(); s=s.replace('not math.isfinite(value) or value <= 0','(isinstance(value, float) and not math.isfinite(value)) or value <= 0 or value > 10 ** 12'); p.write_text(s)
        PY
    ✓ • 0ms

## Activity

    $ git diff --check; git diff --stat; cat infra/README.md infra/research/README.md server/config/README.md docs/adr/015-capability-gated-hosting.md
     .gitignore                                    |  23 ++++
     docs/DIMTS_ARCHITECTURE_CONTEXT.md            |  86 ++++---------
     docs/research/ASSUMPTIONS.md                  |   2 +
     docs/research/CODEBASE_STRUCTURE.md           | 172 +++++++++-----------------
     docs/research/HOSTING_OPTIONS.md              |  25 ++--
     docs/research/IMPLEMENTATION_REVIEW.md        |   2 +
     docs/research/INFRASTRUCTURE_RECHECK.md       |   8 +-
     docs/research/README.md                       |   6 +-
     docs/research/RESEARCH_PLAN.md                |   7 +-
     docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md |   8 +-
     infra/openeuler/backup/.gitkeep               |   0
     infra/openeuler/compose/.gitkeep              |   0
     12 files changed, 136 insertions(+), 203 deletions(-)
    # Deployment targets
    
    Current architecture, 2026-10-02. **Scaffolding only; no running service or provisioning script.** The user intends Zergaw Standard shared hosting. Runtime/database/cron/resource and storage-location capabilities are unconfirmed.
    
    | Target | Purpose | Allowed data | Readiness |
    |---|---|---|---|
    | [in-country/shared-hosting](in-country/shared-hosting/README.md) | Preferred lightweight pilot API + rendered dashboard + scheduled reports/purge | Pseudonymous derived records, consent references and reports; still personal data | Conditional on capability evidence and security tests |
    | [in-country/vps](in-country/vps/README.md) | Fallback when shared-host controls/runtime cannot meet requirements | Same derived-data scope | Provider/OS and versions unselected |
    | [research](research/README.md) | Separate in-country recording transfer, annotation and training | Approved research audio, enrolment identity register, derived research artefacts | Research Mode disabled until provisioned and approved |
    | [huawei-cloud](huawei-cloud/README.md) | Isolated competition demo | Synthetic records only; separate approval for public ML assets | No pilot credentials or connectivity |
    
    One application is deployed to the chosen pilot host and, separately, the synthetic demo. Do not build two production web frameworks or a SQL compatibility layer for hypothetical databases. [ADR-015](../docs/adr/015-capability-gated-hosting.md) defines the selection rule. Shared hosting does not require Docker/root or permit choosing its OS.
    
    Run the offline evidence checker after completing a private copy of the host template:
    
    ```sh
    python3 tools/check-hosting/check_hosting.py infra/in-country/shared-hosting/capabilities.example.json
    ```
    
    The example intentionally returns **BLOCKED**. A passing check only establishes completeness/consistency of declared evidence; it does not probe the provider, certify security, authorise recording or deploy anything. Run HOST/SEC tests before G4-U. See [security architecture](../docs/security/ARCHITECTURE.md).
    # Separate in-country research environment
    
    Scope: approved recording transfer/storage, private annotation, identity register and ML training. Shared hosting never receives raw recordings, embeddings, fine-grained timelines or model-training jobs. No public annotation URL or public object bucket.
    
    Provisioning and access remain unconfirmed. Until readiness/consent is established, Research Mode recording/export stays disabled; the offline Deployment Mode and synthetic tests may proceed within their gates. Prefer a university/private in-country machine with verified backup, access and operational ownership. A separate folder on the shared web account is not this boundary.
    
    Require separate credentials and encryption keys, per-lesson consent references, authenticated transfer/integrity/durable receipt, finite retention, controlled annotator access and restore/deletion tests. Do not store participant identity registers in git. Only explicitly approved signed model releases flow to the phone/pilot. No pilot-trained weights, soft labels, logs or classroom data flow to the Huawei Cloud demo.
    # Configuration contract (future startup gate)
    
    Configuration is environment-specific, never a checked-in secret. Validate before accepting requests:
    
    - Explicit deployment purpose: pilot, synthetic demo or local synthetic tests. Demo cannot enable real enrolment or research ingestion.
    - One framework/runtime and DB driver with pinned supported versions; exact allowed hostnames and browser origins; debug off remotely.
    - TLS/trusted-proxy configuration tied to the actual host; verify remote DB identity or use a confirmed private local socket.
    - Private secret source outside document root, separate DB/app/migration/backup credentials, revocation/session settings and request limits.
    - Approved consent/cohort scope, finite retention and deletion/backup policy; Research Mode disabled by default and separately configured on research infrastructure.
    
    Unknown/missing production settings must refuse startup. The host evidence checker is an offline planning tool; it does not implement this application startup check.
    # ADR-015: Capability-gated shared hosting and separated research infrastructure
    
    Date: 2026-10-02. Status: **Repository structure/security contracts adopted for this pass; framework, database and live deployment remain conditional.**
    
    ## Context and user decisions
    
    The user intends to use Zergaw Standard shared hosting in Ethiopia. They confirmed that runtime/database/cron support is not yet known, requested structure/contracts/deployment scaffolding only (no product features), and reported that a separate in-country research machine is not yet available. Earlier documents assumed an openEuler/openGauss VPS and overgeneralised limitations of shared Python hosting.
    
    ## Decision
    
    1. Make `infra/in-country/shared-hosting/` the preferred pilot target; move empty OS-specific VPS scaffolding to `infra/in-country/vps/`. Keep `infra/openeuler/README.md` as a historical redirect.
    2. Host a small Python application for derived-record ingest, rendered reports and scheduled tasks. Research audio/annotation/training move to a separate `infra/research/` boundary. Research Mode remains blocked until provisioned/approved. Preserve the separate synthetic Huawei Cloud target.
    3. Select one framework after evidence: FastAPI for verified managed ASGI; Django for managed WSGI-only. Python support or SSH does not establish either. Do not ship two frameworks or implement a speculative adapter layer. [FastAPI deployment](https://fastapi.tiangolo.com/deployment/manually/), [Django WSGI](https://docs.djangoproject.com/en/5.2/howto/deployment/wsgi/).
    4. Prefer PostgreSQL. If WSGI hosting provides supported MySQL/MariaDB, choose one after transactional/idempotency/migration/restore tests; Django supports these engines. Remove openGauss as a baseline requirement. [Django databases](https://docs.djangoproject.com/en/5.2/ref/databases/).
    5. Use bounded cron-invoked CLI jobs and rendered templates. No separate SPA, Docker/root dependency on shared hosting, persistent background worker or application-owned server-OS requirement.
    6. Enforce the [security architecture](../security/ARCHITECTURE.md) as future implementation/release criteria. Add a fail-closed **offline evidence checker**, not a deployment or runtime-security implementation. Unknown host settings are blocked.
    7. Keep the mobile architecture and existing STEM-first gates. ML framework/runtime selection is not changed by this hosting ADR; it remains subject to device/training evidence and is outside this structural pass.
    
    ## Alternatives and consequences
    
    A VPS gives operational control but adds cost/administration; retain it only as fallback. Shared hosting may lower operational burden, but requires provider evidence on runtime, isolation, resources, scheduling and data/backup locations. No ASGI/WSGI conclusion can be drawn from the screenshot control panel.
    
    Separate domain/security contracts from HTTP and cron adapters, while using one selected framework's maintained facilities. Use the same framework/DB engine across pilot and synthetic demo where available. Do not keep an openGauss/PostgreSQL common SQL subset solely for an unused alternative.
    
    No product routes, authentication implementation, schema migrations, dependency installs, live deployment or provider contact occurs in this pass. Exact versions/entrypoints await capability selection; existing historical evidence remains dated and subordinate to this ADR for current topology.
    ✓ • 0ms

## Activity

    $ PYTHONDONTWRITEBYTECODE=1 python3 tests/acceptance/runner.py --validate
    Validated 18 fixture definitions; product tests NOT RUN.
    ✓ • 0ms

## Activity

    $ PYTHONDONTWRITEBYTECODE=1 python3 -m unittest discover -s tests/infrastructure -p 'test_*.py'
    ..........
    ----------------------------------------------------------------------
    Ran 10 tests in 0.357s
    
    OK
    ✓ • 356ms

## Activity

    $ PYTHONDONTWRITEBYTECODE=1 python3 -m unittest discover -s tests/acceptance -p 'test_harness.py'
    ......
    ----------------------------------------------------------------------
    Ran 6 tests in 0.886s
    
    OK
    ✓ • 871ms

## Assistant

What peak pilot load should the hosting acceptance tests target: simultaneous classrooms syncing, plus approximate class length and sessions per day? If unknown, I’ll keep capacity approval blocked until those numbers are set.

## Activity

    $ cat server/README.md server/domain/README.md server/security/README.md server/jobs/README.md infra/in-country/shared-hosting/README.md; git diff -- docs/research/RESEARCH_PLAN.md docs/research/HOSTING_OPTIONS.md .gitignore; git status --short
    # Pilot and synthetic-demo backend
    
    One modular Python application; no product implementation exists yet. Framework selection is conditional: FastAPI if managed ASGI is verified, Django if only managed WSGI is available. PostgreSQL is preferred; confirmed supported MySQL/MariaDB may be selected with Django after transaction and recovery tests. Implement one combination, not all alternatives.
    
    | Directory | Responsibility / dependency boundary |
    |---|---|
    | `api/` | HTTP validation, authenticated principal, calls to domain use cases; no direct bypass of scope checks |
    | `domain/` | Enrolment/cohort rules, immutable lesson ingest, reports and withdrawal; no web-framework imports |
    | `security/` | Central authorisation policy, credential/session lifecycle and redacted audit contracts |
    | `db/migrations/` | Chosen-engine migrations and scoped repositories; separate migration privilege |
    | `jobs/` | Bounded cron-invoked CLI jobs calling the same use cases, DB leases/checkpoints, retry/idempotency |
    | `templates/` | Rendered dashboard/report pages, autoescaping, no separate SPA build |
    | `config/` | Environment-specific validated settings and startup refusal conditions |
    | `tests/` | Future API/database/security integration tests on synthetic disposable fixtures |
    
    Dependency direction: HTTP/CLI → authorised use cases → scoped repositories. No public research-audio endpoints or training imports. The server never needs the ML framework installed to accept indicator records. Framework-native auth/session/CSRF mechanisms should be reused; do not create custom cryptography or a second generic web framework.
    
    See [security architecture](../docs/security/ARCHITECTURE.md), [ADR-015](../docs/adr/015-capability-gated-hosting.md) and [hosting target](../infra/in-country/shared-hosting/README.md).
    # Domain boundary
    
    Future use cases: enrolment, cohort/context validation, immutable lesson acceptance, local/server report rules, consent withdrawal and deletion. Require an authenticated actor plus explicit capability/scope for each operation; domain objects never trust request teacher/school/cohort IDs as authority.
    
    Transactions commit lesson + idempotency result together. Owner/record ID + canonical payload hash defines identical retries; conflicting content fails. Revocation wins over stale uploads; use one transaction/locking strategy for consent status checks and acceptance. STEM records cannot enter early-grade coaching or district aggregates. All callers, including jobs, use this boundary.
    # Security implementation boundary (future)
    
    The normative design is [docs/security/ARCHITECTURE.md](../../docs/security/ARCHITECTURE.md). Centralise resource authorisation, enrolment-token revocation, browser session policy and audit redaction. Use the selected framework's maintained authentication/password/CSRF facilities; no shared global API key for teachers.
    
    Separate teacher, aggregate viewer, research annotator and operator capabilities. Operator account administration does not automatically grant permission to read individual reports. Enrolment and recovery are rate-limited, audited and bound to cohort/institution. No raw credentials, report bodies or audio in logs. Every endpoint and job requires negative authorisation tests.
    # Scheduled command contract
    
    Use host-supported cron invoking bounded CLI commands in the application's virtual environment. Proposed commands: generate due weekly reports, process purge work, expire sessions and verify backups. Names/entrypoints await framework selection; no public HTTP endpoint is a cron substitute.
    
    Jobs acquire a database lease, store durable checkpoints, process a bounded batch and exit within the host's time/memory limit. Report-generation keys include teacher/cohort/week/spec version. Duplicate invocations must not create duplicate reports; expired leases must permit crash recovery. Record last-success and backlog metrics without personal payloads; missed runs must alert the operator. Purge overrides stale report work and replays after restore. Invoke the same authorised use cases as the API.
    # Zergaw shared-hosting target
    
    Preferred budget candidate, not yet verified. The user confirmed on 2026-10-02 that runtime, database and cron support are unknown. A screenshot advertising Python/SSH/SSL/100 GB cannot determine ASGI/WSGI, root access, process management or tenant isolation.
    
    ## Select one deployment path
    
    | Confirmed capability | Candidate application | Database choice |
    |---|---|---|
    | Managed ASGI with restart and reverse proxy | FastAPI; rendered templates | PostgreSQL preferred |
    | Managed WSGI only | Django; rendered templates | PostgreSQL preferred; supported MySQL/MariaDB transactional configuration acceptable after testing |
    | Neither, no package installation, insufficient isolation or no reliable scheduling | Do not deploy pilot here | Use the in-country VPS fallback |
    
    Do not implement both paths. Record the selected runtime/framework/database versions after provider confirmation and a disposable synthetic smoke test. No framework-dependent entrypoint, connection string, worker command or Compose file is supplied while those choices are unknown. Do not run a web server under an SSH session as a substitute for provider-managed process lifecycle.
    
    ## Capability intake
    
    Copy `capabilities.example.json` to `capabilities.local.json` (ignored). It contains no credentials. Each capability has `value` and `evidence`: use a dated support ticket or private test-report reference. Populate all required fields after confirmation. Database/resource numbers must reflect the actual plan, not the word “unlimited”. The readiness checker requires both provider capability evidence and a supported application combination.
    
    Ask Zergaw for:
    
    1. Supported Python versions; ASGI/Uvicorn versus WSGI; virtualenv/pip support; managed restart/reload and rollback procedure.
    2. Database engine/version, transaction support, DB/user permissions, connection cap and connection protection (authenticated TLS or verified local socket).
    3. RAM, CPU, worker/process, disk/inode, request/body/time limits; scheduled-task frequency and maximum runtime.
    4. Host/proxy configuration, HTTPS renewal, trusted forwarded-header handling and static document-root mapping. Secrets, DB dumps and private files must be outside the public root.
    5. Account/filesystem/process isolation; staff access policy and access logging; secret injection, patch ownership and incident contact.
    6. Primary, database, logs, backup/replica and restore locations; encryption at rest, daily encrypted recoverable backup, customer restore access and retention/deletion procedure. Do not infer all locations from the primary server's country.
    
    ## Deployment sequence after product implementation
    
    1. Complete intake and checker; ratify one framework/DB; run HOST-01–04 and applicable SEC cases on synthetic fixtures.
    2. Install pinned dependencies in a private application directory/venv, never upload the repository wholesale into `public_html`/`web`.
    3. Expose only reviewed static assets through the public document root. Framework gateway routes dynamic requests; secrets, source, fixtures, docs and research assets stay inaccessible.
    4. Load secrets using provider-private configuration; use separate application/migration accounts and separate demo/pilot credentials. Set exact hosts/origins, debug off, verified HTTPS/proxy handling and production session settings.
    5. Backup, migrate once with a deployment lock, load no real data, run auth/scope/restore tests and switch traffic using provider-supported restart. Keep a documented application rollback and schema recovery plan.
    6. Schedule bounded, idempotent CLI jobs for reports, purge and backup. Use a DB-backed lease and checkpoint, not an in-memory timer or publicly callable cron URL. Alert on missed runs; cron limitations are a selection gate.
    7. Enable undergraduate pilot enrolment only after G4-U. Research Mode has a different endpoint and remains disabled until the research environment is ready. Repeat G4-E separately.
    
    Proposed recovery objectives: backup at least daily, RPO ≤24 h, restore target ≤8 h in a tested run. Retention is set through the approved pilot policy before collection; do not retain backups indefinitely. These are project targets, not claims about Zergaw's service. Weekly-only backup does not meet them.
    diff --git a/.gitignore b/.gitignore
    index 4f074fd..121b69e 100644
    --- a/.gitignore
    +++ b/.gitignore
    @@ -1,2 +1,25 @@
     .DS_Store
     **/.DS_Store
    +
    +# Local configuration, secrets and private research/runtime artefacts
    +.env
    +.env.*
    +!.env.example
    +*.local.json
    +*.local.toml
    +*.pem
    +*.key
    +*.p12
    +*.pfx
    +/private-data/
    +/artifacts/
    +/backups/
    +*.sqlite3
    +*.sqlite3-*
    +*.db
    +*.db-*
    +__pycache__/
    +*.py[cod]
    +.venv/
    +.pytest_cache/
    +# Copyrighted PDFs already tracked are NOT removed by ignore rules.
    diff --git a/docs/research/HOSTING_OPTIONS.md b/docs/research/HOSTING_OPTIONS.md
    index 4b7f7f0..4e01132 100644
    --- a/docs/research/HOSTING_OPTIONS.md
    +++ b/docs/research/HOSTING_OPTIONS.md
    @@ -1,30 +1,23 @@
     # In-country hosting options — Article 22 compliance
    
    -> 2026-09-27 follow-up: this is a dated provider shortlist, not a selected host. The undergraduate STEM trial uses the same in-country privacy boundary as early grades. See [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md): openEuler service-pack pinning, exact database/driver TLS identity validation (openGauss limitation), provider backup/location terms and restore tests are mandatory selection gates. Prices below were not revalidated in this pass.
    +> 2026-09-27 follow-up: this is a dated provider shortlist, not a selected host. The undergraduate STEM trial uses the same in-country privacy boundary as early grades. See [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md): exact runtime/database compatibility, authenticated connections, provider backup/location terms and restore tests are mandatory selection gates; the October revision below removes the OS-specific baseline. Prices below were not revalidated in this pass.
    
    
     Checked 2026-09-26, prompted by a review of Ethio Telecom's shared-hosting pricing (`myportal.ethiotelecom.et`) against the uploaded copy of Proclamation No. 1321/2016 E.C. (= 1321/2024 G.C.), Article 22 ("Data Sovereignty"): *"Every data controller or data processor shall ensure the storage, on a server or data center located in Ethiopia, of personal data collected or obtained locally."* This matches the Negarit Gazeta text already used in [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) §3 (Art. 22). A copy of the Proclamation is kept at [`../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf`](../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf).
    
     This answers a narrower question than the rest of the pack: **where can the `infra/openeuler` sovereign-tier server (§8.1, §12) actually be hosted?**
    
    -## The screenshot in question doesn't fit the job
    +## 2026-10-02 correction: shared hosting is a conditional candidate
    
    -The Ethio Telecom pricing shown (Linux Bronze/Silver/Gold/Platinum, Plesk, ETB 650–5,200/year) is **shared web hosting** — a PHP/MySQL site host with a control panel, not a VPS or bare-metal server with root access. Dimts' sovereign tier needs to run a custom FastAPI app and openGauss/PostgreSQL, which shared Plesk hosting cannot do. Even if Ethio Telecom's data centers are in Ethiopia (they are — see below), this specific product is the wrong tier.
    +The earlier rejection of all shared hosting was too broad. Python application support can be sufficient for derived records and reports without root access. The user intends Zergaw Standard and supplied screenshots advertising Ethiopia hosting, Python/SSH, weekly backup, 100 GB and ETB 7,035/year. These are screenshot claims, not verified capacity or a current quote; the cropped “Unlimited” rows do not establish application resources.
    
    -## Update 2026-09-26: Zergaw's own shared-hosting tier has the same problem
    +Neither the dashboard image nor “Python support” establishes ASGI, WSGI, Passenger, installed databases, scheduler rights or tenant isolation. The user confirmed these are **not yet confirmed**. Do not assume a particular gateway or infer feasibility from storage quota.
    
    -The user found Zergaw's shared-hosting page (`zergaw.com/shared-hosting-platform/`), which states **"Hosting Location: Ethiopia"** per plan — even more explicit Article 22 evidence than anything found earlier. But most of its tiers have the same fit problem as the Ethio Telecom screenshot:
    +Use [ADR-015](../adr/015-capability-gated-hosting.md) and the [shared-hosting intake](../../infra/in-country/shared-hosting/README.md): managed ASGI permits FastAPI/PostgreSQL; WSGI permits Django with a supported selected database. Verify dependency installation, process/restart and job limits, transaction semantics, private files/secrets, protected DB access, local backups/logs, and measured load. Weekly backup alone fails the proposed daily recovery target. Use a VPS if any required capability or security boundary fails.
    
    -| Zergaw plan | Server | Root access | Price | Fits `infra/openeuler`? |
    -|---|---|---|---|---|
    -| Basic | "None (shared)" | No | ETB 5,481/**year** | No — shared hosting, "Python support" is a WSGI/Passenger app slot, not a machine you control |
    -| Standard | "None (shared)" | No | ETB 7,035/year | No |
    -| Premium | "None (shared)" | No | ETB 10,139/year | No |
    -| **Dedicated** | 2 CPU, 4 GB RAM, dedicated IP | **Yes** | ETB 5,646.55/**month** (≈ ETB 67,760/year) | **Yes** — this is the one that can install openGauss and run FastAPI, WorkManager ingest, and cron jobs as your own daemons |
    -
    -The "Dedicated" row is a materially different product from the shared tiers above it, despite being listed on the same pricing page. It overlaps in spec (2 vCPU/4 GB) with a similarly-priced entry on Zergaw's separate Cloud Server (VPS) page (`CS02004`, ETB 5,205/month, quoted in the table below) — confirm with Zergaw sales whether "Dedicated" (shared-hosting page) and the `CS02004` cloud server (VPS page) are the same underlying product, and whether either lets you provision an openEuler image rather than their default OS template.
    +Shared hosting never receives research audio, annotation or training workloads. No separate research machine is available yet: [research setup](../../infra/research/README.md) must be completed before Research Mode. Root access and an openEuler image are not prerequisites for the shared application profile.
    
    -**Rule of thumb going forward:** on any Ethiopian host's pricing page, "shared hosting" / "Server: none" / a Plesk-or-cPanel-style feature list (subdomains, email accounts, "N databases") means no root and no ability to install openGauss or run your own background services, whatever the location says. Only a line item that names CPU/RAM and says **root/dedicated IP** (VPS, cloud server, dedicated server, bare metal) is a candidate for `infra/openeuler`.
    +The historical provider shortlist below is retained as research, not as current pricing, compliance certification or a selected deployment. Reconfirm every commercial and location claim with the provider before selection.
    
     ## Providers that offer rentable infrastructure in Ethiopia
    
    @@ -41,8 +34,8 @@ The "Dedicated" row is a materially different product from the shared tiers abov
     - **Article 33 registration.** None of these providers' marketing pages mention helping customers register as a data controller/processor with the Ethiopian Communications Authority. That's still the team's responsibility regardless of host (see `CLAIMS_AUDIT.md` §3, C-7f; `RESEARCH_PLAN.md` WS-F Q1).
     - **Whether renting from a private Ethiopian cloud (Zergaw, teleCloud) satisfies Art. 22** as fully as the government's own G-Cloud. The article only requires the server/data center be *located in Ethiopia* — it doesn't require a government operator — but confirm this reading with the legal advisor alongside the other WS-F questions.
     - **Ethio Telecom and HostHabesha sites were unreachable from this session** (network policy), so their VPS/teleCloud pricing and specs are unverified here. Fetch them directly.
    -- **Actual database compatibility.** Zergaw's DBaaS lists PostgreSQL/MySQL/MariaDB, not openGauss by name. openGauss is PostgreSQL-lineage (per `CLAIMS_AUDIT.md` §4, 7j), so a managed *PostgreSQL* instance is a reasonable substitute if openGauss itself isn't offered — this feeds back into ADR-005 (backend/database choice).
    +- **Actual database compatibility.** The historical DBaaS list does not establish engines included with Standard. Confirm the exact plan, versions, driver, transaction, migration and connection-security behavior. PostgreSQL is preferred, with supported MySQL/MariaDB conditional on the Django path.
    
     ## Recommendation
    
    -For a **budget self-service VPS or managed Postgres with an explicit local-law framing**, contact **Zergaw** first, specifically about their **Dedicated** server or `CS02004` cloud server (both root, 2 vCPU/4 GB) — not the Basic/Standard/Premium shared tiers, which don't give you a machine to install anything on regardless of their stated location. For the strongest **compliance and pitch narrative** ("hosted on Ethiopia's own national data center"), get a quote from **INSA's G-Cloud**. Check **Ethio Telecom teleCloud** too, given the Huawei Cloud Stack overlap with the rest of the competition build. Treat Raxio and UT Solutions as colocation/build options only, not as what you want for W0–W1's spike S6.
    +Assess Zergaw Standard first using the capability manifest and HOST/SEC acceptance plan. Its suitability is **unknown**, not rejected or approved. Compare the full cost of daily backups, reliable scheduled jobs, support and capacity with a local VPS if the gates cannot be met. Use one maintained application and one tested database; do not install openGauss merely for a competition technology mapping. Retain alternative providers as fallbacks with fresh contractual and technical evidence.
    diff --git a/docs/research/RESEARCH_PLAN.md b/docs/research/RESEARCH_PLAN.md
    index 2b8160d..2d315e4 100644
    --- a/docs/research/RESEARCH_PLAN.md
    +++ b/docs/research/RESEARCH_PLAN.md
    @@ -1,5 +1,7 @@
     # Research and implementation plan — Dimts (reviewed proposal, v1.2 — STEM first)
    
    +> **2026-10-02 update:** [ADR-015](../adr/015-capability-gated-hosting.md) changes hosting selection to a capability-gated shared-hosting candidate with VPS fallback. Runtime/DB and research machine are unconfirmed. Research Mode and research-dependent field work stay blocked pending infrastructure and approvals. Undergraduate STEM remains first, followed by separate early-grade gates; scaffolding is not gate sign-off.
    +
     Status as of 2026-09-27. Implementation must follow the ordered gates in [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md); none is recorded as passed. This plan builds on [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md), which is now updated with the full texts in [`papers/`](papers/). Related files:
    
     - [`STEM_TRIAL_PLAN.md`](STEM_TRIAL_PLAN.md) — first classroom phase and transition to early grades.
    @@ -125,12 +127,13 @@ The core thesis question: *Do Dimts' acoustic indicators measure something Ethio
     | S3 | Each FP export agrees with its own source graph on fixed features (initial tolerance 1e-4, justified per model); compare frameworks only for weight-equivalent graphs. Assess PTQ through task/indicator degradation separately; record actual precision/fallback ops | W3 |
     | S4 | MindSpore 2.10 trains a toy model on the in-country hardware | W1 |
     | S5 | Confirm exact services before choosing a region; ModelArts, OBS and ECS all in **AF-Johannesburg**; flavours and prices recorded; any required unencrypted training bucket restricted to approved corpus objects, never anonymous/public access; verify licence/privacy separately | W1 |
    -| S6 | Pin maintained openEuler SP3 candidate + chosen DB/driver; test authenticated TLS including wrong hostname/CA rejection (INF-09); no generic SASL downgrade; **host chosen from [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md)** — Zergaw VPS/DBaaS, INSA G-Cloud or Ethio Telecom teleCloud, not the on-campus box, unless the university prefers self-hosting | W1 |
    +| S6 | Confirm managed ASGI or WSGI, exact Python/framework/DB/driver versions and migration/transaction tests. Prefer PostgreSQL; supported MySQL/MariaDB allowed with Django. Verify remote DB TLS identity or protected local socket. Zergaw shared candidate first; VPS fallback if required controls fail, per ADR-015 | W1, blocked on evidence |
     | S7 | Modern target-SDK microphone permission/foreground-service lifecycle; `AudioRecordingCallback` detects call-silencing; 40-minute battery test | W2 |
     | S8 | 16 KB decision: test Lite, SQLCipher and any fallback native libraries, ELF/ZIP alignment and runtime; sideload only on verified compatible phones | W6 |
     | S9 | SQLCipher + Keystore key; backup/transfer exclusions, key-loss/reinstall and shared-phone tests before G4-U | W2 |
    -| **S10** (new) | **Competition cloud stack:** ECS + RDS for PostgreSQL (GaussDB only after a separate compatibility decision) + OBS in AF-Johannesburg serving the demo page and lesson simulator with synthetic lessons. Redeployable from a script. | W5 |
    +| **S10** | **Competition cloud stack:** same selected application and DB engine where available, independent synthetic data/credentials; ECS with managed DB optional after region/quota verification. OBS/ModelArts only as needed. Reproducible deployment after runtime decision. | W5 |
     | S11 | Sync ownership, ACK/conflict/retry, withdrawal and restore-purge tests per G4-U; repeat changed paths for G4-E; cohort isolation and disclosure tests before G5-E | Before pilot/release |
    +| S12 | Complete provider capability evidence and HOST-01–04/SEC-01–12 tests; prepare separate in-country research host, protected transfer, annotation access and restore/deletion evidence before Research Mode. Daily backup/RPO ≤24 h/RTO ≤8 h provisional targets; finite approved retention required | Before G4-U / Research Mode |
    
     ### WS-F: Legal and ethics (Lead)
    
     M .gitignore
     M docs/DIMTS_ARCHITECTURE_CONTEXT.md
     M docs/research/ASSUMPTIONS.md
     M docs/research/CODEBASE_STRUCTURE.md
     M docs/research/HOSTING_OPTIONS.md
     M docs/research/IMPLEMENTATION_REVIEW.md
     M docs/research/INFRASTRUCTURE_RECHECK.md
     M docs/research/README.md
     M docs/research/RESEARCH_PLAN.md
     M docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md
     D infra/openeuler/backup/.gitkeep
     D infra/openeuler/compose/.gitkeep
    ?? README.md
    ?? dashboard/README.md
    ?? docs/adr/015-capability-gated-hosting.md
    ?? docs/images/
    ?? docs/security/
    ?? docs/testing/HOSTING_SECURITY_TEST_PLAN.md
    ?? infra/README.md
    ?? infra/huawei-cloud/README.md
    ?? infra/in-country/
    ?? infra/openeuler/README.md
    ?? infra/research/
    ?? server/README.md
    ?? server/config/
    ?? server/domain/
    ?? server/jobs/README.md
    ?? server/security/
    ?? server/templates/
    ?? tests/infrastructure/
    ?? tools/check-hosting/
    ✓ • 0ms

## Activity

    $ python3 - <<'PY'
        from pathlib import Path
        import re
        files = [Path('README.md'), Path('docs/DIMTS_ARCHITECTURE_CONTEXT.md')]
        files += list(Path('infra').rglob('*.md')) + list(Path('server').rglob('*.md'))
        files += [Path('docs/research') / n for n in ['CODEBASE_STRUCTURE.md','RESEARCH_PLAN.md','HOSTING_OPTIONS.md','IMPLEMENTATION_REVIEW.md','INFRASTRUCTURE_RECHECK.md','README.md','ASSUMPTIONS.md']]
        files += list(Path('docs/security').glob('*.md')) + list(Path('docs/testing').glob('*.md')) + [Path('docs/adr/015-capability-gated-hosting.md'),Path('tools/check-hosting/README.md')]
        missing=[]
        for p in files:
            s=p.read_text()
            if len(re.findall(r'^```',s,re.M)) % 2: print('Unbalanced fences:',p)
            for target in re.findall(r'\]\(([^)]+)\)',s):
                if '://' in target or target.startswith('#'): continue
                target=target.split('#')[0]
                if not (p.parent / target).exists(): missing.append((str(p),target))
        print('Markdown files checked:',len(files))
        print('Unresolved local links:',missing)
        PY
    Markdown files checked: 26
    Unresolved local links: []
    ✓ • 0ms

## Activity

    $ cat >> infra/research/README.md <<'EOF'
        
        ## Preparation sequence before Research Mode
        
        1. Lead names a university/private operator and records the proposed machine, physical/data location, administrator access, funding and maintenance owner. Select hardware using measured audio-volume and ML training spikes; shared-plan storage size is not a research capacity estimate. If no suitable training accelerator exists, benchmark CPU or another approved in-country machine before committing the research schedule.
        2. Sys provisions a supported patched OS, private network/VPN access, encrypted research volume and separate protected backup target in Ethiopia. Restrict ingress to approved transfer/annotation access and administrator channels. Review DNS, remote support, logging, telemetry and browser-loaded resources so they do not export participant data. Record capacity, recovery and retention limits.
        3. Lead/Sys separate the identity register from pseudonymous recordings. Define who can enrol, upload, annotate, export approved models, revoke consent and restore archives. Create individual accounts and keys; operators' privileged access remains documented. Confirm the annotation edition supports required access boundaries or deploy isolated projects/instances that do.
        4. Implement the consent-bound transfer and annotation adapters in a later product pass. Test interrupted/resumed transfers, payload integrity, durable receipts, duplicate/conflicting uploads, wrong-cohort access, withdrawal during transfer and audio removal from the phone only after verified receipt. Do not reroute research audio through Zergaw.
        5. Restore synthetic recordings and annotation fixtures on a fresh isolated instance. Replay withdrawal/deletion records and verify derived artefact handling before reopening access. Test missed backups, disk limits, lost keys, compromised accounts and operator recovery using ANNOT-01 and relevant SEC cases.
        6. Record the readiness evidence and G4-U consent/eligibility approvals before enabling undergraduate Research Mode. Enable only the approved cohort/devices and finite collection window. G4-E separately authorises later early-grade research; STEM approval never carries over automatically.
        
        Record each prerequisite as NOT STARTED, BLOCKED, FAILED or VERIFIED with an owner and evidence reference. Current state: **BLOCKED — no research machine confirmed**. Offline synthetic tests can continue; any validation needing new consented research recordings must wait. Research service entrypoints, dependencies and provisioning scripts are intentionally deferred until the machine and access model are selected.
        EOF
        python3 - <<'PY'
        from pathlib import Path
        p=Path('docs/research/HOSTING_OPTIONS.md'); s=p.read_text().replace('**where can the `infra/openeuler` sovereign-tier server (§8.1, §12) actually be hosted?**','**where can the in-country application and separate research services (§8.1, §12) actually be hosted?**'); p.write_text(s)
        p=Path('infra/in-country/shared-hosting/README.md'); s=p.read_text().replace('Each capability has `value` and `evidence`:','Each capability has `value`, `evidence` and `confirmed_on`:'); s=s.replace('PostgreSQL preferred |','PostgreSQL (this baseline) |',1); p.write_text(s)
        p=Path('tools/check-hosting/check_hosting.py'); s=p.read_text().replace("errors.append(f'{key}: positive measured limit required')", "errors.append(f'{key}: finite measured limit in (0, 10^12] required')"); p.write_text(s)
        p=Path('tools/check-hosting/README.md'); s=p.read_text().replace('Limits use the units in field names:', 'Numeric limits must be finite and in (0, 10^12]; the upper bound rejects nonsensical input, not a provider quota. Limits use the units in field names:'); p.write_text(s)
        PY
    ✓ • 0ms

## Assistant

The 10 hosting-checker tests and 6 existing acceptance-harness tests pass; all 18 fixture definitions also validate. Actual hosting, product, device and security tests remain unrun. I’ve also added a step-by-step research infrastructure plan, with Research Mode blocked until that environment is ready.

## Activity

    $ git diff --check; git diff --numstat; git check-ignore infra/in-country/shared-hosting/capabilities.local.json server/.env infra/research/private.local.json
    23    0    .gitignore
    24    62    docs/DIMTS_ARCHITECTURE_CONTEXT.md
    2    0    docs/research/ASSUMPTIONS.md
    57    115    docs/research/CODEBASE_STRUCTURE.md
    10    17    docs/research/HOSTING_OPTIONS.md
    2    0    docs/research/IMPLEMENTATION_REVIEW.md
    5    3    docs/research/INFRASTRUCTURE_RECHECK.md
    4    2    docs/research/README.md
    5    2    docs/research/RESEARCH_PLAN.md
    5    3    docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md
    0    0    infra/openeuler/backup/.gitkeep
    0    0    infra/openeuler/compose/.gitkeep
    infra/in-country/shared-hosting/capabilities.local.json
    server/.env
    infra/research/private.local.json
    ✓ • 0ms

## Activity

    $ python3 - <<'PY'
        from pathlib import Path
        import re
        security=Path('docs/security/ARCHITECTURE.md').read_text()
        plan=Path('docs/testing/HOSTING_SECURITY_TEST_PLAN.md').read_text()
        assert set(re.findall(r'^\| (SEC-\d+)',security,re.M)) == set(re.findall(r'^\| (SEC-\d+)',plan,re.M)) == {f'SEC-{n:02d}' for n in range(1,13)}
        assert set(re.findall(r'^\| (HOST-\d+)',plan,re.M)) == {f'HOST-{n:02d}' for n in range(1,5)}
        assert Path('infra/in-country/vps/compose/.gitkeep').exists()
        assert Path('infra/in-country/vps/backup/.gitkeep').exists()
        print('12 security contracts mapped to procedures; 4 HOST cases; relocated placeholders verified.')
        PY
    12 security contracts mapped to procedures; 4 HOST cases; relocated placeholders verified.
    ✓ • 0ms

## Activity

    $ PYTHONDONTWRITEBYTECODE=1 python3 -m unittest discover -s tests/infrastructure -p 'test_*.py'
    ..........
    ----------------------------------------------------------------------
    Ran 10 tests in 0.283s
    
    OK
    ✓ • 255ms

## User

> What peak pilot load should the hosting acceptance tests target: simultaneous classrooms syncing, plus approximate class length and sessions per day? If unknown, I’ll keep capacity approval blocked until those numbers are set.

simeltaneous sync and 3 class sessions (1:30min each).

## Assistant

How many classrooms/devices will sync simultaneously? Also, does “1:30min” mean 90 minutes per session, with three sessions per classroom per day?

## Assistant

I’ll include simultaneous syncing and three class sessions in the load plan. Classroom count is still needed to set a capacity target, and I’ve asked whether the duration means 90 minutes per session. Shared hosting will receive derived lesson records, so audio-processing performance remains a separate phone test.

## Activity

    $ cat > docs/testing/PILOT_LOAD_PROFILE.md <<'EOF'
        # Undergraduate STEM pilot load profile
        
        Updated 2026-10-02 from the user's workload clarification. This is a post-implementation test input specification, not a measured capacity claim.
        
        | Input | Current evidence / status |
        |---|---|
        | Sync pattern | User requests simultaneous sync; test a reconnect burst, not evenly spaced requests |
        | Class sessions | User specified three class sessions; confirmation requested that this is per classroom per day |
        | Session duration | User wrote “1:30min each”; 90 minutes is the working interpretation, awaiting confirmation |
        | Concurrent classrooms/devices (`N`) | Not supplied; capacity approval remains BLOCKED |
        | Offline backlog days (`D`) | Not supplied; choose and approve a maximum before capacity testing |
        | Maximum record/batch sizes, retry concurrency and request rate | To be frozen in the API/config contract before implementation acceptance |
        | Acceptable sync completion and report/purge delay | Lead/Sys to set numerical budgets before measuring the host |
        
        Do not infer classroom concurrency from “three sessions”; these are different inputs. Do not describe the Standard plan as sufficient until `N`, backlog, payload limits and latency budgets are established and HOST-03 passes.
        
        ## Workload construction after confirmation
        
        For one final derived record per session, three sessions per classroom per day yields `3 × N` new records daily. After `D` offline days, the first reconnect can contain `3 × N × D` unique records plus retries. Update this formula if the final API intentionally divides sessions into multiple records. Generate unique instructor/device identities and scoped records for each classroom; duplicate retry IDs retain identical canonical content, while conflict cases deliberately change content.
        
        1. Synchronise all `N` enrolled synthetic devices at a start barrier, each with its own `3 × D` backlog. Exercise the actual bounded batching and retry policy rather than sending an unlimited request stream.
        2. Measure unique durable records, ACK latency, backlog drain time, failed/retried requests and duplicate/conflict responses. Account for every accepted/rejected record, including a disconnect immediately after commit.
        3. Repeat within the agreed provider load-test envelope with report/purge jobs active and delayed network reconnects. Do not treat HTTP success alone as proof of durable ingest; verify the database and generated reports.
        4. Check peak process/RAM/CPU/DB connections and scheduled-job runtime against measured plan quotas. Preserve resource headroom agreed before the test. If capacity fails, reduce the documented supported load or select another host; do not silently lower the test load.
        
        Session duration affects phone capture, thermal/battery stability and offline storage tests independently of the shared backend. If 90 minutes is confirmed, add a full 90-minute device endurance run and a three-session daily-use run to DEVICE coverage, with the existing inference/memory/privacy targets. Do not claim these pass from a 40-minute benchmark. Research audio volume is a separate storage/transfer budget to establish on the private research environment before Research Mode; no audio is sent to shared hosting.
        EOF
        python3 - <<'PY'
        from pathlib import Path
        p=Path('docs/testing/HOSTING_SECURITY_TEST_PLAN.md'); s=p.read_text().replace('Decide expected simultaneous classrooms, offline backlog per device and acceptable sync/report delay before the capacity test;', 'Use the [pilot load profile](PILOT_LOAD_PROFILE.md), which records simultaneous sync and the user’s three-session input. Confirm classroom count/session duration, offline backlog per device and acceptable sync/report delay before the capacity test;'); p.write_text(s)
        p=Path('docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md'); s=p.read_text().replace('## Execution order and reporting','The [pilot load profile](PILOT_LOAD_PROFILE.md) adds the requested simultaneous-sync and three-session workload. Exact concurrent device count and session-duration interpretation remain open; extend endurance tests to the confirmed session length rather than treating the existing 40-minute benchmark as sufficient.\n\n## Execution order and reporting',1); p.write_text(s)
        p=Path('infra/in-country/shared-hosting/README.md'); s=p.read_text(); s += '\nCapacity acceptance uses the [STEM pilot load profile](../../../docs/testing/PILOT_LOAD_PROFILE.md): simultaneous sync and three sessions are user inputs; device count and duration interpretation remain to be confirmed. HOST-03 cannot establish suitability without those values and agreed numeric latency/resource budgets.\n'; p.write_text(s)
        PY
        git diff --check
    ✓ • 0ms

## Activity

    $ python3 - <<'PY'
        from pathlib import Path
        import re
        paths=[Path('docs/testing/PILOT_LOAD_PROFILE.md'),Path('docs/testing/HOSTING_SECURITY_TEST_PLAN.md'),Path('docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md'),Path('infra/in-country/shared-hosting/README.md')]
        for path in paths:
            for target in re.findall(r'\]\(([^)]+)\)',path.read_text()):
                if '://' not in target and not target.startswith('#'):
                    assert (path.parent / target.split('#')[0]).exists(), (path,target)
        print('Load-profile references verified; no capacity result asserted.')
        PY
    Load-profile references verified; no capacity result asserted.
    ✓ • 0ms

## User

> How many classrooms/devices will sync simultaneously? Also, does “1:30min” mean 90 minutes per session, with three sessions per classroom per day?

3 devices per day and 3 sessions/day for a week.

## Assistant

I’ll set the test profile to three devices syncing together over seven days. To cover the higher-load interpretation, I’ll test three sessions per device per day: 9 records daily and a 63-record reconnect burst after a week offline. Session duration remains provisionally 90 minutes; it hasn’t been explicitly confirmed.

## Activity

    $ python3 - <<'PY'
        from pathlib import Path
        p=Path('docs/testing/PILOT_LOAD_PROFILE.md'); s=p.read_text()
        s=s.replace('| Class sessions | User specified three class sessions; confirmation requested that this is per classroom per day |','| Class sessions | User specified three sessions/day. Conservative test assumption: three per device per day; if three total was intended, this over-tests the reported daily volume |')
        s=s.replace('| Concurrent classrooms/devices (`N`) | Not supplied; capacity approval remains BLOCKED |','| Concurrent devices (`N`) | Three devices/day supplied; test all three reconnecting simultaneously |\n| Pilot duration | One week (seven days) supplied |')
        s=s.replace('| Offline backlog days (`D`) | Not supplied; choose and approve a maximum before capacity testing |','| Offline backlog days (`D`) | Test an entire seven-day pilot backlog as a conservative outage case, plus normal daily sync; this is a test envelope, not a claim that a week offline is expected |')
        s=s.replace('Do not infer classroom concurrency from “three sessions”; these are different inputs. Do not describe the Standard plan as sufficient until `N`, backlog, payload limits and latency budgets are established and HOST-03 passes.','The user clarified “3 devices per day and 3 sessions/day for a week.” Device count is now known; per-device session count and 90-minute duration remain explicit test assumptions. Do not describe the Standard plan as sufficient until payload limits/latency budgets are established and HOST-03 passes.')
        s=s.replace('## Workload construction after confirmation','## Prepared workload and remaining assumptions')
        s=s.replace('For one final derived record per session, three sessions per classroom per day yields `3 × N` new records daily. After `D` offline days, the first reconnect can contain `3 × N × D` unique records plus retries.','For one final derived record per session, three sessions per device per day yields `3 × N = 9` new records daily. After `D = 7` offline days, the first reconnect contains `3 × N × D = 63` unique records plus retries: 21 records per device. If three sessions/day meant a total across all devices, actual weekly volume is 21; retain the 63-record case as the conservative test envelope.')
        s=s.replace('each with its own `3 × D` backlog','each with its own `3 × D = 21` backlog')
        s=s.replace('If 90 minutes is confirmed, add a full 90-minute device endurance run and a three-session daily-use run to DEVICE coverage, with the existing inference/memory/privacy targets.','Prepare a 90-minute device endurance run and a three-session (4.5-hour capture) daily-use run, with the existing inference/memory/privacy targets; confirm duration before using these as the field acceptance target.')
        p.write_text(s)
        p=Path('docs/testing/HOSTING_SECURITY_TEST_PLAN.md'); s=p.read_text().replace('which records simultaneous sync and the user’s three-session input. Confirm classroom count/session duration, offline backlog per device and acceptable sync/report delay before the capacity test;', 'which sets three simultaneous devices and a conservative 63-record weekly reconnect burst. Confirm the session-count/duration assumptions and acceptable sync/report delay before the capacity test;'); p.write_text(s)
        p=Path('docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md'); s=p.read_text().replace('adds the requested simultaneous-sync and three-session workload. Exact concurrent device count and session-duration interpretation remain open;', 'adds three simultaneous devices across seven days, using a conservative three sessions/device/day and 63-record reconnect burst. Session duration and the per-device session-count interpretation remain explicit assumptions;'); p.write_text(s)
        p=Path('infra/in-country/shared-hosting/README.md'); s=p.read_text().replace('simultaneous sync and three sessions are user inputs; device count and duration interpretation remain to be confirmed. HOST-03 cannot establish suitability without those values and agreed numeric latency/resource budgets.', 'three devices syncing simultaneously across a seven-day pilot; conservatively test nine records/day and a 63-record weekly backlog. Session count per device and 90-minute duration remain assumptions. HOST-03 still requires payload limits, agreed numeric latency/resource budgets and actual measurements.'); p.write_text(s)
        PY
        git diff --check
    ✓ • 0ms

## Assistant

Updated the repository’s structure, architecture contracts and deployment scaffolding. No product features or live deployment were implemented.

- **Infrastructure:** separated conditional Zergaw shared hosting, VPS fallback, private research infrastructure and synthetic cloud demo. Framework/database choices remain conditional.
- **Security:** added access-control, secret-management, isolation, retention, backup and recovery contracts. Research Mode remains blocked pending its infrastructure.
- **Testing:** prepared hosting/security procedures and a pilot profile for three simultaneous devices over seven days—conservatively 9 records/day and a 63-record backlog. The 90-minute session duration remains an assumption.
- **Validation:** 10 checker tests and 6 harness tests passed; 18 fixture definitions validated. Actual infrastructure/product tests remain pending.

See the [repository structure](/Users/anteneh/Documents/Repos/Echo/docs/research/CODEBASE_STRUCTURE.md), [security architecture](/Users/anteneh/Documents/Repos/Echo/docs/security/ARCHITECTURE.md), and [pilot test profile](/Users/anteneh/Documents/Repos/Echo/docs/testing/PILOT_LOAD_PROFILE.md).

**Zergaw Standard’s suitability remains unconfirmed** until provider capabilities and measured acceptance tests support it.
