package com.ysanjeet535.newsbox.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ysanjeet535.newsbox.BuildConfig
import com.ysanjeet535.newsbox.data.model.NewsItem
import com.ysanjeet535.newsbox.data.remote.dto.Article
import com.ysanjeet535.newsbox.data.remote.dto.NewsResponse
import com.ysanjeet535.newsbox.data.repository.NewsArticleRepository
import com.ysanjeet535.newsbox.utils.Constants.API_KEY
import com.ysanjeet535.newsbox.utils.Constants.API_KEY2
import com.ysanjeet535.newsbox.utils.ResponseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NewsArticleRepository) : ViewModel() {

    private val _newsResponseLiveData = MutableLiveData<ResponseHandler<NewsResponse>>()
    private val _newsTopicResponseLiveData = MutableLiveData<ResponseHandler<NewsResponse>>()
    private val _newsSearchResponseLiveData = MutableLiveData<ResponseHandler<NewsResponse>>()



    val newsResponseLiveData : LiveData<ResponseHandler<NewsResponse>> get() = _newsResponseLiveData
    val newsTopicResponseLiveData : LiveData<ResponseHandler<NewsResponse>> get() = _newsTopicResponseLiveData
    val newsSearchResponseLiveData : LiveData<ResponseHandler<NewsResponse>> get() = _newsSearchResponseLiveData

    var  newsItemsLiveData : LiveData<List<NewsItem>>

    private var countryCode : String = "us"
    private var category : String = "business"

    private var apiKeyForUse = API_KEY

    init {
        getTopheadlines()
        getTopheadlinesOfTopic()
        newsItemsLiveData = repository.getAllNewsItem()
    }

    fun getTopheadlines(){
        viewModelScope.launch(Dispatchers.IO) {
            _newsResponseLiveData.postValue(ResponseHandler.Loading())
            val response = repository.getTopHeadlines(countryCode, apiKeyForUse)
            _newsResponseLiveData.postValue(handleResponse(response))
        }
    }

    fun getTopheadlinesOfTopic(){
        viewModelScope.launch(Dispatchers.IO) {
            _newsTopicResponseLiveData.postValue(ResponseHandler.Loading())
            val response = repository.getTopHeadlinesOfTopic(countryCode,category ,apiKeyForUse)
            _newsTopicResponseLiveData.postValue(handleResponse(response))
        }
    }

    fun getSearchNews(query : String="bitcoin"){
        viewModelScope.launch(Dispatchers.IO) {
            _newsSearchResponseLiveData.postValue(ResponseHandler.Loading())
            val response = repository.getSearchNews(query = query, apiKeyForUse)
            _newsSearchResponseLiveData.postValue(handleResponse(response))
        }
    }

    private fun handleResponse(response : Response<NewsResponse>) : ResponseHandler<NewsResponse> {
        if(response.isSuccessful){
            response.body()?.let {
                return ResponseHandler.Success(it)
            }
        }
        return ResponseHandler.Error(response.body()?.status)
    }

    fun updateCountryCode(code:String){
        countryCode = code
        Log.d("VIEWMODEL",countryCode)
    }

    fun updateCategory(cate:String){
        category = cate
        Log.d("VIEWMODEL",category)
    }

    //database
    fun insertNewsItem(newsItem: NewsItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNewsItem(newsItem)
        }
    }

    fun deleteNewsItem(newsItem: NewsItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNewsItem(newsItem)
        }
    }

    fun deleteAllNews(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAllNews()
        }
    }

    fun getAllNewsItem(): LiveData<List<NewsItem>> {
        return newsItemsLiveData
    }
}