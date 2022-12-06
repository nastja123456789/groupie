package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {
    private var _binding: TvShowsFragmentBinding? = null

    private val binding get() = _binding!!

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

        adapter.add(Movie("Black Panther", R.drawable.ic_avatar))
        adapter.add(Movie("Black Widow",R.drawable.ic_avatar))
        adapter.add(Movie("Captain America", R.drawable.ic_avatar))
        adapter.add(Movie("Hulk", R.drawable.ic_avatar))
        adapter.add(Movie("Black Panther", R.drawable.ic_avatar))
        adapter.add(Movie("Black Widow",R.drawable.ic_avatar))
        adapter.add(Movie("Captain America", R.drawable.ic_avatar))
        adapter.add(Movie("Hulk", R.drawable.ic_avatar))
        adapter.add(Movie("Black Panther", R.drawable.ic_avatar))
        adapter.add(Movie("Black Widow",R.drawable.ic_avatar))
        adapter.add(Movie("Captain America", R.drawable.ic_avatar))
        adapter.add(Movie("Hulk", R.drawable.ic_avatar))

        binding.movieRV.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}