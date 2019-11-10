package io.mochadwi.util.service;

import android.content.Context;
import android.util.Log;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import io.mochadwi.domain.model.movie.Movie;
import io.mochadwi.domain.repository.AppRepository;

import static io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_RELEASE;
import static io.mochadwi.util.helper.AppHelper.Strings.getTimeEqualToday;

public class NotifyReleaseWorker extends Worker {

    private static final String TAG = NotifyReleaseWorker.class.getSimpleName();

    private List<Movie> mMovies = new ArrayList<>();
    private Result isSucceed = Result.success();

    private AppRepository repo;

    public NotifyReleaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public NotifyReleaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams, AppRepository repo) {
        super(context, workerParams);
        this.repo = repo;
    }

    @NonNull
    @Override
    public Result doWork() {
        if (new DateTime().getHourOfDay() == 8) {
            Log.d(TAG_MOVIE_RELEASE, "date: " + getTimeEqualToday("2018-10-10"));
            return fetchNetwork("in");
        } else {
            Log.d(TAG_MOVIE_RELEASE, "worker retry");
            return Result.retry();
        }
    }

    /**
     * Use to fetch network request using Retrofit
     * to obtain actual data from themoviedb.org
     */
    private Result fetchNetwork(String lang) {
//        Call<BaseResponse<Movie>> call = ServiceGenerator
//                .createService(TmdbClient.class, getApplicationContext())
//                .getNowPlayingMovies(BuildConfig.API_KEY, lang, "popularity.desc");
//        mMovies.clear();
//
//        Log.d(TAG_MOVIE_RELEASE, "worker fetch api");
//        if (call != null) {
//            call.enqueue(new Callback<BaseResponse<Movie>>() {
//
//                @Override
//                public void onResponse(
//                        @NonNull Call<BaseResponse<Movie>> call, @NonNull Response<BaseResponse<Movie>> response) {
//                    if (response.isSuccessful()) {
//                        if (response.body() != null) {
//                            if (mMovies.isEmpty()) {
//                                // TODO: Add data from previous stored DB as well
//
//                                for (Movie movie : response.body().getResults()) {
//
//                                    if (getTimeEqualToday(movie.getReleaseDate())) {
//                                        mMovies.add(movie);
//
//                                        Intent services = new Intent(getApplicationContext(), NotificationReleaseService.class);
//                                        services.putExtra(EXTRA_MOVIE_TITLE, movie.getOriginalTitle());
//
//                                        getApplicationContext().startService(services);
//                                        Log.d(TAG_MOVIE_RELEASE, "worker release running | " + movie.getOriginalTitle());
//
//                                        Log.d(TAG_MOVIE_RELEASE, "worker success");
//                                    }
//                                }
//
//                                isSucceed = Result.success();
//                            }
//                        }
//                    } else {
//                        // TODO: handle http error code
//                        isSucceed = Result.retry();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<BaseResponse<Movie>> call, Throwable t) {
//                    isSucceed = Result.retry();
//                }
//            });
//        }

//        Log.d(TAG_MOVIE_RELEASE, isSucceed ? "worker fetched successfully" : "worker fetched unsuccessful");
        Log.d(TAG_MOVIE_RELEASE, isSucceed + "");

        return isSucceed;
    }
}
