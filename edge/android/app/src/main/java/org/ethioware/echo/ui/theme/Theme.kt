package org.ethioware.echo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
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

private val DarkColors = darkColorScheme(
    primary = TealNight,
    onPrimary = Color(0xFF00201C),
    primaryContainer = TealNightSoft,
    onPrimaryContainer = TealSoft,
    secondary = Color(0xFF9DB9D8),
    onSecondary = Color(0xFF0E2236),
    secondaryContainer = Color(0xFF263447),
    onSecondaryContainer = InkNight,
    tertiary = Amber,
    onTertiary = Color(0xFF2B1D00),
    tertiaryContainer = AmberNightSoft,
    onTertiaryContainer = Color(0xFFFFE2B0),
    background = CloudNight,
    onBackground = InkNight,
    surface = SurfaceNight,
    onSurface = InkNight,
    surfaceVariant = Color(0xFF222A36),
    onSurfaceVariant = InkMutedNight,
    outline = Color(0xFF8793A4),
    outlineVariant = LineNight,
    error = Color(0xFFF2B8B5),
)

/** Fixed brand colours (no wallpaper-derived dynamic colour) so the app looks the same on every phone. */
@Composable
fun EchoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = EchoTypography,
        content = content,
    )
}
