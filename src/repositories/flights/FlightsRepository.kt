package com.rafag.flightplanner.repositories.flights

import com.rafag.flightplanner.model.domain.Flight

interface FlightsRepository {
    suspend fun add(flight: Flight): Flight?
    suspend fun getFlight(id: String): Flight?
    suspend fun getFlights(): List<Flight>
    suspend fun remove(id: String)
    suspend fun clearAll()
}