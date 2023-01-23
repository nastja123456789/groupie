package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.vo.MovieModel
import ru.androidschool.intensiv.data.vo.MoviesResponse
import ru.androidschool.intensiv.data.vo.TVModel
import ru.androidschool.intensiv.data.vo.TVResponse
import ru.androidschool.intensiv.ui.tvshows.Movie

object MovieMapper {
    fun toValueObject(list: TVResponse): List<TVModel> {
        return list.results
    }
}