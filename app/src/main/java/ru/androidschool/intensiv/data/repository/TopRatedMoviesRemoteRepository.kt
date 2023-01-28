package ru.androidschool.intensiv.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieMapper
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.feed.Extension
import ru.androidschool.intensiv.ui.feed.MovieModel

class TopRatedMoviesRemoteRepository  {
    fun getMovies(): Single<List<MovieModel>> {
        return MovieApiClient.apiClient.getTopRatedMovies(Extension.API_KEY, Extension.language).map {
            MovieMapper.toValueObject(it)
        }
    }
}