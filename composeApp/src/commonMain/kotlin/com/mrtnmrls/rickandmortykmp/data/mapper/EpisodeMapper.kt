package com.mrtnmrls.rickandmortykmp.data.mapper

import com.mrtnmrls.rickandmortykmp.data.model.EpisodeResponse
import com.mrtnmrls.rickandmortykmp.domain.model.Episode

fun EpisodeResponse.EpisodeData.toEpisode(): Episode =
    Episode(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
    )
