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
