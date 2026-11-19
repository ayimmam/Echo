from pathlib import Path
from io import BytesIO
from zipfile import ZipFile

from docx import Document
from docx.shared import Inches, Pt

import build_echo_chapter_one_advisor_revision as base


ROOT = Path('/Users/anteneh/Documents/Repos/Echo')
SOURCE = ROOT / 'Echo Chapter One Advisor Revision.docx'
OUTPUT = ROOT / 'Echo Chapters One and Two.docx'


def insert_before(anchor, text, level=None, page_break=False):
    p = anchor.insert_paragraph_before()
    if level is None:
        p.style = 'Normal'
        p.paragraph_format.first_line_indent = Inches(0.28)
        p.paragraph_format.alignment = 3
        p.paragraph_format.line_spacing = 1.18
        p.paragraph_format.space_after = Pt(6)
        base.set_run_font(p.add_run(text), 11.5)
    else:
        p.style = f'Heading {level}'
        p.paragraph_format.keep_with_next = True
        p.paragraph_format.space_before = Pt(10 if level == 1 else 7)
        p.paragraph_format.space_after = Pt(5)
        base.set_run_font(p.add_run(text), 14 if level == 1 else 12.5, bold=True)
    if page_break:
        p.paragraph_format.page_break_before = True
    return p


