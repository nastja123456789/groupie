package ru.androidschool.intensiv.ui.feed

import android.content.ContentValues.TAG
import android.database.Observable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.FeedFragmentBinding
import ru.androidschool.intensiv.databinding.FeedHeaderBinding
import ru.androidschool.intensiv.ui.afterTextChanged
import ru.androidschool.intensiv.ui.tvshows.Movie
import timber.log.Timber
import java.util.function.BiFunction

class FeedFragment : Fragment(R.layout.feed_fragment) {

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

    private var moviesList = listOf<MainCardContainer>()
    private var moviesListRecommended = listOf<MainCardContainer>()

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
        val call = MovieApiClient.apiClient.getTopRatedMovies(API_KEY, "ru")
        call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        it ->
                    val movies = it.results
                    moviesList = listOf(
                    MainCardContainer(
                        R.string.upcoming,
                        movies.map {
                            MovieItem(it) { movie ->
                                openMovieDetails(
                                    movie
                                )
                            }
                        }.toList()
                    )
                )
                binding.moviesRecyclerView.adapter = adapter.apply { addAll(moviesList) }
                },
                {
                        error ->
                    Log.e("TAG", error.toString())
                }
            )

        val callRecommended = MovieApiClient.apiClient.getNowPlayingMovies(API_KEY, "ru")
        callRecommended
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        it ->
                    val movies = it.results
                // Передаем результат в adapter и отображаем элементы
                moviesListRecommended = listOf(
                    MainCardContainer(
                        R.string.recommended,
                        movies.map {
                            MovieItem(it) { movie ->
                                openMovieDetails(
                                    movie
                                )
                            }
                        }.toList()
                    )
                )
                binding.moviesRecyclerView.adapter = adapter.apply { addAll(moviesListRecommended) }
                },
                {
                        error ->
                    Log.e("TAG", error.toString())
                }
            )

        val obserOne = io.reactivex.Observable.just(call)
        val obserTwo = io.reactivex.Observable.just(callRecommended)
        val zipper = BiFunction<Single<MoviesResponse>, Single<MoviesResponse>, String> {
            first, second -> first.toString() + second.toString()
        }
        

        searchBinding.searchToolbar.binding.searchEditText.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }


    }

    private fun openMovieDetails(movie: MovieModel) {
        val bundle = Bundle()
        bundle.putString(KEY_TITLE, movie.title)
        bundle.putString("image",movie.posterPath)
        bundle.putString("overview", movie.overview)
        bundle.putString("release",movie.releaseDate)
        bundle.putInt("rating",movie.voteAverage!!.toInt())
        adapter.apply {
            removeAll(moviesList)
            removeAll(moviesListRecommended)
        }

        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    private fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        adapter.apply {
            removeAll(moviesList)
            removeAll(moviesListRecommended)
        }
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        searchBinding.searchToolbar.clear()
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
        const val KEY_TITLE = "title"
        const val KEY_SEARCH = "search"
        const val API_KEY = "38021fe5bd0d26b759953028cf30ab6d"
    }
}
