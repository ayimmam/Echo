package org.ethioware.echo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Teal,
    onPrimary = Color.White,
    primaryContainer = TealSoft,
    onPrimaryContainer = Color(0xFF00201C),
    secondary = Slate,
    onSecondary = Color.White,
    secondaryContainer = CloudDeep,
    onSecondaryContainer = Ink,
    tertiary = AmberInk,
    onTertiary = Color.White,
    tertiaryContainer = AmberSoft,
    onTertiaryContainer = AmberInk,
    background = Cloud,
    onBackground = Ink,
    surface = Color.White,
    onSurface = Ink,
    surfaceVariant = TealMist,
    onSurfaceVariant = InkMuted,
    outline = Color(0xFF6B7686),
    outlineVariant = CloudLine,
    error = Color(0xFFB3261E),
)

/** Fixed light brand colours, independent of the phone's dark-mode setting. */
@Composable
fun EchoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = EchoTypography,
        content = content,
    )
}
