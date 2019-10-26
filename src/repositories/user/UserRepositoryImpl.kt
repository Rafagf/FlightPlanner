package com.rafag.flightplanner.repositories.user

import com.rafag.flightplanner.model.User

class UserRepositoryImpl : UserRepository {
    override suspend fun createUser(userId: String, hash: String?): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}