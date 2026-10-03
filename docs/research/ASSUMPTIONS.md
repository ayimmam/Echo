# Assumptions register — Dimts

> **2026-10-02 deployment addendum:** Hosting/gateway/database/cron capabilities remain unconfirmed. [ADR-015](../adr/015-capability-gated-hosting.md) supersedes OS-specific hosting assumptions: shared candidate, VPS fallback, PostgreSQL preferred, one conditional framework/engine. Separate research infrastructure is not available; Research Mode is **removed** by draft ADR-016, not awaiting setup. The historical register below is retained as evidence, not deployment sign-off.

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

- **Historical 2026-09-27 snapshot of 50 assumptions (not recounted after the scope changes below):**
  - 6 hold;
  - 16 are plausible but untested in this setting;
  - 17 are untested;
  - 11 were contradicted and have been replaced.
- **The five that could sink the project if wrong:**
  - **A-T4:** a desk-placed phone yields usable audio.
  - **FR-1/FR-2:** a lawful existing coarse-model data path and repeatable live reference can be obtained; single/choral is deferred.
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
| A-P5 | Talk ratio (I-1) is a meaningful quality proxy that teachers can change with feedback. | ARCH §5 | 🟡 Plausible | RCT Table 2 −0.035 = −3.5 pp (≈−4.8% relative), bundled feedback to online mentors (Demszky & Liu, 2023); Liu & Cohen (2021) | Descriptive only; no quality/ideal-ratio rule. Aggregate agreement and card usefulness (B2/A5) | Report it only as descriptive |
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
| A-T4 | A phone lying on the teacher's desk captures usable audio. | ARCH §3 | ❓ Untested — **critical** | Jensen et al. (2020): room mics "produced noisy audio". Schlotterbeck et al. (2021): lavalier into a smartphone. | E1: desk vs wired lavalier. ML, W1 | Test adult and child audibility before selecting desk or wired mic; disable biased output |
| A-T5 | MindSpore Lite 2.10.0 runs on Android via the Java API. | ARCH §7 | ✅ Holds (package) / ❓ on device | AAR inspected; minSdk 19 | S1 | JNI or ONNX fallback |
| A-T6 | The app can be published on Google Play later. | PACK | ❓ Untested | The AAR is 4 KB-aligned; Play requires 16 KB for API 35+ updates from 2027-02-01; sideloading does not fix incompatible native libraries on 16 KB devices | S8 | Rebuild Lite with 16 KB alignment |
| A-T7 | Capture runs uninterrupted for 40 minutes. | ARCH §8.3 | 🟡 Plausible, with gaps | Android silences the mic during calls or when a higher-priority app takes it | S7; mark silenced spans as missing | Indicators computed on partial lessons |
| A-T8 | MindNLP loads the SSL teacher encoder. | ARCH §7.2 | ❌ Changed | The repository pivoted to MindAct; legacy branch frozen | **Replacement under ADR-016:** one working training framework, Lite first for runtime; no mandatory teacher/student split | — |
| A-T9 | MindSpore 2.10 trains on the in-country node. | ARCH §7.2 | ❓ Untested | 2.10 notes are Ascend-focused | S4 | Train the student in PyTorch too; keep Lite for inference |
| A-T10 | openGauss works with the Python backend. | ARCH §12 | 🟡 Plausible | Known SASL/sha256 workaround | S6 | ADR-015 removes openGauss mandate; test one selected engine, no shared-SQL compatibility assumption |
| A-T11 | Encrypted local storage via SQLCipher or Jetpack Security. | ARCH §8.3 | ❌ Changed | Jetpack Security deprecated | **Replacement:** SQLCipher + Keystore-wrapped key | — |
| A-T12 | Huawei Cloud AF-Johannesburg offers what the demo needs (ECS, RDS/GaussDB, OBS, ModelArts). | PACK | 🟡 Plausible | Huawei recommends AF-Johannesburg for African users (Huawei Cloud, 2026a). ModelArts needs OBS in the same region, unencrypted (Huawei Cloud, 2026b). | S5 | Use AF-Cairo |

## D — Data and ML

| ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
|---|---|---|---|---|---|---|
| A-D1 | A fixed 5-class frame classifier beats clustering diarization for this task. | ARCH ADR-001 | 🟡 Plausible | VTC (Lavechin et al., 2020); diarization weak in classrooms (Wang et al., 2025) | E2 | — |
| A-D2 | No ASR is needed for any indicator. | ARCH C-1 | ✅ Holds | Precedents: DART, Schlotterbeck et al., Chanchal & Zualkernan | — | — |
| A-D3 | Sidaamu Afoo speech data is scarce. | ARCH D-4 | ❌ Changed | Afrivoice about 603 h (225 h transcribed); WAXAL `sid_asr`; MMS `sid` | **Replacement:** adult Sidama resources exist, with gating/licences/overlap unresolved. This establishes neither child/choral labels nor a tiny classroom LID model. Production LID is deferred. | — |
| A-D4 | LID works on teacher segments ≥ 2 s. | ARCH §5 | ❓ Untested | Short-segment LID is hard; the thresholds conflict across §5, §8.3 and §14.1 | E3 | Raise to 3–5 s; more "unknown" labels |
| A-D5 | CHILD_SINGLE and CHILD_CHORAL are acoustically separable. | ARCH §9 | ❓ Untested — **critical** | No directly comparable classroom single/choral benchmark was verified in this review. Speaker-count estimation is the nearest precedent (Stöter et al., 2018). | E4 | 4-class fallback (§18) |
| A-D6 | Teacher–student distillation gets a tiny model close to a large one. | ARCH §8.2 | 🟡 Plausible | Chang et al. (2022); Schmid et al. (2023) | E6 | Supervised small model only |
| A-D7 | 6–10 newly recorded annotated hours with κ ≥0.70 | ARCH §9.3 | ❌ **Removed** | Fixed RAM-only guardrail; ADR-016 | Replace with live aggregate protocol, not frame ground truth | Narrow thesis claims; no recording workaround |
| A-D8 | Frame-level errors don't wreck indicator validity. | PACK | ❓ Untested | Errors bias downstream estimates (Gautheron et al., 2025) | E7 | Report calibrated indicators with intervals |
| A-D9 | MMS models (CC-BY-NC) may be used for this project. | PACK | 🟡 Plausible | Non-commercial thesis and competition | Review competition use and derived-weight redistribution before G3; document in model manifests | Use an approved alternative where restrictions cannot be met |
| A-D10 | Synthetic classroom mixtures give a useful pre-training signal. | ARCH D-6 | 🟡 Plausible | Common practice (Ko et al., 2017) | E2/E4 comparisons | Rely on real data only |

## L — Legal and ethics

| ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
|---|---|---|---|---|---|---|
| A-L1 | Personal data must be stored in Ethiopia. | ARCH C-7 | ✅ Holds | Proclamation 1321/2024, Art. 22(1) | — | — |
| A-L2 | Incidental child audio needs only school/parent *notification*. | ARCH §11 | ❌ Changed | Art. 11: parent/guardian consent or vital interest | **Replacement:** obtain approved child/guardian consent and assent for transient capture and live observations; no capture before G4-E (WS-F Q2) | No Research Mode; defer field use if approvals fail |
| A-L3 | Voice audio without speaker ID isn't biometric/sensitive data. | PACK | 🟡 Plausible | Art. 2(6) ties biometric data to unique identification | Legal advisor, WS-F Q4 | Project guardrail prohibits personal-data export regardless; clarify lawful transient processing |
| A-L4 | A student research project is exempt from Art. 33 registration. | PACK | ❓ Untested | — | WS-F Q1 | Register with the Ethiopian Communications Authority before recording |
| A-L5 | Indicator records under a pseudonymous teacher ID are personal data. | PACK | ✅ Holds (by definition, Art. 2) | Pseudonymisation defined; still personal data | — | — |
| A-L6 | The 72-hour breach notice goes to the Authority only. | ARCH §11 | ❌ Changed | Art. 44 also covers data subjects | **Replacement:** plan both Authority and subject workflows; legal office checks Art. 43/44 triggers and exceptions | — |

## C — Competition

| ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
|---|---|---|---|---|---|---|
| A-C1 | The Preliminary submission is due around **1 Dec 2026**. | PACK | ❓ Untested | The page says only "Nov–Dec 2026" | Read the template and portal deadline. Lead, W0 | Pull W7–8 work forward |
| A-C2 | The Huawei stack is a *preference*. | ARCH C-11 | ❌ Changed | 2026–27: "must be deployed on Huawei Cloud and use at least one Huawei Cloud service" | **Replacement:** Huawei Cloud deployment is mandatory for the competition build | — |
| A-C3 | A Huawei Cloud demo holding **only synthetic or public data** satisfies both the competition ("complying with local laws") and Proclamation 1321/2024. | PACK | 🟡 Plausible — **critical** | Synthetic records minimise pilot-data risk; public voices, mixtures and derived artefacts still require review. Legal and competition acceptance remains open | Legal advisor WS-F Q8, in writing | Obtain organiser-approved synthetic/public-only design or acknowledge eligibility risk; never export pilot data |
| A-C4 | Ethiopia competes in the "Northern Africa" division. | PACK | 🟡 Plausible | The Huawei Technologies Ethiopia registration guide points to the Northern Africa division | Email the Huawei Ethiopia contact | Different schedule or rules |
| A-C5 | A live Android demo will count. | ARCH §17 | ✅ Holds (optional) | "Hardware devices are optional … prepare them on their own" | — | — |
| A-C6 | Scoring rewards system and technical complexity. | ARCH §17 | ❌ Changed → see A-C2 | Prelim/National score Innovation 60 / Application value 40 | Pitch leads with innovation and application value; complexity supports them | — |
| A-C7 | The team can open-source the product code by the Regional stage. | PACK | ❓ Untested | Mandatory for Regional and Global shortlists | Team decision (RESEARCH_PLAN §6 Q7) | Not eligible beyond National |

## O — Operational

| ID | Assumption | Origin | Status | Evidence | Test / owner | If wrong |
|---|---|---|---|---|---|---|
| A-O1 | Ethics approval arrives by W3–4. | ARCH §16 | ❓ Untested — **critical** | — | Ask the IRB for typical turnaround. Lead, W0 | Synthetic-only ML until approval; the Preliminary uses synthetic data |
| A-O2 | Three people can deliver edge, server, dashboard, ML and cloud demo by December. | ARCH C-10 | 🟡 Plausible, tight | The scope grew with the cloud demo tier (S10) | Weekly burn-down | Cut the dashboard to one view; the demo page reuses it |
| A-O3 | Twenty to thirty new classroom recordings | ARCH §16 | ❌ **Removed** | RAM-only guardrail/ADR-016 | Live aggregate observation replaces recording | Report narrower evidence, no new audio corpus |


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

## Follow-up register — feature scope (2026-10-03)

These current assumptions supersede the older full-scope entries where they conflict; old counts are historical. All thresholds/capacity below are proposals, not published empirical results. [Evidence and polish log](FEATURE_REVIEW.md).

