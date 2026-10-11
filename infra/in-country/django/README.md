# In-country Django deployment

Executable application: `server/pilot/`. Deploy only on a verified Ethiopian host with PostgreSQL and a managed WSGI/ASGI lifecycle. No Huawei resources are involved. The included service/proxy/cron files are templates, not evidence of a deployed server.

## Shared hosting

1. Complete the existing capability intake and `tools/check-hosting` review. The implemented Django/PostgreSQL stack requires supported Python/pip, transactional PostgreSQL, private environment injection and reliable CLI cron. If Zergaw only provides MySQL or cannot support this stack, use the approved Ethiopian VPS route; this implementation does not pretend to support MySQL.
2. Install the code and virtualenv outside `public_html`. Point the managed WSGI gateway to `server/pilot/pilot_backend/wsgi.py`, with `server/pilot` on the Python import path. ASGI entry is `pilot_backend.asgi:application` if a supported gateway is used.
3. Copy `.env.example` into provider-private configuration, set exact hostname/secrets/database settings and review primary/replica/log/backup locations. Do not upload the repository's research PDFs or demo fixtures to the document root.
4. Set hosting/retention approval flags only after reviewing the evidence. Run `manage.py check --deploy` with those production settings before enabling the gateway.
5. Migrate with the separate migration role; run the service with a least-privilege application role. Collect static files to a reviewed public-only directory. Dynamic source and configuration remain private.
6. Schedule purge every 15 minutes and hourly status probes through a private wrapper that loads environment settings. The status exit code must reach an operator alert; missed purge is not silently accepted.
7. Validate HTTPS and proxy-header stripping, permission failures, enrollment/withdrawal, limits, deployment rollback and restore on synthetic disposable data.

## Ethiopian VPS fallback

Use the example Gunicorn config and systemd service with an `echo` service account, private `/etc/echo/pilot.env`, installed Python dependencies and an approved PostgreSQL service. The Nginx example terminates HTTPS using operator-provisioned certificates, strips forwarded identity headers and limits bodies to 4 KB. Do not expose the Gunicorn listener publicly.

The Nginx example collapses remote addresses at the application gateway; its shared-IP rate budget is sufficient only for this eight-teacher pilot. For broader deployment, add verified gateway rate limits and trusted client-IP handling, not blind trust in client `X-Forwarded-For` headers. Certificate renewal, OS patching, database TLS/certificates and provider logging require an operator-owned setup.

Backups use the access-only command. Full snapshots/WAL archives containing collected data must be disabled or subjected to an approved expiry/withdrawal implementation. A daily full snapshot retained another month conflicts with the current one-month data promise. Keep private accounts/backups encrypted in Ethiopia. Record provider evidence and a successful synthetic restore before real enrollment.

## Android origin binding

After HTTPS/provider evidence passes, build from `edge/android` with:

```sh
./gradlew :app:assembleDebug -PechoResearchOrigin=https://APPROVED_HOST
```

Use the real approved Ethiopian hostname, not the illustrative placeholder. The runtime refuses other destinations and never follows redirects. Default builds leave this property empty and keep research sharing disconnected. A user cannot redirect data by entering a different host. Do not place credentials in Gradle properties, APK resources or command arguments.
