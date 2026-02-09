package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mrtnmrls.rickandmortykmp.domain.repository.CharacterRepository
import com.mrtnmrls.rickandmortykmp.presentation.navigation.Screen
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterSideEffect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class CharacterDetailViewModel(
    private val characterRepository: CharacterRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), ContainerHost<CharacterDetailState, CharacterDetailSideEffect> {

    override val container: Container<CharacterDetailState, CharacterDetailSideEffect> =
        viewModelScope.container(CharacterDetailState())

    private val characterId: Int = savedStateHandle.toRoute<Screen.CharactersDetail>().id

    init {
        getCharacter()
    }

    private fun getCharacter() = intent {
        reduce { state.copy(isLoading = true) }
        characterRepository.getCharacter(characterId)
            .onSuccess { character ->
                reduce {
                    state.copy(
                        isLoading = false,
                        character = character,
                        error = null
                    )
                }
            }
            .onFailure { error ->
                reduce {
                    state.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            }

    }

    fun navigateBack() = intent {
        postSideEffect(CharacterDetailSideEffect.NavigateToBack)
    }

}