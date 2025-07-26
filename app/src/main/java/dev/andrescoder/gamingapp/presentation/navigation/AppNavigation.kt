package dev.andrescoder.gamingapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.andrescoder.gamingapp.presentation.screens.login.LoginScreen
import dev.andrescoder.gamingapp.presentation.screens.profile.ProfileScreen
import dev.andrescoder.gamingapp.presentation.screens.profile_edit.ProfileEditScreen
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
        composable(route = AppScreen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(
            route = AppScreen.ProfileEdit.route,
            arguments = listOf(navArgument("user") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("user")?.let { user ->
                ProfileEditScreen(navController, user = user)
            }

        }
    }
}