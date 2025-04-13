package dev.andrescoder.gamingapp.presentation.screens.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignupScreen(
    navController: NavHostController? = null
) {
    Scaffold(
        topBar = {},
        content = {
            Text(text = "SignupScreen")
        },
        bottomBar = {}
    )
}