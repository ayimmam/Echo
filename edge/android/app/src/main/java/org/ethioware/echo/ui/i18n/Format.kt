package org.ethioware.echo.ui.i18n

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import org.ethioware.echo.data.Activity
import org.ethioware.echo.data.AdultConditions
import org.ethioware.echo.data.Mic
import org.ethioware.echo.data.REASON_ADULT_CONTEXT
import org.ethioware.echo.data.REASON_INSUFFICIENT_SPEECH

fun Translator.date(day: LocalDate): String =
    DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(javaLocale).format(day)

/** "3 h 07 min" for long spans (weekly totals). */
fun Translator.hm(totalS: Double): String {
    val minutes = Math.round(totalS / 60.0).toInt()
    return this(S.FMT_HM, minutes / 60, minutes % 60)
}

fun Translator.label(a: Activity): String = this(
    when (a) {
        Activity.DEMONSTRATION -> S.ACT_DEMONSTRATION
        Activity.MIXED -> S.ACT_MIXED
        Activity.PUPIL_PRACTICE -> S.ACT_PUPIL_PRACTICE
        Activity.UNSURE -> S.ACT_UNSURE
    },
)

fun Translator.label(a: AdultConditions): String = this(
    when (a) {
        AdultConditions.SINGLE_ADULT -> S.ADULT_SINGLE
        AdultConditions.OTHER_OR_PLAYBACK -> S.ADULT_OTHER
        AdultConditions.UNSURE -> S.ADULT_UNSURE
    },
)

fun Translator.label(m: Mic): String = this(if (m == Mic.WIRED) S.MIC_WIRED else S.MIC_BUILTIN)

fun Translator.reason(reason: String?): String = this(
    if (reason == REASON_ADULT_CONTEXT) S.R_ADULT_CONTEXT
    else if (reason == REASON_INSUFFICIENT_SPEECH) S.R_INSUFFICIENT
    else S.R_INSUFFICIENT,
)
