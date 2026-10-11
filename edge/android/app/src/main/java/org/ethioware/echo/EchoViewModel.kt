package org.ethioware.echo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.UUID
import org.ethioware.echo.data.Activity
import org.ethioware.echo.data.AdultConditions
import org.ethioware.echo.data.AppLocale
import org.ethioware.echo.data.Capture
import org.ethioware.echo.data.DemoBundle
import org.ethioware.echo.data.FollowUpAnswer
import org.ethioware.echo.data.KeyValueStore
import org.ethioware.echo.data.LessonRecord
import org.ethioware.echo.data.LocalState
import org.ethioware.echo.data.Mic
import org.ethioware.echo.data.MockJson
import org.ethioware.echo.data.objects
import org.ethioware.echo.data.strings
import org.ethioware.echo.domain.LessonSession
import org.ethioware.echo.domain.MockMeasure
import org.ethioware.echo.domain.Phase
import org.ethioware.echo.domain.ReadinessCheck
import org.ethioware.echo.domain.Totals
import org.ethioware.echo.domain.WeekSummary
import org.ethioware.echo.domain.answerFollowUp
import org.ethioware.echo.domain.chooseCard
import org.ethioware.echo.domain.offeredCards
import org.ethioware.echo.domain.pendingFollowUp
import org.ethioware.echo.domain.removeCard
import org.ethioware.echo.domain.summariseWeek
import org.ethioware.echo.domain.weekStart
import org.json.JSONArray
import org.json.JSONObject

enum class Tab { TODAY, REFLECTION, SETTINGS }

/** Pushed, full-screen destinations above the tab bar. */
sealed interface Screen {
    data object Readiness : Screen
    data object Running : Screen
    data object End : Screen
    data class LessonDetail(val id: String) : Screen
    data class CardDetail(val id: String) : Screen
}

/** Choices made on the end-of-lesson screen, before anything is saved. */
data class LessonDraft(
    val totals: Totals,
    val startedAtTime: LocalTime,
    val activity: Activity = Activity.UNSURE,
    val adult: AdultConditions = AdultConditions.UNSURE,
    val exclude: Boolean = false,
)

/**
 * All demo state and flows. Time and storage are injected so every flow is unit-testable on the JVM.
 * Nothing here records audio: sessions only measure time, and measures come from [MockMeasure].
 */
