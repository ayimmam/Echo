# Research plan — Dimts (final, v1.0)

Status as of 2026-09-26. This plan builds on [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md), which is now updated with the full texts in [`papers/`](papers/). Related files:

- [`ASSUMPTIONS.md`](ASSUMPTIONS.md) — every assumption the design still rests on.
- [`CODEBASE_STRUCTURE.md`](CODEBASE_STRUCTURE.md) — the repository layout that follows from these findings.

Owners follow §15 of the architecture document:

- **Lead** — Anteneh
- **ML** — Person 2
- **Sys** — Person 3

Weeks count from W0 = 2026-09-25. Sources are cited (Author, year) and listed in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).

---

## 1. What the evidence now says

1. **The problem is real and measured.**
   - Only 32.4% of Grade 2–3 pupils reached the top two reading benchmarks in 2018, essentially unchanged since 2014 (31.3%) and 2016 (34.3%). Only 6.2% read fluently with full comprehension (AIR, 2019).
   - Only 39% of mother-tongue teachers are Proficient-or-above, and Sidaamu Afoo teachers sit in the 22–33% band (AIR, 2020).
2. **Observation-based feedback is associated with better reading, but it is patchy.** Where a school has someone who observes mother-tongue classes, and observes more often, pupils read better (AIR, 2019, pp. 53–54). There is no standardised or frequent measure. This is the gap Dimts fills. "No feedback at all" overstates it.
3. **Classroom language has never been measured systematically.** Earlier work used handwritten observation notes (Heugh et al., 2007) or children's self-reports (Piper et al., 2016). Automated I-6 is a defensible novelty claim.
4. **Audio-only classroom analytics work at coarse grain.** Reported results: ~90% (Owens et al., 2017), >80% (Schlotterbeck et al., 2021), F1 0.64–0.78 (Donnelly et al., 2016). Fine-grained teacher-vs-child separation is still hard: 69% in Wang et al. (2025).
5. **Talk-time feedback changes behaviour.** In an RCT, automated feedback cut talk share by 5 pp (Demszky & Liu, 2023). No such evidence exists for choral ratio or language share yet.
6. **The competition has changed shape.**
   - 2026–27 requires an AI application deployed on **Huawei Cloud** with a **visual demo page**.
   - Preliminary and National stages score **Innovation 60% / Application value 40%**.
   - Regional-stage entries must be reproducible and open source.
   - The Preliminary runs **Nov–Dec 2026**, earlier than the architecture doc's feature freeze.
7. **Some stack assumptions broke:**
   - MindNLP is discontinued and MindAudio is unmaintained.
   - Lite has no INT8 GRU.
   - The AAR is 4 KB-aligned.
   - Jetpack Security is deprecated.
   - Sidama speech data and models do exist.

---

## 2. Principles

1. **Evidence bar.** Only **VERIFIED** claims go into the thesis or pitch. Anything weaker is written as "reported by …".
2. **One log.** Every search goes into `docs/research/search-log.csv` with columns `date, database, query, hits, screened, kept, notes`, so the review is PRISMA-style.
3. **Check before you build.** Don't ratify an ADR until its research question closes.
4. **Two data worlds.** Personal data (the pilot) stays in-country. The competition cloud demo holds synthetic or public data only (ASSUMPTIONS A-C3).
5. **Reproducible by a stranger.** The Regional stage re-runs your code and data, so every number in the pitch must come from a script in `ml/eval/` or `tools/demo/`.

---

## 3. Workstreams

Status is **Done**, **Open** or **Blocked** (waiting on an item in [`TO_OBTAIN.md`](TO_OBTAIN.md)).

### WS-A: Problem evidence (Lead)

| RQ | Question | Status | Result or next step |
|---|---|---|---|
| A1 | EGRA 2014–2018 results | **Done** | 31.3 / 34.3 / 32.4% top-two levels, Grades 2–3, 7 languages. The Sidaamu Afoo 2016 value is flagged as unreliable (AIR, 2019). |
| A2 | Pre-primary figures | **Done** | ESAA 2024/25: GER 59.8% excluding Amhara. The 75% O-class/child-to-child share is from ESAA 2021/22. Class size in Grades 1–6: 54.2 nationally, 59.6 in Sidama. |
| A3 | How often are teachers observed, by whom, and with what feedback? | **Open** | EGRA Table 16 shows association but not base rates. Run 5–10 interviews (teachers, unit leaders, woreda supervisors) during W1–W3 school visits. Output: a 1-page memo. Due W3. |
| A4 | Actual classroom languages and code-switching in the pilot schools | **Open** | Heugh et al. (2007) observed routine Amharic–English switching. Confirm for Sidama and Hawassa schools in W1 visits. This sets the LID label set. Due W2. |
| A5 | Home vs school vs teacher factors | **Done** | MTTCA ranks home, then school, then teacher (AIR, 2020). Framing updated. |
| A6 | Current mother-tongue policy grade span | **Blocked** (ETP 2023 text) | Needed for the §2.4 wording. Due W2. |

