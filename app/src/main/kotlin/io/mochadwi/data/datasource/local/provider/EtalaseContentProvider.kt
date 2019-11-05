package io.mochadwi.data.datasource.local.provider

import android.content.*
import android.database.Cursor
import android.net.Uri
import io.mochadwi.BuildConfig
import io.mochadwi.data.datasource.local.provider.FavouriteProvider.Companion.fromContentValues
import io.mochadwi.data.datasource.local.room.FavouriteDao
import io.mochadwi.data.datasource.local.room.FavouriteEntity.Companion.FAVOURITE_TABLE_NAME
import io.mochadwi.data.mapper.FavouriteDataMapper
import kotlinx.coroutines.runBlocking

/**
 * Created by mochadwi on 2019-11-05
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

/**
 * A [ContentProvider] based on a Room database.
 *
 *
 *
 * Note that you don't need to implement a ContentProvider unless you want to expose the data
 * outside your process or your application already uses a ContentProvider.
 */
class EtalaseContentProvider(
        private val ctx: Context,
        private val favouriteDao: FavouriteDao) : ContentProvider() {

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val code = MATCHER.match(uri)
        if (code == CODE_MOVIE || code == CODE_MOVIE_ID) {
            val cursor: Cursor = when (code) {
                CODE_MOVIE -> runBlocking { favouriteDao.selectAll() }
                else -> runBlocking { favouriteDao.selectById(ContentUris.parseId(uri)) }
            }
            cursor.setNotificationUri(ctx.contentResolver, uri)
            return cursor
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return when (MATCHER.match(uri)) {
            CODE_MOVIE -> "vnd.android.cursor.dir/$AUTHORITY.$FAVOURITE_TABLE_NAME"
            CODE_MOVIE_ID -> "vnd.android.cursor.item/$AUTHORITY.$FAVOURITE_TABLE_NAME"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (MATCHER.match(uri)) {
            CODE_MOVIE -> {
                values?.let {
                    val id = favouriteDao.insert(FavouriteDataMapper.from(fromContentValues(values)))
                    ctx.contentResolver.notifyChange(uri, null)
                    return ContentUris.withAppendedId(uri, id)
                }

                throw IllegalArgumentException("values is null")
            }
            CODE_MOVIE_ID -> throw IllegalArgumentException("Invalid URI, cannot insert with ID: $uri")
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun delete(uri: Uri, selection: String?,
                        selectionArgs: Array<String>?): Int {
        when (MATCHER.match(uri)) {
            CODE_MOVIE -> throw IllegalArgumentException("Invalid URI, cannot update without ID$uri")
            CODE_MOVIE_ID -> {
                val count = runBlocking { favouriteDao.deleteFavouriteById(ContentUris.parseId(uri)) }
                ctx.contentResolver.notifyChange(uri, null)
                return count
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        when (MATCHER.match(uri)) {
            CODE_MOVIE -> throw IllegalArgumentException("Invalid URI, cannot update without ID $uri")
            CODE_MOVIE_ID -> {
                values?.let {
                    val movie = FavouriteDataMapper.from(fromContentValues(values))
                    val count = favouriteDao.update(movie)
                    ctx.contentResolver.notifyChange(uri, null)
                    return count
                }

                throw IllegalArgumentException("values is null")
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    companion object {
        /**
         * The authority of this content provider.
         */
        const val AUTHORITY = BuildConfig.AUTHORITY

        /**
         * The URI for the MovieModel table.
         */
        val URI_FAVOURITE = Uri.Builder().scheme("content")
                .authority(AUTHORITY)
                .appendPath(FAVOURITE_TABLE_NAME)
                .build()

        /**
         * The match code for some items in the MovieModel table.
         */
        private const val CODE_MOVIE = 1

        /**
         * The match code for an item in the MovieModel table.
         */
        private const val CODE_MOVIE_ID = 2

        /**
         * The URI matcher.
         */
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(AUTHORITY, FAVOURITE_TABLE_NAME, CODE_MOVIE)
            MATCHER.addURI(AUTHORITY, "$FAVOURITE_TABLE_NAME/#", CODE_MOVIE_ID)
        }
    }
}
