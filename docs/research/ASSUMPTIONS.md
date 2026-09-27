# Assumptions register — Dimts

Evidence register dated 2026-09-26; planning caveats updated 2026-09-27. Status counts include the 2026-09-27 downgrade of the unproven novelty claim, not implementation readiness. See [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md) for current gates. This lists every assumption the design still rests on, where it comes from, how strong the evidence is, and how it will be tested. Evidence is detailed in [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md); tests are scheduled in [`RESEARCH_PLAN.md`](RESEARCH_PLAN.md).

**Origin column:**

- **ARCH** — stated in `DIMTS_ARCHITECTURE_CONTEXT.md`.
- **PACK** — introduced by this research pack. These are my working assumptions, so challenge them.

**Status column:**

| Status | Meaning |
|---|---|
| ✅ Holds | Verified against a primary source. |
| 🟡 Plausible | Supported, but not yet tested in the Dimts setting. |
| ❓ Untested | No evidence either way yet. |
| ❌ Changed | Contradicted; the replacement assumption is given. |

---

## Summary

- **Out of 50 assumptions:**
  - 6 hold;
  - 16 are plausible but untested in this setting;
  - 17 are untested;
  - 11 were contradicted and have been replaced.
- **The five that could sink the project if wrong:**
  - **A-T4:** a desk-placed phone yields usable audio.
  - **A-D5:** CHILD_SINGLE and CHILD_CHORAL are separable.
  - **A-L2:** incidental child audio can be handled under the ethics approval.
  - **A-C3:** a synthetic-data cloud demo satisfies both the competition and the law.
  - **A-O1:** ethics approval arrives by W3–4.
- **The biggest changes from the architecture document:**
  - The competition now *requires* Huawei Cloud (A-C2).
  - MindNLP is gone (A-T8).
  - Sidama speech data exists (A-D3).
  - Teachers do get *some* observation, and it matters (A-P2).

---

## P — Problem and pedagogy

| ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
|---|---|---|---|---|---|---|
| A-P1 | Early-grade reading is low and flat (≈32% at the top two benchmarks, 2014–2018). | ARCH §2.1 | ✅ Holds (corrected: 2016 = 34.3%) | AIR (2019), p. ix | — | — |
| A-P2 | Teachers get **no** feedback on classroom practice. | ARCH §2.2 | ❌ Changed | EGRA 2018: some schools have mother-tongue class observers, and observation frequency is associated with reading (d = 0.15–0.28) (AIR, 2019, pp. 53–54) | **Replacement:** observation is uneven, infrequent and unstandardised, and where it exists it is associated with better reading. Dimts makes it frequent, standardised and teacher-owned. RQ-A3 / Lead | The pitch has to lean on "frequency and standardisation" |
| A-P3 | Amharic-medium and Sidaamu Afoo-medium pilot schools are reachable from Hawassa. | PACK | ❓ Untested | ESAA 2024/25 excludes Amhara because schooling there is disrupted (MoE, 2025, PDF p. 10) | Confirm Amharic-medium schools in Hawassa city or Sidama. Lead, W1 | The cross-language experiment (§14.3) loses one language |
| A-P4 | Teacher capability is the binding constraint. | ARCH §2.2 | ❌ Changed | MTTCA ranks home, then school, then teacher (AIR, 2020) | **Replacement:** "a significant, addressable constraint" | — |
| A-P5 | Talk ratio (I-1) is a meaningful quality proxy that teachers can change with feedback. | ARCH §5 | 🟡 Plausible | RCT −5 pp talk share (Demszky & Liu, 2023, US online); Liu & Cohen (2021) | Pilot pre/post plus the expert panel (B2) | Report it only as descriptive |
| A-P6 | Short response latency (I-2) signals insufficient wait time. | ARCH §5 | 🟡 Plausible, confounded | Rowe (1986); Tobin (1987). Choral drills have near-zero latency by design. | Split I-2 by response type; question detection (R-4) | Drop the coaching rule for I-2 |
| A-P7 | High choral ratio (I-3) is undesirable. | ARCH §5 | ❌ Changed | Heward et al. (1989) vs Pontefract & Hardman (2005) | **Replacement:** I-3 is a *balance* indicator | — |
| A-P8 | Classroom language has not been measured systematically, so I-6 is novel. | ARCH §2.4 | ❓ Untested (novelty search incomplete) | Heugh et al. (2007): notes, checklist abandoned. Piper et al. (2016): child self-report. | Systematic search C1 before claiming "first" | Describe proposed continuous measurement; first-of-kind claim remains unverified |
| A-P9 | Teachers switch between Amharic, Sidaamu Afoo and English within lessons. | ARCH §6 C-6 | 🟡 Plausible | Heugh et al. (2007) observed routine Amharic–English code-switching | RQ-A4 classroom visits | The LID label set changes |
| A-P10 | Teachers will use a private, non-punitive weekly report. | ARCH §3 | ❓ Untested | Jensen et al. (2020): feedback is usually evaluative. Birnhack & Perry-Hazan (2021): teachers weigh their own privacy. | Pilot interviews (§14.5), co-design | Product redesign |
| A-P11 | Classrooms have 40–80 children. | ARCH §6 C-5 | 🟡 Plausible (averages) | Pupil-section ratio, Grades 1–6: national 54.2, Sidama 59.6 (MoE, 2025) | Record class size per pilot lesson | Augmentation ranges change |
| A-P12 | O-class pedagogy can be coached with the same indicators as Grades 1–3. | ARCH §1 | ❓ Untested | Play-based ECE differs (CLASS, MELE) | RQ-B3 | O-class becomes measure-only |

