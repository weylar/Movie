package com.android.movie.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters

interface ChildWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): CoroutineWorker
}