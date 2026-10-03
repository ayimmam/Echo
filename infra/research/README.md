# Separate in-country research environment

Scope revised 2026-10-03 by [draft ADR-016](../../docs/adr/016-defence-scope-and-no-recording.md): approved **existing** acoustic corpora, restricted consent/identity register, live observer totals and one bounded ML development path. No new captured audio, embeddings, posteriors or fine-grained timeline is persisted. Shared hosting never receives training jobs or corpus media.

**Removed:** the earlier Research Mode recording/transfer/storage path, per-lesson audio keys, durable media receipts, new 20–30-lesson corpus and Label Studio annotation service. These are not disabled pending provisioning; consent or a separate recorder cannot re-enable them. The previous six-step recording-service preparation sequence is superseded by the sequence below.

Provisioning/access remain unconfirmed. Prefer an approved university/private in-country machine with verified access, retention and operational ownership. A folder on the shared web account is not this boundary. Offline fixture work can proceed within its gates; field use still needs cohort-specific approvals and the Article 22 local-storage decision.

**Author access/publication:** draft [ADR-017](../../docs/adr/017-research-data-access-and-publication.md) and the [data management plan](../../docs/research/RESEARCH_DATA_MANAGEMENT_PLAN.md) require a named account/workspace for Anteneh, approved analysis scope and continuity after graduation. Separate R0 linkage/consent from R1 study snapshots and R2 approved anonymous publication files. No new portal or cloud warehouse: use checked numeric forms and approved local storage. Test author access, field allowlists, backup/withdrawal and disclosure under RD-01–08 before applicable collection/release. Proposed retention through 30 June 2029, final backup expiry 31 July, must be approved before consent. Anonymous scholarly outputs may be published after independent review; no pilot import to Huawei Cloud is enabled.

## Preparation sequence for the reduced research scope

1. Lead records available hardware/location, operator, funding and lawful access to existing acoustic corpora. Verify licences, speaker/source overlap and whether redistribution or derived weights are permitted. Do not obtain or use transcripts as model features or generate speech text.
2. Sys provides maintained software, approved private access and storage/backup in Ethiopia, with separate credentials/keys. Review remote support, telemetry and logging; public voice data is not automatically anonymous. Benchmark one training/export path on actual hardware.
3. Keep enrolment/consent contacts separate from pseudonymous observation forms. Observers retain only broad lesson totals, ten-minute counts and non-content uncertainty/timing metadata, never children’s names or speech content. Set approved finite retention and access scopes.
4. Rehearse the [A5 live-observer protocol](../../docs/research/FEATURE_REVIEW.md#a5--replace-the-unavailable-research-corpus-with-bounded-live-validation), assess reference repeatability, and freeze development/test allocations before held-out use. This cannot produce 10 ms ground truth.
5. Test protected access, backup/restore/deletion and missed-job handling for the actual retained records using synthetic forms. No audio-transfer/ANNOT service is provisioned. Publish only approved non-personal summaries and reproducible licensed assets.
6. Obtain G4-U before STEM and a separate G4-E before Grade 2 observation. The model/reference must pass their own gates before the associated indicator is shown. No pilot data, weights, soft labels, logs or identifiers flow to Huawei Cloud.

Record prerequisites as NOT STARTED, BLOCKED, FAILED or VERIFIED with owner/evidence. Current state: **environment unconfirmed; no field/model validity established**. Lack of suitable lawful data or repeatable live observation narrows the thesis to engineering feasibility; it does not justify recording.
