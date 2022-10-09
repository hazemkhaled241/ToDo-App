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
   @Update
   suspend fun update(toDoData: ToDoData)
    @Delete
    suspend fun delete(toDoData: ToDoData)
    @Query(value = "DELETE FROM todo_table")
    suspend fun deleteAll()
    @Query(value = "SELECT * FROM todo_table WHERE title LIKE :query")
    fun searchInDataBase(query: String):LiveData<List<ToDoData>>
}