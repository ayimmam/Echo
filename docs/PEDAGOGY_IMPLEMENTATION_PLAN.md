# Echo pedagogy and offline evidence implementation plan

Prepared 2026-10-23. Status: proposed implementation sequence; [implementation status](PEDAGOGY_IMPLEMENTATION_STATUS.md) records work completed after the plan was written. Neither document grants field authorization.

## 1. Outcome and scope

Deliver a complete offline teacher-reflection workflow: record or review lesson context, choose a curriculum-relevant goal, inspect available evidence, choose one reviewed teaching action, and revisit it. Introduce acoustic measurements only when their exact method and classroom scope have passed independent validation.

This plan implements the recommendations in [the 22 October pedagogy and offline feasibility report](echo_pedagogy_evidence_and_offline_feasibility_report.pdf), particularly sections 6–16 and 18–22. File-specific findings below come from inspection of the current working tree. The report proposes changes; it does not establish model accuracy, phone performance, learning impact or an approved expanded pilot.

There are three separately deliverable increments:

| Increment | Deliverable | Dependency on trained models |
|---|---|---|
| A: reliable reflection | Reviewed content, structured goals/context, deterministic summaries, follow-up, capture accounting and device evidence | None |
| B: validated acoustic support | Bounded local analysis, reproducible arithmetic, shadow evaluation and individually released metrics | A suitable licensed candidate and local validation |
| C: secondary pathway | Grades 11–12 profiles and curriculum-specific content, initially Grade 11 mathematics for feasibility | None for reflection; separate validation for any secondary role metrics |

C is a proposed extension from the discussion following the report, whose pilot remains Grades 1–4. Implement secondary software capability separately from enabling a secondary field cohort. Do not divide the existing eight-teacher primary sample between populations or pool their findings. Grade 11 mathematics is an initial planning assumption, subject to educator access and curriculum review; Grade 12 support can be developed without claiming it has been piloted.

Optional transient transcription is a later experiment. It is not on the critical path for A, B or C.

## 2. Existing behavior and concrete gaps

Android paths in this document are relative to `edge/android/app/src/main/java/org/ethioware/echo/` unless stated otherwise. **New** marks a proposed file; it does not exist merely because it is listed here.

| Current implementation | Gap to address |
|---|---|
| `pilot/RecordingService.kt`, `AndroidPrivateFiles.kt` and `PilotStore` implement encrypted AAC chunks, playback, recovery and expiry | Need measured target-phone acceptance, a benchmark mode and explicit capture-span/gap metadata |
| `pilot/PilotData.kt` has three hardcoded English draft cards | No full content review schema, applicability rules, pack validation or immutable card-version references |
| `PilotApp.kt` renders reflection text directly; `PilotViewModel.kt` selects cards from goal/subject | Need a pure composer and typed reflection state that remain useful without measurements |
| `SignalAnalysis` in `LocalAnalysis.kt` emits clipping and near-zero fractions | No acoustic inference; near-zero amplitude must remain distinct from non-speech |
| `domain/MetricEvidence.kt` and Python `evidence.py` have empty model-release registries | Preserve this default; connect real outputs through a trusted adapter and expand scope/coverage checks |
| Python `_alternations()` resets on missing capture but skips overlap | New report rule requires overlap to break a pair; introduce a new spec rather than changing historical meaning |
| `timeline.py` separates non-speech and missing capture | No explicit uncertain-speech-role category in the current coarse schema |
| `domain/Rules.kt` uses three eligible lessons and two days | Report proposes two recorded lessons per week; freeze a compatible collection/display policy before a metric trial |
| `ClassProfile.valid()` and the profile picker accept Grades 1–4 | Secondary support requires changes to UI, validation, cohort policies and content, not just the picker |
| Research v1 admits Grades 1–4, three practice IDs and one exact draft pack version | New cards and cohorts need a negotiated schema rollout; an app-only update would be rejected |
| `ResearchProjection` maps both `notyet` and `notuseful` to `no` | Preserve distinct follow-up meanings in a new schema |
| Research dashboard combines the whole pilot | New cohorts/studies require server-enforced separation before admission |
| Some UI text says nothing leaves the phone even when enrollment/submission is available | Make copy accurate for disconnected, enrolled, queued, acknowledged and withdrawn states |

