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
        reduce { state.copy(isLoading = true) }
        val characters = characterRepository.getCharacters(1)
        reduce {
            state.copy(
                isLoading = false,
                characters = characters
            )
        }
    }

}