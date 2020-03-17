package com.android.movie.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton



class ViewModelProviderFactory @Inject constructor(
    private val creators: MutableMap<Class<out ViewModel?>?, Provider<ViewModel?>?>?
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel?>? = creators!![modelClass]
        if (creator == null) {
            for (
            entry
            in creators.entries
            ) {
                if (modelClass.isAssignableFrom(entry.key!!)) {
                    creator = entry.value
                    break
                }
            }
        }
        requireNotNull(creator) { "unknown model class $modelClass" }
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }


}
