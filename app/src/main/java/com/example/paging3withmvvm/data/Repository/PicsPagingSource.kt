package com.example.paging3withmvvm.data.Repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3withmvvm.data.Network.ApiService
import com.example.paging3withmvvm.data.Pics
import retrofit2.HttpException
import java.io.IOException

private val DEFAULT_PAGE_INDEX= 34

class PicsPagingSource
constructor(private val apiService: ApiService,private val page:Int): PagingSource<Int,Pics>() {
    override fun getRefreshKey(state: PagingState<Int, Pics>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pics> {

        val page = params.key ?: page
        return try {
            //pass your page No.
            var response = apiService.getAllPics(page)
            LoadResult.Page(
                response,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)
        } catch (exception: HttpException){
            LoadResult.Error(exception)
        }

    }


}









//class PicsPagingSource constructor(private val apiService: ApiService): PagingSource<Int,Pics>() {
//    override fun getRefreshKey(state: PagingState<Int, Pics>): Int? {
//        return null
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pics> {
//
//        val page = params.key ?: DEFAULT_PAGE_INDEX
//        return try {
//            //pass your page No.
//            var response = apiService.getAllPics(page)
//            LoadResult.Page(
//                response,
//                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
//                nextKey = if (response.isEmpty()) null else page + 1
//            )
//        } catch (exception: IOException){
//            LoadResult.Error(exception)
//        } catch (exception: HttpException){
//            LoadResult.Error(exception)
//        }
//
//    }
//
//
//}