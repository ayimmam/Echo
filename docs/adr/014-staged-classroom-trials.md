# ADR-014: Undergraduate STEM technical trial before early-grade validation

Date: 2026-09-27. Status: **Stage order directed by user; detailed schema/model design proposed for G1 ratification.**

## Context

The intended product serves O-class and Grades 1–3, but the user plans to test the setup first in undergraduate STEM classrooms. Audio roles, participant ages, session formats and languages differ. Neither the starter app nor the proposed models have established classroom readiness.

## Decision

Proceed P0 synthetic/staged → P1 undergraduate STEM technical trial → P2 intended early-grade validation. Use the in-country pilot tier for both real cohorts and keep the Huawei Cloud demo synthetic. P1 is a setup/feasibility trial, with separate approvals and voluntary participant consent, not evidence of child-speech accuracy or early-grade pedagogical value.

Require cohort/session context, label schema and model supported-cohort declarations. STEM annotations describe adult instructor/student roles; child-only model logits cannot be renamed into those roles. Keep manifests/calibration/evaluation/reports isolated by cohort. Disable early-grade coaching and district aggregation for STEM. If no adult-compatible model exists, P1 can still test capture, coarse activity, storage/sync and human-coded indicator arithmetic with explicit provenance.

## Alternatives considered

- Begin in early grades: superseded by the user's requested order.
- Reuse child labels and combine results: rejected because it conceals the domain change.
- Build a separate university product: unnecessary scope; use shared infrastructure with explicit cohort contracts.

## Consequences and validation

P1 precedes P2 recruitment/capture readiness, with distinct G4-U/G4-E approvals. Technical fixes can transfer, but microphones, model/indicator accuracy and coaching need early-grade revalidation. Dates move when prerequisites slip. [STEM_TRIAL_PLAN.md](../research/STEM_TRIAL_PLAN.md) owns trial details; [acceptance suites](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) own test procedures. No product implementation is authorised by this ADR alone.
