package com.wallet.pools.presentation.screen.textPhraseFragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wallet.pools.R
import com.wallet.pools.base.BaseAdapter
import com.wallet.pools.databinding.ItemTextChooseFillBinding
import com.wallet.pools.presentation.screen.textPhraseFragment.model.TextPhrase
import javax.inject.Inject


class ChooseTextPhraseAdapter @Inject constructor(
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
            ItemTextChooseFillBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ItemTextChooseFillHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        (holder as ChooseTextPhraseAdapter.ItemTextChooseFillHolder).bind(item)
    }

    inner class ItemTextChooseFillHolder(
        private val binding: ItemTextChooseFillBinding,
    ) : RecyclerView.ViewHolder(binding.root), Binder<TextPhrase> {
        override fun bind(item: TextPhrase) {
            binding.apply {
                tvTextChoose.text = item.text
                if(item.isSelected){
                    llTextChoose.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_ll_conner_radius_color_06c270)
                }else {
                    llTextChoose.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_radius_conner_gray)
                }
            }

            itemView.setOnClickListener {
                onClickChooseTextListener?.let {
                    it(item)
                }
            }
        }
    }
}
