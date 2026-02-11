package com.mrtnmrls.rickandmortykmp.presentation.screens.episode.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mrtnmrls.rickandmortykmp.domain.model.Episode
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.episode.EpisodeState

class EpisodeStateParameterProvider : PreviewParameterProvider<EpisodeState> {
    override val values: Sequence<EpisodeState> = sequenceOf(
        EpisodeState(
            isLoading = true,
            episodes = emptyList(),
        ),
        EpisodeState(
            isLoading = false,
            episodes = listOf(
                Episode(
                    id = 1,
                    name = "Pilot",
                    airDate = "2026-01-01",
                    episode = "S01E01",
                ),
                Episode(
                    id = 2,
                    name = "Co-Pilot",
                    airDate = "2026-01-02",
                    episode = "S02E02",
                ),
            ),
        ),
    )
}
