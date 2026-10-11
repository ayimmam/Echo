# ADR-018: Measurement evidence, simpler UI and default sharing preferences

> **Current scope, 2026-10-11:** [ADR-019](019-offline-recorded-reflection-pilot.md) adds a separate real local recording flow. The default-on sharing preferences described here belong to the synthetic activity only. The local flow has no network permission, sharing toggle or transfer service. Provenance and model-release gates remain applicable; no model is released.

2026-10-06. Status: **Adopted planning/contract revision; demo UI/preferences and provenance gates implemented on 2026-10-09. Transfer services, device/model validation and institutional deployment approval remain pending.**

## Context

At adoption, the user confirms ADR-016's June 2027 scope and requests focused papers/code-convention research, plans/contracts, a simpler UI, derived-summary sync and research contribution ON by default, and opt-out controls only in Settings. On 2026-10-09, the user asks for implementation within the README's current offline teacher-reflection scope. [Focused review](../research/FOCUSED_ARCHITECTURE_REVIEW.md) records sources and inspected commits. Existing runnable components use synthetic data; current switches and `Measure.available` are not production validity/consent controls.

## Decision

1. Adopt [measurement/evidence contract](../contracts/MEASUREMENT_EVIDENCE.md): explicit source provenance; immutable model/spec/feature identity; independent, scoped metric-release evidence; unavailable reasons and compatible aggregation. Synthetic, observer and model results remain distinguishable.
2. Preserve deterministic indicator arithmetic and one model/runtime/backend plan. Existing cohort declarations are compatibility checks only; field release needs reviewed evidence. No automatic quality/equity/learning claim or metric-selected practice card.
3. Adopt [UI/sharing contract](../contracts/SHARING_AND_UI.md): three destinations (Today, Reflection, Settings), one primary task per screen, detail on demand, visible capture state and Settings-only sharing controls.
4. For new eligible enrolments, default private-summary sync and research contribution preferences to ON. Begin transfer without a per-session share action once a summary is saved and prior purpose-specific authorisation plus infrastructure readiness are verified. Brief first-use disclosure remains visible. Prior consent may be recorded by the approved study enrolment process; a default switch itself grants none. No upload before required authorisation.
5. Keep purposes independently controllable. Sync OFF stops future private-summary uploads; research OFF stops future research uploads and starts research withdrawal. Settings exposes deletion status/actions explicitly. Privacy-reducing migrations cannot turn existing OFF/withdrawn choices back ON.
6. Research receives only a versioned minimal study projection through its approved in-country intake. Private teacher reports, local card/exclusion state, audio/features/timelines and child identities remain excluded. R0/R1/R2 permissions and publication review still apply.
7. Automatic research intake is a newly planned capability. Define purpose-scoped outboxes, grants/revocations and server admission tests before implementation. Do not disguise a manual form route as automatic sync; re-estimate the previous optional-sync budget.

## Supersession and limits

This revises ADR-016 §7's opt-in sync default and ADR-017 §4's per-session opt-in/manual-only baseline. It preserves participant choice, separate research authorisation, no-recording/no-ASR guardrails, STEM-first progression, Grade 2 target and deferred five-way/official-dashboard scope. The synthetic Android demo now implements the navigation, local preferences and fail-closed provenance presentation rules. Zergaw's suitability remains unconfirmed.

The demo's default-ON settings are preferences only. It has no purpose grants, outboxes, model release, network behavior or approved destination; no data is transmitted. Record-envelope v4 and local-state migration preserve unknown or prior OFF choices. No production migration, dependencies, model weights or actual sharing service are included. Automatic upload on first launch without prior authorisation is not approved for field use; default ON operates only within the consent and readiness prerequisites described in the sharing contract.