## T — Technical (device, stack, infrastructure)

| ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
|---|---|---|---|---|---|---|
| A-T1 | Teachers own Android 10+ phones with about 2 GB RAM. | ARCH C-2 | 🟡 Plausible | StatCounter: Android 94%, mostly v11+ (web traffic) | Phone survey of pilot teachers. Sys, W1 | Provide reference phones |
| A-T2 | RTF ≤ 0.3 and RSS ≤ 300 MB on the target phone. | ARCH C-3 | ❓ Untested | Schlotterbeck et al. (2021) ≈ RTF 0.12 on unspecified hardware | S2 with the bundled `benchmark` tool | Smaller model or frame skipping |
| A-T3 | All on-device inference is INT8. | ARCH C-2 | ❌ Changed | Lite r2.10.0: GRU/LSTM FP16/FP32 only | **Replacement:** INT8 candidate where export/runtime support is demonstrated; recurrent layers may need FP16/FP32 (or a convolution-only student) | — |
| A-T4 | A phone lying on the teacher's desk captures usable audio. | ARCH §3 | ❓ Untested — **critical** | Jensen et al. (2020): room mics "produced noisy audio". Schlotterbeck et al. (2021): lavalier into a smartphone. | E1: desk vs wired lavalier. ML, W1 | Add a clip-on mic to the kit (D-7) |
| A-T5 | MindSpore Lite 2.10.0 runs on Android via the Java API. | ARCH §7 | ✅ Holds (package) / ❓ on device | AAR inspected; minSdk 19 | S1 | JNI or ONNX fallback |
| A-T6 | The app can be published on Google Play later. | PACK | ❓ Untested | The AAR is 4 KB-aligned; Play requires 16 KB for API 35+ updates from 2027-02-01; sideloading does not fix incompatible native libraries on 16 KB devices | S8 | Rebuild Lite with 16 KB alignment |
| A-T7 | Capture runs uninterrupted for 40 minutes. | ARCH §8.3 | 🟡 Plausible, with gaps | Android silences the mic during calls or when a higher-priority app takes it | S7; mark silenced spans as missing | Indicators computed on partial lessons |
| A-T8 | MindNLP loads the SSL teacher encoder. | ARCH §7.2 | ❌ Changed | The repository pivoted to MindAct; legacy branch frozen | **Replacement:** PyTorch/HF teacher; student in MindSpore (D-1) | — |
| A-T9 | MindSpore 2.10 trains on the in-country node. | ARCH §7.2 | ❓ Untested | 2.10 notes are Ascend-focused | S4 | Train the student in PyTorch too; keep Lite for inference |
| A-T10 | openGauss works with the Python backend. | ARCH §12 | 🟡 Plausible | Known SASL/sha256 workaround | S6 | Fall back to PostgreSQL (same SQL) |
| A-T11 | Encrypted local storage via SQLCipher or Jetpack Security. | ARCH §8.3 | ❌ Changed | Jetpack Security deprecated | **Replacement:** SQLCipher + Keystore-wrapped key | — |
| A-T12 | Huawei Cloud AF-Johannesburg offers what the demo needs (ECS, RDS/GaussDB, OBS, ModelArts). | PACK | 🟡 Plausible | Huawei recommends AF-Johannesburg for African users (Huawei Cloud, 2026a). ModelArts needs OBS in the same region, unencrypted (Huawei Cloud, 2026b). | S5 | Use AF-Cairo |

