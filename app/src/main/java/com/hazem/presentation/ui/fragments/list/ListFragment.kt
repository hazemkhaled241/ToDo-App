package com.hazem.presentation.ui.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.hazem.data.viewmodel.SharedViewModel
import com.hazem.data.viewmodel.ToDoViewModel
import com.hazem.presentation.ui.fragments.list.adapter.ListAdapter
import com.hazem.todoapplication.R
import com.hazem.todoapplication.databinding.FragmentListBinding

class ListFragment : Fragment(),MenuProvider {
private lateinit var recyclerView:RecyclerView
private val toDoViewModel:ToDoViewModel by viewModels()
private val mSharedViewModel:SharedViewModel by viewModels()
private var _binding:FragmentListBinding?=null
private val binding get() = _binding!!
    private val adapter: ListAdapter by lazy { ListAdapter() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
         _binding=FragmentListBinding.inflate(inflater,container,false)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
        setUpRecyclerView()
        toDoViewModel.getAllData.observe(viewLifecycleOwner) {
           mSharedViewModel.setIsEmptyList(it)
            adapter.setData(it)
        }
        binding.lifecycleOwner=this
        binding.mSharedViewModel=mSharedViewModel

    }

    private fun setUpRecyclerView() {
        recyclerView=binding.rvList
        recyclerView.adapter=adapter
        swipeToDelete(recyclerView)
    }
    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallBack=object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
             val currItem=adapter.dataList[viewHolder.adapterPosition]
             toDoViewModel.deleteData(currItem)
                Toast.makeText(requireContext(),"${currItem.title} deleted successfully",Toast.LENGTH_LONG).show()
            }
        }
        val itemTouchHelper=ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.list_fragment_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      when(menuItem.itemId){
          R.id.menu_deleteAll->{deleteAll()
              return true}
      }

        return true
    }

    private fun deleteAll() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            toDoViewModel.deleteAll()
            Toast.makeText(requireContext(),"Successfully Deleted", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete EveryThing!")
        builder.setMessage("Are you sure you want to remove everything?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}