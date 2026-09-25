# Research plan — Dimts

Builds on [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) (2026-09-25). Owners follow §15 of the architecture document:

- **Lead** — Anteneh
- **ML** — Person 2
- **Sys** — Person 3

Weeks follow §16 (W0 starts 2026-09-25). Sources are cited (Author, year) and listed in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).

---

## 1. Principles

1. **Evidence bar.** A claim goes into the thesis or pitch only once it is **VERIFIED** as defined in [`README.md`](README.md). Anything weaker is written as "reported by …".
2. **One log.** Every search, source and verdict goes into `docs/research/search-log.csv`, with columns `date, database, query, hits, screened, kept, notes`. This makes the literature review reproducible and PRISMA-style.
3. **Check before you build.** Each workstream below names the ADR or design decision it feeds. Don't ratify that ADR until its research question is answered.
4. **Primary over convenient.** Cite the EGRA report, not a paper that repeats its numbers. Cite the Gazette, not a law-firm summary. Cite the release notes, not a blog.

---

## 2. Workstreams and research questions

### WS-A: Problem evidence and Ethiopian context (Lead)

| RQ | Question | Why it matters | Method and first sources | Output | Due |
|---|---|---|---|---|---|
| A1 | What exactly did EGRA 2014, 2016 and 2018 find (grades, languages, benchmark definitions, sample)? | §2.1 headline chart (audit 2.1a) | Get the READ M&E *EGRA 2018 Endline Report* (USAID DEC PA00X5JW) and the 2016 midline from AIR, the MoE, NEAEA or the Hawassa library. USAID DEC was unreachable from here. Cross-check with AIR (2020), which uses EGRA 2018. | One verified flat-line table plus citation | W1 |
| A2 | What are the verified pre-primary figures (GER, NER, KG schools, regional GER, O-class share)? | §2.3 (audit 2.3a–b) | Get ESAA 2024/25 (2017 E.C.) Table 2.1 from the MoE EMIS directorate. **Don't download from `moe.gov.et`** until the site is clean. Trace the "75% O-class + child-to-child" figure to its original source. | Verified table with comparability notes | W2 |
| A3 | How often do early-grade teachers currently get observation and feedback, and from whom? | §2.2d, §2.5c, and the core "no feedback loop" premise | Literature: CPD and cluster supervision studies in Ethiopia; World Bank Teach deployments (Molina et al., 2018); Popova et al. (2021) on professional development practice. Primary data: 5–10 short interviews with teachers and woreda supervisors during W0–W2 school visits. | 1-page evidence memo | W3 |
| A4 | Which languages are actually used in Sidama and Amhara early-grade classrooms, and how much code-switching is there? | I-6 design and the LID class set | Heugh et al. (2007); Vujcich (2013); Gobana (2025). Ask teachers directly in the W0 visits. | Prior for the LID label set | W2 |
| A5 | What do home and school factors explain relative to teachers? | Framing of §2.2 (audit 2.2b) | Read AIR (2020) chapter 6 in full (multivariate analyses). | Reworded §2.2 | W1 |

### WS-B: Pedagogy and indicator validity (Lead)

The core thesis question: *Do Dimts' acoustic indicators measure something teachers and experts in Ethiopia recognise as instructional quality, and in which direction?*

