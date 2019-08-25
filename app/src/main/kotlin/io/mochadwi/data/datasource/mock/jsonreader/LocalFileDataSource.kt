package io.mochadwi.data.datasource.mock.jsonreader

import io.mochadwi.data.datasource.network.RetrofitEndpoint
import io.mochadwi.data.datasource.network.kotlinx.response.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

/**
 * Read json files and render etalase date
 */
class LocalFileDataSource(val jsonReader: JsonReader, val delayed: Boolean) : RetrofitEndpoint {

    override fun getMoviesAsync(): Deferred<List<MovieResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getDiscoverMoviesAsync(
        apiKey: String
    ): Response<BaseResponse<MovieResponse>> {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val DEFAULT_CITY = "toulouse"
    }
}