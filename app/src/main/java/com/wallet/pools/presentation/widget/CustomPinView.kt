package com.wallet.pools.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.wallet.pools.R
import timber.log.Timber
import java.util.Stack

class CustomPinView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val frmBack : FrameLayout
    private val tvTitle : TextView
    private val tvSubTitle : TextView
    private val pinDisplay1 : ImageView
    private val pinDisplay2 : ImageView
    private val pinDisplay3 : ImageView
    private val pinDisplay4 : ImageView
    private val pinDisplay5 : ImageView
    private val pinDisplay6 : ImageView

    private val btnKey0 : Button
    private val btnKey1 : Button
    private val btnKey2 : Button
    private val btnKey3 : Button
    private val btnKey4 : Button
    private val btnKey5 : Button
    private val btnKey6 : Button
    private val btnKey7 : Button
    private val btnKey8 : Button
    private val btnKey9 : Button
    private val llBiometric : LinearLayout
    private val btnKeyDelete : Button
    private  var pinDisplays: ArrayList<ImageView>
    private  var pinButtons: ArrayList<Button>
    private  var pinViewInterface: PinViewInterface?=null
    private var actionEnoughPass: () -> Unit = {}
    private var actionBack: () -> Unit = {}
    private  var pinStack: Stack<Int> = Stack()


    init {
        LayoutInflater.from(context).inflate(R.layout.view_custom_pin, this, true)

        frmBack = findViewById(R.id.frmBack)
        tvTitle = findViewById(R.id.tvTitle)
        tvSubTitle = findViewById(R.id.tvSubTitle)
        pinDisplay1 = findViewById(R.id.pinDisplay1)
        pinDisplay2 = findViewById(R.id.pinDisplay2)
        pinDisplay3 = findViewById(R.id.pinDisplay3)
        pinDisplay4 = findViewById(R.id.pinDisplay4)
        pinDisplay5 = findViewById(R.id.pinDisplay5)
        pinDisplay6 = findViewById(R.id.pinDisplay6)

        btnKey0 = findViewById(R.id.btnKey0)
        btnKey1 = findViewById(R.id.btnKey1)
        btnKey2 = findViewById(R.id.btnKey2)
        btnKey3 = findViewById(R.id.btnKey3)
        btnKey4 = findViewById(R.id.btnKey4)
        btnKey5 = findViewById(R.id.btnKey5)
        btnKey6 = findViewById(R.id.btnKey6)
        btnKey7 = findViewById(R.id.btnKey7)
        btnKey8 = findViewById(R.id.btnKey8)
        btnKey9 = findViewById(R.id.btnKey9)
        llBiometric = findViewById(R.id.llBiometric)
        btnKeyDelete = findViewById(R.id.btnKeyDelete)

        pinDisplays = buildArray {
            add(pinDisplay1)
            add(pinDisplay2)
            add(pinDisplay3)
            add(pinDisplay4)
            add(pinDisplay5)
            add(pinDisplay6)
        }

        pinButtons = buildArray {
            add(btnKey0)
            add(btnKey1)
            add(btnKey2)
            add(btnKey3)
            add(btnKey4)
            add(btnKey5)
            add(btnKey6)
            add(btnKey7)
            add(btnKey8)
            add(btnKey9)
            add(btnKeyDelete)
        }
        frmBack.setOnClickListener {
            actionBack()
        }
        eventClickButton()
    }

    private fun eventClickButton() {
        for ((idx, view) in pinButtons.withIndex()) {
            view.setOnClickListener {
                clickPin(idx)
            }
        }
    }
    private fun clickPin(number: Int) {
        when (number) {
            in 0..9 -> pinStack = addPin(pinStack, number)
            10 -> pinStack= delPin(pinStack)
        }
    }

    private fun addPin(stack: Stack<Int>, number: Int): Stack<Int> {

        val newStack = Stack<Int>()
        newStack.addAll(stack)
        if (newStack.size < 6) newStack.push(number)
        pinViewInterface?.sendData(newStack)
        changeViewPin(newStack)
        return newStack
    }

    private fun delPin(stack: Stack<Int>): Stack<Int> {
        val newStack = Stack<Int>()
        newStack.addAll(stack)
        if (newStack.isNotEmpty()) newStack.pop()
        changeViewPin(newStack)
        return newStack

    }

    private fun changeViewPin(stack: Stack<Int>){
        for (idx in 0 until pinDisplays.size) {
            pinDisplays[idx].setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    if (idx < stack.size) R.drawable.pin_enter else R.drawable.pin_empty
                )
            )
        }

        if (stack.size == 6) {
            actionEnoughPass()
        }
    }

    fun setPinClickListener(listener:PinViewInterface?){
        pinViewInterface=listener
    }

    fun actionEnoughPass(action: () -> Unit){
        actionEnoughPass = action
    }
    fun enoughPin(stack: Stack<Int>) :Boolean{
        return stack.size == 6
    }

    fun clearPin(){
        val newStack = Stack<Int>()
        pinStack =  newStack
        Timber.i("TTT clearPin : ${pinStack.size}")
    }
    fun setTextPin(textTitle : String , textSubTitle : String){
        tvTitle.text = textTitle
        tvSubTitle.text = textSubTitle
    }
    fun showBiometric(show : Boolean){
        if(show){
            llBiometric.visibility = View.VISIBLE
        }else {
            llBiometric.visibility = View.GONE
        }
    }
    fun setButtonBack(action: () -> Unit){
        actionBack = action
    }
    private fun <V> buildArray(build: ArrayList<V>.() -> Unit): ArrayList<V> {
        val arrayList = ArrayList<V>()
        arrayList.build()
        return arrayList
    }

    interface PinViewInterface {
        fun sendData(stack: Stack<Int>)
    }
}