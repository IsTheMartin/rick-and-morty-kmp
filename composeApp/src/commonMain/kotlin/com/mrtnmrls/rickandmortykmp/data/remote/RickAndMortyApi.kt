package com.mrtnmrls.rickandmortykmp.data.remote

import com.mrtnmrls.rickandmortykmp.data.model.CharacterResponse
import com.mrtnmrls.rickandmortykmp.data.model.EpisodeResponse
import com.mrtnmrls.rickandmortykmp.data.model.LocationResponse

interface RickAndMortyApi {
    suspend fun getCharacters(page: Int): Result<CharacterResponse>

    suspend fun getCharacter(id: Int): Result<CharacterResponse.CharacterData>

    suspend fun getLocations(page: Int): Result<LocationResponse>

    suspend fun getEpisodes(page: Int): Result<EpisodeResponse>
}
