package org.ethioware.echo.pilot

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.delay
import org.ethioware.echo.ui.theme.EchoTheme

private val PilotNavy = Color(0xFF204C5D)
private val PilotBackground = Color(0xFFF7F5F1)
private val PilotMint = Color(0xFFE3F2EF)
private val PilotMuted = Color(0xFF78909C)
private fun tr(locale: String, en: String, am: String) = if (locale == "am") am else en

@Composable
fun PilotApp(vm: PilotViewModel, openDemo: () -> Unit) {
    val recording = RecordingMonitor.state
    val owner = LocalLifecycleOwner.current
    DisposableEffect(owner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) vm.refresh()
            if (event == Lifecycle.Event.ON_STOP) vm.stopPlayback()
        }
        owner.lifecycle.addObserver(observer)
        onDispose { owner.lifecycle.removeObserver(observer) }
    }
    LaunchedEffect(recording.running) { if (!recording.running) vm.refresh() }
    LaunchedEffect(vm.tab) { if (vm.loaded && vm.tab == 3) vm.recordUsability("review_opened") }
    LaunchedEffect(Unit) { while (true) { delay(60_000); if (!RecordingMonitor.state.running) vm.refresh() } }

    val recordPermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
        if (result[Manifest.permission.RECORD_AUDIO] == true) vm.record() else vm.permissionDenied()
    }
    val checkPermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) vm.checkMic() else vm.permissionDenied()
    }
    val notificationPermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission(), vm::updateReminders)
    var editProfile by rememberSaveable { mutableStateOf(false) }
    var addProfile by rememberSaveable { mutableStateOf(false) }
    var settingsPage by rememberSaveable { mutableStateOf("") }
    val locale = vm.locale

    fun startRecording() {
        val permissions = if (Build.VERSION.SDK_INT >= 33) arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.POST_NOTIFICATIONS)
            else arrayOf(Manifest.permission.RECORD_AUDIO)
        recordPermission.launch(permissions)
    }

    EchoTheme {
        Scaffold(
            containerColor = PilotBackground,
            bottomBar = {
                if (vm.loaded && vm.profile != null && !editProfile) PilotBottomBar(vm.tab, locale) {
                    vm.tab = it; settingsPage = ""
                }
            },
        ) { inset ->
            when {
                !vm.loaded -> PilotSplash(Modifier.padding(inset), locale)
                vm.profile == null || editProfile -> Column(
                    Modifier.fillMaxSize().padding(inset).verticalScroll(rememberScrollState()).padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Text("ECHO", color = PilotNavy, style = MaterialTheme.typography.headlineMedium)
                    ProfileEditor(if (addProfile) null else vm.profile, vm.busy || recording.running, locale,
                        onSave = { vm.saveProfile(it); editProfile = false; addProfile = false },
                        onCancel = if (vm.profile != null) ({ editProfile = false; addProfile = false }) else null)
                }
                else -> Column(Modifier.fillMaxSize().padding(inset)) {
                    vm.error?.let { ErrorBanner(it, locale, vm::refresh) }
                    when (vm.tab) {
                        0 -> HomeScreen(vm, recording, locale, ::startRecording, { vm.tab = 2 }, { vm.tab = 3 }, { vm.tab = 4; settingsPage = "microphone" })
                        1 -> RecordScreen(vm, recording, locale, ::startRecording) { checkPermission.launch(Manifest.permission.RECORD_AUDIO) }
                        2 -> HistoryScreen(vm, recording.running, locale)
                        3 -> ReflectionScreen(vm, recording.running, locale)
                        else -> SettingsScreen(vm, recording.running, locale, settingsPage, { settingsPage = it },
                            onEdit = { editProfile = true }, onAdd = { addProfile = true; editProfile = true },
                            onReminder = {
                                if (it && Build.VERSION.SDK_INT >= 33) notificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
                                else vm.updateReminders(it)
                            }, openDemo = openDemo)
                    }
                }
            }
        }
    }
}

@Composable private fun PilotSplash(modifier: Modifier, locale: String) {
    Box(modifier.fillMaxSize().background(PilotNavy), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("E", color = PilotNavy, style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.background(Color.White, RoundedCornerShape(24.dp)).padding(horizontal = 22.dp, vertical = 12.dp))
            Text("ECHO", color = Color.White, style = MaterialTheme.typography.headlineMedium)
            Text(tr(locale, "Private reflection for teachers", "ለመምህራን የግል ምልከታ"), color = Color.White.copy(alpha = .78f))
        }
    }
}

@Composable private fun PilotBottomBar(selected: Int, locale: String, select: (Int) -> Unit) {
    val tabs = listOf(
        Triple("⌂", "Home", "መነሻ"), Triple("●", "Record", "ቅዳ"), Triple("▤", "History", "ታሪክ"),
        Triple("◇", "Reflection", "ምልከታ"), Triple("⚙", "Settings", "ቅንብሮች"),
    )
    NavigationBar(containerColor = Color.White) {
        tabs.forEachIndexed { index, tab ->
            val label = tr(locale, tab.second, tab.third)
            NavigationBarItem(selected = selected == index, onClick = { select(index) },
                icon = { Text(tab.first, modifier = Modifier.semantics { contentDescription = label }) },
                label = { Text(label, maxLines = 1, style = MaterialTheme.typography.labelSmall) },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = PilotNavy, selectedTextColor = PilotNavy,
                    indicatorColor = PilotMint, unselectedIconColor = PilotMuted, unselectedTextColor = PilotMuted))
        }
    }
}

