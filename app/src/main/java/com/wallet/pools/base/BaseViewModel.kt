package com.wallet.pools.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wallet.pools.util.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val contextProvider: CoroutineContextProvider) : ViewModel() {

    private val job: Job = Job()

    protected fun launchCoroutineIO(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(contextProvider.io + job) {
            ensureActive()

            block()
        }
    }

    protected fun launchCoroutineMain(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(contextProvider.main + job) {
            ensureActive()

            block()
        }
    }

    public override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
