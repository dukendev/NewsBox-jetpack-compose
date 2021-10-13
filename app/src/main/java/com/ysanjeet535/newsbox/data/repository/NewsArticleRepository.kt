package com.ysanjeet535.newsbox.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ysanjeet535.newsbox.data.remote.NewsApi
import com.ysanjeet535.newsbox.data.remote.dto.NewsResponse
import com.ysanjeet535.newsbox.utils.Constants.API_KEY
import com.ysanjeet535.newsbox.utils.Response
import javax.inject.Inject

class NewsArticleRepository @Inject constructor (private val newsApi : NewsApi) {

    private val _newsResponseLiveData = MutableLiveData<NewsResponse>()

    val newsResponseLiveData : LiveData<NewsResponse> get() = _newsResponseLiveData

    suspend fun getTopHeadlines(country:String,apiKey :String) {

            val result = newsApi.getTopheadlines("us",API_KEY)
            if(result?.body()!=null){
                _newsResponseLiveData.postValue(result.body()!!)
            }

    }
}


