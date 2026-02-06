package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrtnmrls.rickandmortykmp.domain.repository.LocationRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class LocationViewModel (
    private val locationRepository: LocationRepository
) : ViewModel(), ContainerHost<LocationState, LocationSideEffect> {

    override val container: Container<LocationState, LocationSideEffect> =
        viewModelScope.container(LocationState())

    init {
        getLocations()
    }

    private fun getLocations() = intent {
        reduce { state.copy(isLoading = true) }
        val locations = locationRepository.getLocation(1)
        reduce {
            state.copy(
                isLoading = false,
                locations = locations
            )
        }
    }

}