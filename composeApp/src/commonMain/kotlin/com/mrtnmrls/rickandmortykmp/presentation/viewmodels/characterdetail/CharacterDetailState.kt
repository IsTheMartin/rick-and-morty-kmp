package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.characterdetail

import com.mrtnmrls.rickandmortykmp.domain.model.Character

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String? = null
)
