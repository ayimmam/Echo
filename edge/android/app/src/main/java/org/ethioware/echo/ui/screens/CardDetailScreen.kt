package org.ethioware.echo.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.data.AppLocale
import org.ethioware.echo.ui.components.CardTone
import org.ethioware.echo.ui.components.DemoBanner
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.PrimaryButton
import org.ethioware.echo.ui.components.ScreenHeader
import org.ethioware.echo.ui.components.SidFallbackBanner
import org.ethioware.echo.ui.components.StatusChip
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S

/** A practice card: one action, a short example and an optional on-screen prompt for a colleague. */
@Composable
fun CardDetailScreen(vm: EchoViewModel, id: String) {
    val t = LocalT.current
    val card = vm.cards.firstOrNull { it.id == id }
    BackHandler { vm.pop() }
    if (card == null) {
        LaunchedEffect(Unit) { vm.pop() }
        return
    }
    val isChosen = vm.localState.chosen?.cardId == id

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            DemoBanner()
            SidFallbackBanner()
            ScreenHeader(card.title.forLocale(t.locale), onBack = { vm.pop() })
            Column(
                Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                if (card.title.isFallback(t.locale) && t.locale == AppLocale.SID) StatusChip("[SID-DRAFT]", warn = true)
                EchoCard(tone = CardTone.Calm) {
                    Muted(t(S.CARD_ACTION))
                    Text(card.action.forLocale(t.locale), style = MaterialTheme.typography.titleSmall)
                }
                EchoCard {
                    Muted(t(S.CARD_EXAMPLE))
                    Text(card.example.forLocale(t.locale), style = MaterialTheme.typography.bodyLarge)
                }
                EchoCard {
                    Muted(t(S.CARD_DISCUSS))
                    Text(card.discussionPrompt.forLocale(t.locale), style = MaterialTheme.typography.bodyLarge)
                    Muted(t(S.CARD_DISCUSS_HELP))
                }
                Muted(t(S.CARD_DRAFT_NOTE))
            }
            if (!isChosen) {
                Column(Modifier.padding(16.dp)) {
                    PrimaryButton(t(S.CARD_CHOOSE), onClick = { vm.chooseCard(id); vm.pop() })
                }
            }
        }
    }
}
