# Research and implementation plan — Dimts (reviewed proposal, v1.4 — bounded scope, STEM first, publication access)

> **2026-10-02 update:** [ADR-015](../adr/015-capability-gated-hosting.md) changes hosting selection to a capability-gated shared-hosting candidate with VPS fallback. Runtime/DB and research machine are unconfirmed. Draft ADR-016 **removes** recorded Research Mode and the new classroom corpus; it does not await infrastructure. Approved live aggregate observation replaces recording-dependent field work. Undergraduate STEM remains first, followed by separate early-grade gates; scaffolding is not gate sign-off.

Scope reviewed 2026-10-02–03; repository implementation baseline remains unverified. [FEATURE_REVIEW](FEATURE_REVIEW.md) records all ratings, evidence and two revision passes; [ADR-016](../adr/016-defence-scope-and-no-recording.md) records the architectural change. Implementation must follow the ordered gates in [`IMPLEMENTATION_REVIEW.md`](IMPLEMENTATION_REVIEW.md); none is recorded as passed. This plan builds on [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md), which is now updated with the full texts in [`papers/`](papers/). Related files:

- [`STEM_TRIAL_PLAN.md`](STEM_TRIAL_PLAN.md) — first classroom phase and transition to early grades.
- [`INFRASTRUCTURE_RECHECK.md`](INFRASTRUCTURE_RECHECK.md) — official docs rechecked 2026-09-27.
- [`../testing/POST_IMPLEMENTATION_TEST_PLAN.md`](../testing/POST_IMPLEMENTATION_TEST_PLAN.md) — contract, device, infrastructure and field suites.
- [`ASSUMPTIONS.md`](ASSUMPTIONS.md) — every assumption the design still rests on.
- [`CODEBASE_STRUCTURE.md`](CODEBASE_STRUCTURE.md) — the repository layout that follows from these findings.
- [`STAKEHOLDER_MAP.md`](STAKEHOLDER_MAP.md) — participation intensity, consent/decision rights, owners and proposed cadence; appointments unconfirmed.
- [`RESEARCH_DATA_MANAGEMENT_PLAN.md`](RESEARCH_DATA_MANAGEMENT_PLAN.md) — Anteneh's named in-country analysis access, anonymous publication outputs and post-defence retention; draft ADR-017.

Owners follow §15 of the architecture document:

- **Lead** — Anteneh
- **ML** — Person 2
- **Sys** — Person 3

Week labels follow the revised milestone calendar in §5 (first planning block starts 2026-10-02); earlier W0–W6 due labels are relative planning targets, not evidence of completion. Dates are targets: real-data phases run first on undergraduate STEM after G4-U; early-grade E2/E7 aggregate validation follows STEM exit and G4-E; public/staged work and the synthetic demo continue independently. Sources are cited (Author, year) and listed in [`BIBLIOGRAPHY.md`](BIBLIOGRAPHY.md).

---

## 1. What the evidence now says

1. **The problem is real and measured.**
   - Only 32.4% of Grade 2–3 pupils reached the top two reading benchmarks in 2018, essentially unchanged since 2014 (31.3%) and 2016 (34.3%). Only 6.2% read fluently with full comprehension (AIR, 2019).
   - Only 39% of mother-tongue teachers are Proficient-or-above, and Sidaamu Afoo teachers sit in the 22–33% band (AIR, 2020).
