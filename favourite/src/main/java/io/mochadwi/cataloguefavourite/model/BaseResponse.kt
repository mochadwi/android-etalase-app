package io.mochadwi.cataloguefavourite.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class BaseResponse<T> {

    @SerializedName("dates")
    var dates: Dates? = null

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("results")
    var results: List<T>? = null

    @SerializedName("total_results")
    var totalResults: Int = 0

    override fun toString(): String {
        return Gson().toJson(this)
    }

    inner class Dates {

        @SerializedName("maximum")
        var maximum: String? = null

        @SerializedName("minimum")
        var minimum: String? = null

        override fun toString(): String {
            return Gson().toJson(this)
        }
    }
}