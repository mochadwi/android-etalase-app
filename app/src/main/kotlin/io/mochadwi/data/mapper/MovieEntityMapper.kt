package io.mochadwi.data.mapper

import io.mochadwi.data.datasource.local.room.MovieEntity
import io.mochadwi.data.datasource.network.kotlinx.response.movie.MovieResponse
import io.mochadwi.domain.model.movie.Movie
import io.mochadwi.util.ext.default

/**
 * @author Mochamad Iqbal Dwi Cahyo, (moch.iqbal@dana.id)
 * @version MovieResultMapper.kt, v 0.1 2019-08-13 23:16 by Mochamad Iqbal Dwi Cahyo
 */

typealias ListMovie<T> = List<T>

typealias ListMovieEntity = List<MovieEntity>
typealias ListMovieResponse = List<MovieResponse>

class MovieEntityMapper {

    companion object {
        // TODO(mochadwi): 2019-08-25 This is either known issue or due to type erasure at compile time, that detect different List<A> & List<B> as clashes
        fun <S : Any, D : Any> from(movies: ListMovie<S>): List<D> = movies.map {
            with(it) {
                when (this) {
                    is MovieEntity -> Movie(
                        id,
                        title,
                        originalTitle = originalTitle,
                        adult = adult,
                        backdropPath = backdropPath.default,
                        genreIds = genreIds,
                        originalLanguage = originalLanguage,
                        overview = overview,
                        posterPath = posterPath.default,
                        popularity = popularity,
                        releaseDate = releaseDate,
                        video = video,
                        voteAverage = voteAverage,
                        voteCount = voteCount
                    )

                    is MovieResponse -> MovieEntity(
                        id,
                        title,
                        originalTitle = originalTitle,
                        adult = adult,
                        backdropPath = backdropPath.default,
                        genreIds = genreIds,
                        originalLanguage = originalLanguage,
                        overview = overview,
                        posterPath = posterPath.default,
                        popularity = popularity,
                        releaseDate = releaseDate,
                        video = video,
                        voteAverage = voteAverage,
                        voteCount = voteCount
                    )

                    else -> TODO()
                } as D
            }
        }
    }
}