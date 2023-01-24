package ru.androidschool.intensiv.domain.repository

import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.vo.MovieModel
import ru.androidschool.intensiv.ui.feed.Extension

class SearchRepository {
    fun searchByQuery(str: String) : List<MovieModel> {
        val def = MovieApiClient.apiClient.searchByQuery(Extension.API_KEY, Extension.language,str)
        return def.results
    }
}