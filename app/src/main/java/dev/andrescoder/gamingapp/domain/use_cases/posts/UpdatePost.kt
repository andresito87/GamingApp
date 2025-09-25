package dev.andrescoder.gamingapp.domain.use_cases.posts

import dev.andrescoder.gamingapp.domain.model.Post
import dev.andrescoder.gamingapp.domain.repository.PostsRepository
import java.io.File
import javax.inject.Inject

class UpdatePost @Inject constructor(private val repository: PostsRepository) {
    suspend operator fun invoke(post: Post, file: File?) = repository.update(post, file)
}