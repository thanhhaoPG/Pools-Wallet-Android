package com.wallet.pools.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.wallet.pools.databinding.LayoutEmptyBinding

class EmptyView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val binding: LayoutEmptyBinding

    init {
        binding = LayoutEmptyBinding.inflate(LayoutInflater.from(context), this, true)


    }


    fun setText(text: String) {
        binding.tvDes.text = text
    }


}
