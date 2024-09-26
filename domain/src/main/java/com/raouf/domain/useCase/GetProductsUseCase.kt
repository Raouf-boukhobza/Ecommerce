package com.raouf.domain.useCase

import com.raouf.domain.repository.ProductRepository

class GetProductsUseCase (
    private val repository: ProductRepository
) {
    suspend fun execute () = repository.getProduct()
}