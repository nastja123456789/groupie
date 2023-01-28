package ru.androidschool.intensiv.ui.movie_details

import MovieViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.api.load
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.*
import ru.androidschool.intensiv.data.dto.MovieModelDB
import ru.androidschool.intensiv.ui.feed.MovieRoomDatabase


class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private var _binding: MovieDetailsFragmentBinding? = null
    private var _searchBinding: MovieDetailsFragmentBinding? = null
    private val vm: MovieViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val searchBinding get() = _searchBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        _searchBinding = MovieDetailsFragmentBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var title = arguments?.getString("title")
        binding.detailText.text = title.toString()
        var image = arguments?.getString("image")
        binding.detailImage.load(image)
        var overview = arguments?.getString("overview")
        binding.detailDescription.text = overview.toString()
        var release = arguments?.getString("release")
        binding.ageOfRealise.text = release.toString()
        var rating = arguments?.getInt("rating")
        binding.movieRating.rating = rating!!.toFloat()
        vm.setLike(false)
        binding.likeImage.setOnClickListener {
            if (vm.hasLike.value == false) {
                binding.likeImage.setImageResource(R.drawable.ic_heart)
                vm.setLike(true)
                lifecycleScope.launch(Dispatchers.IO) {
                    MovieRoomDatabase.getDatabase(requireContext()).movieDao().insert(MovieModelDB(image!!))
                }
            } else {
                binding.likeImage.setImageResource(R.drawable.ic_like)
                vm.setLike(false)
                lifecycleScope.launch(Dispatchers.IO) {
                    MovieRoomDatabase.getDatabase(requireContext()).movieDao().delete(MovieModelDB(image!!))
                }
            }
        }
    }
}