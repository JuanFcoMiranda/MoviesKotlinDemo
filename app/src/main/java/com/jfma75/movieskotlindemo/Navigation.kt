/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jfma75.movieskotlindemo

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.KEY_ROUTE

/**
 * Class defining the screens we have in the app: home, article details and interests
 */
/*sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object BuyTickets : Screen("BuyTickets") {
        val routeWithArg: String = "$route?movieId={arg}"
        fun withArg(arg: Long): String = routeWithArg.replace("{arg}", "$arg")
    }
}*/

/**
 * Helpers for saving and loading a [Screen] object to a [Bundle].
 *
 * This allows us to persist navigation across process death, for example caused by a long video
 * call.
 */
/*
private const val SIS_SCREEN = "sis_screen"
private const val SIS_NAME = "screen_name"
private const val SIS_POST = "post"
*/

/**
 * Convert a screen to a bundle that can be stored in [SavedStateHandle]
 */
/*private fun Screen.toBundle(): Bundle {
    return bundleOf(SIS_NAME to route.name).also {
        // add extra keys for various types here
        if (this is Screen.BuyTickets) {
            it.putLong(SIS_POST, movieId)
        }
    }
}*/

/**
 * Read a bundle stored by [Screen.toBundle] and return desired screen.
 *
 * @return the parsed [Screen]
 * @throws IllegalArgumentException if the bundle could not be parsed
 */
/*private fun Bundle.toScreen(): Screen {
    return when (ScreenName.valueOf(getStringOrThrow(SIS_NAME))) {
        ScreenName.HOME -> Screen.Home
        ScreenName.BUYTICKETS -> {
            val movieId = getLong(SIS_POST)
            Screen.BuyTickets(movieId)
        }
    }
}*/

/**
 * Throw [IllegalArgumentException] if key is not in bundle.
 *
 * @see Bundle.getString
 */
//private fun Bundle.getStringOrThrow(key: String) = requireNotNull(getString(key)) { "Missing key '$key' in $this" }

/**
 * This is expected to be replaced by the navigation component, but for now handle navigation
 * manually.
 *
 * Instantiate this ViewModel at the scope that is fully-responsible for navigation, which in this
 * application is [MainActivity].
 *
 * This app has simplified navigation; the back stack is always [Home] or [Home, dest] and more
 * levels are not allowed. To use a similar pattern with a longer back stack, use a [ModelList] to
 * hold the back stack state.
 */
/*class NavigationViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    *//**
     * Hold the current screen in an observable, restored from savedStateHandle after process
     * death.
     *
     * mutableStateOf is an observable similar to LiveData that's designed to be read by compose. It
     * supports observability via property delegate syntax as shown here.
     *//*
    var currentScreen: Screen by savedStateHandle.getMutableStateOf<Screen>(
        key = SIS_SCREEN,
        default = Screen.Home,
        save = { it.toBundle() },
        restore = { it.toScreen() }
    )
        private set // limit the writes to only inside this class.

    *//**
     * Go back (always to [Home]).
     *
     * Returns true if this call caused user-visible navigation. Will always return false
     * when [currentScreen] is [Home].
     *//*
    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreen != Screen.Home
        currentScreen = Screen.Home
        return wasHandled
    }

    *//**
     * Navigate to requested [Screen].
     *
     * If the requested screen is not [Home], it will always create a back stack with one element:
     * ([Home] -> [screen]). More back entries are not supported in this app.
     *//*
    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }
}*/