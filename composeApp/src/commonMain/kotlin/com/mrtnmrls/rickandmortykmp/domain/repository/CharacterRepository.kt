package com.mrtnmrls.rickandmortykmp.domain.repository

import com.mrtnmrls.rickandmortykmp.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(page: Int): List<Character>
    suspend fun getCharacter(id: Int): Character
}