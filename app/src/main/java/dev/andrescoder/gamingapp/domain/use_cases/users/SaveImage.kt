package dev.andrescoder.gamingapp.domain.use_cases.users

import dev.andrescoder.gamingapp.domain.repository.UsersRepository
import javax.inject.Inject

class SaveImage @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(file: java.io.File) = repository.saveImage(file)

}