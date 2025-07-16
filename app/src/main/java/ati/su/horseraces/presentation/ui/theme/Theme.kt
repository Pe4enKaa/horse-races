package ati.su.horseraces.presentation.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val lightColorPalette =
    lightColorScheme(
        primary = PrimaryColor,
        primaryContainer = PrimaryVariantColor,
        secondary = AccentColor,
        background = Color.White,
        surfaceVariant = Color.White,
        surface = lightSurface,
        onPrimary = OnPrimaryLight,
        onSecondary = OnSecondaryLight,
        onBackground = OnBackgroundLight,
        onSurface = OnSurfaceLight,
    )

@Composable
fun HorseRacesTheme(
    //darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = lightColorPalette,
        typography = latoTypography(),
        content = content,
    )
}
