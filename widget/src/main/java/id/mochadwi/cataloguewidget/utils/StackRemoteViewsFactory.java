package io.mochadwi.cataloguewidget.utils;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.mochadwi.cataloguewidget.BuildConfig;
import io.mochadwi.cataloguewidget.R;
import io.mochadwi.cataloguewidget.model.MovieModel;

import static io.mochadwi.cataloguewidget.model.MovieProvider.URI_MOVIE;
import static io.mochadwi.cataloguewidget.model.MovieProvider.fromCursorValues;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Cursor mCursor;
    private Context mContext;
    private int mAppWidgetId;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        initData();
    }

    //called on start and when notifyAppWidgetViewDataChanged is called
    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {
        if (mCursor != null) mCursor.close();
    }

    @Override
    public int getCount() {
        if (mCursor == null) return 0;
        return mCursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (mCursor == null || mCursor.getCount() == 0) return null;
        mCursor.moveToPosition(position);
        MovieModel movie = fromCursorValues(mCursor);

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        try {
            Bitmap bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load(BuildConfig.IMAGE_URL + movie.getPosterPath())
                    .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();

            rv.setImageViewBitmap(R.id.iv_widget_favouritecover, bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        rv.setTextViewText(R.id.tv_widget_favouritedate, movie.getReleaseDate());
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void initData() {
        // Get all movie info
        if (mCursor != null) mCursor.close();
        mCursor = mContext.getContentResolver().query(
                URI_MOVIE,
                null,
                null,
                null,
                null
        );
    }
}