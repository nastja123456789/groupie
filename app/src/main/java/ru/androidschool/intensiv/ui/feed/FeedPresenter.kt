package ru.androidschool.intensiv.ui.feed

import android.annotation.SuppressLint
import ru.androidschool.intensiv.domain.TopRatedMoviesUseCase
import ru.androidschool.intensiv.ui.base.BasePresenter
import timber.log.Timber

class FeedPresenter(private val useCase: TopRatedMoviesUseCase):
BasePresenter<FeedPresenter.FeedView>(){

    @SuppressLint("CheckResult")
    fun getMovies() {
        useCase.getMovies()
            .subscribe(
                {
                    view?.showMovies(it)
                },
                {
                    t ->
                    Timber.e(t, t.toString())
                    view?.showEmptyMovies()
                }
            )
    }
    interface FeedView {
        fun showMovies(movies: List<MovieModel>)
        fun showLoading()
        fun hideLoading()
        fun showEmptyMovies()
        fun showError()
    }
}