package ru.androidschool.intensiv.ui.tvshows

import android.annotation.SuppressLint
import android.util.Log
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.data.vo.TVModel
import ru.androidschool.intensiv.ui.base.BasePresenter
import timber.log.Timber
import ru.androidschool.intensiv.domain.usecase.TopRatedMoviesUseCase

class TvShowsPresenter:
BasePresenter<TvShowsPresenter.FeedView>(), KoinComponent {

    private val useCase: TopRatedMoviesUseCase by inject()

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