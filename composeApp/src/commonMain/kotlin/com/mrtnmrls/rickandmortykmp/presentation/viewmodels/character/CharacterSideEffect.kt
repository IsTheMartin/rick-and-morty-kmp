package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character

sealed class CharacterSideEffect {
    data class NavigateToCharacterDetail(val id: Int) : CharacterSideEffect()
}