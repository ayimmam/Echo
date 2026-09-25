# Claims audit — DIMTS_ARCHITECTURE_CONTEXT.md

Checked 2026-09-25. Verdict labels are defined in [`README.md`](README.md). Citations are (Author, year) and resolve in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md). Section numbers (§) refer to the architecture document.

## 0. Findings that change the design

These are ordered by how much they change the plan. Details are in the section tables below.

1. **MindNLP is no longer a viable dependency (§7.2, §9.4).** CONTRADICTED.
   - The GitHub repository (`mindlab-ai/mindnlp`, formerly `mindspore-lab/mindnlp`) now hosts **MindAct**, a PyTorch/Hugging Face embodied-AI toolkit. Its README says the MindNLP code "is preserved in the `legacy` branch".
   - The `legacy` branch's last commit is 2026-03-08, and the last PyPI release is 0.5.1 (2025-11-05).
   - The teacher-model path "SSL encoder loaded via MindNLP" needs a new decision (see RESEARCH_PLAN §3, D-1).
2. **Sidaamu Afoo speech technology and data exist (§2.6, §9.2 D-4).** CONTRADICTED.
   - Meta's MMS models include a Sidamo (`sid`) ASR adapter in `mms-1b-all`, a `mms-tts-sid` voice, and `sid`, `amh` and `eng` in the MMS-LID-256 and MMS-LID-4017 label sets. Licence: CC-BY-NC-4.0 (Pratap et al., 2023).
   - Digital Umuganda's *Afrivoice Ethiopia* (CC-BY-4.0, gated) has about 603 h of Sidama speech, 225 h of it transcribed, and about 618 h of Amharic.
   - Google's WAXAL corpus (Diack et al., 2026) has `sid_asr` and `amh_asr` configs.
   - The no-ASR rule still holds, but it must be justified by privacy, child speech, noise and on-device cost, not by "ASR does not exist". D-4 is no longer the bottleneck the risk register (§18) assumes.
3. **MindSpore Lite has no INT8 kernels for GRU or LSTM (§6 C-2, §9.4, ADR-002).** PARTLY.
   - The r2.10.0 operator list gives GRU and LSTM on CPU as FP16/FP32 only. Convolution, MatMul, fully-connected, LayerNorm and Softmax do support INT8.
   - A CRNN student will not be fully INT8. Either switch to a convolution-only (TCN-style) student or accept mixed precision and re-budget RTF and size.
4. **The MindSpore Lite 2.10.0 Android AAR is 4 KB page-aligned (§7.2, §12).** New finding.
   - The release package contains `mindspore-lite-2.10.0.aar`, but every arm64 `.so` has PT_LOAD alignment 4096 (checked from the ELF headers).
   - Google Play will reject updates to apps targeting API 35+ that lack 16 KB page support from **2027-02-01**. The 16 KB requirement doesn't apply to sideloaded pilot phones, but it does if Play distribution is ever planned.
5. **The legal duties are broader than the document lists (§6 C-7, §11).** Read from the Gazette text of Proclamation 1321/2024. The document omits:
   - **Registration** with the Ethiopian Communications Authority before processing (Art. 33).
   - **Parent or guardian consent** for processing minors' data (Art. 11). The document only plans "parent/school notification".
   - Breach notification **to data subjects within 72 h** (Art. 44), as well as to the Authority.
   - **DPIA** (Art. 47), **DPO** (Art. 40) and a conditional **research exemption** (Art. 54).
6. **"Teacher capability is the binding constraint" is stronger than the best Ethiopian evidence (§2.2).** PARTLY.
   - The USAID/AIR Mother-Tongue Teachers' Competencies Assessment (2019 data, 7 languages including Sidaamu Afoo) found only 39% of teachers "Proficient or above".
   - But in its multivariate analysis, home resources were the strongest predictor of reading, then school resources, then teacher competencies (American Institutes for Research [AIR], 2020).
