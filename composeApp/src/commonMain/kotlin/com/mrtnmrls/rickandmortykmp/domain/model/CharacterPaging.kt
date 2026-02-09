package com.mrtnmrls.rickandmortykmp.domain.model

data class CharacterPaging (
    val characters: List<Character>,
    val canLoadMore: Boolean
)