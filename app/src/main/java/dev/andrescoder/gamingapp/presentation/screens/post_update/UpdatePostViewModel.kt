package dev.andrescoder.gamingapp.presentation.screens.post_update

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.andrescoder.gamingapp.R
import dev.andrescoder.gamingapp.domain.model.Post
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.use_cases.auth.AuthUseCases
import dev.andrescoder.gamingapp.domain.use_cases.posts.PostsUseCases
import dev.andrescoder.gamingapp.presentation.utils.ComposeFileProvider
import dev.andrescoder.gamingapp.presentation.utils.ResultingActivityHandler
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UpdatePostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val saveStateHandle: SavedStateHandle,
    private val postsUseCases: PostsUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    var state by mutableStateOf(UpdatePostState())

    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()

    val data = saveStateHandle.get<String>("post")
    val post = Post.fromJson(data = data!!)

    var updatePostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    private val currentUser = authUseCases.getCurrentUser()

    val radioOptions = listOf(
        CategoryRadioButton("Pc", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
        CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButton("MOBILE", R.drawable.icon_mobile),
    )

    init {
        state = state.copy(
            name = post.name,
            description = post.description,
            category = post.category,
            image = post.image,
        )
    }

    fun updatePost(post: Post) = viewModelScope.launch {
        updatePostResponse = Response.Loading
        val result = postsUseCases.updatePost(post, file)
        updatePostResponse = result
    }

    fun onUpdatePost() = viewModelScope.launch {
        val post = Post(
            id = post.id,
            name = state.name,
            description = state.description,
            category = state.category,
            image = post.image,
            idUser = currentUser?.uid ?: "",
        )
        updatePost(post)
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if (result != null) {
            file = ComposeFileProvider.createFileFromUrl(context, result)
            state = state.copy(image = result.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
    }

    fun clearForm() {
        updatePostResponse = null
    }

    fun onNameInputChange(name: String) {
        state = state.copy(name = name)
    }

    fun onDescriptionInputChange(description: String) {
        state = state.copy(description = description)
    }

    fun onCategorySelected(category: String) {
        state = state.copy(category = category)
    }

    fun onImageSelected(image: String) {
        state = state.copy(image = image)
    }

}

data class CategoryRadioButton(
    val category: String,
    val image: Int,
)