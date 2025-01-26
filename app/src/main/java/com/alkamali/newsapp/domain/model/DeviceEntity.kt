package com.alkamali.newsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alkamali.newsapp.util.Constants.DEVICE_NAME_TABLE
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = DEVICE_NAME_TABLE)
data class DeviceEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    val image: String,
    val about: String,
    val rating: Double,
    val processor: String?,
    val ram: String?,
    val storage: String?,
    val releaseDate: String?,
    val battery: String?,
    val screenSize: String?,
    val ports: List<String>,
    val abilities: List<String>,
    val features: List<String>,
    val operatingSystem: String?,
    val manufacturer: String?,
    val dateTime: String?
)

