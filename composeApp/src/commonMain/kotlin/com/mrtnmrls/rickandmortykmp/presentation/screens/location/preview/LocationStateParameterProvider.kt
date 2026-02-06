package com.mrtnmrls.rickandmortykmp.presentation.screens.location.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mrtnmrls.rickandmortykmp.domain.model.Character
import com.mrtnmrls.rickandmortykmp.domain.model.Location
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterState
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.location.LocationState

class LocationStateParameterProvider: PreviewParameterProvider<LocationState> {
    override val values: Sequence<LocationState> = sequenceOf(
        LocationState(
            isLoading = true,
            locations = emptyList()
        ),
        LocationState(
            isLoading = false,
            locations = listOf(
                Location(
                    id = 1,
                    name = "Earth",
                    type = "Planet",
                    dimension = "Dimension C-137"
                ),
                Location(
                    id = 2,
                    name = "Mars",
                    type = "Planet",
                    dimension = "Milky Way"
                ),
                Location(
                    id = 3,
                    name = "Glacio",
                    type = "Planet",
                    dimension = "Milky Way"
                )
            )
        )
    )
}