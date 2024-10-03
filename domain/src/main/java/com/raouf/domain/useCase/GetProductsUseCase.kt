package com.raouf.domain.useCase

import com.raouf.domain.model.Product
import com.raouf.domain.repository.ProductRepository
import com.raouf.domain.util.Category
import com.raouf.domain.util.Resource

class GetProductsUseCase (
    private val repository: ProductRepository
) {
    suspend fun execute (category: String) = repository.getProductList(category = category)

}