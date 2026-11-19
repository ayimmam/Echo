package org.ethioware.echo.pilot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable internal fun HistoryScreen(vm: PilotViewModel, locale: String, open: (String) -> Unit) {
    val lessons = vm.lessons
    val classId = vm.selectedId
    val rows = remember(lessons, classId) { lessons.filter { it.profile.id == classId && it.status != "recording" } }
    var query by rememberSaveable(classId) { mutableStateOf("") }
    val filtered = remember(rows, query, locale) {
        rows.filter { lessonTitle(it, locale).contains(query.trim(), ignoreCase = true) }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 22.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item { PageTitle(tr(locale, "Recording history", "የቅጂ ታሪክ"), "", locale) }
        if (vm.profiles.size > 1) item { ClassChoice(vm, locale) }
        if (rows.size > 4) item {
            OutlinedTextField(query, { query = it }, modifier = Modifier.fillMaxWidth(), singleLine = true,
                shape = RoundedCornerShape(14.dp), label = { Text(tr(locale, "Find recordings", "ቅጂዎችን ፈልግ")) })
        }
        if (filtered.isEmpty()) item {
            if (rows.isEmpty()) EmptyCard(tr(locale, "No recordings yet", "ገና ምንም ቅጂ የለም"),
                tr(locale, "Start a lesson recording from Home.", "ከመነሻ ገጽ የትምህርት ቅጂ ይጀምሩ።"))
            else Text(tr(locale, "No matching recordings.", "የሚዛመድ ቅጂ የለም።"), color = PilotMuted)
        }
        items(filtered, key = { it.id }) { row -> LessonSummary(row, locale) { open(row.id) } }
    }
}

