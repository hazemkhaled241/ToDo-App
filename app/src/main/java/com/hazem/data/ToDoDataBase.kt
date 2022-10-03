package com.hazem.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
abstract class ToDoDataBase:RoomDatabase() {
    abstract fun toDoDao():ToDoDao
    companion object{
         @Volatile
         
        private var INSTANCE: ToDoDataBase?=null
        fun getDataBase(context:Context):ToDoDataBase{
          val tempInstance= INSTANCE
            if(tempInstance!=null) {
                return tempInstance
            }
            synchronized(this){
               val instance =Room.databaseBuilder(
                   context.applicationContext,
                  ToDoDataBase::class.java,
                   "todo_dayabase"
               ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}