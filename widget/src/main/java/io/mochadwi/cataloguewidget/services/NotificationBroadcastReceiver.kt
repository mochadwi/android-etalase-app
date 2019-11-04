package io.mochadwi.cataloguewidget.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.widget.Toast

import io.mochadwi.cataloguewidget.R

import io.mochadwi.cataloguewidget.services.NotificationService.Companion.REPLY_ACTION

class NotificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (REPLY_ACTION == intent.action) {
            val message = NotificationService.getReplyMessage(intent)
            val messageId = intent.getIntExtra(KEY_MESSAGE_ID, 0)

            Toast.makeText(context, "Message ID: $messageId\nMessage: $message",
                    Toast.LENGTH_SHORT).show()

            val notifyId = intent.getIntExtra(KEY_NOTIFICATION_ID, 1)
            updateNotification(context, notifyId)
        }
    }

    private fun updateNotification(context: Context, notifyId: Int) {
        val notificationManager = NotificationManagerCompat.from(context)

        val builder = NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setContentTitle(context.getString(R.string.notif_title_sent))
                .setContentText(context.getString(R.string.notif_content_sent))

        notificationManager.notify(notifyId, builder.build())
    }

    companion object {
        private val KEY_NOTIFICATION_ID = "key_noticiation_id"
        private val KEY_MESSAGE_ID = "key_message_id"

        fun getReplyMessageIntent(context: Context, notificationId: Int, messageId: Int): Intent {
            val intent = Intent(context, NotificationBroadcastReceiver::class.java)
            intent.action = REPLY_ACTION
            intent.putExtra(KEY_NOTIFICATION_ID, notificationId)
            intent.putExtra(KEY_MESSAGE_ID, messageId)
            return intent
        }
    }
}
