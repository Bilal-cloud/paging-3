package com.example.paging3withmvvm.di

import com.example.paging3withmvvm.data.Network.ApiService
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesApiService()=Retrofit.Builder().baseUrl(ApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(ApiService::class.java)

//    private val moshi  = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()!!

//    @Provides
//    @Singleton
//    fun providesApiService() = Retrofit
//        .Builder()
//        .baseUrl(ApiService.BASE_URL)
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .build()
//        .create(ApiService::class.java)!!
}