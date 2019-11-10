package io.mochadwi.util.service

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_DAILY
import org.joda.time.DateTime

class NotifyDailyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        return when (DateTime().hourOfDay) {
            7 -> {
                applicationContext.startService(Intent(applicationContext, NotificationDailyService::class.java))
                Log.d(TAG_MOVIE_DAILY, "worker daily running")
                Result.success()
            }
            else -> {
                Log.d(TAG_MOVIE_DAILY, "worker retry")
                Result.retry()
            }
        }
    }

    companion object {

        private val TAG = NotifyDailyWorker::class.java.simpleName
    }
}
