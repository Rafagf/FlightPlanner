package com.rafag.flightplanner.webapp.flights

import com.rafag.flightplanner.model.Flight
import com.rafag.flightplanner.model.Price
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale

data class FlightViewModel(
    val bookingReference: String,
    val date: String,
    val departingTime: String,
    val arrivalTime: String,
    val origin: String,
    val destination: String,
    val airline: String,
    val people: Int,
    val price: String
)

fun Flight.map(): FlightViewModel {
    return FlightViewModel(
        bookingReference = bookingReference,
        date = getDateFormatted(this.departingDate),
        departingTime = getTimeFormatted(this.departingDate),
        arrivalTime = getTimeFormatted(this.arrivalDate),
        origin = this.origin,
        destination = this.destination,
        airline = airline,
        people = people,
        price = getPriceFormatted(this.price)
    )
}

fun List<Flight>.map(): List<FlightViewModel> {
    val list = mutableListOf<FlightViewModel>()
    this.forEach {
        list.add(it.map())
    }
    return list
}

fun getDateFormatted(date: Date): String {
    val format = SimpleDateFormat("dd/MM/yy")
    return format.format(date)
}

fun getTimeFormatted(date: Date): String {
    val format = SimpleDateFormat("HH:mm")
    return format.format(date)
}

fun getPriceFormatted(price: Price): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.UK)
    numberFormat.minimumFractionDigits = 1
    numberFormat.maximumFractionDigits = 2
    return numberFormat.format(price.toDouble())
}
