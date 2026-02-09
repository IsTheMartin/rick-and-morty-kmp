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

        try {
            val result = characterRepository.getCharacters(state.page)
            reduce {
                state.copy(
                    isLoading = false,
                    isLoadingNextPage = false,
                    characters = state.characters + result.characters,
                    canLoadMore = result.canLoadMore,
                    page = state.page + 1
                )
            }
        } catch (e: Exception) {
            println(e.toString())
            reduce {
                state.copy(
                    isLoading = false,
                    isLoadingNextPage = false,
                    page = state.page + 1
                )
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