package com.rafag.flightplanner.webapp

import io.ktor.application.ApplicationCall
import io.ktor.locations.locations
import io.ktor.response.respondRedirect

suspend fun ApplicationCall.redirect(location: Any) {
    respondRedirect(application.locations.href(location))
}
