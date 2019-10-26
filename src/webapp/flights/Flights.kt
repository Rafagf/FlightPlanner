package com.rafag.flightplanner.webapp.flights

import com.rafag.flightplanner.auth.securityCode
import com.rafag.flightplanner.auth.verifyCode
import com.rafag.flightplanner.getDate
import com.rafag.flightplanner.model.UserSession
import com.rafag.flightplanner.model.domain.Flight
import com.rafag.flightplanner.model.domain.Price
import com.rafag.flightplanner.repositories.flights.FlightsRepository
import com.rafag.flightplanner.repositories.user.UserRepository
import com.rafag.flightplanner.webapp.redirect
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import webapp.SignIn

const val FLIGHTS = "flights"

@Location(FLIGHTS)
class Flights

fun Route.flights(
    userRepository: UserRepository,
    flightsRepository: FlightsRepository,
    hashFunction: (String) -> String
) {
    get<Flights> {
        val user = call.sessions.get<UserSession>()?.let { userRepository.getUser(it.userId) }

        if (user == null) {
            call.redirect(SignIn())
        } else {
            val flights = flightsRepository.getFlights(user.userId)
            val date = System.currentTimeMillis()
            val code = call.securityCode(date, user, hashFunction)

            call.respond(
                FreeMarkerContent(
                    template = "flights.ftl",
                    model = mapOf(
                        "flights" to flightsRepository.getFlights(user.userId).map(),
                        "user" to user,
                        "date" to date,
                        "code" to code
                    )
                )
            )

            call.respond(
                FreeMarkerContent(
                    "flights.ftl",
                    mapOf("flights" to flights, "user" to user, "date" to date, "code" to code)
                )
            )
        }
    }

    post<Flights> {
        val user = call.sessions.get<UserSession>()?.let { userRepository.getUser(it.userId) }
        val params = call.receiveParameters()
        val date = params["date"]?.toLongOrNull() ?: return@post call.redirect(it)
        val code = params["code"] ?: return@post call.redirect(it)
        val action = params["action"] ?: throw IllegalArgumentException("Missing action")

        if (user == null || !call.verifyCode(date, user, code, hashFunction)) {
            call.redirect(SignIn())
        }

        when (action) {
            "delete" -> {
                val id = params["id"] ?: throw java.lang.IllegalArgumentException("Missing id")
                flightsRepository.remove(id)
            }
            "add" -> {
                flightsRepository.add(createFlight(params = params), userId = user!!.userId)
            }
        }

        call.redirect(Flights())
    }
}

private fun createFlight(params: Parameters): Flight {
    val bookingReference =
        params["bookingReference"] ?: throw IllegalArgumentException("Missing argument: bookingReference")
    val origin = params["origin"] ?: throw IllegalArgumentException("Missing argument: destination")
    val destination = params["destination"] ?: throw IllegalArgumentException("Missing argument: destination")
    val departingDate = params["departing_date"] ?: throw IllegalArgumentException("Missing argument: date")
    val arrivalDate = params["arrival_date"] ?: throw IllegalArgumentException("Missing argument: arrivalDate")
    val airline = params["airline"] ?: throw IllegalArgumentException("Missing argument: airline")
    val people = params["people"] ?: throw IllegalArgumentException("Missing argument: people")
    val price = params["price"] ?: throw IllegalArgumentException("Missing argument: price")

    return Flight(
        bookingReference = bookingReference,
        departingDate = departingDate.getDate(),
        arrivalDate = arrivalDate.getDate(),
        origin = origin,
        destination = destination,
        airline = airline,
        people = people.toInt(),
        price = Price(price.toDouble())
    )
}
