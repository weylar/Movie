package com.android.movie.dagger.home

import androidx.lifecycle.ViewModel
import com.android.dependencyinjection.dagger.ViewModelKey
import com.android.movie.view.favoriteMovies.FavoriteMovieViewModel
import com.android.movie.view.home.HomeViewModel
import com.android.movie.view.movieDetails.MovieDetailFragmentViewModel
import com.android.movie.view.popularMovies.PopularMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularMovieViewModel::class)
    abstract fun bindPopularMovieViewModel(popularMovieViewModel: PopularMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteMovieViewModel::class)
    abstract fun bindFavoriteMovieViewModel(favoriteMovieViewModel: FavoriteMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailFragmentViewModel::class)
    abstract fun bindMovieDetailViewModel(homeViewModel: MovieDetailFragmentViewModel): ViewModel
}