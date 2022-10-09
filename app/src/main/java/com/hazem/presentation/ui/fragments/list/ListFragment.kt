package com.hazem.presentation.ui.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.view.animation.OvershootInterpolator
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hazem.data.model.ToDoData
import com.hazem.data.viewmodel.SharedViewModel
import com.hazem.data.viewmodel.ToDoViewModel
import com.hazem.presentation.ui.fragments.list.adapter.ListAdapter
import com.hazem.todoapplication.R
import com.hazem.todoapplication.databinding.FragmentListBinding
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class ListFragment : Fragment(),MenuProvider,SearchView.OnQueryTextListener {
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
        recyclerView.itemAnimator=SlideInUpAnimator(OvershootInterpolator(1f))
        swipeToDelete(recyclerView)
    }
    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallBack=object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
             val currItem=adapter.dataList[viewHolder.adapterPosition]
             toDoViewModel.deleteData(currItem)
              adapter.notifyItemRemoved(viewHolder.adapterPosition)
                restoreDeletedData(viewHolder.itemView,currItem,viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper=ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    private fun restoreDeletedData(view:View,deletedItem:ToDoData,position:Int){
     val snackBar=Snackbar.make(view,"${deletedItem.title} is Deleted",Snackbar.LENGTH_LONG)
        snackBar.setAction("Undo"){
            toDoViewModel.insertData(deletedItem)
            adapter.notifyDataSetChanged()
        }
        snackBar.show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.list_fragment_menu,menu)
        val search:MenuItem=menu.findItem(R.id.menu_search)
        val searchView: SearchView? =search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled=true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      when(menuItem.itemId){
          R.id.menu_deleteAll->{deleteAll()
              return true}
          R.id.priority_high->{toDoViewModel.sortByHighPriority.observe(viewLifecycleOwner){
              adapter.setData(it)
          }
              return true}
          R.id.priority_low->{toDoViewModel.sortByLowPriority.observe(viewLifecycleOwner){
              adapter.setData(it)
          }
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query!=null){
        searchThroughDataBase(query)
        }
        return true
    }
    override fun onQueryTextChange(query: String?): Boolean {
        if(query!=null){
            searchThroughDataBase(query)
        }
        return true
    }

    private fun searchThroughDataBase(query:String) {
        val strQuery:String="%$query%"
      toDoViewModel.searchInDataBase(strQuery).observe(viewLifecycleOwner){list->
          list?.let {
         adapter.setData(it)
          }

      }
    }

}