package com.ancient.essentials.view.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ancientinc on 22/04/20.
 **/
abstract class BaseAdapter<T : RecyclerView.ViewHolder?>(protected var mValues: MutableList<Any>) :
    RecyclerView.Adapter<T>() {

    override fun getItemCount(): Int = if (mValues.isNotEmpty()) mValues.size else 0

    fun updateDate(aAdapterData: MutableList<Any>) {
        this.mValues = aAdapterData
        this.notifyDataSetChanged()
    }
}