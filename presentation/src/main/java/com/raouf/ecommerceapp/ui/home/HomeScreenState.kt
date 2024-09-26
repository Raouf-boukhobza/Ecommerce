package com.raouf.ecommerceapp.ui.home

import com.raouf.domain.model.Product

data class HomeScreenState(
    val error : String = "",
    val isLoading : Boolean = false,
    val productsList: List<Product> = emptyList()
)