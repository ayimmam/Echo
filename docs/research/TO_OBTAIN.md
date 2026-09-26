# Sources to obtain — blocked from this session

These sites returned HTTP 403 (bot-blocked), 502 (gateway down), or require a login this session doesn't have. Fetch the PDF/page yourself and either paste the text into chat, attach the file, or drop it in `docs/research/sources/` and tell me — I'll re-verify the relevant claims in [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) and upgrade their verdicts.

## Tier 1 — fixes a SECONDARY or UNVERIFIED verdict that matters for the thesis core

| # | Document | Where to get it | What I need from it |
|---|---|---|---|
| 1 | **READ M&E, *EGRA 2018 Endline Report*** (USAID DEC accession PA00X5JW) | Try `https://pdf.usaid.gov/pdf_docs/PA00X5JW.pdf` directly, or search the USAID Development Experience Clearinghouse (`https://dec.usaid.gov`) for "EGRA 2018 Ethiopia endline". If both are dead, ask the Hawassa University library or AIR directly. | The actual upper-benchmark percentages by grade and language, the benchmark definition, and sample sizes — this is the real source behind the "31.3/34.2/32.4%" figures in §2.1, which I could currently only confirm from a search snippet of an unrelated paper. |
| 2 | **READ M&E, *EGRA 2016 Midline Report*** and ***EGRA 2014 Baseline Report*** | Same DEC search as above; search "READ M&E EGRA 2016 Ethiopia midline" / "2014 baseline". | Same as above, for the other two rounds of the flat-line chart. |
| 3 | **Haile, S. Z., & Mendisu, B. S. (2023).** Early-grade reading: The challenges that affect teachers' practice of phonological awareness: The case of Koorete language. *Education Research International.* | `https://onlinelibrary.wiley.com/doi/10.1155/2023/9527369` (Wiley) or `https://downloads.hindawi.com/journals/edri/2023/9527369.pdf` (Hindawi mirror, same paper) | Confirm whether it actually cites the 31.3/34.2/32.4% EGRA figures, and if so, what it cites as their source. I only saw this via a search-engine summary. |
| 4 | **Ethiopia Education Statistics Annual Abstract (ESAA) 2024/25** (2017 E.C.) | `moe.gov.et` is currently serving what looks like injected gambling spam on its resources pages — **don't use it**. Ask the MoE EMIS directorate directly, or check if your university's library/EMIS contact has a clean copy. A Scribd upload (`MoE Annual Abstract 2024-2025`) may also be a usable mirror if it matches the official document. | Table 2.1 (pre-primary GER/NER by region) and any table giving the O-class / child-to-child enrolment share — I currently only have these numbers via a third-party blog (Atenu.org) that cites the ESAA but isn't the ESAA itself. |

## Tier 2 — upgrades an abstract-only citation to full text (matters for the literature review, not urgent)

| # | Document | Where to get it | What I need |
|---|---|---|---|
| 5 | **Yisihak, E., & Damtew, A. (2024).** Ethiopian early grade English teachers' preparedness to teach basic reading skills. *Education Research International.* | `https://onlinelibrary.wiley.com/doi/10.1155/2024/5596229` | Full methodology and discussion — I only have the abstract (43.4% mean score, 70% below 50%, n = 30). |
| 6 | **Alemu, A. (2025).** Education crisis in Ethiopia: Reimagining early childhood care and education for lasting impact. *SAGE Open, 15*(4). | `https://journals.sagepub.com/doi/10.1177/21582440251379497` | Where the "75% of pre-primary children in O-classes / child-to-child modalities" figure originally comes from, and the full regional findings (currently abstract-only). |
| 7 | **Gobana, J. A. (2025).** Investigating quality education in mother tongue: Ensuring children's rights to access and equity schooling. *Cogent Education, 12*(1). | `https://www.tandfonline.com/doi/full/10.1080/2331186X.2025.2545617` | Full findings on mother-tongue instruction quality — I have only the Crossref record, no abstract text. |
| 8 | **Piper, B., Zuilkowski, S. S., & Ong'ele, S. (2016).** Implementing mother tongue instruction in the real world… *Comparative Education Review, 60*(4), 776–807. | `https://www.journals.uchicago.edu/doi/10.1086/688493` | The classroom-observation results on language actually used in non-MT subjects — I only have this via a search-engine summary, not the paper itself. This is one of very few studies that observed classroom language use directly, which matters for the "no one measures this" novelty claim in §2.4. |
| 9 | **Heugh, K., Benson, C., Bogale, B., & Yohannes, M. G. (2007).** *Study on medium of instruction in primary schools in Ethiopia: Final report.* Ministry of Education. | `https://repository.hsrc.ac.za/handle/20.500.11910/6273`, or try the mirror `https://everythingharar.com/wp-content/uploads/2017/02/4379_Heugh_Studyonmediumofinstruction.pdf` (unverified, found via search only) | Findings on teacher language proficiency and classroom language practice — currently cited only from search-result summaries, not read at all. |

## Tier 3 — nice to have, lower stakes

| # | Document | Where to get it | What I need |
|---|---|---|---|
| 10 | **Huawei ICT Competition 2026–2027 rules and manual** | `https://e.huawei.com/en/talent/portal/` (requires a Huawei Talent account — register with your team email) | Registration deadline, Ethiopia's regional grouping, and whether the judging criteria or AI-mandatory language changed from the 2025–26 cycle. |
| 11 | ACM-published full texts: Schlotterbeck et al. (2021, LAK21), Jensen et al. (2020, CHI), Donnelly et al. (2016, UMAP), Demszky & Liu (2023, L@S) | `https://dl.acm.org` — search each title, or use DOIs in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md) | Model details and result tables for the classroom-audio/feedback comparison table in the research plan (WS-C2). Metadata and abstracts are already in hand; full text would sharpen the comparison. |
| 12 | IEEE-published full texts: Cosbey et al. (2019), Li et al. (2020), Zualkernan & Khan (2020), Shapsough & Zualkernan (2017) | `https://ieeexplore.ieee.org` — search each title, or via your university's IEEE access if any | Same purpose as #11. |
| 13 | ScienceDirect full texts: Wang, Pan, Miller & Cortina (2014); Jacobs et al. (2022); Birnhack & Perry-Hazan (2021) | `https://www.sciencedirect.com` — search each title | Same purpose as #11, plus the surveillance-perception literature for the consent-copy work (WS-B5). |
| 14 | Huawei Cloud ModelArts region availability | `https://support.huaweicloud.com/intl/en-us/productdesc-modelarts/` (returned 404 on the specific page I tried; the section may have moved) | Whether ModelArts (and which Ascend/GPU flavours) are actually offered in AF-Johannesburg or AF-Cairo, and pricing — needed for spike S5 in the research plan. |

---

**How to get this back to me:** paste text directly in chat, attach a file (I can read PDFs), or if it's a lot of files, add them under `docs/research/sources/` in this repo and tell me the paths — I'll read them from there.
