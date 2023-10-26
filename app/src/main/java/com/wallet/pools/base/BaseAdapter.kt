package com.wallet.pools.base

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected abstract val differ: AsyncListDiffer<T>

    var list: List<T>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position in list.indices) {
            (holder as Binder<T>).bind(list[position])
        }
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override fun getItemCount(): Int {
        return list.size
    }

    protected var onItemClickListener: ((T) -> Unit)? = null

    protected var onClickFillTextListener: ((List<T>) -> Unit)? = null
    protected var onClickChooseTextListener: ((List<T>) -> Unit)? = null


    fun setItemClickListener(listener: (T) -> Unit) {
        onItemClickListener = listener
    }
    fun setClickFillTextListener(listener: (List<T>) -> Unit) {
        onClickFillTextListener = listener
    }
    fun setClickChooseTextListener(listener: (List<T>) -> Unit) {
        onClickChooseTextListener = listener
    }


    interface Binder<in T> {
        fun bind(item: T)
    }
}
