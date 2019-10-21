package com.rafag.flightplanner

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.locations.Locations
import io.ktor.routing.routing
import webapp.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    installFeatures()

    routing {
        home()
        about()
        flights()
        signIn()
        signOut()
        signUp()
    }
}

private fun Application.installFeatures() {
    install(Locations)
}

