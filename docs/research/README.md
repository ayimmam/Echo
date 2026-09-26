# Dimts research pack

Research audit and plan for the Dimts (ድምጽ) proposal described in `DIMTS_ARCHITECTURE_CONTEXT.md` (status "pre-architecture", 2026-09-25). Finalised 2026-09-26 after reading the supplied full texts. No code exists yet, so "the codebase" here means the design in §7–§13 of that document.

| File | What it is |
|---|---|
| [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) | Each factual claim and assumption in the architecture document, checked against sources (2026-09-25; updated 2026-09-26 with supplied full texts), with a verdict and the change it implies. |
| [`RESEARCH_PLAN.md`](RESEARCH_PLAN.md) | What is still open: research questions, reading lists, experiments, infrastructure spikes, legal questions, team decisions and a schedule merged with the 2026–27 competition calendar. |
| [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md) | Every source used or recommended, in APA 7th style, tagged with how far it was checked. |
| [`ASSUMPTIONS.md`](ASSUMPTIONS.md) | Register of the 50 assumptions the design rests on: origin, status, evidence, test and impact if wrong. |
| [`CODEBASE_STRUCTURE.md`](CODEBASE_STRUCTURE.md) | Proposed monorepo layout revised from §12 in light of the findings, including the two-tier (competition cloud / in-country pilot) deployment. |
| [`TO_OBTAIN.md`](TO_OBTAIN.md) | Sources still needed, and what the supplied batch resolved. |
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
