package io.mochadwi.data.datasource.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by mochadwi on 2019-10-31
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

@Entity(tableName = "tbl_favourite")
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
        val isDeleted: Boolean?
) {
    companion object {
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