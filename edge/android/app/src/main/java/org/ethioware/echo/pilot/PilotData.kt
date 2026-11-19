package org.ethioware.echo.pilot

import org.json.JSONObject
import org.json.JSONArray
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.time.DayOfWeek
import java.util.UUID
import org.ethioware.echo.BuildConfig

const val RETENTION_MS = 30L * 24 * 60 * 60 * 1000
const val SHADOW_DELETE_MS = 15L * 24 * 60 * 60 * 1000

data class ClassProfile(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "My class",
    val grade: Int = 2,
    val subject: String = "Reading",
    val language: String = "am",
    val size: Int = 50,
    val goal: String = "understanding",
    val topic: String = "",
) {
    val cohort: String get() = if (grade in 11..12) "secondary" else "early_grade"
    fun valid() = name.isNotBlank() && name.length <= 60 && (grade in 1..4 || grade in 11..12) &&
        subject in listOf("Reading", "Mathematics", "Other") && language in LANGUAGES.keys &&
        size in 1..150 && topic.length <= 80 && goal in Coaching.available(this).map { it.id }
    fun json() = JSONObject().put("id", id).put("name", name).put("grade", grade)
        .put("subject", subject).put("language", language).put("size", size).put("goal", goal)
        .put("topic", topic).put("schema", 2).put("cohort", cohort)
    companion object {
        fun parse(o: JSONObject): ClassProfile {
            val profile = ClassProfile(o.getString("id"), o.getString("name"), o.getInt("grade"),
                o.getString("subject"), o.getString("language"), o.getInt("size"), o.getString("goal"),
                o.optString("topic", ""))
            require(o.optInt("schema", 1) in 1..2 &&
                (!o.has("cohort") || o.getString("cohort") == profile.cohort)) { "Unsupported profile context" }
            return profile
        }
    }
}

val LANGUAGES = linkedMapOf("en" to "English", "am" to "አማርኛ", "om" to "Afaan Oromo", "sid" to "Sidaamu Afoo")

