package com.jfma75.movieskotlindemo.screens

import androidx.compose.material.AmbientContentColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.jfma75.movieskotlindemo.models.Movie
import com.jfma75.movieskotlindemo.movies
import com.jfma75.movieskotlindemo.theme.lightThemeColors

@Composable
fun MoviesHomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Kotlin Movies",
                        style = MaterialTheme.typography.subtitle1,
                        color = AmbientContentColor.current
                    )
                }
            )
        },
        bodyContent = {
            HomeScreenContent(navController)
        }
    )
}

@Composable
fun HomeScreenContent(navController: NavHostController) {
    ScrollableColumn {
        Column {
            movies.forEach { row ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    row.forEach { movie ->
                        MovieView(movie, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieView(movie: Movie, navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.preferredSize(width = 160.dp, height =  230.dp)) {
            Image(
                imageResource(movie.imageId),
                modifier = Modifier.clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit
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
            onClick = { navController.navigate("BuyTickets/${movie.id}L") }
        ) {
            Text(
                text = "Buy Tickets",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun NavigateBackButton(navController: NavController) {
    if (navController.previousBackStackEntry != null) {
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonConstants.defaultButtonColors(backgroundColor = LightGray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Go to Previous screen")
        }
    }
}

@Preview
@Composable
fun MoviesHomeScreen_Preview() {
    MaterialTheme(colors = lightThemeColors) {
        val navController = rememberNavController()
        MoviesHomeScreen(navController = navController)
    }
}