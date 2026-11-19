from __future__ import annotations

import shutil
from pathlib import Path

from docx import Document
from docx.enum.section import WD_SECTION
from docx.enum.table import WD_CELL_VERTICAL_ALIGNMENT, WD_TABLE_ALIGNMENT
from docx.enum.text import WD_ALIGN_PARAGRAPH, WD_BREAK, WD_LINE_SPACING
from docx.oxml import OxmlElement
from docx.oxml.ns import qn
from docx.shared import Inches, Pt, RGBColor


REFERENCE = Path("/Users/anteneh/Downloads/FINAL project SSP.docx")
OUTPUT = Path("/Users/anteneh/Documents/Repos/Echo/Echo Final Year Project Documentation Framework.docx")
LOGO = Path("/private/tmp/echo-doc-ssp/reference-package/word/media/image1.jpg")

TEAM = [
    ("Selwa Jemal", "3472/16"),
    ("Hanna Woldeselassie", "2328/16"),
    ("Yordanos Tesfaye", "3977/16"),
    ("Yoseph Zekarias", "4000/16"),
    ("Amensisa Abebe", "1244/16"),
    ("Anteneh Fisseha", "1008/16"),
]

TITLE = "Echo"
SUBTITLE = "An Offline On Device Audio Analysis Mobile Application for Pedagogy Feedback"


def set_run_font(run, size=12, bold=False, italic=False, color="000000"):
    run.font.name = "Times New Roman"
    run._element.get_or_add_rPr().rFonts.set(qn("w:ascii"), "Times New Roman")
    run._element.get_or_add_rPr().rFonts.set(qn("w:hAnsi"), "Times New Roman")
    run._element.get_or_add_rPr().rFonts.set(qn("w:cs"), "Times New Roman")
    run.font.size = Pt(size)
    run.font.bold = bold
    run.font.italic = italic
    run.font.color.rgb = RGBColor.from_string(color)
    return run


def clear_container(container):
    element = container._element
    for child in list(element):
        element.remove(child)


def clear_body_keep_sectpr(doc):
    body = doc._element.body
    for child in list(body):
        if child.tag != qn("w:sectPr"):
            body.remove(child)


def set_cell_margins(cell, top=90, start=110, bottom=90, end=110):
    tc = cell._tc
    tcPr = tc.get_or_add_tcPr()
    tcMar = tcPr.first_child_found_in("w:tcMar")
    if tcMar is None:
        tcMar = OxmlElement("w:tcMar")
        tcPr.append(tcMar)
    for edge, value in (("top", top), ("start", start), ("bottom", bottom), ("end", end)):
        node = tcMar.find(qn(f"w:{edge}"))
        if node is None:
            node = OxmlElement(f"w:{edge}")
            tcMar.append(node)
        node.set(qn("w:w"), str(value))
        node.set(qn("w:type"), "dxa")


def set_table_borders(table, color="D9D9D9", size="6"):
    tblPr = table._tbl.tblPr
    borders = tblPr.first_child_found_in("w:tblBorders")
    if borders is None:
        borders = OxmlElement("w:tblBorders")
        tblPr.append(borders)
    for name in ("top", "left", "bottom", "right", "insideH", "insideV"):
        edge = borders.find(qn(f"w:{name}"))
        if edge is None:
            edge = OxmlElement(f"w:{name}")
            borders.append(edge)
        edge.set(qn("w:val"), "single")
        edge.set(qn("w:sz"), size)
        edge.set(qn("w:space"), "0")
        edge.set(qn("w:color"), color)


def remove_table_borders(table):
    set_table_borders(table, color="FFFFFF", size="0")
    borders = table._tbl.tblPr.first_child_found_in("w:tblBorders")
    if borders is not None:
        for edge in borders:
            edge.set(qn("w:val"), "nil")


def shade_cell(cell, fill):
    tcPr = cell._tc.get_or_add_tcPr()
    shd = tcPr.find(qn("w:shd"))
    if shd is None:
        shd = OxmlElement("w:shd")
        tcPr.append(shd)
    shd.set(qn("w:fill"), fill)


def repeat_table_header(row):
    trPr = row._tr.get_or_add_trPr()
    tblHeader = OxmlElement("w:tblHeader")
    tblHeader.set(qn("w:val"), "true")
    trPr.append(tblHeader)


def add_picture_with_alt(run, image_path, width, alt_text):
    """Insert an image and give Word/a11y tools a meaningful description."""
    shape = run.add_picture(str(image_path), width=width)
    shape._inline.docPr.set("descr", alt_text)
    shape._inline.docPr.set("title", alt_text)
    return shape


def set_repeat_keep(paragraph, keep_next=False, keep_lines=True):
    pPr = paragraph._p.get_or_add_pPr()
    if keep_next:
        keep = pPr.find(qn("w:keepNext"))
        if keep is None:
            keep = OxmlElement("w:keepNext")
            pPr.append(keep)
    if keep_lines:
        keep = pPr.find(qn("w:keepLines"))
        if keep is None:
            keep = OxmlElement("w:keepLines")
            pPr.append(keep)


def set_page_number_format(section, fmt="decimal", start=1):
    sectPr = section._sectPr
    pgNumType = sectPr.find(qn("w:pgNumType"))
    if pgNumType is None:
        pgNumType = OxmlElement("w:pgNumType")
        sectPr.append(pgNumType)
    pgNumType.set(qn("w:fmt"), fmt)
    pgNumType.set(qn("w:start"), str(start))


def add_page_field(paragraph):
    paragraph.alignment = WD_ALIGN_PARAGRAPH.CENTER
    run = paragraph.add_run()
    set_run_font(run, 10)
    fld_begin = OxmlElement("w:fldChar")
    fld_begin.set(qn("w:fldCharType"), "begin")
    instr = OxmlElement("w:instrText")
    instr.set(qn("xml:space"), "preserve")
    instr.text = " PAGE "
    fld_sep = OxmlElement("w:fldChar")
    fld_sep.set(qn("w:fldCharType"), "separate")
    text = OxmlElement("w:t")
    text.text = "1"
    fld_end = OxmlElement("w:fldChar")
    fld_end.set(qn("w:fldCharType"), "end")
    run._r.extend([fld_begin, instr, fld_sep, text, fld_end])


def configure_page(section):
    section.page_width = Inches(8.5)
    section.page_height = Inches(11)
    section.top_margin = Inches(1)
    section.bottom_margin = Inches(0.85)
    section.left_margin = Inches(1)
    section.right_margin = Inches(1)
    section.header_distance = Inches(0.28)
    section.footer_distance = Inches(0.35)


