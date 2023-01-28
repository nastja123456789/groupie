package ru.androidschool.intensiv.ui.feed

import android.annotation.SuppressLint
import ru.androidschool.intensiv.data.vo.TVModel
import ru.androidschool.intensiv.ui.base.BasePresenter
import timber.log.Timber
import ru.androidschool.intensiv.domain.usecase.TopRatedMoviesUseCase

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
        fun showMovies(movies: List<TVModel>)
        fun showLoading()
        fun hideLoading()
        fun showEmptyMovies()
        fun showError()
    }
}