The indicator calculation engine currently has a Python reference; the Kotlin arithmetic adapter remains future work. Existing Kotlin report/evidence logic is not evidence of a complete Kotlin timeline engine. Also reconcile the historical `undergraduate_stem` versus `stem_adult` cohort names through explicit boundary mappings, not a global rename that changes stored records.

## 3. Architecture and contracts

Keep the existing local flow and optional research service. Extract responsibilities from `PilotData.kt` incrementally, preserving behavior through each migration.

```text
Local encrypted capture + capture-span metadata
                   |
          bounded AAC decoder
                   |
        experimental acoustic candidate
                   |
     versioned arithmetic + evidence packet
                   |
       per-metric eligibility/release policy
                   |
teacher goal/context + approved content -> reflection composer -> local UI
                                              |
                                     follow-up and usability

Explicit research submission -> strict projection -> encrypted queue -> study-scoped intake
```

Audio, private notes, transcript text, feature tensors and speaker embeddings are not research-summary fields. No login, enrollment or network request is required for the local workflow.

### 3.1 Proposed typed contracts

| Object | Required information and invariant |
|---|---|
| `ClassProfileV2` | Stable class ID; schema version; explicit cohort and grade; stable subject/goal IDs; declared classroom language(s). Keep app language separate. Existing profiles migrate to primary context without inventing curriculum metadata. |
| `LessonContext` | Activity, optional curriculum/topic ID, teacher interpretation and other-adult/playback condition. Teacher corrections are distinct from measured output. |
| `CaptureManifest` | Committed chunk references, monotonic relative timing, sample counts, known gaps, terminal status and uncertainty. Do not infer audibility from successful recording. |
| `PracticeCard` / `ContentPack` | IDs and versions; grade/subject/language/goal applicability; curriculum reference where reviewed; prerequisites/materials/time; steps/example; source/evidence limitations; reviewer/review date/status; unsuitable contexts; integrity identity. |
| `ReflectionRecord` | Original creation/expiry anchor, goal, chosen card ID/version/pack hash, interpretation, follow-up answer, usefulness response and permitted local feasibility fields. Old unknown fields remain unknown. |
| `AnalysisResult` | Capture identity, source/build/model/runtime/codec/feature/spec provenance, coverage, uncertainty, metric values or reasons. Detailed frame outputs stay transient by default. |
| `MetricDecision` | Per-metric availability, stable reason, current release-policy identity and supported context. A trusted model result is still unavailable without a matching release. |
| `ReflectionReport` | Evidence observation or unavailable message, limitation, teacher context, proposed action, follow-up, card provenance. Every claim is attributable to a typed input or reviewed text. |
| `ResearchSummaryV2` | Strict approved numeric/enumerated projection; schema/content versions; original collection anchor. Study/cohort authorization comes from the server grant, not arbitrary client claims. |

These objects describe contracts, not a requirement to store everything. Persist only fields necessary for reflection/reproduction and permitted by the current data boundary. Freeze exact schemas in PR-01 before implementing serializers.

### 3.2 Compatibility and retention rules

1. Version local records, content packs, indicator definitions, model manifests, release policy and research payloads independently.
2. Use atomic, idempotent local migrations. A malformed record must produce a recoverable error, never a synthetic replacement or fabricated zero.
3. Keep old creation/expiry timestamps during migration, recomputation, update and retry. Deletion and expiry cover all newly introduced derivatives and checkpoints.
4. Preserve old indicator specs and their golden fixtures. A new algorithm never silently replaces an old recorded result.
5. Store card provenance at selection time. Current code writes a global content version on save; replace this with the actual selected content identity. Keep old public card definitions available for interpreting unexpired reflections.
6. Apply withdrawal and current release policy when displaying old measurements, without rewriting their original provenance.
7. A submitted queue item remains immutable, with its original UUID, schema, payload and deadline. Never convert an acknowledged v1 record into a v2 retry.
8. Application rollback must not trigger destructive migration or overwrite records it cannot understand. Stop incompatible writes with a clear message; use a tested forward repair where binary rollback is unsafe.

## 4. Work packages and acceptance criteria

### WP1 — Freeze contracts and isolate pure domain logic

**Priority:** P0. **Owner:** Android/domain lead with research and education reviewers. **Depends on:** none.

