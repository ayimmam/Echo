# In-country VPS fallback

Use only if the shared-hosting readiness checks fail or later measured load exceeds the plan. This is a fallback topology, not a second pilot or a purchased server.

Select a provider-supported maintained Linux LTS (openEuler is optional), one chosen application framework, one database, HTTPS proxy and scheduled CLI jobs. Compose is appropriate here only if the selected host supports it. `compose/` and `backup/` are relocated empty placeholders from `infra/openeuler/`; deployment files await runtime/version selection.

Keep the same domain/security contracts and cohort separation as shared hosting. No openGauss-specific schema or required OS brand. Do not combine Label Studio/ML training with the public application. Require private DB networking, authenticated DB connections, volume encryption, encrypted in-country backups, restore testing and root/admin access controls. Prefer the same framework/database as the synthetic demo; switching DB engine needs migration and behaviour tests.
