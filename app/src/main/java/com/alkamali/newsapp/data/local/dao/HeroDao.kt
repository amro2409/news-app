package com.alkamali.newsapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.alkamali.newsapp.domain.model.Hero

@Dao
interface HeroDao {

    @Query("SELECT *FROM heroes ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>
    @Query("SELECT *FROM Heroes WHERE  id=:heroId ")
    fun getSelectedHero(heroId: Int): Hero

    @Insert
    suspend fun insert(hero: Hero)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hero: List<Hero>)

    @Delete
    suspend fun delete(hero: Hero)
    @Query("DELETE FROM Heroes")
    suspend fun deleteAll()

}