package com.mrtnmrls.rickandmortykmp.domain.repository

import com.mrtnmrls.rickandmortykmp.domain.model.Character
import com.mrtnmrls.rickandmortykmp.domain.model.CharacterPaging

interface CharacterRepository {
    suspend fun getCharacters(page: Int): Result<CharacterPaging>
    suspend fun getCharacter(id: Int): Result<Character>
}