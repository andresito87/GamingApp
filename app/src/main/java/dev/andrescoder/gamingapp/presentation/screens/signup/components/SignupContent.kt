package dev.andrescoder.gamingapp.presentation.screens.signup.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import dev.andrescoder.gamingapp.presentation.screens.signup.SignupViewModel
import dev.andrescoder.gamingapp.presentation.ui.theme.Darkgray500
import dev.andrescoder.gamingapp.presentation.ui.theme.Red500

@Composable
fun SignupContent(
    paddingValues: PaddingValues,
    navController: NavHostController?,
    viewModel: SignupViewModel = hiltViewModel(),
) {
    val signupFlow = viewModel.signupFlow.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .background(Red500)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.height(130.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "icon user"
                )
            }
        }

        Card(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 240.dp, bottom = 30.dp),
            colors = CardDefaults.cardColors(containerColor = Darkgray500),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "REGISTRO",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 20.dp, end = 20.dp),
                    text = "Rellena la información para crear una cuenta",
                    fontSize = 18.sp,
                    color = Color.White
                )
                DefaultTextField(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp),
                    value = viewModel.username.value,
                    onValueChange = { viewModel.username.value = it },
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    contentDescription = "Introduce nombre de usuario",
                    errorMsg = viewModel.usernameErrMsg.value,
                    validateField = { viewModel.validateUsername() }
                )
                DefaultTextField(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
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
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    value = viewModel.password.value,
                    onValueChange = { viewModel.password.value = it },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    contentDescription = "Introduce contraseña",
                    errorMsg = viewModel.passwordErrMsg.value,
                    validateField = { viewModel.validatePassword() }
                )
                DefaultTextField(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    value = viewModel.confirmPassword.value,
                    onValueChange = { viewModel.confirmPassword.value = it },
                    label = "Confirmar contraseña",
                    icon = Icons.Outlined.Lock,
                    hideText = true,
                    contentDescription = "Confirmar contraseña",
                    errorMsg = viewModel.confirmPasswordErrMsg.value,
                    validateField = { viewModel.validateConfirmPassword() }
                )
                DefaultButton(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 60.dp, end = 60.dp)
                        .fillMaxWidth(),
                    text = "Crear cuenta",
                    onClick = { viewModel.onSignup() },
                    enabled = viewModel.isEnabledSignupButton
                )
            }
        }

        // Loading overlay for signup
        if (signupFlow.value is Response.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

    signupFlow.value.let {
        when (it) {
            is Response.Success -> {
                LaunchedEffect(Unit) {
                    viewModel.createUser()
                    navController?.popBackStack(AppScreen.Login.route, true) // Clear the back stack
                    navController?.navigate(AppScreen.Profile.route)
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
