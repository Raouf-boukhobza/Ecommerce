package com.raouf.domain.useCase

import com.raouf.domain.repository.ProductRepository

class GetCategoriesUseCase(
    private val repository: ProductRepository
) {
    suspend fun getCategories() = repository.getCategories()

}