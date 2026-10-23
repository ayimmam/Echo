from pathlib import Path
import re, json, math, html
from reportlab.pdfgen import canvas
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle, PageBreak, Flowable, KeepTogether
from reportlab.lib import colors
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.enums import TA_LEFT
from reportlab.pdfbase import pdfmetrics
from reportlab.pdfbase.ttfonts import TTFont

ROOT = Path('/Users/anteneh/Documents/Repos/Echo')
OUT = ROOT / 'output/pdf/echo_pedagogy_evidence_and_offline_feasibility_report.pdf'
OUT.parent.mkdir(parents=True, exist_ok=True)
FONTDIR = Path('/System/Library/Fonts/Supplemental')
for name, file in [('Body','Arial.ttf'),('Bold','Arial Bold.ttf'),('Italic','Arial Italic.ttf'),('BoldItalic','Arial Bold Italic.ttf')]:
    pdfmetrics.registerFont(TTFont(name, str(FONTDIR/file)))
pdfmetrics.registerFontFamily('Body', normal='Body', bold='Bold', italic='Italic', boldItalic='BoldItalic')
NAVY=colors.HexColor('#14304A'); TEAL=colors.HexColor('#087F83'); GOLD=colors.HexColor('#B46C17')
INK=colors.HexColor('#233645'); MUTED=colors.HexColor('#526574'); LIGHT=colors.HexColor('#EDF5F5')
LINE=colors.HexColor('#D7E1E7'); WHITE=colors.white
W,H=595.276,841.89
CW=W-100
styles=getSampleStyleSheet()
styles.add(ParagraphStyle(name='Text',fontName='Body',fontSize=10.2,leading=14.6,textColor=INK,spaceAfter=8))
styles.add(ParagraphStyle(name='SmallText',parent=styles['Text'],fontSize=8.6,leading=11.6,spaceAfter=6))
styles.add(ParagraphStyle(name='TitleText',fontName='Bold',fontSize=24,leading=28,textColor=NAVY,spaceAfter=15))
styles.add(ParagraphStyle(name='SectionTitle',fontName='Bold',fontSize=19,leading=23,textColor=NAVY,spaceAfter=13))
styles.add(ParagraphStyle(name='Sub',fontName='Bold',fontSize=11.4,leading=15,textColor=TEAL,spaceBefore=6,spaceAfter=6))
styles.add(ParagraphStyle(name='Caption',fontName='Italic',fontSize=8.6,leading=11.6,textColor=MUTED,spaceBefore=5,spaceAfter=10))
styles.add(ParagraphStyle(name='Cell',fontName='Body',fontSize=9,leading=12.3,textColor=INK,spaceAfter=0))
styles.add(ParagraphStyle(name='CellHead',fontName='Bold',fontSize=9,leading=12.3,textColor=WHITE))
styles.add(ParagraphStyle(name='Ref',fontName='Body',fontSize=9,leading=12.4,textColor=INK,spaceAfter=10,leftIndent=15,firstLineIndent=-15,splitLongWords=True))
story=[]
section_pages=[]

def P(text,style='Text'):
    return Paragraph(text,styles[style])
def p(text,style='Text'): story.append(P(text,style))
def sub(text): p(text,'Sub')
def page(num,title,kicker=None):
    if story: story.append(PageBreak())
    p((kicker or 'RESEARCH AND IMPLEMENTATION REPORT').upper(),'Caption')
    heading=P(f'{num:02d}  {title}','SectionTitle'); heading.section_title=title; story.append(heading)
def box(text):
    t=Table([[P(text)]],colWidths=[CW]); t.setStyle(TableStyle([('BACKGROUND',(0,0),(-1,-1),LIGHT),('BOX',(0,0),(-1,-1),.7,LINE),('LEFTPADDING',(0,0),(-1,-1),12),('RIGHTPADDING',(0,0),(-1,-1),12),('TOPPADDING',(0,0),(-1,-1),10),('BOTTOMPADDING',(0,0),(-1,-1),5)]));story.append(t);story.append(Spacer(1,9))
def table(headers,rows,widths=None):
    widths=[CW*x for x in widths] if widths else [CW/len(headers)]*len(headers)
    data=[[P(x,'CellHead') for x in headers]]+[[P(str(x),'Cell') for x in row] for row in rows]
    t=Table(data,colWidths=widths,repeatRows=1,hAlign='LEFT')
    t.setStyle(TableStyle([('BACKGROUND',(0,0),(-1,0),NAVY),('ROWBACKGROUNDS',(0,1),(-1,-1),[WHITE,colors.HexColor('#F3F6F8')]),('VALIGN',(0,0),(-1,-1),'TOP'),('LEFTPADDING',(0,0),(-1,-1),8),('RIGHTPADDING',(0,0),(-1,-1),8),('TOPPADDING',(0,0),(-1,-1),8),('BOTTOMPADDING',(0,0),(-1,-1),8),('LINEBELOW',(0,0),(-1,0),.7,NAVY),('LINEBELOW',(0,1),(-1,-1),.4,LINE)]))
    story.append(t);story.append(Spacer(1,10))

class Visual(Flowable):
    def __init__(self,kind,height=240): Flowable.__init__(self);self.kind=kind;self.width=CW;self.height=height
    def draw(self):
        c=self.canv
        def rect(x,y,w,h,title,body='',fill=LIGHT):
            c.setFillColor(fill);c.setStrokeColor(LINE);c.roundRect(x,y,w,h,7,fill=1,stroke=1)
            q=P(title,'Sub');qw,qh=q.wrap(w-18,h);q.drawOn(c,x+9,y+h-qh-9)
            if body:
                q=P(body,'SmallText');qw,qh=q.wrap(w-18,h);q.drawOn(c,x+9,y+h-qh-33)
        def arrow(x1,y1,x2,y2,color=TEAL):
            c.setStrokeColor(color);c.setFillColor(color);c.setLineWidth(1.4);c.line(x1,y1,x2,y2)
            ang=math.atan2(y2-y1,x2-x1);a=5
            q=c.beginPath();q.moveTo(x2,y2);q.lineTo(x2-a*math.cos(ang-.5),y2-a*math.sin(ang-.5));q.lineTo(x2-a*math.cos(ang+.5),y2-a*math.sin(ang+.5));q.close();c.drawPath(q,fill=1,stroke=0)
        if self.kind=='theory':
            ys=[184,124,64,4]
            data=[('1. Capture a usable lesson','Device tests + observed audibility'),('2. Notice a trustworthy pattern','Held-out indicator agreement + coverage'),('3. Try a relevant teaching action','Teacher interpretation + reviewed practice'),('4. Evaluate what changed','Independent observation; learning is a later study')]
            for i,((title,body),y) in enumerate(zip(data,ys)):
                rect(0,y,CW-110,51,title,body)
                q=P(['Engineering','Measurement','Pedagogy','Outcomes'][i],'SmallText');q.wrap(95,40);q.drawOn(c,CW-95,y+18)
                if i<3:arrow((CW-110)/2,y,(CW-110)/2,y-9)
        elif self.kind=='pipeline':
            c.translate(0,16)
            rect(0,204,CW,53,'Encrypted audio on the phone','Decode one bounded window; preserve capture gaps')
            arrow(CW/2,204,CW/2,195)
            c.setStrokeColor(TEAL);c.line(119,195,376,195)
            arrow(119,195,119,185);arrow(376,195,376,185)
            rect(0,128,238,57,'Acoustic inference','VAD; adult/child/overlap; uncertainty')
            rect(257,128,238,57,'Optional semantic branch','Short RAM-only ASR; restricted experiment')
            arrow(119,128,119,111);arrow(376,128,376,111)
            rect(0,53,CW,57,'Metric-specific evidence gate','Context + provenance + validated scope + sufficient coverage')
            arrow(CW/2,53,CW/2,37)
            rect(0,-16,CW,52,'Offline reflection','Teacher goal + approved card + transparent wording')
        elif self.kind=='budget':
            vals=[('10-second PCM window',.32),('30-second PCM window',.96),('One hour AAC, 32 kbit/s',14.4),('One hour PCM',115.2)]
            c.setFont('Body',9);c.setFillColor(MUTED);c.drawString(0,self.height-12,'Illustrative payload sizes (decimal MB); not whole-app memory')
            for i,(label,value) in enumerate(vals):
                y=self.height-47-i*43
                c.setFillColor(INK);c.drawString(0,y+14,label)
                c.setFillColor(TEAL);c.roundRect(0,y,max(2,value/115.2*410),8,3,fill=1,stroke=0)
                c.setFillColor(INK);c.drawRightString(CW,y,f'{value:g} MB')
        elif self.kind=='pilot':
            labels=[('BEFORE','Readiness','Phones, consent,<br/>reviewed cards'),('WEEK 1','Baseline','Observe + choose<br/>a teaching goal'),('WEEKS 2-3','Practice','One action; weekly<br/>reflection'),('WEEK 4','Endline','Observe, interview,<br/>decide next stage')]
            width=(CW-30)/4
            for i,(time,title,body) in enumerate(labels):
                x=i*(width+10);rect(x,45,width,110,title,body)
                c.setFillColor(TEAL);c.setFont('Bold',9);c.drawString(x,169,time)
                if i<3:arrow(x+width,100,x+width+10,100)
            c.setFillColor(MUTED);c.setFont('Body',9);c.drawString(0,18,'Optional weeks 5-6 follow-up needs separate funding and retention planning.')
        elif self.kind=='gates':
            items=[('G0','Permission + content','No classroom use before readiness'),('G1','Reliable recording','Reflection-only remains possible'),('G2','Validated indicators','Only supported metrics appear'),('G3','Meaningful use','Teachers interpret reports correctly'),('G4','Impact study','Separate, larger comparative design')]
            for i,(g,title,body) in enumerate(items):
                y=self.height-47-i*46
                c.setFillColor(TEAL);c.circle(17,y+19,15,fill=1,stroke=0);c.setFillColor(WHITE);c.setFont('Bold',9);c.drawCentredString(17,y+16,g)
                q=P(f'<b>{title}</b> - {body}','SmallText');q.wrap(CW-50,40);q.drawOn(c,45,y+10)
                if i<4:arrow(17,y+4,17,y-12)

