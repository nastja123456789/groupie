package ru.androidschool.intensiv.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.dto.MovieModelDB
import ru.androidschool.intensiv.data.vo.MovieModel

class SearchMoviesAdapter(
    private val movies: List<MovieModel>,
    private val rowLayout: Int
) : RecyclerView.Adapter<SearchMoviesAdapter.MovieViewHolder>() {
    class MovieViewHolder(v: View):RecyclerView.ViewHolder(v) {
        internal var movieTitle: TextView = v.findViewById(R.id.title)
        internal var data: TextView = v.findViewById(R.id.subtitle)
        internal var movieDescription: TextView = v.findViewById(R.id.description)
        internal var rating: TextView = v.findViewById(R.id.rating)
        internal var cover = v.findViewById<AppCompatImageView>(R.id.cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val current = movies[position]
        holder.movieTitle.text = current.title
        holder.data.text = current.releaseDate
        holder.movieDescription.text = current.overview
        holder.rating.text = current.voteAverage!!.toString()
        Picasso.get()
            .load(current.posterPath)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.cover)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}