7. **The choral-response indicator has a contested normative direction (§5 I-3).**
   - Choral responding is an evidence-based active-response technique (Heward et al., 1989), and "we do" choral practice is part of the structured pedagogy READ promoted (AIR, 2020).
   - It is also a marker of rote teaching in East African classroom-discourse research (Pontefract & Hardman, 2005; Hardman et al., 2009).
   - The coaching rule table must not treat high I-3 as bad by default.
8. **The cited source for the EGRA flat-line figures is not an EGRA report (§2.1, §21).**
   - Haile and Mendisu (2023) is a 30-teacher study of phonological-awareness teaching in Koorete. It repeats the 31.3/34.2/32.4% figures (seen via a search summary) but is not their origin.
   - The primary source, the READ M&E EGRA 2018 endline report, could not be opened.
9. **Separating teacher from child speech in real classrooms is hard, even for state-of-the-art systems.**
   - The best system in Wang et al. (2025) reached 69% teacher-vs-child accuracy, with a diarization error rate of about 34%.
   - The §14.1 targets (macro-F1 ≥ 0.70 across 5 classes, CHILD_SINGLE vs CHILD_CHORAL F1 ≥ 0.75) are ambitious and need an early feasibility check.
10. **The Huawei ecosystem has moved.**
    - Huawei Cloud's African regions are AF-Cairo and AF-Johannesburg. There is **no Lagos region**.
    - MindAudio's last release is 0.3.0 (2024-01), not 0.1.x. Its GitHub repository is no longer publicly reachable and its Gitee mirror was last updated 2024-11-22.
    - `androidx.security:security-crypto` ("Jetpack Security", §8.3) is **deprecated**.

---

## 1. §2 Problem and evidence

