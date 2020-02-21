package com.android.movie.view.favoriteMovies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.movie.database.DatabaseMovie
import com.android.movie.databinding.FavoriteMovieListItemBinding
import com.android.movie.model.Result
import com.like.LikeButton
import com.like.OnLikeListener


class FavoriteMovieAdapter(private val clickListener: MovieClickListener,
                           private val viewModel: FavoriteMovieFragmentViewModel) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(MovieDiffCallback()) {


    fun submitListOnCall(list: List<DatabaseMovie>) {
        submitList(list.map { DataItem.MovieItem(it) })

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val result = getItem(position) as DataItem.MovieItem
                holder.bind(viewModel, clickListener, result.movie)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(private val binding: FavoriteMovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: FavoriteMovieFragmentViewModel,
                 clickListener: MovieClickListener, item: DatabaseMovie) {
            binding.result = item
            binding.starButton.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton) {
                }
                override fun unLiked(likeButton: LikeButton) {
                    viewModel.removeFavorite(item.id)

                }
            })
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteMovieListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class MovieDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class MovieClickListener(val clickListener: (movie: DatabaseMovie) -> Unit) {
    fun onClick(movie: DatabaseMovie) = clickListener(movie)
}

sealed class DataItem {

    data class MovieItem(val movie: DatabaseMovie) : DataItem() {
        override val id = movie.id
    }

    abstract val id: Long
}
