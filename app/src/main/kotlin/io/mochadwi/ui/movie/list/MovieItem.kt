package io.mochadwi.ui.movie.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

@Parcelize
data class MovieItem(
        val id: Int,
        val title: String? = null,
        val name: String? = null,
        val adult: Boolean = false,
        val backdropPath: String = "",
        val genreIds: List<Int> = emptyList(),
        val originalLanguage: String = "",
        val originalTitle: String = "",
        val originalName: String = "",
        val overview: String = "",
        val posterPath: String = "",
        val popularity: Double = 0.0,
        val releaseDate: String = "",
        val video: Boolean = false,
        val voteAverage: Double = 0.0,
        val voteCount: Int = 0,
        val isFavourite: Boolean?
) : Parcelable