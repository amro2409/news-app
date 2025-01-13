package com.alkamali.newsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alkamali.newsapp.util.Constants.HERO_NAME_TABLE

@Entity(tableName = HERO_NAME_TABLE)
data class Hero(
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
