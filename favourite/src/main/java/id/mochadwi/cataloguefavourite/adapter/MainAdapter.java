package id.mochadwi.cataloguefavourite.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mochadwi.cataloguefavourite.BuildConfig;
import id.mochadwi.cataloguefavourite.GlideApp;
import id.mochadwi.cataloguefavourite.R;
import id.mochadwi.cataloguefavourite.model.MovieModel;

import static id.mochadwi.cataloguefavourite.model.MovieProvider.fromCursorValues;

/**
 * Created by mochadwi on 8/6/18.
 */

public class MainAdapter extends CursorAdapter {

    // Setup the ButterKnife for widget dashboard_item.xml
    @BindView(R.id.cl_item_container) ConstraintLayout mMovieContainer;
    @BindView(R.id.iv_item_moviephoto) ImageView mMoviePhoto;
    @BindView(R.id.tv_item_movietitle) TextView mMovieTitle;
    @BindView(R.id.tv_item_moviedescription) TextView mMovieDescription;
    @BindView(R.id.tv_item_movierelease) TextView mMovieRelease;

    public MainAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.main_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ButterKnife.bind(this, view);

        if (cursor != null) {
//            if (cursor.moveToFirst() && cursor.getCount() > 0) {
//            }
            MovieModel movieModel = fromCursorValues(cursor);
            if (movieModel != null) {
//                if (movieModel.isFavourite()) {
                GlideApp.with(view)
                        .load(BuildConfig.IMAGE_URL + movieModel.getPosterPath())
                        .override(100, 200)
                        .into(mMoviePhoto);
                mMovieTitle.setText(movieModel.getTitle());
                mMovieDescription.setText(movieModel.getOverview());
                mMovieRelease.setText(movieModel.getReleaseDate());
//                }
            }
        }
    }
}