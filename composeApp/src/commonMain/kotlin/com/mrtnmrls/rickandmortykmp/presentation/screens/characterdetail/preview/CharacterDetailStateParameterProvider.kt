package com.mrtnmrls.rickandmortykmp.presentation.screens.characterdetail.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mrtnmrls.rickandmortykmp.domain.model.Character
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.characterdetail.CharacterDetailState

class CharacterDetailStateParameterProvider : PreviewParameterProvider<CharacterDetailState> {
    override val values: Sequence<CharacterDetailState> = sequenceOf(
        CharacterDetailState(
            isLoading = true,
            character = null,
        ),
        CharacterDetailState(
            isLoading = false,
            character = Character(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Humano",
                type = "",
                gender = "Male",
                location = "Earth",
                image = "",
            ),
        ),
        CharacterDetailState(
            isLoading = false,
            character = null,
            error = "An error happened",
        ),
    )
}
