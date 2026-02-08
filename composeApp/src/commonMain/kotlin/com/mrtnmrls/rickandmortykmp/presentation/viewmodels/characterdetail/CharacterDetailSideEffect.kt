package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.characterdetail

sealed class CharacterDetailSideEffect {
    data object NavigateToBack : CharacterDetailSideEffect()
}