package com.rafag.flightplanner.model

import java.math.BigDecimal
import java.util.*


typealias Price = BigDecimal

data class Flight(
    val bookingReference: String,
    val departingDate: Date,
    val arrivalDate: Date,
    val origin: String,
    val destination: String,
    val airline: String?,
    val people: Int?,
    val price: Price?
)

fun mockedFlights(): List<Flight> {
    return listOf(
        Flight(
            bookingReference = "XDE23F",
            departingDate = getDate(2019, 10, 29, 17, 30),
            arrivalDate = getDate(2019, 10, 29, 18, 45),
            origin = "London (UK)",
            destination = "Madrid (Spain)",
            airline = "Ryanair",
            people = 4,
            price = Price(300.23)
        ),
        Flight(
            bookingReference = "XDE22F",
            departingDate = getDate(2019, 11, 29, 19, 30),
            arrivalDate = getDate(2019, 11, 29, 19, 45),
            origin = "London (UK)",
            destination = "Berlin (Germany)",
            airline = "WizzAir",
            people = 2,
            price = Price(100.23)
        ),
        Flight(
            bookingReference = "XAE23F",
            departingDate = getDate(2019, 12, 29, 12, 30),
            arrivalDate = getDate(2019, 12, 29, 20, 45),
            origin = "London (UK)",
            destination = "Lisbon (Portugal)",
            airline = "British Airways",
            people = 4,
            price = Price(50.23)
        )
    )
}

private fun getDate(year: Int, month: Int, day: Int, hour: Int, minute: Int): Date {
    return Calendar.getInstance().run {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, day)
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        time
    }
}
