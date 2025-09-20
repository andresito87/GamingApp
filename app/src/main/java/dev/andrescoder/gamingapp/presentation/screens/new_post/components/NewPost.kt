package dev.andrescoder.gamingapp.presentation.screens.new_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.presentation.components.ProgressBar
import dev.andrescoder.gamingapp.presentation.navigation.Graph
import dev.andrescoder.gamingapp.presentation.screens.new_post.NewPostViewModel

@Composable
fun NewPost(viewModel: NewPostViewModel = hiltViewModel()) {
    when (val response = viewModel.createPostResponse) {
        is Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            viewModel.clearForm()
            Toast.makeText(
                LocalContext.current,
                "Publicación creada con éxito",
                Toast.LENGTH_LONG
            ).show()
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