package com.ysanjeet535.newsbox.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ysanjeet535.newsbox.data.remote.dto.NewsResponse
import com.ysanjeet535.newsbox.data.repository.NewsArticleRepository
import com.ysanjeet535.newsbox.utils.Constants.API_KEY
import com.ysanjeet535.newsbox.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NewsArticleRepository) : ViewModel() {

    val newsResponse : LiveData<NewsResponse> get() = repository.newsResponseLiveData
    val newsResponseTopic : LiveData<NewsResponse> get() = repository.newsTopicResponseLiveData

    private var countryCode : String = "us"
    private var category : String = "business"

    init {
        getTopheadlines()
        getTopheadlinesOfTopic()
    }

    fun getTopheadlines(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTopHeadlines(countryCode, API_KEY)
        }
    }

    fun getTopheadlinesOfTopic(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTopHeadlinesOfTopic(countryCode,category ,API_KEY)
        }
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