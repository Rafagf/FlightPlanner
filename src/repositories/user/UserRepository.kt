package com.rafag.flightplanner.repositories.user

import com.rafag.flightplanner.model.domain.User

interface UserRepository {
    suspend fun getUser(userId: String, hash: String? = null): User?
    suspend fun createUser(user: User)
    suspend fun userByEmail(email: String): User?
}