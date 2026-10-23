Echo Project Synopsis

An offline mobile application for teacher self-reflection in Ethiopian primary schools

## Statement of the Problem

Effective early-grade teaching requires teachers to check understanding, respond to misconceptions and guide foundational literacy practice. Research in Hawassa identifies gaps in teachers’ knowledge of basic reading instruction (Yisihak and Damtew, 2024). A study of Grade 1 Koorete instruction also reports limitations in phonological-awareness knowledge and classroom practice (Haile and Mendisu, 2023). These findings indicate a need for practical teacher support, although they do not establish the current performance of the proposed pilot participants or represent all Ethiopian classrooms.

Echo targets Grades 1–4 teachers working with approximately 40–70 pupils in Hawassa. Large classes, limited opportunities for individualized coaching, unreliable connectivity and low-capability phones constrain regular reflection. Teachers need feedback that is practical, understandable in their preferred language and easy to obtain without interrupting lessons.

Echo will support private weekly reflection using classroom audio recorded on the teacher’s phone, teacher-provided context and evidence-informed visual guidance. Reviews will suggest one manageable teaching strategy to try the following week. Audio alone cannot establish student understanding or instructional quality; interpretations must remain open to teacher correction and local validation. The project will investigate the feasibility and usefulness of this approach rather than assume that automated feedback improves learning.

## Objectives

### General Objective

To develop and evaluate an offline, privacy-preserving Android application that supports Ethiopian primary school teachers in improving their pedagogical practice through weekly classroom-audio reflection and practical, evidence-informed coaching.

### Specific Objectives

Identify and locally validate useful coaching priorities, particularly checking understanding, explicit foundational literacy instruction and eliciting student explanations.

Implement reliable recording with an external microphone, encrypted local storage, efficient processing and a simple Record–Stop workflow for low-capability Android phones.

Provide weekly visual reviews lasting 2–10 minutes, with practical strategies, teacher corrections and reviewed content in Amharic, English, Afaan Oromo and Sidaamu Afoo.

Support separately consented, minimized research-summary transfer to Ethiopian hosting, with access control, deletion and a rolling 30-day retention limit for collected app data.

Assess technical feasibility, teacher adoption, coaching usefulness and reported or observed changes in teaching during a one-month pilot involving eight teachers in four Hawassa schools.

## Research Methodology

### Research Design

A design-science approach will guide development, combined with a mixed-methods feasibility study. Eight teachers will be purposively recruited from four primary schools in Hawassa for a concurrent one-month pilot. Baseline and endline observations and interviews will be combined with weekly feedback and app-use records. The small sample and absence of a control group will limit conclusions to feasibility and exploratory changes in teaching, without establishing causal effects on student learning.

### Data Collection

Data will include classroom audio retained only on the device; class profiles covering grade, language and class-size band; teacher-selected goals and contextual corrections; weekly usefulness ratings; and baseline and endline interviews and structured observations. Recording will begin only after institutional ethics review, school authorization and the required teacher and child/guardian consent or assent arrangements. No child names or individual speaker identities will be added to structured records.

Research participation will require separate consent. Only allowlisted summaries will leave the phone; audio and free-text classroom notes will remain local. Pseudonymous records will have restricted access and expire within 30 days of collection. Aggregate disclosure will require review because eight participants can be identifiable. University specialists will be invited to validate coaching content and observation rubrics.

Analysis will combine descriptive statistics with thematic analysis of interviews and corrections. Targets are sustained use by more than 70% of teachers (at least six of eight) and positive ratings on more than 80% of submitted reviews, provisionally defined as 4 or 5 on a five-point scale. Response rates and denominators will be reported. A proposed two-week follow-up will assess continued adoption; observations and interviews will examine teaching changes.

### Development Methodology

Iterative, user-centered development will combine teacher consultation, prototyping, implementation, testing and revision. Each weekly review will connect an available observation, teacher interpretation and one next-week teaching experiment. Literature-informed strategies will be reviewed with Hawassa University education specialists and teacher educators. Automatic pedagogical inference will be introduced only after validation against human-coded examples in the relevant languages. Recording, recovery, retention, usability, microphone performance, battery use and memory consumption will be tested on representative 2 GB Android devices before classroom deployment.

### Tools and Technologies

The implementation uses Kotlin and Jetpack Compose for Android; AudioRecord and a foreground service for screen-off capture; MediaCodec AAC compression; and AES-GCM encryption with Android Keystore keys. JobScheduler supports deferred local processing and optional summary synchronization. A Python/Django backend with PostgreSQL and HTTPS provides consent-controlled intake and restricted research aggregates, intended for in-country hosting such as Zergaw. Git, Gradle, automated tests and physical-device trials support development. Reviewed offline language packs and a curated coaching library will minimize connectivity requirements. Validated multilingual pedagogical inference and production hosting remain development and deployment milestones.

Selected references: Yisihak and Damtew (2024), doi:10.1155/2024/5596229; Haile and Mendisu (2023), doi:10.1155/2023/9527369.
