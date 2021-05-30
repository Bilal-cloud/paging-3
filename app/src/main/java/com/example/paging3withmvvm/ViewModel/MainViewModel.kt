package com.example.paging3withmvvm.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.paging3withmvvm.data.Network.ApiService
import com.example.paging3withmvvm.data.Pics
import com.example.paging3withmvvm.data.Repository.PicsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MainViewModel
@Inject
constructor(private val apiService: ApiService): ViewModel() {


    fun getAllPics(page:Int):Flow<PagingData<Pics>> = Pager(config =
    PagingConfig(20,maxSize = 100,enablePlaceholders = false)){
        PicsPagingSource(apiService,page)
    }.flow.cachedIn(viewModelScope)


}






//    fun getAllPics(page:Int)= Pager(config =
//    PagingConfig(10,enablePlaceholders = false),
//        pagingSourceFactory = {PicsPagingSource(apiService,page) }
//        ).liveData