- Update [measurement evidence](contracts/MEASUREMENT_EVIDENCE.md) to reflect the encrypted AAC path and proposed unknown-role/coverage contracts.
- Add **new** `docs/contracts/CONTENT_PACK_V1.md`, `LOCAL_REFLECTION_V2.md` and `RESEARCH_SUMMARY_V2.md` as proposed contracts.
- Record adoption/supersession decisions in a new ADR, preserving ADR-019/020 authority until those decisions are actually made. Do not label the ADR accepted merely because the code plan exists.
- Extract typed profiles/reflections/content definitions from `pilot/PilotData.kt` into small domain files as changes require them. Keep the storage interface and encryption adapter.
- Add migrations for profiles, lessons and reflections, with an inventory of every stored object and expiry anchor.
- Define canonical cohort IDs and mappings for legacy test/demo contracts. Secondary profiles must never default to the primary or adult-STEM cohort.

**Done when:** old synthetic fixtures and current local records load with unchanged meaning; migration survives interruption/retry; malformed/unknown versions fail visibly; original expiry remains unchanged; no model release is introduced.

### WP2 — Reviewed offline content packs and languages

**Priority:** P0. **Owner:** Android/content tooling plus education and fluent-language reviewers. **Depends on:** WP1.

**Files:** replace `Coaching` in `PilotData.kt`; add **new** `pilot/content/ContentPack.kt`, `ContentRepository.kt`, `PracticeSelector.kt`; add a dedicated real-content assets directory separate from `mock/`; add **new** `tools/content/validate_pack.py` and pack fixtures.

1. Introduce strict pack validation for IDs, versions, sources, applicability, review status and language completeness.
2. Separate teaching-goal IDs from practice-card IDs; several practices can serve one goal. Do not store localized display text as an identifier.
3. Filter by approved cohort/grade, subject, goal, language, time/material constraints and teacher choice. Add a reason a suggestion fits, plus a decline/adapt option.
4. Keep draft packs usable only in clearly labelled development previews. Field configuration must not silently substitute unreviewed draft advice when an approved pack is absent.
5. First distribute packs inside the signed APK, with a manifest and hashes. Separate importable signed packs, key rotation and rollback protection are a later extension if needed; never embed private signing keys in the app or repository.
6. Localize the real pilot UI, including errors, evidence limitations and accessibility labels. Review Amharic, Afaan Oromo, Sidaamu Afoo and English separately; existing demo translations do not certify the real flow.
7. Show pack review date and missing-language status. Model-supported language remains independent of app/classroom language.

**Done when:** an incompatible or unreviewed card is never selected in field mode; corrupted packs are rejected; first use works in airplane mode; selected card/version remains stable after update; all released languages pass educator review and on-device glyph/wrapping checks. Missing reviewed content leaves recording/private reflection available with an honest unavailable message.

### WP3 — Deterministic reflection and follow-up

**Priority:** P1, started alongside WP2. **Owner:** Android/product. **Depends on:** WP1 and content interface from WP2.

**Files:** `PilotApp.kt`, `PilotViewModel.kt`; **new** `pilot/reflection/ReflectionComposer.kt`, `ReflectionReport.kt`, `ReflectionRecord.kt`; update `PilotStore` persistence.

- Move composition/selection decisions out of composables into pure functions.
- Render one relevant permitted observation with coverage and a limitation; otherwise render a goal-based reflection with an explicit unavailable reason.
- Ask for structured lesson interpretation before suggesting an action. Context may change wording/relevance, never original measured values.
- Preserve the existing choose/remove/follow-up workflow; distinguish `tried`, `not_yet`, `did_not_fit` and `unanswered`.
- Add structured barriers such as time, materials, unclear instructions or unsuitable lesson, with private optional notes kept local.
- Capture local reflection opened/completed events and foreground interaction duration only as needed for the study. Define completion explicitly; screen opening is not completion and background time is not engagement.
- Ensure reflection without any successful recording remains usable. Do not require three lessons, research enrollment or a valid model to choose a practice.
- Make all transfer wording state-aware, especially the unconditional banner and usefulness-sharing text.

**Done when:** synthetic/model/observer/self-reported evidence cannot be confused; no unsupported trend or teacher-quality claim appears; offline/no-metric/no-recording states work; the prior selected practice is correctly followed up even after pack updates or week navigation.

### WP4 — Capture accounting and target-phone benchmarks

**Priority:** P0. **Owner:** Android/device QA. **Depends on:** WP1 for persisted schema.

