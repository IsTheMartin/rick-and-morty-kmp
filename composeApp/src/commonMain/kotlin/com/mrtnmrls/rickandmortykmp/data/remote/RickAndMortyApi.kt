package com.mrtnmrls.rickandmortykmp.data.remote

import com.mrtnmrls.rickandmortykmp.data.model.CharacterResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

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
}