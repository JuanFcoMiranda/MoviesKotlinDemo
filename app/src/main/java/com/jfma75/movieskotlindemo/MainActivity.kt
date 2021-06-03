package com.jfma75.movieskotlindemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.paging.compose.collectAsLazyPagingItems
import com.jfma75.movieskotlindemo.models.Movie
import com.jfma75.movieskotlindemo.screens.BuyTicketsScreen
import com.jfma75.movieskotlindemo.screens.MoviesHomeScreen
import com.jfma75.movieskotlindemo.theme.AppTheme
import com.jfma75.movieskotlindemo.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

var movies = listOf(
    Movie(0, "Deadpool 2", "Action", R.drawable.deadpool_2),
    Movie(1, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water),
    Movie(2, "Jurassic World", "Action", R.drawable.jurassic_world),
    Movie(3, "Tomb Raider", "Action", R.drawable.tomb_raider),
    Movie(4, "Deadpool 2", "Action", R.drawable.deadpool_2),
    Movie(5, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water),
    Movie(6, "Jurassic World", "Action", R.drawable.jurassic_world),
    Movie(7, "Tomb Raider", "Action", R.drawable.tomb_raider),
    Movie(8, "Deadpool 2", "Action", R.drawable.deadpool_2),
    Movie(9, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water),
    Movie(10, "Jurassic World", "Action", R.drawable.jurassic_world),
    Movie(11, "Tomb Raider", "Action", R.drawable.tomb_raider),
    Movie(12, "Deadpool 2", "Action", R.drawable.deadpool_2),
    Movie(13, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water),
    Movie(14, "Jurassic World", "Action", R.drawable.jurassic_world),
    Movie(15, "Tomb Raider", "Action", R.drawable.tomb_raider),
    Movie(16, "Deadpool 2", "Action", R.drawable.deadpool_2),
    Movie(17, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water),
    Movie(18, "Jurassic World", "Action", R.drawable.jurassic_world),
    Movie(19, "Tomb Raider", "Action", R.drawable.tomb_raider),
    Movie(20, "Deadpool 2", "Action", R.drawable.deadpool_2),
    Movie(21, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water),
    Movie(22, "Jurassic World", "Action", R.drawable.jurassic_world),
    Movie(23, "Tomb Raider", "Action", R.drawable.tomb_raider),
    Movie(24, "Deadpool 2", "Action", R.drawable.deadpool_2),
    Movie(25, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water),
    Movie(26, "Jurassic World", "Action", R.drawable.jurassic_world),
    Movie(27, "Tomb Raider", "Action", R.drawable.tomb_raider),
    Movie(28, "Deadpool 2", "Action", R.drawable.deadpool_2),
    Movie(29, "The Shape of Water", "Drama|Fantasy", R.drawable.shape_of_water),
    Movie(30, "Jurassic World", "Action", R.drawable.jurassic_world),
    Movie(31, "Tomb Raider", "Action", R.drawable.tomb_raider)
)

@ExperimentalStdlibApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            AppTheme {
                AppContent(viewModel)
            }
        }
    }
}

@ExperimentalStdlibApi
@ExperimentalFoundationApi
@Composable
private fun AppContent(viewModel: MainActivityViewModel) {
    val navController = rememberNavController()

    val movies = viewModel.getListData().collectAsLazyPagingItems()

    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            MoviesHomeScreen(movies) { movie ->
                navController.navigate("BuyTickets/${movie.id}")
            }
        }
        composable(
            route = "BuyTickets/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val movieId = arguments.getLong("movieId")

            BuyTicketsScreen(
                movieId = movieId,
                upPress = { navController.navigateUp() }
            )
        }
    }
}