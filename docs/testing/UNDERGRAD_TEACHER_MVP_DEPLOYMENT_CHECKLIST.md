# Undergraduate teacher MVP deployment checklist

Use this checklist only after the implementation plan's release gates are satisfied. It is a handoff checklist, not evidence that the current build is field-ready.

Current implementation status (2026-11-17): the code-verifiable MVP is implemented across WP0–WP7, including the new bilingual UI, K9 signal check, shadow deletion, versioned card pack, timed reminder, environment admission, synthetic Render blueprint, bounded audit/events and usefulness calculation. Automated suites pass. English/Amharic human design approval, physical K9/device qualification, deployed staging, PostgreSQL concurrency, institutional authorization, signed release and field evidence remain open. See [the status matrix](../UNDERGRAD_TEACHER_MVP_IMPLEMENTATION_STATUS.md).

## 1. Freeze the release candidate

- [ ] Assign the release commit and tag; confirm `git status --short` contains no unintended files.
- [ ] Record the accepted design-export revision and resolved decisions from WP0.
- [ ] Record owners for Android, backend/database, Render synthetic staging, the approved live host, bilingual content review, and incident response.
- [ ] Record the adult-rehearsal dates, devices, expected sessions, longest lesson, longest offline interval, and stop/pause authority.
- [ ] Confirm the research summary schema, usefulness denominator/coverage rule, shadow-delete disclosure, and live-summary deletion policy are approved in writing.
- [ ] Keep adult rehearsal audio and ratings local unless the exact destination has separate authorization.

Create a release manifest containing:

```sh
git rev-parse HEAD
git status --short
shasum -a 256 edge/android/app/build/outputs/apk/release/app-release.apk
```

Record the app commit, APK hash, backend commit, schema/contract versions, coaching-pack version, design revision, Android build variant, research origin, host/environment, and approval references. Do not put participant credentials or consent references in the manifest.

## 2. Run automated release gates

- [ ] Run Android unit tests, assembly, and lint from `edge/android`:

```sh
./gradlew :app:testDebugUnitTest :app:assembleDebug :app:lintDebug --offline -PechoPilotEnvironment=offline
```

- [ ] Build the signed release through the approved signing process; keep the keystore and passwords outside the repository.
- [ ] Run the Django and repository suites from the repository root:

```sh
.venv/bin/python server/pilot/manage.py check
.venv/bin/python server/pilot/manage.py makemigrations --check --dry-run
.venv/bin/python server/pilot/manage.py test research
.venv/bin/python -m unittest discover -s indicators-core/python/tests
.venv/bin/python -m unittest discover -s server/tests
.venv/bin/python -m unittest discover -s tests/demo
.venv/bin/python -m unittest discover -s tests/infrastructure
```

- [ ] Repeat Django integration tests with disposable PostgreSQL, including concurrent ingest/withdrawal.
- [ ] Archive test reports, lint reports, migration output, APK hash, and CI run URL with the release manifest.
- [ ] Stop if a test is skipped unexpectedly, a migration is generated, or the release APK differs from the recorded hash.

## 3. Qualify the offline Android build

- [ ] Install the default disconnected build; verify it has a blank research origin and sends no research traffic.
- [ ] On every supported phone/K9 pairing, record OS/API, ABI, RAM, free space, phone year/model, receiver/transmitter identity, and result.
- [ ] Prove the routed input after capture starts; test receiver-only, muted/off transmitter, unplug/replug, wrong receiver, phone-mic opt-in, calls, screen lock, process death, permission revoke, storage pressure, and reboot.
- [ ] Run setup → mic readiness → record → stop → playback → context correction → weekly reflection → 1–5 rating → support in English and Amharic.
- [ ] Repeat with TalkBack, large font, a small supported screen, rotation, and airplane mode.
- [ ] Verify share, download, and export are unreachable.
- [ ] Verify ordinary deletion disappears immediately and denies stale playback; encrypted audio remains until exactly 15 days from deletion even if the former 30-day deadline occurs first.
- [ ] Verify delete-all and explicit research withdrawal purge shadow-deleted audio immediately; test restart/missed maintenance and app termination during deletion.
- [ ] Measure battery, memory/PSS, thermal state, storage growth, captured/attempted duration, gaps, and stop-to-ready time against frozen budgets.
- [ ] Complete [OFFLINE_PILOT_RUNBOOK.md](OFFLINE_PILOT_RUNBOOK.md) and attach device evidence.

Do not enroll teachers if any supported K9/phone combination silently falls back, a deleted recording remains accessible, Amharic copy is unreviewed, or the measured device budget fails.

## 4. Deploy synthetic-only Render staging

