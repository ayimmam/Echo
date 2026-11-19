package org.ethioware.echo.pilot

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.net.toUri
import androidx.core.view.WindowCompat
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import java.util.Locale
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import org.ethioware.echo.ui.theme.EchoTypography

internal val PilotNavy = Color(0xFF1C4052)
private val PilotBackground = Color(0xFFF5F3EF)
private val PilotPaper = Color(0xFFFCFBF9)
internal val PilotMint = Color(0xFFE0EEEB)
internal val PilotMuted = Color(0xFF54707A)
private val PilotColors = lightColorScheme(
    primary = PilotNavy, onPrimary = Color.White,
    secondary = PilotAccent, secondaryContainer = PilotMint, onSecondaryContainer = PilotNavy,
    background = PilotBackground, onBackground = PilotNavy,
    surface = PilotPaper, onSurface = PilotNavy, surfaceVariant = PilotMint, onSurfaceVariant = PilotMuted,
    outline = Color(0xFF80979D), outlineVariant = Color(0xFFDADFDA),
)
internal fun tr(locale: String, en: String, am: String) = if (locale == "am") am else en

@Composable
fun PilotApp(vm: PilotViewModel, openDemo: () -> Unit) {
    val recording = RecordingMonitor.state
    val owner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val snackbar = remember { SnackbarHostState() }
    var micAction by rememberSaveable { mutableStateOf("") }
    DisposableEffect(owner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME && vm.loaded && micAction.isEmpty() && !RecordingMonitor.state.running) vm.refresh()
            if (event == Lifecycle.Event.ON_STOP) vm.stopPlayback()
        }
        owner.lifecycle.addObserver(observer)
        onDispose { owner.lifecycle.removeObserver(observer) }
    }
    LaunchedEffect(vm) {
        snapshotFlow { RecordingMonitor.state.takeUnless { it.running } }.drop(1).collect { completed ->
            if (completed?.lessonId != null) vm.refresh()
        }
    }
    LaunchedEffect(recording.takeUnless { it.running }) {
        if (!recording.running && !recording.failed && recording.lessonId != null && vm.tab == 0) {
            snackbar.showSnackbar(tr(vm.locale, "Recording saved on this phone", "ቅጂው በዚህ ስልክ ላይ ተቀምጧል"))
        }
    }
    LaunchedEffect(vm.loaded, vm.tab) { if (vm.loaded && vm.tab == 3) vm.recordUsability("review_opened") }
    LaunchedEffect(Unit) {
        while (true) {
            delay(60_000)
            if (!vm.busy && micAction.isEmpty() && !RecordingMonitor.state.running &&
                owner.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) vm.refresh()
        }
    }

    val locale = vm.locale
    var permissionIntro by rememberSaveable { mutableStateOf(false) }
    var permissionDenied by rememberSaveable { mutableStateOf(false) }
    fun runMicAction(action: String) {
        if (action == "record") vm.record()
        if (action == "check") vm.checkMic()
    }
    val microphonePermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) runMicAction(micAction) else permissionDenied = true
        micAction = ""
    }
    fun requestMicrophone(action: String) {
        if (context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            runMicAction(action)
        } else {
            micAction = action
            permissionIntro = true
        }
    }
    val start = { requestMicrophone("record") }
    val check = { requestMicrophone("check") }
    val notificationPermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission(), vm::updateReminders)

    var setup by rememberSaveable { mutableStateOf(false) }
    var editProfile by rememberSaveable { mutableStateOf(false) }
    var addProfile by rememberSaveable { mutableStateOf(false) }
    var settingsPage by rememberSaveable { mutableStateOf("") }
    var lessonId by rememberSaveable { mutableStateOf<String?>(null) }
    val firstClass = vm.profile == null
    val welcome = vm.loaded && firstClass && !setup
    val fullScreen = !vm.loaded || welcome || recording.running
    val home = vm.loaded && !firstClass && !editProfile && vm.tab == 0
    val selectedLesson = vm.lessons.firstOrNull { it.id == lessonId && it.profile.id == vm.selectedId }
    val hasDetail = vm.tab == 2 && selectedLesson != null
    fun closeDetails() {
        vm.stopPlayback()
        lessonId = null
    }
    fun selectTab(tab: Int) {
        closeDetails()
        settingsPage = ""
        vm.tab = tab
    }
    fun goBack() {
        when {
            editProfile -> { editProfile = false; addProfile = false }
            setup && firstClass -> setup = false
            hasDetail -> closeDetails()
            settingsPage.isNotEmpty() -> settingsPage = ""
            else -> selectTab(0)
        }
    }
    BackHandler(enabled = !recording.running && (editProfile || setup && firstClass || vm.tab != 0), onBack = ::goBack)
    val darkStatus = fullScreen || home && vm.error == null
    SideEffect {
        (context as? Activity)?.window?.let { window ->
            WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !darkStatus
        }
    }

    MaterialTheme(colorScheme = PilotColors, typography = EchoTypography) {
        Scaffold(
            containerColor = if (fullScreen) PilotNavy else PilotBackground,
            contentWindowInsets = WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
            snackbarHost = { SnackbarHost(snackbar) },
            bottomBar = {
                if (!fullScreen && !firstClass && !editProfile && vm.tab in 0..3 && !hasDetail) {
                    PilotBottomBar(vm.tab, locale, ::selectTab)
                }
            },
        ) { inset ->
            val modifier = Modifier.fillMaxSize().padding(inset)
            when {
                !vm.loaded -> Column(modifier) {
                    if (vm.error != null) Box(Modifier.statusBarsPadding()) { ErrorBanner(vm.error.orEmpty(), locale, vm::refresh) }
                    PilotSplash(Modifier.weight(1f), locale)
                }
                recording.running -> Box(modifier) { ActiveRecording(recording, vm.externalMic, locale, vm::stop) }
                welcome -> Box(modifier) {
                    TeacherWelcome(locale, !vm.busy, { vm.selectLanguage(if (locale == "am") "en" else "am") }) { setup = true }
                }
                firstClass || editProfile -> Column(modifier.statusBarsPadding()) {
                    vm.error?.let { ErrorBanner(it, locale, vm::refresh) }
                    Page(tr(locale, "Class details", "የክፍል ዝርዝር"), "", locale, back = ::goBack) {
                        ProfileEditor(if (addProfile) null else vm.profile, vm.busy, locale, firstClass,
                            onSave = { vm.saveProfile(it); editProfile = false; addProfile = false })
                    }
                }
                else -> Column(if (home && vm.error == null) modifier else modifier.statusBarsPadding()) {
                    vm.error?.let { ErrorBanner(it, locale, vm::refresh) }
                    when (vm.tab) {
                        0 -> HomeScreen(vm, recording, locale, start, check, { selectTab(4) }, { id ->
                            selectTab(2)
                            lessonId = id
                        }, { selectTab(3) })
                        1 -> Page(tr(locale, "Record lesson", "ትምህርት ቅዳ"),
                            vm.profile?.name.orEmpty(), locale) {
                            RecordingOutcome(recording, locale)
                            if (vm.profiles.size > 1) ClassChoice(vm, locale)
                            CaptureCard(vm, recording, locale, start, check)
                            if (!recording.failed && vm.lessons.any { it.id == recording.lessonId && it.profile.id == vm.selectedId }) {
                                TextButton(onClick = { selectTab(2); lessonId = recording.lessonId }) {
                                    Text(tr(locale, "Open saved recording", "የተቀመጠውን ቅጂ ክፈት"))
                                }
                            }
                        }
                        2 -> if (selectedLesson != null) LessonDetail(vm, selectedLesson, locale, ::closeDetails)
                            else HistoryScreen(vm, locale) { lessonId = it }
                        3 -> ReflectionScreen(vm, locale)
                        else -> SettingsScreen(vm, false, locale, settingsPage, { settingsPage = it },
                            onBack = ::goBack, onCheck = check,
                            onEdit = { editProfile = true }, onAdd = { addProfile = true; editProfile = true },
                            onReminder = {
                                if (it && Build.VERSION.SDK_INT >= 33 &&
                                    context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
                                ) notificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
                                else vm.updateReminders(it)
                            }, openDemo = openDemo)
                    }
                }
            }
        }
        if (permissionIntro) AlertDialog(
            onDismissRequest = { permissionIntro = false; micAction = "" },
            icon = { PilotGlyph(PilotIcon.Mic, PilotNavy) },
            title = { Text(tr(locale, "Allow microphone access", "የማይክሮፎን ፈቃድ ይስጡ")) },
            text = { Text(tr(locale, "Echo uses the microphone when you record a lesson or run a mic test. The mic test saves no audio.", "Echo ማይክሮፎኑን ትምህርት ሲቀዱ ወይም ማይኩን ሲፈትሹ ይጠቀማል። የማይክ ፍተሻው ድምጽ አያስቀምጥም።")) },
            confirmButton = { TextButton(onClick = {
                permissionIntro = false
                microphonePermission.launch(Manifest.permission.RECORD_AUDIO)
            }) { Text(tr(locale, "Continue", "ቀጥል")) } },
            dismissButton = { TextButton(onClick = { permissionIntro = false; micAction = "" }) { Text(tr(locale, "Not now", "አሁን አይደለም")) } },
        )
        if (permissionDenied) AlertDialog(
            onDismissRequest = { permissionDenied = false },
            title = { Text(tr(locale, "Microphone access is off", "የማይክሮፎን ፈቃድ ጠፍቷል")) },
            text = { Text(tr(locale, "You can still use History and Reflection. To use the microphone, allow access in Android Settings.", "ታሪክና ምልከታን መጠቀም ይችላሉ። ማይክሮፎኑን ለመጠቀም በAndroid ቅንብሮች ውስጥ ፈቃድ ይስጡ።")) },
            confirmButton = { TextButton(onClick = {
                permissionDenied = false
                context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", context.packageName, null)))
            }) { Text(tr(locale, "Open settings", "ቅንብሮችን ክፈት")) } },
            dismissButton = { TextButton(onClick = { permissionDenied = false }) { Text(tr(locale, "Close", "ዝጋ")) } },
        )
    }
}

