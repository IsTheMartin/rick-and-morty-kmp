package com.mrtnmrls.rickandmortykmp.domain.model

import com.mrtnmrls.rickandmortykmp.data.model.CharacterResponse.LocationData

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val location: String,
    val image: String,
)