| # | Claim in document | Verdict | Evidence | What to change |
|---|---|---|---|---|
| 2.1a | EGRA upper-benchmark performance was 31.3% (2014), 34.2% (2016), 32.4% (2018). | **SECONDARY** | The figures appear in Haile and Mendisu (2023), seen via a search summary; the full text returned 403. That paper is a Koorete phonological-awareness study (n = 30 teachers), not an EGRA analysis. The primary source, the READ M&E *EGRA 2018 Endline Report* (USAID DEC PA00X5JW), was unreachable. | Cite the endline report directly. State the grades, languages and benchmark definition behind "upper benchmark". |
| 2.1b | ~40% of Grade 2–3 students read at 20–25 wpm (USAID 2018). | **VERIFIED** | USAID fact sheet (Oct 2020, ERIC ED628406): "only 40 percent of students in grades 2 and 3 can read at a satisfactory level of 20-25 words per minute". | This is a 2-page advocacy fact sheet. Pair it with the endline report. |
| 2.1c | 0.5%–13% of students could read with comprehension. | **VERIFIED**, but dated | RTI EdData II brochure (undated; the programme ran 2004–2013): "between 0.5% and 13% of students could read with comprehension, depending on the language and region". Comprehension means ≥ 80% correct. | Say it predates the 2014–2018 rounds. Don't place it beside 2018 figures as if current. |
| 2.1d | Ethiopia has been measured repeatedly; another child assessment tool is not the missing piece. | **PARTLY** | EGRA rounds and the MTTCA are documented (AIR, 2020). That report also finds home literacy resources are the strongest predictor of reading. | Keep the argument, but acknowledge factors outside the classroom. |
| 2.2a | Early-grade English teachers averaged 43.4% on a reading-instruction test; 70% scored below 50%. | **VERIFIED (abstract)**, PARTLY as generalised | Yisihak and Damtew (2024) abstract gives both figures. The sample is n = 30 English teachers from 5 schools at one site. | Present it as a small case study. Lead with the national MTTCA: 39% Proficient-or-above overall, >50% for Amharic teachers, 22–33% band for Sidaamu Afoo, with pedagogy components weakest (AIR, 2020). |
| 2.2b | Teacher capability is the binding constraint. | **PARTLY** | MTTCA multivariate analysis ranks home resources, then school resources, then teacher competencies. All are significant and the design is correlational (AIR, 2020, pp. 65–66). | Reword to "a significant and addressable constraint". |
| 2.2c | Preservice programs devote only a few sessions to early reading. | **VERIFIED (abstract)** for one sample | Yisihak and Damtew (2024). | Scope it to that study. MTTCA co-interpretation notes (AIR, 2020, p. 29) add region-level comments such as "no adequate subject matter pedagogy courses in the CTEs in Sidama region". |
| 2.2d | Teachers receive no feedback loop on actual classroom practice. | **UNVERIFIED** | Nothing found. Ethiopia has CPD and school- or cluster-based supervision on paper. | RESEARCH_PLAN RQ-A3: find the frequency and quality of observation and feedback. |
| 2.3a | Pre-primary GER 59.8% (2024/25); KG schools +22%; Addis Ababa 145.9%, Somali 17.7%, Afar 26.8%. | **SECONDARY** | All numbers match Atenu.org's analysis (Mar 2026), which cites *ESAA 2024/25*, Table 2.1: GER 57.8→59.8, NER 45.2→58.1, KG schools 14,909→18,209. Atenu warns that Tigray was re-included and parts of Amhara were excluded in 2023/24. The ESAA itself was not opened, and `moe.gov.et` looks compromised. | Get ESAA 2024/25 from the MoE EMIS directorate and cite Table 2.1. Carry the comparability caveat. |
| 2.3b | O-classes and child-to-child modalities serve ~75% of pre-primary children. | **PARTLY** (abstract) | Stated as background in Alemu (2025). The original data source isn't identified in the abstract. | Trace it to MoE or ESAA data. |
| 2.3c | A 2025 mixed-methods study found systemic deficits nationwide. | **PARTLY** | Alemu (2025): 380 teachers from rural Amhara and Oromia, plus 12 principals and 4 ECCE coordinators. "Nationwide" is the author's inference from trivial differences between these two regions. | Say "in rural Amhara and Oromia". Add Wendie and Berhanu (2025) for an urban case (Bahir Dar). |
| 2.4a | Policy mandates primary instruction in nationality languages. | **UNVERIFIED** (primary policy text not opened) | Context: READ supported mother-tongue curricula in 7 languages including Sidaamu Afoo (AIR, 2020). Heugh et al. (2007) is the MoE-commissioned medium-of-instruction study. | Cite the Education and Training Policy text in force. |
| 2.4b | There are shortages of trained teachers and materials in L1, and teachers who aren't proficient in the language. | **PARTLY** | MTTCA measures teachers' language knowledge and reports regional deficits (AIR, 2020). Piper et al. (2016) documents non-speaker teachers, but in Kenya. Gobana (2025) was not opened. | Use Ethiopian sources. Open Gobana (2025) and Heugh et al. (2007). |
| 2.4c | No one currently measures, at scale, which language is actually spoken in the classroom. | **PARTLY** | Human-observed or surveyed language-use studies exist: Heugh et al. (2007); Vujcich (2013), using the Young Lives school survey; and Piper et al. (2016), who report classroom observation of language use (known only from a search summary, so SECONDARY). No automated classroom language-ID study was found, but the search wasn't systematic. | Claim novelty only for **automated, continuous, passive** measurement, and only after the systematic search in RESEARCH_PLAN RQ-C1. |
| 2.5a | Acoustic indicators (talk ratio, wait time, choral vs individual response) are well-established correlates of instructional quality. | **PARTLY** | **Wait time:** established (Rowe, 1986, abstract: teachers typically wait < 1 s; Tobin, 1987, ERIC abstract, with the 3-s threshold known only from a search summary), mostly from US science classrooms. **Talk ratio:** a teacher-centred instruction factor negatively predicts value-added, from US transcripts (Liu & Cohen, 2021); dialogue *quality* matters more than quantity (Howe et al., 2019). **Choral response:** contested (see finding 7). No evidence yet from Ethiopian early grades. | Separate "established in the literature" from "validated in Ethiopia". Make local construct validation a thesis contribution (RQ-B1, RQ-B2). |
| 2.5b | These indicators are extractable without transcription. | **VERIFIED** (precedents exist) | DART classifies single/multiple/no voice from classroom audio with ~90% accuracy (Owens et al., 2017). Audio-only Stallings coding in semi-rural Pakistan reached F1 0.83 (Chanchal & Zualkernan, ERIC ED626900). The Voice Type Classifier (Lavechin et al., 2020) is another precedent. | Cite these as direct precedents. They strengthen ADR-001. |
| 2.5c | Classroom observation requires trained observers who don't exist at scale in Ethiopia. | **UNVERIFIED** | Nothing found yet. Large-scale human observation tools do exist (Teach: Molina et al., 2018; Stallings: Stallings et al., 2014). | RQ-A3. |
| 2.5d | Recording an adult teacher, with her consent, is ethically and logistically far lighter than collecting child speech data. | **PARTLY** | Children's voices are still captured. Processing a minor's personal data is lawful only with parent or guardian consent or for the minor's vital interest (Proclamation 1321/2024, Art. 11). Research Mode stores child audio. | Treat incidental child audio as minors' personal data unless the legal advisor rules otherwise (RESEARCH_PLAN §6). |
| 2.6a | Amharic ASR is weak. | **UNVERIFIED** as stated (no classroom WER found) | Amharic ASR resources exist: MMS `adapter.amh`, ALFFA (OpenSLR SLR25), FLEURS `am_et`, Afrivoice (219 h transcribed). | Don't use it as the argument. |
| 2.6b | Sidaamu Afoo ASR effectively doesn't exist. | **CONTRADICTED** | See finding 2. | Rewrite §2.6. |

