# Non-Huawei pilot infrastructure runbook

Updated 2026-10-11. This implementation is in the original Echo repository; the competition repository is independent. No Huawei library/service/account is needed to build or run the pilot research infrastructure.

## Implemented components

| Component | Status and verification boundary |
|---|---|
| Android encrypted recording, recovery, playback, 30-day expiry | Existing implementation; JVM tests pass; real-device execution still required |
| Deferred local processing | Charging-aware job stores captured signal statistics; pedagogy/model inference explicitly unavailable |
| Research enrollment and summary queue | Explicit consent/code, immutable encrypted payload, bounded HTTPS retries, ACK markers, priority withdrawal |
| Host isolation | Empty origin by default; connected build binds one HTTPS origin and refuses redirects/other destinations |
| Research API | Django/PostgreSQL migrations, consent grants, strict numeric/enumerated schema, idempotency, transactional withdrawal, rate/body limits |
| Research view | Browser authentication/CSRF and explicit aggregate permission, five-contributor gates, no subgroup or individual-record route |
| Retention/recovery | Collection-anchored expiry, scheduled purge, maintenance status, token recovery without extending expiry, access-only backup |
| Deployment | WSGI/ASGI, private env template, Gunicorn/proxy/systemd/cron examples for verified Ethiopian hosting |

## Automated checks

From the repo root:

```sh
python3 -m venv .venv
.venv/bin/python -m pip install -r server/pilot/requirements.txt
.venv/bin/python server/pilot/manage.py test research
.venv/bin/python server/pilot/manage.py makemigrations --check --dry-run
```

SQLite runs all portable cases and skips the PostgreSQL row-lock race. Repeat with a disposable PostgreSQL test database configured using `ECHO_DB_ENGINE=postgresql`, `ECHO_DB_HOST`, `ECHO_DB_PORT`, `ECHO_DB_NAME`, `ECHO_DB_USER`, `ECHO_DB_PASSWORD` and the appropriate verified SSL mode. The testing role needs permission to create its disposable test database; production service roles do not.

From `edge/android`:

```sh
./gradlew :app:testDebugUnitTest :app:assembleDebug :app:lintDebug --offline
```

Offline needs cached dependencies. A connected Android test build additionally requires `-PechoResearchOrigin=https://APPROVED_HOST`, a disposable HTTPS service and individually issued test credentials. The default build exercises local features without enabling remote research calls.

The GitHub workflow `.github/workflows/pilot-infrastructure.yml` defines PostgreSQL, reference-Python and Android build checks with synthetic data and no production credentials. Its commands are verified locally; the workflow has not been run on GitHub in this task.

## Integrated acceptance pass

Use only synthetic summaries and authorized adult device-test audio until field readiness is approved.

1. With the default APK, record/review/clear data offline. Verify no research endpoint or network request is attempted.
2. Against a disposable approved HTTPS service, issue one participant-specific enrollment code. Check the separate consent acknowledgement and one-time enrollment; retrying/expired codes must fail. Never record the code/token in the test report.
3. Submit a selected class/week. Inspect the **keys**, not private content: there must be no names, class identifiers, notes, audio, transcripts or model metrics. Inspect queued/acknowledged/rejected counts.
4. Lose connectivity before/after POST. Verify an identical retry receives duplicate, never duplicate records. Do not edit the frozen payload to “fix” a conflict.
5. Deny/expire/revoke token. Sharing stops and does not resume silently. Credential recovery rotates the one existing grant, deletes previous summaries and preserves its original expiry.
6. Withdraw offline. Pending summaries disappear immediately; new submission is blocked; the app states withdrawal pending. Restore connectivity and verify remote deletion before displaying completion. Test withdrawal while ACK is in flight and while server intake holds a grant lock.
7. Delete/exclude a submitted lesson. The app retracts the enrollment rather than leaving an acknowledged stale summary. Confirm the privacy UI updates and no queue item is recreated.
8. Advance time to exact expiry on disposable phone/server. App audio/analysis/queue records expire; server reads deny expired data before cron; purge physically removes it on its next allowed run. Old retry never extends expiry.
9. Create fewer than five enrolled contributors. No counts or ratings appear. With five contributors but fewer than five rated ones, rating remains unavailable. Staff flag alone must not grant dashboard access; test unauthorized filters and browser CSRF.
10. Run purge twice, then maintenance probe. Disable cron in a disposable environment and verify the status fails after an hour. Confirm an operator actually receives the configured host alert.
11. Create an access-only backup; verify it contains no grants/summaries/sessions. Restore into a clean database and confirm old tokens/data are absent. Provider snapshots and WAL require a separate demonstrated retention decision.
12. Run the existing [device checklist](OFFLINE_PILOT_RUNBOOK.md), including battery/memory, screen lock, route loss, boot and permissions. Network jobs must not initiate new work during recording. Measure rather than infer resource performance.

## External readiness and limitations

Zergaw runtime/PostgreSQL/cron, domain/TLS, tenant isolation, encryption at rest and Ethiopia-only primary/replica/log/backup locations are not verified by the code. Production startup requires reviewed hosting/retention flags, but flags are not evidence. Follow the [deployment guide](../../infra/in-country/django/README.md) and existing provider checker; use a verified Ethiopian VPS if the selected stack is unsupported.

Django's deployment check can warn when whole-domain HSTS subdomain/preload options are disabled. Enable those options only if the hostname owner confirms all relevant subdomains support HTTPS and understands preload requirements; do not apply them blindly to shared hosting domains. Clickjacking, secure session cookies, exact hosts and HTTPS are enforced separately.

No validated acoustic/pedagogy model, four-language reviewed UI/coaching, live external translation API, institutional approval or field performance evidence has been supplied. The deferred local job is signal processing only. The research service's restricted aggregates are pseudonymous operational data; public research release still requires disclosure review.

## Verification recorded 2026-10-11

- Android: 82 JVM tests, no failures/errors/skips; APK assembly and lint succeed with existing warnings.
- Research backend: 29 tests pass on isolated PostgreSQL 16.15, including actual transaction/withdrawal locking. SQLite runs 28 and skips that one PostgreSQL-only case.
- Existing Python suites: 12 indicator, 18 synthetic server, 7 mock-data and 10 hosting tests pass.
- Django schema consistency/system checks pass. Production deployment checks pass when privately supplied security settings include reviewed whole-domain HSTS options; this does not prove hosting approval.
- Gunicorn 26.2.0 loopback HTTP checks passed with disposable data for enrollment, accepted/duplicate ACK, withdrawal and stale-upload rejection; no real host or teacher account is provisioned by this implementation.
- No Android device/emulator is connected. Android network/certificate behavior, job scheduling, Keystore, playback and battery behavior remain device acceptance work.
