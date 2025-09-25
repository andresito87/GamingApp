package dev.andrescoder.gamingapp.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.use_cases.posts.GetPosts
import dev.andrescoder.gamingapp.presentation.components.ProgressBar
import dev.andrescoder.gamingapp.presentation.navigation.Graph
import dev.andrescoder.gamingapp.presentation.screens.posts.PostsViewModel

@Composable
fun LikePosts(
    viewModel: PostsViewModel = hiltViewModel(),
) {

    when (val response = viewModel.likeResponse) {
        is Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {}

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