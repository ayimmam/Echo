package org.ethioware.echo.pilot

import org.json.JSONObject
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.time.DayOfWeek
import java.util.UUID

const val RETENTION_MS = 30L * 24 * 60 * 60 * 1000

data class ClassProfile(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "My class",
    val grade: Int = 2,
    val subject: String = "Reading",
    val language: String = "am",
    val size: Int = 50,
    val goal: String = "understanding",
) {
    fun valid() = name.isNotBlank() && name.length <= 60 && grade in 1..4 &&
        subject in listOf("Reading", "Mathematics", "Other") && language in LANGUAGES.keys &&
        size in 1..150 && goal in Coaching.cards.map { it.id }
    fun json() = JSONObject().put("id", id).put("name", name).put("grade", grade)
        .put("subject", subject).put("language", language).put("size", size).put("goal", goal)
    companion object {
        fun parse(o: JSONObject) = ClassProfile(o.getString("id"), o.getString("name"), o.getInt("grade"),
            o.getString("subject"), o.getString("language"), o.getInt("size"), o.getString("goal"))
    }
}

val LANGUAGES = linkedMapOf("en" to "English", "am" to "አማርኛ", "om" to "Afaan Oromo", "sid" to "Sidaamu Afoo")

data class LocalLesson(
    val id: String,
    val createdAt: Long,
    val checkpointAt: Long,
    val profile: ClassProfile,
    val capturedMs: Long = 0,
    val chunks: Int = 0,
    val status: String = "recording",
    val reason: String = "",
    val samples: Long = 0,
    val clipped: Long = 0,
    val quiet: Long = 0,
    val excluded: Boolean = false,
    val context: String = "Not specified",
    val note: String = "",
) {
    val expiresAt: Long get() = createdAt + RETENTION_MS
    fun json() = JSONObject().put("id", id).put("created", createdAt).put("checkpoint", checkpointAt)
        .put("profile", profile.json()).put("captured", capturedMs).put("chunks", chunks)
        .put("status", status).put("reason", reason).put("samples", samples).put("clipped", clipped)
        .put("quiet", quiet).put("excluded", excluded).put("context", context).put("note", note)
    companion object {
        fun parse(o: JSONObject) = LocalLesson(o.getString("id"), o.getLong("created"), o.getLong("checkpoint"),
            ClassProfile.parse(o.getJSONObject("profile")), o.getLong("captured"), o.getInt("chunks"),
            o.getString("status"), o.getString("reason"), o.getLong("samples"), o.getLong("clipped"),
            o.getLong("quiet"), o.getBoolean("excluded"), o.getString("context"), o.getString("note"))
    }
}

data class CoachingCard(val id: String, val title: String, val steps: List<String>, val example: String,
    val source: String, val scope: String)

object Coaching {
    const val VERSION = "hawassa-coaching-2026-10-draft-1"
    // Source-linked development content. No translation or institutional approval is implied.
    val cards = listOf(
        CoachingCard("understanding", "Check understanding, then check again", listOf(
            "Ask one question about today's learning. Give children time to think.",
            "Invite explanations from different parts of the room. Listen for one misunderstanding.",
            "Explain that point again, then ask a fresh question to check understanding."),
            "How did you work that out? What helped you decide?",
            "https://educationendowmentfoundation.org.uk/education-evidence/guidance-reports/feedback", "All subjects"),
        CoachingCard("reading", "Model, practice together, try independently", listOf(
            "Choose one sound–symbol or word skill from your language's curriculum. Model it clearly.",
            "Practice together using a familiar example.",
            "Invite an individual attempt with a new example; give specific feedback and another try."),
            "I will show one example. Let's try together. Now try this new one.",
            "https://www.worldbank.org/en/news/press-release/2025/10/30/new-report-offers-evidence-based-solutions-to-address-global-literacy-crisis-among-children", "Reading; adapt examples to the language"),
        CoachingCard("explanations", "Make room for an explanation", listOf(
            "Ask a question that invites a reason, not only a short answer.",
            "Give thinking time, then let partners exchange ideas briefly.",
            "Invite an explanation and build on it. Check another child's understanding."),
            "Why do you think that? Can someone explain it another way?",
            "https://educationendowmentfoundation.org.uk/education-evidence/teaching-learning-toolkit/oral-language-interventions", "All subjects"),
    )
    fun forGoal(goal: String, subject: String): CoachingCard =
        cards.first { it.id == if (goal == "reading" && subject != "Reading") "understanding" else goal }
}

