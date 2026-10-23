package org.ethioware.echo.pilot

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.delay
import org.ethioware.echo.ui.theme.EchoTheme

@Composable
fun PilotApp(vm: PilotViewModel, openDemo: () -> Unit) {
    val state = RecordingMonitor.state
    val owner = LocalLifecycleOwner.current
    DisposableEffect(owner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) vm.refresh()
            if (event == Lifecycle.Event.ON_STOP) vm.stopPlayback()
        }
        owner.lifecycle.addObserver(observer)
        onDispose { owner.lifecycle.removeObserver(observer) }
    }
    LaunchedEffect(state.running) { if (!state.running) vm.refresh() }
    LaunchedEffect(Unit) { while (true) { delay(60_000); if (!RecordingMonitor.state.running) vm.refresh() } }
    val permission = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { granted ->
        if (granted[Manifest.permission.RECORD_AUDIO] == true) vm.record() else vm.permissionDenied()
    }
    val reminderPermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted -> vm.updateReminders(granted) }
    var editing by rememberSaveable { mutableStateOf(false) }
    var adding by rememberSaveable { mutableStateOf(false) }
    EchoTheme {
        Scaffold(bottomBar = {
            NavigationBar {
                listOf("Today" to Icons.Default.Home, "Reflection" to Icons.Default.DateRange, "Settings" to Icons.Default.Settings)
                    .forEachIndexed { index, (name, icon) ->
                        NavigationBarItem(selected = vm.tab == index, onClick = { vm.tab = index },
                            icon = { Icon(icon, contentDescription = null) }, label = { Text(name) })
                    }
            }
        }) { inset ->
            Column(Modifier.fillMaxSize().padding(inset).verticalScroll(rememberScrollState()).padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text("Echo", style = MaterialTheme.typography.headlineMedium)
                Text(if (vm.researchOrigin.isBlank()) "Local recording preview · Research transfer unavailable"
                    else "Local recording preview · Optional research sharing", style = MaterialTheme.typography.labelLarge)
                if (vm.locale != "en") Notice("${LANGUAGES[vm.locale]} selected. This new flow currently uses English preview copy; reviewed translations are not installed.")
                vm.error?.let { Notice(it); OutlinedButton(onClick = vm::refresh) { Text("Retry") } }
                if (!vm.loaded) {
                    Text("Opening protected local storage…")
                } else {
                    if (state.running) {
                        Panel("Recording · ${state.seconds / 60}:${(state.seconds % 60).toString().padStart(2, '0')}") {
                            Text(state.message)
                            Button(onClick = vm::stop, modifier = Modifier.fillMaxWidth()) { Text("Stop and save") }
                            Text("Lock your phone and continue teaching. Completed audio is encrypted in short chunks.", style = MaterialTheme.typography.bodySmall)
                        }
                    } else if (state.message.isNotEmpty()) Notice(state.message)
                    if (vm.profile == null || editing) {
                        ProfileEditor(if (adding) null else vm.profile, vm.busy || state.running,
                            onSave = { vm.saveProfile(it); editing = false; adding = false },
                            onCancel = if (vm.profile != null) ({ editing = false; adding = false }) else null)
                    } else when (vm.tab) {
                        0 -> {
                            Choice("Class", vm.selectedId, vm.profiles.associate { it.id to "${it.name} · Grade ${it.grade}" },
                                enabled = !state.running && !vm.busy, onChange = vm::selectClass)
                            Text("${vm.profile!!.subject} · ${LANGUAGES[vm.profile!!.language]} · ${vm.profile!!.size} learners")
                            if (!state.running) {
                                Button(onClick = {
                                    val permissions = if (Build.VERSION.SDK_INT >= 33) arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.POST_NOTIFICATIONS)
                                        else arrayOf(Manifest.permission.RECORD_AUDIO)
                                    permission.launch(permissions)
                                }, enabled = !vm.busy, modifier = Modifier.fillMaxWidth()) { Text("Record lesson") }
                                Text(if (vm.externalMic) "Connect your wired or USB microphone first. Echo stops if the selected route changes."
                                    else "Phone microphone selected for testing. Room-wide speech may not be captured.")
                                Text("This button records real audio. Use only where recording is authorized. The current build is for device testing; automatic teaching analysis is not validated.", style = MaterialTheme.typography.bodySmall)
                            }
                            OutlinedButton(onClick = { editing = true }, enabled = !state.running && !vm.busy) { Text("Edit class details") }
                            Text("Recent lessons", style = MaterialTheme.typography.titleLarge)
                            if (vm.lessons.isEmpty()) Text("No recordings yet. Your first saved lesson will appear here.")
                            vm.lessons.filter { it.profile.id == vm.selectedId }.take(8).forEach { row -> LessonPanel(vm, row, state.running) }
                        }
                        1 -> ReflectionScreen(vm, state.running)
                        2 -> {
                            Choice("App language", vm.locale, LANGUAGES, !vm.busy, vm::selectLanguage)
                            Text("Language packs must be reviewed locally before classroom use. Classroom language is saved separately in each profile.", style = MaterialTheme.typography.bodySmall)
                            Choice("Microphone", if (vm.externalMic) "external" else "phone",
                                mapOf("external" to "Wired / USB external mic", "phone" to "Phone mic — device testing"), !state.running && !vm.busy) { vm.selectMic(it == "external") }
                            OutlinedButton(onClick = { adding = true; editing = true }, enabled = !state.running && !vm.busy) { Text("Add another class") }
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Switch(checked = vm.reminders, enabled = !vm.busy, onCheckedChange = {
                                    if (it && Build.VERSION.SDK_INT >= 33) reminderPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
                                    else vm.updateReminders(it)
                                })
                                Text("Weekend reflection reminder")
                            }
                            Text("One local reminder on the weekend, when Android permits background work. No internet required.", style = MaterialTheme.typography.bodySmall)
                            Panel("Privacy and retention") {
                                Text("Audio and reflections are encrypted locally. Collected data expires after 30 days; deletion runs on app opening and scheduled maintenance. If the phone is off or Echo is force-stopped, deletion resumes when Android allows it.")
                                Text(vm.researchStatus)
                                if (vm.researchOrigin.isBlank()) Text("Research server not configured in this build. No data is sent.")
                                else ResearchSettings(vm, state.running)
                                Text("Saved class settings remain until you edit them or clear app data. System backups are disabled.")
                                DeleteAll(vm, state.running)
                            }
                            Panel("Development status") {
                                Text("Coaching content is source-linked draft material awaiting Hawassa educator review. Talk ratio, question quality, participation and understanding are not inferred from these recordings.")
                                OutlinedButton(onClick = openDemo, enabled = !state.running) { Text("Open separate synthetic demo") }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable private fun Panel(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            content()
        }
    }
}
@Composable private fun Notice(text: String) {
    Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
        Text(text, Modifier.padding(12.dp), style = MaterialTheme.typography.bodySmall)
    }
}
@Composable private fun Choice(label: String, value: String, options: Map<String, String>, enabled: Boolean = true, onChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Text(label, style = MaterialTheme.typography.labelLarge)
        Box {
            OutlinedButton(onClick = { expanded = true }, enabled = enabled, modifier = Modifier.fillMaxWidth()) { Text(options[value] ?: "Choose") }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { (key, text) -> DropdownMenuItem(text = { Text(text) }, onClick = { onChange(key); expanded = false }) }
            }
        }
    }
}

