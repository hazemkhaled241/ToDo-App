package com.hazem.presentation.ui.fragments

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hazem.data.model.ToDoData
import com.hazem.data.viewmodel.SharedViewModel
import com.hazem.todoapplication.R

class BindingAdapters {
    companion object{
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
       fun navigateToAddList(view:FloatingActionButton,navigate:Boolean){
           if(navigate){
              view.setOnClickListener {
                view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
              }
           }
       }
        @BindingAdapter("showNoDataImage")
        @JvmStatic
        fun showNoDataImage(view: View, mutableLiveData: MutableLiveData<Boolean>){
          when(mutableLiveData.value){
              true -> view.visibility=View.VISIBLE
               false -> view.visibility=View.INVISIBLE
              else -> {}
          }

        }

    }
}