package dev.andrescoder.gamingapp.domain.use_cases.posts

data class PostsUseCases(
    val create: CreatePost,
    val getPosts: GetPosts,
    val getPostsByIdUser: GetPostsByIdUser,
    val deletePost: DeletePost,
    val updatePost: UpdatePost,
    val likePost: LikePost,
    val deleteLikePost: DeleteLikePost,
)