package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.vo.TVModel
import ru.androidschool.intensiv.domain.repository.TopRatedMoviesRemoteRepository
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.domain.usecase.TopRatedMoviesUseCase
import ru.androidschool.intensiv.ui.feed.*

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment), FeedPresenter.FeedView {
    private var _binding: TvShowsFragmentBinding? = null
    private val binding get() = _binding!!
    private var moviesList = listOf<Movie>()
    private val presenter: FeedPresenter by lazy {
        FeedPresenter(TopRatedMoviesUseCase(TopRatedMoviesRemoteRepository()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TvShowsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = GroupAdapter<GroupieViewHolder>()
        presenter.attachView(this)
        binding.movieRV.layoutManager = LinearLayoutManager(context)
        binding.movieRV.adapter = adapter.apply { addAll(listOf()) }
        presenter.getMovies()
        binding.movieRV.adapter = adapter.apply { addAll(moviesList) }
//        val call = MovieApiClient.apiClient.getPopularTV(Extension.API_KEY, Extension.language)
//        call
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                        it ->
//                    val movies = it.results
//                    moviesList = movies.map {
//                        moviesModel -> Movie(moviesModel)
//                    }.toList()
//                    binding.movieRV.adapter = adapter.apply { addAll(moviesList) }
//                },
//                {
//                    error ->
//                    Log.e("TAG", error.toString())
//                }
//            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showMovies(movies: List<TVModel>) {
        moviesList = movies.map {
                        moviesModel -> Movie(moviesModel)
                    }.toList()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showEmptyMovies() {
        TODO("Not yet implemented")
    }

    override fun showError() {
        TODO("Not yet implemented")
    }
}