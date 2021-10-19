package com.ysanjeet535.newsbox.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ysanjeet535.newsbox.data.remote.dto.Article


@Entity(tableName = "newsitem")
data class NewsItem(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String,
    val urlToImage: String?
){
    companion object{
        fun NewsItem.mapToArticle(newsItem: NewsItem) : Article{
            return Article(
                author, content, description, publishedAt, source, title, url, urlToImage
            )
        }

    }

}