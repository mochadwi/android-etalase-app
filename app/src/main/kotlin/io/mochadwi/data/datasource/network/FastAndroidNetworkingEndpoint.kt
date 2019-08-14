package io.mochadwi.data.datasource.network

import io.mochadwi.data.datasource.network.kotlinx.response.post.PostResponse
import io.mochadwi.data.datasource.network.kotlinx.response.product.ProductsResponse
import io.mochadwi.util.helper.AppHelper.Const.ENDPOINT_POSTS
import io.mochadwi.util.helper.AppHelper.Const.ENDPOINT_PRODUCTS
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */
// TODO(mochamadiqbaldwicahyo): 2019-08-15 This only a replica to switching framework
interface FastAndroidNetworkingEndpoint {

    @GET(ENDPOINT_POSTS)
    fun getPostsAsync(): Deferred<List<PostResponse>>

    @GET(ENDPOINT_PRODUCTS)
    fun getProductsAsync(): Deferred<ProductsResponse>
}