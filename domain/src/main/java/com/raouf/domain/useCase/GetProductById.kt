package com.raouf.domain.useCase

import com.raouf.domain.repository.ProductRepository

class GetProductById (private val repository: ProductRepository) {
    suspend  operator fun invoke(id : Int) = repository.getProductById(id)
}