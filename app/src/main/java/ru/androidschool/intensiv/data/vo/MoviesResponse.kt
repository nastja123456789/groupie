package ru.androidschool.intensiv.data.vo

import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.data.vo.MovieModel

data class MoviesResponse(
    var page: Int,
    var results: List<MovieModel>,
    @SerializedName("total_results")
    var totalResults: Int,
    @SerializedName("total_pages")
    var totalPages: Int
)