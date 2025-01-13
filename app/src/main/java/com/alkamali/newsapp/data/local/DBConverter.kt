package com.alkamali.newsapp.data.local

import androidx.room.TypeConverter

class DBConverter {
    private val separator = ","

    @TypeConverter
    fun convertList2Str(list: List<String>): String{
        val stringBuilder = StringBuilder()
        for (str in list){
            stringBuilder.append(str).append(separator)
        }
       return stringBuilder.toString().substringBeforeLast(separator)
    }
    @TypeConverter
    fun convertStr2List(string: String): List<String>{
        return string.split(separator)
    }

}
