package com.mrtnmrls.rickandmortykmp.presentation.viewmodel.episode

import com.mrtnmrls.rickandmortykmp.data.mapper.toEpisode
import com.mrtnmrls.rickandmortykmp.data.repository.fake.EpisodeRepositoryFake
import com.mrtnmrls.rickandmortykmp.data.testdata.TestData
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.episode.EpisodeState
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.episode.EpisodeViewModel
import kotlinx.coroutines.test.runTest
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

class EpisodeViewModelTest {
    val repository = EpisodeRepositoryFake()
    val viewModel = EpisodeViewModel(repository)

    @Test
    fun `initial load success`() =
        runTest {
            val episodes = TestData.episodeResponse.results.map { it.toEpisode() }
            repository.episodesResult = Result.success(episodes)

            viewModel.test(this, EpisodeState()) {
                runOnCreate()
                expectState {
                    copy(isLoading = true)
                }
                expectState {
                    copy(
                        isLoading = false,
                        episodes = episodes,
                        errorMessage = null,
                    )
                }
            }
        }

    @Test
    fun `initial load fails`() =
        runTest {
            val errorMessage = "Network error"
            repository.episodesResult = Result.failure(Exception(errorMessage))

            viewModel.test(this, EpisodeState()) {
                runOnCreate()
                expectState {
                    copy(isLoading = true)
                }
                expectState {
                    copy(
                        isLoading = false,
                        errorMessage = errorMessage,
                    )
                }
            }
        }
}
