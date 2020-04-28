package com.ancient.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ancient.essentials.view.adapter.BaseAdapter

/**
 * Created by ancientinc on 23/04/20.
 **/

class SimpleAdapter(aItems: MutableList<String>) :
    BaseAdapter<SimpleViewHolder>(aItems.toMutableList()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        return SimpleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                android.R.layout.simple_list_item_1,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.textItem.text = mValues[position] as String
    }
}

class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textItem = itemView.findViewById<TextView>(android.R.id.text1)
}