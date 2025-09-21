package dev.andrescoder.gamingapp.domain.use_cases.posts

import dev.andrescoder.gamingapp.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostsByIdUser @Inject constructor(private val repository: PostsRepository) {

    operator fun invoke(idUser: String) = repository.getPostsByUserId(idUser)

}