| RQ | Question | Method and first sources | Output | Due |
|---|---|---|---|---|
| B1 | Construct definition. For each of I-1 to I-8, what construct does it proxy, what is its literature basis, and what is the normative direction in *early-grade, large-class, structured-pedagogy* settings? | Build an indicator × literature matrix from the core reading list (§7), for example I-2 from Rowe (1986) and Tobin (1987), and I-3 from Heward et al. (1989) vs Pontefract & Hardman (2005). | `docs/indicator-rationale.md`, cited in the thesis methods chapter | W2 |
| B2 | Local expert validation. Do Ethiopian early-grade experts agree with each indicator's meaning and direction? | A short Delphi or panel (5–8 people) from the Hawassa College of Education, READ alumni and the E-Learning Directorate. Rate relevance and direction; report agreement. | Validated rule-table inputs (ADR-010) | W5 |
| B3 | O-class specifics. Do talk ratio and choral ratio mean the same thing in play-based pre-primary as in Grades 1–3? | ECE observation literature: CLASS (La Paro & Pianta, 2003); UNICEF/World Bank MELE and Teach ECE (to be located); Alemu (2025); Kim (2020). | Decide whether O-class gets separate rules or is out of scope for coaching | W3 |
| B4 | Feedback design. What makes automated feedback change teacher practice? | Kraft et al. (2018); Hattie & Timperley (2007); Demszky et al. (2023, 2025); Jacobs et al. (2022); Jensen et al. (2020); Wang, Miller & Cortina (2013). | Report copy guidelines: one focus, non-punitive, actionable | W4 |
| B5 | Surveillance perception. How do teachers perceive classroom recording, and what reduces harm? | Birnhack & Perry-Hazan (2021); MTTCA's explicit "not an accountability measure" stance (AIR, 2020, p. 6); co-design sessions with 2 teachers (§18). | Consent copy and product principles | W3 |
| B6 | Choral-response construct. Should I-3 be treated as a *balance* indicator rather than something to minimise? | Heward et al. (1989); Hardman et al. (2009); Alexander (2015); READ structured-pedagogy materials ("I do, we do, you do"). | I-3 reporting rule | W2 |

### WS-C: Prior systems and novelty (Lead + ML)

"Similar data" was scoped to prior systems and projects.

**C1: Systematic search.** Has anyone automatically measured classroom language use, choral vs individual response, or teacher talk ratio from audio in a low- or middle-income country? Run the search protocol in §6 over ERIC, the ACL Anthology, the ISCA Archive, IEEE Xplore (via the library), arXiv, Google Scholar and the LAK/EDM proceedings. The output is the novelty statement for §2.4 and §17. Due W2.

**C2: Comparison table.** Fill in the table below: what each system measured, its input, accuracy, setting, and what Dimts takes from it. Due W3.

| System | Input → output | Setting | Reported result | Relevance to Dimts |
|---|---|---|---|---|
| DART (Owens et al., 2017) | Volume and variance → single / multiple / no voice | 1,486 US college STEM sessions | ~90% accuracy | Closest no-ASR precedent. Coarse classes. |
| LENA teacher feedback (Wang, Miller & Cortina, 2013) | LENA recorder → teacher vs student talk, 12-h feedback | US math teachers' professional development | Increased student talk (SECONDARY) | Closest teacher-facing talk-ratio precedent |
| Audio Stallings coding (Chanchal & Zualkernan, ERIC ED626900; Zualkernan & Khan, 2020; Shapsough & Zualkernan, 2017) | Mel spectrograms → management / lecture / drill / Q&A | Semi-rural Pakistan, phone recordings | F1 0.83 supervised; 0.72–0.81 with 5–20% labels | LMIC precedent; semi-supervised learning with few labels |
| Schlotterbeck et al. (2021) | Spectral features → teaching practices | Chile, low-performing schools, teacher's phone mic | See paper | Cheap-phone precedent |
| Cosbey et al. (2019); Li et al. (2020) | Deep audio (and multimodal) → activity | US; China | See papers | Architecture baselines |
| Classroom diarization (Wang et al., 2025) | ECAPA-TDNN + Whisper → who spoke, teacher vs child | Noisy US group work | Teacher-vs-child 69.17%; DER ~34% | Realistic difficulty benchmark |
| Voice Type Classifier (Lavechin et al., 2020) | SincNet + RNN → child / other child / male / female | 260 h, 10 languages, home recordings | Beats LENA | Fixed-class frame classifier; possible baseline |
| M-Powering Teachers, TeachFX (Demszky et al., 2023, 2025) | ASR + NLP → uptake and questioning feedback | US online and in-person | +13% uptake (RCT) | Evidence that automated feedback changes practice. ASR-based, so not portable. |
| TalkMoves (Suresh et al., 2022; Jacobs et al., 2022) | Transcripts → discourse moves → feedback | US math | See papers | Feedback design |
| Question authenticity / CLIPS (Kelly et al., 2018; Blanchard et al., 2016; Donnelly et al., 2016; Jensen et al., 2020) | ASR + ML → question types | US classrooms | r = .60–.69 with humans | Shows the ceiling with ASR |
| Text-as-data measures (Liu & Cohen, 2021) | Transcripts → teacher-centred vs interactive factors | US Grades 4–5 | Factors predict value-added | Validity argument for talk ratio |
| Human tools: Teach (Molina et al., 2018), Stallings (Stallings et al., 2014), CLASS | Human observation | Global / LMIC | Validated instruments | The comparator Dimts replaces or complements |

