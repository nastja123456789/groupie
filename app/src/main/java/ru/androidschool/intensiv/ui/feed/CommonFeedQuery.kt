package ru.androidschool.intensiv.ui.feed

import ru.androidschool.intensiv.data.vo.MoviesResponse

class CommonFeedQuery(ratedMovie: MoviesResponse, playMovie: MoviesResponse) {
    val rat = ratedMovie
    val play = playMovie
}