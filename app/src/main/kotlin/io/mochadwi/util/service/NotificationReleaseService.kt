package io.mochadwi.util.service

import android.app.IntentService
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.mochadwi.R

import io.mochadwi.util.helper.AppHelper.Const.EXTRA_MOVIE_TITLE
import io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_RELEASE

class NotificationReleaseService : IntentService(TAG) {
    private lateinit var mNotifManager: NotificationManagerCompat

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            mNotifManager = NotificationManagerCompat.from(this)

            if (intent.hasExtra(EXTRA_MOVIE_TITLE))
                setupReleaseReminder(intent.getStringExtra(EXTRA_MOVIE_TITLE))
        }
    }

    private fun showReleaseNotification(title: String) {
        val notification = NotificationCompat.Builder(this, "$NOTIFICATION_RELEASE_ID")
                .setSmallIcon(R.drawable.ic_tmdb)
                .setLargeIcon(BitmapFactory.decodeResource(
                        resources,
                        R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setContentText(getString(R.string.message_releasereminder, title))
                .setAutoCancel(true)
                .setShowWhen(true)

        mNotifManager.notify(NOTIFICATION_RELEASE_ID, notification.build())
    }

    private fun setupReleaseReminder(title: String) {
        Log.d(TAG_MOVIE_RELEASE, "showReleaseNotification(title) executed!")
        showReleaseNotification(title)
    }

    companion object {

        const val NOTIFICATION_RELEASE_ID = 2
        private val TAG = NotificationReleaseService::class.java.simpleName
    }
}
