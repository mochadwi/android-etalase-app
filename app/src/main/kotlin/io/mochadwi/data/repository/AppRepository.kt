package io.mochadwi.data.repository

import io.mochadwi.data.datasource.room.PostDao
import io.mochadwi.data.datasource.room.PostEntity
import io.mochadwi.data.datasource.webservice.AppWebDatasource
import io.mochadwi.data.mapper.ProductResultMapper
import io.mochadwi.domain.post.PostModel
import io.mochadwi.domain.product.Product
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
class AppRepositoryImpl(
        private val appWebDatasource: AppWebDatasource,
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
        val result = appWebDatasource.getPostsAsync().await()
        result.map {
            postDao.upsert(PostEntity.from(it))
            PostModel.from(it)
        }
    }

    // TODO(mochamadiqbaldwicahyo): 2019-08-14 implement local data source
    override fun getProductsAsync(): List<Product>? = runBlocking {
        val remote = async { remoteGetProductsAsync() }
        val local = async { remoteGetProductsAsync() }

        local.await() ?: remote.await()
    }

    // TODO(mochamadiqbaldwicahyo): 2019-08-14 should we do something with deferred or just use suspend as well?
    private suspend fun remoteGetProductsAsync(): List<Product>? = ProductResultMapper.from(
        appWebDatasource.getProductsAsync().await()
    )

    override fun searchPostsAsync(query: String): Deferred<List<PostModel>?> = coroutineAsync(IO) {
        postDao.searchPosts(query).map {
            PostModel.from(it)
        }
    }
}