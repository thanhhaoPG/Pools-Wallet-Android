package com.wallet.pools.presentation.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.wallet.pools.R

class TvLight(context: Context, attrs: AttributeSet? = null) :
    AppCompatTextView(context, attrs) {

    init {
        applyCustomFont()
    }

    private fun applyCustomFont() {
        val customTypeface = ResourcesCompat.getFont(context, R.font.poppins_light)
        typeface = customTypeface
    }

}