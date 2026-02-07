package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.episode

import com.mrtnmrls.rickandmortykmp.domain.model.Episode

data class EpisodeState (
    val isLoading: Boolean = false,
    val episodes: List<Episode> = emptyList()
) {
    val episodePerSeason = episodes.groupBy { it.episode.substring(0,3) }
}