@Composable private fun HomeScreen(
    vm: PilotViewModel, state: RecordingStatus, locale: String, start: () -> Unit,
    history: () -> Unit, reflection: () -> Unit, microphone: () -> Unit,
) {
    val lessons = vm.lessons.filter { it.profile.id == vm.selectedId }
    val thisWeek = lessons.filter { monday(lessonDate(it, ZoneId.systemDefault())) == monday(LocalDate.now()) }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Surface(color = PilotNavy, shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)) {
            Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 28.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(tr(locale, "Welcome", "እንኳን ደህና መጡ"), color = Color.White.copy(alpha = .8f))
                Text(vm.profile?.name.orEmpty(), color = Color.White, style = MaterialTheme.typography.headlineMedium)
                Text(tr(locale, "Capture today’s lesson for your private reflection.", "የዛሬን ትምህርት ለግል ምልከታዎ ይቅዱ።"), color = Color.White.copy(alpha = .8f))
            }
        }
        Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
            Choice(tr(locale, "Class", "ክፍል"), vm.selectedId, vm.profiles.associate { it.id to "${it.name} · ${tr(locale, "Grade", "ክፍል")} ${it.grade}" }, !state.running && !vm.busy, vm::selectClass)
            PilotCard {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    RoundIcon(if (state.running) "●" else "≋")
                    Column(Modifier.weight(1f)) {
                        Text(if (state.running) tr(locale, "Recording now", "አሁን በመቅዳት ላይ") else tr(locale, "Ready to record", "ለመቅዳት ዝግጁ"), fontWeight = FontWeight.SemiBold)
                        Text(micSummary(vm, locale), color = PilotMuted, style = MaterialTheme.typography.bodySmall)
                    }
                    TextButton(onClick = microphone, enabled = !state.running) { Text(tr(locale, "Mic", "ማይክ")) }
                }
                if (state.running) {
                    Text(formatTimer(state.seconds), style = MaterialTheme.typography.headlineMedium, color = PilotNavy)
                    Button(onClick = vm::stop, modifier = Modifier.fillMaxWidth()) { Text(tr(locale, "Stop and save", "አቁምና አስቀምጥ")) }
                } else Button(onClick = start, enabled = !vm.busy, modifier = Modifier.fillMaxWidth()) { Text(tr(locale, "Start Recording", "መቅዳት ጀምር")) }
            }
            SectionTitle(tr(locale, "This week", "በዚህ ሳምንት"), tr(locale, "View reflection", "ምልከታ ይመልከቱ"), reflection)
            PilotCard {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Stat(thisWeek.size.toString(), tr(locale, "Sessions", "ክፍለ ጊዜ"))
                    Stat("${thisWeek.sumOf { it.capturedMs } / 60_000}", tr(locale, "Minutes", "ደቂቃ"))
                    Stat(if (thisWeek.isEmpty()) "—" else "100%", tr(locale, "Local", "በስልክ"))
                }
                Text(tr(locale, "Counts show saved recordings, not teaching quality.", "ቁጥሮቹ የተቀመጡ ቅጂዎችን ያሳያሉ፤ የማስተማር ጥራትን አይለኩም።"), color = PilotMuted, style = MaterialTheme.typography.bodySmall)
            }
            SectionTitle(tr(locale, "Recent recording", "የቅርብ ጊዜ ቅጂ"))
            if (lessons.isEmpty()) EmptyCard(tr(locale, "No recordings yet", "ገና ምንም ቅጂ የለም"), tr(locale, "Your first saved lesson will appear here.", "የመጀመሪያው የተቀመጠ ትምህርትዎ እዚህ ይታያል።"))
            else LessonSummary(lessons.first(), locale, history)
        }
    }
}

@Composable private fun RecordScreen(vm: PilotViewModel, state: RecordingStatus, locale: String, start: () -> Unit, check: () -> Unit) {
    Page(tr(locale, "Record lesson", "ትምህርት ቅዳ"), tr(locale, "Audio stays encrypted on this phone.", "ድምጹ በዚህ ስልክ ላይ ተመስጥሮ ይቆያል።")) {
        if (state.running) PilotCard {
            RoundIcon("●")
            Text(formatTimer(state.seconds), style = MaterialTheme.typography.headlineMedium, color = PilotNavy)
            Text(localizedStatus(state.message, locale), color = PilotMuted)
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth(), color = PilotNavy)
            Button(onClick = vm::stop, modifier = Modifier.fillMaxWidth()) { Text(tr(locale, "Stop and save", "አቁምና አስቀምጥ")) }
            Text(tr(locale, "Completed chunks save automatically. Pause/resume is omitted because it could hide capture gaps.", "የተጠናቀቁ ክፍሎች በራስ-ሰር ይቀመጣሉ። በቅጂው ውስጥ ክፍተትን እንዳይደብቅ ለአፍታ ማቆም/መቀጠል አይጠቀምም።"), color = PilotMuted, style = MaterialTheme.typography.bodySmall)
        } else {
            MicCard(vm, locale, check)
            state.message.takeIf(String::isNotBlank)?.let { Notice(localizedStatus(it, locale)) }
            PilotCard {
                Text(tr(locale, "Before you start", "ከመጀመርዎ በፊት"), style = MaterialTheme.typography.titleMedium)
                Text(tr(locale, "Confirm recording is authorized, connect the selected microphone, and keep at least 32 MB free.", "መቅዳት የተፈቀደ መሆኑን ያረጋግጡ፣ የመረጡትን ማይክሮፎን ያገናኙና ቢያንስ 32 MB ነጻ ቦታ ያስቀሩ።"))
                Button(onClick = start, enabled = !vm.busy, modifier = Modifier.fillMaxWidth()) { Text(tr(locale, "Start Recording", "መቅዳት ጀምር")) }
            }
        }
    }
}

