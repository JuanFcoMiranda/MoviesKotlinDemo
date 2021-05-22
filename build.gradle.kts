// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
      repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Libs.Android.androidGradlePlugin)
        classpath(Libs.Kotlin.gradlePlugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        classpath(Libs.Google.Android.hiltAndroidGradlePlugin)
        classpath (Libs.Kotlin.serializationPlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}