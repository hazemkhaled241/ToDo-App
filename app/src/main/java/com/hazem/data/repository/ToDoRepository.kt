package com.hazem.data.repository

import androidx.lifecycle.LiveData
import com.hazem.data.ToDoDao
import com.hazem.data.model.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {
    val getAllData:LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insert(toDoData)
    }
    suspend fun updateData(toDoData: ToDoData){
        toDoDao.update(toDoData)
    }
    suspend fun deleteData(toDoData: ToDoData){
        toDoDao.delete(toDoData)
    }
    suspend fun deleteAll(){
        toDoDao.deleteAll()
    }
}