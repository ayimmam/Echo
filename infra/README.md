# Deployment targets

Deployment status, 2026-10-11. **Django/PostgreSQL application and deployment templates implemented; no external host provisioned.** The user intends Zergaw Standard shared hosting. Runtime/database/cron/resource and storage-location capabilities are unconfirmed.

| Target | Purpose | Allowed data | Readiness |
|---|---|---|---|
| [in-country/shared-hosting](in-country/shared-hosting/README.md) | Preferred lightweight pilot API + rendered dashboard + scheduled reports/purge | Pseudonymous derived records, consent references and reports; still personal data | Conditional on capability evidence and security tests |
| [in-country/vps](in-country/vps/README.md) | Fallback when shared-host controls/runtime cannot meet requirements | Same derived-data scope | Provider/OS and versions unselected |
| [research](research/README.md) | Future authorized research on minimized summaries and approved corpora | No classroom audio export; small-cohort summaries require disclosure review | Django summary intake implemented; environment not provisioned; no audio export |
| [huawei-cloud](huawei-cloud/README.md) | Isolated competition demo | Synthetic records only; separate approval for public ML assets | No pilot credentials or connectivity |

The [Django deployment guide](in-country/django/README.md) and [ADR-020](../docs/adr/020-provider-independent-pilot-infrastructure.md) define the selected research service. Zergaw support remains unconfirmed.

The current Android development flow records and reflects locally under [ADR-019](../docs/adr/019-offline-recorded-reflection-pilot.md). The optional encrypted research queue is implemented; default builds have no configured origin and no data is sent. The agreed one-month pilot covers four schools/eight teachers; 20,000 ETB is hardware-only and is not a hosting allocation. Activating implemented minimized research transfer on Zergaw needs verified capabilities, authorization, retention/backup deletion and security evidence. Legal and institutional decisions remain unresolved; the local build does not settle them.

One application, if enabled, is deployed to the chosen pilot host and, separately, the synthetic demo. Do not build two production web frameworks or a SQL compatibility layer for hypothetical databases. [ADR-015](../docs/adr/015-capability-gated-hosting.md) defines the selection rule. Shared hosting does not require Docker/root or permit choosing its OS.

Run the offline evidence checker after completing a private copy of the host template:

```sh
python3 tools/check-hosting/check_hosting.py infra/in-country/shared-hosting/capabilities.example.json
```

The example intentionally returns **BLOCKED**. A passing check only establishes completeness/consistency of declared evidence; it does not probe the provider, certify security, authorise recording or deploy anything. Run applicable HOST/SEC tests before any real-data deployment; older G4-U/G4-E sequencing must be reconciled with ADR-019. See [security architecture](../docs/security/ARCHITECTURE.md).