**Files:** `RecordingService.kt`, `PilotData.kt`, `AndroidPrivateFiles.kt`, `PilotViewModel.kt`; **new** `pilot/CaptureManifest.kt` and `tools/device-benchmark/`; extend [offline runbook](testing/OFFLINE_PILOT_RUNBOOK.md).

- Record per-chunk timing/sample metadata atomically after encrypted audio commit. Preserve known interruptions and unknown trailing loss separately; wall-clock changes must not manufacture captured duration.
- Recover only committed audio. Distinguish captured duration, attempted session duration, usable analysis duration and audibility checks.
- Maintain an all-attempts feasibility view separate from `weeklyLessons()`, which intentionally selects included nonempty lessons for reflection. Store failed-start outcomes only within the approved minimal retention scope.
- Add a debug/test benchmark path using authorized synthetic/public audio. It must exercise capture/decode/encryption and must not import synthetic metrics into real reports.
- Measure whole-process PSS/peaks, native allocation, runtime, storage and matched-baseline battery consumption. Record device model/OS/ABI, build/configuration and results without device serials or classroom content.
- Test screen-off capture, calls, input silencing, microphone unplug/route changes, storage exhaustion, process kill, reboot, clock changes, playback deletion and expiry.

**Provisional engineering budgets from report §13:** capture PSS ≤200 MB; acoustic-analysis PSS ≤300 MB; 60-minute capture; 60-minute analysis within 15 minutes without thermal warning; capture incremental drain ≤5 battery percentage points/hour under matched conditions. These are proposed targets to ratify after a two-phone spike, not established guarantees. Measure every enrolled phone configuration before field release.

**Done when:** known gaps are not treated as silence; failed attempts remain countable; no partial write is claimed as committed; device evidence documents both successes and failures; changed code does not weaken encryption, backup exclusion or 30-day expiry.

### WP5 — Indicator specification, Kotlin engine and policy conformance

**Priority:** P1. **Owner:** domain/ML QA. **Depends on:** WP1; can use synthetic timelines before WP6.

**Files:** Python `timeline.py`, `indicators.py`, `adapter.py`, `evidence.py`, `reporting.py`; Android `domain/MetricEvidence.kt`, `Rules.kt`; **new** Kotlin timeline/indicator engine and test adapter; shared acceptance and golden fixtures.

- Introduce a new coarse specification, with the final version allocated during WP1; retain specs 2/3 unchanged.
- Define uncertain-role speech separately from non-speech, overlap and capture loss. Freeze the denominator and eligibility rule for uncertain speech before calculation/release; no silent exclusion to improve apparent confidence.
- For the new spec, reset adult/child alternation pairing on overlap, missing capture and uncertain role. Freeze whether any long non-speech gap breaks a pair rather than adding an undocumented cap.
- Preserve independent I1 and I5 availability. I3/I4/I6 remain deferred; I2 experimental; I7 research-only initially; I8 uses neutral no-detected-speech wording.
- Extend exact release matching to declared language/context and required runtime/codec/microphone/configuration scope. Unknown required context makes a metric unavailable. Do not infer teacher role merely from adult voice.
- Reconcile the report's two-lessons/week proposal with the current three-lesson display threshold. Select and document either a justified two-lesson rule or a collection schedule that meets three; do not quietly lower a threshold to populate the dashboard. Reflection-only use is independent of this decision.
- Prevent aggregation across incompatible versions, languages, lesson contexts and model identities. Review all combined-ready UI paths so one unavailable metric cannot hide another independently released metric.
- Implement the Kotlin arithmetic engine independently against hand-calculated fixtures; its adapter must invoke production arithmetic rather than expected results or the Python implementation.

**Done when:** exact counts/categories/nulls match and floats match to `1e-6` across Python/Kotlin; tests cover overlap breaks, uncertain speech, gaps, bin boundaries, zero denominators and chunk partition invariance; historical fixtures still pass under their original specs; empty/expired/withdrawn release registries suppress metrics.

### WP6 — Bounded local acoustic analysis in shadow mode

**Priority:** P1. **Owner:** ML plus Android. **Depends on:** WP4/WP5 contracts and a permitted candidate/corpus.

**Files:** `LocalAnalysis.kt`, `AnalysisJob.kt`, `PilotData.kt`; **new** `pilot/analysis/AudioWindowDecoder.kt`, `AcousticAnalyzer.kt`, `AnalysisResult.kt`, `ModelManifest.kt`, `AnalysisCheckpoint.kt`; build/runtime dependencies only after a measured spike.

