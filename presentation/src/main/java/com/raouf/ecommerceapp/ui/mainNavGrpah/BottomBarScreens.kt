package com.raouf.ecommerceapp.ui.mainNavGrpah

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonOutline
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.twotone.Home
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens (
    val title : String,
    val selectedIcon : ImageVector,
    val  unselectedIcon : ImageVector,
    val selectedColor : Color
){

    data object Home : BottomBarScreens(
        title = "Home",
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home,
        selectedColor = Color.Black
    )

    data object Search : BottomBarScreens(
        title = "Search",
        selectedIcon = Icons.Rounded.Search,
        unselectedIcon = Icons.Rounded.Search,
        selectedColor = Color.Black
    )

    data object Favorite: BottomBarScreens(
        title = "Favorite",
        selectedIcon = Icons.Rounded.Favorite,
        unselectedIcon = Icons.Rounded.FavoriteBorder,
        selectedColor = Color.Black
    )

    data object Profile : BottomBarScreens(
        title = "Profile",
        selectedIcon = Icons.Rounded.Person,
        unselectedIcon = Icons.Rounded.PersonOutline,
        selectedColor = Color.Black
    )
}