**C3: Competition benchmarking.** Review past Huawei ICT Competition Innovation winners, including Ahmadu Bello University's 2025–26 Grand Prize, to calibrate how much complexity and how much demo is expected. Due W6.

### WS-D: ML and data (ML)

| RQ / experiment | Question | Method | Decision fed | Due |
|---|---|---|---|---|
| D1 / E1 | Is desk-placed phone audio usable? | W0 test lesson. Measure SNR, clipping and reverb (RT60 estimate). Compare desk, wall and clip-on positions. Background: Shield & Dockrell (2003). | Capture spec, risk "audio unusable" | W0 |
| D2 / E2 | How hard is T-1 on real Ethiopian classroom audio? | Once ethics is approved, annotate 1–2 lessons. Run off-the-shelf baselines server-side, in-country: Voice Type Classifier, `pyannote/segmentation-3.0` + VAD, and a DART-style energy baseline. Report per-class F1. | Realism of the §14.1 targets; 4-class fallback | W4 |
| D3 / E3 | How accurate is LID at 2 / 3 / 5 s on teacher speech, zero-shot? | Run `facebook/mms-lid-256` (restricted to amh/sid/eng + other) on FLEURS `am_et`, Afrivoice `sid` and `am`, and pilot classroom teacher segments. Plot accuracy vs segment length. | The single LID threshold (audit 5d); whether MMS-LID can serve as the LID teacher | W3 |
| D4 / E4 | Can CHILD_SINGLE and CHILD_CHORAL be separated? | Build synthetic choral mixtures (D-6) from Afrivoice adult speakers with time jitter and pitch shift, plus child-like speech via pitch/formant scaling (see Lee et al., 1999, for child acoustics). Train a CountNet-style speaker-count head (Stöter et al., 2018). Validate on real annotated clips. | Keep the 5-class design or fall back | W5 |
| D5 / E5 | Student architecture under MindSpore Lite constraints. | Compare (a) CRNN with FP16/FP32 GRU, (b) convolution-only TCN at full INT8, and (c) a tiny conformer (MatMul and LayerNorm are INT8-capable). Measure F1, size and on-device RTF. | ADR-002 | W4 |
| D6 / E6 | Distillation gap. | Hinton-style KD (Hinton et al., 2015) from the teacher chosen in D-1 to the E5 winner. Report the student-vs-teacher gap (§14.1). References: Chang et al. (2022); Schmid et al. (2023). | §14.1 gap target | W8 |
| D7 / E7 | Error propagation. What segment F1 does each indicator's MAE target require? | Simulate confusion matrices on golden timelines and compute indicator error (Gautheron et al., 2025). | Final §14 targets | W3 |
| D8 | Data licences and provenance. | Record for each dataset: licence, gating terms, speaker demographics, register (read vs spontaneous), and whether the WAXAL and Afrivoice Ethiopian subsets overlap (a community note suggests the same upstream). | `ml/data/DATASETS.md` | W1 |
| D9 | Augmentation. | MUSAN (Snyder et al., 2015), RIRs (Ko et al., 2017), SpecAugment (Park et al., 2019), plus classroom babble built from real non-speech segments. | Feature/augment config | W2 |

