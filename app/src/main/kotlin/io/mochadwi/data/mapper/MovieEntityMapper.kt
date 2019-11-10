package io.mochadwi.data.mapper

import io.mochadwi.data.datasource.local.room.MovieEntity
import io.mochadwi.data.datasource.network.kotlinx.response.movie.MovieResponse
import io.mochadwi.domain.model.favourite.Favourite
import io.mochadwi.domain.model.movie.Movie
import io.mochadwi.util.ext.default

/**
 * @author Mochamad Iqbal Dwi Cahyo, (moch.iqbal@gmail.com)
 * @version MovieResultMapper.kt, v 0.1 2019-08-13 23:16 by Mochamad Iqbal Dwi Cahyo
 */

typealias ListMovie<T> = List<T>

typealias ListMovieEntity = List<MovieEntity>
typealias ListMovieResponse = List<MovieResponse>

class MovieEntityMapper {

    companion object {
        fun from(favourite: Favourite) = with(favourite) {
            Movie(0, id, title, name, adult, backdropPath, genreIds, originalLanguage, originalTitle, originalName, overview, posterPath, popularity, releaseDate, video, voteAverage, voteCount, isFavourite)
        }

        // TODO(mochadwi): 2019-08-25 This is either known issue or due to type erasure at compile time, that detect different List<A> & List<B> as clashes
        fun <S : Any, D : Any> from(movies: ListMovie<S>): List<D> = movies.map {
            with(it) {
                when (this) {
                    is MovieEntity -> Movie(
                            0,
                            id,
                            title,
                            name,
                            adult = adult,
                            backdropPath = backdropPath.default,
                            genreIds = genreIds,
                            originalLanguage = originalLanguage,
                            originalTitle = originalTitle,
                            originalName = originalName,
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
                        name,
                        adult = adult,
                        backdropPath = backdropPath.default,
                        genreIds = genreIds,
                        originalLanguage = originalLanguage,
                        originalTitle = originalTitle,
                        originalName = originalName,
                        overview = overview,
                        posterPath = posterPath.default,
                        popularity = popularity,
                            releaseDate = if (releaseDate.isBlank()) firstAirDate else releaseDate,
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