package com.mrtnmrls.rickandmortykmp.data.repository

import com.mrtnmrls.rickandmortykmp.data.remote.fake.RickAndMortyApiFake
import com.mrtnmrls.rickandmortykmp.data.testdata.TestData.locationResponse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocationRepositoryImplTest {
    private val api = RickAndMortyApiFake()
    private val repository = LocationRepositoryImpl(api)

    @Test
    fun `getCharacters return successful result`() = runTest {
        api.locationsResult = Result.success(locationResponse)

        val result = repository.getLocation(1)

        assertTrue(result.isSuccess)
        result.onSuccess { paging ->
            assertEquals(1, paging.size)
            assertEquals("Earth", paging.first().name)
            assertEquals("Planet", paging.first().type)
        }
    }

    @Test
    fun `getCharacters return failed when api fails`() = runTest {
        val exception = Exception("Network error")
        api.locationsResult = Result.failure(exception)

        val result = repository.getLocation(1)

        assertTrue(result.isFailure)
        result.onFailure { error ->
            assertEquals(exception.message, error.message)
        }
    }
}