package com.rafag.flightplanner.repository

import com.rafag.flightplanner.model.Flight

interface Repository {
    suspend fun add(flight: Flight): Flight?
    suspend fun getFlight(id: String): Flight?
    suspend fun getFlights(): List<Flight>
    suspend fun remove(id: String)
    suspend fun clearAll()
}