package com.ysanjeet535.newsbox.data.model

data class NewsItem(
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
        fun mock() : NewsItem {
            return NewsItem(
                "TIMESOFINDIA.COM",
                "Cardiovascular diseases are one of the most common ailments among people and is the leading cause of death in and around the world, as per the World Health Organization (WHO). While older people are … [+593 chars]",
                "Cardiovascular diseases are one of the most common ailments among people and is the leading cause of death in and around the world, as per the World Health Organization (WHO). While older people are more prone to heart conditions, in recent times, there has b…",
                "2021-10-11T18:30:00Z",
                Source("the-times-of-india","The Times of India"),
                "Lifestyle habits to keep your heart healthy - Times of India",
                "https://timesofindia.indiatimes.com/life-style/health-fitness/health-news/lifestyle-habits-to-keep-your-heart-healthy/photostory/86943741.cms",
                "https://static.toiimg.com/photo/86943767.cms"
            )
        }
    }

}