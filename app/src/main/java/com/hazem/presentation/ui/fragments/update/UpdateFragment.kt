package com.hazem.presentation.ui.fragments.update

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
import androidx.navigation.fragment.navArgs
import com.hazem.data.model.Priority
import com.hazem.data.model.ToDoData
import com.hazem.data.viewmodel.SharedViewModel
import com.hazem.data.viewmodel.ToDoViewModel
import com.hazem.todoapplication.R

class UpdateFragment : Fragment(),MenuProvider {
private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel:SharedViewModel by viewModels()
    private val mToDoViewModel:ToDoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
        view.findViewById<EditText>(R.id.et_input_title_update).setText(args.currentItem.title)
        view.findViewById<EditText>(R.id.et_update_input_description).setText(args.currentItem.description)
        view.findViewById<Spinner>(R.id.spinner_update).setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
        view.findViewById<Spinner>(R.id.spinner_update).onItemSelectedListener=mSharedViewModel.listener
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
       menuInflater.inflate(R.menu.update_fragment_menu,menu)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId==R.id.menu_save){
            updateData()
        return true
        }
        return false
    }

    private fun updateData() {
        val title=requireView().findViewById<EditText>(R.id.et_input_title_update).text.toString()
        val description=requireView().findViewById<EditText>(R.id.et_update_input_description).text.toString()
        val  priority=requireView().findViewById<Spinner>(R.id.spinner_update).selectedItem.toString()
        if(mSharedViewModel.verifyDataFromUser(title,description)){
        val updatedItem=ToDoData(args.currentItem.id,title, mSharedViewModel.parsePriority(priority),description)
        mToDoViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(),"Updated Successfully",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"Please fill out all fields",Toast.LENGTH_LONG).show()

        }

    }

}