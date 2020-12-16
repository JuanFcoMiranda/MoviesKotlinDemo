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

package com.jfma75.buildsrc

import com.jfma75.buildsrc.Versions.activityVersion
import com.jfma75.buildsrc.Versions.androidGradlePluginVersion
import com.jfma75.buildsrc.Versions.appCompatVersion
import com.jfma75.buildsrc.Versions.composeVersion
import com.jfma75.buildsrc.Versions.coreKtxVersion
import com.jfma75.buildsrc.Versions.coroutinesVersion
import com.jfma75.buildsrc.Versions.dataStoreVersion
import com.jfma75.buildsrc.Versions.espressoVersion
import com.jfma75.buildsrc.Versions.hiltVersion
import com.jfma75.buildsrc.Versions.jUnitVersion
import com.jfma75.buildsrc.Versions.kotlinVersion
import com.jfma75.buildsrc.Versions.lifecycleSavedstateVersion
import com.jfma75.buildsrc.Versions.lifecycleVersion
import com.jfma75.buildsrc.Versions.navComposeVersion
import com.jfma75.buildsrc.Versions.testsVersion

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:$androidGradlePluginVersion"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:${kotlinVersion}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"
    }

    object Lifecycle {
        const val compose = "androidx.compose.runtime:runtime-livedata:$composeVersion"
        const val extensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleSavedstateVersion"
    }

    object Android {
        const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val activity = "androidx.activity:activity-ktx:$activityVersion"
        const val datastore = "androidx.datastore:datastore-core:$dataStoreVersion"

        object Compose {
            const val snapshot = ""

            const val animation = "androidx.compose.animation:animation:${composeVersion}"
            const val animationCore = "androidx.compose.animation:animation-core:${composeVersion}"
            const val compiler = "androidx.compose.compiler:compiler:${composeVersion}"
            const val foundation = "androidx.compose.foundation:foundation:${composeVersion}"
            const val iconsCore = "androidx.compose.material:material-icons-core:$composeVersion"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
            const val layout = "androidx.compose.foundation:foundation-layout:${composeVersion}"
            const val material = "androidx.compose.material:material:${composeVersion}"
            const val navigation = "androidx.navigation:navigation-compose:$navComposeVersion"
            const val runtime = "androidx.compose.runtime:runtime:${composeVersion}"
            const val tooling = "androidx.compose.ui:ui-tooling:${composeVersion}"
            const val ui = "androidx.compose.ui:ui:${composeVersion}"
            const val uiUtil = "androidx.compose.ui:ui-util:${composeVersion}"
        }

        object Test {
            const val core = "androidx.test:core:$testsVersion"
            const val rules = "androidx.test:rules:$testsVersion"

            object Ext {
                const val junit = "androidx.test.ext:junit-ktx:$jUnitVersion"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"
        }
    }
}