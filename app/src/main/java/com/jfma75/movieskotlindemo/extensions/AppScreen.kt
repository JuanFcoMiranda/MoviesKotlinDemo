package com.jfma75.movieskotlindemo.extensions

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import com.jfma75.movieskotlindemo.AppTheme

@Composable
fun AppScreen(screenTitle: String, state: MyAppStatus, bodyContent: @Composable() () -> Unit) {
    AppTheme {
        val scaffoldState = remember { ScaffoldState() }
        Scaffold(
            scaffoldState = scaffoldState,
            topAppBar = {
                TopAppBar(
                    title = { Text(screenTitle) },
                    navigationIcon = {
                        if (state.canGoBack) {
                            IconButton(onClick = { state.navigateBack() }) {
                                Icon(Icons.Filled.ArrowBack)
                            }
                        }
                    }
                )
            },
            bodyContent = {
                Column {
                    Box(Modifier.weight(1f)) {
                        bodyContent()
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppScreen_Preview() {
    AppScreen("Test Title", MyAppStatus(), {})
}