data class ChunkEvidence(val index: Int, val startSample: Long, val endSample: Long, val commitElapsedMs: Long?) {
    fun json() = JSONObject().put("index", index).put("start_sample", startSample)
        .put("end_sample", endSample).put("commit_elapsed_ms", commitElapsedMs ?: JSONObject.NULL)
    companion object { fun parse(o: JSONObject) = ChunkEvidence(o.getInt("index"), o.getLong("start_sample"),
        o.getLong("end_sample"), if (o.isNull("commit_elapsed_ms")) null else o.getLong("commit_elapsed_ms")) }
}

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
    val chunkEvidence: List<ChunkEvidence> = emptyList(),
    val attemptedMs: Long? = null,
) {
    val expiresAt: Long get() = createdAt + RETENTION_MS
    fun json() = JSONObject().put("id", id).put("created", createdAt).put("checkpoint", checkpointAt)
        .put("profile", profile.json()).put("captured", capturedMs).put("chunks", chunks)
        .put("status", status).put("reason", reason).put("samples", samples).put("clipped", clipped)
        .put("quiet", quiet).put("excluded", excluded).put("context", context).put("note", note)
        .put("chunk_evidence", JSONArray(chunkEvidence.map { it.json() }))
        .put("attempted_ms", attemptedMs ?: JSONObject.NULL)
    companion object {
        fun parse(o: JSONObject): LocalLesson {
            val evidence = o.optJSONArray("chunk_evidence")?.let { list ->
                (0 until list.length()).map { ChunkEvidence.parse(list.getJSONObject(it)) }
            }.orEmpty()
            val lesson = LocalLesson(o.getString("id"), o.getLong("created"), o.getLong("checkpoint"),
            ClassProfile.parse(o.getJSONObject("profile")), o.getLong("captured"), o.getInt("chunks"),
            o.getString("status"), o.getString("reason"), o.getLong("samples"), o.getLong("clipped"),
            o.getLong("quiet"), o.getBoolean("excluded"), o.getString("context"), o.getString("note"),
            evidence, if (o.isNull("attempted_ms")) null else o.getLong("attempted_ms"))
            require(evidence.isEmpty() || (evidence.size == lesson.chunks && evidence.indices.all { index ->
                evidence[index].index == index && evidence[index].startSample >= 0 &&
                    evidence[index].endSample > evidence[index].startSample &&
                    (index == 0 || evidence[index].startSample >= evidence[index - 1].endSample)
            })) { "Invalid chunk evidence" }
            return lesson
        }
    }
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
    @Synchronized fun auditEvent(component: String, action: String, outcome: String, retryBucket: Int = 0) {
        require(component in setOf("capture", "storage", "research", "ui", "maintenance"))
        require(action.matches(Regex("[a-z0-9_]{3,40}")) && outcome.matches(Regex("[a-z0-9_]{2,32}")))
        require(retryBucket in 0..4)
        runCatching {
            val current = objectAt("audit")?.optJSONArray("events") ?: JSONArray()
            val kept = (maxOf(0, current.length() - 127) until current.length()).map { current.getJSONObject(it) }
            val event = JSONObject().put("at_ms", now()).put("component", component).put("action", action)
                .put("outcome", outcome).put("retry_bucket", retryBucket).put("app_version", BuildConfig.VERSION_NAME)
            put("audit", JSONObject().put("schema", 1).put("events", JSONArray(kept + event)))
        } // Audit failure must never corrupt or block the application action.
    }
    @Synchronized fun usabilityEvent(name: String, properties: Map<String, String> = emptyMap()) {
        require(name in setOf("setup_completed", "mic_check_result", "record_attempt_result", "review_opened", "practice_chosen", "support_opened"))
        require(properties.keys.all { it in setOf("result", "source", "language", "environment") })
        require(properties.values.all { it.matches(Regex("[a-z0-9_-]{1,32}")) })
        // This encrypted local buffer is deliberately not network-connected. A reviewed adapter may
        // drain synthetic events only after provider/consent/retention approval.
        if (!settings().optBoolean("syntheticAnalytics", false)) return
        runCatching {
            val current = objectAt("usability")?.optJSONArray("events") ?: JSONArray()
            val kept = (maxOf(0, current.length() - 127) until current.length()).map { current.getJSONObject(it) }
            val event = JSONObject().put("at_ms", now()).put("name", name).put("properties", JSONObject(properties))
            put("usability", JSONObject().put("schema", 1).put("events", JSONArray(kept + event)))
        }
    }
    @Synchronized fun lessons(): List<LocalLesson> {
        expire()
        val deleted = deletedIds()
        return files.names().filter { it.startsWith("lesson.") && it.removePrefix("lesson.") !in deleted }
            .mapNotNull { objectAt(it)?.let(LocalLesson::parse) }
            .sortedByDescending { it.createdAt }
    }
    private fun lesson(id: String): LocalLesson {
        check(objectAt("deletion.$id") == null) { "This recording has been deleted." }
        return LocalLesson.parse(requireNotNull(objectAt("lesson.$id")))
    }
    private fun save(value: LocalLesson) = put("lesson.${value.id}", value.json())
    private fun deletedIds() = files.names().filter { it.startsWith("deletion.") }
        .map { it.removePrefix("deletion.") }.toSet()
    private fun eraseLessonComponents(id: String, includeAudio: Boolean) {
        files.names().filter { name ->
            name == "lesson.$id" || name == "analysis.$id" || (includeAudio && name.startsWith("audio.$id."))
        }.forEach { name ->
            if (includeAudio) files.delete(name)
            else runCatching { files.delete(name) } // Tombstone already denies access; retry metadata cleanup later.
        }
    }
    private fun purgeShadowDeleted() {
        deletedIds().forEach { id ->
            eraseLessonComponents(id, includeAudio = true)
            files.delete("deletion.$id")
        }
    }
    @Synchronized fun purgeShadowDeletedRecordings() = purgeShadowDeleted()
    private fun withdrawResearchAndPurgeHidden() {
        ResearchQueue(this).withdraw()
        purgeShadowDeleted()
    }
    @Synchronized fun create(profile: ClassProfile): LocalLesson {
        require(profile.valid())
        val time = now()
        return LocalLesson(UUID.randomUUID().toString(), time, time, profile).also(::save)
    }
    /** Chunk first, then metadata: a crash never claims an uncommitted audio chunk was saved. */
    @Synchronized fun append(id: String, audio: ByteArray, durationMs: Long, samples: Long, clipped: Long, quiet: Long,
                             commitElapsedMs: Long? = null, savedSamples: Long = samples) {
        val row = lesson(id)
        check(now() < row.expiresAt && row.status == "recording")
        require(audio.isNotEmpty() && audio.size <= 512_000 && durationMs in 1..35_000 && savedSamples > 0)
        files.write("audio.$id.${row.chunks}", audio)
        val start = row.chunkEvidence.lastOrNull()?.endSample ?: 0L
        val evidence = if (row.chunks == row.chunkEvidence.size) {
            row.chunkEvidence + ChunkEvidence(row.chunks, start, start + savedSamples, commitElapsedMs)
        } else emptyList() // Legacy rows have no full timing history; never claim complete coverage.
        save(row.copy(checkpointAt = now(), capturedMs = row.capturedMs + durationMs, chunks = row.chunks + 1,
            samples = row.samples + samples, clipped = row.clipped + clipped, quiet = row.quiet + quiet,
            chunkEvidence = evidence))
    }
    @Synchronized fun finish(id: String, reason: String = "", attemptedMs: Long? = null): LocalLesson {
        val row = lesson(id)
        require(attemptedMs == null || attemptedMs >= 0)
        val outcome = reason.ifEmpty {
            if (row.chunks == 0) "No completed audio was saved. Check the microphone and try again." else ""
        }
        return row.copy(status = if (outcome.isEmpty()) "saved" else "interrupted", reason = outcome,
            attemptedMs = attemptedMs ?: row.attemptedMs).also(::save)
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
        if (excluded && !row.excluded) withdrawResearchAndPurgeHidden()
        save(row.copy(context = context, note = note, excluded = excluded))
    }
    @Synchronized fun deleteLesson(id: String) {
        lesson(id) // Reject stale UI actions and deletion of an unknown record.
        // A deletion after an acknowledged summary invalidates its research use too.
        if (researchRead("config")?.optBoolean("enabled", false) == true) withdrawResearchAndPurgeHidden()
        val deletedAt = now()
        // Commit the minimal tombstone first. Reads fail closed as soon as it exists, even if the
        // process stops before lesson metadata is removed.
        put("deletion.$id", JSONObject().put("schema", 1).put("id", id)
            .put("deleted_at", deletedAt).put("purge_at", deletedAt + SHADOW_DELETE_MS))
        eraseLessonComponents(id, includeAudio = false)
    }
    @Synchronized fun reflection(classId: String, week: LocalDate): JSONObject {
        expire()
        return objectAt("reflection.$classId.$week") ?: JSONObject()
    }
    @Synchronized fun saveReflection(classId: String, week: LocalDate, value: JSONObject) {
        val name = "reflection.$classId.$week"
        val created = objectAt(name)?.optLong("created", now()) ?: now()
        value.put("created", created)
        if (!value.has("contentVersion")) value.put("contentVersion", Coaching.VERSION)
        if (value.optString("chosen").isNotBlank() && !value.has("chosenCardVersion")) {
            value.put("chosenCardVersion", if (value.optString("contentVersion") == Coaching.VERSION) "2" else "1")
        }
        put(name, value)
    }
    @Synchronized fun expire() {
        val time = now()
        files.names().filter { it.startsWith("deletion.") }.forEach { name ->
            val id = name.removePrefix("deletion.")
            val tombstone = requireNotNull(objectAt(name))
            require(tombstone.optInt("schema") == 1 && tombstone.getString("id") == id &&
                tombstone.getLong("purge_at") == tombstone.getLong("deleted_at") + SHADOW_DELETE_MS) {
                "Invalid deletion tombstone"
            }
            eraseLessonComponents(id, includeAudio = time >= tombstone.getLong("purge_at"))
            if (time >= tombstone.getLong("purge_at")) files.delete(name)
        }
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
        val deleted = deletedIds()
        files.names().filter { it.startsWith("audio.") }.forEach { name ->
            val parts = name.split('.')
            val id = parts.getOrNull(1)
            val row = rows[id]
            if (id !in deleted && (row == null || (parts.last().toIntOrNull() ?: Int.MAX_VALUE) >= row.chunks)) files.delete(name)
        }
    }
    @Synchronized fun clearCollectedData() {
        withdrawResearchAndPurgeHidden()
        files.names().filter { it.startsWith("audio.") || it.startsWith("lesson.") || it.startsWith("reflection.") ||
            it.startsWith("analysis.") || it.startsWith("deletion.") || it == "audit" || it == "usability" }.forEach(files::delete)
    }
    @Synchronized fun withdrawResearch() = withdrawResearchAndPurgeHidden()
    fun analyzeSignal(id: String): JSONObject? = signalAnalysis(id)

    @Synchronized fun signalAnalysis(id: String): JSONObject? {
        if (objectAt("deletion.$id") != null) return null
        val data = objectAt("lesson.$id") ?: return null
        val row = LocalLesson.parse(data)
        if (row.status == "recording" || now() >= row.expiresAt) return null
        return SignalAnalysis.build(row)
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
