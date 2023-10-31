package com.wallet.pools.presentation.screen.confirmPin

import android.os.Bundle
import android.view.View

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentConfirmPinBinding
import com.wallet.pools.extenstion.showToast
import com.wallet.pools.presentation.screen.pin.PinViewModel
import com.wallet.pools.presentation.widget.CustomPinView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.Stack


@AndroidEntryPoint
class ConfirmPinFragment : BaseFragment<FragmentConfirmPinBinding, BaseViewModel>() {


    override val viewModel: PinViewModel by viewModels()
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
        binding.apply {
            customPinView.setTextPin("Confirm again  your pin number","(This pincode will unlock your Pools wallet only on this device)")
            customPinView.showBiometric(false)
            customPinView.setButtonBack {
                onBackFragment()
            }
            customPinView.setPinClickListener(object : CustomPinView.PinViewInterface{
                override fun sendData(stack: Stack<Int>) {
                    if(customPinView.enoughPin(stack)){
                        if(args.pin == stack.toString()){
                            findNavController().navigate(R.id.recoveryFragment)
                        }else {
                            requireActivity().showToast("Nhập sai Vui lòng nhập lại ")
                            Timber.i("TTT sendData : $stack")
                            customPinView.clearPin()
                        }
                    }
                }

            })
        }
    }




}