1. Decode committed encrypted AAC chunks into bounded RAM windows; never decode a whole lesson or write temporary plaintext PCM.
2. Begin with a speech-activity baseline and compare one compact adult/child/overlap candidate. VAD cannot supply speaker roles. Use a provisional model-file budget ≤10 MB; model size alone is not runtime memory.
3. Evaluate one portable CPU runtime candidate at a time. Select from measured operator support, export agreement, license, ABI/page-size compatibility and whole-process performance. Pin the chosen runtime; do not bundle several stacks by default.
4. Preserve timestamps and decoder/chunk boundaries. Reset state across capture gaps and test streaming against whole-input references with defined codec tolerances.
5. Run one cancellable worker with bounded queues and resume checkpoints. Capture has priority. Pause for recording, heat or low battery; add a user-run option because the current charging-only schedule may leave work pending indefinitely.
6. Check deletion/expiry before reads and commits. A worker completing after deletion cannot recreate a result. Checkpoints contain only approved progress metadata and expire with the source lesson.
7. Pin complete run provenance. Changed models/configurations create distinguishable results; historical observations are never silently reinterpreted.
8. Keep unreleased outputs out of teacher reports and observer screens. A missing/corrupt model yields a stable unavailable reason and a working reflection flow.

**Done when:** bounded-memory/cancellation/resource tests pass on representative phones; decoded AAC path is included in evaluation; model/runtime licenses are recorded; no model metric is automatically released. Candidate failure is a valid outcome and does not block Increment A.

### WP7 — Feasibility summaries and research v2

**Priority:** P1/P2. **Owner:** backend plus Android/research lead. **Depends on:** WP1/WP3; WP8 before admitting secondary research.

**Files:** `ResearchQueue.kt`, `ResearchTransfer.kt`, `PilotViewModel.kt`; `server/pilot/research/services.py`, `models.py`, `views.py`, migrations/tests; enrollment/status management commands; research contracts and runbook.

- Freeze the smallest v2 field set needed for the approved outcomes: original collection anchor, content/card identity, distinct follow-up answer, completion/usefulness and approved burden/attempt totals. Do not add free text or model metrics merely because the local app has them.
- Support a reflection-only submission without fabricating a lesson count or recorded minute. v1's minimum-one constraints cannot represent this case.
- Keep all-attempt capture outcomes distinct from selected/included lesson counts. Define missing responses and denominators; weekly ratings do not substitute for the report's one final teacher-level usefulness outcome.
- Introduce a server-owned study assignment and cohort scope on grants. A client cannot switch study by editing a grade field. Bind authorized aggregate access to that study and prevent cross-cohort pooling; contributor thresholds remain necessary but are not anonymity guarantees.
- Replace the single hardcoded content-version check with an explicit admitted pack/card registry. Arbitrary versions and card IDs remain rejected.
- Define consent/protocol version handling for new fields. Existing enrollment does not automatically authorize expanded collection; accept v1 where appropriate and keep v2 disabled for incompatible grants.
- Deploy backend v1/v2 admission first, then the client. Preserve queued v1 bytes and idempotency. Retire v1 only after old clients/queues are accounted for and their original retention windows expire.
- Retain exact-field allowlists, size/rate limits, grant locking, priority withdrawal and collection-based expiry. Reassess the 128-summary cap against the final schedule; do not create per-click upload traffic.
- Enrollment/outcome records must support counting all enrolled teachers, including nonusers, within the approved study window. Record one final rating per participant for that outcome; report missingness rather than averaging prolific users into the endpoint.

**Done when:** v1 remains valid during transition; unsupported fields/packs/cohorts are rejected; primary and secondary results cannot be combined; no-data users and unavailable outcomes are represented honestly; deletion/withdrawal/upload races and expiry tests pass. Offline usefulness never depends on server readiness.

### WP8 — Grade 11–12 product capability

**Priority:** separate extension. **Owner:** Android/domain plus secondary educators. **Depends on:** WP1/WP2/WP3; WP7 for optional research.

**Files:** profile validation/picker in `PilotData.kt` and `PilotApp.kt`; Python `cohort.py`, evidence/reporting contracts; Kotlin context/release policies; content assets; backend admission/study assignment; fixtures and docs.