| ID | Current assumption / status | Evidence / check | Consequence if wrong |
|---|---|---|---|
| FR-1 | **Critical, untested:** existing lawfully usable adult/child acoustic data can support a ≤20 MB coarse model without new recordings. | VTC is a voice-type precedent, not a classroom teacher/choral model; [Kunze et al. 2025](https://doi.org/10.21437/Interspeech.2025-1962) emphasises data relevance. E2/E5 by January. | VAD/system feasibility only; no I-1/I-5 teacher claims; supervisor revises thesis scope. |
| FR-2 | **Critical, untested:** independent live observers can repeatably measure coarse totals/counts across both languages. | Review A5: paired subset, held-out floor, reference repeatability before model comparison. No replay ground truth. | Disable measure, report failed reference protocol; no frame-F1 or subsecond claims. |
| FR-3 | **Critical, unconfirmed:** three students can supply ≈900 hours through defence, including observers/translation and writing. | RESEARCH_PLAN §5; confirm named availability and expenses. | Cut optional sync/LID experiment then I-5; retain ethics/privacy and writing reserve. |
| FR-4 | **Unconfirmed:** local-only personal-data use satisfies Art. 22(1)’s server/data-centre wording. | [Official Gazette](https://justice.gov.et/en/law/personal-data-protection-proclamation/); written university legal decision before field use. | Compliant derived-data in-country server required, or real field use remains blocked. |
| FR-5 | **Untested:** teachers want a chosen practice card and optional follow-up alongside neutral observations. | [Coach](https://documents1.worldbank.org/curated/en/589311630358726963/pdf/Technical-Guidance-Note.pdf) supports a focused cycle, not these exact scripts. Two teachers + adviser/native speakers; ≥6 teachers/3 weeks feasibility. | Revise/drop cards that are unclear or unused; never prescribe from talk-share ranges. |
| FR-6 | **Unconfirmed:** synthetic Huawei Cloud visual demo plus real on-device AI meets current Innovation eligibility. | Supplied capture requires Cloud; live portal unverified. A-C3 remains open; no mandatory MindSpore/HMS inference found. | Seek organiser decision within guardrails; do not claim eligibility or add arbitrary services. |
| FR-7 | **Unknown:** actual teachers own compatible phones and have charging access. | [World Bank connectivity](https://www.worldbank.org/en/results/2025/06/30/empowering-ethiopians-by-laying-the-digital-foundations-for-afe-economic-growth) is not teacher ownership. Survey devices; budget loan handset. | Reduce supported device list/recruitment; report deployment limit. |
| FR-8 | **Decision:** Research Mode/D-1/D-2 are removed; I-2/3/4/6/7/8, production LID, real dashboards and wider grades are post-defence. | Draft ADR-016; no public corpus establishes field/choral validity. | Reopening scope requires an explicit revised plan and evidence, never weakening fixed guardrails. |

## Follow-up register — stakeholders and research access (2026-10-03)

Author request recorded in [ADR-017](../adr/017-research-data-access-and-publication.md). These entries add no empirical claims or confirmed institutional commitments.

| ID | Assumption / status | Evidence / owner / check | Consequence if wrong |
|---|---|---|---|
| RA-1 | **Critical, unconfirmed:** institution permits Anteneh's named research access through manuscript revision, including after graduation | Lead + PI obtain controller/custodian agreement and test account before G4; [data plan](RESEARCH_DATA_MANAGEMENT_PLAN.md) | No field research until lawful usable access exists; synthetic/engineering work continues |
| RA-2 | **Rejected:** coded teacher/session rows or any group of five are automatically anonymous | [NIST SP 800-188](https://doi.org/10.6028/NIST.SP.800-188); existing A-L5; R1/R2 separation and RD-07 | Keep rows restricted; independently review fixed publication outputs |
| RA-3 | **Critical, untested:** tiny field sample supports meaningful anonymous publication tables | ≥4 validation teachers can fail the ≥5-contributor screen; small language/response groups remain identifiable; Lead + disclosure reviewer before final sampling | Publish safe engineering/pooled results and narrow claims; resourced ethics amendment needed for a larger sample, never automatic expansion |
| RA-4 | **Unconfirmed:** competent ethics route, independent consent logistics, two observers and local reviewers are available | [Stakeholder map](STAKEHOLDER_MAP.md); supervisor/PI confirms named capacity and jurisdiction | Move field dates or narrow study; gatekeeper approval cannot replace consent or blinding |
| RA-5 | **Unconfirmed:** Ethiopia-based approved research storage/support can meet Article 22 and remain available after defence | Legal/custodian decision, funded machine/operator and RD-01/06/08 | No personal-data workstation/cloud workaround; keep research synthetic until resolved |
| RA-6 | **Proposed, not institutional policy:** R0/R1 retained through 30 June 2029; 30-day rolling backups, final expiry 31 July; live withdrawal purge ≤7 days | Controller approves finite values before consent; test RD-05/08 and replace dates where university rules require | Revise agreement/participant materials before collection; never imply a statutory two-year requirement |
| RA-7 | **Explicit scope:** structured research responses can be collected without speech-derived text or child identifiers | Participant-selected fixed forms and school-held consent logistics; PI/ethics review | Revise accessible form/protocol; no interview transcription or project child roster |
| RA-8 | **Planning estimate:** stakeholder/access administration and manuscript package fit 30 + 30 hours within existing field/writing budgets | Lead tracks actual effort and external observer/reviewer availability | Cut optional sync/LID/I-5 before increasing unfunded scope; publication acceptance remains uncertain |
