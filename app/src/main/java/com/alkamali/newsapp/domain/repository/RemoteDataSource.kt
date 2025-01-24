package com.alkamali.newsapp.domain.repository

import androidx.paging.PagingData
import com.alkamali.newsapp.domain.model.ItemEntity
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllData(): Flow<PagingData<ItemEntity>>
    fun searchItems(name: String): Flow<PagingData<ItemEntity>>
}