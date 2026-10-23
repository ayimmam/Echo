# Pilot and synthetic-demo backend

The real provider-independent research service is implemented in [pilot/](pilot/README.md): Django 5.2 LTS, PostgreSQL, WSGI/ASGI, consent-bound enrollment, strict summary intake, withdrawal, retention and restricted aggregates. It has no Huawei dependency. Follow the [infrastructure runbook](../docs/testing/NON_HUAWEI_INFRASTRUCTURE_RUNBOOK.md) and [ADR-020](../docs/adr/020-provider-independent-pilot-infrastructure.md).

Zergaw must support the selected stack and pass the existing capability/security/retention checks before deployment; otherwise use a verified Ethiopian VPS. Development/test use can run locally with disposable SQLite. No external service is deployed by the code, and Android defaults to an empty research origin.

The service never accepts classroom audio, transcripts, notes, names or model-derived pedagogy claims. Research summaries remain pseudonymous. Existing top-level domain/security/jobs contracts describe earlier broader backend proposals; pilot/ is the actual narrow research implementation. Browser teacher/director/official report accounts remain outside this slice.

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
For local launch and a browser/HTTP checklist, use the [synthetic demo runbook](../docs/testing/DEMO_DEPLOYMENT_AND_MANUAL_TESTING.md).
