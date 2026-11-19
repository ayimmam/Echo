from pathlib import Path
from zipfile import ZipFile
from PIL import Image, ImageDraw, ImageFont
from docx import Document
from docx.enum.section import WD_SECTION
from docx.enum.table import WD_CELL_VERTICAL_ALIGNMENT, WD_TABLE_ALIGNMENT
from docx.enum.text import WD_ALIGN_PARAGRAPH
from docx.oxml import OxmlElement
from docx.oxml.ns import qn
from docx.shared import Inches, Pt, RGBColor


ROOT = Path('/Users/anteneh/Documents/Repos/Echo')
WORK = ROOT / 'tmp' / 'echo_advisor_revision'
WORK.mkdir(parents=True, exist_ok=True)
OUTPUT = ROOT / 'Echo Chapter One Advisor Revision.docx'
REFERENCE_DOCX = Path('/Users/anteneh/Downloads/FINAL project SSP.docx')
LOGO = WORK / 'hawassa-university-logo.jpg'
GANTT = WORK / 'role-specific-gantt.png'

TITLE = 'Echo'
SUBTITLE = 'Android Based On Device Audio Analysis and Pedagogy Feedback System'
TEAM = [
    ('Anteneh Fisseha', 'TNaScR/1008/16'),
    ('Amensisa Abebe', 'NaScR/1244/16'),
    ('Hanna Woldeselassie', 'NaScR/2328/16'),
    ('Selwa Jemal', 'NaScR/3472/16'),
    ('Yordanos Tesfaye', 'NaScR/3977/16'),
    ('Yoseph Zekarias', 'NaScR/4000/16'),
]


def ensure_assets():
    if not LOGO.exists():
        with ZipFile(REFERENCE_DOCX) as zf:
            LOGO.write_bytes(zf.read('word/media/image1.jpg'))


def create_gantt():
    tasks = [
        ('Anteneh - requirements and audio pipeline', 1, 6, '#1F4E78'),
        ('Anteneh - integration, pilot and analysis', 7, 16, '#4F81BD'),
        ('Hanna - CI and branching policy', 1, 4, '#6B8E23'),
        ('Hanna - release and merge support', 5, 16, '#9BBB59'),
        ('Selwa - teacher flow and UI redesign', 1, 5, '#7F6000'),
        ('Selwa - UI implementation and usability', 6, 12, '#C49A00'),
        ('Amensisa - research database schema', 1, 5, '#7030A0'),
        ('Amensisa - migration and backend tests', 6, 10, '#9E6AB8'),
        ('Yordanos - architecture and data flow', 1, 4, '#C0504D'),
        ('Yordanos - Android integration and evidence', 5, 12, '#D77B78'),
        ('Yoseph - microphone research and procurement', 1, 4, '#008C95'),
        ('Yoseph - device checks and pilot support', 5, 12, '#4CB7BE'),
        ('Whole team - manual, documentation and defense', 13, 16, '#555555'),
    ]
    width, height = 2700, 1450
    left, right, top, bottom = 850, 80, 170, 120
    chart_w = width - left - right
    row_h = (height - top - bottom) / len(tasks)
    img = Image.new('RGB', (width, height), 'white')
    draw = ImageDraw.Draw(img)
    regular_path = '/System/Library/Fonts/Supplemental/Arial.ttf'
    bold_path = '/System/Library/Fonts/Supplemental/Arial Bold.ttf'
    regular = ImageFont.truetype(regular_path, 27)
    small = ImageFont.truetype(regular_path, 24)
    bold = ImageFont.truetype(bold_path, 28)
    title_font = ImageFont.truetype(bold_path, 42)
    draw.text((width / 2, 38), 'Role Specific Project Schedule', fill='#000000', font=title_font, anchor='ma')
    cell_w = chart_w / 16
    for week in range(1, 17):
        x = left + (week - 1) * cell_w
        draw.line((x, top - 10, x, height - bottom + 10), fill='#D9D9D9', width=2)
        draw.text((x + cell_w / 2, top - 45), str(week), fill='#000000', font=bold, anchor='mm')
    draw.line((left + chart_w, top - 10, left + chart_w, height - bottom + 10), fill='#D9D9D9', width=2)
    for row, (label, start, end, color) in enumerate(tasks):
        cy = top + row * row_h + row_h / 2
        draw.text((left - 28, cy), label, fill='#000000', font=regular, anchor='rm')
        x1 = left + (start - 1) * cell_w + 4
        x2 = left + end * cell_w - 4
        y1, y2 = cy - row_h * 0.31, cy + row_h * 0.31
        draw.rounded_rectangle((x1, y1, x2, y2), radius=10, fill=color)
        if end - start >= 2:
            draw.text(((x1 + x2) / 2, cy), f'W{start}-W{end}', fill='white', font=small, anchor='mm')
    draw.text((left + chart_w / 2, height - 45), 'Project week', fill='#000000', font=bold, anchor='mm')
    img.save(GANTT, dpi=(220, 220))


def set_run_font(run, size=11.5, bold=False, italic=False, color='000000'):
    run.font.name = 'Times New Roman'
    run._element.get_or_add_rPr().rFonts.set(qn('w:ascii'), 'Times New Roman')
    run._element.get_or_add_rPr().rFonts.set(qn('w:hAnsi'), 'Times New Roman')
    run.font.size = Pt(size)
    run.bold = bold
    run.italic = italic
    run.font.color.rgb = RGBColor.from_string(color)
    return run


def add_picture_with_alt(run, path, width, alt):
    shape = run.add_picture(str(path), width=width)
    shape._inline.docPr.set('descr', alt)
    shape._inline.docPr.set('title', alt)
    return shape


