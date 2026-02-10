package com.mrtnmrls.rickandmortykmp.data.remote.impl

import com.mrtnmrls.rickandmortykmp.data.model.CharacterResponse
import com.mrtnmrls.rickandmortykmp.data.model.EpisodeResponse
import com.mrtnmrls.rickandmortykmp.data.model.LocationResponse
import com.mrtnmrls.rickandmortykmp.data.remote.RickAndMortyApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class RickAndMortyApiImpl(
    private val httpClient: HttpClient
): RickAndMortyApi {
    override suspend fun getCharacters(
        page: Int
    ): Result<CharacterResponse> {
        return safeApiCall {
            httpClient.get("character") {
                parameter("page", page)
            }
        }
    }

    override suspend fun getCharacter(
        id: Int
    ): Result<CharacterResponse.CharacterData> {
        return safeApiCall {
            httpClient
                .get("character/$id")
        }
    }

    override suspend fun getLocations(
        page: Int
    ): Result<LocationResponse> {
        return safeApiCall {
            httpClient
                .get("location") {
                    parameter("page", page)
                }
        }
    }

    override suspend fun getEpisodes(
        page: Int
    ): Result<EpisodeResponse> {
        return safeApiCall {
            httpClient
                .get("episode") {
                    parameter("page", page)
                }
        }
    }

    suspend inline fun <reified T> safeApiCall(crossinline body: suspend () -> HttpResponse): Result<T> {
        return runCatching {
            withContext(Dispatchers.IO) {
                val response = body()
                if (response.status == HttpStatusCode.Companion.TooManyRequests) {
                    throw Exception(TOO_MANY_REQUEST_MESSAGE)
                }
                if (response.status == HttpStatusCode.Companion.InternalServerError) {
                    throw Exception("")
                }
                response.body<T>()
            }
        }
    }

    companion object {
        const val TOO_MANY_REQUEST_MESSAGE = "Too many requests in server, please wait a few seconds."
    }
}