# ADR-020: Provider-independent Hawassa pilot infrastructure

Date: 2026-10-11. Status: implementation and automated verification; external hosting/device validation pending.

The user separates Huawei competition work into `Echo-Huawei-Innovation-Competition` and requests the main Echo repository implement relevant infrastructure without Huawei coupling.

## Decision

Implement Django 5.2 LTS with PostgreSQL for the real research service, WSGI/ASGI gateways, strict minimized summary intake, individual consent-bound enrollment, an encrypted Android outbox, priority withdrawal, retention jobs and a restricted aggregate view. SQLite is development-only. This selects one maintainable stack instead of leaving all runtime directories as placeholders. Zergaw must support it and pass the existing provider capability gates; otherwise use a verified Ethiopian VPS. MySQL support is not inferred.

The APK now declares Internet permission for the explicit research adapter, with cleartext disabled. The default compiled origin is empty; no network request is made. A connected build binds one reviewed HTTPS origin and rejects alternatives/redirects. Audio, excerpts and private notes remain device-only. No analytics or external AI/translation calls are introduced.

Local processing is scheduled after capture and while charging, stores capture signal statistics under the lesson's retention deadline, and reports pedagogy unavailable. No learned model, diarization, semantic interpretation or educator-approved translation is implied. Future model/content release must pass the existing evidence and review gates.

Research data has collection-based expiry, never arrival-based extension. Withdrawal serializes with ingestion. The access-only backup excludes collected research tables so restore cannot resurrect teacher summaries; provider snapshots/WAL/logs still require separate verified deletion controls. Production startup refuses missing hostname/secret/database and unapproved hosting/retention evidence.

## Consequences

Core infrastructure can be developed, run and tested without Huawei or a real cloud account. A copied competition repository is not modified by this work. External deployment, real enrollment and field readiness still need hostname/capability/authorization/device evidence. Small-pilot research summaries are pseudonymous; the restricted aggregate view is not a public anonymous release.

See [implementation runbook](../testing/NON_HUAWEI_INFRASTRUCTURE_RUNBOOK.md), [summary contract](../contracts/RESEARCH_SUMMARY_V1.md) and [service guide](../../server/pilot/README.md).
