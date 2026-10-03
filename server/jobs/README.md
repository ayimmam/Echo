# Scheduled command contract

Use host-supported cron invoking bounded CLI commands in the application's virtual environment. Proposed commands, when the corresponding service is enabled: process purge work, expire sessions and verify backups; generate synthetic report previews. Local teacher reports never depend on cron; live browser report generation is deferred (ADR-016). Names/entrypoints await framework selection; no public HTTP endpoint is a cron substitute.

Jobs acquire a database lease, store durable checkpoints, process a bounded batch and exit within the host's time/memory limit. Report-generation keys include teacher/cohort/week/spec version. Duplicate invocations must not create duplicate reports; expired leases must permit crash recovery. Record last-success and backlog metrics without personal payloads; missed runs must alert the operator. Purge overrides stale report work and replays after restore. Invoke the same authorised use cases as the API.
