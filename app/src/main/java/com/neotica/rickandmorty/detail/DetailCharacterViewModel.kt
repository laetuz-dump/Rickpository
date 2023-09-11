package com.neotica.rickandmorty.detail

import androidx.lifecycle.ViewModel
import com.neotica.core.domain.model.Character
import com.neotica.core.domain.usecase.CharacterUseCase

class DetailCharacterViewModel(private val charUseCase: CharacterUseCase) : ViewModel() {
    suspend fun setFavoriteCharacter(char: Character, newStatus: Boolean) =
        charUseCase.setFavoriteCharacter(char, newStatus)
}

