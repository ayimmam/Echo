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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.data.Measure
import org.ethioware.echo.domain.formatClock
import org.ethioware.echo.domain.percent
import org.ethioware.echo.ui.components.CardTone
import org.ethioware.echo.ui.components.ConfirmDialog
import org.ethioware.echo.ui.components.DangerButton
import org.ethioware.echo.ui.components.DemoBanner
import org.ethioware.echo.ui.components.Divider
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.ScreenHeader
import org.ethioware.echo.ui.components.SecondaryButton
import org.ethioware.echo.ui.components.SidFallbackBanner
import org.ethioware.echo.ui.components.StatBlock
import org.ethioware.echo.ui.components.StatusChip
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S
import org.ethioware.echo.ui.i18n.date
import org.ethioware.echo.ui.i18n.label
import org.ethioware.echo.ui.i18n.reason

@Composable
fun LessonDetailScreen(vm: EchoViewModel, id: String) {
    val t = LocalT.current
    val lesson = vm.lessonById(id)
    var askDelete by rememberSaveable { mutableStateOf(false) }
    BackHandler { vm.pop() }
    if (lesson == null) {
        LaunchedEffect(Unit) { vm.pop() }
        return
    }
    val excluded = id in vm.localState.excludedIds
    val cap = lesson.capture

    if (askDelete) {
        ConfirmDialog(
            title = t(S.DETAIL_DELETE_TITLE),
            body = t(S.DETAIL_DELETE_BODY),
            confirmText = t(S.DETAIL_DELETE),
            onConfirm = { askDelete = false; vm.deleteLesson(id) },
            onDismiss = { askDelete = false },
            destructive = true,
        )
    }

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            DemoBanner()
            SidFallbackBanner()
            ScreenHeader(t(S.DETAIL_TITLE), onBack = { vm.pop() })
            Column(
                Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(horizontal = 16.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                EchoCard(tone = CardTone.Calm) {
                    StatusChip(if (id in vm.queuedIds) t(S.DETAIL_QUEUED) else t(S.DETAIL_SAVED_LOCAL))
                    Text("${t.date(lesson.startedOn)} · ${lesson.startTime}", style = MaterialTheme.typography.titleSmall)
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        StatBlock(t(S.LESSON_ELAPSED), formatClock(lesson.durationS), Modifier.weight(1f))
                        StatBlock(t(S.LESSON_OBSERVED), formatClock(cap.observedS.toInt()), Modifier.weight(1f), emphasis = true)
                        StatBlock(t(S.LESSON_NOT_OBSERVED), formatClock(cap.missingS.toInt()), Modifier.weight(1f))
                    }
                    Muted(t(S.DETAIL_COVERAGE, formatClock(cap.observedS.toInt()), formatClock(lesson.durationS), percent(cap.coverage)))
                }
                if (excluded) StatusChip(t(S.DETAIL_EXCLUDED), warn = true)
                EchoCard {
                    MeasureBlock(t(S.M_I1), t(S.M_I1_HELP), lesson.i1) { "${percent(it)}%" }
                    Divider()
                    MeasureBlock(t(S.M_I5), t(S.M_I5_HELP), lesson.i5) { "%.1f".format(t.javaLocale, it) }
                    Muted(t(S.ILLUSTRATIVE))
                }
                EchoCard {
                    Text(t(S.DEFERRED_TITLE), style = MaterialTheme.typography.titleSmall)
                    Muted(t(S.DEFERRED_BODY))
                }
                Muted(t(S.DETAIL_CONTEXT, t.label(lesson.activity), t.label(lesson.adult)))
                SecondaryButton(
                    if (excluded) t(S.DETAIL_INCLUDE_ACTION) else t(S.DETAIL_EXCLUDE_ACTION),
                    onClick = { vm.setExcluded(id, !excluded) },
                )
                DangerButton(t(S.DETAIL_DELETE), onClick = { askDelete = true })
            }
        }
    }
}

/** A measure is shown with its value, or with the plain reason it is not shown. Never a zero. */
@Composable
fun MeasureBlock(label: String, help: String, measure: Measure, format: (Double) -> String) {
    val t = LocalT.current
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(label, style = MaterialTheme.typography.titleSmall)
        if (measure.available && measure.value != null) {
            Text(format(measure.value), style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
            Muted(help)
        } else {
            Muted(t.reason(measure.reason))
        }
    }
}