@Composable private fun PilotSplash(modifier: Modifier, locale: String) {
    Box(modifier.fillMaxWidth().statusBarsPadding(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            PilotGlyph(PilotIcon.Wave, Color.White, Modifier.size(48.dp))
            Text("ECHO", color = Color.White, style = MaterialTheme.typography.displaySmall)
            Text(tr(locale, "Private reflection for teachers", "ለመምህራን የግል ምልከታ"), color = Color.White.copy(alpha = .85f))
        }
    }
}

@Composable private fun PilotBottomBar(selected: Int, locale: String, select: (Int) -> Unit) {
    val tabs = listOf(
        Triple(PilotIcon.Home, "Home", "መነሻ"), Triple(PilotIcon.Mic, "Record", "ቅዳ"),
        Triple(PilotIcon.Clipboard, "History", "ታሪክ"), Triple(PilotIcon.Book, "Reflection", "ምልከታ"),
    )
    NavigationBar(containerColor = PilotPaper, tonalElevation = 0.dp) {
        tabs.forEachIndexed { index, tab ->
            val label = tr(locale, tab.second, tab.third)
            NavigationBarItem(selected = selected == index, onClick = { select(index) },
                icon = { PilotGlyph(tab.first, LocalContentColor.current) },
                label = { Text(label, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PilotNavy, selectedTextColor = PilotNavy,
                    indicatorColor = PilotMint, unselectedIconColor = PilotMuted, unselectedTextColor = PilotMuted,
                ))
        }
    }
}

@Composable internal fun ClassChoice(vm: PilotViewModel, locale: String) {
    Choice(tr(locale, "Class", "ክፍል"), vm.selectedId,
        vm.profiles.associate { it.id to "${it.name} · ${tr(locale, "Grade", "ክፍል")} ${it.grade}" },
        !vm.busy, vm::selectClass)
}

@Composable private fun SettingsScreen(
    vm: PilotViewModel, recording: Boolean, locale: String, page: String, setPage: (String) -> Unit,
    onBack: () -> Unit, onCheck: () -> Unit, onEdit: () -> Unit, onAdd: () -> Unit,
    onReminder: (Boolean) -> Unit, openDemo: () -> Unit,
) {
    val context = LocalContext.current
    var dialError by remember { mutableStateOf(false) }
    if (page.isNotEmpty()) {
        Page(settingsTitle(page, locale), "", locale, back = { setPage("") }) {
            when (page) {
                "profile" -> PilotCard { Text(vm.profile?.name.orEmpty(), style = MaterialTheme.typography.titleLarge); Text("${tr(locale, "Grade", "ክፍል")} ${vm.profile?.grade} · ${vm.profile?.subject}", color = PilotMuted); Button(onClick = onEdit) { Text(tr(locale, "Edit class details", "የክፍል ዝርዝር አርትዕ")) }; OutlinedButton(onClick = onAdd) { Text(tr(locale, "Add another class", "ሌላ ክፍል ጨምር")) } }
                "language" -> PilotCard { Choice(tr(locale, "App language", "የመተግበሪያ ቋንቋ"), locale, linkedMapOf("en" to "English", "am" to "አማርኛ"), !vm.busy, vm::selectLanguage); Text(tr(locale, "Classroom language is stored separately in each class profile.", "የክፍል ቋንቋ በእያንዳንዱ የክፍል መገለጫ ውስጥ ተለይቶ ይቀመጣል።"), color = PilotMuted) }
                "microphone" -> PilotCard {
                    MicrophoneControls(vm, locale, onCheck)
                    Text(tr(locale, "The mic test saves no audio.", "የማይክ ፍተሻው ድምጽ አያስቀምጥም።"), color = PilotMuted)
                }
                "notifications" -> NotificationSettings(vm, locale, onReminder)
                "privacy" -> { PrivacyPanel(vm, locale); if (vm.researchOrigin.isNotBlank()) ResearchSettings(vm, recording, locale); DeleteAll(vm, recording, locale) }
                "support" -> {
                    PilotCard {
                        SupportAnswer(tr(locale, "How do I record?", "እንዴት እቀዳለሁ?"),
                            tr(locale, "Open Home and choose a microphone. For the K9, turn on both units, connect the receiver and tap Test mic. Tap Start recording, then Stop and save when finished.", "ወደ መነሻ ይሂዱና ማይክሮፎን ይምረጡ። K9 ሲጠቀሙ ሁለቱንም ክፍሎች ያብሩ፣ ተቀባዩን ያገናኙና ማይኩን ይፈትሹ። መቅዳት ይጀምሩ፤ ሲጨርሱ «አቁምና አስቀምጥ» ይንኩ።"))
                        HorizontalDivider()
                        SupportAnswer(tr(locale, "Where are my recordings?", "ቅጂዎቼ የት ናቸው?"),
                            tr(locale, "Find saved lessons in History. Play an excerpt, check the microphone levels, or delete a recording. History and Reflection work without internet.", "የተቀመጡ ትምህርቶችን በታሪክ ያገኛሉ። ቅንጭብ ያዳምጡ፣ የማይኩን ድምጽ ይፈትሹ ወይም ቅጂን ይሰርዙ። ታሪክና ምልከታ ያለ ኢንተርኔት ይሠራሉ።"))
                        HorizontalDivider()
                        SupportAnswer(tr(locale, "What happens if recording is interrupted?", "ቅጂው ከተቋረጠ ምን ይሆናል?"),
                            tr(locale, "Echo keeps audio it already saved. Open the lesson in History to see what was kept, then check your microphone before starting again.", "Echo አስቀድሞ የተቀመጠውን ድምጽ ይይዛል። የተቀመጠውን በታሪክ ይመልከቱ፣ ከዚያ እንደገና ከመጀመርዎ በፊት ማይኩን ይፈትሹ።"))
                    }
                    PilotCard {
                        Text(tr(locale, "Call support", "ወደ ድጋፍ ይደውሉ"), style = MaterialTheme.typography.titleMedium)
                        OutlinedButton(onClick = {
                            dialError = runCatching { context.startActivity(Intent(Intent.ACTION_DIAL, "tel:0988978263".toUri())) }.isFailure
                        }) { Text("0988978263") }
                        if (dialError) Text(tr(locale, "Use another phone to call support.", "ለድጋፍ ከሌላ ስልክ ይደውሉ።"), color = MaterialTheme.colorScheme.error)
                    }
                }
                "about" -> PilotCard { Text("ECHO", style = MaterialTheme.typography.headlineMedium, color = PilotNavy); Text(tr(locale, "Echo helps teachers record lessons for private reflection. Your recordings and notes stay on your phone. Each week, Echo offers a practice card to help you choose one action for your next lessons.", "Echo መምህራን ትምህርቶችን ለግል ምልከታ እንዲቀዱ ይረዳል። ቅጂዎችዎና ማስታወሻዎችዎ በስልክዎ ላይ ይቆያሉ። በየሳምንቱ Echo ለቀጣይ ትምህርቶችዎ አንድ ድርጊት እንዲመርጡ የሚረዳ የልምምድ ካርድ ያቀርባል።")); Text(tr(locale, "Adult rehearsal build · educator and language approval required before field release.", "የአዋቂዎች ልምምድ ስሪት · ከመስክ ልቀት በፊት የአስተማሪና የቋንቋ ፈቃድ ያስፈልጋል።"), color = PilotMuted); OutlinedButton(onClick = openDemo) { Text(tr(locale, "Open synthetic demo", "ሰው ሰራሽ ማሳያን ክፈት")) } }
            }
        }
    } else Page(tr(locale, "Settings", "ቅንብሮች"), "", locale, back = onBack) {
        SettingRow(Icons.Outlined.Person, tr(locale, "Class profile", "የክፍል መገለጫ"), vm.profile?.name.orEmpty()) { setPage("profile") }
        SettingRow(Icons.Outlined.Edit, tr(locale, "Language", "ቋንቋ"), if (locale == "am") "አማርኛ" else "English") { setPage("language") }
        SettingRow(null, tr(locale, "Microphone", "ማይክሮፎን"), if (vm.externalMic) tr(locale, "External microphone", "ውጫዊ ማይክሮፎን") else tr(locale, "Phone microphone", "የስልክ ማይክሮፎን")) { setPage("microphone") }
        SettingRow(Icons.Outlined.Notifications, tr(locale, "Notifications", "ማሳወቂያዎች"), if (vm.reminders) reminderLabel(vm, locale) else tr(locale, "Off", "ጠፍቷል")) { setPage("notifications") }
        SettingRow(Icons.Outlined.Lock, tr(locale, "Privacy & data", "ግላዊነትና መረጃ"), tr(locale, "Retention and research choices", "የማቆያና የጥናት ምርጫዎች")) { setPage("privacy") }
        SettingRow(Icons.Outlined.Call, tr(locale, "Help & support", "እገዛና ድጋፍ"), "0988978263") { vm.recordUsability("support_opened"); setPage("support") }
        SettingRow(Icons.Outlined.Info, tr(locale, "About ECHO", "ስለ ECHO"), tr(locale, "App information", "የመተግበሪያ መረጃ")) { setPage("about") }
    }
}

@Composable private fun SupportAnswer(question: String, answer: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Row(
        Modifier.fillMaxWidth().heightIn(min = 48.dp).clickable(role = Role.Button) { expanded = !expanded },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(question, Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
        Icon(if (expanded) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown, null)
    }
    if (expanded) Text(answer, color = PilotMuted, style = MaterialTheme.typography.bodyMedium)
}

@Composable private fun NotificationSettings(vm: PilotViewModel, locale: String, onReminder: (Boolean) -> Unit) {
    PilotCard {
        Row(verticalAlignment = Alignment.CenterVertically) { Switch(vm.reminders, onCheckedChange = onReminder); Spacer(Modifier.width(12.dp)); Text(tr(locale, "Weekend reflection reminder", "የሳምንት መጨረሻ ምልከታ ማሳሰቢያ"), Modifier.weight(1f)) }
        Choice(tr(locale, "Day", "ቀን"), vm.reminderDay, linkedMapOf("SATURDAY" to tr(locale, "Saturday", "ቅዳሜ"), "SUNDAY" to tr(locale, "Sunday", "እሁድ")), vm.reminders && !vm.busy) { vm.updateReminderTime(it, vm.reminderMinute) }
        val times = (17..21).associate { (it * 60).toString() to String.format(Locale.ROOT, "%02d:00", it) }
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

@Composable private fun ProfileEditor(
    original: ClassProfile?, disabled: Boolean, locale: String, firstClass: Boolean, onSave: (ClassProfile) -> Unit,
) {
    val initial = remember(original?.id) { original ?: ClassProfile(name = tr(locale, "My class", "ክፍሌ")) }
    var name by rememberSaveable(original?.id) { mutableStateOf(initial.name) }
    var grade by rememberSaveable(original?.id) { mutableIntStateOf(initial.grade) }
    var subject by rememberSaveable(original?.id) { mutableStateOf(initial.subject) }
    var language by rememberSaveable(original?.id) { mutableStateOf(initial.language) }
    var goal by rememberSaveable(original?.id) { mutableStateOf(initial.goal) }
    var size by rememberSaveable(original?.id) { mutableStateOf(original?.size?.toString().orEmpty()) }
    val candidate = initial.copy(name = name.trim(), grade = grade, subject = subject, language = language,
        goal = goal, size = size.toIntOrNull() ?: 0)
    val goals = Coaching.available(candidate)
    val resolved = candidate.copy(goal = goal.takeIf { id -> goals.any { it.id == id } } ?: "understanding")
    PilotCard {
        OutlinedTextField(name, { name = it.take(60) }, label = { Text(tr(locale, "Class name", "የክፍል ስም")) },
            supportingText = { Text(tr(locale, "Leave out learner names.", "የተማሪዎችን ስም አያካትቱ።")) },
            enabled = !disabled, singleLine = true, modifier = Modifier.fillMaxWidth())
        Choice(tr(locale, "Grade", "ክፍል"), grade.toString(),
            ((1..4).toList() + listOf(11, 12)).associate { it.toString() to it.toString() }, !disabled) { grade = it.toInt() }
        Choice(tr(locale, "Subject", "ትምህርት"), subject,
            listOf("Reading", "Mathematics", "Other").associateWith { localizedSubject(it, locale) }, !disabled) { subject = it }
        Choice(tr(locale, "Classroom language", "የክፍል ቋንቋ"), language, LANGUAGES, !disabled) { language = it }
        OutlinedTextField(size, { size = it.filter(Char::isDigit).take(3) }, label = { Text(tr(locale, "Approximate class size", "ግምታዊ የክፍል ተማሪዎች ብዛት")) },
            enabled = !disabled, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
        if (!firstClass) Choice(tr(locale, "Teaching goal", "የማስተማር ግብ"), resolved.goal,
            goals.associate { it.id to localizedCard(it, locale).title }, !disabled) { goal = it }
        Button(onClick = { onSave(resolved) }, enabled = !disabled && resolved.valid(),
            modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp), shape = RoundedCornerShape(16.dp)) {
            Text(if (firstClass) tr(locale, "Go to Home", "ወደ መነሻ ሂድ") else tr(locale, "Save class", "ክፍል አስቀምጥ"))
        }
    }
}

@Composable internal fun Page(
    title: String, subtitle: String, locale: String, back: (() -> Unit)? = null, content: @Composable ColumnScope.() -> Unit,
) = Column(
    Modifier.fillMaxSize().verticalScroll(rememberScrollState()).imePadding().padding(horizontal = 24.dp, vertical = 20.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
) {
    PageTitle(title, subtitle, locale, back)
    content()
    Spacer(Modifier.height(12.dp))
}

@Composable internal fun PageTitle(title: String, subtitle: String, locale: String, back: (() -> Unit)? = null) {
    Column {
        if (back != null) IconButton(onClick = back, modifier = Modifier.offset(x = (-12).dp)) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, tr(locale, "Back", "ተመለስ"))
        }
        Text(title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = PilotNavy,
            modifier = Modifier.semantics { heading() })
        if (subtitle.isNotEmpty()) Text(subtitle, color = PilotMuted, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable internal fun PilotCard(
    onClick: (() -> Unit)? = null, content: @Composable ColumnScope.() -> Unit,
) {
    val colors = CardDefaults.cardColors(containerColor = PilotPaper)
    val shape = RoundedCornerShape(22.dp)
    val elevation = CardDefaults.cardElevation(3.dp)
    val body: @Composable () -> Unit = {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp), content = content)
    }
    if (onClick == null) Card(Modifier.fillMaxWidth(), shape = shape, colors = colors, elevation = elevation, content = { body() })
    else Card(onClick = onClick, modifier = Modifier.fillMaxWidth(), shape = shape, colors = colors, elevation = elevation, content = { body() })
}

@Composable internal fun Stat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(label, color = PilotMuted, style = MaterialTheme.typography.bodySmall)
    }
}
@Composable internal fun SectionTitle(title: String, action: String? = null, click: (() -> Unit)? = null) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f).semantics { heading() })
        if (action != null && click != null) TextButton(onClick = click) { Text(action, color = PilotAccent) }
    }
}
internal fun localizedSubject(subject: String, locale: String) = when (subject) {
    "Reading" -> tr(locale, "Reading", "ንባብ")
    "Mathematics" -> tr(locale, "Mathematics", "ሂሳብ")
    else -> tr(locale, "Other", "ሌላ")
}
@Composable private fun SettingRow(icon: ImageVector?, title: String, subtitle: String, click: () -> Unit) {
    Row(Modifier.fillMaxWidth().clickable(onClick = click).padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(42.dp).background(PilotMint, RoundedCornerShape(14.dp)), contentAlignment = Alignment.Center) {
            if (icon == null) PilotGlyph(PilotIcon.Mic, PilotNavy) else Icon(icon, null, tint = PilotNavy)
        }
        Column(Modifier.weight(1f).padding(horizontal = 14.dp)) {
            Text(title, fontWeight = FontWeight.SemiBold)
            Text(subtitle, color = PilotMuted, style = MaterialTheme.typography.bodySmall)
        }
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
    }
    HorizontalDivider()
}
@Composable private fun ErrorBanner(text: String, locale: String, retry: () -> Unit) {
    Surface(color = MaterialTheme.colorScheme.errorContainer) {
        Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(localizedStatus(text, locale), Modifier.weight(1f))
            TextButton(onClick = retry) { Text(tr(locale, "Retry", "እንደገና ሞክር")) }
        }
    }
}
@Composable internal fun Choice(label: String, value: String, options: Map<String, String>, enabled: Boolean = true, change: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, style = MaterialTheme.typography.labelLarge)
        Box {
            OutlinedButton(onClick = { expanded = true }, enabled = enabled, modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)) {
                Text(options[value] ?: "—", modifier = Modifier.weight(1f))
                Text("⌄")
            }
            DropdownMenu(expanded && enabled, { expanded = false }) {
                options.forEach { (key, text) ->
                    DropdownMenuItem(text = { Text(text, fontWeight = if (key == value) FontWeight.Bold else FontWeight.Normal) },
                        onClick = { expanded = false; change(key) })
                }
            }
        }
    }
}

