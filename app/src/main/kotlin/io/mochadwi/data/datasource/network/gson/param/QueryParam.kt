package io.mochadwi.data.datasource.network.gson.param

import com.google.gson.annotations.SerializedName

/**
 *
 * In syaa Allah created or modified by @mochadwi
 * On 10/08/19 for etalase-app
 */

// TODO(mochamadiqbaldwicahyo): 2019-08-15 This only a replica to switching framework
data class QueryParam(
    val q: String = "",
    val page: Int = 1,
    @SerializedName("per_page")
    val perPage: Int = 15
)