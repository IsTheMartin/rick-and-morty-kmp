package com.mrtnmrls.rickandmortykmp.data.remote

import com.mrtnmrls.rickandmortykmp.data.remote.impl.RickAndMortyApiImpl
import com.mrtnmrls.rickandmortykmp.data.remote.responses.MockResponses
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RickAndMortyApiTest {

    private fun provideRickAndMortyApi(
        response: String,
        httpStatusCode: HttpStatusCode = HttpStatusCode.OK
    ): RickAndMortyApiImpl {
        val mockEngine = MockEngine { _ ->
            respond(
                content = response,
                status = httpStatusCode,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val httpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }
        }
        return RickAndMortyApiImpl(httpClient)
    }

    @Test
    fun `getCharacters returns success result`() = runTest {
        val api = provideRickAndMortyApi(response = MockResponses.GET_CHARACTERS_RESPONSE)
        val result = api.getCharacters(1)

        assertTrue(result.isSuccess)
        result
            .onSuccess { response ->
                assertEquals(response.info.count, 826)
                assertEquals(response.results.size, 3)
            }
    }

    @Test
    fun `getCharacter returns success result`() = runTest {
        val characterId = 2

        val api = provideRickAndMortyApi(response = MockResponses.GET_CHARACTER_RESPONSE)
        val result = api.getCharacter(characterId)

        assertTrue(result.isSuccess)
        result
            .onSuccess { response ->
                assertEquals(response.id, characterId)
                assertEquals(response.name, "Morty Smith")
            }
    }

    @Test
    fun `getLocations returns success result`() = runTest {
        val api = provideRickAndMortyApi(response = MockResponses.GET_LOCATIONS_RESPONSE)
        val result = api.getLocations(1)

        assertTrue(result.isSuccess)
        result
            .onSuccess { response ->
                assertEquals(response.info.count, 126)
                assertEquals(response.results.size, 3)
            }
    }

    @Test
    fun `getEpisodes returns success result`() = runTest {
        val api = provideRickAndMortyApi(response = MockResponses.GET_EPISODES_RESPONSE)
        val result = api.getEpisodes(1)

        assertTrue(result.isSuccess)
        result
            .onSuccess { response ->
                assertEquals(response.info.count, 51)
                assertEquals(response.results.size, 3)
            }
    }

    @Test
    fun `api returns error 429`() = runTest {
        val api = provideRickAndMortyApi(
            response = MockResponses.GET_EPISODES_RESPONSE,
            httpStatusCode = HttpStatusCode.TooManyRequests
        )
        val result = api.getEpisodes(1)

        assertTrue(result.isFailure)
        result
            .onFailure { error ->
                assertEquals(RickAndMortyApiImpl.TOO_MANY_REQUEST_MESSAGE, error.message)
            }
    }

    @Test
    fun `api returns error 500`() = runTest {
        val api = provideRickAndMortyApi(
            response = MockResponses.GET_EPISODES_RESPONSE,
            httpStatusCode = HttpStatusCode.InternalServerError
        )
        val result = api.getEpisodes(1)

        assertTrue(result.isFailure)
    }

}