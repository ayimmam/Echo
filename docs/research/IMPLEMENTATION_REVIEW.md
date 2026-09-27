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
