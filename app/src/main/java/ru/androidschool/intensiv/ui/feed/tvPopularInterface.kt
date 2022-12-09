package ru.androidschool.intensiv.ui.feed

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface tvPopularInterface {
    @GET("movie/popular")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : Call<MoviesResponse>
}