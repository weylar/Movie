package com.android.movie.view.popularMovies

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.movie.R
import com.android.movie.database.DatabaseMovie
import com.android.movie.util.Utility.IMAGE_URL
import com.like.LikeButton
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


@BindingAdapter("image")
fun ImageView.setPosterImage(item: DatabaseMovie?) {
    item?.let {
        Picasso.get()
            .load(IMAGE_URL + item.backdropPath)
            .into(this)


    }

}


@BindingAdapter("title")
fun TextView.setTitle(item: DatabaseMovie?) {
    item?.let {
        text = item.title
    }

}

@BindingAdapter("isFilled")
fun LikeButton.isFilled(item: DatabaseMovie?) {
    item?.let {
        isLiked = item.isFavorite
    }

}






