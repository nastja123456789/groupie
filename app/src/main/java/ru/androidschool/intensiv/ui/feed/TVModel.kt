package ru.androidschool.intensiv.ui.feed

import com.google.gson.annotations.SerializedName

data class TVModel(
    @SerializedName("name")
    val name: String,
) {
    @SerializedName("poster_path")
    var posterPath: String?= null
        get() =
            "https://image.tmdb.org/t/p/w500$field"
}