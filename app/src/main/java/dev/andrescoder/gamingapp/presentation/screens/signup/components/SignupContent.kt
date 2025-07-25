package dev.andrescoder.gamingapp.presentation.screens.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.R
import dev.andrescoder.gamingapp.presentation.components.DefaultButton
import dev.andrescoder.gamingapp.presentation.components.DefaultTextField
import dev.andrescoder.gamingapp.presentation.screens.signup.SignupViewModel
import dev.andrescoder.gamingapp.presentation.ui.theme.Darkgray500
import dev.andrescoder.gamingapp.presentation.ui.theme.Red500

@Composable
fun SignupContent(
    paddingValues: PaddingValues,
    navController: NavHostController?,
    viewModel: SignupViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Allows scrolling
            .imePadding() // Avoid keyboard overlapping content
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
                    value = state.username,
                    onValueChange = { viewModel.onUsernameInput(it) },
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    contentDescription = "Introduce nombre de usuario",
                    errorMsg = viewModel.usernameErrMsg,
                    validateField = { viewModel.validateUsername() }
                )
                DefaultTextField(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    value = state.email,
                    onValueChange = { viewModel.onEmailInput(it) },
                    label = "Correo electrónico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    contentDescription = "Introduce email",
                    errorMsg = viewModel.emailErrMsg,
                    validateField = { viewModel.validateEmail() }
                )
                DefaultTextField(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    value = state.password,
                    onValueChange = { viewModel.onPasswordInput(it) },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    contentDescription = "Introduce contraseña",
                    errorMsg = viewModel.passwordErrMsg,
                    validateField = { viewModel.validatePassword() }
                )
                DefaultTextField(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    value = state.confirmPassword,
                    onValueChange = { viewModel.onConfirmPasswordInput(it) },
                    label = "Confirmar contraseña",
                    icon = Icons.Outlined.Lock,
                    hideText = true,
                    contentDescription = "Confirmar contraseña",
                    errorMsg = viewModel.confirmPasswordErrMsg,
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
    }
}
