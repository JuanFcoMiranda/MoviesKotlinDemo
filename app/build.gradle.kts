import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
    id ("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.jfma75.movieskotlindemo"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "${JavaVersion.VERSION_11}"
        useIR = true
    }

    tasks.withType(KotlinCompile::class).configureEach {
        kotlinOptions.useIR = true
        kotlinOptions.jvmTarget = "${JavaVersion.VERSION_11}"
    }
}

dependencies {
    //implementation(fileTree("libs") { include(listOf("*.jar")) })

    implementation (Libs.Kotlin.stdlib)
    implementation (Libs.Kotlin.collectionsImmutable)

    implementation (Libs.KotlinX.Coroutines.android)
    implementation (Libs.KotlinX.Coroutines.core)

    implementation (Libs.AndroidX.appCompat)
    implementation (Libs.AndroidX.activity)
    implementation (Libs.AndroidX.core)
    implementation (Libs.AndroidX.datastore)

    implementation(Libs.KotlinX.Serialization.json)
    implementation(Libs.KotlinX.Datetime.datetime)

    implementation (Libs.AndroidX.Compose.animation)
    implementation (Libs.AndroidX.Compose.activity)
    implementation (Libs.AndroidX.Compose.foundation)
    implementation (Libs.AndroidX.Compose.iconsExtended)
    implementation (Libs.AndroidX.Compose.layout)
    implementation (Libs.AndroidX.Compose.livedata)
    implementation (Libs.AndroidX.Compose.material)
    implementation (Libs.AndroidX.Compose.navigation)
    implementation (Libs.AndroidX.Compose.ui)
    implementation (Libs.AndroidX.Compose.runtime)
    implementation (Libs.AndroidX.Compose.tooling)

    implementation (Libs.AndroidX.Lifecycle.livedata)
    implementation (Libs.AndroidX.Lifecycle.savedState)
    implementation (Libs.AndroidX.Lifecycle.viewmodel)

    implementation (Libs.Google.Android.hilt)
    kapt (Libs.Google.Android.hiltCompiler)

    testImplementation (Libs.AndroidX.Test.junit)
    androidTestImplementation (Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation (Libs.AndroidX.Test.espressoCore)
    androidTestImplementation (Libs.AndroidX.Test.navigation)
}