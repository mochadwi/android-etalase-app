package io.mochadwi.data.repository

import io.mochadwi.data.datasource.local.room.MovieDao
import io.mochadwi.data.datasource.local.room.MovieEntity
import io.mochadwi.data.datasource.network.RetrofitEndpoint
import io.mochadwi.data.mapper.MovieEntityMapper
import io.mochadwi.data.mapper.MovieResultMapper
import io.mochadwi.domain.model.movie.Movie
import io.mochadwi.domain.repository.AppRepository
import io.mochadwi.util.ext.default
import io.mochadwi.util.ext.sameContentWith
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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

    override fun getDiscoverMovies(): List<Movie>? = runBlocking {
        val local = withContext(IO) { localGetDiscoverMoviesAsync() }
        val remote = withContext(IO) { remoteGetDiscoverMoviesAsync() }

        if ((local sameContentWith remote).default) local
        else remote
    }

    private suspend fun localGetDiscoverMoviesAsync(): List<Movie>? {
        val source = movieDao.getAllMovies()

        return if (source.isNotEmpty()) MovieEntityMapper.from(source)
        else emptyList()
    }

    private suspend fun remoteGetDiscoverMoviesAsync(): List<Movie>? {
        val response = endpoint.getDiscoverMoviesAsync()

        return response.body()?.let {
            if (response.isSuccessful) MovieResultMapper.from(it.results ?: emptyList())
            else emptyList()
        }
    }

    override fun searchMovies(query: String): List<Movie>? = runBlocking {
        MovieEntityMapper.from<MovieEntity, Movie>(movieDao.searchMovies(query))
    }
}