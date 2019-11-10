package io.mochadwi.domain.model.movie

data class Movie(
        val _id: Long,
        val movieId: Int,
        val title: String?,
        val name: String?,
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
        var isFavourite: Boolean? = false
) {
    companion object {
        fun empty() = Movie(
                0,
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
                0
        )
    }
}