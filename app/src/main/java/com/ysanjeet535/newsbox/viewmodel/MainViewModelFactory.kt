package com.ysanjeet535.newsbox.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ysanjeet535.newsbox.data.repository.NewsArticleRepository

class MainViewModelFactory(private val repository: NewsArticleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository = repository) as T
    }
}