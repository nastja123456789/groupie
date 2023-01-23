package ru.androidschool.intensiv.domain

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.applySchedulers
import ru.androidschool.intensiv.data.repository.TopRatedMoviesRemoteRepository
import ru.androidschool.intensiv.ui.feed.MovieModel

class TopRatedMoviesUseCase(private val repository: TopRatedMoviesRemoteRepository) {
    fun getMovies():Single<List<MovieModel>> {
        return repository.getMovies()
            .applySchedulers()
    }
}