## D — Data and ML

| ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
|---|---|---|---|---|---|---|
| A-D1 | A fixed 5-class frame classifier beats clustering diarization for this task. | ARCH ADR-001 | 🟡 Plausible | VTC (Lavechin et al., 2020); diarization weak in classrooms (Wang et al., 2025) | E2 | — |
| A-D2 | No ASR is needed for any indicator. | ARCH C-1 | ✅ Holds | Precedents: DART, Schlotterbeck et al., Chanchal & Zualkernan | — | — |
| A-D3 | Sidaamu Afoo speech data is scarce. | ARCH D-4 | ❌ Changed | Afrivoice about 603 h (225 h transcribed); WAXAL `sid_asr`; MMS `sid` | **Replacement:** public adult Sidama speech is plentiful. *Classroom-register* speech is what's scarce. | — |
| A-D4 | LID works on teacher segments ≥ 2 s. | ARCH §5 | ❓ Untested | Short-segment LID is hard; the thresholds conflict across §5, §8.3 and §14.1 | E3 | Raise to 3–5 s; more "unknown" labels |
| A-D5 | CHILD_SINGLE and CHILD_CHORAL are acoustically separable. | ARCH §9 | ❓ Untested — **critical** | No benchmark exists. Speaker-count estimation is the nearest precedent (Stöter et al., 2018). | E4 | 4-class fallback (§18) |
| A-D6 | Teacher–student distillation gets a tiny model close to a large one. | ARCH §8.2 | 🟡 Plausible | Chang et al. (2022); Schmid et al. (2023) | E6 | Supervised small model only |
| A-D7 | 6–10 annotated hours with κ ≥ 0.70 is achievable. | ARCH §9.3 | ❓ Untested | — | Annotation pilot, W3–4 | Fewer classes or more hours |
| A-D8 | Frame-level errors don't wreck indicator validity. | PACK | ❓ Untested | Errors bias downstream estimates (Gautheron et al., 2025) | E7 | Report calibrated indicators with intervals |
| A-D9 | MMS models (CC-BY-NC) may be used for this project. | PACK | 🟡 Plausible | Non-commercial thesis and competition | Review competition use and derived-weight redistribution before G3; document in model manifests | Use an approved alternative where restrictions cannot be met |
| A-D10 | Synthetic classroom mixtures give a useful pre-training signal. | ARCH D-6 | 🟡 Plausible | Common practice (Ko et al., 2017) | E2/E4 comparisons | Rely on real data only |

## L — Legal and ethics

| ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
|---|---|---|---|---|---|---|
| A-L1 | Personal data must be stored in Ethiopia. | ARCH C-7 | ✅ Holds | Proclamation 1321/2024, Art. 22(1) | — | — |
| A-L2 | Incidental child audio needs only school/parent *notification*. | ARCH §11 | ❌ Changed | Art. 11: parent/guardian consent or vital interest | **Replacement:** plan for parental consent in Research Mode, and ask whether Deployment Mode's in-RAM processing needs it (WS-F Q2) | Research Mode limited to consenting classes |
| A-L3 | Voice audio without speaker ID isn't biometric/sensitive data. | PACK | 🟡 Plausible | Art. 2(6) ties biometric data to unique identification | Legal advisor, WS-F Q4 | Art. 22(3) approval needed for any transfer |
| A-L4 | A student research project is exempt from Art. 33 registration. | PACK | ❓ Untested | — | WS-F Q1 | Register with the Ethiopian Communications Authority before recording |
| A-L5 | Indicator records under a pseudonymous teacher ID are personal data. | PACK | ✅ Holds (by definition, Art. 2) | Pseudonymisation defined; still personal data | — | — |
| A-L6 | The 72-hour breach notice goes to the Authority only. | ARCH §11 | ❌ Changed | Art. 44 also covers data subjects | **Replacement:** notify both | — |

