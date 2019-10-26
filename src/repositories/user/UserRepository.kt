package com.rafag.flightplanner.repositories.user

import com.rafag.flightplanner.model.domain.User

interface UserRepository {
    suspend fun getUserWithHash(userId: String, hash: String): User?
    suspend fun createUser(user: User)
    suspend fun userByEmail(email: String): User?
    suspend fun getUser(userId: String): User?
}