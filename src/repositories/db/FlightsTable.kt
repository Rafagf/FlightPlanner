package com.rafag.flightplanner.repositories.db

import org.jetbrains.exposed.sql.Table

object FlightsTable : Table() {
    val bookingReference = varchar("booking_reference", 10).primaryKey()
    val departingDate = datetime("departing_date")
    val arrivalDate = datetime("arrival_date")
    val origin = varchar("origin", 64)
    val destination = varchar("destination", 64)
    val airline = varchar("airline", 64).nullable()
    val people = integer("people").nullable()
    val price = decimal("price", 7, 2).nullable()
}