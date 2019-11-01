package io.mochadwi.domain.model.favourite

/**
 * Created by mochadwi on 2019-10-31
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

data class Favourite(
        val id: Int,
        val title: String,
        val name: String,
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
        val voteCount: Int
)