## 2. §5 Indicator specification

| # | Item | Verdict | Evidence | What to change |
|---|---|---|---|---|
| 5a | I-2 response latency as a wait-time proxy. | **PARTLY** (the document already flags this) | Wait time I is the pause after a *question* (Rowe, 1986). I-2 also includes gaps after instructions and during choral drills, where near-zero latency is designed in. | Report I-2 separately for choral and single responses. Don't compare it to the 3-s literature threshold until R-4 (question detection) exists. |
| 5b | I-3 choral ratio. | **PARTLY** | See finding 7. | Frame it neutrally. The rule table should prefer balance, for example "add some individual turns", rather than "reduce chorus". |
| 5c | I-6 language share as a "novel policy indicator". | **PARTLY** | See 2.4c. | Reword: novel as *automated* measurement. |
| 5d | Language-label thresholds. | **Internal inconsistency** | §5 labels teacher segments ≥ 2.0 s, §8.3 runs 3-s LID windows on segments ≥ 2 s, and §14.1 evaluates segments ≥ 3 s. | Pick one threshold after experiment E3 in RESEARCH_PLAN. |
| 5e | Error propagation. | **Not addressed** in the document | Classification errors bias downstream estimates in automated speech measures (Gautheron et al., 2025). | Add a simulation (E7) that turns segment errors into indicator error before fixing the §14.1 targets. |

## 3. §6 Hard constraints

