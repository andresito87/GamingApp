package dev.andrescoder.gamingapp.presentation.screens.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.presentation.screens.login.components.LoginBottomBar
import dev.andrescoder.gamingapp.presentation.screens.login.components.LoginContent

@Composable
fun LoginScreen(
    paddingValues: PaddingValues? = null,
    navController: NavHostController? = null,
) {
    Scaffold(
        modifier = Modifier
            .padding(paddingValues = paddingValues ?: PaddingValues()),
        topBar = {},
        content = {
            LoginContent(navController, it)
        },
        bottomBar = {
            LoginBottomBar(navController)
        }
    )
}

