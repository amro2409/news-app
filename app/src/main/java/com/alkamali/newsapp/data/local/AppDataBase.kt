package com.alkamali.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alkamali.newsapp.data.local.dao.ItemDao
import com.alkamali.newsapp.data.local.dao.ItemRemoteKeysDao
import com.alkamali.newsapp.domain.model.ItemEntity
import com.alkamali.newsapp.domain.model.ItemRemoteKeys

@Database(
    entities =
    [
        ItemEntity::class,
        ItemRemoteKeys::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(DBConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun itemRemoteKeysDao(): ItemRemoteKeysDao

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