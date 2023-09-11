package com.neotica.core.domain.usecase

import com.neotica.core.data.Resource
import com.neotica.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterUseCase {
    fun getAllCharacter(): Flow<Resource<List<Character>>>
    fun getFavoriteCharacter(): Flow<List<Character>>
    suspend fun setFavoriteCharacter(char: Character, state: Boolean)
}