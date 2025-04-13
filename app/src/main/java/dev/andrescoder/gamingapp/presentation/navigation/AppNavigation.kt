package dev.andrescoder.gamingapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.andrescoder.gamingapp.presentation.screens.login.LoginScreen
import dev.andrescoder.gamingapp.presentation.screens.signup.SignupScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route
    ) {
        composable(route = AppScreen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AppScreen.Signup.route) {
            SignupScreen(navController = navController)
        }
    }
}