@Composable private fun MicCard(vm: PilotViewModel, locale: String, check: () -> Unit) {
    PilotCard {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            RoundIcon("◉"); Column { Text(tr(locale, "Microphone", "ማይክሮፎን"), style = MaterialTheme.typography.titleMedium); Text(micSummary(vm, locale), color = PilotMuted) }
        }
        Choice(tr(locale, "Input source", "የድምጽ ምንጭ"), if (vm.externalMic) "external" else "phone", linkedMapOf(
            "external" to tr(locale, "External K9 / USB microphone", "ውጫዊ K9 / USB ማይክሮፎን"),
            "phone" to tr(locale, "Phone microphone — approved testing only", "የስልክ ማይክሮፎን — ለተፈቀደ ሙከራ ብቻ")), !vm.busy) { vm.selectMic(it == "external") }
        Button(onClick = check, enabled = !vm.busy, modifier = Modifier.fillMaxWidth()) { Text(if (vm.micCheck.state == "checking") tr(locale, "Checking…", "በመፈተሽ ላይ…") else tr(locale, "Run a short signal check", "አጭር የድምጽ ፍተሻ አድርግ")) }
        Text(micCheckText(vm.micCheck, locale), color = if (vm.micCheck.state == "ready") Color(0xFF147565) else PilotMuted, style = MaterialTheme.typography.bodySmall)
        Text(tr(locale, "The check briefly reads live input and saves no test clip. A visible receiver does not prove the transmitter is sending audio.", "ፍተሻው የቀጥታ ድምጽን ለአጭር ጊዜ ያነባል፤ የሙከራ ቅጂ አያስቀምጥም። ተቀባዩ መታየቱ ላኪው ድምጽ እየላከ መሆኑን አያረጋግጥም።"), color = PilotMuted, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable private fun HistoryScreen(vm: PilotViewModel, recording: Boolean, locale: String) {
    val rows = vm.lessons.filter { it.profile.id == vm.selectedId }
    Page(tr(locale, "Recording history", "የቅጂ ታሪክ"), tr(locale, "Private recordings on this phone", "በዚህ ስልክ ላይ ያሉ የግል ቅጂዎች")) {
        Choice(tr(locale, "Class", "ክፍል"), vm.selectedId, vm.profiles.associate { it.id to "${it.name} · ${it.grade}" }, !recording && !vm.busy, vm::selectClass)
        if (rows.isEmpty()) EmptyCard(tr(locale, "No recordings yet", "ገና ምንም ቅጂ የለም"), tr(locale, "Start from Home or Record.", "ከመነሻ ወይም ከቅዳ ገጽ ይጀምሩ።"))
        rows.forEach { LessonCard(vm, it, recording, locale) }
    }
}

@Composable private fun LessonCard(vm: PilotViewModel, row: LocalLesson, recording: Boolean, locale: String) {
    var expanded by remember(row.id) { mutableStateOf(false) }
    var deleting by remember(row.id) { mutableStateOf(false) }
    var context by remember(row.id, row.context) { mutableStateOf(row.context) }
    var note by remember(row.id, row.note) { mutableStateOf(row.note) }
    var excluded by remember(row.id, row.excluded) { mutableStateOf(row.excluded) }
    var excerpt by remember(row.id) { mutableIntStateOf(0) }
    val date = Instant.ofEpochMilli(row.createdAt).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("EEE d MMM · HH:mm"))
    PilotCard {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RoundIcon(if (row.status == "saved") "▶" else "!")
            Column(Modifier.weight(1f).padding(start = 12.dp)) { Text(date, fontWeight = FontWeight.SemiBold); Text("${row.capturedMs / 60_000} ${tr(locale, "min", "ደቂቃ")} · ${localizedStatus(row.status, locale)}", color = PilotMuted) }
            TextButton(onClick = { expanded = !expanded }, enabled = !recording) { Text(if (expanded) "⌃" else "⌄") }
        }
        if (row.reason.isNotEmpty()) Notice(localizedStatus(row.reason, locale))
        if (row.excluded) Text(tr(locale, "Left out of weekly reflection", "ከሳምንታዊ ምልከታ ወጥቷል"), color = PilotMuted)
        if (expanded) {
            if (row.chunks > 0) {
                Choice(tr(locale, "Private excerpt", "የግል ቅንጭብ"), excerpt.toString(), (0 until row.chunks).associate { it.toString() to "${tr(locale, "Excerpt", "ቅንጭብ")} ${it + 1}" }, !vm.busy) { excerpt = it.toInt() }
                OutlinedButton(onClick = { if (vm.playing == row.id) vm.stopPlayback() else vm.play(row, excerpt) }, enabled = !vm.busy, modifier = Modifier.fillMaxWidth()) { Text(if (vm.playing == row.id) tr(locale, "Stop playback", "ማጫወት አቁም") else tr(locale, "Play privately", "በግል አጫውት")) }
                Text(tr(locale, "Use headphones. Excerpts may include children’s voices.", "የጆሮ ማዳመጫ ይጠቀሙ። ቅንጭቦች የልጆችን ድምጽ ሊይዙ ይችላሉ።"), color = PilotMuted, style = MaterialTheme.typography.bodySmall)
            }
            Choice(tr(locale, "What was happening?", "ምን እየተካሄደ ነበር?"), context, PilotStore.CONTEXTS.associateWith { localizedContext(it, locale) }, !vm.busy) { context = it }
            OutlinedTextField(note, { note = it.take(500) }, label = { Text(tr(locale, "Private note — no pupil names", "የግል ማስታወሻ — የተማሪ ስም አይጻፉ")) }, modifier = Modifier.fillMaxWidth(), enabled = !vm.busy)
            Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(excluded, { excluded = it }, enabled = !vm.busy); Text(tr(locale, "Leave out of weekly reflection", "ከሳምንታዊ ምልከታ አስቀር")) }
            Button(onClick = { vm.correct(row, context, note, excluded); expanded = false }, enabled = !vm.busy, modifier = Modifier.fillMaxWidth()) { Text(tr(locale, "Save changes", "ለውጦችን አስቀምጥ")) }
            TextButton(onClick = { deleting = true }, enabled = !vm.busy) { Text(tr(locale, "Delete recording", "ቅጂውን ሰርዝ"), color = MaterialTheme.colorScheme.error) }
        }
    }
    if (deleting) AlertDialog(onDismissRequest = { deleting = false }, title = { Text(tr(locale, "Delete this recording?", "ይህ ቅጂ ይሰረዝ?")) },
        text = { Text(tr(locale, "It disappears from Echo now. Encrypted audio is permanently removed from this phone 15 days from today. Delete all data removes it immediately.", "አሁን ከEcho ይጠፋል። የተመሰጠረው ድምጽ ከዛሬ 15 ቀን በኋላ ሙሉ በሙሉ ይወገዳል። «ሁሉንም መረጃ ሰርዝ» ወዲያውኑ ያስወግደዋል።")) },
        confirmButton = { TextButton(onClick = { deleting = false; vm.delete(row) }) { Text(tr(locale, "Delete", "ሰርዝ")) } },
        dismissButton = { TextButton(onClick = { deleting = false }) { Text(tr(locale, "Keep", "አቆይ")) } })
}

