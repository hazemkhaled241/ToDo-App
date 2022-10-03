package com.hazem.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hazem.data.model.ToDoData

@Dao
interface ToDoDao {
    @Query(value = "SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData():LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDoData: ToDoData)

}