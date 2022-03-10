plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "io.nexoquad.test"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = "io.nexoquad.test"
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + mutableSetOf("-opt-in=kotlin.RequiresOptIn")
        jvmTarget = "1.8"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.composeVersion
    }
    packagingOptions {
        resources {
            excludes += mutableSetOf(
                "/META-INF/{AL2.0,LGPL2.1}", "META-INF/licenses/ASM"
            )
        }
    }
}

dependencies {
    // Jetpack Core Libraries
    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.coreSplashScreen)

    // Jetpack Lifecycle
    implementation(Dependencies.Lifecycle.lifecycleRuntime)

    // Activity
    implementation(Dependencies.Compose.activityCompose)

    // Jetpack Compose
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeUiToolingPreview)
    implementation(Dependencies.Compose.composeMaterial)
    implementation(Dependencies.Compose.navigationCompose)

    // Dagger Hilt
    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltNavigationCompose)
    kapt(Dependencies.Hilt.hiltCompiler)

    // Testing
    testImplementation(Dependencies.Testing.jUnit4)
    androidTestImplementation(Dependencies.Testing.jUnitExt)
    androidTestImplementation(Dependencies.Testing.espressoCore)
    androidTestImplementation(Dependencies.Compose.composeUiTest)
    debugImplementation(Dependencies.Compose.composeUiToolingTest)
    debugImplementation(Dependencies.Compose.composeUiTestManifest)
}