@Composable private fun ProfileEditor(original: ClassProfile?, disabled: Boolean, onSave: (ClassProfile) -> Unit, onCancel: (() -> Unit)?) {
    var value by remember(original?.id) { mutableStateOf(original ?: ClassProfile()) }
    var size by remember(original?.id) { mutableStateOf(value.size.toString()) }
    Panel(if (original == null) "Set up a class once" else "Class details") {
        OutlinedTextField(value.name, { value = value.copy(name = it.take(60)) }, label = { Text("Class name (no pupil names)") }, enabled = !disabled, modifier = Modifier.fillMaxWidth())
        Choice("Grade", value.grade.toString(), ((1..4).toList() + listOf(11, 12)).associate { it.toString() to "Grade $it" }, !disabled) {
            val changed = value.copy(grade = it.toInt())
            value = changed.copy(goal = if (Coaching.available(changed).any { card -> card.id == changed.goal }) changed.goal else "understanding")
        }
        Choice("Subject", value.subject, listOf("Reading", "Mathematics", "Other").associateWith { it }, !disabled) { value = value.copy(subject = it) }
        if (value.cohort == "secondary") OutlinedTextField(value.topic, { value = value.copy(topic = it.take(80)) },
            label = { Text("Curriculum topic (optional)") }, enabled = !disabled, modifier = Modifier.fillMaxWidth())
        Choice("Classroom language", value.language, LANGUAGES, !disabled) { value = value.copy(language = it) }
        OutlinedTextField(size, { size = it.filter(Char::isDigit).take(3) }, label = { Text("Approximate number of learners") }, enabled = !disabled, modifier = Modifier.fillMaxWidth())
        Choice("Your teaching goal", value.goal, Coaching.available(value).associate { it.id to it.title }, !disabled) { value = value.copy(goal = it) }
        val number = size.toIntOrNull()
        val candidate = value.copy(size = number ?: 0, goal = if (Coaching.available(value).any { it.id == value.goal }) value.goal else "understanding")
        Button(onClick = { onSave(candidate) }, enabled = !disabled && candidate.valid(), modifier = Modifier.fillMaxWidth()) { Text("Save class") }
        onCancel?.let { OutlinedButton(onClick = it, enabled = !disabled) { Text("Cancel") } }
    }
}

