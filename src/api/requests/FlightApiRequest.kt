package api.requests

import java.util.*

data class FlightApiRequest(
    val bookingReference: String,
    val departingDate: Date,
    val arrivalDate: Date,
    val origin: String,
    val destination: String,
    val airline: String?,
    val people: Int?
)