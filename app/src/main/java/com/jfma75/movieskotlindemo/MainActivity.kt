package com.jfma75.movieskotlindemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.core.*
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.jfma75.movieskotlindemo.extensions.AppScreen
import com.jfma75.movieskotlindemo.extensions.MyAppStatus
import com.jfma75.movieskotlindemo.extensions.Screen
import com.jfma75.movieskotlindemo.models.Movie
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
    private val state = MyAppStatus()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent(state)
        }

        //intent?.data?.let { state.navigateTo(Screen.Home) }
    }

    override fun onBackPressed() {
        if (!state.navigateBack()) {
            super.onBackPressed()
        }
    }
}

@Composable
fun AppContent(state: MyAppStatus) {
    Crossfade(state.currentScreen) { screen ->
        AppScreen(
            when (screen) {
                is Screen.Home -> "Kotlin Movies"
                is Screen.BuyTickets -> "Buy Tickets"
            },
            state
        ) {
            when (screen) {
                is Screen.Home -> MoviesList(state)
                is Screen.BuyTickets ->  BuyTickets(movieId = screen.movieId)
            }
        }
    }
}

@Composable
fun MoviesList(state: MyAppStatus) {
    VerticalScroller {
        Column {
            movies.forEach { row ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    row.forEach { movie ->
                        MovieView(movie, state)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieView(movie: Movie, state: MyAppStatus) {
    Column(modifier = Modifier.padding(16.dp), horizontalGravity = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.preferredSize(width = 160.dp, height =  230.dp)) {
            Image(
                asset = imageResource(movie.imageId),
                modifier = Modifier.clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.preferredHeight(8.dp))
        Text(
            text = movie.name,
            style = MaterialTheme.typography.body1
        )
        Spacer(Modifier.preferredHeight(8.dp))
        Button(
            modifier = Modifier.drawShadow(elevation = 12.dp, shape = RoundedCornerShape(8.dp), clip = true),
            onClick = {
                state.navigateTo(Screen.BuyTickets(movie.id))
            }
        ) {
            Text(
                text = "Buy Tickets",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme(colors = lightThemeColors) {
        MoviesList(MyAppStatus())
    }
}