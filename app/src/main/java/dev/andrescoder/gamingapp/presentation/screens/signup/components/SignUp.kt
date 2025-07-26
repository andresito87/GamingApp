package dev.andrescoder.gamingapp.presentation.screens.signup.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.presentation.components.ProgressBar
import dev.andrescoder.gamingapp.presentation.navigation.AppScreen
import dev.andrescoder.gamingapp.presentation.screens.signup.SignupViewModel

@Composable
fun SignUp(
    viewModel: SignupViewModel = hiltViewModel<SignupViewModel>(),
    navController: NavHostController? = null,
) {
    when (val signupResponse = viewModel.signupResponse) {
        is Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit) {
                viewModel.createUser()
                navController?.popBackStack(AppScreen.Login.route, true) // Clear the back stack
                navController?.navigate(AppScreen.Profile.route)
            }
        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                signupResponse.exception?.message ?: "Error desconocido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }
}