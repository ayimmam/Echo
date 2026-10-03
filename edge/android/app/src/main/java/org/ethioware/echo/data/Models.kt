package org.ethioware.echo.data

import java.time.LocalDate
import java.time.LocalTime

/** Interface languages. Sidaamu Afoo wording is not written yet: it falls back to English. */
enum class AppLocale(val tag: String, val nativeName: String) {
    EN("en", "English"),
    AM("am", "አማርኛ"),
    SID("sid", "Sidaamu Afoo");

    companion object {
        fun fromTag(tag: String?): AppLocale = entries.firstOrNull { it.tag == tag } ?: EN
    }
}

enum class Activity(val key: String) {
    DEMONSTRATION("demonstration"),
    MIXED("mixed"),
    PUPIL_PRACTICE("pupil_practice"),
    UNSURE("unsure");

    companion object {
        fun fromKey(key: String?): Activity = entries.firstOrNull { it.key == key } ?: UNSURE
    }
}

/** Adult-voice stands in for teacher talk only when the teacher confirms the conditions. */
enum class AdultConditions(val key: String) {
    SINGLE_ADULT("single_adult_no_playback"),
    OTHER_OR_PLAYBACK("other_adult_or_playback"),
    UNSURE("unsure");

    companion object {
        fun fromKey(key: String?): AdultConditions = entries.firstOrNull { it.key == key } ?: UNSURE
    }
}

enum class Mic(val key: String) {
    BUILTIN("builtin"),
    WIRED("wired_lavalier");

    companion object {
        fun fromKey(key: String?): Mic = entries.firstOrNull { it.key == key } ?: BUILTIN
    }
}

/** A measure is either available with a value, or unavailable with a reason. Never a silent zero. */
data class Measure(val available: Boolean, val value: Double? = null, val reason: String? = null) {
    companion object {
        fun of(value: Double) = Measure(true, value, null)
        fun unavailable(reason: String) = Measure(false, null, reason)
    }
}

const val REASON_ADULT_CONTEXT = "adult_context_not_confirmed"
const val REASON_INSUFFICIENT_SPEECH = "insufficient_speech"

data class Capture(
    val observedS: Double,
    val missingS: Double,
    val coverage: Double,
    val mic: Mic,
    val classSize: Int,
)

/** Reduced ADR-016 lesson summary (spec 3, coarse-v0-draft). Holds numbers only, never sound or text. */
data class LessonRecord(
    val id: String,
    val startedOn: LocalDate,
    val startTime: LocalTime,
    val durationS: Int,
    val grade: String,
    val subject: String,
    val specVersion: Int,
    val capture: Capture,
    val activity: Activity,
    val adult: AdultConditions,
    val i1: Measure,
    val i5: Measure,
)

/** Text in each locale; `null` means "not written yet" (Sidaamu Afoo today). */
data class Localized(val en: String, val am: String?, val sid: String?) {
    fun forLocale(locale: AppLocale): String = when (locale) {
        AppLocale.EN -> en
        AppLocale.AM -> am ?: en
        AppLocale.SID -> sid ?: en
    }

    /** True when [locale] is showing English because no translation exists. */
    fun isFallback(locale: AppLocale): Boolean = when (locale) {
        AppLocale.EN -> false
        AppLocale.AM -> am == null
        AppLocale.SID -> sid == null
    }
}

data class PracticeCard(
    val id: String,
    val title: Localized,
    val action: Localized,
    val example: Localized,
    val discussionPrompt: Localized,
)

data class ChosenCard(val cardId: String, val chosenOn: LocalDate)

enum class FollowUpAnswer { TRIED, NOT_YET, NOT_USEFUL, SKIP }

data class FollowUp(val cardId: String, val answeredOn: LocalDate, val answer: FollowUpAnswer)

/** Preferences that stay on the phone and are never uploaded or applied to sealed records. */
data class LocalState(
    val excludedIds: Set<String> = emptySet(),
    val chosen: ChosenCard? = null,
    val followUps: List<FollowUp> = emptyList(),
    val dismissedCardIds: Set<String> = emptySet(),
)

data class TeacherProfile(
    val pseudoId: String,
    val displayName: String,
    val schoolLabel: String,
    val grade: String,
    val subject: String,
    val classSize: Int,
    val preferredMic: Mic,
    val referenceDate: LocalDate,
)

enum class RemoteDeletion { NOT_NEEDED, PENDING, CONFIRMED }

/** Everything the bundled mock data provides. */
data class DemoBundle(
    val profile: TeacherProfile,
    val lessons: List<LessonRecord>,
    val localState: LocalState,
    val cards: List<PracticeCard>,
)
