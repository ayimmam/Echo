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
2. §7–§13 are the proposed architecture. Treat them as a strong default, not scripture. Changes go through an ADR (§19).
3. §14 defines "done." If a design choice cannot be evaluated against §14, it is out of scope.
4. Anything marked **[VERIFY]** is a fact that must be checked against a primary source before it is relied on.
5. Anything marked **[DECISION]** is open and needs an ADR.
6. Read [`research/IMPLEMENTATION_REVIEW.md`](research/IMPLEMENTATION_REVIEW.md) for the verified repository baseline, unresolved contracts and ordered implementation gates. The Android app is a starter; other component directories are placeholders. No runtime feasibility is established. The review governs unresolved details; [`research/RESEARCH_PLAN.md`](research/RESEARCH_PLAN.md) owns the schedule and [`research/CODEBASE_STRUCTURE.md`](research/CODEBASE_STRUCTURE.md) owns the detailed proposed layout.

---

## 1. One-paragraph summary

Dimts is a proposed offline, on-device classroom audio analysis system for early-grade (O-class and Grades 1–3) teachers in Ethiopia. A teacher places a low-end Android phone on her desk during a lesson. The phone analyses the audio **in real time, on the device**, without speech recognition, and produces pedagogical indicators: how much the teacher talks, how long she waits for children to respond, whether children answer individually or only in chorus, how often turns change, and which language the lesson is conducted in (Amharic, Sidaamu Afoo, English). Deployment Mode never persists or uploads raw audio; separately consented Research Mode transfers encrypted audio only to the in-country research store. Only derived indicators sync to an in-country server, where they feed a private weekly report to the teacher and minimum-group-suppressed aggregates to district education offices, subject to disclosure review. The first classroom test phase is undergraduate STEM, used for technical setup validation before the intended early-grade population (§3.1). The segmentation design avoids transcription; its cross-language robustness remains to be evaluated on Amharic and Sidaamu Afoo. LID is language-dependent. Script differences do not themselves establish acoustic generalisation.

---

## 2. Problem and evidence

### 2.1 The learning crisis is flat, not unmeasured

- EGRA upper-benchmark reading performance: **31.3% (2014), 34.3% (2016), 32.4% (2018)** — essentially flat across three national rounds.
- USAID 2018: only ~**40%** of Grade 2–3 students read at 20–25 words per minute.
- Depending on language and region, **0.5%–13%** of students could read with comprehension.
- Conclusion: Ethiopia has been measured repeatedly. Another child assessment tool is not the missing piece.

### 2.2 Teacher capability is a significant, addressable constraint

- Early-grade English teachers averaged **43.4%** on a reading-instruction knowledge test; **70%** scored below 50%. Preservice programs devote only a few sessions to early reading.
- Teachers are required to teach phonics and interactive reading pedagogy they were never trained in, while existing classroom observation is uneven; the proposed contribution is frequent, private feedback. The cited English-teacher study covers 30 teachers in five schools and is not a national estimate (see the claims audit).

### 2.3 Pre-primary expanded faster than quality

- Pre-primary GER reached **59.8%** in 2024/25; kindergarten schools grew **22%** in one year.
- Extreme regional gaps: Addis Ababa 145.9% vs Somali 17.7%, Afar 26.8%.
- O-classes and child-to-child modalities serve ~**75%** of pre-primary children; a 2025 mixed-methods study found systemic deficits in qualified personnel, play facilities, and classroom conditions nationwide.

### 2.4 Mother-tongue instruction is policy without verification

- Policy mandates primary instruction in nationality languages. Research documents shortages of trained teachers and materials in L1, and teachers not proficient in the language they must teach in.
- The reviewed studies do not establish continuous automated measurement of classroom language. I-6 is a proposed descriptive measure; any claim to be the first requires the RQ-C1 search, and language shares alone do not establish policy compliance.

### 2.5 Why classroom *audio*, and why teacher-facing

- Classroom observation is the standard instrument for improving teaching practice, but it requires trained observers who do not exist at scale in Ethiopia.
- Acoustic indicators (talk ratio, wait time, choral vs individual response) are well-established in the classroom-discourse literature as correlates of instructional quality and are extractable **without transcription**.
- Teacher consent alone is insufficient for classroom capture that includes children. Both modes must follow the ethics and consent decisions in WS-F.

### 2.6 Why no ASR

No ASR is a deliberate scope, compute and data-minimisation decision. Public Amharic and Sidaamu Afoo resources exist (see the research audit); their existence does not resolve classroom acoustics or short-segment LID. Indicators remain acoustic and must be validated independently of transcription.

---

## 3. Users and scenarios

### 3.1 Test undergraduate STEM first

Sequence: synthetic/staged fixtures → undergraduate STEM technical trial → intended O-class/Grades 1–3 validation. See [STEM_TRIAL_PLAN.md](research/STEM_TRIAL_PLAN.md) and [ADR-014](adr/014-staged-classroom-trials.md). The STEM phase tests capture, microphones, streaming, storage, sync and private technical reports. Adult classroom results cannot validate child-speech classes, early-grade coaching or mother-tongue policy measurement.

