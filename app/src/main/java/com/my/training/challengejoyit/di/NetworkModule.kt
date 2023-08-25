package com.my.training.challengejoyit.di

import com.my.training.challengejoyit.ui.login.data.network.ChallengueApiClient
import com.my.training.challengejoyit.ui.login.navigation.AppNavigator
import com.my.training.challengejoyit.ui.login.navigation.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideChallengeApiClient(retrofit: Retrofit) : ChallengueApiClient {
        return retrofit.create(ChallengueApiClient::class.java)
    }

}