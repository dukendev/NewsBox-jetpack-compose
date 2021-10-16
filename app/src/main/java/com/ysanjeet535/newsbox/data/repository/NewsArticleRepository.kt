package com.ysanjeet535.newsbox.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ysanjeet535.newsbox.data.remote.NewsApi
import com.ysanjeet535.newsbox.data.remote.dto.NewsResponse
import javax.inject.Inject

class NewsArticleRepository @Inject constructor (private val newsApi : NewsApi) {

    suspend fun getTopHeadlines(country:String,apiKey :String) =
        newsApi.getTopheadlines(country,apiKey)


    suspend fun getTopHeadlinesOfTopic(country:String,category : String,apiKey :String) =
        newsApi.getTopheadlinesOfTopic(country,category,apiKey)

}


