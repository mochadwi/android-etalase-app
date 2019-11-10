package io.mochadwi.data.datasource.mock.jsonreader

import io.mochadwi.data.datasource.network.RetrofitEndpoint
import io.mochadwi.data.datasource.network.kotlinx.response.BaseResponse
import io.mochadwi.data.datasource.network.kotlinx.response.movie.MovieResponse
import retrofit2.Response

/**
 * Read json files and render etalase date
 */
class LocalFileDataSource(val jsonReader: JsonReader, val delayed: Boolean) : RetrofitEndpoint {


    override suspend fun getDiscoverMovies(
        apiKey: String
    ): Response<BaseResponse<MovieResponse>> {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getTvShows(apiKey: String): Response<BaseResponse<MovieResponse>> {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun searchMovies(apiKey: String, query: String): Response<BaseResponse<MovieResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val DEFAULT_CITY = "toulouse"
    }
}