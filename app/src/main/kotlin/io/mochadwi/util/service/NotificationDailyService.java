package io.mochadwi.util.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;

import static io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_DAILY;

public class NotificationDailyService extends IntentService {

    public static final int NOTIFICATION_DAILY_ID = 1;
    private static final String TAG = NotificationDailyService.class.getSimpleName();
    private NotificationManagerCompat mNotifManager;

    public NotificationDailyService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            mNotifManager = NotificationManagerCompat.from(this);

            setupDailyReminder();
        }
    }

    private void showDailyNotification() {
//        NotificationCompat.Builder notification = new NotificationCompat
//                .Builder(this)
//                .setSmallIcon(R.drawable.dicoding_logo_full)
//                .setLargeIcon(BitmapFactory.decodeResource(
//                        getResources(),
//                        R.mipmap.ic_launcher))
//                .setContentTitle(getString(R.string.app_name))
//                .setContentText(getString(
//                        R.string.message_dailyreminder,
//                        getString(R.string.app_name)))
//                .setAutoCancel(true)
//                .setShowWhen(true);

//        mNotifManager.notify(NOTIFICATION_DAILY_ID, notification.build());
    }

    protected void setupDailyReminder() {
        Log.d(TAG_MOVIE_DAILY, "showDailyNotification() executed!");
        showDailyNotification();
    }
}
