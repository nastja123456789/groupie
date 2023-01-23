package ru.androidschool.intensiv.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.ui.feed.MovieModel
import ru.androidschool.intensiv.ui.feed.TVModel

interface MoviesRepository {
    fun getMovies(): Single<List<TVModel>>
}