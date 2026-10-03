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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.data.AppLocale
import org.ethioware.echo.ui.components.CardTone
import org.ethioware.echo.ui.components.CheckRow
import org.ethioware.echo.ui.components.ChoiceRow
import org.ethioware.echo.ui.components.DemoBanner
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.PrimaryButton
import org.ethioware.echo.ui.components.SecondaryButton
import org.ethioware.echo.ui.components.SidFallbackBanner
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S

private const val STEPS = 3

@Composable
fun OnboardingScreen(vm: EchoViewModel) {
    val t = LocalT.current
    var agreed by rememberSaveable { mutableStateOf(false) }
    val step = vm.onboardingStep
    BackHandler(enabled = step > 0) { vm.onboardingStep = step - 1 }
    Scaffold(containerColor = MaterialTheme.colorScheme.background) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            DemoBanner()
            SidFallbackBanner()
            Column(
                Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Muted(t(S.ONB_STEP, step + 1, STEPS))
                when (step) {
                    0 -> {
                        Text(t(S.ONB_WELCOME_TITLE), style = MaterialTheme.typography.headlineMedium)
                        Text(t(S.ONB_WELCOME_BODY), style = MaterialTheme.typography.bodyLarge)
                        Text(t(S.ONB_LANG_TITLE), style = MaterialTheme.typography.titleMedium)
                        AppLocale.entries.forEach { l ->
                            ChoiceRow(l.nativeName, selected = vm.locale == l, onSelect = { vm.selectLocale(l) })
                        }
                    }
                    1 -> {
                        Text(t(S.ONB_PRIVACY_TITLE), style = MaterialTheme.typography.headlineMedium)
                        listOf(S.ONB_PRIVACY_1, S.ONB_PRIVACY_2, S.ONB_PRIVACY_3).forEach {
                            EchoCard(tone = CardTone.Calm) { Text(t(it), style = MaterialTheme.typography.bodyLarge) }
                        }
                    }
                    else -> {
                        Text(t(S.ONB_CONSENT_TITLE), style = MaterialTheme.typography.headlineMedium)
                        Text(t(S.ONB_CONSENT_BODY), style = MaterialTheme.typography.bodyLarge)
                        CheckRow(t(S.ONB_CONSENT_CHECK), checked = agreed, onChange = { agreed = it })
                        EchoCard(tone = CardTone.Warm) { Text(t(S.ONB_DEMO_NOTE), style = MaterialTheme.typography.bodyMedium) }
                    }
                }
            }
            Column(Modifier.padding(horizontal = 20.dp, vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (step < STEPS - 1) {
                    PrimaryButton(t(S.CONTINUE), onClick = { vm.onboardingStep = step + 1 })
                } else {
                    PrimaryButton(t(S.ONB_START), onClick = { vm.completeOnboarding() }, enabled = agreed)
                }
                if (step > 0) SecondaryButton(t(S.BACK), onClick = { vm.onboardingStep = step - 1 })
            }
        }
    }
}
