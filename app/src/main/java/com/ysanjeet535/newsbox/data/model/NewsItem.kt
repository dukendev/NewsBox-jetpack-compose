package com.ysanjeet535.newsbox.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "newsitem")
data class NewsItem(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
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

    }

}