package com.mrtnmrls.rickandmortykmp.data.repository

import com.mrtnmrls.rickandmortykmp.data.mapper.toCharacter
import com.mrtnmrls.rickandmortykmp.data.remote.RickAndMortyApi
import com.mrtnmrls.rickandmortykmp.domain.model.Character
import com.mrtnmrls.rickandmortykmp.domain.model.CharacterPaging
import com.mrtnmrls.rickandmortykmp.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): CharacterPaging {
        val characters = api.getCharacters(page)
        return CharacterPaging(
            characters = characters.results.map { it.toCharacter() },
            canLoadMore = characters.info.next != null
        )
    }


    override suspend fun getCharacter(id: Int): Character =
        api.getCharacter(id).toCharacter()
}