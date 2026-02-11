package com.mrtnmrls.rickandmortykmp.domain.repository

import com.mrtnmrls.rickandmortykmp.domain.model.Episode

interface EpisodeRepository {
    suspend fun getEpisodes(page: Int): Result<List<Episode>>
}
