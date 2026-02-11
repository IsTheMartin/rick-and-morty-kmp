package com.mrtnmrls.rickandmortykmp.data.repository

import com.mrtnmrls.rickandmortykmp.data.mapper.toCharacter
import com.mrtnmrls.rickandmortykmp.data.remote.RickAndMortyApi
import com.mrtnmrls.rickandmortykmp.domain.model.Character
import com.mrtnmrls.rickandmortykmp.domain.model.CharacterPaging
import com.mrtnmrls.rickandmortykmp.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): Result<CharacterPaging> =
        api.getCharacters(page).map { response ->
            CharacterPaging(
                characters = response.results.map { it.toCharacter() },
                canLoadMore = response.info.next != null,
            )
        }

    override suspend fun getCharacter(id: Int): Result<Character> =
        api.getCharacter(id).map {
            it.toCharacter()
        }
}
