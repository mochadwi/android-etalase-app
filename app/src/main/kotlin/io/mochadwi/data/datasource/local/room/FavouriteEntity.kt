package io.mochadwi.data.datasource.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.mochadwi.data.datasource.local.room.FavouriteEntity.Companion.FAVOURITE_TABLE_NAME

/**
 * Created by mochadwi on 2019-10-31
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

@Entity(tableName = FAVOURITE_TABLE_NAME)
data class FavouriteEntity(
        @PrimaryKey
        val id: Int,
        val title: String?,
        val name: String?,
        val adult: Boolean,
        val backdropPath: String,
        val genreIds: List<Int>,
        val originalLanguage: String,
        val originalTitle: String,
        val originalName: String,
        val overview: String,
        val posterPath: String,
        val popularity: Double,
        val releaseDate: String,
        val video: Boolean,
        val voteAverage: Double,
        val voteCount: Int,
        val isFavourite: Boolean?
) {
    companion object {
        const val FAVOURITE_TABLE_NAME = "tbl_favourite"
        fun empty() = FavouriteEntity(
                0,
                "",
                "",
                false,
                "",
                emptyList(),
                "",
                "",
                "",
                "",
                "",
                0.0,
                "",
                false,
                0.0,
                0,
                false
        )
    }
}