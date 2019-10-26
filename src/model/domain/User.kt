package com.rafag.flightplanner.model.domain

import io.ktor.auth.Principal
import java.io.Serializable


data class User(
    val userId: String,
    val email: String,
    val displayName: String,
    val passwordHash: String
) : Serializable, Principal