# Pilot and synthetic-demo backend

One modular Python application; no product implementation exists yet. Framework selection is conditional: FastAPI if managed ASGI is verified, Django if only managed WSGI is available. PostgreSQL is preferred; confirmed supported MySQL/MariaDB may be selected with Django after transaction and recovery tests. Implement one combination, not all alternatives.

| Directory | Responsibility / dependency boundary |
|---|---|
| `api/` | HTTP validation, authenticated principal, calls to domain use cases; no direct bypass of scope checks |
| `domain/` | Enrolment/cohort rules, immutable lesson ingest, reports and withdrawal; no web-framework imports |
| `security/` | Central authorisation policy, credential/session lifecycle and redacted audit contracts |
| `db/migrations/` | Chosen-engine migrations and scoped repositories; separate migration privilege |
| `jobs/` | Bounded cron-invoked CLI jobs calling the same use cases, DB leases/checkpoints, retry/idempotency |
| `templates/` | Rendered dashboard/report pages, autoescaping, no separate SPA build |
| `config/` | Environment-specific validated settings and startup refusal conditions |
| `tests/` | Future API/database/security integration tests on synthetic disposable fixtures |

Dependency direction: HTTP/CLI → authorised use cases → scoped repositories. No public research-audio endpoints or training imports. The server never needs the ML framework installed to accept indicator records. Framework-native auth/session/CSRF mechanisms should be reused; do not create custom cryptography or a second generic web framework.

Under [ADR-016](../docs/adr/016-defence-scope-and-no-recording.md), personal-data sync is an optional later slice after local reports; live browser-teacher/director/official accounts are deferred. Templates initially serve synthetic competition previews. Local practice/follow-up/exclusion preferences are not uploaded or overridden by server reports. No recorded Research Mode service exists anywhere.

[ADR-017](../docs/adr/017-research-data-access-and-publication.md) adds approved author research access in a separate in-country workspace, not a researcher role on this backend or permission to dump teacher records. App/sync consent does not permit research reuse. The minimum study route is a separately consented, checked numeric form; no research portal/export endpoint is required by June. Anonymous scholarly output review does not enable real official accounts or pilot imports to the Huawei demo.

See [security architecture](../docs/security/ARCHITECTURE.md), [ADR-015](../docs/adr/015-capability-gated-hosting.md) and [hosting target](../infra/in-country/shared-hosting/README.md).

## Synthetic demo stub (throwaway)

`api/demo_wsgi.py` is a **framework-neutral, read-only** WSGI app over generated synthetic fixtures. It is *not* the selected backend (ADR-015 still gates FastAPI vs Django on provider evidence): no database, authentication, ingest or personal data. It gives the dashboard design something to render and the suppression rules something to test.

```sh
python3 server/api/demo_wsgi.py            # http://127.0.0.1:8000/  (loopback only; refuses other binds/purposes)
python3 -m unittest discover -s server/tests -v
```

| Piece | Role |
|---|---|
| `domain/aggregation.py` | Distinct-teacher counting, per-teacher-then-across averaging, hide cells under 5 teachers and reveal nothing about them, no totals row |
| `config/demo_settings.py` | Fail-closed settings: only `purpose=synthetic_demo`, loopback bind, refuses data not marked synthetic |
| `templates/demo_dashboard.html` | Escaped, script-free page with a strict CSP; the same I-1/I-5 contract with unavailable reasons |

Complementary-suppression and repeated-release tests are still required before any real release.
