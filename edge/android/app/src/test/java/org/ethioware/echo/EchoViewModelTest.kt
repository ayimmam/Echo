package org.ethioware.echo

import java.time.LocalDate
import java.time.LocalTime
import org.ethioware.echo.data.AdultConditions
import org.ethioware.echo.data.AppLocale
import org.ethioware.echo.data.FollowUpAnswer
import org.ethioware.echo.data.MemoryStore
import org.ethioware.echo.domain.Phase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class EchoViewModelTest {
    private var now = 5_000_000L
    private val store = MemoryStore()
    private fun vm(s: MemoryStore = store) = EchoViewModel(TestSupport.bundle(), s, clock = { now }, timeOfDay = { LocalTime.of(9, 5) })

    private fun runLesson(vm: EchoViewModel, minutes: Int, adult: AdultConditions, exclude: Boolean = false): String {
        vm.startLesson()
        now += minutes * 60_000L
        vm.endLesson()
        vm.updateDraft { it.copy(adult = adult, exclude = exclude) }
        vm.saveDraft()
        return (vm.stack.last() as Screen.LessonDetail).id
    }

    @Test
    fun `starts at onboarding with the demo date and the seeded week`() {
        val v = vm()
        assertFalse(v.onboarded)
        assertEquals(LocalDate.parse("2026-11-06"), v.today)
        assertEquals(11, v.lessons.size)
        assertTrue(v.weekSummary().observationsReady)
        assertNotNull(v.followUp) // the card chosen last week is asked about once
    }

    @Test
    fun `onboarding records consent on the demo date`() {
        val v = vm()
        v.completeOnboarding()
        assertTrue(v.onboarded)
        assertEquals(v.today, v.consentOn)
        assertTrue(v.tutorialVisible)
        assertTrue(vm().tutorialVisible) // unfinished first-use guide survives a restart
    }

    @Test
    fun `first use tutorial completes once and can be replayed`() {
        val v = vm()
        v.completeOnboarding()
        repeat(EchoViewModel.TUTORIAL_PAGE_COUNT - 1) { v.nextTutorialPage() }
        assertEquals(EchoViewModel.TUTORIAL_PAGE_COUNT - 1, v.tutorialPage)
        v.previousTutorialPage()
        assertEquals(EchoViewModel.TUTORIAL_PAGE_COUNT - 2, v.tutorialPage)
        v.nextTutorialPage()
        v.nextTutorialPage()
        assertTrue(v.tutorialCompleted)
        assertFalse(v.tutorialVisible)
        assertFalse(vm().tutorialVisible)

        v.openTutorial()
        assertTrue(v.tutorialVisible)
        assertEquals(0, v.tutorialPage)
        v.dismissTutorial()
        assertFalse(v.tutorialVisible)
    }

    @Test
    fun `skipping tutorial persists and reset offers it again`() {
        val v = vm()
        v.completeOnboarding()
        v.dismissTutorial()
        assertFalse(vm().tutorialVisible)
        v.resetDemo()
        v.completeOnboarding()
        assertTrue(v.tutorialVisible)
    }

    @Test
    fun `existing onboarded state without tutorial flag gets the guide once`() {
        store.put("state_v1", """{"v":2,"onboarded":true,"local_state":{}}""")
        val v = vm()
        assertTrue(v.onboarded)
        assertTrue(v.tutorialVisible)
    }

    @Test
    fun `saving a confirmed lesson adds it with measures and opens its summary`() {
        val v = vm()
        val id = runLesson(v, 40, AdultConditions.SINGLE_ADULT)
        assertEquals(12, v.lessons.size)
        val saved = v.lessonById(id)!!
        assertEquals(v.today, saved.startedOn)
        assertEquals(LocalTime.of(9, 5), saved.startTime)
        assertEquals(2400.0, saved.capture.observedS, 0.0)
        assertTrue(saved.i1.available && saved.i5.available)
        assertEquals(4, v.weekSummary().eligible.size) // 3 seeded + the new one
    }

    @Test
    fun `unconfirmed adult context yields unavailable measures not zeros`() {
        val v = vm()
        val saved = v.lessonById(runLesson(v, 40, AdultConditions.UNSURE))!!
        assertFalse(saved.i1.available); assertNull(saved.i1.value)
        assertEquals("adult_context_not_confirmed", saved.i1.reason)
        assertEquals(3, v.weekSummary().eligible.size) // does not count towards observations
    }

    @Test
    fun `too little observed time yields insufficient speech`() {
        val v = vm()
        val saved = v.lessonById(runLesson(v, 1, AdultConditions.SINGLE_ADULT))!!
        assertEquals("insufficient_speech", saved.i1.reason)
    }

    @Test
    fun `discard creates no record`() {
        val v = vm()
        v.startLesson(); now += 600_000; v.endLesson()
        v.discardDraft()
        assertEquals(11, v.lessons.size)
        assertNull(v.draft); assertTrue(v.stack.isEmpty())
        assertFalse(store.get("state_v1")?.contains("saved_lessons\":[{") ?: false)
    }

    @Test
    fun `excluding at the end keeps the lesson but removes it from the weekly set`() {
        val v = vm()
        val id = runLesson(v, 40, AdultConditions.SINGLE_ADULT, exclude = true)
        assertTrue(id in v.localState.excludedIds)
        assertEquals(3, v.weekSummary().eligible.size)
        v.setExcluded(id, false)
        assertEquals(4, v.weekSummary().eligible.size)
    }

    @Test
    fun `pause and phone call time is not observed`() {
        val v = vm()
        v.startLesson()
        now += 60_000; v.pauseLesson()
        now += 30_000; v.resumeLesson()
        now += 10_000; v.toggleCall()
        assertEquals(Phase.INTERRUPTED, v.session!!.phase)
        now += 20_000; v.toggleCall()
        now += 5_000; v.endLesson()
        val t = v.draft!!.totals
        assertEquals(125, t.elapsedS); assertEquals(75, t.observedS); assertEquals(50, t.missingS)
    }

    @Test
    fun `state survives a restart`() {
        val first = vm()
        first.selectLocale(AppLocale.AM)
        first.completeOnboarding()
        first.changeClassSize(61)
        val id = runLesson(first, 40, AdultConditions.SINGLE_ADULT)
        first.chooseCard("group_or_volunteer")

        val second = vm()
        assertEquals(AppLocale.AM, second.locale)
        assertTrue(second.onboarded)
        assertEquals(61, second.classSize)
        assertNotNull(second.lessonById(id))
        assertEquals("group_or_volunteer", second.localState.chosen?.cardId)
        assertEquals(12, second.lessons.size)
    }

    @Test
    fun `deleting a seeded lesson persists`() {
        val first = vm()
        val victim = first.lessons.first().id
        first.deleteLesson(victim)
        assertNull(first.lessonById(victim))
        assertNull(vm().lessonById(victim))
    }

    @Test
    fun `follow-up answers and card choice`() {
        val v = vm()
        v.answerFollowUp(FollowUpAnswer.NOT_USEFUL)
        assertNull(v.localState.chosen); assertNull(v.followUp)
        assertEquals(2, v.offered.size)
        v.chooseCard("show_then_practise")
        assertEquals("show_then_practise", v.localState.chosen?.cardId)
        assertEquals(v.today, v.localState.chosen?.chosenOn)
        v.removeCard()
        assertNull(v.localState.chosen)
    }

    @Test
    fun `withdrawal without sync deletes everything and needs no remote deletion`() {
        val v = vm()
        v.completeOnboarding()
        runLesson(v, 40, AdultConditions.SINGLE_ADULT)
        v.withdraw()
        assertTrue(v.withdrawn); assertFalse(v.onboarded)
        assertTrue(v.lessons.isEmpty()); assertNull(v.localState.chosen); assertNull(v.consentOn)
        assertNull(v.session); assertTrue(v.stack.isEmpty())
        val stored = store.get("state_v1")!!
        val ids = TestSupport.bundle().lessons.map { it.id }
        assertTrue("lesson ids remain in storage", ids.none { stored.contains(it) })
        assertTrue(stored.contains("\"saved_lessons\":[]"))
    }

    @Test
    fun `withdrawal after enabling a preference still has no transfer path`() {
        val v = vm()
        v.setSync(true)
        assertTrue(v.queuedIds.isEmpty())
        v.withdraw()
        assertTrue(v.queuedIds.isEmpty())
        assertTrue(vm().queuedIds.isEmpty())
    }

    @Test
    fun `withdrawal while a lesson is running stops capture at once`() {
        val v = vm()
        v.startLesson()
        v.withdraw()
        assertNull(v.session); assertNull(v.draft)
    }

    @Test
    fun `restart after withdrawal stays withdrawn and empty`() {
        val v = vm()
        v.withdraw()
        val again = vm()
        assertTrue(again.withdrawn)
        assertTrue(again.lessons.isEmpty())
        again.startAgain()
        assertFalse(again.withdrawn)
        assertEquals(11, again.lessons.size)
        assertFalse(again.onboarded)
    }

    @Test
    fun `new setup starts preferences on but never queues data in the demo`() {
        val v = vm()
        assertFalse(v.syncEnabled)
        v.completeOnboarding()
        assertTrue(v.privateSyncEnabled)
        assertTrue(v.researchContributionEnabled)
        val id = runLesson(v, 40, AdultConditions.SINGLE_ADULT)
        assertNotNull(v.lessonById(id))
        assertTrue(v.queuedIds.isEmpty())
        v.setPrivateSummarySync(false)
        assertFalse(v.privateSyncEnabled)
        assertTrue(v.researchContributionEnabled)
        v.setResearchContribution(false)
        assertFalse(v.researchContributionEnabled)
    }

    @Test
    fun `corrupt saved state falls back to bundled data instead of crashing`() {
        store.put("state_v1", "{ not json")
        val v = vm()
        assertEquals(11, v.lessons.size)
    }

    @Test
    fun `partial restore failure resets sharing preferences before returning to onboarding`() {
        store.put(
            "state_v1",
            """{"v":2,"onboarded":true,"private_sync_enabled":true,"research_contribution_enabled":true,"sharing_preferences_initialized":true,"local_state":{},"saved_lessons":["broken"]}""",
        )
        val v = vm()
        assertFalse(v.onboarded)
        assertFalse(v.privateSyncEnabled)
        assertFalse(v.researchContributionEnabled)
        assertEquals(11, v.lessons.size)
    }

    @Test
    fun `report week navigation is bounded by the data`() {
        val v = vm()
        assertFalse(v.canGoLater)
        v.goEarlierWeek(); v.goEarlierWeek()
        assertEquals(LocalDate.parse("2026-10-19"), v.reportWeek)
        assertFalse(v.canGoEarlier)
        v.goEarlierWeek()
        assertEquals(LocalDate.parse("2026-10-19"), v.reportWeek)
        v.openReport()
        assertEquals(v.currentWeek, v.reportWeek)
    }
}
