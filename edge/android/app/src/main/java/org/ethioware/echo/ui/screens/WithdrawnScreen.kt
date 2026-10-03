package org.ethioware.echo.ui.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.data.RemoteDeletion
import org.ethioware.echo.ui.components.CardTone
import org.ethioware.echo.ui.components.DemoBanner
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.PrimaryButton
import org.ethioware.echo.ui.components.SecondaryButton
import org.ethioware.echo.ui.components.SidFallbackBanner
import org.ethioware.echo.ui.components.StatusChip
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S

/** Shown after withdrawal. Local deletion is immediate; server deletion is never reported done early. */
@Composable
fun WithdrawnScreen(vm: EchoViewModel) {
    val t = LocalT.current
    Scaffold(containerColor = MaterialTheme.colorScheme.background) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            DemoBanner()
            SidFallbackBanner()
            Column(
                Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Text(t(S.WITHDRAWN_TITLE), style = MaterialTheme.typography.headlineMedium)
                Text(t(S.WITHDRAWN_BODY), style = MaterialTheme.typography.bodyLarge)
                EchoCard(tone = if (vm.remoteDeletion == RemoteDeletion.PENDING) CardTone.Warm else CardTone.Calm) {
                    when (vm.remoteDeletion) {
                        RemoteDeletion.PENDING -> {
                            StatusChip(t(S.WITHDRAWN_REMOTE_PENDING), warn = true)
                            SecondaryButton(t(S.WITHDRAWN_DEMO_ACK), onClick = { vm.acknowledgeRemoteDeletion() })
                        }
                        RemoteDeletion.CONFIRMED -> StatusChip(t(S.WITHDRAWN_REMOTE_DONE))
                        RemoteDeletion.NOT_NEEDED -> Muted(t(S.WITHDRAWN_LOCAL_ONLY))
                    }
                }
            }
            Column(Modifier.padding(20.dp)) {
                PrimaryButton(t(S.WITHDRAWN_RESTART), onClick = { vm.startAgain() })
            }
        }
    }
}
