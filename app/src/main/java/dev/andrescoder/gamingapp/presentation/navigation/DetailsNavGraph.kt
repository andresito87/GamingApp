package dev.andrescoder.gamingapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dev.andrescoder.gamingapp.presentation.screens.new_post.NewPostScreen
import dev.andrescoder.gamingapp.presentation.screens.profile_update.ProfileEditScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileUpdate.route
    ) {

        composable(route = DetailsScreen.NewPost.route) {
            NewPostScreen(navController = navController)
        }

        composable(
            route = DetailsScreen.ProfileUpdate.route,
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

sealed class DetailsScreen(val route: String) {
    object NewPost : DetailsScreen("posts/new")
    object ProfileUpdate : DetailsScreen("profile/edit/{user}") {
        fun passUser(user: String) = "profile/edit/$user"
    }
}
