package dev.andrescoder.gamingapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.R
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.presentation.components.DefaultButton
import dev.andrescoder.gamingapp.presentation.components.DefaultTextField
import dev.andrescoder.gamingapp.presentation.navigation.AppScreen
import dev.andrescoder.gamingapp.presentation.screens.login.LoginViewModel
import dev.andrescoder.gamingapp.presentation.ui.theme.Darkgray500
import dev.andrescoder.gamingapp.presentation.ui.theme.Red500
import kotlinx.coroutines.launch

@Composable
fun LoginContent(
    navController: NavHostController?,
    paddingValues: PaddingValues,
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
) {
    val loginFlow = viewModel.loginFlow.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(Red500)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.height(130.dp),
                    painter = painterResource(id = R.drawable.control),
                    contentDescription = "Mando Xbox 360"
                )
                Text(
                    text = "Gaming App",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        Card(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 280.dp, bottom = 30.dp),
            colors = CardDefaults.cardColors(containerColor = Darkgray500),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(top = 25.dp, bottom = 20.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "ACCESO",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Por favor inicia sesión para continuar",
                    fontSize = 18.sp,
                    color = Color.White
                )
                DefaultTextField(
                    modifier = Modifier
                        .padding(top = 25.dp, start = 20.dp, end = 30.dp),
                    value = viewModel.email.value,
                    onValueChange = { viewModel.email.value = it },
                    label = "Correo electrónico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    contentDescription = "Introduce email",
                    errorMsg = viewModel.emailErrMsg.value,
                    validateField = { viewModel.validateEmail() }
                )
                DefaultTextField(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 20.dp, end = 30.dp),
                    value = viewModel.password.value,
                    onValueChange = { viewModel.password.value = it },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    contentDescription = "Introduce contraseña",
                    errorMsg = viewModel.passwordErrMsg.value,
                    validateField = { viewModel.validatePassword() }
                )
                DefaultButton(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 60.dp, end = 60.dp)
                        .fillMaxWidth(),
                    text = "Iniciar sesión",

                    onClick = {
                        coroutineScope.launch {
                            viewModel.login()
                        }
                    },
                    enabled = viewModel.isEnabledLoginButton
                )
            }
        }

        // 🌀 Animación de carga sobre toda la UI
        if (loginFlow.value is Response.Loading) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

    loginFlow.value.let {
        when (it) {
            is Response.Success -> {
                LaunchedEffect(Unit) {
                    navController!!.navigate(route = AppScreen.Profile.route) {
                        popUpTo(AppScreen.Login.route) { inclusive = true }
                    }
                }
            }

            is Response.Failure -> {
                Toast.makeText(
                    context,
                    it.exception?.message ?: "Error desconocido",
                    Toast.LENGTH_LONG
                ).show()
            }

            else -> {}
        }
    }
}


