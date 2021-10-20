package com.ysanjeet535.newsbox.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ysanjeet535.newsbox.data.db.NewsItemDao
import com.ysanjeet535.newsbox.data.model.NewsItem
import com.ysanjeet535.newsbox.data.remote.NewsApi
import com.ysanjeet535.newsbox.data.remote.dto.NewsResponse
import javax.inject.Inject

class NewsArticleRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val newsItemDao: NewsItemDao
) {

    private lateinit var allNews : LiveData<List<NewsItem>>
    //remote
    suspend fun getTopHeadlines(country:String,apiKey :String) =
        newsApi.getTopheadlines(country,apiKey)


    suspend fun getTopHeadlinesOfTopic(country:String,category : String,apiKey :String) =
        newsApi.getTopheadlinesOfTopic(country,category,apiKey)

    suspend fun getSearchNews(query : String,apiKey: String) =
        newsApi.getSearchNews(query = query,key = apiKey)

    //database
    suspend fun insertNewsItem(newsItem: NewsItem){
        newsItemDao.insertNewsItem(newsItem)
    }

    suspend fun deleteNewsItem(newsItem: NewsItem){
        newsItemDao.deleteNewsItem(newsItem)
    }

    suspend fun deleteAllNews(){
        newsItemDao.deleteAllNews()
    }

    init {
        allNews = newsItemDao.getAllNewsItem()
    }

    fun getAllNewsItem(): LiveData<List<NewsItem>> {
        return allNews
    }

}


