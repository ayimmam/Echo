# Deployment targets

Deployment status, 2026-10-11. **Scaffolding only; no running service or provisioning script.** The user intends Zergaw Standard shared hosting. Runtime/database/cron/resource and storage-location capabilities are unconfirmed.

| Target | Purpose | Allowed data | Readiness |
|---|---|---|---|
| [in-country/shared-hosting](in-country/shared-hosting/README.md) | Preferred lightweight pilot API + rendered dashboard + scheduled reports/purge | Pseudonymous derived records, consent references and reports; still personal data | Conditional on capability evidence and security tests |
| [in-country/vps](in-country/vps/README.md) | Fallback when shared-host controls/runtime cannot meet requirements | Same derived-data scope | Provider/OS and versions unselected |
| [research](research/README.md) | Future authorized research on minimized summaries and approved corpora | No classroom audio export; small-cohort summaries require disclosure review | Environment/intake unimplemented; device-only recording is separate under ADR-019 |
| [huawei-cloud](huawei-cloud/README.md) | Isolated competition demo | Synthetic records only; separate approval for public ML assets | No pilot credentials or connectivity |

The current Android development flow records and reflects locally under [ADR-019](../docs/adr/019-offline-recorded-reflection-pilot.md). It has no Internet permission, outbox or server integration. The agreed one-month pilot covers four schools/eight teachers; 20,000 ETB is hardware-only and is not a hosting allocation. Future minimized research transfer to Zergaw needs verified capabilities, authorization, retention/backup deletion and security evidence. Legal and institutional decisions remain unresolved; the local build does not settle them.

One application, if enabled, is deployed to the chosen pilot host and, separately, the synthetic demo. Do not build two production web frameworks or a SQL compatibility layer for hypothetical databases. [ADR-015](../docs/adr/015-capability-gated-hosting.md) defines the selection rule. Shared hosting does not require Docker/root or permit choosing its OS.

Run the offline evidence checker after completing a private copy of the host template:

```sh
python3 tools/check-hosting/check_hosting.py infra/in-country/shared-hosting/capabilities.example.json
```

The example intentionally returns **BLOCKED**. A passing check only establishes completeness/consistency of declared evidence; it does not probe the provider, certify security, authorise recording or deploy anything. Run applicable HOST/SEC tests before any real-data deployment; older G4-U/G4-E sequencing must be reconciled with ADR-019. See [security architecture](../docs/security/ARCHITECTURE.md).
