# Undergraduate STEM pilot load profile

Updated 2026-10-02 from the user's workload clarification. This is a post-implementation test input specification, not a measured capacity claim.

| Input | Current evidence / status |
|---|---|
| Sync pattern | User requests simultaneous sync; test a reconnect burst, not evenly spaced requests |
| Class sessions | User specified three sessions/day. Conservative test assumption: three per device per day; if three total was intended, this over-tests the reported daily volume |
| Session duration | User wrote “1:30min each”; 90 minutes is the working interpretation, awaiting confirmation |
| Concurrent devices (`N`) | Three devices/day supplied; test all three reconnecting simultaneously |
| Pilot duration | One week (seven days) supplied |
| Offline backlog days (`D`) | Test an entire seven-day pilot backlog as a conservative outage case, plus normal daily sync; this is a test envelope, not a claim that a week offline is expected |
| Maximum record/batch sizes, retry concurrency and request rate | To be frozen in the API/config contract before implementation acceptance |
| Acceptable sync completion and report/purge delay | Lead/Sys to set numerical budgets before measuring the host |

The user clarified “3 devices per day and 3 sessions/day for a week.” Device count is now known; per-device session count and 90-minute duration remain explicit test assumptions. Do not describe the Standard plan as sufficient until payload limits/latency budgets are established and HOST-03 passes.

## Prepared workload and remaining assumptions

For one final derived record per session, three sessions per device per day yields `3 × N = 9` new records daily. After `D = 7` offline days, the first reconnect contains `3 × N × D = 63` unique records plus retries: 21 records per device. If three sessions/day meant a total across all devices, actual weekly volume is 21; retain the 63-record case as the conservative test envelope. Update this formula if the final API intentionally divides sessions into multiple records. Generate unique instructor/device identities and scoped records for each classroom; duplicate retry IDs retain identical canonical content, while conflict cases deliberately change content.

1. Synchronise all `N` enrolled synthetic devices at a start barrier, each with its own `3 × D = 21` backlog. Exercise the actual bounded batching and retry policy rather than sending an unlimited request stream.
2. Measure unique durable records, ACK latency, backlog drain time, failed/retried requests and duplicate/conflict responses. Account for every accepted/rejected record, including a disconnect immediately after commit.
3. Repeat within the agreed provider load-test envelope with report/purge jobs active and delayed network reconnects. Do not treat HTTP success alone as proof of durable ingest; verify the database and generated reports.
4. Check peak process/RAM/CPU/DB connections and scheduled-job runtime against measured plan quotas. Preserve resource headroom agreed before the test. If capacity fails, reduce the documented supported load or select another host; do not silently lower the test load.

Session duration affects phone capture, thermal/battery stability and offline storage tests independently of the shared backend. Prepare a 90-minute device endurance run and a three-session (4.5-hour capture) daily-use run, with the existing inference/memory/privacy targets; confirm duration before using these as the field acceptance target. Do not claim these pass from a 40-minute benchmark. **Removed:** research audio storage/transfer budget and Research Mode (ADR-016). All capture is RAM-only, including extended tests; only derived records may sync. This load profile applies when sync is enabled and does not force a live backend into the offline defence slice.
