package io.mochadwi.cataloguefavourite.model

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 * Created by mochadwi on 9/25/18.
 */

object MovieProvider {
    /**
     * The authority of this content provider.
     */
    val AUTHORITY = "io.mochadwi.etalase.data.provider"

    /**
     * The URI for the MovieModel table.
     */
    val URI_MOVIE = Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(MovieModel.TABLE_NAME)
            .build()

    // the getColumn# used to get from Content Provider
    @Throws(IllegalArgumentException::class, IllegalStateException::class)
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
    fun fromCursorValues(cursor: Cursor): MovieModel {
        val movie = MovieModel()

        movie.overview = getColumnString(cursor, "overview")
        movie.originalLanguage = getColumnString(cursor, "originalLanguage")
        movie.originalTitle = getColumnString(cursor, "originalTitle")
        movie.isVideo = getColumnBool(cursor, "video") == 1
        movie.title = try {
            getColumnString(cursor, "title")
        } catch (e: IllegalArgumentException) {
            getColumnString(cursor, "name")
        } catch (e: IllegalStateException) {
            getColumnString(cursor, "name")
        }
        movie.posterPath = getColumnString(cursor, "posterPath")
        movie.backdropPath = getColumnString(cursor, "backdropPath")
        movie.releaseDate = getColumnString(cursor, "releaseDate")
        movie.voteAverage = getColumnDouble(cursor, "voteAverage")
        movie.popularity = getColumnDouble(cursor, "popularity")
        movie.isAdult = getColumnBool(cursor, "adult") == 1
        movie.voteCount = getColumnInt(cursor, "voteCount")
        movie.isFavourite = getColumnBool(cursor, "favourite") == 1

        return movie
    }

    fun fromContentValues(values: ContentValues): MovieModel {
        val movie = MovieModel()

        movie.overview = values.getAsString("overview")
        movie.originalLanguage = values.getAsString("originalLanguage")
        movie.originalTitle = values.getAsString("originalTitle")
        movie.isVideo = values.getAsBoolean("video")
        movie.title = values.getAsString("title")
        movie.posterPath = values.getAsString("posterPath")
        movie.backdropPath = values.getAsString("backdropPath")
        movie.releaseDate = values.getAsString("releaseDate")
        movie.voteAverage = values.getAsDouble("voteAverage")
        movie.popularity = values.getAsDouble("popularity")
        movie.isAdult = values.getAsBoolean("adult")
        movie.voteCount = values.getAsInteger("voteCount")
        movie.isFavourite = values.getAsBoolean("favourite")

        return movie
    }

    fun toContentValues(movie: MovieModel): ContentValues {
        val values = ContentValues()

        values.put("overview", movie.overview)
        values.put("originalLanguage", movie.originalLanguage)
        values.put("originalTitle", movie.originalTitle)
        values.put("video", movie.isVideo)
        values.put("title", movie.title)
        values.put("posterPath", movie.posterPath)
        values.put("backdropPath", movie.backdropPath)
        values.put("releaseDate", movie.releaseDate)
        values.put("voteAverage", movie.voteAverage)
        values.put("popularity", movie.popularity)
        values.put("adult", movie.isAdult)
        values.put("voteCount", movie.voteCount)
        values.put("favourite", movie.isFavourite)

        return values
    }
}
