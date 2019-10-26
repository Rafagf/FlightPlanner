package com.rafag.flightplanner.repositories.flights

import com.rafag.flightplanner.model.domain.Flight
import com.rafag.flightplanner.DatabaseFactory.dbQuery
import com.rafag.flightplanner.model.db.Flights
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

object FlightsRepositoryImpl : FlightsRepository {
    override suspend fun add(flight: Flight): Flight? {
        return dbQuery {
            val insertStatement = transaction {
                Flights.insert {
                    it[bookingReference] = flight.bookingReference
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

    override suspend fun getFlight(id: String): Flight? {
        return dbQuery {
            Flights.select {
                (Flights.bookingReference eq id)
            }.mapNotNull {
                it.toFlight()
            }.singleOrNull()
        }
    }

    override suspend fun getFlights(): List<Flight> {
        return dbQuery {
            Flights.selectAll().mapNotNull {
                it.toFlight()
            }
        }
    }

    override suspend fun remove(id: String) {
        return dbQuery {
            Flights.deleteWhere {
                Flights.bookingReference eq id
            }
        }
    }

    override suspend fun clearAll() {
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