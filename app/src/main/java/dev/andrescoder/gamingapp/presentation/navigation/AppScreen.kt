package dev.andrescoder.gamingapp.presentation.navigation

sealed class AppScreen(val route: String) {
    object Login : AppScreen("login")
    object Signup : AppScreen("signup")
}