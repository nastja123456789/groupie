package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.util.Log
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
        val title = arguments?.getString("title")
        binding.detailText.text = title.toString()
        val image = arguments?.getString("image")
        binding.detailImage.load(image)
        val overview = arguments?.getString("overview")
        binding.detailDescription.text = overview.toString()
        val release = arguments?.getString("release")
        binding.ageOfRealise.text = release.toString()
        val rating = arguments?.getInt("rating")
        binding.movieRating.rating = rating!!.toFloat()
        val id = arguments?.getInt("ID")
        lifecycleScope.launch(Dispatchers.Default) {
            val tf: Boolean = id?.let { it1 -> vm.exist(requireContext(), it1) } == true
            if (tf) {
                binding.likeImage.setImageResource(R.drawable.ic_heart)
            } else if (!tf) {
                binding.likeImage.setImageResource(R.drawable.ic_like)
            }
        }
        binding.likeImage.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Default) {
                val tf: Boolean = id?.let { it1 -> vm.exist(requireContext(), it1) } == true
                if (!tf) {
                    binding.likeImage.setImageResource(R.drawable.ic_heart)
                    if (id != null) {
                        vm.setImage(requireContext(), image!!, id)
                    }
                    Log.d("${tf}","insertinsert")
                } else if (tf){
                    binding.likeImage.setImageResource(R.drawable.ic_like)
                    if (id != null) {
                        vm.deleteImage(requireContext(), image!!, id)
                    }
                    Log.d("${tf}","insertinsert")
                }
            }
        }
    }
}