class Doc(SimpleDocTemplate):
    def afterFlowable(self,flowable):
        if hasattr(flowable,'section_title'):
            section_pages.append((flowable.section_title,self.page))
            key=f'section{len(section_pages)}';self.canv.bookmarkPage(key);self.canv.addOutlineEntry(flowable.section_title,key,0)
def footer(c,doc):
    c.saveState();c.setStrokeColor(LINE);c.line(50,45,W-50,45)
    c.setFillColor(MUTED);c.setFont('Body',8);c.drawString(50,31,'ECHO  /  EVIDENCE, PEDAGOGY AND OFFLINE FEASIBILITY')
    c.drawRightString(W-50,31,str(doc.page));c.restoreState()

# Content is deliberately paginated into reviewable briefing chapters.
p('ECHO / ECHOVOLVE','Caption')
story.append(Spacer(1,22))
p('A defensible path from<br/>classroom audio to<br/>teacher reflection','TitleText')
p('Pedagogical evidence, offline Android feasibility,<br/>and a staged Hawassa pilot','Sub')
story.append(Spacer(1,18))
story.append(Visual('theory',245))
story.append(Spacer(1,15))
box('<b>Central recommendation</b><br/>Develop and evaluate a private, offline teacher-reflection intervention. Use validated acoustic observations to support a teacher-selected goal; use reviewed pedagogical content to suggest one practical experiment. Keep automated semantic diagnosis optional and experimental.')
p('<b>Prepared for:</b> university/thesis defense, pilot review and engineering decisions.<br/><b>Date:</b> 22 October 2026. <b>Review type:</b> targeted critical evidence and design review.<br/><b>Constraints:</b> 8 teachers; 4 Hawassa schools; Grades 1-4; four languages; 2 GB Android target; 20,000 ETB hardware ceiling.','SmallText')
p('Temporary on-device transcription is permitted for evaluation; transcripts must never be persisted. This report proposes changes, not field authorization, a validated model, or demonstrated learning gains. References appear in the final section.','SmallText')

page(1,'Executive judgment')
p('<b>Echo is defensible as an evidence-informed, locally evaluated reflection aid. It is not yet defensible as an automatic evaluator of teaching quality.</b> The thesis contribution can be the design and evaluation of an offline intervention that makes uncertainty visible, preserves teacher agency and measures feasibility in its actual setting.')
table(['Decision','Recommended position'],[
('Primary contribution','A transparent reflection workflow, valid measurement where possible, and a documented local feasibility study.'),
('First release','Reliable local recording, excerpt playback, reviewed multilingual practice cards, teacher goals and follow-up. Useful without any released acoustic metric.'),
('First model research','Speech detection and coarse adult/child/overlap timing. Release adult-speech share and alternation rate independently, only after local evidence.'),
('Deferred features','Automatic choral classification, language-share dashboards, question-quality judgments, individual-pupil measures and model-driven diagnoses.'),
('Summary generation','Deterministic selection and templates from approved content. A language model is not necessary for the pilot.'),
('Permitted thesis claim','Technical feasibility, indicator agreement in tested conditions, teacher comprehension and preliminary acceptability. Teaching change is exploratory; learning impact requires a later study.')],[.24,.76])
sub('Why this is a substantive contribution')
p('The difficult problem is the chain from an imperfect recording to a trustworthy decision. A prototype that handles missing evidence, stays useful offline and exposes failures can contribute more credible knowledge than a broad classifier with untested educational labels. Report which conditions worked, which failed and how those findings changed the product.')
sub('Read this report by decision')
p('Evidence and pedagogy: Sections 3-6. Indicators and architecture: Sections 7-11. Device and recommendation design: Sections 12-15. Validation and pilot: Sections 16-20. Delivery, governance and defense: Sections 21-24. All numerical acceptance targets are proposed project criteria unless explicitly described as published findings.')

page(2,'Scope, method and evidence status')
p('This is a targeted critical review, not a systematic review or meta-analysis. Searches and primary-source checks were conducted on 22 October 2026 using the repository research pack, journal/author records, education evidence organizations, official Android documentation and model/dataset cards. No new classroom experiment, model training, phone benchmark or statistical reanalysis was performed.')
p('Search themes included teacher coaching, automated classroom feedback, acoustic observation, code-switching language identification, Ethiopian speech resources, low-memory Android inference and evaluation of complex interventions. Older foundational studies were retained when directly relevant; newer sources were checked to correct outdated assumptions. No claim of exhaustive coverage or a PRISMA search count is made.')
table(['Evidence label used here','Interpretation'],[
('Published intervention or synthesis','Evidence about a studied intervention or broader mechanism; transfer to Echo requires justification.'),
('Measurement study','Evidence about agreement with labels or observations; does not establish learning impact.'),
('Official technical / dataset source','Documentation of capabilities, artifacts or resources; vendor or maintainer claims are not phone benchmarks.'),
('Project evidence','Repository implementation or documented test status; does not establish classroom suitability.'),
('Proposed design / target','Recommendations in this report, requiring local approval and evaluation; not empirical findings.')],[.30,.70])
sub('Source-access limits')
p('The supplied full texts of Yisihak and Damtew (2024), Haile and Mendisu (2023), Schlotterbeck et al. (2021) and Demszky and Liu (2023) support the local review. Some other findings rely on publisher abstracts, author summaries or official pages, identified in the reference audit. The 2025 literacy synthesis is represented by its publisher summary because the full PDF exceeded the retrieval limit. A June 2026 ASR preprint is treated as emerging evidence, not established product readiness.')
sub('Project baseline and authority')
p('The source review uses repository revision <b>b03b6ec</b> plus current local files, with README status dated 11 October 2026. Current ADR-019/020 take precedence over historical RAM-only and Grade-2-only plans. The user now permits temporary local transcription, without transcript storage, and changes to indicators or pilot design (Echo project, 2026).')

page(3,'Local need and the coaching mechanism')
sub('Local relevance is real, but narrower than a national diagnosis')
p('Yisihak and Damtew (2024) analyzed 30 English teachers in five Hawassa public primary schools using 2021/22 data. The mean knowledge-assessment score was 43.4%; 21 of 30 scored below 50%. This supports a locally relevant concern about preparation for early reading instruction. It does not diagnose the eight proposed participants, establish current prevalence, or show that a microphone-based intervention will remedy the concern.')
p('Haile and Mendisu (2023) identify challenges in teachers\' phonological-awareness knowledge and practice in Grade 1 Koorete teaching. The MTTCA report examines teacher competencies alongside broader contextual factors (American Institutes for Research [AIR], 2020). These sources support curriculum-linked practical support; they do not justify portraying teachers as the sole cause of weak attainment. Materials, language, class size and home resources can constrain what a teacher can change.')
sub('Coaching evidence supports a mechanism, not an app effect size')
p('Kraft et al. (2018) synthesized 60 causal studies of teacher coaching and reported pooled effects of 0.49 standard deviations on instruction and 0.18 on achievement. Much of the evidence involved early or elementary literacy, but effects were smaller in larger-scale programs. These are results for coaching interventions, often with substantial human support. Echo must not inherit these effect sizes or describe automated cards as equivalent to a coach.')
box('<b>Design implication:</b> preserve the active ingredients that a simple app can support: a clear goal, a concrete example, an opportunity to practice, reflection on evidence and a follow-up. Record the human support required. A research team visiting weekly is part of the intervention, not a free background condition.')
sub('The content should fit the lesson, language and curriculum')
p('The World Bank\'s (2025) summary of a GEEAP-endorsed literacy review emphasizes explicit, systematic instruction spanning decoding and language comprehension. Echo should help teachers enact curriculum-aligned practices. It should not turn the desirable balance between modeling and pupil practice into a universal teacher-talk target. English phonics examples cannot simply be translated into languages with different sound-writing relationships.')

