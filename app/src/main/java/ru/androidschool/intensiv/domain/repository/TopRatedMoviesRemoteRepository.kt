package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.data.mappers.MovieMapper
import ru.androidschool.intensiv.data.network.MovieApiInterface
import ru.androidschool.intensiv.ui.feed.Constants
import ru.androidschool.intensiv.data.vo.TVModel

class TopRatedMoviesRemoteRepository : MoviesRepository, KoinComponent {
    private val apiClient : MovieApiInterface by inject()

    override fun getMovies(): Single<List<TVModel>> {
        return apiClient.getPopularTV(Constants.API_KEY, Constants.language).map {
            MovieMapper.toValueObject(it)
        }
    }
}