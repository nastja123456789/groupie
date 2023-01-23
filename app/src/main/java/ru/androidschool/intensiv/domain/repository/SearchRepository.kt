package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieMapper
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.vo.MovieModel
import ru.androidschool.intensiv.data.vo.MoviesResponse
import ru.androidschool.intensiv.data.vo.TVModel
import ru.androidschool.intensiv.ui.feed.Extension
import ru.androidschool.intensiv.ui.tvshows.Movie

class SearchRepository {
    fun searchByQuery(str: String) : List<MovieModel> {
        val def = MovieApiClient.apiClient.searchByQuery(Extension.API_KEY, Extension.language,str)
        return def.results
    }
}