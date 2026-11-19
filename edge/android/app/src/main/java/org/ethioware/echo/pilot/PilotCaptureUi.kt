package org.ethioware.echo.pilot

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.ZoneId
import java.util.Locale
import kotlin.math.sqrt

private val HeroTop = Color(0xFF2C596A)
private val HeroBottom = Color(0xFF1B3F50)
private val RecordingRed = Color(0xFFDF4D47)
internal val PilotAccent = Color(0xFF23796F)

@Composable
internal fun TeacherWelcome(locale: String, enabled: Boolean, language: () -> Unit, next: () -> Unit) {
    Column(
        Modifier.fillMaxSize().pilotHero().statusBarsPadding()
            .verticalScroll(rememberScrollState()).padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterVertically),
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            OutlinedButton(
                onClick = language, enabled = enabled,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
            ) { Text(if (locale == "am") "English" else "አማርኛ") }
        }
        Surface(color = Color.White.copy(alpha = .12f), shape = RoundedCornerShape(26.dp)) {
            Box(Modifier.size(88.dp), contentAlignment = Alignment.Center) {
                PilotGlyph(PilotIcon.Wave, Color.White, Modifier.size(46.dp))
            }
        }
        Text("ECHO", color = Color.White, style = MaterialTheme.typography.displayMedium)
        Text(
            tr(locale, "Record a lesson. Reflect on your practice.", "ትምህርትዎን ይቅዱ። በማስተማር ልምምድዎ ላይ ያሰላስሉ።"),
            color = Color.White, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center,
            modifier = Modifier.semantics { heading() },
        )
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            WelcomeFact(tr(locale, "Record, listen back and reflect without internet.", "ያለ ኢንተርኔት ይቅዱ፣ ቅጂዎን ያዳምጡ እና ያሰላስሉ።"))
            WelcomeFact(tr(locale, "Recordings and notes are encrypted on this phone.", "ቅጂዎችና ማስታወሻዎች በዚህ ስልክ ላይ ተመስጥረው ይቀመጣሉ።"))
            WelcomeFact(tr(locale, "Get the required school permission before recording a class.", "ክፍልን ከመቅዳትዎ በፊት ከትምህርት ቤቱ አስፈላጊውን ፈቃድ ያግኙ።"))
        }
        Button(
            onClick = next, enabled = enabled, modifier = Modifier.fillMaxWidth().heightIn(min = 54.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = PilotNavy),
        ) { Text(tr(locale, "Set up my class", "ክፍሌን አዘጋጅ")) }
    }
}

@Composable private fun WelcomeFact(text: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(6.dp).background(Color(0xFFB9DAD3), CircleShape))
        Text(text, color = Color.White, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
internal fun HomeScreen(
    vm: PilotViewModel,
    state: RecordingStatus,
    locale: String,
    start: () -> Unit,
    check: () -> Unit,
    settings: () -> Unit,
    history: (String) -> Unit,
    reflection: () -> Unit,
) {
    val today = LocalDate.now()
    val rows = vm.lessons
    val classId = vm.selectedId
    val recent = remember(rows, classId) { rows.firstOrNull { it.profile.id == classId && it.chunks > 0 } }
    val week = remember(rows, classId, today) { weeklyLessons(rows, classId, monday(today), ZoneId.systemDefault()) }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Column(Modifier.fillMaxWidth().pilotHero(48).statusBarsPadding().padding(horizontal = 24.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = settings, modifier = Modifier.offset(x = (-12).dp)) {
                    Icon(Icons.Default.Menu, tr(locale, "Settings", "ቅንብሮች"), tint = Color.White)
                }
                Spacer(Modifier.weight(1f))
                Icon(Icons.Default.Lock, null, tint = Color(0xFFCDE5E2), modifier = Modifier.size(15.dp))
                Spacer(Modifier.width(6.dp))
                Text(tr(locale, "Works offline", "ያለ ኢንተርኔት ይሠራል"), color = Color(0xFFCDE5E2), style = MaterialTheme.typography.labelMedium)
            }
            Spacer(Modifier.height(12.dp))
            Text(tr(locale, "Welcome", "እንኳን ደህና መጡ"), color = Color.White, style = MaterialTheme.typography.bodyLarge)
            Text(vm.profile?.name.orEmpty(), color = Color.White, style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold, modifier = Modifier.semantics { heading() })
            Text(tr(locale, "Record today’s lesson.", "የዛሬን ትምህርት ይቅዱ።"), color = Color(0xFFDAE8EA), style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(20.dp))
        }
        Column(Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            if (vm.profiles.size > 1) Box(Modifier.background(Color.White, RoundedCornerShape(16.dp)).padding(10.dp)) {
                ClassChoice(vm, locale)
            }
            CaptureCard(vm, state, locale, start, check)
            if (state.failed) RecordingOutcome(state, locale)
            SectionTitle(tr(locale, "This week", "በዚህ ሳምንት"), tr(locale, "View reflection", "ምልከታ ይመልከቱ"), reflection)
            PilotCard {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Stat(week.size.toString(), tr(locale, "Recordings", "ቅጂዎች"))
                    Stat(durationText(week.sumOf { it.capturedMs }, locale), tr(locale, "Total recorded", "ጠቅላላ የቅጂ ጊዜ"))
                }
            }
            SectionTitle(tr(locale, "Last recording", "የመጨረሻ ቅጂ"))
            if (recent == null) {
                PilotCard { Text(tr(locale, "Your first saved lesson will appear here.", "የመጀመሪያው የተቀመጠ ትምህርትዎ እዚህ ይታያል።"), color = PilotMuted) }
            } else LessonSummary(recent, locale) { history(recent.id) }
        }
    }
}

