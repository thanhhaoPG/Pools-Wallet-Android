package com.wallet.pools.presentation.screen.textPhraseFragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wallet.pools.R
import com.wallet.pools.base.BaseAdapter
import com.wallet.pools.databinding.ItemFillTextPhraseBinding
import com.wallet.pools.presentation.screen.textPhraseFragment.model.TextPhrase
import javax.inject.Inject


class FillTextPhraseAdapter @Inject constructor(
) : BaseAdapter<TextPhrase>() {
    private val diffCallback = object : DiffUtil.ItemCallback<TextPhrase>() {
        override fun areItemsTheSame(
            oldItem: TextPhrase,
            newItem: TextPhrase
        ): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }

        override fun areContentsTheSame(
            oldItem: TextPhrase,
            newItem: TextPhrase
        ): Boolean {
            return oldItem.text == newItem.text
        }

    }

    override val differ = AsyncListDiffer(this, diffCallback)


    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemFillTextPhraseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ItemFillTextPhraseHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        (holder as FillTextPhraseAdapter.ItemFillTextPhraseHolder).bind(item)
    }

    inner class ItemFillTextPhraseHolder(
        private val binding: ItemFillTextPhraseBinding,
    ) : RecyclerView.ViewHolder(binding.root), Binder<TextPhrase> {
        override fun bind(item: TextPhrase) {
            binding.apply {
                tvFillTextPhrase.text = item.text
                tvNumber.text = (position + 1).toString()
                if(item.isSelected){
                    llFillText.background = ContextCompat.getDrawable(itemView.context,R.drawable.bg_radius_conner_graph_green)
                }else {
                    llFillText.background = ContextCompat.getDrawable(itemView.context,R.drawable.bg_radius_conner_graph_gray)
                }
            }
            itemView.setOnClickListener {
                onClickFillTextListener?.let {
                    it(list)
                }
            }
        }
    }
}
