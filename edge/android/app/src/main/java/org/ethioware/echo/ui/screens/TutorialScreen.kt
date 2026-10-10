package org.ethioware.echo.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.ui.components.CardTone
import org.ethioware.echo.ui.components.DemoBanner
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.PrimaryButton
import org.ethioware.echo.ui.components.ScreenHeader
import org.ethioware.echo.ui.components.SecondaryButton
import org.ethioware.echo.ui.components.SidFallbackBanner
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S

private data class TutorialTip(val control: String, val action: String)
private data class TutorialPage(val title: String, val intro: String, val tips: List<TutorialTip>)

/** A one-time, skippable control guide. Settings can reopen it without changing demo data. */
@Composable
fun TutorialScreen(vm: EchoViewModel) {
    val t = LocalT.current
    val pages = listOf(
        TutorialPage(t(S.TODAY_TITLE), t(S.TUT_TODAY_INTRO), listOf(
            TutorialTip(t(S.TUT_TABS_LABEL), t(S.TUT_TABS_BODY)),
            TutorialTip(t(S.TUT_BACK_LABEL), t(S.TUT_BACK_BODY)),
            TutorialTip(t(S.TODAY_SETUP), t(S.TUT_DETAILS_BODY)),
            TutorialTip(t(S.TUT_SIZE_LABEL), t(S.TUT_SIZE_BODY)),
            TutorialTip(t(S.TODAY_MIC), t(S.TUT_MIC_BODY)),
            TutorialTip(t(S.TODAY_CHECK), t(S.TUT_CHECK_BODY)),
            TutorialTip(t(S.TODAY_START), t(S.TUT_START_BODY)),
        )),
        TutorialPage(t(S.READY_TITLE), t(S.TUT_LESSON_INTRO), listOf(
            TutorialTip(t(S.READY_FINISH_NOW), t(S.TUT_FINISH_NOW_BODY)),
            TutorialTip(t(S.READY_AGAIN), t(S.TUT_AGAIN_BODY)),
            TutorialTip(t(S.READY_START), t(S.TUT_READY_START_BODY)),
            TutorialTip(t(S.TUT_PAUSE_LABEL), t(S.TUT_PAUSE_BODY)),
            TutorialTip(t(S.TUT_CALL_LABEL), t(S.TUT_CALL_BODY)),
            TutorialTip(t(S.DEMO_SKIP), t(S.TUT_SKIP_AHEAD_BODY)),
            TutorialTip(t(S.LESSON_END), t(S.TUT_END_BODY)),
            TutorialTip(t(S.END_DISCARD), t(S.TUT_STOP_DISCARD_BODY)),
        )),
        TutorialPage(t(S.END_TITLE), t(S.TUT_END_INTRO), listOf(
            TutorialTip(t(S.TUT_ACTIVITY_LABEL), t(S.TUT_ACTIVITY_BODY)),
            TutorialTip(t(S.TUT_ADULT_LABEL), t(S.TUT_ADULT_BODY)),
            TutorialTip(t(S.END_EXCLUDE), t(S.TUT_EXCLUDE_BODY)),
            TutorialTip(t(S.END_SAVE), t(S.TUT_SAVE_BODY)),
            TutorialTip(t(S.END_DISCARD), t(S.TUT_DISCARD_BODY)),
            TutorialTip(t(S.TUT_INCLUDE_LABEL), t(S.TUT_INCLUDE_BODY)),
            TutorialTip(t(S.DETAIL_DELETE), t(S.TUT_DELETE_BODY)),
        )),
        TutorialPage(t(S.REPORT_TITLE), t(S.TUT_REFLECTION_INTRO), listOf(
            TutorialTip(t(S.TUT_WEEK_LABEL), t(S.TUT_WEEK_BODY)),
            TutorialTip(t(S.TUT_HISTORY_LABEL), t(S.TUT_HISTORY_BODY)),
            TutorialTip(t(S.TUT_HISTORY_ROW_LABEL), t(S.TUT_HISTORY_ROW_BODY)),
            TutorialTip(t(S.TUT_FOLLOWUP_LABEL), t(S.TUT_FOLLOWUP_BODY)),
            TutorialTip(t(S.CARD_SEE), t(S.TUT_CARD_SEE_BODY)),
            TutorialTip(t(S.CARD_CHOOSE), t(S.TUT_CARD_CHOOSE_BODY)),
            TutorialTip(t(S.TUT_CARD_EDIT_LABEL), t(S.TUT_CARD_EDIT_BODY)),
        )),
        TutorialPage(t(S.SETTINGS_TITLE), t(S.TUT_SETTINGS_INTRO), listOf(
            TutorialTip(t(S.SET_LANGUAGE), t(S.TUT_LANGUAGE_BODY)),
            TutorialTip(t(S.SET_PRIVATE_SYNC), t(S.TUT_PRIVATE_BODY)),
            TutorialTip(t(S.SET_RESEARCH_CONTRIBUTION), t(S.TUT_RESEARCH_BODY)),
            TutorialTip(t(S.SET_DELETE_SHARED), t(S.TUT_DELETE_SHARED_BODY)),
            TutorialTip(t(S.SET_WITHDRAW), t(S.TUT_WITHDRAW_BODY)),
            TutorialTip(t(S.SET_RESET), t(S.TUT_RESET_BODY)),
            TutorialTip(t(S.SET_TUTORIAL), t(S.TUT_REOPEN_BODY)),
            TutorialTip(t(S.WITHDRAWN_RESTART), t(S.TUT_RESTART_BODY)),
        )),
    )
    val pageIndex = vm.tutorialPage.coerceIn(pages.indices)
    val page = pages[pageIndex]
    val scrollState = rememberScrollState()
    LaunchedEffect(pageIndex) { scrollState.scrollTo(0) }
    BackHandler { vm.dismissTutorial() }

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            DemoBanner()
            SidFallbackBanner()
            ScreenHeader(t(S.TUTORIAL_TITLE))
            Row(
                Modifier.fillMaxWidth().padding(start = 20.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Muted(t(S.TUTORIAL_PROGRESS, pageIndex + 1, pages.size), modifier = Modifier.weight(1f))
                TextButton(onClick = vm::dismissTutorial) { Text(t(S.TUTORIAL_SKIP)) }
            }
            Column(
                Modifier.weight(1f).verticalScroll(scrollState).padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(page.title, style = MaterialTheme.typography.headlineSmall)
                Muted(page.intro)
                page.tips.forEach { tip ->
                    EchoCard(tone = CardTone.Calm) {
                        Text(tip.control, style = MaterialTheme.typography.titleSmall)
                        Text(tip.action, style = MaterialTheme.typography.bodyMedium)
                    }
                }
                Spacer(Modifier.height(8.dp))
            }
            Row(
                Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                if (pageIndex > 0) {
                    SecondaryButton(t(S.BACK), onClick = vm::previousTutorialPage, modifier = Modifier.weight(1f))
                }
                PrimaryButton(
                    t(if (pageIndex == pages.lastIndex) S.TUTORIAL_DONE else S.TUTORIAL_NEXT),
                    onClick = vm::nextTutorialPage,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}
