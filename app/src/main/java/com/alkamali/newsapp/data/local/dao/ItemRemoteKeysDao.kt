package com.alkamali.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alkamali.newsapp.domain.model.ItemRemoteKeys

@Dao
interface ItemRemoteKeysDao {

    @Query("SELECT *FROM Item_Remote_Keys WHERE id=:id")
    suspend fun getItemRemoteKeys(id: Int): ItemRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(itemRemoteKeys: List<ItemRemoteKeys>)

    @Query("DELETE FROM Item_Remote_Keys")
    suspend fun deleteAll()
}