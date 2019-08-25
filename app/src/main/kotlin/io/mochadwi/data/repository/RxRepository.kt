package io.mochadwi.data.repository

import io.mochadwi.data.datasource.local.realm.MovieDao
import io.mochadwi.data.datasource.network.FastAndroidNetworkingEndpoint

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

// TODO(mochamadiqbaldwicahyo): 2019-08-15 This only a replica to switching framework
class RxRepository(
    private val endpoint: FastAndroidNetworkingEndpoint,
    private val movieDao: MovieDao
)