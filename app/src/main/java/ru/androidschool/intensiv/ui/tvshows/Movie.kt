package ru.androidschool.intensiv.ui.tvshows

import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.ui.feed.MovieModel

class Movie(
    private val content: MovieModel,
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.movieText).text = content.title
        viewHolder.itemView.findViewById<ImageView>(R.id.movieImage).load(content.posterPath)
    }

    override fun getLayout(): Int = R.layout.movie_detail_image_fragment

}
