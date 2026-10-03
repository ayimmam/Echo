# Deployment targets

Current architecture, 2026-10-02. **Scaffolding only; no running service or provisioning script.** The user intends Zergaw Standard shared hosting. Runtime/database/cron/resource and storage-location capabilities are unconfirmed.

| Target | Purpose | Allowed data | Readiness |
|---|---|---|---|
| [in-country/shared-hosting](in-country/shared-hosting/README.md) | Preferred lightweight pilot API + rendered dashboard + scheduled reports/purge | Pseudonymous derived records, consent references and reports; still personal data | Conditional on capability evidence and security tests |
| [in-country/vps](in-country/vps/README.md) | Fallback when shared-host controls/runtime cannot meet requirements | Same derived-data scope | Provider/OS and versions unselected |
| [research](research/README.md) | Approved existing acoustic corpora and live observer research | Restricted consent register and aggregate observations; no new recording | Environment unconfirmed; **Research Mode/media service removed** (ADR-016) |
| [huawei-cloud](huawei-cloud/README.md) | Isolated competition demo | Synthetic records only; separate approval for public ML assets | No pilot credentials or connectivity |

The defence build prioritises local reports; opt-in personal-data sync is conditional and real official/browser reports are deferred under [ADR-016](../docs/adr/016-defence-scope-and-no-recording.md). Local-only field use needs an Article 22 decision.

One application, if enabled, is deployed to the chosen pilot host and, separately, the synthetic demo. Do not build two production web frameworks or a SQL compatibility layer for hypothetical databases. [ADR-015](../docs/adr/015-capability-gated-hosting.md) defines the selection rule. Shared hosting does not require Docker/root or permit choosing its OS.

Run the offline evidence checker after completing a private copy of the host template:

```sh
python3 tools/check-hosting/check_hosting.py infra/in-country/shared-hosting/capabilities.example.json
```

The example intentionally returns **BLOCKED**. A passing check only establishes completeness/consistency of declared evidence; it does not probe the provider, certify security, authorise recording or deploy anything. Run HOST/SEC tests before G4-U. See [security architecture](../docs/security/ARCHITECTURE.md).
