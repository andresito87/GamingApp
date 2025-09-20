package dev.andrescoder.gamingapp.presentation.screens.new_post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.presentation.components.DefaultButton
import dev.andrescoder.gamingapp.presentation.components.DefaultTopBar
import dev.andrescoder.gamingapp.presentation.screens.new_post.components.NewPost
import dev.andrescoder.gamingapp.presentation.screens.new_post.components.NewPostContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewPostScreen(
    navController: NavHostController,
    viewModel: NewPostViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo Post",
                backIconAvailable = true,
                navController = navController
            )
        },
        content = {
            NewPostContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(50.dp)
                    .fillMaxWidth(),
                text = "PUBLICAR",
                onClick = { viewModel.onAddNewPost() },
            )
        }
    )
    NewPost()
}