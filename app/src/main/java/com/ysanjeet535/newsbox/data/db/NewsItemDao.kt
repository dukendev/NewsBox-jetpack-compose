package com.ysanjeet535.newsbox.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ysanjeet535.newsbox.data.model.NewsItem

@Dao
interface NewsItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsItem(newsItem: NewsItem)

    @Delete
    suspend fun deleteNewsItem(newsItem: NewsItem)

    @Query("DELETE FROM newsitem")
    suspend fun deleteAllNews()


    @Query("SELECT * FROM newsitem")
    fun getAllNewsItem() : LiveData<List<NewsItem>>

}