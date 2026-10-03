# ADR-016: Defence scope, offline reflection and no persisted research audio

Date: 2026-10-03. Status: **Draft architectural decision from the requested feature review; current planning baseline, not implementation, ethics or deployment approval.**

## Context

The June 2027 undergraduate deadline competes with an eight-indicator, two-model, research-corpus and live district-service plan. The user fixes RAM-only audio (≤10 s), no ASR/speech-derived text, no child identity/embeddings, no teacher scoring/comparison, Ethiopia-only personal data and suppressed-only official access. The earlier encrypted Research Mode contradicts that audio commitment. The evidence and complete feature dispositions are in [FEATURE_REVIEW.md](../research/FEATURE_REVIEW.md).

## Decision

1. Remove Research Mode, classroom audio files/uploads, the new 20–30-lesson/6–10-hour replay-annotation corpus, Label Studio media service and classroom-recording-dependent fine-tuning. Consent, encryption or a separate recorder do not create an exception. No saved audio features, posteriors, embeddings or fine timeline replaces recordings. Already existing lawfully usable acoustic corpora require provenance/licence review; no speech text is produced or used.
2. Deliver Grade 2 reading first: offline capture/readiness, pause/resume, discard before save, encrypted summary, lesson context/exclusion, local report and three reviewed practice cards in Amharic, Sidaamu Afoo and English. Teacher chooses one card or none; one optional follow-up at the next weekly opening. Metrics never choose a weakness or a target talk ratio.
3. Attempt one coarse adult/child/overlap/non-speech model. Adult voice is a teacher proxy only in confirmed single-adult/no-playback lessons. A **new label/schema/spec version** is required for `ADULT/CHILD_UNSPECIFIED/OVERLAP/NON_SPEECH`; never silently reinterpret the old five-class logits or fixtures. VAD alone cannot yield teacher talk. I-1 is conditional and I-5 separately gated. I-2/3/4/6/7/8 and production LID remain explicitly deferred and unavailable; their old definitions/fixtures are preserved.
4. Use one training framework and one shipped runtime. Lite is the first candidate; compare the same graph on one fallback only if the bounded export/device spike fails. Remove mandatory distillation/two-framework training; defer CRNN/conformer challengers, cloud training and unrelated roadmap work. Actual measured precision and memory/latency/battery govern release.
5. Replace new recorded ground truth with consented, independently observed lesson totals and ten-minute turn tallies. Freeze reference repeatability, sample and model-error gates before held-out use; [review A5](../research/FEATURE_REVIEW.md#a5--replace-the-unavailable-research-corpus-with-bounded-live-validation) owns the numerical proposal. These data cannot establish frame F1, subsecond latency or causal learning impact. If evidence fails, show unavailable and report a feasibility result; agree the thesis question with the supervisor.
6. Preserve ADR-014’s P0 → STEM P1 → early-grade P2 order and separate approvals. STEM is capture/VAD/system validation, without a new adult-role classifier. No adult result validates child labels.
7. Keep optional opt-in derived sync after the local slice, subject to ADR-015’s capability-selected single backend/DB and every applicable security test. Defer real browser-teacher/director/official accounts and all real aggregate releases. Keep synthetic suppression tests. Local card/exclusion state is authoritative for the phone and is not uploaded; no later local preference silently mutates sealed records.
8. Use an isolated Huawei Cloud visual demo (one service initially) with synthetic records and a separately labelled real inference demonstration. Do not claim synthetic-cloud eligibility is confirmed. Personal field data/artefacts never flow to cloud. Reproducibility, licence/history review and actual current competition rules remain gates.
9. Obtain a written decision on Article 22(1)’s server/data-centre wording before fielding a phone-only personal-data design. If local-only use is not accepted, enable compliant derived-data hosting or keep real field use blocked. Offline fixture development does not depend on buying hosting. No research exemption weakens a fixed guardrail.

## Alternatives considered

- Keep eight indicators and recording-only Research Mode: rejected; guardrail conflict and unresolved labels/validity make the deadline unrealistic.
- Infer pedagogical quality from talk-share thresholds: rejected; no universal ideal or Ethiopian causal evidence.
- Build a new mentor/district workflow: deferred; reuse optional teacher-led conversations within existing CPD structures.
- Ship only synthetic JSON: insufficient for a claim of working acoustic AI; label simulation honestly and demonstrate the model separately.

## Consequences and validation

**Author follow-up, 2026-10-03:** draft [ADR-017](017-research-data-access-and-publication.md) specifies consented derived-research access and anonymous scholarly outputs. It does not revive Research Mode, give researchers teachers' private reports, upload local card/exclusion state, or enable official accounts. The teacher may separately contribute a minimal study summary; research inclusion/withdrawal is distinct from immutable sync records. The no-field-data path into Huawei Cloud remains.

The defence loses detailed choral/LID and classroom-training novelty. It gains a testable private workflow and time for useful field evidence, negative results and writing. The reduced target is still conditional on lawful existing data, target-phone performance, reference reliability, participant access and staff time. [Implementation gates](../research/IMPLEMENTATION_REVIEW.md), [research plan](../research/RESEARCH_PLAN.md) and [acceptance suites](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) carry the changed scope. No feature becomes validated by adoption of this ADR.

ADR-015’s hosting choices/security requirements and the existing uncommitted structural work are preserved. Its research-recording provision is **superseded**, not merely blocked awaiting a machine. The architecture-revision conversation and old mockup images are historical design material, not the June 2027 delivery contract.
