package dev.andrescoder.gamingapp.domain.use_cases.posts

import dev.andrescoder.gamingapp.domain.repository.PostsRepository
import javax.inject.Inject

class GetPosts @Inject constructor(private val repository: PostsRepository) {

    operator fun invoke() = repository.getPosts()

}