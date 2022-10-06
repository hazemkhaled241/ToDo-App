package com.hazem.presentation.ui.fragments.list
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hazem.data.model.Priority
import com.hazem.data.model.ToDoData
import com.hazem.todoapplication.R

class ListAdapter :RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    var dataList= emptyList<ToDoData>()
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.itemView.findViewById<TextView>(R.id.tv_title).text=dataList[position].title
       holder.itemView.findViewById<TextView>(R.id.tv_description).text=dataList[position].description
        holder.itemView.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }
        when(dataList[position].priority){
           Priority.HiIGH-> {holder.itemView.findViewById<CardView>(R.id.cardView_priority).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.red) )}
           Priority.MEDIUM-> {holder.itemView.findViewById<CardView>(R.id.cardView_priority).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.yellow) )}
           Priority.LOW-> {holder.itemView.findViewById<CardView>(R.id.cardView_priority).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.green) )}
        }
    }

    override fun getItemCount(): Int {
     return dataList.size
    }
    fun setData(toDoData: List<ToDoData>){
         this.dataList=toDoData
        notifyDataSetChanged()
    }
}