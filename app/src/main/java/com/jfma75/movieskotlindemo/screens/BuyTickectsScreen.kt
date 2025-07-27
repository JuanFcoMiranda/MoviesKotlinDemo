package com.jfma75.movieskotlindemo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jfma75.movieskotlindemo.extensions.formatToViewDateDefaults
import com.jfma75.movieskotlindemo.extensions.onlyDate
import com.jfma75.movieskotlindemo.models.Movie
import com.jfma75.movieskotlindemo.movies
import com.jfma75.movieskotlindemo.theme.lightThemeColors
import kotlinx.coroutines.flow.merge
import java.util.*
import kotlin.collections.flatten

/**
 * @sample com.jfma75.movieskotlindemo.BuyTickets_Preview
 * */

var selectedDate by mutableStateOf(Date())

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyTicketsScreen(navController: NavController, movieId: Long?) {
    val movie = movies.first { movie -> movie.id == movieId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack(navController.graph.startDestinationId, false) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to Movies List")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            Row(Modifier.fillMaxWidth()) {
                HeaderView(movie = movie)
            }
            Row(Modifier.fillMaxWidth()) {
                CalendarView()
            }
        }
    }
}

@Composable
fun HeaderView(movie: Movie) {
    Column(modifier = Modifier.padding(8.dp)) {
        Box(modifier = Modifier.size(height = 180.dp, width = 120.dp)) {
            Image(
                painter = painterResource(id = movie.imageId),
                modifier = Modifier.clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit,
                contentDescription = "Movie Poster"
            )
        }
    }
    Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.Start) {
        Text(text = movie.name, style = MaterialTheme.typography.titleLarge)
        Text(text = movie.genre, style = MaterialTheme.typography.bodyLarge)

        Spacer(Modifier.height(16.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = {}, shape = RoundedCornerShape(8.dp)) {
                Text(
                    text = selectedDate.formatToViewDateDefaults("MMM dd EEEE").uppercase(Locale.ROOT),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Button(onClick = {}, shape = RoundedCornerShape(8.dp)) {
                Text(
                    text = "7:00 PM",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

fun getCalendars(): List<List<Date?>> {
    val now = Calendar.getInstance()

    val days = arrayOfNulls<Date>(10)
    val delta = 0 //add 2 if your week start on monday

    now.add(Calendar.DAY_OF_MONTH, delta)
    for (i in days.indices) {
        days[i] = now.time
        now.add(Calendar.DAY_OF_MONTH, 1)
    }
    return days.filter { day ->
        day != null && (day.after(Date()) || day.formatToViewDateDefaults() == Date().formatToViewDateDefaults())
    }.chunked(5)
}

@Composable
fun CalendarView() {
    val days = getCalendars()
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Select Date", style = MaterialTheme.typography.headlineSmall)
        days.forEach { row ->
            Spacer(Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                row.forEach { day ->
                    if (day != null) {
                        DayButtonView(day)
                    }
                }
            }
        }
    }
}

@Composable
fun DayButtonView(day: Date) {
    val colors = MaterialTheme.colorScheme

    Button(
        onClick = { selectedDate = day },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = when {
                day.formatToViewDateDefaults() == selectedDate.formatToViewDateDefaults() -> { colors.primary }
                else -> { colors.secondary }
            }
        ),
        modifier = Modifier.size(width = 75.dp, height = 95.dp)
            .padding(0.dp)
            .border(
                width = 1.dp,
                color = when {
                    day.onlyDate() == selectedDate.onlyDate() -> colors.secondary
                    else -> colors.primary
                },
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = day.formatToViewDateDefaults("MMM").uppercase(Locale.ROOT),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium.merge(
                    TextStyle(
                        color = when {
                            day.onlyDate() == selectedDate.onlyDate() -> colors.secondary
                            else -> colors.primary
                        },
                        fontSize = 12.sp,
                        fontWeight = when {
                            day.onlyDate() == selectedDate.onlyDate() -> FontWeight.ExtraBold
                            else -> FontWeight.Light
                        }
                    )
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = day.formatToViewDateDefaults("dd").uppercase(Locale.ROOT),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium.merge(
                    TextStyle(
                        color = when {
                            day.onlyDate() == selectedDate.onlyDate() -> colors.secondary
                            else -> colors.primary
                        },
                        fontSize = 16.sp,
                        fontWeight = when {
                            day.onlyDate() == selectedDate.onlyDate() -> FontWeight.ExtraBold
                            else -> FontWeight.Light
                        }
                    )
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = day.formatToViewDateDefaults("EEEE").uppercase(Locale.ROOT),
                modifier = Modifier.wrapContentSize(align = Alignment.Center),
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium.merge(
                    TextStyle(
                        color = when {
                            day.onlyDate() == selectedDate.onlyDate() -> colors.secondary
                            else -> colors.primary
                        },
                        fontSize = 12.sp,
                        fontWeight = when {
                            day.onlyDate() == selectedDate.onlyDate() -> FontWeight.ExtraBold
                            else -> FontWeight.Light
                        }
                    )
                )
            )
        }
    }
}

@Preview
@Composable
fun BuyTickets_Preview() {
    MaterialTheme(colorScheme = lightThemeColors) {
        val navController = rememberNavController()
        BuyTicketsScreen(navController, movies.first().id)
    }
    /*MaterialTheme(colors = darkThemeColors) {
        BuyTicketsScreen(movies.flatten().first().id, {})
    }*/
}