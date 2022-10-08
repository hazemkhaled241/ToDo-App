package com.hazem.data.viewmodel

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hazem.data.model.Priority
import com.hazem.data.model.ToDoData
import com.hazem.todoapplication.R

class SharedViewModel(application: Application):AndroidViewModel(application) {
   val emptyDataBase:MutableLiveData<Boolean> = MutableLiveData(true)
  val listener:AdapterView.OnItemSelectedListener=object :
    AdapterView.OnItemSelectedListener{
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
     when(position) {
         0 ->{(parent?.getChildAt(0)as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
         1 ->{(parent?.getChildAt(0)as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
         2 ->{(parent?.getChildAt(0)as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
     }
      }

      override fun onNothingSelected(p0: AdapterView<*>?) {}

  }

   fun parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" ->{
                Priority.HiIGH}
            "Medium Priority" ->{
                Priority.MEDIUM}
            "Low Priority" ->{
                Priority.LOW}
            else-> Priority.LOW

        }
    }


     fun verifyDataFromUser(mTitle: String, mDescription: String): Boolean {
        return mTitle.isNotEmpty()&&mDescription.isNotEmpty()
    }

    fun setIsEmptyList(toDoData: List<ToDoData>?) {
     emptyDataBase.value=toDoData!!.isEmpty()
    }
}