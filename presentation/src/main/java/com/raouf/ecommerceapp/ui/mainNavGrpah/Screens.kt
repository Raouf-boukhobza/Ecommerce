package com.raouf.ecommerceapp.ui.mainNavGrpah

sealed class Screens(val route : String) {
    data object Home : Screens(route = "Home")
}