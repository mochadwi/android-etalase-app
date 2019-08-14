package io.mochadwi.data.datasource.network.kotlinx.param

import kotlinx.serialization.Serializable

/**
 *
 * In syaa Allah created or modified by @mochadwi
 * On 10/08/19 for etalase-app
 */

@Serializable
data class QueryParam(
    val q: String = "",
    val page: Int = 1,
    val per_page: Int = 15
)