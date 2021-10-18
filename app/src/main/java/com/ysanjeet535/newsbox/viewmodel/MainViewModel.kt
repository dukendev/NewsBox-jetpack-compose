package com.ysanjeet535.newsbox.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ysanjeet535.newsbox.data.remote.dto.Article
import com.ysanjeet535.newsbox.data.remote.dto.NewsResponse
import com.ysanjeet535.newsbox.data.repository.NewsArticleRepository
import com.ysanjeet535.newsbox.utils.Constants.API_KEY
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

    private var countryCode : String = "us"
    private var category : String = "business"

    init {
        getTopheadlines()
        getTopheadlinesOfTopic()
        getSearchNews()
    }

    fun getTopheadlines(){
        viewModelScope.launch(Dispatchers.IO) {
            _newsResponseLiveData.postValue(ResponseHandler.Loading())
            val response = repository.getTopHeadlines(countryCode, API_KEY)
            _newsResponseLiveData.postValue(handleResponse(response))
        }
    }

    fun getTopheadlinesOfTopic(){
        viewModelScope.launch(Dispatchers.IO) {
            _newsTopicResponseLiveData.postValue(ResponseHandler.Loading())
            val response = repository.getTopHeadlinesOfTopic(countryCode,category ,API_KEY)
            _newsTopicResponseLiveData.postValue(handleResponse(response))
        }
    }

    fun getSearchNews(query : String="bitcoin"){
        viewModelScope.launch(Dispatchers.IO) {
            _newsSearchResponseLiveData.postValue(ResponseHandler.Loading())
            val response = repository.getSearchNews(query = query, API_KEY)
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

}