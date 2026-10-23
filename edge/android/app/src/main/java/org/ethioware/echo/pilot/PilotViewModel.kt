package org.ethioware.echo.pilot

import android.app.Application
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.media.MediaDataSource
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import org.ethioware.echo.BuildConfig
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate
import java.time.ZoneId
import java.util.concurrent.Executors

class PilotViewModel(application: Application) : AndroidViewModel(application) {
    private val io = Executors.newSingleThreadExecutor()
    private val main = Handler(Looper.getMainLooper())
    private val app get() = getApplication<Application>()
    private val store get() = PilotRuntime.store(app)
    private var player: MediaPlayer? = null
    private var audioSource: MemoryAudio? = null
    private var playRequest = 0
    private var pending = 0
    var researchStatus by mutableStateOf("Research sharing is off."); private set
    var researchSchema by mutableIntStateOf(1); private set
    var researchStudy by mutableStateOf("primary"); private set
    var researchActive by mutableStateOf(false); private set
    val researchOrigin get() = BuildConfig.RESEARCH_ORIGIN
    var playing by mutableStateOf<String?>(null); private set
    var loaded by mutableStateOf(false); private set
    var busy by mutableStateOf(false); private set
    var error by mutableStateOf<String?>(null); private set
    var profiles by mutableStateOf<List<ClassProfile>>(emptyList()); private set
    var selectedId by mutableStateOf(""); private set
    var locale by mutableStateOf("en"); private set
    var externalMic by mutableStateOf(true); private set
    var reminders by mutableStateOf(false); private set
    var lessons by mutableStateOf<List<LocalLesson>>(emptyList()); private set
    var week by mutableStateOf(monday(LocalDate.now())); private set
    var reflection by mutableStateOf(JSONObject()); private set
    var previousReflection by mutableStateOf(JSONObject()); private set
    var tab by mutableIntStateOf(0)
    val profile get() = profiles.firstOrNull { it.id == selectedId } ?: profiles.firstOrNull()
    val weekly get() = profile?.let { weeklyLessons(lessons, it.id, week, ZoneId.systemDefault()) }.orEmpty()
    val chosen get() = profile?.let { current -> Coaching.available(current).firstOrNull { card ->
        card.id == reflection.optString("chosen") &&
            card.version == reflection.optString("chosenCardVersion", card.version)
    } }
    val suggested get() = profile?.let(Coaching::forGoal) ?: Coaching.cards.first()
    val report get() = profile?.let { ReflectionComposer.compose(it, weekly, reflection, chosen ?: suggested) }
    val canSubmitResearch get() = profile?.let { current ->
        val expectedStudy = if (current.cohort == "early_grade") "primary" else "secondary"
        researchActive && researchStudy == expectedStudy &&
            (researchSchema == 2 && (weekly.isNotEmpty() || reflection.has("created")) ||
                researchSchema == 1 && current.cohort == "early_grade" && weekly.isNotEmpty())
    } ?: false

