package org.ethioware.echo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.compose.BackHandler
import org.ethioware.echo.EchoViewModel
import org.ethioware.echo.domain.DemoReadiness
import org.ethioware.echo.domain.ReadinessCheck
import org.ethioware.echo.ui.components.CardTone
import org.ethioware.echo.ui.components.DemoBanner
import org.ethioware.echo.ui.components.EchoCard
import org.ethioware.echo.ui.components.LevelMeter
import org.ethioware.echo.ui.components.Muted
import org.ethioware.echo.ui.components.PrimaryButton
import org.ethioware.echo.ui.components.ScreenHeader
import org.ethioware.echo.ui.components.SecondaryButton
import org.ethioware.echo.ui.components.SidFallbackBanner
import org.ethioware.echo.ui.components.StatusChip
import org.ethioware.echo.ui.components.rememberNowMs
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S

/**
 * 30-second capture-readiness check (FEATURE_REVIEW A2). Reports mic status, clipping and gaps; it is
 * advice, not an accuracy certificate. Simulated here: the demo build opens no microphone.
 */
@Composable
fun ReadinessScreen(vm: EchoViewModel) {
    val t = LocalT.current
    val now = rememberNowMs(vm::now, 200)
    val check = vm.readiness ?: ReadinessCheck(now)
    val done = check.done(now)
    BackHandler { vm.pop() }
    Scaffold(containerColor = MaterialTheme.colorScheme.background) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            DemoBanner()
            SidFallbackBanner()
            ScreenHeader(t(S.READY_TITLE), onBack = { vm.pop() })
            Column(
                Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                EchoCard {
                    if (!done) {
                        Muted(t(S.READY_BODY))
                        LevelMeter(now, active = true, description = t(S.READY_TITLE))
                        LinearProgressIndicator(progress = { check.progress(now) }, modifier = Modifier.fillMaxWidth())
                        Text(t(S.READY_COUNTDOWN, check.remainingS(now)), style = MaterialTheme.typography.titleMedium)
                    } else {
                        Text(t(S.READY_DONE), style = MaterialTheme.typography.titleMedium)
                        StatusChip(t(S.READY_MIC_OK))
                        Text(
                            t(S.READY_CLIPPING, "%.1f%%".format(t.javaLocale, DemoReadiness.CLIPPING_FRACTION * 100)),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            t(
                                S.READY_HEARD,
                                "%.1f s".format(t.javaLocale, DemoReadiness.OBSERVED_S),
                                "%.1f s".format(t.javaLocale, DemoReadiness.MISSING_S),
                            ),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Muted(t(S.READY_ADVICE_ONLY))
                    }
                }
                EchoCard(tone = CardTone.Warm) {
                    Text(t(S.READY_TIP_TITLE), style = MaterialTheme.typography.titleSmall)
                    Text(t(S.READY_TIP), style = MaterialTheme.typography.bodyMedium)
                }
                Muted(t(S.READY_DEMO))
            }
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (done) {
                    PrimaryButton(t(S.READY_START), onClick = { vm.startLesson() })
                    SecondaryButton(t(S.READY_AGAIN), onClick = { vm.restartReadiness() })
                } else {
                    SecondaryButton(t(S.READY_FINISH_NOW), onClick = { vm.finishReadinessNow() })
                }
            }
        }
    }
}
