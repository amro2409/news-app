package com.alkamali.newsapp.data.paging_source

import androidx.paging.*
import androidx.room.withTransaction
import com.alkamali.newsapp.data.local.AppDataBase
import com.alkamali.newsapp.data.remote.ApiService
import com.alkamali.newsapp.data.remote.data
import com.alkamali.newsapp.domain.model.ItemEntity
import com.alkamali.newsapp.domain.model.ItemRemoteKeys
import javax.inject.Inject

// MyApi, MyADb
@OptIn(ExperimentalPagingApi::class)
class ItemRemoteMediator @Inject constructor(
    private val apiService: ApiService,
    private val appDataBase: AppDataBase
) : RemoteMediator<Int, ItemEntity>()/*, PagedList.BoundaryCallback*/ {

    private val itemDao = appDataBase.itemDao()
    private val itemRemoteKeysDao = appDataBase.itemRemoteKeysDao()

    //@Suppress("IMPLICIT_CAST_TO_ANY")
    override suspend fun load(loadType: LoadType, state: PagingState<Int, ItemEntity>): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    getRemoteKeyClosesToCurrentPosition(state)?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyToFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyToLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                    nextPage
                }

            }

            val response = data // apiService.getAllHeroes(page = page)
            if (response.entityList.isNotEmpty()) {
                appDataBase.withTransaction {

                    if (loadType == LoadType.REFRESH) {
                        itemRemoteKeysDao.deleteAll()
                        itemDao.deleteAll()
                    }

                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.entityList.map {
                        ItemRemoteKeys(
                            id = it.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }

                    itemRemoteKeysDao.insertAll(itemRemoteKeys = keys)
                    itemDao.insertAll(itemEntities = response.entityList)
                }
            }

            return MediatorResult.Success(
                endOfPaginationReached = response.nextPage == null
            )
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyToLastItem(
        state: PagingState<Int, ItemEntity>
    ): ItemRemoteKeys? {

        return state.pages.lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()?.run {
                itemRemoteKeysDao.getItemRemoteKeys(this.id)
            }

    }

    private suspend fun getRemoteKeyToFirstItem(
        state: PagingState<Int, ItemEntity>
    ): ItemRemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { hero ->
            itemRemoteKeysDao.getItemRemoteKeys(hero.id)
        }
    }

    private suspend fun getRemoteKeyClosesToCurrentPosition(
        state: PagingState<Int, ItemEntity>
    ): ItemRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                itemRemoteKeysDao.getItemRemoteKeys(id)
            }
        }
    }

}