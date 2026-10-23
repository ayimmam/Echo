# Pedagogy implementation status

Updated 2026-10-23. This records code landed against [the implementation plan](PEDAGOGY_IMPLEMENTATION_PLAN.md). It is not a field-readiness statement.

| Work package | Current implementation | Remaining before the plan's acceptance gate |
|---|---|---|
| WP1 contracts and compatibility | Profiles now serialize an explicit primary/secondary cohort and optional topic; legacy profiles parse without a migration rewrite. Draft content identity is retained on reflection selection. | Freeze full typed local contracts, migration/rollback tests and the superseding ADR. |
| WP2 content | Bundled English draft cards now include grade/subject scope, version, target skill, limitations and unsuitable conditions. A draft secondary mathematics example is present. | Educator and fluent-language review; released four-language packs, integrity/withdrawal tooling and device layout checks. No current card is marked reviewed. |
| WP3 reflection | A pure deterministic composer produces an observation, limitation, action and follow-up without needing audio metrics. UI displays honest research state and scoped card options. | Complete language/UI review, observed usability, fuller barrier and burden capture. |
| WP4 capture | New recordings store per-chunk sample ranges and elapsed commit time plus total attempted time when available. Older recordings retain unknown timing. Signal analysis exposes capture fraction only when an attempted duration exists and explicitly marks gap timing unavailable. | Target-phone benchmark harness, live audibility checks, all-attempt failure accounting and full interruption matrix. |
| WP5 indicators | Experimental spec 4 adds `UNKNOWN_ROLE`, makes overlap and uncertain role break alternation pairs, and withholds I1 if role is uncertain. Specs 2/3 and their golden vectors remain unchanged. A separate Kotlin coarse-v1 engine calculates I1/I5 from a strictly tiled timeline; secondary contexts are isolated and no model metric is released. | Freeze thresholds, add full cross-language parity adapter/fixtures, validate per-metric context/coverage and connect only a trusted analysis result. |
| WP6 acoustic analysis | Existing signal-only job remains; no model has been attached. | Licensed candidate, bounded AAC decoder, resource benchmarks and held-out shadow evaluation. |
| WP7 research | Server migration adds study and schema scope to grants. V2 admits reflection-only totals and distinct follow-up states; Android builds v2 after compatible enrollment. Primary v1 remains accepted. Dashboard routes and permissions are separated by study. | Protocol approval, final-outcome denominator capture, operations/hosting activation and physical device transfer tests. |
| WP8 secondary | Grades 11–12 can be configured locally with separate card filtering. Secondary submissions require a v2 secondary grant. | Reviewed subject/grade curriculum content, independent secondary feasibility cohort and any secondary acoustic validation. |
| WP9 reference evaluation | No new annotation workflow. | Research authorization, protected reference workflow, trained raters and field study. |
| WP10 transient ASR | Not implemented. | Separate research decision, phone resource evidence and transcript leak tests if pursued. |

Automated verification completed during this change: Android JVM tests and debug assembly; Python reference and historical acceptance tests; Django research tests and migration consistency check. The PostgreSQL row-lock race test is skipped under the local SQLite test database. No physical Android device, classroom data or independent educator review was available in this run.

The existing README's pilot brief remains Grades 1–4/eight teachers. Grade 11–12 support is a separate development pathway and does not authorize enrollment in the primary study.
