package com.hazem.presentation.ui.fragments.list.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hazem.data.model.ToDoData
import com.hazem.todoapplication.databinding.RowLayoutBinding

class ListAdapter :RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    var dataList= emptyList<ToDoData>()
    class MyViewHolder(private val binding:RowLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(toDoData: ToDoData){
       binding.toDoData=toDoData
       //binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater=LayoutInflater.from(parent.context)
                 val binding=RowLayoutBinding.inflate(layoutInflater,parent,false)
                 return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       /*holder.itemView.findViewById<TextView>(R.id.tv_title).text=dataList[position].title
       holder.itemView.findViewById<TextView>(R.id.tv_description).text=dataList[position].description
        holder.itemView.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }
        when(dataList[position].priority){
           Priority.HiIGH-> {holder.itemView.findViewById<CardView>(R.id.cardView_priority).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.red) )}
           Priority.MEDIUM-> {holder.itemView.findViewById<CardView>(R.id.cardView_priority).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.yellow) )}
           Priority.LOW-> {holder.itemView.findViewById<CardView>(R.id.cardView_priority).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.green) )}
        }*/
        var currentItem=dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
     return dataList.size
    }
    fun setData(toDoData: List<ToDoData>){
        val toDoDiffUtil=ToDoDiffUtil(dataList,toDoData)
        val diffUtilResult=DiffUtil.calculateDiff(toDoDiffUtil)
         this.dataList=toDoData
         diffUtilResult.dispatchUpdatesTo(this)
    }
}