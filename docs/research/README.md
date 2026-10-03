# Dimts research pack

> **Current infrastructure (2026-10-02):** [ADR-015](../adr/015-capability-gated-hosting.md), [structure](CODEBASE_STRUCTURE.md), [security](../security/ARCHITECTURE.md), [HOST/SEC suites](../testing/HOSTING_SECURITY_TEST_PLAN.md) and [deployment scaffolding](../../infra/README.md). Hosting remains conditional; research infrastructure is not yet available. Only the evidence checker and its tests are executable infrastructure additions.

Research audit and plan for the Dimts (ድምጽ) proposal described in `DIMTS_ARCHITECTURE_CONTEXT.md` (reviewed proposal, 2026-09-27). Research evidence updated 2026-09-26; implementation-plan review updated 2026-09-27. A basic Android Compose starter exists; product components remain unimplemented; structural hosting contracts and checker tests also exist. No runtime or product behavior has been validated.

| File | What it is |
|---|---|
| [`FEATURE_REVIEW.md`](FEATURE_REVIEW.md) | Whole-product feature scores, external evidence, five additions, cuts, two critique/search/revision passes and June 2027 scope. Draft ADR-016 governs removed recording paths and deferred components. |
| [`STAKEHOLDER_MAP.md`](STAKEHOLDER_MAP.md) | Full proposed participation map: intensity, phases, contributions, consent/decision rights, data access and engagement owners. |
| [`RESEARCH_DATA_MANAGEMENT_PLAN.md`](RESEARCH_DATA_MANAGEMENT_PLAN.md) | Thesis/manuscript output, named author access, R0/R1/R2 separation, minimal study fields, anonymous release review and retention; draft ADR-017. |
| [`STEM_TRIAL_PLAN.md`](STEM_TRIAL_PLAN.md) | Undergraduate STEM technical trial first, then separate early-grade validation. |
| [`INFRASTRUCTURE_RECHECK.md`](INFRASTRUCTURE_RECHECK.md) | Current official infrastructure findings, proposed choices and linked acceptance cases. |
| [`../testing/POST_IMPLEMENTATION_TEST_PLAN.md`](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) | Test procedures, acceptance criteria and evidence template; links to 18 executable contract cases needing implementation adapters. |
| [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md) | Current repository baseline, architecture findings, contract checklist and implementation gates G0–G5. Read before implementing. |
| [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) | Each factual claim and assumption in the architecture document, checked against sources (2026-09-25; updated 2026-09-26 with supplied full texts), with a verdict and the change it implies. |
| [`RESEARCH_PLAN.md`](RESEARCH_PLAN.md) | What is still open: research questions, reading lists, experiments, infrastructure spikes, legal questions, team decisions and a schedule merged with the 2026–27 competition calendar. |
| [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md) | Every source used or recommended, in APA 7th style, tagged with how far it was checked. |
| [`ASSUMPTIONS.md`](ASSUMPTIONS.md) | Historical register of 50 assumptions plus current FR-1–FR-8 scope assumptions the design rests on: origin, status, evidence, test and impact if wrong. |
| [`CODEBASE_STRUCTURE.md`](CODEBASE_STRUCTURE.md) | Proposed monorepo layout revised from §12 in light of the findings, including shared-hosting, VPS fallback, separate research and synthetic demo boundaries. |
| [`TO_OBTAIN.md`](TO_OBTAIN.md) | Sources still needed, and what the supplied batch resolved. |
| [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md) | Ethiopian hosting/cloud providers checked against Article 22 (data sovereignty), with the current capability-gated shared-hosting correction and VPS alternatives. |
| [`papers/`](papers/) | Full texts supplied on 2026-09-26. Copyrighted, so keep them private (see TO_OBTAIN). |

## Verdict labels

The bar for **verified** is a primary source, a peer-reviewed paper, or official documentation or software that was actually opened. When the primary could not be opened, an abstract or a reputable secondary source is used and labelled as such. Neither counts as verified.

| Label | Meaning |
|---|---|
| **VERIFIED** | Confirmed in a primary, peer-reviewed or official source that was opened (full text, official page, released package or binary). |
| **VERIFIED (abstract)** | Confirmed only from the primary source's own abstract or registry metadata (Crossref, ERIC, Europe PMC). |
| **SECONDARY** | Supported only by a secondary source (blog, press, search-engine summary). The primary was not opened. |
| **PARTLY** | The fact is right but the claim goes beyond it (scope, date, sample, generalisation). |
| **CONTRADICTED** | A primary source says otherwise. |
| **UNVERIFIED** | Could not be checked. The plan says how to check it. |

## Access limits during the first pass (2026-09-25)

Most of the gaps below were closed by the papers supplied on 2026-09-26; see [`TO_OBTAIN.md`](TO_OBTAIN.md).

- **Refused by the site's bot protection (HTTP 403):** Wiley/Hindawi, Taylor & Francis, SAGE, ScienceDirect, ResearchGate, policycommons.net and the Huawei Talent portal. For those papers, Crossref, ERIC or Europe PMC metadata and abstracts were used instead.
- **USAID's document archive** (`pdf.usaid.gov`, `dec.usaid.gov`) was unreachable (gateway 502), so the EGRA 2014/2016/2018 reports could not be opened.
- **`web.archive.org` and several `.gov.et` hosts** were unreachable.
- **`moe.gov.et`** served Indonesian gambling spam on its `/resources/annual-abstract/` pages. The site looks compromised, so nothing was downloaded from it. Get the ESAA from the Ministry through official channels.
- **Search results are not evidence.** Web-search summaries were used only to find sources. Anything that rests on a search summary is labelled SECONDARY.
