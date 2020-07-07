package com.android.movie.view.topRatedMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.movie.R
import com.android.movie.database.DatabaseMovie
import com.android.movie.databinding.FragmentPopularMovieBinding
import com.android.movie.repository.MovieRepository
import com.android.movie.view.home.HomeFragmentDirections
import com.android.movie.view.popularMovies.MovieClickListener
import com.android.movie.view.popularMovies.PopularMovieAdapter
import com.android.movie.view.popularMovies.PopularMovieViewModel
import com.android.movie.viewModel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class TopRatedMovieFragment : DaggerFragment() {

    private lateinit var viewModel: PopularMovieViewModel
    private lateinit var adapter: PopularMovieAdapter
    private lateinit var  binding: FragmentPopularMovieBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_popular_movie, container, false
        )

        viewModel = ViewModelProvider(this, factory)
            .get(PopularMovieViewModel::class.java)

        val click = MovieClickListener(
            fun(result: DatabaseMovie) {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(result)
                this.findNavController().navigate(action)
            }

        )
        adapter = PopularMovieAdapter(click, viewModel)
        binding.recyclerPopularMovies.adapter = adapter
        getMovies()
        return binding.root
    }

    private fun getMovies() {
        CoroutineScope(Main).launch {
            viewModel.getMovie()?.observe(viewLifecycleOwner, Observer {
                val sorted = it.sortedByDescending { movie -> movie.voteAverage }
                Timber.i(sorted.size.toString())
                adapter.submitListOnCall(sorted)
               // binding.loading.visibility = View.GONE
            })
        }
    }




}
