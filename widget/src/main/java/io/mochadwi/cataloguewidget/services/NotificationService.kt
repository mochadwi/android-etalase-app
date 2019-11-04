package io.mochadwi.cataloguewidget.services

import android.app.IntentService
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.app.RemoteInput
import io.mochadwi.cataloguewidget.R
import io.mochadwi.cataloguewidget.home.ReplyActivity

class NotificationService : IntentService("NotificationService") {

    private var mNotificationId: Int = 0
    private var mMessageId: Int = 0

    private val replyPendingIntent: PendingIntent
        get() {
            val intent: Intent
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent = NotificationBroadcastReceiver.getReplyMessageIntent(this, mNotificationId, mMessageId)
                return PendingIntent.getBroadcast(applicationContext, 100, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT)
            } else {
                intent = ReplyActivity.getReplyMessageIntent(this, mNotificationId, mMessageId)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                return PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            }
        }

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            showNotification()
        }
    }

    private fun showNotification() {
        mNotificationId = 1
        mMessageId = 123

        val replyLabel = getString(R.string.notif_action_reply)
        val remoteInput = RemoteInput.Builder(KEY_REPLY)
                .setLabel(replyLabel)
                .build()

        val replyAction = NotificationCompat.Action.Builder(
                android.R.drawable.ic_menu_send, replyLabel, replyPendingIntent)
                .addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(true)
                .build()
        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setContentTitle(getString(R.string.notif_title))
                .setContentText(getString(R.string.notif_content))
                .setShowWhen(true)
                .addAction(replyAction)

        val mNotificationManager = NotificationManagerCompat.from(this)
        mNotificationManager.notify(mNotificationId, mBuilder.build())
    }

    companion object {
        const val REPLY_ACTION = "io.mochadwi.notification.directreply.REPLY_ACTION"
        private val KEY_REPLY = "key_reply_message"


        fun getReplyMessage(intent: Intent): CharSequence? {
            val remoteInput = RemoteInput.getResultsFromIntent(intent)
            return remoteInput?.getCharSequence(KEY_REPLY)
        }
    }
}