    init { refresh(); PilotRuntime.scheduleExpiry(app); ResearchTransfer.schedule(app); LocalAnalysis.schedule(app) }
    private fun task(action: () -> Unit) {
        pending++; busy = true; error = null
        io.execute {
            try { action() }
            catch (_: Exception) { main.post { error = "Could not access private data. Nothing was reset. Retry or check phone storage." } }
            finally { main.post { pending--; busy = pending > 0 } }
        }
    }
    fun refresh() = task {
        val saved = store.settings()
        val rows = store.lessons()
        val classes = saved.optJSONArray("classes") ?: JSONArray()
        val parsed = (0 until classes.length()).map { ClassProfile.parse(classes.getJSONObject(it)) }
        val selected = saved.optString("selected", parsed.firstOrNull()?.id ?: "")
        val targetWeek = week
        val review = if (selected.isNotEmpty()) store.reflection(selected, targetWeek) else JSONObject()
        val previous = if (selected.isNotEmpty()) store.reflection(selected, targetWeek.minusWeeks(1)) else JSONObject()
        main.post {
            if (playing != null && rows.none { it.id == playing }) stopPlayback()
            profiles = parsed; selectedId = selected; locale = saved.optString("locale", "en")
            externalMic = saved.optBoolean("external", true); lessons = rows
            reminders = saved.optBoolean("reminders", false)
            reflection = review; previousReflection = previous; loaded = true
            refreshResearchStatus()

        }
    }
    private fun persistSettings() {
        val value = JSONObject().put("classes", JSONArray(profiles.map { it.json() }))
            .put("selected", selectedId).put("locale", locale).put("external", externalMic).put("reminders", reminders)
        task { store.patchSettings(value) }
    }
    fun saveProfile(value: ClassProfile) {
        require(value.valid())
        profiles = profiles.filterNot { it.id == value.id } + value
        selectedId = value.id; persistSettings(); loadReflection()
    }
    fun selectClass(id: String) { selectedId = id; persistSettings(); loadReflection() }
    fun selectLanguage(language: String) { require(language in LANGUAGES); locale = language; persistSettings() }
    fun selectMic(external: Boolean) { externalMic = external; persistSettings() }
    fun updateReminders(enabled: Boolean) { reminders = enabled; persistSettings() }
    fun moveWeek(delta: Long) {
        val next = week.plusWeeks(delta)
        if (next > monday(LocalDate.now()) || next < monday(LocalDate.now().minusDays(30))) return
        week = next; loadReflection()
    }
    private fun loadReflection() {
        val id = profile?.id ?: return
        val target = week
        task {
            val value = store.reflection(id, target)
            val previous = store.reflection(id, target.minusWeeks(1))
            main.post { if (profile?.id == id && week == target) { reflection = value; previousReflection = previous } }
        }
    }
    fun updateReflection(key: String, value: Any) {
        require(key in setOf("chosen", "tried", "rating"))
        val id = profile?.id ?: return
        val target = week
        val changed = JSONObject(reflection.toString()).put(key, value)
        if (key == "chosen") {
            val card = profile?.let { current -> Coaching.available(current).firstOrNull { it.id == value } }
            require(value == "" || card != null)
            if (card == null) changed.remove("chosenCardVersion")
            else changed.put("chosenCardVersion", card.version).put("contentVersion", Coaching.VERSION)
        }
        reflection = changed
        task { store.saveReflection(id, target, changed) }
    }
    fun correct(row: LocalLesson, context: String, note: String, excluded: Boolean) = task {
        store.correct(row.id, context, note, excluded)
        val rows = store.lessons(); main.post { lessons = rows; refreshResearchStatus() }
    }
    fun delete(row: LocalLesson) {
        stopPlayback()
        task {
            store.deleteLesson(row.id)
            val rows = store.lessons(); main.post { lessons = rows; refreshResearchStatus() }
        }
    }
    fun clearCollectedData() {
        stopPlayback()
        task {
            store.clearCollectedData()
            main.post { lessons = emptyList(); reflection = JSONObject(); previousReflection = JSONObject(); refreshResearchStatus() }
        }
    }
    fun record() {
        if (!loaded || busy || RecordingMonitor.state.running) return
        stopPlayback()
        val selected = profile ?: return
        try {
            app.startForegroundService(Intent(app, RecordingService::class.java).setAction(RecordingService.START)
                .putExtra("profile", selected.json().toString()).putExtra("external", externalMic))
        } catch (_: Exception) { error = "Android could not start recording. Keep Echo open, check microphone permission, and try again." }
    }
    fun stop() { app.startService(Intent(app, RecordingService::class.java).setAction(RecordingService.STOP)) }
    fun play(row: LocalLesson, chunk: Int) {
        if (RecordingMonitor.state.running) return
        stopPlayback()
        val request = playRequest
        task {
            val bytes = store.audio(row.id, chunk)
            main.post {
                if (request != playRequest || RecordingMonitor.state.running) bytes.fill(0)
                else try {
                    val source = MemoryAudio(bytes)
                    audioSource = source
                    player = MediaPlayer().apply {
                        setDataSource(source)
                        setOnPreparedListener { it.start(); playing = row.id }
                        setOnCompletionListener { stopPlayback() }
                        setOnErrorListener { _, _, _ -> stopPlayback(); error = "This excerpt could not be played."; true }
                        prepareAsync()
                    }
                } catch (_: Exception) { bytes.fill(0); stopPlayback(); error = "Audio playback is unavailable." }
            }
        }
    }
    fun stopPlayback() {
        playRequest++; player?.release(); player = null
        audioSource?.close(); audioSource = null; playing = null
    }
    private fun refreshResearchStatus() = task {
        val queue = ResearchQueue(store)
        val config = queue.config()
        val entries = queue.pending().mapNotNull(store::researchRead)
        val queued = entries.count { !it.optBoolean("sent",false) }
        val sent = entries.count { it.optBoolean("sent",false) && !it.optBoolean("rejected",false) }
        val rejected = entries.count { it.optBoolean("rejected",false) }
        val message = when {
            config.optBoolean("withdrawal",false) -> "Sharing is off. Remote withdrawal is pending connectivity."
            config.optBoolean("enabled",false) -> "Research active: $queued queued, $sent acknowledged, $rejected rejected. Only submitted reviews are shared."
            else -> "Research sharing is off."
        }
        main.post {
            researchStatus = message
            researchSchema = config.optInt("schema_version",1)
            researchStudy = config.optString("study","primary")
            researchActive = config.optBoolean("enabled",false)
        }
    }
    fun enrollResearch(code: String) = task {
        check(!RecordingMonitor.state.running)
        ResearchQueue(store).enroll(researchOrigin, code.trim(), ResearchTransfer.transport())
        ResearchTransfer.schedule(app)
        main.post { refreshResearchStatus() }
    }
    fun submitResearchReview() {
        val rows = weekly.toList(); val review = JSONObject(reflection.toString())
        val selectedProfile = profile ?: return
        val key = "${profile?.id}.$week"
        task {
            val queue = ResearchQueue(store)
            val config = queue.config()
            val study = if (selectedProfile.cohort == "early_grade") "primary" else "secondary"
            check(config.optString("study","primary") == study)
            val payload = if (config.optInt("schema_version",1)==2) ResearchProjection.buildV2(selectedProfile,rows,review)
                else ResearchProjection.build(rows, review)
            queue.enqueue(key, payload)
            ResearchTransfer.schedule(app)
            main.post { researchStatus = "Review queued. Audio, names and private context stay on this phone." }
        }
    }
    fun withdrawResearch() = task {
        ResearchQueue(store).withdraw()
        ResearchTransfer.schedule(app)
        main.post { refreshResearchStatus() }
    }
    fun syncResearch() = task {
        check(!RecordingMonitor.state.running)
        ResearchQueue(store).send(ResearchTransfer.transport())
        main.post { refreshResearchStatus() }
    }
    fun permissionDenied() { error = "Microphone permission is required to record. No recording was started." }
    override fun onCleared() { stopPlayback(); io.shutdown(); super.onCleared() }
}

/** Only one short decrypted excerpt in RAM. No temporary plaintext audio file. */
private class MemoryAudio(private val bytes: ByteArray) : MediaDataSource() {
    override fun getSize() = bytes.size.toLong()
    override fun readAt(position: Long, buffer: ByteArray, offset: Int, size: Int): Int {
        if (position < 0 || position >= bytes.size) return -1
        val count = minOf(size, bytes.size - position.toInt())
        bytes.copyInto(buffer, offset, position.toInt(), position.toInt() + count)
        return count
    }
    override fun close() { bytes.fill(0) }
}
