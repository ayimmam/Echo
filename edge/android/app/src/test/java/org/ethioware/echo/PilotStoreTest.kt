package org.ethioware.echo

import org.ethioware.echo.pilot.*
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class PilotStoreTest {
    private class Files : PrivateFiles {
        val data = mutableMapOf<String, ByteArray>()
        var failLessonWrite = false
        override fun names() = data.keys.toList()
        override fun read(name: String) = data[name]?.clone()
        override fun write(name: String, bytes: ByteArray) {
            if (failLessonWrite && name.startsWith("lesson.")) error("disk full")
            data[name] = bytes.clone()
        }
        override fun delete(name: String) { data.remove(name) }
    }
    private val files = Files()
    private var time = Instant.parse("2026-10-05T07:00:00Z").toEpochMilli()
    private val store = PilotStore(files) { time }
    private val profile = ClassProfile(id = "class-one", grade = 4, language = "om")
    private fun recorded(): LocalLesson {
        val row = store.create(profile)
        store.append(row.id, byteArrayOf(1, 2), 30_000, 480_000, 2, 0)
        return row
    }

    @Test fun `grade four and all four classroom languages are supported`() {
        LANGUAGES.keys.forEach { assertTrue(profile.copy(language = it).valid()) }
        assertFalse(profile.copy(grade = 5).valid())
        assertFalse(profile.copy(size = 0).valid())
    }
    @Test fun `secondary profile is scoped and legacy profile retains its cohort`() {
        val secondary = profile.copy(grade = 11, subject = "Mathematics", goal = "math_reasoning", topic = "Quadratic equations")
        assertTrue(secondary.valid())
        assertEquals("secondary", ClassProfile.parse(secondary.json()).cohort)
        assertEquals("Quadratic equations", ClassProfile.parse(secondary.json()).topic)
        assertFalse(secondary.copy(goal = "reading").valid())
        assertFalse(secondary.copy(grade = 10).valid())
        val legacy = profile.json().apply { remove("schema"); remove("cohort"); remove("topic") }
        assertEquals("early_grade", ClassProfile.parse(legacy).cohort)
        assertEquals("", ClassProfile.parse(legacy).topic)
        assertThrows(IllegalArgumentException::class.java) {
            ClassProfile.parse(secondary.json().put("cohort", "early_grade"))
        }
    }
    @Test fun `draft practice selection respects secondary subject`() {
        val math = profile.copy(grade = 11, subject = "Mathematics", goal = "math_reasoning")
        assertTrue(Coaching.available(math).any { it.id == "math_reasoning" })
        assertFalse(Coaching.available(math).any { it.id == "reading" })
        assertFalse(Coaching.available(profile).any { it.id == "math_reasoning" })
        assertFalse(Coaching.forGoal(math).reviewed)
    }
    @Test fun `reflection composer is useful without recording and does not invent a metric`() {
        val math = profile.copy(grade = 11, subject = "Mathematics", goal = "math_reasoning")
        val report = ReflectionComposer.compose(math, emptyList(), JSONObject(), Coaching.forGoal(math))
        assertTrue(report.observation.contains("No saved lesson audio"))
        assertTrue(report.limitation.contains("No validated acoustic observation"))
        assertNotNull(report.action)
        assertFalse(report.observation.contains("understood"))
    }
    @Test fun `recording snapshots profile rather than later class edits`() {
        val row = recorded()
        store.finish(row.id)
        store.saveSettings(JSONObject().put("class", profile.copy(grade = 1).json()))
        assertEquals(4, store.lessons().single().profile.grade)
        assertEquals(30_000L, store.lessons().single().capturedMs)
    }
    @Test fun `retention expires audio notes and reflections exactly at thirty days`() {
        val row = recorded()
        store.correct(row.id, "Partner or group work", "Local context", false)
        store.saveReflection(profile.id, LocalDate.parse("2026-10-05"), JSONObject().put("chosen", "understanding"))
        store.saveSettings(JSONObject().put("locale", "om"))
        time += RETENTION_MS - 1
        assertEquals(1, store.lessons().size)
        time++
        assertTrue(store.lessons().isEmpty())
        assertTrue(files.names().none { it.startsWith("audio.") || it.startsWith("reflection.") })
        assertEquals("om", store.settings().getString("locale"))
    }
    @Test fun `changing a reflection does not restart its retention clock`() {
        val week = LocalDate.parse("2026-10-05")
        store.saveReflection(profile.id, week, JSONObject().put("chosen", "reading"))
        time += RETENTION_MS - 1
        store.saveReflection(profile.id, week, JSONObject().put("chosen", "understanding"))
        time++
        assertEquals(0, store.reflection(profile.id, week).length())
    }
    @Test fun `clock rollback cannot resurrect expired data`() {
        recorded()
        val start = time
        time += RETENTION_MS
        store.expire()
        time = start
        assertTrue(store.lessons().isEmpty())
        assertEquals(start + RETENTION_MS, store.now())
    }
    @Test fun `playback denies expired audio even before maintenance runs`() {
        val row = recorded()
        assertArrayEquals(byteArrayOf(1, 2), store.audio(row.id, 0))
        time += RETENTION_MS
        assertThrows(IllegalStateException::class.java) { store.audio(row.id, 0) }
    }
    @Test fun `recovery marks interruption without inventing elapsed duration`() {
        recorded()
        time += 86_400_000
        PilotStore(files) { time }.recover()
        val row = store.lessons().single()
        assertEquals("interrupted", row.status)
        assertEquals(30_000L, row.capturedMs)
        assertTrue(row.reason.contains("completed chunks"))
    }
    @Test fun `committed chunks retain bounded sample evidence and attempted time`() {
        val row = store.create(profile)
        store.append(row.id, byteArrayOf(1, 2), 1000, 16_000, 0, 0, commitElapsedMs = 1200, savedSamples = 16_000)
        store.finish(row.id, attemptedMs = 1500)
        val saved = store.lessons().single()
        assertEquals(1, saved.chunkEvidence.size)
        assertEquals(0L, saved.chunkEvidence.single().startSample)
        assertEquals(16_000L, saved.chunkEvidence.single().endSample)
        assertEquals(1500L, saved.attemptedMs)
        assertEquals(1000.0 / 1500, store.analyzeSignal(row.id)!!.getDouble("capture_fraction"), 0.0001)
        assertFalse(store.signalAnalysis(row.id)!!.getBoolean("gap_timing_available"))
    }
    @Test fun `crash between audio and metadata does not count uncommitted audio`() {
        val row = recorded()
        files.failLessonWrite = true
        assertThrows(IllegalStateException::class.java) { store.append(row.id, byteArrayOf(3), 30_000, 1, 0, 0) }
        files.failLessonWrite = false
        store.recover()
        assertEquals(1, store.lessons().single().chunks)
        assertFalse(files.names().contains("audio.${row.id}.1"))
    }
    @Test fun `corrections leave original recording facts intact`() {
        val row = recorded()
        store.correct(row.id, "Other adult or recorded audio", "A colleague visited", true)
        val changed = store.lessons().single()
        assertEquals(30_000L, changed.capturedMs)
        assertEquals(2L, changed.clipped)
        assertTrue(changed.excluded)
        assertEquals("A colleague visited", changed.note)
    }
    @Test fun `weekly reflection isolates classes dates and excluded lessons`() {
        val row = recorded()
        val other = store.create(profile.copy(id = "class-two"))
        store.append(other.id, byteArrayOf(1), 2000, 10, 0, 0)
        val week = LocalDate.parse("2026-10-05")
        assertEquals(1, weeklyLessons(store.lessons(), profile.id, week, ZoneId.of("Africa/Addis_Ababa")).size)
        store.correct(row.id, "Not specified", "", true)
        assertTrue(weeklyLessons(store.lessons(), profile.id, week, ZoneId.of("Africa/Addis_Ababa")).isEmpty())
    }
    @Test fun `deletion removes only the selected lesson and its chunks`() {
        val first = recorded()
        val second = recorded()
        store.deleteLesson(first.id)
        assertEquals(second.id, store.lessons().single().id)
        assertTrue(files.names().none { it.contains(first.id) })
    }
    @Test fun `erase collected data preserves class configuration`() {
        recorded()
        store.saveSettings(JSONObject().put("selected", profile.id))
        store.clearCollectedData()
        assertTrue(store.lessons().isEmpty())
        assertEquals(profile.id, store.settings().getString("selected"))
    }
    @Test fun `settings patches preserve reminder deduplication state`() {
        store.saveSettings(JSONObject().put("remindedWeek", "2026-10-05").put("locale", "en"))
        store.patchSettings(JSONObject().put("locale", "om"))
        assertEquals("2026-10-05", store.settings().getString("remindedWeek"))
        assertEquals("om", store.settings().getString("locale"))
    }
    @Test fun `a corrupt record is not replaced by synthetic or empty data`() {
        val row = recorded()
        files.data["lesson.${row.id}"] = "corrupt".toByteArray()
        assertThrows(Exception::class.java) { store.lessons() }
        assertTrue(files.names().any { it.startsWith("audio.") })
    }
    @Test fun `reading specific coaching is not suggested for mathematics`() {
        assertEquals("understanding", Coaching.forGoal("reading", "Mathematics").id)
        assertEquals("reading", Coaching.forGoal("reading", "Reading").id)
    }
}
