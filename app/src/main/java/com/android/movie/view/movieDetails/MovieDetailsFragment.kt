package com.android.movie.view.movieDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.movie.R
import com.android.movie.database.DatabaseMovie
import com.android.movie.databinding.FragmentMovieDetailBinding
import com.android.movie.util.Utility.IMAGE_URL
import com.android.movie.viewModel.ViewModelProviderFactory
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MovieDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    @Inject
    lateinit var picasso: Picasso
    private lateinit var detailFragmentViewModel: MovieDetailFragmentViewModel
    private lateinit var binding: FragmentMovieDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailFragmentViewModel =
            ViewModelProvider(this, factory).get(MovieDetailFragmentViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_detail, container, false
        )
        binding.detailViewModel = detailFragmentViewModel
        binding.lifecycleOwner = this
        binding.backBtn.setOnClickListener { findNavController().navigateUp() }
        detailFragmentViewModel
            .getMovie(arguments!!["movie"] as DatabaseMovie)
            .observe(viewLifecycleOwner, Observer {
                bindView(binding, it)
            })


        return binding.root


    }

    private fun bindView(binding: FragmentMovieDetailBinding, it: DatabaseMovie) {
        binding.title.text = it.title
        binding.overview.text = it.overview
        binding.starButton.isLiked = it.isFavorite
        binding.starButton.click(detailFragmentViewModel, it)
        binding.rating.rating = it.voteAverage?.div(2)?.toFloat() ?: 0.toFloat()
        binding.releaseDate.append(" ${it.releaseDate}")
        picasso
            .load(IMAGE_URL + it.backdropPath)
            .error(R.drawable.image_failed)
            .into(binding.expandedImage)
    }
}


private fun LikeButton.click(
    detailFragmentViewModel: MovieDetailFragmentViewModel,
    it: DatabaseMovie
) {
    setOnLikeListener(object : OnLikeListener {
        override fun liked(likeButton: LikeButton) {
            detailFragmentViewModel.setFavorite(it.id)
        }

        override fun unLiked(likeButton: LikeButton) {
            detailFragmentViewModel.removeFavorite(it.id)
        }
    })
}