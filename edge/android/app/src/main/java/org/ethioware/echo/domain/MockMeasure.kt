package org.ethioware.echo.domain

import java.util.Random
import org.ethioware.echo.data.AdultConditions
import org.ethioware.echo.data.Measure
import org.ethioware.echo.data.REASON_ADULT_CONTEXT
import org.ethioware.echo.data.REASON_INSUFFICIENT_SPEECH

/**
 * Stand-in for the on-device model + indicator engine, which does not exist yet (gate G2). Values are
 * seeded pseudo-random numbers in a plausible range and are NOT derived from any sound. The availability
 * rules are real: unconfirmed adult context or too little observed time yields "unavailable", not zero.
 */
object MockMeasure {
    fun forSession(seed: Long, observedS: Int, adult: AdultConditions): Pair<Measure, Measure> {
        if (adult != AdultConditions.SINGLE_ADULT) {
            val none = Measure.unavailable(REASON_ADULT_CONTEXT)
            return none to none
        }
        if (observedS < MIN_SPEECH_WINDOW_S) {
            val none = Measure.unavailable(REASON_INSUFFICIENT_SPEECH)
            return none to none
        }
        val rng = Random(seed)
        val i1 = (0.52 + rng.nextDouble() * 0.20).round(3)
        val i5 = (1.7 + rng.nextDouble() * 0.7).round(2)
        return Measure.of(i1) to Measure.of(i5)
    }

    private const val MIN_SPEECH_WINDOW_S = 120

    private fun Double.round(digits: Int): Double {
        val f = Math.pow(10.0, digits.toDouble())
        return Math.round(this * f) / f
    }
}
