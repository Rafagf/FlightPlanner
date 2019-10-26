package com.rafag.flightplanner.webapp.flights

import com.rafag.flightplanner.getDateFormatted
import com.rafag.flightplanner.getPriceFormatted
import com.rafag.flightplanner.getTimeFormatted
import com.rafag.flightplanner.model.domain.Flight

data class FlightViewModel(
    val bookingReference: String,
    val origin: String,
    val departingDate: String,
    val departingTime: String,
    val destination: String,
    val arrivalDate: String,
    val arrivalTime: String,
    val airline: String,
    val people: Int,
    val price: String
)

fun Flight.map(): FlightViewModel {
    return FlightViewModel(
        bookingReference = bookingReference,
        origin = this.origin,
        departingDate = this.departingDate.getDateFormatted(),
        departingTime = this.departingDate.getTimeFormatted(),
        destination = this.destination,
        arrivalDate = this.arrivalDate.getDateFormatted(),
        arrivalTime = this.arrivalDate.getTimeFormatted(),
        airline = this.airline,
        people = this.people,
        price = this.price.getPriceFormatted()
    )
}

fun List<Flight>.map(): List<FlightViewModel> {
    return map {
        it.map()
    }
}