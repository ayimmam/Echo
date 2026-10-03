package org.ethioware.echo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import org.ethioware.echo.data.RemoteDeletion
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

enum class Tab { TODAY, LESSONS, REPORT, SETTINGS }

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
    var syncEnabled by mutableStateOf(false); private set
    var withdrawn by mutableStateOf(false); private set
    var remoteDeletion by mutableStateOf(RemoteDeletion.NOT_NEEDED); private set
    var classSize by mutableStateOf(bundle.profile.classSize); private set
    var mic by mutableStateOf(bundle.profile.preferredMic); private set
    var localState by mutableStateOf(bundle.localState); private set
    var lessons by mutableStateOf(bundle.lessons.sortedForDisplay()); private set
    var queuedIds by mutableStateOf<Set<String>>(emptySet()); private set
    private var savedLessons: List<LessonRecord> = emptyList()
    private var deletedIds: Set<String> = emptySet()

    // ---- navigation and flow state ----
    var tab by mutableStateOf(Tab.TODAY)
    val stack = mutableStateListOf<Screen>()
    var onboardingStep by mutableStateOf(0)
    var readiness by mutableStateOf<ReadinessCheck?>(null); private set
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
        onboarded = true
        consentOn = today
        persist()
    }

    fun changeClassSize(value: Int) { classSize = value.coerceIn(1, 150); persist() }
    fun selectMic(value: Mic) { mic = value; persist() }

    /** Opt-in only. In this demo nothing leaves the phone; the queue just shows what would wait. */
    fun setSync(enabled: Boolean) {
        syncEnabled = enabled
        queuedIds = if (enabled) lessons.map { it.id }.toSet() else emptySet()
        persist()
    }

    // ---- navigation ----
    fun push(screen: Screen) { stack.add(screen) }
    fun pop() { if (stack.isNotEmpty()) stack.removeAt(stack.lastIndex) }
    private fun replaceTop(screen: Screen) { pop(); push(screen) }

    fun openReport() { reportWeek = currentWeek; tab = Tab.REPORT; stack.clear() }
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
        if (syncEnabled) queuedIds = queuedIds + id
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
        queuedIds = queuedIds - id
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
    /**
     * Stops capture and deletes everything held on the phone. If anything was ever queued for sync, the
     * server-side deletion is only *pending* until acknowledged; we never claim it is complete early.
     */
    fun withdraw() {
        val everSynced = syncEnabled || queuedIds.isNotEmpty()
        session = null; draft = null; readiness = null
        stack.clear(); tab = Tab.TODAY
        savedLessons = emptyList(); deletedIds = emptySet(); queuedIds = emptySet()
        lessons = emptyList()
        localState = LocalState()
        consentOn = null
        syncEnabled = false
        onboarded = false
        withdrawn = true
        remoteDeletion = if (everSynced) RemoteDeletion.PENDING else RemoteDeletion.NOT_NEEDED
        persist()
    }

    fun acknowledgeRemoteDeletion() {
        if (remoteDeletion == RemoteDeletion.PENDING) { remoteDeletion = RemoteDeletion.CONFIRMED; persist() }
    }

    /** Back to the welcome steps with the original synthetic lessons. */
    fun resetDemo() {
        store.clear()
        session = null; draft = null; readiness = null
        stack.clear(); tab = Tab.TODAY; onboardingStep = 0
        savedLessons = emptyList(); deletedIds = emptySet(); queuedIds = emptySet()
        locale = AppLocale.EN; onboarded = false; consentOn = null; syncEnabled = false
        withdrawn = false; remoteDeletion = RemoteDeletion.NOT_NEEDED
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
            .put("v", 1)
            .put("locale", locale.tag)
            .put("onboarded", onboarded)
            .put("consent_on", consentOn?.toString() ?: JSONObject.NULL)
            .put("sync_enabled", syncEnabled)
            .put("withdrawn", withdrawn)
            .put("remote_deletion", remoteDeletion.name)
            .put("class_size", classSize)
            .put("mic", mic.key)
            .put("local_state", MockJson.localStateToJson(localState))
            .put("saved_lessons", JSONArray(savedLessons.map(MockJson::lessonToJson)))
            .put("deleted_ids", JSONArray(deletedIds.sorted()))
            .put("queued_ids", JSONArray(queuedIds.sorted()))
        store.put(STATE_KEY, o.toString())
    }

    private fun restore() {
        val text = store.get(STATE_KEY)
        if (text == null) { refreshLessons(); return }
        try {
            val o = JSONObject(text)
            locale = AppLocale.fromTag(o.optString("locale"))
            onboarded = o.optBoolean("onboarded")
            consentOn = o.optString("consent_on").takeIf { !o.isNull("consent_on") && it.isNotEmpty() }
                ?.let(LocalDate::parse)
            syncEnabled = o.optBoolean("sync_enabled")
            withdrawn = o.optBoolean("withdrawn")
            remoteDeletion = runCatching { RemoteDeletion.valueOf(o.optString("remote_deletion")) }
                .getOrDefault(RemoteDeletion.NOT_NEEDED)
            classSize = o.optInt("class_size", classSize)
            mic = Mic.fromKey(o.optString("mic"))
            if (!withdrawn) {
                localState = MockJson.localStateFromJson(o.getJSONObject("local_state"))
                savedLessons = o.optJSONArray("saved_lessons").objects().map(MockJson::lessonFromJson)
                deletedIds = o.optJSONArray("deleted_ids").strings().toSet()
                queuedIds = o.optJSONArray("queued_ids").strings().toSet()
            } else {
                localState = LocalState()
                deletedIds = bundle.lessons.map { it.id }.toSet()
            }
            refreshLessons()
        } catch (e: Exception) {
            // Corrupt demo state must never crash the app: start from the bundled data.
            store.clear()
            refreshLessons()
        }
    }

    companion object {
        const val SPEC_VERSION = 3
        private const val STATE_KEY = "state_v1"

        private fun List<LessonRecord>.sortedForDisplay() =
            sortedWith(compareByDescending<LessonRecord> { it.startedOn }.thenByDescending { it.startTime })
    }
}
