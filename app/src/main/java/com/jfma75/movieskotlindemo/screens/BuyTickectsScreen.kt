package com.jfma75.movieskotlindemo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import java.util.*

/**
 * @sample com.jfma75.movieskotlindemo.screens.BuyTickets_Preview
 * */

var selectedDate by mutableStateOf(Date())

@ExperimentalStdlibApi
@Composable
fun BuyTicketsScreen(movieId: Long, upPress: () -> Unit) {
    val movie = movies.first { movie -> movie.id == movieId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.subtitle1,
                        color = LocalContentColor.current
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { upPress() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(Modifier.fillMaxWidth()) {
                HeaderView(movie = movie)
            }
            Row(Modifier.fillMaxWidth()) {
                CalendarView()
            }
        }
    }
}

@ExperimentalStdlibApi
@Composable
fun HeaderView(movie: Movie) {
    val colors = MaterialTheme.colors

    Column(modifier = Modifier.padding(8.dp)) {
        Box(modifier = Modifier.size(height = 180.dp, width = 120.dp)) {
            Image(painterResource(id = movie.imageId), contentDescription = "", Modifier.clip(shape = RoundedCornerShape(12.dp)), contentScale = ContentScale.Fit)
        }
    }
    Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.Start) {
        Text(text = movie.name, style = MaterialTheme.typography.h6)
        Text(text = movie.genre, style = MaterialTheme.typography.body1)

        Spacer(Modifier.height(16.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = {}, shape = RoundedCornerShape(8.dp)) {
                Text(
                    text = selectedDate.formatToViewDateDefaults("MMM dd EEEE").uppercase(),
                    style = MaterialTheme.typography.subtitle2.merge(
                        TextStyle(
                            color = colors.secondary,
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
                            color = colors.secondary,
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

@ExperimentalStdlibApi
@Composable
fun CalendarView() {
    val days = getCalendars()
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Select Date", style = MaterialTheme.typography.h5)
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

@ExperimentalStdlibApi
@Composable
fun DayButtonView(day: Date) {
    val colors = MaterialTheme.colors

    Button(
        onClick = { selectedDate = day },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = when {
                day.formatToViewDateDefaults() == selectedDate.formatToViewDateDefaults() -> { colors.primary }
                else -> { colors.secondary }
            }
        ),
        modifier = Modifier
            .size(width = 70.dp, height = 90.dp)
            .padding(0.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            Text(
                text = day.formatToViewDateDefaults("MMM").uppercase(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.overline.merge(
                    TextStyle(
                        color = if (day.onlyDate() == selectedDate.onlyDate()) {
                            colors.secondary
                        } else {
                            colors.primary
                        },
                        fontSize = 14.sp,
                        fontWeight = if (day.onlyDate() == selectedDate.onlyDate()) {
                            FontWeight.ExtraBold
                        } else {
                            FontWeight.Light
                        }
                    )
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = day.formatToViewDateDefaults("dd").uppercase(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.overline.merge(
                    TextStyle(
                        color = if (day.onlyDate() == selectedDate.onlyDate()) {
                            colors.secondary
                        } else {
                            colors.primary
                        },
                        fontSize = 18.sp,
                        fontWeight = if (day.onlyDate() == selectedDate.onlyDate()) {
                            FontWeight.ExtraBold
                        } else {
                            FontWeight.Light
                        }
                    )
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = day.formatToViewDateDefaults("EEEE").uppercase(),
                modifier = Modifier.wrapContentSize(align = Alignment.Center),
                maxLines = 1,
                style = MaterialTheme.typography.overline.merge(
                    TextStyle(
                        color = if (day.onlyDate() == selectedDate.onlyDate()) {
                            colors.secondary
                        } else {
                            colors.primary
                        },
                        fontSize = 14.sp,
                        fontWeight = if (day.onlyDate() == selectedDate.onlyDate()) {
                            FontWeight.ExtraBold
                        } else {
                            FontWeight.Light
                        }
                    )
                )
            )
        }
    }
}

@ExperimentalStdlibApi
@Preview
@Composable
fun BuyTickets_Preview() {
    MaterialTheme(colors = lightThemeColors) {
        BuyTicketsScreen(movies.first().id) { }
    }
   /* MaterialTheme(colors = darkThemeColors) {
        BuyTicketsScreen(navController, movies.first().id)
    }*/
}