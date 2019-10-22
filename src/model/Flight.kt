package com.rafag.flightplanner.model

import java.math.BigDecimal
import java.util.*


typealias Price = BigDecimal

data class Flight(
    val departingDate: Date,
    val arrivalDate: Date,
    val originCity: String,
    val originCountry: String,
    val destinationCity: String,
    val destinationCountry: String,
    val bookingReference: String,
    val airline: String,
    val people: Int,
    val price: Price
)

fun mockedFlights(): List<Flight> {
    return listOf(
        Flight(
            departingDate = getDate(2019, 10, 29, 17, 30),
            arrivalDate = getDate(2019, 10, 29, 18, 45),
            originCity = "London",
            originCountry = "UK",
            destinationCity = "Madrid",
            destinationCountry = "Spain",
            bookingReference = "XDE23F",
            airline = "Ryanair",
            people = 4,
            price = Price(300.23)
        ),
        Flight(
            departingDate = getDate(2019, 11, 29, 19, 30),
            arrivalDate = getDate(2019, 11, 29, 19, 45),
            originCity = "London",
            originCountry = "UK",
            destinationCity = "Berlin",
            destinationCountry = "Germany",
            bookingReference = "XDE33F",
            airline = "WizzAir",
            people = 2,
            price = Price(100.23)
        ),
        Flight(
            departingDate = getDate(2019, 12, 29, 12, 30),
            arrivalDate = getDate(2019, 12, 29, 20, 45),
            originCity = "London",
            originCountry = "UK",
            destinationCity = "Lisbon",
            destinationCountry = "Portugal",
            bookingReference = "XDE23F",
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
