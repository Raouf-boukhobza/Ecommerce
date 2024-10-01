package com.raouf.ecommerceapp.ui.home

import com.raouf.domain.model.Product
import com.raouf.domain.util.Category

data class HomeScreenState(
    val error : String = "",
    val isLoading : Boolean = false,
    val category : Category = Category.Watches,
    val productsList: List<Product> = emptyList()
)