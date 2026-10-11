# Simpler UI and automatic derived-data sharing contract

> **Applicability, 2026-10-11:** The tables below describe the earlier synthetic UI and proposed sharing contract, not the current recording launcher. Under [ADR-019](../adr/019-offline-recorded-reflection-pilot.md), the real flow saves encrypted audio locally on Stop, offers duration-based reflection and sends nothing. Default-on demo preferences do not enroll teachers in research. Future minimized summary sharing requires its own implemented authorization, payload, retention and deletion controls. See the [current workflow](../../edge/android/README.md).

2026-10-06. User-directed planning revision under [ADR-018](../adr/018-measurement-evidence-and-sharing.md). On 2026-10-09 the Android demo implemented the three-destination UI, independent persisted preferences and default-ON behavior for a new setup. **No grants, outboxes, deletion endpoint or network transfer are implemented**; the demo states this and sends nothing. Capture, consent and infrastructure prerequisites still apply.

## Product flow

| Destination / moment | Proposed visible experience |
|---|---|
| First use | The demo provides language selection, a short purpose/privacy notice and a demo-use acknowledgement. It says the planned app may sync privately and contribute study summaries only after separate authorisation and service readiness. No per-session sharing prompt or sharing switch appears in the daily flow. Enrolment evidence must exist before any real transmission. |
| Today | One primary action: preview the simulated mic check, then start a demo lesson. Remember mic/class defaults; display a clear synthetic-demo and no-transfer status. Put class/mic edits and technical detail behind a labelled detail action. No numeric quality score. |
| During lesson | Large elapsed time, unmistakable simulated microphone state, Pause/Resume, Finish and Stop/discard. Show interruption immediately and observed/missing totals. This demo does not access a microphone. |
| Finish | One compact context section: activity and single-adult/no-playback confirmation (unknown suppresses teacher-proxy metrics). Save summary or Discard. In this demo saving is local only; no transfer adapter is connected. |
| Reflection | Weekly supported summary with coverage/unavailable explanation, one teacher-selected practice card or none, and lesson history as a secondary view. Up to two released metrics, with detail on demand. Keep local report exclusion; it does not secretly change research inclusion. |
| Settings | Always-labelled third destination. Language; **Sync private summaries** and **Contribute study summaries** preferences; explicit no-transfer status; local withdrawal; and help/about. Shared-data deletion is unavailable because the demo has sent nothing. No research portal or official-account navigation. |

Merge today's four top-level tabs into Today / Reflection / Settings. Sharing controls live only in Settings; a first-use privacy notice is disclosure, not another control. Keep demo labels and unreviewed-translation indicators truthful. Reduce repeated notices and nested cards, not missing-data explanations or privacy access.

The synthetic demo implements the three destinations, one primary action on Today, collapsible lesson history, labelled Settings preferences and local-only withdrawal. Design targets still to validate with teachers include reaching either sharing switch within Settings with at most one additional tap, text scaling, screen-reader support, offline operation and three reviewed locales. Actual task-success targets and translations must be frozen on development feedback. This is not a measured usability result.

## Defaults, authorisation and timing

Two independent persisted preferences: `private_sync_enabled=true` and `research_contribution_enabled=true` on a **new** enrolment. Preferences are not consent records. Each purpose separately records scope/version, status (`pending`, `active`, `revoked`), authorised session range, participant/enrolment reference and revocation generation. Classroom/cohort eligibility and institutional launch approvals are additional prerequisites.

In the synthetic demo, completing a new setup initializes both preferences to ON. The demo does not evaluate enrolment eligibility or authorisation, and neither preference creates a queue or sends data. A production build may apply the ON default only after the approved eligibility workflow establishes a new enrolment.

```text
may_send(purpose, record) =
    preference[purpose] is ON
    AND prior authorisation[purpose] is active and covers the record
    AND cohort/session and approved destination are ready
    AND record is explicitly saved, allowlisted and valid for that purpose
    AND no local or server withdrawal/opt-out invalidates the request
```

For an enrolled, authorised participant with a ready service, upload starts automatically after Save, subject to network/background execution. “Immediately” means no extra share-confirmation action, not guaranteed network delivery or transmission of live audio. Unknown authorisation, unready hosting/research intake or failed validation means **zero data transfer**, with a clear pending/unavailable status. No default, elapsed time, app usage or failure to find Settings constitutes consent.

