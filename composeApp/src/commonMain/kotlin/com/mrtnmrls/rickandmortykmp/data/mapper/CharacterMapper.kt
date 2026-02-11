package com.mrtnmrls.rickandmortykmp.data.mapper

import com.mrtnmrls.rickandmortykmp.data.model.CharacterResponse
import com.mrtnmrls.rickandmortykmp.domain.model.Character

fun CharacterResponse.CharacterData.toCharacter(): Character =
    Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        location = location.name,
        image = image,
    )
