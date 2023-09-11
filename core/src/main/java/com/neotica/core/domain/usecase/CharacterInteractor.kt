package com.neotica.core.domain.usecase

import com.neotica.core.data.Resource
import com.neotica.core.domain.model.Character
import com.neotica.core.domain.repository.ICharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterInteractor(private val characterRepository: ICharacterRepository): CharacterUseCase {

    override fun getAllCharacter(): Flow<Resource<List<Character>>> =
        characterRepository.getAllCharacter()

    override fun getFavoriteCharacter(): Flow<List<Character>> = characterRepository.getFavoriteCharacter()

    override suspend fun setFavoriteCharacter(char: Character, state: Boolean) = characterRepository.setFavoriteCharacter(char, state)
}