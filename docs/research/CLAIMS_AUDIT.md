# Claims audit — DIMTS_ARCHITECTURE_CONTEXT.md

First checked 2026-09-25. Updated 2026-09-26 with the full texts supplied in [`papers/`](papers/). Verdict labels are defined in [`README.md`](README.md). Citations are (Author, year) and resolve in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md). Section numbers (§) refer to the architecture document. Page numbers marked "PDF p." count PDF pages rather than printed page numbers.

## 0. Findings that change the design

These are ordered by how much they change the plan. Details are in the section tables below.

1. **The 2026–27 Innovation Competition requires deployment on Huawei Cloud (§7, §17).** New, VERIFIED from the Huawei Talent page for the Northern Africa division (supplied screenshots).
   - The topic is "Creating Innovative AI Applications Powered by Huawei Cloud". Entries must "be deployed on Huawei Cloud and use at least one Huawei Cloud service while complying with local laws and regulations", and must be shown on "a visual demo page".
   - Scoring differs from what §17 assumes:
     - Preliminary and National stages: **Innovation 60% / Application value 40%**.
     - Regional stage: Innovation 40 / Application value 35 / Completeness & demonstrability 15 / Presentation 10.
     - At Regional, the committee **re-runs the submitted code and datasets**, and a mismatch disqualifies the entry.
     - Regional- and Global-shortlisted entries must be **open source** (GitHub or Gitee).
   - This is in tension with C-7 (personal data stays in Ethiopia; Huawei Cloud has no Ethiopian region). The design needs a **cloud demo tier that holds only synthetic or public data**, separate from the in-country pilot tier. See ASSUMPTIONS A-C3 and CODEBASE_STRUCTURE.
2. **MindNLP is no longer a viable dependency (§7.2, §9.4).** CONTRADICTED.
   - The GitHub repository (`mindlab-ai/mindnlp`, formerly `mindspore-lab/mindnlp`) now hosts **MindAct**, a PyTorch/Hugging Face embodied-AI toolkit. Its README says the MindNLP code "is preserved in the `legacy` branch".
   - The `legacy` branch's last commit is 2026-03-08, and the last PyPI release is 0.5.1 (2025-11-05).
3. **Sidaamu Afoo speech technology and data exist (§2.6, §9.2 D-4).** CONTRADICTED.
   - MMS has a Sidamo ASR adapter, a Sidamo TTS voice, and `sid`/`amh`/`eng` in the MMS-LID-256 and MMS-LID-4017 label sets (CC-BY-NC-4.0).
   - *Afrivoice Ethiopia* (CC-BY-4.0) has about 603 h of Sidama speech (225 h transcribed). WAXAL has `sid_asr`.
   - The no-ASR rule still holds, but on privacy, child-speech, noise and on-device grounds.
4. **MindSpore Lite has no INT8 kernels for GRU or LSTM (§6 C-2, §9.4, ADR-002).** PARTLY. A CRNN student will not be fully INT8.
5. **The MindSpore Lite 2.10.0 Android AAR is 4 KB page-aligned (§7.2).** New. This blocks Google Play updates targeting API 35+ from 2027-02-01. It doesn't matter for sideloaded pilot phones.
6. **The legal duties are broader than the document lists (§6 C-7, §11).** From the Gazette text:
   - registration with the Ethiopian Communications Authority (Art. 33);
   - parent or guardian consent for minors' data (Art. 11);
   - breach notice to data subjects within 72 h (Art. 44);
   - a DPIA (Art. 47), a DPO (Art. 40), and a conditional research exemption (Art. 54).
7. **"Teachers get no feedback" and "teacher capability is the binding constraint" both overstate the evidence (§2.2).** PARTLY.
   - MTTCA: 39% of mother-tongue teachers are Proficient-or-above, but home resources predict reading more strongly than teacher competency (AIR, 2020).
   - EGRA 2018 shows classroom observation **does exist in some schools**. Having someone who observes mother-tongue classes is **positively associated with reading** (d = 0.23–0.28), as is observing them three times per semester vs never (d = 0.18) (AIR, 2019, pp. 53–54).
   - The accurate framing: observation is uneven, infrequent and not standardised, and where it happens it is associated with better reading. That supports Dimts better than "no feedback loop".