def set_cell_margins(cell, top=85, start=90, bottom=85, end=90):
    tc = cell._tc
    tcPr = tc.get_or_add_tcPr()
    tcMar = tcPr.first_child_found_in('w:tcMar')
    if tcMar is None:
        tcMar = OxmlElement('w:tcMar')
        tcPr.append(tcMar)
    for m, v in [('top', top), ('start', start), ('bottom', bottom), ('end', end)]:
        node = tcMar.find(qn(f'w:{m}'))
        if node is None:
            node = OxmlElement(f'w:{m}')
            tcMar.append(node)
        node.set(qn('w:w'), str(v))
        node.set(qn('w:type'), 'dxa')


def set_table_borders(table, color='D9D9D9', size='6'):
    tblPr = table._tbl.tblPr
    borders = tblPr.first_child_found_in('w:tblBorders')
    if borders is None:
        borders = OxmlElement('w:tblBorders')
        tblPr.append(borders)
    for edge in ('top', 'left', 'bottom', 'right', 'insideH', 'insideV'):
        tag = borders.find(qn(f'w:{edge}'))
        if tag is None:
            tag = OxmlElement(f'w:{edge}')
            borders.append(tag)
        tag.set(qn('w:val'), 'single')
        tag.set(qn('w:sz'), size)
        tag.set(qn('w:color'), color)


def remove_table_borders(table):
    set_table_borders(table, 'FFFFFF', '0')
    borders = table._tbl.tblPr.first_child_found_in('w:tblBorders')
    for edge in borders:
        edge.set(qn('w:val'), 'nil')


def shade_cell(cell, fill):
    tcPr = cell._tc.get_or_add_tcPr()
    shd = tcPr.find(qn('w:shd'))
    if shd is None:
        shd = OxmlElement('w:shd')
        tcPr.append(shd)
    shd.set(qn('w:fill'), fill)


def repeat_table_header(row):
    trPr = row._tr.get_or_add_trPr()
    flag = OxmlElement('w:tblHeader')
    flag.set(qn('w:val'), 'true')
    trPr.append(flag)


def prevent_row_split(row):
    trPr = row._tr.get_or_add_trPr()
    if trPr.find(qn('w:cantSplit')) is None:
        trPr.append(OxmlElement('w:cantSplit'))


def set_cell_width(cell, width):
    cell.width = width
    tcPr = cell._tc.get_or_add_tcPr()
    tcW = tcPr.find(qn('w:tcW'))
    if tcW is None:
        tcW = OxmlElement('w:tcW')
        tcPr.append(tcW)
    tcW.set(qn('w:w'), str(int(width.inches * 1440)))
    tcW.set(qn('w:type'), 'dxa')


def add_page_number(paragraph):
    paragraph.alignment = WD_ALIGN_PARAGRAPH.CENTER
    run = paragraph.add_run()
    set_run_font(run, 10)
    begin = OxmlElement('w:fldChar'); begin.set(qn('w:fldCharType'), 'begin')
    instr = OxmlElement('w:instrText'); instr.set(qn('xml:space'), 'preserve'); instr.text = ' PAGE '
    separate = OxmlElement('w:fldChar'); separate.set(qn('w:fldCharType'), 'separate')
    text = OxmlElement('w:t'); text.text = '1'
    end = OxmlElement('w:fldChar'); end.set(qn('w:fldCharType'), 'end')
    run._r.extend([begin, instr, separate, text, end])


def set_page_number_format(section, fmt='decimal', start=1):
    pg = section._sectPr.find(qn('w:pgNumType'))
    if pg is None:
        pg = OxmlElement('w:pgNumType')
        section._sectPr.append(pg)
    pg.set(qn('w:fmt'), fmt)
    pg.set(qn('w:start'), str(start))


def clear_container(container):
    element = container._element
    for child in list(element):
        if child.tag != qn('w:sectPr'):
            element.remove(child)


def configure_section(section, top=0.82, bottom=0.72):
    section.page_width = Inches(8.5)
    section.page_height = Inches(11)
    section.left_margin = Inches(1)
    section.right_margin = Inches(1)
    section.top_margin = Inches(top)
    section.bottom_margin = Inches(bottom)
    section.header_distance = Inches(0.22)
    section.footer_distance = Inches(0.3)


def make_header(section):
    section.header.is_linked_to_previous = False
    header = section.header
    clear_container(header)
    table = header.add_table(1, 3, Inches(6.5))
    table.alignment = WD_TABLE_ALIGNMENT.CENTER
    table.autofit = False
    repeat_table_header(table.rows[0])
    prevent_row_split(table.rows[0])
    remove_table_borders(table)
    widths = [Inches(2.75), Inches(1.0), Inches(2.75)]
    for c, w in zip(table.rows[0].cells, widths):
        set_cell_width(c, w); set_cell_margins(c, 0, 0, 0, 0)
        c.vertical_alignment = WD_CELL_VERTICAL_ALIGNMENT.CENTER
    p = table.cell(0, 0).paragraphs[0]; p.alignment = WD_ALIGN_PARAGRAPH.LEFT
    set_run_font(p.add_run('Echo'), 10, italic=True)
    p = table.cell(0, 1).paragraphs[0]; p.alignment = WD_ALIGN_PARAGRAPH.CENTER
    add_picture_with_alt(p.add_run(), LOGO, Inches(0.55), 'Hawassa University crest')
    p = table.cell(0, 2).paragraphs[0]; p.alignment = WD_ALIGN_PARAGRAPH.RIGHT
    set_run_font(p.add_run('Nov 2026 G.C.'), 10)


def make_footer(section, fmt='decimal', start=1):
    section.footer.is_linked_to_previous = False
    clear_container(section.footer)
    add_page_number(section.footer.add_paragraph())
    set_page_number_format(section, fmt, start)


def add_centered(doc, text, size=12, bold=False, italic=False, before=0, after=0):
    p = doc.add_paragraph()
    p.alignment = WD_ALIGN_PARAGRAPH.CENTER
    p.paragraph_format.space_before = Pt(before)
    p.paragraph_format.space_after = Pt(after)
    set_run_font(p.add_run(text), size, bold, italic)
    return p


