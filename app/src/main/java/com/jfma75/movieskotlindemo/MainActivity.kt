package com.jfma75.movieskotlindemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.jfma75.movieskotlindemo.models.Movie
import com.jfma75.movieskotlindemo.screens.BuyTicketsScreen
import com.jfma75.movieskotlindemo.screens.MoviesHomeScreen
import com.jfma75.movieskotlindemo.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

var movies = listOf(
        Movie(0, "Deadpool 2", "Action", R.drawable.deadpool_2),
        Movie(1, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water),
        Movie(2, "Jurassic World", "Action", R.drawable.jurassic_world),
        Movie(3, "Tomb Raider", "Action", R.drawable.tomb_raider),
        Movie(4, "Deadpool 2", "Action", R.drawable.deadpool_2),
        Movie(5, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water),
        Movie(6, "Jurassic World", "Action", R.drawable.jurassic_world),
        Movie(7, "Tomb Raider", "Action", R.drawable.tomb_raider)
)

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var randomString : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", randomString)
        setContent {
            App()
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun App() {
    AppTheme {
        AppContent()
    }
}

@ExperimentalFoundationApi
@Composable
private fun AppContent() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            MoviesHomeScreen(onMovieClick = { movie ->
                navController.navigate("BuyTickets/${movie.id}")
            })
        }
        composable(
            route = "BuyTickets/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.LongType })) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val movieId = arguments.getLong("movieId")

            BuyTicketsScreen(
                movieId = movieId,
                upPress = { navController.navigateUp() }
            )
        }
    }
}