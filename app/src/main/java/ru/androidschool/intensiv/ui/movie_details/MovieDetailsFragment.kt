package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.*
import ru.androidschool.intensiv.databinding.ActivityMainBinding.inflate
import ru.androidschool.intensiv.ui.feed.MovieModelDB
import ru.androidschool.intensiv.ui.feed.MovieViewModel


class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private var _binding: MovieDetailsFragmentBinding? = null
    private var _searchBinding: MovieDetailsFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val searchBinding get() = _searchBinding!!
    private val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

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
        var tr = false
        binding.likeImage.setOnClickListener {
            if (tr == false) {
                binding.likeImage.setImageResource(R.drawable.ic_heart)
                tr = true
                lifecycleScope.launch {
                    viewModel.repository.insert(MovieModelDB(title!!))
                }
            } else {
                binding.likeImage.setImageResource(R.drawable.ic_like)
                tr = false
            }
        }
    }
}