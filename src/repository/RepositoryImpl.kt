package com.rafag.flightplanner.repository

import com.rafag.flightplanner.model.Flight
import com.rafag.flightplanner.repository.db.DatabaseFactory.dbQuery
import com.rafag.flightplanner.repository.db.FlightsTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class RepositoryImpl : Repository {
    override suspend fun add(flight: Flight): Flight? {
        return dbQuery {
            val insertStatement = transaction {
                FlightsTable.insert {
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
            FlightsTable.select {
                (FlightsTable.bookingReference eq id)
            }.mapNotNull {
                it.toFlight()
            }.singleOrNull()
        }
    }

    override suspend fun getFlights(): List<Flight> {
        return dbQuery {
            FlightsTable.selectAll().mapNotNull {
                it.toFlight()
            }
        }
    }

    override suspend fun remove(id: String) {
        return dbQuery {
            FlightsTable.deleteWhere {
                FlightsTable.bookingReference eq id
            }
        }
    }

    override suspend fun clearAll() {
        return dbQuery {
            FlightsTable.deleteAll()
        }
    }

    private fun ResultRow.toFlight(): Flight {
        return Flight(
            bookingReference = this[FlightsTable.bookingReference],
            departingDate = this[FlightsTable.departingDate].toDate(),
            arrivalDate = this[FlightsTable.departingDate].toDate(),
            origin = this[FlightsTable.origin],
            destination = this[FlightsTable.destination],
            airline = this[FlightsTable.airline],
            people = this[FlightsTable.people],
            price = this[FlightsTable.price]
        )
    }
}