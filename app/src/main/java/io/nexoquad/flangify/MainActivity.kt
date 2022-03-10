package io.nexoquad.flangify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import io.nexoquad.flangify.navigation.FlangifyNavigation
import io.nexoquad.flangify.ui.theme.FlangifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        setContent {
            FlangifyTheme {
                FlangifyNavigation()
            }
        }
    }
}