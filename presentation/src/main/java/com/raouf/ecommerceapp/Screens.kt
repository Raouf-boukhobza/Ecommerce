package com.raouf.ecommerceapp

sealed class Screens(val route : String) {
    data object Home : Screens(route = "Home")
}