page(4,'What automated feedback evidence proves')
table(['Study / source','Useful finding','Boundary for Echo'],[
('Demszky et al. (2025)','Randomized study of 224 Utah mathematics/science teachers; focusing questions increased by 20%. Feedback emails were opened about 53-65% of the time.','Supports testing targeted feedback and measuring engagement. Other practices did not improve; setting and semantic pipeline differ.'),
('Demszky & Liu (2023)','NLP feedback studied with 414 online mentors. The talk-share coefficient was -0.035 against a 0.722 control mean.','This is -3.5 percentage points, not -5 points. Bundled online feedback cannot establish the effect of an isolated acoustic gauge.'),
('Owens et al. (2017)','DART demonstrates classification of coarse classroom sound categories in college science courses.','A sound class is not an assessment of teaching quality; primary-school voices and activities require new validation.'),
('Schlotterbeck et al. (2021)','42 lessons from 19 teachers in Chile; smartphone/lavalier audio used for supervised activity classification.','Reported performance on selected activities is context-specific. Their processing time is not evidence for a 2 GB Android implementation.')],[.23,.37,.40])
sub('Do not merge different validation questions')
p('A classifier can reproduce an observer label while the label remains a poor measure of instructional quality. A summary can be accurate while teachers find it unhelpful. A useful report can influence a practice without improving learning. The causal chain must be tested at each link. The repository\'s Tanner correspondence reinforces this caution but is not treated here as an independently published validation result (Echo project, 2026).')
sub('Evidence that would change the recommendation')
p('Strong local agreement, stable capture of quiet children, clear teacher comprehension and low burden would justify carefully expanding metric use. Systematic failure to hear distant pupils, persistent misinterpretation of ratios, or high recording burden would favor a reflection-only or human-supported product. Those outcomes should be accepted as legitimate study results, not hidden behind a dashboard.')

page(5,'A theory of change with separate tests')
story.append(Visual('theory',245))
p('Figure 1. Proposed claim-and-evidence chain for Echo. This is an original project framework, not a validated measurement instrument. Each arrow is a hypothesis.','Caption')
p('Use the complex-intervention perspective of Skivington et al. (2021): make the program theory explicit, examine context and uncertainty, involve stakeholders and refine the intervention. Applying this framework to education is a methodological adaptation, not evidence of Echo\'s effectiveness.')
table(['Link','Mechanism and likely failure','Evidence required'],[
('Capture to observation','A mic makes lesson evidence available; distant children or noise can distort it.','Audibility checks and independent labeled reference, including failures.'),
('Observation to reflection','A teacher notices something relevant; a ratio may instead trigger shame or a false inference.','Think-aloud interpretation and counterexample tasks.'),
('Reflection to action','A manageable strategy supports practice; workload or materials can prevent use.','Teacher-selected action, reported barriers and independent observation.'),
('Action to outcomes','Better instruction may improve learning; curriculum, exposure and other conditions intervene.','Later comparative design with student outcomes and sufficient independent units.')],[.21,.43,.36])
p('A single-arm four-week pilot can evaluate the first links and describe preliminary practice change. It cannot isolate the causal effect of Echo from observation, novelty, researcher attention, concurrent training or changing lesson content.')

page(6,'Pedagogical framework and practice library')
p('A weekly interaction should move through <b>notice, interpret, choose, rehearse, try and revisit</b>. This proposed sequence is informed by professional-development guidance and focused coaching practice, then adapted for Echo (Education Endowment Foundation [EEF], 2021a; Wilichowski &amp; Arenge, 2022). It must be co-designed and tested locally.')
table(['Priority practice','A feasible classroom experiment','Evidence Echo can use'],[
('Check understanding','After modeling, use a fresh item; sample answers across the room; address an error; check again.','Teacher confirmation and independent observation. No inference of answer correctness from sound.'),
('Explicit early literacy','Model a curriculum-aligned sound/symbol or word skill, practice together, then ask for individual application.','Goal/subject metadata and reviewed local-language examples.'),
('Elicit reasoning','After an answer, ask for the reason or supporting part of a text.','Teacher-confirmed example or observer code. Acoustic alternations are only supporting context.'),
('Create response opportunities','Give suitable thinking time and a short partner response before inviting explanations.','Observer evidence; validated timing may describe exchanges but cannot certify thinking.'),
('Give usable feedback','Name the relevant step, indicate an actionable next step, then let the child retry.','Observation of feedback and pupil use. A praise count is insufficient.')],[.22,.42,.36])
p('These priorities are a design judgment, not an Ethiopian ranking of intervention effect sizes. EEF (2021b) addresses teacher-to-pupil feedback; it does not validate automated feedback to teachers. The local literacy studies and the World Bank (2025) summary motivate contextual content review, not acoustic diagnoses.')
sub('Each practice card needs an evidence record')
p('Store a stable ID, version, target skill, grade/subject range, language, lesson conditions, steps, example, source citation, evidence limitations, reviewer and review date. Include a reason the card may not fit. Allow teachers to choose, adapt or decline. Avoid optimizing for higher talk-switch counts or lower teacher speech; the goal is an appropriate teaching action.')
box('<b>Human review before release:</b> an education specialist checks the pedagogical rationale; a fluent teacher checks language and curriculum fit; pilot teachers check feasibility and clarity. Unreviewed machine translation must never be displayed as a finished language pack.')

page(7,'Indicator audit: timing is not pedagogy')
p('The historical eight-indicator specification is a useful starting point, but several names imply more than their signals establish. Rename measures before testing teacher comprehension. Version changed denominators, categories and thresholds explicitly (Echo project, 2026).')
table(['Existing measure','Recommended interpretation / disposition','Validation and limits'],[
('I-1 Teacher talk ratio','Rename <b>adult share of detected speech</b>. Permit a teacher interpretation only in confirmed single-adult, no-playback contexts. Candidate for conditional release.','Validate adult/child/overlap timing and distant-child audibility. Overlap remains separate; missing intervals are not silence. A high value is not a weakness.'),
('I-2 Response latency','Rename <b>adult-to-child transition gap</b>. Keep experimental. The original definition includes 0-10 s non-speech gaps between eligible turns.','Without recognizing a question, this is not question-specific wait time. A cutoff truncates the distribution; report eligible events and exclusions. Do not trigger a universal 3-second rule.'),
('I-3 Choral balance','Defer automatic release. If researched, call it <b>estimated coordinated-response share</b> and distinguish overlap/other group speech.','Requires human-labeled coordinated responses, including counterexamples. Synchrony alone cannot establish appropriate participation or learning.'),
('I-4 Individual responses','Rename <b>single-child speech segments</b>. Defer until a single-child class is independently valid.','Counts are not distinct pupils or correct answers. Segmentation thresholds change counts; repeated speech from one pupil cannot establish equity.')],[.23,.39,.38])
sub('Measurement error can reverse the interpretation')
p('A teacher-worn microphone may hear the teacher well and miss quiet or distant children. The result is an upward bias in estimated adult share, not merely random noise. More weeks of the same biased recording do not fix it. Background speech, another adult or recorded audio can also corrupt role attribution. A model score cannot certify that inaudible children were captured.')
p('Wang et al. (2025) report about 34% diarization error and 69.17% teacher-versus-child accuracy in challenging classroom data, even with an ASR-assisted system. This is a useful warning about task difficulty, not a baseline performance promise or a directly comparable target for Echo\'s simpler categories.')

page(8,'Indicator audit: coverage and language')
table(['Existing measure','Recommended interpretation / disposition','Validation and limits'],[
('I-5 Turn density','Rename <b>adult-child alternation rate</b>. Candidate for release independently of I-1.','Count events across uninterrupted observed spans; overlap and missingness break pairs. Validate event counts, not just speech-duration accuracy.'),
('I-6 Language shares','Defer automatic release. Let the teacher declare lesson languages; test language identification separately.','Short mixed utterances, borrowed words and unfamiliar languages need explicit uncertainty. Language proportions do not establish policy compliance or instructional value.'),
('I-7 Interaction coverage','Rename <b>time bins with a detected alternation</b>. Research-only derivative of I-5 initially.','Depends on bin width and boundaries. It does not measure class-wide participation or add independent validation to I-5.'),
('I-8 Long silence','Rename <b>sustained no-detected-speech intervals</b>. Use neutrally or for signal review, not a pedagogy alert.','A quiet signal can be reading, writing, a muted mic or missed speech. Near-zero amplitude is not the same as non-speech.')],[.23,.39,.38])
sub('Add indicators that directly serve feasibility')
table(['New field','Why it is more useful in the first pilot'],[
('Usable evidence coverage','Report observed duration, missing duration, uncertain labels and eligible lessons separately. Do not hide failed sessions.'),
('Reflection and action follow-through','Record whether a reflection was opened, an action chosen and later reportedly tried; distinguish self-report from observed practice.'),
('Burden and usefulness','Measure time, interruptions, charging burden, reasons for nonuse and teacher-level usefulness ratings.'),
('Teacher interpretation accuracy','Check whether a teacher mistakes an acoustic observation for understanding, quality, equity or a prescribed target.')],[.31,.69])
p('Do not combine the eight indicators into a teacher-quality score. Prefer one descriptive observation relevant to the chosen goal, alongside context and coverage. A failed acoustic pipeline should leave the reviewed goal-based reflection available.')

