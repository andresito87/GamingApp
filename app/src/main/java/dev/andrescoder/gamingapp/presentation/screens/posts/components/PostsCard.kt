package dev.andrescoder.gamingapp.presentation.screens.posts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import dev.andrescoder.gamingapp.domain.model.Post
import dev.andrescoder.gamingapp.presentation.navigation.DetailsScreen

@Composable
fun PostsCard(
    navController: NavHostController,
    post: Post) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable {
                // Navigate to details post screen
                navController.navigate(route = DetailsScreen.DetailsPost.passPost(post.toJson()))
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White
        )
    ) {
        Column() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(bottom = 5.dp),
                model = post.image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                text = post.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Proportional,
                        trim = LineHeightStyle.Trim.Both
                    ),
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                text = post.user?.username ?: "Autor Desconocido",
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 16.sp,
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Proportional,
                        trim = LineHeightStyle.Trim.Both
                    ),
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        }
    }

}