package io.mochadwi.data.repository

import io.mochadwi.data.datasource.local.room.FavouriteDao
import io.mochadwi.data.datasource.local.room.MovieDao
import io.mochadwi.data.datasource.local.room.MovieEntity
import io.mochadwi.data.datasource.network.RetrofitEndpoint
import io.mochadwi.data.mapper.FavouriteDataMapper
import io.mochadwi.data.mapper.MovieEntityMapper
import io.mochadwi.data.mapper.MovieResultMapper
import io.mochadwi.domain.model.favourite.Favourite
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
        private val movieDao: MovieDao,
        private val favouriteDao: FavouriteDao
) : AppRepository {

    override fun getDiscoverMovies(): List<Movie>? = runBlocking(IO) {
        val local = withContext(IO) { localGetDiscoverMoviesAsync() }
        val remote = withContext(IO) { remoteGetDiscoverMoviesAsync() }

        if ((local sameContentWith remote).default) local
        else remote
    }

    override fun getTvShows(): List<Movie>? = runBlocking {
        withContext(IO) { remoteGetTvShowsAsync() }
    }

    private suspend fun localGetDiscoverMoviesAsync(): List<Movie>? {
        val source = movieDao.getAllMovies()

        return if (source.isNotEmpty()) MovieEntityMapper.from(source)
        else emptyList()
    }

    private suspend fun remoteGetDiscoverMoviesAsync(): List<Movie>? {
        val response = endpoint.getDiscoverMovies()

        return response.body()?.let {
            if (response.isSuccessful) MovieResultMapper.from(it.results ?: emptyList())
            else emptyList()
        }
    }

    private suspend fun remoteGetTvShowsAsync(): List<Movie>? {
        val response = endpoint.getTvShows()

        return response.body()?.let {
            //            movieDao.upsert(MovieEntityMapper.from<MovieResponse, MovieEntity>(it.results ?: emptyList()))
            if (response.isSuccessful) MovieResultMapper.from(it.results ?: emptyList())
            else emptyList()
        }
    }

    override fun searchMovies(query: String): List<Movie>? = runBlocking {
        MovieEntityMapper.from<MovieEntity, Movie>(movieDao.searchMovies(query))
    }

    override fun getLocalFavouriteMovies(): List<Favourite> = runBlocking {
        try {
            // TODO(mochadwi): 2019-11-01 Don't use mapper here
            favouriteDao.getFavourites()
                    .filter { it.name.isNullOrBlank() }
                    .map { FavouriteDataMapper.from(it) }
        } catch (e: IllegalStateException) {
            emptyList<Favourite>()
        }
    }

    override fun getLocalFavouriteTv(): List<Favourite>? = runBlocking {
        try {
            // TODO(mochadwi): 2019-11-01 Don't use mapper here
            favouriteDao.getFavourites()
                    .filter { it.title.isNullOrBlank() }
                    .map { FavouriteDataMapper.from(it) }
        } catch (e: IllegalStateException) {
            emptyList<Favourite>()
        }
    }

    override fun addToLocalFavourite(model: Favourite): Boolean = runBlocking {
        try {
            favouriteDao.upsert(FavouriteDataMapper.from(model))
            true
        } catch (e: IllegalAccessException) {
            false
        }
    }

    override fun deleteFromLocalFavouriteById(id: Int): Boolean = runBlocking {
        try {
            val updatedRowsCount = favouriteDao.deleteFavouriteById(id)
            updatedRowsCount > 0
        } catch (e: IllegalAccessException) {
            false
        }
    }
}