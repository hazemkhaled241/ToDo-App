package com.hazem.presentation.ui.fragments

import android.os.Build
import android.view.View
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hazem.data.model.Priority
import com.hazem.data.model.ToDoData
import com.hazem.data.viewmodel.SharedViewModel
import com.hazem.presentation.ui.fragments.list.ListFragmentDirections
import com.hazem.presentation.ui.fragments.update.UpdateFragmentDirections
import com.hazem.todoapplication.R

class BindingAdapters {
    companion object {
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddList(view: FloatingActionButton, navigate: Boolean) {
            if (navigate) {
                view.setOnClickListener {
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("showNoDataImage")
        @JvmStatic
        fun showNoDataImage(view: View, mutableLiveData: MutableLiveData<Boolean>) {
            when (mutableLiveData.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
                else -> {}
            }

        }

        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(spinner: Spinner, priority: Priority) {
            when (priority) {
                Priority.HiIGH ->{ spinner.setSelection(0)}
                Priority.MEDIUM -> {spinner.setSelection(1)}
                Priority.LOW -> {spinner.setSelection(2)}
            }

        }
        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView:CardView,priority: Priority){
            when(priority){
                Priority.HiIGH->{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        cardView.setCardBackgroundColor(cardView.context.getColor(R.color.red))
                    }
                }
                Priority.MEDIUM->{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
                    }
                }
                Priority.LOW->{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))
                    }
                }
            }
        }
        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun sendDataToUpdateFragment(constraintLayout: ConstraintLayout,currItem: ToDoData){
            constraintLayout.setOnClickListener {
                val action=ListFragmentDirections.actionListFragmentToUpdateFragment(currItem)
                constraintLayout.findNavController().navigate(action)
            }

        }

    }
}