- [ ] Create a staging service and PostgreSQL database in the approved region; label the environment `SYNTHETIC ONLY`.
- [ ] Configure the existing `ECHO_DB_*` settings, private Django secret, exact allowed host/origin, HTTPS/proxy settings, and the repository's required production approval flags. Use private same-region database connectivity.
- [ ] Do not copy live credentials, adult rehearsal audio/ratings, enrollment grants, or production backups into Render.
- [ ] Install `server/pilot/requirements.txt`, run `manage.py check --deploy`, and run migrations as a controlled release step.
- [ ] Run `manage.py purge_research` before routing traffic.
- [ ] Schedule `manage.py purge_research` every 15 minutes and alert when `manage.py research_status` reports stale maintenance.
- [ ] Configure `/healthz` as the minimal liveness check and monitor `/readyz` separately for database/purge readiness.
- [ ] Create only restricted researcher accounts and permissions; verify `/research/` and `/research/secondary/` authorization boundaries.
- [ ] Generate synthetic fixtures only. Verify accepted/duplicate/permanent-rejection/retryable outcomes, body/rate limits, CSRF/cookies, TLS, and shared-NAT behavior.
- [ ] Exercise database outage, stale purge, restart/cold start, rollback, and access-only restore. Confirm research tables remain empty after access-only restore.
- [ ] Document provider WAL, replica, backup, log, and deletion behavior. A configuration flag is not evidence of retention compliance.

Do not enable a live Android origin for Render. Render remains a synthetic rehearsal destination under this plan.

## 5. Run connected synthetic acceptance

- [ ] Build a separate connected test APK with only the approved synthetic HTTPS origin:

```sh
./gradlew :app:assembleDebug -PechoPilotEnvironment=synthetic
```

- [ ] Keep the synthetic teacher APK disconnected. Use the generated synthetic fixture stream—not adult rehearsal audio, ratings or teacher reviews—to exercise the Render API with disposable credentials.
- [ ] Inspect request keys and logs: no audio, transcript, note, class name, filename, contact detail, consent reference, or unapproved identifier may leave the device.
- [ ] Test airplane mode/reconnect, captive network, TLS failure, redirect, timeout, 429, 4xx/5xx, post-commit disconnect, duplicate retry, expiry, and withdrawal racing ingest.
- [ ] Run the agreed four-school synthetic load with purge/dashboard activity. Record exact input/output counts, p50/p95 acknowledgement latency, drain time, error rate, and resource headroom.
- [ ] Send only consented synthetic PostHog events. Verify the allowlist on the wire, no traffic before opt-in, opt-out queue deletion, and no effect on local work during an analytics outage.
- [ ] Complete [NON_HUAWEI_INFRASTRUCTURE_RUNBOOK.md](NON_HUAWEI_INFRASTRUCTURE_RUNBOOK.md) and archive evidence.

## 6. Approve and deploy the live service

- [ ] Obtain signed institutional/school authorization for the exact cohort, dates, fields, destinations, retention, withdrawal, support, and incident process.
- [ ] Confirm the live service, PostgreSQL, replicas, WAL, backups, logs, monitoring, and operators meet the approved in-country requirements.
- [ ] Re-run all host, PostgreSQL, purge, restore, security, and load checks on the live topology with synthetic data before issuing any real enrollment.
- [ ] Create the live database and service with least-privilege credentials; keep secrets in the host secret store and out of Git, APK logs, screenshots, and evidence bundles.
- [ ] Apply reviewed migrations, run the initial purge, enable the 15-minute purge schedule, verify `/healthz` and `/readyz`, and prove alerts reach the named operator.
- [ ] Create restricted researcher access; verify no individual-record, subgroup, export, or anonymous publication route exists.
- [ ] Build and sign the exact connected release APK with `-PechoPilotEnvironment=live -PechoResearchOrigin=https://APPROVED-LIVE-HOST`. Record its hash and ensure it is distinguishable from disconnected and synthetic builds.
- [ ] Issue participant-specific enrollment near the study start so the 30-day grant window is not shortened unnecessarily.
- [ ] Stage rollout to a small authorized subset, verify offline continuity and remote withdrawal, then obtain the named go/no-go approval before expanding.

## 7. Go-live and rollback

- [ ] Give teachers the reviewed bilingual setup, privacy, deletion, recovery, and phone-support guide.
- [ ] Verify each installed APK hash, phone/K9 matrix entry, permissions, free space, local clock/timezone, and offline fallback before first use.
- [ ] Monitor only approved bounded operational signals. Never copy raw audio, tokens, payloads, or participant identifiers into tickets or chat.
- [ ] Pause rollout on silent mic fallback, inaccessible deletion/withdrawal, prohibited data egress, repeated capture loss, stale purge/readiness, authentication leakage, or failed incident alerting.
- [ ] During a backend outage, keep teacher recording/reflection offline; do not change the APK to an alternate origin.
- [ ] Roll back application code only with migration compatibility verified. Never restore research data from a full database backup unless the approved retention/withdrawal mechanism has been demonstrated for that restore.
- [ ] For a privacy incident, disable intake, preserve minimal approved audit evidence, prioritize withdrawal/deletion controls, notify the named authority, and document scope without exposing teacher data.

## 8. Close-out

- [ ] Reconcile expected, queued, acknowledged, rejected, expired, withdrawn, and purged summaries without exposing identities.
- [ ] Report usefulness as valid scores 4–5 divided by all submitted valid scores; report prompted, submitted, positive, unrated, and distinct-teacher coverage beside it. Exactly 80% does not meet a strict `>80%` target.
- [ ] Complete final withdrawal/expiry/purge checks, revoke access and credentials, and verify provider retention deadlines.
- [ ] Archive the release manifest, approvals, device/load results, incidents, protocol deviations, and close-out evidence in the approved restricted location.
- [ ] Record the validated device/host envelope and unresolved limitations before planning the four-school field release.
