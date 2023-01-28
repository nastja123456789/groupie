package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieMapper
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.ui.feed.Extension
import ru.androidschool.intensiv.data.vo.TVModel

class TopRatedMoviesRemoteRepository : MoviesRepository {
    override fun getMovies(): Single<List<TVModel>> {
        return MovieApiClient.apiClient.getPopularTV(Extension.API_KEY, Extension.language).map {
            MovieMapper.toValueObject(it)
        }
    }
}