def add_body(doc, text, first_indent=True, after=6):
    p = doc.add_paragraph()
    p.alignment = WD_ALIGN_PARAGRAPH.JUSTIFY
    p.paragraph_format.line_spacing = 1.15
    p.paragraph_format.space_after = Pt(after)
    if first_indent:
        p.paragraph_format.first_line_indent = Inches(0.35)
    set_run_font(p.add_run(text), 11.5)
    return p


def add_heading(doc, text, level=1):
    p = doc.add_paragraph(style=f'Heading {level}')
    p.paragraph_format.keep_with_next = True
    p.paragraph_format.space_before = Pt(9 if level == 1 else 6)
    p.paragraph_format.space_after = Pt(4)
    size = 14 if level == 1 else 12.5
    set_run_font(p.add_run(text), size, bold=True)
    return p


def add_numbered(doc, items):
    for i, text in enumerate(items, 1):
        p = doc.add_paragraph()
        p.paragraph_format.left_indent = Inches(0.35)
        p.paragraph_format.first_line_indent = Inches(-0.28)
        p.paragraph_format.space_after = Pt(4)
        p.paragraph_format.line_spacing = 1.1
        set_run_font(p.add_run(f'{i}. '), 11.5, bold=True)
        set_run_font(p.add_run(text), 11.5)


def add_table(doc, headers, rows, widths, font_size=10.5):
    table = doc.add_table(rows=1, cols=len(headers))
    table.alignment = WD_TABLE_ALIGNMENT.CENTER
    table.autofit = False
    set_table_borders(table)
    repeat_table_header(table.rows[0])
    for idx, (cell, header, width) in enumerate(zip(table.rows[0].cells, headers, widths)):
        set_cell_width(cell, width); set_cell_margins(cell)
        shade_cell(cell, '1F4E78')
        cell.vertical_alignment = WD_CELL_VERTICAL_ALIGNMENT.CENTER
        p = cell.paragraphs[0]
        p.alignment = WD_ALIGN_PARAGRAPH.CENTER if idx == 0 else WD_ALIGN_PARAGRAPH.LEFT
        set_run_font(p.add_run(header), font_size, bold=True, color='FFFFFF')
    for ridx, row in enumerate(rows):
        cells = table.add_row().cells
        prevent_row_split(table.rows[-1])
        for idx, (cell, value, width) in enumerate(zip(cells, row, widths)):
            set_cell_width(cell, width); set_cell_margins(cell)
            cell.vertical_alignment = WD_CELL_VERTICAL_ALIGNMENT.CENTER
            if ridx % 2:
                shade_cell(cell, 'EEF3F8')
            p = cell.paragraphs[0]
            p.paragraph_format.space_after = Pt(0)
            p.alignment = WD_ALIGN_PARAGRAPH.CENTER if idx == 0 else WD_ALIGN_PARAGRAPH.LEFT
            set_run_font(p.add_run(str(value)), font_size)
    doc.add_paragraph().paragraph_format.space_after = Pt(0)
    return table


def add_hyperlink(paragraph, text, url):
    part = paragraph.part
    r_id = part.relate_to(url, 'http://schemas.openxmlformats.org/officeDocument/2006/relationships/hyperlink', is_external=True)
    hyperlink = OxmlElement('w:hyperlink')
    hyperlink.set(qn('r:id'), r_id)
    new_run = OxmlElement('w:r')
    rPr = OxmlElement('w:rPr')
    color = OxmlElement('w:color'); color.set(qn('w:val'), '0563C1')
    underline = OxmlElement('w:u'); underline.set(qn('w:val'), 'single')
    rPr.extend([color, underline])
    new_run.append(rPr)
    t = OxmlElement('w:t'); t.text = text
    new_run.append(t); hyperlink.append(new_run); paragraph._p.append(hyperlink)


def add_reference(doc, number, citation, link_text=None, url=None):
    p = doc.add_paragraph()
    p.paragraph_format.left_indent = Inches(0.38)
    p.paragraph_format.first_line_indent = Inches(-0.38)
    p.paragraph_format.line_spacing = 1.08
    p.paragraph_format.space_after = Pt(7)
    set_run_font(p.add_run(f'[{number}] {citation}'), 10.5)
    if url:
        set_run_font(p.add_run(' '), 10.5)
        add_hyperlink(p, link_text or url, url)


