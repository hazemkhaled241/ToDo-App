package com.hazem.presentation.ui.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hazem.data.model.ToDoData

class ToDoDiffUtil(private val oldList:List<ToDoData>,private val newList:List<ToDoData>) :DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition]===oldList[oldItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (newList[newItemPosition].id==oldList[oldItemPosition].id)&&
                (newList[newItemPosition].title==oldList[oldItemPosition].title)&&
                (newList[newItemPosition].description==oldList[oldItemPosition].description)&&
                (newList[newItemPosition].priority==oldList[oldItemPosition].priority)
    }
}