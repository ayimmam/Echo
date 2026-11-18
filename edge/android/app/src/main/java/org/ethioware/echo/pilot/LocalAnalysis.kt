package org.ethioware.echo.pilot

import org.json.JSONObject

/** Known capture evidence only. Quiet samples are never called silence, VAD or student speech. */
object SignalAnalysis {
    const val VERSION = "capture-signal-v1"
    fun build(lesson: LocalLesson): JSONObject {
        require(lesson.samples >= 0 && lesson.clipped in 0..lesson.samples && lesson.quiet in 0..lesson.samples)
        return JSONObject().put("version",VERSION).put("created",lesson.createdAt)
            .put("captured_ms",lesson.capturedMs).put("chunks",lesson.chunks)
            .put("attempted_ms",lesson.attemptedMs ?: JSONObject.NULL)
            .put("capture_fraction",lesson.attemptedMs?.takeIf { it > 0 && it >= lesson.capturedMs }?.let {
                lesson.capturedMs.toDouble() / it
            } ?: JSONObject.NULL)
            .put("capture_fraction_reason",if (lesson.attemptedMs == null) "attempted_duration_unknown"
                else if (lesson.attemptedMs < lesson.capturedMs) "inconsistent_timing" else JSONObject.NULL)
            .put("chunk_evidence_complete",lesson.chunkEvidence.size == lesson.chunks && lesson.chunks > 0)
            .put("gap_timing_available",false)
            .put("signal_available",lesson.samples>0)
            .put("clipped_fraction",if (lesson.samples>0) lesson.clipped.toDouble()/lesson.samples else JSONObject.NULL)
            .put("near_zero_fraction",if (lesson.samples>0) lesson.quiet.toDouble()/lesson.samples else JSONObject.NULL)
            .put("pedagogy_available",false).put("reason","No validated model is installed")
    }
}
