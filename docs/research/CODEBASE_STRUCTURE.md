# Codebase structure — Dimts

Updated 2026-10-02. [ADR-015](../adr/015-capability-gated-hosting.md) adopts the structure and security contracts below. Runtime, database and deployment remain conditional on Zergaw's confirmed capabilities. The user authorised scaffolding only, with no product features. Research infrastructure is not yet available; Research Mode is subsequently **removed** by draft [ADR-016](../adr/016-defence-scope-and-no-recording.md); retained directories do not authorise a recording service.

## Current layout and status

```text
Echo/
├── docs/
│   ├── adr/015-capability-gated-hosting.md
│   ├── adr/016-defence-scope-and-no-recording.md
│   ├── security/ARCHITECTURE.md         # trust boundaries and SEC-01–12
│   ├── testing/                       # product and hosting/security procedures
│   └── research/                      # STEM-first plan, evidence, contracts
├── edge/android/                      # app is a Compose starter; other areas placeholders
├── indicators-core/                   # proposed Python/Kotlin implementations and golden data
├── ml/                                # research placeholders; no training on shared hosting
├── server/                            # framework-neutral contracts, no running application
│   ├── api/                           # future HTTP adapters
│   ├── domain/                        # authorised use cases and idempotency contracts
│   ├── security/                      # authentication, scope and redacted audit contracts
│   ├── config/                        # future fail-closed runtime configuration
│   ├── db/migrations/                 # selected engine only; currently empty
│   ├── jobs/                          # bounded scheduled CLI work, DB leases/checkpoints
│   ├── templates/                     # future rendered reports/dashboard
│   └── tests/                         # future product tests
├── dashboard/                         # design boundary; no separate frontend service
├── infra/
│   ├── in-country/
│   │   ├── shared-hosting/            # conditional default, derived records/reports only
│   │   │   ├── README.md              # capability intake and deployment sequence
│   │   │   └── capabilities.example.json  # deliberately unconfirmed
│   │   └── vps/                       # fallback; compose/ and backup/ placeholders moved here
│   ├── research/                      # separate private in-country setup; not provisioned
│   ├── huawei-cloud/                  # isolated synthetic demo; ecs/rds/obs placeholders
│   ├── openeuler/                     # legacy path redirect only
│   └── ci/                            # future CI definitions
├── tools/check-hosting/               # executable offline evidence checker
└── tests/
    ├── infrastructure/                # executable checker tests, synthetic evidence only
    └── acceptance/                    # 18 contract vectors and harness; needs product adapters
```

Other existing ML, Android and tools placeholders remain. Named responsibilities above are not implemented modules. No server entry point, framework dependency, database migration or deployment automation is supplied before host selection; the deployment scaffolding is a capability manifest and runbook. Only the empty OS-specific backup/Compose placeholders moved.

## Starter pass — 2026-10-03

Starter code with **synthetic data** was added at the user's request; ADR-015's "no product features" note above is superseded for these items only. Added: `edge/android/app` demo UI; `indicators-core/python/dimts_indicators/` (reference + adapter + tests) and `indicators-core/golden/coarse-v0-draft.json`; `tools/synthetic-lessons/synth_timeline.py`; `tools/demo/` generator and `mock-data/` (shared with the Android assets via Gradle); `server/api/demo_wsgi.py`, `server/domain/aggregation.py`, `server/config/demo_settings.py`, `server/templates/demo_*` and `server/tests/`; `tests/demo/`. The stub is framework-neutral and **does not select** FastAPI or Django.

## Stack and dependency boundaries

One modular Python application handles the API and rendered pages. HTTP and CLI adapters call authorised domain use cases, which access scoped repositories; neither templates nor jobs bypass authorisation. The web process does not import ML frameworks, run inference, host annotation or train models. See [server contracts](../../server/README.md).

Prefer PostgreSQL. Managed ASGI permits FastAPI/PostgreSQL; WSGI-only hosting permits Django with PostgreSQL or a supported MySQL/MariaDB version. Select and pin one path after provider evidence and tests. Do not build two backends or maintain cross-engine migrations. Scheduled CLI jobs must survive bounded runtimes and retries; no resident worker, Redis, Docker or root access is required for the shared profile. VPS is the fallback if required capabilities or isolation fail. openEuler is optional there; openGauss is not the baseline.

