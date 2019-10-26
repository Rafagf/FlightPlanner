package api

import api.requests.FlightApiRequest
import api.requests.toFlight
import com.rafag.flightplanner.api.API_VERSION
import com.rafag.flightplanner.api.apiUser
import com.rafag.flightplanner.repositories.flights.FlightsRepository
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route

const val FLIGHTS_API_ENDPOINT = "$API_VERSION/flights"

@Location(FLIGHTS_API_ENDPOINT)
class FlightsApi

fun Route.flightsApi(flightsRepository: FlightsRepository) {

    authenticate("jwt") {
        get<FlightsApi> {
            val user = call.apiUser!!
            val flights = flightsRepository.getFlights(user.userId)
            call.respond(flights)
        }

        post<FlightsApi> {
            val user = call.apiUser!!
            try {
                val flightApiRequest = call.receive<FlightApiRequest>()
                val flight = flightsRepository.add(flightApiRequest.toFlight(), user.userId)
                if (flight != null) {
                    call.respond(flight)
                } else {
                    call.respondText("Invalid data received", status = HttpStatusCode.InternalServerError)
                }

            } catch (e: Throwable) {
                call.respondText("Invalid data received", status = HttpStatusCode.BadRequest)
            }
        }
    }
}
