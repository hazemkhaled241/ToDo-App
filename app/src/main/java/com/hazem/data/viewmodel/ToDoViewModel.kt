package com.hazem.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hazem.data.ToDoDataBase
import com.hazem.data.model.ToDoData
import com.hazem.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application:Application):AndroidViewModel(application) {
    private val toDoDao=ToDoDataBase.getDataBase(application).toDoDao()
    private val toDoRepository:ToDoRepository = ToDoRepository(toDoDao)
    val getAllData:LiveData<List<ToDoData>> = toDoRepository.getAllData
    fun insertData(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO) {
       toDoRepository.insertData(toDoData)
        }
    }
    fun updateData(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            toDoRepository.updateData(toDoData)
        }
    }
    fun deleteData(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            toDoRepository.deleteData(toDoData)
        }
    }
    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO){
            toDoRepository.deleteAll()
        }
    }

}