| # | Claim | Verdict | Evidence | What to change |
|---|---|---|---|---|
| C-2a | Target device is Android 10+. | **SECONDARY** (reasonable) | StatCounter, Ethiopia, Aug 2026: Android 94.0% of mobile traffic. Top versions: 14 (20.2%), 16 (13.9%), 13 (13.7%), 15 (11.9%), 12 (11.6%), 11 (11.6%). Web traffic over-represents newer and pricier phones. | Survey pilot teachers' phones (RQ-E6). |
| C-2b | Teachers use Tecno, Itel and Samsung. | **PARTLY** (SECONDARY) | Same source: Samsung 48.1%, Tecno 11.6%, Infinix 6.2%, Apple 5.9%, Itel 2.2%. | Add Infinix and confirm with a teacher survey. |
| C-2c | 2 GB RAM, Cortex-A53/A55, no NPU. | **UNVERIFIED** | — | Teacher phone survey. Buy two reference devices. |
| C-2d | All inference on CPU, INT8. | **PARTLY** | GRU and LSTM have no INT8 kernels in MindSpore Lite r2.10.0; Conv, MatMul, FC, LayerNorm and Softmax do. | See finding 3. |
| C-3 | RTF ≤ 0.3 is achievable. | **UNVERIFIED** | The 2.10.0 Android package ships an on-device `tools/benchmark/benchmark` binary. | Spike S2. |
| C-7a | Consent is required. | **VERIFIED** with additions | Arts. 7, 8 and 11. Art. 11 requires parent or guardian consent for minors. | See finding 5. |
| C-7b | Personal data must be stored in Ethiopia. | **VERIFIED** | Art. 22(1): "ensure the storage, on a server or data center located in Ethiopia, of personal data collected or obtained locally". Art. 22(3): cross-border transfer of *sensitive* data needs prior Authority approval. | Keep ADR-003. |
| C-7c | 72-hour breach notification. | **VERIFIED** with addition | Art. 43: to the Authority within 72 h. Art. 44: to the data subject within 72 h. | Update the breach runbook. |
| C-7d | Special protection for minors under 16. | **VERIFIED** | Art. 2(15): minor means below sixteen. Art. 11(4): no profiling of minors. | — |
| C-7e | In force since 24 Jul 2024. | **VERIFIED** | Art. 70 (in force on publication). Federal Negarit Gazette, 30th Year No. 35, 24 July 2024, p. 15619. | — |
| C-7f | *Not in document:* registration, DPO, DPIA, research exemption, regulator identity. | **VERIFIED** gaps | Arts. 33, 40, 47, 54. The Authority is the Ethiopian Communications Authority (Art. 2(36)). | Add these to §11 and the ADR backlog. |
| C-8 | No voice embeddings are persisted. | Consistent with law | Art. 2(6) defines biometric data by "specific technical processing … which allow or confirm the unique identification". Not identifying speakers keeps audio out of that definition, though this needs legal confirmation. | Put this reasoning in writing to the legal advisor. |

## 4. §7 Huawei technology mapping