@Composable private fun LessonPanel(vm: PilotViewModel, row: LocalLesson, recording: Boolean) {
    var expanded by remember(row.id) { mutableStateOf(false) }
    var deleting by remember(row.id) { mutableStateOf(false) }
    var context by remember(row.id, row.context) { mutableStateOf(row.context) }
    var note by remember(row.id, row.note) { mutableStateOf(row.note) }
    var excluded by remember(row.id, row.excluded) { mutableStateOf(row.excluded) }
    var excerpt by remember(row.id) { mutableIntStateOf(0) }
    val time = Instant.ofEpochMilli(row.createdAt).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("EEE d MMM · HH:mm"))
    Panel(time) {
        Text("${row.capturedMs / 60_000} min ${row.capturedMs / 1000 % 60} sec saved · ${row.status}")
        if (row.reason.isNotEmpty()) Text(row.reason)
        if (row.excluded) Text("Excluded from weekly reflection")
        if (row.samples > 0 && row.clipped.toDouble() / row.samples > .01) Text("Audio clipping detected. Check microphone placement before the next lesson.")
        if (row.samples > 0 && row.quiet.toDouble() / row.samples > .95) Text("Most captured samples were near zero. Check the microphone; this does not mean learners were silent.")
        OutlinedButton(onClick = { expanded = !expanded }, enabled = !recording) { Text(if (expanded) "Hide details" else "Context, correction or deletion") }
        if (expanded) {
            if (row.chunks > 0) {
                Choice("Local excerpt", excerpt.toString(), (0 until row.chunks).associate {
                    it.toString() to "Excerpt ${it + 1} · about ${it * 30 / 60}m ${it * 30 % 60}s"
                }, !vm.busy) { excerpt = it.toInt() }
                OutlinedButton(onClick = { if (vm.playing == row.id) vm.stopPlayback() else vm.play(row, excerpt) }, enabled = !vm.busy) {
                    Text(if (vm.playing == row.id) "Stop playback" else "Play excerpt on phone")
                }
                Text("Use headphones for private listening. Excerpts may include children's voices.", style = MaterialTheme.typography.bodySmall)
            }
            Choice("What was happening?", context, PilotStore.CONTEXTS.associateWith { it }, !vm.busy) { context = it }
            OutlinedTextField(note, { note = it.take(500) }, label = { Text("Optional private note (no pupil names)") }, modifier = Modifier.fillMaxWidth(), enabled = !vm.busy)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(excluded, { excluded = it }, enabled = !vm.busy); Text("Leave out of weekly reflection")
            }
            Button(onClick = { vm.correct(row, context, note, excluded); expanded = false }, enabled = !vm.busy) { Text("Save correction") }
            Text("Audio observations are preserved; your context is stored separately. Expires ${Instant.ofEpochMilli(row.expiresAt).atZone(ZoneId.systemDefault()).toLocalDate()}.", style = MaterialTheme.typography.bodySmall)
            TextButton(onClick = { deleting = true }, enabled = !vm.busy) { Text("Delete lesson and audio") }
        }
    }
    if (deleting) AlertDialog(onDismissRequest = { deleting = false }, title = { Text("Delete this lesson?") },
        text = { Text("This permanently removes its local audio, notes and summary.") },
        confirmButton = { TextButton(onClick = { deleting = false; vm.delete(row) }) { Text("Delete") } },
        dismissButton = { TextButton(onClick = { deleting = false }) { Text("Keep") } })
}