@Composable private fun ReflectionScreen(vm: PilotViewModel, recording: Boolean, locale: String) {
    val rows = vm.weekly
    val report = vm.report
    val card = vm.chosen ?: vm.suggested
    val text = localizedCard(card, locale)
    Page(tr(locale, "Weekly reflection", "ሳምንታዊ ምልከታ"), tr(locale, "Your notes stay on this device.", "ማስታወሻዎችዎ በዚህ መሣሪያ ላይ ይቆያሉ።")) {
        Choice(tr(locale, "Class", "ክፍል"), vm.selectedId, vm.profiles.associate { it.id to "${it.name} · ${it.grade}" }, !recording && !vm.busy, vm::selectClass)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { vm.moveWeek(-1) }, enabled = vm.week > monday(LocalDate.now().minusDays(30))) { Text(tr(locale, "← Earlier", "← ቀደም")) }
            Text("${tr(locale, "Week of", "የሳምንቱ መጀመሪያ")} ${vm.week}", style = MaterialTheme.typography.labelLarge)
            TextButton(onClick = { vm.moveWeek(1) }, enabled = vm.week < monday(LocalDate.now())) { Text(tr(locale, "Later →", "ቀጣይ →")) }
        }
        PilotCard {
            Text(tr(locale, "Your recorded week", "የተቀዳው ሳምንትዎ"), style = MaterialTheme.typography.titleMedium)
            Text(report?.observation.orEmpty())
            if (rows.isNotEmpty()) {
                val longest = rows.maxOf { it.capturedMs }.coerceAtLeast(1)
                rows.forEach { row ->
                    Text("${lessonDate(row, ZoneId.systemDefault()).dayOfWeek} · ${row.capturedMs / 60_000} ${tr(locale, "min", "ደቂቃ")}", style = MaterialTheme.typography.bodySmall)
                    LinearProgressIndicator(progress = { row.capturedMs.toFloat() / longest }, modifier = Modifier.fillMaxWidth(), color = PilotNavy)
                }
            }
            Text(report?.limitation.orEmpty(), color = PilotMuted, style = MaterialTheme.typography.bodySmall)
        }
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
            Text(tr(locale, "It matches your selected goal, grade and subject. Sound patterns did not select it.", "ከመረጡት ግብ፣ ክፍልና ትምህርት ጋር ይዛመዳል። የድምጽ ቅርጽ አልመረጠውም።"), color = PilotMuted)
            text.steps.forEachIndexed { index, step -> Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) { RoundIcon("${index + 1}"); Text(step, Modifier.weight(1f)) } }
            Text("“${text.example}”", color = PilotNavy, fontWeight = FontWeight.SemiBold)
            Text(tr(locale, "Does not fit when:", "የማይስማማበት ጊዜ፦"), fontWeight = FontWeight.SemiBold); Text(text.notFitWhen, color = PilotMuted)
            Button(onClick = { vm.updateReflection("chosen", card.id) }, enabled = !vm.busy && vm.chosen?.id != card.id, modifier = Modifier.fillMaxWidth()) { Text(if (vm.chosen?.id == card.id) tr(locale, "Chosen for next week", "ለሚቀጥለው ሳምንት ተመርጧል") else tr(locale, "Try this next week", "በሚቀጥለው ሳምንት ሞክር")) }
            Choice(tr(locale, "Choose another practice", "ሌላ ልምምድ ይምረጡ"), card.id, vm.profile?.let(Coaching::available).orEmpty().associate { it.id to localizedCard(it, locale).title }, !vm.busy) { vm.updateReflection("chosen", it) }
            Text("${tr(locale, "Draft content pack", "ረቂቅ የይዘት ጥቅል")} · ${Coaching.VERSION} · v${card.version}", color = PilotMuted, style = MaterialTheme.typography.bodySmall)
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

