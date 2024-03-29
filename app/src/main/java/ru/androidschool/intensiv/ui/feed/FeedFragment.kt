package ru.androidschool.intensiv.ui.feed

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.FeedFragmentBinding
import ru.androidschool.intensiv.databinding.FeedHeaderBinding
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.vo.MovieModel
import ru.androidschool.intensiv.data.vo.MoviesResponse
import ru.androidschool.intensiv.ui.afterTextChanged

class FeedFragment : Fragment(R.layout.feed_fragment){

    private var _binding: FeedFragmentBinding? = null
    private var _searchBinding: FeedHeaderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val searchBinding get() = _searchBinding!!

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    private var popularMovie = listOf<MovieModel>()
    private var ratedMovies = listOf<MovieModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        _searchBinding = FeedHeaderBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBarWaitForResult.visibility = View.VISIBLE
        binding.moviesRecyclerView.visibility = View.INVISIBLE
        val ratedMovie = MovieApiClient.apiClient.getTopRatedMovies(Constants.API_KEY,Constants.language)
        val playMovie = MovieApiClient.apiClient.getNowPlayingMovies(Constants.API_KEY, Constants.language)
        io.reactivex.Observable.zip(
            ratedMovie,
            playMovie,
            io.reactivex.functions.BiFunction<MoviesResponse, MoviesResponse, CommonFeedQuery> {
                    ratedMovie, playMovie ->
                return@BiFunction CommonFeedQuery(ratedMovie, playMovie)
            }
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    data ->
                    popularMovie = data.play.results
                    ratedMovies = data.rat.results
                    ratedMovies.let {
                        val ratedMovies  = listOf(
                            MainCardContainer(
                                R.string.upcoming,
                                ratedMovies.map { it2 ->
                                    MovieItem(it2) {movie ->
                                        val id = ratedMovies.indexOf(movie)
                                        openMovieDetails(movie, id)
                                    }
                                }.toList()
                            )
                        )
                        binding.moviesRecyclerView.adapter = adapter.apply { addAll(ratedMovies) }
                    }
                    popularMovie.let {
                        val popularMovie  = listOf(
                            MainCardContainer(
                                R.string.recommended,
                                popularMovie.map { it2 ->
                                    MovieItem(it2) {movie ->
                                        val id = ratedMovies.indexOf(movie)
                                        openMovieDetails(movie, id)
                                    }
                                }.toList()
                            )
                        )
                        binding.moviesRecyclerView.adapter = adapter.apply { addAll(popularMovie) }
                    }
                    binding.moviesRecyclerView.visibility = View.VISIBLE
                    binding.progressBarWaitForResult.visibility = View.INVISIBLE
                }, {
                    error -> Log.e("TAG", error.toString())
                }
            )
        searchBinding.searchToolbar.binding.searchEditText.afterTextChanged {
            //Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }
    }

    private fun openMovieDetails(movie: MovieModel, id: Int) {
        val bundle = Bundle()
        bundle.putInt(ID, id)
        bundle.putString(KEY_TITLE, movie.title)
        bundle.putString(IMAGE, movie.posterPath)
        bundle.putString(OVERVIEW, movie.overview)
        bundle.putString(RELEASE, movie.releaseDate)
        bundle.putInt(RATING, movie.voteAverage!!.toInt())
        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    private fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        searchBinding.searchToolbar.clear()
        adapter.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _searchBinding = null
    }

    companion object {
        const val MIN_LENGTH = 3
        const val ID = "ID"
        const val KEY_TITLE = "title"
        const val KEY_SEARCH = "search"
        const val IMAGE = "image"
        const val OVERVIEW = "overview"
        const val RELEASE = "release"
        const val RATING = "rating"
    }

}
