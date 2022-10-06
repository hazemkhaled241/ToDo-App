package com.hazem.presentation.ui.fragments.list

import android.os.Bundle
import android.view.*

import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hazem.data.ToDoDataBase
import com.hazem.data.viewmodel.ToDoViewModel
import com.hazem.todoapplication.R

class ListFragment : Fragment(),MenuProvider {
lateinit var recyclerView:RecyclerView
private val toDoViewModel:ToDoViewModel by viewModels()
    private val adapter:ListAdapter by lazy { ListAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_list, container, false)
      val floatingActionButton=view.findViewById<FloatingActionButton>(R.id.floatingActionButton)

          floatingActionButton.setOnClickListener {
          findNavController().navigate(R.id.action_listFragment_to_addFragment)
      }

    return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
        recyclerView=view.findViewById<RecyclerView>(R.id.rv_list)
        recyclerView.adapter=adapter
        toDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {
         adapter.setData(it)
        })
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.list_fragment_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {


        return true
    }

}