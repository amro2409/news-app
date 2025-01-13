package com.alkamali.newsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alkamali.newsapp.util.Constants.HERO_REMOTE_NAME_TABLE

@Entity(tableName = HERO_REMOTE_NAME_TABLE)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
   val id: Int,
   val prevKey: Int,
   val nextKey: Int
)
