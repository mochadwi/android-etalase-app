package io.mochadwi.util.service

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.mochadwi.R
import io.mochadwi.util.helper.AppHelper.Const.EXTRA_MOVIE_PHOTO
import io.mochadwi.util.helper.AppHelper.Const.EXTRA_MOVIE_TITLE
import io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_RELEASE
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class NotificationReleaseService : IntentService(TAG) {
    private lateinit var mNotifManager: NotificationManagerCompat

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            mNotifManager = NotificationManagerCompat.from(this)

            val title = intent.getStringExtra(EXTRA_MOVIE_TITLE)
            val photoUrl = intent.getStringExtra(EXTRA_MOVIE_PHOTO)

            if (title.isNotBlank()) setupReleaseReminder(title, photoUrl)
        }
    }

    // TODO(mochadwi): 2019-11-12 Build group notif dialog? But need ViewModel/something to retain the data
    private fun showReleaseNotification(title: String, photoUrl: String) {
        val notification = NotificationCompat.Builder(this, "$NOTIFICATION_RELEASE_ID")
                .setSmallIcon(R.drawable.ic_tmdb)
                .setLargeIcon(getBitmapFromURL(photoUrl))
                .setContentTitle(title)
                .setContentText(getString(R.string.message_releasereminder, title))
                .setAutoCancel(true)
                .setShowWhen(true)

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */
        val CHANNEL_NAME = "dicoding channel"
        val CHANNEL_ID = "channel_01"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /* Create or update. */
            val channel = NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT)

            notification.setChannelId(CHANNEL_ID)

            mNotifManager.createNotificationChannel(channel)
        }

        mNotifManager.notify(NOTIFICATION_RELEASE_ID, notification.build())
    }

    private fun setupReleaseReminder(title: String, photoUrl: String) {
        Log.d(TAG_MOVIE_RELEASE, "showReleaseNotification(title) executed!")
        showReleaseNotification(title, photoUrl)
    }

    private fun getBitmapFromURL(src: String): Bitmap? = try {
        val url = URL(src)
        val connection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input = connection.inputStream
        val myBitmap = BitmapFactory.decodeStream(input)
        myBitmap
    } catch (e: IOException) {
        // Log exception
        BitmapFactory.decodeResource(resources, R.drawable.ic_tmdb)
    }

    companion object {

        const val NOTIFICATION_RELEASE_ID = 2
        private val TAG = NotificationReleaseService::class.java.simpleName
    }
}
