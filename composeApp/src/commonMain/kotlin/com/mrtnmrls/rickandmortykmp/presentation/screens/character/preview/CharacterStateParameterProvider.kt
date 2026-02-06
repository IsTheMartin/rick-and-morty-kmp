package com.mrtnmrls.rickandmortykmp.presentation.screens.character.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mrtnmrls.rickandmortykmp.domain.model.Character
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterState

class CharacterStateParameterProvider: PreviewParameterProvider<CharacterState> {
    override val values: Sequence<CharacterState> = sequenceOf(
        CharacterState(
            isLoading = true,
            characters = emptyList()
        ),
        CharacterState(
            isLoading = false,
            characters = listOf(
                Character(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Human",
                    type = "",
                    gender = "Male",
                    location = "Earth",
                    image = ""
                ),
                Character(
                    id = 2,
                    name = "Morty Smith",
                    status = "Alive",
                    species = "Human",
                    type = "",
                    gender = "Male",
                    location = "Earth",
                    image = ""
                )
            )
        )
    )
}