class EchoViewModel(
    private val bundle: DemoBundle,
    private val store: KeyValueStore,
    private val clock: () -> Long = System::currentTimeMillis,
    private val timeOfDay: () -> LocalTime = { LocalTime.now() },
) : ViewModel() {

    /** The fixed demo "today" (from the mock data), so weekly reports are deterministic. */
    val today: LocalDate = bundle.profile.referenceDate
    val profile get() = bundle.profile
    val cards get() = bundle.cards

    // ---- persisted state ----
    var locale by mutableStateOf(AppLocale.EN); private set
    var onboarded by mutableStateOf(false); private set
    var consentOn by mutableStateOf<LocalDate?>(null); private set
    var privateSyncEnabled by mutableStateOf(false); private set
    var researchContributionEnabled by mutableStateOf(false); private set
    var tutorialCompleted by mutableStateOf(false); private set
    var withdrawn by mutableStateOf(false); private set
    var classSize by mutableStateOf(bundle.profile.classSize); private set
    var mic by mutableStateOf(bundle.profile.preferredMic); private set
    var localState by mutableStateOf(bundle.localState); private set
    var lessons by mutableStateOf(bundle.lessons.sortedForDisplay()); private set
    /** There is no transfer adapter in this demo, so no record is ever queued or sent. */
    val queuedIds: Set<String> get() = emptySet()
    /** Kept as a source-compatible name for older callers; it controls private-summary preference only. */
    val syncEnabled: Boolean get() = privateSyncEnabled
    private var sharingPreferencesInitialized = false
    private var savedLessons: List<LessonRecord> = emptyList()
    private var deletedIds: Set<String> = emptySet()

    // ---- navigation and flow state ----
    var tab by mutableStateOf(Tab.TODAY)
    val stack = mutableStateListOf<Screen>()
    var onboardingStep by mutableStateOf(0)
    var tutorialPage by mutableIntStateOf(0); private set
    var tutorialOpen by mutableStateOf(false); private set
    val tutorialVisible: Boolean get() = onboarded && (!tutorialCompleted || tutorialOpen)
    var readiness by mutableStateOf<ReadinessCheck?>(null); private set
    val readinessComplete: Boolean get() = readiness?.done(now()) == true
    var session by mutableStateOf<LessonSession?>(null); private set
    var draft by mutableStateOf<LessonDraft?>(null); private set
    var reportWeek by mutableStateOf(weekStart(today)); private set
    private var sessionStartTime: LocalTime = LocalTime.NOON

    init {
        restore()
    }

    fun now(): Long = clock()

    // ---- derived ----
    fun lessonById(id: String): LessonRecord? = lessons.firstOrNull { it.id == id }

    fun weekSummary(week: LocalDate = reportWeek): WeekSummary = summariseWeek(
        weekStart = week,
        allLessons = lessons,
        excludedIds = localState.excludedIds,
        grade = profile.grade,
        subject = profile.subject,
        specVersion = SPEC_VERSION,
        allowSyntheticPreview = true,
    )

    val currentWeek: LocalDate get() = weekStart(today)
    val earliestWeek: LocalDate get() = lessons.minOfOrNull { weekStart(it.startedOn) } ?: currentWeek
    val canGoEarlier: Boolean get() = reportWeek > earliestWeek
    val canGoLater: Boolean get() = reportWeek < currentWeek

    /** The card to ask about, once, at the first weekly opening after it was chosen. */
    val followUp get() = pendingFollowUp(localState, today)
    val offered get() = offeredCards(cards, localState)

    // ---- onboarding / settings ----
    fun selectLocale(value: AppLocale) { locale = value; persist() }

    fun completeOnboarding() {
        if (!sharingPreferencesInitialized) {
            // ADR-018 default applies only to a new enrolment. These are preferences, not grants.
            privateSyncEnabled = true
            researchContributionEnabled = true
            sharingPreferencesInitialized = true
        }
        onboarded = true
        consentOn = today
        persist()
    }

    fun openTutorial() {
        if (!onboarded) return
        tutorialPage = 0
        tutorialOpen = true
    }

    fun nextTutorialPage() {
        if (tutorialPage < TUTORIAL_PAGE_COUNT - 1) tutorialPage++ else dismissTutorial()
    }

    fun previousTutorialPage() {
        if (tutorialPage > 0) tutorialPage--
    }

    fun dismissTutorial() {
        tutorialCompleted = true
        tutorialOpen = false
        tutorialPage = 0
        persist()
    }

    fun changeClassSize(value: Int) { classSize = value.coerceIn(1, 150); persist() }
    fun selectMic(value: Mic) { mic = value; persist() }

    /** Preferences are independent; without a grant and ready destination, they never send data. */
    fun setPrivateSummarySync(enabled: Boolean) {
        privateSyncEnabled = enabled
        sharingPreferencesInitialized = true
        persist()
    }

    fun setResearchContribution(enabled: Boolean) {
        researchContributionEnabled = enabled
        sharingPreferencesInitialized = true
        persist()
    }

    fun setSync(enabled: Boolean) = setPrivateSummarySync(enabled)

    // ---- navigation ----
    fun push(screen: Screen) { stack.add(screen) }
    fun pop() { if (stack.isNotEmpty()) stack.removeAt(stack.lastIndex) }
    private fun replaceTop(screen: Screen) { pop(); push(screen) }

    fun openReport() { reportWeek = currentWeek; tab = Tab.REFLECTION; stack.clear() }
    fun goEarlierWeek() { if (canGoEarlier) reportWeek = reportWeek.minusWeeks(1) }
    fun goLaterWeek() { if (canGoLater) reportWeek = reportWeek.plusWeeks(1) }

    // ---- readiness check (simulated) ----
    fun startReadiness() { readiness = ReadinessCheck(now()); push(Screen.Readiness) }
    fun restartReadiness() { readiness = ReadinessCheck(now()) }
    fun finishReadinessNow() { readiness = ReadinessCheck(now() - ReadinessCheck.DURATION_S * 1000L) }

    // ---- lesson session ----
    fun startLesson() {
        session = LessonSession.start(now())
        sessionStartTime = timeOfDay()
        readiness = null
        if (stack.lastOrNull() == Screen.Readiness) replaceTop(Screen.Running) else push(Screen.Running)
    }

    fun pauseLesson() { session = session?.pause(now()) }
    fun resumeLesson() { session = session?.resume(now()) }
    fun toggleCall() {
        val s = session ?: return
        session = if (s.phase == Phase.INTERRUPTED) s.resume(now()) else s.interrupt(now())
    }
    fun skipAhead() { session = session?.fastForward(10 * 60_000L) }

    /** Ends capture. The result stays unsaved until the teacher chooses Save summary or Discard. */
    fun endLesson() {
        val s = session ?: return
        draft = LessonDraft(totals = s.totals(now()), startedAtTime = sessionStartTime)
        session = null
        replaceTop(Screen.End)
    }

    fun discardSession() {
        session = null
        draft = null
        stack.clear()
        tab = Tab.TODAY
    }

    fun updateDraft(transform: (LessonDraft) -> LessonDraft) { draft = draft?.let(transform) }

    fun discardDraft() {
        draft = null
        stack.clear()
        tab = Tab.TODAY
    }

    fun saveDraft() {
        val d = draft ?: return
        val id = UUID.randomUUID().toString()
        val t = d.totals
        val (i1, i5) = MockMeasure.forSession(id.hashCode().toLong(), t.observedS, d.adult)
        val record = LessonRecord(
            id = id,
            synthetic = true,
            schemaVersion = RECORD_SCHEMA_VERSION,
            measurementProvenanceVersion = 1,
            labelSchemaVersion = LABEL_SCHEMA_VERSION,
            startedOn = today,
            startTime = d.startedAtTime.truncatedTo(ChronoUnit.MINUTES),
            durationS = t.elapsedS,
            grade = profile.grade,
            subject = profile.subject,
            specVersion = SPEC_VERSION,
            capture = Capture(t.observedS.toDouble(), t.missingS.toDouble(), t.coverage, mic, classSize),
            activity = d.activity,
            adult = d.adult,
            i1 = i1,
            i5 = i5,
        )
        savedLessons = savedLessons + record
        if (d.exclude) localState = localState.copy(excludedIds = localState.excludedIds + id)
        refreshLessons()
        draft = null
        stack.clear()
        push(Screen.LessonDetail(id))
        persist()
    }

    // ---- saved lessons ----
    fun setExcluded(id: String, excluded: Boolean) {
        val ids = localState.excludedIds
        localState = localState.copy(excludedIds = if (excluded) ids + id else ids - id)
        persist()
    }

    fun deleteLesson(id: String) {
        savedLessons = savedLessons.filterNot { it.id == id }
        deletedIds = deletedIds + id
        localState = localState.copy(excludedIds = localState.excludedIds - id)
        refreshLessons()
        stack.clear()
        persist()
    }

    // ---- practice card ----
    fun chooseCard(cardId: String) { localState = chooseCard(localState, cardId, today); persist() }
    fun removeCard() { localState = removeCard(localState); persist() }
    fun answerFollowUp(answer: FollowUpAnswer) { localState = answerFollowUp(localState, answer, today); persist() }

    // ---- withdrawal ----
    /** Stops the demo flow and removes its local lessons, choices and use preference. */
    fun withdraw() {
        session = null; draft = null; readiness = null
        stack.clear(); tab = Tab.TODAY
        savedLessons = emptyList(); deletedIds = emptySet()
        lessons = emptyList()
        localState = LocalState()
        consentOn = null
        locale = AppLocale.EN
        classSize = profile.classSize
        mic = profile.preferredMic
        reportWeek = currentWeek
        privateSyncEnabled = false
        researchContributionEnabled = false
        tutorialCompleted = false; tutorialOpen = false; tutorialPage = 0
        sharingPreferencesInitialized = true
        onboarded = false
        withdrawn = true
        // This build has no transfer path; never invent a remote deletion request or receipt.
        persist()
    }

    /** Back to the welcome steps with the original synthetic lessons. */
    fun resetDemo() {
        store.clear()
        session = null; draft = null; readiness = null
        stack.clear(); tab = Tab.TODAY; onboardingStep = 0
        savedLessons = emptyList(); deletedIds = emptySet()
        locale = AppLocale.EN; onboarded = false; consentOn = null
        privateSyncEnabled = false; researchContributionEnabled = false; sharingPreferencesInitialized = false
        tutorialCompleted = false; tutorialOpen = false; tutorialPage = 0
        withdrawn = false
        classSize = profile.classSize; mic = profile.preferredMic
        localState = bundle.localState
        reportWeek = currentWeek
        refreshLessons()
    }

    /** After withdrawal, "Start again" is a full demo reset. */
    fun startAgain() = resetDemo()

    // ---- persistence ----
    private fun refreshLessons() {
        lessons = (bundle.lessons.filterNot { it.id in deletedIds } + savedLessons).sortedForDisplay()
    }

    private fun persist() {
        val o = JSONObject()
            .put("v", 2)
            .put("locale", locale.tag)
            .put("onboarded", onboarded)
            .put("consent_on", consentOn?.toString() ?: JSONObject.NULL)
            .put("private_sync_enabled", privateSyncEnabled)
            .put("research_contribution_enabled", researchContributionEnabled)
            .put("tutorial_completed", tutorialCompleted)
            .put("sharing_preferences_initialized", sharingPreferencesInitialized)
            .put("withdrawn", withdrawn)
            .put("class_size", classSize)
            .put("mic", mic.key)
            .put("local_state", MockJson.localStateToJson(localState))
            .put("saved_lessons", JSONArray(savedLessons.map(MockJson::lessonToJson)))
            .put("deleted_ids", JSONArray(deletedIds.sorted()))
        store.put(STATE_KEY, o.toString())
    }

    private fun restore() {
        val text = store.get(STATE_KEY)
        if (text == null) { refreshLessons(); return }
        try {
            val o = JSONObject(text)
            val stateVersion = o.optInt("v", 1)
            locale = AppLocale.fromTag(o.optString("locale"))
            onboarded = o.optBoolean("onboarded")
            tutorialCompleted = o.optBoolean("tutorial_completed", false)
            consentOn = o.optString("consent_on").takeIf { !o.isNull("consent_on") && it.isNotEmpty() }
                ?.let(LocalDate::parse)
            if (stateVersion >= 2) {
                privateSyncEnabled = o.optBoolean("private_sync_enabled", false)
                researchContributionEnabled = o.optBoolean("research_contribution_enabled", false)
                sharingPreferencesInitialized = o.optBoolean("sharing_preferences_initialized", true)
            } else {
                // Legacy opt-in OFF stays OFF. The old demo had no separate research preference.
                privateSyncEnabled = o.optBoolean("sync_enabled", false)
                researchContributionEnabled = false
                sharingPreferencesInitialized = true
            }
            withdrawn = o.optBoolean("withdrawn")
            // Legacy queue/deletion fields were demo-only state; nothing was ever sent.
            classSize = o.optInt("class_size", classSize)
            mic = Mic.fromKey(o.optString("mic"))
            if (!withdrawn) {
                localState = MockJson.localStateFromJson(o.getJSONObject("local_state"))
                savedLessons = o.optJSONArray("saved_lessons").objects().map(MockJson::lessonFromJson)
                deletedIds = o.optJSONArray("deleted_ids").strings().toSet()
            } else {
                localState = LocalState()
                deletedIds = bundle.lessons.map { it.id }.toSet()
            }
            refreshLessons()
        } catch (e: Exception) {
            // Corrupt demo state must never crash the app: start from the bundled data.
            resetDemo()
        }
    }

    companion object {
        const val SPEC_VERSION = 3
        const val RECORD_SCHEMA_VERSION = 4
        const val LABEL_SCHEMA_VERSION = "coarse-v0-draft"
        const val TUTORIAL_PAGE_COUNT = 5
        private const val STATE_KEY = "state_v1"

        private fun List<LessonRecord>.sortedForDisplay() =
            sortedWith(compareByDescending<LessonRecord> { it.startedOn }.thenByDescending { it.startTime })
    }
}
