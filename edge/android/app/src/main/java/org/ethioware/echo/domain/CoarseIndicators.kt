package org.ethioware.echo.domain

/** Experimental coarse-v1 (spec 4) arithmetic. Input labels come from a trusted, validated adapter. */
enum class CoarseLabel { ADULT, CHILD_UNSPECIFIED, UNKNOWN_ROLE, OVERLAP, NON_SPEECH, MISSING }

data class CoarseSegment(val startTick: Int, val endTick: Int, val label: CoarseLabel)

data class CoarseMeasures(
    val observedSeconds: Double,
    val missingSeconds: Double,
    val uncertainRoleSeconds: Double,
    val uncertainRoleFraction: Double?,
    val adultShareOfDetectedSpeech: Double?,
    val adultChildAlternationsPerMinute: Double?,
    val alternationCount: Int,
)

object CoarseIndicators {
    const val SPEC_VERSION = 4
    const val TICKS_PER_SECOND = 100

    fun compute(segments: List<CoarseSegment>, durationTicks: Int): CoarseMeasures {
        require(durationTicks >= 0)
        var cursor = 0
        var missing = 0L
        var unknown = 0L
        var adult = 0L
        var speech = 0L
        var priorRole: CoarseLabel? = null
        var alternations = 0
        for (segment in segments) {
            require(segment.startTick == cursor && segment.endTick > cursor && segment.endTick <= durationTicks) {
                "Timeline must tile the duration with positive, nonoverlapping spans"
            }
            val ticks = (segment.endTick - segment.startTick).toLong()
            cursor = segment.endTick
            when (segment.label) {
                CoarseLabel.MISSING -> { missing += ticks; priorRole = null }
                CoarseLabel.UNKNOWN_ROLE -> { unknown += ticks; speech += ticks; priorRole = null }
                CoarseLabel.OVERLAP -> { speech += ticks; priorRole = null }
                CoarseLabel.NON_SPEECH -> Unit
                CoarseLabel.ADULT, CoarseLabel.CHILD_UNSPECIFIED -> {
                    speech += ticks
                    if (segment.label == CoarseLabel.ADULT) adult += ticks
                    if (priorRole != null && priorRole != segment.label) alternations++
                    priorRole = segment.label
                }
            }
        }
        require(cursor == durationTicks) { "Timeline does not cover the duration" }
        val observed = durationTicks.toLong() - missing
        return CoarseMeasures(
            observedSeconds = observed.toDouble() / TICKS_PER_SECOND,
            missingSeconds = missing.toDouble() / TICKS_PER_SECOND,
            uncertainRoleSeconds = unknown.toDouble() / TICKS_PER_SECOND,
            uncertainRoleFraction = if (speech > 0) unknown.toDouble() / speech else null,
            adultShareOfDetectedSpeech = if (speech > 0 && unknown == 0L) adult.toDouble() / speech else null,
            adultChildAlternationsPerMinute = if (observed > 0) alternations.toDouble() * 60 * TICKS_PER_SECOND / observed else null,
            alternationCount = alternations,
        )
    }
}
