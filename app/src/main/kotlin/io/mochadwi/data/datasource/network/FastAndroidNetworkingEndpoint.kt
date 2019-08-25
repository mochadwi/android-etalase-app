package io.mochadwi.data.datasource.network

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */
// TODO(mochamadiqbaldwicahyo): 2019-08-15 This only a replica to switching framework
interface FastAndroidNetworkingEndpoint {

    fun getMoviesAsync(): List<MovieResponse>
}