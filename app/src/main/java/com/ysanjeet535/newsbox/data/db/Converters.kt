package com.ysanjeet535.newsbox.data.db

import androidx.room.TypeConverter
import com.ysanjeet535.newsbox.data.model.Source

class Converters {
    @TypeConverter
    fun fromSourceToString(source: Source) : String{
        val id = source.id?:"Not available"
        val name = source.name?:"Not available"
        return "${id}#${name}"
    }

    @TypeConverter
    fun fromStringToSource(string: String) : Source{
        val id = string.dropLastWhile { it != '#' }.dropLast(1)
        val name = string.dropWhile { it != '#' }.drop(1)
        return Source(id = id,name = name)
    }
}