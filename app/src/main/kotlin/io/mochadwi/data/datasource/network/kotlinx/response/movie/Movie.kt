package io.mochadwi.data.datasource.network.kotlinx.response.movie

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO(mochamadiqbaldwicahyo): 2019-08-23 Dont use default value?, value will be mapped from network instead
@Serializable
data class MovieResponse(
        val adult: Boolean = false,
        @Optional
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
        @Optional
    @SerialName("genre_ids")
    val genreIds: List<Int> = listOf(),
        val id: Int = 0,
        @Optional
    @SerialName("original_language")
    val originalLanguage: String = "",
        @Optional
    @SerialName("original_title")
    val originalTitle: String = "",
        @SerialName("original_name")
    val originalName: String = "",
        val overview: String = "",
        val popularity: Double = 0.0,
        @Optional
    @SerialName("poster_path")
    val posterPath: String? = null,
        @Optional
    @SerialName("release_date")
    val releaseDate: String = "",
        @SerialName("first_air_date")
        val firstAirDate: String = "",
        val title: String? = null,
        val name: String? = null,
        val video: Boolean = false,
        @Optional
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
        @Optional
    @SerialName("vote_count")
    val voteCount: Int = 0
)