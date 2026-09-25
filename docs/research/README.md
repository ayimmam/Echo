# Dimts research pack

Research audit and plan for the Dimts (ድምጽ) proposal described in `DIMTS_ARCHITECTURE_CONTEXT.md` (status "pre-architecture", 2026-09-25). No code exists yet, so "the codebase" here means the design in §7–§13 of that document.

| File | What it is |
|---|---|
| [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) | Each factual claim and assumption in the architecture document, checked against sources on 2026-09-25, with a verdict and the change it implies. |
| [`RESEARCH_PLAN.md`](RESEARCH_PLAN.md) | What is still open: research questions, reading lists, experiments, infrastructure spikes, legal questions and a schedule aligned to W0–W10. |
| [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md) | Every source used or recommended, in APA 7th style, tagged with how far it was checked. |

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

## Access limits during this audit (2026-09-25)

- **Refused by the site's bot protection (HTTP 403):** Wiley/Hindawi, Taylor & Francis, SAGE, ScienceDirect, ResearchGate, policycommons.net and the Huawei Talent portal. For those papers, Crossref, ERIC or Europe PMC metadata and abstracts were used instead.
- **USAID's document archive** (`pdf.usaid.gov`, `dec.usaid.gov`) was unreachable (gateway 502), so the EGRA 2014/2016/2018 reports could not be opened.
- **`web.archive.org` and several `.gov.et` hosts** were unreachable.
- **`moe.gov.et`** served Indonesian gambling spam on its `/resources/annual-abstract/` pages. The site looks compromised, so nothing was downloaded from it. Get the ESAA from the Ministry through official channels.
- **Search results are not evidence.** Web-search summaries were used only to find sources. Anything that rests on a search summary is labelled SECONDARY.
