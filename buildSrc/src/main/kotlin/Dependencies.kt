import Versions.activityVersion
import Versions.androidGradlePluginVersion
import Versions.appCompatVersion
import Versions.collectionsImmutableVersion
import Versions.composeActivityVersion
import Versions.composeConstraintVersion
import Versions.composeNavigationVersion
import Versions.composePaging
import Versions.composeVersion
import Versions.coreKtxVersion
import Versions.coroutinesVersion
import Versions.daggerVersion
import Versions.dataStoreVersion
import Versions.espressoVersion
import Versions.hiltVersion
import Versions.jUnit
import Versions.jUnitVersion
import Versions.kotlinVersion
import Versions.lifecycleRuntimeVersion
import Versions.lifecycleSavedstateVersion
import Versions.lifecycleVersion
import Versions.materialVersion
import Versions.navigationVersion
//import Versions.serializationPluginVersion
import Versions.testsVersion

/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

object Libs {
    object AndroidX {
        const val core = "androidx.core:core-ktx:$coreKtxVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val activity = "androidx.activity:activity-ktx:$activityVersion"
        const val datastore = "androidx.datastore:datastore-core:$dataStoreVersion"

        object Compose {
            const val livedata = "androidx.compose.runtime:runtime-livedata:$composeVersion"

            const val activity = "androidx.activity:activity-compose:$composeActivityVersion"
            const val constraintLayout = "androidx.constraintlauout:constraintlauout-compose:$composeConstraintVersion"

            const val animation = "androidx.compose.animation:animation:$composeVersion"
            const val animationCore = "androidx.compose.animation:animation-core:$composeVersion"
            const val compiler = "androidx.compose.compiler:compiler:$composeVersion"
            const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
            const val iconsCore = "androidx.compose.material:material-icons-core:$composeVersion"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
            const val layout = "androidx.compose.foundation:foundation-layout:$composeVersion"
            const val material = "androidx.compose.material:material:$composeVersion"
            const val navigation = "androidx.navigation:navigation-compose:$composeNavigationVersion"

            const val paging = "androidx.paging:paging-compose:$composePaging"
            const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
            const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
            const val ui = "androidx.compose.ui:ui:$composeVersion"
            const val uiUtil = "androidx.compose.ui:ui-util:$composeVersion"
        }

        object Lifecycle {
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeVersion"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
            const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleSavedstateVersion"
        }

        object Test {
            const val core = "androidx.test:core:$testsVersion"
            const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"
            const val junit = "junit:junit:$jUnit"
            const val navigation = "androidx.navigation:navigation-testing:$navigationVersion"
            const val rules = "androidx.test:rules:$testsVersion"

            object Ext {
                const val junit = "androidx.test.ext:junit-ktx:$jUnitVersion"
            }
        }
    }

    object Google {
        object Android {
            const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
            const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"

            object Material {
                const val material = "com.google.android.material:material:$materialVersion"
            }
        }

        object Dagger {
            const val core = "com.google.dagger:dagger:$daggerVersion"
            const val compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
        }
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$kotlinVersion"
        const val collectionsImmutable = "org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:$collectionsImmutableVersion"
    }

    object KotlinX {
        object Coroutines {
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"
        }

        object Datetime {
            const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.2.0"
        }

        object Serialization {
            const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.0"
        }
    }
}