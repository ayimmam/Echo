# Echo documentation

Updated 2026-10-11. Start with the [project README](../README.md). The current build is an offline recording/reflection development slice, not a validated classroom release.

| Need | Read |
|---|---|
| Non-Huawei research infrastructure and activation | [Infrastructure runbook](testing/NON_HUAWEI_INFRASTRUCTURE_RUNBOOK.md), [ADR-020](adr/020-provider-independent-pilot-infrastructure.md), [research contract](contracts/RESEARCH_SUMMARY_V1.md) |
| Innovation Competition requirements and delivery plan | [Criteria, architecture, work packages, gates and submissions](competition/INNOVATION_IMPLEMENTATION_PLAN.md) |
| Agreed pilot scope, features and limitations | [Project overview](../README.md) |
| Build, install and source responsibilities | [Android guide](../edge/android/README.md) and [code map](research/CODEBASE_STRUCTURE.md) |
| Current architecture and data retention decision | [ADR-019](adr/019-offline-recorded-reflection-pilot.md) |
| Device acceptance and recorded test results | [Offline runbook](testing/OFFLINE_PILOT_RUNBOOK.md) |
| Evidence, practical strategies and pilot evaluation | [Hawassa assessment](research/HAWASSA_PILOT_REVIEW_2026-10-10.md) and [research catalog](research/README.md) |
| Isolated fixture app and local dashboard | [Synthetic runbook](testing/DEMO_DEPLOYMENT_AND_MANUAL_TESTING.md) |
| Future server, provider and security work | [Server](../server/README.md), [infrastructure](../infra/README.md), [security](security/ARCHITECTURE.md) |
| Metric evidence and future sharing constraints | [Measurement contract](contracts/MEASUREMENT_EVIDENCE.md), [sharing contract](contracts/SHARING_AND_UI.md) |
| Previous design and rationale | [Archived architecture overview](archive/ARCHITECTURE_OVERVIEW_PRE_ADR019.md) and dated [ADRs](adr/) |

## Reading older plans

ADR-019 replaces the earlier RAM-only/no-persisted-audio restriction for the real local flow. The current agreed scope is Grades 1–4, four schools/eight teachers, one month concurrently, with 20,000 ETB for hardware only. Earlier Grade 2/STEM-first sequences and default-on demo sharing controls do not define this flow.

Historical research findings retain their dates and limitations. Provider/evidence gates remain applicable where compatible. Automated test success, a draft strategy card or a named validation partner does not establish field readiness, teaching effectiveness or institutional approval.
