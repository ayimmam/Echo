package org.ethioware.echo

import org.ethioware.echo.domain.*
import org.junit.Assert.*
import org.junit.Test

class CoarseIndicatorsTest {
    private fun segments(vararg labels: CoarseLabel) = labels.mapIndexed { index, label ->
        CoarseSegment(index * 100, (index + 1) * 100, label)
    }

    @Test fun overlapAndUnknownRoleBreakPairsAndWithholdAdultShare() {
        val result = CoarseIndicators.compute(segments(CoarseLabel.ADULT, CoarseLabel.OVERLAP,
            CoarseLabel.CHILD_UNSPECIFIED, CoarseLabel.UNKNOWN_ROLE, CoarseLabel.ADULT,
            CoarseLabel.CHILD_UNSPECIFIED), 600)
        assertNull(result.adultShareOfDetectedSpeech)
        assertEquals(1, result.alternationCount)
        assertEquals(10.0, result.adultChildAlternationsPerMinute!!, 1e-6)
        assertEquals(1.0 / 6, result.uncertainRoleFraction!!, 1e-6)
    }

    @Test fun missingCaptureBreaksPairsAndNeverBecomesSilence() {
        val result = CoarseIndicators.compute(segments(CoarseLabel.ADULT, CoarseLabel.MISSING,
            CoarseLabel.CHILD_UNSPECIFIED, CoarseLabel.NON_SPEECH,
            CoarseLabel.ADULT), 500)
        assertEquals(1.0, result.missingSeconds, 1e-6)
        assertEquals(4.0, result.observedSeconds, 1e-6)
        assertEquals(1, result.alternationCount)
        assertEquals(15.0, result.adultChildAlternationsPerMinute!!, 1e-6)
        assertEquals(2.0 / 3, result.adultShareOfDetectedSpeech!!, 1e-6)
    }

    @Test fun invalidTilingAndEmptyDenominatorsAreExplicit() {
        assertThrows(IllegalArgumentException::class.java) {
            CoarseIndicators.compute(listOf(CoarseSegment(100, 200, CoarseLabel.ADULT)), 200)
        }
        val empty = CoarseIndicators.compute(emptyList(), 0)
        assertNull(empty.adultShareOfDetectedSpeech)
        assertNull(empty.adultChildAlternationsPerMinute)
    }
}
