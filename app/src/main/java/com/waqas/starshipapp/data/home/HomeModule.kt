package com.waqas.starshipapp.data.home

import com.waqas.starshipapp.data.common.module.NetworkModule
import com.waqas.starshipapp.data.home.remote.api.GetStarshipsApi
import com.waqas.starshipapp.data.home.repository.HomeRepositoryImp
import com.waqas.starshipapp.domain.home.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Singleton
    @Provides
    fun provideGetStarshipsApi(retrofit: Retrofit): GetStarshipsApi {
        return retrofit.create(GetStarshipsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(getStarshipsApi: GetStarshipsApi): HomeRepository {
        return HomeRepositoryImp(getStarshipsApi)
    }
}