package io.mochadwi.data.datasource.local.provider

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import io.mochadwi.BuildConfig
import io.mochadwi.data.datasource.local.room.FAVOURITE_TABLE_NAME
import io.mochadwi.domain.model.movie.Movie

/**
 * Created by mochadwi on 2019-11-05
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

class FavouriteProvider {
    companion object {
        /**
         * The authority of this content provider.
         */
        val AUTHORITY = BuildConfig.AUTHORITY

        /**
         * The URI for the MovieModel table.
         */
        val URI_MOVIE = Uri.Builder().scheme("content")
                .authority(AUTHORITY)
                .appendPath(FAVOURITE_TABLE_NAME)
                .build()

        // the getColumn# used to get from Content Provider
        fun getColumnString(cursor: Cursor, columnName: String): String {
            return cursor.getString(cursor.getColumnIndexOrThrow(columnName))
        }

        fun getColumnInt(cursor: Cursor, columnName: String): Int {
            return cursor.getInt(cursor.getColumnIndexOrThrow(columnName))
        }

        fun getColumnLong(cursor: Cursor, columnName: String): Long {
            return cursor.getLong(cursor.getColumnIndexOrThrow(columnName))
        }

        fun getColumnDouble(cursor: Cursor, columnName: String): Double {
            return cursor.getDouble(cursor.getColumnIndexOrThrow(columnName))
        }

        // boolean is binary values on DB: 1 (true) or 0 (false)
        fun getColumnBool(cursor: Cursor, columnName: String): Int {
            return cursor.getInt(cursor.getColumnIndexOrThrow(columnName))
        }

        // for insert & update query
        fun fromCursorValues(cursor: Cursor): Movie {
            return Movie(
                    _id = getColumnLong(cursor, "_id"),
                    movieId = getColumnInt(cursor, "movieId"),
                    title = getColumnString(cursor, "title"),
                    name = getColumnString(cursor, "name"),
                    adult = getColumnBool(cursor, "adult") == 1,
                    backdropPath = getColumnString(cursor, "backdropPath"),
                    originalLanguage = getColumnString(cursor, "originalLanguage"),
                    originalTitle = getColumnString(cursor, "originalTitle"),
                    overview = getColumnString(cursor, "overview"),
                    posterPath = getColumnString(cursor, "posterPath"),
                    popularity = getColumnDouble(cursor, "popularity"),
                    releaseDate = getColumnString(cursor, "releaseDate"),
                    video = getColumnBool(cursor, "video") == 1,
                    voteAverage = getColumnDouble(cursor, "voteAverage"),
                    voteCount = getColumnInt(cursor, "voteCount"),
                    isFavourite = getColumnBool(cursor, "favourite") == 1

            )
        }

        fun fromContentValues(values: ContentValues): Movie {
            return Movie(
                    _id = values.getAsLong("_id"),
                    movieId = values.getAsInteger("movieId"),
                    title = values.getAsString("title"),
                    name = values.getAsString("name"),
                    adult = values.getAsBoolean("adult"),
                    backdropPath = values.getAsString("backdropPath"),
                    originalLanguage = values.getAsString("originalLanguage"),
                    originalTitle = values.getAsString("originalTitle"),
                    overview = values.getAsString("overview"),
                    posterPath = values.getAsString("posterPath"),
                    popularity = values.getAsDouble("popularity"),
                    releaseDate = values.getAsString("releaseDate"),
                    video = values.getAsBoolean("video"),
                    voteAverage = values.getAsDouble("voteAverage"),
                    voteCount = values.getAsInteger("voteCount"),
                    isFavourite = values.getAsBoolean("favourite")
            )
        }

        fun toContentValues(movie: Movie): ContentValues {
            val values = ContentValues()

            values.put("_id", movie._id)
            values.put("movieId", movie.movieId)
            values.put("overview", movie.overview)
            values.put("originalLanguage", movie.originalLanguage)
            values.put("originalTitle", movie.originalTitle)
            values.put("video", movie.video)
            values.put("title", movie.title)
            values.put("name", movie.name)
            values.put("posterPath", movie.posterPath)
            values.put("backdropPath", movie.backdropPath)
            values.put("releaseDate", movie.releaseDate)
            values.put("voteAverage", movie.voteAverage)
            values.put("popularity", movie.popularity)
            values.put("adult", movie.adult)
            values.put("voteCount", movie.voteCount)
            values.put("favourite", movie.isFavourite)

            return values
        }
    }

}