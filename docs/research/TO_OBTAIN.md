# Sources to obtain

Updated 2026-09-26. The first batch you supplied is in [`papers/`](papers/) and has been read into the audit. The table at the bottom shows what it resolved; the items below are still open.

## Still needed

| # | Document | Where to get it | What it resolves | Priority |
|---|---|---|---|---|
| 1 | **Education and Training Policy (2023)**, official text | MoE, or the Hawassa University library | Audit 2.4a: the current grade span of mother-tongue instruction for Sidaamu Afoo and Amharic. | High |
| 2 | **Proclamation 1321/2024**, official Negarit Gazeta copy | University legal office or law library | ~~Confirms~~ **Done 2026-09-26** — you supplied a copy (2016 E.C. = 2024 G.C.); it matches the DataGuidance-hosted scan used for the legal analysis, including Article 22. Kept in the repo at [`../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf`](../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf). Still worth checking against the university legal office's copy. | Closed |
| 2b | **Ethio Telecom teleCloud / VPS pricing** (`ethiotelecom.et`, `telecloud.ethiotelecom.et`) and **HostHabesha** | Fetch directly — both returned no response from this session (network policy) | [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md): confirms whether teleCloud offers a rentable VPS/managed-Postgres tier suitable for `infra/openeuler`, and its ETB pricing | Medium |
| 2c | **Zergaw DBaaS pricing** and **INSA G-Cloud quote** | `zergaw.com/database-as-a-service/` "Our Pricing" table (not shown to this session); request a quote at `cloud.gov.et` | Firms up the ADR-005/S6 hosting decision in [`HOSTING_OPTIONS.md`](HOSTING_OPTIONS.md) | Medium |
| 3 | **Competition templates:** Template 1 (Entry Information Form) and Template 2 (Regional PPT) | Huawei Talent → Innovation Competition → Northern Africa (logged-in download) | Shapes the Preliminary submission (ASSUMPTIONS A-C1). | High, due before the Nov–Dec 2026 Preliminary |
| 4 | **Confirmation that Ethiopia competes in "Northern Africa"** | Tamire Dawud, Huawei Technologies Ethiopia (contact in `papers/Steps to Register…`) | Audit 17d. | High |
| 5 | READ M&E **EGRA 2016 midline** and **2014 baseline** reports | USAID DEC or AIR | Not blocking: the 2018 endline already reports all three rounds. Useful for the per-language series. | Low |
| 6 | Base rates behind EGRA 2018 **Table 16** (share of schools with an assigned classroom observer) | Report annex or AIR data request | RQ-A3: how common classroom observation is. | Medium |
| 7 | Tobin (1987) full text | `https://doi.org/10.3102/00346543057001069` | Confirms the 3-second threshold (currently SECONDARY). | Medium |
| 8 | Wang, Miller & Cortina (2013), *Unterrichtswissenschaft 41*(4) | Springer, or ResearchGate from a browser | The LENA teacher-feedback precedent (currently SECONDARY). | Medium |
| 9 | Wendie & Berhanu (2025) full text | `https://doi.org/10.1186/s40723-025-00146-1` (open access) | Urban ECCE case for §2.3. | Low |
| 10 | IEEE full texts: Cosbey et al. (2019), Li et al. (2020), Zualkernan & Khan (2020), Shapsough & Zualkernan (2017) | IEEE Xplore via library | Architecture details for the prior-systems table (WS-C2). | Low |
| 11 | ScienceDirect full texts: Wang, Pan et al. (2014); Jacobs et al. (2022) | ScienceDirect | Prior-systems table. | Low |
| 12 | Per-region ModelArts availability for AF-Johannesburg (Ascend/GPU flavours, pricing) | Huawei Cloud console (log in, choose AF-Johannesburg, open ModelArts) | Spike S5. The supplied overview didn't list regions. | Medium |

## Resolved by the first batch (2026-09-26)

| Supplied file | Resolved |
|---|---|
| EGRA 2018 Endline (AIR, 2019) | 2.1a → VERIFIED (34.2 corrected to 34.3); new evidence for 2.2d |
| Haile & Mendisu (2023) article and book chapter | Origin of the 34.2% typo |
| Yisihak & Damtew (2024) | 2.2a → VERIFIED, narrow (authors' own limitation) |
| MoE Annual Abstract 2024/25 | 2.3a → VERIFIED; Amhara excluded; class sizes (C-5) |
| Atenu blog | Superseded by the ESAA |
| Alemu (2025) | 2.3b traced to ESAA 2021/22; sample-size inconsistency noted |
| Gobana (2025) | Conceptual review; secondary for 2.4b |
| Piper et al. (2016) | Classroom language measured by child self-report, which strengthens 2.4c |
| Heugh et al. (2007) | 2.4b VERIFIED; 2.4c novelty VERIFIED |
| Schlotterbeck et al. (2021); Donnelly et al. (2016); Jensen et al. (2020); Demszky & Liu (2023) | Prior-systems table; microphone-placement evidence; talk-time RCT |
| Birnhack & Perry-Hazan (2021) | Surveillance-perception literature (WS-B5) |
| Shapsough et al. (2016) | Mobile EGRA data-pipeline precedent |
| Huawei Talent competition page; Huawei Ethiopia registration guides | 2026–27 rules, schedule, scoring, Huawei Cloud requirement |
| ModelArts and Cloud Connect service overviews | OBS/ModelArts same-region and no-encryption constraints; "Africa → AF-Johannesburg" |

**Before open-sourcing:** the competition requires shortlisted entries to be public on GitHub or Gitee. `docs/research/papers/` contains publisher-copyrighted PDFs (Elsevier, ACM, University of Chicago Press, Routledge). Remove that folder, or keep research in a separate private repository, before making this repository public.
