package com.ysanjeet535.newsbox.data.remote.dto

import com.ysanjeet535.newsbox.data.model.NewsItem
import com.ysanjeet535.newsbox.data.model.Source

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
){
    companion object{
        fun Article.mapToNewsItem(article: Article) : NewsItem {
            return NewsItem(
                author = article.author,
                content = article.content,
                description = article.description,
                publishedAt = article.publishedAt,
                source = article.source,
                title = article.title,
                url = article.url,
                urlToImage = article.urlToImage
            )
        }
    }

}