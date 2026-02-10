package com.mrtnmrls.rickandmortykmp.data.repository.fake

import com.mrtnmrls.rickandmortykmp.domain.model.Character
import com.mrtnmrls.rickandmortykmp.domain.model.CharacterPaging
import com.mrtnmrls.rickandmortykmp.domain.repository.CharacterRepository

class CharacterRepositoryFake: CharacterRepository {

    var charactersResult: Result<CharacterPaging>? = null
    var characterResult: Result<Character>? = null

    override suspend fun getCharacters(page: Int): Result<CharacterPaging> {
        return charactersResult ?: Result.failure(Exception("Not initialized"))
    }

    override suspend fun getCharacter(id: Int): Result<Character> {
        return characterResult ?: Result.failure(Exception("Not initialized"))
    }

}