8. **The EGRA flat-line figures are confirmed, with one typo (§2.1).** VERIFIED.
   - The EGRA 2018 endline report gives **31.3% (2014), 34.3% (2016), 32.4% (2018)** for the top two benchmark levels combined, Grades 2–3, all languages (AIR, 2019, p. ix).
   - The architecture document's "34.2%" comes from Haile and Mendisu (2023), who mis-copied it.
9. **The choral-response indicator has a contested normative direction (§5 I-3).**
   - It is an evidence-based active-response technique (Heward et al., 1989) and part of READ's "we do" structured pedagogy (AIR, 2020).
   - It is also a marker of rote teaching (Pontefract & Hardman, 2005; Hardman et al., 2009).
10. **Classroom language has never been measured systematically. That strengthens the I-6 novelty claim (§2.4).** VERIFIED from full texts.
    - Heugh et al. (2007) used about 100 classroom observations recorded as handwritten notes. Their checklist was "used in the first half of the fieldwork, [but] not … in the second".
    - Piper et al. (2016) measured school language use through **children's self-reports**, not observation.
11. **Separating teacher from child speech is hard, and desk-placed microphones are the weak link.**
    - The best system in Wang et al. (2025) reached 69% teacher-vs-child accuracy.
    - Jensen et al. (2020) chose a $230 wireless headset because room microphones "produced noisy audio".
    - Schlotterbeck et al. (2021) used low-cost lavalier mics plugged into teachers' smartphones.
    - Plan a **clip-on microphone** arm in experiment E1, not just desk placement.

---

## 1. §2 Problem and evidence

