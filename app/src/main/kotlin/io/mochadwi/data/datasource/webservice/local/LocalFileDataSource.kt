package io.mochadwi.data.datasource.webservice.local

import io.mochadwi.data.datasource.webservice.AppWebDatasource
import io.mochadwi.data.datasource.webservice.json.post.PostResponse
import io.mochadwi.data.datasource.webservice.json.product.ProductsResponse
import kotlinx.coroutines.Deferred

/**
 * Read json files and render etalase date
 */
class LocalFileDataSource(val jsonReader: JsonReader, val delayed: Boolean) : AppWebDatasource {
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