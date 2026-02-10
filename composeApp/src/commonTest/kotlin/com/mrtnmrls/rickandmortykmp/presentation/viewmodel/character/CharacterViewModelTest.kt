package com.mrtnmrls.rickandmortykmp.presentation.viewmodel.character

import com.mrtnmrls.rickandmortykmp.data.mapper.toCharacter
import com.mrtnmrls.rickandmortykmp.data.repository.fake.CharacterRepositoryFake
import com.mrtnmrls.rickandmortykmp.data.testdata.TestData
import com.mrtnmrls.rickandmortykmp.domain.model.CharacterPaging
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterSideEffect
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterState
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterViewModel
import kotlinx.coroutines.test.runTest
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

class CharacterViewModelTest {

    val repository = CharacterRepositoryFake()
    val viewModel = CharacterViewModel(repository)

    @Test
    fun `initial load success`() = runTest {
        val characters = listOf(TestData.characterData.toCharacter())
        val paging = CharacterPaging(characters, true)
        repository.charactersResult = Result.success(paging)

        viewModel.test(this, CharacterState()) {
            runOnCreate()
            expectState {
                copy(isLoading = true)
            }
            expectState {
                copy(
                    isLoading = false,
                    characters = characters,
                    canLoadMore = true,
                    page = 2,
                    errorMessage = null
                )
            }
        }
    }

    @Test
    fun `load next page is success`() = runTest {
        val characters = listOf(TestData.characterData.toCharacter())
        val paging = CharacterPaging(characters, true)
        repository.charactersResult = Result.success(paging)

        viewModel.test(this, CharacterState()) {
            runOnCreate()
            expectState {
                copy(isLoading = true)
            }
            expectState {
                copy(
                    isLoading = false,
                    characters = characters,
                    canLoadMore = true,
                    page = 2,
                    errorMessage = null
                )
            }
            viewModel.loadNextPage()
            expectState {
                copy(isLoadingNextPage = true)
            }
            expectState {
                copy(
                    isLoadingNextPage = false,
                    characters = characters.plus(characters),
                    canLoadMore = true,
                    page = 3,
                    errorMessage = null
                )
            }
        }
    }

    @Test
    fun `initial load fails`() = runTest {
        val errorMessage = "Network error"
        repository.charactersResult = Result.failure(Exception(errorMessage))

        viewModel.test(this, CharacterState()) {
            runOnCreate()
            expectState {
                copy(isLoading = true)
            }
            expectState {
                copy(
                    isLoading = false,
                    errorMessage = errorMessage
                )
            }
        }
    }

    @Test
    fun `on character item clicked emits navigation side effect`() = runTest {
        viewModel.test(this, CharacterState()) {
            containerHost.onCharacterClicked(1)
            expectSideEffect(CharacterSideEffect.NavigateToCharacterDetail(1))
        }
    }
}