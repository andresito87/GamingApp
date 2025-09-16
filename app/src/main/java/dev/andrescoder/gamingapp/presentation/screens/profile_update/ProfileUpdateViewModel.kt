package dev.andrescoder.gamingapp.presentation.screens.profile_update

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.model.User
import dev.andrescoder.gamingapp.domain.use_cases.users.UsersUseCases
import dev.andrescoder.gamingapp.presentation.utils.ComposeFileProvider
import dev.andrescoder.gamingapp.presentation.utils.ResultingActivityHandler
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCases: UsersUseCases,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    var state by mutableStateOf(ProfileUpdateState())
        private set

    var usernameErrMsg by mutableStateOf("")
        private set

    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set

    var imageUri by mutableStateOf("")

    var file: File? = null

    val resultingActivityHandler = ResultingActivityHandler()

    init {
        state = state.copy(
            username = user.username,
            image = user.image
        )
    }

    fun saveImage() = viewModelScope.launch {
        if (file != null) {
            // actualizar imagen y username
            saveImageResponse = Response.Loading
            val result = usersUseCases.saveImage(file!!)
            saveImageResponse = result
            if (result is Response.Success) {
                onUpdate(result.data)
            }
        } else {
            // actualizar solo username
            onUpdate(state.image)
        }
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

    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    fun onUpdate(imageUrl: String) {
        val updatedUser = User(
            id = user.id,
            username = state.username,
            image = imageUrl
        )
        update(updatedUser)
    }

    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCases.update(user)
        updateResponse = result
    }

    fun validateUsername() {
        usernameErrMsg = if (state.username.length > 4) {
            ""
        } else {
            "El nombre de usuario no es válido"
        }
    }
}