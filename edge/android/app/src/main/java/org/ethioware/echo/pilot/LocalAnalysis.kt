package org.ethioware.echo.pilot

import org.json.JSONObject

/** Known capture evidence only. Quiet samples are never called silence, VAD or student speech. */
object SignalAnalysis {
    const val VERSION = "capture-signal-v1"

    data class Evidence(val clippedFraction: Double, val nearZeroFraction: Double, val samples: Long) {
        // Conservative pilot diagnostics for microphone input, independent of classroom activity.
        val enoughInput get() = samples >= CAPTURE_RATE
        val frequentClipping get() = enoughInput && clippedFraction >= 0.01
        val mostlyNearZero get() = enoughInput && nearZeroFraction >= 0.95
    }

    fun evidence(lesson: LocalLesson): Evidence? {
        if (lesson.status == "recording" || lesson.chunks == 0 || lesson.samples <= 0 ||
            lesson.clipped !in 0..lesson.samples || lesson.quiet !in 0..lesson.samples) return null
        return Evidence(lesson.clipped.toDouble() / lesson.samples, lesson.quiet.toDouble() / lesson.samples, lesson.samples)
    }

    fun build(lesson: LocalLesson): JSONObject {
        val signal = evidence(lesson)
        val attempted = lesson.attemptedMs
        val fractionReason = when {
            attempted == null -> "attempted_duration_unknown"
            attempted < lesson.capturedMs -> "inconsistent_timing"
            attempted <= 0 -> "attempted_duration_unknown"
            else -> null
        }
        val fraction = attempted?.takeIf { fractionReason == null }?.let { lesson.capturedMs.toDouble() / it }
        return JSONObject().put("version", VERSION).put("created", lesson.createdAt)
            .put("captured_ms", lesson.capturedMs).put("chunks", lesson.chunks)
            .put("attempted_ms", attempted ?: JSONObject.NULL)
            .put("capture_fraction", fraction ?: JSONObject.NULL)
            .put("capture_fraction_reason", fractionReason ?: JSONObject.NULL)
            .put("chunk_evidence_complete", lesson.chunkEvidence.size == lesson.chunks && lesson.chunks > 0)
            .put("gap_timing_available", false)
            .put("signal_available", signal != null)
            .put("clipped_fraction", signal?.clippedFraction ?: JSONObject.NULL)
            .put("near_zero_fraction", signal?.nearZeroFraction ?: JSONObject.NULL)
            .put("pedagogy_available", false).put("reason", "No validated model is installed")
    }
}