@Composable
internal fun CaptureCard(
    vm: PilotViewModel, state: RecordingStatus, locale: String, start: () -> Unit, check: () -> Unit,
) = PilotCard {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Surface(shape = CircleShape, color = PilotMint) {
            Box(Modifier.size(50.dp), contentAlignment = Alignment.Center) { PilotGlyph(PilotIcon.Wave, PilotAccent) }
        }
        val title = if (vm.externalMic && !vm.externalConnected) tr(locale, "Choose a microphone", "ማይክሮፎን ይምረጡ")
            else tr(locale, "Ready to record", "ለመቅዳት ዝግጁ")
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
    }
    MicrophoneControls(vm, locale, check)
    Button(
        onClick = start, enabled = !vm.busy && !state.running && (!vm.externalMic || vm.externalConnected),
        modifier = Modifier.fillMaxWidth().heightIn(min = 54.dp), shape = RoundedCornerShape(16.dp),
    ) {
        PilotGlyph(PilotIcon.Mic, LocalContentColor.current, Modifier.size(19.dp))
        Spacer(Modifier.width(8.dp))
        Text(tr(locale, "Start recording", "መቅዳት ጀምር"))
    }
}

@Composable
internal fun MicrophoneControls(vm: PilotViewModel, locale: String, check: () -> Unit) {
    val enabled = !vm.busy && !RecordingMonitor.state.running
    Column {
        Choice(
            tr(locale, "Microphone", "ማይክሮፎን"), if (vm.externalMic) "external" else "phone",
            linkedMapOf("external" to tr(locale, "External (wired / USB)", "ውጫዊ (በገመድ / USB)"),
                "phone" to tr(locale, "Phone microphone", "የስልክ ማይክሮፎን")), enabled,
        ) { vm.selectMic(it == "external") }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            val status = when {
                vm.externalMic && !vm.externalConnected -> tr(locale, "Connect your microphone", "ማይክሮፎኑን ያገናኙ")
                else -> when (vm.micCheck.state) {
                    "checking" -> tr(locale, "Speak briefly", "ለአጭር ጊዜ ይናገሩ")
                    "ready" -> tr(locale, "Signal detected", "ድምጽ ተገኝቷል")
                    "no_signal", "no_input" -> if (vm.externalMic) tr(locale, "No signal. Check power and mute.", "ድምጽ የለም። ኃይሉንና ድምጸ ከል ሁኔታውን ይፈትሹ።")
                        else tr(locale, "No signal. Speak close to the phone.", "ድምጽ የለም። ወደ ስልኩ ቀርበው ይናገሩ።")
                    "route_failed", "failed" -> tr(locale, "Check the microphone and retry", "ማይክሮፎኑን ፈትሸው እንደገና ይሞክሩ")
                    "permission" -> tr(locale, "Microphone access is needed", "የማይክሮፎን ፈቃድ ያስፈልጋል")
                    else -> if (vm.externalMic) tr(locale, "Connected", "ተገናኝቷል") else tr(locale, "Built-in input", "አብሮ የተሠራ ማይክሮፎን")
                }
            }
            Text(status, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall,
                color = if (vm.micCheck.state == "ready") PilotAccent else PilotMuted)
            TextButton(onClick = check, enabled = enabled && (!vm.externalMic || vm.externalConnected)) {
                Text(tr(locale, "Test mic", "ማይኩን ፈትሽ"))
            }
        }
    }
}

