package com.wallet.pools.presentation.screen.pin

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel

import com.wallet.pools.databinding.FragmentPinViewBinding
import com.wallet.pools.extenstion.showToast
import com.wallet.pools.presentation.widget.CustomPinView

import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.Stack


@AndroidEntryPoint
class PinFragment : BaseFragment<FragmentPinViewBinding, BaseViewModel>() {


    override val viewModel: PinViewModel by viewModels()
    override fun getViewBinding(): FragmentPinViewBinding =
        FragmentPinViewBinding.inflate(layoutInflater)

    override fun onBackFragment() {
        findNavController().navigate(R.id.loginFragment)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
    }


    private fun initViewStateChange() {


    }

    private fun initView() {
        binding.apply {
            customPinView.setTextPin("Put in your pin number","(This pincode will unlock your Pools wallet only on this device)")
            customPinView.showBiometric(false)
            customPinView.actionEnoughPass {
                requireContext().showToast("text")
            }
            customPinView.setButtonBack {
                onBackFragment()
            }
            customPinView.setPinClickListener(object :CustomPinView.PinViewInterface{
                override fun sendData(stack: Stack<Int>) {
                    if(customPinView.enoughPin(stack)){
                        val bundle  = bundleOf("pin" to stack.toString())
                        findNavController().navigate(R.id.confirmPinFragment,bundle)
                    }
                }

            })

            }
        }



}