### WS-B: Pedagogy and indicator validity (Lead)

The core thesis question: *Do Dimts' acoustic indicators measure something Ethiopian teachers and experts recognise as instructional quality, and in which direction?*

| RQ | Question | Status | Method | Output | Due |
|---|---|---|---|---|---|
| B1 | Construct and normative direction for I-1 to I-8 | Open | Build the indicator × literature matrix (§9 reading list). I-1 now has RCT support (Demszky & Liu, 2023). | `docs/indicator-rationale.md` | W2 |
| B2 | Local expert validation | Open | Panel of 5–8 people (Hawassa College of Education, READ alumni, E-Learning Directorate) rating relevance and direction. | Inputs to ADR-010 | W5 |
| B3 | O-class specifics | Open | CLASS, MELE, Teach ECE; Alemu (2025). | Scope decision D-4 | W3 |
| B4 | Feedback design | Open | Kraft et al. (2018); Demszky et al. (2023, 2025); Jensen et al. (2020); Hattie & Timperley (2007). | Report copy rules | W4 |
| B5 | Surveillance perception | Open | Birnhack & Perry-Hazan (2021): nearly all teachers weigh their own privacy. Co-design with 2 teachers. | Consent copy | W3 |
| B6 | I-3 as a balance indicator | Open | Heward et al. (1989) vs Pontefract & Hardman (2005). | I-3 reporting rule | W2 |

### WS-C: Prior systems and novelty (Lead + ML)

- **C1: Systematic search** (protocol in §8). Novelty statement for I-6 and choral detection. Due W2.
- **C2: Comparison table.** Done except the IEEE papers; see the table below.
- **C3: Competition benchmarking.** Review previous Innovation winners in the Learning Space (the page links "reference entries from previous competitions"). Due W4.

| System | Input → output | Setting | Result | Take-away for Dimts |
|---|---|---|---|---|
| DART (Owens et al., 2017) | Volume/variance → single, multiple or no voice | 1,486 US college sessions | ~90% | Closest no-ASR precedent; coarse classes |
| Schlotterbeck et al. (2021) | Lavalier + smartphone; amplitude, mel and MFCC statistics → COPUS practices | 42 lessons, 19 teachers, Chile | >80% accuracy (2 practices); 7 min per hour of audio | Cheap-phone precedent; **clip-on mic** |
| Chanchal & Zualkernan (ED626900) | Mel spectrograms → Stallings categories | Semi-rural Pakistan, phones | F1 0.83; 0.72–0.81 with 5–20% labels | Semi-supervised learning with few labels |
| Donnelly et al. (2016) | Teacher audio, speech/rest, ASR, acoustic features → 5 segment types | 76 classes, 11 teachers | F1 0.64–0.78, teacher-independent | Validate across teachers, not frames |
| Jensen et al. (2020) | Headset, ASR, ML → discourse features | 142 sessions, 89% usable | Moderate accuracy | Room mics noisy; usability metric |
| M-Powering Teachers (Demszky & Liu, 2023; Demszky et al., 2023, 2025) | Transcripts → talk time and uptake feedback | US; RCTs | −5 pp talk time; +10–13% uptake | Automated feedback changes practice |
| Classroom diarization (Wang et al., 2025) | ECAPA + Whisper | US group work | Teacher-vs-child 69% | Realistic difficulty |
| VTC (Lavechin et al., 2020) | SincNet + RNN → 4 voice types | 260 h, 10 languages | Beats LENA | Frame-classifier baseline |
| LENA feedback (Wang et al., 2013) | LENA → talk ratio | US maths PD | SECONDARY | Teacher-facing precedent |
| Shapsough et al. (2016) | Mobile EGRA + MQTT | LMIC assessment | Low power | Sync design context |
| Human tools: Teach, Stallings, CLASS, and Heugh et al. (2007) field notes | Observers | Global / Ethiopia | Validated or qualitative | What Dimts complements |

### WS-D: ML and data (ML)

