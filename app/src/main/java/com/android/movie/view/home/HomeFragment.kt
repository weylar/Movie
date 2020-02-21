package com.android.movie.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.movie.R
import com.android.movie.database.DatabaseMovie
import com.android.movie.databinding.FragmentHomeLayoutBinding
import com.android.movie.repository.MovieRepository
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


const val POPULAR = "Popular"
const val TOP_RATED = "Top Rated"
const val FAVORITE = "Favorites"

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeLayoutBinding
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_layout, container, false
        )

        val application = requireNotNull(this.activity).application
        val factory = HomeFragmentViewModelFactory(MovieRepository(application))
        homeFragmentViewModel =
            ViewModelProvider(this, factory).get(HomeFragmentViewModel::class.java)
        val tabLayout = binding.tabLayout
        homeViewPagerAdapter = HomeViewPagerAdapter(this)
        binding.pager.adapter = homeViewPagerAdapter
        TabLayoutMediator(tabLayout, binding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> TOP_RATED
                1 -> POPULAR
                2 -> FAVORITE
                else -> POPULAR
            }

        }.attach()
        binding.homeFragmentViewModel = homeFragmentViewModel
        binding.lifecycleOwner = this
        getMovies()
        CoroutineScope(Dispatchers.Main).launch {
            homeFragmentViewModel.getMovie()?.observe(viewLifecycleOwner,
                Observer {
                    searchClick(it)

                })
        }

        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun searchClick(it: List<DatabaseMovie>) {
        binding.searchEdit.setOnEditorActionListener { _: TextView,
                                                       actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val key = binding.searchEdit.text.toString().trim()
                if (key.isNotEmpty()) {
                    val result = key.transformToData(it)
                    result?.let { openMovie(result) }

                }
                return@setOnEditorActionListener true
            }
            false
        }

        binding.searchEdit.setOnTouchListener(View.OnTouchListener { _,
                                                                     event ->
            if (event.rawX >= binding.searchEdit.right -
                (binding.searchEdit.compoundDrawables[2].bounds.height()
                        + binding.searchEdit.compoundDrawables[2].bounds.width())
            ) {
                val key = binding.searchEdit.text.toString().trim()
                if (key.isNotEmpty()) {
                    val result = key.transformToData(it)
                    result?.let { openMovie(result) }
                }
                return@OnTouchListener true
            }
            false
        })
    }

    private fun getMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            homeFragmentViewModel.getMovie()?.observe(viewLifecycleOwner,
                Observer {
                    val adapter = ArrayAdapter(
                        context!!,
                        android.R.layout.simple_spinner_dropdown_item, it.map { name
                            ->
                            name.title
                        }
                    )
                    binding.searchEdit.setAdapter(adapter)
                    binding.searchEdit.threshold = 1

                })
        }
    }

    private fun String.transformToData(list: List<DatabaseMovie>): DatabaseMovie? {
        val result = list.filter { it.title == this }
        return if (result.isNotEmpty()) result[0] else null
    }

    private fun openMovie(key: DatabaseMovie) {
        binding.searchEdit.setText("")
        val action =
            HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(key)
        this.findNavController().navigate(action)
    }
}