package dev.andrescoder.gamingapp.presentation.screens.my_posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.presentation.components.ProgressBar
import dev.andrescoder.gamingapp.presentation.screens.my_posts.MyPostsViewModel
import dev.andrescoder.gamingapp.presentation.screens.posts.PostsViewModel

@Composable
fun GetPostsByIdUser(
    navController: NavHostController,
    viewModel: MyPostsViewModel = hiltViewModel(),
) {

    when (val response = viewModel.postsResponse) {
        is Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            MyPostsContent(navController = navController, posts = response.data)
        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                response.exception?.message ?: "Error desconocido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }

}