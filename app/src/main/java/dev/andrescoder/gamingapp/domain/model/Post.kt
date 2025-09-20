package dev.andrescoder.gamingapp.domain.model

data class Post(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var category: String = "",
    var image: String = "",
    var idUser: String = "",
    var user: User = User()
)
