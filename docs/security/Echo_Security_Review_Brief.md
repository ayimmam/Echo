Echo Security Design Review Brief

Prepared for the security reviewer | 17 October 2026

We seek a practical security design review of Echo before classroom deployment, prioritizing protection of children’s voices, teacher privacy and reliable deletion on inexpensive phones.

## Purpose and operating context

Echo is an Android app for private teacher self-reflection in Ethiopian Grades 1–4. The planned one-month Hawassa pilot involves eight teachers across four schools, with 40–70 pupils per class. Teachers use their own phones and a supplied external microphone: save class details, tap Record, teach, then Stop. A weekly 2–10 minute visual review provides practical coaching and accepts teacher corrections. Target phones have 2 GB RAM and intermittent or no internet; intended languages are Amharic, English, Afaan Oromo and Sidaamu Afoo.

## Data flow and trust boundaries

External microphone → Android capture → compressed, encrypted local audio → local playback and reflection. A separate, optional path sends explicitly submitted research summaries over HTTPS to an intended Ethiopian host such as Zergaw. Audio, excerpts, transcripts and private classroom notes are excluded from that transfer. Summaries contain coarse collection day, grade, language, class-size band, lesson counts/minutes, optional usefulness rating and enumerated coaching choices. They remain pseudonymous: stable enrollment and the small cohort can permit re-identification. Raw audio may contain spoken names or other identifying information.

## Current implementation proposed for review

The Kotlin/Compose app uses foreground microphone capture, bounded PCM buffers and approximately 30-second AAC chunks. AES-GCM authenticates encrypted files, including file identity, with a separate Android Keystore key per file. Audio, notes and the research queue use app-private storage excluded from backup; no plaintext audio file is deliberately written. Playback decrypts excerpts into RAM. Hardware-backed key protection has not been established on target phones.

Research transfer is disabled in the default build. Connected builds restrict requests to one approved HTTPS origin, use platform TLS validation and reject redirects. The Django/PostgreSQL service uses hashed, single-use enrollment codes, individual bearer credentials stored hashed server-side, strict field validation, request limits and transactional withdrawal. Researchers receive a permission-controlled aggregate view, with a minimum of five contributors and no individual export. This does not establish anonymity.

Collected app data has a rolling 30-day retention limit; class settings persist separately. Expired data is blocked on access, with scheduled physical cleanup. Phone shutdown or force-stop can delay deletion. Withdrawal clears queued summaries and retries server revocation; remote deletion requires connectivity. Backend cleanup runs every 15 minutes. Application backups exclude research records, but provider snapshots, PostgreSQL WAL, replicas and logs still require verified deletion controls.

## Advice requested and unresolved risks

Please recommend a threat model and prioritized design changes covering lost/shared/unlocked phones, rooted devices, app access controls, key lifecycle and memory exposure; enrollment recovery, credential theft and administrator compromise; consent, withdrawal and re-identification; and retention across devices, logs and backups. We also need advice on hosting hardening, database protection, audit logging without sensitive content, incident response and security acceptance tests. Please distinguish minimum pilot safeguards from later improvements, considering offline use, battery and memory limits.

Status: development implementation with automated tests, not independently security-audited or field-approved. Production hosting, physical-device security and deletion behavior remain unvalidated. No external AI/translation calls or validated automated pedagogy model are active.
