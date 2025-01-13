package com.alkamali.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alkamali.newsapp.domain.model.HeroRemoteKey

@Dao
interface HeroRemoteKeyDao {

    @Query("SELECT *FROM Hero_Remote_Key WHERE id=:id")
    suspend fun getHeroRemoteKey(id: Int): HeroRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(heroRemoteKey: List<HeroRemoteKey>)

    @Query("DELETE FROM Hero_Remote_Key")
    suspend fun deleteAll()
}