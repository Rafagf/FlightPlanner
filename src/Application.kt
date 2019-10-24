package com.rafag.flightplanner

import com.rafag.flightplanner.repository.Repository
import com.rafag.flightplanner.repository.RepositoryImpl
import com.rafag.flightplanner.repository.db.DatabaseFactory
import com.rafag.flightplanner.webapp.flights.flights
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.locations.Locations
import io.ktor.routing.routing
import webapp.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val repository = initDatabase()

    installFeatures()

    routing {
        static("/static") {
            resources("images")
        }

        home()
        about()
        flights(repository)
        signIn()
        signOut()
        signUp()
    }
}

private fun initDatabase(): Repository {
    DatabaseFactory.init()
    return RepositoryImpl()
}

private fun Application.installFeatures() {
    install(Locations)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
}

