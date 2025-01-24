package com.alkamali.newsapp.di

import com.alkamali.newsapp.data.local.AppDataBase
import com.alkamali.newsapp.data.remote.ApiService
import com.alkamali.newsapp.data.repository.RemoteDataSourceImpl
import com.alkamali.newsapp.domain.repository.RemoteDataSource
import com.alkamali.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient
    ): Retrofit {
        //val contentType = MediaType.parse("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        apiService: ApiService,
        appDataBase: AppDataBase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            apiService = apiService,
            appDataBase = appDataBase
        )
    }

}