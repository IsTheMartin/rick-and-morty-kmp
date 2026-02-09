package com.mrtnmrls.rickandmortykmp.data.remote

import com.mrtnmrls.rickandmortykmp.data.model.CharacterResponse
import com.mrtnmrls.rickandmortykmp.data.model.EpisodeResponse
import com.mrtnmrls.rickandmortykmp.data.model.LocationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class RickAndMortyApi(
    private val httpClient: HttpClient
) {
    suspend fun getCharacters(
        page: Int
    ): Result<CharacterResponse> {
        return safeApiCall {
            httpClient.get("character") {
                parameter("page", page)
            }
        }
    }

    suspend fun getCharacter(
        id: Int
    ): Result<CharacterResponse.CharacterData> {
        return safeApiCall {
            httpClient
                .get("character/$id")
        }
    }

    suspend fun getLocations(
        page: Int
    ): Result<LocationResponse> {
        return safeApiCall {
            httpClient
                .get("location") {
                    parameter("page", page)
                }
        }
    }

    suspend fun getEpisodes(
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
                if (response.status.value == 429) {
                    throw Exception("Too many requests in server, please wait a few seconds.")
                }
                response.body<T>()
            }
        }
    }
}