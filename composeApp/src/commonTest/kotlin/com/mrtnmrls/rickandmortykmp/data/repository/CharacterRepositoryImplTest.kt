package com.mrtnmrls.rickandmortykmp.data.repository

import com.mrtnmrls.rickandmortykmp.data.remote.fake.RickAndMortyApiFake
import com.mrtnmrls.rickandmortykmp.data.testdata.TestData.characterData
import com.mrtnmrls.rickandmortykmp.data.testdata.TestData.characterResponse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CharacterRepositoryImplTest {
    private val api = RickAndMortyApiFake()
    private val repository = CharacterRepositoryImpl(api)

    @Test
    fun `getCharacters return successful result`() =
        runTest {
            api.charactersResult = Result.success(characterResponse)

            val result = repository.getCharacters(1)

            assertTrue(result.isSuccess)
            result.onSuccess { paging ->
                assertEquals(1, paging.characters.size)
                assertEquals("Rick Sanchez", paging.characters.first().name)
                assertFalse(paging.canLoadMore)
            }
        }

    @Test
    fun `getCharacters return failed when api fails`() =
        runTest {
            val exception = Exception("Network error")
            api.charactersResult = Result.failure(exception)

            val result = repository.getCharacters(1)

            assertTrue(result.isFailure)
            result.onFailure { error ->
                assertEquals(exception.message, error.message)
            }
        }

    @Test
    fun `getCharacter return successful result`() =
        runTest {
            api.characterResult = Result.success(characterData)

            val result = repository.getCharacter(1)

            assertTrue(result.isSuccess)
            result.onSuccess { character ->
                assertEquals(1, character.id)
                assertEquals("Rick Sanchez", character.name)
            }
        }

    @Test
    fun `getCharacter return failed when api fails`() =
        runTest {
            val exception = Exception("Network error")
            api.characterResult = Result.failure(exception)

            val result = repository.getCharacter(1)

            assertTrue(result.isFailure)
            result.onFailure { error ->
                assertEquals(exception.message, error.message)
            }
        }
}
