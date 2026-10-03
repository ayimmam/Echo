package org.ethioware.echo

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.ethioware.echo.ui.components.DemoBanner
import org.ethioware.echo.ui.components.SidFallbackBanner
import org.ethioware.echo.ui.i18n.LocalT
import org.ethioware.echo.ui.i18n.S
import org.ethioware.echo.ui.i18n.Translator
import org.ethioware.echo.ui.screens.CardDetailScreen
import org.ethioware.echo.ui.screens.EndScreen
import org.ethioware.echo.ui.screens.LessonDetailScreen
import org.ethioware.echo.ui.screens.LessonScreen
import org.ethioware.echo.ui.screens.LessonsScreen
import org.ethioware.echo.ui.screens.OnboardingScreen
import org.ethioware.echo.ui.screens.ReadinessScreen
import org.ethioware.echo.ui.screens.ReportScreen
import org.ethioware.echo.ui.screens.SettingsScreen
import org.ethioware.echo.ui.screens.TodayScreen
import org.ethioware.echo.ui.screens.WithdrawnScreen
import org.ethioware.echo.ui.theme.EchoTheme

@Composable
fun EchoApp(vm: EchoViewModel) {
    val translator = remember(vm.locale) { Translator(vm.locale) }
    CompositionLocalProvider(LocalT provides translator) {
        EchoTheme {
            when {
                vm.withdrawn -> WithdrawnScreen(vm)
                !vm.onboarded -> OnboardingScreen(vm)
                else -> {
                    val top = vm.stack.lastOrNull()
                    if (top == null) TabShell(vm) else PushedScreen(vm, top)
                }
            }
        }
    }
}

@Composable
private fun PushedScreen(vm: EchoViewModel, screen: Screen) {
    when (screen) {
        Screen.Readiness -> ReadinessScreen(vm)
        Screen.Running -> LessonScreen(vm)
        Screen.End -> EndScreen(vm)
        is Screen.LessonDetail -> LessonDetailScreen(vm, screen.id)
        is Screen.CardDetail -> CardDetailScreen(vm, screen.id)
    }
}

private data class TabSpec(val tab: Tab, val label: S, val icon: ImageVector)

private val tabs = listOf(
    TabSpec(Tab.TODAY, S.TAB_TODAY, Icons.Filled.Home),
    TabSpec(Tab.LESSONS, S.TAB_LESSONS, Icons.AutoMirrored.Filled.List),
    TabSpec(Tab.REPORT, S.TAB_REPORT, Icons.Filled.DateRange),
    TabSpec(Tab.SETTINGS, S.TAB_SETTINGS, Icons.Filled.Settings),
)

@Composable
private fun TabShell(vm: EchoViewModel) {
    val t = LocalT.current
    // Back from a non-home tab returns to Today before leaving the app.
    BackHandler(enabled = vm.tab != Tab.TODAY) { vm.tab = Tab.TODAY }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar {
                tabs.forEach { spec ->
                    NavigationBarItem(
                        selected = vm.tab == spec.tab,
                        onClick = { if (spec.tab == Tab.REPORT) vm.openReport() else vm.tab = spec.tab },
                        icon = { Icon(spec.icon, contentDescription = null) },
                        label = { Text(t(spec.label), style = MaterialTheme.typography.labelMedium) },
                    )
                }
            }
        },
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            DemoBanner()
            SidFallbackBanner()
            when (vm.tab) {
                Tab.TODAY -> TodayScreen(vm)
                Tab.LESSONS -> LessonsScreen(vm)
                Tab.REPORT -> ReportScreen(vm)
                Tab.SETTINGS -> SettingsScreen(vm)
            }
        }
    }
}
