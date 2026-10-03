package org.ethioware.echo

import java.time.LocalDate
import java.time.LocalTime
import org.ethioware.echo.data.Activity
import org.ethioware.echo.data.AdultConditions
import org.ethioware.echo.data.Capture
import org.ethioware.echo.data.ChosenCard
import org.ethioware.echo.data.FollowUpAnswer
import org.ethioware.echo.data.LessonRecord
import org.ethioware.echo.data.LocalState
import org.ethioware.echo.data.Measure
import org.ethioware.echo.data.Mic
import org.ethioware.echo.domain.IneligibleReason
import org.ethioware.echo.domain.answerFollowUp
import org.ethioware.echo.domain.chooseCard
import org.ethioware.echo.domain.lessonIneligibleReason
import org.ethioware.echo.domain.offeredCards
import org.ethioware.echo.domain.pendingFollowUp
import org.ethioware.echo.domain.summariseWeek
import org.ethioware.echo.domain.weekStart
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RulesTest {
    private fun record(
        observed: Double = 2400.0,
        coverage: Double = 1.0,
        adult: AdultConditions = AdultConditions.SINGLE_ADULT,
        day: String = "2026-11-02",
        id: String = "r-$observed-$coverage-$adult-$day",
    ) = LessonRecord(
        id = id, startedOn = LocalDate.parse(day), startTime = LocalTime.of(8, 15), durationS = 2400,
        grade = "2", subject = "mother_tongue", specVersion = 3,
        capture = Capture(observed, 2400 - observed, coverage, Mic.BUILTIN, 58),
        activity = Activity.MIXED, adult = adult, i1 = Measure.of(0.6), i5 = Measure.of(2.0),
    )

    @Test
    fun `thresholds are inclusive at exactly 10 observed minutes and 80 percent`() {
        assertNull(lessonIneligibleReason(record(observed = 600.0, coverage = 0.80), excluded = false))
        assertEquals(IneligibleReason.UNDER_TEN_MINUTES, lessonIneligibleReason(record(observed = 599.9), false))
        assertEquals(IneligibleReason.LOW_COVERAGE, lessonIneligibleReason(record(coverage = 0.799), false))
    }

    @Test
    fun `unconfirmed adult context and exclusion make a lesson ineligible`() {
        assertEquals(IneligibleReason.ADULT_CONTEXT, lessonIneligibleReason(record(adult = AdultConditions.UNSURE), false))
        assertEquals(IneligibleReason.ADULT_CONTEXT, lessonIneligibleReason(record(adult = AdultConditions.OTHER_OR_PLAYBACK), false))
        assertEquals(IneligibleReason.EXCLUDED, lessonIneligibleReason(record(), excluded = true))
    }

    @Test
    fun `weeks start on Monday`() {
        assertEquals(LocalDate.parse("2026-11-02"), weekStart(LocalDate.parse("2026-11-06")))
        assertEquals(LocalDate.parse("2026-11-02"), weekStart(LocalDate.parse("2026-11-02")))
        assertEquals(LocalDate.parse("2026-11-02"), weekStart(LocalDate.parse("2026-11-08")))
    }

    @Test
    fun `three lessons on one day are not enough`() {
        val lessons = listOf(record(id = "a"), record(id = "b"), record(id = "c"))
        val w = summariseWeek(LocalDate.parse("2026-11-02"), lessons, emptySet(), "2", "mother_tongue", 3)
        assertEquals(3, w.eligible.size)
        assertFalse(w.observationsReady) // needs 2 different days
        assertNull(w.i1Mean)
    }

    @Test
    fun `shared mock data reproduces the Python eligibility scenarios`() {
        // Mirrors tests/demo/test_mock_data.py: week 43 ready, week 44 not enough, week 45 ready (one lesson excluded).
        val b = TestSupport.bundle()
        fun week(d: String) = summariseWeek(
            LocalDate.parse(d), b.lessons, b.localState.excludedIds, b.profile.grade, b.profile.subject, 3,
        )
        val w43 = week("2026-10-19")
        val w44 = week("2026-10-26")
        val w45 = week("2026-11-02")
        assertTrue(w43.observationsReady); assertEquals(4, w43.eligible.size)
        assertFalse(w44.observationsReady); assertEquals(1, w44.eligible.size); assertEquals(3, w44.lessons.size)
        assertTrue(w45.observationsReady); assertEquals(3, w45.eligible.size); assertEquals(4, w45.lessons.size)
        assertNotNull(w45.i1Mean)
        assertEquals(w45.eligible.mapNotNull { it.i1.value }.average(), w45.i1Mean!!, 1e-12)
    }

    @Test
    fun `week 44 failures are the intended ones`() {
        val b = TestSupport.bundle()
        val reasons = b.lessons.filter { weekStart(it.startedOn) == LocalDate.parse("2026-10-26") }
            .mapNotNull { lessonIneligibleReason(it, it.id in b.localState.excludedIds) }.toSet()
        assertEquals(setOf(IneligibleReason.LOW_COVERAGE, IneligibleReason.ADULT_CONTEXT), reasons)
    }

    @Test
    fun `follow-up is asked once at the first weekly opening after choosing`() {
        val chosen = LocalState(chosen = ChosenCard("c1", LocalDate.parse("2026-10-29")))
        assertNull(pendingFollowUp(chosen, LocalDate.parse("2026-10-30"))) // same week
        assertNotNull(pendingFollowUp(chosen, LocalDate.parse("2026-11-02"))) // next week
        val answered = answerFollowUp(chosen, FollowUpAnswer.TRIED, LocalDate.parse("2026-11-03"))
        assertNull(pendingFollowUp(answered, LocalDate.parse("2026-11-10"))) // never asked twice
        assertEquals("c1", answered.chosen?.cardId) // Tried keeps the card
    }

    @Test
    fun `not useful removes the card and it is never offered again`() {
        val b = TestSupport.bundle()
        val state = answerFollowUp(b.localState, FollowUpAnswer.NOT_USEFUL, LocalDate.parse("2026-11-06"))
        assertNull(state.chosen)
        val offered = offeredCards(b.cards, state).map { it.id }
        assertEquals(2, offered.size)
        assertFalse("explain_after_answer" in offered)
        // Choosing it again deliberately clears the dismissal.
        val again = chooseCard(state, "explain_after_answer", LocalDate.parse("2026-11-06"))
        assertEquals(3, offeredCards(b.cards, again).size)
    }

    @Test
    fun `mock bundle parses completely`() {
        val b = TestSupport.bundle()
        assertEquals(11, b.lessons.size)
        assertEquals(3, b.cards.size)
        assertEquals(LocalDate.parse("2026-11-06"), b.profile.referenceDate)
        assertTrue(b.cards.all { it.title.sid == null && it.title.am != null })
        // Unavailable measures carry a reason and no value.
        val unavailable = b.lessons.filter { !it.i1.available }
        assertEquals(1, unavailable.size)
        assertNull(unavailable.single().i1.value)
        assertEquals("adult_context_not_confirmed", unavailable.single().i1.reason)
    }
}
