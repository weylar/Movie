package com.android.movie.work

import android.content.Context
import androidx.work.*
import javax.inject.Inject
import javax.inject.Provider

class MyWorkerFactory @Inject constructor(
    val workerFactories: Map<Class<out CoroutineWorker>,
            @JvmSuppressWildcards Provider<ChildWorkerFactory>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val foundEntry =
            workerFactories.entries.find {
                Class.forName(RefreshDataWorker::class.java.name).isAssignableFrom(it.key)
            }
        val factoryProvider = foundEntry?.value
            ?: throw IllegalArgumentException("unknown worker class name: $workerClassName")
        return factoryProvider.get().create(appContext, workerParameters)
    }
}