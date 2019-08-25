package io.mochadwi.data.datasource.network.kotlinx.response

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @Optional
    @SerialName("status_code")
    val statusCode: Int = 0,
    @Optional
    @SerialName("status_message")
    val statusMessage: String = "",
    val success: Boolean = false
)