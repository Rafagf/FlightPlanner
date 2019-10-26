package api.requests

import com.rafag.flightplanner.model.domain.Flight
import com.rafag.flightplanner.model.domain.Price
import java.util.*

data class FlightApiRequest(
    val bookingReference: String,
    val departingDate: Date,
    val arrivalDate: Date,
    val origin: String,
    val destination: String,
    val airline: String,
    val people: Int,
    val price: Price
)

fun FlightApiRequest.toFlight(): Flight {
    return Flight(
        bookingReference = this.bookingReference,
        departingDate = this.departingDate,
        arrivalDate = this.arrivalDate,
        origin = this.origin,
        destination = this.destination,
        airline = this.airline,
        people = this.people,
        price = this.price
    )
}