def make_header(section):
    section.header.is_linked_to_previous = False
    header = section.header
    clear_container(header)
    table = header.add_table(rows=1, cols=3, width=Inches(6.5))
    table.alignment = WD_TABLE_ALIGNMENT.CENTER
    table.autofit = False
    widths = [Inches(2.7), Inches(1.1), Inches(2.7)]
    for cell, width in zip(table.rows[0].cells, widths):
        cell.width = width
        set_cell_margins(cell, 0, 0, 0, 0)
        cell.vertical_alignment = WD_CELL_VERTICAL_ALIGNMENT.CENTER
    remove_table_borders(table)
    repeat_table_header(table.rows[0])
    p = table.cell(0, 0).paragraphs[0]
    p.alignment = WD_ALIGN_PARAGRAPH.LEFT
    set_run_font(p.add_run("Echo"), 10)
    p = table.cell(0, 1).paragraphs[0]
    p.alignment = WD_ALIGN_PARAGRAPH.CENTER
    add_picture_with_alt(p.add_run(), LOGO, Inches(0.58), "Hawassa University crest")
    p = table.cell(0, 2).paragraphs[0]
    p.alignment = WD_ALIGN_PARAGRAPH.RIGHT
    set_run_font(p.add_run("2026 G.C."), 10)


def make_footer(section, fmt="decimal", start=1):
    section.footer.is_linked_to_previous = False
    footer = section.footer
    clear_container(footer)
    add_page_field(footer.add_paragraph())
    set_page_number_format(section, fmt, start)


def add_centered(doc, text, size=12, bold=False, italic=False, space_before=0, space_after=0):
    p = doc.add_paragraph()
    p.alignment = WD_ALIGN_PARAGRAPH.CENTER
    p.paragraph_format.space_before = Pt(space_before)
    p.paragraph_format.space_after = Pt(space_after)
    set_run_font(p.add_run(text), size, bold, italic)
    return p


def add_body(doc, text, indent=True, bold_lead=None):
    p = doc.add_paragraph()
    p.alignment = WD_ALIGN_PARAGRAPH.JUSTIFY
    p.paragraph_format.line_spacing = 1.5
    p.paragraph_format.space_after = Pt(6)
    if indent:
        p.paragraph_format.first_line_indent = Inches(0.35)
    if bold_lead and text.startswith(bold_lead):
        set_run_font(p.add_run(bold_lead), 12, bold=True)
        set_run_font(p.add_run(text[len(bold_lead):]), 12)
    else:
        set_run_font(p.add_run(text), 12)
    set_repeat_keep(p, keep_lines=True)
    return p


def add_heading(doc, text, level=1, page_break_before=False):
    p = doc.add_paragraph(style=f"Heading {level}")
    p.paragraph_format.page_break_before = page_break_before
    p.paragraph_format.space_before = Pt(10 if level > 1 else 12)
    p.paragraph_format.space_after = Pt(6)
    p.paragraph_format.keep_with_next = True
    p.alignment = WD_ALIGN_PARAGRAPH.LEFT
    size = {1: 17, 2: 14, 3: 12.5}.get(level, 12)
    set_run_font(p.add_run(text), size, bold=True)
    return p


def add_chapter_title(doc, text):
    p = doc.add_paragraph()
    p.paragraph_format.page_break_before = True
    p.paragraph_format.space_after = Pt(16)
    p.paragraph_format.keep_with_next = True
    p.alignment = WD_ALIGN_PARAGRAPH.CENTER
    set_run_font(p.add_run(text.upper()), 20, bold=True)
    return p


def add_front_title(doc, text):
    p = doc.add_paragraph()
    p.paragraph_format.space_after = Pt(14)
    p.alignment = WD_ALIGN_PARAGRAPH.CENTER
    set_run_font(p.add_run(text.upper()), 16, bold=True)
    return p


def add_bullet(doc, text, level=0):
    p = doc.add_paragraph()
    p.paragraph_format.left_indent = Inches(0.35 + level * 0.25)
    p.paragraph_format.first_line_indent = Inches(-0.2)
    p.paragraph_format.line_spacing = 1.3
    p.paragraph_format.space_after = Pt(4)
    set_run_font(p.add_run("• "), 12)
    set_run_font(p.add_run(text), 12)
    return p


def add_numbered(doc, text):
    number = getattr(add_numbered, "counter", 0) + 1
    add_numbered.counter = number
    p = doc.add_paragraph()
    p.paragraph_format.left_indent = Inches(0.45)
    p.paragraph_format.first_line_indent = Inches(-0.25)
    p.paragraph_format.line_spacing = 1.3
    p.paragraph_format.space_after = Pt(5)
    set_run_font(p.add_run(f"{number}. "), 12, bold=True)
    set_run_font(p.add_run(text), 12)
    return p


def add_prompt(doc, text):
    p = doc.add_paragraph()
    p.paragraph_format.left_indent = Inches(0.25)
    p.paragraph_format.space_after = Pt(8)
    p.paragraph_format.keep_with_next = False
    set_run_font(p.add_run("Team writing prompt: "), 11, bold=True, italic=True, color="555555")
    set_run_font(p.add_run(text), 11, italic=True, color="555555")
    return p


def add_standard_table(doc, headers, rows, widths):
    table = doc.add_table(rows=1, cols=len(headers))
    table.alignment = WD_TABLE_ALIGNMENT.CENTER
    table.autofit = False
    set_table_borders(table)
    for idx, (cell, header, width) in enumerate(zip(table.rows[0].cells, headers, widths)):
        cell.width = width
        cell.vertical_alignment = WD_CELL_VERTICAL_ALIGNMENT.CENTER
        set_cell_margins(cell)
        shade_cell(cell, "1F4E78")
        p = cell.paragraphs[0]
        p.alignment = WD_ALIGN_PARAGRAPH.CENTER if idx == 0 else WD_ALIGN_PARAGRAPH.LEFT
        set_run_font(p.add_run(header), 10.5, bold=True, color="FFFFFF")
    repeat_table_header(table.rows[0])
    for r_index, row in enumerate(rows):
        cells = table.add_row().cells
        for idx, (cell, value, width) in enumerate(zip(cells, row, widths)):
            cell.width = width
            cell.vertical_alignment = WD_CELL_VERTICAL_ALIGNMENT.CENTER
            set_cell_margins(cell)
            if r_index % 2 == 1:
                shade_cell(cell, "F3F6F9")
            p = cell.paragraphs[0]
            p.alignment = WD_ALIGN_PARAGRAPH.CENTER if idx == 0 and len(headers) > 2 else WD_ALIGN_PARAGRAPH.LEFT
            p.paragraph_format.space_after = Pt(0)
            set_run_font(p.add_run(str(value)), 10.5)
    doc.add_paragraph().paragraph_format.space_after = Pt(0)
    return table


def page_break(doc):
    p = doc.add_paragraph()
    p.add_run().add_break(WD_BREAK.PAGE)