page(9,'Proposed offline system architecture')
story.append(Visual('pipeline',286))
p('Figure 2. Proposed data flow. The semantic branch is optional and unavailable by default. Neither raw audio nor transcript text enters a remote summary service.','Caption')
p('The existing app captures 16 kHz mono audio, encodes short AAC chunks and encrypts local files. Future models must be evaluated on the <b>actual decoded AAC path</b>, including interruptions and chunk boundaries, rather than on clean WAV files alone. The current local analysis records capture-signal statistics and explicitly reports that pedagogy is unavailable (Echo project, 2026).')
sub('Separate responsibilities')
p('<b>Capture:</b> preserve committed chunks and known gaps. <b>Inference:</b> produce tentative time labels plus uncertainty in bounded memory. <b>Metric code:</b> calculate versioned numerators, denominators and counts. <b>Release policy:</b> decide which metric is allowed in which context. <b>Reflection:</b> combine permitted evidence with teacher context and reviewed content. None of these layers should bypass the next layer\'s checks.')
sub('Make offline operation complete')
p('Bundle the model, tokenizer if used, content, language resources and release policy. Record, stop, listen, review, choose an action and delete must work without Internet, login or cloud inference. Optional authorized research transfer must be independent of local usefulness. Missing research connectivity must not block a report or extend retention.')

page(10,'Acoustic modeling and uncertainty')
sub('Start with baselines and a narrow label contract')
p('Compare a speech-activity baseline with a compact supervised classifier for adult speech, child speech, overlap and non-speech. Keep capture loss separate. Use bounded log-mel or similar spectral features; evaluate a small convolutional model before assuming a large pretrained encoder is necessary. Silero is a candidate VAD baseline, not an adult/child classifier. Its maintainer reports compact models and fast CPU inference; neither claim demonstrates performance on Echo\'s phones (Silero Team, n.d.).')
p('Training needs labeled examples and a permitted development corpus. Public single-speaker data can help learn speech representations but cannot by itself teach classroom overlap, role or coordinated response. Synthetic mixes and room perturbations are useful stress tests; they do not replace real held-out classroom evaluation. Do not train a new four-language pedagogy system from eight pilot teachers.')
sub('Keep arithmetic separate from prediction')
box('<b>Historical I-1:</b> exclusive adult duration / (adult + child + overlap speech duration).<br/><b>I-5:</b> eligible adult-child alternations / observed minutes.<br/><b>Coverage:</b> observed capture duration / intended recording duration.<br/>Freeze and test definitions in Python and Kotlin. Unknown role labels require an explicit new contract; never silently drop them from the denominator.')
p('For detected speech whose role is uncertain, report the uncertain fraction and withhold a ratio if necessary. A sensitivity analysis can assign uncertain duration to alternative classes and show how far the result moves. That analysis cannot bound speech the microphone never captured. Distinguish classification uncertainty, recording missingness and reference-annotation disagreement.')
sub('Release a metric, not a model confidence score')
p('Calibrate thresholds on development data, freeze them and evaluate on held-out teachers. Show accuracy and eligible coverage together: a system can appear accurate by rejecting difficult lessons. A softmax score is not a measured error interval. A metric may be usable for duration but unsuitable for turn counts because boundary errors accumulate. Quantization, changed microphones and altered preprocessing each require renewed end-to-end checks.')

page(11,'Multilingual speech and temporary ASR')
p('The limitation is not that relevant speech data are absent. Afrivoice Ethiopia includes Amharic, Afaan Oromo and Sidama among its languages. Its card lists roughly 603 hours of Sidama audio, about 225 transcribed hours; these are dataset-card totals, not classroom or child-speech coverage. WAXAL is another current resource, but configurations and licenses must be checked at the pinned subset level (Digital Umuganda, n.d.; Google, n.d.-d).')
p('Check speaker/recording overlap before combining corpora or using one to test a model trained on the other. Shared provenance can produce leakage even when dataset names differ. Audit age, dialect, recording conditions, label quality, licenses and permissions. A general speech corpus is not a labeled pedagogy dataset.')
table(['Task','Feasible research direction','Boundary'],[
('Speech timing','Evaluate across all intended languages without requiring word recognition.','No language-independent accuracy claim without stratified evidence.'),
('Language identification','Optional short-window model with unknown/mixed output; compare with fluent human judgment.','Window length trades temporal resolution for evidence. The historical 3-second proposal can miss brief switches.'),
('Temporary ASR','Short, teacher-selected, acoustically usable excerpts; only tested language/domain combinations.','Text and token buffers remain transient. No semantic coaching released without downstream validation.'),
('Semantic interpretation','Initially human-confirmed questions or lesson context.','An audio-language model still interprets private linguistic content even if no transcript is displayed.')],[.22,.40,.38])
p('Chua et al. (2023) provide a code-switching language-identification benchmark for English-Mandarin child-directed speech. It demonstrates the need for suitable mixed-language data, not transfer to Ethiopian languages. Olufemi et al. (2026), an emerging preprint, report 38.0% macro-average WER for specialized WAXAL models versus 64.9% for the best zero-shot baseline. Those corpus results justify investigation; they do not establish classroom semantic accuracy or low-end Android performance.')
p('If ASR is evaluated, report word and character errors where suitable, insertion/hallucination errors, mixed-language failures, and accuracy of the specific downstream task. Compute human-reference comparisons transiently on the phone and retain only permitted error totals. A single pooled WER cannot authorize pedagogical advice.')

page(12,'What fits on a 2 GB phone?')
p('Two gigabytes is total device RAM, not the app\'s allowance. Android, the UI, codecs, native allocations and other processes compete for memory. Use whole-process measurements and test the actual ABI/OS; the repository currently targets Android 10+ and arm64. A low RAM label alone does not establish compatibility (Google, n.d.-a; Echo project, 2026).')
story.append(Visual('budget',205))
p('Figure 3. Arithmetic payload estimates at 16 kHz mono, 16-bit PCM and 32 kbit/s AAC. PCM = samples/second x bytes/sample x seconds. AAC = bits/second x seconds / 8. Codec, encryption and filesystem overhead are excluded.','Caption')
table(['Design choice','Feasibility interpretation'],[
('Bounded buffers','10 s PCM is 0.32 MB; 30 s is 0.96 MB. Avoid loading or decoding an entire lesson at once. Float arrays and feature copies add memory.'),
('Compressed retention','32 kbit/s AAC is about 14.4 MB/hour; 30 one-hour sessions are about 432 MB before overhead. Scale by actual recorded hours, not calendar days.'),
('Small acoustic model','Propose an initial model-file target of 10 MB or less. This is a design budget, not a proven sufficient architecture or RAM estimate.'),
('Large LID / ASR','MMS-LID-256 has 1 billion parameters and a noncommercial license. It is not the default deployment candidate. Even 8-bit weights alone would be about 1 GB.'),
('Offline transcription','whisper.cpp lists about 273 MB memory for tiny and 388 MB for base in its reference table. These are maintainer figures, not Echo measurements or guarantees for the target languages.')],[.26,.74])
p('Model sizes and capabilities: Meta (n.d.); ggml-org (n.d.). Quantization can reduce model size, but operator support, accuracy, working memory and latency must be measured on the selected runtime (Google, n.d.-c).')

