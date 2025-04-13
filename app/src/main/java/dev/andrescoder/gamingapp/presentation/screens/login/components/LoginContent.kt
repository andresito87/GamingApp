package dev.andrescoder.gamingapp.presentation.screens.login.components


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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.andrescoder.gamingapp.R
import dev.andrescoder.gamingapp.presentation.components.DefaultButton
import dev.andrescoder.gamingapp.presentation.components.DefaultTextField
import dev.andrescoder.gamingapp.presentation.ui.theme.Darkgray500
import dev.andrescoder.gamingapp.presentation.ui.theme.Red500

@Composable
fun LoginContent(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth(),
    ) {
        BoxHeader()
        CardFrom()
    }
}

@Composable
fun BoxHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .background(Red500)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .height(130.dp),
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
}

@Composable
fun CardFrom() {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Card(
        modifier = Modifier
            .padding(
                start = 30.dp,
                end = 30.dp,
                top = 240.dp,
                bottom = 30.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Darkgray500
        ),
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
                    .padding(
                        top = 25.dp,
                        start = 20.dp,
                        end = 30.dp
                    ),
                value = email,
                onValueChange = {
                    email = it
                },
                label = "Correo electrónico",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                contentDescription = "Introduce email"
            )
            DefaultTextField(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        start = 20.dp,
                        end = 30.dp
                    ),
                value = password,
                onValueChange = {
                    password = it
                },
                label = "Contraseña",
                icon = Icons.Default.Lock,
                hideText = true,
                contentDescription = "Introduce contraseña"
            )
            DefaultButton(
                text = "Iniciar sesión",
                onClick = {}
            )
        }
    }
}
