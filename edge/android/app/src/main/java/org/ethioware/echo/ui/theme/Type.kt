package org.ethioware.echo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Ge'ez script needs generous line height so stacked marks are not clipped; the system font
// (Noto Sans Ethiopic on almost every Android phone) is used, so nothing is bundled.
private fun style(size: Int, line: Int, weight: FontWeight = FontWeight.Normal) =
    TextStyle(fontFamily = FontFamily.Default, fontWeight = weight, fontSize = size.sp, lineHeight = line.sp)

val EchoTypography = Typography(
    headlineMedium = style(28, 38, FontWeight.SemiBold),
    headlineSmall = style(24, 34, FontWeight.SemiBold),
    titleLarge = style(22, 32, FontWeight.SemiBold),
    titleMedium = style(18, 28, FontWeight.SemiBold),
    titleSmall = style(16, 24, FontWeight.SemiBold),
    bodyLarge = style(17, 26),
    bodyMedium = style(15, 23),
    bodySmall = style(13, 20),
    labelLarge = style(16, 22, FontWeight.Medium),
    labelMedium = style(14, 20, FontWeight.Medium),
    labelSmall = style(12, 18, FontWeight.Medium),
)
