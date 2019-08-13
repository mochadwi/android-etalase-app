package io.mochadwi.domain.repository

import io.mochadwi.domain.post.PostModel
import io.mochadwi.domain.product.Product
import kotlinx.coroutines.Deferred

/**
 * @author Mochamad Iqbal Dwi Cahyo, (moch.iqbal@dana.id)
 * @version AppRepository.kt, v 0.1 2019-08-13 23:45 by Mochamad Iqbal Dwi Cahyo
 */

/**
 * App repository
 */
interface AppRepository {

    fun getPostsAsync(): Deferred<List<PostModel>?>
    fun getProductsAsync(): Deferred<List<Product>?>
    fun searchPostsAsync(query: String): Deferred<List<PostModel>?>
}