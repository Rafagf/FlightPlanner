package com.rafag.flightplanner

import com.rafag.flightplanner.model.UserSession
import com.rafag.flightplanner.repositories.db.DatabaseFactory
import com.rafag.flightplanner.repositories.flights.FlightsRepositoryImpl
import com.rafag.flightplanner.repositories.user.UserRepositoryImpl
import com.rafag.flightplanner.webapp.flights.flights
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.locations.Locations
import io.ktor.routing.routing
import io.ktor.sessions.SessionTransportTransformerMessageAuthentication
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import webapp.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val userHashFunction = { s: String -> hash(s) }
    DatabaseFactory.init()

    installFeatures()

    routing {
        static("/static") {
            resources("images")
        }

        home()
        about()
        flights(FlightsRepositoryImpl, userHashFunction)
        signIn(UserRepositoryImpl, userHashFunction)
        signOut()
        signUp(UserRepositoryImpl, userHashFunction)
    }
}

private fun Application.installFeatures() {
    install(Locations)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(Sessions) {
        cookie<UserSession>("SESSION") {
            transform(SessionTransportTransformerMessageAuthentication(hashKey))
        }
    }
}

