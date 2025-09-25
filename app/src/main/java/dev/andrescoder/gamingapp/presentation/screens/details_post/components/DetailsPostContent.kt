package dev.andrescoder.gamingapp.presentation.screens.details_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import dev.andrescoder.gamingapp.R
import dev.andrescoder.gamingapp.presentation.screens.details_post.DetailsPostViewModel
import dev.andrescoder.gamingapp.presentation.ui.theme.GamingAppTheme
import dev.andrescoder.gamingapp.presentation.ui.theme.Red500

@Composable
fun DetailsPostContent(
    navController: NavHostController,
    viewModel: DetailsPostViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Box() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                model = viewModel.post.image,
                contentDescription = "Imagen del post",
                contentScale = ContentScale.Crop
            )
            Box(
                Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            0.0f to Color.Black.copy(alpha = 0.95f),
                            0.30f to Color.Transparent
                        )
                    )
            )
            FilledIconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
                    .size(44.dp),
                shape = CircleShape,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color.White.copy(alpha = 0.55f),
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
            }
        }
        if (!viewModel.post.user?.username.isNullOrBlank()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 15.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(75.dp)
                            .padding(5.dp)
                            .clip(CircleShape),
                        model = viewModel.post.user?.image,
                        contentDescription = "Imagen del usuario",
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 20.dp)
                    ) {
                        Text(
                            text = viewModel.post.user?.username ?: "",
                            fontSize = 13.sp
                        )
                        Text(
                            text = viewModel.post.user?.email ?: "",
                            fontSize = 11.sp
                        )
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                thickness = 1.dp,
                color = Color.DarkGray
            )
        } else {
            Spacer(modifier = Modifier.height(10.dp))
        }

        Text(
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, end = 20.dp),
            text = viewModel.post.name,
            fontSize = 21.sp,
            color = Red500,
            fontWeight = FontWeight.Bold
        )
        Card(
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, end = 20.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Red500
            ),
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 3.dp, horizontal = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .size(35.dp)
                        .padding(5.dp),
                    painter = painterResource(
                        id = when (viewModel.post.category) {
                            "PC" -> R.drawable.icon_pc
                            "PS4" -> R.drawable.icon_ps4
                            "XBOX" -> R.drawable.icon_xbox
                            "NINTENDO" -> R.drawable.icon_nintendo
                            else -> R.drawable.icon_mobile
                        }
                    ),
                    contentDescription = "Imagen del post",
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = viewModel.post.category,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp),
            thickness = 1.dp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "DESCRIPCIÓN",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        )
        Text(
            text = viewModel.post.description,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetailsPostContent() {
    GamingAppTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DetailsPostContent(rememberNavController())
        }
    }
}