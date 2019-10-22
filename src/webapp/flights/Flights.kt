package com.rafag.flightplanner.webapp.flights

import com.rafag.flightplanner.model.mockedFlights
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route

const val FLIGHTS = "flights"

@Location(FLIGHTS)
class Flights

fun Route.flights() {
    get<Flights> {
        call.respond(
            FreeMarkerContent(
                template = "flights.ftl",
                model = mapOf(
                    "flights" to mockedFlights().map()
                )
            )
        )
    }
}