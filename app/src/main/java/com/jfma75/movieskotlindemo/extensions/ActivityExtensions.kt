package com.jfma75.movieskotlindemo.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit) =
    Intent(this, T::class.java).apply(body)

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}