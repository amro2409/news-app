package com.alkamali.newsapp.di

import android.content.Context
import com.alkamali.newsapp.data.repository.DataStoreOperationsImpl
import com.alkamali.newsapp.data.repository.Repository
import com.alkamali.newsapp.domain.repository.DataStoreOperations
import com.alkamali.newsapp.domain.use_cases.UseCases
import com.alkamali.newsapp.domain.use_cases.get_all_items.GetAllItemsUS
import com.alkamali.newsapp.domain.use_cases.read_onboarding.ReadOnBoardingUS
import com.alkamali.newsapp.domain.use_cases.save_onboarding.SaveOnBoardingUS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDatastoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
       return DataStoreOperationsImpl(context)
    }
    @Provides
    @Singleton
    fun provideUseCases(
        repository: Repository
    ): UseCases {
       return UseCases(
           getAllItemsUS = GetAllItemsUS(repository),
           saveOnBoardingUS = SaveOnBoardingUS(repository),
           readOnBoardingUS = ReadOnBoardingUS(repository)
       )
    }
}