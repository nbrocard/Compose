package fr.freebox.composedays.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = FreeboxRed,
    onPrimary = Color.White,
    secondary = PurpleGrey40,
    tertiary = GreyDe,
    onTertiary = Color.White,
    onBackground = Black4b,
    surface = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    primary = FreeboxRed,
    onPrimary = Color.White,
    secondary = PurpleGrey80,
    tertiary = Black36,
    onTertiary = Grey68,
    onBackground = LightGrey,
    surface = Black22,
)

private val LightExtendedColors = ExtendedColors(
    backgroundContrast = GreyF7,
    onBackgroundStroke = Color.Black,
    onBackgroundDisabled = GreyDd,
    onSurfaceStrong = Color.Black,
    onSurfaceStrokeButton = GreyDd,
    onSurfaceDisabled = GreyDd,
)

private val DarkExtendedColors = ExtendedColors(
    backgroundContrast = Black22,
    onBackgroundStroke = Color.White,
    onBackgroundDisabled = Black36,
    onSurfaceStrong = Color.White,
    onSurfaceStrokeButton = Color.White,
    onSurfaceDisabled = Black36,
)


@Composable
fun ComposeDaysTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val extendedColors = when (darkTheme) {
        true -> DarkExtendedColors
        false -> LightExtendedColors
    }

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object ExtendedTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}