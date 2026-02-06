package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character

import com.mrtnmrls.rickandmortykmp.domain.model.Character

data class CharacterState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null
)
