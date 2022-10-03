package com.hazem.data.repository

import androidx.lifecycle.LiveData
import com.hazem.data.ToDoDao
import com.hazem.data.model.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {
    val getAllData:LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insert(toDoData)
    }
}