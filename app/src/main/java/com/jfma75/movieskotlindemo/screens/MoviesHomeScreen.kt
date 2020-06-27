package com.jfma75.movieskotlindemo.screens

import androidx.compose.Composable
import androidx.compose.remember
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
import com.jfma75.movieskotlindemo.Screen
import com.jfma75.movieskotlindemo.models.Movie
import com.jfma75.movieskotlindemo.movies
import com.jfma75.movieskotlindemo.theme.lightThemeColors

@Composable
fun MoviesHomeScreen(navigateTo: (Screen) -> Unit, scaffoldState: ScaffoldState = remember { ScaffoldState() }) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movies",
                        style = MaterialTheme.typography.subtitle1,
                        color = contentColor()
                    )
                }
            )
        },
        bodyContent = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            HomeScreenContent(navigateTo, modifier)
        }
    )
}

@Composable
private fun HomeScreenContent(navigateTo: (Screen) -> Unit, modifier: Modifier = Modifier) {
    VerticalScroller {
        Column {
            movies.forEach { row ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    row.forEach { movie ->
                        MovieView(movie, navigateTo)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieView(movie: Movie, navigateTo: (Screen) -> Unit) {
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
            onClick = { navigateTo(Screen.BuyTickets(movie.id)) }
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
fun MoviesHomeScreen_Preview() {
    MaterialTheme(colors = lightThemeColors) {
        MoviesHomeScreen({})
    }
}