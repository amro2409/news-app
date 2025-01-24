package com.alkamali.newsapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alkamali.newsapp.util.Constants.ITEM_REMOTE_KEYS_NAME_TABLE

@Entity(tableName = ITEM_REMOTE_KEYS_NAME_TABLE)
data class ItemRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "prev_key")
    val prevPage: Int?,
    @ColumnInfo(name = "next_key")
    val nextPage: Int?
)
