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
        locale: String = "en",
    ): ReflectionReport {
        val eligible = lessons.filter { !it.excluded && it.capturedMs > 0 && it.profile.id == profile.id }
        val am = locale == "am"
        val observation = if (eligible.isEmpty()) {
            if (am) "በዚህ ሳምንት የተቀመጠ የትምህርት ድምጽ የለም። አሁንም የማስተማር ግብ መምረጥ ይችላሉ።" else "No saved lesson audio is available for this week. You can still choose a teaching goal."
        } else {
            if (am) "${eligible.size} የተቀመጡ ትምህርቶች፤ ${eligible.sumOf { it.capturedMs } / 60_000} የተቀዱ ደቂቃዎች።"
            else "${eligible.size} saved lesson${if (eligible.size == 1) "" else "s"}; ${eligible.sumOf { it.capturedMs } / 60_000} recorded minutes."
        }
        val choice = card?.takeIf { it.fits(profile) }
        return ReflectionReport(
            observation = observation,
            limitation = if (am) "የተቀዳ ጊዜ ግንዛቤን፣ ተሳትፎን ወይም የማስተማር ጥራትን አይለካም። የተረጋገጠ የድምጽ ምልከታ የለም።" else "Recorded time does not measure understanding, participation or teaching quality. No validated acoustic observation is available.",
            action = choice?.let { if (am) "በአንድ ትምህርት ይሞክሩ፦ ${localizedCard(it, locale).steps.first()}" else "Try one lesson action: ${it.steps.first()}" },
            followUp = when (reflection.optString("tried")) {
                "yes" -> if (am) "ያለፈውን ልምምድ እንደሞከሩ ገልጸዋል። ተማሪዎቹ ምን አሳዩዎት?" else "You reported trying the previous practice. What did learners show you?"
                "notyet" -> if (am) "ያለፈውን ልምምድ ገና አልሞከሩም። ምን ቢኖር መሞከር ይችላሉ?" else "You have not tried the previous practice yet. What would make it feasible?"
                "notuseful" -> if (am) "ያለፈው ልምምድ አልተስማማም። ሌላ ድርጊት ይምረጡ ወይም ያስተካክሉ።" else "The previous practice did not fit. Choose or adapt another action."
                else -> if (am) "በሚቀጥለው ሳምንት የመረጡት ድርጊት ከትምህርትዎ ጋር መስማማቱን ይመዝግቡ።" else "Next week, record whether the chosen action fit your lesson."
            },
            basis = choice?.let { if (am) "ረቂቅ ልምምድ ${it.id} v${it.version}፤ ${localizedCard(it, locale).limitations}" else "Draft practice ${it.id} v${it.version}; ${it.limitations}" },
        )
    }
}
