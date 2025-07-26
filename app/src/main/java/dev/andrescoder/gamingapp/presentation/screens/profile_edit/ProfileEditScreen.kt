package dev.andrescoder.gamingapp.presentation.screens.profile_edit

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.presentation.components.DefaultTopBar
import dev.andrescoder.gamingapp.presentation.screens.profile_edit.components.ProfileEditContent

@Composable
fun ProfileEditScreen(
    navController: NavHostController,
    user: String,
) {

    Log.d("ProfileEditScreen", "User data: $user")

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar usuario",
                backIconAvailable = true,
                navController
            )
        },
        content = {
            ProfileEditContent(it, navController)
        },
        bottomBar = {}
    )
}