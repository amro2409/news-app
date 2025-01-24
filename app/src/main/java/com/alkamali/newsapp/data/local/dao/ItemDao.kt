package com.alkamali.newsapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.alkamali.newsapp.domain.model.ItemEntity

@Dao
interface ItemDao {

    @Query("SELECT *FROM Items ORDER BY id ASC")
    fun getAllItems(): PagingSource<Int, ItemEntity>
    @Query("SELECT *FROM Items WHERE  id=:heroId ")
    fun getSelectedItem(heroId: Int): ItemEntity

    @Insert
    suspend fun insert(itemEntity: ItemEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(itemEntities: List<ItemEntity>)

    @Delete
    suspend fun delete(itemEntity: ItemEntity)
    @Query("DELETE FROM Items")
    suspend fun deleteAll()

}