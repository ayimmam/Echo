# Semgrep security audit — 2026-10-24

Semgrep OSS 1.182.1 found **10 unique findings: four fixed and six reviewed as intentional**. No actionable findings remain in the scanned rule sets. The six reviewed findings remain visible; no rules or source findings were suppressed.

## Scope and method

Scanned 222 Git-tracked text files using `p/default`, `p/security-audit`, and `p/mobsfscan`. Targets included native Python, Kotlin, HTML, YAML, JSON, XML and shell parsing where applicable, plus generic and regular-expression scanning of text files (including documentation and configuration). The mobile rules applied to 57 files. All three final scans completed without reported errors or timeouts. No secret-detection findings were returned.

The CLI dependency download failed, so the verified wheel's **semgrep-core** engine was run directly with local registry snapshots and an explicit target manifest. The engine wheel's SHA-256 matched PyPI metadata. Source stayed local. The [machine-readable evidence](semgrep-audit-2026-10-24.json) records rule snapshot hashes, counts and finding dispositions. Registry packs overlap, so their counts must not be added together.

This is a static source audit of the current checkout, not dependency vulnerability analysis, Git-history secret scanning, runtime penetration testing, or a review of deployed infrastructure. Binary assets and generated build output were outside scope.

## Fixes

The workflow used mutable tags for four action invocations. Pinned them to the commit objects returned by the upstream GitHub tag APIs on the audit date:

| Action | Existing release | Verified commit |
| --- | --- | --- |
| `actions/checkout` (two invocations) | `v7` | `3d3c42e5aac5ba805825da76410c181273ba90b1` |
| `actions/setup-python` | `v7` | `6d4fd22b20465ccc0f7354476588e8df7c9ca0e0` |
| `actions/setup-java` | `v6` | `b843ebf9b913146b38da2eaa7aeddf76074ae1ad` |

The rule is `yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag`. Release comments retain the previous major versions. Future action upgrades should update the verified commit pins.

## Reviewed findings

| Finding | Count | Review and evidence |
| --- | --- | --- |
| `python.django.security.audit.csrf-exempt.no-csrf-exempt` | 1 | `json_api` only accepts JSON POST requests. Upload/withdraw authorization requires an explicit bearer token; enrollment requires a private single-use code. Browser sessions never authorize these operations. Browser login and logout retain Django CSRF checks. Added a regression test with CSRF enforcement and an authenticated, permission-bearing browser session: uploads and withdrawals fail without bearer credentials, and an upload succeeds with a valid bearer token. Documented this boundary on the decorator. Re-review if cookie authentication or accepted content types change. |
| `kotlin.lang.security.gcm-detection.gcm-detection` | 3 | These are prompts to inspect nonce handling. `AuthenticatedEnvelope.seal` creates a fresh cipher and asks the provider to generate the IV for each encryption, checks the 12-byte IV, and authenticates the file identity as AAD. Android Keystore keys require randomized encryption. Decryption consumes the stored IV and verifies the GCM tag. Existing tests cover fresh nonces, tampering, file substitution and malformed envelopes. |
| `mobsf.mobsfscan.kotlin.crypto.android_kotlin_insecure_random` | 1 | `MockMeasure` uses a seeded generator for reproducible synthetic measurements. It produces no credentials, keys, nonces or security decisions. Production credentials use Python `secrets`; Android encryption uses the cipher provider's generated IV. |
| `java.android.security.exported_activity.exported_activity` | 1 | `MainActivity` is the launcher and must be exported. It accepts only a boolean reflection-navigation hint; it does not accept paths, content URIs, commands or credentials. Starting it does not submit research data or start recording. Internal activities and services are not exported. |

## Validation

- Final scans: default 5 findings, security-audit 1, mobile 1; six unique reviewed findings, zero mutable-tag findings, zero reported scan errors.
- Django research tests: 32 passed, one PostgreSQL-specific concurrency test skipped on SQLite. This includes the new browser-session/API authorization regression test.
- Demo backend: 18 tests passed; hosting infrastructure: 10 passed; synthetic demo: 7 passed.
- Android `:app:testDebugUnitTest --offline`: successful; Gradle reused up-to-date results, including the existing encryption tests. Android sources were unchanged.
- `git diff --check`: passed. The GitHub-hosted workflow itself was not executed locally.

To repeat with an installed Semgrep CLI (registry rules may have changed):

```sh
semgrep scan --config p/default --config p/security-audit --config p/mobsfscan \
  --metrics off --disable-version-check --json --output /tmp/echo-semgrep.json .
```

Review the six intentional findings against the evidence above; new findings require fresh triage.
