package com.mrtnmrls.rickandmortykmp.presentation.viewmodels.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrtnmrls.rickandmortykmp.domain.repository.LocationRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class LocationViewModel(
    private val locationRepository: LocationRepository
) : ViewModel(), ContainerHost<LocationState, LocationSideEffect> {

    override val container: Container<LocationState, LocationSideEffect> =
        viewModelScope.container(
            initialState = LocationState(),
            onCreate = {
                getLocations()
            }
        )

    private fun getLocations() = intent {
        reduce { state.copy(isLoading = true) }
        locationRepository.getLocation(1)
            .onSuccess { locations ->
                reduce {
                    state.copy(
                        isLoading = false,
                        locations = locations,
                        errorMessage = null
                    )
                }
            }
            .onFailure { error ->
                reduce {
                    state.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
            }
    }
}