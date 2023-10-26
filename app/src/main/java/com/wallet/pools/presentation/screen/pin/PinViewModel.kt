package com.wallet.pools.presentation.screen.pin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.util.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Stack
import javax.inject.Inject


@HiltViewModel
class PinViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    ) : BaseViewModel(contextProvider) {

    private val _pinStack: MutableLiveData<Stack<Int>> = MutableLiveData(Stack<Int>())
    var pinStack: LiveData<Stack<Int>> = _pinStack

    fun clickPin(number: Int) {
        when (number) {
            in 0..9 -> _pinStack.value = addPin(_pinStack.value!!, number)
            10 -> _pinStack.value = delPin(_pinStack.value!!)
        }
    }


    private fun addPin(stack: Stack<Int>, number: Int): Stack<Int> {
        val newStack = Stack<Int>()
        newStack.addAll(stack)
        if (newStack.size < 6) newStack.push(number)
        return newStack
    }

    private fun delPin(stack: Stack<Int>): Stack<Int> {
        val newStack = Stack<Int>()
        newStack.addAll(stack)
        if (newStack.isNotEmpty()) newStack.pop()
        return newStack
    }
    fun clearPin(){
        val newStack = Stack<Int>()
        _pinStack.value =  newStack
    }

}