Maintain separate cohorts, label/model contracts, approvals, dataset partitions and result tables. P1 has instructor/student roles and course/session context, not child labels or O–3 grades. A child-only model is incompatible; use capture/coarse-activity testing or explicitly human-coded timelines until a STEM-compatible model is available. No automatic logit relabelling. Both classroom cohorts use the in-country tier; the cloud demo stays synthetic.

### 3.2 Intended-population users and scenarios

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

The definitions below apply to the **early-grade profile**. The STEM profile uses a separately versioned adult-role schema and exposes only validated descriptive measures; no child/choral metric or early-grade coaching is inferred by relabelling.

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

**Performance budget (target device, 40-min lesson):**
- RTF ≤ 0.3 total (segmentation + LID + indicators).
- Peak RSS ≤ 300 MB.
- Battery ≤ 10% per 40-min lesson [measure].
- Models: preferred segmentation ≤ 8 MB and LID ≤ 8 MB; hard combined ceiling ≤ 20 MB at actual shipped precision.

---

## 9. ML design

### 9.1 Tasks
T-1/T-2 below target early grades. STEM has separate supported-cohort/label manifests; adult instructor/student attribution needs its own validation.
- **T-1 Segmentation:** 5-class frame labelling {TEACHER, CHILD_SINGLE, CHILD_CHORAL, OVERLAP, NON_SPEECH}.
- **T-2 Language ID:** 4-class segment labelling {amh, sid, eng, oth} over teacher speech.

### 9.2 Data sources

| Source | Content | Use | Location |
|---|---|---|---|
| D-0 Undergraduate STEM trial | Proposed ≥6 sessions, ≥3 instructors, ≥2 courses; adult roles, actual languages/session durations recorded | First technical trial; exploratory adult-domain metrics, separate from child validation | In-country only, G4-U |
| D-1 Classroom Research Mode recordings | 20–30 lessons × ~40 min, Amharic + Sidaamu Afoo schools | Fine-tune teacher model, train student, **evaluate** | In-country research store only |
| D-2 Annotated subset of D-1 | 6–10 hours, frame labels + language labels | Supervised training + test sets | In-country |
| D-3 Public Amharic speech (e.g., ALFFA Amharic, FLEURS Amharic) [VERIFY licences & availability] | Adult read/spontaneous speech | LID training, encoder adaptation | Cloud only after licence and privacy review |
| D-4 Sidaamu Afoo speech | Afrivoice/WAXAL/MMS resources reported by audit; verify speaker overlap, licences and classroom-domain suitability | LID training | In-country unless approved for the cloud data register |
| D-5 Public noise / reverberation / babble corpora (e.g., MUSAN-style, RIR sets) [VERIFY licences] | Augmentation | Anywhere |
| D-6 Synthetic classroom mixtures | Adult speech + child-babble + RIR + choral simulation (time-aligned copies with jitter/pitch shift) | Pre-training the student before real data arrives | Cloud only for approved inputs; mixing public voices is not anonymisation |

**Rule:** intended-population validity uses an **early-grade real classroom test set only** (D-2 held-out lessons, split by teacher and by school, never by random frames).

### 9.3 Annotation
- Tool: Label Studio (self-hosted on the in-country server) or equivalent audio segmentation tool [DECISION: ADR-006].
- STEM requires a separate instructor/student/group-speech guide and independent agreement; lab chatter is not choral response. D-0 never pools with D-2 evaluation.
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
  "cohort": "early_grade",
  "session_type": "early_grade_lesson",
  "label_schema_version": "early-grade-v1",
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

### 13.1 Sync API (sovereign tier)
- `POST /v1/lessons` — body: array of `LessonRecord`; response: per-record `{record_id, status: accepted|duplicate|rejected, reason?}`. Idempotent on `record_id`.
- `GET /v1/reports/latest?teacher_pseudo_id=…` — authenticated per teacher device token.
- `POST /v1/consent` — consent event append.
- `DELETE /v1/teacher/{pseudo_id}` — withdrawal, triggers purge.
- Auth: device-bound token issued at enrolment; server derives teacher/school scope from the authenticated enrolment and rejects mismatched payload IDs. Reinstall needs re-enrolment/recovery; tokens are revocable.
- A transaction persists each valid lesson before ACK. Same owner + record ID + same canonical payload is duplicate; changed payload is conflict, never success. Mixed batches return per-record results; retry only retryable failures.
- Withdrawal revokes every enrolled device, blocks stale uploads, purges records and derived reports/aggregates, and specifies backup expiry/restore deletion replay. Restrict any retained audit metadata and set its retention in WS-F.
- Report reads/deletes must enforce ownership server-side; query parameters are never authority. Set request limits and supported schema/spec versions at G1.

