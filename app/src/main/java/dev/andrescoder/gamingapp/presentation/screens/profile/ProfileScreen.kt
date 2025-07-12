package dev.andrescoder.gamingapp.presentation.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.presentation.components.DefaultButton
import dev.andrescoder.gamingapp.presentation.navigation.AppScreen
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {},
        bottomBar = {}
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            DefaultButton(
                modifier = Modifier,
                text = "Cerrar Sesión",
                onClick = {
                    coroutineScope.launch {
                        viewModel.logout()
                        navHostController.navigate(route = AppScreen.Login.route){
                            popUpTo(AppScreen.Profile.route) { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}