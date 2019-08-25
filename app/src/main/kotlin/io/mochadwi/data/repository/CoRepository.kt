package io.mochadwi.data.repository

import io.mochadwi.data.datasource.local.room.MovieDao
import io.mochadwi.data.datasource.local.room.MovieEntity
import io.mochadwi.data.datasource.network.RetrofitEndpoint
import io.mochadwi.data.mapper.MovieResultMapper
import io.mochadwi.domain.model.movie.Movie
import io.mochadwi.domain.model.movie.MovieModel
import io.mochadwi.domain.repository.AppRepository
import io.mochadwi.util.ext.coroutineAsync
import io.mochadwi.util.ext.default
import io.mochadwi.util.ext.sameContentWith
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

/**
 * App repository
 * Make use of AppWebDatasource & add some cache
 */
class CoRepository(
    private val endpoint: RetrofitEndpoint,
    private val movieDao: MovieDao
) : AppRepository {

    override fun getMoviesAsync(): Deferred<List<MovieModel>?> = coroutineAsync(IO) {
        val local = localGetMoviesAsync().await() ?: emptyList()
        val remote = remoteGetMoviesAsync().await() ?: emptyList()

        if ((local sameContentWith remote).default) local
        else remote
    }

    private fun localGetMoviesAsync(): Deferred<List<MovieModel>?> = coroutineAsync(IO) {
        movieDao.getAllMovies().map {
            MovieModel.from(it)
        }
    }

    // TODO(mochamadiqbaldwicahyo): 2019-08-13 Don't do transformation / mapper here in the repo
    private fun remoteGetMoviesAsync(): Deferred<List<MovieModel>?> = coroutineAsync(IO) {
        val result = endpoint.getMoviesAsync().await()
        result.map {
            movieDao.upsert(MovieEntity.from(it))
            MovieModel.from(it)
        }
    }

    override fun getDiscoverMovies(): List<Movie>? = runBlocking(IO) {
        val remote = async { remoteGetDiscoverMoviesAsync() }
        val local = async { remoteGetDiscoverMoviesAsync() }

        remote.await() ?: local.await()
    }

    private suspend fun remoteGetDiscoverMoviesAsync(): List<Movie>? {
        val response = endpoint.getDiscoverMoviesAsync()

        return response.body()?.let {
            if (response.isSuccessful) {
                MovieResultMapper.from(it.results ?: emptyList())
            } else {
                emptyList()
            }
        }
    }

    override fun searchMoviesAsync(query: String): Deferred<List<MovieModel>?> = coroutineAsync(
        IO) {
        movieDao.searchMovies(query).map {
            MovieModel.from(it)
        }
    }
}