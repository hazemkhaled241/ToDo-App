package com.hazem.todoapplication.add

import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.hazem.todoapplication.R


class AddFragment : Fragment(),MenuProvider {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.addMenuProvider(this)
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
       menuInflater.inflate(R.menu.add_fragment_menu,menu)
        val searchItem = menu.findItem(R.id.menu_search)
        val sortByItem = menu.findItem(R.id.menu_sortBy)
        val deleteAllItem = menu.findItem(R.id.menu_deleteAll)
        searchItem.isVisible = false
        sortByItem.isVisible=false
        deleteAllItem.isVisible=false
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }



}