page(13,'Android execution and acceptance budget')
p('Capture should have priority over inference. Start microphone capture from an explicit visible action, use the required foreground-service configuration and provide a clear stop control. Android places restrictions on microphone foreground-service starts and background behavior; verify them for the target OS/SDK (Google, n.d.-b).')
table(['Component','Implementation direction','Proposed acceptance evidence'],[
('Live capture','Bounded PCM; short encrypted commits; cheap signal checks; no full ASR during lessons.','60-minute screen-off run on each target configuration; every interruption is surfaced and timed.'),
('Deferred analysis','One worker, bounded windows, checkpoints; pause on heat/low battery; explicit user-run option.','Initial target: process 60 minutes within 15 minutes, with no thermal warning. Measure actual availability of idle/charging periods.'),
('Memory','Reuse buffers; avoid unbounded queues, full-lesson tensors and duplicate model runtimes.','Initial whole-process PSS target: capture <=200 MB; acoustic analysis <=300 MB. Also measure peaks, native memory and low-memory termination.'),
('Battery','Separate capture, playback and inference costs; test screen-off matched baseline.','Provisional capture target: <=5 percentage points/hour incremental drain, repeated under matched conditions; report battery capacity/health and variance.'),
('Storage and recovery','Disk reserve; atomic writes; metadata after commit; no silent restart or substitution.','Low-space, process-kill, route-change, call-interruption, reboot and clock-change tests.'),
('Offline updates','Signed, versioned model/content bundles with integrity checks and rollback.','No first-use downloads. Expired or incompatible measurement policy suppresses affected metrics; goal-based content remains available.')],[.20,.43,.37])
p('<b>All thresholds above are proposed engineering budgets, not published standards or achieved results.</b> Ratify them after a two-phone feasibility spike and then test all enrolled phones. A device that passes a short synthetic benchmark may still fail a full lesson with heat, low storage or concurrent apps.')
p('Compare one portable CPU runtime candidate at a time, such as LiteRT or a suitable ONNX build, using the same frozen artifact and representative workload. Pin versions and required operators; avoid adding multiple inference stacks to the release. Check native-library page-size compatibility and APK packaging. No NPU, cloud acceleration or always-on connection is assumed.')

page(14,'Summary generation without a large model')
p('For the pilot, a deterministic composer is the strongest default: it is small, explainable, fully offline and easy to audit. It takes a typed evidence packet, filters approved cards and fills reviewed language templates. The intervention can still be personalized through goals, lesson context and previous actions without inventing semantic findings.')
table(['Step','Required behavior'],[
('1. Validate evidence','Check origin, model/configuration identity, supported context and per-metric coverage. Preserve unavailable reasons.'),
('2. Select a relevant observation','Use only released metrics and comparable lessons. Otherwise state that no usable audio observation is available.'),
('3. Ask for interpretation','Use a brief structured prompt: modeling, practice, discussion, other/unsure. Teacher input affects interpretation, not original measurements.'),
('4. Select an approved practice','Match goal, grade, subject, language, materials and time. Permit teacher choice; do not map a high ratio directly to a deficit.'),
('5. Compose and revisit','State the observation, its limitation, one concrete action and next-week follow-up. Save source/card/version and permitted structured state.')],[.26,.74])
sub('Illustrative weekly report - fabricated example, not pilot data')
box('<b>Your goal:</b> check understanding after a worked example.<br/><b>Evidence available:</b> three comparable lessons passed the current measurement checks. Adult-child alternations were less frequent than in your previous comparable set. This does not tell us whether children understood.<br/><b>Your context:</b> you marked these lessons as mostly teacher modeling.<br/><b>Try next week:</b> after one model, use a fresh item, sample several answers and revisit the step that caused difficulty.<br/><b>Next reflection:</b> did the fresh item reveal an unexpected error? Did you adjust the explanation?<br/><b>Basis:</b> a reviewed checking-understanding card; acoustic evidence is descriptive context, not the reason to diagnose a weakness.')
p('If a language model is later added, constrain it to approved facts and card text, validate output against a schema and fall back to templates. Never let it calculate metrics, invent a citation, output pupil names or convert an unavailable measure into a claim. Evaluate factual fidelity, language clarity and recommendation fit independently of stylistic fluency.')

page(15,'Keeping the pedagogy current offline')
p('An offline model cannot know new research until something is updated. Maintain a curated evidence-to-practice library outside the classroom runtime, then distribute approved versioned packs. The app should display the pack\'s review date, not claim that its advice always reflects the latest science.')
table(['Stage','Proposed review rule'],[
('Search and screen','Review relevant syntheses, intervention studies and local curriculum work on a scheduled basis, for example quarterly. Log sources, dates and inclusion reasons.'),
('Appraise','Separate causal intervention evidence, observational association and expert guidance. Record population, language, subject, comparator, outcomes, uncertainty and transfer limits.'),
('Adapt','Translate a supported principle into one feasible action. Check materials, class size, curriculum and language-specific examples.'),
('Review and test','Pedagogy specialist + fluent educator + teacher comprehension test. Disagreement returns the card for revision.'),
('Release and withdraw','Publish signed content/version/source metadata. Remove misleading advice through the next approved update and document what offline devices cannot yet receive.')],[.25,.75])
sub('Two kinds of feedback must remain distinct')
p('Feedback delivered to a pupil and feedback delivered to a teacher have different recipients and mechanisms. EEF\'s pupil-feedback guidance can support card content; the coaching and professional-development evidence supports the teacher reflection process. Neither is proof that a particular audio statistic selects the correct card (EEF, 2021a, 2021b; Kraft et al., 2018).')
sub('Four languages require reviewed content, not four unchecked translations')
p('Treat app language, classroom language and model-supported language as independent fields. Bundle Amharic, Afaan Oromo, Sidaamu Afoo and English text only after review. Use a font with the required glyph coverage and test line wrapping, reading comfort and offline rendering. Optional human-recorded instructions may help comprehension but increase storage and require separate review. Machine-generated drafts are preparation aids, not automatically releasable content.')
p('Keep a source-to-claim ledger: each empirical claim and card points to a reference; each reference records what was actually read. A newer paper should change a card only when its quality, applicability and practical implications warrant the change. Recency alone is not evidence strength.')

page(16,'Validation I: capture and acoustic measures')
sub('A reference protocol before a model score')
p('Define an annotation codebook with examples of adult, child, overlap, non-speech, uncertain role and missing capture. Two independent trained raters should code a shared subset before adjudication. Preserve their disagreement in the results. Local playback annotation can establish what the recording contains; a synchronized live observer is also needed to identify speech occurring in the room that the mic misses.')
table(['Experiment','Suggested design and analysis'],[
('Microphone feasibility','Test at least two placements on representative phones before purchasing all kits. With authorization, sample front/back/side child positions, quiet speakers, clothing noise and overlapping speech. Compare live events with recorded audibility.'),
('Frozen-model evaluation','Prefer external development data and freeze the candidate before evaluating all eight teachers. If pilot data are used for tuning, separate development from held-out teachers/schools; do not count tuned cases as validation.'),
('Bounded annotation workload','Proposed minimum feasibility sample: two preselected 10-minute windows per teacher across separate lessons (160 minutes). Double-code all 16 windows if feasible. This is an initial error-finding sample, not proof of four-language generalization.'),
('Metric agreement','Report confusion patterns, class recall, event precision/recall, lesson-level bias and absolute error. Plot paired reference/model measures and difference against magnitude. Correlation alone can conceal systematic bias.'),
('Stress and subgroup checks','Report eligible coverage and failures by language mix, grade, mic, device, activity and distance when observable. Sparse cells stay descriptive; do not imply validated coverage for every combination.')],[.27,.73])
sub('Predeclare tolerances and display limits')
p('The repository\'s I-1 mean absolute error target of 5 percentage points is an aspiration, not an established educational threshold. A provisional I-5 target could be no more than two count errors per 10-minute window, supported by event-level error analysis; teachers and advisers must ratify whether that resolution is useful. Inspect outliers and conditional bias even if a mean passes.')
p('Log thresholds, model hash, codec, feature configuration and runtime before the held-out test. A failed gate leaves the metric unavailable. Keep shadow-model outputs hidden from observers and, until validated, from teacher reports. Live aggregate observation cannot establish frame-level accuracy unless an appropriate time-aligned reference is also available.')

page(17,'Validation II: meaning and teacher use')
p('Use an independent observer rubric focused on the practices the pilot actually encourages. Teach Primary offers an established framework for observing instruction, including checks for understanding and feedback, with observer-training requirements. A shortened Echo-specific rubric should be labeled <b>Teach-informed</b>, not presented as the validated full instrument (World Bank, 2022).')
table(['Construct','Example observable evidence','Do not substitute'],[
('Checking understanding','Teacher elicits evidence beyond a general yes/no confirmation and responds to an observed difficulty.','Amount of child speech.'),
('Actionable feedback','Feedback identifies a relevant step or next action and pupils have an opportunity to use it.','Praise words, teacher volume or turn count.'),
('Reasoning opportunity','Teacher invites explanation or evidence, with an opportunity to respond.','Any question intonation or any long response.'),
('Use of chosen practice','Observed example consistent with the selected action; context and opportunity recorded.','An app checkbox as proof of implementation.')],[.25,.44,.31])
sub('Test interpretation using counterexamples')
p('Present matched scenarios: the same high adult-speech share during useful modeling and during a pupil-discussion task; silence during reading and during a microphone failure; group speech during coordinated practice and unrelated conversation. Ask teachers what the report establishes, what it cannot establish and whether the card fits. These are proposed comprehension tasks, not validated psychometric tests.')
p('Test wording in every released app language. Collect unprompted interpretation first, then a structured teach-back. Redesign any wording that consistently produces beliefs about teacher ranking, learning, equity or an ideal talk ratio. A provisional progression criterion is at least 7 of 8 teachers correctly identifying the main limitation without coaching, but report each error and the small denominator.')
sub('Evaluate advice independently of whether teachers like it')
p('Have at least two education reviewers independently rate card factual grounding, curriculum fit, feasibility, specificity and unsupported claims before reconciliation. Record reasons for disagreement. Teacher usefulness is important but cannot replace expert review or observation. If only synthetic/mock reports are tested, state that their comprehension results do not validate real measurements.')

