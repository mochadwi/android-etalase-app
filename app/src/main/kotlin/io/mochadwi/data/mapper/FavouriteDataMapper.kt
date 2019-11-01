package io.mochadwi.data.mapper

import io.mochadwi.data.datasource.local.room.FavouriteEntity
import io.mochadwi.domain.model.favourite.Favourite

/**
 * Created by mochadwi on 2019-11-01
 * Copyright (c) 2019 dicoding. All rights reserved.
 */
class FavouriteDataMapper {
    companion object {
        fun from(entity: FavouriteEntity) = with(entity) {
            Favourite(id, title, name, adult, backdropPath, genreIds, originalLanguage, originalTitle, originalName, overview, posterPath, popularity, releaseDate, video, voteAverage, voteCount)
        }

        fun from(model: Favourite) = with(model) {
            FavouriteEntity(id, title, name, adult, backdropPath, genreIds, originalLanguage, originalTitle, originalName, overview, posterPath, popularity, releaseDate, video, voteAverage, voteCount, false)
        }
    }
}