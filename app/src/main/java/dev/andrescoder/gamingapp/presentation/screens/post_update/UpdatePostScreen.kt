package dev.andrescoder.gamingapp.presentation.screens.post_update

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
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
import dev.andrescoder.gamingapp.presentation.screens.post_update.components.UpdatePost
import dev.andrescoder.gamingapp.presentation.screens.post_update.components.UpdatePostContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UpdatePostScreen(
    navController: NavHostController,
    post: String,
    viewModel: UpdatePostViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar Post",
                backIconAvailable = true,
                navController = navController
            )
        },
        content = { innerPadding ->
            UpdatePostContent(
                modifier = Modifier.padding(innerPadding)
            )
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .imePadding()
                    .padding(10.dp)
                    .height(50.dp),
                text = "Actualizar",
                onClick = { viewModel.onUpdatePost() },
            )
        }
    )
    UpdatePost()
}
