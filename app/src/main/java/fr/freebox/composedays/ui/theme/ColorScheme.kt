package fr.freebox.composedays.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val backgroundContrast: Color = Color.Unspecified,
    val onBackgroundStroke: Color = Color.Unspecified,
    val onBackgroundDisabled: Color = Color.Unspecified,
    val onSurfaceStrong: Color = Color.Unspecified,
    val onSurfaceStrokeButton: Color = Color.Unspecified,
    val onSurfaceDisabled: Color = Color.Unspecified,
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors()
}