page(18,'Pilot design: two coordinated tracks')
story.append(Visual('pilot',170))
p('Figure 4. Proposed concurrent four-school pilot. Readiness work happens before the four-week intervention. The timeline is a study proposal, not a completed schedule.','Caption')
p('<b>Track A: reflection feasibility.</b> All eight teachers receive the same frozen, reviewed goal-based workflow. Record a manageable sample of lessons, proposed as two per week, rather than every lesson. Each teacher chooses one curriculum-linked goal. Track setup time, weekly reflection, practice selection, follow-up and burden.')
p('<b>Track B: measurement validation.</b> Run an experimental frozen acoustic candidate in shadow mode on approved sessions. Observers and teachers do not see unvalidated metrics. This allows the pilot to assess the reflection product and measurement feasibility separately. It does not measure the benefit of showing acoustic feedback if those metrics are hidden.')
table(['Period','Protocol'],[
('Before week 1','Phone/mic checks, permission and consent, language/content review, observer calibration, preregistered outcomes, analysis and retention plan. No enrollment deadline overrides readiness.'),
('Week 1','Onboard, record baseline lessons and independent observations, establish goal. Baseline reflection must not claim improvement.'),
('Weeks 2-3','Choose and try one action, review weekly, record technical support and barriers. Avoid changing model or card policy midway; log unavoidable deviations.'),
('Week 4','Repeat comparable observations; gather final ratings and structured interviews; complete baseline/endline analysis before data expiry.'),
('Optional follow-up','Weeks 5-6 may assess use without researcher prompting, but only if separately agreed, funded and compatible with retention. Otherwise call the outcome end-of-pilot adoption.')],[.22,.78])
p('Propose two 20-minute baseline and two 20-minute endline observations per teacher: 32 observations, about 10.7 observer-hours before travel. Double-code a planned subset. These numbers are workload proposals; fewer observations weaken stability and should narrow claims. Use comparable lesson families, while recording unavoidable differences.')

page(19,'Outcomes and analysis plan')
table(['Outcome','Operational definition / denominator','Interpretation'],[
('Technical feasibility','Completed usable captures / all attempted captures; interruptions and unavailable analysis included.','Report per phone and teacher, not just pooled recordings.'),
('Adoption','Predefine a minimum recording/reflection sequence in each intervention week. Count successes among all 8 enrolled teachers.','More than 70% requires at least 6/8. Define separately any unprompted post-pilot use.'),
('Usefulness','One final teacher-level rating, with positive defined as 4-5 on a five-point scale; report nonresponse.','More than 80% of all enrolled requires at least 7/8 positive. Repeated ratings must not let a frequent user dominate.'),
('Interpretation and burden','Teach-back errors; time to complete; reasons for nonuse; device/storage/charging burden.','Qualitative evidence explains mechanisms and failure, not just satisfaction.'),
('Practice change','Within-teacher baseline/endline observer descriptions of the selected practice, plus agreement and opportunity counts.','Exploratory change, not a causal effect of Echo.'),
('Learning','No learning-effect estimate in the proposed first pilot.','A later study needs suitable outcomes, a comparison, exposure and sample planning.')],[.22,.47,.31])
sub('Small samples demand transparent denominators')
p('Eight teachers are eight teacher-level units; thousands of frames do not create thousands of independent teachers. Four schools are only four clusters. Report individual trajectories, counts, missingness and uncertainty. Avoid an elaborate regression or significance test that creates an appearance of precision unsupported by the design.')
p('As an illustration only, a Wilson 95% interval treating teachers as independent is about 41-93% for 6/8 and 53-98% for 7/8. School clustering and purposive recruitment further limit generalization. These intervals are arithmetic illustrations, not pilot results or a justification of sample size.')
sub('Prespecify analysis choices')
p('Freeze primary outcomes, exclusions, positive-rating definition, observation codebook, paired-comparison rules and handling of missing records before recruitment. Report protocol deviations. Use interviews to explain perceived changes and failures, while avoiding claims that self-report establishes observed change. A future causal study should compare against an equivalent goal/card workflow without audio to isolate audio\'s incremental value.')

page(20,'Progression gates and fallback decisions')
story.append(Visual('gates',245))
p('Figure 5. Proposed progression gates. G2 authorizes only explicitly tested pilot contexts; it does not imply broad product validation. Targets must be agreed before data collection.','Caption')
table(['Observed result','Decision'],[
('Capture unreliable or deletion fails','Stop classroom deployment until fixed. Technical failure cannot be offset by positive ratings.'),
('Recording works; indicators fail','Continue only the approved reflection-only workflow. Report acoustic feasibility failure and the conditions causing it.'),
('Indicators agree; interpretation fails','Revise labels, visualizations and prompts. Do not display valid measurements in a misleading interface.'),
('Teachers understand; usage is low','Investigate workload, trust, relevance and support dependence. Simplify or change the intervention before expanding AI.'),
('Usefulness and feasibility are promising','Plan a longer, adequately resourced comparison study. Do not upgrade the claim to improved learning.')],[.43,.57])
p('A possible future comparison is goal-based reflection versus the same workflow plus validated acoustic evidence, with support time matched. Randomization level must account for teacher communication within schools. Eight teachers/four schools are not enough to establish a robust comparative impact estimate; use the pilot to inform recruitment, contamination and outcome-variance assumptions.')

page(21,'Engineering implementation plan')
p('The inspected code already separates synthetic evidence from real local recording, and its metric-release list is empty. Preserve that behavior. The following are proposed work packages, not claims of completed implementation (Echo project, 2026).')
table(['Priority / owner','Deliverable','Acceptance artifact'],[
('P0 - Android','Verify RecordingService, PilotStore, encrypted playback, expiry and recovery on target phones. Add a reproducible benchmark mode.','Device inventory; interruption matrix; memory/battery/storage logs using authorized test material.'),
('P0 - Education + language reviewers','Small curriculum-linked four-language card pack, goals, examples and follow-up prompts.','Versioned source-to-card ledger; reviewer decisions; teacher comprehension records.'),
('P1 - ML + Android','Bounded AAC decoder adapter and one acoustic candidate; explicit unknown/missing states.','Frozen model manifest; runtime comparison; license/provenance record; shadow evaluation.'),
('P1 - Domain + QA','Version indicator definitions and Kotlin/Python parity; metric-specific eligibility and neutral labels.','Golden fixtures for overlap, gaps, zero denominators, incompatible versions and abstention.'),
('P1 - Product','Deterministic report composer with teacher context, fallback and practice follow-up.','Each statement traceable to evidence/card; no unsupported trend or diagnosis; offline usability checks.'),
('P2 - Research','On-device annotation/reference workflow with minimal authorized outputs.','Independent rater procedure; no exported classroom audio; locked analysis protocol.'),
('P3 - Optional experiment','Transient ASR on short excerpts, language-specific tests, no text persistence.','Leak checks and downstream accuracy/resource evidence. Separate opt-in research scope.')],[.24,.42,.34])
sub('Integration boundaries worth retaining')
p('Use the existing <b>pilot/</b> flow for real recording, <b>domain/MetricEvidence.kt</b> for per-metric release decisions and <b>indicators-core/python/</b> as the arithmetic reference. Do not import synthetic observations into the real report. Add interfaces for analysis results, content packs and report composition rather than a new cloud-centered architecture.')
p('Before a release, store source revision, model/runtime versions, artifact hashes, codec/feature configuration, supported context, validation manifest and content version. A changed model or threshold must not silently rewrite the interpretation of historical observations.')

