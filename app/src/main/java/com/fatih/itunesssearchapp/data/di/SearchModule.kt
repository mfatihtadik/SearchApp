package com.fatih.itunesssearchapp.data.di

import android.content.Context
import com.fatih.itunesssearchapp.SearchApplication
import com.fatih.itunesssearchapp.data.repository.SearchRepostoryImpl
import com.fatih.itunesssearchapp.data.service.ApiServices
import com.fatih.itunesssearchapp.domain.repository.SeachRepository
import com.fatih.itunesssearchapp.domain.usecase.GetSearchUsecase
import com.fatih.itunesssearchapp.presentation.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {
    @Provides
    @Singleton
    fun provideSearchApi() : ApiServices {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
    @Provides
    @Singleton
    fun provideSearchRepository(api : ApiServices) : SeachRepository{
        return SearchRepostoryImpl(api = api)
    }
    @Provides
    @Singleton
    fun provideGetSearchUseCase(repository: SearchRepostoryImpl):GetSearchUsecase{
        return GetSearchUsecase(repository)
    }
    @Provides
    @Singleton
    fun provideAppContext(app:SearchApplication) : Context = app.applicationContext
}