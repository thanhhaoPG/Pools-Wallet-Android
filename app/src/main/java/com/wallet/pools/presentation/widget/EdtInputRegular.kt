package com.wallet.pools.presentation.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import com.wallet.pools.R


@SuppressLint("ClickableViewAccessibility")
class EdtInputRegular(context: Context, attrs: AttributeSet? = null) :
    AppCompatEditText(context, attrs) {

    init {
        applyCustomFont()
    }

    private fun applyCustomFont() {
        val customTypeface = ResourcesCompat.getFont(context, R.font.poppins_regular)
        typeface = customTypeface

        setHintTextColor(ContextCompat.getColorStateList(context, R.color.color_hint_text_8f8f8f))

    }


}