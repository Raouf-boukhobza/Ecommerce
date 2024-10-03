package com.raouf.ecommerceapp.ui.detail

import com.raouf.domain.model.Product

data class DetailScreenState(
    val isLoading: Boolean = false,
    val selectedProduct: Product? = null,
    val selectedImageIndex : Int = 0
)