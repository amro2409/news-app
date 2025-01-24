package com.alkamali.newsapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alkamali.newsapp.data.local.AppDataBase
import com.alkamali.newsapp.data.paging_source.ItemRemoteMediator
import com.alkamali.newsapp.data.remote.ApiService
import com.alkamali.newsapp.domain.model.ItemEntity
import com.alkamali.newsapp.domain.repository.RemoteDataSource
import com.alkamali.newsapp.util.Constants.SIZE_PER_PAGE
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class RemoteDataSourceImpl (
    private val apiService: ApiService,
    private val appDataBase: AppDataBase
): RemoteDataSource {
    private val itemDao = appDataBase.itemDao()


    override fun getAllData(): Flow<PagingData<ItemEntity>> {
        val pagingSourceFactory = {itemDao.getAllItems()}
        return Pager(
            config = PagingConfig(
                pageSize = SIZE_PER_PAGE
            ),
            remoteMediator = ItemRemoteMediator(
                apiService = apiService,
                appDataBase = appDataBase
            ),
            pagingSourceFactory= pagingSourceFactory
        ).flow

    }

    override fun searchItems(name: String): Flow<PagingData<ItemEntity>> {
        TODO("Not yet implemented")
    }
}