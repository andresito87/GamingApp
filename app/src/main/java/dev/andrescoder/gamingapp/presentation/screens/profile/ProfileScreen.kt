package dev.andrescoder.gamingapp.presentation.screens.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.presentation.screens.profile.components.ProfileContent


@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {},
        bottomBar = {}
    ) { paddingValuesIgnored ->
        ProfileContent(
            navController = navController,
            modifier = Modifier.padding(paddingValuesIgnored)
        )
    }
}