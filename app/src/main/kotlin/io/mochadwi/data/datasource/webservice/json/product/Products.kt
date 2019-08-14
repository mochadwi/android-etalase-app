package io.mochadwi.data.datasource.webservice.json.product

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    val `data`: DataResponse,
    val message: String,
    val status: String
) {

    @Serializable
    data class DataResponse(
        val banner: BannerResponse,
        val products: List<ProductResponse>
    ) {

        @Serializable
        data class BannerResponse(
            val image: String
        )

        @Serializable
        data class ProductResponse(
            val description: String,
            val images: ImagesResponse,
            val price: Int,
            @Optional
            @SerialName("product_id")
            val productId: Int,
            @Optional
            @SerialName("product_name")
            val productName: String,
            val stock: Int
        ) {

            @Serializable
            data class ImagesResponse(
                val large: String,
                val thumbnail: String
            )
        }
    }
}