- Add explicit Grades 11 and 12 support through configured cohort scope. Do not widen to all Grades 1–12 unintentionally.
- Add stable secondary subject IDs and optional curriculum/topic context. Start reviewed content with Grade 11 mathematics; expose Grade 12 practice recommendations only when their packs are reviewed.
- Replace child-specific copy where inappropriate through cohort-aware reviewed text.
- Keep adult/child labels from authorizing teacher/student measures in secondary classrooms. A future role model needs its own label contract, validation and release; no relabeling of logits.
- Adapt the independent observation rubric to secondary teaching. A primary rubric or translated card is not automatically validated for the new cohort.
- Optional class-level checks of understanding can be planned as a separate assessment contract with item/version, number responding and number correct, marked teacher-entered. Do not introduce pupil identities or exam predictions. This is additional scope, not required by the report or the first secondary reflection release.

**Done when:** both grades can be represented without breaking old profiles; Grade 11 content is properly scoped; unsupported Grade 12 content is disclosed; primary cards/metrics cannot leak into secondary reports; secondary research is separately enrolled and aggregated. A four-week feasibility result is never labelled evidence of improved entrance-exam pass rates.

### WP9 — Reference annotation and field evaluation

**Priority:** P2. **Owner:** research/education plus tooling. **Depends on:** WP4 and frozen WP5/WP6 candidate for measurement evaluation; WP2/WP3 for reflection evaluation.

- Add a restricted local playback annotation workflow, codebook/version, preselected windows, two independent rater passes and adjudication. Keep initial ratings separate from adjudicated results.
- Pair recorded-audio annotation with synchronized live observation to identify speech the microphone missed; playback alone cannot establish audibility of all classroom speech.
- Fine time-aligned labels are a new restricted research artifact under the existing aggregate-only evidence contract. Define their local protection/expiry and protocol explicitly before storing them; do not silently add them to summaries. If no such storage is authorized, retain only permitted aggregates and narrow claims accordingly.
- Freeze candidate/configuration before held-out evaluation. If any pilot data tune the model, exclude those teachers from the held-out claim; keep teacher/school identities out of public fixtures.
- Use the report's proposed initial 16 ten-minute windows/160 minutes and 32 twenty-minute baseline/endline observations/approximately 10.7 observer-hours as workload assumptions. Double coding, training, travel and analysis are additional work.
- Report errors, bias, eligible coverage, uncertain fractions and failure conditions. I1's five-percentage-point MAE aspiration and I5's proposed two-count-errors/ten-minutes threshold need local ratification; an average passing score cannot erase severe subgroup failures.
- Evaluate teacher comprehension using counterexamples and observe selected practice use independently. Questionnaire preference alone does not validate advice or measurements.
- Schedule paired analysis before baseline records expire. Do not extend retention to accommodate a delayed week-four visit. Any later follow-up needs a retention-compatible protocol.

**Done when:** independent labels, frozen manifests, negative findings and teacher-level denominators are documented in the permitted evidence store; only specifically supported metrics/contexts can be proposed for release. Public code/reproduction uses synthetic or properly licensed nonpersonal assets.

### WP10 — Optional transient ASR experiment

**Priority:** P3, separately estimated. **Owner:** ML/privacy QA. **Depends on:** Increment A, bounded analysis infrastructure and separate experimental scope.

- Process short excerpts in bounded RAM only; disable by default. No transcript UI is necessary for the experiment.
- Exclude text/tokens from storage, logs, exceptions, analytics, saved UI state, clipboard and network schemas. Do not persist embeddings as a workaround.
- Test distinctive synthetic canary phrases through normal completion, cancellation, backgrounding, exceptions and process death; inspect storage/logs/transport.
- Evaluate language-specific/code-switching and downstream-task accuracy, plus phone memory/battery. Avoid claiming correctness from transcript fluency.
- Release buffers promptly and clear mutable buffers where practical without promising erasure of all OS-managed copies.

**Done when:** documented leakage/resource/accuracy results justify continuing the experiment. ASR success does not release semantic teaching-quality judgments.

## 5. Proposed pull-request sequence

Effort ranges are initial engineering estimates in focused person-days of roughly six hours, including implementation, review and targeted automated checks. They exclude educator translation/review, model training/data acquisition, physical device runs, fieldwork, hosting activation and waiting time. Re-estimate after PR-01 and the two-phone spike.

