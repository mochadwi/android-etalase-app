package io.mochadwi.util.service

import android.app.IntentService
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat

import androidx.core.app.NotificationManagerCompat
import io.mochadwi.R

import io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_DAILY

class NotificationDailyService : IntentService(TAG) {
    private lateinit var mNotifManager: NotificationManagerCompat

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            mNotifManager = NotificationManagerCompat.from(this)
            setupDailyReminder()
        }
    }

    private fun showDailyNotification() {
        val notification = NotificationCompat
                .Builder(this, "$NOTIFICATION_DAILY_ID")
                .setSmallIcon(R.drawable.ic_tmdb)
                .setLargeIcon(BitmapFactory.decodeResource(
                        resources,
                        R.drawable.ic_tmdb))
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(
                        R.string.message_dailyreminder,
                        getString(R.string.app_name)))
                .setAutoCancel(true)
                .setShowWhen(true)

        mNotifManager.notify(NOTIFICATION_DAILY_ID, notification.build());
    }

    private fun setupDailyReminder() {
        Log.d(TAG_MOVIE_DAILY, "showDailyNotification() executed!")
        showDailyNotification()
    }

    companion object {

        const val NOTIFICATION_DAILY_ID = 1
        private val TAG = NotificationDailyService::class.java.simpleName
    }
}
