package dev.andrescoder.gamingapp.presentation.screens.my_posts

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.andrescoder.gamingapp.domain.model.Post
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.use_cases.auth.AuthUseCases
import dev.andrescoder.gamingapp.domain.use_cases.posts.PostsUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPostsViewModel @Inject constructor(
    private val postsUseCases: PostsUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
    var currentUser = authUseCases.getCurrentUser()

    init {
        getMyPosts()
    }

    fun getMyPosts() = viewModelScope.launch {
        postsResponse = Response.Loading

        postsUseCases.getPostsByIdUser(currentUser?.uid ?: "").collect() { response ->
            postsResponse = response
        }
    }
}