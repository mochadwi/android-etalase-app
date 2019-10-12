package io.mochadwi.domain.repository

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
}