package io.mochadwi.cataloguefavourite.adapter

import android.content.Context
import android.database.Cursor
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import io.mochadwi.cataloguefavourite.BuildConfig
import io.mochadwi.cataloguefavourite.GlideApp
import io.mochadwi.cataloguefavourite.R
import io.mochadwi.cataloguefavourite.model.MovieProvider.fromCursorValues

/**
 * Created by mochadwi on 8/6/18.
 */

class MainAdapter(context: Context, c: Cursor?, autoRequery: Boolean) : CursorAdapter(context, c, autoRequery) {

    // Setup the ButterKnife for widget dashboard_item.xml
    @BindView(R.id.cl_item_container)
    lateinit var mMovieContainer: ConstraintLayout
    @BindView(R.id.iv_item_moviephoto)
    lateinit var mMoviePhoto: ImageView
    @BindView(R.id.tv_item_movietitle)
    lateinit var mMovieTitle: TextView
    @BindView(R.id.tv_item_moviedescription)
    lateinit var mMovieDescription: TextView
    @BindView(R.id.tv_item_movierelease)
    lateinit var mMovieRelease: TextView

    override fun getCursor(): Cursor {
        return super.getCursor()
    }

    override fun newView(context: Context, cursor: Cursor, viewGroup: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.main_item, viewGroup, false)
    }

    override fun bindView(view: View, context: Context, cursor: Cursor?) {
        ButterKnife.bind(this, view)

        if (cursor != null) {
            //            if (cursor.moveToFirst() && cursor.getCount() > 0) {
            //            }
            val movieModel = fromCursorValues(cursor)
            if (movieModel != null) {
                //                if (movieModel.isFavourite()) {
                GlideApp.with(view)
                        .load(BuildConfig.IMAGE_URL + movieModel.posterPath!!)
                        .override(100, 200)
                        .into(mMoviePhoto)
                mMovieTitle!!.text = movieModel.title
                mMovieDescription!!.text = movieModel.overview
                mMovieRelease!!.text = movieModel.releaseDate
                //                }
            }
        }
    }
}