package com.mrtnmrls.rickandmortykmp.data.mapper

import com.mrtnmrls.rickandmortykmp.data.model.LocationResponse
import com.mrtnmrls.rickandmortykmp.domain.model.Location

fun LocationResponse.LocationData.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}