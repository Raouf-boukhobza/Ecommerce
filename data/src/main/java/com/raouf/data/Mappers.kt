package com.raouf.data

import com.raouf.data.remote.ProductDtoItem
import com.raouf.domain.model.Product
import com.raouf.domain.model.Rating

fun ProductDtoItem.toProduct() : Product{
    return Product(
        id = id,
        description = description,
        category = category,
        image = image,
        price = price,
        title = title,
        rating = Rating(rate = rating.rate , count = rating.count)
    )
}