| Experiment | Question | Method | Feeds | Due |
|---|---|---|---|---|
| E1 | Usable audio: desk vs clip-on | W0–W1 test lessons. Measure SNR, clipping and RT60 estimate. Compare desk phone with a wired lavalier into the same phone (Schlotterbeck et al., 2021; Jensen et al., 2020). | Capture spec; ASSUMPTIONS A-T4 | W1 |
| E2 | How hard is T-1 on real Ethiopian audio? | 1–2 annotated lessons; VTC, pyannote-seg-3.0 and a DART-style energy baseline; per-class F1 with teacher-independent splits. | §14.1 realism; 4-class fallback | W4 |
| E3 | LID accuracy at 2 / 3 / 5 s | MMS-LID-256 (amh/sid/eng + other) on FLEURS, Afrivoice and pilot teacher segments. | LID threshold (audit 5d) | W3 |
| E4 | Can choral vs single child speech be separated? | Synthetic choral mixtures from Afrivoice speakers + pitch/formant scaling; CountNet-style head; validate on real clips. | 5-class vs 4-class | W5 |
| E5 | Student architecture under Lite ops | CRNN (FP16/FP32 GRU) vs convolution-only TCN (INT8) vs tiny conformer. Measure F1, MB and RTF. | ADR-002 | W4 |
| E6 | Distillation gap | KD from the D-1 teacher to the E5 winner. | §14.1 | W8 |
| E7 | Error propagation | Simulate confusion matrices on golden timelines and compute indicator error (Gautheron et al., 2025). | Final §14 targets | W3 |
| E8 | Dataset register | Licence, gating, register, overlap between WAXAL and Afrivoice. | `ml/data/DATASETS.md` | W1 |
| E9 | Competition "dataset selection and processing" section | Document D-3 to D-6 plus the synthetic-lesson generator as the Template 1 dataset story. | Preliminary submission | W6 |

### WS-E: Stack and device spikes (Sys + ML)

| Spike | Pass criterion | Due |
|---|---|---|
| S1 | `.ms` INT8 hello-world through the 2.10.0 AAR on a 2 GB phone | W0 |
| S2 | RTF ≤ 0.3 and RSS ≤ 300 MB via the bundled `benchmark` tool and the in-app harness | W3 |
| S3 | PyTorch→ONNX→`.ms` and MindSpore→MindIR→`.ms` outputs agree within 1e-4; full-quant PTQ runs; list the ops that fall back to FP32 | W3 |
| S4 | MindSpore 2.10 trains a toy model on the in-country hardware | W1 |
| S5 | ModelArts, OBS and ECS all in **AF-Johannesburg**; flavours and prices recorded; OBS unencrypted and holding **public data only** | W1 |
| S6 | openEuler LTS + openGauss + psycopg connect (SASL/sha256 workaround) | W1 |
| S7 | Microphone foreground service; `AudioRecordingCallback` detects call-silencing; 40-minute battery test | W2 |
| S8 | 16 KB decision: sideload for the pilot; rebuild Lite before any Play release | W6 |
| S9 | SQLCipher + Keystore key | W2 |
| **S10** (new) | **Competition cloud stack:** ECS + RDS for PostgreSQL (or GaussDB) + OBS in AF-Johannesburg serving the demo page and lesson simulator with synthetic lessons. Redeployable from a script. | W5 |

### WS-F: Legal and ethics (Lead)

