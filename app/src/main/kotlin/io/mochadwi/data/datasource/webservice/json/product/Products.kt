package io.mochadwi.data.datasource.webservice.json.product

import io.mochadwi.domain.product.Images
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
            @Transient
            val images: Images = Images(),
            val price: Int,
            @Optional
            @SerialName("product_id")
            val productId: Int,
            @Optional
            @SerialName("product_name")
            val productName: String,
            val stock: Int
        )
    }
}