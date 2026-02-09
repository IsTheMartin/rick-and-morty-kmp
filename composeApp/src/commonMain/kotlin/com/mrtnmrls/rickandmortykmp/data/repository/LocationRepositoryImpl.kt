package com.mrtnmrls.rickandmortykmp.data.repository

import com.mrtnmrls.rickandmortykmp.data.mapper.toLocation
import com.mrtnmrls.rickandmortykmp.data.remote.RickAndMortyApi
import com.mrtnmrls.rickandmortykmp.domain.model.Location
import com.mrtnmrls.rickandmortykmp.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val api: RickAndMortyApi
) : LocationRepository {
    override suspend fun getLocation(page: Int): Result<List<Location>> {
        return api.getLocations(page)
            .map { response ->
                response.results.map { result -> result.toLocation() }
            }
    }
}