internal fun localizedStatus(value: String, locale: String): String {
    if (locale != "am") return value.replaceFirstChar { it.uppercase() }
    return when {
        value == "saved" || value.startsWith("Lesson saved") -> "ቅጂው በዚህ ስልክ ላይ ተቀምጧል።"
        value == "interrupted" -> "ተቋርጧል"
        value.contains("route changed") -> "የማይክሮፎኑ መንገድ ተቀይሯል። ያገናኙና እንደገና ይሞክሩ።"
        value.contains("permission", ignoreCase = true) -> "የማይክሮፎን ፈቃድ ያስፈልጋል።"
        value.contains("storage", ignoreCase = true) || value.contains("32 MB") -> "የማከማቻ ቦታ በቂ አይደለም።"
        value.startsWith("Connect a wired") || value.contains("selected microphone") -> "ማይክሮፎኑን ያገናኙና እንደገና ይሞክሩ።"
        value.startsWith("No completed audio") || value.contains("before audio was saved") -> "ምንም ድምጽ አልተቀመጠም።"
        value.contains("completed chunks", ignoreCase = true) -> "የተጠናቀቁ የድምጽ ክፍሎች ብቻ ተመልሰዋል።"
        value.startsWith("Some ending audio") -> "የመጨረሻው ድምጽ ክፍል አልተቀመጠም።"
        value.startsWith("Two-hour") -> "ቅጂው የሁለት ሰዓት ገደቡ ላይ ደርሷል።"
        value.startsWith("No audio arrived") || value.contains("microphone", ignoreCase = true) -> "የማይክሮፎኑን ግንኙነት ይፈትሹና እንደገና ይሞክሩ።"
        else -> value
    }
}
private fun localizedResearchStatus(value: String, locale: String): String { if (locale != "am") return value; return when { value.startsWith("Research sharing is off") -> "የጥናት ማጋራት ጠፍቷል።"; value.startsWith("Sharing is off") -> "ማጋራት ጠፍቷል። የርቀት ፈቃድ ማንሳት ግንኙነትን እየጠበቀ ነው።"; value.startsWith("Research active") -> "ጥናቱ ንቁ ነው። በግልጽ የገቡ ምልከታዎች ብቻ ይጋራሉ።"; else -> value } }
private fun settingsTitle(page: String, locale: String) = when (page) { "profile" -> tr(locale, "Class profile", "የክፍል መገለጫ"); "language" -> tr(locale, "Language", "ቋንቋ"); "microphone" -> tr(locale, "Microphone", "ማይክሮፎን"); "notifications" -> tr(locale, "Notifications", "ማሳወቂያዎች"); "privacy" -> tr(locale, "Privacy & data", "ግላዊነትና መረጃ"); "support" -> tr(locale, "Help & support", "እገዛና ድጋፍ"); else -> tr(locale, "About ECHO", "ስለ ECHO") }
private fun reminderLabel(vm: PilotViewModel, locale: String): String {
    val day = if (vm.reminderDay == "SUNDAY") tr(locale, "Sunday", "እሁድ") else tr(locale, "Saturday", "ቅዳሜ")
    val time = String.format(Locale.ROOT, "%02d:%02d", vm.reminderMinute / 60, vm.reminderMinute % 60)
    return "$day · $time"
}
