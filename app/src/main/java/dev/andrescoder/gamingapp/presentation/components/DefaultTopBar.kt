package dev.andrescoder.gamingapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.andrescoder.gamingapp.presentation.ui.theme.Red500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    title: String,
    backIconAvailable: Boolean = false,
    navController: NavHostController? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Red500)
            .height(50.dp)
            .drawBehind {
                val shadowHeight = 6.dp.toPx()
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.15f),
                            Color.Transparent
                        ),
                        startY = size.height,
                        endY = size.height + shadowHeight
                    ),
                    topLeft = Offset(0f, size.height),
                    size = androidx.compose.ui.geometry.Size(size.width, shadowHeight)
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (backIconAvailable) {
            IconButton(
                onClick = { navController?.popBackStack() },
                modifier = Modifier
                    .height(50.dp),
            ) {
                Icon(

                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White
                )
            }
        }
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