| PR | Scope | Depends on | Estimate |
|---|---|---|---|
| PR-01 | Contracts, compatibility decisions, migration fixtures and accepted-scope ADR draft | — | 2–3 days |
| PR-02 | Typed local models and atomic migrations | PR-01 | 3–5 days |
| PR-03 | Pack schema, validator, loader, selector and draft/released separation | PR-02 | 3–5 days |
| PR-04 | Composer, follow-up semantics, local usability fields and accurate sharing copy | PR-02/03 | 3–5 days |
| PR-05 | Capture manifests, failure accounting and deletion/recovery extensions | PR-02 | 3–5 days |
| PR-06 | Benchmark harness, instrumentation cases and evidence templates | PR-05 | 2–4 days |
| PR-07 | New indicator spec, Python reference, Kotlin engine and parity adapters | PR-01 | 4–7 days |
| PR-08 | Per-metric release/coverage policy, compatible aggregation and real-flow adapter | PR-04/07 | 3–5 days |
| PR-09 | AAC decoder, one runtime/candidate, cancellable shadow analysis | PR-05/07; candidate available | 5–9 days |
| PR-10 | Study-scoped backend v2, migration and dual-schema admission | PR-01/04 | 3–5 days |
| PR-11 | Client v2 projection/queue compatibility and enrolled-denominator handling | PR-04/10 | 2–4 days |
| PR-12 | Secondary profiles/cohort guards and reviewed-content integration | PR-03/04; PR-10/11 for sync | 3–5 days |
| PR-13 | Restricted reference workflow and reproducible evaluation tooling | PR-06/07/09; protocol ready | 4–7 days |
| PR-14 | Localization integration, release configuration, end-to-end regression and handover docs | Relevant increments ready | 3–5 days |

Total engineering planning envelope: **43–74 person-days (258–444 focused hours)**, excluding WP10 and the nonengineering dependencies above. At the historical assumption of three students contributing ten hours/week each, that is approximately **9–15 team-weeks of engineering capacity before external work and scheduling constraints**. It is not a promised calendar delivery date. Increment A can ship before the model and secondary work; capacity should first fund PR-01–06 and the relevant PR-14 work.

Independent work packages can overlap across available team members; this plan does not assume extra staff or automated agents. Educator review and target-phone availability are likely schedule constraints even when code is ready.

## 6. Validation matrix

| Area | Required evidence | Blocking failure |
|---|---|---|
| Migration/retention | Old/new records; interrupted migration; clock change; original expiry; delete during analysis | Lost audio, extended retention, stale worker recreates deleted data |
| Content | Bad signature/hash/schema; unknown version; unreviewed translation; grade/subject mismatch; offline rendering | Draft or unsuitable advice presented as reviewed |
| Reflection | No recordings/model/network; incompatible weeks; changed cards; declined actions; missing responses | Invented observation, conflated self-report or network dependency |
| Capture | 60-minute screen-off and interruption matrix on target configurations | Silent loss, unsafe recovery, missing failure denominator |
| Arithmetic | Python/Kotlin golden vectors and streaming equivalence | Cross-gap/overlap counting, denominator drift, incompatible-spec reinterpretation |
| Release policy | Context/language/model mismatch; expired/withdrawn policy; I1-only/I5-only releases | Any unsupported metric shown or hidden eligible metric coupled to another |
| Runtime | Actual AAC decode; bounded windows; cancellation; memory/thermal/battery | Unbounded allocation, capture interference or incomplete provenance |
| Research | v1/v2 rollout; exact allowlists; study isolation; nonuser denominators; retry/withdrawal races | Unauthorized fields, mixed cohorts, resurrection after withdrawal or deadline reset |
| Secondary | Explicit 11/12 scope; curriculum fit; unavailable role model | Primary voice categories treated as validated secondary roles |
| Field interpretation | Unprompted teach-back; independent observation; burden and support dependence | Persistent teacher-quality/learning/equity misinterpretation |

Current automated entry points to reuse (commands are instructions, not results of this planning task):

```sh
# Repository root: Python reference and historical acceptance suite.
python3 -m unittest discover -s indicators-core/python/tests -v
python3 tests/acceptance/runner.py --validate
python3 -m unittest discover -s tests/acceptance -p 'test_harness.py'
python3 tests/acceptance/runner.py --adapter '["python3", "indicators-core/python/dimts_indicators/adapter.py"]'

# Repository root, after the documented research environment is installed.
.venv/bin/python server/pilot/manage.py test research

# From edge/android, with the required JDK/SDK and cached dependencies.
./gradlew :app:testDebugUnitTest :app:assembleDebug :app:lintDebug --offline

# Connected device/emulator: extend instrumentation beyond the current placeholder.
./gradlew :app:connectedDebugAndroidTest
```

