# ADR-015: Capability-gated shared hosting and separated research infrastructure

Date: 2026-10-02. Status: **Repository structure/security contracts adopted for this pass; framework, database and live deployment remain conditional.**

> **2026-10-03 supersession:** [Draft ADR-016](016-defence-scope-and-no-recording.md) removes this ADR’s research-recording provision, makes personal-data sync optional after the local slice (subject to Article 22 review), and defers real official/browser reports. Hosting capability selection, one framework/DB, bounded jobs, security and the existing structural work remain in force.

## Context and user decisions

The user intends to use Zergaw Standard shared hosting in Ethiopia. They confirmed that runtime/database/cron support is not yet known, requested structure/contracts/deployment scaffolding only (no product features), and reported that a separate in-country research machine is not yet available. Earlier documents assumed an openEuler/openGauss VPS and overgeneralised limitations of shared Python hosting.

## Decision

1. Make `infra/in-country/shared-hosting/` the preferred pilot target; move empty OS-specific VPS scaffolding to `infra/in-country/vps/`. Keep `infra/openeuler/README.md` as a historical redirect.
2. Host a small Python application for derived-record ingest, rendered reports and scheduled tasks. **Superseded by ADR-016:** the earlier research audio/annotation boundary is removed. `infra/research/` now covers approved existing acoustic corpora, live observer summaries and a restricted identity register; no recording/transfer service. Preserve the separate synthetic Huawei Cloud target.
3. Select one framework after evidence: FastAPI for verified managed ASGI; Django for managed WSGI-only. Python support or SSH does not establish either. Do not ship two frameworks or implement a speculative adapter layer. [FastAPI deployment](https://fastapi.tiangolo.com/deployment/manually/), [Django WSGI](https://docs.djangoproject.com/en/5.2/howto/deployment/wsgi/).
4. Prefer PostgreSQL. If WSGI hosting provides supported MySQL/MariaDB, choose one after transactional/idempotency/migration/restore tests; Django supports these engines. Remove openGauss as a baseline requirement. [Django databases](https://docs.djangoproject.com/en/5.2/ref/databases/).
5. Use bounded cron-invoked CLI jobs and rendered templates. No separate SPA, Docker/root dependency on shared hosting, persistent background worker or application-owned server-OS requirement.
6. Enforce the [security architecture](../security/ARCHITECTURE.md) as future implementation/release criteria. Add a fail-closed **offline evidence checker**, not a deployment or runtime-security implementation. Unknown host settings are blocked.
7. Keep the mobile architecture and existing STEM-first gates. ML framework/runtime selection is not changed by this hosting ADR; it remains subject to device/training evidence and is outside this structural pass.

## Alternatives and consequences

A VPS gives operational control but adds cost/administration; retain it only as fallback. Shared hosting may lower operational burden, but requires provider evidence on runtime, isolation, resources, scheduling and data/backup locations. No ASGI/WSGI conclusion can be drawn from the screenshot control panel.

Separate domain/security contracts from HTTP and cron adapters, while using one selected framework's maintained facilities. Use the same framework/DB engine across pilot and synthetic demo where available. Do not keep an openGauss/PostgreSQL common SQL subset solely for an unused alternative.

No product routes, authentication implementation, schema migrations, dependency installs, live deployment or provider contact occurs in this pass. Exact versions/entrypoints await capability selection; existing historical evidence remains dated and subordinate to this ADR for current topology.