def build():
    ensure_assets()
    create_gantt()
    doc = Document()

    # Base styles.
    normal = doc.styles['Normal']
    normal.font.name = 'Times New Roman'; normal.font.size = Pt(11.5)
    normal._element.rPr.rFonts.set(qn('w:ascii'), 'Times New Roman')
    normal._element.rPr.rFonts.set(qn('w:hAnsi'), 'Times New Roman')
    for name in ('Title', 'Heading 1', 'Heading 2'):
        style = doc.styles[name]
        style.font.name = 'Times New Roman'
        style._element.rPr.rFonts.set(qn('w:ascii'), 'Times New Roman')
        style._element.rPr.rFonts.set(qn('w:hAnsi'), 'Times New Roman')
        style.font.color.rgb = RGBColor(0, 0, 0)
        style.font.bold = True
    title_ppr = doc.styles['Title']._element.get_or_add_pPr()
    title_border = title_ppr.find(qn('w:pBdr'))
    if title_border is not None:
        title_ppr.remove(title_border)

    cover = doc.sections[0]
    configure_section(cover, top=0.45, bottom=0.45)
    clear_container(cover.header); clear_container(cover.footer)
    p = doc.add_paragraph(); p.alignment = WD_ALIGN_PARAGRAPH.CENTER
    add_picture_with_alt(p.add_run(), LOGO, Inches(1.02), 'Hawassa University crest')
    p.paragraph_format.space_after = Pt(7)
    add_centered(doc, 'HAWASSA UNIVERSITY', 16, True)
    add_centered(doc, 'INSTITUTE OF TECHNOLOGY', 15, True)
    add_centered(doc, 'FACULTY OF INFORMATICS', 14, True)
    add_centered(doc, 'DEPARTMENT OF INFORMATION SYSTEM', 13.5, True, after=8)
    title = add_centered(doc, 'Echo (Classroom Audio Insight Software)', 21, True, after=3); title.style = doc.styles['Title']
    add_centered(doc, SUBTITLE, 15, True, after=9)
    add_centered(doc, 'A PROJECT SUBMITTED IN PARTIAL FULFILLMENT OF THE', 12.5, True)
    add_centered(doc, 'REQUIREMENTS FOR THE AWARD OF BACHELOR OF SCIENCE BSc', 12.5, True)
    add_centered(doc, 'IN INFORMATION SYSTEM', 12.5, True, after=7)

    team = doc.add_table(rows=1, cols=2); team.alignment = WD_TABLE_ALIGNMENT.CENTER; team.autofit = False
    remove_table_borders(team); repeat_table_header(team.rows[0])
    for cell, width in zip(team.rows[0].cells, (Inches(4.7), Inches(1.4))): set_cell_width(cell, width)
    set_run_font(team.cell(0,0).paragraphs[0].add_run('Name'), 11.5, True)
    p = team.cell(0,1).paragraphs[0]; p.alignment = WD_ALIGN_PARAGRAPH.RIGHT
    set_run_font(p.add_run('Student ID'), 11.5, True)
    for i, (name, sid) in enumerate(TEAM, 1):
        cells = team.add_row().cells; set_cell_width(cells[0], Inches(4.7)); set_cell_width(cells[1], Inches(1.4))
        set_cell_margins(cells[0], 30, 0, 30, 0); set_cell_margins(cells[1], 30, 0, 30, 0)
        set_run_font(cells[0].paragraphs[0].add_run(f'{i}. {name}'), 11.2)
        cells[1].paragraphs[0].alignment = WD_ALIGN_PARAGRAPH.RIGHT
        set_run_font(cells[1].paragraphs[0].add_run(sid), 11.2)
    add_centered(doc, 'Advisor: Mr. Mulat S.', 12, True, before=7, after=3)
    add_centered(doc, 'Submitted to: Information System Department', 11.5)
    add_centered(doc, 'Hawassa, Ethiopia', 11.5)
    add_centered(doc, 'November 2026', 11.5, before=4)

    prelim = doc.add_section(WD_SECTION.NEW_PAGE)
    configure_section(prelim)
    make_header(prelim); make_footer(prelim, 'lowerRoman', 1)
    add_centered(doc, 'TABLE OF CONTENTS', 17, True, after=10)
    toc = [
        ('CHAPTER ONE INTRODUCTION', '1'), ('Echo in This Project', '1'), ('1.1 Background of the Study', '1'),
        ('1.2 Statement of the Problem', '2'), ('1.3 Objectives of the Project', '3'),
        ('1.4 Scope of the Project', '3'), ('1.5 Limitations of the Project', '4'),
        ('1.6 Significance of the Project', '4'), ('1.7 Methodology', '4'),
        ('1.8 Development Environment', '7'), ('1.9 Feasibility Study', '7'),
        ('1.10 Project Schedule and Cost Estimate', '8'), ('1.11 Organization of the Project', '9'),
        ('REFERENCES', '9'),
    ]
    for i, (label, pg) in enumerate(toc):
        p = doc.add_paragraph()
        p.paragraph_format.space_after = Pt(2)
        p.paragraph_format.left_indent = Inches(0.18 if i and not label.startswith('CHAPTER') and label != 'REFERENCES' else 0)
        set_run_font(p.add_run(label), 11.2, bold=(label.startswith('CHAPTER') or label == 'REFERENCES'))
        tab = p.paragraph_format.tab_stops.add_tab_stop(Inches(6.2))
        p.add_run('\t')
        set_run_font(p.add_run(pg), 11.2, bold=(label.startswith('CHAPTER') or label == 'REFERENCES'))
    add_centered(doc, 'LIST OF TABLES AND FIGURES', 16, True, before=15, after=8)
    for item in [
        'Table 1.1 Development Environment',
        'Figure 1.1 Role Specific Project Gantt Chart',
        'Table 1.2 Detailed Project Cost Estimate',
    ]:
        p = doc.add_paragraph(); p.paragraph_format.space_after = Pt(3)
        set_run_font(p.add_run(item), 11.2, bold=True)

    main = doc.add_section(WD_SECTION.NEW_PAGE)
    configure_section(main)
    make_header(main); make_footer(main, 'decimal', 1)
    add_centered(doc, 'CHAPTER ONE INTRODUCTION', 18, True, after=14)

    add_heading(doc, 'Echo in This Project', 1)
    add_body(doc, 'Echo (Classroom Audio Insight Software) is the name used in this project for the proposed Android software that helps teachers revisit their own classroom audio and draw useful insights for reflection. In the present documentation, “insight” means information a teacher can inspect and interpret in context, such as recording duration, capture-quality warnings, a privately replayed excerpt, and a teacher-written reflection. It does not mean an automatic judgment of teaching quality or pupil learning. The planned system also includes an optional, separately authorized research service for limited summaries; classroom audio remains on the teacher’s device.', first_indent=False)

    add_heading(doc, '1.1 Background of the Study', 1)
    add_body(doc, 'Education develops the knowledge, skills, values, and capabilities people need to participate in society and improve their lives. Sustainable Development Goal 4 therefore calls for inclusive and equitable quality education, but quality cannot be reduced to enrollment or examination results alone. It also depends on qualified teachers, supportive leadership, appropriate learning materials, safe schools, and opportunities for teachers to improve their practice. UNESCO reports that, worldwide, about 82 percent of primary teachers meet national professional qualification requirements, showing both the scale of the teaching profession and the continuing need for professional support [1].')
    add_body(doc, 'Across sub-Saharan Africa, education systems have expanded access while still facing uneven teacher preparation, large classes, limited instructional resources, and difficulty providing frequent school-based support. UNESCO reports that 17 percent of teachers in the region are not trained [1]. Evidence from a broader meta-analysis indicates that sustained teacher coaching can improve instruction, while African programmes that depend on repeated face-to-face mentoring can be expensive and labour intensive at scale [7]. Governments and partners have therefore tested peer, remote, hybrid, and technology-supported approaches to continuing professional development [2]. These conditions create a practical need for tools that strengthen, rather than replace, the work of school leaders, supervisors, and teacher educators.')
    add_body(doc, 'Ethiopia has made progress in expanding primary education, yet access and learning quality remain major concerns. UNICEF reports that at least 13 million children are out of school, the estimated primary pupil-teacher ratio is 39 to 1, and 90 percent of ten-year-olds cannot read or understand a simple text-based sentence [3]. Current national and partner-supported initiatives emphasize teacher training, school leadership, materials, and improved learning environments. The World Bank-supported Ethiopia Education Transformation Operation for Learning is intended to benefit about 370,000 pre-primary and primary teachers and 15,000 school leaders [4]. Ethiopia has also implemented Assessment for Learning, an approach that encourages continuous assessment and recording of pupil progress so teachers can adjust instruction [5].')
    add_body(doc, 'In the Sidama Region, teacher and school-leadership development is also visible in current education initiatives. UNICEF documents teachers and administrators in Sidama receiving leadership and pedagogical support and using school-based structures to assist pupils [6]. These efforts show that teacher development is a regional priority, but they do not establish how often individual teachers in the intended Hawassa schools are reviewed, what supervisors or peer reviewers write, how feedback is delivered, or whether teachers can revisit evidence from a lesson after the reviewer has left. Those details must be established through a local gap and requirements study rather than assumed from national programmes.')
    add_body(doc, 'The proposed project responds to that practical gap with an Android-based system that helps a teacher record a lesson, retain the recording privately on the device, review limited duration and capture-quality information, add context, and select a manageable practice for follow-up. The system is intended for low-capability Android phones and unreliable connectivity. It does not replace classroom observation, professional judgment, or human coaching. Instead, it gives the teacher a repeatable private reflection workflow and gives reviewers a clearer basis for discussion when the teacher chooses to share a summary.')
    add_body(doc, 'Echo is organized as a software project with a requirements study, system analysis, design, development, implementation, testing, documentation, and controlled school evaluation. Before finalizing features, the team will document existing teacher assessment techniques and feedback mechanisms in the selected schools, identify the people who conduct reviews, examine anonymized examples of review forms and written comments, determine review frequency and follow-up practice, and convert the findings into traceable system requirements. This sequence makes the application a response to a verified operational gap rather than a technology solution built from assumptions.')

    add_heading(doc, '1.2 Statement of the Problem', 1)
    add_body(doc, 'Teachers need timely and specific feedback that helps them understand what happened during a lesson and decide what to improve next. In many school systems, teacher support is provided through classroom observation, supervisor visits, performance appraisal forms, workshops, peer discussion, or informal advice. These approaches can be useful, but their effectiveness depends on how frequently reviews occur, what evidence is recorded, how comments are communicated, whether agreed actions are followed up, and whether the teacher can revisit the lesson after the observation.')
    add_body(doc, 'For the intended Hawassa schools, the project team does not yet have a verified description of the complete review process. The gap study must therefore establish the previous and current teacher assessment techniques, the feedback channels used, the reviewers involved, the fields and comments written on existing forms, the normal frequency of reviews, the delay between observation and feedback, and the problems teachers and reviewers experience. Without that requirements baseline, a mobile application could duplicate an existing form, omit an important approval step, or offer feedback that does not fit school practice.')
    add_body(doc, 'General recording applications are not designed around teacher review cycles, evidence retention, privacy, class profiles, structured reflection, or reviewed pedagogy content. Cloud-based classroom-analysis services may require stable internet access and may move sensitive classroom audio outside the teacher\'s control. Automated speech or teaching-quality systems also require language-specific training data and validation that are not currently available for the intended Sidaamu Afoo, Amharic, Afaan Oromo, and English primary-classroom settings.')
    add_body(doc, 'The project problem is therefore to design and implement a simple Android system that fits verified teacher review and feedback practices, works offline on representative devices, keeps classroom audio encrypted on the phone, provides bounded on-device analysis, supports teacher reflection, includes a clear support contact, and produces documentation that teachers can follow. The system must help rather than rank teachers, and it must make unavailable or unvalidated measurements explicit.')

    add_heading(doc, '1.3 Objectives of the Project', 1)
    add_heading(doc, '1.3.1 General Objective', 2)
    add_body(doc, 'The general objective of this project is to analyze requirements and document the design of Echo (Classroom Audio Insight Software), an Android-based on-device audio analysis and pedagogy feedback system intended to support private teacher reflection in selected primary schools in Hawassa.', first_indent=False)
    add_heading(doc, '1.3.2 Specific Objectives', 2)
    add_numbered(doc, [
        'To gather requirements by studying existing teacher assessment techniques and feedback mechanisms, including the reviewers involved, the content written in review forms or comments, review frequency, feedback delivery, and follow-up practice.',
        'To analyze, prioritize, and document the functional, nonfunctional, privacy, usability, device, language, and support requirements derived from teachers, reviewers, school leaders, existing documents, and the current software baseline.',
        'To document the proposed system architecture, tier boundaries, user flow, local data model, audio-processing pipeline, security controls, research-summary boundary, and user interfaces for low-capability Android phones.',
        'To specify the scoped Android features: class profiles, teacher-controlled Record and Stop, encrypted local storage, interrupted-session recovery, duration and capture-quality information, private playback, weekly reflection, reviewed practice cards, deletion and retention controls, and a Help and Support page with a configurable teacher contact line.',
        'To define the optional, separately authorized research-summary transfer, including its allowlisted numeric and enumerated data and the exclusion of raw audio, excerpts, transcripts, child identities, and private notes.',
        'To prepare a traceable design and verification plan covering security, accessibility, physical devices, microphone compatibility, usability, and acceptance criteria for a later implementation phase.',
        'To prepare the semester documentation and a draft user manual explaining installation, permissions, microphone readiness, recording, review, reflection, deletion, privacy, troubleshooting, and teacher support.',
    ])

    add_heading(doc, '1.4 Scope of the Project', 1)
    add_body(doc, 'The project covers a requirements and gap study in selected Hawassa primary schools and the design, development, and testing of an Android 10 or later application for approximately 2 GB RAM devices. The application scope includes teacher and class profiles; explicit Record and Stop controls; wired or USB microphone readiness where required; encrypted, app-private, backup-excluded audio storage; committed-chunk recovery; private playback; duration and capture-quality information; context correction; teacher-selected goals; reviewed practice cards; weekly follow-up; usefulness ratings; deletion; a 30-day rolling retention rule; accessibility and localization readiness; and a Help and Support page containing a configurable phone or email contact for teachers. The project also includes an administrator-approved research path for minimized summary data, a controlled one-month pilot planned for eight Grades 1 to 4 teachers in four Hawassa schools, and a user manual for teachers and project support staff.')
    add_body(doc, 'The project does not include teacher ranking, disciplinary surveillance, child identification, speaker recognition, automatic transcription, raw-audio upload, or claims that audio alone measures pupil understanding or teaching quality. Grades 11 and 12, national deployment, automatic pedagogical judgment, unreviewed translations, third-party AI processing of classroom recordings, and causal claims about pupil learning remain outside the project. Any later addition of these capabilities would require a separately approved scope, evidence, privacy review, and device validation.')

    add_heading(doc, '1.5 Limitations of the Project', 1)
    add_body(doc, 'The project is limited by its small planned sample of eight teachers in four schools, the short one-month pilot, variation among Android phones and microphones, noise and overlapping speech in real classrooms, incomplete multilingual content, and dependence on school authorization, consent, educator review, and compatible hardware. These limitations matter because a short feasibility project cannot represent all Sidama or Ethiopian schools, audio cannot capture visual classroom activity or prove understanding, and results from one device or language cannot automatically be generalized to others. The project will therefore report technical and usability findings within the tested setting, identify unavailable measures, and avoid claims of causal improvement in teaching or pupil learning.')

    add_heading(doc, '1.6 Significance of the Project', 1)
    add_body(doc, 'For teachers, the system provides a private way to revisit selected lesson evidence, add missing context, choose one practical action, and obtain help through a visible support contact. For reviewers and school leaders, the requirements study and system records can clarify how existing assessment and feedback practices operate without replacing professional observation or introducing teacher scores. For Hawassa University and the student team, the project integrates requirements engineering, mobile development, audio processing, interface design, security, testing, documentation, and responsible data management. For future work, the project creates a traceable baseline showing which features were implemented, which were tested, and which require further evidence or authorization.')

    add_heading(doc, '1.7 Methodology', 1)
    add_body(doc, 'The project will follow an iterative, user-centered systems-development approach. It will begin with a gap and requirements study using document review, semi-structured interviews, observation of the current review workflow, and anonymized examples of teacher assessment or feedback forms. Teachers, reviewers, school leaders, and relevant university stakeholders will be asked who performs reviews, what is written, how often reviews occur, how feedback and follow-up are handled, and what problems a mobile system should solve. The findings will be converted into prioritized and testable requirements, then traced through analysis, design, development, implementation, testing, pilot use, and the user manual. Classroom recording will not begin until the required institutional and school permissions and consent arrangements are complete.')
    add_heading(doc, '1.7.1 Data Collection', 2)
    add_body(doc, 'In-person interviews are the primary method for gathering requirements. The team will interview teachers, academic vice principals, department heads, and other relevant reviewers in participating Hawassa schools. It will also interview private and international schools in Hawassa to compare their teacher review and feedback practices. With each school’s permission, the team will inspect paper observation tools and related records, including the photographed example from Diaspora Elementary School, and record the date, role, workflow, written feedback, frequency, and follow-up described by participants. Interviews will inform requirements; statements from one school will not be treated as facts about all schools.')
    add_heading(doc, '1.7.2 Data Analysis', 2)
    add_body(doc, 'Requirements data will be analyzed using a structured coding matrix. Existing forms and interview responses will be coded by reviewer role, assessment technique, information recorded, feedback channel, review frequency, turnaround time, follow-up action, privacy concern, and requested feature. Repeated needs will be grouped into functional and nonfunctional requirements, and conflicting requests will be resolved with stakeholders. Each approved requirement will receive an identifier, priority, source, acceptance criterion, design link, implementation status, and test reference.')
    add_body(doc, 'Pilot usage and testing data will be summarized with counts, proportions, medians, ranges, completion rates, device-specific failures, and clearly stated denominators. Interview, correction, support, and usability comments will be analyzed thematically. Findings will distinguish technical reliability, usability, teacher-reported usefulness, reviewer observations, and any later teaching-practice evidence; these categories will not be combined into an unsupported effectiveness claim.')
    add_heading(doc, '1.7.3 System Development Methodology', 2)
    add_body(doc, 'This semester will establish the requirements baseline and document the interface, architecture, local data, audio pipeline, privacy, support contact, and optional research-summary design. The documentation will trace each proposed component to a requirement and a later verification criterion. A subsequent development phase can use small increments for integration and testing. Git and GitHub will support version control and review of the design artifacts. Proposed features will remain clearly separated from the current development build and verified features.')
    add_heading(doc, '1.7.4 Testing and Evaluation', 2)
    add_body(doc, 'Testing will include unit tests for domain rules; integration tests for storage, encryption, recovery, retention, and research contracts; security tests for permissions, access, transport, deletion, and withdrawal; accessibility checks; and acceptance tests for setup, Record, Stop, Review, Reflect, Support, and Delete. Physical-device testing will examine microphone routing, screen-off continuity, calls and interruptions, playback, battery use, memory, storage, font scaling, localization space, and performance on representative low-capability phones. The user manual will be tested by asking intended users to complete core tasks without developer assistance.')
    add_heading(doc, '1.7.5 Deployment and Operations', 2)
    add_body(doc, 'The offline application will remain usable without a configured server. If the research component is approved, a separate build will communicate only with a reviewed HTTPS origin and will transfer allowlisted summaries under consent-bound credentials. Teachers will receive installation guidance, privacy information, the user manual, and the support contact before use. Operational records will document device assignment, microphone compatibility, support requests, releases, retention, withdrawal, and unresolved issues.')

    doc.add_page_break()
    add_heading(doc, '1.8 Development Environment', 1)
    add_body(doc, 'Table 1.1 summarizes the planned development environment. Final versions will be copied from the verified build configuration in the implementation chapter.', first_indent=False)
    add_centered(doc, 'Table 1.1 Development Environment', 11, italic=True, after=4)
    add_table(doc, ['Category', 'Tools and Constraints'], [
        ('Mobile platform', 'Android 10 or later; representative phones near 2 GB RAM'),
        ('Mobile language and UI', 'Kotlin and Jetpack Compose'),
        ('Audio capture and encoding', 'AudioRecord, foreground service, MediaCodec AAC-LC'),
        ('Local security', 'AES-GCM, Android Keystore, app-private backup-excluded storage'),
        ('Background work', 'Android JobScheduler and bounded local work'),
        ('Research backend', 'Python, Django 5.2 LTS, PostgreSQL, HTTPS'),
        ('Version control and CI', 'Git, GitHub, reviewed pull requests, automated checks'),
        ('Design and documentation', 'Draw.io or Mermaid, Microsoft Word compatible DOCX, user manual'),
        ('Testing', 'JVM/Python tests, Android lint, physical devices, microphone checks'),
    ], [Inches(2.0), Inches(4.5)])

    add_heading(doc, '1.9 Feasibility Study', 1)
    add_heading(doc, '1.9.1 Technical Feasibility', 2)
    add_body(doc, 'Android provides standard APIs for foreground audio capture, AAC encoding, app-private storage, cryptography, background scheduling, and local interfaces. A development baseline already implements several of these components. Feasibility still depends on measured microphone routing, recovery, resource use, accessibility, and performance across the target phones. Any learned audio model must be licensed, small enough for the device, language appropriate, and separately validated before it is exposed to teachers.')
    add_heading(doc, '1.9.2 Operational Feasibility', 2)
    add_body(doc, 'The intended workflow is short: prepare the class profile, check the microphone, record, stop, review, reflect, and obtain support if needed. Offline operation reduces dependence on connectivity, while teacher-controlled deletion and visible retention rules support trust. The requirements study will determine whether this flow fits existing school review practice, who should receive support requests, and how the system should coexist with human observation and feedback.')
    add_heading(doc, '1.9.3 Economic Feasibility', 2)
    add_body(doc, 'The project uses open-source development tools and teacher-owned Android phones where compatible, but the complete budget must include hardware, printing, school visits, hosting, and AI-assisted development subscriptions. Eight microphones are budgeted at the current working estimate of 3,000 ETB each, and the hosting line uses the current shared-hosting reference [9]. The ETB subtotal is 34,881 ETB, excluding the separate 80 USD subscription estimate and any unapproved educator-review or localization payment. Prices, stock, taxes, exchange rates, and school arrangements must be reconfirmed before purchase.')
    add_heading(doc, '1.9.4 Ethical and Legal Feasibility', 2)
    add_body(doc, 'Classroom audio may contain personal data. Collection therefore requires an approved purpose, clear information, appropriate teacher and child or guardian arrangements, restricted access, secure storage, defined retention, deletion, and withdrawal procedures. Ethiopia\'s Personal Data Protection Proclamation No. 1321/2024 provides the relevant national legal framework [8]. The project will not record a classroom until the responsible university and school authorities approve the protocol and the required consent or assent process is complete.')

    add_heading(doc, '1.10 Project Schedule and Cost Estimate', 1)
    add_heading(doc, '1.10.1 Role Specific Project Schedule', 2)
    add_body(doc, 'Figure 1.1 presents a sixteen-week schedule derived from the team handoff. The assignments separate requirements, design, infrastructure, hardware, implementation, testing, pilot support, and documentation while preserving coordination points among owners.', first_indent=False)
    p = doc.add_paragraph(); p.alignment = WD_ALIGN_PARAGRAPH.CENTER
    add_picture_with_alt(p.add_run(), GANTT, Inches(6.45), 'Sixteen-week role-specific Gantt chart for the six Echo team members')
    p.paragraph_format.space_after = Pt(2)
    add_centered(doc, 'Figure 1.1 Role Specific Project Gantt Chart', 10.5, italic=True, after=7)
    add_body(doc, 'Hanna owns the branching, continuous-integration, and release-policy work; Selwa owns the teacher flow and user-interface redesign; Amensisa owns the research-service database schema and migration plan; Yordanos owns the Android architecture and integration evidence; Yoseph owns microphone research, procurement checks, and device support; and Anteneh coordinates requirements, the audio-analysis pipeline, integration, pilot analysis, and final delivery. All members contribute to the user manual, documentation, and defense package.', first_indent=False)
    add_heading(doc, '1.10.2 Detailed Project Cost Estimate', 2)
    add_body(doc, 'Table 1.2 records the currently identified direct costs. The AI subscription remains in USD to avoid using an unstated exchange rate.', first_indent=False)
    add_centered(doc, 'Table 1.2 Detailed Project Cost Estimate', 11, italic=True, after=4)
    add_table(doc, ['No.', 'Item', 'Quantity and Basis', 'Unit Cost', 'Estimate'], [
        ('1', 'Wireless microphones', '8 microphones', '3,000 ETB', '24,000 ETB'),
        ('2', 'Documentation printing', '2 copies', '1,100 ETB', '2,200 ETB'),
        ('3', 'School travel and operations', '4 schools', '800 ETB', '3,200 ETB'),
        ('4', 'Approved Ethiopian hosting', 'Working annual estimate', '5,481 ETB', '5,481 ETB'),
        ('5', 'AI tool subscriptions', 'Project subscription allowance', '80 USD', '80 USD'),
        ('6', 'Teacher Android phones', 'Teacher-owned where compatible', '0 ETB', '0 ETB'),
        ('7', 'Open-source libraries', 'Kotlin, Android and Python stack', '0 ETB', '0 ETB'),
        ('', 'Confirmed ETB subtotal', 'Excludes USD subscription and unapproved costs', '', '34,881 ETB'),
        ('', 'Separate USD subtotal', 'Convert only using an approved dated rate', '', '80 USD'),
    ], [Inches(0.43), Inches(1.35), Inches(2.45), Inches(0.95), Inches(1.15)], font_size=9.2)

    add_heading(doc, '1.11 Organization of the Project', 1)
    add_body(doc, 'The project is organized into six coordinated work packages. The first documents the existing teacher assessment and feedback system and establishes the requirements baseline. The second analyzes those requirements and produces the interface, architecture, database, audio-pipeline, privacy, and support designs. The third develops and integrates the Android application and the separately bounded research service. The fourth verifies the system through automated, security, accessibility, device, microphone, and usability testing. The fifth conducts the approved controlled school pilot and analyzes technical and user evidence. The sixth completes the user manual, project documentation, defense materials, and handover. The project lead coordinates scope and integration, while each role owner is responsible for the deliverables shown in Figure 1.1 and for cross-review where another owner\'s contract is affected.')

    add_centered(doc, 'REFERENCES', 18, True, after=12)
    references = [
        (1, 'UNESCO Global Education Monitoring Report, “Monitoring SDG 4: Quality education,” accessed Nov. 11, 2026.', 'UNESCO GEM quality education', 'https://www.unesco.org/gem-report/en/quality'),
        (2, 'Empower Learning Africa, “Teaching at the Right Level: Learning how to improve mentoring and monitoring support to teachers at scale in African government systems,” 2024.', 'UNESCO IICBA Africa Education Knowledge Platform', 'https://www.iicba.unesco.org/en/africa-education-knowledge-platform/teaching-right-level-learning-how-improve-mentoring-and-monitoring-support-teachers-scale-african'),
        (3, 'UNICEF Ethiopia, “Learning and development,” accessed Nov. 11, 2026.', 'UNICEF Ethiopia learning and development', 'https://www.unicef.org/ethiopia/learning-and-development'),
        (4, 'World Bank, “The Advancing Innovative Methods to Promote Learning AIM4Learning Program: Ethiopia,” Feb. 20, 2025.', 'World Bank AIM4Learning Ethiopia', 'https://www.worldbank.org/en/news/factsheet/2025/02/20/the-advancing-innovative-methods-to-promote-learning-aim4learning-program-ethiopia'),
        (5, 'UNICEF, “Formative assessment places student learning at the heart of teaching Ethiopia,” accessed Nov. 11, 2026.', 'UNICEF formative assessment Ethiopia', 'https://www.unicef.org/documents/formative-assessment-places-student-learning-heart-teaching-ethiopia'),
        (6, 'D. Bizuwerk, “Empowered female teachers are empowering female students in Ethiopia,” UNICEF Ethiopia, Sept. 16, 2025.', 'UNICEF teacher leadership in Sidama', 'https://www.unicef.org/ethiopia/stories/empowered-female-teachers-are-empowering-female-students-ethiopia'),
        (7, 'M. A. Kraft, D. Blazar, and D. Hogan, “The effect of teacher coaching on instruction and achievement: A meta-analysis of the causal evidence,” Review of Educational Research, vol. 88, no. 4, pp. 547-588, 2018.', 'DOI 10.3102/0034654318759268', 'https://doi.org/10.3102/0034654318759268'),
        (8, 'Federal Democratic Republic of Ethiopia, Personal Data Protection Proclamation No. 1321/2024, 2024.', 'Official proclamation page', 'https://justice.gov.et/en/law/personal-data-protection-proclamation/'),
        (9, 'Zergaw Cloud, “Shared Hosting Platform,” accessed Nov. 11, 2026.', 'Zergaw shared hosting', 'https://zergaw.com/shared-hosting-platform/'),
    ]
    for ref in references:
        add_reference(doc, *ref)

    # Metadata and field refresh.
    settings = doc.settings._element
    update = settings.find(qn('w:updateFields'))
    if update is None:
        update = OxmlElement('w:updateFields'); settings.append(update)
    update.set(qn('w:val'), 'true')
    doc.core_properties.title = 'Echo Chapter One Advisor Revision'
    doc.core_properties.subject = 'Android based on device audio analysis and pedagogy feedback system'
    doc.core_properties.author = '; '.join(name for name, _ in TEAM)
    doc.core_properties.comments = 'Revised Chapter One incorporating advisor feedback dated November 2026.'

    # Ensure every packaged image has alt text, including inherited header parts.
    for part in doc.part.package.parts:
        element = getattr(part, 'element', None)
        if element is None:
            continue
        for doc_pr in element.iter(qn('wp:docPr')):
            if not doc_pr.get('descr'):
                doc_pr.set('descr', 'Document image')
            if not doc_pr.get('title'):
                doc_pr.set('title', doc_pr.get('descr'))

    doc.save(OUTPUT)
    print(OUTPUT)


if __name__ == '__main__':
    build()
