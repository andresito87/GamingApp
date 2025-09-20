package dev.andrescoder.gamingapp.presentation.screens.posts.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.andrescoder.gamingapp.domain.model.Post

@Composable
fun PostsContent(
    posts: List<Post>,
    contentPadding: PaddingValues = PaddingValues()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .navigationBarsPadding(),
        contentPadding = PaddingValues(
            top = contentPadding.calculateTopPadding(),
            bottom = contentPadding.calculateBottomPadding() + 75.dp
        )
    ) {
        items(posts) { post -> PostsCard(post) }
    }
}