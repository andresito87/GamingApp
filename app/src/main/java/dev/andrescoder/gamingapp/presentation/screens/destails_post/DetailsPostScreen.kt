package dev.andrescoder.gamingapp.presentation.screens.destails_post

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.presentation.screens.destails_post.components.DetailsPostContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsPostScreen(navController: NavHostController, post: String) {
    Scaffold(
        content = {
            DetailsPostContent(navController = navController)
        }
    )
}

