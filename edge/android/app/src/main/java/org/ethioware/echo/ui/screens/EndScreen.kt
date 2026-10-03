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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.data.Activity
import org.ethioware.echo.data.AdultConditions
import org.ethioware.echo.domain.ObservationRules
import org.ethioware.echo.domain.formatClock
import org.ethioware.echo.ui.components.CardTone
import org.ethioware.echo.ui.components.CheckRow
import org.ethioware.echo.ui.components.ChoiceRow
import org.ethioware.echo.ui.components.ConfirmDialog
import org.ethioware.echo.ui.components.DemoBanner
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.PrimaryButton
import org.ethioware.echo.ui.components.ScreenHeader
import org.ethioware.echo.ui.components.SecondaryButton
import org.ethioware.echo.ui.components.SectionTitle
import org.ethioware.echo.ui.components.SidFallbackBanner
import org.ethioware.echo.ui.components.StatBlock
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S
import org.ethioware.echo.ui.i18n.label

/**
 * End of lesson: nothing is saved or queued until the teacher taps Save summary; Discard creates no
 * record at all (FEATURE_REVIEW A3/A4). Context is a few finite choices, never free text.
 */
@Composable
fun EndScreen(vm: EchoViewModel) {
    val t = LocalT.current
    val draft = vm.draft ?: return
    var askDiscard by rememberSaveable { mutableStateOf(false) }
    BackHandler { askDiscard = true }

    if (askDiscard) {
        ConfirmDialog(
            title = t(S.DISCARD_TITLE),
            body = t(S.DISCARD_BODY),
            confirmText = t(S.DISCARD_CONFIRM),
            onConfirm = { askDiscard = false; vm.discardDraft() },
            onDismiss = { askDiscard = false },
            destructive = true,
        )
    }

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            DemoBanner()
            SidFallbackBanner()
            ScreenHeader(t(S.END_TITLE))
            Column(
                Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                EchoCard {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        StatBlock(t(S.LESSON_ELAPSED), formatClock(draft.totals.elapsedS), Modifier.weight(1f))
                        StatBlock(t(S.LESSON_OBSERVED), formatClock(draft.totals.observedS), Modifier.weight(1f), emphasis = true)
                        StatBlock(t(S.LESSON_NOT_OBSERVED), formatClock(draft.totals.missingS), Modifier.weight(1f))
                    }
                }
                if (draft.totals.coverage < ObservationRules.MIN_COVERAGE) {
                    EchoCard(tone = CardTone.Warm) { Text(t(S.END_LOW_COVERAGE), style = MaterialTheme.typography.bodyMedium) }
                }
                SectionTitle(t(S.END_ABOUT))
                EchoCard {
                    Text(t(S.END_ACTIVITY_Q), style = MaterialTheme.typography.titleSmall)
                    Activity.entries.forEach { a ->
                        ChoiceRow(t.label(a), draft.activity == a, { vm.updateDraft { it.copy(activity = a) } })
                    }
                }
                EchoCard {
                    Text(t(S.END_ADULT_Q), style = MaterialTheme.typography.titleSmall)
                    AdultConditions.entries.forEach { a ->
                        ChoiceRow(t.label(a), draft.adult == a, { vm.updateDraft { it.copy(adult = a) } })
                    }
                    Muted(t(S.END_ADULT_HELP))
                }
                CheckRow(t(S.END_EXCLUDE), draft.exclude, { v -> vm.updateDraft { it.copy(exclude = v) } })
            }
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                PrimaryButton(t(S.END_SAVE), onClick = { vm.saveDraft() })
                SecondaryButton(t(S.END_DISCARD), onClick = { askDiscard = true }, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
