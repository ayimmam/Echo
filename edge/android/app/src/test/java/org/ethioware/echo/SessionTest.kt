package org.ethioware.echo

import org.ethioware.echo.domain.LessonSession
import org.ethioware.echo.domain.Phase
import org.ethioware.echo.domain.ReadinessCheck
import org.ethioware.echo.domain.formatClock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SessionTest {
    private val t0 = 1_000_000L
    private fun sec(n: Int) = t0 + n * 1000L

    @Test
    fun `running time is observed`() {
        val totals = LessonSession.start(t0).totals(sec(60))
        assertEquals(60, totals.elapsedS); assertEquals(60, totals.observedS); assertEquals(0, totals.missingS)
        assertEquals(1.0, totals.coverage, 0.0)
    }

    @Test
    fun `paused time is missing not silence`() {
        val s = LessonSession.start(t0).pause(sec(60)).resume(sec(100))
        val totals = s.totals(sec(130))
        assertEquals(130, totals.elapsedS)
        assertEquals(60 + 30, totals.observedS)
        assertEquals(40, totals.missingS)
    }

    @Test
    fun `an interruption accrues missing time until recovery`() {
        val s = LessonSession.start(t0).interrupt(sec(10))
        assertEquals(Phase.INTERRUPTED, s.phase)
        assertEquals(41, s.totals(sec(51)).missingS)
        assertEquals(10, s.totals(sec(51)).observedS)
        val recovered = s.resume(sec(51))
        assertEquals(Phase.RUNNING, recovered.phase)
        assertEquals(41, recovered.totals(sec(51)).missingS)
    }

    @Test
    fun `observed plus missing always equals elapsed`() {
        var s = LessonSession.start(t0)
        s = s.pause(sec(7)).resume(sec(19)).interrupt(sec(33)).resume(sec(40)).pause(sec(41))
        val totals = s.totals(sec(77))
        assertEquals(totals.elapsedS, totals.observedS + totals.missingS)
        assertEquals(77, totals.elapsedS)
    }

    @Test
    fun `invalid transitions are ignored`() {
        val running = LessonSession.start(t0)
        assertEquals(running, running.resume(sec(5)))
        val paused = running.pause(sec(5))
        assertEquals(paused, paused.pause(sec(9)))
        assertEquals(paused, paused.interrupt(sec(9))) // cannot be interrupted while paused
    }

    @Test
    fun `fast forward adds time to the current phase only`() {
        val observed = LessonSession.start(t0).fastForward(600_000).totals(t0)
        assertEquals(600, observed.observedS); assertEquals(0, observed.missingS)
        val missing = LessonSession.start(t0).interrupt(t0).fastForward(120_000).totals(t0)
        assertEquals(120, missing.missingS); assertEquals(0, missing.observedS)
    }

    @Test
    fun `clock never goes backwards`() {
        val totals = LessonSession.start(t0).totals(t0 - 5000)
        assertEquals(0, totals.elapsedS)
    }

    @Test
    fun `readiness check lasts thirty seconds`() {
        val check = ReadinessCheck(t0)
        assertEquals(30, check.remainingS(t0))
        assertTrue(!check.done(sec(29)))
        assertTrue(check.done(sec(30)))
        assertEquals(0, check.remainingS(sec(500)))
        assertEquals(1f, check.progress(sec(500)), 0f)
    }

    @Test
    fun `clock formatting`() {
        assertEquals("00:00", formatClock(0))
        assertEquals("12:04", formatClock(724))
        assertEquals("1:02:03", formatClock(3723))
        assertEquals("00:00", formatClock(-5))
    }
}
