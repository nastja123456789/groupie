package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.dto.MovieModelDB
import ru.androidschool.intensiv.data.vo.MovieModel
import ru.androidschool.intensiv.data.vo.TVModel
import ru.androidschool.intensiv.data.vo.TVResponse
import ru.androidschool.intensiv.data.vo.Movie as movie

object MovieMapper {
    fun toValueObject(list: TVResponse): List<TVModel> {
        return list.results
    }

    fun toView(movieModelDB: MovieModelDB): movie {
        return movie(
            title = movieModelDB.title,
            voteAverage = 0.0
        )
    }

    fun fromMovieDBtoMovieModel(movieModelDB: MovieModelDB):MovieModel {
        return MovieModel(
            title = movieModelDB.title,
            voteAverage = 0.0,
            overview = "",
            releaseDate = null)
    }

    fun fromMovieToMovieDB(movie: movie):MovieModelDB = MovieModelDB(
        title = movie.title.toString()
    )

    private fun MovieModelDB(title: String): MovieModelDB {
        return MovieModelDB(title)
    }

    fun fromMovieModelToMovieDB(movieModel: MovieModel): MovieModelDB = MovieModelDB(
        title = movieModel.title.toString()
    )
}