fun monday(date: LocalDate): LocalDate = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
fun lessonDate(lesson: LocalLesson, zone: ZoneId): LocalDate = Instant.ofEpochMilli(lesson.createdAt).atZone(zone).toLocalDate()
fun weeklyLessons(lessons: List<LocalLesson>, classId: String, week: LocalDate, zone: ZoneId) =
    lessons.filter { it.profile.id == classId && !it.excluded && it.capturedMs > 0 && monday(lessonDate(it, zone)) == week }

/** Storage uses authenticated encryption on Android; pure interface permits meaningful recovery tests. */
interface PrivateFiles {
    fun names(): List<String>
    fun read(name: String): ByteArray?
    fun write(name: String, bytes: ByteArray)
    fun delete(name: String)
}

class PilotStore(private val files: PrivateFiles, private val clock: () -> Long = System::currentTimeMillis) {
    private fun objectAt(name: String) = files.read(name)?.let { JSONObject(it.toString(Charsets.UTF_8)) }
    private fun put(name: String, value: JSONObject) = files.write(name, value.toString().toByteArray())

    /** High-water wall time prevents a clock rollback from reviving already expired records. */
    @Synchronized fun now(): Long {
        val previous = objectAt("clock")?.optLong("max", 0) ?: 0
        val current = maxOf(clock(), previous)
        if (current != previous) put("clock", JSONObject().put("max", current))
        return current
    }
    @Synchronized fun settings(): JSONObject = objectAt("settings") ?: JSONObject()
    @Synchronized fun saveSettings(value: JSONObject) = put("settings", value)
    @Synchronized fun patchSettings(value: JSONObject) {
        val current = settings()
        value.keys().forEach { current.put(it, value.get(it)) }
        put("settings", current)
    }
    @Synchronized fun lessons(): List<LocalLesson> {
        expire()
        return files.names().filter { it.startsWith("lesson.") }.mapNotNull { objectAt(it)?.let(LocalLesson::parse) }
            .sortedByDescending { it.createdAt }
    }
    private fun lesson(id: String): LocalLesson = LocalLesson.parse(requireNotNull(objectAt("lesson.$id")))
    private fun save(value: LocalLesson) = put("lesson.${value.id}", value.json())
    @Synchronized fun create(profile: ClassProfile): LocalLesson {
        require(profile.valid())
        val time = now()
        return LocalLesson(UUID.randomUUID().toString(), time, time, profile).also(::save)
    }
    /** Chunk first, then metadata: a crash never claims an uncommitted audio chunk was saved. */
    @Synchronized fun append(id: String, audio: ByteArray, durationMs: Long, samples: Long, clipped: Long, quiet: Long) {
        val row = lesson(id)
        check(now() < row.expiresAt && row.status == "recording")
        require(audio.isNotEmpty() && audio.size <= 512_000 && durationMs in 1..35_000)
        files.write("audio.$id.${row.chunks}", audio)
        save(row.copy(checkpointAt = now(), capturedMs = row.capturedMs + durationMs, chunks = row.chunks + 1,
            samples = row.samples + samples, clipped = row.clipped + clipped, quiet = row.quiet + quiet))
    }
    @Synchronized fun finish(id: String, reason: String = "") {
        val row = lesson(id)
        save(row.copy(status = if (reason.isEmpty()) "saved" else "interrupted", reason = reason))
    }
    @Synchronized fun audio(id: String, index: Int): ByteArray {
        val row = lesson(id)
        check(now() < row.expiresAt) { "This recording has expired." }
        require(index in 0 until row.chunks)
        return requireNotNull(files.read("audio.$id.$index"))
    }
    @Synchronized fun correct(id: String, context: String, note: String, excluded: Boolean) {
        val row = lesson(id)
        check(now() < row.expiresAt)
        require(context in CONTEXTS && note.length <= 500)
        if (excluded && !row.excluded) ResearchQueue(this).withdraw()
        save(row.copy(context = context, note = note, excluded = excluded))
    }
    @Synchronized fun deleteLesson(id: String) {
        // A deletion after an acknowledged summary invalidates its research use too.
        if (researchRead("config")?.optBoolean("enabled", false) == true) ResearchQueue(this).withdraw()
        files.names().filter { it == "lesson.$id" || it == "analysis.$id" || it.startsWith("audio.$id.") }.forEach(files::delete)
    }
    @Synchronized fun reflection(classId: String, week: LocalDate): JSONObject {
        expire()
        return objectAt("reflection.$classId.$week") ?: JSONObject()
    }
    @Synchronized fun saveReflection(classId: String, week: LocalDate, value: JSONObject) {
        val name = "reflection.$classId.$week"
        val created = objectAt(name)?.optLong("created", now()) ?: now()
        value.put("created", created).put("contentVersion", Coaching.VERSION)
        put(name, value)
    }
    @Synchronized fun expire() {
        val time = now()
        files.names().filter { it.startsWith("lesson.") }.forEach {
            val row = objectAt(it)?.let(LocalLesson::parse) ?: return@forEach
            if (time >= row.expiresAt) files.names().filter { name -> name == "lesson.${row.id}" || name == "analysis.${row.id}" || name.startsWith("audio.${row.id}.") }.forEach(files::delete)
        }
        files.names().filter { it.startsWith("analysis.") }.forEach { name ->
            val value = objectAt(name) ?: return@forEach
            val id = name.removePrefix("analysis.")
            if (time >= value.getLong("created") + RETENTION_MS || objectAt("lesson.$id") == null) files.delete(name)
        }
        researchNames().filter { it.startsWith("item.") }.forEach { name ->
            val row = researchRead(name) ?: return@forEach
            if (time >= row.getJSONObject("payload").getLong("collected_at_ms") + RETENTION_MS) researchDelete(name)
        }
        researchRead("config")?.let { if (time >= it.optLong("expires", 0)) researchNames().forEach(::researchDelete) }
        files.names().filter { it.startsWith("reflection.") }.forEach {
            val row = objectAt(it) ?: return@forEach
            if (time >= row.getLong("created") + RETENTION_MS) files.delete(it)
        }
    }
    /** Call once at process initialization, never when rotating or reopening an active service. */
    @Synchronized fun recover() {
        lessons().forEach { row ->
            if (row.status == "recording") save(row.copy(status = "interrupted", reason = "App or phone stopped. Only completed chunks were recovered."))
        }
        val rows = lessons().associateBy { it.id }
        files.names().filter { it.startsWith("audio.") }.forEach { name ->
            val parts = name.split('.')
            val row = rows[parts.getOrNull(1)]
            if (row == null || (parts.last().toIntOrNull() ?: Int.MAX_VALUE) >= row.chunks) files.delete(name)
        }
    }
    @Synchronized fun clearCollectedData() {
        ResearchQueue(this).withdraw()
        files.names().filter { it.startsWith("audio.") || it.startsWith("lesson.") || it.startsWith("reflection.") || it.startsWith("analysis.") }.forEach(files::delete)
    }
    @Synchronized fun analyzeSignal(id: String): JSONObject? {
        expire()
        val data = objectAt("lesson.$id") ?: return null
        val row = LocalLesson.parse(data)
        if (row.status == "recording") return null
        val result = SignalAnalysis.build(row)
        put("analysis.$id",result)
        return result
    }
    @Synchronized fun signalAnalysis(id: String): JSONObject? {
        expire()
        return objectAt("analysis.$id")
    }
    @Synchronized fun researchRead(name: String): JSONObject? = objectAt("research.$name")
    @Synchronized fun researchWrite(name: String, value: JSONObject) {
        require(name.matches(Regex("[a-zA-Z0-9.\\-]+")))
        put("research.$name", value)
    }
    @Synchronized fun researchDelete(name: String) = files.delete("research.$name")
    @Synchronized fun researchNames(): List<String> = files.names().filter { it.startsWith("research.") }.map { it.removePrefix("research.") }
    companion object {
        val CONTEXTS = listOf("Not specified", "Teacher modeling", "Whole-class practice", "Partner or group work", "Independent practice", "Other adult or recorded audio")
    }
}
