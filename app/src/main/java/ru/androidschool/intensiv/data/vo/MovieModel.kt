package ru.androidschool.intensiv.data.vo

import com.google.gson.annotations.SerializedName

class MovieModel(

    @SerializedName("overview")
    var overview: String?,

    @SerializedName("release_date")
    var releaseDate: String?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("vote_average")
    var voteAverage: Double?
) {
    @SerializedName("poster_path")
    var posterPath: String?= null
    get() =
        "https://image.tmdb.org/t/p/w500$field"
}