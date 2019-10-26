package com.rafag.flightplanner

import com.rafag.flightplanner.model.UserSession
import com.rafag.flightplanner.repositories.flights.FlightsRepository
import com.rafag.flightplanner.repositories.user.UserRepository
import com.rafag.flightplanner.webapp.flights.flights
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.freemarker.FreeMarker
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.locations.Locations
import io.ktor.response.respondText
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

        home(UserRepository)
        about(UserRepository)
        flights(UserRepository, FlightsRepository, userHashFunction)
        signIn(UserRepository, userHashFunction)
        signOut()
        signUp(UserRepository, userHashFunction)
    }
}

private fun Application.installFeatures() {
    install(DefaultHeaders)
    install(StatusPages) {
        exception<Throwable> { exception ->
            call.respondText(
                exception.localizedMessage,
                ContentType.Text.Plain,
                HttpStatusCode.InternalServerError
            )
        }
    }
    install(Locations)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(Sessions) {
        cookie<UserSession>(COOKIES_SESSION) {
            transform(SessionTransportTransformerMessageAuthentication(hashKey))
        }
    }
}