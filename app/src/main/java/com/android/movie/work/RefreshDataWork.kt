package com.android.movie.work

/*
* Created by Idris Aminu O. on 20-2-20
* */
import android.app.Application
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.android.movie.repository.MovieRepository
import retrofit2.HttpException
import timber.log.Timber

class RefreshDataWorker(
    appContext: Context,
    params: WorkerParameters
) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }


    override suspend fun doWork(): Result {
        val repository = MovieRepository(applicationContext as Application)
        return try {
            repository.refreshMovies()
            Result.success()
        } catch (e: HttpException) {
            Result.failure()
        }
    }
}