Primary text: Proclamation 1321/2024. Get the official Gazette copy (TO_OBTAIN #2).

Questions for the legal advisor, answered in writing by W3:

1. Is the university the data controller, and does Art. 33 registration apply to student research?
2. Is transient in-RAM processing of children's voices processing of a minor's data (Arts. 2(16), 11)? What consent does it need?
3. Research Mode child audio: Art. 11 parental consent, or the Art. 54 research exemption?
4. Is raw voice audio without speaker identification *biometric* under Art. 2(6)?
5. Are teacher-linked indicators personal data, and does in-country storage satisfy Art. 22(1)?
6. Are a DPIA (Art. 47) and a DPO (Art. 40) needed?
7. Could the weekly report be read as an automated decision about the teacher (Art. 31)?
8. **New:** does a competition demo on Huawei Cloud (AF-Johannesburg) holding only synthetic lessons, plus scripted demo accounts, fall entirely outside the Proclamation? Get this in writing.

Ethics: the Hawassa IRB (the critical path); assent and consent forms in Amharic, Sidaamu Afoo and English; school and woreda letters.

### WS-G: Competition (Lead)

Northern Africa division, 2026–27 (see audit §7):

| Item | Requirement | Plan |
|---|---|---|
| Topic | AI application powered by Huawei Cloud | Dimts cloud tier: indicator analytics, teacher report generation, district dashboard and a lesson simulator, all on Huawei Cloud with synthetic data |
| Huawei Cloud service | ≥ 1 service (ECS, RDS, DCS …) | ECS + RDS/GaussDB + OBS (+ ModelArts for training on public data) |
| Visual demo page | Mandatory | `dashboard/` + `tools/lesson-simulator` web page |
| Hardware | Optional, team-provided | Live phone demo as a bonus |
| Prelim/National scoring | Innovation 60 / Application value 40 | Lead with the no-ASR, language-agnostic, on-device design plus the I-6 novelty. Back application value with EGRA and MTTCA numbers. |
| Regional reproducibility | Code, datasets and README re-run by the committee | Synthetic dataset generator and scripts in the repo; the pilot data is never needed to reproduce the demo |
| Open source | GitHub or Gitee for Regional and Global shortlists | Keep `docs/research/papers/` out of the public repository |

---

## 4. Decisions that need the team

| ID | Decision | Recommendation |
|---|---|---|
| D-1 | Teacher-model framework (MindNLP is gone) | PyTorch/HF teacher on the in-country node, MMS-LID-256 as the LID teacher, and the student trained in MindSpore. Reword the pitch to "every model on the phone is trained in MindSpore and runs on MindSpore Lite". |
| D-2 | Student architecture | Decide on E5 data; default to a convolution-only TCN if RTF is tight. |
| D-3 | Distribution | Sideload for the pilot; handle 16 KB before any Play release. |
| D-4 | O-class in scope for coaching? | Wait for B3. |
| D-5 | CC-BY-NC models (MMS) | Research and competition only; state it in the model cards. |
| **D-6** (new) | Competition cloud tier vs data sovereignty | Two deployments from one codebase: `infra/huawei-cloud` (demo, synthetic data, AF-Johannesburg) and `infra/openeuler` (pilot, personal data, Hawassa). There is no data path from pilot to cloud (ASSUMPTIONS A-C3). |
| **D-7** (new) | Microphone | Decide from E1. If desk SNR is poor, ship a wired lavalier as part of the kit. |

---

## 5. Schedule (architecture §16 merged with the competition calendar)

The exact Preliminary deadline within Nov–Dec 2026 is not yet known. The plan assumes **1 Dec 2026** until the templates or portal say otherwise (ASSUMPTIONS A-C1).

| Week | Dates | Research and build | Competition |
|---|---|---|---|
| W0 | Sep 25 – Oct 4 | E1 test lessons; S1, S4, S6; legal questions sent; ethics submitted; A3/A4 visits begin | Register team; download templates; confirm division with Huawei Ethiopia |
| W1–2 | Oct 5 – 18 | A4, A6; B1, B6; C1; E8; S5, S7, S9; ADR-001/004/005 | Apply for Huawei Cloud vouchers |
| W3–4 | Oct 19 – Nov 1 | A3 memo; B3, B5; E2, E3, E5, E7; S2, S3; legal answers; ADR-002/003 | Draft Template 1 |
| W5–6 | Nov 2 – 15 | B2 panel; E4; **S10 cloud demo live**; S8 decision; E9 | Record demo video |
| W7–8 | Nov 16 – 29 | E6 distillation; indicator validity v1 (§14.2) on pilot data | **Preliminary submission (assumed by 1 Dec)** |
| W9–10 | Nov 30 – Dec 13 | Cross-language evaluation (§14.3); feature freeze | Preliminary review |
| W11–16 | Dec 14 – Jan 31 | Pilot continues; literature review chapter from the search log | **National final (Jan 2027)** |
| W17–24 | Feb – Mar 2027 | Open-source cleanup; reproducibility check by a non-team member | **Regional final (Feb/Mar 2027)** |

---

## 6. Open questions only the team can answer

1. The training node's hardware (S4, D-1).
2. The pilot teachers' phones (ASSUMPTIONS A-T1).
3. Hawassa IRB turnaround.
4. Play Store: ever?
5. Who is the data controller?
6. **New:** will the Amharic-medium pilot schools be in Hawassa or Sidama rather than Amhara region? ESAA 2024/25 excludes Amhara because schooling is disrupted there (ASSUMPTIONS A-P3).
7. **New:** does the team accept making the product code open source (a Regional requirement)?

---

## 7. Search protocol (C1 and literature review)

**Databases:** ERIC, ACL Anthology, ISCA Archive, IEEE Xplore, ACM DL (LAK, L@S, CHI, UMAP), EDM/JEDM, arXiv (cs.SD, eess.AS, cs.CL), Google Scholar, plus Scopus or Web of Science via the library.

**Query blocks, combined with AND:**

1. `classroom OR lesson OR teacher OR "early grade" OR preschool OR kindergarten`
2. `audio OR acoustic OR "speech activity" OR diarization OR "voice type" OR "language identification" OR "code-switching"`
3. `feedback OR observation OR "teacher talk" OR "wait time" OR choral OR "student talk" OR "language of instruction"`
4. Optional LMIC filter: `Africa OR Ethiopia OR Kenya OR "low-income" OR "developing countries" OR India OR Pakistan`

**Inclusion criteria:** empirical, automated or audio-based, classroom setting, 2010–2026, plus backward and forward snowballing from DART, VTC, M-Powering Teachers and Schlotterbeck et al. (2021).

**Record for each paper:** setting, grade, language(s), microphone, input, classes, metric, result, and whether ASR was used.

---

## 8. Reading lists

★ marks the core list to read first. Full references are in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).

