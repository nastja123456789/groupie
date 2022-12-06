package ru.androidschool.intensiv.ui.movie_details

import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import ru.androidschool.intensiv.R

class Movie(
    val text: String,
    val image: Int
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.movieText).text = text
        viewHolder.itemView.findViewById<ImageView>(R.id.movieImage).load(image)
    }

    override fun getLayout(): Int = R.layout.movie_detail_image_fragment

}
