package dev.andrescoder.gamingapp.presentation.screens.my_posts

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPostsScreen(navController: NavHostController) {

    Scaffold(
        content = {
            Text(text = "My Posts Screen")
        }
    )
}