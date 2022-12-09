package ru.androidschool.intensiv.ui.tvshows

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import retrofit2.Call
import retrofit2.Response
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.ui.feed.*

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {
    private var _binding: TvShowsFragmentBinding? = null
    private val binding get() = _binding!!
    private var moviesList = listOf<Movie>()

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

        val call = MovieApiClient.apiClient.getPopularTV(FeedFragment.API_KEY, "ru")
        call.enqueue(object : retrofit2.Callback<TVResponse> {
            override fun onResponse(
                call: Call<TVResponse>,
                response: Response<TVResponse>
            ) {
                val movies = response.body()!!.results
                // Передаем результат в adapter и отображаем элементы
                moviesList = movies.map {
                    movieModel -> Movie(movieModel)
                }.toList()
                binding.movieRV.adapter = adapter.apply { addAll(moviesList) }
            }
            override fun onFailure(call: Call<TVResponse>, t: Throwable) {
                // Log error here since request failed
                Log.e(ContentValues.TAG, t.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}