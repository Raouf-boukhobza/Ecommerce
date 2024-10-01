package com.raouf.data.remote

data class ProductDtoItem(
    val brand: String?,
    val category: String?,
    val description: String?,
    val discountPercentage: Double?,
    val id: Int?,
    val images: List<String>?,
    val minimumOrderQuantity: Int?,
    val price: Double?,
    val rating: Double?,
    val shippingInformation: String?,
    val stock: Int?,
    val tags: List<String>?,
    val title: String?,
    val warrantyInformation: String?,
)