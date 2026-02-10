package com.mrtnmrls.rickandmortykmp.di

import com.mrtnmrls.rickandmortykmp.data.repository.CharacterRepositoryImpl
import com.mrtnmrls.rickandmortykmp.data.repository.EpisodeRepositoryImpl
import com.mrtnmrls.rickandmortykmp.data.repository.LocationRepositoryImpl
import com.mrtnmrls.rickandmortykmp.domain.repository.CharacterRepository
import com.mrtnmrls.rickandmortykmp.domain.repository.EpisodeRepository
import com.mrtnmrls.rickandmortykmp.domain.repository.LocationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CharacterRepository> {
        CharacterRepositoryImpl(get())
    }
    single<LocationRepository> {
        LocationRepositoryImpl(get())
    }
    single<EpisodeRepository> {
        EpisodeRepositoryImpl(get())
    }
}