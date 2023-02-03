package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import ru.androidschool.intensiv.applySchedulers
import ru.androidschool.intensiv.domain.repository.TopRatedMoviesRemoteRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.data.vo.TVModel
import ru.androidschool.intensiv.domain.repository.MoviesRepository

class TopRatedMoviesUseCase(private val repository: MoviesRepository) {
    fun getMovies(): Single<List<TVModel>> {
        return repository.getMovies()
            .applySchedulers()
    }
}