package com.raouf.ecommerceapp.ui.mainNavGrpah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardBackspace
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raouf.ecommerceapp.ui.detail.DetailScreen
import com.raouf.ecommerceapp.ui.detail.DetailScreenViewModel
import com.raouf.ecommerceapp.ui.home.HomeScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainNavGraph() {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                navController = navController
            )
        },
        bottomBar = { BottomBar(navController) }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = BottomBarScreens.Home.title,
            modifier = Modifier.padding(padding)
        ) {
            composable(route = BottomBarScreens.Home.title) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate("Detail/$id")
                    }
                )
            }
            composable(route = BottomBarScreens.Search.title) {
                Text(text = "search")
            }
            composable(route = BottomBarScreens.Favorite.title) {
                Text(text = "favorite")
            }
            composable(route = BottomBarScreens.Profile.title) {
                Text(text = "profile")
            }
            composable(
                route = "Detail/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )
            ) {
                val detailViewModel: DetailScreenViewModel = koinViewModel()
                val id = it.arguments?.getInt("id") ?: -1
                DetailScreen(
                    id = id,
                    state = detailViewModel.detailState.collectAsState().value,
                    onEvent = detailViewModel::onEvent
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (currentDestination?.route?.startsWith("Detail") == true) {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Rounded.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )
                }

            },
            actions = {

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }


            }
        )

    }
}


@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        BottomBarScreens.Home,
        BottomBarScreens.Search,
        BottomBarScreens.Favorite,
        BottomBarScreens.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomDestinations = screens.any { it.title == currentDestination?.route }
    if (bottomDestinations) {
        Column {
            HorizontalDivider(
                thickness = (0.5).dp,
                modifier = Modifier.fillMaxWidth()
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                screens.forEach { screen ->
                    val selected = (currentDestination?.route == screen.title)
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(screen.title) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (selected) screen.selectedIcon
                                else screen.unselectedIcon,
                                contentDescription = null,
                                modifier = Modifier.size(30.dp),
                                tint = if (selected) screen.selectedColor else Color.Gray
                            )

                        }
                        Text(
                            text = screen.title,
                            fontStyle = FontStyle.Italic,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (selected) Color.Black
                            else Color.Gray
                        )
                    }
                }
            }
        }
    }


}