### WS-E: Huawei stack and device spikes (Sys + ML)

| Spike | What to establish | Pass criterion | Due |
|---|---|---|---|
| S1 | Hello-world `.ms` INT8 model through the 2.10.0 AAR on a 2 GB phone | Runs in the app; Java API works (ADR-004) | W0 |
| S2 | RTF and RSS measurement | Use the bundled `tools/benchmark/benchmark` over `adb`, then the in-app harness. RTF ≤ 0.3 and RSS ≤ 300 MB for the E5 candidates. | W3 |
| S3 | Converter path | Both PyTorch→ONNX→`.ms` and MindSpore→MindIR→`.ms` produce identical outputs within 1e-4 (FP32). Full-quant PTQ with a real calibration set works. Record which ops fall back to FP32. | W3 |
| S4 | MindSpore 2.10 on the in-country training hardware | Confirm the hardware first. Then install and train a toy model on it, recording backend (CPU, GPU/CUDA or Ascend) and versions. | W0–W1 |
| S5 | ModelArts in AF-Johannesburg or AF-Cairo | Which Ascend/GPU flavours exist, their price, and whether a student or education voucher applies | W1 |
| S6 | openEuler + openGauss + Python driver | Pick the openEuler LTS version. psycopg2 or psycopg3 connects (note the SASL/sha256 issue). The migrations tool works. | W0–W1 |
| S7 | Android capture robustness | FGS type `microphone`. `AudioRecordingCallback` detects silencing during an incoming call. Doze and battery over 40 min. | W2 |
| S8 | 16 KB page size | Decide: sideload only (fine), or rebuild MindSpore Lite with `-Wl,-z,max-page-size=16384` before any Play release. Test on an Android 15+ 16 KB emulator. | W6 |
| S9 | Encrypted storage | SQLCipher 4.9.x + Room with a Keystore-wrapped key. Drop Jetpack Security (deprecated). | W2 |

### WS-F: Legal and ethics (Lead)

Primary text: Proclamation No. 1321/2024 (Federal Negarit Gazette, 30th Year No. 35). Get the **official Gazette copy** through the university legal office. The copy used here is a scan hosted by DataGuidance.

Questions for the faculty legal advisor, to answer in writing before W3:

1. Is Hawassa University (or the team) the *data controller*? Does Art. 33 **registration with the Ethiopian Communications Authority** apply to a student research project, and has the Authority issued the registration Directive yet?
2. **Deployment Mode:** transient in-RAM processing captures children's voices. Is that processing a minor's personal data under Arts. 2(16) and 11? If so, what consent is required: parental consent, school-level consent, or none under the Art. 54 research exemption?
3. **Research Mode** stores child audio. Is parental consent (Art. 11(2)(a)) mandatory, or can Art. 54 (pseudonymised research) apply? What does the Hawassa ethics board require for assent?
4. Is raw voice audio **sensitive (biometric) data** under Art. 2(6) when no speaker identification is performed? This decides whether Art. 22(3) prior-approval rules apply to any transfer.
5. Are derived, teacher-linked indicators personal data? (Almost certainly yes, via `teacher_pseudo_id`.) Does storing them in-country satisfy Art. 22(1)?
6. Is a **DPIA** (Art. 47) required, and is a **DPO** (Art. 40) needed for a research project?
7. Could the weekly report (Art. 31, automated decisions) be read as automated evaluation of a teacher? The coaching-not-surveillance design should be documented against this.
8. Breach runbook: notify the Authority (Art. 43) and the data subjects (Art. 44), each within 72 h.

Ethics:

- Hawassa University IRB forms and typical turnaround (this is on the critical path).
- The national research ethics guideline in force, to be located.
- School and woreda permission letters.
- Consent and assent forms in Amharic, Sidaamu Afoo and English.

### WS-G: Competition (Lead)

Log in to the Huawei Talent portal (it blocked automated access) and record:

- the 2026–27 Innovation Competition rules and manual;
- the registration deadline;
- Ethiopia's regional grouping;
- the preliminary submission format (earlier cycles required technical documents, code and a demo video);
- any requirement to use Huawei technology.

Then re-check the Q&A lines in §7.3 against the audit (items 7m and 7n). Due W0.

---

## 3. Decisions that need the team's input

| ID | Decision | Options | Recommendation |
|---|---|---|---|
| D-1 | Teacher-model framework, given that MindNLP is discontinued | (a) PyTorch/HF teacher (XLS-R, MMS or HuBERT) on the in-country node; student trained in MindSpore. (b) Port an SSL encoder to MindSpore natively. (c) Use MMS-LID-256 as the LID teacher, plus a smaller supervised segmentation teacher. | **(a) + (c).** Reword the Q&A line to "every model that runs on the phone is trained in MindSpore and deployed with MindSpore Lite". (b) costs too much for a 3-person team. |
| D-2 | Student architecture (ADR-002) | CRNN (non-INT8 GRU), convolution-only TCN (full INT8), tiny conformer | Decide on E5 data. Default to TCN if RTF is tight. |
| D-3 | Distribution channel | Sideload/APK for the pilot vs Google Play | Sideload for the pilot. Treat 16 KB alignment as a pre-Play task (S8). |
| D-4 | O-class in scope for coaching? | Include with separate rules, or measure only | Wait for B3. |
| D-5 | Use of CC-BY-NC models (MMS) | Research-only vs replace before any commercial path | Research-only; document it in the model cards. |

---

## 4. Schedule aligned to §16

| Week | Research deliverables |
|---|---|
| W0 (Sep 25 – Oct 4) | A1 request sent; A3/A4 school-visit interviews; E1 test recording; S1, S4, S6 spikes; WS-G portal check; legal questions sent to the advisor; ethics submission |
| W1–2 | A1, A2, A5 closed; B1, B6 matrix; C1 systematic search; D8 dataset register; D9 augmentation; S5, S7, S9; ADR-001, 004, 005 with citations |
| W3–4 | A3 memo; B3, B5; C2 table; E2, E3, E5, E7; S2, S3; legal answers in; ADR-002 and ADR-003 |
| W5–6 | B2 expert panel; E4 choral separability; C3; S8 decision |
| W7–8 | E6 distillation; indicator validity v1 (§14.2) |
| W9–10 | Cross-language evaluation (§14.3); literature review chapter drafted from the search log |

---

## 5. Open questions I couldn't resolve

These need facts only the team has.

1. What hardware is the in-country training node (GPU model, or CPU only)? This decides S4 and D-1.
2. Which phone models do the pilot teachers actually own (RAM, Android version)? StatCounter covers web traffic, not teachers.
3. How long does Hawassa University's IRB typically take? The W3–4 approval target depends on it.
4. Will the app ever be distributed through Google Play? This decides S8.
5. Was "upper benchmark" in §2.1 meant for a specific grade and language set? A1 will show which one the numbers refer to.
6. Who is the data controller: the university, the department, or the team? This decides WS-F Q1.

---

## 6. Search protocol (for C1 and the literature review)

**Databases:** ERIC, ACL Anthology, ISCA Archive (Interspeech, Odyssey), IEEE Xplore, ACM DL (LAK, L@S, CHI, UMAP), EDM / JEDM, arXiv (cs.SD, eess.AS, cs.CL), Google Scholar. Add Scopus or Web of Science if the Hawassa library provides access.

**Query blocks, combined with AND:**

1. `classroom OR lesson OR teacher OR "early grade" OR preschool OR kindergarten`
2. `audio OR acoustic OR "speech activity" OR diarization OR "voice type" OR "language identification" OR "code-switching"`
3. `feedback OR observation OR "teacher talk" OR "wait time" OR choral OR "student talk" OR "language of instruction"`
4. Optional LMIC filter: `Africa OR Ethiopia OR Kenya OR "low-income" OR "developing countries" OR India OR Pakistan`

