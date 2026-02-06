package com.mrtnmrls.rickandmortykmp.data.remote

import com.mrtnmrls.rickandmortykmp.data.model.CharacterResponse
import com.mrtnmrls.rickandmortykmp.data.model.LocationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.parameters

class RickAndMortyApi(
    private val httpClient: HttpClient
) {
    suspend fun getCharacters(
        page: Int
    ): CharacterResponse {
        return httpClient
            .get("character") {
                parameter("page", page)
            }
            .body()
    }

    suspend fun getLocations(
        page: Int
    ): LocationResponse {
        return httpClient
            .get("location") {
                parameter("page", page)
            }
            .body()
    }
}