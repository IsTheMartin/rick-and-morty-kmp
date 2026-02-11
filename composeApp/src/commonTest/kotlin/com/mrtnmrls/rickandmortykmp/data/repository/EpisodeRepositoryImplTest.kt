package com.mrtnmrls.rickandmortykmp.data.repository

import com.mrtnmrls.rickandmortykmp.data.remote.fake.RickAndMortyApiFake
import com.mrtnmrls.rickandmortykmp.data.testdata.TestData.episodeResponse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EpisodeRepositoryImplTest {
    private val api = RickAndMortyApiFake()
    private val repository = EpisodeRepositoryImpl(api)

    @Test
    fun `getEpisodes return successful result`() =
        runTest {
            api.episodesResult = Result.success(episodeResponse)

            val result = repository.getEpisodes(1)

            assertTrue(result.isSuccess)
            result.onSuccess { paging ->
                assertEquals(1, paging.size)
                assertEquals("Pilot", paging.first().name)
                assertEquals("S01E01", paging.first().episode)
            }
        }

    @Test
    fun `getEpisodes return failed when api fails`() =
        runTest {
            val exception = Exception("Network error")
            api.episodesResult = Result.failure(exception)

            val result = repository.getEpisodes(1)

            assertTrue(result.isFailure)
            result.onFailure { error ->
                assertEquals(exception.message, error.message)
            }
        }
}
