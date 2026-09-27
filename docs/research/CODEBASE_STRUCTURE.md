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
