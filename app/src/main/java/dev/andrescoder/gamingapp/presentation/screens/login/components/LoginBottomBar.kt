package dev.andrescoder.gamingapp.presentation.screens.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.presentation.navigation.AppScreen

@Composable
fun LoginBottomBar(navController: NavHostController?){
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¿No tienes cuenta?",
            fontSize = 18.sp,
            color = Color.Gray
        )
        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable {
                    navController?.navigate(
                        route = AppScreen.Signup.route
                    )
                },
            text = "Regístrate",
            fontSize = 18.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )
    }
}