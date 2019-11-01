package io.mochadwi.ui.favourite.mapper

import io.mochadwi.domain.model.favourite.Favourite
import io.mochadwi.ui.favourite.item.FavouriteItem
import io.mochadwi.ui.movie.list.MovieItem

/**
 * Created by mochadwi on 2019-11-01
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

class FavouriteItemMapper {
    companion object {
        fun from(item: FavouriteItem) = with(item) {
            Favourite(
                    id, title, name, adult, backdropPath, genreIds, originalLanguage, originalTitle, originalName, overview, posterPath, popularity, releaseDate, video, voteAverage, voteCount, isFavourite
            )
        }

        fun from(item: MovieItem) = with(item) {
            FavouriteItem(
                    id, title, name, adult, backdropPath, genreIds, originalLanguage, originalTitle, originalName, overview, posterPath, popularity, releaseDate, video, voteAverage, voteCount, isFavourite
            )
        }
    }
}