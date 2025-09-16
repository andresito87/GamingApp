package dev.andrescoder.gamingapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.presentation.components.ProgressBar
import dev.andrescoder.gamingapp.presentation.navigation.AuthScreen
import dev.andrescoder.gamingapp.presentation.navigation.Graph
import dev.andrescoder.gamingapp.presentation.screens.login.LoginViewModel

@Composable
fun Login(navController: NavHostController?, viewModel: LoginViewModel = hiltViewModel()) {
    when (val loginResponse = viewModel.loginResponse) {
        is Response.Loading -> {
            ProgressBar() // Personalized Loading Component
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                navController!!.navigate(route = Graph.HOME) {
                    popUpTo(Graph.AUTHENTICATION) { inclusive = true }
                }
            }
        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                loginResponse.exception?.message ?: "Error desconocido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }
}