package com.ysanjeet535.newsbox.di

import android.content.Context
import androidx.room.Room
import com.ysanjeet535.newsbox.App
import com.ysanjeet535.newsbox.data.db.NewsItemDao
import com.ysanjeet535.newsbox.data.db.NewsItemDatabase
import com.ysanjeet535.newsbox.data.remote.NewsApi
import com.ysanjeet535.newsbox.data.repository.NewsArticleRepository
import com.ysanjeet535.newsbox.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : NewsItemDatabase {
        return Room.databaseBuilder(context.applicationContext,
            NewsItemDatabase::class.java,
            "newsItemDB"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(newsItemDatabase: NewsItemDatabase) : NewsItemDao {
        return newsItemDatabase.newsItemDao()
    }

    @Singleton
    @Provides
    fun provideRepository(newsApi: NewsApi,newsItemDao: NewsItemDao) : NewsArticleRepository{
        return NewsArticleRepository(newsApi = newsApi,newsItemDao = newsItemDao)
    }

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context: Context) : App {
        return context as App
    }
}