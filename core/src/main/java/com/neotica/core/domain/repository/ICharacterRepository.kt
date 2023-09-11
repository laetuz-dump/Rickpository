package com.neotica.core.domain.repository

import com.neotica.core.data.Resource
import com.neotica.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface ICharacterRepository {

    fun getAllCharacter(): Flow<Resource<List<Character>>>

    fun getFavoriteCharacter(): Flow<List<Character>>

    suspend fun setFavoriteCharacter(character: Character, state: Boolean)

}
