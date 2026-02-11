package com.mrtnmrls.rickandmortykmp.presentation.viewmodel.location

import com.mrtnmrls.rickandmortykmp.data.mapper.toLocation
import com.mrtnmrls.rickandmortykmp.data.repository.fake.LocationRepositoryFake
import com.mrtnmrls.rickandmortykmp.data.testdata.TestData
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.location.LocationState
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.location.LocationViewModel
import kotlinx.coroutines.test.runTest
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

class LocationViewModelTest {
    val repository = LocationRepositoryFake()
    val viewModel = LocationViewModel(repository)

    @Test
    fun `initial load success`() =
        runTest {
            val locations = TestData.locationResponse.results.map { it.toLocation() }
            repository.locationsResult = Result.success(locations)

            viewModel.test(this, LocationState()) {
                runOnCreate()
                expectState {
                    copy(isLoading = true)
                }
                expectState {
                    copy(
                        isLoading = false,
                        locations = locations,
                        errorMessage = null,
                    )
                }
            }
        }

    @Test
    fun `initial load fails`() =
        runTest {
            val errorMessage = "Network error"
            repository.locationsResult = Result.failure(Exception(errorMessage))

            viewModel.test(this, LocationState()) {
                runOnCreate()
                expectState {
                    copy(isLoading = true)
                }
                expectState {
                    copy(
                        isLoading = false,
                        errorMessage = errorMessage,
                    )
                }
            }
        }
}
