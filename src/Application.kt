package com.rafag.flightplanner

import api.flightsApi
import api.loginApi
import com.rafag.flightplanner.auth.COOKIES_SESSION
import com.rafag.flightplanner.auth.JwtService
import com.rafag.flightplanner.auth.hash
import com.rafag.flightplanner.auth.hashKey
import com.rafag.flightplanner.model.UserSession
import com.rafag.flightplanner.repositories.flights.FlightsRepository
import com.rafag.flightplanner.repositories.user.UserRepository
import com.rafag.flightplanner.webapp.flights
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.freemarker.FreeMarker
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.locations.Locations
import io.ktor.locations.locations
import io.ktor.response.respondRedirect
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

        //Api
        flightsApi(FlightsRepository)
        loginApi(UserRepository, JwtService)
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

    install(Authentication) {
        jwt("jwt") {
            verifier(JwtService.verifier)
            realm = "flightplanner app"
            validate {
                val payload = it.payload
                val claim = payload.getClaim("id")
                val claimString = claim.asString()
                val user = UserRepository.getUser(claimString)
                user
            }
        }
    }

    install(ContentNegotiation) {
        gson()
    }
}

suspend fun ApplicationCall.redirect(location: Any) {
    respondRedirect(application.locations.href(location))
}