| # | Claim in document | Verdict | Evidence | What to change |
|---|---|---|---|---|
| 2.1a | EGRA upper-benchmark performance was 31.3% (2014), 34.2% (2016), 32.4% (2018). | **VERIFIED** with correction | EGRA 2018 Endline (AIR, 2019, p. ix): "upper two benchmark levels was 31.3% in 2014 … increased slightly to 34.3% in 2016 and then slipped back to 32.4% in 2018, with the differences being too small to be considered practically significant." The benchmark is Levels 1 + 2 ("full" + "increasing" proficiency), with language-specific ORF cut scores from a 2015 MoE/USAID workshop. Grades 2–3, 7 languages. Caveats (footnotes 2–3): Aff Somali 2014 and Wolayttatto 2016 are excluded from aggregates, and "2016 data for Sidamu Affo may not be reliable". | Change 34.2 → **34.3**. Cite AIR (2019), not Haile & Mendisu. Add: only **6.2%** reach the top level (Amharic 11.5% → Tigrigna 2.0%). The top-two share is 50.0% for Amharic and 16.0% for Haddiysa (p. viii). |
| 2.1b | ~40% of Grade 2–3 students read at 20–25 wpm (USAID 2018). | **VERIFIED** | USAID fact sheet (Oct 2020, ERIC ED628406). | Pair it with AIR (2019). |
| 2.1c | 0.5%–13% of students could read with comprehension. | **VERIFIED**, but dated | RTI EdData II brochure (programme 2004–2013). | Say it predates the 2014–2018 rounds. |
| 2.1d | Ethiopia has been measured repeatedly; another child assessment isn't the missing piece. | **PARTLY** | Supported by EGRA 2010/2014/2016/2018 and the MTTCA. Home literacy resources are the strongest predictor (AIR, 2020). | Keep the argument; acknowledge factors outside the classroom. |
| 2.2a | Early-grade English teachers averaged 43.4%; 70% scored below 50%. | **VERIFIED**, narrow | Yisihak and Damtew (2024), full text: n = 30 English teachers, 5 schools. The authors' own limitation: "conducted in one of big cities in Ethiopia … generalizability … is limited". | Present it as a case study. Lead with MTTCA (39% Proficient-or-above; Sidaamu Afoo in the 22–33% band). |
| 2.2b | Teacher capability is the binding constraint. | **PARTLY** | MTTCA multivariate ranking: home resources, then school resources, then teacher competency (AIR, 2020, pp. 65–66). | Say "a significant and addressable constraint". |
| 2.2c | Preservice programs devote only a few sessions to early reading. | **VERIFIED** for one sample | Yisihak and Damtew (2024). MTTCA expert notes: "no adequate subject matter pedagogy courses in the CTEs in Sidama region" (AIR, 2020, p. 29). | Scope it to those sources. |
| 2.2d | Teachers receive no feedback loop on actual classroom practice. | **PARTLY** (overstated) | Some Ethiopian schools have a person who observes mother-tongue classes. Where present, observation (d = 0.23–0.28) and frequency (3×/semester vs never, d = 0.18) are positively associated with reading. But director "monitoring by classroom observation" is negatively associated (d = −0.10). All correlational (AIR, 2019, pp. 53–54). AIR recommends observing every mother-tongue teacher at least three times per semester (p. 66). In the US, feedback is "infrequent … often more focused on evaluating performance than on improving practice" (Jensen et al., 2020). | Reframe: observation is uneven, infrequent and unstandardised. Dimts supplies frequent, non-evaluative, teacher-owned feedback. |
| 2.3a | Pre-primary GER 59.8% (2024/25); KG schools +22%; Addis Ababa 145.9%, Somali 17.7%, Afar 26.8%. | **VERIFIED** | ESAA 2024/25 (MoE, 2025): national GER 59.8%, +2 pp, GPI 0.95 (PDF p. 14). Table 2.1: Afar 26.8, Somali 17.7, Harari 102.9, Addis Ababa 145.9. KG schools 18,209 (72% government) vs 14,909 (PDF p. 17). **Indicators exclude Amhara** because "teaching-learning is not fully implemented in most parts of Amhara region for the last two consecutive years"; Tigray is re-included (PDF p. 10). | Cite ESAA directly. State that national figures exclude Amhara. |
| 2.3b | O-classes and child-to-child modalities serve ~75% of pre-primary children. | **VERIFIED** (via citation), dated | Alemu (2025), full text: "According to MoE (2021), 75% of children enrolled in pre-primary education in the 2021/2022 academic year were attending O-classes and child-to-child programs. Only 25% … kindergartens." | Say "in 2021/22 (ESAA)". Look for a newer split in ESAA 2024/25. |
| 2.3c | A 2025 mixed-methods study found systemic deficits nationwide. | **PARTLY** | Alemu (2025), full text: rural Amhara and Oromia only. n = 380 valid responses (86.4% response rate). The plain-language summary says 541, which contradicts the methods section. | Say "in rural Amhara and Oromia". |
| 2.4a | Policy mandates primary instruction in nationality languages. | **PARTLY** | Gobana (2025) says the model is anchored in the 1994 and 2023 Education and Training Policies. Heugh et al. (2007) describe the SNNPR model at the time: mother tongues including Sidama as MOI in the first cycle. The policy texts themselves weren't opened. | Cite the 2023 ETP text. Confirm the current grade span for Sidaamu Afoo MOI. |
| 2.4b | Shortages of trained L1 teachers and materials; teachers not proficient in the language. | **VERIFIED** (Ethiopian source) | Heugh et al. (2007, PDF p. 38): "Many teachers do not have adequate academic literacy skills or proficiency in the language of learning and teaching". SNNPR/Sidama rural schools had "severe shortages of books" and "large class size" (PDF p. 74). MTTCA regional deficits (AIR, 2020). Gobana (2025) is conceptual and cites Trudell (2016). | Cite Heugh et al. and AIR (2020); treat Gobana as secondary. |
| 2.4c | No one measures, at scale, which language is actually spoken in the classroom. | **VERIFIED** for systematic/automated measurement | Heugh et al. (2007, PDF pp. 5, 16): about 100 observations, note-taking, occasional audio/video; the checklist was abandoned mid-fieldwork. Piper et al. (2016, PDF p. 9): school language use from children's self-report (Kiswahili 79–87%, English 21–26%, mother tongue ~29%). Vujcich (2013): survey. | Claim novelty for **systematic, automated, continuous** measurement. Still run the RQ-C1 search before saying "first". |
| 2.5a | Acoustic indicators are well-established correlates of instructional quality. | **PARTLY** | **Wait time:** established (Rowe, 1986; Tobin, 1987), US. **Talk ratio:** a teacher-centred factor negatively predicts value-added (Liu & Cohen, 2021). An RCT showed that talk-time feedback **cut mentors' talk share by 5 pp, to 69%** (Demszky & Liu, 2023), using the same definition as I-1. **Choral:** contested. No Ethiopian validation. | Separate "established" from "validated in Ethiopia". |
| 2.5b | Extractable without transcription. | **VERIFIED** | DART ~90% (Owens et al., 2017). Pakistan audio Stallings F1 0.83 (Chanchal & Zualkernan). Chile, smartphone + lavalier, >80% accuracy for the two most common practices (Schlotterbeck et al., 2021). VTC (Lavechin et al., 2020). | Cite as precedents. |
| 2.5c | Trained observers don't exist at scale in Ethiopia. | **UNVERIFIED** | EGRA 2018 shows some schools have an assigned observer (director, unit leader or department head), but not how many. | RQ-A3; get the Table 16 base rates from AIR's data or report annex. |
| 2.5d | Recording an adult teacher is ethically much lighter than collecting child speech. | **PARTLY** | Children's voices are still captured (Proclamation 1321/2024, Art. 11). | Treat incidental child audio as minors' data unless the legal advisor rules otherwise. |
| 2.6a | Amharic ASR is weak. | **UNVERIFIED** as stated | ASR resources exist (MMS `adapter.amh`, ALFFA, FLEURS, Afrivoice). | Don't use it as the argument. |
| 2.6b | Sidaamu Afoo ASR effectively doesn't exist. | **CONTRADICTED** | See finding 3. | Rewrite §2.6. |

