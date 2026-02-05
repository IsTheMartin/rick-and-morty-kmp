package com.mrtnmrls.rickandmortykmp.data.repository

import com.mrtnmrls.rickandmortykmp.data.mapper.toCharacter
import com.mrtnmrls.rickandmortykmp.data.remote.RickAndMortyApi
import com.mrtnmrls.rickandmortykmp.domain.model.Character
import com.mrtnmrls.rickandmortykmp.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): List<Character> =
        api.getCharacters(page).results.map { it.toCharacter() }
}