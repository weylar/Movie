package com.android.movie.view.favoriteMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.movie.R
import com.android.movie.databinding.FragmentFavoriteLayoutBinding
import com.android.movie.repository.MovieRepository
import com.android.movie.view.home.HomeFragmentDirections
import com.android.movie.viewModel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class FavoriteMovieFragment : DaggerFragment() {


    private lateinit var viewModel: FavoriteMovieViewModel
    private lateinit var  binding: FragmentFavoriteLayoutBinding
    private lateinit var  adapter: FavoriteMovieAdapter

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favorite_layout, container, false
        )

        viewModel = ViewModelProvider(this, factory).get(FavoriteMovieViewModel::class.java)
        binding.favoriteViewModel = viewModel
        binding.lifecycleOwner = this
        adapter = FavoriteMovieAdapter(MovieClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it)
            this.findNavController().navigate(action)
        }, viewModel)
        binding.favoriteRecycler.adapter = adapter
        binding.favoriteRecycler.layoutManager = GridLayoutManager(context, 2)
        return binding.root


    }

    override fun onResume() {
        super.onResume()
        viewModel.favoriteMovies?.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) binding.empty.visibility = View.VISIBLE
            else binding.empty.visibility = View.GONE
            adapter.submitListOnCall(it)
            binding.loading.visibility = View.GONE
        })

    }

}