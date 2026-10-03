# Zergaw shared-hosting target

Preferred budget candidate, not yet verified. The user confirmed on 2026-10-02 that runtime, database and cron support are unknown. A screenshot advertising Python/SSH/SSL/100 GB cannot determine ASGI/WSGI, root access, process management or tenant isolation.

## Select one deployment path

| Confirmed capability | Candidate application | Database choice |
|---|---|---|
| Managed ASGI with restart and reverse proxy | FastAPI; rendered templates | PostgreSQL (this baseline) |
| Managed WSGI only | Django; rendered templates | PostgreSQL preferred; supported MySQL/MariaDB transactional configuration acceptable after testing |
| Neither, no package installation, insufficient isolation or no reliable scheduling | Do not deploy pilot here | Use the in-country VPS fallback |

Do not implement both paths. Record the selected runtime/framework/database versions after provider confirmation and a disposable synthetic smoke test. No framework-dependent entrypoint, connection string, worker command or Compose file is supplied while those choices are unknown. Do not run a web server under an SSH session as a substitute for provider-managed process lifecycle.

## Capability intake

Copy `capabilities.example.json` to `capabilities.local.json` (ignored). It contains no credentials. Each capability has `value`, `evidence` and `confirmed_on`: use a dated support ticket or private test-report reference. Populate all required fields after confirmation. Database/resource numbers must reflect the actual plan, not the word “unlimited”. The readiness checker requires both provider capability evidence and a supported application combination.

Ask Zergaw for:

1. Supported Python versions; ASGI/Uvicorn versus WSGI; virtualenv/pip support; managed restart/reload and rollback procedure.
2. Database engine/version, transaction support, DB/user permissions, connection cap and connection protection (authenticated TLS or verified local socket).
3. RAM, CPU, worker/process, disk/inode, request/body/time limits; scheduled-task frequency and maximum runtime.
4. Host/proxy configuration, HTTPS renewal, trusted forwarded-header handling and static document-root mapping. Secrets, DB dumps and private files must be outside the public root.
5. Account/filesystem/process isolation; staff access policy and access logging; secret injection, patch ownership and incident contact.
6. Primary, database, logs, backup/replica and restore locations; encryption at rest, daily encrypted recoverable backup, customer restore access and retention/deletion procedure. Do not infer all locations from the primary server's country.

## Deployment sequence after product implementation

1. Complete intake and checker; ratify one framework/DB; run HOST-01–04 and applicable SEC cases on synthetic fixtures.
2. Install pinned dependencies in a private application directory/venv, never upload the repository wholesale into `public_html`/`web`.
3. Expose only reviewed static assets through the public document root. Framework gateway routes dynamic requests; secrets, source, fixtures, docs and research assets stay inaccessible.
4. Load secrets using provider-private configuration; use separate application/migration accounts and separate demo/pilot credentials. Set exact hosts/origins, debug off, verified HTTPS/proxy handling and production session settings.
5. Backup, migrate once with a deployment lock, load no real data, run auth/scope/restore tests and switch traffic using provider-supported restart. Keep a documented application rollback and schema recovery plan.
6. Schedule bounded, idempotent CLI jobs for reports, purge and backup. Use a DB-backed lease and checkpoint, not an in-memory timer or publicly callable cron URL. Alert on missed runs; cron limitations are a selection gate.
7. Enable undergraduate pilot enrolment only after G4-U. Research Mode has been removed (ADR-016); no raw-audio/media endpoint exists. Repeat G4-E separately.

Proposed recovery objectives: backup at least daily, RPO ≤24 h, restore target ≤8 h in a tested run. Retention is set through the approved pilot policy before collection; do not retain backups indefinitely. These are project targets, not claims about Zergaw's service. Weekly-only backup does not meet them.

Capacity acceptance uses the [STEM pilot load profile](../../../docs/testing/PILOT_LOAD_PROFILE.md): three devices syncing simultaneously across a seven-day pilot; conservatively test nine records/day and a 63-record weekly backlog. Session count per device and 90-minute duration remain assumptions. HOST-03 still requires payload limits, agreed numeric latency/resource budgets and actual measurements.
