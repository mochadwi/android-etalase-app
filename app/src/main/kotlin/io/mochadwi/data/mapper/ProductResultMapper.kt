package io.mochadwi.data.mapper

import io.mochadwi.data.datasource.webservice.json.product.ProductsResponse
import io.mochadwi.data.datasource.webservice.json.product.ProductsResponse.DataResponse.ProductResponse.ImagesResponse
import io.mochadwi.domain.product.Images
import io.mochadwi.domain.product.Product

/**
 * @author Mochamad Iqbal Dwi Cahyo, (moch.iqbal@dana.id)
 * @version ProductResultMapper.kt, v 0.1 2019-08-13 23:16 by Mochamad Iqbal Dwi Cahyo
 */

object ProductResultMapper {

    fun from(products: ProductsResponse): List<Product> = products.data.products.map {
        with(it) {
            Product(description, from(images), price, productId, productName, stock)
        }
    }

    fun from(image: ImagesResponse): Images = with(image) {
        Images(large, thumbnail)
    }
}