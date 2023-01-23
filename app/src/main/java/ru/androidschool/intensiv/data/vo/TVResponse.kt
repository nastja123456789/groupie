package ru.androidschool.intensiv.data.vo

import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.data.vo.TVModel

data class TVResponse(
    var page: Int,
    var results: List<TVModel>,
    @SerializedName("total_pages")
    var totalPages: Int
)