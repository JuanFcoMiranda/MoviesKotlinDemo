plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.safeArgs)
    alias(libs.plugins.compose.compiler)
}

android {
    compileSdk = 36
    namespace = "com.jfma75.movieskotlindemo"

    defaultConfig {
        applicationId = "com.jfma75.movieskotlindemo"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.jfma75.mychampionsleaguecomposeroom.runners.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE-notice.md}"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.material)

    // Coroutines
    implementation(libs.kotlin.coroutines)

    // Lifecycle
    implementation(libs.lifecycle.runtime)

    // Compose
    implementation(platform(libs.compose.bom))

    implementation(libs.compose.activity)
    implementation(libs.compose.animation)
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundation.layout)

    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.core)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.material.ripple)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    implementation(libs.navigation.compose)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)

    implementation(libs.androidx.ui.test.junit4.android)

    //ksp(libs.compose.compiler)
    ksp(libs.lifecycle.compiler)

//    implementation Libs.AndroidX.datastore

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    // Tests
    // jUnit
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit)

    testImplementation(libs.kotlin.test.io.runner.junit5)
    //testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)

    androidTestImplementation(libs.compose.ui.test.junit4)
    // Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    debugImplementation(libs.compose.ui.test.manifest)
    testImplementation(libs.test.ext.junit)

    androidTestImplementation(libs.mockweb.server)

    // jUnit 5
    testImplementation(libs.junit5)
    testRuntimeOnly(libs.junit5.engine)
    testImplementation(libs.junit5.params)
    testRuntimeOnly(libs.junit5.vintage)

    testImplementation(libs.turbine)
    androidTestImplementation(libs.turbine)

    // Navigation testing
    androidTestImplementation (libs.navigation.testing)

    // UI Tests
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.test.rules)

    // MOCKK
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.mockk.agent)

    // Espresso
    testImplementation(libs.espresso.core)
    androidTestImplementation(libs.espresso.contrib)
    androidTestImplementation(libs.espresso.intents)

    // Hilt
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)

    // Compose testing
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}