def main():
    doc = Document(SOURCE)
    refs = next(p for p in doc.paragraphs if p.text == 'REFERENCES')
    chapter_title = insert_before(refs, 'CHAPTER TWO DESCRIPTION OF THE EXISTING SYSTEM', 1, True)
    chapter_title.alignment = 1
    for run in chapter_title.runs:
        base.set_run_font(run, 18, bold=True)

    sections = [
        ('2.1 Introduction', 1),
        ('This chapter describes teacher assessment and feedback approaches that inform Echo and identifies the local information still needed before the system requirements can be approved. Published descriptions of Ethiopian formative assessment and comparable classroom observation tools are secondary evidence. They do not establish the exact process used in the four intended Hawassa schools. The school workflow, forms, reviewer comments, and review frequency will be verified through authorized interviews and document review.', None),

        ('2.2 Overview of the Existing System', 1),
        ('The in-person interview notes from Diaspora Elementary School describe several connected paper-based and face-to-face practices [14]. Each class has a supervisor who follows attendance and learner observations. The academic vice principal gives weekly live review and feedback. A team comprising the academic vice principal, director, and department head conducts classroom observations for teachers one to three times a year. The notes also describe same-subject peer comments, monthly discussions among teachers and departments, school-led continuing professional development, and a quarterly report to the Sidama education board. These are reported practices at the interviewed school, not a verified schedule for every Hawassa school.', None),
        ('Figure 2.1 Photograph of a paper classroom observation record from Diaspora Elementary School', None),
        ('The photographed tool is a handwritten classroom observation record with notes and signatures. It demonstrates that review evidence can be captured on paper and approved by people involved in the process. The interview notes indicate that reviewers consider lesson-plan objectives and written comments. The exact fields, wording, authorship, and retention practice of the photographed page require confirmation with the school before being converted into system requirements.', None),
        ('2.3 Existing Teacher Assessment and Feedback Practices', 1),
        ('Teacher support can involve an observer visiting a lesson, recording evidence against a form or rubric, discussing strengths and areas for improvement with the teacher, and agreeing on a follow-up action. The World Bank Teach Primary tool is one structured example: trained observers collect evidence on classroom culture, instruction, socioemotional skills, time on task, and aspects of the learning environment. It can support professional development, but its authors caution against high-stakes teacher evaluation [10, 13]. This is a reference process, not a claim that participating Hawassa schools use Teach Primary.', None),
        ('Ethiopia’s Assessment for Learning initiative focuses on continuous checks of pupil progress and using the results to adapt teaching [5]. That learner-assessment process is related to, but different from, an observation or coaching review of the teacher. Echo must distinguish a teacher’s own lesson reflection, feedback from a human reviewer, and evidence about pupil learning. The application cannot infer the third from classroom audio alone.', None),
        ('2.3.1 Reviewers, Records, and Feedback', 2),
        ('The requirements study will identify which roles actually review a teacher in each school, such as school leaders, department heads, pedagogical supervisors, mentors, or peers. With school approval, the team will examine blank forms and anonymized completed examples. For each review it will record the purpose, lesson and grade context, observation method, criteria, evidence notes, ratings if present, written strengths, specific improvement comments, the teacher’s response, agreed action, reviewer and teacher sign-off, and the place where records are stored. These fields are a study checklist; they are not asserted to be present on existing school forms.', None),
        ('The team will also document whether feedback is spoken, written, entered into a digital system, or delivered in a later meeting; when the teacher receives it; whether the teacher can correct factual errors; and how the next review checks the agreed action. A review form may preserve the reviewer’s judgment but may not give the teacher a private, repeatable way to listen back to the lesson. Conversely, a recording cannot capture visual behavior, learning materials, or classroom relationships that an observer can see. The design should preserve both sources of context.', None),
        ('2.3.2 Frequency and Follow-up', 2),
        ('The Diaspora Elementary School interview notes report weekly live feedback from the academic vice principal, formal in-class observations one to three times a year, and monthly meetings [14]. These different cadences appear to serve different purposes and require confirmation with the school. The team will ask each additional school for its scheduled and actual frequency, duration of observation, time to feedback, and follow-up interval. Results will be reported by school and reviewer role with a stated period and denominator. The World Bank’s coaching guidance emphasizes observation followed by tailored feedback and ongoing support [12].', None),

        ('2.4 Existing Tools and Comparable Systems', 1),
        ('2.4.1 Paper Observation and Coaching Records', 2),
        ('Paper or locally stored forms can give reviewers a clear rubric, space for evidence, and a signed record of discussion. Their value depends on observer preparation, fair criteria, and a useful feedback conversation. Teach Primary provides a detailed observer manual and training resources, showing why a scoring form by itself is not a reliable automated judgment [10, 13]. The Echo team will therefore treat human review forms as requirements evidence and as a possible companion to the app, not as a template to copy without local authorization.', None),
        ('2.4.2 General Audio Recording and Messaging Tools', 2),
        ('A phone recorder can retain a lesson for later listening, and a messaging tool can exchange comments. Neither is automatically a teacher-review workflow with class context, microphone readiness, retention rules, private reflection, and a visible support route. The actual apps and channels used in the selected schools must be identified in the field study. The design will ask teachers which steps are useful and which create extra work, since merely adding a second record can make feedback less practical.', None),
        ('2.4.3 Classroom Audio Feedback Services', 2),
        ('TeachFX describes a service in which teachers record classroom audio and receive private feedback about classroom talk patterns and possible instructional moves [11]. It illustrates the appeal of audio-supported reflection. Its published product description does not establish suitability for Echo’s target languages, offline use, low-memory phones, local retention policy, or approved Ethiopian classroom data handling. These are evaluation questions rather than deficiencies established by this project. Echo’s current build, in contrast, provides local recording and saved-duration information; it has no released speech recognition, speaker identification, or validated teaching-quality metric.', None),

        ('2.5 Strengths and Limitations of the Existing System', 1),
        ('2.5.1 Strengths of the Existing System', 2),
        ('The Diaspora Elementary School notes show several strengths: an assigned class supervisor, live weekly feedback, formal observation by school leaders, peer comments, monthly discussion, and school-provided professional development [14]. The paper record preserves written observations and signatures. These practices give teachers access to human judgment and a route for collective discussion, which the proposed software should complement.', None),
        ('2.5.2 Limitations of the Existing System', 2),
        ('The same notes suggest areas to examine further: formal observations occur only one to three times a year, and feedback based on a teacher’s own motivation may be informal [14]. Paper records may be difficult for a teacher to revisit alongside the actual lesson. The notes also describe large pupil numbers across shifts as a challenge for detailed learner assessment. These are interview-reported constraints, not measured failure rates. In-person interviews at other private and international schools in Hawassa will test which limitations are shared and which are specific to this school.', None),
        ('2.5.3 Comparative Analysis', 2),
        ('Table 2.1 compares the purpose and evidence boundaries of the approaches. “To verify” denotes school-specific facts that the team has not yet collected.', None),
    ]
    for text, level in sections:
        insert_before(refs, text, level)

    source_photo_caption = next(p for p in doc.paragraphs if p.text.startswith('Figure 2.1 Photograph'))
    with ZipFile('/Users/anteneh/Downloads/Diaspora Elementary Data (2).docx') as archive:
        photo_bytes = archive.read('word/media/image1.jpg')
    picture = doc.add_paragraph()
    picture.alignment = 1
    picture.paragraph_format.keep_with_next = True
    shape = picture.add_run().add_picture(BytesIO(photo_bytes), width=Inches(3.55))
    shape._inline.docPr.set('descr', 'Photograph of handwritten classroom observation record supplied by Diaspora Elementary School')
    shape._inline.docPr.set('title', 'Diaspora Elementary School paper observation record')
    source_photo_caption._p.addprevious(picture._p)
    source_photo_caption.alignment = 1
    source_photo_caption.paragraph_format.first_line_indent = Inches(0)
    for run in source_photo_caption.runs:
        run.italic = True

    caption = insert_before(refs, 'Table 2.1 Comparison of Existing Approaches', None)
    caption.paragraph_format.first_line_indent = Inches(0)
    caption.alignment = 1
    caption.paragraph_format.keep_with_next = True
    for run in caption.runs:
        run.italic = True
    table = base.add_table(doc,
        ['Approach', 'Useful capability', 'Boundary for Echo'],
        [
            ('School review form', 'Human observation, written comments, action discussion', 'Local content, reviewers, frequency, and access rules to verify'),
            ('Assessment for Learning', 'Continuous checks of pupil progress [5]', 'Pupil assessment is distinct from teacher audio reflection'),
            ('Teach Primary', 'Structured observation and trained reviewer framework [10, 13]', 'Not evidence of use in the selected schools; not an automated audio score'),
            ('TeachFX', 'Private classroom audio feedback about talk patterns [11]', 'Offline, language, hardware, and data-location fit need evaluation'),
            ('Echo development build', 'Offline encrypted recording, playback, duration, reflection', 'Pedagogical inference and field suitability remain unvalidated'),
        ], [Inches(1.3), Inches(2.45), Inches(2.75)], font_size=9.4)
    refs._p.addprevious(table._tbl)

    closing = [
        ('2.6 Proposed System', 1),
        ('Echo (Classroom Audio Insight Software) is planned as a two-tier system: an offline Android device tier for teacher-controlled recording and reflection, and an optional research-server tier for separately authorized, limited summaries. The school’s human review process remains outside the software tiers and can use teacher-shared information in its own feedback conversations. Table 2.2 shows the proposed tier boundary.', None),
        ('2.6.1 Proposed Tier Architecture', 2),
        ('Echo is planned as a support tool for a teacher’s own reflection. The current Android development build permits explicit Record and Stop, encrypted local chunks with a 30-day limit, private playback, weekly reflection, and saved-duration bars. Practice cards are draft selections based on teacher-entered goals and subject, not audio-derived recommendations. A separate, optional research path can send constrained numeric or enumerated summaries when authorized, while the default build has no configured server. Proposed additions include clearer microphone and interruption states, a reviewed teacher flow, validated capture-quality indicators, a configurable Help and Support contact, and a tested user manual. The app will not score or rank teachers or replace human coaching.', None),
        ('A useful future workflow would let a teacher prepare a class profile, check the microphone route, record by choice, listen privately, correct context, write one reflection and action, and request support if needed. A reviewer would continue to use the school’s authorized observation process and discuss any summary the teacher voluntarily shares. The requirements study will decide the exact handoff, access, and follow-up rules; the project will not assume that a reviewer can access private recordings.', None),
        ('2.6.2 Proposed Workflow', 2),
        ('The proposed workflow starts with teacher setup and a microphone check. The teacher explicitly starts and stops a lesson recording, reviews it privately, corrects missing context, and writes a reflection or selects a practice goal. Available duration and capture-quality information is shown with its limits. The teacher may choose to discuss a summary with a human reviewer; the reviewer does not gain automatic access to private audio. If a separate study is authorized and the teacher consents, an allowlisted summary may enter the encrypted outbox and be sent to the research server when connectivity returns. Deletion, expiry, and withdrawal take precedence over a queued transfer. The user manual and Help and Support contact will explain each step.', None),
    ]
    for text, level in closing:
        insert_before(refs, text, level)

    architecture_heading = next(p for p in doc.paragraphs if p.text == '2.6.1 Proposed Tier Architecture')
    architecture_caption = doc.add_paragraph('Table 2.2 Proposed Two Tier Architecture')
    architecture_caption.alignment = 1
    architecture_caption.paragraph_format.keep_with_next = True
    for run in architecture_caption.runs:
        base.set_run_font(run, 11, italic=True)
    architecture_heading._p.addnext(architecture_caption._p)
    architecture = base.add_table(doc, ['Component', 'Planned responsibility', 'Data boundary'], [
        ('Android device', 'Record and stop; encrypt local audio; private playback, reflection, expiry, and bounded on-device analysis', 'Raw audio and private notes remain on the teacher’s phone'),
        ('Optional research server', 'Django and PostgreSQL receive consent-bound, allowlisted numeric or enumerated summaries and provide restricted aggregates', 'No raw audio, excerpts, transcripts, child identities, or private notes'),
        ('Human review outside tiers', 'Observers and teachers discuss authorized observations and any summary a teacher chooses to share', 'No automatic reviewer access to private audio'),
    ], [Inches(1.25), Inches(2.8), Inches(2.45)], font_size=9.2)
    architecture_caption._p.addnext(architecture._tbl)
    next(p for p in doc.paragraphs if p.text.startswith('Echo is planned as a support tool')).paragraph_format.space_before = Pt(8)

    refs.paragraph_format.page_break_before = True
    toc_ref = next(p for p in doc.paragraphs if p.text.strip().startswith('REFERENCES\t'))
    toc_chapter_two = [
        ('CHAPTER TWO DESCRIPTION OF THE EXISTING SYSTEM', '10'),
        ('2.1 Introduction', '10'),
        ('2.2 Overview of the Existing System', '10'),
        ('2.3 Existing Teacher Assessment and Feedback Practices', '11'),
        ('2.4 Existing Tools and Comparable Systems', '12'),
        ('2.5 Strengths and Limitations of the Existing System', '13'),
        ('2.6 Proposed System', '14'),
    ]
    for label, page in toc_chapter_two:
        p = toc_ref.insert_paragraph_before()
        p.paragraph_format.space_after = Pt(2)
        p.paragraph_format.left_indent = Inches(0 if label.startswith('CHAPTER') else 0.18)
        base.set_run_font(p.add_run(label), 10.5, bold=label.startswith('CHAPTER'))
        p.paragraph_format.tab_stops.add_tab_stop(Inches(6.2))
        p.add_run('\t')
        base.set_run_font(p.add_run(page), 10.5, bold=label.startswith('CHAPTER'))
    for run in toc_ref.runs:
        if run.text == '9':
            run.text = '16'
    for p in doc.paragraphs:
        if p.text == 'Table 1.2 Detailed Project Cost Estimate':
            insert = doc.add_paragraph('Figure 2.1 Diaspora Elementary School Paper Observation Record')
            p._p.addnext(insert._p)
            insert.paragraph_format.space_after = Pt(3)
            base.set_run_font(insert.runs[0], 11.2, bold=True)
            insert2 = doc.add_paragraph('Table 2.1 Comparison of Existing Approaches')
            insert._p.addnext(insert2._p)
            base.set_run_font(insert2.runs[0], 11.2, bold=True)
            insert3 = doc.add_paragraph('Table 2.2 Proposed Two Tier Architecture')
            insert2._p.addnext(insert3._p)
            base.set_run_font(insert3.runs[0], 11.2, bold=True)
            break

    extra_refs = [
        (10, 'World Bank, “Teach Primary: Helping Countries to Measure Effective Teaching Practices,” Aug. 30, 2022.', 'World Bank Teach Primary', 'https://www.worldbank.org/en/brief/2022/08/30/teach-helping-countries-track-and-improve-teaching-quality'),
        (11, 'TeachFX, “TeachFX,” product description, accessed Nov. 11, 2026.', 'TeachFX product site', 'https://teachfx.com/'),
        (12, 'World Bank, “8 Tips to Structure Effective One-to-One Support Systems for Teachers,” accessed Nov. 11, 2026.', 'World Bank teacher support guidance', 'https://blogs.worldbank.org/en/education/8-tips-structure-effective-one-one-support-systems-teachers'),
        (13, 'World Bank, Teach Primary Observer Manual, second edition, 2021.', 'Teach Primary observer manual', 'https://documents1.worldbank.org/curated/en/872291641201520569/pdf/Teach-Primary-Observer-Manual.pdf'),
    ]
    for item in extra_refs:
        base.add_reference(doc, *item)
    p = doc.add_paragraph()
    p.paragraph_format.left_indent = Inches(0.35)
    p.paragraph_format.first_line_indent = Inches(-0.35)
    p.paragraph_format.space_after = Pt(7)
    base.set_run_font(p.add_run('[14] Echo project team, “Diaspora Elementary School interview notes and photographed classroom observation record,” unpublished field material, supplied Nov. 2026.'), 11)
    doc.core_properties.title = 'Echo Chapters One and Two'
    doc.core_properties.subject = 'Introduction and description of the existing system'
    doc.save(OUTPUT)
    print(OUTPUT)


if __name__ == '__main__':
    main()