### 13.2 Model contract (edge)
```
seg_student.ms   input:  float32[1, 64, T]   (log-mel, T frames)
                 output: float32[1, T, 5]    (posteriors: TEACHER, CHILD_SINGLE, CHILD_CHORAL, OVERLAP, NON_SPEECH)
lid_student.ms   input:  float32[1, 64, 300] (3 s log-mel)
                 output: float32[1, 4]       (amh, sid, eng, oth)
```
Each model ships with a manifest: hashes, runtime/converter versions, feature and postprocess contracts, supported cohorts, label schema, input/output shape and class order, context/stride/lookahead and state reset, precision by layer, upstream licences, split/calibration provenance and eval metrics. Reject incompatible bundles atomically; use the last compatible bundle or stop processing. Overlapping windows emit each tick exactly once. MISSING resets streaming state. Feature parity must cover chunk boundaries, resampling, FFT/mel conventions and normalisation. Unknown LID is postprocessing, not a fifth output class.

---

## 14. Evaluation plan (definition of done)

Run [post-implementation suites](testing/POST_IMPLEMENTATION_TEST_PLAN.md) in stage order. The 18 prepared contract cases require actual implementation adapters; no product tests have run. P1 STEM establishes technical feasibility only; §14.1–14.3 and §14.5 remain P2 early-grade criteria. Report each cohort separately. Re-check conditions/models on P2 even if P1 passes.

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
- End-to-end RTF ≤ 0.3 including capture/features/LID/storage; bounded backlog and measured emission latency; peak RSS ≤ 300 MB; battery ≤ 10 percentage points / 40 min under documented conditions; report actual crashes/attempts and exposure. A small STEM sample cannot establish the longer-term 99% crash-free target. Also test the longest scheduled STEM session.
- Sync: 100% of valid, non-withdrawn records eventually delivered in a simulated 72-hour offline test with random disconnects.

### 14.5 Intended early-grade pilot / usefulness (after STEM technical exit)
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

---

## 16. Timeline and gates

Use [`research/RESEARCH_PLAN.md`](research/RESEARCH_PLAN.md) §5 for the single schedule and [`research/IMPLEMENTATION_REVIEW.md`](research/IMPLEMENTATION_REVIEW.md) for G0–G5 acceptance gates. Dates are targets, not evidence of approval or completion. No classroom recording, even a microphone test, precedes ethics and required consent. Before that, use authorised adult/staged fixtures.

Critical paths run separately: synthetic demo → competition submission; G4-U → STEM trial → technical exit → G4-E → early-grade collection → annotation → held-out validation → private pilot reports. Delayed ethics must not block the synthetic demo, and demo success must not be presented as classroom validity.

---

## 17. Competition mapping

The research audit reports 2026–27 Prelim/National weighting of Innovation 60 / Application value 40, mandatory Huawei Cloud deployment and a visual demo page, plus open-source/reproduction requirements at later stages. Reconfirm portal rules and Ethiopia division before submission. The table below organises evidence, not current scoring categories.

| Criterion | Dimts evidence |
|---|---|
| Creativity | Teacher-facing, not child-facing. No-ASR design turns a language gap into a strength. Proposed continuous acoustic language-share measurement; novelty and usefulness remain to be established. |
| System complexity | Edge (streaming inference) + sovereign server (openEuler/openGauss) + cloud (ModelArts) + dashboard; offline-first sync; consent ledger. |
| Technical complexity | Teacher–student distillation from a multilingual SSL encoder into an INT8 on-device student; real-time RTF ≤ 0.3 on a 2 GB phone; cross-script generalisation experiment. |
| Societal benefit | EGRA flatline (§2.1) → teacher feedback loop at near-zero marginal cost; data sovereignty by design; pilot numbers. |
| Functionality | Live demo on a cheap Android phone: start lesson, play recorded classroom clip, show indicators and report, show the synthetic cloud dashboard update. |

### 17.1 Demo script (draft, 15 min)
1. (2 min) The flatline chart. One sentence: "Ethiopia has measured children for 15 years. Teachers need frequent, private feedback."
2. (2 min) An approved synthetic/staged 20-second clip. Ask judges to guess the teacher talk ratio.
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
| Audio unusable (too noisy) | Med | High | Ethics-gated classroom test; authorised staged audio before approval. Test placement (desk vs wall), clip-on mic fallback. |
| Training/export hardware or framework incompatibility | Med | Med | Week-1 spike; ONNX escape hatch; keep student training in MindSpore. |
| CHILD_SINGLE vs CHILD_CHORAL not separable | Low–Med | High | Synthetic choral simulation; add OVERLAP class; fall back to CHILD_UNSPECIFIED with a new schema/model contract; I-3/I-4 and response-type I-2 become unavailable, not zero. |
| Sidaamu Afoo LID data scarce | High | Med | Collect consented adult speech early; use explicit unknown labels; any reduced label set needs a new model/spec version. |
| Phone too slow | Low–Med | High | Budget in W3 thin slice; reduce context window; frame skipping. |
| Teachers perceive surveillance | Med | High | Private-by-default reports; co-design report wording with 2 teachers; consent copy reviewed by E-Learning Directorate. |
| Team capacity | Med | High | Strict scope; roadmap items stay roadmap. |
| Legal interpretation of Proclamation 1321/2024 | Med | Med | Get written guidance via university legal/ethics office; default to in-country storage for all personal data. |

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
