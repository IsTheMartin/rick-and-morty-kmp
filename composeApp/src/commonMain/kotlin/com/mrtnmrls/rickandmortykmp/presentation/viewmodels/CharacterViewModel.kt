package com.mrtnmrls.rickandmortykmp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrtnmrls.rickandmortykmp.domain.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val characterRepository: CharacterRepository
): ViewModel() {

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            val characters = characterRepository.getCharacters(1)
            println(characters)
        }
    }

}