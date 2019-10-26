package com.rafag.flightplanner.api

import com.rafag.flightplanner.model.domain.User
import io.ktor.application.ApplicationCall
import io.ktor.auth.authentication

const val API_VERSION = "/api/v1"

val ApplicationCall.apiUser get() = authentication.principal<User>()
