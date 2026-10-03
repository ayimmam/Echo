# Configuration contract (future startup gate)

Configuration is environment-specific, never a checked-in secret. Validate before accepting requests:

- Explicit deployment purpose: pilot, synthetic demo or local synthetic tests. Demo cannot enable real enrolment or research ingestion.
- One framework/runtime and DB driver with pinned supported versions; exact allowed hostnames and browser origins; debug off remotely.
- TLS/trusted-proxy configuration tied to the actual host; verify remote DB identity or use a confirmed private local socket.
- Private secret source outside document root, separate DB/app/migration/backup credentials, revocation/session settings and request limits.
- Approved consent/cohort scope, finite retention and deletion/backup policy; Research Mode **removed**, no configuration can enable captured-audio persistence/upload. Real official/browser roles and deferred metrics remain disabled (ADR-016).

Unknown/missing production settings must refuse startup. The host evidence checker is an offline planning tool; it does not implement this application startup check.
