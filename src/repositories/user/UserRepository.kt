package com.rafag.flightplanner.repositories.user

import com.rafag.flightplanner.model.User

interface UserRepository {
    suspend fun createUser(userId: String, hash: String? = null): User?

}