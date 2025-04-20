package dev.andrescoder.gamingapp.presentation.screens.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.presentation.components.DefaultTopBar
import dev.andrescoder.gamingapp.presentation.screens.signup.components.SignupContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignupScreen(
    paddingValues: PaddingValues? = null,
    navController: NavHostController? = null
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo usuario",
                backIconAvailable = true,
                navController
            )
        },
        content = {
            SignupContent(it, navController)
        },
        bottomBar = {}
    )
}