# ADR-017: Consented derived research data, author access and publication

Date: 2026-10-03. Status: **Draft architectural decision requested by the author; planning baseline, not institutional approval or implemented access.**

## Context

The author requests a stakeholder participation map and a research output with access to anonymised data. ADR-016 removed persistent audio and narrowed the thesis to engineering feasibility, coarse model/observer agreement and teacher usefulness. These questions need paired and repeated measurements; removing every link would impede validation, while merely replacing names would not make a small classroom dataset anonymous. No research machine or institutional access agreement is confirmed.

## Decision

1. Adopt [STAKEHOLDER_MAP](../research/STAKEHOLDER_MAP.md) for participation, approvals and consent rights. Anteneh F. Yimmam is the lead analyst/intended corresponding author; the university must identify its controller, formal PI, ethics route and custodian.
2. Make the thesis, manuscript draft and usable author research-data access explicit deliverables. Agree named, task-scoped access in Ethiopia before collection, including post-graduation access for revision. Target retention/access through 30 June 2029, subject to institutional approval before consent; no actual permission is granted here.
3. Separate R0 identity/consent/linkage, R1 restricted pseudonymised research records and R2 disclosure-reviewed anonymous publication aggregates, as specified in the [data management plan](../research/RESEARCH_DATA_MANAGEMENT_PLAN.md). Anteneh receives authorised R1 analysis access and the approved R2 package. Researchers do not receive teachers' private reports by default.
4. Research contribution is separately opt-in and withdrawable; a participant may omit a session without losing the local report. Sync consent is not research consent. Local card choices/follow-up/exclusion state remain private. No code, schema implementation, new server role, research dashboard or bulk export service is introduced by this decision; the minimum collection path is two-person checked numeric summary forms with protected in-country handling.
5. No captured audio, embeddings, features/posteriors, fine timeline, individual child records, speech transcripts or speech-derived research notes. Retain only allowed coarse measurements and separately consented structured, participant-selected responses. School/institutional consent logistics must avoid transferring child rosters into project records.
6. Require independent disclosure review for fixed scholarly releases. Five distinct contributors is only a minimum screen, not anonymity proof; small language groups and the four-teacher validation floor may prevent public numerical release. Do not enlarge the sample automatically or publish coded microdata. Restricted verification stays in Ethiopia.
7. Clarify prior “no pilot-derived export” wording: approved anonymous aggregate scholarly outputs can be published; personal records and field artefacts cannot leave Ethiopia and no research import into Huawei Cloud is enabled. The competition demo remains synthetic-only.
8. Apply withdrawal to source forms, analysis extracts, unreleased figures and backups; retain only minimal deletion-control metadata. Freeze does not override withdrawal. Provide a finite retention/closure plan and disclose the limits of withdrawing already anonymous public results.

## Alternatives considered

- Give the author an unrestricted production database copy: rejected; unnecessary access to private reports and unconsented data.
- Call coded lesson rows “anonymous” and release them: rejected; small sites, language, timing and repeated records can identify people.
- Keep only irreversibly unlinked totals from the outset: rejected as the only research path; it prevents pairing, teacher-cluster uncertainty and effective pre-publication withdrawal.
- Add a full research portal/cloud warehouse: deferred; controlled forms and an approved local research workspace fit the defence scope.

## Consequences and validation

Author access becomes a pre-field institutional agreement rather than an assumed privilege. A methods/feasibility paper is planned; its acceptance and the availability of safe publishable field tables remain uncertain. The existing time budget reserves 30 ethics/field hours and 30 writing hours for this work, rather than adding a new service. When capacity or consent fails, narrow evidence/claims, not guardrails.

The [RD acceptance cases](../testing/POST_IMPLEMENTATION_TEST_PLAN.md#h-research-access-and-publication-suite-g4-u-g4-e-and-public-release), G4-U/G4-E and publication review enforce these boundaries. ADR-015 hosting/security requirements and ADR-016's removed recording path remain unchanged. R2 publication review does not enable a real official dashboard.
