package com.jfma75.movieskotlindemo.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jfma75.movieskotlindemo.Screen
import com.jfma75.movieskotlindemo.models.Movie
import com.jfma75.movieskotlindemo.movies
import com.jfma75.movieskotlindemo.theme.lightThemeColors

@Composable
fun MoviesHomeScreen(navigateTo: (Screen) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Kotlin Movies",
                        style = MaterialTheme.typography.subtitle1,
                        color = contentColor()
                    )
                }
            )
        },
        bodyContent = {
            HomeScreenContent(navigateTo)
        }
    )
}

@Composable
private fun HomeScreenContent(navigateTo: (Screen) -> Unit) {
    ScrollableColumn {
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