def add_contents_line(doc, text, level=0):
    p = doc.add_paragraph()
    p.paragraph_format.left_indent = Inches(level * 0.25)
    p.paragraph_format.space_after = Pt(2)
    set_run_font(p.add_run(text), 11, bold=(level == 0))


def prepare_document():
    shutil.copy2(REFERENCE, OUTPUT)
    doc = Document(OUTPUT)
    clear_body_keep_sectpr(doc)

    # Normalize retained template styles.
    normal = doc.styles["Normal"]
    normal.font.name = "Times New Roman"
    normal._element.rPr.rFonts.set(qn("w:ascii"), "Times New Roman")
    normal._element.rPr.rFonts.set(qn("w:hAnsi"), "Times New Roman")
    normal.font.size = Pt(12)
    normal.font.color.rgb = RGBColor(0, 0, 0)
    normal.paragraph_format.line_spacing = 1.5
    normal.paragraph_format.space_after = Pt(6)
    for name, size in (("Title", 20), ("Heading 1", 17), ("Heading 2", 14), ("Heading 3", 12.5)):
        style = doc.styles[name]
        style.font.name = "Times New Roman"
        style._element.rPr.rFonts.set(qn("w:ascii"), "Times New Roman")
        style._element.rPr.rFonts.set(qn("w:hAnsi"), "Times New Roman")
        style.font.size = Pt(size)
        style.font.color.rgb = RGBColor(0, 0, 0)
        style.font.bold = name != "Title" or True

    cover = doc.sections[0]
    configure_page(cover)
    cover.top_margin = Inches(0.45)
    cover.bottom_margin = Inches(0.45)
    cover.different_first_page_header_footer = False
    cover.header.is_linked_to_previous = False
    cover.footer.is_linked_to_previous = False
    clear_container(cover.header)
    clear_container(cover.footer)

    # Cover page.
    p = doc.add_paragraph()
    p.alignment = WD_ALIGN_PARAGRAPH.CENTER
    p.paragraph_format.space_after = Pt(8)
    add_picture_with_alt(p.add_run(), LOGO, Inches(1.08), "Hawassa University crest")
    add_centered(doc, "HAWASSA UNIVERSITY", 16, True, space_after=1)
    add_centered(doc, "INSTITUTE OF TECHNOLOGY", 15, True, space_after=1)
    add_centered(doc, "FACULTY OF INFORMATICS", 14, True, space_after=1)
    add_centered(doc, "DEPARTMENT OF INFORMATION SYSTEM", 13.5, True, space_after=9)
    title_p = add_centered(doc, TITLE, 21, True, space_after=4)
    title_p.style = doc.styles["Title"]
    add_centered(doc, SUBTITLE, 14.5, True, space_after=10)
    add_centered(doc, "A PROJECT SUBMITTED IN PARTIAL FULFILLMENT OF THE", 12.5, True)
    add_centered(doc, "REQUIREMENTS FOR THE AWARD OF BACHELOR OF SCIENCE BSc", 12.5, True)
    add_centered(doc, "IN INFORMATION SYSTEM", 12.5, True, space_after=7)

    team_table = doc.add_table(rows=1, cols=2)
    team_table.alignment = WD_TABLE_ALIGNMENT.CENTER
    team_table.autofit = False
    remove_table_borders(team_table)
    repeat_table_header(team_table.rows[0])
    for cell, width in zip(team_table.rows[0].cells, (Inches(4.6), Inches(1.5))):
        cell.width = width
        set_cell_margins(cell, 0, 40, 0, 40)
    set_run_font(team_table.cell(0, 0).paragraphs[0].add_run("Name"), 11.5, True)
    p = team_table.cell(0, 1).paragraphs[0]
    p.alignment = WD_ALIGN_PARAGRAPH.RIGHT
    set_run_font(p.add_run("Student ID"), 11.5, True)
    for i, (name, sid) in enumerate(TEAM, 1):
        cells = team_table.add_row().cells
        cells[0].width = Inches(4.6)
        cells[1].width = Inches(1.5)
        for cell in cells:
            set_cell_margins(cell, 0, 40, 0, 40)
        set_run_font(cells[0].paragraphs[0].add_run(f"{i}. {name}"), 11.5)
        cells[1].paragraphs[0].alignment = WD_ALIGN_PARAGRAPH.RIGHT
        set_run_font(cells[1].paragraphs[0].add_run(sid), 11.5)
    add_centered(doc, "Advisor: Mr. Mulat S.", 11.5, True, space_before=3, space_after=1)
    add_centered(doc, "Submitted to: Information System Department", 11.5, space_after=2)
    add_centered(doc, "Hawassa, Ethiopia 2026 G.C.", 11.5)

    # Preliminary section.
    prelim = doc.add_section(WD_SECTION.NEW_PAGE)
    configure_page(prelim)
    make_header(prelim)
    make_footer(prelim, "lowerRoman", 1)

    add_front_title(doc, "Declaration")
    add_body(doc, "We, the undersigned students of Hawassa University, declare that this Final Year Project documentation entitled Echo is the result of our collaborative academic work. It is submitted in partial fulfillment of the requirements for the Bachelor of Science in Information System. We affirm that the completed report will acknowledge all sources and contributions and will not be submitted elsewhere for another academic award.")
    decl_rows = [(f"{i}. {name}", sid, "____________________") for i, (name, sid) in enumerate(TEAM, 1)]
    add_standard_table(doc, ["Name", "Student ID", "Signature"], decl_rows, [Inches(3.3), Inches(1.25), Inches(1.65)])
    p = doc.add_paragraph()
    set_run_font(p.add_run("Submission Date: "), 12, bold=True)
    set_run_font(p.add_run("____________________________"), 12)

    page_break(doc)
    add_front_title(doc, "Advisor Approval")
    add_body(doc, "This is to certify that the Final Year Project documentation entitled Echo has been reviewed and approved for submission by the assigned advisor.")
    for label, value in (("Advisor Name", "Mr. Mulat S."), ("Signature", "____________________________"), ("Date", "____________________________"), ("Department", "Information System Department")):
        p = doc.add_paragraph()
        p.paragraph_format.space_after = Pt(12)
        set_run_font(p.add_run(f"{label}: "), 12, bold=True)
        set_run_font(p.add_run(value), 12)

    page_break(doc)
    add_front_title(doc, "Examiners Approval")
    add_body(doc, "This is to certify that the Final Year Project documentation entitled Echo has been reviewed, evaluated, and approved by the following examiners.")
    add_standard_table(doc, ["Role", "Name", "Signature", "Date"], [("Examiner One", "", "", ""), ("Examiner Two", "", "", ""), ("Examiner Three", "", "", "")], [Inches(1.5), Inches(1.9), Inches(1.5), Inches(1.3)])

    page_break(doc)
    add_front_title(doc, "Acknowledgments")
    add_body(doc, "We thank Hawassa University, the Institute of Technology, the Faculty of Informatics, and the Department of Information System for providing the academic environment in which this project is being developed. We are especially grateful to our advisor, Mr. Mulat S., for his guidance, review, and technical direction.")
    add_body(doc, "We also acknowledge the education specialists, teachers, school leaders, research reviewers, and other stakeholders whose future review and voluntary participation are necessary before Echo can be evaluated in real classrooms. Their contribution will help the team keep the system useful, understandable, ethical, and appropriate for the local context.")
    add_body(doc, "Finally, we appreciate our families, classmates, and friends for their encouragement and practical support. Each team member shares responsibility for the accuracy, integrity, and timely completion of this work.")

    page_break(doc)
    add_front_title(doc, "Abstract")
    add_prompt(doc, "Complete this page after implementation, target-device testing, educator review, and the approved pilot. In 200 to 300 words, summarize the problem, objective, research and development methods, principal features, technologies, verified results, limitations, and conclusion. Do not claim that the application improves teaching or student learning unless the final evidence supports that conclusion.")
    p = doc.add_paragraph()
    set_run_font(p.add_run("Keywords: "), 12, bold=True)
    set_run_font(p.add_run("Echo; on-device audio analysis; teacher reflection; pedagogy feedback; offline Android application; privacy-preserving educational technology"), 12)

    page_break(doc)
    add_front_title(doc, "Table of Contents")
    contents = [
        ("CHAPTER ONE INTRODUCTION", 0),
        ("1.1 Background of the Study", 1), ("1.2 Statement of the Problem", 1),
        ("1.3 Objectives of the Project", 1), ("1.4 Scope of the Project", 1),
        ("1.5 Limitations of the Project", 1), ("1.6 Significance of the Project", 1),
        ("1.7 Methodology", 1), ("1.8 Development Environment", 1),
        ("1.9 Feasibility Study", 1), ("1.10 Project Schedule and Cost Estimate", 1),
        ("1.11 Organization of the Report", 1),
        ("CHAPTER TWO DESCRIPTION OF THE EXISTING SYSTEM", 0),
        ("CHAPTER THREE SYSTEM ANALYSIS AND REQUIREMENTS", 0),
        ("CHAPTER FOUR SYSTEM DESIGN IMPLEMENTATION AND TESTING", 0),
        ("CHAPTER FIVE CONCLUSION AND RECOMMENDATIONS", 0),
        ("REFERENCES", 0), ("APPENDICES", 0),
    ]
    for text, level in contents:
        add_contents_line(doc, text, level)
    add_prompt(doc, "After the team completes the document, insert or update Word's automatic Table of Contents so final page numbers and hyperlinks are generated from the heading styles.")

    page_break(doc)
    add_front_title(doc, "List of Tables")
    for line in ("Table 1.1 Development Environment", "Table 1.2 Remaining Validation and Documentation Schedule", "Table 1.3 Preliminary Cost Estimate", "Table A.1 Team Responsibility Matrix"):
        add_contents_line(doc, line, 0)
    add_prompt(doc, "Add every approved table with a numbered caption and update this list before final submission.")

    page_break(doc)
    add_front_title(doc, "List of Figures")
    add_prompt(doc, "No project figure is inserted in this framework. Add numbered captions for approved diagrams, evaluation charts, and interface screenshots, then update this list. Expected items may include a use case diagram, system architecture, recording and reflection flow, data-flow diagram, deployment view, and selected user-interface screens.")

    # Main body section.
    main = doc.add_section(WD_SECTION.NEW_PAGE)
    configure_page(main)
    make_header(main)
    make_footer(main, "decimal", 1)

    add_chapter_title(doc, "Chapter One Introduction")

    add_heading(doc, "1.1 Background of the Study", 1)
    add_body(doc, "Teacher professional learning is most useful when it is connected to classroom practice and helps a teacher examine a manageable aspect of instruction. Research on teacher coaching has found that sustained, practice-focused support can improve instruction, while international guidance emphasizes cycles of observation, reflection, modelling, practice, and goal setting [1], [2]. In many schools, however, regular individualized coaching is difficult to provide because specialists must serve many teachers, classroom visits are costly, and feedback may arrive long after a lesson.")
    add_body(doc, "The need is particularly important in early-grade education, where teachers support foundational literacy, language development, and pupils' first experiences of formal learning. Ethiopian studies have reported gaps in preparedness for teaching basic reading skills and challenges in phonological-awareness knowledge and classroom practice [3], [4]. These studies do not represent every teacher or establish the performance of Echo's intended participants, but they show why practical, locally reviewed support for reflection deserves investigation.")
    add_body(doc, "Mobile technology can make professional learning more accessible, but an educational application must fit the conditions in which it will be used. Teachers may have low-capability Android phones, limited data connectivity, irregular access to charging, and classes of approximately 40 to 70 pupils. A system that requires continuous cloud access or uploads long classroom recordings would be costly, fragile, and difficult to reconcile with the privacy expectations of teachers, pupils, and families.")
    add_body(doc, "Audio provides a partial record of classroom activity that a teacher can revisit after a lesson. It can support recall of pacing, pauses, and speaking patterns, but audio alone cannot determine whether pupils understood a concept, whether instruction was effective, or whether a specific teaching practice was appropriate. Any automated analysis must therefore be bounded, transparent, and validated for the relevant languages, grades, devices, microphone positions, and classroom conditions. The teacher must remain able to correct context and decide whether a suggested practice is useful.")
    add_body(doc, "Echo is proposed as an offline Android application for private, weekly teacher reflection. The teacher saves basic class information, connects an external microphone when required, starts and stops a recording, reviews a short visual reflection, and chooses one manageable practice to try in the following week. Classroom audio is encrypted and retained on the device for a limited period. The intended application minimizes data movement and keeps audio, excerpts, and private notes out of the research service.")
    add_body(doc, "The project combines software engineering with a feasibility study. The current repository contains a development implementation of offline recording, encrypted local storage, private excerpt playback, duration-based reflection, draft coaching cards, and separately scoped research-summary infrastructure. These components still require target-device testing, educator review, localization, institutional authorization, and classroom evaluation. Accordingly, this documentation treats Echo as a development and research project rather than a proven system for measuring teaching quality.")

    add_heading(doc, "1.2 Statement of the Problem", 1)
    add_body(doc, "Primary-school teachers need opportunities to reflect on classroom practice and receive feedback that is timely, specific, and manageable. Conventional support commonly depends on occasional observation, workshops, supervisor visits, or informal discussion. These approaches can be valuable, but limited staff, travel, scheduling, and follow-up capacity may prevent frequent individualized reflection. A teacher may therefore complete many lessons without a convenient record for reviewing what happened or selecting one concrete practice for the next week.")
    add_body(doc, "Existing digital approaches also present limitations. Cloud-based recording and analysis depend on stable connectivity and can expose sensitive classroom audio to external systems. General voice-recording applications preserve audio but do not organize it around classes, reflection cycles, retention rules, or reviewed pedagogical guidance. Advanced automated classroom-analysis systems may require large models, speech recognition, extensive training data, or language resources that are not validated for Ethiopian primary classrooms and low-capability phones.")
    add_body(doc, "The central problem is the absence of a simple, offline, privacy-preserving mobile application that enables teachers to review limited classroom-audio evidence, add context, and receive locally reviewed pedagogy feedback without turning the application into a surveillance or ranking tool. The system must operate under device, connectivity, language, privacy, and evidence constraints while remaining easy to use during a teacher's weekly routine.")
    add_body(doc, "Echo addresses this problem through an on-device recording and reflection workflow. It is designed to retain classroom audio locally, minimize research data, make limitations visible, and withhold automated pedagogy claims until they are validated. The project will determine whether the workflow is technically reliable and acceptable to intended users; it will not assume that the application improves teaching or student learning before appropriate evidence is collected.")

    add_heading(doc, "1.3 Objectives of the Project", 1)
    add_heading(doc, "1.3.1 General Objective", 2)
    add_body(doc, "To design, develop, and evaluate an offline, privacy-preserving Android application that uses bounded on-device classroom-audio analysis to support weekly teacher reflection and practical pedagogy feedback in Ethiopian primary schools.", indent=False)
    add_heading(doc, "1.3.2 Specific Objectives", 2)
    objectives = [
        "To identify and locally review useful reflection and coaching priorities for Grades 1 to 4 teachers, particularly practices related to checking understanding, foundational literacy, and eliciting pupil explanations.",
        "To implement a simple Record and Stop workflow with external-microphone support, encrypted local audio storage, interrupted-session recovery, and a rolling retention period suitable for low-capability Android phones.",
        "To develop short weekly visual reflections that clearly distinguish observed or recorded evidence, teacher-provided context, system limitations, and one practical next-week teaching strategy.",
        "To support reviewed content in Amharic, English, Afaan Oromo, and Sidaamu Afoo while preventing unreviewed translations or unsupported analysis from being presented as validated feedback.",
        "To implement separately consented and minimized research-summary transfer to approved in-country infrastructure while keeping audio, excerpts, and private notes on the teacher's device.",
        "To test functional correctness, security, usability, recording reliability, resource use, and data-retention behavior on representative devices before any classroom pilot.",
        "To assess technical feasibility, teacher adoption, usefulness, and reported or independently observed changes during a small approved pilot without making causal claims about pupil learning.",
    ]
    for item in objectives:
        add_numbered(doc, item)

    add_heading(doc, "1.4 Scope of the Project", 1)
    add_heading(doc, "1.4.1 In Scope", 2)
    in_scope = [
        "A one-month feasibility pilot planned for eight Grades 1 to 4 teachers across four primary schools in Hawassa, subject to institutional, school, teacher, and required child or guardian authorization.",
        "An Android application targeting phones with approximately 2 GB RAM and unreliable connectivity, using a foreground recording service and an external wired or USB microphone where required.",
        "Teacher-initiated classroom recording, encrypted app-private storage, private excerpt playback, context notes, lesson exclusion, deletion, recovery of committed audio chunks, and rolling expiry of collected data.",
        "Short class and weekly reflection views, duration and capture-quality information, teacher-selected goals, source-linked practice cards, follow-up prompts, and usefulness ratings.",
        "Offline English draft interaction during development and a controlled path for educator-reviewed Amharic, English, Afaan Oromo, and Sidaamu Afoo content before release.",
        "Separately authorized transfer of allowlisted numeric research summaries to approved Ethiopian hosting. Audio, excerpts, and free-text classroom notes remain on the device.",
        "Automated unit, integration, security, retention, and contract testing, followed by real-device and usability testing before approved classroom use.",
    ]
    for item in in_scope:
        add_bullet(doc, item)
    add_heading(doc, "1.4.2 Out of Scope", 2)
    out_scope = [
        "Teacher ranking, performance appraisal, disciplinary monitoring, or comparison of named teachers or schools.",
        "Identification of individual pupils, speaker recognition, pupil profiles, or storage of child names in project records.",
        "Claims that audio alone measures pupil understanding, teaching effectiveness, instructional quality, or learning outcomes.",
        "Uploading raw classroom audio, private excerpts, transcripts, or free-text notes to cloud services, external analytics, or third-party artificial-intelligence providers.",
        "General automatic speech recognition, semantic transcription, or automatic pedagogical interpretation unless a later separately approved experiment meets language, privacy, accuracy, and device-resource requirements.",
        "Large-scale deployment, nationwide generalization, or institutional adoption decisions based on the planned small feasibility pilot.",
        "Grades 11 and 12, which are represented in a separate development pathway and are not part of the agreed primary-school pilot.",
    ]
    for item in out_scope:
        add_bullet(doc, item)

    add_heading(doc, "1.5 Limitations of the Project", 1)
    limitations = [
        ("Small feasibility sample. ", "Eight teachers across four schools cannot represent all Ethiopian teachers, languages, subjects, or school conditions. Findings will be reported as exploratory and contextual."),
        ("No causal design. ", "The proposed one-month pilot has no randomized control group. Interviews, ratings, and observations may describe feasibility and change, but they cannot establish that Echo caused improved instruction or pupil learning."),
        ("Audio is incomplete evidence. ", "Microphone placement, background noise, overlapping speech, distance, device processing, and classroom size can affect what is captured. A recording cannot reveal all visual activity or reliably determine understanding."),
        ("Device variability. ", "Android models differ in microphone routing, battery management, codec behavior, storage, and performance. The application must be tested on representative target phones before classroom use."),
        ("Language and content readiness. ", "Reviewed multilingual coaching content and validated on-device models are not yet complete. Development copy or fallback language must be clearly disclosed and cannot be treated as a released translation."),
        ("External approvals and operations. ", "The pilot depends on ethics review, school authorization, consent arrangements, educator review, compatible microphones, and validated Ethiopian hosting. Delay or failure in any dependency may narrow or postpone field work."),
    ]
    for lead, rest in limitations:
        add_body(doc, lead + rest, indent=False, bold_lead=lead)

    add_heading(doc, "1.6 Significance of the Project", 1)
    add_body(doc, "For teachers, Echo may provide a private and repeatable way to revisit a lesson, add contextual corrections, and choose one practical action for the next week. The design keeps the teacher in control of recording, review, inclusion, deletion, and practice selection rather than presenting an automatic score.")
    add_body(doc, "For schools and teacher educators, the project can provide evidence about whether a low-cost, offline reflection workflow is understandable and usable under local classroom conditions. The application is not intended to replace human coaching; it can instead support conversations by giving the teacher a bounded record and a focused reflection prompt.")
    add_body(doc, "For software and educational-technology research, Echo explores how on-device processing, encryption, explicit retention, consent-bound research summaries, and evidence gates can be combined in a low-connectivity setting. Negative results, unavailable measures, and teacher corrections are treated as useful findings rather than hidden failures.")
    add_body(doc, "For the student team and Hawassa University, the project integrates mobile development, audio engineering, human-computer interaction, information security, research methods, and responsible data management. It also creates a documented foundation that future work can extend only after evidence and authorization support the additional scope.")

    add_heading(doc, "1.7 Methodology", 1)
    add_heading(doc, "1.7.1 Research Design", 2)
    add_body(doc, "The project will use a design-science approach combined with a mixed-methods feasibility study. Design science supports iterative construction and evaluation of the application, while mixed methods combine quantitative usage and rating data with qualitative interviews, corrections, and observation notes. The study is intended to evaluate feasibility and usefulness, not causal effects on pupil learning.")
    add_heading(doc, "1.7.2 Participants and Pilot Setting", 2)
    add_body(doc, "Subject to approval and readiness checks, eight teachers will be purposively recruited from four primary schools in Hawassa for a concurrent one-month pilot. The primary scope covers Grades 1 to 4, typically with 40 to 70 pupils per class. Eligibility, consent, class language, device compatibility, microphone setup, and school authorization will be verified before recording begins.")
    add_heading(doc, "1.7.3 Data Collection", 2)
    add_body(doc, "Data may include app use records, class-profile categories, recording duration and capture-quality metadata, teacher-selected goals, context corrections, chosen practice cards, follow-up responses, usefulness ratings, baseline and endline interviews, and structured observations. Classroom audio remains encrypted on the teacher's phone and expires under the approved retention rule. No child names or individual speaker identities will be added to structured research records.")
    add_heading(doc, "1.7.4 Data Analysis", 2)
    add_body(doc, "Quantitative analysis will use descriptive counts, proportions, medians, ranges, completion rates, and clearly stated denominators. Proposed targets include sustained use by more than 70 percent of participating teachers and positive ratings on more than 80 percent of submitted reviews, subject to a finalized definition of positive and complete reporting of missing responses. Qualitative interviews and corrections will be analyzed thematically. Independent observation is required before describing changes in teaching practice.")
    add_heading(doc, "1.7.5 System Development Methodology", 2)
    add_body(doc, "Development will follow an iterative, user-centered process: requirements review, low-fidelity design, implementation, automated testing, device testing, stakeholder review, revision, and controlled release. Work will be divided into small increments so that recording, storage, reflection, content, research transfer, and deletion behavior can be tested independently. Unsupported model outputs and unreviewed content will remain unavailable by default.")
    add_heading(doc, "1.7.6 Testing and Evaluation", 2)
    add_body(doc, "Testing will include unit tests for domain rules; integration tests for storage, encryption, recovery, retention, and research contracts; security tests for authentication, authorization, transport, and deletion; and acceptance tests for the complete Record, Stop, Review, Choose, and Follow-up workflow. Physical-device tests will examine microphone routing, screen-off continuity, interruptions, playback, battery use, memory, storage, accessibility, and performance on representative low-capability phones.")
    add_heading(doc, "1.7.7 Deployment and Research Operations", 2)
    add_body(doc, "The Android application will remain usable without a configured server. If the research component is approved, a connected build will communicate only with one reviewed HTTPS origin and will transfer allowlisted summaries under individual consent-bound credentials. The research service will use Django and PostgreSQL on verified Ethiopian infrastructure, with restricted access, collection-based expiry, withdrawal handling, and tested operational procedures.")

    add_heading(doc, "1.8 Development Environment", 1)
    add_body(doc, "The proposed environment is summarized in Table 1.1. Versions must be frozen in the final implementation chapter from the verified build configuration rather than copied from this planning summary.")
    add_centered(doc, "Table 1.1 Development Environment", 11, italic=True, space_after=5)
    dev_rows = [
        ("Mobile platform", "Android 10 or later; target phones approximately 2 GB RAM"),
        ("Mobile language and UI", "Kotlin and Jetpack Compose"),
        ("Audio capture and encoding", "AudioRecord, foreground service, MediaCodec AAC"),
        ("Local security", "AES-GCM encryption with Android Keystore keys; app-private backup-excluded storage"),
        ("Background work", "Android JobScheduler and bounded local workers"),
        ("Research backend", "Python, Django 5.2 LTS, PostgreSQL, HTTPS"),
        ("Version control", "Git and GitHub"),
        ("Development tools", "Android Studio, Gradle, Python test tooling, physical Android devices"),
        ("Documentation", "Microsoft Word compatible DOCX, diagrams, test reports, and repository records"),
    ]
    add_standard_table(doc, ["Category", "Tools and Constraints"], dev_rows, [Inches(2.0), Inches(4.2)])

    add_heading(doc, "1.9 Feasibility Study", 1)
    add_heading(doc, "1.9.1 Technical Feasibility", 2)
    add_body(doc, "The core workflow is technically feasible because Android provides standard APIs for foreground audio capture, codec-based compression, app-private storage, cryptography, scheduling, and local user interfaces. A development implementation and automated tests already exist. Final feasibility nevertheless depends on real-device evidence for microphone routing, interruptions, battery and memory use, playback, accessibility, and performance on target phones. Any learned audio model must be licensed, small enough for the device, and validated before release.")
    add_heading(doc, "1.9.2 Operational Feasibility", 2)
    add_body(doc, "The workflow is intentionally short: save a class profile, record, stop, review, and select one practice. Offline use reduces dependence on network availability, and teacher control over inclusion and deletion supports trust. Operational feasibility remains to be established through educator review and observed teacher use. The application should not be introduced where recording arrangements, school procedures, consent, or technical support are unclear.")
    add_heading(doc, "1.9.3 Economic Feasibility", 2)
    add_body(doc, "The application uses open-source development tools and teacher-owned Android phones where compatible. The currently identified budget is 20,000 ETB for microphones and related hardware only. Engineering time, field logistics, printing, hosting, educator review, and long-term support require separate resources or institutional contribution. The team should therefore avoid presenting the hardware ceiling as the total project cost.")
    add_heading(doc, "1.9.4 Ethical and Legal Feasibility", 2)
    add_body(doc, "Classroom audio can contain personal data and requires a reviewed lawful basis, clear information, appropriate teacher and child or guardian arrangements, restricted access, secure storage, defined retention, and withdrawal procedures. Ethiopia's Personal Data Protection Proclamation establishes obligations relevant to collection, storage, security, child data, and data location [5]. No classroom recording will begin until the responsible university and school authorities approve the protocol and the required consent or assent process is complete.")

    add_heading(doc, "1.10 Project Schedule and Cost Estimate", 1)
    add_heading(doc, "1.10.1 Remaining Validation and Documentation Schedule", 2)
    add_body(doc, "Table 1.2 presents a sixteen-week working schedule for remaining validation and documentation. Calendar dates should be inserted after approvals, device access, and school availability are confirmed.")
    add_centered(doc, "Table 1.2 Remaining Validation and Documentation Schedule", 11, italic=True, space_after=5)
    schedule_rows = [
        ("1", "Weeks 1-2", "Freeze scope, protocol, content-review plan, and device test matrix"),
        ("2", "Weeks 3-5", "Complete priority implementation refinements, security review, and automated regression tests"),
        ("3", "Weeks 6-8", "Run target-phone, microphone, interruption, retention, accessibility, and usability checks"),
        ("4", "Weeks 9-12", "Conduct the approved one-month pilot concurrently across the selected schools"),
        ("5", "Weeks 13-14", "Analyze descriptive results, interviews, corrections, observations, and limitations"),
        ("6", "Weeks 15-16", "Complete documentation, verification, defense materials, and handover"),
    ]
    add_standard_table(doc, ["Phase", "Period", "Main Activities"], schedule_rows, [Inches(0.7), Inches(1.2), Inches(4.3)])
    add_heading(doc, "1.10.2 Preliminary Cost Estimate", 2)
    add_body(doc, "Only the hardware ceiling is currently confirmed. Table 1.3 separates confirmed and unresolved items so that later contributors can insert approved quotations without inventing a total.")
    add_centered(doc, "Table 1.3 Preliminary Cost Estimate", 11, italic=True, space_after=5)
    cost_rows = [
        ("1", "External microphones and accessories", "Confirmed hardware ceiling", "20,000 ETB"),
        ("2", "Teacher Android phones", "Teacher-owned where compatible; loan devices if required", "To be confirmed"),
        ("3", "Software and development libraries", "Open-source tools; no planned license purchase", "0 ETB license cost"),
        ("4", "Approved Ethiopian hosting and operations", "Needed only for the authorized research service", "To be confirmed"),
        ("5", "Field travel, observation, communication, and printing", "Depends on university and school arrangements", "To be confirmed"),
        ("6", "Educator review and localization", "Availability and compensation require approval", "To be confirmed"),
    ]
    add_standard_table(doc, ["No.", "Item", "Basis", "Estimate"], cost_rows, [Inches(0.5), Inches(1.75), Inches(2.7), Inches(1.25)])

    add_heading(doc, "1.11 Organization of the Report", 1)
    add_body(doc, "Chapter One introduces the background, problem, objectives, scope, limitations, significance, methodology, development environment, feasibility, schedule, and preliminary cost. Chapter Two will review current teacher-support practices and comparable systems and will identify the gap addressed by Echo. Chapter Three will define stakeholders, functional and nonfunctional requirements, privacy rules, use cases, analysis models, and interface requirements. Chapter Four will document the architecture, on-device audio pipeline, security design, research infrastructure, implementation, deployment, testing, and verified results. Chapter Five will summarize achievement of the objectives, conclusions, recommendations, and future work. References and appendices will provide the supporting sources, instruments, detailed test evidence, and team records.")

    # Skeleton chapters.
    add_chapter_title(doc, "Chapter Two Description of the Existing System")
    chapter2 = [
        ("2.1 Introduction", "State the purpose and boundaries of the existing-system review."),
        ("2.2 Current Teacher Reflection and Coaching Practices", "Document verified practices in the intended schools or comparable settings. Separate direct findings from literature."),
        ("2.3 Manual and Digital Tools", "Describe observation forms, workshops, voice recorders, messaging tools, and teacher-development platforms actually used or reviewed."),
        ("2.4 Comparable Audio Based Systems", "Compare relevant classroom-audio and automated-feedback systems by purpose, connectivity, language, privacy, hardware, evidence, and cost."),
        ("2.5 Strengths of Existing Approaches", "Summarize capabilities worth retaining, supported by citations or field evidence."),
        ("2.6 Weaknesses and Gap Analysis", "Show the unmet need without exaggerating or claiming that Echo has already solved it."),
        ("2.7 Description of the Proposed System", "Provide a concise verified description of Echo and distinguish implemented, planned, and deferred capabilities."),
        ("2.8 Chapter Summary", "Summarize the evidence that leads into the requirements chapter."),
    ]
    for heading, prompt in chapter2:
        add_heading(doc, heading, 1 if heading.endswith("Introduction") or heading.endswith("Summary") else 2)
        add_prompt(doc, prompt)

    add_chapter_title(doc, "Chapter Three System Analysis and Requirements")
    chapter3 = [
        ("3.1 Introduction", "Explain how requirements were elicited, validated, prioritized, and traced."),
        ("3.2 Stakeholders and Actors", "List teachers, school leaders, university researchers, education reviewers, system administrators, and data-protection roles with verified responsibilities."),
        ("3.3 Functional Requirements", "Number testable requirements for profiles, capture, review, correction, reflection, content, deletion, optional research transfer, withdrawal, and administration."),
        ("3.4 Nonfunctional Requirements", "Define measurable usability, accessibility, security, privacy, reliability, performance, offline, battery, memory, retention, and localization requirements."),
        ("3.5 Business Privacy and Evidence Rules", "Document consent boundaries, prohibited data, retention, deletion, evidence gates, and rules that prevent unsupported pedagogy claims."),
        ("3.6 Use Case Model", "Insert the approved use case diagram and essential use case descriptions with preconditions, normal flow, alternatives, and postconditions."),
        ("3.7 Dynamic Analysis Models", "Add sequence and activity diagrams for recording, recovery, reflection, deletion, research submission, and withdrawal."),
        ("3.8 Structural and Data Model", "Document domain classes, local entities, research schemas, ownership, retention, and relationships."),
        ("3.9 User Interface Requirements and Prototype", "Insert verified screen flows and accessibility requirements for Today, Record, Review, Reflection, and Settings."),
        ("3.10 Requirements Traceability", "Map each requirement to its source, design component, test, implementation status, and evidence."),
        ("3.11 Chapter Summary", "Summarize the approved requirements that constrain design."),
    ]
    for heading, prompt in chapter3:
        add_heading(doc, heading, 1 if heading.endswith("Introduction") or heading.endswith("Summary") else 2)
        add_prompt(doc, prompt)

    add_chapter_title(doc, "Chapter Four System Design Implementation and Testing")
    chapter4 = [
        ("4.1 Introduction", "State what this chapter documents and identify the implemented baseline being described."),
        ("4.2 Design Goals and Constraints", "Connect design choices to offline use, low-capability phones, privacy, teacher control, language readiness, and evidence limits."),
        ("4.3 System Architecture", "Insert an accurate architecture view covering the Android app, encrypted local storage, optional on-device analysis, research outbox, and approved backend."),
        ("4.4 Android Subsystem Design", "Describe profiles, recording service, storage, playback, reflection, content, scheduling, and settings components."),
        ("4.5 On Device Audio Pipeline", "Document capture format, buffering, AAC chunking, encryption, recovery, playback, analysis boundaries, and resource controls."),
        ("4.6 Security Privacy and Retention Design", "Document threats, controls, keys, storage, permissions, transport, access, expiry, deletion, withdrawal, logs, and backup boundaries."),
        ("4.7 Research Infrastructure", "Describe Django/PostgreSQL services, enrollment, grants, allowlisted summaries, admission rules, aggregates, expiry, and operational deployment."),
        ("4.8 User Interface Design", "Insert approved screens and explain navigation, copy, limitations, accessibility, opt-outs, and error handling."),
        ("4.9 Implementation Summary", "Describe only implemented modules and versions. Link each major claim to code, configuration, or test evidence."),
        ("4.10 Deployment Design", "Document build variants, Android installation, server configuration, HTTPS origin binding, migration, monitoring, backup, and rollback."),
        ("4.11 Testing and Evaluation Results", "Report automated and physical-device results, usability findings, pilot outcomes, denominators, failures, limitations, and unresolved acceptance criteria."),
        ("4.12 Chapter Summary", "Summarize the verified implementation and evidence without repeating unsupported aspirations."),
    ]
    for heading, prompt in chapter4:
        add_heading(doc, heading, 1 if heading.endswith("Introduction") or heading.endswith("Summary") else 2)
        add_prompt(doc, prompt)

    add_chapter_title(doc, "Chapter Five Conclusion and Recommendations")
    chapter5 = [
        ("5.1 Summary of the Project", "Summarize the problem, delivered system, study, and verified evidence in relation to the approved scope."),
        ("5.2 Achievement of Objectives", "Evaluate each specific objective as achieved, partially achieved, deferred, or not achieved, with evidence."),
        ("5.3 Conclusions", "State conclusions supported by the results. Keep feasibility, usability, teaching change, and pupil learning claims separate."),
        ("5.4 Recommendations", "Provide actionable recommendations for the university, schools, future developers, researchers, and operators."),
        ("5.5 Future Work", "List prioritized work that requires new evidence, authorization, resources, or scope, including reviewed multilingual content and validated analysis."),
    ]
    for heading, prompt in chapter5:
        add_heading(doc, heading, 1 if heading.endswith("Project") or heading.endswith("Conclusions") else 2)
        add_prompt(doc, prompt)

    add_chapter_title(doc, "References")
    refs = [
        "[1] M. A. Kraft, D. Blazar, and D. Hogan, 'The effect of teacher coaching on instruction and achievement: A meta-analysis of the causal evidence,' Review of Educational Research, vol. 88, no. 4, pp. 547-588, 2018. doi: 10.3102/0034654318759268.",
        "[2] World Bank, Facilitating Effective 1-1 Coaching Sessions: Technical Guidance Note, 2022.",
        "[3] E. Yisihak and A. Damtew, 'Ethiopian early grade English teachers' preparedness to teach basic reading skills,' Education Research International, 2024, pp. 1-14, 2024. doi: 10.1155/2024/5596229.",
        "[4] S. Z. Haile and B. S. Mendisu, 'Early-grade reading: The challenges that affect teachers' practice of phonological awareness: The case of Koorete language,' Education Research International, 2023, pp. 1-14, 2023. doi: 10.1155/2023/9527369.",
        "[5] Federal Democratic Republic of Ethiopia, Personal Data Protection Proclamation No. 1321/2024, 2024.",
    ]
    for ref in refs:
        p = doc.add_paragraph()
        p.paragraph_format.left_indent = Inches(0.35)
        p.paragraph_format.first_line_indent = Inches(-0.35)
        p.paragraph_format.line_spacing = 1.15
        p.paragraph_format.space_after = Pt(7)
        set_run_font(p.add_run(ref), 11)
    add_prompt(doc, "These entries support Chapter One. Add every source cited in later chapters, use one consistent citation style, verify metadata and access, and remove any uncited entry before submission.")

    add_chapter_title(doc, "Appendices")
    appendices = [
        ("Appendix A Team Responsibility Matrix", "Assign an owner and reviewer for every section. Record status, evidence links, and completion dates."),
        ("Appendix B Research Instruments", "Attach only the approved interview guides, observation rubrics, consent materials, and questionnaires with version dates."),
        ("Appendix C Requirements and Traceability", "Include the final numbered requirements and their links to design, implementation, tests, and evidence."),
        ("Appendix D Test Evidence", "Include summarized automated, device, accessibility, security, usability, and pilot evidence. Do not paste unfiltered logs."),
        ("Appendix E Additional Diagrams and Screens", "Place large supporting diagrams or secondary screenshots here when they interrupt the main narrative."),
    ]
    for heading, prompt in appendices:
        add_heading(doc, heading, 1)
        add_prompt(doc, prompt)
    add_centered(doc, "Table A.1 Team Responsibility Matrix", 11, italic=True, space_before=6, space_after=5)
    responsibility_rows = [(name, "", "", "Not started") for name, _ in TEAM]
    add_standard_table(doc, ["Team Member", "Primary Section or Artifact", "Reviewer", "Status"], responsibility_rows, [Inches(1.55), Inches(2.55), Inches(1.25), Inches(1.0)])

    # Ask Word to update fields when opened.
    settings = doc.settings._element
    update = settings.find(qn("w:updateFields"))
    if update is None:
        update = OxmlElement("w:updateFields")
        settings.append(update)
    update.set(qn("w:val"), "true")

    doc.core_properties.title = "Echo Final Year Project Documentation Framework"
    doc.core_properties.subject = "Completed Chapter One and team documentation skeleton"
    doc.core_properties.author = "Selwa Jemal; Hanna Woldeselassie; Yordanos Tesfaye; Yoseph Zekarias; Amensisa Abebe; Anteneh Fisseha"
    doc.core_properties.comments = "Prepared from the supplied final-year project reference and the Echo repository."

    # The reference package retains an unused legacy header part. Ensure every
    # image in the package, including that inherited part, has fallback alt text.
    for part in doc.part.package.parts:
        element = getattr(part, "element", None)
        if element is None:
            continue
        for doc_pr in element.iter(qn("wp:docPr")):
            if not doc_pr.get("descr"):
                doc_pr.set("descr", "Hawassa University crest")
            if not doc_pr.get("title"):
                doc_pr.set("title", "Hawassa University crest")

    doc.save(OUTPUT)
    print(OUTPUT)


if __name__ == "__main__":
    prepare_document()
