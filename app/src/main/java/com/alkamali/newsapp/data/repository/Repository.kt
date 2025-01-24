package com.alkamali.newsapp.data.repository

import androidx.paging.PagingData
import com.alkamali.newsapp.domain.model.ItemEntity
import com.alkamali.newsapp.domain.repository.DataStoreOperations
import com.alkamali.newsapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


// in repository we are going to add basically all functionalities
// which we are going to need in this application.
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {
    fun getAllItem(): Flow<PagingData<ItemEntity>> {
        return remoteDataSource.getAllData()
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}