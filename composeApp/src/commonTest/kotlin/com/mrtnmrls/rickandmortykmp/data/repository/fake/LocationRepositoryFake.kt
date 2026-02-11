package com.mrtnmrls.rickandmortykmp.data.repository.fake

import com.mrtnmrls.rickandmortykmp.domain.model.Location
import com.mrtnmrls.rickandmortykmp.domain.repository.LocationRepository

class LocationRepositoryFake : LocationRepository {
    var locationsResult: Result<List<Location>>? = null

    override suspend fun getLocation(page: Int): Result<List<Location>> = locationsResult ?: Result.failure(Exception("Not initialized"))
}