| # | Claim | Verdict | Evidence | What to change |
|---|---|---|---|---|
| 7a | MindSpore Lite 2.x Android-aarch64 AAR; current release 2.10.0. | **VERIFIED** | GitHub tag `v2.10.0` on `mindspore-ai/mindspore-lite` (commit 2026-07-22). The r2.10.0 downloads page links `mindspore-lite-2.10.0-android-aarch64.tar.gz` (22.1 MB, last modified 2026-08-03). The package contains `mindspore-lite-2.10.0.aar` (5.7 MB; arm64-v8a and armeabi-v7a; `libmindspore-lite.so` 8.2 MB) and an on-device benchmark tool. | Pin 2.10.0. Note the 16 KB issue (finding 4). |
| 7b | Supports post-training INT8 quantization. | **VERIFIED** | Quantization docs (r2.10.0): weight, full and dynamic PTQ; "Full quantization supports 8bit". Lite does **not** do quantization-aware training. | — |
| 7c | Converts MindIR, ONNX and TFLite to `.ms`. | **VERIFIED** | Lite README: "supports the conversion of models serialized from … MindSpore, ONNX, TF, etc." TFLite was upgraded to 2.20.0 in 2.10.0. | — |
| 7d | Built into HarmonyOS, so the same model ports to R-5. | **VERIFIED** (vendor statement) | Downloads page: "a lightweight AI engine built into HarmonyOS". | — |
| 7e | *New:* 2.10.0 removed features. | **VERIFIED** | Release notes: 2.10.0 "Removed the model obfuscation module"; cloud side removed OpenSSL "along with the related encryption and decryption features"; 2.8.0 removed MindData and high-level `Train()`/`Evaluate()`. | Don't plan on Lite-side model encryption or obfuscation. |
| 7f | MindSpore 2.x for training. | **PARTLY** | 2.10.0 on PyPI (2026-07-31). Its release notes focus on Ascend and distributed LLM features, and the GPU/CUDA support status isn't stated. The GitHub mirror stops at v2.3.0; development moved to Gitee and AtomGit. | Spike S4: confirm MindSpore 2.10 runs on the in-country node's hardware. |
| 7g | MindAudio: last tagged release 0.1.x. | **CONTRADICTED** (detail) | PyPI shows 0.3.0 (2024-01-26). `github.com/mindspore-lab/mindaudio` isn't publicly reachable. The Gitee mirror's last commit is 2024-11-22 and has conformer, ECAPA-TDNN and others, but no wav2vec2. | Treat it as unmaintained. Use your own log-mel code, mirrored in Kotlin. |
| 7h | MindNLP (HF-compatible, PyTorch-API proxy) loads SSL encoders. | **CONTRADICTED** as viable | See finding 1. | Decision D-1 in RESEARCH_PLAN. |
| 7i | ModelArts: nearest regions Johannesburg, Cairo, Lagos AZ; no Ethiopia region. | **PARTLY** | Huawei Cloud International FAQ (updated 2026-04-16) lists AF-Cairo and AF-Johannesburg only in Africa, with no Lagos and no Ethiopia. ModelArts/Ascend availability per region wasn't checked. | Spike S5. |
| 7j | openGauss: standard drivers mostly work. | **PARTLY** | psycopg2 is known to fail with "none of the server's SASL authentication mechanisms are supported" under openGauss sha256 auth. The workaround is `password_encryption_type=1` (openGauss community blog, SECONDARY). openGauss docs describe their own psycopg2 build. | Keep the W0 spike and record the result in ADR-005. |
| 7k | openEuler LTS. | **UNVERIFIED** (which LTS) | — | Spike S6. |
| 7l | HarmonyOS NEXT doesn't run Android apps; teachers use Android. | Second half **SECONDARY** (StatCounter); first half **UNVERIFIED** here | — | Cite a Huawei primary source for the pitch. |
| 7m | Q&A line: "Every model is trained in MindSpore…". | **At risk** | This becomes false if the teacher model moves to PyTorch (D-1). | Reword it to cover the deployed student model only. |
| 7n | Q&A line: "…personal data stays on Ethiopian soil … because Ethiopian law requires it". | **VERIFIED** | Art. 22(1). | — |

## 5. §8–§9 System and ML design

