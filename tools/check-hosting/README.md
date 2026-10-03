# Offline hosting evidence checker

This standard-library tool checks the shape and declared completeness of the conditional Zergaw shared-hosting profile. It performs no network probes, deployment or provider verification. It is not a runtime security control.

Copy `infra/in-country/shared-hosting/capabilities.example.json` to a private `*.local.json` file. Fill every value, non-secret evidence reference and ISO confirmation date from provider answers or measured test evidence. Keep credentials and personal data out of this record; local files are ignored by git. The example remains intentionally unconfirmed.

From the repository root:

```sh
python3 tools/check-hosting/check_hosting.py infra/in-country/shared-hosting/capabilities.local.json
PYTHONDONTWRITEBYTECODE=1 python3 -m unittest discover -s tests/infrastructure -p 'test_*.py'
```

Exit codes: `0` complete declarations (still requires independent evidence review and HOST/SEC/cohort approvals), `1` blocked/incompatible declarations, `2` unreadable or invalid JSON. Errors name fields and omit supplied values. Duplicate JSON keys and non-finite numbers are rejected. Valid evidence references are not dereferenced or authenticated; confirmation dates are not a freshness guarantee. Recheck evidence before every deployment and material provider change.

Numeric limits must be finite and in (0, 10^12]; the upper bound rejects nonsensical input, not a provider quota. Limits use the units in field names: CPU limit in vCPU equivalents, process/connection counts, RAM in MB, request/cron durations in seconds, backup/RPO/restore durations in hours, backup retention in days. Provider vague “unlimited” claims cannot substitute for measured/contractual limits. Sample limits in unit tests are synthetic and do not establish a feasible classroom size or retention policy. Request/batch/rate and other runtime retention settings belong in future validated application configuration; this manifest is not the entire configuration schema.

The accepted profiles are ASGI/FastAPI/PostgreSQL or WSGI/Django with PostgreSQL/MySQL/MariaDB. Exact supported dependency compatibility remains an evidenced check, not something inferred from the Python version pattern. Research Mode and raw data are forbidden in this shared profile. See [infra runbook](../../infra/README.md) and [future host/security tests](../../docs/testing/HOSTING_SECURITY_TEST_PLAN.md).