## 2. §5 Indicator specification

| # | Item | Verdict | Evidence | What to change |
|---|---|---|---|---|
| 5a | I-2 response latency as a wait-time proxy. | **PARTLY** | Wait time I follows a question (Rowe, 1986). I-2 also mixes in instructions and choral drills. | Report I-2 separately for choral and single responses. Don't compare with the 3-s threshold until R-4 exists. |
| 5b | I-3 choral ratio. | **PARTLY** | See finding 9. | Frame it as a balance indicator. |
| 5c | I-6 language share is novel. | **VERIFIED** as automated | See 2.4c. | — |
| 5d | Language-label thresholds. | **Internal inconsistency** | ≥ 2.0 s (§5) vs 3-s windows (§8.3) vs ≥ 3 s evaluation (§14.1). | Decide after E3. |
| 5e | Error propagation. | **Not addressed** | Gautheron et al. (2025). | Experiment E7. |
| 5f | I-1 talk ratio definition. | **Consistent with literature** | Demszky and Liu (2023) define talk time as teacher / (teacher + student), the same as I-1. | Cite it. |

## 3. §6 Hard constraints

| # | Claim | Verdict | Evidence | What to change |
|---|---|---|---|---|
| C-2a | Target device is Android 10+. | **SECONDARY** (reasonable) | StatCounter, Aug 2026: Android 94.0%. Versions 14 (20.2%), 16, 13, 15, 12, 11. | Teacher phone survey. |
| C-2b | Tecno, Itel, Samsung. | **PARTLY** (SECONDARY) | Samsung 48.1%, Tecno 11.6%, Infinix 6.2%, Apple 5.9%, Itel 2.2%. | Add Infinix. |
| C-2c | 2 GB RAM, A53/A55, no NPU. | **UNVERIFIED** | — | Teacher phone survey. |
| C-2d | All inference CPU INT8. | **PARTLY** | GRU/LSTM FP16/FP32 only in Lite r2.10.0. | Finding 4. |
| C-3 | RTF ≤ 0.3. | **UNVERIFIED** | The package ships an on-device benchmark tool. For scale, Schlotterbeck et al. (2021) report ~7 min per 1 h of audio (RTF ≈ 0.12, their hardware). | Spike S2. |
| C-5 | Classrooms of 40–80 children. | **PARTLY VERIFIED** (averages) | ESAA 2024/25 pupil-section ratio, Grades 1–6: national 54.2 (standard 50), **Sidama 59.6**, Oromia 60.0, Addis Ababa 37.1 (PDF p. 28). Averages hide the upper tail. | Report the range observed in pilot classrooms. |
| C-7a | Consent is required. | **VERIFIED** + additions | Arts. 7, 8 and 11. | Finding 6. |
| C-7b | Personal data stored in Ethiopia. | **VERIFIED** | Art. 22(1); Art. 22(3). | Keep ADR-003. The competition cloud demo must hold no personal data. |
| C-7c | 72-hour breach notice. | **VERIFIED** + addition | Arts. 43, 44. | Update the runbook. |
| C-7d | Minors under 16. | **VERIFIED** | Art. 2(15); Art. 11(4). | — |
| C-7e | In force 24 Jul 2024. | **VERIFIED** | Art. 70; Negarit Gazette 30th Year No. 35. | — |
| C-7f | *Not in document:* registration, DPO, DPIA, research exemption. | **VERIFIED** gaps | Arts. 33, 40, 47, 54. | Add to §11. |
| C-8 | No voice embeddings persisted. | Consistent | Art. 2(6). | Legal confirmation. |
| C-11 | Huawei stack is a "preference". | **CONTRADICTED** for the competition | 2026–27 rules make Huawei Cloud deployment mandatory (finding 1). | Upgrade to a hard requirement for the competition build only. |

