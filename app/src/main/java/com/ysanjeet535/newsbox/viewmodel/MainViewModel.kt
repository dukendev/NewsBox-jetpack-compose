package com.ysanjeet535.newsbox.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.ysanjeet535.newsbox.App
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
class MainViewModel @Inject constructor(private val app : App,private val repository: NewsArticleRepository) : AndroidViewModel(app) {

    private val _newsResponseLiveData = MutableLiveData<ResponseHandler<NewsResponse>>()
    private val _newsTopicResponseLiveData = MutableLiveData<ResponseHandler<NewsResponse>>()
    private val _newsSearchResponseLiveData = MutableLiveData<ResponseHandler<NewsResponse>>()



    val newsResponseLiveData : LiveData<ResponseHandler<NewsResponse>> get() = _newsResponseLiveData
    val newsTopicResponseLiveData : LiveData<ResponseHandler<NewsResponse>> get() = _newsTopicResponseLiveData
    val newsSearchResponseLiveData : LiveData<ResponseHandler<NewsResponse>> get() = _newsSearchResponseLiveData

    var  newsItemsLiveData : LiveData<List<NewsItem>>

    private var countryCode : String = "us"
    private var category : String = "business"

    private var apiKeyForUse = API_KEY2

    init {
        getTopheadlines()
        getTopheadlinesOfTopic()
        newsItemsLiveData = repository.getAllNewsItem()

    }

    fun getTopheadlines(){
        viewModelScope.launch(Dispatchers.IO) {
            _newsResponseLiveData.postValue(ResponseHandler.Loading())
            if(isNetworkAvailable()){
                val response = repository.getTopHeadlines(countryCode, apiKeyForUse)
                _newsResponseLiveData.postValue(handleResponse(response))
            } else {
                _newsResponseLiveData.postValue(ResponseHandler.Error("No internet"))
            }

        }
    }

    fun getTopheadlinesOfTopic(){
        viewModelScope.launch(Dispatchers.IO) {
            _newsTopicResponseLiveData.postValue(ResponseHandler.Loading())
            if(isNetworkAvailable()){
                val response = repository.getTopHeadlinesOfTopic(countryCode,category ,apiKeyForUse)
                _newsTopicResponseLiveData.postValue(handleResponse(response))
            } else{
                _newsTopicResponseLiveData.postValue(ResponseHandler.Error("No internet"))
            }

        }
    }

    fun getSearchNews(query : String="bitcoin"){
        viewModelScope.launch(Dispatchers.IO) {
            _newsSearchResponseLiveData.postValue(ResponseHandler.Loading())
            if(isNetworkAvailable()){
                val response = repository.getSearchNews(query = query, apiKeyForUse)
                _newsSearchResponseLiveData.postValue(handleResponse(response))
            } else {
                _newsSearchResponseLiveData.postValue(ResponseHandler.Error("No internet"))
            }

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


    ///for internet check
    fun isNetworkAvailable(): Boolean {
        val context = app.applicationContext ?: return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}