package com.hazem.presentation.ui.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.hazem.data.model.Priority
import com.hazem.data.model.ToDoData
import com.hazem.data.viewmodel.SharedViewModel
import com.hazem.data.viewmodel.ToDoViewModel
import com.hazem.todoapplication.R


class AddFragment : Fragment(),MenuProvider {
   private val toDoViewModel:ToDoViewModel by viewModels()
   private val mSharedViewModel:SharedViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
        view?.findViewById<Spinner>(R.id.spinner)!!.onItemSelectedListener=mSharedViewModel.listener

    }
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
       menuInflater.inflate(R.menu.add_fragment_menu,menu)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
     if(menuItem.itemId==R.id.menu_add) {
         insertToDatabase()
     return true
     }

        return false
    }

    private fun insertToDatabase() {
     val mTitle= requireView().findViewById<TextInputEditText>(R.id.et_input_title).text.toString()
     val mDescription= requireView().findViewById<TextInputEditText>(R.id.et_input_description).text.toString()
     val priority=requireView().findViewById<Spinner>(R.id.spinner).selectedItem.toString()
        if(mSharedViewModel.verifyDataFromUser(mTitle,mDescription )){
         val newData=ToDoData(0,mTitle,mSharedViewModel.parsePriority(priority),mDescription)
       toDoViewModel.insertData(newData)
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
            Toast.makeText(requireContext(),"Successfully added",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(requireContext(),"Please fill out all fields",Toast.LENGTH_LONG).show()
        }
    }




}