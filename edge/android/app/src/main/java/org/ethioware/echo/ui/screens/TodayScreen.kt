package org.ethioware.echo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.data.Mic
import org.ethioware.echo.ui.components.CardTone
import org.ethioware.echo.ui.components.ChoiceRow
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.PrimaryButton
import org.ethioware.echo.ui.components.ScreenHeader
import org.ethioware.echo.ui.components.SecondaryButton
import org.ethioware.echo.ui.components.StatusChip
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S

@Composable
fun TodayScreen(vm: EchoViewModel) {
    val t = LocalT.current
    Column(Modifier.verticalScroll(rememberScrollState())) {
        ScreenHeader(t(S.TODAY_TITLE))
        Column(Modifier.padding(horizontal = 16.dp, vertical = 4.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            EchoCard {
                Text(t(S.TODAY_LESSON_TYPE), style = MaterialTheme.typography.titleMedium)
                Muted(t(S.TODAY_FIRST_NOTE))
            }
            EchoCard {
                Text(t(S.TODAY_CLASS_SIZE), style = MaterialTheme.typography.titleSmall)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedButton(
                        onClick = { vm.changeClassSize(vm.classSize - 1) },
                        modifier = Modifier.size(52.dp).semantics { contentDescription = "-" },
                    ) { Text("−", style = MaterialTheme.typography.titleLarge) }
                    Text(
                        vm.classSize.toString(),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(88.dp),
                    )
                    OutlinedButton(
                        onClick = { vm.changeClassSize(vm.classSize + 1) },
                        modifier = Modifier.size(52.dp).semantics { contentDescription = "+" },
                    ) { Text("+", style = MaterialTheme.typography.titleLarge) }
                }
                Spacer(Modifier.size(4.dp))
                Text(t(S.TODAY_MIC), style = MaterialTheme.typography.titleSmall)
                ChoiceRow(t(S.MIC_BUILTIN), vm.mic == Mic.BUILTIN, { vm.selectMic(Mic.BUILTIN) })
                ChoiceRow(t(S.MIC_WIRED), vm.mic == Mic.WIRED, { vm.selectMic(Mic.WIRED) })
            }
            SecondaryButton(t(S.TODAY_CHECK), onClick = { vm.startReadiness() })
            PrimaryButton(t(S.TODAY_START), onClick = { vm.startLesson() })
            EchoCard(tone = CardTone.Calm) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.width(10.dp))
                    Text(t(S.PRIVACY_TITLE), style = MaterialTheme.typography.titleMedium)
                }
                Text(t(S.PRIVACY_BODY), style = MaterialTheme.typography.bodyMedium)
            }
            if (vm.syncEnabled && vm.queuedIds.isNotEmpty()) {
                StatusChip(t(S.SYNC_WAITING, vm.queuedIds.size), warn = true)
            } else {
                StatusChip(t(S.SYNC_OFF_STATUS), modifier = Modifier.fillMaxWidth())
            }
            Spacer(Modifier.size(8.dp))
        }
    }
}
