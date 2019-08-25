package io.mochadwi.domain.repository

import io.mochadwi.domain.model.movie.Movie

/**
 * @author Mochamad Iqbal Dwi Cahyo, (moch.iqbal@dana.id)
 * @version CoRepository.kt, v 0.1 2019-08-13 23:45 by Mochamad Iqbal Dwi Cahyo
 */

/**
 * App repository
 *
 */
interface AppRepository {
    fun getDiscoverMovies(): List<Movie>?
    fun searchMoviesAsync(query: String): List<Movie>?
}