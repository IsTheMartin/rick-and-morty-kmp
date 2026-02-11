package com.mrtnmrls.rickandmortykmp.data.repository.fake

import com.mrtnmrls.rickandmortykmp.domain.model.Episode
import com.mrtnmrls.rickandmortykmp.domain.repository.EpisodeRepository

class EpisodeRepositoryFake : EpisodeRepository {
    var episodesResult: Result<List<Episode>>? = null

    override suspend fun getEpisodes(page: Int): Result<List<Episode>> = episodesResult ?: Result.failure(Exception("Not initialized"))
}
