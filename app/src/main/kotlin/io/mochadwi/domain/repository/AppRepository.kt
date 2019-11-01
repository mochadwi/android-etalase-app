package io.mochadwi.domain.repository

import io.mochadwi.data.datasource.local.room.FavouriteEntity
import io.mochadwi.domain.model.favourite.Favourite
import io.mochadwi.domain.model.movie.Movie

/**
 * @author Mochamad Iqbal Dwi Cahyo, (moch.iqbal@gmail.com)
 * @version CoRepository.kt, v 0.1 2019-08-13 23:45 by Mochamad Iqbal Dwi Cahyo
 */

/**
 * App repository
 *
 */
interface AppRepository {
    fun getDiscoverMovies(): List<Movie>?
    fun getTvShows(): List<Movie>?
    fun searchMovies(query: String): List<Movie>?
    fun getLocalFavourites(
            transform: (List<FavouriteEntity>) -> List<Favourite>
    ): List<Favourite>?

    fun addToLocalFavourite(entity: FavouriteEntity): Boolean
    fun deleteFromLocalFavouriteById(id: Int): Boolean
}