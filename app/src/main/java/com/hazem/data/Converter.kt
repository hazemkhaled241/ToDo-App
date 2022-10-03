package com.hazem.data

import androidx.room.TypeConverter
import com.hazem.data.model.Priority

class Converter {
    @TypeConverter
    fun fromPriority(priority: Priority):String{
        return priority.name
    }
    @TypeConverter
    fun toPriority(string: String): Priority {
        return Priority.valueOf(string)
    }

}