@Composable private fun ReflectionScreen(vm: PilotViewModel, recording: Boolean) {
    val rows = vm.weekly
    val report = vm.report
    var details by remember { mutableStateOf(false) }
    Text("One small step for next week", style = MaterialTheme.typography.headlineSmall)
    Choice("Class", vm.selectedId, vm.profiles.associate { it.id to "${it.name} · Grade ${it.grade}" }, !vm.busy, vm::selectClass)
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        TextButton(onClick = { vm.moveWeek(-1) }, enabled = !vm.busy && vm.week > monday(LocalDate.now().minusDays(30))) { Text("Earlier") }
        TextButton(onClick = { vm.moveWeek(1) }, enabled = !vm.busy && vm.week < monday(LocalDate.now())) { Text("Later") }
    }
    Text("Week of ${vm.week} · 2–10 minutes · Private", style = MaterialTheme.typography.labelLarge)
    Panel("Your recorded week") {
        Text(report?.observation ?: "Choose a class to begin reflection.")
        if (rows.isEmpty()) Text("No saved lessons for this class and week. You can still choose a goal-based practice.")
        else {
            val longest = rows.maxOf { it.capturedMs }.coerceAtLeast(1)
            rows.forEach { row ->
                Text("${lessonDate(row, ZoneId.systemDefault()).dayOfWeek} · ${row.capturedMs / 60_000} min · ${row.status}", style = MaterialTheme.typography.bodySmall)
                LinearProgressIndicator(progress = { row.capturedMs.toFloat() / longest }, modifier = Modifier.fillMaxWidth())
            }
            Text("Bars show saved recording duration, not teaching quality or the proportion of the lesson observed.", style = MaterialTheme.typography.bodySmall)
        }
        Text(report?.limitation ?: "Automatic teaching observations are not available.")
        TextButton(onClick = { details = !details }) { Text(if (details) "Hide lesson context" else "Review or correct lesson context") }
    }
    if (details) vm.lessons.filter { it.profile.id == vm.selectedId && monday(lessonDate(it, ZoneId.systemDefault())) == vm.week }
        .forEach { LessonPanel(vm, it, recording) }
    val previous = Coaching.cards.firstOrNull { it.id == vm.previousReflection.optString("chosen") }
    if (previous != null) Panel("Last week's practice: ${previous.title}") {
        Text("Your answer is a reflection, not an audio finding.")
        Choice("Did you try it?", vm.reflection.optString("tried"), linkedMapOf("yes" to "I tried it", "notyet" to "Not yet", "notuseful" to "It did not fit my class"), !vm.busy) { vm.updateReflection("tried", it) }
        Text(report?.followUp ?: "What did learners show you?")
    }
    val card = vm.chosen ?: vm.suggested
    Panel(card.title) {
        Text("Suggested from your goal · ${card.scope}", style = MaterialTheme.typography.labelMedium)
        report?.action?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
        card.steps.forEachIndexed { index, step ->
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("${index + 1}", style = MaterialTheme.typography.headlineSmall)
                Text(step, modifier = Modifier.weight(1f))
            }
        }
        Text("Try saying: “${card.example}”")
        Text("Try this in one lesson. Next week, reflect on whether it helped you decide what to teach next.")
        Button(onClick = { vm.updateReflection("chosen", card.id) }, enabled = !vm.busy && vm.chosen?.id != card.id, modifier = Modifier.fillMaxWidth()) {
            Text(if (vm.chosen?.id == card.id) "Chosen for next week" else "Try this next week")
        }
        Choice("Or choose another practice", card.id, vm.profile?.let(Coaching::available).orEmpty().associate { it.id to it.title }, !vm.busy) { vm.updateReflection("chosen", it) }
        if (vm.chosen != null) TextButton(onClick = { vm.updateReflection("chosen", "") }, enabled = !vm.busy) { Text("Remove my choice") }
        Text("Evidence: ${card.source}", style = MaterialTheme.typography.bodySmall)
        Text("Draft ${Coaching.VERSION} · card v${card.version}. ${card.limitations} Local educator and language review pending.", style = MaterialTheme.typography.bodySmall)
    }
    Choice("Was this review useful?", vm.reflection.optString("rating"), linkedMapOf("1" to "1 — Not useful", "2" to "2 — Slightly useful", "3" to "3 — Somewhat useful", "4" to "4 — Useful", "5" to "5 — Very useful"), !vm.busy) { vm.updateReflection("rating", it) }
    Text(if (vm.researchOrigin.isBlank()) "Your rating stays on this phone."
        else "Your rating stays on this phone unless you explicitly submit this review after study enrollment.", style = MaterialTheme.typography.bodySmall)
}

