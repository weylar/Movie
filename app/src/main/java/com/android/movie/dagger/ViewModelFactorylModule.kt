package com.android.movie.dagger

import androidx.lifecycle.ViewModelProvider
import com.android.movie.viewModel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {


    @Binds
    abstract fun bindViewModelFactory(
        viewModelFactory:
        ViewModelProviderFactory
    ):
            ViewModelProvider.Factory


}