package io.mochadwi.cataloguewidget.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.mochadwi.cataloguewidget.R;

import static io.mochadwi.cataloguewidget.services.NotificationService.REPLY_ACTION;

public class ReplyActivity extends AppCompatActivity {


    private static final String KEY_MESSAGE_ID = "key_message_id";
    private static final String KEY_NOTIFY_ID = "key_notify_id";
    @BindView(R.id.edit_reply) EditText mEditReply;
    @BindView(R.id.button_send) ImageButton mSendButton;
    private int mMessageId;
    private int mNotifyId;

    public static Intent getReplyMessageIntent(Context context, int notifyId, int messageId) {
        Intent intent = new Intent(context, ReplyActivity.class);
        intent.setAction(REPLY_ACTION);
        intent.putExtra(KEY_MESSAGE_ID, messageId);
        intent.putExtra(KEY_NOTIFY_ID, notifyId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reply_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        if (REPLY_ACTION.equals(intent.getAction())) {
            mMessageId = intent.getIntExtra(KEY_MESSAGE_ID, 0);
            mNotifyId = intent.getIntExtra(KEY_NOTIFY_ID, 0);
        }

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(mNotifyId, mMessageId);
            }
        });
    }

    private void sendMessage(int notifyId, int messageId) {
        updateNotification(notifyId);
        String message = mEditReply.getText().toString().trim();
        Toast.makeText(this, "Message ID: " + messageId + "\nMessage: " + message,
                Toast.LENGTH_SHORT).show();
        finish();
    }

    private void updateNotification(int notifyId) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setContentTitle(getString(R.string.notif_title_sent))
                .setContentText(getString(R.string.notif_content_sent));

        notificationManager.notify(notifyId, builder.build());
    }
}
