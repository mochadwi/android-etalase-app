package io.mochadwi.cataloguefavourite

import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ListView
import android.widget.TextView

import butterknife.BindView
import butterknife.ButterKnife
import io.mochadwi.cataloguefavourite.adapter.MainAdapter

import io.mochadwi.cataloguefavourite.model.MovieProvider.URI_MOVIE

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    // UI
    @BindView(R.id.lv_favourite_movies)
    lateinit var mLvMovies: ListView
    @BindView(R.id.tv_favourite_empty)
    lateinit var mTvEmpty: TextView

    // DATA
    private var mAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        ButterKnife.bind(this)

        mAdapter = MainAdapter(this@MainActivity, null, true)
        mLvMovies!!.adapter = mAdapter
        supportLoaderManager.initLoader(LOADER_MOVIES, null, this)
    }

    override fun onResume() {
        super.onResume()
        supportLoaderManager.restartLoader(LOADER_MOVIES, null, this)
        if (mLvMovies!!.adapter.count > 0) {
            mTvEmpty!!.visibility = View.GONE
        } else {
            mTvEmpty!!.visibility = View.VISIBLE
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
        mAdapter!!.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        mAdapter!!.swapCursor(null)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(this, URI_MOVIE, null, null, null, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportLoaderManager.destroyLoader(LOADER_MOVIES)
    }

    companion object {

        private val LOADER_MOVIES = 1
    }
}
