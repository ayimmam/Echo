# Measurement provenance and evidence contract

2026-10-06. Implementation contract under [ADR-018](../adr/018-measurement-evidence-and-sharing.md). On 2026-10-09 the synthetic fixtures and Android records moved to envelope v4 with provenance version 1; Kotlin and Python now apply fail-closed per-metric release decisions. The model-release registry remains empty, so no model metric is released. Examples and type names below remain design notation where no runtime service exists.

## Boundaries

```text
RAM-only capture → features/model → deterministic acoustic totals
                                         ↓
                           provenance + eligibility/release policy
                                         ↓
                        permitted local report / sharing projection

Independent live observer forms → restricted research comparison only
Teacher choice → practice card (independent of metric values)
```

Model confidence does not establish accuracy or pedagogical validity. Duration/coverage checks do not establish model release. A valid timeline/spec does not establish a supported real-world use. `NON_SPEECH`, `MISSING`, unknown context and unavailable measures remain distinct.

## Proposed objects and ownership

| Object | Minimum fields / invariant | Owner |
|---|---|---|
| Measurement | Stable metric ID, unit, numerator/denominator where applicable; origin `synthetic`, `model` or `observer`; method/spec version; available numeric value OR unavailable reason; observation coverage | Reference math and Android domain |
| Model provenance | Artifact SHA-256, runtime/export version, label schema, feature/postprocess configuration hashes; acquisition cohort/context; no speech content or device serial | Trusted inference adapter; caller cannot invent a model identity |
| Synthetic provenance | Fixture/generator version and seed/manifest reference, explicitly synthetic | Demo generator/adapters; never admitted as a real lesson |
| Observer provenance | Protocol/codebook version and permitted observer role code; common-window aggregate method | Restricted research environment; never silently substituted into teacher-facing model metrics |
| Metric release | Release ID and lifecycle `draft`, `experimental`, `released`, `withdrawn`; exact model/spec/config identities; permitted metric/cohort/language/context scope; evidence manifest hash; review/version references and limitations | Reviewed release configuration supplied through the controlled release process |
| Decision | `available` or `unavailable`, stable reason code, policy/release version; display/export scope | Pure release/eligibility policy, independently applied by consumers |

`available` requires a finite valid value; `unavailable` carries no exposed numeric substitute. Unknown origin or identity is rejected or unavailable, never defaulted to model-derived. Release evidence includes held-out/reference repeatability and resource/device results appropriate to that metric; I-1 and I-5 may have different statuses. A model's self-declared `supported_cohorts` remains necessary where used, but never sufficient.

New provenance fields require an explicitly versioned record envelope and migration. Existing synthetic fixtures gain declared synthetic provenance when migrated; legacy records with unknown provenance remain unverified. Do not silently reinterpret five-class spec 2 as coarse spec 3. Neither a schema bump nor a filled model card creates a release approval.

## Release decision order

1. Validate record shape, numeric invariants, time accounting, origin and exact schema/spec identity.
2. Apply purpose: demo accepts synthetic data only; real product accepts trusted model outputs; research may compare separately identified sources. A research observer measurement cannot unlock a model report.
3. Resolve the pinned model/feature/postprocess identity against a controlled release registry. It starts with **no real metrics released**. Public clients cannot set `approved=true`, attach an arbitrary approval URL or bypass a missing release.
4. Match permitted cohort, target grade/subject, declared lesson language and relevant observable conditions. Unknown required context, other adults or playback prevent teacher-proxy interpretation. Do not claim automatic noise/reverberation certification from a 30-second readiness check; unmeasured conditions are limitations.
5. Apply metric-specific acoustic sufficiency, missingness and frozen threshold rules. Uncalibrated confidence is neither an uncertainty interval nor a substitute for these checks.
6. Emit a versioned decision with a reason such as `unknown_provenance`, `unreleased_metric`, `unsupported_context`, `insufficient_observation`, `version_mismatch` or `release_withdrawn`. Keep teacher wording short; retain technical details in approved diagnostic metadata.

Supply release policy in the signed app/release bundle initially; a remotely updated policy, if later added, needs authenticated integrity/version and rollback protection using maintained mechanisms. Define its refresh/validity interval before field launch. Offline devices can enforce only the last valid policy they hold; do not promise instant remote revocation. An expired policy makes affected metrics unavailable. The server always checks its current authoritative policy before admitting/using a record.

## Aggregation and research

Weekly eligibility is **per metric**. Three eligible lessons in the week do not authorise I-5 if only one has a released/available I-5. Count qualifying lessons/days and coverage separately for each measure. Group compatible origin, cohort/grade/subject, declared language, model, label/spec, method and release policy; never average incompatible versions or observer/synthetic/model results together. Freeze any permitted compatibility mapping explicitly; no implicit conversion.

Preserve immutable measurement provenance. A later evidence withdrawal can change display/export eligibility without rewriting the original sealed record. Recompute cached views under the current policy. Research may retain an authorised experimental comparison with an explicit unreleased/unavailable status; it cannot present that comparison as a validated teacher metric. Rejected model-reference comparisons and uncertainty stay visible in study reporting.

The capture ring/features are transient. This contract permits aggregate numeric measurements and non-sensitive artifact identifiers, not persisted waveforms, feature arrays, posterior traces or fine timelines. Restricted common ten-minute research totals require the existing protocol and consent; they are not a new public export.

## Reproducible model and run manifests (future files)

Use the existing ML/evaluation directories. A release links a machine-readable manifest to a short model/evidence card and a run manifest: source commit, dependency lock hashes, corpus licence/provenance and split IDs, seed, training/config/checkpoint hash, converter/runtime/precision, tensor/class contract, approved test-fixture hash, device/OS/page size, build type, settings, accuracy/coverage/error breakdown, RTF/latency/RSS/battery, operator/date and evidence location. Report measured results only; no placeholder PASS.

Use a clean rebuild and fixed inputs to reproduce deterministic math; predeclare justified tolerances for floating-point/runtime results. Recheck the same frozen graph before/after export/quantization, stream boundaries/reset/warm-up, and the complete release pipeline under device interruptions. Private field manifests/results stay in-country; only reviewed R2 summaries and synthetic/public reproduction assets may be published.

## Mapping to current code

- `edge/android/.../data/Models.kt` and `MockJson.kt` carry record envelope v4, explicit synthetic/origin fields and model identity; legacy records remain unknown and unavailable.
- `edge/android/.../domain/MetricEvidence.kt` and `indicators-core/python/dimts_indicators/evidence.py` apply matching fail-closed per-measure decisions. The checked-in model release registries are empty.
- Android `domain/Rules.kt` and Python `reporting.py` gate I-1 and I-5 independently and refuse to average incompatible measurement signatures.
- `server/domain/aggregation.py` revalidates fixture provenance and only accepts explicitly synthetic records for the synthetic dashboard. It is not a real admission API.
- The deterministic indicator arithmetic remains independent of presentation, network, research forms and release storage. Shared cross-language policy conformance fixtures and any real release registry remain future work.
