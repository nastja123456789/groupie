package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.TVModel

interface MoviesRepository {
    fun getMovies(): Single<List<TVModel>>
}