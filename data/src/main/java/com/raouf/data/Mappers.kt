package com.raouf.data

import com.raouf.data.remote.ProductDtoItem
import com.raouf.domain.model.Product
import java.util.Locale

fun ProductDtoItem.toProduct(): Product {
    return Product(
        id = id ?: 0,
        description = description ?: "",
        category = category?: "",
        images = images?: emptyList(),
        price = price ?: 0.0,
        title = title?: "",
        shippingInformation = shippingInformation?: "",
        stock = stock?: 0,
        brand = brand?: "",
        tags = tags ?: emptyList(),
        priceAfterDiscount = discountPercentage?.let {
            val discountedPrice = price?.minus((price * it) / 100)
            String.format( Locale.UK , "%.2f", discountedPrice)
        } ?: "",
        rating = rating?: 0.0,
        warrantyInformation = warrantyInformation?: "",
        discountPercentage = discountPercentage?: 0.0,
        minimumOrderQuantity = minimumOrderQuantity?: 0
        )
}