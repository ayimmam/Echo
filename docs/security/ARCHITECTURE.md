# Security architecture — shared-hosting pilot

2026-10-02. **Architecture requirements, not implemented controls.** Normative for the hosting revision in [ADR-015](../adr/015-capability-gated-hosting.md). User confirmed hosting capabilities are unknown and separate research infrastructure is not yet available. Draft [ADR-016](../adr/016-defence-scope-and-no-recording.md) removes Research Mode entirely. Personal-data sync is optional after the local slice; field use of phone-only storage needs the Article 22 decision.

## Trust boundaries and minimisation

```text
Android (encrypted local state, on-device inference)
  └─ HTTPS derived records → provider gateway → pilot app → selected database
                               │                 └─ bounded cron jobs
                               └─ private config; reviewed static assets only

Private in-country research machine [NOT YET AVAILABLE]
  ├─ R0: separate consent/identity/linkage; R1: approved coded study summaries
  ├─ named researcher access in Ethiopia; approved existing acoustic corpora; no recording endpoint
  └─ R2: fixed anonymous scholarly outputs only after independent disclosure approval

Huawei Cloud synthetic demo
  └─ different credentials, hostname, DB and logs; no pilot/research import
```

The shared backend stores pseudonymous records, minimal enrolment and consent references, and private reports. Pseudonymous does not mean anonymous. Keep names/contact register separately where practical; do not add student identifiers. Store no audio, embeddings, fine-grained speech timeline, model-training targets or biometric identifiers on shared hosting. These exclusions are the default API field allowlist and infrastructure boundary, not merely upload-size limits.

## Threat model and controls

| ID | Threat / control | Enforcement owner and acceptance evidence |
|---|---|---|
| SEC-01 | Guessed IDs, forged institution/cohort, role escalation: deny by default; derive scope from authenticated enrolment and check ownership on every record/report/export/job. | `server/security` + scoped DB queries; cross-user/cohort negative tests |
| SEC-02 | Credential theft/reuse: per-device revocable credentials; one-use enrolment codes; explicit recovery. Browser accounts use maintained framework password hashing/session protection. Rate-limit enrolment/login/recovery by account and source without globally locking victims out. | API/config; expiry, replay, revocation and throttling tests; admin MFA via supported mechanism or restricted operator access |
| SEC-03 | Web attacks: exact hosts/origins; browser session cookies Secure/HttpOnly/SameSite; CSRF on cookie-authenticated changes; autoescaping; CSP appropriate to actual assets; no framing; no debug traces. Native token API is separately authenticated. | Framework/proxy; CSRF/XSS/host/proxy spoof tests; CORS is not authentication |
| SEC-04 | Cross-tenant/provider access: verify filesystem/process/account isolation and staff-access policy; secrets/source/backups outside document root; least-privilege filesystem/DB roles. | Provider + deployment; unauthorised-file/access tests. Provider admins remain trusted processors; app encryption with keys on the same host does not protect against host compromise. Reject host if residual risk is unacceptable. |
| SEC-05 | Transport interception: HTTPS with normal hostname/CA validation; remote DB authenticated TLS or verified private local socket. Trust forwarded headers only from the provider's identified proxy. | Provider + API; wrong-cert/hostname and forged-header tests. Pinning is optional only with backup pins/rotation/recovery; never disable validation to recover. |
| SEC-06 | Secret/data disclosure: private config, distinct demo/pilot/app/migration/backup credentials, rotation and no plaintext tokens/passwords in DB or logs. Use established libraries and random secrets. Log only allowlisted operational/security metadata. | Config + audit; canary-secret/log scan, rotation and document-root tests |
| SEC-07 | Duplicate/replayed records and withdrawal races: scoped immutable IDs with canonical content hash; transaction before ACK; revoke enrolment before purge and reject stale devices. | Domain + DB; dropped-ACK, concurrent withdrawal, conflict and partial-batch tests |
| SEC-08 | Silent cron failures or restore resurrection: bounded idempotent DB-leased jobs, checkpointing and missed-run alerts; erase derived reports; replay deletion ledger before restored service returns. | Jobs + backup runbook; crash/retry/lease expiry and restore tests. No long-lived in-memory scheduler or public cron URL. |
| SEC-09 | Excessive data collection/retention: strict JSON schema, body/batch/rate limits; finite approved retention; no raw-audio endpoints in pilot app. Limits must be numeric/configured before launch. | API + jobs; oversized/extra-field rejection and retention tests |
| SEC-10 | Disclosure through aggregates: distinct-teacher threshold, equal teacher weighting, fixed cohorts, complementary suppression and repeated-release review. STEM excluded from early-grade coaching/district aggregation. | Reports; disclosure and mixed-cohort tests |
| SEC-11 | Backup/supply-chain exposure: encrypted daily in-country backups, keys stored separately from archives, least privilege, dependency pins/hashes, small deploy bundle and restore tests. No deploy of entire repo, PDFs, private data or training dependencies. | Deployment + CI; backup/RPO/restore evidence, dependency/secret scan and protected release workflow |
| SEC-12 | Lost/shared phone and model tampering: SQLCipher/Keystore, account lock/logout, OS backup/transfer exclusions, model manifest integrity and compatibility checks. Offline use remains available within approved enrolled scope. | Android future implementation; storage/privacy/device/model suites; local revocation acts immediately, remote revocation applies once contact occurs |

