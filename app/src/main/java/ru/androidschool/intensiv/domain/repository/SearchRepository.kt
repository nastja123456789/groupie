package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.MovieModelDB
import ru.androidschool.intensiv.data.mappers.MovieMapper
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.vo.MovieModel
import ru.androidschool.intensiv.data.vo.MoviesResponse
import ru.androidschool.intensiv.ui.feed.Extension

class SearchRepository {
    fun search(str: String) : Single<MoviesResponse> {
        return MovieApiClient.apiClient.searchByQuery(Extension.API_KEY, Extension.language, str)
    }
}