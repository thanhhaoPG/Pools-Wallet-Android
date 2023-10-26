package com.wallet.pools.presentation.screen.pin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentLoginBinding
import com.wallet.pools.databinding.FragmentPinViewBinding
import com.wallet.pools.presentation.screen.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PinFragment : BaseFragment<FragmentPinViewBinding, BaseViewModel>() {


    override val viewModel: PinViewModel by viewModels()
    private lateinit var pinDisplays: ArrayList<ImageView>
    private lateinit var pinButtons: ArrayList<Button>
    override fun getViewBinding(): FragmentPinViewBinding =
        FragmentPinViewBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        findNavController().navigateUp()

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
                findNavController().navigate(R.id.confirmPinFragment)

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