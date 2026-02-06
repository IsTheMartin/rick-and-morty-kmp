package com.mrtnmrls.rickandmortykmp.di

import com.mrtnmrls.rickandmortykmp.data.remote.RickAndMortyApi
import com.mrtnmrls.rickandmortykmp.data.repository.CharacterRepositoryImpl
import com.mrtnmrls.rickandmortykmp.data.repository.LocationRepositoryImpl
import com.mrtnmrls.rickandmortykmp.domain.repository.CharacterRepository
import com.mrtnmrls.rickandmortykmp.domain.repository.LocationRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val repositoryModule = module {
    single<CharacterRepository> {
        CharacterRepositoryImpl(get())
    }
    single<LocationRepository> {
        LocationRepositoryImpl(get())
    }
}