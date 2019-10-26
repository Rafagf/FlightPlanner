package com.rafag.flightplanner.repositories.user

import com.rafag.flightplanner.model.User

object UserRepositoryImpl : UserRepository {
    override suspend fun userByEmail(email: String): User? {
        return null
    }

    override suspend fun getUser(userId: String, hash: String?): User? {
        return null
    }

    override suspend fun createUser(user: User) {
    }
}