page(22,'Privacy, transcripts and pilot approval')
p('Call the input <b>private local audio</b>, not anonymized audio. A changed filename or pseudonymous ID does not remove a voice or spoken identifying content. NIST distinguishes disclosure-risk management from merely masking identifiers; it does not provide an anonymity guarantee for an eight-teacher dataset (Garfinkel et al., 2023). Voice anonymization is also a separate privacy/utility research task, not a prerequisite for measuring the original recording locally (Tomashenko et al., 2024).')
sub('Enforce the no-transcript-persistence requirement')
p('If the optional ASR branch is enabled, keep tokens, text and human reference text in bounded process memory only. Do not write them to databases, files, analytics, crash reports, debug logs, queues, saved UI state or clipboard. Prefer not to display transcripts in the pilot. Test normal completion, cancellation, exceptions and process death with distinctive synthetic canary phrases. Inspect application storage and logs and verify that network transport cannot accept text.')
p('Release buffers promptly and zero mutable buffers where practical, but do not promise forensic erasure of every OS-managed memory copy. Plaintext speech necessarily exists briefly during capture/playback/inference. Derived semantic labels and embeddings can remain sensitive; avoid retaining them unless explicitly necessary and approved.')
sub('The 30-day promise constrains the research too')
p('Apply expiry to audio, local observations, notes, reflection state and any authorized research copies. A powered-off or force-stopped phone cannot execute deletion until it runs again; deny expired playback immediately on reopening. Complete baseline/endline comparisons before baseline data expires. Do not silently retain identifiable study outcomes for a thesis after the agreed retention limit.')
p('A research plan may propose disclosure-reviewed anonymous aggregate findings and nonpersonal reproducibility artifacts for the thesis, but approval must establish what can be retained. With two teachers per school, combinations of language, grade, timing and quotations can identify participants. A minimum contributor count alone is insufficient. Optional weeks 5-6 follow-up needs a separate retention-compatible design.')
sub('Approval package')
p('Confirm institutional ethics review, school authorization, applicable parent/guardian information and consent, age-appropriate pupil assent where required, teacher voluntariness, withdrawal, a contact for concerns and no employment-ranking use. Establish controller/custodian responsibilities and local legal review under the Ethiopian Personal Data Protection Proclamation (Federal Democratic Republic of Ethiopia, 2024). This report does not determine compliance or grant approval.')

page(23,'Resources, risks and realistic scope')
p('The 20,000 ETB ceiling is hardware-only. Obtain local quotes after checking the actual phones; the allocation below is a planning scenario, not current market pricing. If a phone is incompatible or child audibility fails, revise the pilot before buying eight identical microphone kits.')
table(['Illustrative hardware allowance','ETB'],[
('Eight microphone kits plus two spares','12,000'),('Adapters, clips and cable protection','2,000'),('Shared power accessories where needed','4,000'),('Compatibility/replacement contingency','2,000'),('<b>Total ceiling</b>','<b>20,000</b>')],[.80,.20])
p('Engineering, translation, education review, observer training, travel, participant support and any research hosting require separate resources. The observation proposal alone implies about 10.7 hours of primary observation, plus double coding and travel. A no-cloud pilot reduces infrastructure dependency, not the cost of valid educational evaluation.')
table(['Main risk','Early signal','Response'],[
('Teacher mic misses children','Live child turns absent from playback.','Suppress affected ratios/turns; test placement; retain reflection-only option.'),
('Teachers optimize the metric','Reports interpreted as a talk quota or score.','Remove normative visuals and automatic deficit mapping; retest wording.'),
('Model works only in one context','Large errors in a language/device/activity stratum.','Narrow scope; display unavailable states; do not pool away failures.'),
('ASR distorts the lesson','Invented text, mixed-language errors or high resource use.','Disable semantic branch; preserve typed goals and human interpretation.'),
('Pilot appears successful due to support','Use falls when researchers stop prompting.','Measure contact time; distinguish supported use from sustained adoption.'),
('Retention harms reproducibility','Baseline expires before pairing or labels cannot be audited.','Schedule analysis inside the window; retain approved aggregate checks and public fixtures, not unauthorized copies.')],[.27,.32,.41])

page(24,'Thesis defense and final recommendation')
table(['Likely defense question','Defensible response'],[
('What is the research contribution?','An evaluated design for private offline reflection under device/language constraints, with explicit evidence gates and documented failures.'),
('Why should this change teaching?','Coaching and targeted-feedback studies support the mechanism. Echo\'s specific mechanism and context remain hypotheses tested through comprehension, use and observation.'),
('What does the audio actually measure?','Only validated acoustic timing/categories in tested conditions. It does not directly measure understanding, equity or teaching quality.'),
('Why no mandatory transcription?','Core reflection and timing measures do not require it. The target languages, mixed speech and device constraints make semantic inference a separate, higher-risk validation task.'),
('What if the AI fails?','The approved reflection workflow can remain useful; the thesis reports failed measurement feasibility honestly and identifies the boundary.'),
('Can eight teachers prove impact?','No. They support a small feasibility and usability study with exploratory observations, not a general causal learning claim.'),
('How is the advice evidence-based?','Reviewed, versioned, locally adapted practice cards with explicit sources, limitations and teacher choice. The app does not manufacture research claims.')],[.36,.64])
sub('Recommended thesis framing')
box('<b>Suggested title:</b> Designing and evaluating an offline, privacy-preserving teacher-reflection aid for multilingual primary classrooms in Hawassa.<br/><b>Research question:</b> Under what recording, language, device and workflow conditions can Echo provide interpretable evidence and feasible teaching actions, and where must it abstain?')
p('Proceed first with reliable recording, a reviewed practice library and an independent observation protocol. Evaluate acoustic measurement in a distinct shadow track. Release only the metrics supported by evidence for the intended conditions. Keep temporary ASR optional, brief and nonpersistent. Treat a later learning-impact study as a new research phase with its own comparison, sample and resources.')
p('For approval, attach the frozen pilot protocol, consent materials, content-review ledger, device-test dossier, observation codebook, analysis plan, retention design and incident/withdrawal procedure. For engineering, use the same artifacts as acceptance requirements. A defensible project is one whose claims, behavior and evidence remain aligned when the recording or model is uncertain.')

page(25,'Source audit and reproducibility notes')
p('All sources cited in the report are listed in the following reference section. Inline citations use APA author-date form. The access categories below describe the material inspected; they are not grades of study quality. References to technical documentation describe the state checked on 22 October 2026 and should be rechecked before implementation.')
table(['Source group','Access and use in this review'],[
('Yisihak & Damtew; Haile & Mendisu; Schlotterbeck et al.; Demszky & Liu','Supplied full-text PDFs and the repository research audit. Relevant methods/results checked; no new reanalysis. These studies remain distinct populations and interventions.'),
('Kraft et al.; Demszky et al. (2025); Wang et al.; Chua et al.','Author-hosted manuscript abstract, published/author abstract or journal record. Numerical findings used narrowly. The 2025 journal issue year is used for Demszky et al.; earlier working-paper recruitment counts are not combined.'),
('Owens et al.; Skivington et al.; AIR','Primary article/report records, indexed primary text and repository audit. Full-page retrieval was limited for parts of this group. No claim of an exhaustive full-text review.'),
('EEF; World Bank coaching and Teach Primary','Official guidance pages and coaching note. These guide intervention design and observation; proposed Echo adaptations are not validated instruments or endorsed products.'),
('World Bank (2025)','Publisher summary of the GEEAP-endorsed literacy synthesis. Full report download exceeded the retrieval limit; findings are attributed to the summary.'),
('Olufemi et al. (2026)','Preprint abstract and metadata. Emerging corpus-level ASR results only; not independently reproduced or device-validated.'),
('Android, LiteRT, Silero, whisper.cpp, MMS, Afrivoice, WAXAL','Official documentation/repositories/model or dataset cards. Artifact claims and maintainer figures are separated from measured Echo performance.'),
('NIST, VoicePrivacy, Ethiopian proclamation','Official publication pages or evaluation-plan text. Used for data-boundary reasoning and approval planning, not a determination of local legal compliance.')],[.30,.70])
p('Project sources: README.md; docs/adr/019-offline-recorded-reflection-pilot.md; docs/adr/020-provider-independent-pilot-infrastructure.md; docs/contracts/MEASUREMENT_EVIDENCE.md; docs/DIMTS_ARCHITECTURE_CONTEXT.md; docs/research/HAWASSA_PILOT_REVIEW_2026-10-10.md; docs/research/CLAIMS_AUDIT.md; and inspected MetricEvidence.kt / LocalAnalysis.kt. Working-tree code was read; product code was not modified by this report.','SmallText')

