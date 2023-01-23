package ru.androidschool.intensiv.ui.search

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.FeedHeaderBinding
import ru.androidschool.intensiv.databinding.FragmentSearchBinding
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.ui.feed.Extension
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_SEARCH
import ru.androidschool.intensiv.data.vo.MovieModel
import ru.androidschool.intensiv.domain.repository.SearchRepository
import java.util.concurrent.TimeUnit

class SearchFragment() : androidx.fragment.app.Fragment(R.layout.fragment_search), Parcelable {

    private var _binding: FragmentSearchBinding? = null
    private var _searchBinding: FeedHeaderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val searchBinding get() = _searchBinding!!

    private lateinit var searchViewModel: SearchFragmentViewModel

    val editText: EditText by lazy {
        searchBinding.searchToolbar.findViewById(R.id.search_edit_text)
    }

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        _searchBinding = FeedHeaderBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchTerm = requireArguments().getString(KEY_SEARCH)
        val searchModelFactory = SearchFragmentModelFactory(SearchRepository())
        searchViewModel = ViewModelProvider(this, searchModelFactory).get(SearchFragmentViewModel::class.java)

        searchBinding.searchToolbar.setText(searchTerm)
        searchBinding
            .searchToolbar
            .onTextChangeObservable
            .map { it.trim() }
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter { it.isNotEmpty() }
//            .flatMapSingle { it -> searchViewModel.searchByQuery(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                searchViewModel.searchMoviesLiveData.observe(viewLifecycleOwner) { list ->
                    setMovies(list)
                }
                Log.d("Tag", it.toString())
            }, {
                Log.e("Tag", it.toString())
            })
    }

    fun setMovies(movies: List<MovieModel>) {
        binding.moviesRecyclerView.adapter = SearchMoviesAdapter(movies, R.layout.list_item_movie)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _searchBinding = null
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchFragment> {
        override fun createFromParcel(parcel: Parcel): SearchFragment {
            return SearchFragment(parcel)
        }

        override fun newArray(size: Int): Array<SearchFragment?> {
            return arrayOfNulls(size)
        }
    }
}

