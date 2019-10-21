package webapp

import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Route

const val FLIGHTS = "flights"

@Location(FLIGHTS)
class Flights

fun Route.flights() {
    get<Flights> {
        call.respondText("Flights page")
    }
}