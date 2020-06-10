package com.canlioya.customviewexercise.carousellayoutmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.canlioya.customviewexercise.R

class SimpleListAdapter (private val list: List<String>) : RecyclerView.Adapter<SimpleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return SimpleViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.textView.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.item_tv)
}