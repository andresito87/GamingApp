package dev.andrescoder.gamingapp.presentation.screens.posts

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.presentation.screens.posts.components.GetPosts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(
    navController: NavHostController,
    viewModel: PostsViewModel = hiltViewModel(),
) {
    Scaffold(
        content = {
            GetPosts(navController = navController)
        }
    )
}