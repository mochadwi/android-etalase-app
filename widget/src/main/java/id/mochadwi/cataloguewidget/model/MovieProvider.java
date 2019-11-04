package io.mochadwi.cataloguewidget.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by mochadwi on 9/25/19.
 */

public class MovieProvider {
    /**
     * The authority of this content provider.
     */
    public static final String AUTHORITY = "io.mochadwi.cataloguemovie.data.provider";

    /**
     * The URI for the MovieModel table.
     */
    public static final Uri URI_MOVIE = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(MovieModel.TABLE_NAME)
            .build();

    // the getColumn# used to get from Content Provider
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName));
    }

    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(columnName));
    }

    // boolean is binary values on DB: 1 (true) or 0 (false)
    public static int getColumnBool(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
    }

    // for insert & update query
    public static MovieModel fromCursorValues(Cursor cursor) {
        final MovieModel movie = new MovieModel();

        movie.setOverview(getColumnString(cursor, "overview"));
        movie.setOriginalLanguage(getColumnString(cursor, "originalLanguage"));
        movie.setOriginalTitle(getColumnString(cursor, "originalTitle"));
        movie.setVideo(getColumnBool(cursor, "video") == 1);
        movie.setTitle(getColumnString(cursor, "title"));
        movie.setPosterPath(getColumnString(cursor, "posterPath"));
        movie.setBackdropPath(getColumnString(cursor, "backdropPath"));
        movie.setReleaseDate(getColumnString(cursor, "releaseDate"));
        movie.setVoteAverage(getColumnDouble(cursor, "voteAverage"));
        movie.setPopularity(getColumnDouble(cursor, "popularity"));
        movie.setAdult(getColumnBool(cursor, "adult") == 1);
        movie.setVoteCount(getColumnInt(cursor, "voteCount"));
        movie.setFavourite(getColumnBool(cursor, "favourite") == 1);

        return movie;
    }

    public static MovieModel fromContentValues(ContentValues values) {
        final MovieModel movie = new MovieModel();

        movie.setOverview(values.getAsString("overview"));
        movie.setOriginalLanguage(values.getAsString("originalLanguage"));
        movie.setOriginalTitle(values.getAsString("originalTitle"));
        movie.setVideo(values.getAsBoolean("video"));
        movie.setTitle(values.getAsString("title"));
        movie.setPosterPath(values.getAsString("posterPath"));
        movie.setBackdropPath(values.getAsString("backdropPath"));
        movie.setReleaseDate(values.getAsString("releaseDate"));
        movie.setVoteAverage(values.getAsDouble("voteAverage"));
        movie.setPopularity(values.getAsDouble("popularity"));
        movie.setAdult(values.getAsBoolean("adult"));
        movie.setVoteCount(values.getAsInteger("voteCount"));
        movie.setFavourite(values.getAsBoolean("favourite"));

        return movie;
    }

    public static ContentValues toContentValues(MovieModel movie) {
        final ContentValues values = new ContentValues();

        values.put("overview", movie.getOverview());
        values.put("originalLanguage", movie.getOriginalLanguage());
        values.put("originalTitle", movie.getOriginalTitle());
        values.put("video", movie.isVideo());
        values.put("title", movie.getTitle());
        values.put("posterPath", movie.getPosterPath());
        values.put("backdropPath", movie.getBackdropPath());
        values.put("releaseDate", movie.getReleaseDate());
        values.put("voteAverage", movie.getVoteAverage());
        values.put("popularity", movie.getPopularity());
        values.put("adult", movie.isAdult());
        values.put("voteCount", movie.getVoteCount());
        values.put("favourite", movie.isFavourite());

        return values;
    }
}
