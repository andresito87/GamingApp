package dev.andrescoder.gamingapp.presentation.screens.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.andrescoder.gamingapp.presentation.screens.login.components.LoginBottomBar
import dev.andrescoder.gamingapp.presentation.screens.login.components.LoginContent
import dev.andrescoder.gamingapp.presentation.ui.theme.GamingAppTheme

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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    GamingAppTheme (
        darkTheme = true
    ){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen(
                paddingValues = PaddingValues(),
                navController = rememberNavController()
            )
        }
    }
}

