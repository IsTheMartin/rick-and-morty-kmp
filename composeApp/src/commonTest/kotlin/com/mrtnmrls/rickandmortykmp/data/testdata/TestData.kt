package com.mrtnmrls.rickandmortykmp.data.testdata

import com.mrtnmrls.rickandmortykmp.data.model.CharacterResponse
import com.mrtnmrls.rickandmortykmp.data.model.EpisodeResponse
import com.mrtnmrls.rickandmortykmp.data.model.InfoResponse
import com.mrtnmrls.rickandmortykmp.data.model.LocationResponse

object TestData {
    val infoResponse = InfoResponse(
        count = 1,
        pages = 1,
        next = null,
        prev = null,
    )

    val characterData = CharacterResponse.CharacterData(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = CharacterResponse.CharacterData.LocationData(
            name = "Earth",
            url = ""
        ),
        location = CharacterResponse.CharacterData.LocationData(
            name = "Earth",
            url = ""
        ),
        image = "",
        episode = listOf(),
        url = "",
        created = "",
    )

    val characterResponse = CharacterResponse(
        info = infoResponse,
        results = listOf(characterData)
    )

    val locationResponse = LocationResponse(
        info = infoResponse,
        results = listOf(
            LocationResponse.LocationData(
                id = 1,
                name = "Earth",
                type = "Planet",
                dimension = ""
            )
        )
    )

    val episodeResponse = EpisodeResponse(
        info = infoResponse,
        results = listOf(
            EpisodeResponse.EpisodeData(
                id = 1,
                name = "Pilot",
                airDate = "2026, 9 02",
                episode = "S01E01"
            )
        )
    )
}