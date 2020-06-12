package com.jfma75.movieskotlindemo.models

import androidx.compose.Model
import java.time.LocalDate
import java.time.LocalTime

@Model
data class TicketDate(var date: LocalDate, var time: LocalTime)