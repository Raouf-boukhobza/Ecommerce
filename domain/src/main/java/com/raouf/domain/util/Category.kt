package com.raouf.domain.util

sealed class Category(val name : String){
    data object MenClothes : Category(name = "men's clothing")
    data object WomenClothes : Category(name = "women's clothing")
    data object Jewelery : Category(name = "jewelery")
    data object Electronics : Category(name = "electronics")
}