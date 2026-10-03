# Domain boundary

Future use cases: enrolment, cohort/context validation, immutable lesson acceptance, local/server report rules, consent withdrawal and deletion. Require an authenticated actor plus explicit capability/scope for each operation; domain objects never trust request teacher/school/cohort IDs as authority.

Current scope: teacher-selected practice cards, follow-ups and later lesson exclusion stay local. Initial supported context is sealed before saving; subsequent local preference changes never mutate synced records. Synthetic server previews may take explicit fixture preferences but are not authoritative over phone state.

Transactions commit lesson + idempotency result together. Owner/record ID + canonical payload hash defines identical retries; conflicting content fails. Revocation wins over stale uploads; use one transaction/locking strategy for consent status checks and acceptance. STEM records cannot enter early-grade coaching or district aggregates. All callers, including jobs, use this boundary.
