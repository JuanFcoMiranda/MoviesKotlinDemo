import Versions.androidGradlePluginVersion
import Versions.hiltVersion
import Versions.kotlinVersion
import Versions.serializationPluginVersion

object Plugins {
    object Android {
        const val gradle = "com.android.tools.build:gradle:$androidGradlePluginVersion"
    }

    object Kotlin {
        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:$serializationPluginVersion"
    }

    object Google {
        object Hilt {
            object Android {
                const val gradle = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
            }
        }
    }
}