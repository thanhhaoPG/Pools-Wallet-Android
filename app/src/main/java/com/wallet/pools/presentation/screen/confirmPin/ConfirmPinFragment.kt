package com.wallet.pools.presentation.screen.confirmPin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentConfirmPinBinding
import com.wallet.pools.databinding.FragmentPinViewBinding
import com.wallet.pools.extenstion.showToast
import com.wallet.pools.presentation.screen.main.MainActivity
import com.wallet.pools.presentation.screen.pin.PinViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ConfirmPinFragment : BaseFragment<FragmentConfirmPinBinding, BaseViewModel>() {


    override val viewModel: PinViewModel by viewModels()
    private lateinit var pinDisplays: ArrayList<ImageView>
    private lateinit var pinButtons: ArrayList<Button>
    private val args : ConfirmPinFragmentArgs by navArgs()
    override fun getViewBinding(): FragmentConfirmPinBinding =
        FragmentConfirmPinBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        findNavController().navigate(R.id.pinFragment)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
    }


    private fun initViewStateChange() {


    }

    private fun initView() {
        binding.frmBack.setOnClickListener {
            onBackFragment()
        }
        pinDisplays = buildArray {
            add(binding.pinDisplay1)
            add(binding.pinDisplay2)
            add(binding.pinDisplay3)
            add(binding.pinDisplay4)
            add(binding.pinDisplay5)
            add(binding.pinDisplay6)
        }

        pinButtons = buildArray {
            add(binding.pinKey0)
            add(binding.pinKey1)
            add(binding.pinKey2)
            add(binding.pinKey3)
            add(binding.pinKey4)
            add(binding.pinKey5)
            add(binding.pinKey6)
            add(binding.pinKey7)
            add(binding.pinKey8)
            add(binding.pinKey9)
            add(binding.pinKeyBack)
        }

        onListener()
        observeViewModelData()
    }

    private fun observeViewModelData() {
        viewModel.pinStack.observe(viewLifecycleOwner) {
            for (idx in 0 until pinDisplays.size) {
                pinDisplays[idx].setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        if (idx < it.size) R.drawable.pin_enter else R.drawable.pin_empty
                    )
                )
            }

            if (it.size == 6) {
                if(args.pin == viewModel.pinStack.value!!.toString()){
                   findNavController().navigate(R.id.textPhraseFragment)
                }else {
                    requireActivity().showToast("Nhập sai Vui lòng nhập lại ")
                    viewModel.clearPin()
                }
            }
        }
    }

    private fun onListener() {
        for ((idx, view) in pinButtons.withIndex()) {
            view.setOnClickListener {
                viewModel.clickPin(idx)
            }
        }
    }
    private fun <V> buildArray(build: ArrayList<V>.() -> Unit): ArrayList<V> {
        val arrayList = ArrayList<V>()
        arrayList.build()
        return arrayList
    }


}