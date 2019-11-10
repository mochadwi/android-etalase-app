package io.mochadwi.util.service;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import io.mochadwi.R;

import static io.mochadwi.util.helper.AppHelper.Const.EXTRA_MOVIE_TITLE;
import static io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_RELEASE;

public class NotificationReleaseService extends IntentService {

    public static final int NOTIFICATION_RELEASE_ID = 2;
    private static final String TAG = NotificationReleaseService.class.getSimpleName();
    private NotificationManagerCompat mNotifManager;

    public NotificationReleaseService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            mNotifManager = NotificationManagerCompat.from(this);

            if (intent.hasExtra(EXTRA_MOVIE_TITLE))
                setupReleaseReminder(intent.getStringExtra(EXTRA_MOVIE_TITLE));
        }
    }

    private void showReleaseNotification(String title) {
        NotificationCompat.Builder notification = new NotificationCompat
                .Builder(this)
                .setSmallIcon(R.drawable.ic_tmdb)
                .setLargeIcon(BitmapFactory.decodeResource(
                        getResources(),
                        R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setContentText(getString(R.string.message_releasereminder, title))
                .setAutoCancel(true)
                .setShowWhen(true);

        mNotifManager.notify(NOTIFICATION_RELEASE_ID, notification.build());
    }

    protected void setupReleaseReminder(String title) {
        Log.d(TAG_MOVIE_RELEASE, "showReleaseNotification(title) executed!");
        showReleaseNotification(title);
    }
}
