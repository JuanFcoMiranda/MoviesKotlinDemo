package com.jfma75.movieskotlindemo.models

import android.os.Parcelable
import androidx.compose.Model
//import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Model
@Parcelize
//@JsonClass(generateAdapter = true)
data class Movie(var id :Long, var name : String, var genre: String, var imageId: Int) : Parcelable