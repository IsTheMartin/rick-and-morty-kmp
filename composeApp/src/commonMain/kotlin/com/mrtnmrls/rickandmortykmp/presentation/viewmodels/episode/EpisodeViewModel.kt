package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrtnmrls.rickandmortykmp.domain.repository.EpisodeRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class EpisodeViewModel(
    private val episodeRepository: EpisodeRepository
) : ViewModel(), ContainerHost<EpisodeState, EpisodeSideEffect> {

    override val container: Container<EpisodeState, EpisodeSideEffect> =
        viewModelScope.container(EpisodeState())

    init {
        getEpisodes()
    }

    private fun getEpisodes() = intent {
        reduce { state.copy(isLoading = true) }
        episodeRepository.getEpisodes(1)
            .onSuccess { episodes ->
                reduce {
                    state.copy(
                        isLoading = false,
                        episodes = episodes,
                        errorMessage = null
                    )
                }
            }
            .onFailure { error ->
                state.copy(
                    isLoading = false,
                    errorMessage = error.message
                )
            }
    }
}