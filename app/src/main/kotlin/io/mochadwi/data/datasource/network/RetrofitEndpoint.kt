package io.mochadwi.data.datasource.network

import io.mochadwi.BuildConfig
import io.mochadwi.data.datasource.network.kotlinx.response.BaseResponse
import io.mochadwi.data.datasource.network.kotlinx.response.movie.MovieResponse
import io.mochadwi.util.helper.AppHelper.Const.ENDPOINT_DISCOVER_MOVIES
import io.mochadwi.util.helper.AppHelper.Const.ENDPOINT_DISCOVER_TV
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

const val ENDPOINT_SEARCH_MOVIES = "search/movie"

interface RetrofitEndpoint {
    @GET(ENDPOINT_DISCOVER_MOVIES)
    suspend fun getDiscoverMovies(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<BaseResponse<MovieResponse>>

    @GET(ENDPOINT_SEARCH_MOVIES)
    suspend fun searchMovies(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("query") query: String
    ): Response<BaseResponse<MovieResponse>>

    @GET(ENDPOINT_DISCOVER_TV)
    suspend fun getTvShows(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<BaseResponse<MovieResponse>>
}