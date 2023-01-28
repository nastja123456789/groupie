package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.vo.MoviesResponse
import ru.androidschool.intensiv.ui.feed.Constants

class SearchRepository {
    fun search(str: String) : Single<MoviesResponse> {
        return MovieApiClient.apiClient.searchByQuery(Constants.API_KEY, Constants.language, str)
    }
}