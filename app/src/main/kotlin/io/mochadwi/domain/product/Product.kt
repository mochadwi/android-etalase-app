package io.mochadwi.domain.product

data class Product(
    val description: String = "",
    val images: Images = Images(),
    val price: Int = 0,
    val productId: Int = 0,
    val productName: String = "",
    val stock: Int = 0
)