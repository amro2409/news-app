package com.alkamali.newsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alkamali.newsapp.util.Constants.ITEM_NAME_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = ITEM_NAME_TABLE)
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>
)