@Composable private fun DeleteAll(vm: PilotViewModel, recording: Boolean) {
    var confirming by remember { mutableStateOf(false) }
    OutlinedButton(onClick = { confirming = true }, enabled = !recording && !vm.busy) { Text("Delete all recordings and reflections") }
    if (confirming) AlertDialog(onDismissRequest = { confirming = false }, title = { Text("Delete collected data?") },
        text = { Text("All local audio, lesson notes and weekly reflections will be permanently removed. Class settings remain.") },
        confirmButton = { TextButton(onClick = { confirming = false; vm.clearCollectedData() }) { Text("Delete all") } },
        dismissButton = { TextButton(onClick = { confirming = false }) { Text("Cancel") } })
}

@Composable private fun ResearchSettings(vm: PilotViewModel, recording: Boolean) {
    var code by remember { mutableStateOf("") }
    var agreed by remember { mutableStateOf(false) }
    Text("Approved research server: ${vm.researchOrigin}")
    Text("Optional study enrollment sends grade, language, class-size band, recording and reflection totals, chosen practice, follow-up and usefulness rating under the enrolled study schema. These summaries may still identify a participant in a small study. Audio, class names and private notes stay here. Separate study consent is required.")
    OutlinedTextField(value=code,onValueChange={code=it},label={Text("Private enrollment code")},singleLine=true,enabled=!recording && !vm.busy)
    Row(verticalAlignment=Alignment.CenterVertically) {
        Checkbox(checked=agreed,onCheckedChange={agreed=it},enabled=!recording && !vm.busy)
        Text("I have reviewed and agreed to the separate study consent.")
    }
    Button(onClick={vm.enrollResearch(code);code="";agreed=false},enabled=agreed && code.length in 30..100 && !recording && !vm.busy) {Text("Enroll in research")}
    OutlinedButton(onClick=vm::submitResearchReview,enabled=vm.canSubmitResearch && !recording && !vm.busy) {Text("Submit selected weekly review")}
    if (vm.profile?.cohort == "secondary" && vm.researchStudy != "secondary") Text("Secondary research submissions require a separate study enrollment; local reflection remains available.")
    OutlinedButton(onClick=vm::syncResearch,enabled=!recording && !vm.busy) {Text("Retry research transfer")}
    OutlinedButton(onClick=vm::withdrawResearch,enabled=!recording && !vm.busy) {Text("Stop sharing and withdraw")}
    Text("Withdrawal clears queued summaries immediately. Remote deletion waits for connectivity; all records also expire within 30 days. Deleting or excluding lessons stops enrollment to retract any submitted summary.")
}
