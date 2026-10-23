package org.ethioware.echo.pilot

import org.json.JSONObject

/** Pure, offline wording assembled only from saved facts and the bundled practice catalog. */
data class ReflectionReport(
    val observation: String,
    val limitation: String,
    val action: String?,
    val followUp: String,
    val basis: String?,
)

object ReflectionComposer {
    fun compose(
        profile: ClassProfile,
        lessons: List<LocalLesson>,
        reflection: JSONObject,
        card: CoachingCard?,
    ): ReflectionReport {
        val eligible = lessons.filter { !it.excluded && it.capturedMs > 0 && it.profile.id == profile.id }
        val observation = if (eligible.isEmpty()) {
            "No saved lesson audio is available for this week. You can still choose a teaching goal."
        } else {
            "${eligible.size} saved lesson${if (eligible.size == 1) "" else "s"}; " +
                "${eligible.sumOf { it.capturedMs } / 60_000} recorded minutes."
        }
        val choice = card?.takeIf { it.fits(profile) }
        return ReflectionReport(
            observation = observation,
            limitation = "Recorded time does not measure understanding, participation or teaching quality. " +
                "No validated acoustic observation is available.",
            action = choice?.let { "Try one lesson action: ${it.steps.first()}" },
            followUp = when (reflection.optString("tried")) {
                "yes" -> "You reported trying the previous practice. What did learners show you?"
                "notyet" -> "You have not tried the previous practice yet. What would make it feasible?"
                "notuseful" -> "The previous practice did not fit. Choose or adapt another action."
                else -> "Next week, record whether the chosen action fit your lesson."
            },
            basis = choice?.let { "Draft practice ${it.id} v${it.version}; ${it.limitations}" },
        )
    }
}
