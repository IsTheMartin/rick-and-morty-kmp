package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character

sealed class CharacterSideEffect {
    data class NavigateToCharacterDetail(val id: Int) : CharacterSideEffect()
    data class ShowSnackBar(val message: String) : CharacterSideEffect()
}