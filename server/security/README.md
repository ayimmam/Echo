# Security implementation boundary (future)

The normative design is [docs/security/ARCHITECTURE.md](../../docs/security/ARCHITECTURE.md). Centralise resource authorisation, enrolment-token revocation, browser session policy and audit redaction. Use the selected framework's maintained authentication/password/CSRF facilities; no shared global API key for teachers.

Separate teacher, aggregate viewer, research annotator and operator capabilities. Operator account administration does not automatically grant permission to read individual reports. Enrolment and recovery are rate-limited, audited and bound to cohort/institution. No raw credentials, report bodies or audio in logs. Every endpoint and job requires negative authorisation tests.
