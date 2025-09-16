package dev.andrescoder.gamingapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.RowScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.andrescoder.gamingapp.presentation.navigation.HomeBottomBarNavGraph
import dev.andrescoder.gamingapp.presentation.navigation.HomeBottomBarScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {
        HomeBottomBarNavGraph(navController = navController)
    }
}

@Composable
private fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        HomeBottomBarScreen.Posts,
        HomeBottomBarScreen.MyPosts,
        HomeBottomBarScreen.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val selected = currentDestination
        ?.hierarchy
        ?.any { it.route == screen.route } == true

    NavigationBarItem(
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.title
            )
        },
        label = { Text(text = screen.title) },
        colors = NavigationBarItemDefaults.colors()
    )
}