The existing study chooses consent-based participation. [Official Proclamation 1321/2024](https://justice.gov.et/en/law/personal-data-protection-proclamation/), Article 8, addresses prior active informed consent and withdrawal. Prior purpose-specific consent may be collected through the approved enrolment process; this avoids adding another daily prompt. If that process is absent, automatic transfer from first launch with only later opt-out is not an approved field design. The institution must confirm the workflow before collection; this contract grants no legal/ethics authorisation.

Existing OFF/withdrawn choices survive upgrade, reinstall recovery and account switching; default ON applies only when no previous preference exists and new enrolment is established. An unknown migration state stays blocked pending recovery. Do not enable sharing retroactively for existing local records. At first enrolment or re-enable, the default eligible range starts prospectively; an older-record contribution needs separately scoped authorisation.

## What goes where

| Purpose | Allowed projection | Destination / access |
|---|---|---|
| Private summary sync | Sealed minimal lesson summary, pseudonymous owner/cohort, approved context, coverage, allowed measurements/provenance and sync consent reference | Capability-approved in-country app/DB; owner access under existing policy; no official real dashboard |
| Research contribution | Only the model-side fields in the approved R1 dictionary: study/session code, permitted aggregate totals/availability/provenance, coarse context and authorised study period | Separate approved in-country research intake and custodian-controlled R1 environment. Study codes issued through R0; no private-report account database copy |
| Observer / questionnaire evidence | Independently locked live aggregate forms and separately volunteered fixed-choice responses | Restricted research workflow; not fabricated from model results or extracted from local card/follow-up state |

Research may accept authorised experimental acoustic totals without labelling them released teacher metrics. Synthetic records never become real participant evidence. No raw audio, feature/posterior arrays, fine timeline, child IDs, speech text, practice-card choice, local reflection exclusion or report screenshot enters either automatic projection. Exact dates/app-to-study linkage needed for transfer stay minimal and restricted to the enrolment/intake control boundary; R1 uses the approved relative time/code representation. R0 identity/linkage remains outside the shared host. R2 public scholarly releases remain separately reviewed; neither switch publishes data.

Plan a narrow research intake adapter within the existing Python codebase, deployed to the approved private research environment with independent credentials/access/storage. Reuse transport/retry primitives with purpose-scoped payload builders and authorisation, not a second framework or a generic bulk-export service. Zergaw is not assumed to host R0/R1 research operations. Its derived-summary role and the separate research destination both require actual readiness evidence.

The electronic research intake is new planned work, superseding manual-only automatic-sharing claims. Two-person checked numeric forms remain a study fallback if expressly disclosed and authorised; their use does not count as automatic sharing delivery. No additional audio/research-recording service is permitted.

## Opt-out, withdrawal and deletion

- **Sync OFF:** persist the change before cancelling work; stop new private uploads and clear unsent private outbox entries. Keep the local report. Explain that previously synced copies remain until the user chooses Delete shared summaries. Research has its own clearly adjacent switch; disabling sync does not silently change it.
- **Research OFF:** immediately stop new study uploads, clear unsent research entries and create a durable research-withdrawal request for prior restricted contributions. Continue local reports and independently permitted private sync. Show removal pending while offline; never claim server deletion before receipt.
- **Delete / withdraw:** Settings distinguishes deleting private server copies, removing a study session, research withdrawal and withdrawing/deleting everything. Explain scope before destructive local deletion. Turning a purpose OFF itself requires no confirmation or coercive prompt. In-flight records may have reached a server; withdrawal receipt/reconciliation must cover that race.
- **Re-enable:** respect any revoked authorisation. A preference switch cannot reactivate research consent or restore erased data. New authorised enrolment/version is needed where withdrawal revoked consent; prospective sharing only by default.
- **Local exclusion:** leaving a lesson out of Reflection affects that view only. Label this clearly; research removal is an explicit Settings action. Discard before save never creates either contribution.
- **Anonymous releases:** withdrawn restricted records are removed under the approved policy; already released genuinely anonymous R2 results cannot reliably be linked back for removal. Explain this limit during enrolment and in Settings details.

## Transport/security invariants

Use two logical outboxes and acknowledgements keyed by purpose, record ID and immutable canonical payload hash. Consent/revocation generation is checked again at enqueue, worker send and server transaction. Off/withdrawal is durable and survives process death. A server-known revocation rejects stale requests; an offline device cannot instantly revoke remote copies, so pending states and reconciliation are mandatory. Revocation control messages remain sendable even while ordinary data sharing is OFF.

Server admission independently validates purpose authorisation, owner/cohort, allowed fields, origins and current evidence policy. Separate endpoint audience/credentials prevent an app token from granting researcher access. Disable either purpose independently if its destination is unready; no fallback to foreign storage/email. Restore replays purpose-specific deletion/withdrawal ledgers before reopening. Logs omit payloads/identities/secrets, and the same retention/locality policy covers retries, failed intake and backups.
