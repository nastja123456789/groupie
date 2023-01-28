package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import ru.androidschool.intensiv.applySchedulers
import ru.androidschool.intensiv.domain.repository.TopRatedMoviesRemoteRepository
import ru.androidschool.intensiv.data.vo.TVModel

class TopRatedMoviesUseCase(private val repository: TopRatedMoviesRemoteRepository) {
    fun getMovies():Single<List<TVModel>> {
        return repository.getMovies()
            .applySchedulers()
    }
}