@Composable
internal fun ActiveRecording(state: RecordingStatus, external: Boolean, locale: String, stop: () -> Unit) {
    Column(
        Modifier.fillMaxSize().pilotHero().statusBarsPadding()
            .verticalScroll(rememberScrollState()).padding(horizontal = 24.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
    ) {
        val title = when {
            state.stopping -> tr(locale, "Saving on this phone…", "በዚህ ስልክ ላይ በማስቀመጥ ላይ…")
            state.lessonId == null -> tr(locale, "Checking microphone…", "ማይክሮፎኑን በመፈተሽ ላይ…")
            else -> tr(locale, "Recording", "በመቅዳት ላይ")
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(Modifier.size(10.dp).background(RecordingRed, CircleShape))
            Text(title, color = Color.White, style = MaterialTheme.typography.titleMedium)
        }
        val timerStyle = if (LocalDensity.current.fontScale > 1.25f) MaterialTheme.typography.displaySmall
            else MaterialTheme.typography.displayLarge
        Text(timerText(state.seconds), color = Color.White, style = timerStyle, maxLines = 1, textAlign = TextAlign.Center)
        Text(
            if (external) tr(locale, "External microphone", "ውጫዊ ማይክሮፎን") else tr(locale, "Phone microphone", "የስልክ ማይክሮፎን"),
            color = Color(0xFFD9E8EB), textAlign = TextAlign.Center,
        )
        if (state.stopping) {
            LinearProgressIndicator(Modifier.fillMaxWidth(), color = Color(0xFFA6D1CB))
        } else {
            LevelWave(state.levels)
        }
        val stopLabel = tr(locale, "Stop and save", "አቁምና አስቀምጥ")
        Surface(
            onClick = stop, enabled = !state.stopping, shape = CircleShape,
            color = RecordingRed, contentColor = Color.White,
            modifier = Modifier.size(108.dp).semantics {
                role = Role.Button
                contentDescription = stopLabel
            },
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(Modifier.size(32.dp).background(Color.White, RoundedCornerShape(6.dp)))
            }
        }
        Text(stopLabel, color = Color.White, style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.clearAndSetSemantics {})
        Text(tr(locale, "You can lock your screen while recording.", "እየቀዱ ማያዎን መቆለፍ ይችላሉ።"), color = Color(0xFFC8DCE1),
            style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
    }
}

@Composable
internal fun RecordingOutcome(state: RecordingStatus, locale: String) {
    if (state.message.isBlank()) return
    val color = if (state.failed) MaterialTheme.colorScheme.errorContainer else PilotMint
    Surface(color = color, shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth()) {
        Text(localizedStatus(state.message, locale), Modifier.padding(12.dp), style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable private fun LevelWave(levels: List<Int>) {
    Canvas(Modifier.fillMaxWidth().height(96.dp)) {
        val step = size.width / 25f
        for (index in 0 until 24) {
            val fraction = sqrt((levels.getOrNull(index) ?: 0) / 32768f)
            val radius = (0.05f + fraction.coerceIn(0f, 1f) * 0.43f) * size.height
            val x = (index + 1) * step
            drawLine(Color(0xFF9AC4BC), Offset(x, center.y - radius), Offset(x, center.y + radius), strokeWidth = 3.dp.toPx(), cap = StrokeCap.Round)
        }
    }
}

private fun Modifier.pilotHero(overlap: Int = 0) = drawBehind {
    val bottom = size.height + overlap.dp.toPx()
    val radius = if (overlap > 0) 32.dp.toPx() else 0f
    drawRoundRect(Brush.verticalGradient(listOf(HeroTop, HeroBottom), endY = bottom),
        size = Size(size.width, bottom), cornerRadius = CornerRadius(radius))
    if (radius > 0) drawRect(HeroTop, size = Size(size.width, radius))
    val accent = Path().apply {
        moveTo(size.width * .5f, bottom * .7f)
        quadraticTo(size.width * .8f, bottom * .33f, size.width, bottom * .42f)
        lineTo(size.width, bottom * .75f)
        quadraticTo(size.width * .75f, bottom * .62f, size.width * .5f, bottom * .7f)
        close()
    }
    drawPath(accent, Color.White.copy(alpha = .065f))
}

internal enum class PilotIcon { Home, Mic, Clipboard, Book, Wave }

@Composable
internal fun PilotGlyph(icon: PilotIcon, tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier.size(24.dp)) {
        val unit = minOf(size.width, size.height) / 24f
        translate(left = center.x - 12 * unit, top = center.y - 12 * unit) {
            scale(unit, unit, Offset.Zero) {
                val stroke = Stroke(width = 1.8f, cap = StrokeCap.Round, join = StrokeJoin.Round)
                fun line(x1: Float, y1: Float, x2: Float, y2: Float) =
                    drawLine(tint, Offset(x1, y1), Offset(x2, y2), stroke.width, StrokeCap.Round)
                fun path(build: Path.() -> Unit) = drawPath(Path().apply(build), tint, style = stroke)
                when (icon) {
                    PilotIcon.Home -> path {
                        moveTo(3f, 10f); lineTo(12f, 3f); lineTo(21f, 10f); lineTo(21f, 21f)
                        lineTo(3f, 21f); close()
                        moveTo(9f, 21f); lineTo(9f, 13f); lineTo(15f, 13f); lineTo(15f, 21f)
                    }
                    PilotIcon.Mic -> {
                        drawRoundRect(tint, Offset(8.5f, 2f), Size(7f, 12f), CornerRadius(3.5f), style = stroke)
                        path { moveTo(5f, 10f); lineTo(5f, 12f); cubicTo(5f, 20f, 19f, 20f, 19f, 12f); lineTo(19f, 10f) }
                        line(12f, 18f, 12f, 22f)
                    }
                    PilotIcon.Clipboard -> {
                        path {
                            moveTo(8f, 5f); lineTo(5f, 5f); lineTo(5f, 21f); lineTo(19f, 21f)
                            lineTo(19f, 5f); lineTo(16f, 5f)
                        }
                        drawRoundRect(tint, Offset(8f, 3f), Size(8f, 5f), CornerRadius(1.5f), style = stroke)
                    }
                    PilotIcon.Book -> path {
                        moveTo(12f, 7f); quadraticTo(9f, 3f, 2f, 5f); lineTo(2f, 20f)
                        quadraticTo(8f, 18f, 12f, 21f); quadraticTo(16f, 18f, 22f, 20f)
                        lineTo(22f, 5f); quadraticTo(15f, 3f, 12f, 7f); lineTo(12f, 21f)
                    }
                    PilotIcon.Wave -> {
                        line(2f, 9f, 2f, 15f); line(7f, 6f, 7f, 18f); line(12f, 2f, 12f, 22f)
                        line(17f, 6f, 17f, 18f); line(22f, 9f, 22f, 15f)
                    }
                }
            }
        }
    }
}

internal fun timerText(seconds: Long) = String.format(Locale.ROOT, "%02d:%02d:%02d", seconds / 3600, seconds / 60 % 60, seconds % 60)

internal fun durationText(millis: Long, locale: String): String {
    val seconds = millis / 1000
    return when {
        seconds >= 3600 -> "${seconds / 3600} ${tr(locale, "hr", "ሰዓት")} ${seconds / 60 % 60} ${tr(locale, "min", "ደቂቃ")}"
        seconds >= 60 -> "${seconds / 60} ${tr(locale, "min", "ደቂቃ")} ${seconds % 60} ${tr(locale, "sec", "ሰከንድ")}"
        else -> "$seconds ${tr(locale, "sec", "ሰከንድ")}"
    }
}