refs = [
('American Institutes for Research. (2020). <i>Mother tongue teachers\' competencies assessment (MTTCA) 2019 report</i>. U.S. Agency for International Development.', 'https://www.air.org/sites/default/files/2025-09/Mother-Tongue-Teachers-Competencies-Assessment-Final-Report-June-2020.pdf'),
('Chua, V. Y. H., Liu, H., Garcia Perera, L. P., Woon, F. T., Wong, J., Zhang, X., Khudanpur, S., Khong, A. W. H., Dauwels, J., &amp; Styles, S. J. (2023). <i>MERLIon CCS challenge: A English-Mandarin code-switching child-directed speech corpus for language identification and diarization</i> [Preprint]. arXiv.', 'https://doi.org/10.48550/arXiv.2305.18881'),
('Demszky, D., &amp; Liu, J. (2023). M-Powering teachers: Natural language processing powered feedback improves 1:1 instruction and student outcomes. In <i>Proceedings of the Tenth ACM Conference on Learning @ Scale</i> (pp. 59-69). Association for Computing Machinery.', 'https://doi.org/10.1145/3573051.3593379'),
('Demszky, D., Liu, J., Hill, H. C., Sanghi, S., &amp; Chung, A. (2025). Automated feedback improves teachers\' questioning quality in brick-and-mortar classrooms: Opportunities for further enhancement. <i>Computers &amp; Education, 227</i>, Article 105183.', 'https://doi.org/10.1016/j.compedu.2024.105183'),
('Digital Umuganda. (n.d.). <i>Afrivoice Ethiopia</i> [Data set and dataset card]. Hugging Face. Retrieved October 22, 2026, from', 'https://huggingface.co/datasets/DigitalUmuganda/Afrivoice_Ethiopia'),
('Echo project. (2026). <i>Echo / Echovolve: Project documentation and source code</i> [Repository; local revision b03b6ec and inspected working-tree files]. See Section 25 for the source-file inventory.', 'https://github.com/ayimmam/Echo'),
('Education Endowment Foundation. (2021a). <i>Effective professional development</i>.', 'https://educationendowmentfoundation.org.uk/education-evidence/guidance-reports/effective-professional-development'),
('Education Endowment Foundation. (2021b). <i>Teacher feedback to improve pupil learning</i>.', 'https://educationendowmentfoundation.org.uk/education-evidence/guidance-reports/feedback'),
('Federal Democratic Republic of Ethiopia. (2024). <i>Personal Data Protection Proclamation No. 1321/2024</i>. Federal Negarit Gazette. Ministry of Justice publication page.', 'https://justice.gov.et/en/law/personal-data-protection-proclamation/'),
('Garfinkel, S., Guttman, B., Near, J., Dajani, A., &amp; Singer, P. (2023). <i>De-identifying government datasets: Techniques and governance</i> (NIST Special Publication 800-188). National Institute of Standards and Technology.', 'https://doi.org/10.6028/NIST.SP.800-188'),
('ggml-org. (n.d.). <i>whisper.cpp</i> [Computer software and documentation]. GitHub. Retrieved October 22, 2026, from', 'https://github.com/ggml-org/whisper.cpp'),
('Google. (n.d.-a). <i>Android memory</i>. Android Developers. Retrieved October 22, 2026, from', 'https://developer.android.com/topic/performance/memory'),
('Google. (n.d.-b). <i>Foreground service types</i>. Android Developers. Retrieved October 22, 2026, from', 'https://developer.android.com/develop/background-work/services/fgs/service-types'),
('Google. (n.d.-c). <i>Post-training quantization</i>. Google AI Edge. Retrieved October 22, 2026, from', 'https://developers.google.com/edge/litert/conversion/tensorflow/quantization/post_training_quantization'),
('Google. (n.d.-d). <i>WaxalNLP</i> [Data set and dataset card]. Hugging Face. Retrieved October 22, 2026, from', 'https://huggingface.co/datasets/google/WaxalNLP'),
('Haile, S. Z., &amp; Mendisu, B. S. (2023). Early-grade reading: The challenges that affect teachers\' practice of phonological awareness: The case of Koorete language. <i>Education Research International, 2023</i>, Article 9527369.', 'https://doi.org/10.1155/2023/9527369'),
('Kraft, M. A., Blazar, D., &amp; Hogan, D. (2018). The effect of teacher coaching on instruction and achievement: A meta-analysis of the causal evidence. <i>Review of Educational Research, 88</i>(4), 547-588.', 'https://doi.org/10.3102/0034654318759268'),
('Meta. (n.d.). <i>mms-lid-256</i> [Model and model card]. Hugging Face. Retrieved October 22, 2026, from', 'https://huggingface.co/facebook/mms-lid-256'),
('Olufemi, V. T., Babatunde, O., Njema, R., Gbotemi, B., Yen, W. L., Uzodinma, J., Ajayi, S., Williams, O., Moshood, K., Anyaele, I. E., Arefaine, A., Hunzwi, C., Daniel, W. D., Namuganga, E., Kadima, C., Bahizire, A., Ranaivoson, O., Aaron, E., Ladislaus, N., . . . Mitra, P. (2026). <i>WAXAL-NET: Finetuned edge ASR across 19 African languages</i> [Preprint]. arXiv.', 'https://doi.org/10.48550/arXiv.2606.02375'),
('Owens, M. T., Seidel, S. B., Wong, M., Bejines, T. E., Lietz, S., Perez, J. R., Sit, S., Subedar, Z.-S., Acker, G. N., Akana, S. F., Balukjian, B., Benton, H. P., Blair, J. R., Boaz, S. M., Boyer, K. E., Bram, J. B., Burrus, L. W., Byrd, D. T., Caporale, N., . . . Tanner, K. D. (2017). Classroom sound can be used to classify teaching practices in college science courses. <i>Proceedings of the National Academy of Sciences, 114</i>(12), 3085-3090.', 'https://doi.org/10.1073/pnas.1618693114'),
('Schlotterbeck, D., Uribe, P., Araya, R., Jimenez, A., &amp; Caballero, D. (2021). What classroom audio tells about teaching: A cost-effective approach for detection of teaching practices using spectral audio features. In <i>LAK21: 11th International Learning Analytics and Knowledge Conference</i> (pp. 132-140). Association for Computing Machinery.', 'https://doi.org/10.1145/3448139.3448152'),
('Silero Team. (n.d.). <i>Silero VAD</i> [Computer software and documentation]. GitHub. Retrieved October 22, 2026, from', 'https://github.com/snakers4/silero-vad'),
('Skivington, K., Matthews, L., Simpson, S. A., Craig, P., Baird, J., Blazeby, J. M., Boyd, K. A., Craig, N., French, D. P., McIntosh, E., Petticrew, M., Rycroft-Malone, J., White, M., &amp; Moore, L. (2021). A new framework for developing and evaluating complex interventions: Update of Medical Research Council guidance. <i>BMJ, 374</i>, Article n2061.', 'https://doi.org/10.1136/bmj.n2061'),
('Tomashenko, N., Miao, X., Champion, P., Meyer, S., Wang, X., Vincent, E., Panariello, M., Evans, N., Yamagishi, J., &amp; Todisco, M. (2024). <i>The VoicePrivacy 2024 challenge evaluation plan</i> (Version 2.0). VoicePrivacy Challenge.', 'https://www.voiceprivacychallenge.org/docs/VoicePrivacy_2024_Eval_Plan_v2.0.pdf'),
('Wang, J., Dudy, S., He, X., Wang, Z., Southwell, R., &amp; Whitehill, J. (2025). Optimizing speaker diarization for the classroom: Applications in timing student speech and distinguishing teachers from children. <i>Journal of Educational Data Mining, 17</i>(1), 98-125.', 'https://doi.org/10.5281/zenodo.14871875'),
('Wilichowski, T., &amp; Arenge, G. (2022). <i>Facilitating effective 1-1 coaching sessions: Technical guidance note</i>. World Bank. (Coauthor spelling follows the acknowledgments; the suggested-citation line prints "Arrange.")', 'https://documents1.worldbank.org/curated/en/589311630358726963/pdf/Technical-Guidance-Note.pdf'),
('World Bank. (2022, August 30). <i>Teach Primary: Helping countries to measure effective teaching practices</i>.', 'https://www.worldbank.org/en/brief/2022/08/30/teach-helping-countries-track-and-improve-teaching-quality'),
('World Bank. (2025, October 30). <i>New report offers evidence-based solutions to address global literacy crisis among children</i> [Press release and publisher summary].', 'https://www.worldbank.org/en/news/press-release/2025/10/30/new-report-offers-evidence-based-solutions-to-address-global-literacy-crisis-among-children'),
('Yisihak, E., &amp; Damtew, A. (2024). Ethiopian early grade English teachers\' preparedness to teach basic reading skills. <i>Education Research International, 2024</i>, Article 5596229.', 'https://doi.org/10.1155/2024/5596229'),
]
story.append(PageBreak())
heading=P('References','SectionTitle');heading.section_title='References';story.append(heading)
p('APA author-date references. URLs are clickable. Technical and dataset pages were checked on 22 October 2026. Access limitations are recorded in Section 25.','SmallText')
for idx,(ref,url) in enumerate(refs):
    if idx in (10,20):
        story.append(PageBreak())
        p('References (continued)','SectionTitle')
    safe=html.escape(url,quote=True)
    story.append(KeepTogether([P(ref+'<br/><link href="'+safe+'" color="#087F83">'+safe+'</link>','Ref')]))

doc=Doc(str(OUT),pagesize=(W,H),rightMargin=44,leftMargin=44,topMargin=48,bottomMargin=61,
    title='Echo: Pedagogical Evidence and Offline Feasibility',author='Prepared for the Echo project',
    subject='Critical evidence review, low-end Android architecture and Hawassa feasibility pilot',
    pageCompression=1)
doc.build(story,onFirstPage=footer,onLaterPages=footer)
manifest={'output':str(OUT),'sections':section_pages,'references':len(refs)}
(ROOT/'tmp/pdfs/report_manifest.json').write_text(json.dumps(manifest,indent=2))
print(json.dumps(manifest,indent=2))
