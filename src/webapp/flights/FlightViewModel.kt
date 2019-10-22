package com.rafag.flightplanner.webapp.flights

import com.rafag.flightplanner.model.Flight
import com.rafag.flightplanner.model.Price
import java.text.NumberFormat
import java.util.*
import java.util.Locale


data class FlightViewModel(
    val date: String,
    val departingTime: String,
    val arrivalTime: String,
    val origin: String,
    val destination: String,
    val bookingReference: String,
    val airline: String,
    val people: Int,
    val price: String
)

fun Flight.map(): FlightViewModel {
    return FlightViewModel(
        date = getDateFormatted(this.departingDate),
        departingTime = getTimeFormatted(this.departingDate),
        arrivalTime = getTimeFormatted(this.arrivalDate),
        origin = getLocationFormatted(this.originCity, this.originCountry),
        destination = getLocationFormatted(this.destinationCity, this.destinationCountry),
        bookingReference = bookingReference,
        airline = airline,
        people = people,
        price = getPriceFormatted(this.price)
    )
}

fun getLocationFormatted(city: String, country: String) = "$city ($country)"

fun List<Flight>.map(): List<FlightViewModel> {
    val list = mutableListOf<FlightViewModel>()
    this.forEach {
        list.add(it.map())
    }
    return list
}

fun getDateFormatted(date: Date): String {
    //TODO
    return "depart date"
}

fun getTimeFormatted(date: Date): String {
    //TODO
    return "x time"
}

fun getPriceFormatted(price: Price): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.UK)
    numberFormat.minimumFractionDigits = 1
    numberFormat.maximumFractionDigits = 2
    return numberFormat.format(price.toDouble())
}
