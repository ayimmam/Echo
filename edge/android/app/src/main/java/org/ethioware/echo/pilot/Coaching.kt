package org.ethioware.echo.pilot

/** Bundled development content. A card is never described as educator reviewed without a review record. */
data class CoachingCard(
    val id: String,
    val title: String,
    val steps: List<String>,
    val example: String,
    val source: String,
    val scope: String,
    val grades: Set<Int>,
    val subjects: Set<String>,
    val language: String = "en",
    val version: String = "2",
    val targetSkill: String,
    val limitations: String,
    val notFitWhen: String,
    val reviewer: String? = null,
    val reviewedOn: String? = null,
) {
    val reviewed: Boolean get() = !reviewer.isNullOrBlank() && !reviewedOn.isNullOrBlank()
    fun fits(profile: ClassProfile): Boolean = profile.grade in grades && profile.subject in subjects
}

object Coaching {
    const val VERSION = "hawassa-coaching-2026-10-draft-2"
    private val allGrades = (1..4).toSet() + setOf(11, 12)
    private val allSubjects = setOf("Reading", "Mathematics", "Other")
    val cards = listOf(
        CoachingCard(
            "understanding", "Check understanding, then check again",
            listOf("After modeling, ask learners to solve a fresh item independently.",
                "Sample answers from across the room and identify one misunderstanding.",
                "Address that point, then check again with another item."),
            "What led you to that answer? Try this new example on your own.",
            "https://educationendowmentfoundation.org.uk/education-evidence/guidance-reports/feedback",
            "All grades and subjects; adapt the item to the curriculum", allGrades, allSubjects,
            targetSkill = "checking understanding", limitations = "The recording cannot establish answer correctness.",
            notFitWhen = "No comparable independent item is available.",
        ),
        CoachingCard(
            "reading", "Model, practice together, try independently",
            listOf("Choose one sound–symbol or word skill from the local curriculum and model it clearly.",
                "Practice together using a familiar example.",
                "Invite an individual attempt with a new example; give specific feedback and another try."),
            "I will show one example. Let's try together. Now try this new one.",
            "https://www.worldbank.org/en/news/press-release/2025/10/30/new-report-offers-evidence-based-solutions-to-address-global-literacy-crisis-among-children",
            "Grades 1–4 reading; local-language examples need review", (1..4).toSet(), setOf("Reading"),
            targetSkill = "early reading", limitations = "Sound-writing examples require local-language review.",
            notFitWhen = "The lesson is not teaching a matching reading skill.",
        ),
        CoachingCard(
            "explanations", "Make room for an explanation",
            listOf("Ask for a reason or supporting evidence, not only a short answer.",
                "Give thinking time, then let partners exchange ideas briefly.",
                "Invite an explanation and check another learner's understanding."),
            "Why do you think that? Can someone explain it another way?",
            "https://educationendowmentfoundation.org.uk/education-evidence/teaching-learning-toolkit/oral-language-interventions",
            "All grades and subjects; adapt the question", allGrades, allSubjects,
            targetSkill = "eliciting reasoning", limitations = "Turn counts cannot establish reasoning quality.",
            notFitWhen = "Learners first need a worked example or prerequisite knowledge.",
        ),
        CoachingCard(
            "math_reasoning", "Compare two solution methods",
            listOf("Choose one curriculum-aligned problem and show a worked method.",
                "Ask learners to solve a parallel problem independently.",
                "Invite two methods, discuss why each step works, and check a fresh item."),
            "Which step explains why the methods give the same result?",
            "https://educationendowmentfoundation.org.uk/education-evidence/guidance-reports/secondary-maths",
            "Draft Grades 11–12 mathematics example; curriculum review pending", setOf(11, 12), setOf("Mathematics"),
            targetSkill = "mathematical reasoning", limitations = "Requires review against the actual grade and topic.",
            notFitWhen = "The lesson does not permit independent practice or method comparison.",
        ),
    ).also { catalog ->
        require(catalog.map { it.id }.distinct().size == catalog.size)
        require(catalog.all { it.steps.isNotEmpty() && it.source.startsWith("https://") &&
            it.grades.isNotEmpty() && it.subjects.isNotEmpty() && it.targetSkill.isNotBlank() &&
            it.limitations.isNotBlank() && it.notFitWhen.isNotBlank() &&
            (it.reviewer.isNullOrBlank() == it.reviewedOn.isNullOrBlank()) })
    }

    fun available(profile: ClassProfile) = cards.filter { it.fits(profile) }

    fun forGoal(profile: ClassProfile): CoachingCard =
        available(profile).firstOrNull { it.id == profile.goal }
            ?: available(profile).first { it.id == "understanding" }

    // Compatibility for older development tests and callers without a grade context.
    fun forGoal(goal: String, subject: String): CoachingCard =
        forGoal(ClassProfile(subject = subject, goal = if (subject != "Reading" && goal == "reading") "understanding" else goal))
}
