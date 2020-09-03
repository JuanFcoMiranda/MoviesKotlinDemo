package com.jfma75.movieskotlindemo.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.jfma75.movieskotlindemo.extensions.formatToViewDateDefaults
import com.jfma75.movieskotlindemo.extensions.onlyDate
import com.jfma75.movieskotlindemo.models.Movie
import com.jfma75.movieskotlindemo.movies
import com.jfma75.movieskotlindemo.theme.lightThemeColors
import java.util.*

/**
 * @sample com.jfma75.movieskotlindemo.BuyTickets_Preview
 * */

var selectedDate by mutableStateOf(Date())

@Composable
fun BuyTicketsScreen(movieId: Long, onBack: () -> Unit) {
    val movie = movies.flatten().first { movie -> movie.id == movieId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.subtitle1,
                        color = contentColor()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        },
        bodyContent = {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(Modifier.fillMaxWidth()) {
                    HeaderView(movie = movie)
                }
                Row(Modifier.fillMaxWidth()) {
                    CalendarView()
                }
            }
        }
    )
}

@Composable
fun HeaderView(movie: Movie) {
    Column(modifier = Modifier.padding(8.dp)) {
        Box(modifier = Modifier.preferredSize(height = 180.dp, width = 120.dp)) {
            Image(
                asset = imageResource(movie.imageId),
                modifier = Modifier.clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit
            )
        }
    }
    Column(modifier = Modifier.padding(12.dp), horizontalGravity = Alignment.Start) {
        Text(text = movie.name, style = MaterialTheme.typography.h6)
        Text(text = movie.genre, style = MaterialTheme.typography.body1)

        Spacer(Modifier.preferredHeight(16.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = {}, shape = RoundedCornerShape(8.dp)) {
                Text(
                    text = selectedDate.formatToViewDateDefaults("MMM dd EEEE").toUpperCase(Locale.ROOT),
                    style = MaterialTheme.typography.subtitle2.merge(
                        TextStyle(
                            fontSize = 12.sp
                        )
                    )
                )
            }
            Button(onClick = {}, shape = RoundedCornerShape(8.dp)) {
                Text(
                    text = "7:00 PM",
                    style = MaterialTheme.typography.subtitle2.merge(
                        TextStyle(
                            fontSize = 12.sp
                        )
                    )
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
        Text(text = "Select Date", style = MaterialTheme.typography.h5)
        days.forEach { row ->
            Spacer(Modifier.preferredHeight(16.dp))
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
    val colors = MaterialTheme.colors

    Button(
        onClick = { selectedDate = day },
        shape = RoundedCornerShape(12.dp),
        elevation = 12.dp,
        backgroundColor = if (day.formatToViewDateDefaults() == selectedDate.formatToViewDateDefaults()) { colors.primary } else { colors.secondary },
        modifier = Modifier.preferredSize(width = 70.dp, height = 90.dp).padding(0.dp)
    ) {
        Column(horizontalGravity = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            Text(
                text = day.formatToViewDateDefaults("MMM").toUpperCase(Locale.ROOT),
                modifier = Modifier.gravity(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.overline.merge(
                    TextStyle(
                        color = if (day.onlyDate() == selectedDate.onlyDate()) { colors.secondary } else { colors.primary },
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            )
            Spacer(Modifier.preferredHeight(8.dp))
            Text(
                text = day.formatToViewDateDefaults("dd").toUpperCase(Locale.ROOT),
                modifier = Modifier.gravity(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.overline.merge(
                    TextStyle(
                        color = if (day.onlyDate() == selectedDate.onlyDate()) { colors.secondary } else { colors.primary },
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            )
            Spacer(Modifier.preferredHeight(8.dp))
            Text(
                text = day.formatToViewDateDefaults("EEEE").toUpperCase(Locale.ROOT),
                modifier = Modifier.wrapContentSize(align = Alignment.Center),
                maxLines = 1,
                style = MaterialTheme.typography.overline.merge(
                    TextStyle(
                        color = if (day.onlyDate() == selectedDate.onlyDate()) { colors.secondary } else { colors.primary },
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            )
        }
    }
}

@Preview
@Composable
fun BuyTickets_Preview() {
    MaterialTheme(colors = lightThemeColors) {
        BuyTicketsScreen(movies.flatten().first().id, {})
    }
    /*MaterialTheme(colors = darkThemeColors) {
        BuyTicketsScreen(movies.flatten().first().id, {})
    }*/
}