package com.mrtnmrls.rickandmortykmp.presentation.viewmodel.character

import com.mrtnmrls.rickandmortykmp.data.mapper.toCharacter
import com.mrtnmrls.rickandmortykmp.data.repository.fake.CharacterRepositoryFake
import com.mrtnmrls.rickandmortykmp.data.testdata.TestData
import com.mrtnmrls.rickandmortykmp.domain.model.CharacterPaging
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterSideEffect
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterState
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterViewModel
import kotlinx.coroutines.test.runTest
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
    fun `cannot load next page`() = runTest {
        val characters = listOf(TestData.characterData.toCharacter())
        val paging = CharacterPaging(characters, false)
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
                    canLoadMore = false,
                    page = 2,
                    errorMessage = null
                )
            }
            viewModel.loadNextPage()
            expectNoItems()
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
    fun `load next page is error`() = runTest {
        val characters = listOf(TestData.characterData.toCharacter())
        val paging = CharacterPaging(characters, true)
        val errorMessage = "Network error"
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
            repository.charactersResult = Result.failure(Exception(errorMessage))
            viewModel.loadNextPage()
            expectState {
                copy(isLoadingNextPage = true)
            }
            expectState {
                copy(isLoadingNextPage = false)
            }
            expectSideEffect(CharacterSideEffect.ShowSnackBar(errorMessage))
        }
    }

    @Test
    fun `load next page is error with null exception`() = runTest {
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
            repository.charactersResult = Result.failure(Exception())
            viewModel.loadNextPage()
            expectState {
                copy(isLoadingNextPage = true)
            }
            expectState {
                copy(isLoadingNextPage = false)
            }
            expectSideEffect(CharacterSideEffect.ShowSnackBar("An error happened"))
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