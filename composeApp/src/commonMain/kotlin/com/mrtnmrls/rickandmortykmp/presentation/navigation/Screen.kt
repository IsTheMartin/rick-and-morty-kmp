package com.mrtnmrls.rickandmortykmp.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object Characters: Screen()

    @Serializable
    object Locations: Screen()

    @Serializable
    object Episodes: Screen()
}