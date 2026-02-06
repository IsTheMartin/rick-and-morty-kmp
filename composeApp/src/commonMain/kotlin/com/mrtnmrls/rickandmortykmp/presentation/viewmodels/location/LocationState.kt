package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.location

import com.mrtnmrls.rickandmortykmp.domain.model.Location

data class LocationState(
    val isLoading: Boolean = false,
    val locations: List<Location> = emptyList()
)
