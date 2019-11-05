package io.mochadwi.data.datasource.local.provider

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import io.mochadwi.BuildConfig
import io.mochadwi.data.datasource.local.room.FavouriteEntity.Companion.FAVOURITE_TABLE_NAME
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
                    id = getColumnInt(cursor, "id"),
                    overview = getColumnString(cursor, "overview"),
                    originalLanguage = getColumnString(cursor, "originalLanguage"),
                    originalTitle = getColumnString(cursor, "originalTitle"),
                    video = getColumnBool(cursor, "video") == 1,
                    title = getColumnString(cursor, "title"),
                    name = getColumnString(cursor, "name"),
                    posterPath = getColumnString(cursor, "posterPath"),
                    backdropPath = getColumnString(cursor, "backdropPath"),
                    releaseDate = getColumnString(cursor, "releaseDate"),
                    voteAverage = getColumnDouble(cursor, "voteAverage"),
                    popularity = getColumnDouble(cursor, "popularity"),
                    adult = getColumnBool(cursor, "adult") == 1,
                    voteCount = getColumnInt(cursor, "voteCount"),
                    isFavourite = getColumnBool(cursor, "favourite") == 1

            )
        }

        fun fromContentValues(values: ContentValues): Movie {
            return Movie(
                    id = values.getAsInteger("id"),
                    overview = values.getAsString("overview"),
                    originalLanguage = values.getAsString("originalLanguage"),
                    originalTitle = values.getAsString("originalTitle"),
                    video = values.getAsBoolean("video"),
                    title = values.getAsString("title"),
                    name = values.getAsString("name"),
                    posterPath = values.getAsString("posterPath"),
                    backdropPath = values.getAsString("backdropPath"),
                    releaseDate = values.getAsString("releaseDate"),
                    voteAverage = values.getAsDouble("voteAverage"),
                    popularity = values.getAsDouble("popularity"),
                    adult = values.getAsBoolean("adult"),
                    voteCount = values.getAsInteger("voteCount"),
                    isFavourite = values.getAsBoolean("favourite")
            )
        }

        fun toContentValues(movie: Movie): ContentValues {
            val values = ContentValues()

            values.put("id", movie.id)
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