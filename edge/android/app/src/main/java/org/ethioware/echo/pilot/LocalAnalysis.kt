package org.ethioware.echo.pilot

import org.json.JSONObject

/** Known capture evidence only. Quiet samples are never called silence, VAD or student speech. */
object SignalAnalysis {
    const val VERSION = "capture-signal-v1"
    fun build(lesson: LocalLesson): JSONObject {
        require(lesson.samples >= 0 && lesson.clipped in 0..lesson.samples && lesson.quiet in 0..lesson.samples)
        return JSONObject().put("version",VERSION).put("created",lesson.createdAt)
            .put("captured_ms",lesson.capturedMs).put("chunks",lesson.chunks)
            .put("signal_available",lesson.samples>0)
            .put("clipped_fraction",if (lesson.samples>0) lesson.clipped.toDouble()/lesson.samples else JSONObject.NULL)
            .put("near_zero_fraction",if (lesson.samples>0) lesson.quiet.toDouble()/lesson.samples else JSONObject.NULL)
            .put("pedagogy_available",false).put("reason","No validated model is installed")
    }
}
