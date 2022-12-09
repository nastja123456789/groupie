package ru.androidschool.intensiv.ui.feed

import com.google.gson.annotations.SerializedName

data class TVResponse(
    var page: Int,
    var results: List<TVModel>,
    @SerializedName("total_pages")
    var totalPages: Int
)