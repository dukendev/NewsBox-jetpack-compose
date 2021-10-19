package com.ysanjeet535.newsbox.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ysanjeet535.newsbox.data.model.NewsItem


@Database(entities = [NewsItem::class],version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsItemDatabase : RoomDatabase() {
    abstract fun newsItemDao() : NewsItemDao

    companion object{
        private var INSTANCE: NewsItemDatabase? = null

        fun getDatabase(context: Context) : NewsItemDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NewsItemDatabase::class.java,
                        "newsItemDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}