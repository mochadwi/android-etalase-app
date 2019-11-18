package io.mochadwi.util.service

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import io.mochadwi.BuildConfig
import io.mochadwi.R
import io.mochadwi.data.datasource.local.provider.FavouriteProvider.Companion.fromCursorValues
import io.mochadwi.data.datasource.local.room.FavouriteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class StackRemoteViewsFactory(private val mContext: Context, intent: Intent) : RemoteViewsService.RemoteViewsFactory, KoinComponent {

    private val favoriteDao: FavouriteDao by inject()
    private lateinit var mCursor: Cursor
    private val mAppWidgetId: Int = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)

    override fun onCreate() {
        initData()
    }

    //called on start and when notifyAppWidgetViewDataChanged is called
    override fun onDataSetChanged() {
        initData()
    }

    override fun onDestroy() {
        if (::mCursor.isInitialized) mCursor.close()
    }

    override fun getCount(): Int {
        return if (::mCursor.isInitialized.not()) 0 else mCursor.count
    }

    override fun getViewAt(position: Int): RemoteViews? {
        if (::mCursor.isInitialized.not() || mCursor.count == 0) return null

        mCursor.moveToPosition(position)
        val movie = fromCursorValues(mCursor)

        val rv = RemoteViews(mContext.packageName, R.layout.widget_item)

        try {
            val bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load(BuildConfig.IMAGE_URL + movie.posterPath!!)
                    .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get()

            rv.setImageViewBitmap(R.id.iv_widget_favouritecover, bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        rv.setTextViewText(R.id.tv_widget_favouritedate, movie.releaseDate)
        return rv
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    private fun initData() {
        // Get all movie info
        if (::mCursor.isInitialized) mCursor.close()
        mCursor = runBlocking {
            withContext(Dispatchers.IO) {
                favoriteDao.selectAll()
            }
        }
    }
}