Add the new Kotlin acceptance adapter and content validator to CI once implemented. Keep original fixtures and add version-specific new cases; do not change historical expected values to make a new algorithm pass. Instrumented tests do not replace physical microphone/thermal/battery tests. No model/field PASS is inferred from unit-test success.

## 7. Rollout and decision gates

These named milestones are specific to this plan; they do not renumber historical repository G0–G5 gates.

| Milestone | Required result | Permitted next step |
|---|---|---|
| Contract-ready | Schemas, versioning, retention, collection/display thresholds and scope documented | Implement bounded work packages |
| Reflection-ready | Reviewed content for released languages, complete offline workflow, migration and device evidence, applicable field permissions | Primary reflection feasibility study |
| Shadow-ready | Frozen candidate, validated runtime path, reference protocol and protected local research workflow | Collect permitted model/reference comparisons with outputs hidden |
| Metric-ready | Held-out agreement/coverage and teacher comprehension support the exact metric/context | Add only that reviewed metric release |
| Secondary-ready | Reviewed secondary content, device checks, separate cohort/protocol/enrollment and no primary-role leakage | Secondary reflection feasibility study |
| Impact-study-ready | Feasibility supports a longer comparison, with planned independent units and learning measures | Separate learning-impact study; not an automatic app release claim |

Roll out first to development with synthetic/public test material, then authorized device rehearsals, then the approved cohort. Preserve a reflection-only configuration throughout. Backend schema expansion precedes connected client rollout. A broken candidate or withdrawn metric returns the app to reflection-only behavior; a recording/deletion failure stops classroom recording until fixed.

APK-bundled content is the first update mechanism. A later offline importer must authenticate updates, preserve a last-known-compatible bundle and prevent rollback to a withdrawn/expired measurement policy. Devices without an update cannot receive instant revocations; policy validity and unavailable behavior must be explicit.

## 8. Risks, decisions and ownership

| Decision/dependency | Proposed default | Needed before |
|---|---|---|
| Two versus three lessons/week | Choose one explicit collection/display policy with researchers; reflection works regardless | Freezing metric eligibility |
| First secondary subject | Grade 11 mathematics; Grade 12 software support separately scoped | Secondary content review/recruitment |
| Reviewed languages | Release each only after educator/language approval; disclose absent packs | Classroom use in that language |
| Acoustic corpus/candidate | One permitted compact baseline; keep model work time-bounded | PR-09 and held-out study |
| Runtime and device budgets | Decide after two-phone measurements; no assumed acceleration | Packaging inference |
| Detailed annotation storage | Separate restricted local protocol, no summary export | WP9 persistence |
| Research v2 consent/study assignment | Explicit versioned grants; no silent scope expansion | Connected rollout |
| Final usefulness/adoption outcomes | Predefine participant denominators, missingness and support exposure | Enrollment |
| Data retention and follow-up | Keep existing 30-day collected-data boundary; complete paired analysis in time | Study scheduling |
| Field resources | Name education reviewers, observers, device operator and research custodian | Claiming field readiness |

The 20,000 ETB allocation remains hardware-only; it does not fund the engineering estimate, translation, observer work or hosting operations. If resources tighten, defer ASR, broad secondary subject coverage and automatic metrics while preserving reviewed reflection, reliable capture and honest evaluation.

## 9. Completion checklist for this implementation program

- [ ] Contracts and migrations are versioned, reviewed and documented.
- [ ] Released content is curriculum/language reviewed and traceable to sources and reviewers.
- [ ] Teacher reflection works offline without a model or research account.
- [ ] Target-device capture, recovery, expiry and analysis evidence is recorded with failures.
- [ ] New arithmetic has independent golden expectations and Python/Kotlin conformance.
- [ ] Experimental outputs stay hidden; any release is metric/context-specific.
- [ ] Research v2 preserves consent, original deadlines, withdrawal and study isolation.
- [ ] Secondary support has separate content and measurement scope.
- [ ] Field findings separate technical feasibility, comprehension, self-report, observed practice and learning claims.
- [ ] README, contracts, runbooks and capability descriptions match the actual shipped increment.

These boxes define the full acceptance criteria. See the linked implementation status for current progress; a partial code change does not satisfy an unchecked field or model gate.
