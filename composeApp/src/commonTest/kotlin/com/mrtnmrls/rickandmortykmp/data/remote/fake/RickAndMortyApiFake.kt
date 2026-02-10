package com.mrtnmrls.rickandmortykmp.data.remote.fake

import com.mrtnmrls.rickandmortykmp.data.model.CharacterResponse
import com.mrtnmrls.rickandmortykmp.data.model.EpisodeResponse
import com.mrtnmrls.rickandmortykmp.data.model.LocationResponse
import com.mrtnmrls.rickandmortykmp.data.remote.RickAndMortyApi

class RickAndMortyApiFake: RickAndMortyApi {

    var charactersResult: Result<CharacterResponse>? = null
    var characterResult: Result<CharacterResponse.CharacterData>? = null
    var locationsResult: Result<LocationResponse>? = null
    var episodesResult: Result<EpisodeResponse>? = null

    override suspend fun getCharacters(page: Int): Result<CharacterResponse> {
        return charactersResult ?: Result.failure(Exception("Not initialized"))
    }

    override suspend fun getCharacter(id: Int): Result<CharacterResponse.CharacterData> {
        return characterResult ?: Result.failure(Exception("Not initialized"))
    }

    override suspend fun getLocations(page: Int): Result<LocationResponse> {
        return locationsResult ?: Result.failure(Exception("Not initialized"))
    }

    override suspend fun getEpisodes(page: Int): Result<EpisodeResponse> {
        return episodesResult ?: Result.failure(Exception("Not initialized"))
    }

}