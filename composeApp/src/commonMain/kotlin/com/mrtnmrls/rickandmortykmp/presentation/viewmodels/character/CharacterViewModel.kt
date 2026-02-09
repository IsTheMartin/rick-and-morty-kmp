package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrtnmrls.rickandmortykmp.domain.repository.CharacterRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel(), ContainerHost<CharacterState, CharacterSideEffect> {

    override val container: Container<CharacterState, CharacterSideEffect> =
        viewModelScope.container(CharacterState())

    init {
        getCharacters()
    }

    private fun getCharacters() = intent {
        if (state.isLoading || !state.canLoadMore || state.isLoadingNextPage) return@intent

        if (state.characters.isEmpty()) {
            reduce { state.copy(isLoading = true) }
        } else {
            reduce { state.copy(isLoadingNextPage = true) }
        }

        characterRepository.getCharacters(state.page).onSuccess { result ->
            reduce {
                state.copy(
                    isLoading = false,
                    isLoadingNextPage = false,
                    characters = state.characters + result.characters,
                    canLoadMore = result.canLoadMore,
                    page = state.page + 1
                )
            }
        }
            .onFailure { error ->
                if (state.characters.isEmpty()) {
                    reduce {
                        state.copy(
                            isLoading = false,
                            isLoadingNextPage = false,
                            errorMessage = error.message
                        )
                    }
                }
                else {
                    reduce {
                        state.copy(
                            isLoading = false,
                            isLoadingNextPage = false
                        )
                    }
                    postSideEffect(CharacterSideEffect.ShowSnackBar(error.message ?: "An error happened"))
                }
            }

    }

    fun onCharacterClicked(id: Int) = intent {
        postSideEffect(CharacterSideEffect.NavigateToCharacterDetail(id))
    }

    fun loadNextPage() = intent {
        getCharacters()
    }

}