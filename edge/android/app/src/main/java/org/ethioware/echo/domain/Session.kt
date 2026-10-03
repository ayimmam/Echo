package org.ethioware.echo.domain

import kotlin.math.max

enum class Phase { RUNNING, PAUSED, INTERRUPTED }

data class Totals(val elapsedS: Int, val observedS: Int, val missingS: Int) {
    val coverage: Double get() = if (elapsedS == 0) 1.0 else observedS.toDouble() / elapsedS
}

/**
 * Lesson timing state machine. Time is derived from timestamps (never counted by a UI ticker), so it
 * survives rotation and screen-off. While paused or interrupted the time accrues as MISSING: it is
 * "not observed", never silence, and is excluded from every rate (README s3.1).
 */
data class LessonSession(
    val phase: Phase,
    val phaseSinceMs: Long,
    val observedMs: Long = 0,
    val missingMs: Long = 0,
) {
    private fun flush(nowMs: Long): LessonSession {
        val span = max(0L, nowMs - phaseSinceMs)
        return when (phase) {
            Phase.RUNNING -> copy(observedMs = observedMs + span, phaseSinceMs = nowMs)
            else -> copy(missingMs = missingMs + span, phaseSinceMs = nowMs)
        }
    }

    private fun switchTo(next: Phase, nowMs: Long): LessonSession =
        if (phase == next) this else flush(nowMs).copy(phase = next)

    fun pause(nowMs: Long) = if (phase == Phase.RUNNING) switchTo(Phase.PAUSED, nowMs) else this

    /** Resuming is explicit and starts a fresh stream; it never fills the gap with invented audio. */
    fun resume(nowMs: Long) = if (phase == Phase.RUNNING) this else switchTo(Phase.RUNNING, nowMs)

    /** The OS silenced the microphone (e.g. a phone call). */
    fun interrupt(nowMs: Long) = if (phase == Phase.RUNNING) switchTo(Phase.INTERRUPTED, nowMs) else this

    /** Demo helper: pretend [ms] more time has passed in the current phase. */
    fun fastForward(ms: Long) = copy(phaseSinceMs = phaseSinceMs - ms)

    fun totals(nowMs: Long): Totals {
        val s = flush(nowMs)
        return Totals(
            elapsedS = ((s.observedMs + s.missingMs) / 1000).toInt(),
            observedS = (s.observedMs / 1000).toInt(),
            missingS = (s.missingMs / 1000).toInt(),
        )
    }

    companion object {
        fun start(nowMs: Long) = LessonSession(Phase.RUNNING, nowMs)
    }
}

/** The 30-second capture-readiness check. Simulated in the demo: no microphone is opened. */
data class ReadinessCheck(val startedAtMs: Long) {
    fun elapsedS(nowMs: Long): Int = ((nowMs - startedAtMs) / 1000).toInt().coerceIn(0, DURATION_S)
    fun remainingS(nowMs: Long): Int = DURATION_S - elapsedS(nowMs)
    fun progress(nowMs: Long): Float = elapsedS(nowMs).toFloat() / DURATION_S
    fun done(nowMs: Long): Boolean = nowMs - startedAtMs >= DURATION_S * 1000L

    companion object {
        const val DURATION_S = 30
    }
}

/** Fixed, clearly simulated readiness numbers. */
object DemoReadiness {
    const val CLIPPING_FRACTION = 0.001
    const val OBSERVED_S = 29.4
    const val MISSING_S = 0.6
}
