package com.example.paging3withmvvm.data.Network

import com.example.paging3withmvvm.data.Pics
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object{
        const val BASE_URL = "https://picsum.photos/"
    }

    @GET("v2/list")
    suspend fun getAllPics(
        @Query("page") page:Int
       // @Query("limit") limit:Int
    ):List<Pics>
}