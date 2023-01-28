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

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment), TvShowsPresenter.FeedView {
    private var _binding: TvShowsFragmentBinding? = null
    private val binding get() = _binding!!
    private var moviesList = listOf<Movie>()
    private var adapter = GroupAdapter<GroupieViewHolder>()
    private val presenter: TvShowsPresenter by lazy {
        TvShowsPresenter(TopRatedMoviesUseCase(TopRatedMoviesRemoteRepository()))
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
        presenter.attachView(this)
        binding.movieRV.layoutManager = LinearLayoutManager(context)
        binding.movieRV.adapter = adapter.apply { addAll(listOf()) }
        presenter.getMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showMovies(movies: List<TVModel>) {
        moviesList = movies.map {
                        moviesModel -> Movie(moviesModel)
                    }.toList()
        binding.movieRV.adapter = adapter.apply { addAll(moviesList) }
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showEmptyMovies() {
        moviesList = listOf()
    }

    override fun showError() {
        TODO("Not yet implemented")
    }
}