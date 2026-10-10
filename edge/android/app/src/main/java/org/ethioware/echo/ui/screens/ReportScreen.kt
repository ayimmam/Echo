package org.ethioware.echo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.Screen
import org.ethioware.echo.data.FollowUpAnswer
import org.ethioware.echo.data.PracticeCard
import org.ethioware.echo.domain.percent
import org.ethioware.echo.domain.formatClock
import org.ethioware.echo.ui.components.CardTone
import org.ethioware.echo.ui.components.Divider
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.PrimaryButton
import org.ethioware.echo.ui.components.ScreenHeader
import org.ethioware.echo.ui.components.SecondaryButton
import org.ethioware.echo.ui.components.SectionTitle
import org.ethioware.echo.ui.components.StatusChip
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S
import org.ethioware.echo.ui.i18n.date
import org.ethioware.echo.ui.i18n.hm

/**
 * The weekly reflection (FEATURE_REVIEW A1). At most two measures, shown only when the eligibility
 * rule passes; otherwise an honest "not enough reliable observations". Values never pick the practice.
 */
@Composable
fun ReportScreen(vm: EchoViewModel) {
    val t = LocalT.current
    val week = vm.weekSummary()
    val isCurrent = vm.reportWeek == vm.currentWeek
    var lastAnswer by remember { mutableStateOf<FollowUpAnswer?>(null) }
    var choosing by remember { mutableStateOf(false) }
    var showHistory by rememberSaveable { mutableStateOf(false) }

    Column(Modifier.verticalScroll(rememberScrollState())) {
        ScreenHeader(t(S.REPORT_TITLE))
        Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { vm.goEarlierWeek() }, enabled = vm.canGoEarlier) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = t(S.REPORT_PREV))
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(t(S.REPORT_WEEK_OF, t.date(vm.reportWeek)), style = MaterialTheme.typography.titleSmall, textAlign = TextAlign.Center)
                    if (isCurrent) Muted(t(S.REPORT_THIS_WEEK))
                }
                IconButton(onClick = { vm.goLaterWeek() }, enabled = vm.canGoLater) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = t(S.REPORT_NEXT))
                }
            }
            if (week.lessons.isNotEmpty()) {
                Muted(t(S.REPORT_SUMMARY, vm.profile.grade, t(S.SUBJECT_MT), week.lessons.size, t.hm(week.observedS)))
            }
            StatusChip(t(S.REPORT_PRIVATE))

            val followUp = vm.followUp
            if (isCurrent && followUp != null) {
                val card = vm.cards.firstOrNull { it.id == followUp.cardId }
                if (card != null) FollowUpCard(vm, card) { lastAnswer = it }
            }

            EchoCard {
                if (week.lessons.isEmpty()) {
                    Muted(t(S.REPORT_NO_LESSONS))
                } else {
                    Text(t(S.REPORT_OBS_TITLE), style = MaterialTheme.typography.titleMedium)
                    if (week.i1Ready && week.i1Mean != null) {
                        MeasureBlock(t(S.M_I1), t(S.M_I1_HELP), org.ethioware.echo.data.Measure.of(week.i1Mean)) { "${percent(it)}%" }
                        Muted(t(S.REPORT_METRIC_N, week.i1Eligible.size, week.i1Eligible.map { it.startedOn }.toSet().size, percent(week.i1Coverage ?: 0.0)))
                    } else {
                        Text(t(S.M_I1), style = MaterialTheme.typography.titleSmall)
                        Muted(metricUnavailableText(t(S.REPORT_METRIC_NOT_READY, week.i1Eligible.size, week.i1Eligible.map { it.startedOn }.toSet().size), t(S.REPORT_UNRELEASED), week.i1Reason))
                    }
                    Divider()
                    if (week.i5Ready && week.i5Mean != null) {
                        MeasureBlock(t(S.M_I5), t(S.M_I5_HELP), org.ethioware.echo.data.Measure.of(week.i5Mean)) {
                            "%.1f".format(t.javaLocale, it)
                        }
                        Muted(t(S.REPORT_METRIC_N, week.i5Eligible.size, week.i5Eligible.map { it.startedOn }.toSet().size, percent(week.i5Coverage ?: 0.0)))
                    } else {
                        Text(t(S.M_I5), style = MaterialTheme.typography.titleSmall)
                        Muted(metricUnavailableText(t(S.REPORT_METRIC_NOT_READY, week.i5Eligible.size, week.i5Eligible.map { it.startedOn }.toSet().size), t(S.REPORT_UNRELEASED), week.i5Reason))
                    }
                    Divider()
                    Muted(t(S.REPORT_NO_BAND))
                    Muted(t(S.REPORT_OBS_SCOPE))
                    if (week.i1Ready || week.i5Ready) Muted(t(S.ILLUSTRATIVE))
                }
            }
            EchoCard {
                Text(t(S.DEFERRED_TITLE), style = MaterialTheme.typography.titleSmall)
                Muted(t(S.DEFERRED_BODY))
            }

            if (isCurrent) {
                SectionTitle(t(S.CARD_SECTION))
                lastAnswer?.let {
                    EchoCard(tone = CardTone.Calm) {
                        Text(t(S.FOLLOW_THANKS), style = MaterialTheme.typography.bodyMedium)
                        if (it == FollowUpAnswer.NOT_USEFUL) Text(t(S.FOLLOW_NEXT_DIFFERENT), style = MaterialTheme.typography.bodyMedium)
                    }
                }
                val chosen = vm.localState.chosen?.let { c -> vm.cards.firstOrNull { it.id == c.cardId } }
                if (chosen != null && !choosing) {
                    ChosenCardPanel(vm, chosen, onChange = { choosing = true })
                } else {
                    Muted(t(S.CARD_CHOOSE_INTRO))
                    vm.offered.forEach { card ->
                        CardOption(vm, card, isCurrent = card.id == chosen?.id) { choosing = false }
                    }
                }
            }

            SectionTitle(t(S.REPORT_HISTORY))
            SecondaryButton(
                if (showHistory) t(S.REPORT_HISTORY_HIDE) else t(S.REPORT_HISTORY_SHOW, week.lessons.size),
                onClick = { showHistory = !showHistory },
            )
            if (showHistory) {
                if (week.lessons.isEmpty()) Muted(t(S.LESSONS_EMPTY))
                week.lessons.forEach { lesson ->
                    val excluded = lesson.id in vm.localState.excludedIds
                    EchoCard(
                        modifier = Modifier.clickable(role = Role.Button) { vm.push(Screen.LessonDetail(lesson.id)) },
                    ) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(t.date(lesson.startedOn), style = MaterialTheme.typography.titleSmall)
                            Text(lesson.startTime.toString(), style = MaterialTheme.typography.titleSmall)
                        }
                        Muted(t(S.LESSONS_ROW_META, formatClock(lesson.capture.observedS.toInt()), formatClock(lesson.durationS)))
                        if (excluded) StatusChip(t(S.TAG_EXCLUDED), warn = true)
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

private fun metricUnavailableText(notReady: String, unreleased: String, reason: String?): String =
    if (reason == "insufficient_observation" || reason == "insufficient_speech") notReady else unreleased

@Composable
private fun FollowUpCard(vm: EchoViewModel, card: PracticeCard, onAnswered: (FollowUpAnswer) -> Unit) {
    val t = LocalT.current
    EchoCard(tone = CardTone.Warm) {
        Text(t(S.FOLLOW_Q, card.title.forLocale(t.locale)), style = MaterialTheme.typography.titleSmall)
        val answers = listOf(
            FollowUpAnswer.TRIED to S.FOLLOW_TRIED,
            FollowUpAnswer.NOT_YET to S.FOLLOW_NOT_YET,
            FollowUpAnswer.NOT_USEFUL to S.FOLLOW_NOT_USEFUL,
            FollowUpAnswer.SKIP to S.FOLLOW_SKIP,
        )
        answers.chunked(2).forEach { pair ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                pair.forEach { (answer, label) ->
                    SecondaryButton(
                        t(label),
                        onClick = { vm.answerFollowUp(answer); onAnswered(answer) },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun ChosenCardPanel(vm: EchoViewModel, card: PracticeCard, onChange: () -> Unit) {
    val t = LocalT.current
    EchoCard(tone = CardTone.Calm) {
        Muted(t(S.CARD_CURRENT))
        Text(card.title.forLocale(t.locale), style = MaterialTheme.typography.titleMedium)
        Text(card.action.forLocale(t.locale), style = MaterialTheme.typography.bodyLarge)
        PrimaryButton(t(S.CARD_SEE), onClick = { vm.push(Screen.CardDetail(card.id)) })
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SecondaryButton(t(S.CARD_CHANGE), onClick = onChange, modifier = Modifier.weight(1f))
            SecondaryButton(t(S.CARD_REMOVE), onClick = { vm.removeCard() }, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun CardOption(vm: EchoViewModel, card: PracticeCard, isCurrent: Boolean, onChosen: () -> Unit) {
    val t = LocalT.current
    EchoCard {
        Text(card.title.forLocale(t.locale), style = MaterialTheme.typography.titleSmall)
        Muted(card.action.forLocale(t.locale))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SecondaryButton(t(S.CARD_SEE), onClick = { vm.push(Screen.CardDetail(card.id)) }, modifier = Modifier.weight(1f))
            if (isCurrent) {
                StatusChip(t(S.CARD_CURRENT))
            } else {
                PrimaryButton(t(S.CARD_CHOOSE), onClick = { vm.chooseCard(card.id); onChosen() }, modifier = Modifier.weight(1f))
            }
        }
    }
}
