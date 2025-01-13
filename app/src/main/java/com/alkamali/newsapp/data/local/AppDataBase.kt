package com.alkamali.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alkamali.newsapp.data.local.dao.HeroDao
import com.alkamali.newsapp.data.local.dao.HeroRemoteKeyDao
import com.alkamali.newsapp.domain.model.Hero
import com.alkamali.newsapp.domain.model.HeroRemoteKey

@Database(
    entities =
    [
        Hero::class,
        HeroRemoteKey::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DBConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao

//    companion object {
//        private var INSTANCE: AppDatabase? = null

//        fun getDatabase(context: Context): AppDatabase {
//            return (INSTANCE) ?: synchronized(this) {
//                val tempInstance = Room.databaseBuilder(
//                    context,
//                    AppDatabase::class.java,
//                    "news_db"
//                ).build()
//                INSTANCE = tempInstance
//                tempInstance
//            }
//        }
//    }

}