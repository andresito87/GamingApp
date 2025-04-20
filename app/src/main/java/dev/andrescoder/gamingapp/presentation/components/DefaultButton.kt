package dev.andrescoder.gamingapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.ModifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.andrescoder.gamingapp.presentation.ui.theme.Red500
import dev.andrescoder.gamingapp.presentation.ui.theme.Red700

@Composable
fun DefaultButton(
    modifier: Modifier,
    text: String,
    errorMsg: String = "",
    onClick: () -> Unit,
    color: Color = Red500,
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowForward,
    enabled: Boolean = true
) {
    Column {
        Button(
            modifier = modifier,
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = color
            ),
            enabled = enabled
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Pulsar para acceder"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                fontSize = 22.sp
            )
        }
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = errorMsg,
            fontSize = 11.sp,
            color = Red700
        )
    }
}