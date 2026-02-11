package com.mrtnmrls.rickandmortykmp.di

import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterViewModel
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.characterdetail.CharacterDetailViewModel
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.episode.EpisodeViewModel
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.location.LocationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel {
        CharacterDetailViewModel(
            characterRepository = get(),
            savedStateHandle = get(),
        )
    }
}
