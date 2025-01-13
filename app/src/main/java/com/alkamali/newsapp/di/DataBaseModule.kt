package com.alkamali.newsapp.di

import android.content.Context
import androidx.room.Room
import com.alkamali.newsapp.data.local.AppDataBase
import com.alkamali.newsapp.util.Constants.NAME_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDataBase::class.java, NAME_DATABASE).build()

}