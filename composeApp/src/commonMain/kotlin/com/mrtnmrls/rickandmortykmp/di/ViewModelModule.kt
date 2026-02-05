package com.mrtnmrls.rickandmortykmp.di

import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.CharacterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterViewModel(get()) }
}