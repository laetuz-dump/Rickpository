package com.neotica.core.data

import com.neotica.core.data.source.local.LocalDataSource
import com.neotica.core.data.source.remote.RemoteDataSource
import com.neotica.core.data.source.remote.network.ApiResponse
import com.neotica.core.data.source.remote.response.CharacterResponse
import com.neotica.core.domain.model.Character
import com.neotica.core.domain.repository.ICharacterRepository
import com.neotica.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CharacterRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ICharacterRepository {

    override fun getAllCharacter(): Flow<Resource<List<Character>>> =
        object : NetworkBoundResource<List<Character>, List<CharacterResponse>>(),
            Flow<Resource<List<Character>>> {
            override suspend fun collect(collector: FlowCollector<Resource<List<Character>>>) {
            }

            override fun loadFromDB(): Flow<List<Character>> {
                return localDataSource.getAllCharacter().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<CharacterResponse>>> =
                remoteDataSource.getAllCharacter()

            override suspend fun saveCallResult(data: List<CharacterResponse>) {
                val charList = DataMapper.mapResponsesToEntities(data)
                withContext(Dispatchers.IO){
                    localDataSource.insertCharacter(charList)
                }
            }

            override fun shouldFetch(data: List<Character>?): Boolean = data!!.isEmpty()
        }.asFlow()

    override fun getFavoriteCharacter(): Flow<List<Character>> {
        return localDataSource.getFavoriteCharacter().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override suspend fun setFavoriteCharacter(character: Character, state: Boolean) {
        val charEntity = DataMapper.mapDomainToEntity(character)
        withContext(Dispatchers.IO){
            localDataSource.setFavoriteCharacter(charEntity, state)
        }
        //appExecutors.diskIO().execute {  }
    }
}

