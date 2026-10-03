package org.ethioware.echo.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import org.ethioware.echo.data.AdultConditions
import org.ethioware.echo.data.ChosenCard
import org.ethioware.echo.data.FollowUp
import org.ethioware.echo.data.FollowUpAnswer
import org.ethioware.echo.data.LessonRecord
import org.ethioware.echo.data.LocalState
import org.ethioware.echo.data.PracticeCard

/**
 * Observation eligibility, FEATURE_REVIEW A1. Mirrors `indicators-core/python/.../reporting.py`;
 * both must agree. PROVISIONAL thresholds: design safeguards to freeze on usability evidence, not
 * literature cut-offs. Measure values never choose a practice card.
 */
object ObservationRules {
    const val RULE_VERSION = "obs-eligibility-v0-draft"
    const val MIN_OBSERVED_S = 10 * 60
    const val MIN_COVERAGE = 0.80
    const val MIN_LESSONS = 3
    const val MIN_DISTINCT_DAYS = 2
}

enum class IneligibleReason { EXCLUDED, UNDER_TEN_MINUTES, LOW_COVERAGE, ADULT_CONTEXT }

fun lessonIneligibleReason(record: LessonRecord, excluded: Boolean): IneligibleReason? = when {
    excluded -> IneligibleReason.EXCLUDED
    record.capture.observedS < ObservationRules.MIN_OBSERVED_S -> IneligibleReason.UNDER_TEN_MINUTES
    record.capture.coverage < ObservationRules.MIN_COVERAGE -> IneligibleReason.LOW_COVERAGE
    record.adult != AdultConditions.SINGLE_ADULT -> IneligibleReason.ADULT_CONTEXT
    else -> null
}

fun weekStart(day: LocalDate): LocalDate = day.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

data class WeekSummary(
    val weekStart: LocalDate,
    val lessons: List<LessonRecord>,
    val eligible: List<LessonRecord>,
    val eligibleDays: Int,
    val observationsReady: Boolean,
    val observedS: Double,
    /** Mean of per-lesson values over eligible lessons; only meaningful when [observationsReady]. */
    val i1Mean: Double?,
    val i5Mean: Double?,
    val meanCoverage: Double?,
)

/** Summarise one week of lessons for the chosen grade/subject and one spec version. */
fun summariseWeek(
    weekStart: LocalDate,
    allLessons: List<LessonRecord>,
    excludedIds: Set<String>,
    grade: String,
    subject: String,
    specVersion: Int,
): WeekSummary {
    val lessons = allLessons
        .filter { weekStart(it.startedOn) == weekStart && it.grade == grade && it.subject == subject }
        .sortedWith(compareBy({ it.startedOn }, { it.startTime }))
    val eligible = lessons.filter {
        it.specVersion == specVersion && lessonIneligibleReason(it, it.id in excludedIds) == null
    }
    val days = eligible.map { it.startedOn }.toSet().size
    val ready = eligible.size >= ObservationRules.MIN_LESSONS && days >= ObservationRules.MIN_DISTINCT_DAYS
    fun mean(values: List<Double>) = if (ready && values.isNotEmpty()) values.average() else null
    return WeekSummary(
        weekStart = weekStart,
        lessons = lessons,
        eligible = eligible,
        eligibleDays = days,
        observationsReady = ready,
        observedS = lessons.sumOf { it.capture.observedS },
        i1Mean = mean(eligible.mapNotNull { it.i1.value }),
        i5Mean = mean(eligible.mapNotNull { it.i5.value }),
        meanCoverage = if (ready) eligible.map { it.capture.coverage }.average() else null,
    )
}

/** One question, once, at the first weekly opening after a card was chosen. */
fun pendingFollowUp(state: LocalState, today: LocalDate): ChosenCard? {
    val chosen = state.chosen ?: return null
    val newWeek = weekStart(chosen.chosenOn) < weekStart(today)
    val answered = state.followUps.any { it.cardId == chosen.cardId && it.answeredOn >= chosen.chosenOn }
    return chosen.takeIf { newWeek && !answered }
}

fun chooseCard(state: LocalState, cardId: String, today: LocalDate): LocalState =
    state.copy(chosen = ChosenCard(cardId, today), dismissedCardIds = state.dismissedCardIds - cardId)

fun removeCard(state: LocalState): LocalState = state.copy(chosen = null)

/** "Not useful" stops that card being offered again; other answers keep the card until changed. */
fun answerFollowUp(state: LocalState, answer: FollowUpAnswer, today: LocalDate): LocalState {
    val chosen = state.chosen ?: return state
    val recorded = state.copy(followUps = state.followUps + FollowUp(chosen.cardId, today, answer))
    return if (answer == FollowUpAnswer.NOT_USEFUL) {
        recorded.copy(chosen = null, dismissedCardIds = recorded.dismissedCardIds + chosen.cardId)
    } else {
        recorded
    }
}

fun offeredCards(cards: List<PracticeCard>, state: LocalState): List<PracticeCard> =
    cards.filter { it.id !in state.dismissedCardIds }
