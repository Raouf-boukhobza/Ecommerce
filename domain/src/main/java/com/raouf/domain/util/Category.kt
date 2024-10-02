package com.raouf.domain.util


// I use this categories class because i don't wanna use all the categories in the api

sealed class Category(val name: String, val slug: String) {
    data object MenWatches : Category(name = "Watches", slug = "mens-watches")
    data object womenWatches : Category(name = "Watches", slug = "womens-watches")
    data object Shoes : Category(name = "Mens Shoes", slug = "mens-shoes")
    data object Sport : Category( name = "Sports Accessories",slug = "sports-accessories")
    data object Tops : Category(slug = "tops", name = "Tops")
    data object Vehicle : Category(slug = "vehicle", name = "Vehicle")
    data object HomeDecoration: Category(name = "Home Decoration" , slug ="home-decoration" )

}