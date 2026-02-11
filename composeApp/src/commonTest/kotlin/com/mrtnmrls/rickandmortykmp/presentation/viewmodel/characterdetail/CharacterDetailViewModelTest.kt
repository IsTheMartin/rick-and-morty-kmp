package com.mrtnmrls.rickandmortykmp.presentation.viewmodel.characterdetail

import androidx.lifecycle.SavedStateHandle
import com.mrtnmrls.rickandmortykmp.data.mapper.toCharacter
import com.mrtnmrls.rickandmortykmp.data.repository.fake.CharacterRepositoryFake
import com.mrtnmrls.rickandmortykmp.data.testdata.TestData
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.characterdetail.CharacterDetailSideEffect
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.characterdetail.CharacterDetailState
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.characterdetail.CharacterDetailViewModel
import kotlinx.coroutines.test.runTest
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

class CharacterDetailViewModelTest {
    val repository = CharacterRepositoryFake()
    val savedStateHandle = SavedStateHandle(mapOf("id" to 1))
    val viewModel = CharacterDetailViewModel(repository, savedStateHandle)

    @Test
    fun `initial load success`() =
        runTest {
            val character = TestData.characterData.toCharacter()
            repository.characterResult = Result.success(character)

            viewModel.test(this, CharacterDetailState()) {
                runOnCreate()
                expectState {
                    copy(isLoading = true)
                }
                expectState {
                    copy(
                        isLoading = false,
                        character = character,
                    )
                }
            }
        }

    @Test
    fun `initial load fails`() =
        runTest {
            val errorMessage = "Network error"
            repository.characterResult = Result.failure(Exception(errorMessage))

            viewModel.test(this, CharacterDetailState()) {
                runOnCreate()
                expectState {
                    copy(isLoading = true)
                }
                expectState {
                    copy(
                        isLoading = false,
                        error = errorMessage,
                    )
                }
            }
        }

    @Test
    fun `on character item clicked emits navigation side effect`() =
        runTest {
            viewModel.test(this, CharacterDetailState()) {
                containerHost.navigateBack()
                expectSideEffect(CharacterDetailSideEffect.NavigateToBack)
            }
        }
}
