# Tanner follow-up: acoustic validity and EQUIP

Reviewed 2026-10-05. Source: Dr. Tanner's reply supplied by the user in this conversation. The correspondence's actual send date was not supplied; it is an author clarification, not a new experiment or independently authenticated publication. This note updates research/validation planning only. [ADR-016](../adr/016-defence-scope-and-no-recording.md) remains the current scope: coarse summaries first, no new saved classroom recordings, and five-way detection deferred.

## What the reply establishes

Asked whether DART's voice classes were validated as pedagogically meaningful, Tanner answered, “We did not.” She describes DART as a low-cost rolling noise-magnitude/variation measure, designed mainly to notice nonlecture activity in large undergraduate courses. She reports more errors with fewer students and background noise and stresses new-context validation and restrained claims. These statements do not give error rates for Ethiopian classrooms or a quantified reverberation result. In particular, the question mentioned concrete walls; the reply does not establish which reverberation conditions were tested.

DART remains a precedent for inexpensive acoustic measurement. Its reported classification accuracy is not evidence that the five proposed labels identify instructional quality, equitable participation, understanding or learning. Large early-grade enrolment does not make a classroom equivalent to a large university lecture: voice characteristics, response formats, room noise and mic placement differ.

## Keep three questions separate

| Question | Suitable evidence | What a positive result would support |
|---|---|---|
| Does the system measure its defined acoustic categories? | Lawful existing labelled-corpus tests, plus independent live aggregate reference agreement and supported-condition checks | Acoustic performance in the tested conditions; live totals cannot establish frame F1 |
| Do the summaries correspond to relevant opportunities in this lesson context? | Independently coded lesson activity and teacher interpretation, including cases with similar sound but different activity | A limited, context-specific association, if supported; never assumed from acoustic accuracy |
| Does using the aid improve teaching or learning? | A separately designed intervention/outcome study | Only the effects that study can identify; outside the current feasibility thesis |

A quiet interval may be reading, thinking, waiting or disengagement. Multiple voices may be coordinated response, productive group discussion or unrelated conversation. One voice may be explanation, reading, a short answer or playback. Teacher/child role attribution also needs evidence; volume and pitch alone cannot establish the speaker's role. These are counterexamples to test, not findings about the recruited classrooms. The existing `NON_SPEECH` class means no detected speech, not necessarily physical silence; capture loss stays `MISSING` metadata. Quiet or uncertain signals must not be silently converted into successful pedagogical classification.

## How EQUIP helps, and where it differs

The official [EQUIP about page](https://www.equip.ninja/about) identifies **Daniel Reinholz and Niral Shah** (correcting the names in the supplied reply). [EQUIP's guide](https://www.equip.ninja/help) describes human coding of contributions, discourse dimensions and student-associated participation. This supplies contextual information that an acoustic label does not contain. Use it to inform an observer guide, not as proof that the microphone measures equity.

The [EQUIP stability study](https://danielreinholz.com/wp-content/uploads/2022/11/2021-SNSS-EQUIP-Stability.pdf), Reinholz, Pelaez and Shah (2021), studied 20 undergraduate mathematics instructors. Observation requirements varied by code; some needed nearly 20 observations. Therefore neither one lesson nor a one-week pilot automatically represents a teacher's usual practice. This is not a universal minimum for Dimts; the local sample needs its own precision/stability assessment.

Full EQUIP usage relies on student-linked information. The proposed project adaptation retains only class-level numeric context totals, with no student roster, demographic fields, identities or utterance notes. Such an adaptation is **EQUIP-informed**, not the validated EQUIP instrument. No EQUIP account, integration or participant-data upload is added. Review any future use separately against the project data boundary.

## Changes to the proposed validation procedure

1. **Independent acoustic reference:** preserve A5's blinded observers, fixed common observation windows, paired-observer repeatability and held-out teacher/language separation. Observers label what they observe, not what the model suggests. Weak reference agreement leaves a measure unavailable.
2. **Independent activity context:** with local teachers/adviser, define a small non-evaluative guide for demonstration/explanation, individual response, coordinated group response, independent quiet work and other/uncertain activity. Apply only where observable and approved. Retain aggregate durations/counts and uncertainty; no words spoken, named students, fine timeline or recording. This is an optional observer form, not a new product classifier or equity score. Pilot the guide for agreement before association analysis.
3. **Counterexamples and confounds:** seek comparable acoustic totals across different observed activities. Check placement/distance, competing adult/playback, background noise, group size and visibly reverberant rooms where available. Record coarse room/placement categories, not an unmeasured SNR or RT60 claim. Add allowed synthetic/existing-fixture gain, noise and reverberation perturbations to P0 acoustic checks; these cannot substitute for local field evidence.
4. **Report failures explicitly:** stratify acoustic-reference discrepancies and unavailable coverage by observed conditions. An overall correlation can conceal systematic failure. Do not drop noisy sessions silently or tune on held-out teachers. The small pilot may reveal failures without estimating a reliable effect size for each room condition.
5. **Teacher interpretation:** present the supported summary with its context and unavailable states. Use participant-selected structured responses to check whether teachers mistakenly infer learning, universal talk-ratio targets or equitable distribution. No recorded interviews or speech-derived notes. Retain descriptive wording and teacher-selected practice cards; metrics do not diagnose a weakness.
6. **Release scope:** a successful system/capture test does not pass an acoustic-validity gate; acoustic agreement does not pass pedagogical or causal gates. The June plan may report technical feasibility and descriptive usefulness alone. No five-way field claim, automatic quality score, learning-impact claim or teacher ranking is added.

The proposed three-device, seven-day workload remains an engineering envelope. Sessions from the same device/teacher are repeated measurements, not additional independent teachers. Do not transfer a P1 undergraduate result into a P2 child-speech claim or pool cohorts to enlarge a sample. More contextual observation requires staffing/consent before collection, not a recording exception.

## Follow-up evidence still needed

Read the foundational EQUIP methods paper listed on its about page (Reinholz & Shah, 2018), and obtain local advice on which class-level observations can be made reliably within the no-recording protocol. Finalise supported room conditions, the observer guide and interpretation checks before held-out testing. Tanner's reply narrows the DART claim; it does not close these dependencies.
