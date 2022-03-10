package io.nexoquad.flangify.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorPalette = darkColors(
    primary = PurplePrimaryDark,
    primaryVariant = PurplePrimaryVariantDark,
    secondary = PurpleSecondaryDark,
    background = Black,
    surface = SurfaceDark,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = SurfaceContentDark,
)

private val LightColorPalette = lightColors(
    primary = PurplePrimaryLight,
    primaryVariant = PurplePrimaryVariantLight,
    secondary = PurpleSecondaryLight,
    background = White,
    surface = SurfaceLight,
    onPrimary = Black,
    onSecondary = Black,
    onBackground = Black,
    onSurface = SurfaceContentLight,
)

@Composable
fun FlangifyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val view = LocalView.current

    SideEffect {
        (view.context as Activity).window.statusBarColor = Transparent.toArgb()
        (view.context as Activity).window.navigationBarColor =
            colors.background.toArgb()

        ViewCompat.getWindowInsetsController(view)?.isAppearanceLightNavigationBars = !darkTheme
        ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !darkTheme
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}