**Ethiopian context**
★ AIR (2019, EGRA 2018 endline) · ★ AIR (2020, MTTCA) · ★ MoE (2025, ESAA 2024/25) · ★ Heugh et al. (2007) · Vujcich (2013) · Alemu (2025) · Kim (2020) · Wendie & Berhanu (2025) · Gobana (2025) · Yisihak & Damtew (2024) · Haile & Mendisu (2023) · Piper et al. (2016)

**Pedagogy and indicator validity**
★ Rowe (1986) · ★ Tobin (1987) · ★ Howe et al. (2019) · ★ Liu & Cohen (2021) · ★ Pontefract & Hardman (2005) · ★ Heward et al. (1989) · ★ Kraft et al. (2018) · Hardman et al. (2009) · Alexander (2015) · Kelly et al. (2018) · Hattie & Timperley (2007) · Popova et al. (2021) · Popova & Evans (2016) · Glewwe & Muralidharan (2016) · Piper & Zuilkowski (2015) · Piper et al. (2018) · Cilliers et al. (2018) · Molina et al. (2018) · Stallings et al. (2014) · La Paro & Pianta (2003) · ★ Birnhack & Perry-Hazan (2021) · Shield & Dockrell (2003)

**Prior systems**
★ Owens et al. (2017) · ★ Schlotterbeck et al. (2021) · ★ Demszky & Liu (2023) · ★ Jensen et al. (2020) · ★ Donnelly et al. (2016) · Demszky et al. (2023, 2025) · Jacobs et al. (2022) · Suresh et al. (2022) · Demszky & Hill (2023) · Wang & Demszky (2023) · Chanchal & Zualkernan (ED626900) · Zualkernan & Khan (2020) · Shapsough & Zualkernan (2017) · Shapsough et al. (2016) · Cosbey et al. (2019) · Li et al. (2020) · Wang et al. (2013, 2014) · Blanchard et al. (2016)

**Speech and ML**
★ Wang et al. (2025) · ★ Lavechin et al. (2020) · ★ Gautheron et al. (2025) · ★ Pratap et al. (2023) · Diack et al. (2026) · Conneau et al. (2022) · Valk & Alumäe (2020) · Babu et al. (2021) · Baevski et al. (2020) · Hsu et al. (2021) · Radford et al. (2022) · Plaquet & Bredin (2023) · Bredin (2023) · Stöter et al. (2018) · Hinton et al. (2015) · Chang et al. (2022) · Schmid et al. (2023) · Jacob et al. (2018) · Snyder et al. (2015, 2018) · Desplanques et al. (2020) · Ko et al. (2017) · Park et al. (2019) · Potamianos & Narayanan (2003) · Lee et al. (1999) · Abate et al. (2005) · Gauthier et al. (2016) · Ganek & Eriks-Brophy (2018)

**Infrastructure documentation**
★ MindSpore Lite 2.10.0 (release notes, downloads, quantization, operator list) · ★ Huawei Cloud ModelArts and Cloud Connect overviews · ★ 2026–27 Innovation Competition page · MindSpore 2.10.0 release notes · Android FGS, audio sharing, 16 KB pages, Jetpack Security notes · SQLCipher · RFC 9562 · openGauss psycopg2 · Label Studio

**Legal and ethics**
★ Proclamation 1321/2024 · Sweeney (2002) · Hawassa IRB guidance (to obtain)
