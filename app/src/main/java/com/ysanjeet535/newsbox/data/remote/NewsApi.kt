package com.ysanjeet535.newsbox.data.remote

import com.ysanjeet535.newsbox.data.remote.dto.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopheadlines(
        @Query("country") country : String = "us",
        @Query("apiKey") key : String
    ) : Response<NewsResponse>
}