| # | Claim | Verdict | Evidence | What to change |
|---|---|---|---|---|
| 8a | Foreground service with persistent notification and wake lock during lessons. | **VERIFIED** with detail | Android 14+ needs `foregroundServiceType="microphone"` and `FOREGROUND_SERVICE_MICROPHONE`, and the service can't be started while the app is in the background (Android developer docs). | Start the service only from the visible *Start lesson* UI. |
| 8b | *Not in document:* capture can be silently interrupted. | **VERIFIED** risk | Since Android 10, when a higher-priority app or a voice call takes the mic, "the previously capturing app continues to run, but receives silence". A voice call "always receives audio". | Register `AudioManager.AudioRecordingCallback`, mark silenced spans as *missing* rather than NON_SPEECH, and exclude them from I-8 and I-7. |
| 8c | Encrypted storage: "SQLCipher or Jetpack Security + Room". | **PARTLY** | `androidx.security:security-crypto` 1.1.0 (2025-07-30) deprecated all APIs "in favour of existing platform APIs and direct use of Android Keystore". SQLCipher for Android 4.9.0 is current on Maven Central. | Use SQLCipher with a Keystore-wrapped key. Drop the Jetpack Security option. |
| 8d | UUIDv7 record IDs. | **VERIFIED** | RFC 9562 (2024). | — |
| 8e | Segmentation and LID models ≤ 8 MB each at INT8. | Plausible | `pyannote/segmentation-3.0` is 5.9 MB in FP32 (MIT, gated), a precedent for tiny frame-level segmenters with an overlap class (Plaquet & Bredin, 2023). | — |
| 8f | Frame classification rather than clustering diarization (ADR-001). | **Supported** | Fixed-class voice-type classifiers work in child-centred audio (Lavechin et al., 2020). Clustering diarization in classrooms is weak (Wang et al., 2025). | Cite both in ADR-001. |
| 9a | D-3 ALFFA Amharic and FLEURS Amharic. | **VERIFIED** | OpenSLR SLR25 (MIT; about 1.0 GB Amharic read speech). Hugging Face `google/fleurs` (CC-BY-4.0) with an `am_et` config. | — |
| 9b | D-4 Sidaamu Afoo speech is publicly scarce. | **CONTRADICTED** | See finding 2. Afrivoice is gated (you accept its terms), CC-BY-4.0, and a mix of scripted, unscripted and "expert" adult speech. | Use it for LID and for D-6 mixtures. Keep self-collection for classroom-register speech. |
| 9c | D-5 MUSAN and RIR sets. | **VERIFIED** | OpenSLR SLR17 MUSAN page: CC BY 4.0. SLR28 RIRS_NOISES and SLR26: Apache 2.0. | Check per-subset licences inside MUSAN. |
| 9d | *New:* MMS-LID as an LID teacher or baseline. | Option | `facebook/mms-lid-256` includes `amh`, `sid` and `eng`. Licence CC-BY-NC-4.0. | Fine for a non-commercial thesis. Needs a licence review before any commercial use. |
| 9e | ADR-006 Label Studio. | **VERIFIED** licence | `label-studio` 1.23.1 on PyPI, Apache-2.0. | — |

## 6. §14 Evaluation targets

| # | Target | Verdict | Evidence |
|---|---|---|---|
| 14a | T-1 macro-F1 ≥ 0.70; CHILD_SINGLE vs CHILD_CHORAL F1 ≥ 0.75. | **Ambitious, unbenchmarked** | Closest benchmarks: 69% teacher-vs-child accuracy in noisy group work (Wang et al., 2025); about 90% on the coarser single/multiple/no-voice task (Owens et al., 2017). No choral-vs-single benchmark was found. Speaker-count estimation (Stöter et al., 2018) is the nearest precedent. |
| 14b | Indicator MAE targets (I-1 ≤ 5 pp and others). | Not derivable from the literature | Set them after E7, the error-propagation simulation. |

## 7. §17 Competition

| # | Claim | Verdict | Evidence |
|---|---|---|---|
| 17a | Criteria: creativity, system complexity, technical complexity, societal benefit, functionality. | **VERIFIED** | Huawei (2025) article describing the 2023–2024 Global Final Innovation Competition. |
| 17b | 15-min presentation + 5-min Q&A in English; PPT, video or physical demo. | **VERIFIED** for the 2024–25 and 2025–26 Global Finals | Huawei ICT Competition minisites. |
| 17c | AI is mandatory; works must be fully functional. | **VERIFIED** for 2024–25 | 2024–25 Global Final rules: "comprehensively use AI (mandatory) … design fully functional works". |
| 17d | 2026–27 rules, deadlines and Ethiopia's regional grouping. | **UNVERIFIED** | The 2025–26 minisite banner says "Huawei ICT Competition 2026-2027 is now in full swing". The Talent portal (rules, manual) returned 403. |
| 17e | *Context:* African teams can win the Innovation Grand Prize. | **VERIFIED** | 2025–26 Global Final winners list includes Ahmadu Bello University (Nigeria), Innovation Competition Grand Prize. |