Architecture decisions follow [OWASP authorisation guidance](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html) for per-request checks and least privilege, and [OWASP logging guidance](https://cheatsheetseries.owasp.org/cheatsheets/Logging_Cheat_Sheet.html) for excluding secrets/sensitive content. Framework settings must be verified against the selected release, e.g. [Django deployment checklist](https://docs.djangoproject.com/en/5.2/howto/deployment/checklist/). These references do not certify this application or provider.

## Access matrix (default deny)

| Principal | Permitted | Prohibited by default |
|---|---|---|
| Enrolled teacher/instructor device | Own cohort sessions/records and reports; own consent/withdrawal | Other users/institutions, role changes, raw research upload to pilot |
| Teacher/instructor browser session — **deferred** | No real accounts through defence; synthetic preview only | All real browser report access |
| District aggregate viewer — **deferred** | Synthetic demo only through defence | All real aggregate release routes; individual reports, STEM, arbitrary filters |
| Research annotator — **removed recorded-media role** | No new audio/media service; authorised live observers retain only aggregate forms | Raw recording/replay, child IDs, speech text, shared-backend or cloud access |
| Lead research analyst — Anteneh; named supervisor/ML collaborators by scope | Approved R1 snapshots/workspace in Ethiopia and approved R2 package under ADR-017 | R0 by default, unconsented private reports, participant-level public release, foreign access/cloud export |
| Research custodian / consent staff | R0 linkage/consent, scoped R1 admission/revocation, backups and access administration | Child roster in project analytics, academic use beyond approved purpose, unilateral publication approval |
| Publication recipient / ordinary external reviewer | Independently approved R2 files, public methods and synthetic fixtures | R1/R0 downloads; “reasonable request” is not automatic permission |
| Operator | Deploy, account support and redacted system health through restricted operator channel | Routine access to individual report/audio content; role escalation through public UI |
| Scheduled job | Named internal use case with scoped DB privilege | Public login, arbitrary SQL/admin rights, cross-tier export |
| Demo identity | Synthetic deployment only | Any pilot/research authentication or connectivity |

Support access to personal content, if ever needed, requires a specifically authorised audited workflow; database administration capabilities must be documented as residual privileged access. Do not describe operator exclusion as protection from a host/database administrator.

Research access is a separate governed workspace, not a new production-backend role or bulk export endpoint. The [data plan](../research/RESEARCH_DATA_MANAGEMENT_PLAN.md) requires separate optional consent, a strict aggregate-field allowlist, no speech-derived text, two-person checked transfers, named author access, finite retention and propagation of withdrawal to forms/analysis/backups. Test RD-01–08. R1 is personal despite codes; separate keys/permissions protect R0 linkage. An analyst who also recruits participants may recognise them; never claim anonymity from that analyst. All personal access and processing remain in Ethiopia, including remote support.

## Shared-hosting launch gates

- Confirm exact Python/DB/scheduler resources, tenant isolation, protected file placement, secret management, backup/log destinations, encryption and restore rights. Unknown entries block real-data deployment.
- Run the host checklist checker and the actual HOST/SEC tests. Checklist success only validates recorded evidence shape; no provider claim becomes true because a JSON value is `true`.
- G4-U still requires undergraduate consent/eligibility and institutional approval. G4-E remains separate. Research Mode has no enablement path. Live observation needs its own approved protocol and in-country handling; applicable server controls remain mandatory if sync is enabled.
- Daily backup and target RPO ≤24 h / RTO ≤8 h are provisional project requirements. Retention and deletion-ledger retention need explicit approved values before capture. Keep the service unavailable during restore until deletion replay is complete.
- If a shared host cannot enforce these boundaries, use the VPS fallback. Root, Docker and an openEuler image are not security prerequisites for a well-managed shared application service.

## Incident response and operations

Assign a named operator/contact and response process before launch. On suspected credential compromise: revoke affected credentials, suspend suspect enrolments, preserve minimum redacted evidence in-country, rotate secrets and assess records/backup exposure. Follow the existing university/legal notification process; this document establishes no new legal deadline. Document who patches the provider runtime versus application dependencies and schedule periodic access/restore reviews.
