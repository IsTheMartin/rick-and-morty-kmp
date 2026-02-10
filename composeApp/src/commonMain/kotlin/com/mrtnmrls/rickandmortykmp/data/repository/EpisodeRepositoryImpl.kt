package com.mrtnmrls.rickandmortykmp.data.repository

import com.mrtnmrls.rickandmortykmp.data.mapper.toEpisode

import com.mrtnmrls.rickandmortykmp.data.remote.RickAndMortyApi
import com.mrtnmrls.rickandmortykmp.domain.model.Episode
import com.mrtnmrls.rickandmortykmp.domain.repository.EpisodeRepository

class EpisodeRepositoryImpl(
    private val api: RickAndMortyApi
) : EpisodeRepository {
    override suspend fun getEpisodes(page: Int): Result<List<Episode>> {
        return api.getEpisodes(page)
            .map { response ->
                response.results
                    .map { result -> result.toEpisode() }
            }
    }
}