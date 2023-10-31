package com.wallet.pools.presentation.screen.recovery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wallet.pools.base.BaseAdapter
import com.wallet.pools.databinding.ItemRecoveryBinding
import javax.inject.Inject

class ListRecoveryAdapter @Inject constructor(
) : BaseAdapter<String>() {

    private val diffCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

    }

    override val differ = AsyncListDiffer(this, diffCallback)


    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemRecoveryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ItemRecoveryHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        (holder as ListRecoveryAdapter.ItemRecoveryHolder).bind(item)
    }

    inner class ItemRecoveryHolder(
        private val binding: ItemRecoveryBinding,
    ) : RecyclerView.ViewHolder(binding.root), Binder<String> {
        override fun bind(item: String) {
            binding.apply {
                tvRecovery.text = item
                tvNumber.text = bindingAdapterPosition.toString()
            }
        }
    }
}
