package io.mochadwi.data.datasource.local.jsonreader

import io.mochadwi.data.datasource.network.RetrofitEndpoint
import io.mochadwi.data.datasource.network.kotlinx.response.post.PostResponse
import io.mochadwi.data.datasource.network.kotlinx.response.product.ProductsResponse
import kotlinx.coroutines.Deferred

/**
 * Read json files and render etalase date
 */
class LocalFileDataSource(val jsonReader: JsonReader, val delayed: Boolean) : RetrofitEndpoint {
    override fun getPostsAsync(): Deferred<List<PostResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProductsAsync(): Deferred<ProductsResponse> {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val DEFAULT_CITY = "toulouse"
    }
}