package ru.androidschool.intensiv.data.mappers

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.ui.feed.MovieModel
import ru.androidschool.intensiv.ui.feed.MoviesResponse

object MovieMapper {
    fun toValueObject(list: MoviesResponse): List<MovieModel> {
        return list.results
    }
}