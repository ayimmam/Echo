package org.ethioware.echo.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.domain.Phase
import org.ethioware.echo.domain.formatClock
import org.ethioware.echo.ui.components.CardTone
import org.ethioware.echo.ui.components.ConfirmDialog
import org.ethioware.echo.ui.components.DemoBanner
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.LevelMeter
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.PrimaryButton
import org.ethioware.echo.ui.components.ScreenHeader
import org.ethioware.echo.ui.components.SecondaryButton
import org.ethioware.echo.ui.components.SidFallbackBanner
import org.ethioware.echo.ui.components.StatBlock
import org.ethioware.echo.ui.components.StatusChip
import org.ethioware.echo.ui.components.rememberNowMs
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S
import org.ethioware.echo.ui.i18n.label

/**
 * The lesson itself: a timer, a level meter and the microphone's state. Deliberately NO live score
 * (README s3.1): a number during the lesson would turn the phone into an invigilator.
 */
@Composable
fun LessonScreen(vm: EchoViewModel) {
    val t = LocalT.current
    val now = rememberNowMs(vm::now, 250)
    val session = vm.session ?: return
    val totals = session.totals(now)
    var askLeave by rememberSaveable { mutableStateOf(false) }
    BackHandler { askLeave = true }

    if (askLeave) {
        ConfirmDialog(
            title = t(S.LESSON_LEAVE_TITLE),
            body = t(S.LESSON_LEAVE_BODY),
            confirmText = t(S.LESSON_END),
            dismissText = t(S.LESSON_KEEP_GOING),
            onConfirm = { askLeave = false; vm.endLesson() },
            onDismiss = { askLeave = false },
        )
    }

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            DemoBanner()
            SidFallbackBanner()
            ScreenHeader(t(S.LESSON_TITLE))
            Column(
                Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Muted(
                    t(S.LESSON_META, vm.profile.grade, t(S.SUBJECT_MT), t.label(vm.mic)),
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    formatClock(totals.elapsedS),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
                EchoCard {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        StatBlock(t(S.LESSON_ELAPSED), formatClock(totals.elapsedS), Modifier.weight(1f))
                        StatBlock(t(S.LESSON_OBSERVED), formatClock(totals.observedS), Modifier.weight(1f), emphasis = true)
                        StatBlock(t(S.LESSON_NOT_OBSERVED), formatClock(totals.missingS), Modifier.weight(1f))
                    }
                }
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    StatusChip(
                        when (session.phase) {
                            Phase.RUNNING -> t(S.LESSON_MIC_OK)
                            Phase.PAUSED -> t(S.LESSON_MIC_PAUSED)
                            Phase.INTERRUPTED -> t(S.LESSON_MIC_INTERRUPTED)
                        },
                        warn = session.phase != Phase.RUNNING,
                    )
                }
                LevelMeter(now, active = session.phase == Phase.RUNNING, description = t(S.LESSON_MIC_OK))
                if (session.phase == Phase.INTERRUPTED) {
                    EchoCard(tone = CardTone.Warm) {
                        Text(
                            t(S.LESSON_INTERRUPT_TITLE, formatClock(((now - session.phaseSinceMs) / 1000).toInt())),
                            style = MaterialTheme.typography.titleSmall,
                        )
                        Text(t(S.LESSON_INTERRUPT_BODY), style = MaterialTheme.typography.bodyMedium)
                    }
                }
                if (session.phase == Phase.PAUSED) Muted(t(S.LESSON_PAUSED_NOTE))
                EchoCard(tone = CardTone.Calm) {
                    Text(t(S.LESSON_NO_AUDIO), style = MaterialTheme.typography.titleSmall)
                    Text(t(S.LESSON_NO_SCORE), style = MaterialTheme.typography.bodyMedium)
                }
                EchoCard {
                    Text(t(S.DEMO_CONTROLS), style = MaterialTheme.typography.titleSmall)
                    Muted(t(S.DEMO_NO_MIC))
                    SecondaryButton(t(S.DEMO_SKIP), onClick = { vm.skipAhead() })
                    SecondaryButton(
                        if (session.phase == Phase.INTERRUPTED) t(S.DEMO_CALL_END) else t(S.DEMO_CALL_START),
                        onClick = { vm.toggleCall() },
                        enabled = session.phase != Phase.PAUSED,
                    )
                }
            }
            Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                if (session.phase == Phase.PAUSED) {
                    SecondaryButton(t(S.LESSON_RESUME), onClick = { vm.resumeLesson() }, modifier = Modifier.weight(1f))
                } else {
                    SecondaryButton(
                        t(S.LESSON_PAUSE),
                        onClick = { vm.pauseLesson() },
                        modifier = Modifier.weight(1f),
                        enabled = session.phase == Phase.RUNNING,
                    )
                }
                PrimaryButton(t(S.LESSON_END), onClick = { vm.endLesson() }, modifier = Modifier.weight(1f))
            }
        }
    }
}
