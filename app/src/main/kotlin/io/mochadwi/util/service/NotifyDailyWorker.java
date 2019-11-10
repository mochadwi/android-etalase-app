package io.mochadwi.util.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.joda.time.DateTime;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_DAILY;

public class NotifyDailyWorker extends Worker {

    private static final String TAG = NotifyDailyWorker.class.getSimpleName();

    public NotifyDailyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (new DateTime().getHourOfDay() == 7) {
            getApplicationContext().startService(new Intent(getApplicationContext(), NotificationDailyService.class));
            Log.d(TAG_MOVIE_DAILY, "worker daily running");

            Log.d(TAG_MOVIE_DAILY, "worker success");
            return Result.success();
        } else {
            Log.d(TAG_MOVIE_DAILY, "worker retry");
            return Result.retry();
        }
    }
}
