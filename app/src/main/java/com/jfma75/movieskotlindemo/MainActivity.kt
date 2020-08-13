package com.jfma75.movieskotlindemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.jfma75.movieskotlindemo.models.Movie
import com.jfma75.movieskotlindemo.screens.BuyTicketsScreen
import com.jfma75.movieskotlindemo.screens.MoviesHomeScreen
import com.jfma75.movieskotlindemo.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

var movies = listOf(
    listOf(
        Movie(0, "Deadpool 2", "Action", R.drawable.deadpool_2),
        Movie(1, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water)
    ),
    listOf(
        Movie(2, "Jurassic World", "Action", R.drawable.jurassic_world),
        Movie(3, "Tomb Rider", "Action", R.drawable.tomb_raider)
    ),
    listOf(
        Movie(4, "Deadpool 2", "Action", R.drawable.deadpool_2),
        Movie(5, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water)
    ),
    listOf(
        Movie(6, "Jurassic World", "Action", R.drawable.jurassic_world),
        Movie(7, "Tomb Rider", "Action", R.drawable.tomb_raider)
    )
)

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val navigationViewModel by viewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(navigationViewModel)
        }
    }

    override fun onBackPressed() {
        if (!navigationViewModel.onBack()) {
            super.onBackPressed()
        }
    }
}

@Composable
fun App(viewModel: NavigationViewModel) {
    AppTheme {
        AppContent(navigationViewModel = viewModel)
    }
}

@Composable
private fun AppContent(navigationViewModel: NavigationViewModel) {
    Crossfade(navigationViewModel.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.Home -> MoviesHomeScreen(navigateTo = navigationViewModel::navigateTo)
                is Screen.BuyTickets -> BuyTicketsScreen(
                    movieId = screen.movieId,
                    onBack = { navigationViewModel.onBack() })
            }
        }
    }
}