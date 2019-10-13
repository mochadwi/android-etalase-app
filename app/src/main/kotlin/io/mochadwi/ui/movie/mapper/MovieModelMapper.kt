package io.mochadwi.ui.movie.mapper

import io.mochadwi.data.datasource.local.room.MovieEntity
import io.mochadwi.domain.model.movie.Movie
import io.mochadwi.ui.movie.list.MovieItem

/**
 * @author Mochamad Iqbal Dwi Cahyo, (moch.iqbal@gmail.com)
 * @version MovieModelMapper.kt, v 0.1 2019-08-25 20:54 by Mochamad Iqbal Dwi Cahyo
 */

class MovieModelMapper {

    companion object {
        fun from(model: Movie) = with(model) {
            MovieItem(id, title, name, adult, backdropPath, genreIds, originalLanguage,
                originalTitle, originalName, overview, posterPath, popularity, releaseDate, video,
                voteAverage,
                voteCount)
        }

        fun from(entity: MovieEntity) = with(entity) {
            MovieItem(id, title, name, adult, backdropPath, genreIds, originalLanguage,
                originalTitle, originalName, overview, posterPath, popularity, releaseDate, video,
                voteAverage,
                voteCount)
        }
    }
}