## C — Competition

| ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
|---|---|---|---|---|---|---|
| A-C1 | The Preliminary submission is due around **1 Dec 2026**. | PACK | ❓ Untested | The page says only "Nov–Dec 2026" | Read the template and portal deadline. Lead, W0 | Pull W7–8 work forward |
| A-C2 | The Huawei stack is a *preference*. | ARCH C-11 | ❌ Changed | 2026–27: "must be deployed on Huawei Cloud and use at least one Huawei Cloud service" | **Replacement:** Huawei Cloud deployment is mandatory for the competition build | — |
| A-C3 | A Huawei Cloud demo holding **only synthetic or public data** satisfies both the competition ("complying with local laws") and Proclamation 1321/2024. | PACK | 🟡 Plausible — **critical** | Synthetic records minimise pilot-data risk; public voices, mixtures and derived artefacts still require review. Legal and competition acceptance remains open | Legal advisor WS-F Q8, in writing | Demo hosted in Ethiopia with only a thin Huawei Cloud service (e.g. RDS for public aggregates) |
| A-C4 | Ethiopia competes in the "Northern Africa" division. | PACK | 🟡 Plausible | The Huawei Technologies Ethiopia registration guide points to the Northern Africa division | Email the Huawei Ethiopia contact | Different schedule or rules |
| A-C5 | A live Android demo will count. | ARCH §17 | ✅ Holds (optional) | "Hardware devices are optional … prepare them on their own" | — | — |
| A-C6 | Scoring rewards system and technical complexity. | ARCH §17 | ❌ Changed → see A-C2 | Prelim/National score Innovation 60 / Application value 40 | Pitch leads with innovation and application value; complexity supports them | — |
| A-C7 | The team can open-source the product code by the Regional stage. | PACK | ❓ Untested | Mandatory for Regional and Global shortlists | Team decision (RESEARCH_PLAN §6 Q7) | Not eligible beyond National |

## O — Operational

| ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
|---|---|---|---|---|---|---|
| A-O1 | Ethics approval arrives by W3–4. | ARCH §16 | ❓ Untested — **critical** | — | Ask the IRB for typical turnaround. Lead, W0 | Synthetic-only ML until approval; the Preliminary uses synthetic data |
| A-O2 | Three people can deliver edge, server, dashboard, ML and cloud demo by December. | ARCH C-10 | 🟡 Plausible, tight | The scope grew with the cloud demo tier (S10) | Weekly burn-down | Cut the dashboard to one view; the demo page reuses it |
| A-O3 | Twenty to thirty lessons can be recorded by W5–6. | ARCH §16 | ❓ Untested | Depends on A-O1 and on teachers | Recruitment log | Fewer lessons, more augmentation |


## Follow-up register — STEM-first trial (2026-09-27)

The 50-entry counts above cover the original audit only. Stage order is user-directed, not an assumption. These additional assumptions remain open:

| ID | Assumption | Status / test | Consequence |
|---|---|---|---|
| A-U1 | Undergraduate participants are all adults. | Not assumed; eligibility and approved consent at G4-U. | Apply appropriate procedure or exclude minors before recording. |
| A-U2 | Child-role model transfers to adult instructor/student attribution. | Untested; STREAM-02/MODEL-01; do not relabel logits. | Capture/coarse-activity only or compatible adult model/human annotations. |
| A-U3 | STEM rooms/mics/languages represent early grades. | Untested; E1/E3 repeated independently in P2. | P1 technical success cannot replace G5-E validity. |
| A-U4 | ≥3 instructors/≥2 courses/6 sessions can be recruited. | Planning target; confirm formats, durations, languages and voluntary participation. | Report actual sample and unsupported conditions. |
| A-U5 | Selected DB/driver verifies server identity. | Open; INF-09/DB-01 documents openGauss verify-full limitation. | Ratify authenticated alternative or PostgreSQL before real data. |

See [STEM_TRIAL_PLAN.md](STEM_TRIAL_PLAN.md) and [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md). Neither classroom cohort may export personal or pilot-derived data to the synthetic cloud demo.
