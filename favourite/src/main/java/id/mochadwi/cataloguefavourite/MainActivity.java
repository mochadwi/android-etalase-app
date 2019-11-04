package io.mochadwi.cataloguefavourite;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.mochadwi.cataloguefavourite.adapter.MainAdapter;

import static io.mochadwi.cataloguefavourite.model.MovieProvider.URI_MOVIE;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_MOVIES = 1;

    // UI
    @BindView(R.id.lv_favourite_movies) ListView mLvMovies;
    @BindView(R.id.tv_favourite_empty) TextView mTvEmpty;

    // DATA
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        mAdapter = new MainAdapter(MainActivity.this, null, true);
        mLvMovies.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(LOADER_MOVIES, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(LOADER_MOVIES, null, this);
        if (mLvMovies.getAdapter().getCount() > 0) {
            mTvEmpty.setVisibility(View.GONE);
        } else {
            mTvEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, URI_MOVIE, null, null, null, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(LOADER_MOVIES);
    }
}