@Composable private fun SettingsScreen(
    vm: PilotViewModel, recording: Boolean, locale: String, page: String, setPage: (String) -> Unit,
    onEdit: () -> Unit, onAdd: () -> Unit, onReminder: (Boolean) -> Unit, openDemo: () -> Unit,
) {
    val context = LocalContext.current
    var dialError by remember { mutableStateOf(false) }
    if (page.isNotEmpty()) {
        Page(settingsTitle(page, locale), "", back = { setPage("") }) {
            when (page) {
                "profile" -> PilotCard { Text(vm.profile?.name.orEmpty(), style = MaterialTheme.typography.titleLarge); Text("${tr(locale, "Grade", "ክፍል")} ${vm.profile?.grade} · ${vm.profile?.subject}", color = PilotMuted); Button(onClick = onEdit) { Text(tr(locale, "Edit class details", "የክፍል ዝርዝር አርትዕ")) }; OutlinedButton(onClick = onAdd) { Text(tr(locale, "Add another class", "ሌላ ክፍል ጨምር")) } }
                "language" -> PilotCard { Choice(tr(locale, "App language", "የመተግበሪያ ቋንቋ"), locale, linkedMapOf("en" to "English", "am" to "አማርኛ"), !vm.busy, vm::selectLanguage); Text(tr(locale, "Classroom language is stored separately in each class profile.", "የክፍል ቋንቋ በእያንዳንዱ የክፍል መገለጫ ውስጥ ተለይቶ ይቀመጣል።"), color = PilotMuted) }
                "microphone" -> MicCard(vm, locale, vm::checkMic)
                "notifications" -> NotificationSettings(vm, locale, onReminder)
                "privacy" -> { PrivacyPanel(vm, locale); if (vm.researchOrigin.isNotBlank()) ResearchSettings(vm, recording, locale); DeleteAll(vm, recording, locale) }
                "support" -> {
                    PilotCard { Text(tr(locale, "Recording guide", "የመቅዳት መመሪያ"), style = MaterialTheme.typography.titleMedium); Text(tr(locale, "Power and connect both K9 units, then run the signal check. Echo never switches to the phone microphone automatically.", "ሁለቱንም K9 ክፍሎች ያብሩና ያገናኙ፤ ከዚያ የድምጽ ፍተሻውን ያድርጉ። Echo ወደ ስልክ ማይክሮፎን በራስ-ሰር አይቀየርም።")) }
                    PilotCard { Text(tr(locale, "Call support", "ወደ ድጋፍ ይደውሉ"), style = MaterialTheme.typography.titleMedium); Text("0988978263", color = PilotNavy, style = MaterialTheme.typography.titleLarge); Button(onClick = { dialError = runCatching { context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:0988978263"))) }.isFailure }, modifier = Modifier.fillMaxWidth()) { Text(tr(locale, "Open dialer", "መደወያውን ክፈት")) }; if (dialError) Text(tr(locale, "No calling app is available. This is not an internet error; dial from another phone.", "የመደወያ መተግበሪያ የለም። ይህ የኢንተርኔት ችግኝ አይደለም፤ ከሌላ ስልክ ይደውሉ።"), color = MaterialTheme.colorScheme.error) }
                }
                "about" -> PilotCard { Text("ECHO", style = MaterialTheme.typography.headlineMedium, color = PilotNavy); Text(tr(locale, "Echo helps teachers record lessons for private reflection. Your recordings and notes stay on your phone. Each week, Echo offers a practice card to help you choose one action for your next lessons.", "Echo መምህራን ትምህርቶችን ለግል ምልከታ እንዲቀዱ ይረዳል። ቅጂዎችዎና ማስታወሻዎችዎ በስልክዎ ላይ ይቆያሉ። በየሳምንቱ Echo ለቀጣይ ትምህርቶችዎ አንድ ድርጊት እንዲመርጡ የሚረዳ የልምምድ ካርድ ያቀርባል።")); Text(tr(locale, "Adult rehearsal build · educator and language approval required before field release.", "የአዋቂዎች ልምምድ ስሪት · ከመስክ ልቀት በፊት የአስተማሪና የቋንቋ ፈቃድ ያስፈልጋል።"), color = PilotMuted); OutlinedButton(onClick = openDemo) { Text(tr(locale, "Open synthetic demo", "ሰው ሰራሽ ማሳያን ክፈት")) } }
            }
        }
    } else Page(tr(locale, "Settings", "ቅንብሮች"), "") {
        SettingRow("♙", tr(locale, "Class profile", "የክፍል መገለጫ"), vm.profile?.name.orEmpty()) { setPage("profile") }
        SettingRow("◎", tr(locale, "Language", "ቋንቋ"), if (locale == "am") "አማርኛ" else "English") { setPage("language") }
        SettingRow("◉", tr(locale, "Microphone", "ማይክሮፎን"), micSummary(vm, locale)) { setPage("microphone") }
        SettingRow("♢", tr(locale, "Notifications", "ማሳወቂያዎች"), if (vm.reminders) reminderLabel(vm, locale) else tr(locale, "Off", "ጠፍቷል")) { setPage("notifications") }
        SettingRow("▣", tr(locale, "Privacy & data", "ግላዊነትና መረጃ"), tr(locale, "Retention and research choices", "የማቆያና የጥናት ምርጫዎች")) { setPage("privacy") }
        SettingRow("?", tr(locale, "Help & support", "እገዛና ድጋፍ"), "0988978263") { vm.recordUsability("support_opened"); setPage("support") }
        SettingRow("i", tr(locale, "About ECHO", "ስለ ECHO"), tr(locale, "Pilot MVP", "የሙከራ MVP")) { setPage("about") }
    }
}

@Composable private fun NotificationSettings(vm: PilotViewModel, locale: String, onReminder: (Boolean) -> Unit) {
    PilotCard {
        Row(verticalAlignment = Alignment.CenterVertically) { Switch(vm.reminders, onCheckedChange = onReminder); Spacer(Modifier.width(12.dp)); Text(tr(locale, "Weekend reflection reminder", "የሳምንት መጨረሻ ምልከታ ማሳሰቢያ"), Modifier.weight(1f)) }
        Choice(tr(locale, "Day", "ቀን"), vm.reminderDay, linkedMapOf("SATURDAY" to tr(locale, "Saturday", "ቅዳሜ"), "SUNDAY" to tr(locale, "Sunday", "እሁድ")), vm.reminders && !vm.busy) { vm.updateReminderTime(it, vm.reminderMinute) }
        val times = (17..21).associate { (it * 60).toString() to String.format("%02d:00", it) }
        Choice(tr(locale, "Time", "ሰዓት"), vm.reminderMinute.toString(), times, vm.reminders && !vm.busy) { vm.updateReminderTime(vm.reminderDay, it.toInt()) }
        Text(tr(locale, "Suggested: Sunday at 18:00. Android may deliver later; reflection remains available on demand.", "የተጠቆመው፦ እሁድ 18:00። Android ማሳወቂያውን ዘግይቶ ሊያቀርብ ይችላል፤ ምልከታው ሁልጊዜ ይገኛል።"), color = PilotMuted, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable private fun PrivacyPanel(vm: PilotViewModel, locale: String) = PilotCard {
    Text(tr(locale, "Private by default", "በነባሪ የግል"), style = MaterialTheme.typography.titleMedium)
    Text(tr(locale, "Recordings, notes and ratings are encrypted locally. Active recordings expire after 30 days. Ordinary deletion hides a recording now and removes encrypted audio after 15 days; Delete all removes it immediately.", "ቅጂዎች፣ ማስታወሻዎችና ደረጃዎች በስልኩ ላይ ተመስጥረው ይቀመጣሉ። ንቁ ቅጂዎች ከ30 ቀን በኋላ ያበቃሉ። መደበኛ ስረዛ ቅጂውን አሁን ይደብቃልና ድምጹን ከ15 ቀን በኋላ ያስወግዳል፤ «ሁሉንም ሰርዝ» ወዲያውኑ ያስወግዳል።"))
    Text(localizedResearchStatus(vm.researchStatus, locale), color = PilotMuted)
}

@Composable private fun DeleteAll(vm: PilotViewModel, recording: Boolean, locale: String) {
    var confirm by remember { mutableStateOf(false) }
    PilotCard { Text(tr(locale, "Delete all local data", "ሁሉንም የአካባቢ መረጃ ሰርዝ"), style = MaterialTheme.typography.titleMedium); Text(tr(locale, "Immediately removes active and shadow-deleted audio, notes and reflections. Class settings remain.", "ንቁና በ15 ቀን መስኮት ውስጥ ያሉ ድምጾችን፣ ማስታወሻዎችንና ምልከታዎችን ወዲያውኑ ያስወግዳል። የክፍል ቅንብሮች ይቆያሉ።"), color = PilotMuted); OutlinedButton(onClick = { confirm = true }, enabled = !recording && !vm.busy) { Text(tr(locale, "Delete all recordings and reflections", "ሁሉንም ቅጂዎችና ምልከታዎች ሰርዝ")) } }
    if (confirm) AlertDialog(onDismissRequest = { confirm = false }, title = { Text(tr(locale, "Delete collected data?", "የተሰበሰበ መረጃ ይሰረዝ?")) }, text = { Text(tr(locale, "This permanent local deletion cannot be undone.", "ይህ ቋሚ ስረዛ መመለስ አይችልም።")) }, confirmButton = { TextButton(onClick = { confirm = false; vm.clearCollectedData() }) { Text(tr(locale, "Delete all", "ሁሉንም ሰርዝ")) } }, dismissButton = { TextButton(onClick = { confirm = false }) { Text(tr(locale, "Cancel", "ይቅር")) } })
}

@Composable private fun ResearchSettings(vm: PilotViewModel, recording: Boolean, locale: String) {
    var code by remember { mutableStateOf("") }; var agreed by remember { mutableStateOf(false) }
    PilotCard {
        Text(tr(locale, "Optional research contribution", "አማራጭ የጥናት አስተዋጽኦ"), style = MaterialTheme.typography.titleMedium)
        Text(tr(locale, "Only explicitly submitted numeric/enumerated weekly summaries are sent after separate consent. Audio, class names and private notes stay here. Withdrawal applies to the whole enrollment.", "ከተለየ ፈቃድ በኋላ በግልጽ ያስገቡት የቁጥር/የተዘረዘረ ማጠቃለያ ብቻ ይላካል። ድምጽ፣ የክፍል ስምና ማስታወሻ እዚህ ይቆያሉ። ፈቃድ ማንሳት ሙሉውን ምዝገባ ይመለከታል።"), color = PilotMuted)
        OutlinedTextField(code, { code = it }, label = { Text(tr(locale, "Private enrollment code", "የግል ምዝገባ ኮድ")) }, enabled = !recording && !vm.busy, modifier = Modifier.fillMaxWidth())
        Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(agreed, { agreed = it }); Text(tr(locale, "I agreed to the separate study consent.", "ለተለየው የጥናት ፈቃድ ተስማምቻለሁ።")) }
        Button(onClick = { vm.enrollResearch(code); code = ""; agreed = false }, enabled = agreed && code.length in 30..100 && !recording && !vm.busy) { Text(tr(locale, "Enroll", "ተመዝገብ")) }
        OutlinedButton(onClick = vm::submitResearchReview, enabled = vm.canSubmitResearch && !recording && !vm.busy) { Text(tr(locale, "Submit selected weekly review", "የተመረጠውን ምልከታ አስገባ")) }
        OutlinedButton(onClick = vm::withdrawResearch, enabled = !recording && !vm.busy) { Text(tr(locale, "Stop sharing and withdraw", "ማጋራትን አቁምና ፈቃድ አንሳ")) }
    }
}

@Composable private fun ProfileEditor(original: ClassProfile?, disabled: Boolean, locale: String, onSave: (ClassProfile) -> Unit, onCancel: (() -> Unit)?) {
    var value by remember(original?.id) { mutableStateOf(original ?: ClassProfile()) }; var size by remember(original?.id) { mutableStateOf(value.size.toString()) }
    PilotCard {
        Text(if (original == null) tr(locale, "Set up your class", "ክፍልዎን ያዘጋጁ") else tr(locale, "Class details", "የክፍል ዝርዝር"), style = MaterialTheme.typography.titleLarge)
        Text(tr(locale, "Keep this local and do not enter pupil names.", "ይህ በስልኩ ላይ ይቆይ፤ የተማሪ ስም አያስገቡ።"), color = PilotMuted)
        OutlinedTextField(value.name, { value = value.copy(name = it.take(60)) }, label = { Text(tr(locale, "Class name", "የክፍል ስም")) }, enabled = !disabled, modifier = Modifier.fillMaxWidth())
        Choice(tr(locale, "Grade", "ክፍል"), value.grade.toString(), ((1..4).toList() + listOf(11, 12)).associate { it.toString() to "${tr(locale, "Grade", "ክፍል")} $it" }, !disabled) { val changed = value.copy(grade = it.toInt()); value = changed.copy(goal = if (Coaching.available(changed).any { card -> card.id == changed.goal }) changed.goal else "understanding") }
        Choice(tr(locale, "Subject", "ትምህርት"), value.subject, linkedMapOf("Reading" to tr(locale, "Reading", "ንባብ"), "Mathematics" to tr(locale, "Mathematics", "ሂሳብ"), "Other" to tr(locale, "Other", "ሌላ")), !disabled) { value = value.copy(subject = it) }
        Choice(tr(locale, "Classroom language", "የክፍል ቋንቋ"), value.language, LANGUAGES, !disabled) { value = value.copy(language = it) }
        OutlinedTextField(size, { size = it.filter(Char::isDigit).take(3) }, label = { Text(tr(locale, "Approximate learners", "ግምታዊ የተማሪ ብዛት")) }, enabled = !disabled, modifier = Modifier.fillMaxWidth())
        Choice(tr(locale, "Teaching goal", "የማስተማር ግብ"), value.goal, Coaching.available(value).associate { it.id to localizedCard(it, locale).title }, !disabled) { value = value.copy(goal = it) }
        val candidate = value.copy(size = size.toIntOrNull() ?: 0, goal = if (Coaching.available(value).any { it.id == value.goal }) value.goal else "understanding")
        Button(onClick = { onSave(candidate) }, enabled = !disabled && candidate.valid(), modifier = Modifier.fillMaxWidth()) { Text(tr(locale, "Save class", "ክፍል አስቀምጥ")) }; onCancel?.let { OutlinedButton(onClick = it) { Text(tr(locale, "Cancel", "ይቅር")) } }
    }
}

@Composable private fun Page(title: String, subtitle: String, back: (() -> Unit)? = null, content: @Composable ColumnScope.() -> Unit) = Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 24.dp, vertical = 20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) { Row(verticalAlignment = Alignment.CenterVertically) { if (back != null) TextButton(onClick = back) { Text("←", style = MaterialTheme.typography.titleLarge) }; Column { Text(title, style = MaterialTheme.typography.headlineMedium, color = PilotNavy); if (subtitle.isNotEmpty()) Text(subtitle, color = PilotMuted) } }; content(); Spacer(Modifier.height(12.dp)) }
@Composable private fun PilotCard(content: @Composable ColumnScope.() -> Unit) = Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(22.dp), elevation = CardDefaults.cardElevation(2.dp)) { Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp), content = content) }
@Composable private fun RoundIcon(text: String) = Box(Modifier.size(42.dp).background(PilotMint, RoundedCornerShape(21.dp)), contentAlignment = Alignment.Center) { Text(text, color = PilotNavy, fontWeight = FontWeight.Bold) }
@Composable private fun EmptyCard(title: String, body: String) = PilotCard { RoundIcon("○"); Text(title, style = MaterialTheme.typography.titleMedium); Text(body, color = PilotMuted) }
@Composable private fun Stat(value: String, label: String) = Column(horizontalAlignment = Alignment.CenterHorizontally) { Text(value, style = MaterialTheme.typography.titleLarge, color = PilotNavy); Text(label, color = PilotMuted, style = MaterialTheme.typography.bodySmall) }
@Composable private fun SectionTitle(title: String, action: String? = null, click: (() -> Unit)? = null) = Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { Text(title, style = MaterialTheme.typography.titleMedium, color = PilotNavy, modifier = Modifier.weight(1f)); if (action != null && click != null) TextButton(onClick = click) { Text(action) } }
@Composable private fun LessonSummary(row: LocalLesson, locale: String, click: () -> Unit) = PilotCard { Row(Modifier.fillMaxWidth().clickable(onClick = click), verticalAlignment = Alignment.CenterVertically) { RoundIcon("▶"); Column(Modifier.weight(1f).padding(start = 12.dp)) { Text(Instant.ofEpochMilli(row.createdAt).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("EEE d MMM")), fontWeight = FontWeight.SemiBold); Text("${row.capturedMs / 60_000} ${tr(locale, "min", "ደቂቃ")}", color = PilotMuted) }; Text("›", style = MaterialTheme.typography.titleLarge) } }
@Composable private fun SettingRow(icon: String, title: String, subtitle: String, click: () -> Unit) { Row(Modifier.fillMaxWidth().clickable(onClick = click).padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) { RoundIcon(icon); Column(Modifier.weight(1f).padding(horizontal = 14.dp)) { Text(title, fontWeight = FontWeight.SemiBold); Text(subtitle, color = PilotMuted, style = MaterialTheme.typography.bodySmall) }; Text("›", style = MaterialTheme.typography.titleLarge, color = PilotNavy) }; HorizontalDivider() }
@Composable private fun Notice(text: String) = Surface(color = PilotMint, shape = RoundedCornerShape(14.dp)) { Text(text, Modifier.padding(14.dp), style = MaterialTheme.typography.bodySmall) }
@Composable private fun ErrorBanner(text: String, locale: String, retry: () -> Unit) = Surface(color = MaterialTheme.colorScheme.errorContainer) { Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) { Text(localizedStatus(text, locale), Modifier.weight(1f)); TextButton(onClick = retry) { Text(tr(locale, "Retry", "እንደገና ሞክር")) } } }
@Composable private fun Choice(label: String, value: String, options: Map<String, String>, enabled: Boolean = true, change: (String) -> Unit) { var expanded by remember { mutableStateOf(false) }; Column(verticalArrangement = Arrangement.spacedBy(4.dp)) { Text(label, style = MaterialTheme.typography.labelLarge, color = PilotNavy); Box { OutlinedButton(onClick = { expanded = true }, enabled = enabled, modifier = Modifier.fillMaxWidth()) { Text(options[value] ?: "—", modifier = Modifier.weight(1f)); Text("⌄") }; DropdownMenu(expanded, { expanded = false }) { options.forEach { (key, text) -> DropdownMenuItem({ Text(text) }, { change(key); expanded = false }) } } } } }

