package com.rafag.flightplanner.repositories.user

import com.rafag.flightplanner.DatabaseFactory.dbQuery
import com.rafag.flightplanner.model.db.Users
import com.rafag.flightplanner.model.domain.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

object UserRepositoryImpl : UserRepository {
    override suspend fun userByEmail(email: String): User? {
        return dbQuery {
            Users.select {
                Users.email.eq(email)
            }.mapNotNull {
                it.toUser()
            }.singleOrNull()
        }
    }

    override suspend fun getUser(userId: String): User? {
        return dbQuery {
            Users.select {
                (Users.id eq userId)
            }.mapNotNull {
                it.toUser()
            }.singleOrNull()
        }
    }

    override suspend fun getUserWithHash(userId: String, hash: String): User? {
        val user: User? = dbQuery {
            Users.select {
                (Users.id eq userId)
            }.mapNotNull {
                it.toUser()
            }.singleOrNull()
        }

        return when {
            user?.passwordHash == hash -> user
            else -> null
        }
    }

    override suspend fun createUser(user: User) {
        dbQuery {
            Users.insert {
                it[id] = user.userId
                it[displayName] = user.displayName
                it[email] = user.email
                it[passwordHash] = user.passwordHash
            }
        }
    }

    private fun ResultRow.toUser(): User =
        User(
            userId = this[Users.id],
            email = this[Users.email],
            displayName = this[Users.displayName],
            passwordHash = this[Users.passwordHash]
        )
}