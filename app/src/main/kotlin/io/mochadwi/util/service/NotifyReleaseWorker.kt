package io.mochadwi.util.service

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.mochadwi.BuildConfig
import io.mochadwi.domain.model.movie.Movie
import io.mochadwi.domain.repository.AppRepository
import io.mochadwi.util.helper.AppHelper.Const.EXTRA_MOVIE_PHOTO
import io.mochadwi.util.helper.AppHelper.Const.EXTRA_MOVIE_TITLE
import io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_RELEASE
import io.mochadwi.util.helper.AppHelper.Strings.getTimeEqualToday
import org.joda.time.DateTime
import org.koin.core.KoinComponent
import org.koin.core.inject

class NotifyReleaseWorker(context: Context, workerParameters: WorkerParameters) :
        Worker(context, workerParameters), KoinComponent {

    private var mMovies = mutableListOf<Movie>()
    private var isSucceed = Result.success()
    private val repo: AppRepository by inject()

    override fun doWork(): Result =
            when (DateTime().hourOfDay) {
                8 -> {
                    Log.d(TAG_MOVIE_RELEASE, "date: " + getTimeEqualToday(DateTime.now().toString("yyyy-MM-dd")))
                    getDiscoverMovies("in")
                }
                else -> {
                    Log.d(TAG_MOVIE_RELEASE, "worker retry")
                    Result.retry()
                }
            }

    private fun getDiscoverMovies(lang: String): Result {
        val list = repo.getDiscoverMovies() ?: emptyList()
        mMovies.clear()

        when {
            list.isEmpty() -> isSucceed = Result.retry()
            else -> {
                mMovies.addAll(list.filter { getTimeEqualToday(it.releaseDate) })
                mMovies.forEach {
                    applicationContext.startService(
                            Intent(applicationContext, NotificationReleaseService::class.java).apply {
                                putExtra(EXTRA_MOVIE_TITLE, it.originalTitle)
                                putExtra(EXTRA_MOVIE_PHOTO, "${BuildConfig.IMAGE_URL}${it.posterPath}")
                            }
                    )
                }

                isSucceed = Result.success()
            }
        }

        Log.d(TAG_MOVIE_RELEASE, "$isSucceed")

        return isSucceed
    }

    companion object {

        private val TAG = NotifyReleaseWorker::class.java.simpleName
    }
}
