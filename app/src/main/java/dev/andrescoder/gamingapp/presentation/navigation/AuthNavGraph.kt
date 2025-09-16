package dev.andrescoder.gamingapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.andrescoder.gamingapp.presentation.screens.login.LoginScreen
import dev.andrescoder.gamingapp.presentation.screens.signup.SignupScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route= Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AuthScreen.Signup.route) {
            SignupScreen(navController = navController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object Signup : AuthScreen("signup")
}