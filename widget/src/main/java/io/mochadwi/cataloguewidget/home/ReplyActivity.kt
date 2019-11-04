package io.mochadwi.cataloguewidget.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import io.mochadwi.cataloguewidget.R
import io.mochadwi.cataloguewidget.services.NotificationService.Companion.REPLY_ACTION

class ReplyActivity : AppCompatActivity() {
    @BindView(R.id.edit_reply)
    lateinit var mEditReply: EditText
    @BindView(R.id.button_send)
    lateinit var mSendButton: ImageButton
    private var mMessageId: Int = 0
    private var mNotifyId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reply_activity)
        ButterKnife.bind(this)
        val intent = intent

        if (REPLY_ACTION == intent.action) {
            mMessageId = intent.getIntExtra(KEY_MESSAGE_ID, 0)
            mNotifyId = intent.getIntExtra(KEY_NOTIFY_ID, 0)
        }

        mSendButton!!.setOnClickListener { sendMessage(mNotifyId, mMessageId) }
    }

    private fun sendMessage(notifyId: Int, messageId: Int) {
        updateNotification(notifyId)
        val message = mEditReply!!.text.toString().trim { it <= ' ' }
        Toast.makeText(this, "Message ID: $messageId\nMessage: $message",
                Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun updateNotification(notifyId: Int) {
        val notificationManager = NotificationManagerCompat.from(this)

        val builder = NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setContentTitle(getString(R.string.notif_title_sent))
                .setContentText(getString(R.string.notif_content_sent))

        notificationManager.notify(notifyId, builder.build())
    }

    companion object {


        private val KEY_MESSAGE_ID = "key_message_id"
        private val KEY_NOTIFY_ID = "key_notify_id"

        fun getReplyMessageIntent(context: Context, notifyId: Int, messageId: Int): Intent {
            val intent = Intent(context, ReplyActivity::class.java)
            intent.action = REPLY_ACTION
            intent.putExtra(KEY_MESSAGE_ID, messageId)
            intent.putExtra(KEY_NOTIFY_ID, notifyId)
            return intent
        }
    }
}
