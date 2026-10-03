package org.ethioware.echo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.Screen
import org.ethioware.echo.domain.IneligibleReason
import org.ethioware.echo.domain.ObservationRules
import org.ethioware.echo.domain.formatClock
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.ScreenHeader
import org.ethioware.echo.ui.components.StatusChip
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S
import org.ethioware.echo.ui.i18n.date

@Composable
fun LessonsScreen(vm: EchoViewModel) {
    val t = LocalT.current
    Column(Modifier.fillMaxSize()) {
        ScreenHeader(t(S.LESSONS_TITLE))
        if (vm.lessons.isEmpty()) {
            Muted(t(S.LESSONS_EMPTY), modifier = Modifier.padding(16.dp))
            return@Column
        }
        LazyColumn(
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(vm.lessons, key = { it.id }) { lesson ->
                val excluded = lesson.id in vm.localState.excludedIds
                Column(
                    Modifier.clip(RoundedCornerShape(16.dp)).clickable { vm.push(Screen.LessonDetail(lesson.id)) },
                ) {
                    EchoCard {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(t.date(lesson.startedOn), style = MaterialTheme.typography.titleSmall)
                            Text(lesson.startTime.toString(), style = MaterialTheme.typography.titleSmall)
                        }
                        Muted(t(S.LESSONS_ROW_META, formatClock(lesson.capture.observedS.toInt()), formatClock(lesson.durationS)))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            if (excluded) StatusChip(t(S.TAG_EXCLUDED), warn = true)
                            if (lesson.capture.coverage < ObservationRules.MIN_COVERAGE) StatusChip(t(S.TAG_LOW_COVERAGE), warn = true)
                            if (lesson.id in vm.queuedIds) StatusChip(t(S.TAG_WAITING))
                        }
                    }
                }
            }
        }
    }
}
