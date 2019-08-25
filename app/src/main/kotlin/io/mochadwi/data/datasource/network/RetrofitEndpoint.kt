package io.mochadwi.data.datasource.network

import io.mochadwi.data.datasource.network.kotlinx.response.BaseResponse
import io.mochadwi.util.helper.AppHelper.Const.ENDPOINT_DISCOVER_MOVIES
import io.mochadwi.util.helper.AppHelper.Const.ENDPOINT_POSTS
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

interface RetrofitEndpoint {
    @GET(ENDPOINT_POSTS)
    fun getMoviesAsync(): Deferred<List<MovieResponse>>

    @GET(ENDPOINT_DISCOVER_MOVIES)
    suspend fun getDiscoverMoviesAsync(
        @Query("api_key") apiKey: String = "334879b2c8dc36a9f2c64f7bd4f0c91d"
    ): Response<BaseResponse<MovieResponse>>
}