package io.mochadwi.data.datasource.network.kotlinx.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(

    @SerialName("page")
    var page: Int = 0,

    @SerialName("total_pages")
    var totalPages: Int = 0,

    @SerialName("results")
    var results: List<T>? = null,

    @SerialName("total_results")
    var totalResults: Int = 0
)