@Composable internal fun LessonDetail(vm: PilotViewModel, row: LocalLesson, locale: String, back: () -> Unit) {
    var editing by rememberSaveable(row.id) { mutableStateOf(false) }
    var deleting by rememberSaveable(row.id) { mutableStateOf(false) }
    var lessonContext by rememberSaveable(row.id, row.context) { mutableStateOf(row.context) }
    var note by rememberSaveable(row.id, row.note) { mutableStateOf(row.note) }
    var excluded by rememberSaveable(row.id, row.excluded) { mutableStateOf(row.excluded) }
    var excerpt by rememberSaveable(row.id) { mutableIntStateOf(0) }
    val playing = vm.playing == row.id
    Page(lessonTitle(row, locale), durationText(row.capturedMs, locale), locale, back) {
        if (row.reason.isNotEmpty()) Notice(localizedStatus(row.reason, locale))
        if (row.chunks > 0) PilotCard {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(shape = RoundedCornerShape(48.dp), color = PilotMint) {
                    Box(Modifier.padding(horizontal = 32.dp, vertical = 16.dp), contentAlignment = Alignment.Center) {
                        FilledIconButton(onClick = { if (playing) vm.stopPlayback() else vm.play(row, excerpt) },
                            enabled = !vm.busy, modifier = Modifier.size(60.dp)) {
                            val label = if (playing) tr(locale, "Stop playback", "ማጫወት አቁም") else tr(locale, "Play excerpt", "ቅንጭቡን አጫውት")
                            if (playing) Box(Modifier.size(16.dp).background(Color.White).semantics { contentDescription = label })
                            else Icon(Icons.Default.PlayArrow, label, modifier = Modifier.size(28.dp))
                        }
                    }
                }
                if (row.chunks > 1) Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = { vm.stopPlayback(); excerpt-- }, enabled = excerpt > 0) {
                        Text(tr(locale, "Previous", "ቀዳሚ"))
                    }
                    Text("${excerpt + 1} / ${row.chunks}", style = MaterialTheme.typography.labelMedium)
                    TextButton(onClick = { vm.stopPlayback(); excerpt++ }, enabled = excerpt < row.chunks - 1) {
                        Text(tr(locale, "Next", "ቀጣይ"))
                    }
                }
                Text(tr(locale, "Listen privately with headphones.", "በጆሮ ማዳመጫ በግል ያዳምጡ።"),
                    Modifier.padding(top = 8.dp), color = PilotMuted, style = MaterialTheme.typography.bodySmall)
            }
        }
        OfflineSignalCard(row, locale)
        PilotCard {
            SectionTitle(tr(locale, "Notes", "ማስታወሻዎች"), if (!editing) tr(locale, "Edit", "አርትዕ") else null) { editing = true }
            if (!editing) {
                Text(row.note.ifBlank { tr(locale, "Add a private note about this lesson.", "ስለዚህ ትምህርት የግል ማስታወሻ ያክሉ።") })
                if (row.context != "Not specified") Text(localizedContext(row.context, locale), color = PilotMuted)
                if (row.excluded) Text(tr(locale, "Left out of weekly reflection", "ከሳምንታዊ ምልከታ ወጥቷል"), color = PilotMuted)
            } else {
                OutlinedTextField(note, { note = it.take(500) }, label = { Text(tr(locale, "Private note", "የግል ማስታወሻ")) },
                    supportingText = { Text(tr(locale, "Leave out learner names.", "የተማሪዎችን ስም አያካትቱ።")) },
                    modifier = Modifier.fillMaxWidth(), enabled = !vm.busy)
                Choice(tr(locale, "What was happening?", "ምን እየተካሄደ ነበር?"), lessonContext,
                    PilotStore.CONTEXTS.associateWith { localizedContext(it, locale) }, !vm.busy) { lessonContext = it }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(excluded, { excluded = it }, enabled = !vm.busy)
                    Text(tr(locale, "Leave out of weekly reflection", "ከሳምንታዊ ምልከታ አስቀር"))
                }
                Button(onClick = { vm.correct(row, lessonContext, note, excluded); editing = false },
                    enabled = !vm.busy, modifier = Modifier.fillMaxWidth()) { Text(tr(locale, "Save changes", "ለውጦችን አስቀምጥ")) }
            }
        }
        TextButton(onClick = { deleting = true }, enabled = !vm.busy,
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)) {
            Text(tr(locale, "Delete recording", "ቅጂውን ሰርዝ"))
        }
    }
    if (deleting) AlertDialog(
        onDismissRequest = { deleting = false },
        title = { Text(tr(locale, "Delete this recording?", "ይህን ቅጂ ይሰረዝ?")) },
        text = { Text(tr(locale, "It disappears from History and Reflection immediately. Encrypted audio is erased after 15 days.", "ከታሪክና ከምልከታ ወዲያውኑ ይወገዳል። የተመሰጠረው ድምጽ ከ15 ቀን በኋላ ይሰረዛል።")) },
        confirmButton = { TextButton(onClick = { deleting = false; vm.delete(row) }) { Text(tr(locale, "Delete recording", "ቅጂውን ሰርዝ")) } },
        dismissButton = { TextButton(onClick = { deleting = false }) { Text(tr(locale, "Cancel", "ይቅር")) } },
    )
}