2. **Observation-based feedback is associated with better reading, but it is patchy.** Where a school has someone who observes mother-tongue classes, and observes more often, pupils read better (AIR, 2019, pp. 53–54). Local frequency and availability remain to be established; the app’s benefit is a hypothesis. "No feedback at all" overstates it.
3. **Continuous automated classroom-language measurement remains a novelty hypothesis.** Earlier work used handwritten observation notes (Heugh et al., 2007) or children's self-reports (Piper et al., 2016). Run C1 before making a first-of-kind claim; these studies alone cannot prove absence of other systems.
4. **Audio-only classroom analytics work at coarse grain.** Reported results: ~90% (Owens et al., 2017), >80% (Schlotterbeck et al., 2021), F1 0.64–0.78 (Donnelly et al., 2016). Fine-grained teacher-vs-child separation is still hard: 69% in Wang et al. (2025).
5. **Talk-time feedback changes behaviour.** In an RCT, a bundled automated-feedback intervention estimated −3.5 percentage points (Table 2 coefficient −0.035; about −4.8% relative to control mean 0.722), not −5 pp (Demszky & Liu, 2023). The population was online 1:1 mentors; this is not an isolated acoustic-gauge effect or Ethiopian literacy evidence. No such evidence exists for choral ratio or language share yet.
6. **The competition has changed shape.**
   - 2026–27 requires an AI application deployed on **Huawei Cloud** with a **visual demo page**.
   - Preliminary and National stages score **Innovation 60% / Application value 40%**.
   - Regional-stage entries must be reproducible and open source. The supplied capture scores Regional 40/35/15/10 and Global 40/30/15/10/5; current portal/division and synthetic-cloud eligibility still need confirmation ([review §2.4](FEATURE_REVIEW.md#24-huawei-requirements-and-past-winners)).
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
2. **One log.** The future systematic review records searches in `docs/research/search-log.csv` with columns `date, database, query, hits, screened, kept, notes`, for a reproducible systematic review. This feature review is a targeted narrative review, not a completed PRISMA search; its two targeted passes and access failures are recorded in FEATURE_REVIEW §8.
3. **Check before you build.** Don't ratify an ADR until its research question closes.
4. **Two data worlds.** No new audio is persisted anywhere. Personal derived data (the pilot) stays in-country. The competition cloud demo holds synthetic or public data only (ASSUMPTIONS A-C3).
5. **Reproducible by a stranger.** The captured Regional rules require rerunnable code/data. Demo/model numbers need reproducible scripts and licensed assets; private field evidence remains in-country and its non-reproducibility on foreign cloud must be disclosed. No code is implemented by this review.
6. **Research access is a deliverable.** Before field work, agree Anteneh's named, purpose-limited access through manuscript revision. Separate coded personal study records (R1) from disclosure-reviewed anonymous publication files (R2); no automatic private-report export. Use structured participant-selected responses, never speech-derived notes/transcripts. See WS-H.

---

## 3. Workstreams

User-directed order: P0 synthetic/staged → P1 undergraduate STEM technical trial → P2 early-grade validation. P1 does not establish early-grade indicator accuracy or classroom usefulness. Languages/session types remain configurable until recruited; no assumption of English-only or adult-only enrolment.

Status is **Done**, **Open** or **Blocked** (waiting on an item in [`TO_OBTAIN.md`](TO_OBTAIN.md)).

### WS-A: Problem evidence (Lead)

| RQ | Question | Status | Result or next step |
|---|---|---|---|
| A1 | EGRA 2014–2018 results | **Done** | 31.3 / 34.3 / 32.4% top-two levels, Grades 2–3, 7 languages. The Sidaamu Afoo 2016 value is flagged as unreliable (AIR, 2019). |
| A2 | Pre-primary figures | **Done** | ESAA 2024/25: GER 59.8% excluding Amhara. The 75% O-class/child-to-child share is from ESAA 2021/22. Class size in Grades 1–6: 54.2 nationally, 59.6 in Sidama. |
| A3 | How often are teachers observed, by whom, and with what feedback? | **Open** | EGRA Table 16 shows association but not base rates. Seek 5–10 voluntary written/participant-selected structured responses (teachers, unit leaders, woreda supervisors), with unrecorded consultation, during W1–W3 visits. Output: a 1-page aggregate findings memo from the written responses; no speech-derived notes/transcripts. Due W3. |
| A4 | Actual classroom languages and code-switching in the pilot schools | **Open** | Heugh et al. (2007) observed routine Amharic–English switching. Confirm for Sidama and Hawassa schools in W1 visits. This informs supported language/copy and future LID research, not language-policy monitoring. Due W2. |
| A5 | Home vs school vs teacher factors | **Done** | MTTCA ranks home, then school, then teacher (AIR, 2020). Framing updated. |
| A6 | Current mother-tongue policy grade span | **Blocked** (ETP 2023 text) | Needed for current-policy wording. Refworld’s “2023” record links to a 1994 PDF (feature review); current policy remains unverified. |

### WS-B: Pedagogy and indicator validity (Lead)

The reduced thesis question: *Can a RAM-only low-end phone produce repeatable coarse acoustic summaries that Grade 2 reading teachers understand and find useful?* No acoustic quality score or causal learning claim. Supervisor acceptance of aggregate validation is a dependency.

| RQ | Question | Status | Method | Output | Due |
|---|---|---|---|---|---|
| B1 | I-1/I-5 limits and card relevance; other indicators deferred | Open | Build the indicator × literature matrix (§9 reading list). The RCT supports bundled feedback, not a universal ideal ratio. | `docs/indicator-rationale.md` | W2 |
| B2 | Local expert validation | Open | Two teachers plus one local pedagogy adviser first, with native-speaker review for each locale; broader 5–8 panel deferred if unavailable. | Inputs to ADR-010 | W5 |
| B3 | O-class specifics | **Deferred post-defence** | CLASS, MELE, Teach ECE; Alemu (2025). | Scope decision D-4 | W3 |
| B4 | Feedback design | Open | Kraft et al. (2018); Demszky et al. (2023, 2025); Jensen et al. (2020); Hattie & Timperley (2007). | Report copy rules | W4 |
| B5 | Surveillance perception | Open | Birnhack & Perry-Hazan (2021): nearly all teachers weigh their own privacy. Co-design with 2 teachers. | Consent copy | W3 |
| B6 | I-3 as a balance indicator | **Deferred post-defence** | Heward et al. (1989) vs Pontefract & Hardman (2005). | I-3 reporting rule | W2 |

### WS-C: Prior systems and novelty (Lead + ML)

- **C1: Systematic search** (protocol in §8). Novelty statement for I-6 and choral detection. Due W2.
- **C2: Comparison table.** Done except the IEEE papers; see the table below.
- **C3: Competition benchmarking.** Review previous Innovation winners in the Learning Space (the page links "reference entries from previous competitions"). Due W4.

| System | Input → output | Setting | Result | Take-away for Dimts |
|---|---|---|---|---|
| DART (Owens et al., 2017) | Volume/variance → single, multiple or no voice | 1,486 US college sessions | ~90% | Closest no-ASR precedent; coarse classes |
| Schlotterbeck et al. (2021) | Lavalier + smartphone; amplitude, mel and MFCC statistics → COPUS practices | 42 lessons, 19 teachers, Chile | >80% accuracy (2 practices); 7 min per hour of audio | Smartphone workflow precedent; no target-device performance proof; compare child as well as teacher audibility |
| Chanchal & Zualkernan (ED626900) | Mel spectrograms → Stallings categories | Semi-rural Pakistan, phones | F1 0.83; 0.72–0.81 with 5–20% labels | Semi-supervised learning with few labels |
| Donnelly et al. (2016) | Teacher audio, speech/rest, ASR, acoustic features → 5 segment types | 76 classes, 11 teachers | F1 0.64–0.78, teacher-independent | Validate across teachers, not frames |
| Jensen et al. (2020) | Headset, ASR, ML → discourse features | 142 sessions, 89% usable | Moderate accuracy | Room mics noisy; usability metric |
| M-Powering Teachers (Demszky & Liu, 2023; Demszky et al., 2023, 2025) | Transcripts → talk time and uptake feedback | US; RCTs | −3.5 pp talk share in the 2023 trial; uptake evidence uses different experiments | Automated feedback changes practice |
| Classroom diarization (Wang et al., 2025) | ECAPA + Whisper | US group work | Teacher-vs-child 69% | Realistic difficulty |
| VTC (Lavechin et al., 2020) | SincNet + RNN → 4 voice types | 260 h, 10 languages | Beats LENA | Frame-classifier baseline |
| LENA feedback (Wang et al., 2013) | LENA → talk ratio | US maths PD | SECONDARY | Teacher-facing precedent |
| Shapsough et al. (2016) | Mobile EGRA + MQTT | LMIC assessment | Five-minute IoT assessment power experiment | Not evidence of classroom-ML battery cost |
| Human tools: Teach, Stallings, CLASS, and Heugh et al. (2007) field notes | Observers | Global / Ethiopia | Validated or qualitative | What Dimts complements |

### WS-D: ML and data (ML)

| Experiment | Question | Method | Feeds | Due |
|---|---|---|---|---|
| E1 | Usable audio: desk vs clip-on | W0–W1 authorised staged/adult fixtures; first classroom comparison in STEM after G4-U; repeat in early grades after G4-E. Measure clipping, capture coverage and observer-rated adult/child audibility; no retained audio. Defer automated RT60 and calibrated SNR claims. Compare desk phone with a wired lavalier into the same phone (Schlotterbeck et al., 2021; Jensen et al., 2020). | Capture spec; ASSUMPTIONS A-T4 | W1 |
| E2 | Coarse model and live aggregate feasibility | Existing lawfully usable labelled acoustic corpora plus VAD baseline; no new recording corpus. P2 uses blinded live observers and frozen per-language I-1/I-5 error/reference gates (FEATURE_REVIEW A5). No live-observer frame F1. | Conditional I-1/I-5; stop if no usable reference/model | Jan model gate; P2 Feb–Mar after G4-E |
| E3 | LID public-corpus feasibility — **production deferred** | At most two ML person-days on existing approved amh/sid/eng assets, licence and size/export inspection. No new adult/child recordings or classroom LID claims. Park after the time box. | Optional labelled experimental result only | Oct; not a release prerequisite |
| E4 | Choral/single separation — **deferred post-defence** | Original synthetic-mixture/real-clip experiment retained as research idea; no new recorded real clips permitted. | Future five-class decision | Post-defence |
| E5 | One convolution-only baseline under Lite | One working training framework; same-graph Lite export/device measurements and at most one runtime fallback. CRNN and conformer challengers deferred; measure actual precision, MB/RSS, RTF, battery and task degradation. | ADR-002 | Oct–Nov |
| E6 | Distillation — **removed as mandatory; optional post-defence** | Original D-1 classroom-teacher KD path removed with corpus. Any later experiment needs lawful existing data and licence review. | No defence dependency | Post-defence |
| E7 | Error propagation | Simulate confusion matrices on golden timelines and compute indicator error (Gautheron et al., 2025). | Final §14 targets | W3 |
| E8 | Dataset register | Licence, gating, register, overlap between WAXAL and Afrivoice. | `ml/data/DATASETS.md` | W1 |
| E9 | Competition "dataset selection and processing" section | Document D-3 to D-6 plus the synthetic-lesson generator as the Template 1 dataset story. | Preliminary submission | W6 |
| E10 | STEM setup trial first | P1 sample and adult-role protocol in STEM_TRIAL_PLAN; hardware/storage/sync first, exploratory adult-role metrics only with compatible model | Technical exit before P2 | After G4-U |

### WS-E: Stack and device spikes (Sys + ML)

| Spike | Pass criterion | Due |
|---|---|---|
| S1 | `.ms` INT8 hello-world through the 2.10.0 AAR on a 2 GB phone | W0 |
| S2 | RTF ≤ 0.3 and RSS ≤ 300 MB via the bundled `benchmark` tool and the in-app harness | W3 |
| S3 | Each FP export agrees with its own source graph on fixed features (initial tolerance 1e-4, justified per model); compare frameworks only for weight-equivalent graphs. Assess PTQ through task/indicator degradation separately; record actual precision/fallback ops | W3 |
| S4 | One selected framework trains/exports a tiny model on available approved hardware; no mandatory MindSpore/PyTorch split. | Oct |
| S5 | Verify one Huawei Cloud service (ECS candidate), actual region/quota/cost and visual demo; confirm synthetic-only eligibility. ModelArts/OBS training/Ascend and managed DB/Redis are deferred. | Before G3 |
| S6 | Confirm managed ASGI or WSGI, exact Python/framework/DB/driver versions and migration/transaction tests. Prefer PostgreSQL; supported MySQL/MariaDB allowed with Django. Verify remote DB TLS identity or protected local socket. Zergaw shared candidate first; VPS fallback if required controls fail, per ADR-015 | W1, blocked on evidence |
| S7 | Modern target-SDK microphone permission/foreground-service lifecycle; `AudioRecordingCallback` detects call-silencing; 40-minute battery test | W2 |
| S8 | 16 KB decision: test Lite, SQLCipher and any fallback native libraries, ELF/ZIP alignment and runtime; sideload only on verified compatible phones | W6 |
| S9 | SQLCipher + Keystore key; backup/transfer exclusions, key-loss/reinstall and shared-phone tests before G4-U | W2 |
| **S10** | **Competition cloud stack:** same selected application and DB engine where available, independent synthetic data/credentials; One service initially (ECS candidate) after region/quota verification; managed DB, OBS/ModelArts training and Redis are deferred. Reproducible deployment after runtime decision. | W5 |
| S11 | Sync ownership, ACK/conflict/retry, withdrawal and restore-purge tests per G4-U; repeat changed paths for G4-E; cohort isolation and disclosure tests before G5-E | Before pilot/release |
| S12 | If personal-data sync is enabled: capability evidence and all applicable HOST/SEC tests, numeric retention, daily backup/RPO≤24 h/RTO≤8 h, named operator and restore/deletion evidence. No research audio host/service. Local-only field use requires an Article 22 decision. | Before server/field enablement; not G0 offline blocker |

### WS-F: Legal and ethics (Lead)

Primary text: Proclamation 1321/2024, now also located at the [Ministry of Justice](https://justice.gov.et/en/law/personal-data-protection-proclamation/). ECA’s [indexed draft manual v1.1.3](https://pdp.eca.et/files/PDP%20Manual%20Draft.%20v_1.1.3.pdf) suggests registration/DPIA/breach workflows; direct access failed. Obtain the current version and any binding directives; do not assume guidance is absent. The supplied Gazette copy is in `docs/legal/`; use it with the legal advisor (see TO_OBTAIN #2).

Questions for the legal advisor, answered in writing by W3:

1. Is the university the data controller, and does Art. 33 registration apply to student research?
2. Is transient in-RAM processing of children's voices processing of a minor's data (Arts. 2(16), 11)? What consent does it need?
3. Which approvals and consent cover transient capture and observer totals? Article 54 is not permission to revive the removed Research Mode or weaken any fixed guardrail.
4. Is raw voice audio without speaker identification *biometric* under Art. 2(6)?
5. How does Art. 22(1)’s server/data-centre wording apply to a phone-only deployment? Obtain a written answer before local-only personal-data field use; if necessary require compliant in-country derived-data storage.
6. Are a DPIA (Art. 47) and a DPO (Art. 40) needed?
7. Could the weekly report be read as an automated decision about the teacher (Art. 31)?
8. **New:** does a competition demo on Huawei Cloud (AF-Johannesburg) holding only synthetic lessons, plus scripted demo accounts, fall entirely outside the Proclamation? Get this in writing.

Add P1 questions: institution-approved instructor/student consent, age eligibility, withdrawal without academic consequences and a non-recording alternative; then obtain separate P2 scope/approval.

Ethics: identify the competent Hawassa University review committee for this education/computing study (critical path; do not assume the medical IRB); assent and consent forms in Amharic, Sidaamu Afoo and English; school and applicable education-authority letters. Gatekeeper permission never substitutes for individual consent. Whole-room refusal/visitor handling must work without identifying children or excluding them from ordinary teaching.

Additional written decisions: formal PI/controller/custodian; research versus app/sync consent; school-held guardian documentation without child rosters in project data; author access after graduation; finite research retention; anonymous publication review and restricted examiner/reviewer access within Ethiopia. Article 54 does not resolve these automatically.

### WS-G: Competition (Lead)

Supplied Northern Africa Innovation capture, 2026–27 (see audit §7); current division/rules and synthetic-cloud eligibility unconfirmed:

| Item | Requirement | Plan |
|---|---|---|
| Topic | AI application powered by Huawei Cloud | Dimts cloud tier: indicator analytics, teacher report generation, district dashboard and a lesson simulator, all on Huawei Cloud with synthetic data |
| Huawei Cloud service | ≥ 1 service (ECS, RDS, DCS …) | One service initially (ECS candidate); additional services deferred, no inferred points for stack breadth |
| Visual demo page | Mandatory | `dashboard/` + `tools/lesson-simulator` web page |
| Hardware | Optional, team-provided | Live phone demo as a bonus |
| Prelim/National scoring | Innovation 60 / Application value 40 | Lead with measured privacy, offline feasibility, abstention and teacher workflow; no language-agnostic or I-6 novelty claim. Back application value with EGRA and MTTCA numbers. |
| Regional reproducibility | Code, datasets and README re-run by the committee | Synthetic dataset generator and scripts in the repo; the pilot data is never needed to reproduce the demo |
| Open source | GitHub or Gitee for Regional and Global shortlists | Keep `docs/research/papers/` out of the public repository |

---

### WS-H: Stakeholders, research data and publication (Lead + PI + custodian)

The author requests a research output in addition to the defence and competition entry. [ADR-017](../adr/017-research-data-access-and-publication.md), the [stakeholder map](STAKEHOLDER_MAP.md) and [data plan](RESEARCH_DATA_MANAGEMENT_PLAN.md) own this scope. All items are **Open**; no appointment/access grant is inferred from a planning role.

| Item | Deliverable / acceptance | Due |
|---|---|---|
| H1 | Name academic PI, controller/access approver, competent ethics route, custodian and stakeholder contacts; agree voluntary participation and duties | Oct; before any field work |
| H2 | Written author access agreement: Anteneh can analyse approved R1 in Ethiopia through manuscript revision; receives approved R2 tables; named backup/operator, retention and graduation continuity | Before G4-U; extend at G4-E |
| H3 | Frozen minimal dictionary, separate research consent and session contribution, model-blind observer forms, participant-selected questionnaires, checked local numeric transfer; RD-01–06 rehearsal | Before applicable cohort gate |
| H4 | Weekly checked snapshots; freeze by 15 April; analysis retains pairing/teacher clustering without scoring teachers; withdrawals supersede snapshots | During P1/P2; Apr freeze |
| H5 | Independent R2 disclosure review, actual data-availability statement, thesis + submission-ready feasibility manuscript draft and contribution record | Apr–June 2027 |
| H6 | Funded custodian and documented access after graduation; proposed R1/R0 expiry 30 June 2029 and final backup expiry 31 July, approved before consent; publication revisions or closure | Agree before field work; verify June handover |

The four-teacher validation floor may be insufficient for anonymous result release. Analyse permitted strata privately, publish only safe results and narrow claims or seek a resourced/approved larger sample before collection; do not mix STEM and early grades to meet a disclosure threshold. The June build needs no research portal, recording path or new cloud service. The minimum transfer is a two-person checked numeric study form, not a report screenshot or database dump.

---

## 4. Decisions that need the team

| ID | Decision | Recommendation |
|---|---|---|
| D-1 | Teacher-model framework (MindNLP is gone) | One training framework that passes S4; Lite first for deployment. Mandatory teacher/distillation split removed; LID deferred. Report the actual path only. |
| D-2 | Student architecture | One convolution-only baseline; challengers deferred. Decide actual runtime/precision on E5 evidence. |
| D-3 | Distribution | Sideload only on tested compatible phones; test every native library plus ELF/ZIP packaging and 16 KB runtime before wider distribution. |
| D-4 | O-class in scope for coaching? | **Defer post-defence**; Grade 2 reading first. |
| D-5 | CC-BY-NC models (MMS) | Review exact upstream and derived-weight terms for competition use and redistribution; record attribution/restrictions and use approved alternatives if unresolved. |
| **D-6** (new) | Competition cloud tier vs data sovereignty | Two deployments from one codebase: `infra/huawei-cloud` (demo, synthetic data, AF-Johannesburg) and `infra/in-country` (conditional pilot, personal data, Ethiopia). There is no pilot-to-cloud path, including features, soft labels, weights, logs and backups. Public voices/mixtures need explicit approval; synthetic records are the demo default (ASSUMPTIONS A-C3). |
| **D-7** (new) | Microphone | Decide from E1 in STEM, then recheck in early-grade rooms. Do not assume a lavalier helps child audibility: retain only a placement that improves the required adult/child measure without bias. |
| D-8 / ADR-014 | First testing population | Undergraduate STEM first (user directed); isolate adult roles/data/results; later early-grade validation remains required. |
| D-9 / ADR-017 | Author research access and publication | Named access to consented R1 within Ethiopia; R2 anonymous aggregates after independent review; institutional access/retention agreement before collection. No personal-data export or automatic report sharing. |

---

## 5. Schedule (architecture §16 merged with the competition calendar)

P1 starts only after G4-U. P2 moves until STEM technical exit and G4-E pass; fixed competition dates do not imply target-population results will exist.

The exact Preliminary deadline within Nov–Dec 2026 is not yet known. The plan assumes **1 Dec 2026** until the templates or portal say otherwise (ASSUMPTIONS A-C1).

| Week | Dates | Research and build | Competition |
|---|---|---|---|
| W0–2 | Oct 2–18 | Confirm actual hours/phones/data/adviser; S1/S4 bounded spike; no-recording ADR; observer rehearsal with authorised adults; ethics and Article 22 questions | Confirm division/rubric/deadline/synthetic eligibility; obtain templates |
| W3–6 | Oct 19 – Nov 15 | G0/G1 → G2 offline slice; one coarse/VAD baseline, A1–A4, native copy, A5 observer protocol; E3 limited to two person-days | G3 synthetic page + actual inference evidence; draft package |
| W7–12 | Nov 16 – Dec | P1 only after G4-U; extended-device and shared-phone tests; no new recorded corpus; fix technical defects | Preliminary at actual deadline (1 Dec unconfirmed); reproducible package |
| W13–16 | Jan 2027 | STEM exit; freeze candidate/reference thresholds; no usable corpus/model/reference → revise thesis to feasibility with supervisor, disable unsupported measures; separate G4-E | National date to confirm |
| W17–24 | Feb – Mar | P2 live aggregate validation + ≥3-week usefulness study if approved; report missing strata. Optional secure sync only within capacity | Regional window to confirm; public release/licence audit and independent rerun |
| W25–26 | Apr 1–15 | Field/data/feature freeze; no new models/services | Freeze reproducible evidence |
| W27–32 | Apr 16 – May | Held-out analysis, negative results, thesis writing, usability findings and limits | Global if eligible, date to confirm |
| Defence | June 2027 | Defence preparation/revisions; post-defence handover or approved data/service closure | No new feature promises |

**Capacity assumption:** 3 students ×10 h/week ×30 productive weeks ≈900 person-hours. Envelopes: capture/privacy 170; core/report/localisation 100; ML/export 150; ethics/co-design/field 120; optional sync 50; synthetic cloud/reproduction 60; thesis/pitch 140; contingency 110. Within existing envelopes, reserve 30 ethics/field hours for stakeholder/data-access administration and 30 writing hours for curation, disclosure and manuscript packaging; no extra 60 hours or new service is assumed. Confirm availability, including observer and review time. Cut sync, LID experiments then I-5 when hours/gates slip, never privacy or evidence standards. A 50-hour sync stop limit is not a proven secure-service estimate. All real-data operations need a named post-defence operator or planned closure.

---

## 6. Open questions only the team can answer

1. The training node's hardware (S4, D-1).
2. The pilot teachers' phones (ASSUMPTIONS A-T1).
3. Hawassa IRB turnaround.
4. Play Store: ever?
5. Who is the data controller?
6. **New:** will the Amharic-medium pilot schools be in Hawassa or Sidama rather than Amhara region? ESAA 2024/25 excludes Amhara because schooling is disrupted there (ASSUMPTIONS A-P3).
7. **New:** does the team accept making the product code open source (a Regional requirement)?
8. Will the supervisor accept the narrowed aggregate/engineering thesis; are paired live observers, four held-out teachers and both languages available?
9. Which STEM courses, instructors, languages and session formats/durations are available for P1? Recruitment plan defaults are in STEM_TRIAL_PLAN; confirm actual numbers before capture.
10. Who will sign and provision Anteneh's research access after graduation, and which publication outlet/restricted-examination process accepts these data constraints? Confirm the proposed 2029 retention dates before issuing consent.

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
