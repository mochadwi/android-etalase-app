package io.mochadwi.ui.favourite.item

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by mochadwi on 2019-11-01
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

@Parcelize
data class FavouriteItem(
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
) : Parcelable