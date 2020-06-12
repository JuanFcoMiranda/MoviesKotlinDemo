package com.jfma75.movieskotlindemo.extensions

import androidx.compose.Model
import java.util.*


/**
 * Class defining the screens we have in the app: home, article details and interests
 */
sealed class Screen {
    object Home : Screen()
    data class BuyTickets(val movieId: Long) : Screen()
}

@Model
class MyAppStatus {
    var currentScreen: Screen = Screen.Home
    private val screens = LinkedList<Screen>().apply { push(Screen.Home) }

    fun navigateTo(destination: Screen) {
        if (currentScreen == destination) return

        currentScreen = destination
        screens.push(destination)
        canGoBack = true
    }

    fun navigateBack(): Boolean {
        return if (screens.size > 1) {
            screens.pop()
            currentScreen = screens.first
            canGoBack = screens.size > 1
            true
        } else false
    }

    var canGoBack: Boolean = false
}