private fun formatTimer(seconds: Long) = "%02d:%02d".format(seconds / 60, seconds % 60)
private fun micSummary(vm: PilotViewModel, locale: String) = if (vm.externalMic) { if (vm.externalConnected) tr(locale, "External K9/USB detected · run signal check", "ውጫዊ K9/USB ተገኝቷል · የድምጽ ፍተሻ ያድርጉ") else tr(locale, "External K9/USB not detected", "ውጫዊ K9/USB አልተገኘም") } else tr(locale, "Phone microphone selected deliberately", "የስልክ ማይክሮፎን በፈቃድ ተመርጧል")
private fun micCheckText(result: MicCheckResult, locale: String) = when (result.state) { "idle" -> tr(locale, "No signal check run yet.", "የድምጽ ፍተሻ ገና አልተደረገም።"); "checking" -> tr(locale, "Listening briefly… no clip is saved.", "ለአጭር ጊዜ በማዳመጥ ላይ… ምንም ቅጂ አይቀመጥም።"); "ready" -> tr(locale, "Signal detected on the selected routed microphone.", "በተመረጠው ማይክሮፎን ላይ ድምጽ ተገኝቷል።"); "no_signal", "no_input" -> tr(locale, "Route opened but no usable signal was detected. Check K9 power/mute and retry.", "መንገዱ ተከፍቷል፣ ግን ጠቃሚ ድምጽ አልተገኘም። የK9 ኃይል/ዝምታ ይፈትሹ።"); "disconnected" -> tr(locale, "Selected microphone is not connected. Echo will not fall back automatically.", "የተመረጠው ማይክሮፎን አልተገናኘም። Echo በራስ-ሰር አይቀየርም።"); "permission" -> tr(locale, "Microphone permission is required.", "የማይክሮፎን ፈቃድ ያስፈልጋል።"); else -> tr(locale, "Check failed. Reconnect and retry.", "ፍተሻው አልተሳካም። እንደገና ያገናኙና ይሞክሩ።") }
private fun localizedStatus(value: String, locale: String): String { if (locale != "am") return value.replaceFirstChar { it.uppercase() }; return when { value == "saved" -> "ተቀምጧል"; value == "interrupted" -> "ተቋርጧል"; value.contains("Lesson saved") -> "ትምህርቱ በዚህ ስልክ ላይ ተቀምጧል።"; value.contains("route changed") -> "የማይክሮፎን መንገድ ተቀይሯል። እንደገና ያገናኙት።"; value.contains("permission", true) -> "የማይክሮፎን ፈቃድ ያስፈልጋል።"; value.contains("storage", true) -> "የማከማቻ ቦታ በቂ አይደለም።"; value.contains("completed chunks") -> "የተጠናቀቁ የድምጽ ክፍሎች ብቻ ተመልሰዋል።"; else -> value } }
private fun localizedContext(value: String, locale: String) = if (locale != "am") value else when (value) { "Not specified" -> "አልተገለጸም"; "Teacher modeling" -> "የመምህር ማሳያ"; "Whole-class practice" -> "የሙሉ ክፍል ልምምድ"; "Partner or group work" -> "የጥንድ ወይም የቡድን ሥራ"; "Independent practice" -> "የግል ልምምድ"; else -> "ሌላ አዋቂ ወይም የተቀዳ ድምጽ" }
private fun localizedResearchStatus(value: String, locale: String): String { if (locale != "am") return value; return when { value.startsWith("Research sharing is off") -> "የጥናት ማጋራት ጠፍቷል።"; value.startsWith("Sharing is off") -> "ማጋራት ጠፍቷል። የርቀት ፈቃድ ማንሳት ግንኙነትን እየጠበቀ ነው።"; value.startsWith("Research active") -> "ጥናቱ ንቁ ነው። በግልጽ የገቡ ምልከታዎች ብቻ ይጋራሉ።"; else -> value } }
private fun settingsTitle(page: String, locale: String) = when (page) { "profile" -> tr(locale, "Class profile", "የክፍል መገለጫ"); "language" -> tr(locale, "Language", "ቋንቋ"); "microphone" -> tr(locale, "Microphone", "ማይክሮፎን"); "notifications" -> tr(locale, "Notifications", "ማሳወቂያዎች"); "privacy" -> tr(locale, "Privacy & data", "ግላዊነትና መረጃ"); "support" -> tr(locale, "Help & support", "እገዛና ድጋፍ"); else -> tr(locale, "About ECHO", "ስለ ECHO") }
private fun reminderLabel(vm: PilotViewModel, locale: String) = "${if (vm.reminderDay == "SUNDAY") tr(locale, "Sunday", "እሁድ") else tr(locale, "Saturday", "ቅዳሜ")} · %02d:%02d".format(vm.reminderMinute / 60, vm.reminderMinute % 60)
