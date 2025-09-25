package dev.andrescoder.gamingapp.presentation.screens.my_posts.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import dev.andrescoder.gamingapp.domain.model.Post
import dev.andrescoder.gamingapp.presentation.navigation.DetailsScreen
import dev.andrescoder.gamingapp.presentation.screens.my_posts.MyPostsViewModel

@Composable
fun MyPostsCard(
    navController: NavHostController,
    post: Post,
    viewModel: MyPostsViewModel = hiltViewModel(),
) {

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    navController.navigate(route = DetailsScreen.UpdatePost.passPost(post.toJson()))
                }) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar Post",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    viewModel.delete(post.id)
                }) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Borrar Post",
                        tint = Color.White
                    )
                }
            }
        }
    }

}