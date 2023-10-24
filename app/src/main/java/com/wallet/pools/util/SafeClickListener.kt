package com.wallet.pools.util

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineClickHandler(
    private val defaultInterval: Long = 200L,
    private val onSafeClick: (View) -> Unit
) : View.OnClickListener {

    private var clickJob: Job? = null

    override fun onClick(v: View) {
        clickJob?.cancel() // Cancel the previous job if it exists

        clickJob = CoroutineScope(Dispatchers.Main).launch {
            delay(defaultInterval)

            // Delay is over; handle the click event
            onSafeClick(v)
        }
    }
}

fun View.setSafeOnClickListener(
    onSafeClick: (View) -> Unit
) {
    val coroutineClickListener = CoroutineClickHandler {
        onSafeClick(it)
    }
    setOnClickListener(coroutineClickListener)
}
