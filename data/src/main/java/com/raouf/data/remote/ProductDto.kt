package com.raouf.data.remote

data class ProductDto(
    val limit: Int,
    val products: List<ProductDtoItem>,
    val skip: Int,
    val total: Int
)