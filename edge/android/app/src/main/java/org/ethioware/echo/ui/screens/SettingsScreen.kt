package org.ethioware.echo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.data.AppLocale
import org.ethioware.echo.ui.components.CardTone
import org.ethioware.echo.ui.components.ChoiceRow
import org.ethioware.echo.ui.components.ConfirmDialog
import org.ethioware.echo.ui.components.DangerButton
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.ScreenHeader
import org.ethioware.echo.ui.components.SecondaryButton
import org.ethioware.echo.ui.components.SectionTitle
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S
import org.ethioware.echo.ui.i18n.date

@Composable
fun SettingsScreen(vm: EchoViewModel) {
    val t = LocalT.current
    var askWithdraw by rememberSaveable { mutableStateOf(false) }
    var askReset by rememberSaveable { mutableStateOf(false) }

    if (askWithdraw) {
        ConfirmDialog(
            title = t(S.WITHDRAW_TITLE),
            body = t(S.WITHDRAW_BODY),
            confirmText = t(S.WITHDRAW_CONFIRM),
            onConfirm = { askWithdraw = false; vm.withdraw() },
            onDismiss = { askWithdraw = false },
            destructive = true,
        )
    }
    if (askReset) {
        ConfirmDialog(
            title = t(S.RESET_TITLE),
            body = t(S.RESET_BODY),
            confirmText = t(S.RESET_CONFIRM),
            onConfirm = { askReset = false; vm.resetDemo() },
            onDismiss = { askReset = false },
        )
    }

    Column(Modifier.verticalScroll(rememberScrollState())) {
        ScreenHeader(t(S.SETTINGS_TITLE))
        Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            EchoCard {
                Text(t(S.SET_LANGUAGE), style = MaterialTheme.typography.titleSmall)
                AppLocale.entries.forEach { l ->
                    ChoiceRow(l.nativeName, selected = vm.locale == l, onSelect = { vm.selectLocale(l) })
                }
            }
            SectionTitle(t(S.SET_DATA_TITLE))
            EchoCard {
                Text(t(S.SET_CONSENT), style = MaterialTheme.typography.titleSmall)
                vm.consentOn?.let { Muted(t(S.CONSENT_GRANTED, t.date(it))) }
                Spacer(Modifier.width(4.dp))
                Text(t(S.SET_STORAGE), style = MaterialTheme.typography.titleSmall)
                Muted(t(S.SET_STORAGE_BODY))
            }
            EchoCard {
                Row(
                    Modifier.fillMaxWidth().heightIn(min = 56.dp).toggleable(
                        value = vm.syncEnabled, role = Role.Switch, onValueChange = { vm.setSync(it) },
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(t(S.SET_SYNC), style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f))
                    Spacer(Modifier.width(12.dp))
                    Switch(checked = vm.syncEnabled, onCheckedChange = null)
                }
                Muted(t(S.SET_SYNC_HELP))
                Muted(t(S.SET_SYNC_DEMO))
            }
            EchoCard(tone = CardTone.Warm) {
                Muted(t(S.SET_WITHDRAW_HELP))
                DangerButton(t(S.SET_WITHDRAW), onClick = { askWithdraw = true })
            }
            EchoCard {
                Text(t(S.SET_ABOUT), style = MaterialTheme.typography.titleSmall)
                Muted(t(S.SET_ABOUT_BODY))
                Muted(t(S.SET_DEMO_DATE, t.date(vm.today)))
                SecondaryButton(t(S.SET_RESET), onClick = { askReset = true })
            }
            Spacer(Modifier.width(8.dp))
        }
    }
}
