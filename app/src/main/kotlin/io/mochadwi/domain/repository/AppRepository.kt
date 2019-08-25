package io.mochadwi.domain.repository

import io.mochadwi.domain.model.movie.Movie
import io.mochadwi.domain.model.movie.MovieModel
import kotlinx.coroutines.Deferred

/**
 * @author Mochamad Iqbal Dwi Cahyo, (moch.iqbal@dana.id)
 * @version CoRepository.kt, v 0.1 2019-08-13 23:45 by Mochamad Iqbal Dwi Cahyo
 */

/**
 * App repository
 *
 */
interface AppRepository {

    // TODO(mochamadiqbaldwicahyo): 2019-08-14 don't put external dependency e.g (Deferred) in domain layer
    fun getMoviesAsync(): Deferred<List<MovieModel>?>

    fun getDiscoverMovies(): List<Movie>?
    fun searchMoviesAsync(query: String): Deferred<List<MovieModel>?>
}