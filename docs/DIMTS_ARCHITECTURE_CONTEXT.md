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