The later feature review adds readiness/pause/discard/context and teacher-selected cards to the Android plan, makes sync optional, reduces the model to a coarse candidate and defers production LID. Kotlin/Compose/encrypted storage and all enabled-sync safety contracts remain. No module/build changes accompany this docs review.

## Data boundaries

| Target | Allowed data / access | Release condition |
|---|---|---|
| Shared hosting | Derived records, minimal pseudonymous enrolment/consent references and private reports | Provider evidence + HOST/SEC suites + G4-U; no audio/features/training targets |
| Private research environment | Approved existing acoustic corpora, restricted identity register and live observer totals | Approved access/retention/ethics; **new audio recording/transfer/annotation service removed**, not waiting for provisioning |
| Huawei Cloud demo | Synthetic records; other assets only after separate provenance/privacy/licence review | Separate identities, secrets, DB and logs; no pilot-derived import |

Research is a separate environment, not another folder in the shared account. All pilot/research personal data, logs, replicas and backups stay in-country under the existing project boundary. The synthetic cloud demo should use the same selected app/engine when available. Neither the undergraduate trial nor a complete capability checklist approves early-grade release.

Draft [ADR-017](../adr/017-research-data-access-and-publication.md) adds the [stakeholder map](STAKEHOLDER_MAP.md) and [research data/access plan](RESEARCH_DATA_MANAGEMENT_PLAN.md). Actual consent/linkage (R0), coded study tables/analysis (R1) and approved anonymous publication files (R2) live in the approved research environment, **not new folders in this repository**. Anteneh's named access and post-graduation continuity are readiness requirements; a checked numeric-form workflow avoids a new research portal/service. No modules are scaffolded by this planning change.

## Verification

[Hosting/security procedures](../testing/HOSTING_SECURITY_TEST_PLAN.md) cover provider selection and future implementation. [Existing product suites](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) still govern device, model, indicator and cohort gates. The checker verifies declared evidence completeness only; it cannot establish provider truth or actual security. See [infra](../../infra/README.md) for commands and profile selection.

## Contract changes worth making now

**Historical full-scope `LessonRecord` v1 → v2 (§10.1), illustrative subset only:** ADR-016 requires a new coarse-label/spec version, unavailable deferred indicators, initial enumerated context and local-only later exclusion/card state. The example below preserves the earlier proposal; it is not the reduced active schema. Freeze full schema, null/availability reasons, denominators, consent reference, processing versions and validation at G1. No previous version is implemented. STEM requires a distinct context without O–3 grade, compatible model/labels, and cohort-isolated reports/aggregates (ADR-014).

```jsonc
{
  "schema_version": 2,
  "cohort": "early_grade",
  "session_type": "early_grade_lesson",
  "label_schema_version": "early-grade-v1",
  // …all v1 fields…
  "capture": {
    "mic": "builtin|wired_lavalier|usb",   // E1 / D-7
    "observed_s": 2357.5,
    "missing_s": 42.5,                     // all lost capture/processing; excluded from every eligible denominator
    "class_size_estimate": 58              // entered by teacher; C-5
  },
  "indicators": {
    // …I1…I8…
    "I2_response_latency_s": { "choral": {"median": 0.2, "n": 30}, "single": {"median": 1.4, "n": 12} },
    "I3_choral_ratio": 0.71                // reported as a balance indicator
  },
  "model_versions": { "seg": "…", "lid": "…", "model_manifest_hash": "…" }
}
```

**`model_card.json`** gains `upstream_models` (with licences), `precision_by_layer` (records which layers stayed FP16 because Lite lacks INT8 GRU), and compatibility evidence for all bundled native libraries/runtime packaging. Manifest also defines shapes, class order, streaming state/lookahead and atomic compatibility checks (review G1). Upstream/derived licences belong in this manifest; using a model only as a teacher does not automatically remove downstream restrictions.

## Ownership (from §15)

| Area | Owner |
|---|---|
| `indicators-core/python`, `golden/`, `docs/` (ethics, legal, competition, indicator-rationale) | Lead |
| `ml/`, `tools/synthetic-lessons/audio_mix.py` | ML |
| `edge/`, `server/`, `dashboard/`, `infra/`, `tools/check-elf-alignment`, `tools/lesson-simulator` | Sys |
| `indicators-core/kotlin`, `ml/features/parity` | Sys + ML |