## 4. §7 Huawei technology mapping

| # | Claim | Verdict | Evidence | What to change |
|---|---|---|---|---|
| 7a | MindSpore Lite AAR, 2.10.0 current. | **VERIFIED** | Tag, downloads page and AAR inspected. | Pin 2.10.0. |
| 7b | Post-training INT8. | **VERIFIED** | Quantization docs; no QAT in Lite. | — |
| 7c | Converts MindIR, ONNX, TFLite. | **VERIFIED** | Lite README. | — |
| 7d | Built into HarmonyOS. | **VERIFIED** (vendor) | Downloads page. | — |
| 7e | *New:* removed features in 2.8–2.10. | **VERIFIED** | Release notes. | No Lite-side model encryption. |
| 7f | MindSpore 2.x training. | **PARTLY** | 2.10.0 is Ascend-focused; GPU status not stated. | Spike S4. |
| 7g | MindAudio 0.1.x. | **CONTRADICTED** (detail) | 0.3.0 (2024-01); unmaintained. | Own feature code. |
| 7h | MindNLP loads SSL encoders. | **CONTRADICTED** as viable | Finding 2. | Decision D-1. |
| 7i | ModelArts nearest regions Johannesburg, Cairo, Lagos. | **PARTLY** | Regions FAQ lists AF-Cairo and AF-Johannesburg; no Lagos. Cloud Connect overview (Huawei Cloud, 2026a): "If your target users are in Africa, select the AF-Johannesburg region." ModelArts overview (Huawei Cloud, 2026b): "ModelArts does not support encrypted OBS buckets" and "does not support cross-region access to OBS buckets". Per-region ModelArts or Ascend availability still unconfirmed. | Use AF-Johannesburg. Keep ModelArts and OBS in the same region. Never place personal data in unencrypted OBS. |
| 7j | openGauss drivers. | **PARTLY** | psycopg2 SASL/sha256 issue. | W0 spike. |
| 7k | openEuler LTS. | **UNVERIFIED** | — | Spike S6. |
| 7l | HarmonyOS NEXT / Android. | Half SECONDARY | — | Cite a Huawei primary source. |
| 7m | "Every model is trained in MindSpore". | **At risk** | D-1. | Reword to the on-device model. |
| 7n | "Personal data stays on Ethiopian soil". | **VERIFIED** | Art. 22(1). | Pitch the cloud demo as "synthetic data only". |
| 7o | *New:* competition needs a Huawei Cloud service. | **VERIFIED** | Finding 1. ECS, RDS and DCS are named as examples. | Deploy the demo stack on ECS + RDS (or GaussDB) in AF-Johannesburg. |

## 5. §8–§9 System and ML design

