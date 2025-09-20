package dev.andrescoder.gamingapp.presentation.screens.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.andrescoder.gamingapp.domain.model.Post
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.use_cases.posts.PostsUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsUseCases: PostsUseCases
) : ViewModel() {

    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)

    init {
        getPosts()
    }

    fun getPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postsUseCases.getPosts().collect(){ response ->
            postsResponse = response
        }
    }
}