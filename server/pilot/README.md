# Echo research service — provider-independent implementation

Updated 2026-10-23. Django 5.2 LTS, PostgreSQL for real deployments, WSGI and ASGI entry points. SQLite is a disposable development/test option. No Huawei SDK, cloud account or ML framework is required. This service is separate from `server/api/demo_wsgi.py` and never receives classroom audio. Study-scoped v2 admission is described in [the v2 contract](../../docs/contracts/RESEARCH_SUMMARY_V2.md); existing primary v1 admission remains supported.

## Disposable local run

From the repository root, using Python 3.10+:

```sh
python3 -m venv .venv
.venv/bin/python -m pip install -r server/pilot/requirements.txt
.venv/bin/python server/pilot/manage.py migrate
.venv/bin/python server/pilot/manage.py test research
.venv/bin/python server/pilot/manage.py runserver 127.0.0.1:8001
```

Use synthetic data only in development. `/healthz` exposes service/schema status only. `/readyz` returns 503 if the database is unavailable or retention maintenance is missing/overdue. Run the initial purge before routing real traffic. SQLite state is ignored by Git. Production refuses SQLite, missing private secrets, wildcard hosts, or absent hosting/retention approval flags. Pin updates after testing; `requirements.txt` records the versions tested for this slice.

## Research enrollment and withdrawal

1. A responsible operator reviews the separate study consent and issues a **single participant-specific evidence reference**. Do not use a name or one shared reference for all teachers.
2. Run `manage.py issue_enrollment`. Enter the evidence reference privately; give the generated code to that participant over an approved private channel. Codes expire in 24 hours, are single-use and are hashed in the database. Grants expire 30 days after issuance. Provision enrollment near the start of the study.
3. The app requires a compiled, approved HTTPS origin, the private code and explicit study acknowledgement. Default builds have no origin and send nothing. All real devices receive individual credentials; no shared application API key.
4. The teacher explicitly submits the selected class/week review. The encrypted queue freezes the submitted payload. Later editing does not silently rewrite an acknowledged summary.
5. Withdrawal removes pending local summaries immediately and retries remote withdrawal ahead of uploads. Server transactions lock the grant so revocation wins over stale uploads. Deleting/excluding a lesson withdraws the current enrollment to retract potentially affected submissions.
6. If the first enrollment response is lost, the code may be consumed. The operator runs `manage.py rotate_enrollment` after the participant requests recovery; this deletes the previous summaries/token and rotates the one grant without extending its lifetime. It refuses withdrawn/expired grants. This recovery is intentionally not an unauthenticated public endpoint.

Do not publish consent references or tokens. A withdrawn participant needs a newly reviewed study decision before any future enrollment; recovery must not override withdrawal.

## API contract

All API operations are POST with `Content-Type: application/json`, a 4 KB maximum body, bounded per-minute request rates, and no redirects or cookie authentication. Production requires HTTPS. Wrong credentials never reveal grant membership.

| Endpoint | Input / result |
|---|---|
| `/api/v1/research/enroll` | `{ "enrollment_code": "private one-time code" }`; returns private bearer token, opaque grant ID and grant expiry |
| `/api/v1/research/summaries` | Bearer token plus exact schema below; `accepted` or `duplicate`; conflict is 409, expired collection is 410 |
| `/api/v1/research/withdraw` | Bearer token and `{}`; idempotently withdraws and deletes summaries |
| `/research/` | Django-authenticated aggregate viewer with `research.view_research_aggregate`; no subgroup/filter endpoint |

Summary v1 contains exactly: `schema_version=1`, UUID `record_id`, coarse `collected_at_ms`, grade 1–4, `language` (en/am/om/sid), `class_size_band` (1-39/40-70/71-150), `lesson_count`, `recorded_minutes`, optional 1–5 `rating`, enumerated `practice` and `tried`, and the supported content version. These are participation/usefulness data, not model-derived pedagogy metrics. Unknown fields, notes, names, exact class identifiers, audio, transcripts and model claims are rejected.

Collection is conservatively floored to a UTC day and anchored to the earliest included lesson/reflection. Expiry is the earlier of collection + 30 days or grant expiry; retry/arrival never extends it. Stable credentials make data **pseudonymous**, not anonymous. Enrollment must prevent one person inflating contributor counts with multiple grants.

## Restricted research view

`manage.py create_researcher USERNAME` creates a password-validated primary aggregate viewer without staff/superuser flags. Browser login uses Django CSRF protection and rate limiting; logout is POST. Session cookies are secure in production. The primary dashboard combines its own study only, requires five contributing participants and separately requires five rated participants for usefulness. Each participant is weighted equally. The separate `/research/secondary/` route requires `research.view_secondary_aggregate`, which the operator must grant explicitly. No school/language drill-down, individual records or export exists.

This authenticated operational view is not an anonymous publication release. It must not be published or differenced across visits. Public research outputs still require disclosure review. The five-contributor rule alone does not prevent repeated-release inference.

## Retention and operations

Run `manage.py purge_research` every 15 minutes through approved cron; reads exclude expired data immediately, while physical deletion occurs on the next successful job. It removes expired summaries/grants/tokens/references and temporary rate windows. `manage.py research_status` exits nonzero if purge is over one hour late. Both commands output no participant data; wire the exit status to the host's operator alerting system.

`manage.py backup_access /absolute/private/path.json` writes a mode-600, non-overwriting backup of access accounts/permissions **only**. It excludes grants, summaries, enrollment tokens and sessions. Encrypt these backups and keep them in Ethiopia. Do not take full-database snapshots of research tables without an independently verified expiry/withdrawal mechanism. Provider-managed snapshots are part of the hosting gate, not controlled by this command.

After access-only restore, start with empty research tables and expire/re-enroll credentials; old bearer tokens cannot restore deleted data. An entire database restore is not a supported recovery path for collected data in this implementation. PostgreSQL WAL/replicas, provider backups and logs still need verified retention and deletion controls before production. Never claim those controls from an environment flag alone.

See [deployment runbook](../../docs/testing/NON_HUAWEI_INFRASTRUCTURE_RUNBOOK.md), [research contract](../../docs/contracts/RESEARCH_SUMMARY_V1.md) and [in-country deployment templates](../../infra/in-country/django/README.md).