| # | Claim | Verdict | Evidence | What to change |
|---|---|---|---|---|
| 8a | Foreground service, wake lock. | **VERIFIED** | FGS type `microphone`; can't start from background. | Start only from the visible UI. |
| 8b | *Not in document:* silent interruption. | **VERIFIED** risk | Android 10+ audio sharing: the capturing app "receives silence". | `AudioRecordingCallback`; mark spans as missing. |
| 8c | "SQLCipher or Jetpack Security". | **PARTLY** | security-crypto deprecated. | SQLCipher + Keystore. |
| 8d | UUIDv7. | **VERIFIED** | RFC 9562. | — |
| 8e | Models ≤ 8 MB. | Plausible | pyannote seg-3.0 5.9 MB FP32. | — |
| 8f | Frame classification (ADR-001). | **Supported** | Lavechin et al. (2020); Wang et al. (2025); Donnelly et al. (2016): five segment types, F1 0.64–0.78, teacher-independent validation. | Cite in ADR-001. |
| 8g | *New:* microphone placement. | **Evidence against desk-only** | Jensen et al. (2020): room mics "produced noisy audio"; they used a headset; 127/142 (89%) recordings usable. Schlotterbeck et al. (2021): lavalier + smartphone. | E1 compares desk vs a wired lavalier (confirm local price and availability). |
| 9a | ALFFA, FLEURS Amharic. | **VERIFIED** | — | — |
| 9b | Sidama speech scarce. | **CONTRADICTED** | Afrivoice, WAXAL. | Use for LID and D-6. |
| 9c | MUSAN, RIR. | **VERIFIED** | — | Check MUSAN subsets. |
| 9d | *New:* MMS-LID. | Option | CC-BY-NC-4.0. | Research use only. |
| 9e | Label Studio. | **VERIFIED** | Apache-2.0. | — |
| 10a | *New:* sync transport. | Context | A MQTT-based mobile assessment pipeline in an LMIC EGRA context showed low power use (Shapsough et al., 2016). | HTTPS batch sync stays the default; MQTT isn't needed for weekly reports. |

## 6. §14 Evaluation targets

| # | Target | Verdict | Evidence |
|---|---|---|---|
| 14a | T-1 macro-F1 ≥ 0.70; CHILD_SINGLE vs CHILD_CHORAL F1 ≥ 0.75. | **Ambitious** | Wang et al. (2025): teacher vs child 69%. Donnelly et al. (2016): F1 0.64–0.78 for 5 segment types, with ASR. Schlotterbeck et al. (2021): >80% on 2 practices. Owens et al. (2017): ~90% on 3 coarse classes. No choral benchmark exists. |
| 14b | Indicator MAE targets. | Not derivable | Set after E7. |
| 14c | *New:* recording usability. | Benchmark | 89% usable in Jensen et al. (2020). Add "≥ 85% of pilot lessons usable" as a system metric. |

## 7. §17 Competition

| # | Claim | Verdict | Evidence |
|---|---|---|---|
| 17a | Criteria: creativity, system complexity, technical complexity, societal benefit, functionality. | **OUTDATED** | That was the 2023–24 wording (Huawei, 2025). For 2026–27: Prelim/National = Innovation 60 + Application value 40; Regional = 40/35/15/10; Global = 40/30/15, then Application & implementation progress 10 (includes "GitHub stars"), then Presentation 5. |
| 17b | 15-min presentation + 5-min Q&A in English. | **VERIFIED** for 2026–27 Regional and Global | Huawei Talent page. |
| 17c | AI mandatory; fully functional. | **VERIFIED** | "Participants are required to use AI technologies and develop their entries based on Huawei Cloud services." |
| 17d | 2026–27 rules, deadlines, regional grouping. | **VERIFIED** (Northern Africa division) | Registration Aug–Dec 2026; Preliminary Nov–Dec 2026; National Jan 2027; Regional Feb/Mar 2027; Global May 2027. The Huawei Ethiopia registration guide points Ethiopian students to the "Northern Africa" division. Confirm with the Huawei Ethiopia ICT Ecosystem contact. |
| 17e | *New:* submission requirements. | **VERIFIED** | Prelim/National: Template 1 (Word) covering background, innovation highlights, **dataset selection and processing**, key code snippets, quantifiable indicators. Regional: Template 2 PPT, visual demo-page URL, complete code, datasets, reproducible README, and proof of open-sourcing. |
| 17f | Demo on a cheap Android phone. | **Allowed** | "Hardware devices are optional … the team must prepare them on their own." The cloud demo page is mandatory in addition. |
