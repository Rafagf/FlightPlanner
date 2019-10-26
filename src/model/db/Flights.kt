package com.rafag.flightplanner.model.db

import org.jetbrains.exposed.sql.Table

object Flights : Table() {
    val bookingReference = varchar("booking_reference", 10).primaryKey()
    val user = varchar("user_id", 20)
    val departingDate = datetime("departing_date")
    val arrivalDate = datetime("arrival_date")
    val origin = varchar("origin", 64)
    val destination = varchar("destination", 64)
    val airline = varchar("airline", 64).nullable()
    val people = integer("people").nullable()
    val price = decimal("price", 7, 2).nullable()
}