**Inclusion criteria:** empirical, automated or audio-based, classroom setting, 2010–2026. Also include classic pedagogy sources cited by included papers (backward snowballing) and forward citations of DART, VTC and M-Powering Teachers.

**Record for each paper:** setting, grade, language(s), input, classes or targets, metric, result, and whether ASR was used.

---

## 7. Reading lists

Full references are in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md). ★ marks the core list to read first. The status tag shows how each was checked for this audit.

**Pedagogy and indicator validity**
★ Rowe (1986) · ★ Tobin (1987) · ★ Howe et al. (2019) · ★ Liu & Cohen (2021) · ★ Pontefract & Hardman (2005) · ★ Heward et al. (1989) · Hardman et al. (2009) · Alexander (2015) · Kelly et al. (2018) · Hattie & Timperley (2007) · ★ Kraft et al. (2018) · Popova et al. (2021) · Popova & Evans (2016) · Glewwe & Muralidharan (2016) · Piper & Zuilkowski (2015) · Piper et al. (2018) · Cilliers et al. (2018) · Molina et al. (2018) · Stallings et al. (2014) · La Paro & Pianta (2003) · Birnhack & Perry-Hazan (2021) · Shield & Dockrell (2003)

**Ethiopian context**
★ AIR (2020, MTTCA) · ★ EGRA 2018 Endline (to obtain) · ★ ESAA 2024/25 (to obtain) · Heugh et al. (2007) · Vujcich (2013) · Alemu (2025) · Kim (2020) · Wendie & Berhanu (2025) · Gobana (2025) · Yisihak & Damtew (2024) · Haile & Mendisu (2023) · Piper et al. (2016)

**Prior systems and automated feedback**
★ Owens et al. (2017) · ★ Wang, Miller & Cortina (2013) · ★ Demszky et al. (2023) · Demszky et al. (2025) · Demszky & Liu (2023) · Jacobs et al. (2022) · Jensen et al. (2020) · Suresh et al. (2022) · Demszky & Hill (2023) · Wang & Demszky (2023) · Chanchal & Zualkernan (ED626900) · Zualkernan & Khan (2020) · Shapsough & Zualkernan (2017) · Schlotterbeck et al. (2021) · Cosbey et al. (2019) · Li et al. (2020) · Wang, Pan et al. (2014) · Donnelly et al. (2016) · Blanchard et al. (2016)

**Speech and ML**
★ Wang et al. (2025, JEDM) · ★ Lavechin et al. (2020) · ★ Gautheron et al. (2025) · ★ Pratap et al. (2023, MMS) · Diack et al. (2026, WAXAL) · Conneau et al. (2022, FLEURS) · Valk & Alumäe (2020, VoxLingua107) · Babu et al. (2021, XLS-R) · Baevski et al. (2020) · Hsu et al. (2021) · Radford et al. (2022) · Plaquet & Bredin (2023) · Bredin (2023) · Stöter et al. (2018) · Hinton et al. (2015) · Chang et al. (2022) · Schmid et al. (2023) · Jacob et al. (2018) · Snyder et al. (2015, 2018) · Desplanques et al. (2020) · Ko et al. (2017) · Park et al. (2019) · Potamianos & Narayanan (2003) · Lee et al. (1999) · Abate et al. (2005) · Gauthier et al. (2016) · Ganek & Eriks-Brophy (2018)

**Infrastructure documentation**
★ MindSpore Lite 2.10.0 release notes, downloads, quantization and operator list · MindSpore 2.10.0 release notes · Android: FGS types, audio input sharing, 16 KB page sizes, Jetpack Security release notes · SQLCipher for Android · RFC 9562 · Huawei Cloud regions FAQ · openGauss psycopg2 docs · Label Studio

**Legal and ethics**
★ Proclamation No. 1321/2024 (Gazette text) · Sweeney (2002) · Hawassa IRB guidance (to obtain) · national research ethics guideline (to obtain)
