package dev.andrescoder.gamingapp.presentation.screens.details_post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.andrescoder.gamingapp.domain.model.Post
import javax.inject.Inject

@HiltViewModel
class DetailsPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val data = savedStateHandle.get<String>("post")
    val post = Post.fromJson(data!!)

}