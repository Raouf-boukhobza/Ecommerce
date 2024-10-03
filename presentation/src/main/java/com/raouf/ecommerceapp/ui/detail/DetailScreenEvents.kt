package com.raouf.ecommerceapp.ui.detail

sealed interface DetailScreenEvents {

    data class GetProduct(val id : Int) : DetailScreenEvents
    data class SelectImage(val index : Int) : DetailScreenEvents
}