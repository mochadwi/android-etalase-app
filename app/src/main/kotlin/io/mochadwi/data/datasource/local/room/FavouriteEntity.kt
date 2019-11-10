package io.mochadwi.data.datasource.local.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Created by mochadwi on 2019-10-31
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

const val FAVOURITE_TABLE_NAME = "tbl_favourite"

@Entity(tableName = FAVOURITE_TABLE_NAME)
data class FavouriteEntity @JvmOverloads constructor(
        var _id: Long = 0,
        @PrimaryKey
        var favouriteId: Int = 0,
        var title: String? = null,
        var name: String? = null,
        var adult: Boolean = false,
        var backdropPath: String = "",
        @Ignore
        var genreIds: List<Int> = emptyList(),
        var originalLanguage: String = "",
        var originalTitle: String = "",
        var originalName: String = "",
        var overview: String = "",
        var posterPath: String = "",
        var popularity: Double = 0.0,
        var releaseDate: String = "",
        var video: Boolean = false,
        var voteAverage: Double = 0.0,
        var voteCount: Int = 0,
        var favourite: Boolean? = null
)