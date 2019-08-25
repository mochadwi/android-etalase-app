package io.mochadwi.data.mapper

import io.mochadwi.domain.model.movie.Movie

/**
 * @author Mochamad Iqbal Dwi Cahyo, (moch.iqbal@dana.id)
 * @version MovieResultMapper.kt, v 0.1 2019-08-13 23:16 by Mochamad Iqbal Dwi Cahyo
 */

class MovieResultMapper {

    companion object {
        fun from(movies: List<MovieResponse>): List<Movie> = movies.map {
            with(it) {
                Movie(
                    id,
                    title,
                    originalTitle = originalTitle,
                    adult = adult,
                    backdropPath = backdropPath.default,
                    genreIds = genreIds,
                    originalLanguage = originalLanguage,
                    overview = overview,
                    movieerPath = movieerPath.default,
                    popularity = popularity,
                    releaseDate = releaseDate,
                    video = video,
                    voteAverage = voteAverage,
                    voteCount = voteCount
                )
            }
        }
    }
}