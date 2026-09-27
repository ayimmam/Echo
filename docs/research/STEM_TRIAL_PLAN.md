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
