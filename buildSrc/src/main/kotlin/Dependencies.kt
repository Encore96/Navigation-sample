object Dependencies {
    object Compose {
        const val composeVersion = "1.2.0-alpha05"
        private const val navigationVersion = "2.5.0-alpha03"
        private const val activityVersion = "1.5.0-alpha03"

        const val composeUi = "androidx.compose.ui:ui:$composeVersion"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
        const val composeMaterial = "androidx.compose.material:material:$composeVersion"
        const val composeUiTest = "androidx.compose.ui:ui-test-junit4:$composeVersion"
        const val composeUiToolingTest = "androidx.compose.ui:ui-tooling:$composeVersion"
        const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"

        const val navigationCompose = "androidx.navigation:navigation-compose:$navigationVersion"

        const val activityCompose = "androidx.activity:activity-compose:$activityVersion"
    }

    object Firebase {
        private const val firebaseBomVersion = "29.1.0"

        const val firebaseBom = "com.google.firebase:firebase-bom:$firebaseBomVersion"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
        const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    }

    object Core {
        private const val coreKtxVersion = "1.9.0-alpha01"
        private const val coreSplashScreenVersion = "1.0.0-beta01"

        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val coreSplashScreen = "androidx.core:core-splashscreen:$coreSplashScreenVersion"
    }

    object Lifecycle {
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-alpha04"
    }

    object Hilt {
        private const val hiltVersion = "2.41"
        private const val hiltNavigationComposeVersion = "1.0.0"

        const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"
        const val hiltNavigationCompose =
            "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"
    }

    object Testing {
        const val jUnit4 = "junit:junit:4.13.2"
        const val jUnitExt = "androidx.test.ext:junit:1.1.3"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
    }
}