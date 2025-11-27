package com.app.examenmoviles.di

import com.app.examenmoviles.data.remote.api.SudokuApi
import com.app.examenmoviles.data.repository.SudokuRepositoryImpl
import com.app.examenmoviles.domain.repository.SudokuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSudokuApi(retrofit: Retrofit): SudokuApi {
        return retrofit.create(SudokuApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSudokuRepository(
        api: SudokuApi
    ): SudokuRepository {
        return SudokuRepositoryImpl(api)
    }
}