@Composable private fun OfflineSignalCard(row: LocalLesson, locale: String) {
    val signal = remember(row) { SignalAnalysis.evidence(row) }
    PilotCard {
        Text(tr(locale, "Audio check", "የድምጽ ፍተሻ"), style = MaterialTheme.typography.titleMedium)
        when {
            signal == null || !signal.enoughInput -> Text(tr(locale, "Too little input data for an audio check.", "ለድምጽ ፍተሻ በቂ መረጃ የለም።"), color = PilotMuted)
            signal.frequentClipping || signal.mostlyNearZero -> {
                if (signal.frequentClipping) Text(tr(locale, "Some audio may sound distorted. Try moving the mic farther away.", "አንዳንድ ድምጽ ሊዛባ ይችላል። ማይኩን ትንሽ ራቅ ያድርጉ።"))
                if (signal.mostlyNearZero) Text(tr(locale, "The input was very low. Check mic placement, power and mute.", "የገባው ድምጽ በጣም ዝቅተኛ ነበር። የማይኩን አቀማመጥ፣ ኃይልና ድምጸ ከል ሁኔታ ይፈትሹ።"))
            }
            else -> Text(tr(locale, "Input passed the basic level checks.", "የገባው ድምጽ መሠረታዊ ፍተሻዎችን አልፏል።"), color = PilotAccent)
        }
        Text(tr(locale, "Checks microphone levels on this phone.", "በዚህ ስልክ ላይ የማይክሮፎኑን የድምጽ መጠን ይፈትሻል።"),
            color = PilotMuted, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable internal fun ReflectionScreen(vm: PilotViewModel, locale: String) {
    val rows = vm.weekly
    val report = vm.report
    val card = vm.chosen ?: vm.suggested
    val text = localizedCard(card, locale)
    var practiceDetails by rememberSaveable { mutableStateOf(false) }
    var weekDetails by rememberSaveable { mutableStateOf(false) }
    Page(tr(locale, "Weekly reflection", "ሳምንታዊ ምልከታ"), tr(locale, "Your notes stay on this device.", "ማስታወሻዎችዎ በዚህ መሣሪያ ላይ ይቆያሉ።"), locale) {
        if (vm.profiles.size > 1) ClassChoice(vm, locale)
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { vm.moveWeek(-1) }, enabled = !vm.busy && vm.week > monday(LocalDate.now().minusDays(30))) { Text(tr(locale, "Earlier", "ቀደም")) }
            val date = vm.week.format(DateTimeFormatter.ofPattern("MMM d", Locale.forLanguageTag(locale)))
            Text(tr(locale, "Week of $date", "የ$date ሳምንት"), style = MaterialTheme.typography.labelLarge)
            TextButton(onClick = { vm.moveWeek(1) }, enabled = !vm.busy && vm.week < monday(LocalDate.now())) { Text(tr(locale, "Later", "ቀጣይ")) }
        }
        PilotCard {
            SectionTitle(tr(locale, "Your recorded week", "የተቀዳው ሳምንትዎ"),
                if (rows.isEmpty()) null else if (weekDetails) tr(locale, "Hide details", "ዝርዝር ደብቅ") else tr(locale, "Details", "ዝርዝር")) {
                weekDetails = !weekDetails
            }
            Text(report?.observation.orEmpty())
            if (weekDetails && rows.isNotEmpty()) {
                val longest = rows.maxOf { it.capturedMs }.coerceAtLeast(1)
                rows.forEach { row ->
                    Text("${lessonTitle(row, locale)} · ${durationText(row.capturedMs, locale)}", style = MaterialTheme.typography.bodySmall)
                    LinearProgressIndicator(progress = { row.capturedMs.toFloat() / longest }, modifier = Modifier.fillMaxWidth(), color = PilotNavy)
                }
            }
            if (weekDetails) Text(report?.limitation.orEmpty(), color = PilotMuted, style = MaterialTheme.typography.bodySmall)
        }
        key(vm.selectedId, vm.week) { ReflectionNotes(vm, locale) }
        Coaching.cards.firstOrNull { it.id == vm.previousReflection.optString("chosen") }?.let { previous ->
            PilotCard {
                Text(tr(locale, "Last week’s practice", "ያለፈው ሳምንት ልምምድ"), style = MaterialTheme.typography.titleMedium)
                Text(localizedCard(previous, locale).title, fontWeight = FontWeight.SemiBold)
                Choice(tr(locale, "Did you try it?", "ሞክረውታል?"), vm.reflection.optString("tried"), linkedMapOf("yes" to tr(locale, "I tried it", "ሞክሬዋለሁ"), "notyet" to tr(locale, "Not yet", "ገና"), "notuseful" to tr(locale, "It did not fit", "አልተስማማም")), !vm.busy) { vm.updateReflection("tried", it) }
                Text(report?.followUp.orEmpty(), color = PilotMuted)
            }
        }
        PilotCard {
            Text(tr(locale, "Practice card", "የልምምድ ካርድ"), color = Color(0xFF147565), style = MaterialTheme.typography.labelLarge)
            Text(text.title, style = MaterialTheme.typography.titleLarge)
            Text(text.steps.first())
            TextButton(onClick = { practiceDetails = !practiceDetails }) {
                Text(if (practiceDetails) tr(locale, "Hide practice details", "የልምምድ ዝርዝር ደብቅ") else tr(locale, "See how to try it", "እንዴት እንደሚሞከር ይመልከቱ"))
            }
            if (practiceDetails) {
                text.steps.drop(1).forEach { Text(it) }
                Text("“${text.example}”", color = PilotNavy, fontWeight = FontWeight.SemiBold)
                Text(text.notFitWhen, color = PilotMuted)
                Text(tr(locale, "Draft guidance for your teaching goal.", "ለማስተማር ግብዎ የሚሆን ረቂቅ መመሪያ።"), color = PilotMuted,
                    style = MaterialTheme.typography.bodySmall)
            }
            Button(onClick = { vm.updateReflection("chosen", card.id) }, enabled = !vm.busy && vm.chosen?.id != card.id, modifier = Modifier.fillMaxWidth()) { Text(if (vm.chosen?.id == card.id) tr(locale, "Chosen for next week", "ለሚቀጥለው ሳምንት ተመርጧል") else tr(locale, "Try this next week", "በሚቀጥለው ሳምንት ሞክር")) }
            Choice(tr(locale, "Choose another practice", "ሌላ ልምምድ ይምረጡ"), card.id, vm.profile?.let(Coaching::available).orEmpty().associate { it.id to localizedCard(it, locale).title }, !vm.busy) { vm.updateReflection("chosen", it) }
        }
        PilotCard {
            Text(tr(locale, "Was this review useful?", "ይህ ምልከታ ጠቃሚ ነበር?"), style = MaterialTheme.typography.titleMedium)
            Choice(tr(locale, "Rating", "ደረጃ"), vm.reflection.optString("rating"), linkedMapOf(
                "1" to tr(locale, "1 — Not useful", "1 — ጠቃሚ አልነበረም"), "2" to tr(locale, "2 — Slightly useful", "2 — ትንሽ ጠቃሚ"),
                "3" to tr(locale, "3 — Somewhat useful", "3 — መጠነኛ ጠቃሚ"), "4" to tr(locale, "4 — Useful", "4 — ጠቃሚ"), "5" to tr(locale, "5 — Very useful", "5 — በጣም ጠቃሚ")), !vm.busy) { vm.updateReflection("rating", it) }
            Text(tr(locale, "The rating stays local unless you explicitly submit an enrolled research review.", "በጥናት ከተመዘገቡ በኋላ ምልከታውን በግልጽ ካላስገቡ በስተቀር ደረጃው በስልኩ ላይ ይቆያል።"), color = PilotMuted, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable private fun ReflectionNotes(vm: PilotViewModel, locale: String) {
    val saved = vm.reflection.optJSONObject("notes")
    var editing by rememberSaveable { mutableStateOf(false) }
    var participation by rememberSaveable(saved?.optString("participation")) { mutableStateOf(saved?.optString("participation").orEmpty()) }
    var explanation by rememberSaveable(saved?.optString("explanation")) { mutableStateOf(saved?.optString("explanation").orEmpty()) }
    var nextWeek by rememberSaveable(saved?.optString("next_week")) { mutableStateOf(saved?.optString("next_week").orEmpty()) }
    val prompts = listOf(
        tr(locale, "Did learners have enough chances to participate?", "ተማሪዎች በቂ የመሳተፍ ዕድል አግኝተዋል?"),
        tr(locale, "Was my explanation clear?", "ማብራሪያዬ ግልጽ ነበር?"),
        tr(locale, "What will I improve next week?", "በሚቀጥለው ሳምንት ምን አሻሽላለሁ?"),
    )
    PilotCard {
        SectionTitle(tr(locale, "Reflection notes", "የምልከታ ማስታወሻዎች"),
            if (editing) tr(locale, "Close", "ዝጋ") else if (saved == null) tr(locale, "Add notes", "ማስታወሻ ጨምር") else tr(locale, "Edit", "አርትዕ")) {
            editing = !editing
        }
        if (editing) {
            ReflectionField(prompts[0], participation, !vm.busy) { participation = it }
            ReflectionField(prompts[1], explanation, !vm.busy) { explanation = it }
            ReflectionField(prompts[2], nextWeek, !vm.busy) { nextWeek = it }
            Text(tr(locale, "Leave out learner names.", "የተማሪዎችን ስም አያካትቱ።"), color = PilotMuted)
            Button(onClick = { vm.saveReflectionNotes(participation, explanation, nextWeek); editing = false },
                enabled = !vm.busy, modifier = Modifier.fillMaxWidth()) { Text(tr(locale, "Save reflection", "ምልከታን አስቀምጥ")) }
        } else if (saved != null) {
            Text(tr(locale, "Your notes are saved.", "ማስታወሻዎችዎ ተቀምጠዋል።"), color = PilotMuted)
        }
    }
}

@Composable private fun ReflectionField(prompt: String, value: String, enabled: Boolean, change: (String) -> Unit) {
    Text(prompt, fontWeight = FontWeight.SemiBold)
    OutlinedTextField(value, { change(it.take(500)) },
        modifier = Modifier.fillMaxWidth().semantics { contentDescription = prompt },
        enabled = enabled, minLines = 2)
}


@Composable private fun EmptyCard(title: String, body: String) = PilotCard {
    PilotGlyph(PilotIcon.Clipboard, PilotAccent)
    Text(title, style = MaterialTheme.typography.titleMedium)
    Text(body, color = PilotMuted)
}

@Composable private fun Notice(text: String) = Surface(color = PilotMint, shape = RoundedCornerShape(14.dp)) {
    Text(text, Modifier.padding(14.dp), style = MaterialTheme.typography.bodySmall)
}

private fun localizedContext(value: String, locale: String) = if (locale != "am") value else when (value) { "Not specified" -> "አልተገለጸም"; "Teacher modeling" -> "የመምህር ማሳያ"; "Whole-class practice" -> "የሙሉ ክፍል ልምምድ"; "Partner or group work" -> "የጥንድ ወይም የቡድን ሥራ"; "Independent practice" -> "የግል ልምምድ"; else -> "ሌላ አዋቂ ወይም የተቀዳ ድምጽ" }

@Composable internal fun LessonSummary(row: LocalLesson, locale: String, click: () -> Unit) = PilotCard(onClick = click) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Surface(shape = RoundedCornerShape(22.dp), color = PilotMint, contentColor = PilotNavy) {
            Box(Modifier.size(42.dp), contentAlignment = Alignment.Center) {
                if (row.chunks > 0) Icon(Icons.Default.PlayArrow, null)
                else PilotGlyph(PilotIcon.Clipboard, PilotNavy)
            }
        }
        Column(Modifier.weight(1f)) {
            Text(lessonTitle(row, locale), fontWeight = FontWeight.SemiBold)
            val duration = durationText(row.capturedMs, locale)
            val subtitle = if (row.status == "interrupted") "$duration · ${localizedStatus(row.status, locale)}" else duration
            Text(subtitle, color = PilotMuted, style = MaterialTheme.typography.bodySmall)
        }
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
    }
}
private fun lessonTitle(row: LocalLesson, locale: String): String {
    val date = Instant.ofEpochMilli(row.createdAt).atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("MMM d · HH:mm", Locale.forLanguageTag(locale)))
    return "${localizedSubject(row.profile.subject, locale)} · $date"
}
