package com.rafag.flightplanner.repositories.flights

import com.rafag.flightplanner.DatabaseFactory.dbQuery
import com.rafag.flightplanner.model.db.Flights
import com.rafag.flightplanner.model.domain.Flight
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

object FlightsRepository {
    suspend fun add(flight: Flight, userId: String): Flight? {
        return dbQuery {
            val insertStatement = transaction {
                Flights.insert {
                    it[bookingReference] = flight.bookingReference
                    it[user] = userId
                    it[departingDate] = DateTime(flight.departingDate)
                    it[arrivalDate] = DateTime(flight.arrivalDate)
                    it[origin] = flight.origin
                    it[destination] = flight.destination
                    it[airline] = flight.airline
                    it[people] = flight.people
                    it[price] = flight.price
                }
            }

            insertStatement.resultedValues?.get(0)?.toFlight()
        }
    }

    suspend fun getFlight(id: String): Flight? {
        return dbQuery {
            Flights.select {
                (Flights.bookingReference eq id)
            }.mapNotNull {
                it.toFlight()
            }.singleOrNull()
        }
    }

    suspend fun getFlights(userId: String): List<Flight> {
        return dbQuery {
            Flights.select {
                (Flights.user eq userId)
            }.mapNotNull {
                it.toFlight()
            }
        }
    }

    suspend fun remove(id: String) {
        return dbQuery {
            Flights.deleteWhere {
                Flights.bookingReference eq id
            }
        }
    }

    suspend fun clearAll() {
        return dbQuery {
            Flights.deleteAll()
        }
    }

    private fun ResultRow.toFlight(): Flight {
        return Flight(
            bookingReference = this[Flights.bookingReference],
            departingDate = this[Flights.departingDate].toDate(),
            arrivalDate = this[Flights.departingDate].toDate(),
            origin = this[Flights.origin],
            destination = this[Flights.destination],
            airline = this[Flights.airline],
            people = this[Flights.people],
            price = this[Flights.price]
        )
    }
}