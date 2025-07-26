package dev.andrescoder.gamingapp.presentation.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.andrescoder.gamingapp.R
import dev.andrescoder.gamingapp.presentation.components.DefaultButton
import dev.andrescoder.gamingapp.presentation.navigation.AppScreen
import dev.andrescoder.gamingapp.presentation.screens.profile.ProfileViewModel
import dev.andrescoder.gamingapp.presentation.ui.theme.GamingAppTheme
import dev.andrescoder.gamingapp.presentation.ui.theme.Red500
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box() {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alpha = 0.5f
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Bienvenid@",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(55.dp))
                Image(
                    modifier = Modifier.size(115.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.height(55.dp))
        Text(
            text = viewModel.userData.username,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color.White
        )
        Text(
            text = viewModel.userData.email,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))
        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Editar datos",
            textColor = Color.Black,
            color = Color.White,
            icon = Icons.Default.Edit,
            onClick = {
                navController.navigate(
                    AppScreen.ProfileEdit.passUser(viewModel.userData.toJson())
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Cerrar Sesión",
            textColor = Color.White,
            color = Red500,
            onClick = {
                coroutineScope.launch {
                    viewModel.logout()
                    navController.navigate(route = AppScreen.Login.route) {
                        popUpTo(AppScreen.Profile.route) { inclusive = true }
                    }
                }
            }
        )
    }
}