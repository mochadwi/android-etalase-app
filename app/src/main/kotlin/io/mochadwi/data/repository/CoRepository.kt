package io.mochadwi.data.repository

import io.mochadwi.data.datasource.local.room.PostDao
import io.mochadwi.data.datasource.local.room.PostEntity
import io.mochadwi.data.datasource.network.RetrofitEndpoint
import io.mochadwi.data.mapper.MovieResultMapper
import io.mochadwi.domain.model.movie.Movie
import io.mochadwi.domain.model.post.PostModel
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
    private val postDao: PostDao
) : AppRepository {

    override fun getPostsAsync(): Deferred<List<PostModel>?> = coroutineAsync(IO) {
        val local = localGetPostsAsync().await() ?: emptyList()
        val remote = remoteGetPostsAsync().await() ?: emptyList()

        if ((local sameContentWith remote).default) local
        else remote
    }

    private fun localGetPostsAsync(): Deferred<List<PostModel>?> = coroutineAsync(IO) {
        postDao.getAllPosts().map {
            PostModel.from(it)
        }
    }

    // TODO(mochamadiqbaldwicahyo): 2019-08-13 Don't do transformation / mapper here in the repo
    private fun remoteGetPostsAsync(): Deferred<List<PostModel>?> = coroutineAsync(IO) {
        val result = endpoint.getPostsAsync().await()
        result.map {
            postDao.upsert(PostEntity.from(it))
            PostModel.from(it)
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
                // TODO(mochamadiqbaldwicahyo): 2019-08-23 Ugly try catch change into better handling?
//                try {
                MovieResultMapper.from(it.results ?: emptyList())
//                } catch (e: NullPointerException) {
//                    // TODO(mochamadiqbaldwicahyo): 2019-08-23 Print the corresponding stacktrace?
//                    null
//                }
            } else {
                emptyList()
            }
        }
    }

    override fun searchPostsAsync(query: String): Deferred<List<PostModel>?> = coroutineAsync(IO) {
        postDao.searchPosts(query).map {
            PostModel.from(it)
        }
    }
}