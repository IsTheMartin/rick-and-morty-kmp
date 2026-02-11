package com.mrtnmrls.rickandmortykmp.domain.repository

import com.mrtnmrls.rickandmortykmp.domain.model.Location

interface LocationRepository {
    suspend fun getLocation(page: Int): Result<List<Location>>
}
