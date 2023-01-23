package ru.androidschool.intensiv.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieMapper
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.feed.Extension
import ru.androidschool.intensiv.ui.feed.MovieModel
import ru.androidschool.intensiv.ui.feed.TVModel

class TopRatedMoviesRemoteRepository : MoviesRepository{
    override fun getMovies(): Single<List<TVModel>> {
        return MovieApiClient.apiClient.getPopularTV(Extension.API_KEY, Extension.language).map {
            MovieMapper.toValueObject(it)
        }
    }
}